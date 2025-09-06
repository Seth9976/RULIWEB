package com.google.crypto.tink.signature;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.internal.EllipticCurvesUtil;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
public final class EcdsaPublicKey extends SignaturePublicKey {
    public static class Builder {
        @Nullable
        private Integer idRequirement;
        @Nullable
        private EcdsaParameters parameters;
        @Nullable
        private ECPoint publicPoint;

        private Builder() {
            this.parameters = null;
            this.publicPoint = null;
            this.idRequirement = null;
        }

        Builder(com.google.crypto.tink.signature.EcdsaPublicKey.1 ecdsaPublicKey$10) {
        }

        public EcdsaPublicKey build() throws GeneralSecurityException {
            EcdsaParameters ecdsaParameters0 = this.parameters;
            if(ecdsaParameters0 == null) {
                throw new GeneralSecurityException("Cannot build without parameters");
            }
            ECPoint eCPoint0 = this.publicPoint;
            if(eCPoint0 == null) {
                throw new GeneralSecurityException("Cannot build without public point");
            }
            EllipticCurvesUtil.checkPointOnCurve(eCPoint0, ecdsaParameters0.getCurveType().toParameterSpec().getCurve());
            if(this.parameters.hasIdRequirement() && this.idRequirement == null) {
                throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
            }
            if(!this.parameters.hasIdRequirement() && this.idRequirement != null) {
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            Bytes bytes0 = this.getOutputPrefix();
            return new EcdsaPublicKey(this.parameters, this.publicPoint, bytes0, this.idRequirement, null);
        }

        private Bytes getOutputPrefix() {
            if(this.parameters.getVariant() == Variant.NO_PREFIX) {
                return Bytes.copyFrom(new byte[0]);
            }
            if(this.parameters.getVariant() != Variant.LEGACY && this.parameters.getVariant() != Variant.CRUNCHY) {
                if(this.parameters.getVariant() != Variant.TINK) {
                    throw new IllegalStateException("Unknown EcdsaParameters.Variant: " + this.parameters.getVariant());
                }
                return Bytes.copyFrom(ByteBuffer.allocate(5).put(1).putInt(((int)this.idRequirement)).array());
            }
            return Bytes.copyFrom(ByteBuffer.allocate(5).put(0).putInt(((int)this.idRequirement)).array());
        }

        public Builder setIdRequirement(@Nullable Integer integer0) {
            this.idRequirement = integer0;
            return this;
        }

        public Builder setParameters(EcdsaParameters ecdsaParameters0) {
            this.parameters = ecdsaParameters0;
            return this;
        }

        public Builder setPublicPoint(ECPoint eCPoint0) {
            this.publicPoint = eCPoint0;
            return this;
        }
    }

    @Nullable
    private final Integer idRequirement;
    private final Bytes outputPrefix;
    private final EcdsaParameters parameters;
    private final ECPoint publicPoint;

    private EcdsaPublicKey(EcdsaParameters ecdsaParameters0, ECPoint eCPoint0, Bytes bytes0, @Nullable Integer integer0) {
        this.parameters = ecdsaParameters0;
        this.publicPoint = eCPoint0;
        this.outputPrefix = bytes0;
        this.idRequirement = integer0;
    }

    EcdsaPublicKey(EcdsaParameters ecdsaParameters0, ECPoint eCPoint0, Bytes bytes0, Integer integer0, com.google.crypto.tink.signature.EcdsaPublicKey.1 ecdsaPublicKey$10) {
        this(ecdsaParameters0, eCPoint0, bytes0, integer0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    // 去混淆评级： 低(40)
    @Override  // com.google.crypto.tink.Key
    public boolean equalsKey(Key key0) {
        return key0 instanceof EcdsaPublicKey ? ((EcdsaPublicKey)key0).parameters.equals(this.parameters) && ((EcdsaPublicKey)key0).publicPoint.equals(this.publicPoint) && Objects.equals(((EcdsaPublicKey)key0).idRequirement, this.idRequirement) : false;
    }

    @Override  // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.idRequirement;
    }

    @Override  // com.google.crypto.tink.signature.SignaturePublicKey
    public Bytes getOutputPrefix() {
        return this.outputPrefix;
    }

    @Override  // com.google.crypto.tink.signature.SignaturePublicKey
    public Parameters getParameters() {
        return this.getParameters();
    }

    public EcdsaParameters getParameters() {
        return this.parameters;
    }

    @Override  // com.google.crypto.tink.signature.SignaturePublicKey
    public SignatureParameters getParameters() {
        return this.getParameters();
    }

    public ECPoint getPublicPoint() {
        return this.publicPoint;
    }

    class com.google.crypto.tink.signature.EcdsaPublicKey.1 {
    }

}

