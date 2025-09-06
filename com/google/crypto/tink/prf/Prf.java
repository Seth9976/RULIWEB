package com.google.crypto.tink.prf;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public interface Prf {
    byte[] compute(byte[] arg1, int arg2) throws GeneralSecurityException;
}

