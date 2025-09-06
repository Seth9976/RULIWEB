package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;

public final class Ed25519Verify implements PublicKeyVerify {
    public static final AlgorithmFipsCompatibility FIPS = null;
    public static final int PUBLIC_KEY_LEN = 0x20;
    public static final int SIGNATURE_LEN = 0x40;
    private final Bytes publicKey;

    static {
        Ed25519Verify.FIPS = AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public Ed25519Verify(byte[] arr_b) {
        if(!Ed25519Verify.FIPS.isCompatible()) {
            throw new IllegalStateException(new GeneralSecurityException("Can not use Ed25519 in FIPS-mode."));
        }
        if(arr_b.length != 0x20) {
            throw new IllegalArgumentException("Given public key\'s length is not 32.");
        }
        this.publicKey = Bytes.copyFrom(arr_b);
    }

    @Override  // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length != 0x40) {
            throw new GeneralSecurityException("The length of the signature is not 64.");
        }
        if(!Ed25519.verify(arr_b1, arr_b, this.publicKey.toByteArray())) {
            throw new GeneralSecurityException("Signature check failed.");
        }
    }
}

