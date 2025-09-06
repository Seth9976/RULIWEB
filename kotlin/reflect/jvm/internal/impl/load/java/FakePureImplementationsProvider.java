package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.StandardClassIds;

public final class FakePureImplementationsProvider {
    public static final FakePureImplementationsProvider INSTANCE;
    private static final Map pureImplementationsClassIds;
    private static final Map pureImplementationsFqNames;

    static {
        FakePureImplementationsProvider fakePureImplementationsProvider0 = new FakePureImplementationsProvider();
        FakePureImplementationsProvider.INSTANCE = fakePureImplementationsProvider0;
        Map map0 = new LinkedHashMap();
        FakePureImplementationsProvider.pureImplementationsClassIds = map0;
        fakePureImplementationsProvider0.implementedWith(StandardClassIds.INSTANCE.getMutableList(), fakePureImplementationsProvider0.fqNameListOf(new String[]{"java.util.ArrayList", "java.util.LinkedList"}));
        fakePureImplementationsProvider0.implementedWith(StandardClassIds.INSTANCE.getMutableSet(), fakePureImplementationsProvider0.fqNameListOf(new String[]{"java.util.HashSet", "java.util.TreeSet", "java.util.LinkedHashSet"}));
        fakePureImplementationsProvider0.implementedWith(StandardClassIds.INSTANCE.getMutableMap(), fakePureImplementationsProvider0.fqNameListOf(new String[]{"java.util.HashMap", "java.util.TreeMap", "java.util.LinkedHashMap", "java.util.concurrent.ConcurrentHashMap", "java.util.concurrent.ConcurrentSkipListMap"}));
        ClassId classId0 = ClassId.topLevel(new FqName("java.util.function.Function"));
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(FqName(\"java.util.function.Function\"))");
        fakePureImplementationsProvider0.implementedWith(classId0, fakePureImplementationsProvider0.fqNameListOf(new String[]{"java.util.function.UnaryOperator"}));
        ClassId classId1 = ClassId.topLevel(new FqName("java.util.function.BiFunction"));
        Intrinsics.checkNotNullExpressionValue(classId1, "topLevel(FqName(\"java.util.function.BiFunction\"))");
        fakePureImplementationsProvider0.implementedWith(classId1, fakePureImplementationsProvider0.fqNameListOf(new String[]{"java.util.function.BinaryOperator"}));
        ArrayList arrayList0 = new ArrayList(map0.size());
        for(Object object0: map0.entrySet()) {
            ClassId classId2 = (ClassId)((Map.Entry)object0).getKey();
            ClassId classId3 = (ClassId)((Map.Entry)object0).getValue();
            arrayList0.add(TuplesKt.to(classId2.asSingleFqName(), classId3.asSingleFqName()));
        }
        FakePureImplementationsProvider.pureImplementationsFqNames = MapsKt.toMap(arrayList0);
    }

    private final List fqNameListOf(String[] arr_s) {
        ArrayList arrayList0 = new ArrayList(arr_s.length);
        for(int v = 0; v < arr_s.length; ++v) {
            arrayList0.add(ClassId.topLevel(new FqName(arr_s[v])));
        }
        return arrayList0;
    }

    public final FqName getPurelyImplementedInterface(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "classFqName");
        return (FqName)FakePureImplementationsProvider.pureImplementationsFqNames.get(fqName0);
    }

    private final void implementedWith(ClassId classId0, List list0) {
        Map map0 = FakePureImplementationsProvider.pureImplementationsClassIds;
        for(Object object0: list0) {
            ClassId classId1 = (ClassId)object0;
            map0.put(object0, classId0);
        }
    }
}

