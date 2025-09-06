package androidx.webkit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public class WebMessageCompat {
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    public static final int TYPE_ARRAY_BUFFER = 1;
    public static final int TYPE_STRING;
    private final byte[] mArrayBuffer;
    private final WebMessagePortCompat[] mPorts;
    private final String mString;
    private final int mType;

    public WebMessageCompat(String s) {
        this(s, null);
    }

    public WebMessageCompat(String s, WebMessagePortCompat[] arr_webMessagePortCompat) {
        this.mString = s;
        this.mArrayBuffer = null;
        this.mPorts = arr_webMessagePortCompat;
        this.mType = 0;
    }

    public WebMessageCompat(byte[] arr_b) {
        this(arr_b, null);
    }

    public WebMessageCompat(byte[] arr_b, WebMessagePortCompat[] arr_webMessagePortCompat) {
        Objects.requireNonNull(arr_b);
        this.mArrayBuffer = arr_b;
        this.mString = null;
        this.mPorts = arr_webMessagePortCompat;
        this.mType = 1;
    }

    private void checkType(int v) {
        if(v != this.mType) {
            throw new IllegalStateException("Wrong data accessor type detected. " + this.typeToString(this.mType) + " expected, but got " + this.typeToString(v));
        }
    }

    public byte[] getArrayBuffer() {
        this.checkType(1);
        Objects.requireNonNull(this.mArrayBuffer);
        return this.mArrayBuffer;
    }

    public String getData() {
        this.checkType(0);
        return this.mString;
    }

    public WebMessagePortCompat[] getPorts() {
        return this.mPorts;
    }

    public int getType() {
        return this.mType;
    }

    private String typeToString(int v) {
        switch(v) {
            case 0: {
                return "String";
            }
            case 1: {
                return "ArrayBuffer";
            }
            default: {
                return "Unknown";
            }
        }
    }
}

