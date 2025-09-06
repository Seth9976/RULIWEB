package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u001F\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001E\n\u0000\n\u0002\u0010\u001D\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000B\u001A-\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000E\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005\u00A2\u0006\u0002\u0010\u0006\u001A&\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001A&\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\b\u001A\u001E\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0000\u001A9\u0010\u000B\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\f2\u0012\u0010\r\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u000E2\u0006\u0010\u000F\u001A\u00020\u0001H\u0002\u00A2\u0006\u0002\b\u0010\u001A9\u0010\u000B\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00112\u0012\u0010\r\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u000E2\u0006\u0010\u000F\u001A\u00020\u0001H\u0002\u00A2\u0006\u0002\b\u0010\u001A(\u0010\u0012\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0014\u001A\u0002H\u0002H\u0087\n\u00A2\u0006\u0002\u0010\u0015\u001A.\u0010\u0012\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n\u00A2\u0006\u0002\u0010\u0016\u001A)\u0010\u0012\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001A)\u0010\u0012\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001A(\u0010\u0017\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0014\u001A\u0002H\u0002H\u0087\n\u00A2\u0006\u0002\u0010\u0015\u001A.\u0010\u0017\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n\u00A2\u0006\u0002\u0010\u0016\u001A)\u0010\u0017\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001A)\u0010\u0017\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001A-\u0010\u0018\u001A\u00020\u0001\"\t\b\u0000\u0010\u0002\u00A2\u0006\u0002\b\u0019*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0014\u001A\u0002H\u0002H\u0087\b\u00A2\u0006\u0002\u0010\u001A\u001A&\u0010\u0018\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00112\u0006\u0010\u001B\u001A\u00020\u001CH\u0087\b\u00A2\u0006\u0002\u0010\u001D\u001A-\u0010\u001E\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000E\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005\u00A2\u0006\u0002\u0010\u0006\u001A&\u0010\u001E\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001A&\u0010\u001E\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\b\u001A.\u0010\u001E\u001A\u00020\u0001\"\t\b\u0000\u0010\u0002\u00A2\u0006\u0002\b\u0019*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\nH\u0087\b\u001A*\u0010\u001E\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\f2\u0012\u0010\r\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u000E\u001A*\u0010\u001E\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00112\u0012\u0010\r\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u000E\u001A\u001D\u0010\u001F\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0007\u00A2\u0006\u0002\u0010 \u001A\u001F\u0010!\u001A\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0007\u00A2\u0006\u0002\u0010 \u001A\u001D\u0010\"\u001A\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0007\u00A2\u0006\u0002\u0010 \u001A\u001F\u0010#\u001A\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0007\u00A2\u0006\u0002\u0010 \u001A-\u0010$\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000E\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005\u00A2\u0006\u0002\u0010\u0006\u001A&\u0010$\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001A&\u0010$\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\b\u001A.\u0010$\u001A\u00020\u0001\"\t\b\u0000\u0010\u0002\u00A2\u0006\u0002\b\u0019*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\nH\u0087\b\u001A*\u0010$\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\f2\u0012\u0010\r\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u000E\u001A*\u0010$\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00112\u0012\u0010\r\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u000E\u001A\u0015\u0010%\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0003H\u0002\u00A2\u0006\u0002\b&\u00A8\u0006\'"}, d2 = {"addAll", "", "T", "", "elements", "", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "", "Lkotlin/sequences/Sequence;", "convertToListIfNotCollection", "", "filterInPlace", "", "predicate", "Lkotlin/Function1;", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", "", "minusAssign", "", "element", "(Ljava/util/Collection;Ljava/lang/Object;)V", "(Ljava/util/Collection;[Ljava/lang/Object;)V", "plusAssign", "remove", "Lkotlin/internal/OnlyInputTypes;", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "index", "", "(Ljava/util/List;I)Ljava/lang/Object;", "removeAll", "removeFirst", "(Ljava/util/List;)Ljava/lang/Object;", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "retainNothing", "retainNothing$CollectionsKt__MutableCollectionsKt", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt__MutableCollectionsKt extends CollectionsKt__MutableCollectionsJVMKt {
    public static final boolean addAll(Collection collection0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        if(iterable0 instanceof Collection) {
            return collection0.addAll(((Collection)iterable0));
        }
        boolean z = false;
        for(Object object0: iterable0) {
            if(collection0.add(object0)) {
                z = true;
            }
        }
        return z;
    }

    public static final boolean addAll(Collection collection0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        boolean z = false;
        for(Object object0: sequence0) {
            if(collection0.add(object0)) {
                z = true;
            }
        }
        return z;
    }

    public static final boolean addAll(Collection collection0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return collection0.addAll(ArraysKt.asList(arr_object));
    }

    public static final Collection convertToListIfNotCollection(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return iterable0 instanceof Collection ? ((Collection)iterable0) : CollectionsKt.toList(iterable0);
    }

    private static final boolean filterInPlace$CollectionsKt__MutableCollectionsKt(Iterable iterable0, Function1 function10, boolean z) {
        boolean z1 = false;
        Iterator iterator0 = iterable0.iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            if(((Boolean)function10.invoke(object0)).booleanValue() == z) {
                iterator0.remove();
                z1 = true;
            }
        }
        return z1;
    }

    private static final boolean filterInPlace$CollectionsKt__MutableCollectionsKt(List list0, Function1 function10, boolean z) {
        if(!(list0 instanceof RandomAccess)) {
            Intrinsics.checkNotNull(list0, "null cannot be cast to non-null type kotlin.collections.MutableIterable<T of kotlin.collections.CollectionsKt__MutableCollectionsKt.filterInPlace>");
            return CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(TypeIntrinsics.asMutableIterable(list0), function10, z);
        }
        int v = 0;
        IntIterator intIterator0 = new IntRange(0, CollectionsKt.getLastIndex(list0)).iterator();
        while(intIterator0.hasNext()) {
            int v1 = intIterator0.nextInt();
            Object object0 = list0.get(v1);
            if(((Boolean)function10.invoke(object0)).booleanValue() != z) {
                if(v != v1) {
                    list0.set(v, object0);
                }
                ++v;
            }
        }
        if(v < list0.size()) {
            int v2 = CollectionsKt.getLastIndex(list0);
            if(v <= v2) {
                while(true) {
                    list0.remove(v2);
                    if(v2 == v) {
                        break;
                    }
                    --v2;
                }
            }
            return true;
        }
        return false;
    }

    private static final void minusAssign(Collection collection0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        CollectionsKt.removeAll(collection0, iterable0);
    }

    private static final void minusAssign(Collection collection0, Object object0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        collection0.remove(object0);
    }

    private static final void minusAssign(Collection collection0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        CollectionsKt.removeAll(collection0, sequence0);
    }

    private static final void minusAssign(Collection collection0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        CollectionsKt.removeAll(collection0, arr_object);
    }

    private static final void plusAssign(Collection collection0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        CollectionsKt.addAll(collection0, iterable0);
    }

    private static final void plusAssign(Collection collection0, Object object0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        collection0.add(object0);
    }

    private static final void plusAssign(Collection collection0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        CollectionsKt.addAll(collection0, sequence0);
    }

    private static final void plusAssign(Collection collection0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        CollectionsKt.addAll(collection0, arr_object);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use removeAt(index) instead.", replaceWith = @ReplaceWith(expression = "removeAt(index)", imports = {}))
    private static final Object remove(List list0, int v) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        return list0.remove(v);
    }

    private static final boolean remove(Collection collection0, Object object0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        return TypeIntrinsics.asMutableCollection(collection0).remove(object0);
    }

    public static final boolean removeAll(Iterable iterable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        return CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(iterable0, function10, true);
    }

    public static final boolean removeAll(Collection collection0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        return collection0.removeAll(CollectionsKt.convertToListIfNotCollection(iterable0));
    }

    private static final boolean removeAll(Collection collection0, Collection collection1) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(collection1, "elements");
        return TypeIntrinsics.asMutableCollection(collection0).removeAll(collection1);
    }

    public static final boolean removeAll(Collection collection0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        Collection collection1 = SequencesKt.toList(sequence0);
        return !collection1.isEmpty() && collection0.removeAll(collection1);
    }

    public static final boolean removeAll(Collection collection0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return arr_object.length != 0 && collection0.removeAll(ArraysKt.asList(arr_object));
    }

    public static final boolean removeAll(List list0, Function1 function10) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        return CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(list0, function10, true);
    }

    public static final Object removeFirst(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        if(list0.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list0.remove(0);
    }

    public static final Object removeFirstOrNull(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        return list0.isEmpty() ? null : list0.remove(0);
    }

    public static final Object removeLast(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        if(list0.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list0.remove(CollectionsKt.getLastIndex(list0));
    }

    public static final Object removeLastOrNull(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        return list0.isEmpty() ? null : list0.remove(CollectionsKt.getLastIndex(list0));
    }

    public static final boolean retainAll(Iterable iterable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        return CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(iterable0, function10, false);
    }

    public static final boolean retainAll(Collection collection0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        return collection0.retainAll(CollectionsKt.convertToListIfNotCollection(iterable0));
    }

    private static final boolean retainAll(Collection collection0, Collection collection1) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(collection1, "elements");
        return TypeIntrinsics.asMutableCollection(collection0).retainAll(collection1);
    }

    public static final boolean retainAll(Collection collection0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        Collection collection1 = SequencesKt.toList(sequence0);
        return collection1.isEmpty() ? CollectionsKt__MutableCollectionsKt.retainNothing$CollectionsKt__MutableCollectionsKt(collection0) : collection0.retainAll(collection1);
    }

    public static final boolean retainAll(Collection collection0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return arr_object.length == 0 ? CollectionsKt__MutableCollectionsKt.retainNothing$CollectionsKt__MutableCollectionsKt(collection0) : collection0.retainAll(ArraysKt.asList(arr_object));
    }

    public static final boolean retainAll(List list0, Function1 function10) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        return CollectionsKt__MutableCollectionsKt.filterInPlace$CollectionsKt__MutableCollectionsKt(list0, function10, false);
    }

    private static final boolean retainNothing$CollectionsKt__MutableCollectionsKt(Collection collection0) {
        collection0.clear();
        return !collection0.isEmpty();
    }
}

