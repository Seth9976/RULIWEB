package com.google.crypto.tink.mac;

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
public final class AesCmacKey extends MacKey {
    public static class Builder {
        @Nullable
        private SecretBytes aesKeyBytes;
        @Nullable
        private Integer idRequirement;
        @Nullable
        private AesCmacParameters parameters;

        private Builder() {
            this.parameters = null;
            this.aesKeyBytes = null;
            this.idRequirement = null;
        }

        Builder(com.google.crypto.tink.mac.AesCmacKey.1 aesCmacKey$10) {
        }

        public AesCmacKey build() throws GeneralSecurityException {
            AesCmacParameters aesCmacParameters0 = this.parameters;
            if(aesCmacParameters0 == null || this.aesKeyBytes == null) {
                throw new GeneralSecurityException("Cannot build without parameters and/or key material");
            }
            if(aesCmacParameters0.getKeySizeBytes() != this.aesKeyBytes.size()) {
                throw new GeneralSecurityException("Key size mismatch");
            }
            if(this.parameters.hasIdRequirement() && this.idRequirement == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            }
            if(!this.parameters.hasIdRequirement() && this.idRequirement != null) {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            Bytes bytes0 = this.getOutputPrefix();
            return new AesCmacKey(this.parameters, this.aesKeyBytes, bytes0, this.idRequirement, null);
        }

        private Bytes getOutputPrefix() {
            if(this.parameters.getVariant() == Variant.NO_PREFIX) {
                return Bytes.copyFrom(new byte[0]);
            }
            if(this.parameters.getVariant() != Variant.LEGACY && this.parameters.getVariant() != Variant.CRUNCHY) {
                if(this.parameters.getVariant() != Variant.TINK) {
                    throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: " + this.parameters.getVariant());
                }
                return Bytes.copyFrom(ByteBuffer.allocate(5).put(1).putInt(((int)this.idRequirement)).array());
            }
            return Bytes.copyFrom(ByteBuffer.allocate(5).put(0).putInt(((int)this.idRequirement)).array());
        }

        public Builder setAesKeyBytes(SecretBytes secretBytes0) throws GeneralSecurityException {
            this.aesKeyBytes = secretBytes0;
            return this;
        }

        public Builder setIdRequirement(@Nullable Integer integer0) {
            this.idRequirement = integer0;
            return this;
        }

        public Builder setParameters(AesCmacParameters aesCmacParameters0) {
            this.parameters = aesCmacParameters0;
            return this;
        }
    }

    private final SecretBytes aesKeyBytes;
    @Nullable
    private final Integer idRequirement;
    private final Bytes outputPrefix;
    private final AesCmacParameters parameters;

    private AesCmacKey(AesCmacParameters aesCmacParameters0, SecretBytes secretBytes0, Bytes bytes0, @Nullable Integer integer0) {
        this.parameters = aesCmacParameters0;
        this.aesKeyBytes = secretBytes0;
        this.outputPrefix = bytes0;
        this.idRequirement = integer0;
    }

    AesCmacKey(AesCmacParameters aesCmacParameters0, SecretBytes secretBytes0, Bytes bytes0, Integer integer0, com.google.crypto.tink.mac.AesCmacKey.1 aesCmacKey$10) {
        this(aesCmacParameters0, secretBytes0, bytes0, integer0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    // 去混淆评级： 低(40)
    @Override  // com.google.crypto.tink.Key
    public boolean equalsKey(Key key0) {
        return key0 instanceof AesCmacKey ? ((AesCmacKey)key0).parameters.equals(this.parameters) && ((AesCmacKey)key0).aesKeyBytes.equalsSecretBytes(this.aesKeyBytes) && Objects.equals(((AesCmacKey)key0).idRequirement, this.idRequirement) : false;
    }

    public SecretBytes getAesKey() {
        return this.aesKeyBytes;
    }

    @Override  // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.idRequirement;
    }

    @Override  // com.google.crypto.tink.mac.MacKey
    public Bytes getOutputPrefix() {
        return this.outputPrefix;
    }

    @Override  // com.google.crypto.tink.mac.MacKey
    public Parameters getParameters() {
        return this.getParameters();
    }

    public AesCmacParameters getParameters() {
        return this.parameters;
    }

    @Override  // com.google.crypto.tink.mac.MacKey
    public MacParameters getParameters() {
        return this.getParameters();
    }

    class com.google.crypto.tink.mac.AesCmacKey.1 {
    }

}

