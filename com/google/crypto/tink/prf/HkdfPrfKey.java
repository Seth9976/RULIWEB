package com.google.crypto.tink.prf;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.util.SecretBytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

@Immutable
public final class HkdfPrfKey extends PrfKey {
    public static final class Builder {
        @Nullable
        private SecretBytes keyBytes;
        @Nullable
        private HkdfPrfParameters parameters;

        private Builder() {
            this.parameters = null;
            this.keyBytes = null;
        }

        Builder(com.google.crypto.tink.prf.HkdfPrfKey.1 hkdfPrfKey$10) {
        }

        public HkdfPrfKey build() throws GeneralSecurityException {
            HkdfPrfParameters hkdfPrfParameters0 = this.parameters;
            if(hkdfPrfParameters0 == null || this.keyBytes == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if(hkdfPrfParameters0.getKeySizeBytes() != this.keyBytes.size()) {
                throw new GeneralSecurityException("Key size mismatch");
            }
            return new HkdfPrfKey(this.parameters, this.keyBytes, null);
        }

        public Builder setKeyBytes(SecretBytes secretBytes0) {
            this.keyBytes = secretBytes0;
            return this;
        }

        public Builder setParameters(HkdfPrfParameters hkdfPrfParameters0) {
            this.parameters = hkdfPrfParameters0;
            return this;
        }
    }

    private final SecretBytes keyBytes;
    private final HkdfPrfParameters parameters;

    private HkdfPrfKey(HkdfPrfParameters hkdfPrfParameters0, SecretBytes secretBytes0) {
        this.parameters = hkdfPrfParameters0;
        this.keyBytes = secretBytes0;
    }

    HkdfPrfKey(HkdfPrfParameters hkdfPrfParameters0, SecretBytes secretBytes0, com.google.crypto.tink.prf.HkdfPrfKey.1 hkdfPrfKey$10) {
        this(hkdfPrfParameters0, secretBytes0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    // 去混淆评级： 低(30)
    @Override  // com.google.crypto.tink.Key
    public boolean equalsKey(Key key0) {
        return key0 instanceof HkdfPrfKey ? ((HkdfPrfKey)key0).parameters.equals(this.parameters) && ((HkdfPrfKey)key0).keyBytes.equalsSecretBytes(this.keyBytes) : false;
    }

    @Override  // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() [...] // Inlined contents

    public SecretBytes getKeyBytes() {
        return this.keyBytes;
    }

    @Override  // com.google.crypto.tink.prf.PrfKey
    public Parameters getParameters() {
        return this.getParameters();
    }

    public HkdfPrfParameters getParameters() {
        return this.parameters;
    }

    @Override  // com.google.crypto.tink.prf.PrfKey
    public PrfParameters getParameters() {
        return this.getParameters();
    }

    class com.google.crypto.tink.prf.HkdfPrfKey.1 {
    }

}

