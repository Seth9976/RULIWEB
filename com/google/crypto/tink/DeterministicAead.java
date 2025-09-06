package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface DeterministicAead {
    byte[] decryptDeterministically(byte[] arg1, byte[] arg2) throws GeneralSecurityException;

    byte[] encryptDeterministically(byte[] arg1, byte[] arg2) throws GeneralSecurityException;
}

