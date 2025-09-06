package kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\n\n\u0002\u0010\u000E\n\u0000\bÂ\u0002\u0018\u00002\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00060\u0004j\u0002`\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0018\u001A\u00020\u00192\b\u0010\u001A\u001A\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u001B\u001A\u00020\u00192\u0006\u0010\u001C\u001A\u00020\u0003H\u0016J\u0013\u0010\u001D\u001A\u00020\u00192\b\u0010\u001E\u001A\u0004\u0018\u00010\u0002H\u0096\u0002J\u0015\u0010\u001F\u001A\u0004\u0018\u00010\u00032\b\u0010\u001A\u001A\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010 \u001A\u00020\u0011H\u0016J\b\u0010!\u001A\u00020\u0019H\u0016J\b\u0010\"\u001A\u00020\u0002H\u0002J\b\u0010#\u001A\u00020$H\u0016R(\u0010\u0007\u001A\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\t0\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u001C\u0010\f\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000BR\u000E\u0010\u000E\u001A\u00020\u000FX\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001A\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013R\u001A\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00030\u00158VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lkotlin/collections/EmptyMap;", "", "", "", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "serialVersionUID", "", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "containsKey", "", "key", "containsValue", "value", "equals", "other", "get", "hashCode", "isEmpty", "readResolve", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class EmptyMap implements Serializable, Map, KMappedMarker {
    public static final EmptyMap INSTANCE = null;
    private static final long serialVersionUID = 0x72723771CB044CD2L;

    static {
        EmptyMap.INSTANCE = new EmptyMap();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean containsKey(Object object0) {
        return false;
    }

    @Override
    public final boolean containsValue(Object object0) {
        return object0 instanceof Void ? this.containsValue(((Void)object0)) : false;
    }

    public boolean containsValue(Void void0) {
        Intrinsics.checkNotNullParameter(void0, "value");
        return false;
    }

    @Override
    public final Set entrySet() {
        return this.getEntries();
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Map && ((Map)object0).isEmpty();
    }

    @Override
    public Object get(Object object0) {
        return null;
    }

    public Void get(Object object0) [...] // Inlined contents

    public Set getEntries() {
        return EmptySet.INSTANCE;
    }

    public Set getKeys() {
        return EmptySet.INSTANCE;
    }

    public int getSize() [...] // Inlined contents

    public Collection getValues() {
        return EmptyList.INSTANCE;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public final Set keySet() {
        return this.getKeys();
    }

    @Override
    public Object put(Object object0, Object object1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Void put(Object object0, Void void0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void putAll(Map map0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    private final Object readResolve() {
        return EmptyMap.INSTANCE;
    }

    @Override
    public Object remove(Object object0) {
        return this.remove(object0);
    }

    public Void remove(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public final int size() {
        return 0;
    }

    @Override
    public String toString() {
        return "{}";
    }

    @Override
    public final Collection values() {
        return this.getValues();
    }
}

