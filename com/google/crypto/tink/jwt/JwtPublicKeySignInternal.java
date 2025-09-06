package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Immutable
public interface JwtPublicKeySignInternal {
    String signAndEncodeWithKid(RawJwt arg1, Optional arg2) throws GeneralSecurityException;
}

