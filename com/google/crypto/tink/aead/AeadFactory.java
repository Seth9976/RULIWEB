package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import java.security.GeneralSecurityException;

@Deprecated
public final class AeadFactory {
    @Deprecated
    public static Aead getPrimitive(KeysetHandle keysetHandle0) throws GeneralSecurityException {
        AeadWrapper.register();
        return (Aead)keysetHandle0.getPrimitive(Aead.class);
    }
}

