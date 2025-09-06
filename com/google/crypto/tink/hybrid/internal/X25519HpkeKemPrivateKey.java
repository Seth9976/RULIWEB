package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.X25519;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
final class X25519HpkeKemPrivateKey implements HpkeKemPrivateKey {
    private final Bytes privateKey;
    private final Bytes publicKey;

    private X25519HpkeKemPrivateKey(byte[] arr_b, byte[] arr_b1) {
        this.privateKey = Bytes.copyFrom(arr_b);
        this.publicKey = Bytes.copyFrom(arr_b1);
    }

    static X25519HpkeKemPrivateKey fromBytes(byte[] arr_b) throws GeneralSecurityException {
        return new X25519HpkeKemPrivateKey(arr_b, X25519.publicFromPrivate(arr_b));
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKemPrivateKey
    public Bytes getSerializedPrivate() {
        return this.privateKey;
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKemPrivateKey
    public Bytes getSerializedPublic() {
        return this.publicKey;
    }
}

