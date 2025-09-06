package androidx.collection;

import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010&\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001A\u00028\u0000\u0012\u0006\u0010\u0005\u001A\u00028\u0001¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0004\u001A\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001A\u0004\b\u0007\u0010\bR\u0016\u0010\u0005\u001A\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001A\u0004\b\n\u0010\b¨\u0006\u000B"}, d2 = {"Landroidx/collection/MapEntry;", "K", "V", "", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class MapEntry implements Map.Entry, KMappedMarker {
    private final Object key;
    private final Object value;

    public MapEntry(Object object0, Object object1) {
        this.key = object0;
        this.value = object1;
    }

    @Override
    public Object getKey() {
        return this.key;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Object setValue(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

