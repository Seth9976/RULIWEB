package kotlin.collections;

import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u0013\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u000E\u001A\u00028\u00002\u0006\u0010\u000F\u001A\u00020\tH\u0096\u0002¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001A\u00020\u00122\u0006\u0010\n\u001A\u00020\t2\u0006\u0010\u0013\u001A\u00020\tR\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000B\u001A\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lkotlin/collections/MovingSubList;", "E", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "list", "", "(Ljava/util/List;)V", "_size", "", "fromIndex", "size", "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "move", "", "toIndex", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class MovingSubList extends AbstractList implements RandomAccess {
    private int _size;
    private int fromIndex;
    private final List list;

    public MovingSubList(List list0) {
        Intrinsics.checkNotNullParameter(list0, "list");
        super();
        this.list = list0;
    }

    @Override  // kotlin.collections.AbstractList
    public Object get(int v) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(v, this._size);
        return this.list.get(this.fromIndex + v);
    }

    @Override  // kotlin.collections.AbstractList
    public int getSize() {
        return this._size;
    }

    public final void move(int v, int v1) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(v, v1, this.list.size());
        this.fromIndex = v;
        this._size = v1 - v;
    }
}

