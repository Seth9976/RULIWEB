package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableSet;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u001E\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010)\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 $*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u0005:\u0001$B\u0007\b\u0016¢\u0006\u0002\u0010\u0006B\u000F\b\u0016\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tB\u0019\b\u0000\u0012\u0010\u0010\n\u001A\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u000B¢\u0006\u0002\u0010\fJ\u0015\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001A\u00020\u00112\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\f\u0010\u0017\u001A\b\u0012\u0004\u0012\u00028\u00000\u0018J\b\u0010\u0019\u001A\u00020\u001AH\u0016J\u0016\u0010\u001B\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0013J\b\u0010\u001C\u001A\u00020\u0011H\u0016J\u000F\u0010\u001D\u001A\b\u0012\u0004\u0012\u00028\u00000\u001EH\u0096\u0002J\u0015\u0010\u001F\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013J\u0016\u0010 \u001A\u00020\u00112\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\u0016\u0010!\u001A\u00020\u00112\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\b\u0010\"\u001A\u00020#H\u0002R\u0018\u0010\n\u001A\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001A\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000F¨\u0006%"}, d2 = {"Lkotlin/collections/builders/SetBuilder;", "E", "", "Lkotlin/collections/AbstractMutableSet;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "initialCapacity", "", "(I)V", "backing", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "size", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "addAll", "elements", "", "build", "", "clear", "", "contains", "isEmpty", "iterator", "", "remove", "removeAll", "retainAll", "writeReplace", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class SetBuilder extends AbstractMutableSet implements Serializable, Set, KMutableSet {
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlin/collections/builders/SetBuilder$Companion;", "", "()V", "Empty", "Lkotlin/collections/builders/SetBuilder;", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final Companion Companion;
    private static final SetBuilder Empty;
    private final MapBuilder backing;

    static {
        SetBuilder.Companion = new Companion(null);
        SetBuilder.Empty = new SetBuilder(MapBuilder.Companion.getEmpty$kotlin_stdlib());
    }

    public SetBuilder() {
        this(new MapBuilder());
    }

    public SetBuilder(int v) {
        this(new MapBuilder(v));
    }

    public SetBuilder(MapBuilder mapBuilder0) {
        Intrinsics.checkNotNullParameter(mapBuilder0, "backing");
        super();
        this.backing = mapBuilder0;
    }

    @Override  // kotlin.collections.AbstractMutableSet
    public boolean add(Object object0) {
        return this.backing.addKey$kotlin_stdlib(object0) >= 0;
    }

    @Override
    public boolean addAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.addAll(collection0);
    }

    public final Set build() {
        this.backing.build();
        return this.size() <= 0 ? SetBuilder.Empty : this;
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

    private final Object writeReplace() {
        if(!this.backing.isReadOnly$kotlin_stdlib()) {
            throw new NotSerializableException("The set cannot be serialized while it is being built.");
        }
        return new SerializedCollection(this, 1);
    }
}

