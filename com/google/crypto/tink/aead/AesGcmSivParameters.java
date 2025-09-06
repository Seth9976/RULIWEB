package com.google.crypto.tink.aead;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class AesGcmSivParameters extends AeadParameters {
    public static final class Builder {
        @Nullable
        private Integer keySizeBytes;
        private Variant variant;

        private Builder() {
            this.keySizeBytes = null;
            this.variant = Variant.NO_PREFIX;
        }

        Builder(com.google.crypto.tink.aead.AesGcmSivParameters.1 aesGcmSivParameters$10) {
        }

        public AesGcmSivParameters build() throws GeneralSecurityException {
            if(this.keySizeBytes == null) {
                throw new GeneralSecurityException("Key size is not set");
            }
            if(this.variant == null) {
                throw new GeneralSecurityException("Variant is not set");
            }
            return new AesGcmSivParameters(((int)this.keySizeBytes), this.variant, null);
        }

        public Builder setKeySizeBytes(int v) throws GeneralSecurityException {
            if(v != 16 && v != 0x20) {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte and 32-byte AES keys are supported", v));
            }
            this.keySizeBytes = v;
            return this;
        }

        public Builder setVariant(Variant aesGcmSivParameters$Variant0) {
            this.variant = aesGcmSivParameters$Variant0;
            return this;
        }
    }

    @Immutable
    public static final class Variant {
        public static final Variant CRUNCHY;
        public static final Variant NO_PREFIX;
        public static final Variant TINK;
        private final String name;

        static {
            Variant.TINK = new Variant("TINK");
            Variant.CRUNCHY = new Variant("CRUNCHY");
            Variant.NO_PREFIX = new Variant("NO_PREFIX");
        }

        private Variant(String s) {
            this.name = s;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private final int keySizeBytes;
    private final Variant variant;

    private AesGcmSivParameters(int v, Variant aesGcmSivParameters$Variant0) {
        this.keySizeBytes = v;
        this.variant = aesGcmSivParameters$Variant0;
    }

    AesGcmSivParameters(int v, Variant aesGcmSivParameters$Variant0, com.google.crypto.tink.aead.AesGcmSivParameters.1 aesGcmSivParameters$10) {
        this(v, aesGcmSivParameters$Variant0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AesGcmSivParameters ? ((AesGcmSivParameters)object0).getKeySizeBytes() == this.getKeySizeBytes() && ((AesGcmSivParameters)object0).getVariant() == this.getVariant() : false;
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    public Variant getVariant() {
        return this.variant;
    }

    @Override  // com.google.crypto.tink.Parameters
    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.keySizeBytes, this.variant});
    }

    @Override
    public String toString() {
        return "AesGcmSiv Parameters (variant: " + this.variant + ", " + this.keySizeBytes + "-byte key)";
    }

    class com.google.crypto.tink.aead.AesGcmSivParameters.1 {
    }

}

