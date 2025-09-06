package com.google.crypto.tink.subtle;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class Base64 {
    static abstract class Coder {
        public int op;
        public byte[] output;

        public abstract int maxOutputSize(int arg1);

        public abstract boolean process(byte[] arg1, int arg2, int arg3, boolean arg4);
    }

    static class Decoder extends Coder {
        private static final int[] DECODE = null;
        private static final int[] DECODE_WEBSAFE = null;
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;

        static {
            Decoder.DECODE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 0x3F, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 0x1F, 0x20, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 0x2F, 0x30, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            Decoder.DECODE_WEBSAFE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 0x3F, -1, 26, 27, 28, 29, 30, 0x1F, 0x20, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 0x2F, 0x30, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        }

        public Decoder(int v, byte[] arr_b) {
            this.output = arr_b;
            this.alphabet = (v & 8) == 0 ? Decoder.DECODE : Decoder.DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        @Override  // com.google.crypto.tink.subtle.Base64$Coder
        public int maxOutputSize(int v) {
            return v * 3 / 4 + 10;
        }

        @Override  // com.google.crypto.tink.subtle.Base64$Coder
        public boolean process(byte[] arr_b, int v, int v1, boolean z) {
            int v2 = this.state;
            if(v2 == 6) {
                return false;
            }
            int v3 = v1 + v;
            byte[] arr_b1 = this.output;
            int[] arr_v = this.alphabet;
            int v4 = this.value;
            int v5 = 0;
            int v6 = v2;
            for(int v7 = v; v7 < v3; ++v7) {
                if(v6 == 0) {
                    while(v7 + 4 <= v3) {
                        v4 = arr_v[arr_b[v7] & 0xFF] << 18 | arr_v[arr_b[v7 + 1] & 0xFF] << 12 | arr_v[arr_b[v7 + 2] & 0xFF] << 6 | arr_v[arr_b[v7 + 3] & 0xFF];
                        if(v4 < 0) {
                            break;
                        }
                        arr_b1[v5 + 2] = (byte)v4;
                        arr_b1[v5 + 1] = (byte)(v4 >> 8);
                        arr_b1[v5] = (byte)(v4 >> 16);
                        v5 += 3;
                        v7 += 4;
                    }
                    if(v7 >= v3) {
                        break;
                    }
                }
                int v8 = arr_v[arr_b[v7] & 0xFF];
                switch(v6) {
                    case 0: {
                        if(v8 >= 0) {
                            v6 = 1;
                            v4 = v8;
                        }
                        else if(v8 != -1) {
                            this.state = 6;
                            return false;
                        }
                        break;
                    }
                    case 1: {
                        if(v8 >= 0) {
                            v6 = 2;
                            v4 = v8 | v4 << 6;
                        }
                        else if(v8 != -1) {
                            this.state = 6;
                            return false;
                        }
                        break;
                    }
                    default: {
                        if(v6 != 2) {
                            switch(v6) {
                                case 3: {
                                    if(v8 >= 0) {
                                        int v9 = v8 | v4 << 6;
                                        arr_b1[v5 + 2] = (byte)v9;
                                        arr_b1[v5 + 1] = (byte)(v9 >> 8);
                                        arr_b1[v5] = (byte)(v9 >> 16);
                                        v5 += 3;
                                        v4 = v9;
                                        v6 = 0;
                                    }
                                    else if(v8 == -2) {
                                        arr_b1[v5 + 1] = (byte)(v4 >> 2);
                                        arr_b1[v5] = (byte)(v4 >> 10);
                                        v5 += 2;
                                        v6 = 5;
                                    }
                                    else if(v8 != -1) {
                                        this.state = 6;
                                        return false;
                                    }
                                    break;
                                }
                                case 4: {
                                    if(v8 == -2) {
                                        v6 = 5;
                                    }
                                    else if(v8 != -1) {
                                        this.state = 6;
                                        return false;
                                    }
                                    break;
                                }
                                case 5: {
                                    if(v8 != -1) {
                                        this.state = 6;
                                        return false;
                                    }
                                }
                            }
                        }
                        else if(v8 >= 0) {
                            v6 = 3;
                            v4 = v8 | v4 << 6;
                        }
                        else if(v8 == -2) {
                            arr_b1[v5] = (byte)(v4 >> 4);
                            ++v5;
                            v6 = 4;
                        }
                        else if(v8 != -1) {
                            this.state = 6;
                            return false;
                        }
                    }
                }
            }
            if(!z) {
                this.state = v6;
                this.value = v4;
                this.op = v5;
                return true;
            }
            switch(v6) {
                case 1: {
                    this.state = 6;
                    return false;
                }
                case 2: {
                    arr_b1[v5] = (byte)(v4 >> 4);
                    ++v5;
                    break;
                }
                case 3: {
                    int v10 = v5 + 1;
                    arr_b1[v5] = (byte)(v4 >> 10);
                    v5 += 2;
                    arr_b1[v10] = (byte)(v4 >> 2);
                    break;
                }
                case 4: {
                    this.state = 6;
                    return false;
                }
            }
            this.state = v6;
            this.op = v5;
            return true;
        }
    }

    static class Encoder extends Coder {
        static final boolean $assertionsDisabled = false;
        private static final byte[] ENCODE = null;
        private static final byte[] ENCODE_WEBSAFE = null;
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean doCr;
        public final boolean doNewline;
        public final boolean doPadding;
        private final byte[] tail;
        int tailLen;

        static {
            Encoder.ENCODE = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 0x2F};
            Encoder.ENCODE_WEBSAFE = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 0x5F};
        }

        public Encoder(int v, byte[] arr_b) {
            this.output = arr_b;
            boolean z = true;
            this.doPadding = (v & 1) == 0;
            boolean z1 = (v & 2) == 0;
            this.doNewline = z1;
            if((v & 4) == 0) {
                z = false;
            }
            this.doCr = z;
            this.alphabet = (v & 8) == 0 ? Encoder.ENCODE : Encoder.ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = z1 ? 19 : -1;
        }

        @Override  // com.google.crypto.tink.subtle.Base64$Coder
        public int maxOutputSize(int v) {
            return v * 8 / 5 + 10;
        }

        @Override  // com.google.crypto.tink.subtle.Base64$Coder
        public boolean process(byte[] arr_b, int v, int v1, boolean z) {
            int v17;
            int v15;
            int v12;
            int v8;
            int v7;
            int v6;
            int v5;
            byte[] arr_b1 = this.alphabet;
            byte[] arr_b2 = this.output;
            int v2 = this.count;
            int v3 = v1 + v;
            int v4 = 0;
            switch(this.tailLen) {
                case 1: {
                    if(v + 2 <= v3) {
                        v5 = v + 2;
                        v6 = arr_b[v + 1] & 0xFF | ((this.tail[0] & 0xFF) << 16 | (arr_b[v] & 0xFF) << 8);
                        this.tailLen = 0;
                    }
                    else {
                        v5 = v;
                        v6 = -1;
                    }
                    break;
                }
                case 2: {
                    if(v + 1 <= v3) {
                        v6 = (this.tail[1] & 0xFF) << 8 | (this.tail[0] & 0xFF) << 16 | arr_b[v] & 0xFF;
                        this.tailLen = 0;
                        v5 = v + 1;
                    }
                    else {
                        v5 = v;
                        v6 = -1;
                    }
                    break;
                }
                default: {
                    v5 = v;
                    v6 = -1;
                }
            }
            if(v6 == -1) {
                v8 = 0;
            }
            else {
                arr_b2[0] = arr_b1[v6 >> 18 & 0x3F];
                arr_b2[1] = arr_b1[v6 >> 12 & 0x3F];
                arr_b2[2] = arr_b1[v6 >> 6 & 0x3F];
                arr_b2[3] = arr_b1[v6 & 0x3F];
                --v2;
                if(v2 == 0) {
                    if(this.doCr) {
                        arr_b2[4] = 13;
                        v7 = 5;
                    }
                    else {
                        v7 = 4;
                    }
                    v8 = v7 + 1;
                    arr_b2[v7] = 10;
                    v2 = 19;
                }
                else {
                    v8 = 4;
                }
            }
            while(v5 + 3 <= v3) {
                int v9 = (arr_b[v5 + 1] & 0xFF) << 8 | (arr_b[v5] & 0xFF) << 16 | arr_b[v5 + 2] & 0xFF;
                arr_b2[v8] = arr_b1[v9 >> 18 & 0x3F];
                arr_b2[v8 + 1] = arr_b1[v9 >> 12 & 0x3F];
                arr_b2[v8 + 2] = arr_b1[v9 >> 6 & 0x3F];
                arr_b2[v8 + 3] = arr_b1[v9 & 0x3F];
                int v10 = v8 + 4;
                --v2;
                if(v2 == 0) {
                    if(this.doCr) {
                        arr_b2[v10] = 13;
                        v10 = v8 + 5;
                    }
                    v8 = v10 + 1;
                    arr_b2[v10] = 10;
                    v5 += 3;
                    v2 = 19;
                }
                else {
                    v8 = v10;
                    v5 += 3;
                }
            }
            if(z) {
                int v11 = this.tailLen;
                if(v5 - v11 == v3 - 1) {
                    if(v11 > 0) {
                        v12 = this.tail[0];
                        v4 = 1;
                    }
                    else {
                        v12 = arr_b[v5];
                    }
                    int v13 = (v12 & 0xFF) << 4;
                    this.tailLen = v11 - v4;
                    arr_b2[v8] = arr_b1[v13 >> 6 & 0x3F];
                    int v14 = v8 + 2;
                    arr_b2[v8 + 1] = arr_b1[v13 & 0x3F];
                    if(this.doPadding) {
                        arr_b2[v14] = 61;
                        v14 = v8 + 4;
                        arr_b2[v8 + 3] = 61;
                    }
                    if(this.doNewline) {
                        if(this.doCr) {
                            arr_b2[v14] = 13;
                            ++v14;
                        }
                        arr_b2[v14] = 10;
                        ++v14;
                    }
                    v8 = v14;
                }
                else if(v5 - v11 == v3 - 2) {
                    if(v11 > 1) {
                        v15 = this.tail[0];
                        v4 = 1;
                    }
                    else {
                        int v16 = arr_b[v5];
                        ++v5;
                        v15 = v16;
                    }
                    if(v11 > 0) {
                        v17 = this.tail[v4];
                        ++v4;
                    }
                    else {
                        v17 = arr_b[v5];
                    }
                    int v18 = (v15 & 0xFF) << 10 | (v17 & 0xFF) << 2;
                    this.tailLen = v11 - v4;
                    arr_b2[v8] = arr_b1[v18 >> 12 & 0x3F];
                    arr_b2[v8 + 1] = arr_b1[v18 >> 6 & 0x3F];
                    int v19 = v8 + 3;
                    arr_b2[v8 + 2] = arr_b1[v18 & 0x3F];
                    if(this.doPadding) {
                        arr_b2[v19] = 61;
                        v19 = v8 + 4;
                    }
                    if(this.doNewline) {
                        if(this.doCr) {
                            arr_b2[v19] = 13;
                            ++v19;
                        }
                        arr_b2[v19] = 10;
                        ++v19;
                    }
                    v8 = v19;
                }
                else if(this.doNewline && v8 > 0 && v2 != 19) {
                    if(this.doCr) {
                        arr_b2[v8] = 13;
                        ++v8;
                    }
                    arr_b2[v8] = 10;
                    ++v8;
                }
            }
            else if(v5 == v3 - 1) {
                int v20 = this.tailLen;
                this.tailLen = v20 + 1;
                this.tail[v20] = arr_b[v5];
            }
            else if(v5 == v3 - 2) {
                int v21 = this.tailLen;
                this.tailLen = v21 + 1;
                this.tail[v21] = arr_b[v5];
                this.tailLen = v21 + 2;
                this.tail[v21 + 1] = arr_b[v5 + 1];
            }
            this.op = v8;
            this.count = v2;
            return true;
        }
    }

    static final boolean $assertionsDisabled = false;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;
    private static final Charset UTF_8;

    static {
        Base64.UTF_8 = Charset.forName("UTF-8");
    }

    public static byte[] decode(String s) {
        return Base64.decode(s, 2);
    }

    public static byte[] decode(String s, int v) {
        return Base64.decode(s.getBytes(Base64.UTF_8), v);
    }

    public static byte[] decode(byte[] arr_b, int v) {
        return Base64.decode(arr_b, 0, arr_b.length, v);
    }

    public static byte[] decode(byte[] arr_b, int v, int v1, int v2) {
        Decoder base64$Decoder0 = new Decoder(v2, new byte[v1 * 3 / 4]);
        if(!base64$Decoder0.process(arr_b, v, v1, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        if(base64$Decoder0.op == base64$Decoder0.output.length) {
            return base64$Decoder0.output;
        }
        byte[] arr_b1 = new byte[base64$Decoder0.op];
        System.arraycopy(base64$Decoder0.output, 0, arr_b1, 0, base64$Decoder0.op);
        return arr_b1;
    }

    public static String encode(byte[] arr_b) {
        return Base64.encodeToString(arr_b, 2);
    }

    public static byte[] encode(byte[] arr_b, int v) {
        return Base64.encode(arr_b, 0, arr_b.length, v);
    }

    public static byte[] encode(byte[] arr_b, int v, int v1, int v2) {
        Encoder base64$Encoder0 = new Encoder(v2, null);
        int v3 = v1 / 3 * 4;
        int v4 = 2;
        if(!base64$Encoder0.doPadding) {
            switch(v1 % 3) {
                case 1: {
                    v3 += 2;
                    break;
                }
                case 2: {
                    v3 += 3;
                }
            }
        }
        else if(v1 % 3 > 0) {
            v3 += 4;
        }
        if(base64$Encoder0.doNewline && v1 > 0) {
            if(!base64$Encoder0.doCr) {
                v4 = 1;
            }
            v3 += ((v1 - 1) / 57 + 1) * v4;
        }
        base64$Encoder0.output = new byte[v3];
        base64$Encoder0.process(arr_b, v, v1, true);
        return base64$Encoder0.output;
    }

    public static String encodeToString(byte[] arr_b, int v) {
        try {
            return new String(Base64.encode(arr_b, v), "US-ASCII");
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new AssertionError(unsupportedEncodingException0);
        }
    }

    public static String encodeToString(byte[] arr_b, int v, int v1, int v2) {
        try {
            return new String(Base64.encode(arr_b, v, v1, v2), "US-ASCII");
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new AssertionError(unsupportedEncodingException0);
        }
    }

    public static byte[] urlSafeDecode(String s) {
        return Base64.decode(s, 11);
    }

    public static String urlSafeEncode(byte[] arr_b) {
        return Base64.encodeToString(arr_b, 11);
    }
}

