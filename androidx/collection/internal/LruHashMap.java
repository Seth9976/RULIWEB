package androidx.collection.internal;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B\u001D\b\u0016\u0012\u0014\u0010\u0004\u001A\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u0002\u0010\u0005B\u0019\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u0016\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0017\u001A\u00028\u0000H\u0086\u0002¢\u0006\u0002\u0010\u0018J\u001D\u0010\u0019\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0017\u001A\u00028\u00002\u0006\u0010\u001A\u001A\u00028\u0001¢\u0006\u0002\u0010\u001BJ\u0015\u0010\u001C\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0017\u001A\u00028\u0000¢\u0006\u0002\u0010\u0018R#\u0010\u000B\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\r0\f8F¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FR\u0011\u0010\u0010\u001A\u00020\u00118F¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0012R*\u0010\u0013\u001A\u001E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014j\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001D"}, d2 = {"Landroidx/collection/internal/LruHashMap;", "K", "", "V", "original", "(Landroidx/collection/internal/LruHashMap;)V", "initialCapacity", "", "loadFactor", "", "(IF)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "isEmpty", "", "()Z", "map", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "get", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class LruHashMap {
    private final LinkedHashMap map;

    public LruHashMap() {
        this(0, 0.0f, 3, null);
    }

    public LruHashMap(int v, float f) {
        this.map = new LinkedHashMap(v, f, true);
    }

    public LruHashMap(int v, float f, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 16;
        }
        if((v1 & 2) != 0) {
            f = 0.75f;
        }
        this(v, f);
    }

    public LruHashMap(LruHashMap lruHashMap0) {
        Intrinsics.checkNotNullParameter(lruHashMap0, "original");
        this(0, 0.0f, 3, null);
        for(Object object0: lruHashMap0.getEntries()) {
            this.put(((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue());
        }
    }

    public final Object get(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "key");
        return this.map.get(object0);
    }

    public final Set getEntries() {
        Set set0 = this.map.entrySet();
        Intrinsics.checkNotNullExpressionValue(set0, "map.entries");
        return set0;
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    public final Object put(Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(object0, "key");
        Intrinsics.checkNotNullParameter(object1, "value");
        return this.map.put(object0, object1);
    }

    public final Object remove(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "key");
        return this.map.remove(object0);
    }
}

