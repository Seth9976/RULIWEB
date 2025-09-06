package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class LazySet implements Provider {
    private volatile Set actualSet;
    private volatile Set providers;

    LazySet(Collection collection0) {
        this.actualSet = null;
        this.providers = Collections.newSetFromMap(new ConcurrentHashMap());
        this.providers.addAll(collection0);
    }

    void add(Provider provider0) {
        synchronized(this) {
            if(this.actualSet == null) {
                this.providers.add(provider0);
            }
            else {
                this.actualSet.add(provider0.get());
            }
        }
    }

    static LazySet fromCollection(Collection collection0) {
        return new LazySet(((Set)collection0));
    }

    @Override  // com.google.firebase.inject.Provider
    public Object get() {
        return this.get();
    }

    public Set get() {
        if(this.actualSet == null) {
            synchronized(this) {
                if(this.actualSet == null) {
                    this.actualSet = Collections.newSetFromMap(new ConcurrentHashMap());
                    this.updateSet();
                }
            }
            return Collections.unmodifiableSet(this.actualSet);
        }
        return Collections.unmodifiableSet(this.actualSet);
    }

    private void updateSet() {
        synchronized(this) {
            for(Object object0: this.providers) {
                this.actualSet.add(((Provider)object0).get());
            }
            this.providers = null;
        }
    }
}

