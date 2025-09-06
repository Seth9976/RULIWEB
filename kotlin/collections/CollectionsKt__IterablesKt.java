package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u001C\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A.\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\bø\u0001\u0000\u001A \u0010\u0006\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\b\u001A\u00020\u0007H\u0001\u001A\u001F\u0010\t\u001A\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001¢\u0006\u0002\u0010\n\u001A\"\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001A@\u0010\r\u001A\u001A\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000F0\f0\u000E\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000F*\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000F0\u000E0\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"Iterable", "", "T", "iterator", "Lkotlin/Function0;", "", "collectionSizeOrDefault", "", "default", "collectionSizeOrNull", "(Ljava/lang/Iterable;)Ljava/lang/Integer;", "flatten", "", "unzip", "Lkotlin/Pair;", "R", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {
    private static final Iterable Iterable(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "iterator");
        return new Object() {
            @Override
            public Iterator iterator() {
                return (Iterator)function00.invoke();
            }
        };
    }

    public static final int collectionSizeOrDefault(Iterable iterable0, int v) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return iterable0 instanceof Collection ? ((Collection)iterable0).size() : v;
    }

    public static final Integer collectionSizeOrNull(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return iterable0 instanceof Collection ? ((Collection)iterable0).size() : null;
    }

    public static final List flatten(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: iterable0) {
            CollectionsKt.addAll(arrayList0, ((Iterable)object0));
        }
        return arrayList0;
    }

    public static final Pair unzip(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        int v = CollectionsKt.collectionSizeOrDefault(iterable0, 10);
        ArrayList arrayList0 = new ArrayList(v);
        ArrayList arrayList1 = new ArrayList(v);
        for(Object object0: iterable0) {
            arrayList0.add(((Pair)object0).getFirst());
            arrayList1.add(((Pair)object0).getSecond());
        }
        return TuplesKt.to(arrayList0, arrayList1);
    }
}

