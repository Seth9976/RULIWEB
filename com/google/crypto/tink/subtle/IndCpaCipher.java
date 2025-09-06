package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;

public interface IndCpaCipher {
    byte[] decrypt(byte[] arg1) throws GeneralSecurityException;

    byte[] encrypt(byte[] arg1) throws GeneralSecurityException;
}

