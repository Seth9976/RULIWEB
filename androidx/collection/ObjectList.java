package androidx.collection;

import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u001C\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\r\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000F\b\u0004\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0006\u0010\u0016\u001A\u00020\u0017J:\u0010\u0016\u001A\u00020\u00172!\u0010\u0018\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u000E\u0010\u001D\u001A\b\u0012\u0004\u0012\u00028\u00000\u001EH&J\u0016\u0010\u001F\u001A\u00020\u00172\u0006\u0010\u001C\u001A\u00028\u0000H\u0086\u0002\u00A2\u0006\u0002\u0010 J\u0014\u0010!\u001A\u00020\u00172\f\u0010\"\u001A\b\u0012\u0004\u0012\u00028\u00000\u0000J\u0019\u0010!\u001A\u00020\u00172\f\u0010\"\u001A\b\u0012\u0004\u0012\u00028\u00000\n\u00A2\u0006\u0002\u0010#J\u0014\u0010!\u001A\u00020\u00172\f\u0010\"\u001A\b\u0012\u0004\u0012\u00028\u00000$J\u0014\u0010!\u001A\u00020\u00172\f\u0010\"\u001A\b\u0012\u0004\u0012\u00028\u00000\u001EJ\u0006\u0010%\u001A\u00020\u0004J:\u0010%\u001A\u00020\u00042!\u0010\u0018\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0015\u0010&\u001A\u00028\u00002\b\b\u0001\u0010\'\u001A\u00020\u0004\u00A2\u0006\u0002\u0010(J>\u0010)\u001A\u00028\u00002\b\b\u0001\u0010\'\u001A\u00020\u00042!\u0010*\u001A\u001D\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\'\u0012\u0004\u0012\u00028\u00000\u0019H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010+J\u0013\u0010,\u001A\u00020\u00172\b\u0010-\u001A\u0004\u0018\u00010\u0002H\u0096\u0002J\u000B\u0010.\u001A\u00028\u0000\u00A2\u0006\u0002\u0010/J?\u0010.\u001A\u00028\u00002!\u0010\u0018\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u00A2\u0006\u0002\u00100J\u0010\u00101\u001A\u0004\u0018\u00018\u0000H\u0086\b\u00A2\u0006\u0002\u0010/JA\u00101\u001A\u0004\u0018\u00018\u00002!\u0010\u0018\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u00A2\u0006\u0002\u00100Jb\u00102\u001A\u0002H3\"\u0004\b\u0001\u001032\u0006\u00104\u001A\u0002H326\u00105\u001A2\u0012\u0013\u0012\u0011H3\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(7\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u0002H306H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u00A2\u0006\u0002\u00108Jw\u00109\u001A\u0002H3\"\u0004\b\u0001\u001032\u0006\u00104\u001A\u0002H32K\u00105\u001AG\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\'\u0012\u0013\u0012\u0011H3\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(7\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u0002H30:H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u00A2\u0006\u0002\u0010;Jb\u0010<\u001A\u0002H3\"\u0004\b\u0001\u001032\u0006\u00104\u001A\u0002H326\u00105\u001A2\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0013\u0012\u0011H3\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002H306H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u00A2\u0006\u0002\u00108Jw\u0010=\u001A\u0002H3\"\u0004\b\u0001\u001032\u0006\u00104\u001A\u0002H32K\u00105\u001AG\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\'\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0013\u0012\u0011H3\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002H30:H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u00A2\u0006\u0002\u0010;J:\u0010>\u001A\u00020?2!\u0010@\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020?0\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001JO\u0010A\u001A\u00020?26\u0010@\u001A2\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\'\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020?06H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J:\u0010B\u001A\u00020?2!\u0010@\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020?0\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001JO\u0010C\u001A\u00020?26\u0010@\u001A2\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\'\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020?06H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0018\u0010D\u001A\u00028\u00002\b\b\u0001\u0010\'\u001A\u00020\u0004H\u0086\u0002\u00A2\u0006\u0002\u0010(J\b\u0010E\u001A\u00020\u0004H\u0016J\u0013\u0010F\u001A\u00020\u00042\u0006\u0010\u001C\u001A\u00028\u0000\u00A2\u0006\u0002\u0010GJ:\u0010H\u001A\u00020\u00042!\u0010\u0018\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J:\u0010I\u001A\u00020\u00042!\u0010\u0018\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0006\u0010J\u001A\u00020\u0017J\u0006\u0010K\u001A\u00020\u0017JR\u0010L\u001A\u00020M2\b\b\u0002\u0010N\u001A\u00020O2\b\b\u0002\u0010P\u001A\u00020O2\b\b\u0002\u0010Q\u001A\u00020O2\b\b\u0002\u0010R\u001A\u00020\u00042\b\b\u0002\u0010S\u001A\u00020O2\u0016\b\u0002\u0010T\u001A\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020O\u0018\u00010\u0019H\u0007J\u000B\u0010U\u001A\u00028\u0000\u00A2\u0006\u0002\u0010/J?\u0010U\u001A\u00028\u00002!\u0010\u0018\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u00A2\u0006\u0002\u00100J\u0013\u0010V\u001A\u00020\u00042\u0006\u0010\u001C\u001A\u00028\u0000\u00A2\u0006\u0002\u0010GJ\u0010\u0010W\u001A\u0004\u0018\u00018\u0000H\u0086\b\u00A2\u0006\u0002\u0010/JA\u0010W\u001A\u0004\u0018\u00018\u00002!\u0010\u0018\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u00A2\u0006\u0002\u00100J\u0006\u0010X\u001A\u00020\u0017J:\u0010Y\u001A\u00020\u00172!\u0010\u0018\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u001A\u0012\b\b\u001B\u0012\u0004\b\b(\u001C\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\b\u0010Z\u001A\u00020MH\u0016R\u0018\u0010\u0006\u001A\u00020\u00048\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\bR\"\u0010\t\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n8\u0000@\u0000X\u0081\u000E\u00A2\u0006\n\n\u0002\u0010\f\u0012\u0004\b\u000B\u0010\bR\u0012\u0010\r\u001A\u00020\u000E8\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R\u0012\u0010\u0011\u001A\u00020\u00048\u00C7\u0002\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001A\u00020\u00048G\u00A2\u0006\u0006\u001A\u0004\b\u0015\u0010\u0013\u0082\u0001\u0001[\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006\\"}, d2 = {"Landroidx/collection/ObjectList;", "E", "", "initialCapacity", "", "(I)V", "_size", "get_size$annotations", "()V", "content", "", "getContent$annotations", "[Ljava/lang/Object;", "indices", "Lkotlin/ranges/IntRange;", "getIndices", "()Lkotlin/ranges/IntRange;", "lastIndex", "getLastIndex", "()I", "size", "getSize", "any", "", "predicate", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "element", "asList", "", "contains", "(Ljava/lang/Object;)Z", "containsAll", "elements", "([Ljava/lang/Object;)Z", "", "count", "elementAt", "index", "(I)Ljava/lang/Object;", "elementAtOrElse", "defaultValue", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "equals", "other", "first", "()Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "firstOrNull", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "acc", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "forEach", "", "block", "forEachIndexed", "forEachReversed", "forEachReversedIndexed", "get", "hashCode", "indexOf", "(Ljava/lang/Object;)I", "indexOfFirst", "indexOfLast", "isEmpty", "isNotEmpty", "joinToString", "", "separator", "", "prefix", "postfix", "limit", "truncated", "transform", "last", "lastIndexOf", "lastOrNull", "none", "reversedAny", "toString", "Landroidx/collection/MutableObjectList;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ObjectList {
    public int _size;
    public Object[] content;

    private ObjectList(int v) {
        this.content = v == 0 ? ObjectListKt.EmptyArray : new Object[v];
    }

    public ObjectList(int v, DefaultConstructorMarker defaultConstructorMarker0) {
        this(v);
    }

    public final boolean any() {
        return this.isNotEmpty();
    }

    public final boolean any(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((Boolean)function10.invoke(arr_object[v1])).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public abstract List asList();

    public final boolean contains(Object object0) {
        return this.indexOf(object0) >= 0;
    }

    public final boolean containsAll(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        Object[] arr_object = objectList0.content;
        int v = objectList0._size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(!this.contains(arr_object[v1])) {
                return false;
            }
        }
        return true;
    }

    public final boolean containsAll(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        for(Object object0: iterable0) {
            if(!this.contains(object0)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public final boolean containsAll(List list0) {
        Intrinsics.checkNotNullParameter(list0, "elements");
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(!this.contains(list0.get(v1))) {
                return false;
            }
        }
        return true;
    }

    public final boolean containsAll(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        for(int v = 0; v < arr_object.length; ++v) {
            if(!this.contains(arr_object[v])) {
                return false;
            }
        }
        return true;
    }

    public final int count() {
        return this._size;
    }

    public final int count(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.content;
        int v = this._size;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((Boolean)function10.invoke(arr_object[v1])).booleanValue()) {
                ++v2;
            }
        }
        return v2;
    }

    public final Object elementAt(int v) {
        if(v < 0 || v >= this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + (this._size - 1));
        }
        return this.content[v];
    }

    public final Object elementAtOrElse(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "defaultValue");
        return v < 0 || v >= this._size ? function10.invoke(v) : this.content[v];
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 instanceof ObjectList) {
            int v = this._size;
            if(((ObjectList)object0)._size == v) {
                Object[] arr_object = this.content;
                Object[] arr_object1 = ((ObjectList)object0).content;
                IntRange intRange0 = RangesKt.until(0, v);
                int v1 = intRange0.getFirst();
                int v2 = intRange0.getLast();
                if(v1 <= v2) {
                    while(true) {
                        if(!Intrinsics.areEqual(arr_object[v1], arr_object1[v1])) {
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

    public final Object first() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("ObjectList is empty.");
        }
        return this.content[0];
    }

    public final Object first(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = arr_object[v1];
            if(((Boolean)function10.invoke(object0)).booleanValue()) {
                return object0;
            }
        }
        throw new NoSuchElementException("ObjectList contains no element matching the predicate.");
    }

    // 去混淆评级： 低(20)
    public final Object firstOrNull() {
        return this.isEmpty() ? null : this.get(0);
    }

    public final Object firstOrNull(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = arr_object[v1];
            if(((Boolean)function10.invoke(object0)).booleanValue()) {
                return object0;
            }
        }
        return null;
    }

    public final Object fold(Object object0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "operation");
        Object[] arr_object = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            object0 = function20.invoke(object0, arr_object[v1]);
        }
        return object0;
    }

    public final Object foldIndexed(Object object0, Function3 function30) {
        Intrinsics.checkNotNullParameter(function30, "operation");
        Object[] arr_object = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            object0 = function30.invoke(v1, object0, arr_object[v1]);
        }
        return object0;
    }

    public final Object foldRight(Object object0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "operation");
        Object[] arr_object = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            object0 = function20.invoke(arr_object[v], object0);
        }
        return object0;
    }

    public final Object foldRightIndexed(Object object0, Function3 function30) {
        Intrinsics.checkNotNullParameter(function30, "operation");
        Object[] arr_object = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            object0 = function30.invoke(v, arr_object[v], object0);
        }
        return object0;
    }

    public final void forEach(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        Object[] arr_object = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            function10.invoke(arr_object[v1]);
        }
    }

    public final void forEachIndexed(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "block");
        Object[] arr_object = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            function20.invoke(v1, arr_object[v1]);
        }
    }

    public final void forEachReversed(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        Object[] arr_object = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            function10.invoke(arr_object[v]);
        }
    }

    public final void forEachReversedIndexed(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "block");
        Object[] arr_object = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            function20.invoke(v, arr_object[v]);
        }
    }

    public final Object get(int v) {
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
        Object[] arr_object = this.content;
        int v = this._size;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = arr_object[v1];
            v2 += (object0 == null ? 0 : object0.hashCode()) * 0x1F;
        }
        return v2;
    }

    public final int indexOf(Object object0) {
        int v = 0;
        if(object0 == null) {
            Object[] arr_object = this.content;
            int v1 = this._size;
            while(v < v1) {
                if(arr_object[v] == null) {
                    return v;
                }
                ++v;
            }
            return -1;
        }
        Object[] arr_object1 = this.content;
        int v2 = this._size;
        while(v < v2) {
            if(object0.equals(arr_object1[v])) {
                return v;
            }
            ++v;
        }
        return -1;
    }

    public final int indexOfFirst(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.content;
        int v = this._size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((Boolean)function10.invoke(arr_object[v1])).booleanValue()) {
                return v1;
            }
        }
        return -1;
    }

    public final int indexOfLast(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            if(((Boolean)function10.invoke(arr_object[v])).booleanValue()) {
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
        return ObjectList.joinToString$default(this, null, null, null, 0, null, null, 0x3F, null);
    }

    public final String joinToString(CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        return ObjectList.joinToString$default(this, charSequence0, null, null, 0, null, null, 62, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        return ObjectList.joinToString$default(this, charSequence0, charSequence1, null, 0, null, null, 60, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return ObjectList.joinToString$default(this, charSequence0, charSequence1, charSequence2, 0, null, null, 56, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return ObjectList.joinToString$default(this, charSequence0, charSequence1, charSequence2, v, null, null, 0x30, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        return ObjectList.joinToString$default(this, charSequence0, charSequence1, charSequence2, v, charSequence3, null, 0x20, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        Object[] arr_object = this.content;
        int v1 = this._size;
        for(int v2 = 0; true; ++v2) {
            if(v2 >= v1) {
                stringBuilder0.append(charSequence2);
                break;
            }
            Object object0 = arr_object[v2];
            if(v2 == v) {
                stringBuilder0.append(charSequence3);
                break;
            }
            if(v2 != 0) {
                stringBuilder0.append(charSequence0);
            }
            if(function10 == null) {
                stringBuilder0.append(object0);
            }
            else {
                stringBuilder0.append(((CharSequence)function10.invoke(object0)));
            }
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public static String joinToString$default(ObjectList objectList0, CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function1 function10, int v1, Object object0) {
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
        if((v1 & 0x20) != 0) {
            function10 = null;
        }
        return objectList0.joinToString(charSequence0, charSequence1, charSequence2, v, charSequence3, function10);
    }

    public final Object last() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("ObjectList is empty.");
        }
        return this.content[this._size - 1];
    }

    public final Object last(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            Object object0 = arr_object[v];
            if(((Boolean)function10.invoke(object0)).booleanValue()) {
                return object0;
            }
        }
        throw new NoSuchElementException("ObjectList contains no element matching the predicate.");
    }

    public final int lastIndexOf(Object object0) {
        if(object0 == null) {
            Object[] arr_object = this.content;
            for(int v = this._size - 1; -1 < v; --v) {
                if(arr_object[v] == null) {
                    return v;
                }
            }
            return -1;
        }
        Object[] arr_object1 = this.content;
        for(int v1 = this._size - 1; -1 < v1; --v1) {
            if(object0.equals(arr_object1[v1])) {
                return v1;
            }
        }
        return -1;
    }

    // 去混淆评级： 低(20)
    public final Object lastOrNull() {
        return this.isEmpty() ? null : this.content[this._size - 1];
    }

    public final Object lastOrNull(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            Object object0 = arr_object[v];
            if(((Boolean)function10.invoke(object0)).booleanValue()) {
                return object0;
            }
        }
        return null;
    }

    public final boolean none() {
        return this.isEmpty();
    }

    public final boolean reversedAny(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.content;
        for(int v = this._size - 1; -1 < v; --v) {
            if(((Boolean)function10.invoke(arr_object[v])).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return ObjectList.joinToString$default(this, null, "[", "]", 0, null, new Function1() {
            {
                ObjectList.this = objectList0;
                super(1);
            }

            public final CharSequence invoke(Object object0) {
                return object0 == ObjectList.this ? "(this)" : String.valueOf(object0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(object0);
            }
        }, 25, null);
    }
}

