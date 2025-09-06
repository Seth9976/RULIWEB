package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

final class Ed25519 {
    static class CachedXYT {
        final long[] t2d;
        final long[] yMinusX;
        final long[] yPlusX;

        CachedXYT() {
            this(new long[10], new long[10], new long[10]);
        }

        CachedXYT(CachedXYT ed25519$CachedXYT0) {
            this.yPlusX = Arrays.copyOf(ed25519$CachedXYT0.yPlusX, 10);
            this.yMinusX = Arrays.copyOf(ed25519$CachedXYT0.yMinusX, 10);
            this.t2d = Arrays.copyOf(ed25519$CachedXYT0.t2d, 10);
        }

        CachedXYT(long[] arr_v, long[] arr_v1, long[] arr_v2) {
            this.yPlusX = arr_v;
            this.yMinusX = arr_v1;
            this.t2d = arr_v2;
        }

        void copyConditional(CachedXYT ed25519$CachedXYT0, int v) {
            Curve25519.copyConditional(this.yPlusX, ed25519$CachedXYT0.yPlusX, v);
            Curve25519.copyConditional(this.yMinusX, ed25519$CachedXYT0.yMinusX, v);
            Curve25519.copyConditional(this.t2d, ed25519$CachedXYT0.t2d, v);
        }

        void multByZ(long[] arr_v, long[] arr_v1) {
            System.arraycopy(arr_v1, 0, arr_v, 0, 10);
        }
    }

    static class CachedXYZT extends CachedXYT {
        private final long[] z;

        CachedXYZT() {
            this(new long[10], new long[10], new long[10], new long[10]);
        }

        CachedXYZT(XYZT ed25519$XYZT0) {
            Field25519.sum(this.yPlusX, ed25519$XYZT0.xyz.y, ed25519$XYZT0.xyz.x);
            Field25519.sub(this.yMinusX, ed25519$XYZT0.xyz.y, ed25519$XYZT0.xyz.x);
            System.arraycopy(ed25519$XYZT0.xyz.z, 0, this.z, 0, 10);
            Field25519.mult(this.t2d, ed25519$XYZT0.t, Ed25519Constants.D2);
        }

        CachedXYZT(long[] arr_v, long[] arr_v1, long[] arr_v2, long[] arr_v3) {
            super(arr_v, arr_v1, arr_v3);
            this.z = arr_v2;
        }

        @Override  // com.google.crypto.tink.subtle.Ed25519$CachedXYT
        public void multByZ(long[] arr_v, long[] arr_v1) {
            Field25519.mult(arr_v, arr_v1, this.z);
        }
    }

    static class PartialXYZT {
        final long[] t;
        final XYZ xyz;

        PartialXYZT() {
            this(new XYZ(), new long[10]);
        }

        PartialXYZT(PartialXYZT ed25519$PartialXYZT0) {
            this.xyz = new XYZ(ed25519$PartialXYZT0.xyz);
            this.t = Arrays.copyOf(ed25519$PartialXYZT0.t, 10);
        }

        PartialXYZT(XYZ ed25519$XYZ0, long[] arr_v) {
            this.xyz = ed25519$XYZ0;
            this.t = arr_v;
        }
    }

    static class XYZ {
        final long[] x;
        final long[] y;
        final long[] z;

        XYZ() {
            this(new long[10], new long[10], new long[10]);
        }

        XYZ(PartialXYZT ed25519$PartialXYZT0) {
            XYZ.fromPartialXYZT(this, ed25519$PartialXYZT0);
        }

        XYZ(XYZ ed25519$XYZ0) {
            this.x = Arrays.copyOf(ed25519$XYZ0.x, 10);
            this.y = Arrays.copyOf(ed25519$XYZ0.y, 10);
            this.z = Arrays.copyOf(ed25519$XYZ0.z, 10);
        }

        XYZ(long[] arr_v, long[] arr_v1, long[] arr_v2) {
            this.x = arr_v;
            this.y = arr_v1;
            this.z = arr_v2;
        }

        static XYZ fromPartialXYZT(XYZ ed25519$XYZ0, PartialXYZT ed25519$PartialXYZT0) {
            Field25519.mult(ed25519$XYZ0.x, ed25519$PartialXYZT0.xyz.x, ed25519$PartialXYZT0.t);
            Field25519.mult(ed25519$XYZ0.y, ed25519$PartialXYZT0.xyz.y, ed25519$PartialXYZT0.xyz.z);
            Field25519.mult(ed25519$XYZ0.z, ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.t);
            return ed25519$XYZ0;
        }

        boolean isOnCurve() {
            long[] arr_v = new long[10];
            Field25519.square(arr_v, this.x);
            long[] arr_v1 = new long[10];
            Field25519.square(arr_v1, this.y);
            long[] arr_v2 = new long[10];
            Field25519.square(arr_v2, this.z);
            long[] arr_v3 = new long[10];
            Field25519.square(arr_v3, arr_v2);
            long[] arr_v4 = new long[10];
            Field25519.sub(arr_v4, arr_v1, arr_v);
            Field25519.mult(arr_v4, arr_v4, arr_v2);
            long[] arr_v5 = new long[10];
            Field25519.mult(arr_v5, arr_v, arr_v1);
            Field25519.mult(arr_v5, arr_v5, Ed25519Constants.D);
            Field25519.sum(arr_v5, arr_v3);
            Field25519.reduce(arr_v5, arr_v5);
            return Bytes.equal(Field25519.contract(arr_v4), Field25519.contract(arr_v5));
        }

        byte[] toBytes() [...] // 潜在的解密器
    }

    static class XYZT {
        final long[] t;
        final XYZ xyz;

        XYZT() {
            this(new XYZ(), new long[10]);
        }

        XYZT(PartialXYZT ed25519$PartialXYZT0) {
            XYZT.fromPartialXYZT(this, ed25519$PartialXYZT0);
        }

        XYZT(XYZ ed25519$XYZ0, long[] arr_v) {
            this.xyz = ed25519$XYZ0;
            this.t = arr_v;
        }

        static XYZT access$400(XYZT ed25519$XYZT0, PartialXYZT ed25519$PartialXYZT0) {
            return XYZT.fromPartialXYZT(ed25519$XYZT0, ed25519$PartialXYZT0);
        }

        static XYZT access$500(byte[] arr_b) throws GeneralSecurityException {
            return XYZT.fromBytesNegateVarTime(arr_b);
        }

        private static XYZT fromBytesNegateVarTime(byte[] arr_b) throws GeneralSecurityException {
            long[] arr_v = new long[10];
            long[] arr_v1 = Field25519.expand(arr_b);
            long[] arr_v2 = new long[10];
            arr_v2[0] = 1L;
            long[] arr_v3 = new long[10];
            long[] arr_v4 = new long[10];
            long[] arr_v5 = new long[10];
            long[] arr_v6 = new long[10];
            long[] arr_v7 = new long[10];
            Field25519.square(arr_v4, arr_v1);
            Field25519.mult(arr_v5, arr_v4, Ed25519Constants.D);
            Field25519.sub(arr_v4, arr_v4, arr_v2);
            Field25519.sum(arr_v5, arr_v5, arr_v2);
            long[] arr_v8 = new long[10];
            Field25519.square(arr_v8, arr_v5);
            Field25519.mult(arr_v8, arr_v8, arr_v5);
            Field25519.square(arr_v, arr_v8);
            Field25519.mult(arr_v, arr_v, arr_v5);
            Field25519.mult(arr_v, arr_v, arr_v4);
            Ed25519.pow2252m3(arr_v, arr_v);
            Field25519.mult(arr_v, arr_v, arr_v8);
            Field25519.mult(arr_v, arr_v, arr_v4);
            Field25519.square(arr_v6, arr_v);
            Field25519.mult(arr_v6, arr_v6, arr_v5);
            Field25519.sub(arr_v7, arr_v6, arr_v4);
            if(Ed25519.isNonZeroVarTime(arr_v7)) {
                Field25519.sum(arr_v7, arr_v6, arr_v4);
                if(Ed25519.isNonZeroVarTime(arr_v7)) {
                    throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19");
                }
                Field25519.mult(arr_v, arr_v, Ed25519Constants.SQRTM1);
            }
            if(!Ed25519.isNonZeroVarTime(arr_v) && (arr_b[0x1F] & 0xFF) >> 7 != 0) {
                throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x\'s least significant bit is not zero");
            }
            if(Ed25519.getLsb(arr_v) == (arr_b[0x1F] & 0xFF) >> 7) {
                Ed25519.neg(arr_v, arr_v);
            }
            Field25519.mult(arr_v3, arr_v, arr_v1);
            return new XYZT(new XYZ(arr_v, arr_v1, arr_v2), arr_v3);
        }

