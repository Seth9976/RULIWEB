package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public interface JwtMac {
    String computeMacAndEncode(RawJwt arg1) throws GeneralSecurityException;

    VerifiedJwt verifyMacAndDecode(String arg1, JwtValidator arg2) throws GeneralSecurityException;
}

