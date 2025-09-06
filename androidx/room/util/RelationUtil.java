package androidx.room.util;

import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001AR\u0010\u0000\u001A\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u00042\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u0007\u001A\u00020\b2\u001E\u0010\t\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u0006\u0012\u0004\u0012\u00020\u00010\n\u001Ar\u0010\u000B\u001A\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u00042\"\u0010\u0005\u001A\u001E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\fj\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0004`\r2\u0006\u0010\u0007\u001A\u00020\b2.\u0010\t\u001A*\u0012 \u0012\u001E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\fj\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0004`\r\u0012\u0004\u0012\u00020\u00010\n\u001A<\u0010\u000E\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00040\u000F2\u0006\u0010\u0007\u001A\u00020\b2\u0018\u0010\t\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000F\u0012\u0004\u0012\u00020\u00010\n¨\u0006\u0010"}, d2 = {"recursiveFetchArrayMap", "", "K", "", "V", "map", "Landroidx/collection/ArrayMap;", "isRelationCollection", "", "fetchBlock", "Lkotlin/Function1;", "recursiveFetchHashMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "recursiveFetchLongSparseArray", "Landroidx/collection/LongSparseArray;", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class RelationUtil {
    public static final void recursiveFetchArrayMap(ArrayMap arrayMap0, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(arrayMap0, "map");
        Intrinsics.checkNotNullParameter(function10, "fetchBlock");
        ArrayMap arrayMap1 = new ArrayMap(999);
        int v = arrayMap0.size();
        int v1 = 0;
    alab1:
        while(true) {
            int v2;
            for(v2 = 0; true; ++v2) {
                if(v1 >= v) {
                    break alab1;
                }
                if(z) {
                    arrayMap1.put(arrayMap0.keyAt(v1), arrayMap0.valueAt(v1));
                }
                else {
                    arrayMap1.put(arrayMap0.keyAt(v1), null);
                }
                ++v1;
                if(v2 + 1 == 999) {
                    function10.invoke(arrayMap1);
                    if(!z) {
                        arrayMap0.putAll(arrayMap1);
                    }
                    arrayMap1.clear();
                    break;
                }
            }
        }
        if(v2 > 0) {
            function10.invoke(arrayMap1);
            if(!z) {
                arrayMap0.putAll(arrayMap1);
            }
        }
    }

    public static final void recursiveFetchHashMap(HashMap hashMap0, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(hashMap0, "map");
        Intrinsics.checkNotNullParameter(function10, "fetchBlock");
        HashMap hashMap1 = new HashMap(999);
        Iterator iterator0 = hashMap0.keySet().iterator();
    alab1:
        while(true) {
            int v;
            for(v = 0; true; ++v) {
                if(!iterator0.hasNext()) {
                    break alab1;
                }
                Object object0 = iterator0.next();
                if(z) {
                    Intrinsics.checkNotNullExpressionValue(object0, "key");
                    hashMap1.put(object0, hashMap0.get(object0));
                }
                else {
                    Intrinsics.checkNotNullExpressionValue(object0, "key");
                    hashMap1.put(object0, null);
                }
                if(v + 1 == 999) {
                    function10.invoke(hashMap1);
                    if(!z) {
                        hashMap0.putAll(hashMap1);
                    }
                    hashMap1.clear();
                    break;
                }
            }
        }
        if(v > 0) {
            function10.invoke(hashMap1);
            if(!z) {
                hashMap0.putAll(hashMap1);
            }
        }
    }

    public static final void recursiveFetchLongSparseArray(LongSparseArray longSparseArray0, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "map");
        Intrinsics.checkNotNullParameter(function10, "fetchBlock");
        LongSparseArray longSparseArray1 = new LongSparseArray(999);
        int v = longSparseArray0.size();
        int v1 = 0;
    alab1:
        while(true) {
            int v2;
            for(v2 = 0; true; ++v2) {
                if(v1 >= v) {
                    break alab1;
                }
                if(z) {
                    longSparseArray1.put(longSparseArray0.keyAt(v1), longSparseArray0.valueAt(v1));
                }
                else {
                    longSparseArray1.put(longSparseArray0.keyAt(v1), null);
                }
                ++v1;
                if(v2 + 1 == 999) {
                    function10.invoke(longSparseArray1);
                    if(!z) {
                        longSparseArray0.putAll(longSparseArray1);
                    }
                    longSparseArray1.clear();
                    break;
                }
            }
        }
        if(v2 > 0) {
            function10.invoke(longSparseArray1);
            if(!z) {
                longSparseArray0.putAll(longSparseArray1);
            }
        }
    }
}

