package androidx.collection;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001A\u00020\u0003H\u0086\nJ\t\u0010\n\u001A\u00020\u0003H\u0086\nJ\u0013\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\b\u0010\u0010\u001A\u00020\u0011H\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Landroidx/collection/LongLongPair;", "", "first", "", "second", "(JJ)V", "getFirst", "()J", "getSecond", "component1", "component2", "equals", "", "other", "hashCode", "", "toString", "", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class LongLongPair {
    private final long first;
    private final long second;

    public LongLongPair(long v, long v1) {
        this.first = v;
        this.second = v1;
    }

    public final long component1() {
        return this.getFirst();
    }

    public final long component2() {
        return this.getSecond();
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof LongLongPair ? ((LongLongPair)object0).first == this.first && ((LongLongPair)object0).second == this.second : false;
    }

    public final long getFirst() {
        return this.first;
    }

    public final long getSecond() {
        return this.second;
    }

    @Override
    public int hashCode() {
        return ((int)(this.first ^ this.first >>> 0x20)) ^ ((int)(this.second ^ this.second >>> 0x20));
    }

    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ')';
    }
}

