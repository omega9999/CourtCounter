package com.example.android.courtcounter;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class WrapperParcellable<T extends Serializable> implements Parcelable {

    public WrapperParcellable(@NonNull final T serializable) {
        this.mSerializable = serializable;
    }

    public static final Creator<WrapperParcellable> CREATOR = new Creator<WrapperParcellable>() {
        @Override
        public WrapperParcellable createFromParcel(Parcel in) {
            return new WrapperParcellable(in);
        }

        @Override
        public WrapperParcellable[] newArray(int size) {
            return new WrapperParcellable[size];
        }
    };

    public T getSerializable() {
        return this.mSerializable;
    }

    @Override
    public int describeContents() {
        return Parcelable.CONTENTS_FILE_DESCRIPTOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(serializable2byteArray(this.mSerializable));
    }

    private WrapperParcellable(Parcel in) {
        if (in != null) {
            final byte[] s = in.createByteArray();
            if (s != null) {
                this.mSerializable = byteArray2Serializable(s);
            }
        }
    }

    private T byteArray2Serializable(@NonNull final byte[] array) {
        try {
            final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(array));
            final Object o = ois.readObject();
            ois.close();
            return (T) o;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    private byte[] serializable2byteArray(@NonNull final T serializable) {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(serializable);
            oos.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    private static RuntimeException convertException(Exception e) {
        final RuntimeException exception = new RuntimeException(String.format("Exception generated from %1$s, message %2$s", e.getClass(), e.getMessage()));
        exception.setStackTrace(e.getStackTrace());
        return exception;
    }

    private T mSerializable;
}
