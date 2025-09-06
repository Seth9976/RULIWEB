package androidx.core.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B/\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u001A\u0010\u0004\u001A\u0016\u0012\u0004\u0012\u00028\u0000\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001A\u00020\u000BH\u0096\u0002J\u000E\u0010\f\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\rJ\u0015\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0011R\"\u0010\u0004\u001A\u0016\u0012\u0004\u0012\u00028\u0000\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u000E¢\u0006\u0002\n\u0000R\u001A\u0010\b\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/core/view/TreeIterator;", "T", "", "rootIterator", "getChildIterator", "Lkotlin/Function1;", "(Ljava/util/Iterator;Lkotlin/jvm/functions/Function1;)V", "iterator", "stack", "", "hasNext", "", "next", "()Ljava/lang/Object;", "prepareNextIterator", "", "item", "(Ljava/lang/Object;)V", "core-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class TreeIterator implements Iterator, KMappedMarker {
    private final Function1 getChildIterator;
    private Iterator iterator;
    private final List stack;

    public TreeIterator(Iterator iterator0, Function1 function10) {
        this.getChildIterator = function10;
        this.stack = new ArrayList();
        this.iterator = iterator0;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public Object next() {
        Object object0 = this.iterator.next();
        this.prepareNextIterator(object0);
        return object0;
    }

    private final void prepareNextIterator(Object object0) {
        Iterator iterator0 = (Iterator)this.getChildIterator.invoke(object0);
        if(iterator0 != null && iterator0.hasNext()) {
            this.stack.add(this.iterator);
            this.iterator = iterator0;
            return;
        }
        while(!this.iterator.hasNext() && !this.stack.isEmpty()) {
            this.iterator = (Iterator)CollectionsKt.last(this.stack);
            CollectionsKt.removeLast(this.stack);
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

