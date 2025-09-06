package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;
import java.security.GeneralSecurityException;

public class AeadOrDaead {
    private final Aead aead;
    private final DeterministicAead deterministicAead;

    public AeadOrDaead(Aead aead0) {
        this.aead = aead0;
        this.deterministicAead = null;
    }

    public AeadOrDaead(DeterministicAead deterministicAead0) {
        this.aead = null;
        this.deterministicAead = deterministicAead0;
    }

    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return this.aead == null ? this.deterministicAead.decryptDeterministically(arr_b, arr_b1) : this.aead.decrypt(arr_b, arr_b1);
    }

    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return this.aead == null ? this.deterministicAead.encryptDeterministically(arr_b, arr_b1) : this.aead.encrypt(arr_b, arr_b1);
    }
}

