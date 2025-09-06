package com.google.crypto.tink.prf;

import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class HkdfPrfParameters extends PrfParameters {
    public static final class Builder {
        @Nullable
        private HashType hashType;
        @Nullable
        private Integer keySizeBytes;
        @Nullable
        private Bytes salt;

        private Builder() {
            this.keySizeBytes = null;
            this.hashType = null;
            this.salt = null;
        }

        Builder(com.google.crypto.tink.prf.HkdfPrfParameters.1 hkdfPrfParameters$10) {
        }

        public HkdfPrfParameters build() throws GeneralSecurityException {
            if(this.keySizeBytes == null) {
                throw new GeneralSecurityException("key size is not set");
            }
            if(this.hashType == null) {
                throw new GeneralSecurityException("hash type is not set");
            }
            return new HkdfPrfParameters(((int)this.keySizeBytes), this.hashType, this.salt, null);
        }

        public Builder setHashType(HashType hkdfPrfParameters$HashType0) {
            this.hashType = hkdfPrfParameters$HashType0;
            return this;
        }

        public Builder setKeySizeBytes(int v) throws GeneralSecurityException {
            if(v < 16) {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit or larger are supported", ((int)(v * 8))));
            }
            this.keySizeBytes = v;
            return this;
        }

        public Builder setSalt(Bytes bytes0) {
            if(bytes0.size() == 0) {
                return this;
            }
            this.salt = bytes0;
            return this;
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

    private static final int MIN_KEY_SIZE = 16;
    private final HashType hashType;
    private final int keySizeBytes;
    @Nullable
    private final Bytes salt;

    private HkdfPrfParameters(int v, HashType hkdfPrfParameters$HashType0, Bytes bytes0) {
        this.keySizeBytes = v;
        this.hashType = hkdfPrfParameters$HashType0;
        this.salt = bytes0;
    }

    HkdfPrfParameters(int v, HashType hkdfPrfParameters$HashType0, Bytes bytes0, com.google.crypto.tink.prf.HkdfPrfParameters.1 hkdfPrfParameters$10) {
        this(v, hkdfPrfParameters$HashType0, bytes0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof HkdfPrfParameters ? ((HkdfPrfParameters)object0).getKeySizeBytes() == this.getKeySizeBytes() && ((HkdfPrfParameters)object0).getHashType() == this.getHashType() && Objects.equals(((HkdfPrfParameters)object0).getSalt(), this.getSalt()) : false;
    }

    public HashType getHashType() {
        return this.hashType;
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    @Nullable
    public Bytes getSalt() {
        return this.salt;
    }

    @Override  // com.google.crypto.tink.Parameters
    public boolean hasIdRequirement() {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.keySizeBytes, this.hashType, this.salt});
    }

    @Override
    public String toString() {
        return "HKDF PRF Parameters (hashType: " + this.hashType + ", salt: " + this.salt + ", and " + this.keySizeBytes + "-byte key)";
    }

    class com.google.crypto.tink.prf.HkdfPrfParameters.1 {
    }

}

