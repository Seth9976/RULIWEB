package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Mac;
import java.security.GeneralSecurityException;

@Deprecated
public final class MacFactory {
    @Deprecated
    public static Mac getPrimitive(KeysetHandle keysetHandle0) throws GeneralSecurityException {
        MacWrapper.register();
        return (Mac)keysetHandle0.getPrimitive(Mac.class);
    }
}

