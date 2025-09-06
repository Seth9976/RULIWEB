package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

@Immutable
final class ChaCha20Poly1305HpkeAead implements HpkeAead {
    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] getAeadId() {
        return HpkeUtil.CHACHA20_POLY1305_AEAD_ID;
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getKeyLength() [...] // Inlined contents

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getNonceLength() {
        return 12;
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] open(byte[] arr_b, byte[] arr_b1, byte[] arr_b2, byte[] arr_b3) throws GeneralSecurityException {
        if(arr_b.length != 0x20) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + 0x20);
        }
        return new InsecureNonceChaCha20Poly1305(arr_b).decrypt(arr_b1, arr_b2, arr_b3);
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] seal(byte[] arr_b, byte[] arr_b1, byte[] arr_b2, byte[] arr_b3) throws GeneralSecurityException {
        if(arr_b.length != 0x20) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + 0x20);
        }
        return new InsecureNonceChaCha20Poly1305(arr_b).encrypt(arr_b1, arr_b2, arr_b3);
    }
}

