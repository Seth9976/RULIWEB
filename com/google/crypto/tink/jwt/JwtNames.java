package com.google.crypto.tink.jwt;

final class JwtNames {
    static final String CLAIM_AUDIENCE = "aud";
    static final String CLAIM_EXPIRATION = "exp";
    static final String CLAIM_ISSUED_AT = "iat";
    static final String CLAIM_ISSUER = "iss";
    static final String CLAIM_JWT_ID = "jti";
    static final String CLAIM_NOT_BEFORE = "nbf";
    static final String CLAIM_SUBJECT = "sub";
    static final String HEADER_ALGORITHM = "alg";
    static final String HEADER_CRITICAL = "crit";
    static final String HEADER_KEY_ID = "kid";
    static final String HEADER_TYPE = "typ";

    // 去混淆评级： 中等(70)
    static boolean isRegisteredName(String s) {
        return s.equals("iss") || s.equals("sub") || s.equals("aud") || s.equals("exp") || s.equals("nbf") || s.equals("iat") || s.equals("jti");
    }

    static void validate(String s) {
        if(JwtNames.isRegisteredName(s)) {
            throw new IllegalArgumentException(String.format("claim \'%s\' is invalid because it\'s a registered name; use the corresponding setter method.", s));
        }
    }
}

