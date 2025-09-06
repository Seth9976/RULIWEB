package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface KmsClient {
    boolean doesSupport(String arg1);

    Aead getAead(String arg1) throws GeneralSecurityException;

    KmsClient withCredentials(String arg1) throws GeneralSecurityException;

    KmsClient withDefaultCredentials() throws GeneralSecurityException;
}

