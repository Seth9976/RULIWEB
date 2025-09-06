package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface HybridEncrypt {
    byte[] encrypt(byte[] arg1, byte[] arg2) throws GeneralSecurityException;
}

