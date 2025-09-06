package com.google.crypto.tink.subtle;

import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.EllipticCurvesUtil;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.KeyAgreement;

public final class EllipticCurves {
    public static enum CurveType {
        NIST_P256,
        NIST_P384,
        NIST_P521;

    }

    public static enum EcdsaEncoding {
        IEEE_P1363,
        DER;

    }

    public static enum PointFormatType {
        UNCOMPRESSED,
        COMPRESSED,
        DO_NOT_USE_CRUNCHY_UNCOMPRESSED;

    }

    static void checkPublicKey(ECPublicKey eCPublicKey0) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(eCPublicKey0.getW(), eCPublicKey0.getParams().getCurve());
    }

    public static byte[] computeSharedSecret(ECPrivateKey eCPrivateKey0, ECPublicKey eCPublicKey0) throws GeneralSecurityException {
        EllipticCurves.validatePublicKeySpec(eCPublicKey0, eCPrivateKey0);
        return EllipticCurves.computeSharedSecret(eCPrivateKey0, eCPublicKey0.getW());
    }

    public static byte[] computeSharedSecret(ECPrivateKey eCPrivateKey0, ECPoint eCPoint0) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(eCPoint0, eCPrivateKey0.getParams().getCurve());
        ECPublicKeySpec eCPublicKeySpec0 = new ECPublicKeySpec(eCPoint0, eCPrivateKey0.getParams());
        PublicKey publicKey0 = ((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("EC")).generatePublic(eCPublicKeySpec0);
        KeyAgreement keyAgreement0 = (KeyAgreement)EngineFactory.KEY_AGREEMENT.getInstance("ECDH");
        keyAgreement0.init(eCPrivateKey0);
        try {
            keyAgreement0.doPhase(publicKey0, true);
            byte[] arr_b = keyAgreement0.generateSecret();
            EllipticCurves.validateSharedSecret(arr_b, eCPrivateKey0);
            return arr_b;
        }
        catch(IllegalStateException illegalStateException0) {
            throw new GeneralSecurityException(illegalStateException0);
        }
    }

    public static ECPoint ecPointDecode(EllipticCurve ellipticCurve0, PointFormatType ellipticCurves$PointFormatType0, byte[] arr_b) throws GeneralSecurityException {
        return EllipticCurves.pointDecode(ellipticCurve0, ellipticCurves$PointFormatType0, arr_b);
    }

    public static byte[] ecdsaDer2Ieee(byte[] arr_b, int v) throws GeneralSecurityException {
        if(!EllipticCurves.isValidDerEncoding(arr_b)) {
            throw new GeneralSecurityException("Invalid DER encoding");
        }
        byte[] arr_b1 = new byte[v];
        int v1 = 1;
        int v2 = (arr_b[1] & 0xFF) < 0x80 ? 2 : 3;
        int v3 = arr_b[v2 + 1];
        int v4 = arr_b[v2 + 2] == 0 ? 1 : 0;
        System.arraycopy(arr_b, v2 + 2 + v4, arr_b1, v / 2 - v3 + v4, v3 - v4);
        int v5 = v2 + v3 + 3;
        int v6 = arr_b[v5];
        if(arr_b[v5 + 1] != 0) {
            v1 = 0;
        }
        System.arraycopy(arr_b, v5 + 1 + v1, arr_b1, v - v6 + v1, v6 - v1);
        return arr_b1;
    }

    public static byte[] ecdsaIeee2Der(byte[] arr_b) throws GeneralSecurityException {
        int v1;
        byte[] arr_b3;
        if(arr_b.length % 2 != 0 || arr_b.length == 0 || arr_b.length > 0x84) {
            throw new GeneralSecurityException("Invalid IEEE_P1363 encoding");
        }
        byte[] arr_b1 = EllipticCurves.toMinimalSignedNumber(Arrays.copyOf(arr_b, arr_b.length / 2));
        byte[] arr_b2 = EllipticCurves.toMinimalSignedNumber(Arrays.copyOfRange(arr_b, arr_b.length / 2, arr_b.length));
        int v = arr_b1.length + 4 + arr_b2.length;
        if(v >= 0x80) {
            arr_b3 = new byte[v + 3];
            arr_b3[0] = 0x30;
            arr_b3[1] = (byte)0x81;
            arr_b3[2] = (byte)v;
            v1 = 3;
        }
        else {
            arr_b3 = new byte[v + 2];
            arr_b3[0] = 0x30;
            arr_b3[1] = (byte)v;
            v1 = 2;
        }
        arr_b3[v1] = 2;
        arr_b3[v1 + 1] = (byte)arr_b1.length;
        System.arraycopy(arr_b1, 0, arr_b3, v1 + 2, arr_b1.length);
        int v2 = v1 + 2 + arr_b1.length;
        arr_b3[v2] = 2;
        arr_b3[v2 + 1] = (byte)arr_b2.length;
        System.arraycopy(arr_b2, 0, arr_b3, v2 + 2, arr_b2.length);
        return arr_b3;
    }

    public static int encodingSizeInBytes(EllipticCurve ellipticCurve0, PointFormatType ellipticCurves$PointFormatType0) throws GeneralSecurityException {
        int v = EllipticCurves.fieldSizeInBytes(ellipticCurve0);
        int v1 = com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[ellipticCurves$PointFormatType0.ordinal()];
        switch(v1) {
            case 1: {
                return v * 2 + 1;
            }
            case 2: {
                return v * 2;
            }
            default: {
                if(v1 != 3) {
                    throw new GeneralSecurityException("unknown EC point format");
                }
                return v + 1;
            }
        }
    }

    public static int fieldSizeInBits(EllipticCurve ellipticCurve0) throws GeneralSecurityException {
        return EllipticCurves.getModulus(ellipticCurve0).subtract(BigInteger.ONE).bitLength();
    }

    public static int fieldSizeInBytes(EllipticCurve ellipticCurve0) throws GeneralSecurityException {
        return (EllipticCurves.fieldSizeInBits(ellipticCurve0) + 7) / 8;
    }

    public static KeyPair generateKeyPair(CurveType ellipticCurves$CurveType0) throws GeneralSecurityException {
        return EllipticCurves.generateKeyPair(EllipticCurves.getCurveSpec(ellipticCurves$CurveType0));
    }

    public static KeyPair generateKeyPair(ECParameterSpec eCParameterSpec0) throws GeneralSecurityException {
        KeyPairGenerator keyPairGenerator0 = (KeyPairGenerator)EngineFactory.KEY_PAIR_GENERATOR.getInstance("EC");
        keyPairGenerator0.initialize(eCParameterSpec0);
        return keyPairGenerator0.generateKeyPair();
    }

    public static ECParameterSpec getCurveSpec(CurveType ellipticCurves$CurveType0) throws NoSuchAlgorithmException {
        switch(com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[ellipticCurves$CurveType0.ordinal()]) {
            case 1: {
                return EllipticCurves.getNistP256Params();
            }
            case 2: {
                return EllipticCurves.getNistP384Params();
            }
            case 3: {
                return EllipticCurves.getNistP521Params();
            }
            default: {
                throw new NoSuchAlgorithmException("curve not implemented:" + ellipticCurves$CurveType0);
            }
        }
    }

    public static ECPrivateKey getEcPrivateKey(CurveType ellipticCurves$CurveType0, byte[] arr_b) throws GeneralSecurityException {
        ECParameterSpec eCParameterSpec0 = EllipticCurves.getCurveSpec(ellipticCurves$CurveType0);
        ECPrivateKeySpec eCPrivateKeySpec0 = new ECPrivateKeySpec(BigIntegerEncoding.fromUnsignedBigEndianBytes(arr_b), eCParameterSpec0);
        return (ECPrivateKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("EC")).generatePrivate(eCPrivateKeySpec0);
    }

    public static ECPrivateKey getEcPrivateKey(byte[] arr_b) throws GeneralSecurityException {
        return (ECPrivateKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("EC")).generatePrivate(new PKCS8EncodedKeySpec(arr_b));
    }

    public static ECPublicKey getEcPublicKey(CurveType ellipticCurves$CurveType0, PointFormatType ellipticCurves$PointFormatType0, byte[] arr_b) throws GeneralSecurityException {
        return EllipticCurves.getEcPublicKey(EllipticCurves.getCurveSpec(ellipticCurves$CurveType0), ellipticCurves$PointFormatType0, arr_b);
    }

    public static ECPublicKey getEcPublicKey(CurveType ellipticCurves$CurveType0, byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        ECParameterSpec eCParameterSpec0 = EllipticCurves.getCurveSpec(ellipticCurves$CurveType0);
        ECPoint eCPoint0 = new ECPoint(new BigInteger(1, arr_b), new BigInteger(1, arr_b1));
        EllipticCurvesUtil.checkPointOnCurve(eCPoint0, eCParameterSpec0.getCurve());
        ECPublicKeySpec eCPublicKeySpec0 = new ECPublicKeySpec(eCPoint0, eCParameterSpec0);
        return (ECPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("EC")).generatePublic(eCPublicKeySpec0);
    }

    public static ECPublicKey getEcPublicKey(ECParameterSpec eCParameterSpec0, PointFormatType ellipticCurves$PointFormatType0, byte[] arr_b) throws GeneralSecurityException {
        ECPublicKeySpec eCPublicKeySpec0 = new ECPublicKeySpec(EllipticCurves.pointDecode(eCParameterSpec0.getCurve(), ellipticCurves$PointFormatType0, arr_b), eCParameterSpec0);
        return (ECPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("EC")).generatePublic(eCPublicKeySpec0);
    }

    public static ECPublicKey getEcPublicKey(byte[] arr_b) throws GeneralSecurityException {
        return (ECPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("EC")).generatePublic(new X509EncodedKeySpec(arr_b));
    }

    public static BigInteger getModulus(EllipticCurve ellipticCurve0) throws GeneralSecurityException {
        return EllipticCurvesUtil.getModulus(ellipticCurve0);
    }

    public static ECParameterSpec getNistP256Params() {
        return EllipticCurvesUtil.NIST_P256_PARAMS;
    }

    public static ECParameterSpec getNistP384Params() {
        return EllipticCurvesUtil.NIST_P384_PARAMS;
    }

    public static ECParameterSpec getNistP521Params() {
        return EllipticCurvesUtil.NIST_P521_PARAMS;
    }

    public static BigInteger getY(BigInteger bigInteger0, boolean z, EllipticCurve ellipticCurve0) throws GeneralSecurityException {
        BigInteger bigInteger1 = EllipticCurves.getModulus(ellipticCurve0);
        BigInteger bigInteger2 = ellipticCurve0.getA();
        BigInteger bigInteger3 = ellipticCurve0.getB();
        BigInteger bigInteger4 = EllipticCurves.modSqrt(bigInteger0.multiply(bigInteger0).add(bigInteger2).multiply(bigInteger0).add(bigInteger3).mod(bigInteger1), bigInteger1);
        return z == bigInteger4.testBit(0) ? bigInteger4 : bigInteger1.subtract(bigInteger4).mod(bigInteger1);
    }

    public static boolean isNistEcParameterSpec(ECParameterSpec eCParameterSpec0) {
        return EllipticCurvesUtil.isNistEcParameterSpec(eCParameterSpec0);
    }

    public static boolean isSameEcParameterSpec(ECParameterSpec eCParameterSpec0, ECParameterSpec eCParameterSpec1) {
        return EllipticCurvesUtil.isSameEcParameterSpec(eCParameterSpec0, eCParameterSpec1);
    }

    public static boolean isValidDerEncoding(byte[] arr_b) {
        int v1;
        if(arr_b.length < 8) {
            return false;
        }
        if(arr_b[0] != 0x30) {
            return false;
        }
        int v = arr_b[1] & 0xFF;
        if(v == 0x81) {
            v = arr_b[2] & 0xFF;
            if(v < 0x80) {
                return false;
            }
            v1 = 2;
        }
        else if(v != 0x80 && v <= 0x81) {
            v1 = 1;
        }
        else {
            return false;
        }
        if(v != arr_b.length - 1 - v1) {
            return false;
        }
        if(arr_b[v1 + 1] != 2) {
            return false;
        }
        int v2 = arr_b[v1 + 2] & 0xFF;
        int v3 = v1 + 3 + v2;
        if(v3 + 1 >= arr_b.length) {
            return false;
        }
        if(v2 == 0) {
            return false;
        }
        int v4 = arr_b[v1 + 3];
        if((v4 & 0xFF) >= 0x80) {
            return false;
        }
        if(v2 > 1 && v4 == 0 && (arr_b[v1 + 4] & 0xFF) < 0x80) {
            return false;
        }
        if(arr_b[v1 + 3 + v2] != 2) {
            return false;
        }
        int v5 = arr_b[v3 + 1] & 0xFF;
        if(v3 + 2 + v5 != arr_b.length) {
            return false;
        }
        if(v5 == 0) {
            return false;
        }
        int v6 = arr_b[v1 + 5 + v2];
        return (v6 & 0xFF) < 0x80 ? v5 <= 1 || v6 != 0 || (arr_b[v1 + 6 + v2] & 0xFF) >= 0x80 : false;
    }

    protected static BigInteger modSqrt(BigInteger bigInteger0, BigInteger bigInteger1) throws GeneralSecurityException {
        BigInteger bigInteger3;
        if(bigInteger1.signum() != 1) {
            throw new InvalidAlgorithmParameterException("p must be positive");
        }
        int v = 0;
        BigInteger bigInteger2 = bigInteger0.mod(bigInteger1);
        if(bigInteger2.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }
        if(bigInteger1.testBit(0) && bigInteger1.testBit(1)) {
            bigInteger3 = bigInteger2.modPow(bigInteger1.add(BigInteger.ONE).shiftRight(2), bigInteger1);
        }
        else if(!bigInteger1.testBit(0) || bigInteger1.testBit(1)) {
            bigInteger3 = null;
        }
        else {
            BigInteger bigInteger4 = BigInteger.ONE;
            BigInteger bigInteger5 = bigInteger1.subtract(BigInteger.ONE).shiftRight(1);
            while(true) {
                BigInteger bigInteger6 = bigInteger4.multiply(bigInteger4).subtract(bigInteger2).mod(bigInteger1);
                if(bigInteger6.equals(BigInteger.ZERO)) {
                    return bigInteger4;
                }
                BigInteger bigInteger7 = bigInteger6.modPow(bigInteger5, bigInteger1);
                if(bigInteger7.add(BigInteger.ONE).equals(bigInteger1)) {
                    BigInteger bigInteger8 = bigInteger1.add(BigInteger.ONE).shiftRight(1);
                    BigInteger bigInteger9 = BigInteger.ONE;
                    int v1 = bigInteger8.bitLength() - 2;
                    BigInteger bigInteger10 = bigInteger9;
                    BigInteger bigInteger11 = bigInteger4;
                    while(v1 >= 0) {
                        BigInteger bigInteger12 = bigInteger11.multiply(bigInteger10);
                        bigInteger11 = bigInteger11.multiply(bigInteger11).add(bigInteger10.multiply(bigInteger10).mod(bigInteger1).multiply(bigInteger6)).mod(bigInteger1);
                        bigInteger10 = bigInteger12.add(bigInteger12).mod(bigInteger1);
                        if(bigInteger8.testBit(v1)) {
                            BigInteger bigInteger13 = bigInteger11.multiply(bigInteger4).add(bigInteger10.multiply(bigInteger6)).mod(bigInteger1);
                            bigInteger10 = bigInteger4.multiply(bigInteger10).add(bigInteger11).mod(bigInteger1);
                            bigInteger11 = bigInteger13;
                        }
                        --v1;
                    }
                    bigInteger3 = bigInteger11;
                    goto label_40;
                }
                if(!bigInteger7.equals(BigInteger.ONE)) {
                    throw new InvalidAlgorithmParameterException("p is not prime");
                }
                bigInteger4 = bigInteger4.add(BigInteger.ONE);
                ++v;
                if(v == 0x80 && !bigInteger1.isProbablePrime(80)) {
                    break;
                }
            }
            throw new InvalidAlgorithmParameterException("p is not prime");
        }
    label_40:
        if(bigInteger3 != null && bigInteger3.multiply(bigInteger3).mod(bigInteger1).compareTo(bigInteger2) != 0) {
            throw new GeneralSecurityException("Could not find a modular square root");
        }
        return bigInteger3;
    }

    public static ECPoint pointDecode(CurveType ellipticCurves$CurveType0, PointFormatType ellipticCurves$PointFormatType0, byte[] arr_b) throws GeneralSecurityException {
        return EllipticCurves.pointDecode(EllipticCurves.getCurveSpec(ellipticCurves$CurveType0).getCurve(), ellipticCurves$PointFormatType0, arr_b);
    }

    public static ECPoint pointDecode(EllipticCurve ellipticCurve0, PointFormatType ellipticCurves$PointFormatType0, byte[] arr_b) throws GeneralSecurityException {
        int v = EllipticCurves.fieldSizeInBytes(ellipticCurve0);
        boolean z = false;
        switch(com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[ellipticCurves$PointFormatType0.ordinal()]) {
            case 1: {
                if(arr_b.length != v * 2 + 1) {
                    throw new GeneralSecurityException("invalid point size");
                }
                if(arr_b[0] != 4) {
                    throw new GeneralSecurityException("invalid point format");
                }
                ECPoint eCPoint0 = new ECPoint(new BigInteger(1, Arrays.copyOfRange(arr_b, 1, v + 1)), new BigInteger(1, Arrays.copyOfRange(arr_b, v + 1, arr_b.length)));
                EllipticCurvesUtil.checkPointOnCurve(eCPoint0, ellipticCurve0);
                return eCPoint0;
            }
            case 2: {
                if(arr_b.length != v * 2) {
                    throw new GeneralSecurityException("invalid point size");
                }
                ECPoint eCPoint1 = new ECPoint(new BigInteger(1, Arrays.copyOfRange(arr_b, 0, v)), new BigInteger(1, Arrays.copyOfRange(arr_b, v, arr_b.length)));
                EllipticCurvesUtil.checkPointOnCurve(eCPoint1, ellipticCurve0);
                return eCPoint1;
            }
            case 3: {
                BigInteger bigInteger0 = EllipticCurves.getModulus(ellipticCurve0);
                if(arr_b.length != v + 1) {
                    throw new GeneralSecurityException("compressed point has wrong length");
                }
                int v1 = arr_b[0];
                if(v1 != 2) {
                    if(v1 != 3) {
                        throw new GeneralSecurityException("invalid format");
                    }
                    z = true;
                }
                BigInteger bigInteger1 = new BigInteger(1, Arrays.copyOfRange(arr_b, 1, arr_b.length));
                if(bigInteger1.signum() == -1 || bigInteger1.compareTo(bigInteger0) >= 0) {
                    throw new GeneralSecurityException("x is out of range");
                }
                return new ECPoint(bigInteger1, EllipticCurves.getY(bigInteger1, z, ellipticCurve0));
            }
            default: {
                throw new GeneralSecurityException("invalid format:" + ellipticCurves$PointFormatType0);
            }
        }
    }

    public static byte[] pointEncode(CurveType ellipticCurves$CurveType0, PointFormatType ellipticCurves$PointFormatType0, ECPoint eCPoint0) throws GeneralSecurityException {
        return EllipticCurves.pointEncode(EllipticCurves.getCurveSpec(ellipticCurves$CurveType0).getCurve(), ellipticCurves$PointFormatType0, eCPoint0);
    }

    public static byte[] pointEncode(EllipticCurve ellipticCurve0, PointFormatType ellipticCurves$PointFormatType0, ECPoint eCPoint0) throws GeneralSecurityException {
        int v = 2;
        EllipticCurvesUtil.checkPointOnCurve(eCPoint0, ellipticCurve0);
        int v1 = EllipticCurves.fieldSizeInBytes(ellipticCurve0);
        switch(com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[ellipticCurves$PointFormatType0.ordinal()]) {
            case 1: {
                int v2 = v1 * 2 + 1;
                byte[] arr_b = new byte[v2];
                byte[] arr_b1 = BigIntegerEncoding.toBigEndianBytes(eCPoint0.getAffineX());
                byte[] arr_b2 = BigIntegerEncoding.toBigEndianBytes(eCPoint0.getAffineY());
                System.arraycopy(arr_b2, 0, arr_b, v2 - arr_b2.length, arr_b2.length);
                System.arraycopy(arr_b1, 0, arr_b, v1 + 1 - arr_b1.length, arr_b1.length);
                arr_b[0] = 4;
                return arr_b;
            }
            case 2: {
                byte[] arr_b3 = new byte[v1 * 2];
                byte[] arr_b4 = BigIntegerEncoding.toBigEndianBytes(eCPoint0.getAffineX());
                if(arr_b4.length > v1) {
                    arr_b4 = Arrays.copyOfRange(arr_b4, arr_b4.length - v1, arr_b4.length);
                }
                byte[] arr_b5 = BigIntegerEncoding.toBigEndianBytes(eCPoint0.getAffineY());
                if(arr_b5.length > v1) {
                    arr_b5 = Arrays.copyOfRange(arr_b5, arr_b5.length - v1, arr_b5.length);
                }
                System.arraycopy(arr_b5, 0, arr_b3, v1 * 2 - arr_b5.length, arr_b5.length);
                System.arraycopy(arr_b4, 0, arr_b3, v1 - arr_b4.length, arr_b4.length);
                return arr_b3;
            }
            case 3: {
                byte[] arr_b6 = new byte[v1 + 1];
                byte[] arr_b7 = BigIntegerEncoding.toBigEndianBytes(eCPoint0.getAffineX());
                System.arraycopy(arr_b7, 0, arr_b6, v1 + 1 - arr_b7.length, arr_b7.length);
                if(eCPoint0.getAffineY().testBit(0)) {
                    v = 3;
                }
                arr_b6[0] = (byte)v;
                return arr_b6;
            }
            default: {
                throw new GeneralSecurityException("invalid format:" + ellipticCurves$PointFormatType0);
            }
        }
    }

    private static byte[] toMinimalSignedNumber(byte[] arr_b) {
        int v = 0;
        int v1;
        for(v1 = 0; v1 < arr_b.length && arr_b[v1] == 0; ++v1) {
        }
        if(v1 == arr_b.length) {
            v1 = arr_b.length - 1;
        }
        if((arr_b[v1] & 0x80) == 0x80) {
            v = 1;
        }
        byte[] arr_b1 = new byte[arr_b.length - v1 + v];
        System.arraycopy(arr_b, v1, arr_b1, v, arr_b.length - v1);
        return arr_b1;
    }

    public static void validatePublicKey(ECPublicKey eCPublicKey0, ECPrivateKey eCPrivateKey0) throws GeneralSecurityException {
        EllipticCurves.validatePublicKeySpec(eCPublicKey0, eCPrivateKey0);
        EllipticCurvesUtil.checkPointOnCurve(eCPublicKey0.getW(), eCPrivateKey0.getParams().getCurve());
    }

    static void validatePublicKeySpec(ECPublicKey eCPublicKey0, ECPrivateKey eCPrivateKey0) throws GeneralSecurityException {
        try {
            if(!EllipticCurves.isSameEcParameterSpec(eCPublicKey0.getParams(), eCPrivateKey0.getParams())) {
                throw new GeneralSecurityException("invalid public key spec");
            }
        }
        catch(IllegalArgumentException | NullPointerException exception0) {
            throw new GeneralSecurityException(exception0);
        }
    }

    private static void validateSharedSecret(byte[] arr_b, ECPrivateKey eCPrivateKey0) throws GeneralSecurityException {
        EllipticCurve ellipticCurve0 = eCPrivateKey0.getParams().getCurve();
        BigInteger bigInteger0 = new BigInteger(1, arr_b);
        if(bigInteger0.signum() == -1 || bigInteger0.compareTo(EllipticCurves.getModulus(ellipticCurve0)) >= 0) {
            throw new GeneralSecurityException("shared secret is out of range");
        }
        EllipticCurves.getY(bigInteger0, true, ellipticCurve0);
    }

    class com.google.crypto.tink.subtle.EllipticCurves.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType;
        static final int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType;

        static {
            int[] arr_v = new int[CurveType.values().length];
            com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType = arr_v;
            try {
                arr_v[CurveType.NIST_P256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[CurveType.NIST_P384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[CurveType.NIST_P521.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[PointFormatType.values().length];
            com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType = arr_v1;
            try {
                arr_v1[PointFormatType.UNCOMPRESSED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[PointFormatType.DO_NOT_USE_CRUNCHY_UNCOMPRESSED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.subtle.EllipticCurves.1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[PointFormatType.COMPRESSED.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

