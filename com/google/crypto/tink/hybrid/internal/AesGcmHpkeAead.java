package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.aead.internal.InsecureNonceAesGcmJce;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

@Immutable
final class AesGcmHpkeAead implements HpkeAead {
    private final int keyLength;

    AesGcmHpkeAead(int v) throws InvalidAlgorithmParameterException {
        if(v != 16 && v != 0x20) {
            throw new InvalidAlgorithmParameterException("Unsupported key length: " + v);
        }
        this.keyLength = v;
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] getAeadId() throws GeneralSecurityException {
        switch(this.keyLength) {
            case 16: {
                return HpkeUtil.AES_128_GCM_AEAD_ID;
            }
            case 0x20: {
                return HpkeUtil.AES_256_GCM_AEAD_ID;
            }
            default: {
                throw new GeneralSecurityException("Could not determine HPKE AEAD ID");
            }
        }
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getKeyLength() {
        return this.keyLength;
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getNonceLength() {
        return 12;
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] open(byte[] arr_b, byte[] arr_b1, byte[] arr_b2, byte[] arr_b3) throws GeneralSecurityException {
        if(arr_b.length != this.keyLength) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + arr_b.length);
        }
        return new InsecureNonceAesGcmJce(arr_b, false).decrypt(arr_b1, arr_b2, arr_b3);
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] seal(byte[] arr_b, byte[] arr_b1, byte[] arr_b2, byte[] arr_b3) throws GeneralSecurityException {
        if(arr_b.length != this.keyLength) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + arr_b.length);
        }
        return new InsecureNonceAesGcmJce(arr_b, false).encrypt(arr_b1, arr_b2, arr_b3);
    }
}

