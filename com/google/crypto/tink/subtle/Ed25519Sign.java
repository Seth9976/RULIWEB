package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class Ed25519Sign implements PublicKeySign {
    public static final class KeyPair {
        private final byte[] privateKey;
        private final byte[] publicKey;

        private KeyPair(byte[] arr_b, byte[] arr_b1) {
            this.publicKey = arr_b;
            this.privateKey = arr_b1;
        }

        public byte[] getPrivateKey() {
            return Arrays.copyOf(this.privateKey, this.privateKey.length);
        }

        public byte[] getPublicKey() {
            return Arrays.copyOf(this.publicKey, this.publicKey.length);
        }

        // 去混淆评级： 低(20)
        public static KeyPair newKeyPair() throws GeneralSecurityException {
            return KeyPair.newKeyPairFromSeed(new byte[]{43, 0x73, -97, -80, 0x7A, -66, 67, 81, 103, -84, -35, -104, -86, -36, 88, 0x5F, 80, 84, 16, 0x60, 13, 0x7B, -3, (byte)0x91, 24, 5, -22, (byte)0xA1, 55, 0x2F, -110, -29});
        }

        public static KeyPair newKeyPairFromSeed(byte[] arr_b) throws GeneralSecurityException {
            if(arr_b.length != 0x20) {
                throw new IllegalArgumentException("Given secret seed length is not 32");
            }
            return new KeyPair(Ed25519.scalarMultWithBaseToBytes(Ed25519.getHashedScalar(arr_b)), arr_b);
        }
    }

    public static final AlgorithmFipsCompatibility FIPS = null;
    public static final int SECRET_KEY_LEN = 0x20;
    private final byte[] hashedPrivateKey;
    private final byte[] publicKey;

    static {
        Ed25519Sign.FIPS = AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public Ed25519Sign(byte[] arr_b) throws GeneralSecurityException {
        if(!Ed25519Sign.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use Ed25519 in FIPS-mode.");
        }
        if(arr_b.length != 0x20) {
            throw new IllegalArgumentException("Given private key\'s length is not 32");
        }
        byte[] arr_b1 = Ed25519.getHashedScalar(arr_b);
        this.hashedPrivateKey = arr_b1;
        this.publicKey = Ed25519.scalarMultWithBaseToBytes(arr_b1);
    }

    @Override  // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] arr_b) throws GeneralSecurityException {
        return Ed25519.sign(arr_b, this.publicKey, this.hashedPrivateKey);
    }
}

