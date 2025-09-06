package kotlin.enums;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\b\u0003\u0018\u0000*\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00060\u0005j\u0002`\u0006B\u0013\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\u0016\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001A\u00028\u00002\u0006\u0010\u0014\u001A\u00020\fH\u0096\u0002¢\u0006\u0002\u0010\u0015J\u0015\u0010\u0016\u001A\u00020\f2\u0006\u0010\u0011\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017J\u0015\u0010\u0018\u001A\u00020\f2\u0006\u0010\u0011\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017J\b\u0010\u0019\u001A\u00020\u001AH\u0002R\u0016\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u000B\u001A\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000E¨\u0006\u001B"}, d2 = {"Lkotlin/enums/EnumEntriesList;", "T", "", "Lkotlin/enums/EnumEntries;", "Lkotlin/collections/AbstractList;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "entries", "", "([Ljava/lang/Enum;)V", "[Ljava/lang/Enum;", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Enum;)Z", "get", "index", "(I)Ljava/lang/Enum;", "indexOf", "(Ljava/lang/Enum;)I", "lastIndexOf", "writeReplace", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class EnumEntriesList extends AbstractList implements Serializable, EnumEntries {
    private final Enum[] entries;

    public EnumEntriesList(Enum[] arr_enum) {
        Intrinsics.checkNotNullParameter(arr_enum, "entries");
        super();
        this.entries = arr_enum;
    }

    public boolean contains(Enum enum0) {
        Intrinsics.checkNotNullParameter(enum0, "element");
        return ((Enum)ArraysKt.getOrNull(this.entries, enum0.ordinal())) == enum0;
    }

    @Override  // kotlin.collections.AbstractCollection
    public final boolean contains(Object object0) {
        return object0 instanceof Enum ? this.contains(((Enum)object0)) : false;
    }

    public Enum get(int v) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(v, this.entries.length);
        return this.entries[v];
    }

    @Override  // kotlin.collections.AbstractList
    public Object get(int v) {
        return this.get(v);
    }

    @Override  // kotlin.collections.AbstractList
    public int getSize() {
        return this.entries.length;
    }

    public int indexOf(Enum enum0) {
        Intrinsics.checkNotNullParameter(enum0, "element");
        int v = enum0.ordinal();
        return ((Enum)ArraysKt.getOrNull(this.entries, v)) == enum0 ? v : -1;
    }

    @Override  // kotlin.collections.AbstractList
    public final int indexOf(Object object0) {
        return object0 instanceof Enum ? this.indexOf(((Enum)object0)) : -1;
    }

    public int lastIndexOf(Enum enum0) {
        Intrinsics.checkNotNullParameter(enum0, "element");
        return this.indexOf(enum0);
    }

    @Override  // kotlin.collections.AbstractList
    public final int lastIndexOf(Object object0) {
        return object0 instanceof Enum ? this.lastIndexOf(((Enum)object0)) : -1;
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(this.entries);
    }
}

