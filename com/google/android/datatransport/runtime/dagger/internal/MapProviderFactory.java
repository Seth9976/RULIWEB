package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import java.util.Map;
import javax.inject.Provider;

public final class MapProviderFactory extends AbstractMapFactory implements Lazy {
    public static final class Builder extends com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory.Builder {
        private Builder(int v) {
            super(v);
        }

        Builder(int v, com.google.android.datatransport.runtime.dagger.internal.MapProviderFactory.1 mapProviderFactory$10) {
            this(v);
        }

        public MapProviderFactory build() {
            return new MapProviderFactory(this.map, null);
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

    private MapProviderFactory(Map map0) {
        super(map0);
    }

    MapProviderFactory(Map map0, com.google.android.datatransport.runtime.dagger.internal.MapProviderFactory.1 mapProviderFactory$10) {
        this(map0);
    }

    public static Builder builder(int v) {
        return new Builder(v, null);
    }

    @Override  // com.google.android.datatransport.runtime.dagger.Lazy, javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public Map get() {
        return this.contributingMap();
    }

    class com.google.android.datatransport.runtime.dagger.internal.MapProviderFactory.1 {
    }

}

