package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums.HashType;
import com.google.crypto.tink.subtle.RsaSsaPkcs1VerifyJce;
import com.google.crypto.tink.subtle.Validators;
import com.google.gson.JsonObject;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Optional;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

class JwtRsaSsaPkcs1VerifyKeyManager extends KeyTypeManager {
    public JwtRsaSsaPkcs1VerifyKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public JwtPublicKeyVerifyInternal getPrimitive(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey0) throws GeneralSecurityException {
                RsaSsaPkcs1VerifyJce rsaSsaPkcs1VerifyJce0 = new RsaSsaPkcs1VerifyJce(JwtRsaSsaPkcs1VerifyKeyManager.access$000(jwtRsaSsaPkcs1PublicKey0), JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(jwtRsaSsaPkcs1PublicKey0.getAlgorithm()));
                String s = jwtRsaSsaPkcs1PublicKey0.getAlgorithm().name();
                return jwtRsaSsaPkcs1PublicKey0.hasCustomKid() ? new JwtPublicKeyVerifyInternal() {
                    @Override  // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                    public VerifiedJwt verifyAndDecodeWithKid(String s, JwtValidator jwtValidator0, Optional optional0) throws GeneralSecurityException {
                        Parts jwtFormat$Parts0 = JwtFormat.splitSignedCompact(s);
                        rsaSsaPkcs1VerifyJce0.verify(jwtFormat$Parts0.signatureOrMac, jwtFormat$Parts0.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                        JsonObject jsonObject0 = JsonUtil.parseJson(jwtFormat$Parts0.header);
                        JwtFormat.validateHeader(s, optional0, LinkFollowing..ExternalSyntheticApiModelOutline0.m(""), jsonObject0);
                        return jwtValidator0.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(jsonObject0), jwtFormat$Parts0.payload));
                    }
                } : new JwtPublicKeyVerifyInternal() {
                    @Override  // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                    public VerifiedJwt verifyAndDecodeWithKid(String s, JwtValidator jwtValidator0, Optional optional0) throws GeneralSecurityException {
                        Parts jwtFormat$Parts0 = JwtFormat.splitSignedCompact(s);
                        rsaSsaPkcs1VerifyJce0.verify(jwtFormat$Parts0.signatureOrMac, jwtFormat$Parts0.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                        JsonObject jsonObject0 = JsonUtil.parseJson(jwtFormat$Parts0.header);
                        JwtFormat.validateHeader(s, optional0, LinkFollowing..ExternalSyntheticApiModelOutline0.m(), jsonObject0);
                        return jwtValidator0.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(jsonObject0), jwtFormat$Parts0.payload));
                    }
                };
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((JwtRsaSsaPkcs1PublicKey)messageLite0));
            }
        }};
        super(JwtRsaSsaPkcs1PublicKey.class, arr_primitiveFactory);
    }

    static RSAPublicKey access$000(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey0) throws GeneralSecurityException {
        return JwtRsaSsaPkcs1VerifyKeyManager.createPublicKey(jwtRsaSsaPkcs1PublicKey0);
    }

    private static final RSAPublicKey createPublicKey(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey0) throws GeneralSecurityException {
        return (RSAPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePublic(new RSAPublicKeySpec(new BigInteger(1, jwtRsaSsaPkcs1PublicKey0.getN().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PublicKey0.getE().toByteArray())));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    public static HashType hashForPkcs1Algorithm(JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager.2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[jwtRsaSsaPkcs1Algorithm0.ordinal()]) {
            case 1: {
                return HashType.SHA256;
            }
            case 2: {
                return HashType.SHA384;
            }
            case 3: {
                return HashType.SHA512;
            }
            default: {
                throw new GeneralSecurityException("unknown algorithm " + jwtRsaSsaPkcs1Algorithm0.name());
            }
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public JwtRsaSsaPkcs1PublicKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return JwtRsaSsaPkcs1PublicKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public void validateKey(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey0) throws GeneralSecurityException {
        Validators.validateVersion(jwtRsaSsaPkcs1PublicKey0.getVersion(), 0);
        Validators.validateRsaModulusSize(new BigInteger(1, jwtRsaSsaPkcs1PublicKey0.getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPkcs1PublicKey0.getE().toByteArray()));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((JwtRsaSsaPkcs1PublicKey)messageLite0));
    }

    class com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager.2 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm;

        static {
            int[] arr_v = new int[JwtRsaSsaPkcs1Algorithm.values().length];
            com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager.2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm = arr_v;
            try {
                arr_v[JwtRsaSsaPkcs1Algorithm.RS256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager.2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager.2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS512.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

