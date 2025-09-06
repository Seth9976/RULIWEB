package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;

abstract class AbstractMapFactory implements Factory {
    public static abstract class Builder {
        final LinkedHashMap map;

        Builder(int v) {
            this.map = DaggerCollections.newLinkedHashMapWithExpectedSize(v);
        }

        Builder put(Object object0, Provider provider0) {
            Object object1 = Preconditions.checkNotNull(object0, "key");
            Object object2 = Preconditions.checkNotNull(provider0, "provider");
            this.map.put(object1, object2);
            return this;
        }

        Builder putAll(Provider provider0) {
            if(provider0 instanceof DelegateFactory) {
                return this.putAll(((DelegateFactory)provider0).getDelegate());
            }
            Map map0 = ((AbstractMapFactory)provider0).contributingMap;
            this.map.putAll(map0);
            return this;
        }
    }

    private final Map contributingMap;

    AbstractMapFactory(Map map0) {
        this.contributingMap = Collections.unmodifiableMap(map0);
    }

    final Map contributingMap() {
        return this.contributingMap;
    }
}

