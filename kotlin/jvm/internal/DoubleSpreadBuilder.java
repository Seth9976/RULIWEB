package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000E\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nJ\u0006\u0010\u000B\u001A\u00020\u0002J\f\u0010\f\u001A\u00020\u0004*\u00020\u0002H\u0014R\u000E\u0010\u0006\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/jvm/internal/DoubleSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class DoubleSpreadBuilder extends PrimitiveSpreadBuilder {
    private final double[] values;

    public DoubleSpreadBuilder(int v) {
        super(v);
        this.values = new double[v];
    }

    public final void add(double f) {
        int v = this.getPosition();
        this.setPosition(v + 1);
        this.values[v] = f;
    }

    @Override  // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(Object object0) {
        return this.getSize(((double[])object0));
    }

    protected int getSize(double[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "<this>");
        return arr_f.length;
    }

    public final double[] toArray() {
        return (double[])this.toArray(this.values, new double[this.size()]);
    }
}

