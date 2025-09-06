package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010)\n\u0000\n\u0002\u0010+\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001D\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000EJ\b\u0010\u000F\u001A\u00020\u000BH\u0016J\u0016\u0010\u0010\u001A\u00028\u00002\u0006\u0010\f\u001A\u00020\u0007H\u0096\u0002¢\u0006\u0002\u0010\u0011J\u000F\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0096\u0002J\u000E\u0010\u0014\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0016J\u0016\u0010\u0014\u001A\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\f\u001A\u00020\u0007H\u0016J\u0015\u0010\u0016\u001A\u00028\u00002\u0006\u0010\f\u001A\u00020\u0007H\u0016¢\u0006\u0002\u0010\u0011J\u001E\u0010\u0017\u001A\u00028\u00002\u0006\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\u0019"}, d2 = {"Lkotlin/collections/ReversedList;", "T", "Lkotlin/collections/AbstractMutableList;", "delegate", "", "(Ljava/util/List;)V", "size", "", "getSize", "()I", "add", "", "index", "element", "(ILjava/lang/Object;)V", "clear", "get", "(I)Ljava/lang/Object;", "iterator", "", "listIterator", "", "removeAt", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class ReversedList extends AbstractMutableList {
    private final List delegate;

    public ReversedList(List list0) {
        Intrinsics.checkNotNullParameter(list0, "delegate");
        super();
        this.delegate = list0;
    }

    @Override  // kotlin.collections.AbstractMutableList
    public void add(int v, Object object0) {
        int v1 = CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt(this, v);
        this.delegate.add(v1, object0);
    }

    @Override
    public void clear() {
        this.delegate.clear();
    }

    @Override
    public Object get(int v) {
        int v1 = CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, v);
        return this.delegate.get(v1);
    }

    @Override  // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.delegate.size();
    }

    @Override
    public Iterator iterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator listIterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator listIterator(int v) {
        return new Object() {
            private final ListIterator delegateIterator;

            {
                ReversedList reversedList0 = v;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.delegateIterator = reversedList0.delegate.listIterator(CollectionsKt__ReversedViewsKt.reversePositionIndex$CollectionsKt__ReversedViewsKt(reversedList0, v));
            }

            @Override
            public void add(Object object0) {
                this.delegateIterator.add(object0);
                this.delegateIterator.previous();
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
                this.delegateIterator.remove();
            }

            @Override
            public void set(Object object0) {
                this.delegateIterator.set(object0);
            }
        };
    }

    @Override  // kotlin.collections.AbstractMutableList
    public Object removeAt(int v) {
        int v1 = CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, v);
        return this.delegate.remove(v1);
    }

    @Override  // kotlin.collections.AbstractMutableList
    public Object set(int v, Object object0) {
        int v1 = CollectionsKt__ReversedViewsKt.reverseElementIndex$CollectionsKt__ReversedViewsKt(this, v);
        return this.delegate.set(v1, object0);
    }
}

