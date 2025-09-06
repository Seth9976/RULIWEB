package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Immutable
interface JwtMacInternal {
    String computeMacAndEncodeWithKid(RawJwt arg1, Optional arg2) throws GeneralSecurityException;

    VerifiedJwt verifyMacAndDecodeWithKid(String arg1, JwtValidator arg2, Optional arg3) throws GeneralSecurityException;
}

