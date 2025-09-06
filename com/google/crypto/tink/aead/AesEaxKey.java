package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
public final class AesEaxKey extends AeadKey {
    public static class Builder {
        @Nullable
        private Integer idRequirement;
        @Nullable
        private SecretBytes keyBytes;
        @Nullable
        private AesEaxParameters parameters;

        private Builder() {
            this.parameters = null;
            this.keyBytes = null;
            this.idRequirement = null;
        }

        Builder(com.google.crypto.tink.aead.AesEaxKey.1 aesEaxKey$10) {
        }

        public AesEaxKey build() throws GeneralSecurityException {
            AesEaxParameters aesEaxParameters0 = this.parameters;
            if(aesEaxParameters0 == null || this.keyBytes == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if(aesEaxParameters0.getKeySizeBytes() != this.keyBytes.size()) {
                throw new GeneralSecurityException("Key size mismatch");
            }
            if(this.parameters.hasIdRequirement() && this.idRequirement == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            }
            if(!this.parameters.hasIdRequirement() && this.idRequirement != null) {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            Bytes bytes0 = this.getOutputPrefix();
            return new AesEaxKey(this.parameters, this.keyBytes, bytes0, this.idRequirement, null);
        }

        private Bytes getOutputPrefix() {
            if(this.parameters.getVariant() == Variant.NO_PREFIX) {
                return Bytes.copyFrom(new byte[0]);
            }
            if(this.parameters.getVariant() == Variant.CRUNCHY) {
                return Bytes.copyFrom(ByteBuffer.allocate(5).put(0).putInt(((int)this.idRequirement)).array());
            }
            if(this.parameters.getVariant() != Variant.TINK) {
                throw new IllegalStateException("Unknown AesEaxParameters.Variant: " + this.parameters.getVariant());
            }
            return Bytes.copyFrom(ByteBuffer.allocate(5).put(1).putInt(((int)this.idRequirement)).array());
        }

        public Builder setIdRequirement(@Nullable Integer integer0) {
            this.idRequirement = integer0;
            return this;
        }

        public Builder setKeyBytes(SecretBytes secretBytes0) {
            this.keyBytes = secretBytes0;
            return this;
        }

        public Builder setParameters(AesEaxParameters aesEaxParameters0) {
            this.parameters = aesEaxParameters0;
            return this;
        }
    }

    @Nullable
    private final Integer idRequirement;
    private final SecretBytes keyBytes;
    private final Bytes outputPrefix;
    private final AesEaxParameters parameters;

    private AesEaxKey(AesEaxParameters aesEaxParameters0, SecretBytes secretBytes0, Bytes bytes0, @Nullable Integer integer0) {
        this.parameters = aesEaxParameters0;
        this.keyBytes = secretBytes0;
        this.outputPrefix = bytes0;
        this.idRequirement = integer0;
    }

    AesEaxKey(AesEaxParameters aesEaxParameters0, SecretBytes secretBytes0, Bytes bytes0, Integer integer0, com.google.crypto.tink.aead.AesEaxKey.1 aesEaxKey$10) {
        this(aesEaxParameters0, secretBytes0, bytes0, integer0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    // 去混淆评级： 低(40)
    @Override  // com.google.crypto.tink.Key
    public boolean equalsKey(Key key0) {
        return key0 instanceof AesEaxKey ? ((AesEaxKey)key0).parameters.equals(this.parameters) && ((AesEaxKey)key0).keyBytes.equalsSecretBytes(this.keyBytes) && Objects.equals(((AesEaxKey)key0).idRequirement, this.idRequirement) : false;
    }

    @Override  // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.idRequirement;
    }

    public SecretBytes getKeyBytes() {
        return this.keyBytes;
    }

    @Override  // com.google.crypto.tink.aead.AeadKey
    public Bytes getOutputPrefix() {
        return this.outputPrefix;
    }

    @Override  // com.google.crypto.tink.aead.AeadKey
    public Parameters getParameters() {
        return this.getParameters();
    }

    @Override  // com.google.crypto.tink.aead.AeadKey
    public AeadParameters getParameters() {
        return this.getParameters();
    }

    public AesEaxParameters getParameters() {
        return this.parameters;
    }

    class com.google.crypto.tink.aead.AesEaxKey.1 {
    }

}

