package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PublicKeyVerify;
import java.security.GeneralSecurityException;

@Deprecated
public final class PublicKeyVerifyFactory {
    @Deprecated
    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle0) throws GeneralSecurityException {
        PublicKeyVerifyWrapper.register();
        return (PublicKeyVerify)keysetHandle0.getPrimitive(PublicKeyVerify.class);
    }
}

