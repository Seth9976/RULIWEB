package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000E\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nJ\u0006\u0010\u000B\u001A\u00020\u0002J\f\u0010\f\u001A\u00020\u0004*\u00020\u0002H\u0014R\u000E\u0010\u0006\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/jvm/internal/ShortSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class ShortSpreadBuilder extends PrimitiveSpreadBuilder {
    private final short[] values;

    public ShortSpreadBuilder(int v) {
        super(v);
        this.values = new short[v];
    }

    public final void add(short v) {
        int v1 = this.getPosition();
        this.setPosition(v1 + 1);
        this.values[v1] = v;
    }

    @Override  // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(Object object0) {
        return this.getSize(((short[])object0));
    }

    protected int getSize(short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "<this>");
        return arr_v.length;
    }

    public final short[] toArray() {
        return (short[])this.toArray(this.values, new short[this.size()]);
    }
}