        private static XYZT fromPartialXYZT(XYZT ed25519$XYZT0, PartialXYZT ed25519$PartialXYZT0) {
            Field25519.mult(ed25519$XYZT0.xyz.x, ed25519$PartialXYZT0.xyz.x, ed25519$PartialXYZT0.t);
            Field25519.mult(ed25519$XYZT0.xyz.y, ed25519$PartialXYZT0.xyz.y, ed25519$PartialXYZT0.xyz.z);
            Field25519.mult(ed25519$XYZT0.xyz.z, ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.t);
            Field25519.mult(ed25519$XYZT0.t, ed25519$PartialXYZT0.xyz.x, ed25519$PartialXYZT0.xyz.y);
            return ed25519$XYZT0;
        }
    }

    private static final CachedXYT CACHED_NEUTRAL = null;
    static final byte[] GROUP_ORDER = null;
    private static final PartialXYZT NEUTRAL = null;
    public static final int PUBLIC_KEY_LEN = 0x20;
    public static final int SECRET_KEY_LEN = 0x20;
    public static final int SIGNATURE_LEN = 0x40;

    static {
        Ed25519.CACHED_NEUTRAL = new CachedXYT(new long[]{1L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L}, new long[]{1L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L}, new long[]{0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L});
        Ed25519.NEUTRAL = new PartialXYZT(new XYZ(new long[]{0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L}, new long[]{1L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L}, new long[]{1L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L}), new long[]{1L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L});
        Ed25519.GROUP_ORDER = new byte[]{-19, -45, -11, 92, 26, 99, 18, 88, -42, -100, -9, -94, -34, -7, -34, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16};
    }

    private static void add(PartialXYZT ed25519$PartialXYZT0, XYZT ed25519$XYZT0, CachedXYT ed25519$CachedXYT0) {
        long[] arr_v = new long[10];
        Field25519.sum(ed25519$PartialXYZT0.xyz.x, ed25519$XYZT0.xyz.y, ed25519$XYZT0.xyz.x);
        Field25519.sub(ed25519$PartialXYZT0.xyz.y, ed25519$XYZT0.xyz.y, ed25519$XYZT0.xyz.x);
        Field25519.mult(ed25519$PartialXYZT0.xyz.y, ed25519$PartialXYZT0.xyz.y, ed25519$CachedXYT0.yMinusX);
        Field25519.mult(ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.xyz.x, ed25519$CachedXYT0.yPlusX);
        Field25519.mult(ed25519$PartialXYZT0.t, ed25519$XYZT0.t, ed25519$CachedXYT0.t2d);
        ed25519$CachedXYT0.multByZ(ed25519$PartialXYZT0.xyz.x, ed25519$XYZT0.xyz.z);
        Field25519.sum(arr_v, ed25519$PartialXYZT0.xyz.x, ed25519$PartialXYZT0.xyz.x);
        Field25519.sub(ed25519$PartialXYZT0.xyz.x, ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.xyz.y);
        Field25519.sum(ed25519$PartialXYZT0.xyz.y, ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.xyz.y);
        Field25519.sum(ed25519$PartialXYZT0.xyz.z, arr_v, ed25519$PartialXYZT0.t);
        Field25519.sub(ed25519$PartialXYZT0.t, arr_v, ed25519$PartialXYZT0.t);
    }

    private static XYZ doubleScalarMultVarTime(byte[] arr_b, XYZT ed25519$XYZT0, byte[] arr_b1) {
        CachedXYZT[] arr_ed25519$CachedXYZT = new CachedXYZT[8];
        arr_ed25519$CachedXYZT[0] = new CachedXYZT(ed25519$XYZT0);
        PartialXYZT ed25519$PartialXYZT0 = new PartialXYZT();
        Ed25519.doubleXYZT(ed25519$PartialXYZT0, ed25519$XYZT0);
        XYZT ed25519$XYZT1 = new XYZT(ed25519$PartialXYZT0);
        for(int v = 1; v < 8; ++v) {
            Ed25519.add(ed25519$PartialXYZT0, ed25519$XYZT1, arr_ed25519$CachedXYZT[v - 1]);
            arr_ed25519$CachedXYZT[v] = new CachedXYZT(new XYZT(ed25519$PartialXYZT0));
        }
        byte[] arr_b2 = Ed25519.slide(arr_b);
        byte[] arr_b3 = Ed25519.slide(arr_b1);
        PartialXYZT ed25519$PartialXYZT1 = new PartialXYZT(Ed25519.NEUTRAL);
        XYZT ed25519$XYZT2 = new XYZT();
        int v1;
        for(v1 = 0xFF; v1 >= 0 && arr_b2[v1] == 0 && arr_b3[v1] == 0; --v1) {
        }
        while(v1 >= 0) {
            Ed25519.doubleXYZ(ed25519$PartialXYZT1, new XYZ(ed25519$PartialXYZT1));
            int v2 = arr_b2[v1];
            if(v2 > 0) {
                Ed25519.add(ed25519$PartialXYZT1, XYZT.access$400(ed25519$XYZT2, ed25519$PartialXYZT1), arr_ed25519$CachedXYZT[arr_b2[v1] / 2]);
            }
            else if(v2 < 0) {
                Ed25519.sub(ed25519$PartialXYZT1, XYZT.access$400(ed25519$XYZT2, ed25519$PartialXYZT1), arr_ed25519$CachedXYZT[-arr_b2[v1] / 2]);
            }
            int v3 = arr_b3[v1];
            if(v3 > 0) {
                Ed25519.add(ed25519$PartialXYZT1, XYZT.access$400(ed25519$XYZT2, ed25519$PartialXYZT1), Ed25519Constants.B2[arr_b3[v1] / 2]);
            }
            else if(v3 < 0) {
                Ed25519.sub(ed25519$PartialXYZT1, XYZT.access$400(ed25519$XYZT2, ed25519$PartialXYZT1), Ed25519Constants.B2[-arr_b3[v1] / 2]);
            }
            --v1;
        }
        return new XYZ(ed25519$PartialXYZT1);
    }

    private static void doubleXYZ(PartialXYZT ed25519$PartialXYZT0, XYZ ed25519$XYZ0) {
        long[] arr_v = new long[10];
        Field25519.square(ed25519$PartialXYZT0.xyz.x, ed25519$XYZ0.x);
        Field25519.square(ed25519$PartialXYZT0.xyz.z, ed25519$XYZ0.y);
        Field25519.square(ed25519$PartialXYZT0.t, ed25519$XYZ0.z);
        Field25519.sum(ed25519$PartialXYZT0.t, ed25519$PartialXYZT0.t, ed25519$PartialXYZT0.t);
        Field25519.sum(ed25519$PartialXYZT0.xyz.y, ed25519$XYZ0.x, ed25519$XYZ0.y);
        Field25519.square(arr_v, ed25519$PartialXYZT0.xyz.y);
        Field25519.sum(ed25519$PartialXYZT0.xyz.y, ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.xyz.x);
        Field25519.sub(ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.xyz.x);
        Field25519.sub(ed25519$PartialXYZT0.xyz.x, arr_v, ed25519$PartialXYZT0.xyz.y);
        Field25519.sub(ed25519$PartialXYZT0.t, ed25519$PartialXYZT0.t, ed25519$PartialXYZT0.xyz.z);
    }

