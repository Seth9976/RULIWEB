package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0013\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001A\u00020\tH\u0086\u0002J\u000F\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0086\u0002R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lkotlin/collections/IndexingIterator;", "T", "", "Lkotlin/collections/IndexedValue;", "iterator", "(Ljava/util/Iterator;)V", "index", "", "hasNext", "", "next", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class IndexingIterator implements Iterator, KMappedMarker {
    private int index;
    private final Iterator iterator;

    public IndexingIterator(Iterator iterator0) {
        Intrinsics.checkNotNullParameter(iterator0, "iterator");
        super();
        this.iterator = iterator0;
    }

    @Override
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public Object next() {
        return this.next();
    }

    public final IndexedValue next() {
        int v = this.index;
        this.index = v + 1;
        if(v < 0) {
            CollectionsKt.throwIndexOverflow();
        }
        Object object0 = this.iterator.next();
        return new IndexedValue(v, object0);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

