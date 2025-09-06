package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Locale;
import java.util.regex.Pattern;

public final class Validators {
    private static final Pattern GCP_KMS_CRYPTO_KEY_PATTERN = null;
    private static final Pattern GCP_KMS_CRYPTO_KEY_VERSION_PATTERN = null;
    private static final int MIN_RSA_MODULUS_SIZE = 0x800;
    private static final String TYPE_URL_PREFIX = "type.googleapis.com/";
    private static final String URI_UNRESERVED_CHARS = "([0-9a-zA-Z\\-\\.\\_~])+";

    static {
        Validators.GCP_KMS_CRYPTO_KEY_PATTERN = Pattern.compile("^projects/([0-9a-zA-Z\\-\\.\\_~])+/locations/([0-9a-zA-Z\\-\\.\\_~])+/keyRings/([0-9a-zA-Z\\-\\.\\_~])+/cryptoKeys/([0-9a-zA-Z\\-\\.\\_~])+$", 2);
        Validators.GCP_KMS_CRYPTO_KEY_VERSION_PATTERN = Pattern.compile("^projects/([0-9a-zA-Z\\-\\.\\_~])+/locations/([0-9a-zA-Z\\-\\.\\_~])+/keyRings/([0-9a-zA-Z\\-\\.\\_~])+/cryptoKeys/([0-9a-zA-Z\\-\\.\\_~])+/cryptoKeyVersions/([0-9a-zA-Z\\-\\.\\_~])+$", 2);
    }

    public static void validateAesKeySize(int v) throws InvalidAlgorithmParameterException {
        if(v != 16 && v != 0x20) {
            throw new InvalidAlgorithmParameterException(String.format("invalid key size %d; only 128-bit and 256-bit AES keys are supported", ((int)(v * 8))));
        }
    }

    public static void validateCryptoKeyUri(String s) throws GeneralSecurityException {
        if(!Validators.GCP_KMS_CRYPTO_KEY_PATTERN.matcher(s).matches()) {
            throw Validators.GCP_KMS_CRYPTO_KEY_VERSION_PATTERN.matcher(s).matches() ? new GeneralSecurityException("Invalid Google Cloud KMS Key URI. The URI must point to a CryptoKey, not a CryptoKeyVersion") : new GeneralSecurityException("Invalid Google Cloud KMS Key URI. The URI must point to a CryptoKey in the format projects/*/locations/*/keyRings/*/cryptoKeys/*. See https://cloud.google.com/kms/docs/reference/rest/v1/projects.locations.keyRings.cryptoKeys#CryptoKey");
        }
    }

    public static void validateExists(File file0) throws IOException {
        if(!file0.exists()) {
            throw new IOException(String.format("Error: %s doesn\'t exist, please choose another file\n", file0));
        }
    }

    public static String validateKmsKeyUriAndRemovePrefix(String s, String s1) {
        if(!s1.toLowerCase(Locale.US).startsWith(s)) {
            throw new IllegalArgumentException(String.format("key URI must start with %s", s));
        }
        return s1.substring(s.length());
    }

    public static void validateNotExists(File file0) throws IOException {
        if(file0.exists()) {
            throw new IOException(String.format("%s exists, please choose another file\n", file0));
        }
    }

    public static void validateRsaModulusSize(int v) throws GeneralSecurityException {
        if(v < 0x800) {
            throw new GeneralSecurityException(String.format("Modulus size is %d; only modulus size >= 2048-bit is supported", v));
        }
        if(TinkFipsUtil.useOnlyFips() && (v != 0x800 && v != 0xC00)) {
            throw new GeneralSecurityException(String.format("Modulus size is %d; only modulus size of 2048- or 3072-bit is supported in FIPS mode.", v));
        }
    }

    public static void validateRsaPublicExponent(BigInteger bigInteger0) throws GeneralSecurityException {
        if(!bigInteger0.testBit(0)) {
            throw new GeneralSecurityException("Public exponent must be odd.");
        }
        if(bigInteger0.compareTo(BigInteger.valueOf(0x10000L)) <= 0) {
            throw new GeneralSecurityException("Public exponent must be greater than 65536.");
        }
    }

    public static void validateSignatureHash(HashType enums$HashType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.subtle.Validators.1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[enums$HashType0.ordinal()]) {
            case 1: 
            case 2: 
            case 3: {
                return;
            }
            default: {
                throw new GeneralSecurityException("Unsupported hash: " + enums$HashType0.name());
            }
        }
    }

    public static void validateTypeUrl(String s) throws GeneralSecurityException {
        if(!s.startsWith("type.googleapis.com/")) {
            throw new GeneralSecurityException(String.format("Error: type URL %s is invalid; it must start with %s.\n", s, "type.googleapis.com/"));
        }
        if(s.length() == 20) {
            throw new GeneralSecurityException(String.format("Error: type URL %s is invalid; it has no message name.\n", s));
        }
    }

    public static void validateVersion(int v, int v1) throws GeneralSecurityException {
        if(v < 0 || v > v1) {
            throw new GeneralSecurityException(String.format("key has version %d; only keys with version in range [0..%d] are supported", v, v1));
        }
    }
}

