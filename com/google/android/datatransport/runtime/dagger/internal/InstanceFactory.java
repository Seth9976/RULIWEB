package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;

public final class InstanceFactory implements Lazy, Factory {
    private static final InstanceFactory NULL_INSTANCE_FACTORY;
    private final Object instance;

    static {
        InstanceFactory.NULL_INSTANCE_FACTORY = new InstanceFactory(null);
    }

    private InstanceFactory(Object object0) {
        this.instance = object0;
    }

    public static Factory create(Object object0) {
        return new InstanceFactory(Preconditions.checkNotNull(object0, "instance cannot be null"));
    }

    public static Factory createNullable(Object object0) {
        return object0 == null ? InstanceFactory.nullInstanceFactory() : new InstanceFactory(object0);
    }

    @Override  // com.google.android.datatransport.runtime.dagger.Lazy, javax.inject.Provider
    public Object get() {
        return this.instance;
    }

    private static InstanceFactory nullInstanceFactory() {
        return InstanceFactory.NULL_INSTANCE_FACTORY;
    }
}

