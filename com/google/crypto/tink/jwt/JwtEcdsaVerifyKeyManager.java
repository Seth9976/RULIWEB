package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EcdsaVerifyJce;
import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import com.google.crypto.tink.subtle.EllipticCurves.EcdsaEncoding;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums.HashType;
import com.google.crypto.tink.subtle.Validators;
import com.google.gson.JsonObject;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Optional;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

class JwtEcdsaVerifyKeyManager extends KeyTypeManager {
    static class JwtPublicKeyVerifyFactory extends PrimitiveFactory {
        public JwtPublicKeyVerifyFactory() {
            super(JwtPublicKeyVerifyInternal.class);
        }

        public JwtPublicKeyVerifyInternal getPrimitive(JwtEcdsaPublicKey jwtEcdsaPublicKey0) throws GeneralSecurityException {
            EcdsaVerifyJce ecdsaVerifyJce0 = new EcdsaVerifyJce(EllipticCurves.getEcPublicKey(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaPublicKey0.getAlgorithm()), jwtEcdsaPublicKey0.getX().toByteArray(), jwtEcdsaPublicKey0.getY().toByteArray()), JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(jwtEcdsaPublicKey0.getAlgorithm()), EcdsaEncoding.IEEE_P1363);
            String s = jwtEcdsaPublicKey0.getAlgorithm().name();
            return jwtEcdsaPublicKey0.hasCustomKid() ? new JwtPublicKeyVerifyInternal() {
                @Override  // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                public VerifiedJwt verifyAndDecodeWithKid(String s, JwtValidator jwtValidator0, Optional optional0) throws GeneralSecurityException {
                    Parts jwtFormat$Parts0 = JwtFormat.splitSignedCompact(s);
                    ecdsaVerifyJce0.verify(jwtFormat$Parts0.signatureOrMac, jwtFormat$Parts0.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                    JsonObject jsonObject0 = JsonUtil.parseJson(jwtFormat$Parts0.header);
                    JwtFormat.validateHeader(s, optional0, LinkFollowing..ExternalSyntheticApiModelOutline0.m(""), jsonObject0);
                    return jwtValidator0.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(jsonObject0), jwtFormat$Parts0.payload));
                }
            } : new JwtPublicKeyVerifyInternal() {
                @Override  // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                public VerifiedJwt verifyAndDecodeWithKid(String s, JwtValidator jwtValidator0, Optional optional0) throws GeneralSecurityException {
                    Parts jwtFormat$Parts0 = JwtFormat.splitSignedCompact(s);
                    ecdsaVerifyJce0.verify(jwtFormat$Parts0.signatureOrMac, jwtFormat$Parts0.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                    JsonObject jsonObject0 = JsonUtil.parseJson(jwtFormat$Parts0.header);
                    JwtFormat.validateHeader(s, optional0, LinkFollowing..ExternalSyntheticApiModelOutline0.m(), jsonObject0);
                    return jwtValidator0.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(jsonObject0), jwtFormat$Parts0.payload));
                }
            };
        }

        @Override  // com.google.crypto.tink.internal.PrimitiveFactory
        public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
            return this.getPrimitive(((JwtEcdsaPublicKey)messageLite0));
        }
    }

    public JwtEcdsaVerifyKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new JwtPublicKeyVerifyFactory()};
        super(JwtEcdsaPublicKey.class, arr_primitiveFactory);
    }

    static final CurveType getCurve(JwtEcdsaAlgorithm jwtEcdsaAlgorithm0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager.1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[jwtEcdsaAlgorithm0.ordinal()]) {
            case 1: {
                return CurveType.NIST_P256;
            }
            case 2: {
                return CurveType.NIST_P384;
            }
            case 3: {
                return CurveType.NIST_P521;
            }
            default: {
                throw new GeneralSecurityException("unknown algorithm " + jwtEcdsaAlgorithm0.name());
            }
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    public static HashType hashForEcdsaAlgorithm(JwtEcdsaAlgorithm jwtEcdsaAlgorithm0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager.1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[jwtEcdsaAlgorithm0.ordinal()]) {
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
                throw new GeneralSecurityException("unknown algorithm " + jwtEcdsaAlgorithm0.name());
            }
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public JwtEcdsaPublicKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return JwtEcdsaPublicKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    static final void validateEcdsaAlgorithm(JwtEcdsaAlgorithm jwtEcdsaAlgorithm0) throws GeneralSecurityException {
        JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(jwtEcdsaAlgorithm0);
    }

    public void validateKey(JwtEcdsaPublicKey jwtEcdsaPublicKey0) throws GeneralSecurityException {
        Validators.validateVersion(jwtEcdsaPublicKey0.getVersion(), 0);
        JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(jwtEcdsaPublicKey0.getAlgorithm());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((JwtEcdsaPublicKey)messageLite0));
    }

    class com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm;

        static {
            int[] arr_v = new int[JwtEcdsaAlgorithm.values().length];
            com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager.1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm = arr_v;
            try {
                arr_v[JwtEcdsaAlgorithm.ES256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager.1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager.1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES512.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

