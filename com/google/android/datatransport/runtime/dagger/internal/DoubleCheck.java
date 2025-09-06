package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;

public final class DoubleCheck implements Lazy, Provider {
    static final boolean $assertionsDisabled;
    private static final Object UNINITIALIZED;
    private volatile Object instance;
    private volatile Provider provider;

    static {
        DoubleCheck.UNINITIALIZED = new Object();
    }

    private DoubleCheck(Provider provider0) {
        this.instance = DoubleCheck.UNINITIALIZED;
        this.provider = provider0;
    }

    @Override  // javax.inject.Provider, com.google.android.datatransport.runtime.dagger.Lazy
    public Object get() {
        Object object0 = this.instance;
        Object object1 = DoubleCheck.UNINITIALIZED;
        if(object0 == object1) {
            synchronized(this) {
                Object object2 = this.instance;
                if(object2 == object1) {
                    object2 = this.provider.get();
                    this.instance = DoubleCheck.reentrantCheck(this.instance, object2);
                    this.provider = null;
                }
                return object2;
            }
        }
        return object0;
    }

    public static Lazy lazy(Provider provider0) {
        return provider0 instanceof Lazy ? ((Lazy)provider0) : new DoubleCheck(((Provider)Preconditions.checkNotNull(provider0)));
    }

    public static Provider provider(Provider provider0) {
        Preconditions.checkNotNull(provider0);
        return provider0 instanceof DoubleCheck ? provider0 : new DoubleCheck(provider0);
    }

    public static Object reentrantCheck(Object object0, Object object1) {
        if(object0 != DoubleCheck.UNINITIALIZED && !(object0 instanceof MemoizedSentinel) && object0 != object1) {
            throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + object0 + " & " + object1 + ". This is likely due to a circular dependency.");
        }
        return object1;
    }
}

