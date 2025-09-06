package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000E\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nJ\u0006\u0010\u000B\u001A\u00020\u0002J\f\u0010\f\u001A\u00020\u0004*\u00020\u0002H\u0014R\u000E\u0010\u0006\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/jvm/internal/BooleanSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class BooleanSpreadBuilder extends PrimitiveSpreadBuilder {
    private final boolean[] values;

    public BooleanSpreadBuilder(int v) {
        super(v);
        this.values = new boolean[v];
    }

    public final void add(boolean z) {
        int v = this.getPosition();
        this.setPosition(v + 1);
        this.values[v] = z;
    }

    @Override  // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(Object object0) {
        return this.getSize(((boolean[])object0));
    }

    protected int getSize(boolean[] arr_z) {
        Intrinsics.checkNotNullParameter(arr_z, "<this>");
        return arr_z.length;
    }

    public final boolean[] toArray() {
        return (boolean[])this.toArray(this.values, new boolean[this.size()]);
    }
}

