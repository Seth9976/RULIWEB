package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public abstract class Utf8 {
    static class DecodeUtil {
        static void handleFourBytes(byte b, byte b1, byte b2, byte b3, char[] arr_c, int v) throws IllegalArgumentException {
            if(DecodeUtil.isNotTrailingByte(b1) || (b << 28) + (b1 + 0x70) >> 30 != 0 || DecodeUtil.isNotTrailingByte(b2) || DecodeUtil.isNotTrailingByte(b3)) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            int v1 = (b & 7) << 18 | (b1 & 0x3F) << 12 | (b2 & 0x3F) << 6 | b3 & 0x3F;
            arr_c[v] = (char)((v1 >>> 10) + 0xD7C0);
            arr_c[v + 1] = (char)((v1 & 0x3FF) + 0xDC00);
        }

        static void handleOneByte(byte b, char[] arr_c, int v) {
            arr_c[v] = (char)b;
        }

        static void handleThreeBytes(byte b, byte b1, byte b2, char[] arr_c, int v) throws IllegalArgumentException {
            if(DecodeUtil.isNotTrailingByte(b1) || b == 0xFFFFFFE0 && b1 < 0xFFFFFFA0 || b == -19 && b1 >= 0xFFFFFFA0 || DecodeUtil.isNotTrailingByte(b2)) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            arr_c[v] = (char)((b & 15) << 12 | (b1 & 0x3F) << 6 | b2 & 0x3F);
        }

        static void handleTwoBytes(byte b, byte b1, char[] arr_c, int v) throws IllegalArgumentException {
            if(b < -62) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal leading byte in 2 bytes utf");
            }
            if(DecodeUtil.isNotTrailingByte(b1)) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal trailing byte in 2 bytes utf");
            }
            arr_c[v] = (char)((b & 0x1F) << 6 | DecodeUtil.trailingByteValue(b1));
        }

        private static char highSurrogate(int v) [...] // Inlined contents

        private static boolean isNotTrailingByte(byte b) {
            return b > -65;
        }

        static boolean isOneByte(byte b) {
            return b >= 0;
        }

        static boolean isThreeBytes(byte b) {
            return b < -16;
        }

        static boolean isTwoBytes(byte b) {
            return b < 0xFFFFFFE0;
        }

        private static char lowSurrogate(int v) [...] // Inlined contents

        private static int trailingByteValue(byte b) [...] // Inlined contents
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int v, int v1) {
            super("Unpaired surrogate at index " + v + " of " + v1);
        }
    }

    private static Utf8 DEFAULT;

    public abstract String decodeUtf8(ByteBuffer arg1, int arg2, int arg3);

    public abstract void encodeUtf8(CharSequence arg1, ByteBuffer arg2);

    public abstract int encodedLength(CharSequence arg1);

    public static Utf8 getDefault() {
        if(Utf8.DEFAULT == null) {
            Utf8.DEFAULT = new Utf8Safe();
        }
        return Utf8.DEFAULT;
    }

    public static void setDefault(Utf8 utf80) {
        Utf8.DEFAULT = utf80;
    }
}

