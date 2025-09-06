package kotlin.reflect.jvm.internal.impl.name;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class StandardClassIds {
    private static final ClassId Annotation;
    private static final ClassId AnnotationRetention;
    private static final ClassId AnnotationTarget;
    private static final ClassId Any;
    private static final ClassId Array;
    private static final FqName BASE_ANNOTATION_PACKAGE;
    private static final FqName BASE_COLLECTIONS_PACKAGE;
    private static final FqName BASE_COROUTINES_PACKAGE;
    private static final FqName BASE_ENUMS_PACKAGE;
    private static final FqName BASE_INTERNAL_IR_PACKAGE;
    private static final FqName BASE_INTERNAL_PACKAGE;
    private static final FqName BASE_JVM_INTERNAL_PACKAGE;
    private static final FqName BASE_JVM_PACKAGE;
    private static final FqName BASE_KOTLIN_PACKAGE;
    private static final FqName BASE_RANGES_PACKAGE;
    private static final FqName BASE_REFLECT_PACKAGE;
    private static final ClassId Boolean;
    private static final ClassId Byte;
    private static final ClassId Char;
    private static final ClassId CharIterator;
    private static final ClassId CharRange;
    private static final ClassId CharSequence;
    private static final ClassId Cloneable;
    private static final ClassId Collection;
    private static final ClassId Comparable;
    private static final ClassId Continuation;
    private static final ClassId Double;
    private static final ClassId Enum;
    private static final ClassId EnumEntries;
    private static final ClassId Float;
    private static final ClassId Function;
    public static final StandardClassIds INSTANCE;
    private static final ClassId Int;
    private static final ClassId IntRange;
    private static final ClassId Iterable;
    private static final ClassId Iterator;
    private static final ClassId KCallable;
    private static final ClassId KClass;
    private static final ClassId KFunction;
    private static final ClassId KMutableProperty;
    private static final ClassId KMutableProperty0;
    private static final ClassId KMutableProperty1;
    private static final ClassId KMutableProperty2;
    private static final ClassId KProperty;
    private static final ClassId KProperty0;
    private static final ClassId KProperty1;
    private static final ClassId KProperty2;
    private static final ClassId List;
    private static final ClassId ListIterator;
    private static final ClassId Long;
    private static final ClassId LongRange;
    private static final ClassId Map;
    private static final ClassId MapEntry;
    private static final ClassId MutableCollection;
    private static final ClassId MutableIterable;
    private static final ClassId MutableIterator;
    private static final ClassId MutableList;
    private static final ClassId MutableListIterator;
    private static final ClassId MutableMap;
    private static final ClassId MutableMapEntry;
    private static final ClassId MutableSet;
    private static final ClassId Nothing;
    private static final ClassId Number;
    private static final ClassId Result;
    private static final ClassId Set;
    private static final ClassId Short;
    private static final ClassId String;
    private static final ClassId Throwable;
    private static final ClassId UByte;
    private static final ClassId UInt;
    private static final ClassId ULong;
    private static final ClassId UShort;
    private static final ClassId Unit;
    private static final Set builtInsPackages;
    private static final Set constantAllowedTypes;
    private static final Map elementTypeByPrimitiveArrayType;
    private static final Map elementTypeByUnsignedArrayType;
    private static final Map primitiveArrayTypeByElementType;
    private static final Set primitiveTypes;
    private static final Map unsignedArrayTypeByElementType;
    private static final Set unsignedTypes;

    static {
        StandardClassIds.INSTANCE = new StandardClassIds();
        FqName fqName0 = new FqName("kotlin");
        StandardClassIds.BASE_KOTLIN_PACKAGE = fqName0;
        FqName fqName1 = fqName0.child(Name.identifier("reflect"));
        Intrinsics.checkNotNullExpressionValue(fqName1, "BASE_KOTLIN_PACKAGE.chil…me.identifier(\"reflect\"))");
        StandardClassIds.BASE_REFLECT_PACKAGE = fqName1;
        FqName fqName2 = fqName0.child(Name.identifier("collections"));
        Intrinsics.checkNotNullExpressionValue(fqName2, "BASE_KOTLIN_PACKAGE.chil…dentifier(\"collections\"))");
        StandardClassIds.BASE_COLLECTIONS_PACKAGE = fqName2;
        FqName fqName3 = fqName0.child(Name.identifier("ranges"));
        Intrinsics.checkNotNullExpressionValue(fqName3, "BASE_KOTLIN_PACKAGE.chil…ame.identifier(\"ranges\"))");
        StandardClassIds.BASE_RANGES_PACKAGE = fqName3;
        FqName fqName4 = fqName0.child(Name.identifier("jvm"));
        Intrinsics.checkNotNullExpressionValue(fqName4, "BASE_KOTLIN_PACKAGE.child(Name.identifier(\"jvm\"))");
        StandardClassIds.BASE_JVM_PACKAGE = fqName4;
        FqName fqName5 = fqName4.child(Name.identifier("internal"));
        Intrinsics.checkNotNullExpressionValue(fqName5, "BASE_JVM_PACKAGE.child(N…e.identifier(\"internal\"))");
        StandardClassIds.BASE_JVM_INTERNAL_PACKAGE = fqName5;
        FqName fqName6 = fqName0.child(Name.identifier("annotation"));
        Intrinsics.checkNotNullExpressionValue(fqName6, "BASE_KOTLIN_PACKAGE.chil…identifier(\"annotation\"))");
        StandardClassIds.BASE_ANNOTATION_PACKAGE = fqName6;
        FqName fqName7 = fqName0.child(Name.identifier("internal"));
        Intrinsics.checkNotNullExpressionValue(fqName7, "BASE_KOTLIN_PACKAGE.chil…e.identifier(\"internal\"))");
        StandardClassIds.BASE_INTERNAL_PACKAGE = fqName7;
        FqName fqName8 = fqName7.child(Name.identifier("ir"));
        Intrinsics.checkNotNullExpressionValue(fqName8, "BASE_INTERNAL_PACKAGE.child(Name.identifier(\"ir\"))");
        StandardClassIds.BASE_INTERNAL_IR_PACKAGE = fqName8;
        FqName fqName9 = fqName0.child(Name.identifier("coroutines"));
        Intrinsics.checkNotNullExpressionValue(fqName9, "BASE_KOTLIN_PACKAGE.chil…identifier(\"coroutines\"))");
        StandardClassIds.BASE_COROUTINES_PACKAGE = fqName9;
        FqName fqName10 = fqName0.child(Name.identifier("enums"));
        Intrinsics.checkNotNullExpressionValue(fqName10, "BASE_KOTLIN_PACKAGE.chil…Name.identifier(\"enums\"))");
        StandardClassIds.BASE_ENUMS_PACKAGE = fqName10;
        StandardClassIds.builtInsPackages = SetsKt.setOf(new FqName[]{fqName0, fqName2, fqName3, fqName6, fqName1, fqName7, fqName9});
        StandardClassIds.Nothing = StandardClassIdsKt.access$baseId("Nothing");
        StandardClassIds.Unit = StandardClassIdsKt.access$baseId("Unit");
        StandardClassIds.Any = StandardClassIdsKt.access$baseId("Any");
        StandardClassIds.Enum = StandardClassIdsKt.access$baseId("Enum");
        StandardClassIds.Annotation = StandardClassIdsKt.access$baseId("Annotation");
        StandardClassIds.Array = StandardClassIdsKt.access$baseId("Array");
        ClassId classId0 = StandardClassIdsKt.access$baseId("Boolean");
        StandardClassIds.Boolean = classId0;
        ClassId classId1 = StandardClassIdsKt.access$baseId("Char");
        StandardClassIds.Char = classId1;
        ClassId classId2 = StandardClassIdsKt.access$baseId("Byte");
        StandardClassIds.Byte = classId2;
        ClassId classId3 = StandardClassIdsKt.access$baseId("Short");
        StandardClassIds.Short = classId3;
        ClassId classId4 = StandardClassIdsKt.access$baseId("Int");
        StandardClassIds.Int = classId4;
        ClassId classId5 = StandardClassIdsKt.access$baseId("Long");
        StandardClassIds.Long = classId5;
        ClassId classId6 = StandardClassIdsKt.access$baseId("Float");
        StandardClassIds.Float = classId6;
        ClassId classId7 = StandardClassIdsKt.access$baseId("Double");
        StandardClassIds.Double = classId7;
        StandardClassIds.UByte = StandardClassIdsKt.access$unsignedId(classId2);
        StandardClassIds.UShort = StandardClassIdsKt.access$unsignedId(classId3);
        StandardClassIds.UInt = StandardClassIdsKt.access$unsignedId(classId4);
        StandardClassIds.ULong = StandardClassIdsKt.access$unsignedId(classId5);
        StandardClassIds.CharSequence = StandardClassIdsKt.access$baseId("CharSequence");
        StandardClassIds.String = StandardClassIdsKt.access$baseId("String");
        StandardClassIds.Throwable = StandardClassIdsKt.access$baseId("Throwable");
        StandardClassIds.Cloneable = StandardClassIdsKt.access$baseId("Cloneable");
        StandardClassIds.KProperty = StandardClassIdsKt.access$reflectId("KProperty");
        StandardClassIds.KMutableProperty = StandardClassIdsKt.access$reflectId("KMutableProperty");
        StandardClassIds.KProperty0 = StandardClassIdsKt.access$reflectId("KProperty0");
        StandardClassIds.KMutableProperty0 = StandardClassIdsKt.access$reflectId("KMutableProperty0");
        StandardClassIds.KProperty1 = StandardClassIdsKt.access$reflectId("KProperty1");
        StandardClassIds.KMutableProperty1 = StandardClassIdsKt.access$reflectId("KMutableProperty1");
        StandardClassIds.KProperty2 = StandardClassIdsKt.access$reflectId("KProperty2");
        StandardClassIds.KMutableProperty2 = StandardClassIdsKt.access$reflectId("KMutableProperty2");
        StandardClassIds.KFunction = StandardClassIdsKt.access$reflectId("KFunction");
        StandardClassIds.KClass = StandardClassIdsKt.access$reflectId("KClass");
        StandardClassIds.KCallable = StandardClassIdsKt.access$reflectId("KCallable");
        StandardClassIds.Comparable = StandardClassIdsKt.access$baseId("Comparable");
        StandardClassIds.Number = StandardClassIdsKt.access$baseId("Number");
        StandardClassIds.Function = StandardClassIdsKt.access$baseId("Function");
        Set set0 = SetsKt.setOf(new ClassId[]{classId0, classId1, classId2, classId3, classId4, classId5, classId6, classId7});
        StandardClassIds.primitiveTypes = set0;
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set0, 10)), 16));
        for(Object object0: set0) {
            Name name0 = ((ClassId)object0).getShortClassName();
            Intrinsics.checkNotNullExpressionValue(name0, "id.shortClassName");
            linkedHashMap0.put(object0, StandardClassIdsKt.access$primitiveArrayId(name0));
        }
        StandardClassIds.primitiveArrayTypeByElementType = linkedHashMap0;
        StandardClassIds.elementTypeByPrimitiveArrayType = StandardClassIdsKt.access$inverseMap(linkedHashMap0);
        Set set1 = SetsKt.setOf(new ClassId[]{StandardClassIds.UByte, StandardClassIds.UShort, StandardClassIds.UInt, StandardClassIds.ULong});
        StandardClassIds.unsignedTypes = set1;
        LinkedHashMap linkedHashMap1 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set1, 10)), 16));
        for(Object object1: set1) {
            Name name1 = ((ClassId)object1).getShortClassName();
            Intrinsics.checkNotNullExpressionValue(name1, "id.shortClassName");
            linkedHashMap1.put(object1, StandardClassIdsKt.access$primitiveArrayId(name1));
        }
        StandardClassIds.unsignedArrayTypeByElementType = linkedHashMap1;
        StandardClassIds.elementTypeByUnsignedArrayType = StandardClassIdsKt.access$inverseMap(linkedHashMap1);
        StandardClassIds.constantAllowedTypes = SetsKt.plus(SetsKt.plus(StandardClassIds.primitiveTypes, StandardClassIds.unsignedTypes), StandardClassIds.String);
        StandardClassIds.Continuation = StandardClassIdsKt.access$coroutinesId("Continuation");
        StandardClassIds.Iterator = StandardClassIdsKt.access$collectionsId("Iterator");
        StandardClassIds.Iterable = StandardClassIdsKt.access$collectionsId("Iterable");
        StandardClassIds.Collection = StandardClassIdsKt.access$collectionsId("Collection");
        StandardClassIds.List = StandardClassIdsKt.access$collectionsId("List");
        StandardClassIds.ListIterator = StandardClassIdsKt.access$collectionsId("ListIterator");
        StandardClassIds.Set = StandardClassIdsKt.access$collectionsId("Set");
        ClassId classId8 = StandardClassIdsKt.access$collectionsId("Map");
        StandardClassIds.Map = classId8;
        StandardClassIds.MutableIterator = StandardClassIdsKt.access$collectionsId("MutableIterator");
        StandardClassIds.CharIterator = StandardClassIdsKt.access$collectionsId("CharIterator");
        StandardClassIds.MutableIterable = StandardClassIdsKt.access$collectionsId("MutableIterable");
        StandardClassIds.MutableCollection = StandardClassIdsKt.access$collectionsId("MutableCollection");
        StandardClassIds.MutableList = StandardClassIdsKt.access$collectionsId("MutableList");
        StandardClassIds.MutableListIterator = StandardClassIdsKt.access$collectionsId("MutableListIterator");
        StandardClassIds.MutableSet = StandardClassIdsKt.access$collectionsId("MutableSet");
        ClassId classId9 = StandardClassIdsKt.access$collectionsId("MutableMap");
        StandardClassIds.MutableMap = classId9;
        ClassId classId10 = classId8.createNestedClassId(Name.identifier("Entry"));
        Intrinsics.checkNotNullExpressionValue(classId10, "Map.createNestedClassId(Name.identifier(\"Entry\"))");
        StandardClassIds.MapEntry = classId10;
        ClassId classId11 = classId9.createNestedClassId(Name.identifier("MutableEntry"));
        Intrinsics.checkNotNullExpressionValue(classId11, "MutableMap.createNestedC…entifier(\"MutableEntry\"))");
        StandardClassIds.MutableMapEntry = classId11;
        StandardClassIds.Result = StandardClassIdsKt.access$baseId("Result");
        StandardClassIds.IntRange = StandardClassIdsKt.access$rangesId("IntRange");
        StandardClassIds.LongRange = StandardClassIdsKt.access$rangesId("LongRange");
        StandardClassIds.CharRange = StandardClassIdsKt.access$rangesId("CharRange");
        StandardClassIds.AnnotationRetention = StandardClassIdsKt.access$annotationId("AnnotationRetention");
        StandardClassIds.AnnotationTarget = StandardClassIdsKt.access$annotationId("AnnotationTarget");
        StandardClassIds.EnumEntries = StandardClassIdsKt.access$enumsId("EnumEntries");
    }

    public final ClassId getArray() {
        return StandardClassIds.Array;
    }

    public final FqName getBASE_ANNOTATION_PACKAGE() {
        return StandardClassIds.BASE_ANNOTATION_PACKAGE;
    }

    public final FqName getBASE_COLLECTIONS_PACKAGE() {
        return StandardClassIds.BASE_COLLECTIONS_PACKAGE;
    }

    public final FqName getBASE_COROUTINES_PACKAGE() {
        return StandardClassIds.BASE_COROUTINES_PACKAGE;
    }

    public final FqName getBASE_ENUMS_PACKAGE() {
        return StandardClassIds.BASE_ENUMS_PACKAGE;
    }

    public final FqName getBASE_KOTLIN_PACKAGE() {
        return StandardClassIds.BASE_KOTLIN_PACKAGE;
    }

    public final FqName getBASE_RANGES_PACKAGE() {
        return StandardClassIds.BASE_RANGES_PACKAGE;
    }

    public final FqName getBASE_REFLECT_PACKAGE() {
        return StandardClassIds.BASE_REFLECT_PACKAGE;
    }

    public final ClassId getEnumEntries() {
        return StandardClassIds.EnumEntries;
    }

    public final ClassId getKClass() {
        return StandardClassIds.KClass;
    }

    public final ClassId getKFunction() {
        return StandardClassIds.KFunction;
    }

    public final ClassId getMutableList() {
        return StandardClassIds.MutableList;
    }

    public final ClassId getMutableMap() {
        return StandardClassIds.MutableMap;
    }

    public final ClassId getMutableSet() {
        return StandardClassIds.MutableSet;
    }
}

