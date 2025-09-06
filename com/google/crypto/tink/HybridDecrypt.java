package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface HybridDecrypt {
    byte[] decrypt(byte[] arg1, byte[] arg2) throws GeneralSecurityException;
}

