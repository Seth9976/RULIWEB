package com.google.crypto.tink.signature;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EcdsaVerifyJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

class EcdsaVerifyKeyManager extends KeyTypeManager {
    public EcdsaVerifyKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public PublicKeyVerify getPrimitive(EcdsaPublicKey ecdsaPublicKey0) throws GeneralSecurityException {
                return new EcdsaVerifyJce(EllipticCurves.getEcPublicKey(SigUtil.toCurveType(ecdsaPublicKey0.getParams().getCurve()), ecdsaPublicKey0.getX().toByteArray(), ecdsaPublicKey0.getY().toByteArray()), SigUtil.toHashType(ecdsaPublicKey0.getParams().getHashType()), SigUtil.toEcdsaEncoding(ecdsaPublicKey0.getParams().getEncoding()));
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((EcdsaPublicKey)messageLite0));
            }
        }};
        super(EcdsaPublicKey.class, arr_primitiveFactory);
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

    public EcdsaPublicKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return EcdsaPublicKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public void validateKey(EcdsaPublicKey ecdsaPublicKey0) throws GeneralSecurityException {
        Validators.validateVersion(ecdsaPublicKey0.getVersion(), 0);
        SigUtil.validateEcdsaParams(ecdsaPublicKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((EcdsaPublicKey)messageLite0));
    }
}

