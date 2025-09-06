package androidx.core.util;

import android.util.SparseBooleanArray;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001A\u0015\u0010\u0005\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u0001H\u0086\n\u001A\u0015\u0010\b\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u0001H\u0086\b\u001A\u0015\u0010\t\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\n\u001A\u00020\u0006H\u0086\b\u001AE\u0010\u000B\u001A\u00020\f*\u00020\u000226\u0010\r\u001A2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\f0\u000EH\u0086\b\u001A\u001D\u0010\u0011\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u00012\u0006\u0010\u0012\u001A\u00020\u0006H\u0086\b\u001A#\u0010\u0013\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u00012\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00060\u0014H\u0086\b\u001A\r\u0010\u0015\u001A\u00020\u0006*\u00020\u0002H\u0086\b\u001A\r\u0010\u0016\u001A\u00020\u0006*\u00020\u0002H\u0086\b\u001A\n\u0010\u0017\u001A\u00020\u0018*\u00020\u0002\u001A\u0015\u0010\u0019\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u001A\u001A\u00020\u0002H\u0086\u0002\u001A\u0012\u0010\u001B\u001A\u00020\f*\u00020\u00022\u0006\u0010\u001A\u001A\u00020\u0002\u001A\u001A\u0010\u001C\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u0006\u001A\u001D\u0010\u001D\u001A\u00020\f*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u0006H\u0086\n\u001A\n\u0010\u001E\u001A\u00020\u001F*\u00020\u0002\"\u0016\u0010\u0000\u001A\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006 "}, d2 = {"size", "", "Landroid/util/SparseBooleanArray;", "getSize", "(Landroid/util/SparseBooleanArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "set", "valueIterator", "Lkotlin/collections/BooleanIterator;", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SparseBooleanArrayKt {
    public static final boolean contains(SparseBooleanArray sparseBooleanArray0, int v) {
        return sparseBooleanArray0.indexOfKey(v) >= 0;
    }

    public static final boolean containsKey(SparseBooleanArray sparseBooleanArray0, int v) {
        return sparseBooleanArray0.indexOfKey(v) >= 0;
    }

    public static final boolean containsValue(SparseBooleanArray sparseBooleanArray0, boolean z) {
        return sparseBooleanArray0.indexOfValue(z) >= 0;
    }

    public static final void forEach(SparseBooleanArray sparseBooleanArray0, Function2 function20) {
        int v = sparseBooleanArray0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            function20.invoke(sparseBooleanArray0.keyAt(v1), Boolean.valueOf(sparseBooleanArray0.valueAt(v1)));
        }
    }

    public static final boolean getOrDefault(SparseBooleanArray sparseBooleanArray0, int v, boolean z) {
        return sparseBooleanArray0.get(v, z);
    }

    public static final boolean getOrElse(SparseBooleanArray sparseBooleanArray0, int v, Function0 function00) {
        int v1 = sparseBooleanArray0.indexOfKey(v);
        return v1 < 0 ? ((Boolean)function00.invoke()).booleanValue() : sparseBooleanArray0.valueAt(v1);
    }

    public static final int getSize(SparseBooleanArray sparseBooleanArray0) {
        return sparseBooleanArray0.size();
    }

    public static final boolean isEmpty(SparseBooleanArray sparseBooleanArray0) {
        return sparseBooleanArray0.size() == 0;
    }

    public static final boolean isNotEmpty(SparseBooleanArray sparseBooleanArray0) {
        return sparseBooleanArray0.size() != 0;
    }

    public static final IntIterator keyIterator(SparseBooleanArray sparseBooleanArray0) {
        return new IntIterator() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < sparseBooleanArray0.size();
            }

            @Override  // kotlin.collections.IntIterator
            public int nextInt() {
                int v = this.index;
                this.index = v + 1;
                return sparseBooleanArray0.keyAt(v);
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }

    public static final SparseBooleanArray plus(SparseBooleanArray sparseBooleanArray0, SparseBooleanArray sparseBooleanArray1) {
        SparseBooleanArray sparseBooleanArray2 = new SparseBooleanArray(sparseBooleanArray0.size() + sparseBooleanArray1.size());
        SparseBooleanArrayKt.putAll(sparseBooleanArray2, sparseBooleanArray0);
        SparseBooleanArrayKt.putAll(sparseBooleanArray2, sparseBooleanArray1);
        return sparseBooleanArray2;
    }

    public static final void putAll(SparseBooleanArray sparseBooleanArray0, SparseBooleanArray sparseBooleanArray1) {
        int v = sparseBooleanArray1.size();
        for(int v1 = 0; v1 < v; ++v1) {
            sparseBooleanArray0.put(sparseBooleanArray1.keyAt(v1), sparseBooleanArray1.valueAt(v1));
        }
    }

    public static final boolean remove(SparseBooleanArray sparseBooleanArray0, int v, boolean z) {
        int v1 = sparseBooleanArray0.indexOfKey(v);
        if(v1 >= 0 && z == sparseBooleanArray0.valueAt(v1)) {
            sparseBooleanArray0.delete(v);
            return true;
        }
        return false;
    }

    public static final void set(SparseBooleanArray sparseBooleanArray0, int v, boolean z) {
        sparseBooleanArray0.put(v, z);
    }

    public static final BooleanIterator valueIterator(SparseBooleanArray sparseBooleanArray0) {
        return new BooleanIterator() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < sparseBooleanArray0.size();
            }

            @Override  // kotlin.collections.BooleanIterator
            public boolean nextBoolean() {
                int v = this.index;
                this.index = v + 1;
                return sparseBooleanArray0.valueAt(v);
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }
}

