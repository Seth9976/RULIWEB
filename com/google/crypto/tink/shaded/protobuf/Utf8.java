package com.google.crypto.tink.shaded.protobuf;

import java.nio.ByteBuffer;
import java.util.Arrays;

final class Utf8 {
    static class DecodeUtil {
        private static void handleFourBytes(byte b, byte b1, byte b2, byte b3, char[] arr_c, int v) throws InvalidProtocolBufferException {
            if(DecodeUtil.isNotTrailingByte(b1) || (b << 28) + (b1 + 0x70) >> 30 != 0 || DecodeUtil.isNotTrailingByte(b2) || DecodeUtil.isNotTrailingByte(b3)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            int v1 = (b & 7) << 18 | (b1 & 0x3F) << 12 | (b2 & 0x3F) << 6 | b3 & 0x3F;
            arr_c[v] = (char)((v1 >>> 10) + 0xD7C0);
            arr_c[v + 1] = (char)((v1 & 0x3FF) + 0xDC00);
        }

        private static void handleOneByte(byte b, char[] arr_c, int v) {
            arr_c[v] = (char)b;
        }

        private static void handleThreeBytes(byte b, byte b1, byte b2, char[] arr_c, int v) throws InvalidProtocolBufferException {
            if(DecodeUtil.isNotTrailingByte(b1) || b == 0xFFFFFFE0 && b1 < 0xFFFFFFA0 || b == -19 && b1 >= 0xFFFFFFA0 || DecodeUtil.isNotTrailingByte(b2)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            arr_c[v] = (char)((b & 15) << 12 | (b1 & 0x3F) << 6 | b2 & 0x3F);
        }

        private static void handleTwoBytes(byte b, byte b1, char[] arr_c, int v) throws InvalidProtocolBufferException {
            if(b < -62 || DecodeUtil.isNotTrailingByte(b1)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            arr_c[v] = (char)((b & 0x1F) << 6 | b1 & 0x3F);
        }

        private static char highSurrogate(int codePoint) [...] // Inlined contents

        private static boolean isNotTrailingByte(byte b) {
            return b > -65;
        }

        private static boolean isOneByte(byte b) {
            return b >= 0;
        }

        private static boolean isThreeBytes(byte b) {
            return b < -16;
        }

        private static boolean isTwoBytes(byte b) {
            return b < 0xFFFFFFE0;
        }

        private static char lowSurrogate(int codePoint) [...] // Inlined contents

        private static int trailingByteValue(byte b) [...] // Inlined contents
    }

    static abstract class Processor {
        final String decodeUtf8(ByteBuffer byteBuffer0, int v, int v1) throws InvalidProtocolBufferException {
            if(byteBuffer0.hasArray()) {
                int v2 = byteBuffer0.arrayOffset();
                return this.decodeUtf8(byteBuffer0.array(), v2 + v, v1);
            }
            return byteBuffer0.isDirect() ? this.decodeUtf8Direct(byteBuffer0, v, v1) : this.decodeUtf8Default(byteBuffer0, v, v1);
        }

        abstract String decodeUtf8(byte[] arg1, int arg2, int arg3) throws InvalidProtocolBufferException;

        final String decodeUtf8Default(ByteBuffer byteBuffer0, int v, int v1) throws InvalidProtocolBufferException {
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
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            return new String(arr_c, 0, v5);
        }

        abstract String decodeUtf8Direct(ByteBuffer arg1, int arg2, int arg3) throws InvalidProtocolBufferException;

        abstract int encodeUtf8(CharSequence arg1, byte[] arg2, int arg3, int arg4);

        final void encodeUtf8(CharSequence charSequence0, ByteBuffer byteBuffer0) {
            if(byteBuffer0.hasArray()) {
                int v = byteBuffer0.arrayOffset();
                byteBuffer0.position(Utf8.encode(charSequence0, byteBuffer0.array(), byteBuffer0.position() + v, byteBuffer0.remaining()) - v);
                return;
            }
            if(byteBuffer0.isDirect()) {
                this.encodeUtf8Direct(charSequence0, byteBuffer0);
                return;
            }
            this.encodeUtf8Default(charSequence0, byteBuffer0);
        }

        final void encodeUtf8Default(CharSequence charSequence0, ByteBuffer byteBuffer0) {
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

        abstract void encodeUtf8Direct(CharSequence arg1, ByteBuffer arg2);

        final boolean isValidUtf8(ByteBuffer byteBuffer0, int v, int v1) {
            return this.partialIsValidUtf8(0, byteBuffer0, v, v1) == 0;
        }

        final boolean isValidUtf8(byte[] arr_b, int v, int v1) {
            return this.partialIsValidUtf8(0, arr_b, v, v1) == 0;
        }

        private static int partialIsValidUtf8(ByteBuffer byteBuffer0, int v, int v1) {
            int v2 = v + Utf8.estimateConsecutiveAscii(byteBuffer0, v, v1);
            while(true) {
                if(v2 >= v1) {
                    return 0;
                }
                int v3 = byteBuffer0.get(v2);
                if(v3 < 0) {
                    if(v3 < 0xFFFFFFE0) {
                        if(v2 + 1 >= v1) {
                            return v3;
                        }
                        if(v3 >= -62 && byteBuffer0.get(v2 + 1) <= -65) {
                            v2 += 2;
                            continue;
                        }
                    }
                    else if(v3 < -16) {
                        if(v2 + 1 >= v1 - 1) {
                            return Utf8.incompleteStateFor(byteBuffer0, v3, v2 + 1, v1 - (v2 + 1));
                        }
                        int v4 = byteBuffer0.get(v2 + 1);
                        if(v4 <= -65 && (v3 != 0xFFFFFFE0 || v4 >= 0xFFFFFFA0) && (v3 != -19 || v4 < 0xFFFFFFA0) && byteBuffer0.get(v2 + 2) <= -65) {
                            v2 += 3;
                            continue;
                        }
                    }
                    else {
                        if(v2 + 1 >= v1 - 2) {
                            return Utf8.incompleteStateFor(byteBuffer0, v3, v2 + 1, v1 - (v2 + 1));
                        }
                        int v5 = byteBuffer0.get(v2 + 1);
                        if(v5 <= -65 && (v3 << 28) + (v5 + 0x70) >> 30 == 0) {
                            int v6 = v2 + 3;
                            if(byteBuffer0.get(v2 + 2) <= -65) {
                                v2 += 4;
                                if(byteBuffer0.get(v6) > -65) {
                                    return -1;
                                }
                                continue;
                            }
                        }
                    }
                    return -1;
                }
                ++v2;
            }
        }

        final int partialIsValidUtf8(int v, ByteBuffer byteBuffer0, int v1, int v2) {
            if(byteBuffer0.hasArray()) {
                int v3 = byteBuffer0.arrayOffset();
                return this.partialIsValidUtf8(v, byteBuffer0.array(), v1 + v3, v3 + v2);
            }
            return byteBuffer0.isDirect() ? this.partialIsValidUtf8Direct(v, byteBuffer0, v1, v2) : this.partialIsValidUtf8Default(v, byteBuffer0, v1, v2);
        }

        abstract int partialIsValidUtf8(int arg1, byte[] arg2, int arg3, int arg4);

        final int partialIsValidUtf8Default(int v, ByteBuffer byteBuffer0, int v1, int v2) {
            int v7;
            int v6;
            if(v != 0) {
                if(v1 >= v2) {
                    return v;
                }
                if(((byte)v) < 0xFFFFFFE0) {
                    if(((byte)v) >= -62) {
                        return byteBuffer0.get(v1) > -65 ? -1 : Processor.partialIsValidUtf8(byteBuffer0, v1 + 1, v2);
                    }
                    return -1;
                }
                if(((byte)v) < -16) {
                    int v3 = (byte)(~(v >> 8));
                    if(v3 == 0) {
                        int v4 = byteBuffer0.get(v1);
                        if(v1 + 1 >= v2) {
                            return Utf8.incompleteStateFor(((byte)v), v4);
                        }
                        ++v1;
                        v3 = v4;
                    }
                    if(v3 <= -65 && (((byte)v) != 0xFFFFFFE0 || v3 >= 0xFFFFFFA0) && (((byte)v) != -19 || v3 < 0xFFFFFFA0)) {
                        return byteBuffer0.get(v1) <= -65 ? Processor.partialIsValidUtf8(byteBuffer0, v1 + 1, v2) : -1;
                    }
                    return -1;
                }
                int v5 = (byte)(~(v >> 8));
                if(v5 == 0) {
                    v6 = v1 + 1;
                    v5 = byteBuffer0.get(v1);
                    if(v6 >= v2) {
                        return Utf8.incompleteStateFor(((byte)v), v5);
                    }
                    v7 = 0;
                }
                else {
                    v7 = (byte)(v >> 16);
                    v6 = v1;
                }
                if(v7 == 0) {
                    int v8 = byteBuffer0.get(v6);
                    if(v6 + 1 >= v2) {
                        return Utf8.incompleteStateFor(((byte)v), v5, v8);
                    }
                    v7 = v8;
                    ++v6;
                }
                if(v5 <= -65 && (((byte)v) << 28) + (v5 + 0x70) >> 30 == 0 && v7 <= -65) {
                    return byteBuffer0.get(v6) <= -65 ? Processor.partialIsValidUtf8(byteBuffer0, v6 + 1, v2) : -1;
                }
                return -1;
            }
            return Processor.partialIsValidUtf8(byteBuffer0, v1, v2);
        }

        abstract int partialIsValidUtf8Direct(int arg1, ByteBuffer arg2, int arg3, int arg4);
    }

    static final class SafeProcessor extends Processor {
        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        String decodeUtf8(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
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
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            return new String(arr_c, 0, v5);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        String decodeUtf8Direct(ByteBuffer byteBuffer0, int v, int v1) throws InvalidProtocolBufferException {
            return this.decodeUtf8Default(byteBuffer0, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        int encodeUtf8(CharSequence charSequence0, byte[] arr_b, int v, int v1) {
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

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        void encodeUtf8Direct(CharSequence charSequence0, ByteBuffer byteBuffer0) {
            this.encodeUtf8Default(charSequence0, byteBuffer0);
        }

        private static int partialIsValidUtf8(byte[] arr_b, int v, int v1) {
            while(v < v1 && arr_b[v] >= 0) {
                ++v;
            }
            return v < v1 ? SafeProcessor.partialIsValidUtf8NonAscii(arr_b, v, v1) : 0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        int partialIsValidUtf8(int v, byte[] arr_b, int v1, int v2) {
            int v7;
            int v6;
            if(v != 0) {
                if(v1 >= v2) {
                    return v;
                }
                if(((byte)v) < 0xFFFFFFE0) {
                    if(((byte)v) >= -62) {
                        return arr_b[v1] > -65 ? -1 : SafeProcessor.partialIsValidUtf8(arr_b, v1 + 1, v2);
                    }
                    return -1;
                }
                if(((byte)v) < -16) {
                    int v3 = (byte)(~(v >> 8));
                    if(v3 == 0) {
                        int v4 = arr_b[v1];
                        if(v1 + 1 >= v2) {
                            return Utf8.incompleteStateFor(((byte)v), v4);
                        }
                        ++v1;
                        v3 = v4;
                    }
                    if(v3 <= -65 && (((byte)v) != 0xFFFFFFE0 || v3 >= 0xFFFFFFA0) && (((byte)v) != -19 || v3 < 0xFFFFFFA0)) {
                        return arr_b[v1] <= -65 ? SafeProcessor.partialIsValidUtf8(arr_b, v1 + 1, v2) : -1;
                    }
                    return -1;
                }
                int v5 = (byte)(~(v >> 8));
                if(v5 == 0) {
                    v6 = v1 + 1;
                    v5 = arr_b[v1];
                    if(v6 >= v2) {
                        return Utf8.incompleteStateFor(((byte)v), v5);
                    }
                    v7 = 0;
                }
                else {
                    v7 = (byte)(v >> 16);
                    v6 = v1;
                }
                if(v7 == 0) {
                    int v8 = arr_b[v6];
                    if(v6 + 1 >= v2) {
                        return Utf8.incompleteStateFor(((byte)v), v5, v8);
                    }
                    v7 = v8;
                    ++v6;
                }
                if(v5 <= -65 && (((byte)v) << 28) + (v5 + 0x70) >> 30 == 0 && v7 <= -65) {
                    return arr_b[v6] <= -65 ? SafeProcessor.partialIsValidUtf8(arr_b, v6 + 1, v2) : -1;
                }
                return -1;
            }
            return SafeProcessor.partialIsValidUtf8(arr_b, v1, v2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        int partialIsValidUtf8Direct(int v, ByteBuffer byteBuffer0, int v1, int v2) {
            return this.partialIsValidUtf8Default(v, byteBuffer0, v1, v2);
        }

        private static int partialIsValidUtf8NonAscii(byte[] arr_b, int v, int v1) {
            while(true) {
                if(v >= v1) {
                    return 0;
                }
                int v2 = v + 1;
                int v3 = arr_b[v];
                if(v3 < 0) {
                    if(v3 < 0xFFFFFFE0) {
                        if(v2 >= v1) {
                            return v3;
                        }
                        if(v3 >= -62) {
                            v += 2;
                            if(arr_b[v2] > -65) {
                                return -1;
                            }
                            continue;
                        }
                    }
                    else if(v3 < -16) {
                        if(v2 >= v1 - 1) {
                            return Utf8.incompleteStateFor(arr_b, v2, v1);
                        }
                        int v4 = v + 2;
                        int v5 = arr_b[v2];
                        if(v5 <= -65 && (v3 != 0xFFFFFFE0 || v5 >= 0xFFFFFFA0) && (v3 != -19 || v5 < 0xFFFFFFA0)) {
                            v += 3;
                            if(arr_b[v4] > -65) {
                                return -1;
                            }
                            continue;
                        }
                    }
                    else {
                        if(v2 >= v1 - 2) {
                            return Utf8.incompleteStateFor(arr_b, v2, v1);
                        }
                        int v6 = arr_b[v2];
                        if(v6 <= -65 && (v3 << 28) + (v6 + 0x70) >> 30 == 0) {
                            int v7 = v + 3;
                            if(arr_b[v + 2] <= -65) {
                                v += 4;
                                if(arr_b[v7] > -65) {
                                    return -1;
                                }
                                continue;
                            }
                        }
                    }
                    return -1;
                }
                v = v2;
            }
        }
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int v, int v1) {
            super("Unpaired surrogate at index " + v + " of " + v1);
        }
    }

    static final class UnsafeProcessor extends Processor {
        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        String decodeUtf8(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
            String s = new String(arr_b, v, v1, Internal.UTF_8);
            if(s.contains("\uFFFD")) {
                byte[] arr_b1 = Arrays.copyOfRange(arr_b, v, v1 + v);
                if(!Arrays.equals(s.getBytes(Internal.UTF_8), arr_b1)) {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            return s;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        String decodeUtf8Direct(ByteBuffer byteBuffer0, int v, int v1) throws InvalidProtocolBufferException {
            if((v | v1 | byteBuffer0.limit() - v - v1) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", byteBuffer0.limit(), v, v1));
            }
            long v2 = UnsafeUtil.addressOffset(byteBuffer0) + ((long)v);
            long v3 = ((long)v1) + v2;
            char[] arr_c = new char[v1];
            int v4;
            for(v4 = 0; v2 < v3; ++v4) {
                int v5 = UnsafeUtil.getByte(v2);
                if(!DecodeUtil.isOneByte(((byte)v5))) {
                    break;
                }
                ++v2;
                DecodeUtil.handleOneByte(((byte)v5), arr_c, v4);
            }
            int v6 = v4;
            while(v2 < v3) {
                long v7 = v2 + 1L;
                int v8 = UnsafeUtil.getByte(v2);
                if(DecodeUtil.isOneByte(((byte)v8))) {
                    int v9 = v6 + 1;
                    DecodeUtil.handleOneByte(((byte)v8), arr_c, v6);
                    long v10 = v7;
                    while(v10 < v3) {
                        int v11 = UnsafeUtil.getByte(v10);
                        if(!DecodeUtil.isOneByte(((byte)v11))) {
                            break;
                        }
                        ++v10;
                        DecodeUtil.handleOneByte(((byte)v11), arr_c, v9);
                        ++v9;
                    }
                    v6 = v9;
                    v2 = v10;
                    continue;
                }
                if(DecodeUtil.isTwoBytes(((byte)v8))) {
                    if(v7 < v3) {
                        v2 += 2L;
                        DecodeUtil.handleTwoBytes(((byte)v8), UnsafeUtil.getByte(v7), arr_c, v6);
                        ++v6;
                        continue;
                    }
                }
                else if(!DecodeUtil.isThreeBytes(((byte)v8))) {
                    if(v7 < v3 - 2L) {
                        int v13 = UnsafeUtil.getByte(v7);
                        long v14 = v2 + 3L;
                        int v15 = UnsafeUtil.getByte(v2 + 2L);
                        v2 += 4L;
                        DecodeUtil.handleFourBytes(((byte)v8), ((byte)v13), ((byte)v15), UnsafeUtil.getByte(v14), arr_c, v6);
                        v6 += 2;
                        continue;
                    }
                }
                else if(v7 < v3 - 1L) {
                    long v12 = v2 + 2L;
                    v2 += 3L;
                    DecodeUtil.handleThreeBytes(((byte)v8), UnsafeUtil.getByte(v7), UnsafeUtil.getByte(v12), arr_c, v6);
                    ++v6;
                    continue;
                }
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            return new String(arr_c, 0, v6);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        int encodeUtf8(CharSequence charSequence0, byte[] arr_b, int v, int v1) {
            long v2 = (long)v;
            long v3 = ((long)v1) + v2;
            int v4 = charSequence0.length();
            if(v4 > v1 || arr_b.length - v1 < v) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v4 - 1) + " at index " + (v + v1));
            }
            int v5 = 0;
            while(v5 < v4) {
                int v6 = charSequence0.charAt(v5);
                if(v6 >= 0x80) {
                    break;
                }
                UnsafeUtil.putByte(arr_b, v2, ((byte)v6));
                ++v5;
                ++v2;
            }
            if(v5 == v4) {
                return (int)v2;
            }
            while(v5 < v4) {
                int v7 = charSequence0.charAt(v5);
                if(v7 < 0x80 && v2 < v3) {
                    UnsafeUtil.putByte(arr_b, v2, ((byte)v7));
                    ++v2;
                }
                else if(v7 < 0x800 && v2 <= v3 - 2L) {
                    long v8 = v2 + 1L;
                    UnsafeUtil.putByte(arr_b, v2, ((byte)(v7 >>> 6 | 960)));
                    v2 += 2L;
                    UnsafeUtil.putByte(arr_b, v8, ((byte)(v7 & 0x3F | 0x80)));
                }
                else if(v7 >= 0xD800 && 0xDFFF >= v7 || v2 > v3 - 3L) {
                    if(v2 > v3 - 4L) {
                        goto label_49;
                    }
                    if(v5 + 1 == v4) {
                        throw new UnpairedSurrogateException(v5 - 1, v4);
                    }
                    int v10 = charSequence0.charAt(v5 + 1);
                    if(!Character.isSurrogatePair(((char)v7), ((char)v10))) {
                        goto label_47;
                    }
                    int v11 = Character.toCodePoint(((char)v7), ((char)v10));
                    UnsafeUtil.putByte(arr_b, v2, ((byte)(v11 >>> 18 | 0xF0)));
                    UnsafeUtil.putByte(arr_b, v2 + 1L, ((byte)(v11 >>> 12 & 0x3F | 0x80)));
                    long v12 = v2 + 3L;
                    UnsafeUtil.putByte(arr_b, v2 + 2L, ((byte)(v11 >>> 6 & 0x3F | 0x80)));
                    v2 += 4L;
                    UnsafeUtil.putByte(arr_b, v12, ((byte)(v11 & 0x3F | 0x80)));
                    ++v5;
                }
                else {
                    UnsafeUtil.putByte(arr_b, v2, ((byte)(v7 >>> 12 | 480)));
                    long v9 = v2 + 2L;
                    UnsafeUtil.putByte(arr_b, v2 + 1L, ((byte)(v7 >>> 6 & 0x3F | 0x80)));
                    v2 += 3L;
                    UnsafeUtil.putByte(arr_b, v9, ((byte)(v7 & 0x3F | 0x80)));
                }
                ++v5;
                continue;
            label_47:
                ++v5;
                throw new UnpairedSurrogateException(v5 - 1, v4);
            label_49:
                if(0xD800 > v7 || v7 > 0xDFFF || v5 + 1 != v4 && Character.isSurrogatePair(((char)v7), charSequence0.charAt(v5 + 1))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + ((char)v7) + " at index " + v2);
                }
                throw new UnpairedSurrogateException(v5, v4);
            }
            return (int)v2;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        void encodeUtf8Direct(CharSequence charSequence0, ByteBuffer byteBuffer0) {
            long v = UnsafeUtil.addressOffset(byteBuffer0);
            long v1 = ((long)byteBuffer0.position()) + v;
            long v2 = ((long)byteBuffer0.limit()) + v;
            int v3 = charSequence0.length();
            if(((long)v3) > v2 - v1) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence0.charAt(v3 - 1) + " at index " + byteBuffer0.limit());
            }
            int v4 = 0;
            while(v4 < v3) {
                int v5 = charSequence0.charAt(v4);
                if(v5 >= 0x80) {
                    break;
                }
                UnsafeUtil.putByte(v1, ((byte)v5));
                ++v4;
                ++v1;
            }
            if(v4 == v3) {
                byteBuffer0.position(((int)(v1 - v)));
                return;
            }
            while(v4 < v3) {
                int v6 = charSequence0.charAt(v4);
                if(v6 < 0x80 && v1 < v2) {
                    UnsafeUtil.putByte(v1, ((byte)v6));
                    ++v1;
                }
                else if(v6 < 0x800 && v1 <= v2 - 2L) {
                    long v7 = v1 + 1L;
                    UnsafeUtil.putByte(v1, ((byte)(v6 >>> 6 | 960)));
                    v1 += 2L;
                    UnsafeUtil.putByte(v7, ((byte)(v6 & 0x3F | 0x80)));
                }
                else if(v6 >= 0xD800 && 0xDFFF >= v6 || v1 > v2 - 3L) {
                    if(v1 > v2 - 4L) {
                        goto label_51;
                    }
                    if(v4 + 1 == v3) {
                        throw new UnpairedSurrogateException(v4 - 1, v3);
                    }
                    int v9 = charSequence0.charAt(v4 + 1);
                    if(!Character.isSurrogatePair(((char)v6), ((char)v9))) {
                        goto label_49;
                    }
                    int v10 = Character.toCodePoint(((char)v6), ((char)v9));
                    UnsafeUtil.putByte(v1, ((byte)(v10 >>> 18 | 0xF0)));
                    UnsafeUtil.putByte(v1 + 1L, ((byte)(v10 >>> 12 & 0x3F | 0x80)));
                    long v11 = v1 + 3L;
                    UnsafeUtil.putByte(v1 + 2L, ((byte)(v10 >>> 6 & 0x3F | 0x80)));
                    v1 += 4L;
                    UnsafeUtil.putByte(v11, ((byte)(v10 & 0x3F | 0x80)));
                    ++v4;
                }
                else {
                    UnsafeUtil.putByte(v1, ((byte)(v6 >>> 12 | 480)));
                    long v8 = v1 + 2L;
                    UnsafeUtil.putByte(v1 + 1L, ((byte)(v6 >>> 6 & 0x3F | 0x80)));
                    v1 += 3L;
                    UnsafeUtil.putByte(v8, ((byte)(v6 & 0x3F | 0x80)));
                }
                ++v4;
                continue;
            label_49:
                ++v4;
                throw new UnpairedSurrogateException(v4 - 1, v3);
            label_51:
                if(0xD800 > v6 || v6 > 0xDFFF || v4 + 1 != v3 && Character.isSurrogatePair(((char)v6), charSequence0.charAt(v4 + 1))) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + ((char)v6) + " at index " + v1);
                }
                throw new UnpairedSurrogateException(v4, v3);
            }
            byteBuffer0.position(((int)(v1 - v)));
            return;
        }

        // 去混淆评级： 中等(60)
        static boolean isAvailable() [...] // 潜在的解密器

        private static int partialIsValidUtf8(long v, int v1) {
            int v5;
            int v2 = UnsafeProcessor.unsafeEstimateConsecutiveAscii(v, v1);
            long v3 = v + ((long)v2);
            int v4 = v1 - v2;
            while(true) {
                while(true) {
                label_3:
                    v5 = 0;
                    while(v4 > 0) {
                        v5 = UnsafeUtil.getByte(v3);
                        if(v5 >= 0) {
                            --v4;
                            ++v3;
                        }
                        else {
                            ++v3;
                            if(true) {
                                break;
                            }
                        }
                    }
                    if(v4 == 0) {
                        return 0;
                    }
                    if(v5 < 0xFFFFFFE0) {
                        if(v4 - 1 == 0) {
                            return v5;
                        }
                        v4 -= 2;
                        if(v5 >= -62 && UnsafeUtil.getByte(v3) <= -65) {
                            ++v3;
                            continue;
                        }
                        return -1;
                    }
                    if(v5 < -16) {
                        if(v4 - 1 < 2) {
                            return UnsafeProcessor.unsafeIncompleteStateFor(v3, v5, v4 - 1);
                        }
                        v4 -= 3;
                        long v6 = v3 + 1L;
                        int v7 = UnsafeUtil.getByte(v3);
                        if(v7 <= -65 && (v5 != 0xFFFFFFE0 || v7 >= 0xFFFFFFA0) && (v5 != -19 || v7 < 0xFFFFFFA0)) {
                            v3 += 2L;
                            if(UnsafeUtil.getByte(v6) <= -65) {
                                continue;
                            }
                        }
                        return -1;
                    }
                    break;
                }
                if(v4 - 1 < 3) {
                    return UnsafeProcessor.unsafeIncompleteStateFor(v3, v5, v4 - 1);
                }
                v4 -= 4;
                int v8 = UnsafeUtil.getByte(v3);
                if(v8 > -65 || (v5 << 28) + (v8 + 0x70) >> 30 != 0) {
                    break;
                }
                long v9 = v3 + 2L;
                if(UnsafeUtil.getByte(v3 + 1L) > -65) {
                    break;
                }
                v3 += 3L;
                if(UnsafeUtil.getByte(v9) <= -65) {
                    goto label_3;
                }
                break;
            }
            return -1;
        }

        private static int partialIsValidUtf8(byte[] arr_b, long v, int v1) {
            int v5;
            int v2 = UnsafeProcessor.unsafeEstimateConsecutiveAscii(arr_b, v, v1);
            int v3 = v1 - v2;
            long v4 = v + ((long)v2);
            while(true) {
                while(true) {
                label_3:
                    v5 = 0;
                    while(v3 > 0) {
                        v5 = UnsafeUtil.getByte(arr_b, v4);
                        if(v5 >= 0) {
                            --v3;
                            ++v4;
                        }
                        else {
                            ++v4;
                            if(true) {
                                break;
                            }
                        }
                    }
                    if(v3 == 0) {
                        return 0;
                    }
                    if(v5 < 0xFFFFFFE0) {
                        if(v3 - 1 == 0) {
                            return v5;
                        }
                        v3 -= 2;
                        if(v5 >= -62 && UnsafeUtil.getByte(arr_b, v4) <= -65) {
                            ++v4;
                            continue;
                        }
                        return -1;
                    }
                    if(v5 < -16) {
                        if(v3 - 1 < 2) {
                            return UnsafeProcessor.unsafeIncompleteStateFor(arr_b, v5, v4, v3 - 1);
                        }
                        v3 -= 3;
                        long v6 = v4 + 1L;
                        int v7 = UnsafeUtil.getByte(arr_b, v4);
                        if(v7 <= -65 && (v5 != 0xFFFFFFE0 || v7 >= 0xFFFFFFA0) && (v5 != -19 || v7 < 0xFFFFFFA0)) {
                            v4 += 2L;
                            if(UnsafeUtil.getByte(arr_b, v6) <= -65) {
                                continue;
                            }
                        }
                        return -1;
                    }
                    break;
                }
                if(v3 - 1 < 3) {
                    return UnsafeProcessor.unsafeIncompleteStateFor(arr_b, v5, v4, v3 - 1);
                }
                v3 -= 4;
                int v8 = UnsafeUtil.getByte(arr_b, v4);
                if(v8 > -65 || (v5 << 28) + (v8 + 0x70) >> 30 != 0) {
                    break;
                }
                long v9 = v4 + 2L;
                if(UnsafeUtil.getByte(arr_b, v4 + 1L) > -65) {
                    break;
                }
                v4 += 3L;
                if(UnsafeUtil.getByte(arr_b, v9) <= -65) {
                    goto label_3;
                }
                break;
            }
            return -1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        int partialIsValidUtf8(int v, byte[] arr_b, int v1, int v2) {
            long v6;
            int v3 = 0;
            if((v1 | v2 | arr_b.length - v2) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", ((int)arr_b.length), v1, v2));
            }
            long v4 = (long)v1;
            if(v != 0) {
                if(v4 >= ((long)v2)) {
                    return v;
                }
                if(((byte)v) < 0xFFFFFFE0) {
                    return ((byte)v) < -62 || UnsafeUtil.getByte(arr_b, v4) > -65 ? -1 : UnsafeProcessor.partialIsValidUtf8(arr_b, v4 + 1L, ((int)(((long)v2) - (v4 + 1L))));
                }
                if(((byte)v) < -16) {
                    int v5 = (byte)(~(v >> 8));
                    if(v5 == 0) {
                        v5 = UnsafeUtil.getByte(arr_b, v4);
                        if(v4 + 1L >= ((long)v2)) {
                            return Utf8.incompleteStateFor(((byte)v), v5);
                        }
                        ++v4;
                    }
                    if(v5 <= -65 && (((byte)v) != 0xFFFFFFE0 || v5 >= 0xFFFFFFA0) && (((byte)v) != -19 || v5 < 0xFFFFFFA0)) {
                        v6 = v4 + 1L;
                        return UnsafeUtil.getByte(arr_b, v4) > -65 ? -1 : UnsafeProcessor.partialIsValidUtf8(arr_b, v6, ((int)(((long)v2) - v6)));
                    }
                    return -1;
                }
                int v7 = (byte)(~(v >> 8));
                if(v7 == 0) {
                    v7 = UnsafeUtil.getByte(arr_b, v4);
                    if(v4 + 1L >= ((long)v2)) {
                        return Utf8.incompleteStateFor(((byte)v), v7);
                    }
                    ++v4;
                }
                else {
                    v3 = (byte)(v >> 16);
                }
                if(v3 == 0) {
                    v3 = UnsafeUtil.getByte(arr_b, v4);
                    if(v4 + 1L >= ((long)v2)) {
                        return Utf8.incompleteStateFor(((byte)v), v7, v3);
                    }
                    ++v4;
                }
                if(v7 <= -65 && (((byte)v) << 28) + (v7 + 0x70) >> 30 == 0 && v3 <= -65) {
                    v6 = v4 + 1L;
                    return UnsafeUtil.getByte(arr_b, v4) <= -65 ? UnsafeProcessor.partialIsValidUtf8(arr_b, v6, ((int)(((long)v2) - v6))) : -1;
                }
                return -1;
            }
            return UnsafeProcessor.partialIsValidUtf8(arr_b, v4, ((int)(((long)v2) - v4)));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Utf8$Processor
        int partialIsValidUtf8Direct(int v, ByteBuffer byteBuffer0, int v1, int v2) {
            long v7;
            int v3 = 0;
            if((v1 | v2 | byteBuffer0.limit() - v2) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", byteBuffer0.limit(), v1, v2));
            }
            long v4 = UnsafeUtil.addressOffset(byteBuffer0) + ((long)v1);
            long v5 = ((long)(v2 - v1)) + v4;
            if(v != 0) {
                if(v4 >= v5) {
                    return v;
                }
                if(((byte)v) < 0xFFFFFFE0) {
                    return ((byte)v) < -62 || UnsafeUtil.getByte(v4) > -65 ? -1 : UnsafeProcessor.partialIsValidUtf8(v4 + 1L, ((int)(v5 - (v4 + 1L))));
                }
                if(((byte)v) < -16) {
                    int v6 = (byte)(~(v >> 8));
                    if(v6 == 0) {
                        v6 = UnsafeUtil.getByte(v4);
                        if(v4 + 1L >= v5) {
                            return Utf8.incompleteStateFor(((byte)v), v6);
                        }
                        ++v4;
                    }
                    if(v6 <= -65 && (((byte)v) != 0xFFFFFFE0 || v6 >= 0xFFFFFFA0) && (((byte)v) != -19 || v6 < 0xFFFFFFA0)) {
                        v7 = v4 + 1L;
                        return UnsafeUtil.getByte(v4) > -65 ? -1 : UnsafeProcessor.partialIsValidUtf8(v7, ((int)(v5 - v7)));
                    }
                    return -1;
                }
                int v8 = (byte)(~(v >> 8));
                if(v8 == 0) {
                    v8 = UnsafeUtil.getByte(v4);
                    if(v4 + 1L >= v5) {
                        return Utf8.incompleteStateFor(((byte)v), v8);
                    }
                    ++v4;
                }
                else {
                    v3 = (byte)(v >> 16);
                }
                if(v3 == 0) {
                    v3 = UnsafeUtil.getByte(v4);
                    if(v4 + 1L >= v5) {
                        return Utf8.incompleteStateFor(((byte)v), v8, v3);
                    }
                    ++v4;
                }
                if(v8 <= -65 && (((byte)v) << 28) + (v8 + 0x70) >> 30 == 0 && v3 <= -65) {
                    v7 = v4 + 1L;
                    return UnsafeUtil.getByte(v4) <= -65 ? UnsafeProcessor.partialIsValidUtf8(v7, ((int)(v5 - v7))) : -1;
                }
                return -1;
            }
            return UnsafeProcessor.partialIsValidUtf8(v4, ((int)(v5 - v4)));
        }

        private static int unsafeEstimateConsecutiveAscii(long v, int v1) {
            if(v1 < 16) {
                return 0;
            }
            int v2 = (int)(-v & 7L);
            int v3 = v2;
            while(v3 > 0) {
                if(UnsafeUtil.getByte(v) < 0) {
                    return v2 - v3;
                }
                --v3;
                ++v;
            }
            int v4;
            for(v4 = v1 - v2; v4 >= 8 && (UnsafeUtil.getLong(v) & 0x8080808080808080L) == 0L; v4 -= 8) {
                v += 8L;
            }
            return v1 - v4;
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] arr_b, long v, int v1) {
            int v2 = 0;
            if(v1 < 16) {
                return 0;
            }
            int v3 = 8 - (((int)v) & 7);
            while(v2 < v3) {
                if(UnsafeUtil.getByte(arr_b, v) < 0) {
                    return v2;
                }
                ++v2;
                ++v;
            }
            while(v2 + 8 <= v1 && (UnsafeUtil.getLong(arr_b, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + v) & 0x8080808080808080L) == 0L) {
                v += 8L;
                v2 += 8;
            }
            while(v2 < v1) {
                if(UnsafeUtil.getByte(arr_b, v) < 0) {
                    return v2;
                }
                ++v2;
                ++v;
            }
            return v1;
        }

        private static int unsafeIncompleteStateFor(long v, int v1, int v2) {
            switch(v2) {
                case 0: {
                    return Utf8.incompleteStateFor(v1);
                }
                case 1: {
                    return Utf8.incompleteStateFor(v1, UnsafeUtil.getByte(v));
                }
                case 2: {
                    return Utf8.incompleteStateFor(v1, UnsafeUtil.getByte(v), UnsafeUtil.getByte(v + 1L));
                }
                default: {
                    throw new AssertionError();
                }
            }
        }

        private static int unsafeIncompleteStateFor(byte[] arr_b, int v, long v1, int v2) {
            switch(v2) {
                case 0: {
                    return Utf8.incompleteStateFor(v);
                }
                case 1: {
                    return Utf8.incompleteStateFor(v, UnsafeUtil.getByte(arr_b, v1));
                }
                case 2: {
                    return Utf8.incompleteStateFor(v, UnsafeUtil.getByte(arr_b, v1), UnsafeUtil.getByte(arr_b, v1 + 1L));
                }
                default: {
                    throw new AssertionError();
                }
            }
        }
    }

    private static final long ASCII_MASK_LONG = 0x8080808080808080L;
    static final int COMPLETE = 0;
    static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor;

    // 去混淆评级： 低(30)
    static {
        Utf8.processor = new UnsafeProcessor();
    }

    static String decodeUtf8(ByteBuffer byteBuffer0, int v, int v1) throws InvalidProtocolBufferException {
        return Utf8.processor.decodeUtf8(byteBuffer0, v, v1);
    }

    static String decodeUtf8(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
        return Utf8.processor.decodeUtf8(arr_b, v, v1);
    }

    static int encode(CharSequence charSequence0, byte[] arr_b, int v, int v1) {
        return Utf8.processor.encodeUtf8(charSequence0, arr_b, v, v1);
    }

    static void encodeUtf8(CharSequence charSequence0, ByteBuffer byteBuffer0) {
        Utf8.processor.encodeUtf8(charSequence0, byteBuffer0);
    }

    static int encodedLength(CharSequence charSequence0) {
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
                v2 += Utf8.encodedLengthGeneral(charSequence0, v1);
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

    private static int estimateConsecutiveAscii(ByteBuffer byteBuffer0, int v, int v1) {
        int v2;
        for(v2 = v; v2 < v1 - 7 && (byteBuffer0.getLong(v2) & 0x8080808080808080L) == 0L; v2 += 8) {
        }
        return v2 - v;
    }

    private static int incompleteStateFor(int byte1) {
        return byte1 <= -12 ? byte1 : -1;
    }

    private static int incompleteStateFor(int byte1, int byte2) {
        return byte1 > -12 || byte2 > -65 ? -1 : byte1 ^ byte2 << 8;
    }

    private static int incompleteStateFor(int byte1, int byte2, int byte3) {
        return byte1 > -12 || byte2 > -65 || byte3 > -65 ? -1 : byte1 ^ byte2 << 8 ^ byte3 << 16;
    }

    private static int incompleteStateFor(ByteBuffer byteBuffer0, int v, int v1, int v2) {
        switch(v2) {
            case 0: {
                return Utf8.incompleteStateFor(v);
            }
            case 1: {
                return Utf8.incompleteStateFor(v, byteBuffer0.get(v1));
            }
            case 2: {
                return Utf8.incompleteStateFor(v, byteBuffer0.get(v1), byteBuffer0.get(v1 + 1));
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    private static int incompleteStateFor(byte[] arr_b, int v, int v1) {
        int v2 = arr_b[v - 1];
        switch(v1 - v) {
            case 0: {
                return Utf8.incompleteStateFor(v2);
            }
            case 1: {
                return Utf8.incompleteStateFor(v2, arr_b[v]);
            }
            case 2: {
                return Utf8.incompleteStateFor(v2, arr_b[v], arr_b[v + 1]);
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    static boolean isValidUtf8(ByteBuffer byteBuffer0) {
        return Utf8.processor.isValidUtf8(byteBuffer0, byteBuffer0.position(), byteBuffer0.remaining());
    }

    static boolean isValidUtf8(byte[] arr_b) {
        return Utf8.processor.isValidUtf8(arr_b, 0, arr_b.length);
    }

    static boolean isValidUtf8(byte[] arr_b, int v, int v1) {
        return Utf8.processor.isValidUtf8(arr_b, v, v1);
    }

    static int partialIsValidUtf8(int v, ByteBuffer byteBuffer0, int v1, int v2) {
        return Utf8.processor.partialIsValidUtf8(v, byteBuffer0, v1, v2);
    }

    static int partialIsValidUtf8(int v, byte[] arr_b, int v1, int v2) {
        return Utf8.processor.partialIsValidUtf8(v, arr_b, v1, v2);
    }
}

