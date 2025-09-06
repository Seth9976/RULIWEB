package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class Internal {
    public interface EnumLite {
        int getNumber();
    }

    public interface EnumLiteMap {
        EnumLite findValueByNumber(int arg1);
    }

    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final ByteBuffer EMPTY_BYTE_BUFFER;

    static {
        byte[] arr_b = new byte[0];
        Internal.EMPTY_BYTE_ARRAY = arr_b;
        Internal.EMPTY_BYTE_BUFFER = ByteBuffer.wrap(arr_b);
    }

    public static boolean isValidUtf8(byte[] arr_b) {
        return Utf8.isValidUtf8(arr_b);
    }

    public static String toStringUtf8(byte[] arr_b) {
        try {
            return new String(arr_b, "UTF-8");
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new RuntimeException("UTF-8 not supported?", unsupportedEncodingException0);
        }
    }
}

