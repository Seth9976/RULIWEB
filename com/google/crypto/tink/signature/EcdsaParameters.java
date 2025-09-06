package com.google.crypto.tink.signature;

import com.google.crypto.tink.internal.EllipticCurvesUtil;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.util.Objects;

public final class EcdsaParameters extends SignatureParameters {
    public static final class Builder {
        private CurveType curveType;
        private HashType hashType;
        private SignatureEncoding signatureEncoding;
        private Variant variant;

        private Builder() {
            this.signatureEncoding = null;
            this.curveType = null;
            this.hashType = null;
            this.variant = Variant.NO_PREFIX;
        }

        Builder(com.google.crypto.tink.signature.EcdsaParameters.1 ecdsaParameters$10) {
        }

        public EcdsaParameters build() throws GeneralSecurityException {
            if(this.signatureEncoding == null) {
                throw new GeneralSecurityException("signature encoding is not set");
            }
            CurveType ecdsaParameters$CurveType0 = this.curveType;
            if(ecdsaParameters$CurveType0 == null) {
                throw new GeneralSecurityException("EC curve type is not set");
            }
            if(this.hashType == null) {
                throw new GeneralSecurityException("hash type is not set");
            }
            if(this.variant == null) {
                throw new GeneralSecurityException("variant is not set");
            }
            if(ecdsaParameters$CurveType0 == CurveType.NIST_P256 && this.hashType != HashType.SHA256) {
                throw new GeneralSecurityException("NIST_P256 requires SHA256");
            }
            if(this.curveType == CurveType.NIST_P384 && this.hashType != HashType.SHA384 && this.hashType != HashType.SHA512) {
                throw new GeneralSecurityException("NIST_P384 requires SHA384 or SHA512");
            }
            if(this.curveType == CurveType.NIST_P521 && this.hashType != HashType.SHA512) {
                throw new GeneralSecurityException("NIST_P521 requires SHA512");
            }
            return new EcdsaParameters(this.signatureEncoding, this.curveType, this.hashType, this.variant, null);
        }

        public Builder setCurveType(CurveType ecdsaParameters$CurveType0) {
            this.curveType = ecdsaParameters$CurveType0;
            return this;
        }

        public Builder setHashType(HashType ecdsaParameters$HashType0) {
            this.hashType = ecdsaParameters$HashType0;
            return this;
        }

        public Builder setSignatureEncoding(SignatureEncoding ecdsaParameters$SignatureEncoding0) {
            this.signatureEncoding = ecdsaParameters$SignatureEncoding0;
            return this;
        }

        public Builder setVariant(Variant ecdsaParameters$Variant0) {
            this.variant = ecdsaParameters$Variant0;
            return this;
        }
    }

    @Immutable
    public static final class CurveType {
        public static final CurveType NIST_P256;
        public static final CurveType NIST_P384;
        public static final CurveType NIST_P521;
        private final String name;
        private final ECParameterSpec spec;

        static {
            CurveType.NIST_P256 = new CurveType("NIST_P256", EllipticCurvesUtil.NIST_P256_PARAMS);
            CurveType.NIST_P384 = new CurveType("NIST_P384", EllipticCurvesUtil.NIST_P384_PARAMS);
            CurveType.NIST_P521 = new CurveType("NIST_P521", EllipticCurvesUtil.NIST_P521_PARAMS);
        }

        private CurveType(String s, ECParameterSpec eCParameterSpec0) {
            this.name = s;
            this.spec = eCParameterSpec0;
        }

        public static CurveType fromParameterSpec(ECParameterSpec eCParameterSpec0) throws GeneralSecurityException {
            CurveType ecdsaParameters$CurveType0 = CurveType.NIST_P256;
            if(EllipticCurvesUtil.isSameEcParameterSpec(eCParameterSpec0, ecdsaParameters$CurveType0.toParameterSpec())) {
                return ecdsaParameters$CurveType0;
            }
            CurveType ecdsaParameters$CurveType1 = CurveType.NIST_P384;
            if(EllipticCurvesUtil.isSameEcParameterSpec(eCParameterSpec0, ecdsaParameters$CurveType1.toParameterSpec())) {
                return ecdsaParameters$CurveType1;
            }
            CurveType ecdsaParameters$CurveType2 = CurveType.NIST_P521;
            if(!EllipticCurvesUtil.isSameEcParameterSpec(eCParameterSpec0, ecdsaParameters$CurveType2.toParameterSpec())) {
                throw new GeneralSecurityException("unknown ECParameterSpec");
            }
            return ecdsaParameters$CurveType2;
        }

        public ECParameterSpec toParameterSpec() {
            return this.spec;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    @Immutable
    public static final class HashType {
        public static final HashType SHA256;
        public static final HashType SHA384;
        public static final HashType SHA512;
        private final String name;

        static {
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
    public static final class SignatureEncoding {
        public static final SignatureEncoding DER;
        public static final SignatureEncoding IEEE_P1363;
        private final String name;

        static {
            SignatureEncoding.IEEE_P1363 = new SignatureEncoding("IEEE_P1363");
            SignatureEncoding.DER = new SignatureEncoding("DER");
        }

        private SignatureEncoding(String s) {
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

    private final CurveType curveType;
    private final HashType hashType;
    private final SignatureEncoding signatureEncoding;
    private final Variant variant;

    private EcdsaParameters(SignatureEncoding ecdsaParameters$SignatureEncoding0, CurveType ecdsaParameters$CurveType0, HashType ecdsaParameters$HashType0, Variant ecdsaParameters$Variant0) {
        this.signatureEncoding = ecdsaParameters$SignatureEncoding0;
        this.curveType = ecdsaParameters$CurveType0;
        this.hashType = ecdsaParameters$HashType0;
        this.variant = ecdsaParameters$Variant0;
    }

    EcdsaParameters(SignatureEncoding ecdsaParameters$SignatureEncoding0, CurveType ecdsaParameters$CurveType0, HashType ecdsaParameters$HashType0, Variant ecdsaParameters$Variant0, com.google.crypto.tink.signature.EcdsaParameters.1 ecdsaParameters$10) {
        this(ecdsaParameters$SignatureEncoding0, ecdsaParameters$CurveType0, ecdsaParameters$HashType0, ecdsaParameters$Variant0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof EcdsaParameters ? ((EcdsaParameters)object0).getSignatureEncoding() == this.getSignatureEncoding() && ((EcdsaParameters)object0).getCurveType() == this.getCurveType() && ((EcdsaParameters)object0).getHashType() == this.getHashType() && ((EcdsaParameters)object0).getVariant() == this.getVariant() : false;
    }

    public CurveType getCurveType() {
        return this.curveType;
    }

    public HashType getHashType() {
        return this.hashType;
    }

    public SignatureEncoding getSignatureEncoding() {
        return this.signatureEncoding;
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
        return Objects.hash(new Object[]{this.signatureEncoding, this.curveType, this.hashType, this.variant});
    }

    @Override
    public String toString() {
        return "ECDSA Parameters (variant: " + this.variant + ", hashType: " + this.hashType + ", encoding: " + this.signatureEncoding + ", curve: " + this.curveType + ")";
    }

    class com.google.crypto.tink.signature.EcdsaParameters.1 {
    }

}