    private static void doubleXYZT(PartialXYZT ed25519$PartialXYZT0, XYZT ed25519$XYZT0) {
        Ed25519.doubleXYZ(ed25519$PartialXYZT0, ed25519$XYZT0.xyz);
    }

    // 去混淆评级： 低(20)
    private static int eq(int a, int b) [...] // Inlined contents

    static byte[] getHashedScalar(byte[] arr_b) throws GeneralSecurityException {
        MessageDigest messageDigest0 = (MessageDigest)EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        messageDigest0.update(arr_b, 0, 0x20);
        byte[] arr_b1 = messageDigest0.digest();
        arr_b1[0] = (byte)(arr_b1[0] & 0xF8);
        byte b = (byte)(arr_b1[0x1F] & 0x7F);
        arr_b1[0x1F] = b;
        arr_b1[0x1F] = (byte)(b | 0x40);
        return arr_b1;
    }

    private static int getLsb(long[] arr_v) {
        return Field25519.contract(arr_v)[0] & 1;
    }

    private static boolean isNonZeroVarTime(long[] arr_v) {
        long[] arr_v1 = new long[arr_v.length + 1];
        System.arraycopy(arr_v, 0, arr_v1, 0, arr_v.length);
        Field25519.reduceCoefficients(arr_v1);
        byte[] arr_b = Field25519.contract(arr_v1);
        for(int v = 0; v < arr_b.length; ++v) {
            if(arr_b[v] != 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSmallerThanGroupOrder(byte[] arr_b) {
        for(int v = 0x1F; v >= 0; --v) {
            int v1 = arr_b[v] & 0xFF;
            int v2 = Ed25519.GROUP_ORDER[v] & 0xFF;
            if(v1 != v2) {
                return v1 < v2;
            }
        }
        return false;
    }

    private static long load3(byte[] arr_b, int v) {
        return ((long)(arr_b[v + 2] & 0xFF)) << 16 | (((long)arr_b[v]) & 0xFFL | ((long)(arr_b[v + 1] & 0xFF)) << 8);
    }

    private static long load4(byte[] arr_b, int v) {
        long v1 = Ed25519.load3(arr_b, v);
        return ((long)(arr_b[v + 3] & 0xFF)) << 24 | v1;
    }

    private static void mulAdd(byte[] arr_b, byte[] arr_b1, byte[] arr_b2, byte[] arr_b3) {
        long v = Ed25519.load3(arr_b1, 0);
        long v1 = Ed25519.load4(arr_b1, 2) >> 5 & 0x1FFFFFL;
        long v2 = Ed25519.load3(arr_b1, 5) >> 2 & 0x1FFFFFL;
        long v3 = Ed25519.load4(arr_b1, 7) >> 7 & 0x1FFFFFL;
        long v4 = Ed25519.load4(arr_b1, 10) >> 4 & 0x1FFFFFL;
        long v5 = Ed25519.load3(arr_b1, 13) >> 1 & 0x1FFFFFL;
        long v6 = Ed25519.load4(arr_b1, 15) >> 6 & 0x1FFFFFL;
        long v7 = Ed25519.load3(arr_b1, 18) >> 3 & 0x1FFFFFL;
        long v8 = Ed25519.load3(arr_b1, 21);
        long v9 = Ed25519.load4(arr_b1, 23) >> 5 & 0x1FFFFFL;
        long v10 = Ed25519.load3(arr_b1, 26) >> 2 & 0x1FFFFFL;
        long v11 = Ed25519.load4(arr_b1, 28);
        long v12 = Ed25519.load3(arr_b2, 0);
        long v13 = Ed25519.load4(arr_b2, 2) >> 5 & 0x1FFFFFL;
        long v14 = Ed25519.load3(arr_b2, 5) >> 2 & 0x1FFFFFL;
        long v15 = Ed25519.load4(arr_b2, 7) >> 7 & 0x1FFFFFL;
        long v16 = Ed25519.load4(arr_b2, 10) >> 4 & 0x1FFFFFL;
        long v17 = Ed25519.load3(arr_b2, 13) >> 1 & 0x1FFFFFL;
        long v18 = Ed25519.load4(arr_b2, 15) >> 6 & 0x1FFFFFL;
        long v19 = Ed25519.load3(arr_b2, 18) >> 3 & 0x1FFFFFL;
        long v20 = Ed25519.load3(arr_b2, 21);
        long v21 = Ed25519.load4(arr_b2, 23) >> 5 & 0x1FFFFFL;
        long v22 = Ed25519.load3(arr_b2, 26) >> 2 & 0x1FFFFFL;
        long v23 = Ed25519.load4(arr_b2, 28);
        long v24 = Ed25519.load3(arr_b3, 0);
        long v25 = Ed25519.load4(arr_b3, 2);
        long v26 = Ed25519.load3(arr_b3, 5);
        long v27 = Ed25519.load4(arr_b3, 7);
        long v28 = Ed25519.load4(arr_b3, 10);
        long v29 = Ed25519.load3(arr_b3, 13);
        long v30 = Ed25519.load4(arr_b3, 15);
        long v31 = Ed25519.load3(arr_b3, 18);
        long v32 = Ed25519.load3(arr_b3, 21);
        long v33 = Ed25519.load4(arr_b3, 23);
        long v34 = (v24 & 0x1FFFFFL) + (v & 0x1FFFFFL) * (v12 & 0x1FFFFFL);
        long v35 = (v26 >> 2 & 0x1FFFFFL) + (v & 0x1FFFFFL) * v14 + v1 * v13 + v2 * (v12 & 0x1FFFFFL);
        long v36 = (v28 >> 4 & 0x1FFFFFL) + (v & 0x1FFFFFL) * v16 + v1 * v15 + v2 * v14 + v3 * v13 + v4 * (v12 & 0x1FFFFFL);
        long v37 = (v30 >> 6 & 0x1FFFFFL) + (v & 0x1FFFFFL) * v18 + v1 * v17 + v2 * v16 + v3 * v15 + v4 * v14 + v5 * v13 + v6 * (v12 & 0x1FFFFFL);
        long v38 = (v32 & 0x1FFFFFL) + (v & 0x1FFFFFL) * (v20 & 0x1FFFFFL) + v1 * v19 + v2 * v18 + v3 * v17 + v4 * v16 + v5 * v15 + v6 * v14 + v7 * v13 + (v8 & 0x1FFFFFL) * (v12 & 0x1FFFFFL);
        long v39 = (Ed25519.load3(arr_b3, 26) >> 2 & 0x1FFFFFL) + (v & 0x1FFFFFL) * v22 + v1 * v21 + v2 * (v20 & 0x1FFFFFL) + v3 * v19 + v4 * v18 + v5 * v17 + v6 * v16 + v7 * v15 + (v8 & 0x1FFFFFL) * v14 + v9 * v13 + v10 * (v12 & 0x1FFFFFL);
        long v40 = v1 * (v23 >> 7) + v2 * v22 + v3 * v21 + v4 * (v20 & 0x1FFFFFL) + v5 * v19 + v6 * v18 + v7 * v17 + (v8 & 0x1FFFFFL) * v16 + v9 * v15 + v10 * v14 + v13 * (v11 >> 7);
        long v41 = v3 * (v23 >> 7) + v4 * v22 + v5 * v21 + v6 * (v20 & 0x1FFFFFL) + v7 * v19 + (v8 & 0x1FFFFFL) * v18 + v9 * v17 + v10 * v16 + v15 * (v11 >> 7);
        long v42 = v5 * (v23 >> 7) + v6 * v22 + v7 * v21 + (v8 & 0x1FFFFFL) * (v20 & 0x1FFFFFL) + v9 * v19 + v10 * v18 + v17 * (v11 >> 7);
        long v43 = v7 * (v23 >> 7) + (v8 & 0x1FFFFFL) * v22 + v9 * v21 + v10 * (v20 & 0x1FFFFFL) + v19 * (v11 >> 7);
        long v44 = v9 * (v23 >> 7) + v10 * v22 + v21 * (v11 >> 7);
        long v45 = (v11 >> 7) * (v23 >> 7);
        long v46 = v34 + 0x100000L >> 21;
        long v47 = (v25 >> 5 & 0x1FFFFFL) + (v & 0x1FFFFFL) * v13 + v1 * (v12 & 0x1FFFFFL) + v46;
        long v48 = v35 + 0x100000L >> 21;
        long v49 = (v27 >> 7 & 0x1FFFFFL) + (v & 0x1FFFFFL) * v15 + v1 * v14 + v2 * v13 + v3 * (v12 & 0x1FFFFFL) + v48;
        long v50 = v36 + 0x100000L >> 21;
        long v51 = (v29 >> 1 & 0x1FFFFFL) + (v & 0x1FFFFFL) * v17 + v1 * v16 + v2 * v15 + v3 * v14 + v4 * v13 + v5 * (v12 & 0x1FFFFFL) + v50;
        long v52 = v37 + 0x100000L >> 21;
        long v53 = (v31 >> 3 & 0x1FFFFFL) + (v & 0x1FFFFFL) * v19 + v1 * v18 + v2 * v17 + v3 * v16 + v4 * v15 + v5 * v14 + v6 * v13 + v7 * (v12 & 0x1FFFFFL) + v52;
        long v54 = v38 + 0x100000L >> 21;
        long v55 = (v33 >> 5 & 0x1FFFFFL) + (v & 0x1FFFFFL) * v21 + v1 * (v20 & 0x1FFFFFL) + v2 * v19 + v3 * v18 + v4 * v17 + v5 * v16 + v6 * v15 + v7 * v14 + (v8 & 0x1FFFFFL) * v13 + v9 * (v12 & 0x1FFFFFL) + v54;
        long v56 = v39 + 0x100000L >> 21;
        long v57 = (Ed25519.load4(arr_b3, 28) >> 7) + (v & 0x1FFFFFL) * (v23 >> 7) + v1 * v22 + v2 * v21 + v3 * (v20 & 0x1FFFFFL) + v4 * v19 + v5 * v18 + v6 * v17 + v7 * v16 + (v8 & 0x1FFFFFL) * v15 + v9 * v14 + v10 * v13 + (v12 & 0x1FFFFFL) * (v11 >> 7) + v56;
        long v58 = v40 + 0x100000L >> 21;
        long v59 = v2 * (v23 >> 7) + v3 * v22 + v4 * v21 + v5 * (v20 & 0x1FFFFFL) + v6 * v19 + v7 * v18 + (v8 & 0x1FFFFFL) * v17 + v9 * v16 + v10 * v15 + v14 * (v11 >> 7) + v58;
        long v60 = v41 + 0x100000L >> 21;
        long v61 = v4 * (v23 >> 7) + v5 * v22 + v6 * v21 + v7 * (v20 & 0x1FFFFFL) + (v8 & 0x1FFFFFL) * v19 + v9 * v18 + v10 * v17 + v16 * (v11 >> 7) + v60;
        long v62 = v42 + 0x100000L >> 21;
        long v63 = v6 * (v23 >> 7) + v7 * v22 + (v8 & 0x1FFFFFL) * v21 + v9 * (v20 & 0x1FFFFFL) + v10 * v19 + v18 * (v11 >> 7) + v62;
        long v64 = v43 + 0x100000L >> 21;
        long v65 = (v8 & 0x1FFFFFL) * (v23 >> 7) + v9 * v22 + v10 * v21 + (v20 & 0x1FFFFFL) * (v11 >> 7) + v64;
        long v66 = v44 + 0x100000L >> 21;
        long v67 = v10 * (v23 >> 7) + v22 * (v11 >> 7) + v66;
        long v68 = v45 + 0x100000L >> 21;
        long v69 = v47 + 0x100000L >> 21;
        long v70 = v49 + 0x100000L >> 21;
        long v71 = v51 + 0x100000L >> 21;
        long v72 = v53 + 0x100000L >> 21;
        long v73 = v55 + 0x100000L >> 21;
        long v74 = v57 + 0x100000L >> 21;
        long v75 = v59 + 0x100000L >> 21;
        long v76 = v61 + 0x100000L >> 21;
        long v77 = v63 + 0x100000L >> 21;
        long v78 = v43 - (v64 << 21) + v77;
        long v79 = v65 + 0x100000L >> 21;
        long v80 = v44 - (v66 << 21) + v79;
        long v81 = v65 - (v79 << 21);
        long v82 = v67 + 0x100000L >> 21;
        long v83 = v45 - (v68 << 21) + v82;
        long v84 = v67 - (v82 << 21);
        long v85 = v42 - (v62 << 21) + v76 - v68 * 0xA6F7DL;
        long v86 = v41 - (v60 << 21) + v75 - v68 * 0xF39ADL + v83 * 0x215D1L - v84 * 0xA6F7DL;
        long v87 = v40 - (v58 << 21) + v74 + v68 * 470296L + v83 * 0x9FB67L - v84 * 0xF39ADL + v80 * 0x215D1L - v81 * 0xA6F7DL;
        long v88 = v37 - (v52 << 21) + v71 + v78 * 0xA2C13L;
        long v89 = v38 - (v54 << 21) + v72 + v80 * 0xA2C13L + v81 * 470296L + v78 * 0x9FB67L;
        long v90 = v39 - (v56 << 21) + v73 + v83 * 0xA2C13L + v84 * 470296L + v80 * 0x9FB67L - v81 * 0xF39ADL + v78 * 0x215D1L;
        long v91 = v88 + 0x100000L >> 21;
        long v92 = v53 - (v72 << 21) + v81 * 0xA2C13L + v78 * 470296L + v91;
        long v93 = v89 + 0x100000L >> 21;
        long v94 = v55 - (v73 << 21) + v84 * 0xA2C13L + v80 * 470296L + v81 * 0x9FB67L - v78 * 0xF39ADL + v93;
        long v95 = v90 + 0x100000L >> 21;
        long v96 = v57 - (v74 << 21) + v68 * 0xA2C13L + v83 * 470296L + v84 * 0x9FB67L - v80 * 0xF39ADL + v81 * 0x215D1L - v78 * 0xA6F7DL + v95;
        long v97 = v87 + 0x100000L >> 21;
        long v98 = v59 - (v75 << 21) + v68 * 0x9FB67L - v83 * 0xF39ADL + v84 * 0x215D1L - v80 * 0xA6F7DL + v97;
        long v99 = v86 + 0x100000L >> 21;
        long v100 = v61 - (v76 << 21) + v68 * 0x215D1L - v83 * 0xA6F7DL + v99;
        long v101 = v85 + 0x100000L >> 21;
        long v102 = v63 - (v77 << 21) + v101;
        long v103 = v92 + 0x100000L >> 21;
        long v104 = v94 + 0x100000L >> 21;
        long v105 = v96 + 0x100000L >> 21;
        long v106 = v87 - (v97 << 21) + v105;
        long v107 = v98 + 0x100000L >> 21;
        long v108 = v86 - (v99 << 21) + v107;
        long v109 = v98 - (v107 << 21);
        long v110 = v100 + 0x100000L >> 21;
        long v111 = v85 - (v101 << 21) + v110;
        long v112 = v100 - (v110 << 21);
        long v113 = v90 - (v95 << 21) + v104 - v102 * 0xA6F7DL;
        long v114 = v89 - (v93 << 21) + v103 - v102 * 0xF39ADL + v111 * 0x215D1L - v112 * 0xA6F7DL;
        long v115 = v88 - (v91 << 21) + v102 * 470296L + v111 * 0x9FB67L - v112 * 0xF39ADL + v108 * 0x215D1L - v109 * 0xA6F7DL;
        long v116 = v34 - (v46 << 21) + v106 * 0xA2C13L;
        long v117 = v35 - (v48 << 21) + v69 + v108 * 0xA2C13L + v109 * 470296L + v106 * 0x9FB67L;
        long v118 = v36 - (v50 << 21) + v70 + v111 * 0xA2C13L + v112 * 470296L + v108 * 0x9FB67L - v109 * 0xF39ADL + v106 * 0x215D1L;
        long v119 = v116 + 0x100000L >> 21;
        long v120 = v47 - (v69 << 21) + v109 * 0xA2C13L + v106 * 470296L + v119;
        long v121 = v117 + 0x100000L >> 21;
        long v122 = v49 - (v70 << 21) + v112 * 0xA2C13L + v108 * 470296L + v109 * 0x9FB67L - v106 * 0xF39ADL + v121;
        long v123 = v118 + 0x100000L >> 21;
        long v124 = v51 - (v71 << 21) + v102 * 0xA2C13L + v111 * 470296L + v112 * 0x9FB67L - v108 * 0xF39ADL + v109 * 0x215D1L - v106 * 0xA6F7DL + v123;
        long v125 = v115 + 0x100000L >> 21;
        long v126 = v92 - (v103 << 21) + v102 * 0x9FB67L - v111 * 0xF39ADL + v112 * 0x215D1L - v108 * 0xA6F7DL + v125;
        long v127 = v114 + 0x100000L >> 21;
        long v128 = v94 - (v104 << 21) + v102 * 0x215D1L - v111 * 0xA6F7DL + v127;
        long v129 = v113 + 0x100000L >> 21;
        long v130 = v96 - (v105 << 21) + v129;
        long v131 = v120 + 0x100000L >> 21;
        long v132 = v122 + 0x100000L >> 21;
        long v133 = v124 + 0x100000L >> 21;
        long v134 = v126 + 0x100000L >> 21;
        long v135 = v128 + 0x100000L >> 21;
        long v136 = v130 + 0x100000L >> 21;
        long v137 = v116 - (v119 << 21) + v136 * 0xA2C13L;
        long v138 = v120 - (v131 << 21) + v136 * 470296L + (v137 >> 21);
        long v139 = v117 - (v121 << 21) + v131 + v136 * 0x9FB67L + (v138 >> 21);
        long v140 = v122 - (v132 << 21) - v136 * 0xF39ADL + (v139 >> 21);
        long v141 = v118 - (v123 << 21) + v132 + v136 * 0x215D1L + (v140 >> 21);
        long v142 = v124 - (v133 << 21) - v136 * 0xA6F7DL + (v141 >> 21);
        long v143 = v115 - (v125 << 21) + v133 + (v142 >> 21);
        long v144 = v126 - (v134 << 21) + (v143 >> 21);
        long v145 = v114 - (v127 << 21) + v134 + (v144 >> 21);
        long v146 = v128 - (v135 << 21) + (v145 >> 21);
        long v147 = v113 - (v129 << 21) + v135 + (v146 >> 21);
        long v148 = v130 - (v136 << 21) + (v147 >> 21);
        long v149 = v137 - (v137 >> 21 << 21) + 0xA2C13L * (v148 >> 21);
        long v150 = v138 - (v138 >> 21 << 21) + 470296L * (v148 >> 21) + (v149 >> 21);
        long v151 = v149 - (v149 >> 21 << 21);
        long v152 = v139 - (v139 >> 21 << 21) + 0x9FB67L * (v148 >> 21) + (v150 >> 21);
        long v153 = v150 - (v150 >> 21 << 21);
        long v154 = v140 - (v140 >> 21 << 21) - 0xF39ADL * (v148 >> 21) + (v152 >> 21);
        long v155 = v152 - (v152 >> 21 << 21);
        long v156 = v141 - (v141 >> 21 << 21) + 0x215D1L * (v148 >> 21) + (v154 >> 21);
        long v157 = v154 - (v154 >> 21 << 21);
        long v158 = v142 - (v142 >> 21 << 21) - (v148 >> 21) * 0xA6F7DL + (v156 >> 21);
        long v159 = v156 - (v156 >> 21 << 21);
        long v160 = v143 - (v143 >> 21 << 21) + (v158 >> 21);
        long v161 = v158 - (v158 >> 21 << 21);
        long v162 = v144 - (v144 >> 21 << 21) + (v160 >> 21);
        long v163 = v160 - (v160 >> 21 << 21);
        long v164 = v145 - (v145 >> 21 << 21) + (v162 >> 21);
        long v165 = v162 - (v162 >> 21 << 21);
        long v166 = v146 - (v146 >> 21 << 21) + (v164 >> 21);
        long v167 = v164 - (v164 >> 21 << 21);
        long v168 = v147 - (v147 >> 21 << 21) + (v166 >> 21);
        long v169 = v166 - (v166 >> 21 << 21);
        long v170 = v148 - (v148 >> 21 << 21) + (v168 >> 21);
        long v171 = v168 - (v168 >> 21 << 21);
        arr_b[0] = (byte)(((int)v151));
        arr_b[1] = (byte)(((int)(v151 >> 8)));
        arr_b[2] = (byte)(((int)(v151 >> 16 | v153 << 5)));
        arr_b[3] = (byte)(((int)(v153 >> 3)));
        arr_b[4] = (byte)(((int)(v153 >> 11)));
        arr_b[5] = (byte)(((int)(v153 >> 19 | v155 << 2)));
        arr_b[6] = (byte)(((int)(v155 >> 6)));
        arr_b[7] = (byte)(((int)(v155 >> 14 | v157 << 7)));
        arr_b[8] = (byte)(((int)(v157 >> 1)));
        arr_b[9] = (byte)(((int)(v157 >> 9)));
        arr_b[10] = (byte)(((int)(v157 >> 17 | v159 << 4)));
        arr_b[11] = (byte)(((int)(v159 >> 4)));
        arr_b[12] = (byte)(((int)(v159 >> 12)));
        arr_b[13] = (byte)(((int)(v159 >> 20 | v161 << 1)));
        arr_b[14] = (byte)(((int)(v161 >> 7)));
        arr_b[15] = (byte)(((int)(v161 >> 15 | v163 << 6)));
        arr_b[16] = (byte)(((int)(v163 >> 2)));
        arr_b[17] = (byte)(((int)(v163 >> 10)));
        arr_b[18] = (byte)(((int)(v163 >> 18 | v165 << 3)));
        arr_b[19] = (byte)(((int)(v165 >> 5)));
        arr_b[20] = (byte)(((int)(v165 >> 13)));
        arr_b[21] = (byte)(((int)v167));
        arr_b[22] = (byte)(((int)(v167 >> 8)));
        arr_b[23] = (byte)(((int)(v167 >> 16 | v169 << 5)));
        arr_b[24] = (byte)(((int)(v169 >> 3)));
        arr_b[25] = (byte)(((int)(v169 >> 11)));
        arr_b[26] = (byte)(((int)(v169 >> 19 | v171 << 2)));
        arr_b[27] = (byte)(((int)(v171 >> 6)));
        arr_b[28] = (byte)(((int)(v171 >> 14 | v170 << 7)));
        arr_b[29] = (byte)(((int)(v170 >> 1)));
        arr_b[30] = (byte)(((int)(v170 >> 9)));
        arr_b[0x1F] = (byte)(((int)(v170 >> 17)));
    }

    private static void neg(long[] arr_v, long[] arr_v1) {
        for(int v = 0; v < arr_v1.length; ++v) {
            arr_v[v] = -arr_v1[v];
        }
    }

    private static void pow2252m3(long[] arr_v, long[] arr_v1) {
        long[] arr_v2 = new long[10];
        long[] arr_v3 = new long[10];
        long[] arr_v4 = new long[10];
        Field25519.square(arr_v2, arr_v1);
        Field25519.square(arr_v3, arr_v2);
        Field25519.square(arr_v3, arr_v3);
        Field25519.mult(arr_v3, arr_v1, arr_v3);
        Field25519.mult(arr_v2, arr_v2, arr_v3);
        Field25519.square(arr_v2, arr_v2);
        Field25519.mult(arr_v2, arr_v3, arr_v2);
        Field25519.square(arr_v3, arr_v2);
        for(int v1 = 1; v1 < 5; ++v1) {
            Field25519.square(arr_v3, arr_v3);
        }
        Field25519.mult(arr_v2, arr_v3, arr_v2);
        Field25519.square(arr_v3, arr_v2);
        for(int v2 = 1; v2 < 10; ++v2) {
            Field25519.square(arr_v3, arr_v3);
        }
        Field25519.mult(arr_v3, arr_v3, arr_v2);
        Field25519.square(arr_v4, arr_v3);
        for(int v3 = 1; v3 < 20; ++v3) {
            Field25519.square(arr_v4, arr_v4);
        }
        Field25519.mult(arr_v3, arr_v4, arr_v3);
        Field25519.square(arr_v3, arr_v3);
        for(int v4 = 1; v4 < 10; ++v4) {
            Field25519.square(arr_v3, arr_v3);
        }
        Field25519.mult(arr_v2, arr_v3, arr_v2);
        Field25519.square(arr_v3, arr_v2);
        for(int v5 = 1; v5 < 50; ++v5) {
            Field25519.square(arr_v3, arr_v3);
        }
        Field25519.mult(arr_v3, arr_v3, arr_v2);
        Field25519.square(arr_v4, arr_v3);
        for(int v6 = 1; v6 < 100; ++v6) {
            Field25519.square(arr_v4, arr_v4);
        }
        Field25519.mult(arr_v3, arr_v4, arr_v3);
        Field25519.square(arr_v3, arr_v3);
        for(int v = 1; v < 50; ++v) {
            Field25519.square(arr_v3, arr_v3);
        }
        Field25519.mult(arr_v2, arr_v3, arr_v2);
        Field25519.square(arr_v2, arr_v2);
        Field25519.square(arr_v2, arr_v2);
        Field25519.mult(arr_v, arr_v2, arr_v1);
    }

    private static void reduce(byte[] arr_b) {
        long v = Ed25519.load3(arr_b, 0);
        long v1 = Ed25519.load4(arr_b, 2);
        long v2 = Ed25519.load3(arr_b, 5);
        long v3 = Ed25519.load4(arr_b, 7);
        long v4 = Ed25519.load4(arr_b, 10);
        long v5 = Ed25519.load3(arr_b, 13);
        long v6 = Ed25519.load4(arr_b, 15);
        long v7 = Ed25519.load3(arr_b, 18);
        long v8 = Ed25519.load3(arr_b, 21);
        long v9 = Ed25519.load4(arr_b, 23);
        long v10 = Ed25519.load3(arr_b, 26);
        long v11 = Ed25519.load4(arr_b, 28);
        long v12 = Ed25519.load4(arr_b, 0x1F);
        long v13 = Ed25519.load3(arr_b, 34);
        long v14 = Ed25519.load4(arr_b, 36);
        long v15 = Ed25519.load3(arr_b, 39);
        long v16 = Ed25519.load3(arr_b, 42);
        long v17 = Ed25519.load4(arr_b, 44);
        long v18 = Ed25519.load3(arr_b, 0x2F) >> 2 & 0x1FFFFFL;
        long v19 = Ed25519.load4(arr_b, 49) >> 7 & 0x1FFFFFL;
        long v20 = Ed25519.load4(arr_b, 52) >> 4 & 0x1FFFFFL;
        long v21 = Ed25519.load3(arr_b, 55) >> 1 & 0x1FFFFFL;
        long v22 = Ed25519.load4(arr_b, 57) >> 6 & 0x1FFFFFL;
        long v23 = Ed25519.load4(arr_b, 60);
        long v24 = (v16 & 0x1FFFFFL) - (v23 >> 3) * 0xA6F7DL;
        long v25 = (v14 >> 6 & 0x1FFFFFL) - (v23 >> 3) * 0xF39ADL + v22 * 0x215D1L - v21 * 0xA6F7DL;
        long v26 = (v12 >> 4 & 0x1FFFFFL) + (v23 >> 3) * 470296L + v22 * 0x9FB67L - v21 * 0xF39ADL + v20 * 0x215D1L - v19 * 0xA6F7DL;
        long v27 = (v6 >> 6 & 0x1FFFFFL) + v18 * 0xA2C13L;
        long v28 = (v8 & 0x1FFFFFL) + v20 * 0xA2C13L + v19 * 470296L + v18 * 0x9FB67L;
        long v29 = (v10 >> 2 & 0x1FFFFFL) + v22 * 0xA2C13L + v21 * 470296L + v20 * 0x9FB67L - v19 * 0xF39ADL + v18 * 0x215D1L;
        long v30 = v27 + 0x100000L >> 21;
        long v31 = (v7 >> 3 & 0x1FFFFFL) + v19 * 0xA2C13L + v18 * 470296L + v30;
        long v32 = v28 + 0x100000L >> 21;
        long v33 = (v9 >> 5 & 0x1FFFFFL) + v21 * 0xA2C13L + v20 * 470296L + v19 * 0x9FB67L - v18 * 0xF39ADL + v32;
        long v34 = v29 + 0x100000L >> 21;
        long v35 = (v11 >> 7 & 0x1FFFFFL) + (v23 >> 3) * 0xA2C13L + v22 * 470296L + v21 * 0x9FB67L - v20 * 0xF39ADL + v19 * 0x215D1L - v18 * 0xA6F7DL + v34;
        long v36 = v26 + 0x100000L >> 21;
        long v37 = (v13 >> 1 & 0x1FFFFFL) + (v23 >> 3) * 0x9FB67L - v22 * 0xF39ADL + v21 * 0x215D1L - v20 * 0xA6F7DL + v36;
        long v38 = v25 + 0x100000L >> 21;
        long v39 = (v15 >> 3 & 0x1FFFFFL) + (v23 >> 3) * 0x215D1L - v22 * 0xA6F7DL + v38;
        long v40 = v24 + 0x100000L >> 21;
        long v41 = (v17 >> 5 & 0x1FFFFFL) + v40;
        long v42 = v31 + 0x100000L >> 21;
        long v43 = v33 + 0x100000L >> 21;
        long v44 = v35 + 0x100000L >> 21;
        long v45 = v26 - (v36 << 21) + v44;
        long v46 = v37 + 0x100000L >> 21;
        long v47 = v25 - (v38 << 21) + v46;
        long v48 = v37 - (v46 << 21);
        long v49 = v39 + 0x100000L >> 21;
        long v50 = v24 - (v40 << 21) + v49;
        long v51 = v39 - (v49 << 21);
        long v52 = v29 - (v34 << 21) + v43 - v41 * 0xA6F7DL;
        long v53 = v28 - (v32 << 21) + v42 - v41 * 0xF39ADL + v50 * 0x215D1L - v51 * 0xA6F7DL;
        long v54 = v27 - (v30 << 21) + v41 * 470296L + v50 * 0x9FB67L - v51 * 0xF39ADL + v47 * 0x215D1L - v48 * 0xA6F7DL;
        long v55 = (v & 0x1FFFFFL) + v45 * 0xA2C13L;
        long v56 = (v2 >> 2 & 0x1FFFFFL) + v47 * 0xA2C13L + v48 * 470296L + v45 * 0x9FB67L;
        long v57 = (v4 >> 4 & 0x1FFFFFL) + v50 * 0xA2C13L + v51 * 470296L + v47 * 0x9FB67L - v48 * 0xF39ADL + v45 * 0x215D1L;
        long v58 = v55 + 0x100000L >> 21;
        long v59 = (v1 >> 5 & 0x1FFFFFL) + v48 * 0xA2C13L + v45 * 470296L + v58;
        long v60 = v56 + 0x100000L >> 21;
        long v61 = (v3 >> 7 & 0x1FFFFFL) + v51 * 0xA2C13L + v47 * 470296L + v48 * 0x9FB67L - v45 * 0xF39ADL + v60;
        long v62 = v57 + 0x100000L >> 21;
        long v63 = (v5 >> 1 & 0x1FFFFFL) + v41 * 0xA2C13L + v50 * 470296L + v51 * 0x9FB67L - v47 * 0xF39ADL + v48 * 0x215D1L - v45 * 0xA6F7DL + v62;
        long v64 = v54 + 0x100000L >> 21;
        long v65 = v31 - (v42 << 21) + v41 * 0x9FB67L - v50 * 0xF39ADL + v51 * 0x215D1L - v47 * 0xA6F7DL + v64;
        long v66 = v53 + 0x100000L >> 21;
        long v67 = v33 - (v43 << 21) + v41 * 0x215D1L - v50 * 0xA6F7DL + v66;
        long v68 = v52 + 0x100000L >> 21;
        long v69 = v35 - (v44 << 21) + v68;
        long v70 = v59 + 0x100000L >> 21;
        long v71 = v61 + 0x100000L >> 21;
        long v72 = v63 + 0x100000L >> 21;
        long v73 = v65 + 0x100000L >> 21;
        long v74 = v67 + 0x100000L >> 21;
        long v75 = v69 + 0x100000L >> 21;
        long v76 = v55 - (v58 << 21) + v75 * 0xA2C13L;
        long v77 = v59 - (v70 << 21) + v75 * 470296L + (v76 >> 21);
        long v78 = v56 - (v60 << 21) + v70 + v75 * 0x9FB67L + (v77 >> 21);
        long v79 = v61 - (v71 << 21) - v75 * 0xF39ADL + (v78 >> 21);
        long v80 = v57 - (v62 << 21) + v71 + v75 * 0x215D1L + (v79 >> 21);
        long v81 = v63 - (v72 << 21) - v75 * 0xA6F7DL + (v80 >> 21);
        long v82 = v54 - (v64 << 21) + v72 + (v81 >> 21);
        long v83 = v65 - (v73 << 21) + (v82 >> 21);
        long v84 = v53 - (v66 << 21) + v73 + (v83 >> 21);
        long v85 = v67 - (v74 << 21) + (v84 >> 21);
        long v86 = v52 - (v68 << 21) + v74 + (v85 >> 21);
        long v87 = v69 - (v75 << 21) + (v86 >> 21);
        long v88 = v76 - (v76 >> 21 << 21) + 0xA2C13L * (v87 >> 21);
        long v89 = v77 - (v77 >> 21 << 21) + 470296L * (v87 >> 21) + (v88 >> 21);
        long v90 = v88 - (v88 >> 21 << 21);
        long v91 = v78 - (v78 >> 21 << 21) + 0x9FB67L * (v87 >> 21) + (v89 >> 21);
        long v92 = v89 - (v89 >> 21 << 21);
        long v93 = v79 - (v79 >> 21 << 21) - 0xF39ADL * (v87 >> 21) + (v91 >> 21);
        long v94 = v80 - (v80 >> 21 << 21) + 0x215D1L * (v87 >> 21) + (v93 >> 21);
        long v95 = v93 - (v93 >> 21 << 21);
        long v96 = v81 - (v81 >> 21 << 21) - (v87 >> 21) * 0xA6F7DL + (v94 >> 21);
        long v97 = v94 - (v94 >> 21 << 21);
        long v98 = v82 - (v82 >> 21 << 21) + (v96 >> 21);
        long v99 = v96 - (v96 >> 21 << 21);
        long v100 = v83 - (v83 >> 21 << 21) + (v98 >> 21);
        long v101 = v98 - (v98 >> 21 << 21);
        long v102 = v84 - (v84 >> 21 << 21) + (v100 >> 21);
        long v103 = v100 - (v100 >> 21 << 21);
        long v104 = v85 - (v85 >> 21 << 21) + (v102 >> 21);
        long v105 = v102 - (v102 >> 21 << 21);
        long v106 = v86 - (v86 >> 21 << 21) + (v104 >> 21);
        long v107 = v104 - (v104 >> 21 << 21);
        long v108 = v87 - (v87 >> 21 << 21) + (v106 >> 21);
        long v109 = v106 - (v106 >> 21 << 21);
        arr_b[0] = (byte)(((int)v90));
        long v110 = v91 - (v91 >> 21 << 21);
        arr_b[1] = (byte)(((int)(v90 >> 8)));
        arr_b[2] = (byte)(((int)(v90 >> 16 | v92 << 5)));
        arr_b[3] = (byte)(((int)(v92 >> 3)));
        arr_b[4] = (byte)(((int)(v92 >> 11)));
        arr_b[5] = (byte)(((int)(v92 >> 19 | v110 << 2)));
        arr_b[6] = (byte)(((int)(v110 >> 6)));
        arr_b[7] = (byte)(((int)(v110 >> 14 | v95 << 7)));
        arr_b[8] = (byte)(((int)(v95 >> 1)));
        arr_b[9] = (byte)(((int)(v95 >> 9)));
        arr_b[10] = (byte)(((int)(v95 >> 17 | v97 << 4)));
        arr_b[11] = (byte)(((int)(v97 >> 4)));
        arr_b[12] = (byte)(((int)(v97 >> 12)));
        arr_b[13] = (byte)(((int)(v97 >> 20 | v99 << 1)));
        arr_b[14] = (byte)(((int)(v99 >> 7)));
        arr_b[15] = (byte)(((int)(v99 >> 15 | v101 << 6)));
        arr_b[16] = (byte)(((int)(v101 >> 2)));
        arr_b[17] = (byte)(((int)(v101 >> 10)));
        arr_b[18] = (byte)(((int)(v101 >> 18 | v103 << 3)));
        arr_b[19] = (byte)(((int)(v103 >> 5)));
        arr_b[20] = (byte)(((int)(v103 >> 13)));
        arr_b[21] = (byte)(((int)v105));
        arr_b[22] = (byte)(((int)(v105 >> 8)));
        arr_b[23] = (byte)(((int)(v105 >> 16 | v107 << 5)));
        arr_b[24] = (byte)(((int)(v107 >> 3)));
        arr_b[25] = (byte)(((int)(v107 >> 11)));
        arr_b[26] = (byte)(((int)(v107 >> 19 | v109 << 2)));
        arr_b[27] = (byte)(((int)(v109 >> 6)));
        arr_b[28] = (byte)(((int)(v109 >> 14 | v108 << 7)));
        arr_b[29] = (byte)(((int)(v108 >> 1)));
        arr_b[30] = (byte)(((int)(v108 >> 9)));
        arr_b[0x1F] = (byte)(((int)(v108 >> 17)));
    }

    private static XYZ scalarMultWithBase(byte[] arr_b) {
        byte[] arr_b1 = new byte[0x40];
        for(int v1 = 0; v1 < 0x20; ++v1) {
            arr_b1[v1 * 2] = (byte)(arr_b[v1] & 15);
            arr_b1[v1 * 2 + 1] = (byte)((arr_b[v1] & 0xFF) >> 4 & 15);
        }
        int v3 = 0;
        int v4 = 0;
        while(v4 < 0x3F) {
            byte b = (byte)(arr_b1[v4] + v3);
            arr_b1[v4] = b;
            int v5 = b + 8 >> 4;
            arr_b1[v4] = (byte)(b - (v5 << 4));
            ++v4;
            v3 = v5;
        }
        arr_b1[0x3F] = (byte)(arr_b1[0x3F] + v3);
        PartialXYZT ed25519$PartialXYZT0 = new PartialXYZT(Ed25519.NEUTRAL);
        XYZT ed25519$XYZT0 = new XYZT();
        for(int v2 = 1; v2 < 0x40; v2 += 2) {
            CachedXYT ed25519$CachedXYT0 = new CachedXYT(Ed25519.CACHED_NEUTRAL);
            Ed25519.select(ed25519$CachedXYT0, v2 / 2, arr_b1[v2]);
            Ed25519.add(ed25519$PartialXYZT0, XYZT.access$400(ed25519$XYZT0, ed25519$PartialXYZT0), ed25519$CachedXYT0);
        }
        XYZ ed25519$XYZ0 = new XYZ();
        Ed25519.doubleXYZ(ed25519$PartialXYZT0, XYZ.fromPartialXYZT(ed25519$XYZ0, ed25519$PartialXYZT0));
        Ed25519.doubleXYZ(ed25519$PartialXYZT0, XYZ.fromPartialXYZT(ed25519$XYZ0, ed25519$PartialXYZT0));
        Ed25519.doubleXYZ(ed25519$PartialXYZT0, XYZ.fromPartialXYZT(ed25519$XYZ0, ed25519$PartialXYZT0));
        Ed25519.doubleXYZ(ed25519$PartialXYZT0, XYZ.fromPartialXYZT(ed25519$XYZ0, ed25519$PartialXYZT0));
        for(int v = 0; v < 0x40; v += 2) {
            CachedXYT ed25519$CachedXYT1 = new CachedXYT(Ed25519.CACHED_NEUTRAL);
            Ed25519.select(ed25519$CachedXYT1, v / 2, arr_b1[v]);
            Ed25519.add(ed25519$PartialXYZT0, XYZT.access$400(ed25519$XYZT0, ed25519$PartialXYZT0), ed25519$CachedXYT1);
        }
        XYZ ed25519$XYZ1 = new XYZ(ed25519$PartialXYZT0);
        if(!ed25519$XYZ1.isOnCurve()) {
            throw new IllegalStateException("arithmetic error in scalar multiplication");
        }
        return ed25519$XYZ1;
    }

    // 去混淆评级： 低(20)
    static byte[] scalarMultWithBaseToBytes(byte[] arr_b) {
        return new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    private static void select(CachedXYT ed25519$CachedXYT0, int v, byte b) {
        ed25519$CachedXYT0.copyConditional(Ed25519Constants.B_TABLE[v][0], 0);
        ed25519$CachedXYT0.copyConditional(Ed25519Constants.B_TABLE[v][1], 0);
        ed25519$CachedXYT0.copyConditional(Ed25519Constants.B_TABLE[v][2], 0);
        ed25519$CachedXYT0.copyConditional(Ed25519Constants.B_TABLE[v][3], 0);
        ed25519$CachedXYT0.copyConditional(Ed25519Constants.B_TABLE[v][4], 0);
        ed25519$CachedXYT0.copyConditional(Ed25519Constants.B_TABLE[v][5], 0);
        ed25519$CachedXYT0.copyConditional(Ed25519Constants.B_TABLE[v][6], 0);
        ed25519$CachedXYT0.copyConditional(Ed25519Constants.B_TABLE[v][7], 0);
        long[] arr_v = Arrays.copyOf(ed25519$CachedXYT0.yMinusX, 10);
        long[] arr_v1 = Arrays.copyOf(ed25519$CachedXYT0.yPlusX, 10);
        long[] arr_v2 = Arrays.copyOf(ed25519$CachedXYT0.t2d, 10);
        Ed25519.neg(arr_v2, arr_v2);
        ed25519$CachedXYT0.copyConditional(new CachedXYT(arr_v, arr_v1, arr_v2), (b & 0xFF) >> 7);
    }

    static byte[] sign(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        byte[] arr_b3 = Arrays.copyOfRange(arr_b, 0, arr_b.length);
        MessageDigest messageDigest0 = (MessageDigest)EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        messageDigest0.update(arr_b2, 0x20, 0x20);
        messageDigest0.update(arr_b3);
        byte[] arr_b4 = messageDigest0.digest();
        Ed25519.reduce(arr_b4);
        byte[] arr_b5 = Arrays.copyOfRange(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0, 0x20);
        messageDigest0.reset();
        messageDigest0.update(arr_b5);
        messageDigest0.update(arr_b1);
        messageDigest0.update(arr_b3);
        byte[] arr_b6 = messageDigest0.digest();
        Ed25519.reduce(arr_b6);
        byte[] arr_b7 = new byte[0x20];
        Ed25519.mulAdd(arr_b7, arr_b6, arr_b2, arr_b4);
        return Bytes.concat(new byte[][]{arr_b5, arr_b7});
    }

    private static byte[] slide(byte[] arr_b) {
        byte[] arr_b1 = new byte[0x100];
        for(int v = 0; v < 0x100; ++v) {
            arr_b1[v] = (byte)(1 & (arr_b[v >> 3] & 0xFF) >> (v & 7));
        }
        for(int v1 = 0; v1 < 0x100; ++v1) {
            if(arr_b1[v1] != 0) {
                for(int v2 = 1; v2 <= 6; ++v2) {
                    int v3 = v1 + v2;
                    if(v3 >= 0x100) {
                        break;
                    }
                    int v4 = arr_b1[v3];
                    if(v4 != 0) {
                        int v5 = arr_b1[v1];
                        if((v4 << v2) + v5 <= 15) {
                            arr_b1[v1] = (byte)(v5 + (v4 << v2));
                            arr_b1[v3] = 0;
                        }
                        else if(v5 - (v4 << v2) >= -15) {
                            arr_b1[v1] = (byte)(v5 - (v4 << v2));
                            while(v3 < 0x100) {
                                if(arr_b1[v3] == 0) {
                                    arr_b1[v3] = 1;
                                    break;
                                }
                                arr_b1[v3] = 0;
                                ++v3;
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
            }
        }
        return arr_b1;
    }

    private static void sub(PartialXYZT ed25519$PartialXYZT0, XYZT ed25519$XYZT0, CachedXYT ed25519$CachedXYT0) {
        long[] arr_v = new long[10];
        Field25519.sum(ed25519$PartialXYZT0.xyz.x, ed25519$XYZT0.xyz.y, ed25519$XYZT0.xyz.x);
        Field25519.sub(ed25519$PartialXYZT0.xyz.y, ed25519$XYZT0.xyz.y, ed25519$XYZT0.xyz.x);
        Field25519.mult(ed25519$PartialXYZT0.xyz.y, ed25519$PartialXYZT0.xyz.y, ed25519$CachedXYT0.yPlusX);
        Field25519.mult(ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.xyz.x, ed25519$CachedXYT0.yMinusX);
        Field25519.mult(ed25519$PartialXYZT0.t, ed25519$XYZT0.t, ed25519$CachedXYT0.t2d);
        ed25519$CachedXYT0.multByZ(ed25519$PartialXYZT0.xyz.x, ed25519$XYZT0.xyz.z);
        Field25519.sum(arr_v, ed25519$PartialXYZT0.xyz.x, ed25519$PartialXYZT0.xyz.x);
        Field25519.sub(ed25519$PartialXYZT0.xyz.x, ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.xyz.y);
        Field25519.sum(ed25519$PartialXYZT0.xyz.y, ed25519$PartialXYZT0.xyz.z, ed25519$PartialXYZT0.xyz.y);
        Field25519.sub(ed25519$PartialXYZT0.xyz.z, arr_v, ed25519$PartialXYZT0.t);
        Field25519.sum(ed25519$PartialXYZT0.t, arr_v, ed25519$PartialXYZT0.t);
    }

    static boolean verify(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        if(arr_b1.length != 0x40) {
            return false;
        }
        if(!Ed25519.isSmallerThanGroupOrder(Arrays.copyOfRange(arr_b1, 0x20, 0x40))) {
            return false;
        }
        MessageDigest messageDigest0 = (MessageDigest)EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        messageDigest0.update(arr_b1, 0, 0x20);
        messageDigest0.update(arr_b2);
        messageDigest0.update(arr_b);
        Ed25519.reduce(messageDigest0.digest());
        for(int v = 0; v < 0x20; ++v) {
            if(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}[v] != arr_b1[v]) {
                return false;
            }
        }
        return true;
    }
}

