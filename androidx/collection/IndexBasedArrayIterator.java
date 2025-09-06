package androidx.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableIterator;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b \u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\n\u001A\u00028\u00002\u0006\u0010\b\u001A\u00020\u0004H$¢\u0006\u0002\u0010\u000BJ\t\u0010\f\u001A\u00020\u0007H\u0096\u0002J\u000E\u0010\r\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000EJ\b\u0010\u000F\u001A\u00020\u0010H\u0016J\u0010\u0010\u0011\u001A\u00020\u00102\u0006\u0010\b\u001A\u00020\u0004H$R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/collection/IndexBasedArrayIterator;", "T", "", "startingSize", "", "(I)V", "canRemove", "", "index", "size", "elementAt", "(I)Ljava/lang/Object;", "hasNext", "next", "()Ljava/lang/Object;", "remove", "", "removeAt", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class IndexBasedArrayIterator implements Iterator, KMutableIterator {
    private boolean canRemove;
    private int index;
    private int size;

    public IndexBasedArrayIterator(int v) {
        this.size = v;
    }

    protected abstract Object elementAt(int arg1);

    @Override
    public boolean hasNext() {
        return this.index < this.size;
    }

    @Override
    public Object next() {
        if(!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object0 = this.elementAt(this.index);
        ++this.index;
        this.canRemove = true;
        return object0;
    }

    @Override
    public void remove() {
        if(!this.canRemove) {
            throw new IllegalStateException("Call next() before removing an element.");
        }
        int v = this.index - 1;
        this.index = v;
        this.removeAt(v);
        --this.size;
        this.canRemove = false;
    }

    protected abstract void removeAt(int arg1);
}

