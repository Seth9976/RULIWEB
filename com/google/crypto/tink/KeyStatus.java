package com.google.crypto.tink;

import com.google.errorprone.annotations.Immutable;

@Immutable
public final class KeyStatus {
    public static final KeyStatus DESTROYED;
    public static final KeyStatus DISABLED;
    public static final KeyStatus ENABLED;
    private final String name;

    static {
        KeyStatus.ENABLED = new KeyStatus("ENABLED");
        KeyStatus.DISABLED = new KeyStatus("DISABLED");
        KeyStatus.DESTROYED = new KeyStatus("DESTROYED");
    }

    private KeyStatus(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

