package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums.HashType;
import com.google.crypto.tink.subtle.RsaSsaPssVerifyJce;
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

class JwtRsaSsaPssVerifyKeyManager extends KeyTypeManager {
    public JwtRsaSsaPssVerifyKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public JwtPublicKeyVerifyInternal getPrimitive(JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey0) throws GeneralSecurityException {
                RSAPublicKey rSAPublicKey0 = JwtRsaSsaPssVerifyKeyManager.createPublicKey(jwtRsaSsaPssPublicKey0);
                HashType enums$HashType0 = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(jwtRsaSsaPssPublicKey0.getAlgorithm());
                RsaSsaPssVerifyJce rsaSsaPssVerifyJce0 = new RsaSsaPssVerifyJce(rSAPublicKey0, enums$HashType0, enums$HashType0, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(jwtRsaSsaPssPublicKey0.getAlgorithm()));
                String s = jwtRsaSsaPssPublicKey0.getAlgorithm().name();
                return jwtRsaSsaPssPublicKey0.hasCustomKid() ? new JwtPublicKeyVerifyInternal() {
                    @Override  // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                    public VerifiedJwt verifyAndDecodeWithKid(String s, JwtValidator jwtValidator0, Optional optional0) throws GeneralSecurityException {
                        Parts jwtFormat$Parts0 = JwtFormat.splitSignedCompact(s);
                        rsaSsaPssVerifyJce0.verify(jwtFormat$Parts0.signatureOrMac, jwtFormat$Parts0.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                        JsonObject jsonObject0 = JsonUtil.parseJson(jwtFormat$Parts0.header);
                        JwtFormat.validateHeader(s, optional0, LinkFollowing..ExternalSyntheticApiModelOutline0.m(""), jsonObject0);
                        return jwtValidator0.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(jsonObject0), jwtFormat$Parts0.payload));
                    }
                } : new JwtPublicKeyVerifyInternal() {
                    @Override  // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                    public VerifiedJwt verifyAndDecodeWithKid(String s, JwtValidator jwtValidator0, Optional optional0) throws GeneralSecurityException {
                        Parts jwtFormat$Parts0 = JwtFormat.splitSignedCompact(s);
                        rsaSsaPssVerifyJce0.verify(jwtFormat$Parts0.signatureOrMac, jwtFormat$Parts0.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                        JsonObject jsonObject0 = JsonUtil.parseJson(jwtFormat$Parts0.header);
                        JwtFormat.validateHeader(s, optional0, LinkFollowing..ExternalSyntheticApiModelOutline0.m(), jsonObject0);
                        return jwtValidator0.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(jsonObject0), jwtFormat$Parts0.payload));
                    }
                };
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((JwtRsaSsaPssPublicKey)messageLite0));
            }
        }};
        super(JwtRsaSsaPssPublicKey.class, arr_primitiveFactory);
    }

    private static final RSAPublicKey createPublicKey(JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey0) throws GeneralSecurityException {
        return (RSAPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePublic(new RSAPublicKeySpec(new BigInteger(1, jwtRsaSsaPssPublicKey0.getN().toByteArray()), new BigInteger(1, jwtRsaSsaPssPublicKey0.getE().toByteArray())));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    static final HashType hashForPssAlgorithm(JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.jwt.JwtRsaSsaPssVerifyKeyManager.2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[jwtRsaSsaPssAlgorithm0.ordinal()]) {
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
                throw new GeneralSecurityException("unknown algorithm " + jwtRsaSsaPssAlgorithm0.name());
            }
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public JwtRsaSsaPssPublicKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return JwtRsaSsaPssPublicKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    static final int saltLengthForPssAlgorithm(JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.jwt.JwtRsaSsaPssVerifyKeyManager.2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[jwtRsaSsaPssAlgorithm0.ordinal()]) {
            case 1: {
                return 0x20;
            }
            case 2: {
                return 0x30;
            }
            case 3: {
                return 0x40;
            }
            default: {
                throw new GeneralSecurityException("unknown algorithm " + jwtRsaSsaPssAlgorithm0.name());
            }
        }
    }

    public void validateKey(JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey0) throws GeneralSecurityException {
        Validators.validateVersion(jwtRsaSsaPssPublicKey0.getVersion(), 0);
        Validators.validateRsaModulusSize(new BigInteger(1, jwtRsaSsaPssPublicKey0.getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPssPublicKey0.getE().toByteArray()));
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((JwtRsaSsaPssPublicKey)messageLite0));
    }

    class com.google.crypto.tink.jwt.JwtRsaSsaPssVerifyKeyManager.2 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm;

        static {
            int[] arr_v = new int[JwtRsaSsaPssAlgorithm.values().length];
            com.google.crypto.tink.jwt.JwtRsaSsaPssVerifyKeyManager.2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm = arr_v;
            try {
                arr_v[JwtRsaSsaPssAlgorithm.PS256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwtRsaSsaPssVerifyKeyManager.2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwtRsaSsaPssVerifyKeyManager.2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS512.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

