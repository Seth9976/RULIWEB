package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPssPrivateKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums.HashType;
import com.google.crypto.tink.subtle.RsaSsaPssSignJce;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public final class JwtRsaSsaPssSignKeyManager extends PrivateKeyTypeManager {
    static class JwtPublicKeySignFactory extends PrimitiveFactory {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        public JwtPublicKeySignInternal getPrimitive(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey0) throws GeneralSecurityException {
            RSAPrivateCrtKey rSAPrivateCrtKey0 = JwtRsaSsaPssSignKeyManager.createPrivateKey(jwtRsaSsaPssPrivateKey0);
            JwtRsaSsaPssSignKeyManager.selfTestKey(rSAPrivateCrtKey0, jwtRsaSsaPssPrivateKey0);
            JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0 = jwtRsaSsaPssPrivateKey0.getPublicKey().getAlgorithm();
            HashType enums$HashType0 = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(jwtRsaSsaPssAlgorithm0);
            RsaSsaPssSignJce rsaSsaPssSignJce0 = new RsaSsaPssSignJce(rSAPrivateCrtKey0, enums$HashType0, enums$HashType0, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(jwtRsaSsaPssAlgorithm0));
            String s = jwtRsaSsaPssAlgorithm0.name();
            return jwtRsaSsaPssPrivateKey0.getPublicKey().hasCustomKid() ? new JwtPublicKeySignInternal() {
                @Override  // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt0, Optional optional0) throws GeneralSecurityException {
                    if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(LinkFollowing..ExternalSyntheticApiModelOutline0.m(""))) {
                        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        optional0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m("");
                    }
                    String s = JwtFormat.createUnsignedCompact(s, optional0, rawJwt0);
                    return JwtFormat.createSignedCompact(s, rsaSsaPssSignJce0.sign(s.getBytes(StandardCharsets.US_ASCII)));
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
                    return JwtFormat.createSignedCompact(s, rsaSsaPssSignJce0.sign(s.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }

        @Override  // com.google.crypto.tink.internal.PrimitiveFactory
        public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
            return this.getPrimitive(((JwtRsaSsaPssPrivateKey)messageLite0));
        }
    }

    JwtRsaSsaPssSignKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new JwtPublicKeySignFactory()};
        super(JwtRsaSsaPssPrivateKey.class, JwtRsaSsaPssPublicKey.class, arr_primitiveFactory);
    }

    private static KeyFormat createKeyFormat(JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0, int v, BigInteger bigInteger0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(((JwtRsaSsaPssKeyFormat)JwtRsaSsaPssKeyFormat.newBuilder().setAlgorithm(jwtRsaSsaPssAlgorithm0).setModulusSizeInBits(v).setPublicExponent(ByteString.copyFrom(bigInteger0.toByteArray())).build()), keyTemplate$OutputPrefixType0);
    }

    private static final RSAPrivateCrtKey createPrivateKey(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey0) throws GeneralSecurityException {
        return (RSAPrivateCrtKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, jwtRsaSsaPssPrivateKey0.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey0.getPublicKey().getE().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey0.getD().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey0.getP().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey0.getQ().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey0.getDp().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey0.getDq().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey0.getCrt().toByteArray())));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey";
    }

    public JwtRsaSsaPssPublicKey getPublicKey(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey0) {
        return jwtRsaSsaPssPrivateKey0.getPublicKey();
    }

    @Override  // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public MessageLite getPublicKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.getPublicKey(((JwtRsaSsaPssPrivateKey)messageLite0));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.internal.KeyTypeManager.KeyFactory keyFactory() {
        return new com.google.crypto.tink.internal.KeyTypeManager.KeyFactory(JwtRsaSsaPssKeyFormat.class) {
            public JwtRsaSsaPssPrivateKey createKey(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat0) throws GeneralSecurityException {
                JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0 = jwtRsaSsaPssKeyFormat0.getAlgorithm();
                KeyPairGenerator keyPairGenerator0 = (KeyPairGenerator)EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                keyPairGenerator0.initialize(new RSAKeyGenParameterSpec(jwtRsaSsaPssKeyFormat0.getModulusSizeInBits(), new BigInteger(1, jwtRsaSsaPssKeyFormat0.getPublicExponent().toByteArray())));
                KeyPair keyPair0 = keyPairGenerator0.generateKeyPair();
                RSAPublicKey rSAPublicKey0 = (RSAPublicKey)keyPair0.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey0 = (RSAPrivateCrtKey)keyPair0.getPrivate();
                JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey0 = (JwtRsaSsaPssPublicKey)JwtRsaSsaPssPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPssAlgorithm0).setE(ByteString.copyFrom(rSAPublicKey0.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey0.getModulus().toByteArray())).build();
                return (JwtRsaSsaPssPrivateKey)JwtRsaSsaPssPrivateKey.newBuilder().setVersion(0).setPublicKey(jwtRsaSsaPssPublicKey0).setD(ByteString.copyFrom(rSAPrivateCrtKey0.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey0.getCrtCoefficient().toByteArray())).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((JwtRsaSsaPssKeyFormat)messageLite0));
            }

            public JwtRsaSsaPssPrivateKey deriveKey(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat0, InputStream inputStream0) {
                throw new UnsupportedOperationException();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((JwtRsaSsaPssKeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("JWT_PS256_2048_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 0x800, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW));
                hashMap0.put("JWT_PS256_2048_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 0x800, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK));
                hashMap0.put("JWT_PS256_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW));
                hashMap0.put("JWT_PS256_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK));
                hashMap0.put("JWT_PS384_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS384, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW));
                hashMap0.put("JWT_PS384_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS384, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK));
                hashMap0.put("JWT_PS512_4096_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS512, 0x1000, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW));
                hashMap0.put("JWT_PS512_4096_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS512, 0x1000, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap0);
            }

            public JwtRsaSsaPssKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return JwtRsaSsaPssKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat0) throws GeneralSecurityException {
                Validators.validateRsaModulusSize(jwtRsaSsaPssKeyFormat0.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPssKeyFormat0.getPublicExponent().toByteArray()));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((JwtRsaSsaPssKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public JwtRsaSsaPssPrivateKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return JwtRsaSsaPssPrivateKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtRsaSsaPssSignKeyManager(), new JwtRsaSsaPssVerifyKeyManager(), z);
    }

    private static final void selfTestKey(RSAPrivateCrtKey rSAPrivateCrtKey0, JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey0) throws GeneralSecurityException {
        RSAPublicKey rSAPublicKey0 = (RSAPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePublic(new RSAPublicKeySpec(new BigInteger(1, jwtRsaSsaPssPrivateKey0.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey0.getPublicKey().getE().toByteArray())));
        JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0 = jwtRsaSsaPssPrivateKey0.getPublicKey().getAlgorithm();
        HashType enums$HashType0 = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(jwtRsaSsaPssAlgorithm0);
        SelfKeyTestValidators.validateRsaSsaPss(rSAPrivateCrtKey0, rSAPublicKey0, enums$HashType0, enums$HashType0, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(jwtRsaSsaPssAlgorithm0));
    }

    public void validateKey(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey0) throws GeneralSecurityException {
        Validators.validateVersion(jwtRsaSsaPssPrivateKey0.getVersion(), 0);
        Validators.validateRsaModulusSize(new BigInteger(1, jwtRsaSsaPssPrivateKey0.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPssPrivateKey0.getPublicKey().getE().toByteArray()));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((JwtRsaSsaPssPrivateKey)messageLite0));
    }
}

