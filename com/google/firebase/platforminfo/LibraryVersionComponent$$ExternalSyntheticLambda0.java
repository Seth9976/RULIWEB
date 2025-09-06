package com.google.firebase.platforminfo;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

public final class LibraryVersionComponent..ExternalSyntheticLambda0 implements ComponentFactory {
    public final String f$0;
    public final VersionExtractor f$1;

    public LibraryVersionComponent..ExternalSyntheticLambda0(String s, VersionExtractor libraryVersionComponent$VersionExtractor0) {
        this.f$0 = s;
        this.f$1 = libraryVersionComponent$VersionExtractor0;
    }

    @Override  // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer0) {
        return LibraryVersionComponent.lambda$fromContext$0(this.f$0, this.f$1, componentContainer0);
    }
}

