package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeysetHandle;
import java.security.GeneralSecurityException;

@Deprecated
public final class HybridDecryptFactory {
    @Deprecated
    public static HybridDecrypt getPrimitive(KeysetHandle keysetHandle0) throws GeneralSecurityException {
        HybridDecryptWrapper.register();
        return (HybridDecrypt)keysetHandle0.getPrimitive(HybridDecrypt.class);
    }
}

