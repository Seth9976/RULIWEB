package kotlin;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00012\u00060\u0003j\u0002`\u0004B\u0015\u0012\u0006\u0010\u0005\u001A\u00028\u0000\u0012\u0006\u0010\u0006\u001A\u00028\u0001¢\u0006\u0002\u0010\u0007J\u000E\u0010\f\u001A\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\tJ\u000E\u0010\r\u001A\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\tJ.\u0010\u000E\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0005\u001A\u00028\u00002\b\b\u0002\u0010\u0006\u001A\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\u000FJ\u0013\u0010\u0010\u001A\u00020\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001A\u00020\u0015HÖ\u0001J\b\u0010\u0016\u001A\u00020\u0017H\u0016R\u0013\u0010\u0005\u001A\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001A\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001A\u00028\u0001¢\u0006\n\n\u0002\u0010\n\u001A\u0004\b\u000B\u0010\t¨\u0006\u0018"}, d2 = {"Lkotlin/Pair;", "A", "B", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "first", "second", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getFirst", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getSecond", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class Pair implements Serializable {
    private final Object first;
    private final Object second;

    public Pair(Object object0, Object object1) {
        this.first = object0;
        this.second = object1;
    }

    public final Object component1() {
        return this.first;
    }

    public final Object component2() {
        return this.second;
    }

    public final Pair copy(Object object0, Object object1) {
        return new Pair(object0, object1);
    }

    public static Pair copy$default(Pair pair0, Object object0, Object object1, int v, Object object2) {
        if((v & 1) != 0) {
            object0 = pair0.first;
        }
        if((v & 2) != 0) {
            object1 = pair0.second;
        }
        return pair0.copy(object0, object1);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Pair)) {
            return false;
        }
        return Intrinsics.areEqual(this.first, ((Pair)object0).first) ? Intrinsics.areEqual(this.second, ((Pair)object0).second) : false;
    }

    public final Object getFirst() {
        return this.first;
    }

    public final Object getSecond() {
        return this.second;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.first == null ? 0 : this.first.hashCode();
        Object object0 = this.second;
        if(object0 != null) {
            v = object0.hashCode();
        }
        return v1 * 0x1F + v;
    }

    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ')';
    }
}

