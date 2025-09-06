package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaKeyFormat;
import com.google.crypto.tink.proto.JwtEcdsaPrivateKey;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EcdsaSignJce;
import com.google.crypto.tink.subtle.EllipticCurves.EcdsaEncoding;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums.HashType;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public final class JwtEcdsaSignKeyManager extends PrivateKeyTypeManager {
    static class JwtPublicKeySignFactory extends PrimitiveFactory {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        public JwtPublicKeySignInternal getPrimitive(JwtEcdsaPrivateKey jwtEcdsaPrivateKey0) throws GeneralSecurityException {
            ECPrivateKey eCPrivateKey0 = EllipticCurves.getEcPrivateKey(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaPrivateKey0.getPublicKey().getAlgorithm()), jwtEcdsaPrivateKey0.getKeyValue().toByteArray());
            JwtPublicKeySignFactory.selfTestKey(eCPrivateKey0, jwtEcdsaPrivateKey0);
            JwtEcdsaAlgorithm jwtEcdsaAlgorithm0 = jwtEcdsaPrivateKey0.getPublicKey().getAlgorithm();
            EcdsaSignJce ecdsaSignJce0 = new EcdsaSignJce(eCPrivateKey0, JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(jwtEcdsaAlgorithm0), EcdsaEncoding.IEEE_P1363);
            String s = jwtEcdsaAlgorithm0.name();
            return jwtEcdsaPrivateKey0.getPublicKey().hasCustomKid() ? new JwtPublicKeySignInternal() {
                @Override  // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt0, Optional optional0) throws GeneralSecurityException {
                    if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(LinkFollowing..ExternalSyntheticApiModelOutline0.m(""))) {
                        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        optional0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m("");
                    }
                    String s = JwtFormat.createUnsignedCompact(s, optional0, rawJwt0);
                    return JwtFormat.createSignedCompact(s, ecdsaSignJce0.sign(s.getBytes(StandardCharsets.US_ASCII)));
                }
            } : new JwtPublicKeySignInternal() {
                @Override  // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt0, Optional optional0) throws GeneralSecurityException {
                    if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(LinkFollowing..ExternalSyntheticApiModelOutline0.m())) {
                        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        optional0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m();
                    }
                    String s = JwtFormat.createUnsignedCompact(s, optional0, rawJwt0);
                    return JwtFormat.createSignedCompact(s, ecdsaSignJce0.sign(s.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }

        @Override  // com.google.crypto.tink.internal.PrimitiveFactory
        public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
            return this.getPrimitive(((JwtEcdsaPrivateKey)messageLite0));
        }

        private static final void selfTestKey(ECPrivateKey eCPrivateKey0, JwtEcdsaPrivateKey jwtEcdsaPrivateKey0) throws GeneralSecurityException {
            HashType enums$HashType0 = JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(jwtEcdsaPrivateKey0.getPublicKey().getAlgorithm());
            SelfKeyTestValidators.validateEcdsa(eCPrivateKey0, EllipticCurves.getEcPublicKey(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaPrivateKey0.getPublicKey().getAlgorithm()), jwtEcdsaPrivateKey0.getPublicKey().getX().toByteArray(), jwtEcdsaPrivateKey0.getPublicKey().getY().toByteArray()), enums$HashType0, EcdsaEncoding.IEEE_P1363);
        }
    }

    JwtEcdsaSignKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new JwtPublicKeySignFactory()};
        super(JwtEcdsaPrivateKey.class, JwtEcdsaPublicKey.class, arr_primitiveFactory);
    }

    private static KeyFormat createKeyFormat(JwtEcdsaAlgorithm jwtEcdsaAlgorithm0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(((JwtEcdsaKeyFormat)JwtEcdsaKeyFormat.newBuilder().setAlgorithm(jwtEcdsaAlgorithm0).build()), keyTemplate$OutputPrefixType0);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtEcdsaPrivateKey";
    }

    public JwtEcdsaPublicKey getPublicKey(JwtEcdsaPrivateKey jwtEcdsaPrivateKey0) {
        return jwtEcdsaPrivateKey0.getPublicKey();
    }

    @Override  // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public MessageLite getPublicKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.getPublicKey(((JwtEcdsaPrivateKey)messageLite0));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(JwtEcdsaKeyFormat.class) {
            public JwtEcdsaPrivateKey createKey(JwtEcdsaKeyFormat jwtEcdsaKeyFormat0) throws GeneralSecurityException {
                JwtEcdsaAlgorithm jwtEcdsaAlgorithm0 = jwtEcdsaKeyFormat0.getAlgorithm();
                KeyPair keyPair0 = EllipticCurves.generateKeyPair(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaKeyFormat0.getAlgorithm()));
                ECPublicKey eCPublicKey0 = (ECPublicKey)keyPair0.getPublic();
                ECPrivateKey eCPrivateKey0 = (ECPrivateKey)keyPair0.getPrivate();
                ECPoint eCPoint0 = eCPublicKey0.getW();
                JwtEcdsaPublicKey jwtEcdsaPublicKey0 = (JwtEcdsaPublicKey)JwtEcdsaPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtEcdsaAlgorithm0).setX(ByteString.copyFrom(eCPoint0.getAffineX().toByteArray())).setY(ByteString.copyFrom(eCPoint0.getAffineY().toByteArray())).build();
                return (JwtEcdsaPrivateKey)JwtEcdsaPrivateKey.newBuilder().setVersion(0).setPublicKey(jwtEcdsaPublicKey0).setKeyValue(ByteString.copyFrom(eCPrivateKey0.getS().toByteArray())).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((JwtEcdsaKeyFormat)messageLite0));
            }

            public JwtEcdsaPrivateKey deriveKey(JwtEcdsaKeyFormat jwtEcdsaKeyFormat0, InputStream inputStream0) {
                throw new UnsupportedOperationException();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((JwtEcdsaKeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("JWT_ES256_RAW", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES256, OutputPrefixType.RAW));
                hashMap0.put("JWT_ES256", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES256, OutputPrefixType.TINK));
                hashMap0.put("JWT_ES384_RAW", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES384, OutputPrefixType.RAW));
                hashMap0.put("JWT_ES384", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES384, OutputPrefixType.TINK));
                hashMap0.put("JWT_ES512_RAW", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES512, OutputPrefixType.RAW));
                hashMap0.put("JWT_ES512", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES512, OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap0);
            }

            public JwtEcdsaKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return JwtEcdsaKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(JwtEcdsaKeyFormat jwtEcdsaKeyFormat0) throws GeneralSecurityException {
                JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(jwtEcdsaKeyFormat0.getAlgorithm());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((JwtEcdsaKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public JwtEcdsaPrivateKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return JwtEcdsaPrivateKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtEcdsaSignKeyManager(), new JwtEcdsaVerifyKeyManager(), z);
    }

    public void validateKey(JwtEcdsaPrivateKey jwtEcdsaPrivateKey0) throws GeneralSecurityException {
        Validators.validateVersion(jwtEcdsaPrivateKey0.getVersion(), 0);
        JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(jwtEcdsaPrivateKey0.getPublicKey().getAlgorithm());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((JwtEcdsaPrivateKey)messageLite0));
    }
}

