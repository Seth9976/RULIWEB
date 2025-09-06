package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface Aead {
    byte[] decrypt(byte[] arg1, byte[] arg2) throws GeneralSecurityException;

    byte[] encrypt(byte[] arg1, byte[] arg2) throws GeneralSecurityException;
}

