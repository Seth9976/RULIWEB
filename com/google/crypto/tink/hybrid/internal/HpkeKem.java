package com.google.crypto.tink.hybrid.internal;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
interface HpkeKem {
    byte[] decapsulate(byte[] arg1, HpkeKemPrivateKey arg2) throws GeneralSecurityException;

    HpkeKemEncapOutput encapsulate(byte[] arg1) throws GeneralSecurityException;

    byte[] getKemId() throws GeneralSecurityException;
}

