package com.google.crypto.tink;

import com.google.errorprone.annotations.Immutable;
import javax.annotation.Nullable;

@Immutable
public abstract class Key {
    public abstract boolean equalsKey(Key arg1);

    @Nullable
    public abstract Integer getIdRequirementOrNull();

    public abstract Parameters getParameters();
}

