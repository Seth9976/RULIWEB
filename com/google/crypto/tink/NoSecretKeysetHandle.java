package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Deprecated
public final class NoSecretKeysetHandle {
    @Deprecated
    public static final KeysetHandle parseFrom(byte[] arr_b) throws GeneralSecurityException {
        try {
            Keyset keyset0 = Keyset.parseFrom(arr_b, ExtensionRegistryLite.getEmptyRegistry());
            NoSecretKeysetHandle.validate(keyset0);
            return KeysetHandle.fromKeyset(keyset0);
        }
        catch(InvalidProtocolBufferException unused_ex) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static final KeysetHandle read(KeysetReader keysetReader0) throws GeneralSecurityException, IOException {
        Keyset keyset0 = keysetReader0.read();
        NoSecretKeysetHandle.validate(keyset0);
        return KeysetHandle.fromKeyset(keyset0);
    }

    private static void validate(Keyset keyset0) throws GeneralSecurityException {
        for(Object object0: keyset0.getKeyList()) {
            if(((Key)object0).getKeyData().getKeyMaterialType() == KeyMaterialType.UNKNOWN_KEYMATERIAL || ((Key)object0).getKeyData().getKeyMaterialType() == KeyMaterialType.SYMMETRIC || ((Key)object0).getKeyData().getKeyMaterialType() == KeyMaterialType.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException("keyset contains secret key material");
            }
            if(false) {
                break;
            }
        }
    }
}

