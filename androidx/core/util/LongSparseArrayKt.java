package androidx.core.util;

import android.util.LongSparseArray;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010(\n\u0000\u001A!\u0010\u0006\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\tH\u0086\n\u001A!\u0010\n\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\tH\u0086\b\u001A&\u0010\u000B\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\f\u001A\u0002H\u0002H\u0086\b\u00A2\u0006\u0002\u0010\r\u001AQ\u0010\u000E\u001A\u00020\u000F\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000326\u0010\u0010\u001A2\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000F0\u0011H\u0086\b\u001A.\u0010\u0014\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u0015\u001A\u0002H\u0002H\u0086\b\u00A2\u0006\u0002\u0010\u0016\u001A4\u0010\u0017\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\t2\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0018H\u0086\b\u00A2\u0006\u0002\u0010\u0019\u001A\u0019\u0010\u001A\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\b\u001A\u0019\u0010\u001B\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\b\u001A\u0016\u0010\u001C\u001A\u00020\u001D\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001A-\u0010\u001E\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0002\u001A$\u0010 \u001A\u00020\u000F\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001A+\u0010!\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\f\u001A\u0002H\u0002\u00A2\u0006\u0002\u0010\"\u001A.\u0010#\u001A\u00020\u000F\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\f\u001A\u0002H\u0002H\u0086\n\u00A2\u0006\u0002\u0010$\u001A\u001C\u0010%\u001A\b\u0012\u0004\u0012\u0002H\u00020&\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\"\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005\u00A8\u0006\'"}, d2 = {"size", "", "T", "Landroid/util/LongSparseArray;", "getSize", "(Landroid/util/LongSparseArray;)I", "contains", "", "key", "", "containsKey", "containsValue", "value", "(Landroid/util/LongSparseArray;Ljava/lang/Object;)Z", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Landroid/util/LongSparseArray;JLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/LongIterator;", "plus", "other", "putAll", "remove", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Z", "set", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)V", "valueIterator", "", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LongSparseArrayKt {
    public static final boolean contains(LongSparseArray longSparseArray0, long v) {
        return longSparseArray0.indexOfKey(v) >= 0;
    }

    public static final boolean containsKey(LongSparseArray longSparseArray0, long v) {
        return longSparseArray0.indexOfKey(v) >= 0;
    }

    public static final boolean containsValue(LongSparseArray longSparseArray0, Object object0) {
        return longSparseArray0.indexOfValue(object0) >= 0;
    }

    public static final void forEach(LongSparseArray longSparseArray0, Function2 function20) {
        int v = longSparseArray0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            function20.invoke(longSparseArray0.keyAt(v1), longSparseArray0.valueAt(v1));
        }
    }

    public static final Object getOrDefault(LongSparseArray longSparseArray0, long v, Object object0) {
        Object object1 = longSparseArray0.get(v);
        return object1 == null ? object0 : object1;
    }

    public static final Object getOrElse(LongSparseArray longSparseArray0, long v, Function0 function00) {
        Object object0 = longSparseArray0.get(v);
        return object0 == null ? function00.invoke() : object0;
    }

    public static final int getSize(LongSparseArray longSparseArray0) {
        return longSparseArray0.size();
    }

    public static final boolean isEmpty(LongSparseArray longSparseArray0) {
        return longSparseArray0.size() == 0;
    }

    public static final boolean isNotEmpty(LongSparseArray longSparseArray0) {
        return longSparseArray0.size() != 0;
    }

    public static final LongIterator keyIterator(LongSparseArray longSparseArray0) {
        return new LongIterator() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < longSparseArray0.size();
            }

            @Override  // kotlin.collections.LongIterator
            public long nextLong() {
                int v = this.index;
                this.index = v + 1;
                return longSparseArray0.keyAt(v);
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }

    public static final LongSparseArray plus(LongSparseArray longSparseArray0, LongSparseArray longSparseArray1) {
        LongSparseArray longSparseArray2 = new LongSparseArray(longSparseArray0.size() + longSparseArray1.size());
        LongSparseArrayKt.putAll(longSparseArray2, longSparseArray0);
        LongSparseArrayKt.putAll(longSparseArray2, longSparseArray1);
        return longSparseArray2;
    }

    public static final void putAll(LongSparseArray longSparseArray0, LongSparseArray longSparseArray1) {
        int v = longSparseArray1.size();
        for(int v1 = 0; v1 < v; ++v1) {
            longSparseArray0.put(longSparseArray1.keyAt(v1), longSparseArray1.valueAt(v1));
        }
    }

    public static final boolean remove(LongSparseArray longSparseArray0, long v, Object object0) {
        int v1 = longSparseArray0.indexOfKey(v);
        if(v1 >= 0 && Intrinsics.areEqual(object0, longSparseArray0.valueAt(v1))) {
            longSparseArray0.removeAt(v1);
            return true;
        }
        return false;
    }

    public static final void set(LongSparseArray longSparseArray0, long v, Object object0) {
        longSparseArray0.put(v, object0);
    }

    public static final Iterator valueIterator(LongSparseArray longSparseArray0) {
        return new Object() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < longSparseArray0.size();
            }

            @Override
            public Object next() {
                int v = this.index;
                this.index = v + 1;
                return longSparseArray0.valueAt(v);
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

