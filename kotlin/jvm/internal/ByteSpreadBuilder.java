package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000E\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nJ\u0006\u0010\u000B\u001A\u00020\u0002J\f\u0010\f\u001A\u00020\u0004*\u00020\u0002H\u0014R\u000E\u0010\u0006\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/jvm/internal/ByteSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class ByteSpreadBuilder extends PrimitiveSpreadBuilder {
    private final byte[] values;

    public ByteSpreadBuilder(int v) {
        super(v);
        this.values = new byte[v];
    }

    public final void add(byte b) {
        int v = this.getPosition();
        this.setPosition(v + 1);
        this.values[v] = b;
    }

    @Override  // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(Object object0) {
        return this.getSize(((byte[])object0));
    }

    protected int getSize(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "<this>");
        return arr_b.length;
    }

    public final byte[] toArray() {
        return (byte[])this.toArray(this.values, new byte[this.size()]);
    }
}

