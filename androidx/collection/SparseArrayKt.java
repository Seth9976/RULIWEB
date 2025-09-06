package androidx.collection;

import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010(\n\u0000\u001A!\u0010\u0006\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\u0001H\u0086\n\u001AT\u0010\t\u001A\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000326\u0010\u000B\u001A2\u0012\u0013\u0012\u00110\u0001\u00A2\u0006\f\b\r\u0012\b\b\u000E\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\r\u0012\b\b\u000E\u0012\u0004\b\b(\u000F\u0012\u0004\u0012\u00020\n0\fH\u0086\b\u00F8\u0001\u0000\u001A.\u0010\u0010\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\u00012\u0006\u0010\u0011\u001A\u0002H\u0002H\u0086\b\u00A2\u0006\u0002\u0010\u0012\u001A7\u0010\u0013\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\u00012\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0014H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001A\u0019\u0010\u0016\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\b\u001A\u0016\u0010\u0017\u001A\u00020\u0018\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001A-\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0002\u001A-\u0010\u001B\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\u00012\u0006\u0010\u000F\u001A\u0002H\u0002H\u0007\u00A2\u0006\u0002\u0010\u001C\u001A.\u0010\u001D\u001A\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\u00012\u0006\u0010\u000F\u001A\u0002H\u0002H\u0086\n\u00A2\u0006\u0002\u0010\u001E\u001A\u001C\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u00020 \"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\"\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006!"}, d2 = {"size", "", "T", "Landroidx/collection/SparseArrayCompat;", "getSize", "(Landroidx/collection/SparseArrayCompat;)I", "contains", "", "key", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "getOrDefault", "defaultValue", "(Landroidx/collection/SparseArrayCompat;ILjava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Landroidx/collection/SparseArrayCompat;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "remove", "(Landroidx/collection/SparseArrayCompat;ILjava/lang/Object;)Z", "set", "(Landroidx/collection/SparseArrayCompat;ILjava/lang/Object;)V", "valueIterator", "", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SparseArrayKt {
    public static final boolean contains(SparseArrayCompat sparseArrayCompat0, int v) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        return sparseArrayCompat0.containsKey(v);
    }

    public static final void forEach(SparseArrayCompat sparseArrayCompat0, Function2 function20) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "action");
        int v = sparseArrayCompat0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            function20.invoke(sparseArrayCompat0.keyAt(v1), sparseArrayCompat0.valueAt(v1));
        }
    }

    public static final Object getOrDefault(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        return sparseArrayCompat0.get(v, object0);
    }

    public static final Object getOrElse(SparseArrayCompat sparseArrayCompat0, int v, Function0 function00) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        Object object0 = sparseArrayCompat0.get(v);
        return object0 == null ? function00.invoke() : object0;
    }

    public static final int getSize(SparseArrayCompat sparseArrayCompat0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        return sparseArrayCompat0.size();
    }

    public static final boolean isNotEmpty(SparseArrayCompat sparseArrayCompat0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        return !sparseArrayCompat0.isEmpty();
    }

    public static final IntIterator keyIterator(SparseArrayCompat sparseArrayCompat0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        return new IntIterator() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < sparseArrayCompat0.size();
            }

            @Override  // kotlin.collections.IntIterator
            public int nextInt() {
                int v = this.index;
                this.index = v + 1;
                return sparseArrayCompat0.keyAt(v);
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }

    public static final SparseArrayCompat plus(SparseArrayCompat sparseArrayCompat0, SparseArrayCompat sparseArrayCompat1) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        Intrinsics.checkNotNullParameter(sparseArrayCompat1, "other");
        SparseArrayCompat sparseArrayCompat2 = new SparseArrayCompat(sparseArrayCompat0.size() + sparseArrayCompat1.size());
        sparseArrayCompat2.putAll(sparseArrayCompat0);
        sparseArrayCompat2.putAll(sparseArrayCompat1);
        return sparseArrayCompat2;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced with member function. Remove extension import!")
    public static final boolean remove(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        return sparseArrayCompat0.remove(v, object0);
    }

    public static final void set(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        sparseArrayCompat0.put(v, object0);
    }

    public static final Iterator valueIterator(SparseArrayCompat sparseArrayCompat0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        return new Object() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < sparseArrayCompat0.size();
            }

            @Override
            public Object next() {
                int v = this.index;
                this.index = v + 1;
                return sparseArrayCompat0.valueAt(v);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }
}

