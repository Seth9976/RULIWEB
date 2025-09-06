package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Collections;
import java.util.Map;

public final class MapBuilder {
    private final Map contributions;

    private MapBuilder(int v) {
        this.contributions = DaggerCollections.newLinkedHashMapWithExpectedSize(v);
    }

    public Map build() {
        return this.contributions.size() == 0 ? Collections.EMPTY_MAP : Collections.unmodifiableMap(this.contributions);
    }

    public static MapBuilder newMapBuilder(int v) {
        return new MapBuilder(v);
    }

    public MapBuilder put(Object object0, Object object1) {
        this.contributions.put(object0, object1);
        return this;
    }

    public MapBuilder putAll(Map map0) {
        this.contributions.putAll(map0);
        return this;
    }
}

