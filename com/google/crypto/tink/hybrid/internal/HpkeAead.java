package com.google.crypto.tink.hybrid.internal;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
interface HpkeAead {
    byte[] getAeadId() throws GeneralSecurityException;

    int getKeyLength();

    int getNonceLength();

    byte[] open(byte[] arg1, byte[] arg2, byte[] arg3, byte[] arg4) throws GeneralSecurityException;

    byte[] seal(byte[] arg1, byte[] arg2, byte[] arg3, byte[] arg4) throws GeneralSecurityException;
}

