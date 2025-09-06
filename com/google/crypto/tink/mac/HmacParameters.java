package com.google.crypto.tink.mac;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class HmacParameters extends MacParameters {
    public static final class Builder {
        private HashType hashType;
        @Nullable
        private Integer keySizeBytes;
        @Nullable
        private Integer tagSizeBytes;
        private Variant variant;

        private Builder() {
            this.keySizeBytes = null;
            this.tagSizeBytes = null;
            this.hashType = null;
            this.variant = Variant.NO_PREFIX;
        }

        Builder(com.google.crypto.tink.mac.HmacParameters.1 hmacParameters$10) {
        }

        public HmacParameters build() throws GeneralSecurityException {
            Integer integer0 = this.keySizeBytes;
            if(integer0 == null) {
                throw new GeneralSecurityException("key size is not set");
            }
            if(this.tagSizeBytes == null) {
                throw new GeneralSecurityException("tag size is not set");
            }
            if(this.hashType == null) {
                throw new GeneralSecurityException("hash type is not set");
            }
            if(this.variant == null) {
                throw new GeneralSecurityException("variant is not set");
            }
            if(((int)integer0) < 16) {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 16 bytes", this.keySizeBytes));
            }
            Builder.validateTagSizeBytes(((int)this.tagSizeBytes), this.hashType);
            return new HmacParameters(((int)this.keySizeBytes), ((int)this.tagSizeBytes), this.variant, this.hashType, null);
        }

        public Builder setHashType(HashType hmacParameters$HashType0) {
            this.hashType = hmacParameters$HashType0;
            return this;
        }

        public Builder setKeySizeBytes(int v) throws GeneralSecurityException {
            this.keySizeBytes = v;
            return this;
        }

        public Builder setTagSizeBytes(int v) throws GeneralSecurityException {
            this.tagSizeBytes = v;
            return this;
        }

        public Builder setVariant(Variant hmacParameters$Variant0) {
            this.variant = hmacParameters$Variant0;
            return this;
        }

        private static void validateTagSizeBytes(int v, HashType hmacParameters$HashType0) throws GeneralSecurityException {
            if(v < 10) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", v));
            }
            if(hmacParameters$HashType0 == HashType.SHA1) {
                if(v > 20) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", v));
                }
                return;
            }
            if(hmacParameters$HashType0 == HashType.SHA224) {
                if(v > 28) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", v));
                }
                return;
            }
            if(hmacParameters$HashType0 == HashType.SHA256) {
                if(v > 0x20) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", v));
                }
                return;
            }
            if(hmacParameters$HashType0 == HashType.SHA384) {
                if(v > 0x30) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", v));
                }
                return;
            }
            if(hmacParameters$HashType0 != HashType.SHA512) {
                throw new GeneralSecurityException("unknown hash type; must be SHA256, SHA384 or SHA512");
            }
            if(v > 0x40) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", v));
            }
        }
    }

    @Immutable
    public static final class HashType {
        public static final HashType SHA1;
        public static final HashType SHA224;
        public static final HashType SHA256;
        public static final HashType SHA384;
        public static final HashType SHA512;
        private final String name;

        static {
            HashType.SHA1 = new HashType("SHA1");
            HashType.SHA224 = new HashType("SHA224");
            HashType.SHA256 = new HashType("SHA256");
            HashType.SHA384 = new HashType("SHA384");
            HashType.SHA512 = new HashType("SHA512");
        }

        private HashType(String s) {
            this.name = s;
        }

        @Override
        public String toString() {
            return this.name;
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

    private final HashType hashType;
    private final int keySizeBytes;
    private final int tagSizeBytes;
    private final Variant variant;

    private HmacParameters(int v, int v1, Variant hmacParameters$Variant0, HashType hmacParameters$HashType0) {
        this.keySizeBytes = v;
        this.tagSizeBytes = v1;
        this.variant = hmacParameters$Variant0;
        this.hashType = hmacParameters$HashType0;
    }

    HmacParameters(int v, int v1, Variant hmacParameters$Variant0, HashType hmacParameters$HashType0, com.google.crypto.tink.mac.HmacParameters.1 hmacParameters$10) {
        this(v, v1, hmacParameters$Variant0, hmacParameters$HashType0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof HmacParameters ? ((HmacParameters)object0).getKeySizeBytes() == this.getKeySizeBytes() && ((HmacParameters)object0).getTotalTagSizeBytes() == this.getTotalTagSizeBytes() && ((HmacParameters)object0).getVariant() == this.getVariant() && ((HmacParameters)object0).getHashType() == this.getHashType() : false;
    }

    public int getCryptographicTagSizeBytes() {
        return this.tagSizeBytes;
    }

    public HashType getHashType() {
        return this.hashType;
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
        return Objects.hash(new Object[]{this.keySizeBytes, this.tagSizeBytes, this.variant, this.hashType});
    }

    @Override
    public String toString() {
        return "HMAC Parameters (variant: " + this.variant + ", hashType: " + this.hashType + ", " + this.tagSizeBytes + "-byte tags, and " + this.keySizeBytes + "-byte key)";
    }

    class com.google.crypto.tink.mac.HmacParameters.1 {
    }

}

