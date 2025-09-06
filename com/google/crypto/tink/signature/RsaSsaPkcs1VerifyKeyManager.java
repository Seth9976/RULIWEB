package com.google.crypto.tink.signature;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPkcs1VerifyJce;
import com.google.crypto.tink.subtle.Validators;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

class RsaSsaPkcs1VerifyKeyManager extends KeyTypeManager {
    public RsaSsaPkcs1VerifyKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public PublicKeyVerify getPrimitive(RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0) throws GeneralSecurityException {
                return new RsaSsaPkcs1VerifyJce(((RSAPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPkcs1PublicKey0.getN().toByteArray()), new BigInteger(1, rsaSsaPkcs1PublicKey0.getE().toByteArray())))), SigUtil.toHashType(rsaSsaPkcs1PublicKey0.getParams().getHashType()));
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((RsaSsaPkcs1PublicKey)messageLite0));
            }
        }};
        super(RsaSsaPkcs1PublicKey.class, arr_primitiveFactory);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public AlgorithmFipsCompatibility fipsStatus() {
        return AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public RsaSsaPkcs1PublicKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return RsaSsaPkcs1PublicKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public void validateKey(RsaSsaPkcs1PublicKey rsaSsaPkcs1PublicKey0) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPkcs1PublicKey0.getVersion(), 0);
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPkcs1PublicKey0.getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPkcs1PublicKey0.getE().toByteArray()));
        SigUtil.validateRsaSsaPkcs1Params(rsaSsaPkcs1PublicKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((RsaSsaPkcs1PublicKey)messageLite0));
    }
}

