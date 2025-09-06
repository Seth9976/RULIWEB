package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

public final class DaggerCollections {
    private static final int MAX_POWER_OF_TWO = 0x40000000;

    private static int calculateInitialCapacity(int v) {
        if(v < 3) {
            return v + 1;
        }
        return v >= 0x40000000 ? 0x7FFFFFFF : ((int)(((float)v) / 0.75f + 1.0f));
    }

    public static boolean hasDuplicates(List list0) {
        return list0.size() >= 2 ? list0.size() != new HashSet(list0).size() : false;
    }

    static HashSet newHashSetWithExpectedSize(int v) {
        return new HashSet(DaggerCollections.calculateInitialCapacity(v));
    }

    public static LinkedHashMap newLinkedHashMapWithExpectedSize(int v) {
        return new LinkedHashMap(DaggerCollections.calculateInitialCapacity(v));
    }

    public static List presizedList(int v) {
        return v == 0 ? Collections.EMPTY_LIST : new ArrayList(v);
    }
}

