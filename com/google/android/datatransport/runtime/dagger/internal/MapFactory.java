package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import javax.inject.Provider;

public final class MapFactory extends AbstractMapFactory {
    public static final class Builder extends com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder {
        private Builder(int v) {
            super(v);
        }

        Builder(int v, com.google.android.datatransport.runtime.dagger.internal.MapFactory.1 mapFactory$10) {
            this(v);
        }

        public MapFactory build() {
            return new MapFactory(this.map, null);
        }

        @Override  // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory$Builder
        public com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder put(Object object0, Provider provider0) {
            return this.put(object0, provider0);
        }

        public Builder put(Object object0, Provider provider0) {
            super.put(object0, provider0);
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory$Builder
        public com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder putAll(Provider provider0) {
            return this.putAll(provider0);
        }

        public Builder putAll(Provider provider0) {
            super.putAll(provider0);
            return this;
        }
    }

    private static final Provider EMPTY;

    static {
        MapFactory.EMPTY = InstanceFactory.create(Collections.EMPTY_MAP);
    }

    private MapFactory(Map map0) {
        super(map0);
    }

    MapFactory(Map map0, com.google.android.datatransport.runtime.dagger.internal.MapFactory.1 mapFactory$10) {
        this(map0);
    }

    public static Builder builder(int v) {
        return new Builder(v, null);
    }

    public static Provider emptyMapProvider() {
        return MapFactory.EMPTY;
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public Map get() {
        LinkedHashMap linkedHashMap0 = DaggerCollections.newLinkedHashMapWithExpectedSize(this.contributingMap().size());
        for(Object object0: this.contributingMap().entrySet()) {
            linkedHashMap0.put(((Map.Entry)object0).getKey(), ((Provider)((Map.Entry)object0).getValue()).get());
        }
        return Collections.unmodifiableMap(linkedHashMap0);
    }

    class com.google.android.datatransport.runtime.dagger.internal.MapFactory.1 {
    }

}

