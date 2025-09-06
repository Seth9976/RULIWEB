package com.google.crypto.tink.signature;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPssVerifyJce;
import com.google.crypto.tink.subtle.Validators;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

class RsaSsaPssVerifyKeyManager extends KeyTypeManager {
    public RsaSsaPssVerifyKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public PublicKeyVerify getPrimitive(RsaSsaPssPublicKey rsaSsaPssPublicKey0) throws GeneralSecurityException {
                RSAPublicKey rSAPublicKey0 = (RSAPublicKey)((KeyFactory)EngineFactory.KEY_FACTORY.getInstance("RSA")).generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPssPublicKey0.getN().toByteArray()), new BigInteger(1, rsaSsaPssPublicKey0.getE().toByteArray())));
                RsaSsaPssParams rsaSsaPssParams0 = rsaSsaPssPublicKey0.getParams();
                return new RsaSsaPssVerifyJce(rSAPublicKey0, SigUtil.toHashType(rsaSsaPssParams0.getSigHash()), SigUtil.toHashType(rsaSsaPssParams0.getMgf1Hash()), rsaSsaPssParams0.getSaltLength());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((RsaSsaPssPublicKey)messageLite0));
            }
        }};
        super(RsaSsaPssPublicKey.class, arr_primitiveFactory);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public RsaSsaPssPublicKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return RsaSsaPssPublicKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public void validateKey(RsaSsaPssPublicKey rsaSsaPssPublicKey0) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPssPublicKey0.getVersion(), 0);
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPssPublicKey0.getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPssPublicKey0.getE().toByteArray()));
        SigUtil.validateRsaSsaPssParams(rsaSsaPssPublicKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((RsaSsaPssPublicKey)messageLite0));
    }
}

