package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\u000B\n\u0002\b\u0005\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000E\u0010\u0004\u001A\u00020\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001A\u00020\u0002H&¨\u0006\u0007"}, d2 = {"Lkotlin/collections/BooleanIterator;", "", "", "()V", "next", "()Ljava/lang/Boolean;", "nextBoolean", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class BooleanIterator implements Iterator, KMappedMarker {
    public final Boolean next() {
        return Boolean.valueOf(this.nextBoolean());
    }

    @Override
    public Object next() {
        return Boolean.valueOf(this.nextBoolean());
    }

    public abstract boolean nextBoolean();

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}

