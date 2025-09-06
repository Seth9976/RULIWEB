package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class Utf8Safe extends Utf8 {
    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int v, int v1) {
            super("Unpaired surrogate at index " + v + " of " + v1);
        }
    }

    private static int computeEncodedLength(CharSequence charSequence0) {
        int v = charSequence0.length();
        int v1;
        for(v1 = 0; v1 < v && charSequence0.charAt(v1) < 0x80; ++v1) {
        }
        int v2 = v;
        while(v1 < v) {
            int v3 = charSequence0.charAt(v1);
            if(v3 < 0x800) {
                v2 += 0x7F - v3 >>> 0x1F;
                ++v1;
            }
            else {
                v2 += Utf8Safe.encodedLengthGeneral(charSequence0, v1);
                if(true) {
                    break;
                }
            }
        }
        if(v2 < v) {
            throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long)v2) + 0x100000000L));
        }
        return v2;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.emoji2.text.flatbuffer.Utf8
    public String decodeUtf8(ByteBuffer byteBuffer0, int v, int v1) throws IllegalArgumentException {
        return byteBuffer0.hasArray() ? Utf8Safe.decodeUtf8Array(byteBuffer0.array(), byteBuffer0.arrayOffset() + v, v1) : Utf8Safe.decodeUtf8Buffer(byteBuffer0, v, v1);
    }

    public static String decodeUtf8Array(byte[] arr_b, int v, int v1) {
        if((v | v1 | arr_b.length - v - v1) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", ((int)arr_b.length), v, v1));
        }
        int v2 = v + v1;
        char[] arr_c = new char[v1];
        int v3;
        for(v3 = 0; v < v2; ++v3) {
            int v4 = arr_b[v];
            if(!DecodeUtil.isOneByte(((byte)v4))) {
                break;
            }
            ++v;
            DecodeUtil.handleOneByte(((byte)v4), arr_c, v3);
        }
        int v5 = v3;
        while(v < v2) {
            int v6 = v + 1;
            int v7 = arr_b[v];
            if(DecodeUtil.isOneByte(((byte)v7))) {
                int v8 = v5 + 1;
                DecodeUtil.handleOneByte(((byte)v7), arr_c, v5);
                while(v6 < v2) {
                    int v9 = arr_b[v6];
                    if(!DecodeUtil.isOneByte(((byte)v9))) {
                        break;
                    }
                    ++v6;
                    DecodeUtil.handleOneByte(((byte)v9), arr_c, v8);
                    ++v8;
                }
                v5 = v8;
                v = v6;
                continue;
            }
            if(DecodeUtil.isTwoBytes(((byte)v7))) {
                if(v6 < v2) {
                    v += 2;
                    DecodeUtil.handleTwoBytes(((byte)v7), arr_b[v6], arr_c, v5);
                    ++v5;
                    continue;
                }
            }
            else if(!DecodeUtil.isThreeBytes(((byte)v7))) {
                if(v6 < v2 - 2) {
                    int v11 = arr_b[v6];
                    int v12 = v + 3;
                    int v13 = arr_b[v + 2];
                    v += 4;
                    DecodeUtil.handleFourBytes(((byte)v7), ((byte)v11), ((byte)v13), arr_b[v12], arr_c, v5);
                    v5 += 2;
                    continue;
                }
            }
            else if(v6 < v2 - 1) {
                int v10 = v + 2;
                v += 3;
                DecodeUtil.handleThreeBytes(((byte)v7), arr_b[v6], arr_b[v10], arr_c, v5);
                ++v5;
                continue;
            }
            throw new IllegalArgumentException("Invalid UTF-8");
        }
        return new String(arr_c, 0, v5);
    }

    public static String decodeUtf8Buffer(ByteBuffer byteBuffer0, int v, int v1) {
        if((v | v1 | byteBuffer0.limit() - v - v1) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", byteBuffer0.limit(), v, v1));
        }
        int v2 = v + v1;
        char[] arr_c = new char[v1];
        int v3;
        for(v3 = 0; v < v2; ++v3) {
            int v4 = byteBuffer0.get(v);
            if(!DecodeUtil.isOneByte(((byte)v4))) {
                break;
            }
            ++v;
            DecodeUtil.handleOneByte(((byte)v4), arr_c, v3);
        }
        int v5 = v3;
        while(v < v2) {
            int v6 = v + 1;
            int v7 = byteBuffer0.get(v);
            if(DecodeUtil.isOneByte(((byte)v7))) {
                int v8 = v5 + 1;
                DecodeUtil.handleOneByte(((byte)v7), arr_c, v5);
                while(v6 < v2) {
                    int v9 = byteBuffer0.get(v6);
                    if(!DecodeUtil.isOneByte(((byte)v9))) {
                        break;
                    }
                    ++v6;
                    DecodeUtil.handleOneByte(((byte)v9), arr_c, v8);
                    ++v8;
                }
                v5 = v8;
                v = v6;
                continue;
            }
            if(DecodeUtil.isTwoBytes(((byte)v7))) {
                if(v6 < v2) {
                    v += 2;
                    DecodeUtil.handleTwoBytes(((byte)v7), byteBuffer0.get(v6), arr_c, v5);
                    ++v5;
                    continue;
                }
            }
            else if(!DecodeUtil.isThreeBytes(((byte)v7))) {
                if(v6 < v2 - 2) {
                    int v11 = byteBuffer0.get(v6);
                    int v12 = v + 3;
                    int v13 = byteBuffer0.get(v + 2);
                    v += 4;
                    DecodeUtil.handleFourBytes(((byte)v7), ((byte)v11), ((byte)v13), byteBuffer0.get(v12), arr_c, v5);
                    v5 += 2;
                    continue;
                }
            }
            else if(v6 < v2 - 1) {
                int v10 = v + 2;
                v += 3;
                DecodeUtil.handleThreeBytes(((byte)v7), byteBuffer0.get(v6), byteBuffer0.get(v10), arr_c, v5);
                ++v5;
                continue;
            }
            throw new IllegalArgumentException("Invalid UTF-8");
        }
        return new String(arr_c, 0, v5);
    }

    @Override  // androidx.emoji2.text.flatbuffer.Utf8
    public void encodeUtf8(CharSequence charSequence0, ByteBuffer byteBuffer0) {
        if(byteBuffer0.hasArray()) {
            int v = byteBuffer0.arrayOffset();
            byteBuffer0.position(Utf8Safe.encodeUtf8Array(charSequence0, byteBuffer0.array(), byteBuffer0.position() + v, byteBuffer0.remaining()) - v);
            return;
        }
        Utf8Safe.encodeUtf8Buffer(charSequence0, byteBuffer0);
    }

    private static int encodeUtf8Array(CharSequence charSequence0, byte[] arr_b, int v, int v1) {
        int v2 = charSequence0.length();
        int v3 = v1 + v;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            int v5 = v4 + v;
            if(v5 >= v3) {
                break;
            }
            int v6 = charSequence0.charAt(v4);
            if(v6 >= 0x80) {
                break;
            }
            arr_b[v5] = (byte)v6;
        }
        if(v4 == v2) {
            return v + v2;
        }
        int v7 = v + v4;
        while(v4 < v2) {
            int v8 = charSequence0.charAt(v4);
            if(v8 < 0x80 && v7 < v3) {
                arr_b[v7] = (byte)v8;
                ++v7;
            }
            else if(v8 < 0x800 && v7 <= v3 - 2) {
                int v9 = v7 + 1;
                arr_b[v7] = (byte)(v8 >>> 6 | 960);
                v7 += 2;
                arr_b[v9] = (byte)(v8 & 0x3F | 0x80);
            }
            else if(v8 >= 0xD800 && 0xDFFF >= v8 || v7 > v3 - 3) {
                if(v7 > v3 - 4) {
                    goto label_49;
                }
                if(v4 + 1 == charSequence0.length()) {
                    throw new UnpairedSurrogateException(v4 - 1, v2);
                }
                int v11 = charSequence0.charAt(v4 + 1);
                if(!Character.isSurrogatePair(((char)v8), ((char)v11))) {
                    goto label_47;
                }
                int v12 = Character.toCodePoint(((char)v8), ((char)v11));
                arr_b[v7] = (byte)(v12 >>> 18 | 0xF0);
                arr_b[v7 + 1] = (byte)(v12 >>> 12 & 0x3F | 0x80);
                int v13 = v7 + 3;
                arr_b[v7 + 2] = (byte)(v12 >>> 6 & 0x3F | 0x80);
                v7 += 4;
                arr_b[v13] = (byte)(v12 & 0x3F | 0x80);
                ++v4;
            }
            else {
                arr_b[v7] = (byte)(v8 >>> 12 | 480);
                int v10 = v7 + 2;
                arr_b[v7 + 1] = (byte)(v8 >>> 6 & 0x3F | 0x80);
                v7 += 3;
                arr_b[v10] = (byte)(v8 & 0x3F | 0x80);
            }
            ++v4;
            continue;
        label_47:
            ++v4;
            throw new UnpairedSurrogateException(v4 - 1, v2);
        label_49:
            if(!(0xD800 <= v8 && v8 <= 0xDFFF && (v4 + 1 == charSequence0.length() || !Character.isSurrogatePair(((char)v8), charSequence0.charAt(v4 + 1))))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + ((char)v8) + " at index " + v7);
            }
            throw new UnpairedSurrogateException(v4, v2);
        }
        return v7;
    }

    private static void encodeUtf8Buffer(CharSequence charSequence0, ByteBuffer byteBuffer0) {
        int v8;
        int v7;
        int v5;
        int v4;
        int v = charSequence0.length();
        int v1 = byteBuffer0.position();
        int v2 = 0;
        try {
            while(v2 < v) {
                int v3 = charSequence0.charAt(v2);
                if(v3 >= 0x80) {
                    break;
                }
                byteBuffer0.put(v1 + v2, ((byte)v3));
                ++v2;
            }
            if(v2 == v) {
                byteBuffer0.position(v1 + v2);
                return;
            }
            v1 += v2;
            while(true) {
            label_13:
                if(v2 >= v) {
                    byteBuffer0.position(v1);
                    return;
                }
                v4 = charSequence0.charAt(v2);
                if(v4 >= 0x80) {
                    break;
                }
                byteBuffer0.put(v1, ((byte)v4));
                ++v2;
                ++v1;
            }
        }
        catch(IndexOutOfBoundsException unused_ex) {
            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
        }
        if(v4 < 0x800) {
            try {
                v5 = v1 + 1;
                byteBuffer0.put(v1, ((byte)(v4 >>> 6 | 0xC0)));
                byteBuffer0.put(v5, ((byte)(v4 & 0x3F | 0x80)));
                v1 = v5;
            }
            catch(IndexOutOfBoundsException unused_ex) {
                v1 = v5;
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
            }
        }
        else if(v4 < 0xD800 || 0xDFFF < v4) {
            try {
                v5 = v1 + 1;
                byteBuffer0.put(v1, ((byte)(v4 >>> 12 | 0xE0)));
                v1 += 2;
            }
            catch(IndexOutOfBoundsException unused_ex) {
                v1 = v5;
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
            }
            try {
                byteBuffer0.put(v5, ((byte)(v4 >>> 6 & 0x3F | 0x80)));
                byteBuffer0.put(v1, ((byte)(v4 & 0x3F | 0x80)));
                ++v2;
                ++v1;
                goto label_13;
            }
            catch(IndexOutOfBoundsException unused_ex) {
            }
            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
        }
        else {
            if(v2 + 1 != v) {
                try {
                    int v6 = charSequence0.charAt(v2 + 1);
                    if(Character.isSurrogatePair(((char)v4), ((char)v6))) {
                        v7 = Character.toCodePoint(((char)v4), ((char)v6));
                        goto label_31;
                    }
                    else {
                        goto label_46;
                    }
                    throw new UnpairedSurrogateException(v2, v);
                }
                catch(IndexOutOfBoundsException unused_ex) {
                    ++v2;
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
                }
                try {
                label_31:
                    byteBuffer0.put(v1, ((byte)(v7 >>> 18 | 0xF0)));
                    v8 = v1 + 2;
                }
                catch(IndexOutOfBoundsException unused_ex) {
                    ++v1;
                    ++v2;
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
                }
                try {
                    byteBuffer0.put(v1 + 1, ((byte)(v7 >>> 12 & 0x3F | 0x80)));
                    v1 += 3;
                }
                catch(IndexOutOfBoundsException unused_ex) {
                    ++v2;
                    v1 = v8;
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
                }
                try {
                    byteBuffer0.put(v8, ((byte)(v7 >>> 6 & 0x3F | 0x80)));
                    byteBuffer0.put(v1, ((byte)(v7 & 0x3F | 0x80)));
                    ++v2;
                    ++v2;
                    ++v1;
                    goto label_13;
                label_46:
                    ++v2;
                }
                catch(IndexOutOfBoundsException unused_ex) {
                    ++v2;
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
                }
            }
            try {
                throw new UnpairedSurrogateException(v2, v);
            }
            catch(IndexOutOfBoundsException unused_ex) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v2) + " at index " + (byteBuffer0.position() + Math.max(v2, v1 - byteBuffer0.position() + 1)));
            }
        }
        ++v2;
        ++v1;
        goto label_13;
    }

    @Override  // androidx.emoji2.text.flatbuffer.Utf8
    public int encodedLength(CharSequence charSequence0) {
        return Utf8Safe.computeEncodedLength(charSequence0);
    }

    private static int encodedLengthGeneral(CharSequence charSequence0, int v) {
        int v1 = charSequence0.length();
        int v2 = 0;
        while(v < v1) {
            int v3 = charSequence0.charAt(v);
            if(v3 < 0x800) {
                v2 += 0x7F - v3 >>> 0x1F;
            }
            else {
                v2 += 2;
                if(0xD800 <= v3 && v3 <= 0xDFFF) {
                    if(Character.codePointAt(charSequence0, v) < 0x10000) {
                        throw new UnpairedSurrogateException(v, v1);
                    }
                    ++v;
                }
            }
            ++v;
        }
        return v2;
    }
}

