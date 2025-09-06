package com.google.crypto.tink.subtle;

import java.math.BigInteger;

final class Ed25519Constants {
    static class Point {
        private BigInteger x;
        private BigInteger y;

        private Point() {
        }

        Point(com.google.crypto.tink.subtle.Ed25519Constants.1 ed25519Constants$10) {
        }

        static BigInteger access$100(Point ed25519Constants$Point0) {
            return ed25519Constants$Point0.y;
        }

        static BigInteger access$102(Point ed25519Constants$Point0, BigInteger bigInteger0) {
            ed25519Constants$Point0.y = bigInteger0;
            return bigInteger0;
        }

        static BigInteger access$200(Point ed25519Constants$Point0) {
            return ed25519Constants$Point0.x;
        }

        static BigInteger access$202(Point ed25519Constants$Point0, BigInteger bigInteger0) {
            ed25519Constants$Point0.x = bigInteger0;
            return bigInteger0;
        }
    }

    static final CachedXYT[] B2;
    static final CachedXYT[][] B_TABLE;
    static final long[] D;
    static final long[] D2;
    private static final BigInteger D2_BI;
    private static final BigInteger D_BI;
    private static final BigInteger P_BI;
    static final long[] SQRTM1;
    private static final BigInteger SQRTM1_BI;

