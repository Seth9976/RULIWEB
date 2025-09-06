package com.google.firebase.platforminfo;

import javax.annotation.Nonnull;

final class AutoValue_LibraryVersion extends LibraryVersion {
    private final String libraryName;
    private final String version;

    AutoValue_LibraryVersion(String s, String s1) {
        if(s == null) {
            throw new NullPointerException("Null libraryName");
        }
        this.libraryName = s;
        if(s1 == null) {
            throw new NullPointerException("Null version");
        }
        this.version = s1;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof LibraryVersion) {
            String s = ((LibraryVersion)object0).getLibraryName();
            if(this.libraryName.equals(s)) {
                String s1 = ((LibraryVersion)object0).getVersion();
                return this.version.equals(s1);
            }
        }
        return false;
    }

    @Override  // com.google.firebase.platforminfo.LibraryVersion
    @Nonnull
    public String getLibraryName() {
        return this.libraryName;
    }

    @Override  // com.google.firebase.platforminfo.LibraryVersion
    @Nonnull
    public String getVersion() {
        return this.version;
    }

    @Override
    public int hashCode() {
        return (this.libraryName.hashCode() ^ 1000003) * 1000003 ^ this.version.hashCode();
    }

    @Override
    public String toString() {
        return "LibraryVersion{libraryName=" + this.libraryName + ", version=" + this.version + "}";
    }
}

