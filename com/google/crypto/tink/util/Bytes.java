package com.google.crypto.tink.util;

import com.google.crypto.tink.subtle.Hex;
import com.google.errorprone.annotations.Immutable;
import java.util.Arrays;

@Immutable
public final class Bytes {
    private final byte[] data;

    private Bytes(byte[] arr_b, int v, int v1) {
        byte[] arr_b1 = new byte[v1];
        this.data = arr_b1;
        System.arraycopy(arr_b, v, arr_b1, 0, v1);
    }

    public static Bytes copyFrom(byte[] arr_b) {
        if(arr_b == null) {
            throw new NullPointerException("data must be non-null");
        }
        return Bytes.copyFrom(arr_b, 0, arr_b.length);
    }

    public static Bytes copyFrom(byte[] arr_b, int v, int v1) {
        if(arr_b == null) {
            throw new NullPointerException("data must be non-null");
        }
        return new Bytes(arr_b, v, v1);
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Bytes ? Arrays.equals(((Bytes)object0).data, this.data) : false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public int size() {
        return this.data.length;
    }

    public byte[] toByteArray() {
        byte[] arr_b = new byte[this.data.length];
        System.arraycopy(this.data, 0, arr_b, 0, this.data.length);
        return arr_b;
    }

    @Override
    public String toString() {
        return "Bytes(" + Hex.encode(this.data) + ")";
    }
}

