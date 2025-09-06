package com.google.crypto.tink.prf;

import com.google.crypto.tink.config.TinkFips;
import java.security.GeneralSecurityException;

public final class PrfConfig {
    public static final String PRF_TYPE_URL;

    static {
        PrfConfig.PRF_TYPE_URL = "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    }

    public static void register() throws GeneralSecurityException {
        PrfSetWrapper.register();
        HmacPrfKeyManager.register(true);
        if(TinkFips.useOnlyFips()) {
            return;
        }
        AesCmacPrfKeyManager.register(true);
        HkdfPrfKeyManager.register(true);
    }
}