    static {
        BigInteger bigInteger0 = BigInteger.valueOf(2L).pow(0xFF).subtract(BigInteger.valueOf(19L));
        Ed25519Constants.P_BI = bigInteger0;
        BigInteger bigInteger1 = BigInteger.valueOf(0xFFFFFFFFFFFE24BFL).multiply(BigInteger.valueOf(0x1DB42L).modInverse(bigInteger0)).mod(bigInteger0);
        Ed25519Constants.D_BI = bigInteger1;
        BigInteger bigInteger2 = BigInteger.valueOf(2L).multiply(bigInteger1).mod(bigInteger0);
        Ed25519Constants.D2_BI = bigInteger2;
        BigInteger bigInteger3 = BigInteger.valueOf(2L).modPow(bigInteger0.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4L)), bigInteger0);
        Ed25519Constants.SQRTM1_BI = bigInteger3;
        Point ed25519Constants$Point0 = new Point(null);
        Point.access$102(ed25519Constants$Point0, BigInteger.valueOf(4L).multiply(BigInteger.valueOf(5L).modInverse(bigInteger0)).mod(bigInteger0));
        Point.access$202(ed25519Constants$Point0, Ed25519Constants.recoverX(Point.access$100(ed25519Constants$Point0)));
        Ed25519Constants.D = Field25519.expand(Ed25519Constants.toLittleEndian(bigInteger1));
        Ed25519Constants.D2 = Field25519.expand(Ed25519Constants.toLittleEndian(bigInteger2));
        Ed25519Constants.SQRTM1 = Field25519.expand(Ed25519Constants.toLittleEndian(bigInteger3));
        Ed25519Constants.B_TABLE = new CachedXYT[0x20][8];
        Point ed25519Constants$Point1 = ed25519Constants$Point0;
        for(int v1 = 0; v1 < 0x20; ++v1) {
            Point ed25519Constants$Point2 = ed25519Constants$Point1;
            for(int v2 = 0; v2 < 8; ++v2) {
                CachedXYT[] arr_ed25519$CachedXYT = Ed25519Constants.B_TABLE[v1];
                arr_ed25519$CachedXYT[v2] = Ed25519Constants.getCachedXYT(ed25519Constants$Point2);
                ed25519Constants$Point2 = Ed25519Constants.edwards(ed25519Constants$Point2, ed25519Constants$Point1);
            }
            for(int v3 = 0; v3 < 8; ++v3) {
                ed25519Constants$Point1 = Ed25519Constants.edwards(ed25519Constants$Point1, ed25519Constants$Point1);
            }
        }
        Point ed25519Constants$Point3 = Ed25519Constants.edwards(ed25519Constants$Point0, ed25519Constants$Point0);
        Ed25519Constants.B2 = new CachedXYT[8];
        for(int v = 0; v < 8; ++v) {
            Ed25519Constants.B2[v] = Ed25519Constants.getCachedXYT(ed25519Constants$Point0);
            ed25519Constants$Point0 = Ed25519Constants.edwards(ed25519Constants$Point0, ed25519Constants$Point3);
        }
    }

    private static Point edwards(Point ed25519Constants$Point0, Point ed25519Constants$Point1) {
        Point ed25519Constants$Point2 = new Point(null);
        BigInteger bigInteger0 = Point.access$200(ed25519Constants$Point0).multiply(Point.access$200(ed25519Constants$Point1)).multiply(Point.access$100(ed25519Constants$Point0)).multiply(Point.access$100(ed25519Constants$Point1));
        BigInteger bigInteger1 = Ed25519Constants.D_BI.multiply(bigInteger0).mod(Ed25519Constants.P_BI);
        Point.access$202(ed25519Constants$Point2, Point.access$200(ed25519Constants$Point0).multiply(Point.access$100(ed25519Constants$Point1)).add(Point.access$200(ed25519Constants$Point1).multiply(Point.access$100(ed25519Constants$Point0))).multiply(BigInteger.ONE.add(bigInteger1).modInverse(Ed25519Constants.P_BI)).mod(Ed25519Constants.P_BI));
        Point.access$102(ed25519Constants$Point2, Point.access$100(ed25519Constants$Point0).multiply(Point.access$100(ed25519Constants$Point1)).add(Point.access$200(ed25519Constants$Point0).multiply(Point.access$200(ed25519Constants$Point1))).multiply(BigInteger.ONE.subtract(bigInteger1).modInverse(Ed25519Constants.P_BI)).mod(Ed25519Constants.P_BI));
        return ed25519Constants$Point2;
    }

    private static CachedXYT getCachedXYT(Point ed25519Constants$Point0) {
        return new CachedXYT(Field25519.expand(Ed25519Constants.toLittleEndian(Point.access$100(ed25519Constants$Point0).add(Point.access$200(ed25519Constants$Point0)).mod(Ed25519Constants.P_BI))), Field25519.expand(Ed25519Constants.toLittleEndian(Point.access$100(ed25519Constants$Point0).subtract(Point.access$200(ed25519Constants$Point0)).mod(Ed25519Constants.P_BI))), Field25519.expand(Ed25519Constants.toLittleEndian(Ed25519Constants.D2_BI.multiply(Point.access$200(ed25519Constants$Point0)).multiply(Point.access$100(ed25519Constants$Point0)).mod(Ed25519Constants.P_BI))));
    }

    private static BigInteger recoverX(BigInteger bigInteger0) {
        BigInteger bigInteger1 = bigInteger0.pow(2).subtract(BigInteger.ONE);
        BigInteger bigInteger2 = bigInteger0.pow(2);
        BigInteger bigInteger3 = Ed25519Constants.D_BI.multiply(bigInteger2).add(BigInteger.ONE);
        BigInteger bigInteger4 = Ed25519Constants.P_BI;
        BigInteger bigInteger5 = bigInteger1.multiply(bigInteger3.modInverse(bigInteger4));
        BigInteger bigInteger6 = bigInteger5.modPow(bigInteger4.add(BigInteger.valueOf(3L)).divide(BigInteger.valueOf(8L)), bigInteger4);
        if(!bigInteger6.pow(2).subtract(bigInteger5).mod(bigInteger4).equals(BigInteger.ZERO)) {
            bigInteger6 = bigInteger6.multiply(Ed25519Constants.SQRTM1_BI).mod(bigInteger4);
        }
        return bigInteger6.testBit(0) ? bigInteger4.subtract(bigInteger6) : bigInteger6;
    }

    private static byte[] toLittleEndian(BigInteger bigInteger0) {
        byte[] arr_b = new byte[0x20];
        byte[] arr_b1 = bigInteger0.toByteArray();
        System.arraycopy(arr_b1, 0, arr_b, 0x20 - arr_b1.length, arr_b1.length);
        for(int v = 0; v < 16; ++v) {
            byte b = arr_b[v];
            arr_b[v] = arr_b[0x1F - v];
            arr_b[0x1F - v] = b;
        }
        return arr_b;
    }

    class com.google.crypto.tink.subtle.Ed25519Constants.1 {
    }

}

