package kotlin;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u0001*\u0006\b\u0002\u0010\u0003 \u00012\u00060\u0004j\u0002`\u0005B\u001D\u0012\u0006\u0010\u0006\u001A\u00028\u0000\u0012\u0006\u0010\u0007\u001A\u00028\u0001\u0012\u0006\u0010\b\u001A\u00028\u0002¢\u0006\u0002\u0010\tJ\u000E\u0010\u000F\u001A\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000BJ\u000E\u0010\u0010\u001A\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u000BJ\u000E\u0010\u0011\u001A\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\u000BJ>\u0010\u0012\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0006\u001A\u00028\u00002\b\b\u0002\u0010\u0007\u001A\u00028\u00012\b\b\u0002\u0010\b\u001A\u00028\u0002HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001A\u00020\u00152\b\u0010\u0016\u001A\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001A\u00020\u0019HÖ\u0001J\b\u0010\u001A\u001A\u00020\u001BH\u0016R\u0013\u0010\u0006\u001A\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001A\u0004\b\n\u0010\u000BR\u0013\u0010\u0007\u001A\u00028\u0001¢\u0006\n\n\u0002\u0010\f\u001A\u0004\b\r\u0010\u000BR\u0013\u0010\b\u001A\u00028\u0002¢\u0006\n\n\u0002\u0010\f\u001A\u0004\b\u000E\u0010\u000B¨\u0006\u001C"}, d2 = {"Lkotlin/Triple;", "A", "B", "C", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "first", "second", "third", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getFirst", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getSecond", "getThird", "component1", "component2", "component3", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Triple;", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class Triple implements Serializable {
    private final Object first;
    private final Object second;
    private final Object third;

    public Triple(Object object0, Object object1, Object object2) {
        this.first = object0;
        this.second = object1;
        this.third = object2;
    }

    public final Object component1() {
        return this.first;
    }

    public final Object component2() {
        return this.second;
    }

    public final Object component3() {
        return this.third;
    }

    public final Triple copy(Object object0, Object object1, Object object2) {
        return new Triple(object0, object1, object2);
    }

    public static Triple copy$default(Triple triple0, Object object0, Object object1, Object object2, int v, Object object3) {
        if((v & 1) != 0) {
            object0 = triple0.first;
        }
        if((v & 2) != 0) {
            object1 = triple0.second;
        }
        if((v & 4) != 0) {
            object2 = triple0.third;
        }
        return triple0.copy(object0, object1, object2);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Triple)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.first, ((Triple)object0).first)) {
            return false;
        }
        return Intrinsics.areEqual(this.second, ((Triple)object0).second) ? Intrinsics.areEqual(this.third, ((Triple)object0).third) : false;
    }

    public final Object getFirst() {
        return this.first;
    }

    public final Object getSecond() {
        return this.second;
    }

    public final Object getThird() {
        return this.third;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.first == null ? 0 : this.first.hashCode();
        int v2 = this.second == null ? 0 : this.second.hashCode();
        Object object0 = this.third;
        if(object0 != null) {
            v = object0.hashCode();
        }
        return (v1 * 0x1F + v2) * 0x1F + v;
    }

    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ", " + this.third + ')';
    }
}

