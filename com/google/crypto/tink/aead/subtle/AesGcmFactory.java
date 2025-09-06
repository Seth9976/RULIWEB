package com.google.crypto.tink.aead.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.subtle.AesGcmJce;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

@Immutable
public final class AesGcmFactory implements AeadFactory {
    private final int keySizeInBytes;

    public AesGcmFactory(int v) throws GeneralSecurityException {
        this.keySizeInBytes = AesGcmFactory.validateAesKeySize(v);
    }

    @Override  // com.google.crypto.tink.aead.subtle.AeadFactory
    public Aead createAead(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length != this.getKeySizeInBytes()) {
            throw new GeneralSecurityException(String.format("Symmetric key has incorrect length; expected %s, but got %s", this.getKeySizeInBytes(), ((int)arr_b.length)));
        }
        return new AesGcmJce(arr_b);
    }

    @Override  // com.google.crypto.tink.aead.subtle.AeadFactory
    public int getKeySizeInBytes() {
        return this.keySizeInBytes;
    }

    private static int validateAesKeySize(int v) throws InvalidAlgorithmParameterException {
        if(v != 16 && v != 0x20) {
            throw new InvalidAlgorithmParameterException(String.format("Invalid AES key size, expected 16 or 32, but got %d", v));
        }
        return v;
    }
}

