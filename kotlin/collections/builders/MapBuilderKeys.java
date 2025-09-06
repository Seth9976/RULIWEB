package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableSet;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u001E\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010)\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0019\b\u0000\u0012\u0010\u0010\u0004\u001A\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000EJ\u0016\u0010\u000F\u001A\u00020\f2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0016J\b\u0010\u0012\u001A\u00020\u0013H\u0016J\u0016\u0010\u0014\u001A\u00020\f2\u0006\u0010\r\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000EJ\b\u0010\u0015\u001A\u00020\fH\u0016J\u000F\u0010\u0016\u001A\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0096\u0002J\u0015\u0010\u0018\u001A\u00020\f2\u0006\u0010\r\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000EJ\u0016\u0010\u0019\u001A\u00020\f2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0016J\u0016\u0010\u001A\u001A\u00020\f2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0016R\u0018\u0010\u0004\u001A\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001A\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\n¨\u0006\u001B"}, d2 = {"Lkotlin/collections/builders/MapBuilderKeys;", "E", "", "Lkotlin/collections/AbstractMutableSet;", "backing", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "size", "", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "addAll", "elements", "", "clear", "", "contains", "isEmpty", "iterator", "", "remove", "removeAll", "retainAll", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class MapBuilderKeys extends AbstractMutableSet implements Set, KMutableSet {
    private final MapBuilder backing;

    public MapBuilderKeys(MapBuilder mapBuilder0) {
        Intrinsics.checkNotNullParameter(mapBuilder0, "backing");
        super();
        this.backing = mapBuilder0;
    }

    @Override  // kotlin.collections.AbstractMutableSet
    public boolean add(Object object0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.backing.clear();
    }

    @Override
    public boolean contains(Object object0) {
        return this.backing.containsKey(object0);
    }

    @Override  // kotlin.collections.AbstractMutableSet
    public int getSize() {
        return this.backing.size();
    }

    @Override
    public boolean isEmpty() {
        return this.backing.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return this.backing.keysIterator$kotlin_stdlib();
    }

    @Override
    public boolean remove(Object object0) {
        return this.backing.removeKey$kotlin_stdlib(object0) >= 0;
    }

    @Override
    public boolean removeAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.removeAll(collection0);
    }

    @Override
    public boolean retainAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.retainAll(collection0);
    }
}

