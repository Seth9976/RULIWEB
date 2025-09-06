package com.google.crypto.tink;

import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

@CheckReturnValue
@Immutable
public final class SecretKeyAccess {
    private static final SecretKeyAccess INSTANCE;

    static {
        SecretKeyAccess.INSTANCE = new SecretKeyAccess();
    }

    static SecretKeyAccess instance() {
        return SecretKeyAccess.INSTANCE;
    }

    public static SecretKeyAccess requireAccess(@Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(secretKeyAccess0 == null) {
            throw new GeneralSecurityException("SecretKeyAccess is required");
        }
        return secretKeyAccess0;
    }
}

