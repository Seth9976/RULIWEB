package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001E\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001B\b\u0007\u0018\u0000 P*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001PB\u000F\b\u0016\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005B\u0007\b\u0016\u00A2\u0006\u0002\u0010\u0006B\u0015\b\u0016\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\b\u00A2\u0006\u0002\u0010\tJ\u0015\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u0016J\u001D\u0010\u0013\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u0019J\u001E\u0010\u001A\u001A\u00020\u00142\u0006\u0010\u0018\u001A\u00020\u00042\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0016\u0010\u001A\u001A\u00020\u00142\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0013\u0010\u001B\u001A\u00020\u00172\u0006\u0010\u0015\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u001CJ\u0013\u0010\u001D\u001A\u00020\u00172\u0006\u0010\u0015\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u001CJ\b\u0010\u001E\u001A\u00020\u0017H\u0016J\u0016\u0010\u001F\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00028\u0000H\u0096\u0002\u00A2\u0006\u0002\u0010\u0016J\u001E\u0010 \u001A\u00020\u00172\u0006\u0010!\u001A\u00020\u00042\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bH\u0002J\u0010\u0010\"\u001A\u00020\u00172\u0006\u0010#\u001A\u00020\u0004H\u0002J\u0010\u0010$\u001A\u00020\u00042\u0006\u0010\u0018\u001A\u00020\u0004H\u0002J\u0010\u0010%\u001A\u00020\u00172\u0006\u0010&\u001A\u00020\u0004H\u0002J\u001D\u0010\'\u001A\u00020\u00142\u0012\u0010(\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140)H\u0082\bJ\u000B\u0010*\u001A\u00028\u0000\u00A2\u0006\u0002\u0010+J\r\u0010,\u001A\u0004\u0018\u00018\u0000\u00A2\u0006\u0002\u0010+J\u0016\u0010-\u001A\u00028\u00002\u0006\u0010\u0018\u001A\u00020\u0004H\u0096\u0002\u00A2\u0006\u0002\u0010.J\u0010\u0010/\u001A\u00020\u00042\u0006\u0010\u0018\u001A\u00020\u0004H\u0002J\u0015\u00100\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u00101J\u0016\u00102\u001A\u00028\u00002\u0006\u0010!\u001A\u00020\u0004H\u0083\b\u00A2\u0006\u0002\u0010.J\u0011\u0010!\u001A\u00020\u00042\u0006\u0010\u0018\u001A\u00020\u0004H\u0083\bJM\u00103\u001A\u00020\u00172>\u00104\u001A:\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u000E\u0012\u001B\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000B\u00A2\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u001705H\u0000\u00A2\u0006\u0002\b8J\b\u00109\u001A\u00020\u0014H\u0016J\u000B\u0010:\u001A\u00028\u0000\u00A2\u0006\u0002\u0010+J\u0015\u0010;\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u00101J\r\u0010<\u001A\u0004\u0018\u00018\u0000\u00A2\u0006\u0002\u0010+J\u0010\u0010=\u001A\u00020\u00042\u0006\u0010\u0018\u001A\u00020\u0004H\u0002J\u0010\u0010>\u001A\u00020\u00042\u0006\u0010\u0018\u001A\u00020\u0004H\u0002J\u0015\u0010?\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u0016J\u0016\u0010@\u001A\u00020\u00142\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u0015\u0010A\u001A\u00028\u00002\u0006\u0010\u0018\u001A\u00020\u0004H\u0016\u00A2\u0006\u0002\u0010.J\u000B\u0010B\u001A\u00028\u0000\u00A2\u0006\u0002\u0010+J\r\u0010C\u001A\u0004\u0018\u00018\u0000\u00A2\u0006\u0002\u0010+J\u000B\u0010D\u001A\u00028\u0000\u00A2\u0006\u0002\u0010+J\r\u0010E\u001A\u0004\u0018\u00018\u0000\u00A2\u0006\u0002\u0010+J\u0016\u0010F\u001A\u00020\u00142\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u001E\u0010G\u001A\u00028\u00002\u0006\u0010\u0018\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00028\u0000H\u0096\u0002\u00A2\u0006\u0002\u0010HJ\u0017\u0010I\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000BH\u0000\u00A2\u0006\u0004\bJ\u0010KJ)\u0010I\u001A\b\u0012\u0004\u0012\u0002HL0\u000B\"\u0004\b\u0001\u0010L2\f\u0010M\u001A\b\u0012\u0004\u0012\u0002HL0\u000BH\u0000\u00A2\u0006\u0004\bJ\u0010NJ\u0015\u0010O\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000BH\u0016\u00A2\u0006\u0002\u0010KJ\'\u0010O\u001A\b\u0012\u0004\u0012\u0002HL0\u000B\"\u0004\b\u0001\u0010L2\f\u0010M\u001A\b\u0012\u0004\u0012\u0002HL0\u000BH\u0016\u00A2\u0006\u0002\u0010NR\u0018\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000BX\u0082\u000E\u00A2\u0006\u0004\n\u0002\u0010\rR\u000E\u0010\u000E\u001A\u00020\u0004X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001E\u0010\u0010\u001A\u00020\u00042\u0006\u0010\u000F\u001A\u00020\u0004@RX\u0096\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012\u00A8\u0006Q"}, d2 = {"Lkotlin/collections/ArrayDeque;", "E", "Lkotlin/collections/AbstractMutableList;", "initialCapacity", "", "(I)V", "()V", "elements", "", "(Ljava/util/Collection;)V", "elementData", "", "", "[Ljava/lang/Object;", "head", "<set-?>", "size", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "addFirst", "(Ljava/lang/Object;)V", "addLast", "clear", "contains", "copyCollectionElements", "internalIndex", "copyElements", "newCapacity", "decremented", "ensureCapacity", "minCapacity", "filterInPlace", "predicate", "Lkotlin/Function1;", "first", "()Ljava/lang/Object;", "firstOrNull", "get", "(I)Ljava/lang/Object;", "incremented", "indexOf", "(Ljava/lang/Object;)I", "internalGet", "internalStructure", "structure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "internalStructure$kotlin_stdlib", "isEmpty", "last", "lastIndexOf", "lastOrNull", "negativeMod", "positiveMod", "remove", "removeAll", "removeAt", "removeFirst", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "testToArray", "testToArray$kotlin_stdlib", "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toArray", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class ArrayDeque extends AbstractMutableList {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001D\u0010\t\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u00042\u0006\u0010\u000B\u001A\u00020\u0004H\u0000¢\u0006\u0002\b\fR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000E\u0010\b\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/collections/ArrayDeque$Companion;", "", "()V", "defaultMinCapacity", "", "emptyElementData", "", "[Ljava/lang/Object;", "maxArraySize", "newCapacity", "oldCapacity", "minCapacity", "newCapacity$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final int newCapacity$kotlin_stdlib(int v, int v1) {
            int v2 = v + (v >> 1) - v1 >= 0 ? v + (v >> 1) : v1;
            if(v2 - 0x7FFFFFF7 > 0) {
                return v1 <= 0x7FFFFFF7 ? 0x7FFFFFF7 : 0x7FFFFFFF;
            }
            return v2;
        }
    }

    public static final Companion Companion = null;
    private static final int defaultMinCapacity = 10;
    private Object[] elementData;
    private static final Object[] emptyElementData = null;
    private int head;
    private static final int maxArraySize = 0x7FFFFFF7;
    private int size;

    static {
        ArrayDeque.Companion = new Companion(null);
        ArrayDeque.emptyElementData = new Object[0];
    }

    public ArrayDeque() {
        this.elementData = ArrayDeque.emptyElementData;
    }

    public ArrayDeque(int v) {
        Object[] arr_object;
        if(v == 0) {
            arr_object = ArrayDeque.emptyElementData;
        }
        else if(v > 0) {
            arr_object = new Object[v];
        }
        else {
            throw new IllegalArgumentException("Illegal Capacity: " + v);
        }
        this.elementData = arr_object;
    }

    public ArrayDeque(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        super();
        Object[] arr_object = collection0.toArray(new Object[0]);
        this.elementData = arr_object;
        this.size = arr_object.length;
        if(arr_object.length == 0) {
            this.elementData = ArrayDeque.emptyElementData;
        }
    }

    @Override  // kotlin.collections.AbstractMutableList
    public void add(int v, Object object0) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(v, this.size());
        if(v == this.size()) {
            this.addLast(object0);
            return;
        }
        if(v == 0) {
            this.addFirst(object0);
            return;
        }
        this.ensureCapacity(this.size() + 1);
        int v1 = this.positiveMod(this.head + v);
        if(v < this.size() + 1 >> 1) {
            int v2 = this.decremented(v1);
            int v3 = this.decremented(this.head);
            int v4 = this.head;
            if(v2 >= v4) {
                Object[] arr_object = this.elementData;
                arr_object[v3] = arr_object[v4];
                ArraysKt.copyInto(arr_object, arr_object, v4, v4 + 1, v2 + 1);
            }
            else {
                ArraysKt.copyInto(this.elementData, this.elementData, v4 - 1, v4, this.elementData.length);
                Object[] arr_object1 = this.elementData;
                arr_object1[arr_object1.length - 1] = arr_object1[0];
                ArraysKt.copyInto(arr_object1, arr_object1, 0, 1, v2 + 1);
            }
            this.elementData[v2] = object0;
            this.head = v3;
        }
        else {
            int v5 = this.positiveMod(this.head + this.size());
            if(v1 < v5) {
                ArraysKt.copyInto(this.elementData, this.elementData, v1 + 1, v1, v5);
            }
            else {
                ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, v5);
                Object[] arr_object2 = this.elementData;
                arr_object2[0] = arr_object2[arr_object2.length - 1];
                ArraysKt.copyInto(arr_object2, arr_object2, v1 + 1, v1, arr_object2.length - 1);
            }
            this.elementData[v1] = object0;
        }
        this.size = this.size() + 1;
    }

    @Override
    public boolean add(Object object0) {
        this.addLast(object0);
        return true;
    }

    @Override
    public boolean addAll(int v, Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(v, this.size());
        if(collection0.isEmpty()) {
            return false;
        }
        if(v == this.size()) {
            return this.addAll(collection0);
        }
        this.ensureCapacity(this.size() + collection0.size());
        int v1 = this.positiveMod(this.head + this.size());
        int v2 = this.positiveMod(this.head + v);
        int v3 = collection0.size();
        if(v < this.size() + 1 >> 1) {
            int v4 = this.head;
            int v5 = v4 - v3;
            if(v2 < v4) {
                ArraysKt.copyInto(this.elementData, this.elementData, v5, v4, this.elementData.length);
                if(v3 >= v2) {
                    ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - v3, 0, v2);
                }
                else {
                    ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - v3, 0, v3);
                    ArraysKt.copyInto(this.elementData, this.elementData, 0, v3, v2);
                }
            }
            else if(v5 >= 0) {
                ArraysKt.copyInto(this.elementData, this.elementData, v5, v4, v2);
            }
            else {
                Object[] arr_object = this.elementData;
                v5 += arr_object.length;
                int v6 = arr_object.length - v5;
                if(v6 >= v2 - v4) {
                    ArraysKt.copyInto(arr_object, arr_object, v5, v4, v2);
                }
                else {
                    ArraysKt.copyInto(arr_object, arr_object, v5, v4, v4 + v6);
                    ArraysKt.copyInto(this.elementData, this.elementData, 0, this.head + v6, v2);
                }
            }
            this.head = v5;
            this.copyCollectionElements(this.negativeMod(v2 - v3), collection0);
            return true;
        }
        int v7 = v2 + v3;
        if(v2 < v1) {
            int v8 = v3 + v1;
            Object[] arr_object1 = this.elementData;
            if(v8 <= arr_object1.length) {
                ArraysKt.copyInto(arr_object1, arr_object1, v7, v2, v1);
            }
            else if(v7 >= arr_object1.length) {
                ArraysKt.copyInto(arr_object1, arr_object1, v7 - arr_object1.length, v2, v1);
            }
            else {
                int v9 = v1 - (v8 - arr_object1.length);
                ArraysKt.copyInto(arr_object1, arr_object1, 0, v9, v1);
                ArraysKt.copyInto(this.elementData, this.elementData, v7, v2, v9);
            }
        }
        else {
            ArraysKt.copyInto(this.elementData, this.elementData, v3, 0, v1);
            Object[] arr_object2 = this.elementData;
            if(v7 >= arr_object2.length) {
                ArraysKt.copyInto(arr_object2, arr_object2, v7 - arr_object2.length, v2, arr_object2.length);
            }
            else {
                ArraysKt.copyInto(arr_object2, arr_object2, 0, arr_object2.length - v3, arr_object2.length);
                ArraysKt.copyInto(this.elementData, this.elementData, v7, v2, this.elementData.length - v3);
            }
        }
        this.copyCollectionElements(v2, collection0);
        return true;
    }

    @Override
    public boolean addAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        if(collection0.isEmpty()) {
            return false;
        }
        this.ensureCapacity(this.size() + collection0.size());
        this.copyCollectionElements(this.positiveMod(this.head + this.size()), collection0);
        return true;
    }

    @Override
    public final void addFirst(Object object0) {
        this.ensureCapacity(this.size() + 1);
        int v = this.decremented(this.head);
        this.head = v;
        this.elementData[v] = object0;
        this.size = this.size() + 1;
    }

    @Override
    public final void addLast(Object object0) {
        this.ensureCapacity(this.size() + 1);
        this.elementData[this.positiveMod(this.head + this.size())] = object0;
        this.size = this.size() + 1;
    }

    @Override
    public void clear() {
        int v = this.positiveMod(this.head + this.size());
        int v1 = this.head;
        if(v1 < v) {
            ArraysKt.fill(this.elementData, null, v1, v);
        }
        else if(!this.isEmpty()) {
            ArraysKt.fill(this.elementData, null, this.head, this.elementData.length);
            ArraysKt.fill(this.elementData, null, 0, v);
        }
        this.head = 0;
        this.size = 0;
    }

    @Override
    public boolean contains(Object object0) {
        return this.indexOf(object0) != -1;
    }

    private final void copyCollectionElements(int v, Collection collection0) {
        Iterator iterator0 = collection0.iterator();
        while(v < this.elementData.length && iterator0.hasNext()) {
            Object[] arr_object = this.elementData;
            Object object0 = iterator0.next();
            arr_object[v] = object0;
            ++v;
        }
        int v1 = this.head;
        for(int v2 = 0; v2 < v1 && iterator0.hasNext(); ++v2) {
            Object[] arr_object1 = this.elementData;
            Object object1 = iterator0.next();
            arr_object1[v2] = object1;
        }
        this.size = this.size() + collection0.size();
    }

    private final void copyElements(int v) {
        Object[] arr_object = new Object[v];
        ArraysKt.copyInto(this.elementData, arr_object, 0, this.head, this.elementData.length);
        ArraysKt.copyInto(this.elementData, arr_object, this.elementData.length - this.head, 0, this.head);
        this.head = 0;
        this.elementData = arr_object;
    }

    private final int decremented(int v) {
        return v == 0 ? ArraysKt.getLastIndex(this.elementData) : v - 1;
    }

    private final void ensureCapacity(int v) {
        if(v < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] arr_object = this.elementData;
        if(v <= arr_object.length) {
            return;
        }
        if(arr_object == ArrayDeque.emptyElementData) {
            this.elementData = new Object[RangesKt.coerceAtLeast(v, 10)];
            return;
        }
        this.copyElements(ArrayDeque.Companion.newCapacity$kotlin_stdlib(arr_object.length, v));
    }

    private final boolean filterInPlace(Function1 function10) {
        int v3;
        int v = 0;
        if(!this.isEmpty() && this.elementData.length != 0) {
            int v1 = this.positiveMod(this.head + this.size());
            int v2 = this.head;
            if(v2 < v1) {
                v3 = v2;
                while(v2 < v1) {
                    Object object0 = this.elementData[v2];
                    if(((Boolean)function10.invoke(object0)).booleanValue()) {
                        this.elementData[v3] = object0;
                        ++v3;
                    }
                    else {
                        v = 1;
                    }
                    ++v2;
                }
                ArraysKt.fill(this.elementData, null, v3, v1);
            }
            else {
                int v4 = v2;
                int v5 = 0;
                while(v2 < this.elementData.length) {
                    Object[] arr_object = this.elementData;
                    Object object1 = arr_object[v2];
                    arr_object[v2] = null;
                    if(((Boolean)function10.invoke(object1)).booleanValue()) {
                        this.elementData[v4] = object1;
                        ++v4;
                    }
                    else {
                        v5 = 1;
                    }
                    ++v2;
                }
                v3 = this.positiveMod(v4);
                while(v < v1) {
                    Object[] arr_object1 = this.elementData;
                    Object object2 = arr_object1[v];
                    arr_object1[v] = null;
                    if(((Boolean)function10.invoke(object2)).booleanValue()) {
                        this.elementData[v3] = object2;
                        v3 = this.incremented(v3);
                    }
                    else {
                        v5 = 1;
                    }
                    ++v;
                }
                v = v5;
            }
            if(v) {
                this.size = this.negativeMod(v3 - this.head);
            }
        }
        return v != 0;
    }

    public final Object first() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return this.elementData[this.head];
    }

    // 去混淆评级： 低(20)
    public final Object firstOrNull() {
        return this.isEmpty() ? null : this.elementData[this.head];
    }

    @Override
    public Object get(int v) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(v, this.size());
        return this.elementData[this.positiveMod(this.head + v)];
    }

    @Override  // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.size;
    }

    private final int incremented(int v) {
        return v == ArraysKt.getLastIndex(this.elementData) ? 0 : v + 1;
    }

    @Override
    public int indexOf(Object object0) {
        int v = this.positiveMod(this.head + this.size());
        int v1 = this.head;
        if(v1 < v) {
            while(v1 < v) {
                if(Intrinsics.areEqual(object0, this.elementData[v1])) {
                    return v1 - this.head;
                }
                ++v1;
            }
            return -1;
        }
        if(v1 >= v) {
            while(v1 < this.elementData.length) {
                if(Intrinsics.areEqual(object0, this.elementData[v1])) {
                    return v1 - this.head;
                }
                ++v1;
            }
            for(int v2 = 0; v2 < v; ++v2) {
                if(Intrinsics.areEqual(object0, this.elementData[v2])) {
                    return v2 + this.elementData.length - this.head;
                }
            }
        }
        return -1;
    }

    private final Object internalGet(int v) {
        return this.elementData[v];
    }

    private final int internalIndex(int v) {
        return this.positiveMod(this.head + v);
    }

    public final void internalStructure$kotlin_stdlib(Function2 function20) {
        int v2;
        Intrinsics.checkNotNullParameter(function20, "structure");
        int v = this.positiveMod(this.head + this.size());
        if(this.isEmpty()) {
            v2 = this.head;
        }
        else {
            int v1 = this.head;
            v2 = v1 >= v ? v1 - this.elementData.length : this.head;
        }
        function20.invoke(v2, this.toArray());
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    public final Object last() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] arr_object = this.elementData;
        return arr_object[this.positiveMod(this.head + CollectionsKt.getLastIndex(this))];
    }

    @Override
    public int lastIndexOf(Object object0) {
        int v2;
        int v = this.positiveMod(this.head + this.size());
        int v1 = this.head;
        if(v1 < v) {
            v2 = v - 1;
            if(v1 <= v2) {
                while(true) {
                    if(Intrinsics.areEqual(object0, this.elementData[v2])) {
                        return v2 - this.head;
                    }
                    if(v2 == v1) {
                        break;
                    }
                    --v2;
                }
            }
        }
        else if(v1 > v) {
            for(int v3 = v - 1; -1 < v3; --v3) {
                if(Intrinsics.areEqual(object0, this.elementData[v3])) {
                    return v3 + this.elementData.length - this.head;
                }
            }
            v2 = ArraysKt.getLastIndex(this.elementData);
            int v4 = this.head;
            if(v4 <= v2) {
                while(true) {
                    if(Intrinsics.areEqual(object0, this.elementData[v2])) {
                        return v2 - this.head;
                    }
                    if(v2 == v4) {
                        break;
                    }
                    --v2;
                }
            }
        }
        return -1;
    }

    public final Object lastOrNull() {
        if(this.isEmpty()) {
            return null;
        }
        Object[] arr_object = this.elementData;
        return arr_object[this.positiveMod(this.head + CollectionsKt.getLastIndex(this))];
    }

    private final int negativeMod(int v) {
        return v >= 0 ? v : v + this.elementData.length;
    }

    private final int positiveMod(int v) {
        return v < this.elementData.length ? v : v - this.elementData.length;
    }

    @Override
    public boolean remove(Object object0) {
        int v = this.indexOf(object0);
        if(v == -1) {
            return false;
        }
        this.remove(v);
        return true;
    }

    @Override
    public boolean removeAll(Collection collection0) {
        int v3;
        Intrinsics.checkNotNullParameter(collection0, "elements");
        int v = 0;
        if(!this.isEmpty() && this.elementData.length != 0) {
            int v1 = this.positiveMod(this.head + this.size());
            int v2 = this.head;
            if(v2 < v1) {
                v3 = v2;
                while(v2 < v1) {
                    Object object0 = this.elementData[v2];
                    if(collection0.contains(object0)) {
                        v = 1;
                    }
                    else {
                        this.elementData[v3] = object0;
                        ++v3;
                    }
                    ++v2;
                }
                ArraysKt.fill(this.elementData, null, v3, v1);
            }
            else {
                int v4 = v2;
                int v5 = 0;
                while(v2 < this.elementData.length) {
                    Object[] arr_object = this.elementData;
                    Object object1 = arr_object[v2];
                    arr_object[v2] = null;
                    if(collection0.contains(object1)) {
                        v5 = 1;
                    }
                    else {
                        this.elementData[v4] = object1;
                        ++v4;
                    }
                    ++v2;
                }
                v3 = this.positiveMod(v4);
                while(v < v1) {
                    Object[] arr_object1 = this.elementData;
                    Object object2 = arr_object1[v];
                    arr_object1[v] = null;
                    if(collection0.contains(object2)) {
                        v5 = 1;
                    }
                    else {
                        this.elementData[v3] = object2;
                        v3 = this.incremented(v3);
                    }
                    ++v;
                }
                v = v5;
            }
            if(v) {
                this.size = this.negativeMod(v3 - this.head);
            }
        }
        return v != 0;
    }

    @Override  // kotlin.collections.AbstractMutableList
    public Object removeAt(int v) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(v, this.size());
        if(v == CollectionsKt.getLastIndex(this)) {
            return this.removeLast();
        }
        if(v == 0) {
            return this.removeFirst();
        }
        int v1 = this.positiveMod(this.head + v);
        Object object0 = this.elementData[v1];
        if(v < this.size() >> 1) {
            int v2 = this.head;
            if(v1 >= v2) {
                ArraysKt.copyInto(this.elementData, this.elementData, v2 + 1, v2, v1);
            }
            else {
                ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, v1);
                Object[] arr_object = this.elementData;
                arr_object[0] = arr_object[arr_object.length - 1];
                ArraysKt.copyInto(arr_object, arr_object, this.head + 1, this.head, arr_object.length - 1);
            }
            int v3 = this.head;
            this.elementData[v3] = null;
            this.head = this.incremented(v3);
        }
        else {
            int v4 = this.positiveMod(this.head + CollectionsKt.getLastIndex(this));
            if(v1 <= v4) {
                ArraysKt.copyInto(this.elementData, this.elementData, v1, v1 + 1, v4 + 1);
            }
            else {
                ArraysKt.copyInto(this.elementData, this.elementData, v1, v1 + 1, this.elementData.length);
                Object[] arr_object1 = this.elementData;
                arr_object1[arr_object1.length - 1] = arr_object1[0];
                ArraysKt.copyInto(arr_object1, arr_object1, 0, 1, v4 + 1);
            }
            this.elementData[v4] = null;
        }
        this.size = this.size() - 1;
        return object0;
    }

    @Override
    public final Object removeFirst() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] arr_object = this.elementData;
        int v = this.head;
        Object object0 = arr_object[v];
        arr_object[v] = null;
        this.head = this.incremented(v);
        this.size = this.size() - 1;
        return object0;
    }

    // 去混淆评级： 低(20)
    public final Object removeFirstOrNull() {
        return this.isEmpty() ? null : this.removeFirst();
    }

    @Override
    public final Object removeLast() {
        if(this.isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int v = this.positiveMod(this.head + CollectionsKt.getLastIndex(this));
        Object[] arr_object = this.elementData;
        Object object0 = arr_object[v];
        arr_object[v] = null;
        this.size = this.size() - 1;
        return object0;
    }

    // 去混淆评级： 低(20)
    public final Object removeLastOrNull() {
        return this.isEmpty() ? null : this.removeLast();
    }

    @Override
    public boolean retainAll(Collection collection0) {
        int v3;
        Intrinsics.checkNotNullParameter(collection0, "elements");
        int v = 0;
        if(!this.isEmpty() && this.elementData.length != 0) {
            int v1 = this.positiveMod(this.head + this.size());
            int v2 = this.head;
            if(v2 < v1) {
                v3 = v2;
                while(v2 < v1) {
                    Object object0 = this.elementData[v2];
                    if(collection0.contains(object0)) {
                        this.elementData[v3] = object0;
                        ++v3;
                    }
                    else {
                        v = 1;
                    }
                    ++v2;
                }
                ArraysKt.fill(this.elementData, null, v3, v1);
            }
            else {
                int v4 = v2;
                int v5 = 0;
                while(v2 < this.elementData.length) {
                    Object[] arr_object = this.elementData;
                    Object object1 = arr_object[v2];
                    arr_object[v2] = null;
                    if(collection0.contains(object1)) {
                        this.elementData[v4] = object1;
                        ++v4;
                    }
                    else {
                        v5 = 1;
                    }
                    ++v2;
                }
                v3 = this.positiveMod(v4);
                while(v < v1) {
                    Object[] arr_object1 = this.elementData;
                    Object object2 = arr_object1[v];
                    arr_object1[v] = null;
                    if(collection0.contains(object2)) {
                        this.elementData[v3] = object2;
                        v3 = this.incremented(v3);
                    }
                    else {
                        v5 = 1;
                    }
                    ++v;
                }
                v = v5;
            }
            if(v) {
                this.size = this.negativeMod(v3 - this.head);
            }
        }
        return v != 0;
    }

    @Override  // kotlin.collections.AbstractMutableList
    public Object set(int v, Object object0) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(v, this.size());
        int v1 = this.positiveMod(this.head + v);
        Object[] arr_object = this.elementData;
        Object object1 = arr_object[v1];
        arr_object[v1] = object0;
        return object1;
    }

    public final Object[] testToArray$kotlin_stdlib() {
        return this.toArray();
    }

    public final Object[] testToArray$kotlin_stdlib(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "array");
        return this.toArray(arr_object);
    }

    @Override
    public Object[] toArray() {
        return this.toArray(new Object[this.size()]);
    }

    @Override
    public Object[] toArray(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "array");
        if(arr_object.length < this.size()) {
            arr_object = ArraysKt.arrayOfNulls(arr_object, this.size());
        }
        int v = this.positiveMod(this.head + this.size());
        int v1 = this.head;
        if(v1 < v) {
            ArraysKt.copyInto$default(this.elementData, arr_object, 0, v1, v, 2, null);
        }
        else if(!this.isEmpty()) {
            ArraysKt.copyInto(this.elementData, arr_object, 0, this.head, this.elementData.length);
            ArraysKt.copyInto(this.elementData, arr_object, this.elementData.length - this.head, 0, v);
        }
        if(arr_object.length > this.size()) {
            arr_object[this.size()] = null;
        }
        return arr_object;
    }
}

