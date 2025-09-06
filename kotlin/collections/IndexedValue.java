package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00028\u0000¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001A\u00020\u0004HÆ\u0003J\u000E\u0010\r\u001A\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ(\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001A\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000FJ\u0013\u0010\u0010\u001A\u00020\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001A\u00020\u0004HÖ\u0001J\t\u0010\u0014\u001A\u00020\u0015HÖ\u0001R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001A\u00028\u0000¢\u0006\n\n\u0002\u0010\u000B\u001A\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lkotlin/collections/IndexedValue;", "T", "", "index", "", "value", "(ILjava/lang/Object;)V", "getIndex", "()I", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(ILjava/lang/Object;)Lkotlin/collections/IndexedValue;", "equals", "", "other", "hashCode", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class IndexedValue {
    private final int index;
    private final Object value;

    public IndexedValue(int v, Object object0) {
        this.index = v;
        this.value = object0;
    }

    public final int component1() {
        return this.index;
    }

    public final Object component2() {
        return this.value;
    }

    public final IndexedValue copy(int v, Object object0) {
        return new IndexedValue(v, object0);
    }

    public static IndexedValue copy$default(IndexedValue indexedValue0, int v, Object object0, int v1, Object object1) {
        if((v1 & 1) != 0) {
            v = indexedValue0.index;
        }
        if((v1 & 2) != 0) {
            object0 = indexedValue0.value;
        }
        return indexedValue0.copy(v, object0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof IndexedValue)) {
            return false;
        }
        return this.index == ((IndexedValue)object0).index ? Intrinsics.areEqual(this.value, ((IndexedValue)object0).value) : false;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Object getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        int v = this.index * 0x1F;
        return this.value == null ? v : v + this.value.hashCode();
    }

    @Override
    public String toString() {
        return "IndexedValue(index=" + this.index + ", value=" + this.value + ')';
    }
}

