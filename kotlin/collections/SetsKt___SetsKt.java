package kotlin.collections;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A,\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001A\u0002H\u0002H\u0086\u0002\u00A2\u0006\u0002\u0010\u0004\u001A4\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000E\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002\u00A2\u0006\u0002\u0010\u0007\u001A-\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001A-\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001A,\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001A\u0002H\u0002H\u0087\b\u00A2\u0006\u0002\u0010\u0004\u001A,\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001A\u0002H\u0002H\u0086\u0002\u00A2\u0006\u0002\u0010\u0004\u001A4\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000E\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0086\u0002\u00A2\u0006\u0002\u0010\u0007\u001A-\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\bH\u0086\u0002\u001A-\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086\u0002\u001A,\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001A\u0002H\u0002H\u0087\b\u00A2\u0006\u0002\u0010\u0004\u00A8\u0006\r"}, d2 = {"minus", "", "T", "element", "(Ljava/util/Set;Ljava/lang/Object;)Ljava/util/Set;", "elements", "", "(Ljava/util/Set;[Ljava/lang/Object;)Ljava/util/Set;", "", "Lkotlin/sequences/Sequence;", "minusElement", "plus", "plusElement", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/SetsKt")
class SetsKt___SetsKt extends SetsKt__SetsKt {
    public static final Set minus(Set set0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        Collection collection0 = CollectionsKt.convertToListIfNotCollection(iterable0);
        if(collection0.isEmpty()) {
            return CollectionsKt.toSet(set0);
        }
        if(collection0 instanceof Set) {
            Collection collection1 = new LinkedHashSet();
            for(Object object0: set0) {
                if(!collection0.contains(object0)) {
                    collection1.add(object0);
                }
            }
            return (Set)collection1;
        }
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(set0);
        linkedHashSet0.removeAll(collection0);
        return linkedHashSet0;
    }

    public static final Set minus(Set set0, Object object0) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(MapsKt.mapCapacity(set0.size()));
        boolean z = false;
        for(Object object1: set0) {
            boolean z1 = true;
            if(!z && Intrinsics.areEqual(object1, object0)) {
                z = true;
                z1 = false;
            }
            if(z1) {
                linkedHashSet0.add(object1);
            }
        }
        return linkedHashSet0;
    }

    public static final Set minus(Set set0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(set0);
        CollectionsKt.removeAll(linkedHashSet0, sequence0);
        return linkedHashSet0;
    }

    public static final Set minus(Set set0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(set0);
        CollectionsKt.removeAll(linkedHashSet0, arr_object);
        return linkedHashSet0;
    }

    private static final Set minusElement(Set set0, Object object0) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        return SetsKt.minus(set0, object0);
    }

    public static final Set plus(Set set0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        Integer integer0 = CollectionsKt.collectionSizeOrNull(iterable0);
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(MapsKt.mapCapacity((integer0 == null ? set0.size() * 2 : set0.size() + integer0.intValue())));
        linkedHashSet0.addAll(set0);
        CollectionsKt.addAll(linkedHashSet0, iterable0);
        return linkedHashSet0;
    }

    public static final Set plus(Set set0, Object object0) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(MapsKt.mapCapacity(set0.size() + 1));
        linkedHashSet0.addAll(set0);
        linkedHashSet0.add(object0);
        return linkedHashSet0;
    }

    public static final Set plus(Set set0, Sequence sequence0) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(MapsKt.mapCapacity(set0.size() * 2));
        linkedHashSet0.addAll(set0);
        CollectionsKt.addAll(linkedHashSet0, sequence0);
        return linkedHashSet0;
    }

    public static final Set plus(Set set0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(MapsKt.mapCapacity(set0.size() + arr_object.length));
        linkedHashSet0.addAll(set0);
        CollectionsKt.addAll(linkedHashSet0, arr_object);
        return linkedHashSet0;
    }

    private static final Set plusElement(Set set0, Object object0) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        return SetsKt.plus(set0, object0);
    }
}

