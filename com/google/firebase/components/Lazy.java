package com.google.firebase.components;

import com.google.firebase.inject.Provider;

public class Lazy implements Provider {
    private static final Object UNINITIALIZED;
    private volatile Object instance;
    private volatile Provider provider;

    static {
        Lazy.UNINITIALIZED = new Object();
    }

    public Lazy(Provider provider0) {
        this.instance = Lazy.UNINITIALIZED;
        this.provider = provider0;
    }

    Lazy(Object object0) {
        this.instance = object0;
    }

    @Override  // com.google.firebase.inject.Provider
    public Object get() {
        Object object0 = this.instance;
        Object object1 = Lazy.UNINITIALIZED;
        if(object0 == object1) {
            synchronized(this) {
                Object object2 = this.instance;
                if(object2 == object1) {
                    object2 = this.provider.get();
                    this.instance = object2;
                    this.provider = null;
                }
                return object2;
            }
        }
        return object0;
    }

    boolean isInitialized() {
        return this.instance != Lazy.UNINITIALIZED;
    }
}

