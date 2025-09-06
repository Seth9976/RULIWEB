package com.google.crypto.tink;

import java.security.GeneralSecurityException;

@Deprecated
public interface Catalogue {
    KeyManager getKeyManager(String arg1, String arg2, int arg3) throws GeneralSecurityException;

    PrimitiveWrapper getPrimitiveWrapper() throws GeneralSecurityException;
}

