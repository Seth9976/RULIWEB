package com.google.crypto.tink.aead;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class AesEaxParameters extends AeadParameters {
    public static final class Builder {
        @Nullable
        private Integer ivSizeBytes;
        @Nullable
        private Integer keySizeBytes;
        @Nullable
        private Integer tagSizeBytes;
        private Variant variant;

        private Builder() {
            this.keySizeBytes = null;
            this.ivSizeBytes = null;
            this.tagSizeBytes = null;
            this.variant = Variant.NO_PREFIX;
        }

        Builder(com.google.crypto.tink.aead.AesEaxParameters.1 aesEaxParameters$10) {
        }

        public AesEaxParameters build() throws GeneralSecurityException {
            if(this.keySizeBytes == null) {
                throw new GeneralSecurityException("Key size is not set");
            }
            if(this.ivSizeBytes == null) {
                throw new GeneralSecurityException("IV size is not set");
            }
            if(this.variant == null) {
                throw new GeneralSecurityException("Variant is not set");
            }
            if(this.tagSizeBytes == null) {
                throw new GeneralSecurityException("Tag size is not set");
            }
            return new AesEaxParameters(((int)this.keySizeBytes), ((int)this.ivSizeBytes), ((int)this.tagSizeBytes), this.variant, null);
        }

        public Builder setIvSizeBytes(int v) throws GeneralSecurityException {
            if(v != 12 && v != 16) {
                throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d; acceptable values have 12 or 16 bytes", v));
            }
            this.ivSizeBytes = v;
            return this;
        }

        public Builder setKeySizeBytes(int v) throws GeneralSecurityException {
            if(v != 16 && v != 24 && v != 0x20) {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", v));
            }
            this.keySizeBytes = v;
            return this;
        }

        public Builder setTagSizeBytes(int v) throws GeneralSecurityException {
            if(v < 0 || v > 16) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; value must be at most 16 bytes", v));
            }
            this.tagSizeBytes = v;
            return this;
        }

        public Builder setVariant(Variant aesEaxParameters$Variant0) {
            this.variant = aesEaxParameters$Variant0;
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

    private final int ivSizeBytes;
    private final int keySizeBytes;
    private final int tagSizeBytes;
    private final Variant variant;

    private AesEaxParameters(int v, int v1, int v2, Variant aesEaxParameters$Variant0) {
        this.keySizeBytes = v;
        this.ivSizeBytes = v1;
        this.tagSizeBytes = v2;
        this.variant = aesEaxParameters$Variant0;
    }

    AesEaxParameters(int v, int v1, int v2, Variant aesEaxParameters$Variant0, com.google.crypto.tink.aead.AesEaxParameters.1 aesEaxParameters$10) {
        this(v, v1, v2, aesEaxParameters$Variant0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AesEaxParameters ? ((AesEaxParameters)object0).getKeySizeBytes() == this.getKeySizeBytes() && ((AesEaxParameters)object0).getIvSizeBytes() == this.getIvSizeBytes() && ((AesEaxParameters)object0).getTagSizeBytes() == this.getTagSizeBytes() && ((AesEaxParameters)object0).getVariant() == this.getVariant() : false;
    }

    public int getIvSizeBytes() {
        return this.ivSizeBytes;
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    public int getTagSizeBytes() {
        return this.tagSizeBytes;
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
        return Objects.hash(new Object[]{this.keySizeBytes, this.ivSizeBytes, this.tagSizeBytes, this.variant});
    }

    @Override
    public String toString() {
        return "AesEax Parameters (variant: " + this.variant + ", " + this.ivSizeBytes + "-byte IV, " + this.tagSizeBytes + "-byte tag, and " + this.keySizeBytes + "-byte key)";
    }

    class com.google.crypto.tink.aead.AesEaxParameters.1 {
    }

}

