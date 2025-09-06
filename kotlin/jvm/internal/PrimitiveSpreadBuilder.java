package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00028\u0000¢\u0006\u0002\u0010\u0012J\b\u0010\u0003\u001A\u00020\u0004H\u0004J\u001D\u0010\u0013\u001A\u00028\u00002\u0006\u0010\u0014\u001A\u00028\u00002\u0006\u0010\u0015\u001A\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001A\u00020\u0004*\u00028\u0000H$¢\u0006\u0002\u0010\u0018R\u001A\u0010\u0006\u001A\u00020\u0004X\u0084\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001E\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000BX\u0082\u0004¢\u0006\n\n\u0002\u0010\u000E\u0012\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "T", "", "size", "", "(I)V", "position", "getPosition", "()I", "setPosition", "spreads", "", "getSpreads$annotations", "()V", "[Ljava/lang/Object;", "addSpread", "", "spreadArgument", "(Ljava/lang/Object;)V", "toArray", "values", "result", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getSize", "(Ljava/lang/Object;)I", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class PrimitiveSpreadBuilder {
    private int position;
    private final int size;
    private final Object[] spreads;

    public PrimitiveSpreadBuilder(int v) {
        this.size = v;
        this.spreads = new Object[v];
    }

    public final void addSpread(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "spreadArgument");
        int v = this.position;
        this.position = v + 1;
        this.spreads[v] = object0;
    }

    protected final int getPosition() {
        return this.position;
    }

    protected abstract int getSize(Object arg1);

    private static void getSpreads$annotations() {
    }

    protected final void setPosition(int v) {
        this.position = v;
    }

    protected final int size() {
        int v = 0;
        IntIterator intIterator0 = new IntRange(0, this.size - 1).iterator();
        while(intIterator0.hasNext()) {
            Object object0 = this.spreads[intIterator0.nextInt()];
            v += (object0 == null ? 1 : this.getSize(object0));
        }
        return v;
    }

    protected final Object toArray(Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(object0, "values");
        Intrinsics.checkNotNullParameter(object1, "result");
        int v = 0;
        int v1 = 0;
        IntIterator intIterator0 = new IntRange(0, this.size - 1).iterator();
        while(intIterator0.hasNext()) {
            int v2 = intIterator0.nextInt();
            Object object2 = this.spreads[v2];
            if(object2 != null) {
                if(v < v2) {
                    int v3 = v2 - v;
                    System.arraycopy(object0, v, object1, v1, v3);
                    v1 += v3;
                }
                int v4 = this.getSize(object2);
                System.arraycopy(object2, 0, object1, v1, v4);
                v1 += v4;
                v = v2 + 1;
            }
        }
        int v5 = this.size;
        if(v < v5) {
            System.arraycopy(object0, v, object1, v1, v5 - v);
        }
        return object1;
    }
}

