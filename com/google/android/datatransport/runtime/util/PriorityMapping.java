package com.google.android.datatransport.runtime.util;

import android.util.SparseArray;
import com.google.android.datatransport.Priority;
import java.util.HashMap;

public final class PriorityMapping {
    private static HashMap PRIORITY_INT_MAP;
    private static SparseArray PRIORITY_MAP;

    static {
        PriorityMapping.PRIORITY_MAP = new SparseArray();
        HashMap hashMap0 = new HashMap();
        PriorityMapping.PRIORITY_INT_MAP = hashMap0;
        hashMap0.put(Priority.DEFAULT, 0);
        PriorityMapping.PRIORITY_INT_MAP.put(Priority.VERY_LOW, 1);
        PriorityMapping.PRIORITY_INT_MAP.put(Priority.HIGHEST, 2);
        for(Object object0: PriorityMapping.PRIORITY_INT_MAP.keySet()) {
            PriorityMapping.PRIORITY_MAP.append(((int)(((Integer)PriorityMapping.PRIORITY_INT_MAP.get(((Priority)object0))))), ((Priority)object0));
        }
    }

    public static int toInt(Priority priority0) {
        Integer integer0 = (Integer)PriorityMapping.PRIORITY_INT_MAP.get(priority0);
        if(integer0 == null) {
            throw new IllegalStateException("PriorityMapping is missing known Priority value " + priority0);
        }
        return (int)integer0;
    }

    public static Priority valueOf(int v) {
        Priority priority0 = (Priority)PriorityMapping.PRIORITY_MAP.get(v);
        if(priority0 == null) {
            throw new IllegalArgumentException("Unknown Priority for value " + v);
        }
        return priority0;
    }
}

