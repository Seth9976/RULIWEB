package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface Mac {
    byte[] computeMac(byte[] arg1) throws GeneralSecurityException;

    void verifyMac(byte[] arg1, byte[] arg2) throws GeneralSecurityException;
}

