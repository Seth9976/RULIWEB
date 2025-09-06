package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface PublicKeyVerify {
    void verify(byte[] arg1, byte[] arg2) throws GeneralSecurityException;
}

