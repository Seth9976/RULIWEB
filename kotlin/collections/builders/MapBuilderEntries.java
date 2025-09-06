package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u001E\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022 \u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u001B\b\u0000\u0012\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\u0007J\u001C\u0010\u000E\u001A\u00020\u000F2\u0012\u0010\u0010\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016J\"\u0010\u0011\u001A\u00020\u000F2\u0018\u0010\u0012\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0013H\u0016J\b\u0010\u0014\u001A\u00020\u0015H\u0016J\"\u0010\u0016\u001A\u00020\u000F2\u0018\u0010\u0012\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0013H\u0016J\u001C\u0010\u0017\u001A\u00020\u000F2\u0012\u0010\u0010\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0018H\u0016J\b\u0010\u0019\u001A\u00020\u000FH\u0016J\u001B\u0010\u001A\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u001BH\u0096\u0002J\u001C\u0010\u001C\u001A\u00020\u000F2\u0012\u0010\u0010\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016J\"\u0010\u001D\u001A\u00020\u000F2\u0018\u0010\u0012\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0013H\u0016J\"\u0010\u001E\u001A\u00020\u000F2\u0018\u0010\u0012\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0013H\u0016R\u001D\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0014\u0010\n\u001A\u00020\u000B8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\r¨\u0006\u001F"}, d2 = {"Lkotlin/collections/builders/MapBuilderEntries;", "K", "V", "Lkotlin/collections/builders/AbstractMapBuilderEntrySet;", "", "backing", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "getBacking", "()Lkotlin/collections/builders/MapBuilder;", "size", "", "getSize", "()I", "add", "", "element", "addAll", "elements", "", "clear", "", "containsAll", "containsEntry", "", "isEmpty", "iterator", "", "remove", "removeAll", "retainAll", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class MapBuilderEntries extends AbstractMapBuilderEntrySet {
    private final MapBuilder backing;

    public MapBuilderEntries(MapBuilder mapBuilder0) {
        Intrinsics.checkNotNullParameter(mapBuilder0, "backing");
        super();
        this.backing = mapBuilder0;
    }

    @Override  // kotlin.collections.AbstractMutableSet
    public boolean add(Object object0) {
        return this.add(((Map.Entry)object0));
    }

    public boolean add(Map.Entry map$Entry0) {
        Intrinsics.checkNotNullParameter(map$Entry0, "element");
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
    public boolean containsAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        return this.backing.containsAllEntries$kotlin_stdlib(collection0);
    }

    @Override  // kotlin.collections.builders.AbstractMapBuilderEntrySet
    public boolean containsEntry(Map.Entry map$Entry0) {
        Intrinsics.checkNotNullParameter(map$Entry0, "element");
        return this.backing.containsEntry$kotlin_stdlib(map$Entry0);
    }

    public final MapBuilder getBacking() {
        return this.backing;
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
        return this.backing.entriesIterator$kotlin_stdlib();
    }

    @Override  // kotlin.collections.builders.AbstractMapBuilderEntrySet
    public boolean remove(Map.Entry map$Entry0) {
        Intrinsics.checkNotNullParameter(map$Entry0, "element");
        return this.backing.removeEntry$kotlin_stdlib(map$Entry0);
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

