package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.collections.builders.ListBuilder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001E\n\u0002\b\n\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u001C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\"\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0001\u001A?\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0006\u001A\u00020\u00072\u001D\u0010\b\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0004\u0012\u00020\n0\t\u00A2\u0006\u0002\b\u000BH\u0081\b\u00F8\u0001\u0000\u001A7\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001D\u0010\b\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0004\u0012\u00020\n0\t\u00A2\u0006\u0002\b\u000BH\u0081\b\u00F8\u0001\u0000\u001A\u0011\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00020\u0007H\u0081\b\u001A\u0011\u0010\u000E\u001A\u00020\u00072\u0006\u0010\u000F\u001A\u00020\u0007H\u0081\b\u001A\"\u0010\u0010\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00112\n\u0010\u0013\u001A\u0006\u0012\u0002\b\u00030\u0014H\u0081\b\u00A2\u0006\u0002\u0010\u0015\u001A4\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0011\"\u0004\b\u0000\u0010\u00162\n\u0010\u0013\u001A\u0006\u0012\u0002\b\u00030\u00142\f\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0011H\u0081\b\u00A2\u0006\u0002\u0010\u0018\u001A\u0014\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0000\u0010\u0002H\u0001\u001A\u001C\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0006\u001A\u00020\u0007H\u0001\u001A\u001F\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0001\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u001B\u001A\u0002H\u0016\u00A2\u0006\u0002\u0010\u001C\u001A1\u0010\u001D\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00120\u0011\"\u0004\b\u0000\u0010\u0016*\n\u0012\u0006\b\u0001\u0012\u0002H\u00160\u00112\u0006\u0010\u001E\u001A\u00020\u001FH\u0000\u00A2\u0006\u0002\u0010 \u001A\u001E\u0010!\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0001\"\u0004\b\u0000\u0010\u0016*\b\u0012\u0004\u0012\u0002H\u00160\"H\u0007\u001A&\u0010!\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0001\"\u0004\b\u0000\u0010\u0016*\b\u0012\u0004\u0012\u0002H\u00160\"2\u0006\u0010#\u001A\u00020$H\u0007\u001A\u001F\u0010%\u001A\b\u0012\u0004\u0012\u0002H\u00160\u0001\"\u0004\b\u0000\u0010\u0016*\b\u0012\u0004\u0012\u0002H\u00160&H\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006\'"}, d2 = {"build", "", "E", "builder", "", "buildListInternal", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "checkCountOverflow", "count", "checkIndexOverflow", "index", "copyToArrayImpl", "", "", "collection", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", "T", "array", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "createListBuilder", "listOf", "element", "(Ljava/lang/Object;)Ljava/util/List;", "copyToArrayOfAny", "isVarargs", "", "([Ljava/lang/Object;Z)[Ljava/lang/Object;", "shuffled", "", "random", "Ljava/util/Random;", "toList", "Ljava/util/Enumeration;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt__CollectionsJVMKt {
    public static final List build(List list0) {
        Intrinsics.checkNotNullParameter(list0, "builder");
        return ((ListBuilder)list0).build();
    }

    private static final List buildListInternal(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        List list0 = CollectionsKt.createListBuilder(v);
        function10.invoke(list0);
        return CollectionsKt.build(list0);
    }

    private static final List buildListInternal(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        List list0 = CollectionsKt.createListBuilder();
        function10.invoke(list0);
        return CollectionsKt.build(list0);
    }

    private static final int checkCountOverflow(int v) {
        if(v < 0) {
            CollectionsKt.throwCountOverflow();
            return v;
        }
        return v;
    }

    private static final int checkIndexOverflow(int v) {
        if(v < 0) {
            CollectionsKt.throwIndexOverflow();
            return v;
        }
        return v;
    }

    private static final Object[] copyToArrayImpl(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "collection");
        return CollectionToArray.toArray(collection0);
    }

    private static final Object[] copyToArrayImpl(Collection collection0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(collection0, "collection");
        Intrinsics.checkNotNullParameter(arr_object, "array");
        return CollectionToArray.toArray(collection0, arr_object);
    }

    public static final Object[] copyToArrayOfAny(Object[] arr_object, boolean z) {
        Intrinsics.checkNotNullParameter(arr_object, "<this>");
        if(z && Intrinsics.areEqual(arr_object.getClass(), Object[].class)) {
            return arr_object;
        }
        Object[] arr_object1 = Arrays.copyOf(arr_object, arr_object.length, Object[].class);
        Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, this.size, Array<Any?>::class.java)");
        return arr_object1;
    }

    public static final List createListBuilder() {
        return new ListBuilder();
    }

    public static final List createListBuilder(int v) {
        return new ListBuilder(v);
    }

    public static final List listOf(Object object0) {
        List list0 = Collections.singletonList(object0);
        Intrinsics.checkNotNullExpressionValue(list0, "singletonList(element)");
        return list0;
    }

    public static final List shuffled(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        List list0 = CollectionsKt.toMutableList(iterable0);
        Collections.shuffle(list0);
        return list0;
    }

    public static final List shuffled(Iterable iterable0, Random random0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(random0, "random");
        List list0 = CollectionsKt.toMutableList(iterable0);
        Collections.shuffle(list0, random0);
        return list0;
    }

    private static final List toList(Enumeration enumeration0) {
        Intrinsics.checkNotNullParameter(enumeration0, "<this>");
        ArrayList arrayList0 = Collections.list(enumeration0);
        Intrinsics.checkNotNullExpressionValue(arrayList0, "list(this)");
        return arrayList0;
    }
}

