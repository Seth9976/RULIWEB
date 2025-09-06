package com.google.firebase.platforminfo;

import javax.annotation.Nonnull;

abstract class LibraryVersion {
    static LibraryVersion create(String s, String s1) {
        return new AutoValue_LibraryVersion(s, s1);
    }

    @Nonnull
    public abstract String getLibraryName();

    @Nonnull
    public abstract String getVersion();
}

