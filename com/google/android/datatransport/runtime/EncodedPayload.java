package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import java.util.Arrays;

public final class EncodedPayload {
    private final byte[] bytes;
    private final Encoding encoding;

    public EncodedPayload(Encoding encoding0, byte[] arr_b) {
        if(encoding0 == null) {
            throw new NullPointerException("encoding is null");
        }
        if(arr_b == null) {
            throw new NullPointerException("bytes is null");
        }
        this.encoding = encoding0;
        this.bytes = arr_b;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof EncodedPayload)) {
            return false;
        }
        return this.encoding.equals(((EncodedPayload)object0).encoding) ? Arrays.equals(this.bytes, ((EncodedPayload)object0).bytes) : false;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    @Override
    public int hashCode() {
        int v = Arrays.hashCode(this.bytes);
        return (this.encoding.hashCode() ^ 1000003) * 1000003 ^ v;
    }

    @Override
    public String toString() {
        return "EncodedPayload{encoding=" + this.encoding + ", bytes=[...]}";
    }
}

