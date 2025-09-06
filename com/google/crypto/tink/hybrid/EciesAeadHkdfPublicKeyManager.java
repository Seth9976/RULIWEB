package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesAeadHkdfPublicKey;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EciesAeadHkdfHybridEncrypt;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

class EciesAeadHkdfPublicKeyManager extends KeyTypeManager {
    public EciesAeadHkdfPublicKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public HybridEncrypt getPrimitive(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey0) throws GeneralSecurityException {
                EciesAeadHkdfParams eciesAeadHkdfParams0 = eciesAeadHkdfPublicKey0.getParams();
                EciesHkdfKemParams eciesHkdfKemParams0 = eciesAeadHkdfParams0.getKemParams();
                ECPublicKey eCPublicKey0 = EllipticCurves.getEcPublicKey(HybridUtil.toCurveType(eciesHkdfKemParams0.getCurveType()), eciesAeadHkdfPublicKey0.getX().toByteArray(), eciesAeadHkdfPublicKey0.getY().toByteArray());
                RegistryEciesAeadHkdfDemHelper registryEciesAeadHkdfDemHelper0 = new RegistryEciesAeadHkdfDemHelper(eciesAeadHkdfParams0.getDemParams().getAeadDem());
                return new EciesAeadHkdfHybridEncrypt(eCPublicKey0, eciesHkdfKemParams0.getHkdfSalt().toByteArray(), HybridUtil.toHmacAlgo(eciesHkdfKemParams0.getHkdfHashType()), HybridUtil.toPointFormatType(eciesAeadHkdfParams0.getEcPointFormat()), registryEciesAeadHkdfDemHelper0);
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((EciesAeadHkdfPublicKey)messageLite0));
            }
        }};
        super(EciesAeadHkdfPublicKey.class, arr_primitiveFactory);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public EciesAeadHkdfPublicKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return EciesAeadHkdfPublicKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public void validateKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey0) throws GeneralSecurityException {
        Validators.validateVersion(eciesAeadHkdfPublicKey0.getVersion(), 0);
        HybridUtil.validate(eciesAeadHkdfPublicKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((EciesAeadHkdfPublicKey)messageLite0));
    }
}

