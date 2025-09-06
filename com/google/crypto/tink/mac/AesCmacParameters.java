package com.google.crypto.tink.mac;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class AesCmacParameters extends MacParameters {
    public static final class Builder {
        @Nullable
        private Integer keySizeBytes;
        @Nullable
        private Integer tagSizeBytes;
        private Variant variant;

        private Builder() {
            this.keySizeBytes = null;
            this.tagSizeBytes = null;
            this.variant = Variant.NO_PREFIX;
        }

        Builder(com.google.crypto.tink.mac.AesCmacParameters.1 aesCmacParameters$10) {
        }

        public AesCmacParameters build() throws GeneralSecurityException {
            if(this.keySizeBytes == null) {
                throw new GeneralSecurityException("key size not set");
            }
            if(this.tagSizeBytes == null) {
                throw new GeneralSecurityException("tag size not set");
            }
            if(this.variant == null) {
                throw new GeneralSecurityException("variant not set");
            }
            return new AesCmacParameters(((int)this.keySizeBytes), ((int)this.tagSizeBytes), this.variant, null);
        }

        public Builder setKeySizeBytes(int v) throws GeneralSecurityException {
            if(v != 16 && v != 0x20) {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit and 256-bit AES keys are supported", ((int)(v * 8))));
            }
            this.keySizeBytes = v;
            return this;
        }

        public Builder setTagSizeBytes(int v) throws GeneralSecurityException {
            if(v < 10 || 16 < v) {
                throw new GeneralSecurityException("Invalid tag size for AesCmacParameters: " + v);
            }
            this.tagSizeBytes = v;
            return this;
        }

        public Builder setVariant(Variant aesCmacParameters$Variant0) {
            this.variant = aesCmacParameters$Variant0;
            return this;
        }
    }

    @Immutable
    public static final class Variant {
        public static final Variant CRUNCHY;
        public static final Variant LEGACY;
        public static final Variant NO_PREFIX;
        public static final Variant TINK;
        private final String name;

        static {
            Variant.TINK = new Variant("TINK");
            Variant.CRUNCHY = new Variant("CRUNCHY");
            Variant.LEGACY = new Variant("LEGACY");
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
    private final int tagSizeBytes;
    private final Variant variant;

    private AesCmacParameters(int v, int v1, Variant aesCmacParameters$Variant0) {
        this.keySizeBytes = v;
        this.tagSizeBytes = v1;
        this.variant = aesCmacParameters$Variant0;
    }

    AesCmacParameters(int v, int v1, Variant aesCmacParameters$Variant0, com.google.crypto.tink.mac.AesCmacParameters.1 aesCmacParameters$10) {
        this(v, v1, aesCmacParameters$Variant0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AesCmacParameters ? ((AesCmacParameters)object0).getKeySizeBytes() == this.getKeySizeBytes() && ((AesCmacParameters)object0).getTotalTagSizeBytes() == this.getTotalTagSizeBytes() && ((AesCmacParameters)object0).getVariant() == this.getVariant() : false;
    }

    public int getCryptographicTagSizeBytes() {
        return this.tagSizeBytes;
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    public int getTotalTagSizeBytes() {
        if(this.variant == Variant.NO_PREFIX) {
            return this.getCryptographicTagSizeBytes();
        }
        if(this.variant == Variant.TINK) {
            return this.getCryptographicTagSizeBytes() + 5;
        }
        if(this.variant == Variant.CRUNCHY) {
            return this.getCryptographicTagSizeBytes() + 5;
        }
        if(this.variant != Variant.LEGACY) {
            throw new IllegalStateException("Unknown variant");
        }
        return this.getCryptographicTagSizeBytes() + 5;
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
        return Objects.hash(new Object[]{this.keySizeBytes, this.tagSizeBytes, this.variant});
    }

    @Override
    public String toString() {
        return "AES-CMAC Parameters (variant: " + this.variant + ", " + this.tagSizeBytes + "-byte tags, and " + this.keySizeBytes + "-byte key)";
    }

    class com.google.crypto.tink.mac.AesCmacParameters.1 {
    }

}

