package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
public interface JwtPublicKeyVerify {
    VerifiedJwt verifyAndDecode(String arg1, JwtValidator arg2) throws GeneralSecurityException;
}

