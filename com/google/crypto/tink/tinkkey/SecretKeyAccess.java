package com.google.crypto.tink.tinkkey;

import com.google.errorprone.annotations.Immutable;

@Immutable
@Deprecated
public final class SecretKeyAccess {
    public static KeyAccess insecureSecretAccess() {
        return KeyAccess.secretAccess();
    }
}

