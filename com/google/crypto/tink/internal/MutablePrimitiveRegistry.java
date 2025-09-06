package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;

public final class MutablePrimitiveRegistry {
    private static MutablePrimitiveRegistry globalInstance;
    private final AtomicReference registry;

    static {
        MutablePrimitiveRegistry.globalInstance = new MutablePrimitiveRegistry();
    }

    MutablePrimitiveRegistry() {
        this.registry = new AtomicReference(new Builder().build());
    }

    public Class getInputPrimitiveClass(Class class0) throws GeneralSecurityException {
        return ((PrimitiveRegistry)this.registry.get()).getInputPrimitiveClass(class0);
    }

    public Object getPrimitive(Key key0, Class class0) throws GeneralSecurityException {
        return ((PrimitiveRegistry)this.registry.get()).getPrimitive(key0, class0);
    }

    public static MutablePrimitiveRegistry globalInstance() {
        return MutablePrimitiveRegistry.globalInstance;
    }

    public void registerPrimitiveConstructor(PrimitiveConstructor primitiveConstructor0) throws GeneralSecurityException {
        synchronized(this) {
            PrimitiveRegistry primitiveRegistry0 = new Builder(((PrimitiveRegistry)this.registry.get())).registerPrimitiveConstructor(primitiveConstructor0).build();
            this.registry.set(primitiveRegistry0);
        }
    }

    public void registerPrimitiveWrapper(PrimitiveWrapper primitiveWrapper0) throws GeneralSecurityException {
        synchronized(this) {
            PrimitiveRegistry primitiveRegistry0 = new Builder(((PrimitiveRegistry)this.registry.get())).registerPrimitiveWrapper(primitiveWrapper0).build();
            this.registry.set(primitiveRegistry0);
        }
    }

    public static void resetGlobalInstanceTestOnly() {
        MutablePrimitiveRegistry.globalInstance = new MutablePrimitiveRegistry();
    }

    public Object wrap(PrimitiveSet primitiveSet0, Class class0) throws GeneralSecurityException {
        return ((PrimitiveRegistry)this.registry.get()).wrap(primitiveSet0, class0);
    }
}

