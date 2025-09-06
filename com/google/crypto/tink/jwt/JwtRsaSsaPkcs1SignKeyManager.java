package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPkcs1SignJce;
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

public final class JwtRsaSsaPkcs1SignKeyManager extends PrivateKeyTypeManager {
    static class JwtPublicKeySignFactory extends PrimitiveFactory {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        public JwtPublicKeySignInternal getPrimitive(JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey0) throws GeneralSecurityException {
            RSAPrivateCrtKey rSAPrivateCrtKey0 = JwtRsaSsaPkcs1SignKeyManager.createPrivateKey(jwtRsaSsaPkcs1PrivateKey0);
            JwtRsaSsaPkcs1SignKeyManager.selfTestKey(rSAPrivateCrtKey0, jwtRsaSsaPkcs1PrivateKey0);
            JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm0 = jwtRsaSsaPkcs1PrivateKey0.getPublicKey().getAlgorithm();
            RsaSsaPkcs1SignJce rsaSsaPkcs1SignJce0 = new RsaSsaPkcs1SignJce(rSAPrivateCrtKey0, JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(jwtRsaSsaPkcs1Algorithm0));
            String s = jwtRsaSsaPkcs1Algorithm0.name();
            return jwtRsaSsaPkcs1PrivateKey0.getPublicKey().hasCustomKid() ? new JwtPublicKeySignInternal() {
                @Override  // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt0, Optional optional0) throws GeneralSecurityException {
                    if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(LinkFollowing..ExternalSyntheticApiModelOutline0.m(""))) {
                        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        optional0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m("");
                    }
                    String s = JwtFormat.createUnsignedCompact(s, optional0, rawJwt0);
                    return JwtFormat.createSignedCompact(s, rsaSsaPkcs1SignJce0.sign(s.getBytes(StandardCharsets.US_ASCII)));
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
                    return JwtFormat.createSignedCompact(s, rsaSsaPkcs1SignJce0.sign(s.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }

        @Override  // com.google.crypto.tink.internal.PrimitiveFactory
        public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
            return this.getPrimitive(((JwtRsaSsaPkcs1PrivateKey)messageLite0));
        }
    }

