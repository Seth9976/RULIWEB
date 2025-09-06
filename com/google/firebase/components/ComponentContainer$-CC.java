package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.Set;

public final class ComponentContainer.-CC {
    public static Object $default$get(ComponentContainer _this, Qualified qualified0) {
        Provider provider0 = _this.getProvider(qualified0);
        return provider0 == null ? null : provider0.get();
    }

    public static Object $default$get(ComponentContainer _this, Class class0) {
        return _this.get(Qualified.unqualified(class0));
    }

    public static Deferred $default$getDeferred(ComponentContainer _this, Class class0) {
        return _this.getDeferred(Qualified.unqualified(class0));
    }

    public static Provider $default$getProvider(ComponentContainer _this, Class class0) {
        return _this.getProvider(Qualified.unqualified(class0));
    }

    public static Set $default$setOf(ComponentContainer _this, Qualified qualified0) {
        return (Set)_this.setOfProvider(qualified0).get();
    }

    public static Set $default$setOf(ComponentContainer _this, Class class0) {
        return _this.setOf(Qualified.unqualified(class0));
    }

    public static Provider $default$setOfProvider(ComponentContainer _this, Class class0) {
        return _this.setOfProvider(Qualified.unqualified(class0));
    }
}

