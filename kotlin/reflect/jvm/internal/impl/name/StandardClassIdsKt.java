package kotlin.reflect.jvm.internal.impl.name;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class StandardClassIdsKt {
    private static final FqName JAVA_LANG_ANNOTATION_PACKAGE;
    private static final FqName JAVA_LANG_PACKAGE;

    static {
        FqName fqName0 = new FqName("java.lang");
        StandardClassIdsKt.JAVA_LANG_PACKAGE = fqName0;
        FqName fqName1 = fqName0.child(Name.identifier("annotation"));
        Intrinsics.checkNotNullExpressionValue(fqName1, "JAVA_LANG_PACKAGE.child(…identifier(\"annotation\"))");
        StandardClassIdsKt.JAVA_LANG_ANNOTATION_PACKAGE = fqName1;
    }

    public static final ClassId access$annotationId(String s) {
        return StandardClassIdsKt.annotationId(s);
    }

    public static final ClassId access$baseId(String s) {
        return StandardClassIdsKt.baseId(s);
    }

    public static final ClassId access$collectionsId(String s) {
        return StandardClassIdsKt.collectionsId(s);
    }

    public static final ClassId access$coroutinesId(String s) {
        return StandardClassIdsKt.coroutinesId(s);
    }

    public static final ClassId access$enumsId(String s) {
        return StandardClassIdsKt.enumsId(s);
    }

    public static final Map access$inverseMap(Map map0) {
        return StandardClassIdsKt.inverseMap(map0);
    }

    public static final ClassId access$primitiveArrayId(Name name0) {
        return StandardClassIdsKt.primitiveArrayId(name0);
    }

    public static final ClassId access$rangesId(String s) {
        return StandardClassIdsKt.rangesId(s);
    }

    public static final ClassId access$reflectId(String s) {
        return StandardClassIdsKt.reflectId(s);
    }

    public static final ClassId access$unsignedId(ClassId classId0) {
        return StandardClassIdsKt.unsignedId(classId0);
    }

    private static final ClassId annotationId(String s) {
        return new ClassId(StandardClassIds.INSTANCE.getBASE_ANNOTATION_PACKAGE(), Name.identifier(s));
    }

    private static final ClassId baseId(String s) {
        return new ClassId(StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE(), Name.identifier(s));
    }

    private static final ClassId collectionsId(String s) {
        return new ClassId(StandardClassIds.INSTANCE.getBASE_COLLECTIONS_PACKAGE(), Name.identifier(s));
    }

    private static final ClassId coroutinesId(String s) {
        return new ClassId(StandardClassIds.INSTANCE.getBASE_COROUTINES_PACKAGE(), Name.identifier(s));
    }

    private static final ClassId enumsId(String s) {
        return new ClassId(StandardClassIds.INSTANCE.getBASE_ENUMS_PACKAGE(), Name.identifier(s));
    }

    private static final Map inverseMap(Map map0) {
        Iterable iterable0 = map0.entrySet();
        Map map1 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable0, 10)), 16));
        for(Object object0: iterable0) {
            Object object1 = ((Map.Entry)object0).getKey();
            Pair pair0 = TuplesKt.to(((Map.Entry)object0).getValue(), object1);
            map1.put(pair0.getFirst(), pair0.getSecond());
        }
        return map1;
    }

    // 去混淆评级： 低(20)
    private static final ClassId primitiveArrayId(Name name0) {
        return new ClassId(StandardClassIds.INSTANCE.getArray().getPackageFqName(), Name.identifier((name0.getIdentifier() + "Array")));
    }

    private static final ClassId rangesId(String s) {
        return new ClassId(StandardClassIds.INSTANCE.getBASE_RANGES_PACKAGE(), Name.identifier(s));
    }

    private static final ClassId reflectId(String s) {
        return new ClassId(StandardClassIds.INSTANCE.getBASE_REFLECT_PACKAGE(), Name.identifier(s));
    }

    private static final ClassId unsignedId(ClassId classId0) {
        Name name0 = Name.identifier(("U" + classId0.getShortClassName().getIdentifier()));
        return new ClassId(StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE(), name0);
    }
}

