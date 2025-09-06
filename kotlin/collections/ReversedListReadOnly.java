package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010(\n\u0000\n\u0002\u0010*\n\u0000\b\u0012\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\n\u001A\u00028\u00002\u0006\u0010\u000B\u001A\u00020\u0007H\u0096\u0002¢\u0006\u0002\u0010\fJ\u000F\u0010\r\u001A\b\u0012\u0004\u0012\u00028\u00000\u000EH\u0096\u0002J\u000E\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0016J\u0016\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\u000B\u001A\u00020\u0007H\u0016R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lkotlin/collections/ReversedListReadOnly;", "T", "Lkotlin/collections/AbstractList;", "delegate", "", "(Ljava/util/List;)V", "size", "", "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "iterator", "", "listIterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
class ReversedListReadOnly extends AbstractList {
    private final List delegate;

    public ReversedListReadOnly(List list0) {
        Intrinsics.checkNotNullParameter(list0, "delegate");
        super();
        this.delegate = list0;
    }

    @Override  // kotlin.collections.AbstractList
    public Object get(int v) {
        int v1 = CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, v);
        return this.delegate.get(v1);
    }

    @Override  // kotlin.collections.AbstractList
    public int getSize() {
        return this.delegate.size();
    }

    @Override  // kotlin.collections.AbstractList
    public Iterator iterator() {
        return this.listIterator(0);
    }

    @Override  // kotlin.collections.AbstractList
    public ListIterator listIterator() {
        return this.listIterator(0);
    }

    @Override  // kotlin.collections.AbstractList
    public ListIterator listIterator(int v) {
        return new Object() {
            private final ListIterator delegateIterator;

            {
                ReversedListReadOnly reversedListReadOnly0 = v;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.delegateIterator = reversedListReadOnly0.delegate.listIterator(CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt(reversedListReadOnly0, v));
            }

            @Override
            public void add(Object object0) {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            public final ListIterator getDelegateIterator() {
                return this.delegateIterator;
            }

            @Override
            public boolean hasNext() {
                return this.delegateIterator.hasPrevious();
            }

            @Override
            public boolean hasPrevious() {
                return this.delegateIterator.hasNext();
            }

            @Override
            public Object next() {
                return this.delegateIterator.previous();
            }

            @Override
            public int nextIndex() {
                int v = this.delegateIterator.previousIndex();
                return CollectionsKt__ReversedViewsKt.reverseIteratorIndex$CollectionsKt__ReversedViewsKt(v, v);
            }

            @Override
            public Object previous() {
                return this.delegateIterator.next();
            }

            @Override
            public int previousIndex() {
                int v = this.delegateIterator.nextIndex();
                return CollectionsKt__ReversedViewsKt.reverseIteratorIndex$CollectionsKt__ReversedViewsKt(v, v);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            @Override
            public void set(Object object0) {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }
}

