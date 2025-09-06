package com.google.crypto.tink.signature;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.PrivateKey;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import javax.annotation.Nullable;

@Immutable
public abstract class SignaturePrivateKey extends Key implements PrivateKey {
    @Override  // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.getPublicKey().getIdRequirementOrNull();
    }

    public final Bytes getOutputPrefix() {
        return this.getPublicKey().getOutputPrefix();
    }

    @Override  // com.google.crypto.tink.Key
    public Parameters getParameters() {
        return this.getParameters();
    }

    public SignatureParameters getParameters() {
        return this.getPublicKey().getParameters();
    }

    @Override  // com.google.crypto.tink.PrivateKey
    public Key getPublicKey() {
        return this.getPublicKey();
    }

    public abstract SignaturePublicKey getPublicKey();
}