    JwtRsaSsaPkcs1SignKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new JwtPublicKeySignFactory()};
        super(JwtRsaSsaPkcs1PrivateKey.class, JwtRsaSsaPkcs1PublicKey.class, arr_primitiveFactory);
    }

    private static KeyFormat createKeyFormat(JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm0, int v, BigInteger bigInteger0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(((JwtRsaSsaPkcs1KeyFormat)JwtRsaSsaPkcs1KeyFormat.newBuilder().setAlgorithm(jwtRsaSsaPkcs1Algorithm0).setModulusSizeInBits(v).setPublicExponent(ByteString.copyFrom(bigInteger0.toByteArray())).build()), keyTemplate$OutputPrefixType0);
    }

    private static final RSAPrivateCrtKey createPrivateKey(JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey0) throws GeneralSecurityException {
        return (RSAPrivateCrtKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getPublicKey().getE().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getD().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getP().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getQ().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getDp().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getDq().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getCrt().toByteArray())));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey";
    }

    public JwtRsaSsaPkcs1PublicKey getPublicKey(JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey0) {
        return jwtRsaSsaPkcs1PrivateKey0.getPublicKey();
    }

    @Override  // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public MessageLite getPublicKey(MessageLite messageLite0) throws GeneralSecurityException {
        return this.getPublicKey(((JwtRsaSsaPkcs1PrivateKey)messageLite0));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.internal.KeyTypeManager.KeyFactory keyFactory() {
        return new com.google.crypto.tink.internal.KeyTypeManager.KeyFactory(JwtRsaSsaPkcs1KeyFormat.class) {
            public JwtRsaSsaPkcs1PrivateKey createKey(JwtRsaSsaPkcs1KeyFormat jwtRsaSsaPkcs1KeyFormat0) throws GeneralSecurityException {
                JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm0 = jwtRsaSsaPkcs1KeyFormat0.getAlgorithm();
                KeyPairGenerator keyPairGenerator0 = (KeyPairGenerator)EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                keyPairGenerator0.initialize(new RSAKeyGenParameterSpec(jwtRsaSsaPkcs1KeyFormat0.getModulusSizeInBits(), new BigInteger(1, jwtRsaSsaPkcs1KeyFormat0.getPublicExponent().toByteArray())));
                KeyPair keyPair0 = keyPairGenerator0.generateKeyPair();
                RSAPublicKey rSAPublicKey0 = (RSAPublicKey)keyPair0.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey0 = (RSAPrivateCrtKey)keyPair0.getPrivate();
                JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey0 = (JwtRsaSsaPkcs1PublicKey)JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPkcs1Algorithm0).setE(ByteString.copyFrom(rSAPublicKey0.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey0.getModulus().toByteArray())).build();
                return (JwtRsaSsaPkcs1PrivateKey)JwtRsaSsaPkcs1PrivateKey.newBuilder().setVersion(0).setPublicKey(jwtRsaSsaPkcs1PublicKey0).setD(ByteString.copyFrom(rSAPrivateCrtKey0.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey0.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey0.getCrtCoefficient().toByteArray())).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((JwtRsaSsaPkcs1KeyFormat)messageLite0));
            }

            public JwtRsaSsaPkcs1PrivateKey deriveKey(JwtRsaSsaPkcs1KeyFormat jwtRsaSsaPkcs1KeyFormat0, InputStream inputStream0) {
                throw new UnsupportedOperationException();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((JwtRsaSsaPkcs1KeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("JWT_RS256_2048_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 0x800, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW));
                hashMap0.put("JWT_RS256_2048_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 0x800, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK));
                hashMap0.put("JWT_RS256_3072_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW));
                hashMap0.put("JWT_RS256_3072_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK));
                hashMap0.put("JWT_RS384_3072_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS384, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW));
                hashMap0.put("JWT_RS384_3072_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS384, 0xC00, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK));
                hashMap0.put("JWT_RS512_4096_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS512, 0x1000, RSAKeyGenParameterSpec.F4, OutputPrefixType.RAW));
                hashMap0.put("JWT_RS512_4096_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS512, 0x1000, RSAKeyGenParameterSpec.F4, OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap0);
            }

            public JwtRsaSsaPkcs1KeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return JwtRsaSsaPkcs1KeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(JwtRsaSsaPkcs1KeyFormat jwtRsaSsaPkcs1KeyFormat0) throws GeneralSecurityException {
                Validators.validateRsaModulusSize(jwtRsaSsaPkcs1KeyFormat0.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPkcs1KeyFormat0.getPublicExponent().toByteArray()));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((JwtRsaSsaPkcs1KeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public JwtRsaSsaPkcs1PrivateKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return JwtRsaSsaPkcs1PrivateKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void registerPair(boolean z) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtRsaSsaPkcs1SignKeyManager(), new JwtRsaSsaPkcs1VerifyKeyManager(), z);
    }

    private static final void selfTestKey(RSAPrivateCrtKey rSAPrivateCrtKey0, JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey0) throws GeneralSecurityException {
        SelfKeyTestValidators.validateRsaSsaPkcs1(rSAPrivateCrtKey0, ((RSAPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePublic(new RSAPublicKeySpec(new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getPublicKey().getE().toByteArray())))), JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(jwtRsaSsaPkcs1PrivateKey0.getPublicKey().getAlgorithm()));
    }

    public void validateKey(JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey0) throws GeneralSecurityException {
        Validators.validateVersion(jwtRsaSsaPkcs1PrivateKey0.getVersion(), 0);
        Validators.validateRsaModulusSize(new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPkcs1PrivateKey0.getPublicKey().getE().toByteArray()));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((JwtRsaSsaPkcs1PrivateKey)messageLite0));
    }
}

