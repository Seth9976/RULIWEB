package com.google.crypto.tink.signature;

import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.internal.EllipticCurvesUtil;
import com.google.crypto.tink.util.SecretBigInteger;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;

@Immutable
public final class EcdsaPrivateKey extends SignaturePrivateKey {
    public static class Builder {
        private SecretBigInteger privateValue;
        private EcdsaPublicKey publicKey;

        private Builder() {
            this.publicKey = null;
            this.privateValue = null;
        }

        Builder(com.google.crypto.tink.signature.EcdsaPrivateKey.1 ecdsaPrivateKey$10) {
        }

        public EcdsaPrivateKey build() throws GeneralSecurityException {
            if(this.publicKey == null) {
                throw new GeneralSecurityException("Cannot build without a ecdsa public key");
            }
            SecretBigInteger secretBigInteger0 = this.privateValue;
            if(secretBigInteger0 == null) {
                throw new GeneralSecurityException("Cannot build without a private value");
            }
            Builder.validatePrivateValue(secretBigInteger0.getBigInteger(InsecureSecretKeyAccess.get()), this.publicKey.getPublicPoint(), this.publicKey.getParameters().getCurveType());
            return new EcdsaPrivateKey(this.publicKey, this.privateValue, null);
        }

        public Builder setPrivateValue(SecretBigInteger secretBigInteger0) {
            this.privateValue = secretBigInteger0;
            return this;
        }

        public Builder setPublicKey(EcdsaPublicKey ecdsaPublicKey0) {
            this.publicKey = ecdsaPublicKey0;
            return this;
        }

        private static void validatePrivateValue(BigInteger bigInteger0, ECPoint eCPoint0, CurveType ecdsaParameters$CurveType0) throws GeneralSecurityException {
            BigInteger bigInteger1 = ecdsaParameters$CurveType0.toParameterSpec().getOrder();
            if(bigInteger0.signum() <= 0 || bigInteger0.compareTo(bigInteger1) >= 0 || !EllipticCurvesUtil.multiplyByGenerator(bigInteger0, ecdsaParameters$CurveType0.toParameterSpec()).equals(eCPoint0)) {
                throw new GeneralSecurityException("Invalid private value");
            }
        }
    }

    private final SecretBigInteger privateValue;
    private final EcdsaPublicKey publicKey;

    private EcdsaPrivateKey(EcdsaPublicKey ecdsaPublicKey0, SecretBigInteger secretBigInteger0) {
        this.publicKey = ecdsaPublicKey0;
        this.privateValue = secretBigInteger0;
    }

    EcdsaPrivateKey(EcdsaPublicKey ecdsaPublicKey0, SecretBigInteger secretBigInteger0, com.google.crypto.tink.signature.EcdsaPrivateKey.1 ecdsaPrivateKey$10) {
        this(ecdsaPublicKey0, secretBigInteger0);
    }

    public static Builder builder() {
        return new Builder(null);
    }

    // 去混淆评级： 低(30)
    @Override  // com.google.crypto.tink.Key
    public boolean equalsKey(Key key0) {
        return key0 instanceof EcdsaPrivateKey ? ((EcdsaPrivateKey)key0).publicKey.equalsKey(this.publicKey) && this.privateValue.equalsSecretBigInteger(((EcdsaPrivateKey)key0).privateValue) : false;
    }

    @Override  // com.google.crypto.tink.signature.SignaturePrivateKey
    public Parameters getParameters() {
        return this.getParameters();
    }

    public EcdsaParameters getParameters() {
        return this.publicKey.getParameters();
    }

    @Override  // com.google.crypto.tink.signature.SignaturePrivateKey
    public SignatureParameters getParameters() {
        return this.getParameters();
    }

    public SecretBigInteger getPrivateValue() {
        return this.privateValue;
    }

    @Override  // com.google.crypto.tink.signature.SignaturePrivateKey
    public Key getPublicKey() {
        return this.getPublicKey();
    }

    public EcdsaPublicKey getPublicKey() {
        return this.publicKey;
    }

    @Override  // com.google.crypto.tink.signature.SignaturePrivateKey
    public SignaturePublicKey getPublicKey() {
        return this.getPublicKey();
    }

    class com.google.crypto.tink.signature.EcdsaPrivateKey.1 {
    }

}

