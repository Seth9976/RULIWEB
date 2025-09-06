package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Immutable
public interface JwtPublicKeyVerifyInternal {
    VerifiedJwt verifyAndDecodeWithKid(String arg1, JwtValidator arg2, Optional arg3) throws GeneralSecurityException;
}

