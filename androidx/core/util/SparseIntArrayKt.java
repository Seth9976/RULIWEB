package androidx.core.util;

import android.util.SparseIntArray;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001A\u0015\u0010\u0005\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u0001H\u0086\n\u001A\u0015\u0010\b\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u0001H\u0086\b\u001A\u0015\u0010\t\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\n\u001A\u00020\u0001H\u0086\b\u001AE\u0010\u000B\u001A\u00020\f*\u00020\u000226\u0010\r\u001A2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\f0\u000EH\u0086\b\u001A\u001D\u0010\u0011\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u00012\u0006\u0010\u0012\u001A\u00020\u0001H\u0086\b\u001A#\u0010\u0013\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u00012\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00010\u0014H\u0086\b\u001A\r\u0010\u0015\u001A\u00020\u0006*\u00020\u0002H\u0086\b\u001A\r\u0010\u0016\u001A\u00020\u0006*\u00020\u0002H\u0086\b\u001A\n\u0010\u0017\u001A\u00020\u0018*\u00020\u0002\u001A\u0015\u0010\u0019\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u001A\u001A\u00020\u0002H\u0086\u0002\u001A\u0012\u0010\u001B\u001A\u00020\f*\u00020\u00022\u0006\u0010\u001A\u001A\u00020\u0002\u001A\u001A\u0010\u001C\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u0001\u001A\u001D\u0010\u001D\u001A\u00020\f*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u0001H\u0086\n\u001A\n\u0010\u001E\u001A\u00020\u0018*\u00020\u0002\"\u0016\u0010\u0000\u001A\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u001F"}, d2 = {"size", "", "Landroid/util/SparseIntArray;", "getSize", "(Landroid/util/SparseIntArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "set", "valueIterator", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SparseIntArrayKt {
    public static final boolean contains(SparseIntArray sparseIntArray0, int v) {
        return sparseIntArray0.indexOfKey(v) >= 0;
    }

    public static final boolean containsKey(SparseIntArray sparseIntArray0, int v) {
        return sparseIntArray0.indexOfKey(v) >= 0;
    }

    public static final boolean containsValue(SparseIntArray sparseIntArray0, int v) {
        return sparseIntArray0.indexOfValue(v) >= 0;
    }

    public static final void forEach(SparseIntArray sparseIntArray0, Function2 function20) {
        int v = sparseIntArray0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            function20.invoke(sparseIntArray0.keyAt(v1), sparseIntArray0.valueAt(v1));
        }
    }

    public static final int getOrDefault(SparseIntArray sparseIntArray0, int v, int v1) {
        return sparseIntArray0.get(v, v1);
    }

    public static final int getOrElse(SparseIntArray sparseIntArray0, int v, Function0 function00) {
        int v1 = sparseIntArray0.indexOfKey(v);
        return v1 < 0 ? ((Number)function00.invoke()).intValue() : sparseIntArray0.valueAt(v1);
    }

    public static final int getSize(SparseIntArray sparseIntArray0) {
        return sparseIntArray0.size();
    }

    public static final boolean isEmpty(SparseIntArray sparseIntArray0) {
        return sparseIntArray0.size() == 0;
    }

    public static final boolean isNotEmpty(SparseIntArray sparseIntArray0) {
        return sparseIntArray0.size() != 0;
    }

    public static final IntIterator keyIterator(SparseIntArray sparseIntArray0) {
        return new IntIterator() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < sparseIntArray0.size();
            }

            @Override  // kotlin.collections.IntIterator
            public int nextInt() {
                int v = this.index;
                this.index = v + 1;
                return sparseIntArray0.keyAt(v);
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }

    public static final SparseIntArray plus(SparseIntArray sparseIntArray0, SparseIntArray sparseIntArray1) {
        SparseIntArray sparseIntArray2 = new SparseIntArray(sparseIntArray0.size() + sparseIntArray1.size());
        SparseIntArrayKt.putAll(sparseIntArray2, sparseIntArray0);
        SparseIntArrayKt.putAll(sparseIntArray2, sparseIntArray1);
        return sparseIntArray2;
    }

    public static final void putAll(SparseIntArray sparseIntArray0, SparseIntArray sparseIntArray1) {
        int v = sparseIntArray1.size();
        for(int v1 = 0; v1 < v; ++v1) {
            sparseIntArray0.put(sparseIntArray1.keyAt(v1), sparseIntArray1.valueAt(v1));
        }
    }

    public static final boolean remove(SparseIntArray sparseIntArray0, int v, int v1) {
        int v2 = sparseIntArray0.indexOfKey(v);
        if(v2 >= 0 && v1 == sparseIntArray0.valueAt(v2)) {
            sparseIntArray0.removeAt(v2);
            return true;
        }
        return false;
    }

    public static final void set(SparseIntArray sparseIntArray0, int v, int v1) {
        sparseIntArray0.put(v, v1);
    }

    public static final IntIterator valueIterator(SparseIntArray sparseIntArray0) {
        return new IntIterator() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < sparseIntArray0.size();
            }

            @Override  // kotlin.collections.IntIterator
            public int nextInt() {
                int v = this.index;
                this.index = v + 1;
                return sparseIntArray0.valueAt(v);
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }
}

