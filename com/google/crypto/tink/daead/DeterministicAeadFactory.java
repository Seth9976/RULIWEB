package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeysetHandle;
import java.security.GeneralSecurityException;

@Deprecated
public final class DeterministicAeadFactory {
    @Deprecated
    public static DeterministicAead getPrimitive(KeysetHandle keysetHandle0) throws GeneralSecurityException {
        DeterministicAeadWrapper.register();
        return (DeterministicAead)keysetHandle0.getPrimitive(DeterministicAead.class);
    }
}

