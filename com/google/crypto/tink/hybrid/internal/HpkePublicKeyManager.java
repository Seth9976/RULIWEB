package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

public final class HpkePublicKeyManager extends KeyTypeManager {
    public HpkePublicKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public HybridEncrypt getPrimitive(HpkePublicKey hpkePublicKey0) throws GeneralSecurityException {
                return HpkeEncrypt.createHpkeEncrypt(hpkePublicKey0);
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((HpkePublicKey)messageLite0));
            }
        }};
        super(HpkePublicKey.class, arr_primitiveFactory);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HpkePublicKey";
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public HpkePublicKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return HpkePublicKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public void validateKey(HpkePublicKey hpkePublicKey0) throws GeneralSecurityException {
        Validators.validateVersion(hpkePublicKey0.getVersion(), 0);
        if(!hpkePublicKey0.hasParams()) {
            throw new GeneralSecurityException("Missing HPKE key params.");
        }
        HpkeUtil.validateParams(hpkePublicKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((HpkePublicKey)messageLite0));
    }
}

