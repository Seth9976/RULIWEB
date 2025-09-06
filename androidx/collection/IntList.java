package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u000F\b\u0004\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001A\u00020\u0015J:\u0010\u0014\u001A\u00020\u00152!\u0010\u0016\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\u00150\u0017H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0011\u0010\u001B\u001A\u00020\u00152\u0006\u0010\u001A\u001A\u00020\u0003H\u0086\u0002J\u000E\u0010\u001C\u001A\u00020\u00152\u0006\u0010\u001D\u001A\u00020\u0000J\u0006\u0010\u001E\u001A\u00020\u0003J:\u0010\u001E\u001A\u00020\u00032!\u0010\u0016\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\u00150\u0017H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0010\u0010\u001F\u001A\u00020\u00032\b\b\u0001\u0010 \u001A\u00020\u0003J9\u0010!\u001A\u00020\u00032\b\b\u0001\u0010 \u001A\u00020\u00032!\u0010\"\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00030\u0017H\u0086\b\u00F8\u0001\u0000J\u0013\u0010#\u001A\u00020\u00152\b\u0010$\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\u0006\u0010%\u001A\u00020\u0003J:\u0010%\u001A\u00020\u00032!\u0010\u0016\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\u00150\u0017H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001Jb\u0010&\u001A\u0002H\'\"\u0004\b\u0000\u0010\'2\u0006\u0010(\u001A\u0002H\'26\u0010)\u001A2\u0012\u0013\u0012\u0011H\'\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u0002H\'0*H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u00A2\u0006\u0002\u0010,Jw\u0010-\u001A\u0002H\'\"\u0004\b\u0000\u0010\'2\u0006\u0010(\u001A\u0002H\'2K\u0010)\u001AG\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b( \u0012\u0013\u0012\u0011H\'\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u0002H\'0.H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u00A2\u0006\u0002\u0010/Jb\u00100\u001A\u0002H\'\"\u0004\b\u0000\u0010\'2\u0006\u0010(\u001A\u0002H\'26\u0010)\u001A2\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0013\u0012\u0011H\'\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\'0*H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u00A2\u0006\u0002\u0010,Jw\u00101\u001A\u0002H\'\"\u0004\b\u0000\u0010\'2\u0006\u0010(\u001A\u0002H\'2K\u0010)\u001AG\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0013\u0012\u0011H\'\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\'0.H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u00A2\u0006\u0002\u0010/J:\u00102\u001A\u0002032!\u00104\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u0002030\u0017H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001JO\u00105\u001A\u00020326\u00104\u001A2\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u0002030*H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J:\u00106\u001A\u0002032!\u00104\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u0002030\u0017H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001JO\u00107\u001A\u00020326\u00104\u001A2\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u0002030*H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0013\u00108\u001A\u00020\u00032\b\b\u0001\u0010 \u001A\u00020\u0003H\u0086\u0002J\b\u00109\u001A\u00020\u0003H\u0016J\u000E\u0010:\u001A\u00020\u00032\u0006\u0010\u001A\u001A\u00020\u0003J:\u0010;\u001A\u00020\u00032!\u0010\u0016\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\u00150\u0017H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J:\u0010<\u001A\u00020\u00032!\u0010\u0016\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\u00150\u0017H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0006\u0010=\u001A\u00020\u0015J\u0006\u0010>\u001A\u00020\u0015J:\u0010?\u001A\u00020@2\b\b\u0002\u0010A\u001A\u00020B2\b\b\u0002\u0010C\u001A\u00020B2\b\b\u0002\u0010D\u001A\u00020B2\b\b\u0002\u0010E\u001A\u00020\u00032\b\b\u0002\u0010F\u001A\u00020BH\u0007JT\u0010?\u001A\u00020@2\b\b\u0002\u0010A\u001A\u00020B2\b\b\u0002\u0010C\u001A\u00020B2\b\b\u0002\u0010D\u001A\u00020B2\b\b\u0002\u0010E\u001A\u00020\u00032\b\b\u0002\u0010F\u001A\u00020B2\u0014\b\u0004\u0010G\u001A\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020B0\u0017H\u0087\b\u00F8\u0001\u0000J\u0006\u0010H\u001A\u00020\u0003J:\u0010H\u001A\u00020\u00032!\u0010\u0016\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\u00150\u0017H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u000E\u0010I\u001A\u00020\u00032\u0006\u0010\u001A\u001A\u00020\u0003J\u0006\u0010J\u001A\u00020\u0015J:\u0010K\u001A\u00020\u00152!\u0010\u0016\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\u00150\u0017H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\b\u0010L\u001A\u00020@H\u0016R\u0018\u0010\u0005\u001A\u00020\u00038\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001A\u00020\t8\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0007R\u0012\u0010\u000B\u001A\u00020\f8\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\r\u0010\u000ER\u0012\u0010\u000F\u001A\u00020\u00038\u00C7\u0002\u00A2\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001A\u00020\u00038G\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u0011\u0082\u0001\u0001M\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006N"}, d2 = {"Landroidx/collection/IntList;", "", "initialCapacity", "", "(I)V", "_size", "get_size$annotations", "()V", "content", "", "getContent$annotations", "indices", "Lkotlin/ranges/IntRange;", "getIndices", "()Lkotlin/ranges/IntRange;", "lastIndex", "getLastIndex", "()I", "size", "getSize", "any", "", "predicate", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "element", "contains", "containsAll", "elements", "count", "elementAt", "index", "elementAtOrElse", "defaultValue", "equals", "other", "first", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "acc", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "forEach", "", "block", "forEachIndexed", "forEachReversed", "forEachReversedIndexed", "get", "hashCode", "indexOf", "indexOfFirst", "indexOfLast", "isEmpty", "isNotEmpty", "joinToString", "", "separator", "", "prefix", "postfix", "limit", "truncated", "transform", "last", "lastIndexOf", "none", "reversedAny", "toString", "Landroidx/collection/MutableIntList;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class IntList {
    public int _size;
    public int[] content;

    private IntList(int v) {
        this.content = v == 0 ? IntSetKt.getEmptyIntArray() : new int[v];
    }

    public IntList(int v, DefaultConstructorMarker defaultConstructorMarker0) {
        this(v);
    }

    public final boolean any() {
        return this.isNotEmpty();
    }

    public final boolean any(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((Boolean)function10.invoke(((int)arr_v[v1]))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public final boolean contains(int v) {
        int[] arr_v = this.content;
        int v1 = this._size;
        for(int v2 = 0; v2 < v1; ++v2) {
            if(arr_v[v2] == v) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsAll(IntList intList0) {
        Intrinsics.checkNotNullParameter(intList0, "elements");
        IntRange intRange0 = RangesKt.until(0, intList0._size);
        int v = intRange0.getFirst();
        int v1 = intRange0.getLast();
        if(v <= v1) {
            while(true) {
                if(!this.contains(intList0.get(v))) {
                    return false;
                }
                if(v == v1) {
                    break;
                }
                ++v;
            }
        }
        return true;
    }

    public final int count() {
        return this._size;
    }

    public final int count(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        int[] arr_v = this.content;
        int v = this._size;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((Boolean)function10.invoke(((int)arr_v[v1]))).booleanValue()) {
                ++v2;
            }
        }
        return v2;
    }

    public final int elementAt(int v) {
        if(v < 0 || v >= this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + (this._size - 1));
        }
        return this.content[v];
    }

    public final int elementAtOrElse(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "defaultValue");
        return v < 0 || v >= this._size ? ((Number)function10.invoke(v)).intValue() : this.content[v];
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 instanceof IntList) {
            int v = this._size;
            if(((IntList)object0)._size == v) {
                int[] arr_v = this.content;
                int[] arr_v1 = ((IntList)object0).content;
                IntRange intRange0 = RangesKt.until(0, v);
                int v1 = intRange0.getFirst();
                int v2 = intRange0.getLast();
                if(v1 <= v2) {
                    while(true) {
                        if(arr_v[v1] != arr_v1[v1]) {
                            return false;
                        }
                        if(v1 == v2) {
                            break;
                        }
                        ++v1;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final int first() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("IntList is empty.");
        }
        return this.content[0];
    }

    public final int first(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = arr_v[v1];
            if(((Boolean)function10.invoke(v2)).booleanValue()) {
                return v2;
            }
        }
        throw new NoSuchElementException("IntList contains no element matching the predicate.");
    }

    public final Object fold(Object object0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "operation");
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            object0 = function20.invoke(object0, ((int)arr_v[v1]));
        }
        return object0;
    }

    public final Object foldIndexed(Object object0, Function3 function30) {
        Intrinsics.checkNotNullParameter(function30, "operation");
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            object0 = function30.invoke(v1, object0, ((int)arr_v[v1]));
        }
        return object0;
    }

    public final Object foldRight(Object object0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "operation");
        int[] arr_v = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            object0 = function20.invoke(((int)arr_v[v]), object0);
        }
        return object0;
    }

    public final Object foldRightIndexed(Object object0, Function3 function30) {
        Intrinsics.checkNotNullParameter(function30, "operation");
        int[] arr_v = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            object0 = function30.invoke(v, ((int)arr_v[v]), object0);
        }
        return object0;
    }

    public final void forEach(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            function10.invoke(((int)arr_v[v1]));
        }
    }

    public final void forEachIndexed(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "block");
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            function20.invoke(v1, ((int)arr_v[v1]));
        }
    }

    public final void forEachReversed(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        int[] arr_v = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            function10.invoke(((int)arr_v[v]));
        }
    }

    public final void forEachReversedIndexed(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "block");
        int[] arr_v = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            function20.invoke(v, ((int)arr_v[v]));
        }
    }

    public final int get(int v) {
        if(v < 0 || v >= this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + (this._size - 1));
        }
        return this.content[v];
    }

    public static void getContent$annotations() {
    }

    public final IntRange getIndices() {
        return RangesKt.until(0, this._size);
    }

    public final int getLastIndex() {
        return this._size - 1;
    }

    public final int getSize() {
        return this._size;
    }

    public static void get_size$annotations() {
    }

    @Override
    public int hashCode() {
        int[] arr_v = this.content;
        int v = this._size;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 += arr_v[v1] * 0x1F;
        }
        return v2;
    }

    public final int indexOf(int v) {
        int[] arr_v = this.content;
        int v1 = this._size;
        for(int v2 = 0; v2 < v1; ++v2) {
            if(v == arr_v[v2]) {
                return v2;
            }
        }
        return -1;
    }

    public final int indexOfFirst(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((Boolean)function10.invoke(((int)arr_v[v1]))).booleanValue()) {
                return v1;
            }
        }
        return -1;
    }

    public final int indexOfLast(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        int[] arr_v = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            if(((Boolean)function10.invoke(((int)arr_v[v]))).booleanValue()) {
                return v;
            }
        }
        return -1;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final boolean isNotEmpty() {
        return this._size != 0;
    }

    public final String joinToString() {
        return IntList.joinToString$default(this, null, null, null, 0, null, 0x1F, null);
    }

    public final String joinToString(CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        return IntList.joinToString$default(this, charSequence0, null, null, 0, null, 30, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        return IntList.joinToString$default(this, charSequence0, charSequence1, null, 0, null, 28, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return IntList.joinToString$default(this, charSequence0, charSequence1, charSequence2, 0, null, 24, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return IntList.joinToString$default(this, charSequence0, charSequence1, charSequence2, v, null, 16, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.content;
        int v1 = this._size;
        for(int v2 = 0; true; ++v2) {
            if(v2 >= v1) {
                stringBuilder0.append(charSequence2);
                break;
            }
            int v3 = arr_v[v2];
            if(v2 == v) {
                stringBuilder0.append(charSequence3);
                break;
            }
            if(v2 != 0) {
                stringBuilder0.append(charSequence0);
            }
            stringBuilder0.append(v3);
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.content;
        int v1 = this._size;
        for(int v2 = 0; true; ++v2) {
            if(v2 >= v1) {
                stringBuilder0.append(charSequence2);
                break;
            }
            int v3 = arr_v[v2];
            if(v2 == v) {
                stringBuilder0.append(charSequence3);
                break;
            }
            if(v2 != 0) {
                stringBuilder0.append(charSequence0);
            }
            stringBuilder0.append(((CharSequence)function10.invoke(v3)));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.content;
        int v1 = this._size;
        for(int v2 = 0; true; ++v2) {
            if(v2 >= v1) {
                stringBuilder0.append(charSequence2);
                break;
            }
            int v3 = arr_v[v2];
            if(v2 == v) {
                stringBuilder0.append("...");
                break;
            }
            if(v2 != 0) {
                stringBuilder0.append(charSequence0);
            }
            stringBuilder0.append(((CharSequence)function10.invoke(v3)));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; true; ++v1) {
            if(v1 >= v) {
                stringBuilder0.append(charSequence2);
                break;
            }
            int v2 = arr_v[v1];
            if(v1 == -1) {
                stringBuilder0.append("...");
                break;
            }
            if(v1 != 0) {
                stringBuilder0.append(charSequence0);
            }
            stringBuilder0.append(((CharSequence)function10.invoke(v2)));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; true; ++v1) {
            if(v1 >= v) {
                stringBuilder0.append("");
                break;
            }
            int v2 = arr_v[v1];
            if(v1 == -1) {
                stringBuilder0.append("...");
                break;
            }
            if(v1 != 0) {
                stringBuilder0.append(charSequence0);
            }
            stringBuilder0.append(((CharSequence)function10.invoke(v2)));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("");
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; true; ++v1) {
            if(v1 >= v) {
                stringBuilder0.append("");
                break;
            }
            int v2 = arr_v[v1];
            if(v1 == -1) {
                stringBuilder0.append("...");
                break;
            }
            if(v1 != 0) {
                stringBuilder0.append(charSequence0);
            }
            stringBuilder0.append(((CharSequence)function10.invoke(v2)));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("");
        int[] arr_v = this.content;
        int v = this._size;
        for(int v1 = 0; true; ++v1) {
            if(v1 >= v) {
                stringBuilder0.append("");
                break;
            }
            int v2 = arr_v[v1];
            if(v1 == -1) {
                stringBuilder0.append("...");
                break;
            }
            if(v1 != 0) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(((CharSequence)function10.invoke(v2)));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public static String joinToString$default(IntList intList0, CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if((v1 & 1) != 0) {
            charSequence0 = ", ";
        }
        if((v1 & 2) != 0) {
            charSequence1 = "";
        }
        if((v1 & 4) != 0) {
            charSequence2 = "";
        }
        if((v1 & 8) != 0) {
            v = -1;
        }
        if((v1 & 16) != 0) {
            charSequence3 = "...";
        }
        return intList0.joinToString(charSequence0, charSequence1, charSequence2, v, charSequence3);
    }

    public static String joinToString$default(IntList intList0, CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function1 function10, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if((v1 & 1) != 0) {
            charSequence0 = ", ";
        }
        if((v1 & 2) != 0) {
            charSequence1 = "";
        }
        if((v1 & 4) != 0) {
            charSequence2 = "";
        }
        if((v1 & 8) != 0) {
            v = -1;
        }
        if((v1 & 16) != 0) {
            charSequence3 = "...";
        }
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        int[] arr_v = intList0.content;
        int v2 = intList0._size;
        for(int v3 = 0; true; ++v3) {
            if(v3 >= v2) {
                stringBuilder0.append(charSequence2);
                break;
            }
            int v4 = arr_v[v3];
            if(v3 == v) {
                stringBuilder0.append(charSequence3);
                break;
            }
            if(v3 != 0) {
                stringBuilder0.append(charSequence0);
            }
            stringBuilder0.append(((CharSequence)function10.invoke(v4)));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final int last() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("IntList is empty.");
        }
        return this.content[this._size - 1];
    }

    public final int last(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        int[] arr_v = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            int v1 = arr_v[v];
            if(((Boolean)function10.invoke(v1)).booleanValue()) {
                return v1;
            }
        }
        throw new NoSuchElementException("IntList contains no element matching the predicate.");
    }

    public final int lastIndexOf(int v) {
        int[] arr_v = this.content;
        for(int v1 = this._size - 1; -1 < v1; --v1) {
            if(arr_v[v1] == v) {
                return v1;
            }
        }
        return -1;
    }

    public final boolean none() {
        return this.isEmpty();
    }

    public final boolean reversedAny(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        int[] arr_v = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            if(((Boolean)function10.invoke(((int)arr_v[v]))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return IntList.joinToString$default(this, null, "[", "]", 0, null, 25, null);
    }
}

