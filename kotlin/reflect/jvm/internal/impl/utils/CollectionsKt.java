package kotlin.reflect.jvm.internal.impl.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class CollectionsKt {
    public static final void addIfNotNull(Collection collection0, Object object0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        if(object0 != null) {
            collection0.add(object0);
        }
    }

    private static final int capacity(int v) {
        return v >= 3 ? v + v / 3 + 1 : 3;
    }

    public static final List compact(ArrayList arrayList0) {
        Intrinsics.checkNotNullParameter(arrayList0, "<this>");
        switch(arrayList0.size()) {
            case 0: {
                return kotlin.collections.CollectionsKt.emptyList();
            }
            case 1: {
                return kotlin.collections.CollectionsKt.listOf(kotlin.collections.CollectionsKt.first(arrayList0));
            }
            default: {
                arrayList0.trimToSize();
                return arrayList0;
            }
        }
    }

    public static final Map mapToIndex(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        LinkedHashMap linkedHashMap0 = new LinkedHashMap();
        int v = 0;
        for(Object object0: iterable0) {
            linkedHashMap0.put(object0, v);
            ++v;
        }
        return linkedHashMap0;
    }

    public static final HashMap newHashMapWithExpectedSize(int v) {
        return new HashMap(CollectionsKt.capacity(v));
    }

    public static final HashSet newHashSetWithExpectedSize(int v) {
        return new HashSet(CollectionsKt.capacity(v));
    }

    public static final LinkedHashSet newLinkedHashSetWithExpectedSize(int v) {
        return new LinkedHashSet(CollectionsKt.capacity(v));
    }
}

