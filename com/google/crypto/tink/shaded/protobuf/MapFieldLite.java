package com.google.crypto.tink.shaded.protobuf;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public final class MapFieldLite extends LinkedHashMap {
    private static final MapFieldLite EMPTY_MAP_FIELD;
    private boolean isMutable;

    static {
        MapFieldLite mapFieldLite0 = new MapFieldLite();
        MapFieldLite.EMPTY_MAP_FIELD = mapFieldLite0;
        mapFieldLite0.makeImmutable();
    }

    private MapFieldLite() {
        this.isMutable = true;
    }

    private MapFieldLite(Map map0) {
        super(map0);
        this.isMutable = true;
    }

    static int calculateHashCodeForMap(Map map0) {
        int v = 0;
        for(Object object0: map0.entrySet()) {
            int v1 = MapFieldLite.calculateHashCodeForObject(((Map.Entry)object0).getKey());
            v += MapFieldLite.calculateHashCodeForObject(((Map.Entry)object0).getValue()) ^ v1;
        }
        return v;
    }

    private static int calculateHashCodeForObject(Object object0) {
        if(object0 instanceof byte[]) {
            return Internal.hashCode(((byte[])object0));
        }
        if(object0 instanceof EnumLite) {
            throw new UnsupportedOperationException();
        }
        return object0.hashCode();
    }

    private static void checkForNullKeysAndValues(Map map0) {
        for(Object object0: map0.keySet()) {
            Internal.checkNotNull(object0);
            Internal.checkNotNull(map0.get(object0));
        }
    }

    @Override
    public void clear() {
        this.ensureMutable();
        super.clear();
    }

    private static Object copy(Object object0) {
        return object0 instanceof byte[] ? Arrays.copyOf(((byte[])object0), ((byte[])object0).length) : object0;
    }

    static Map copy(Map map0) {
        Map map1 = new LinkedHashMap();
        for(Object object0: map0.entrySet()) {
            map1.put(((Map.Entry)object0).getKey(), MapFieldLite.copy(((Map.Entry)object0).getValue()));
        }
        return map1;
    }

    public static MapFieldLite emptyMapField() {
        return MapFieldLite.EMPTY_MAP_FIELD;
    }

    private void ensureMutable() {
        if(!this.isMutable()) {
            throw new UnsupportedOperationException();
        }
    }

    // 去混淆评级： 低(20)
    @Override
    public Set entrySet() {
        return this.isEmpty() ? Collections.EMPTY_SET : super.entrySet();
    }

    // 去混淆评级： 低(20)
    private static boolean equals(Object object0, Object object1) {
        return !(object0 instanceof byte[]) || !(object1 instanceof byte[]) ? object0.equals(object1) : Arrays.equals(((byte[])object0), ((byte[])object1));
    }

    static boolean equals(Map map0, Map map1) {
        if(map0 == map1) {
            return true;
        }
        if(map0.size() != map1.size()) {
            return false;
        }
        for(Object object0: map0.entrySet()) {
            if(!map1.containsKey(((Map.Entry)object0).getKey())) {
                return false;
            }
            if(!MapFieldLite.equals(((Map.Entry)object0).getValue(), map1.get(((Map.Entry)object0).getKey()))) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Map && MapFieldLite.equals(this, ((Map)object0));
    }

    @Override
    public int hashCode() {
        return MapFieldLite.calculateHashCodeForMap(this);
    }

    public boolean isMutable() {
        return this.isMutable;
    }

    public void makeImmutable() {
        this.isMutable = false;
    }

    public void mergeFrom(MapFieldLite mapFieldLite0) {
        this.ensureMutable();
        if(!mapFieldLite0.isEmpty()) {
            this.putAll(mapFieldLite0);
        }
    }

    // 去混淆评级： 低(20)
    public MapFieldLite mutableCopy() {
        return this.isEmpty() ? new MapFieldLite() : new MapFieldLite(this);
    }

    @Override
    public Object put(Object object0, Object object1) {
        this.ensureMutable();
        Internal.checkNotNull(object0);
        Internal.checkNotNull(object1);
        return super.put(object0, object1);
    }

    public Object put(Map.Entry map$Entry0) {
        return this.put(map$Entry0.getKey(), map$Entry0.getValue());
    }

    @Override
    public void putAll(Map map0) {
        this.ensureMutable();
        MapFieldLite.checkForNullKeysAndValues(map0);
        super.putAll(map0);
    }

    @Override
    public Object remove(Object object0) {
        this.ensureMutable();
        return super.remove(object0);
    }
}

