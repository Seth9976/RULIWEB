package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface KeyWrap {
    byte[] unwrap(byte[] arg1) throws GeneralSecurityException;

    byte[] wrap(byte[] arg1) throws GeneralSecurityException;
}

