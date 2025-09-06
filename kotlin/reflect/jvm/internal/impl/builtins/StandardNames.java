package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

public final class StandardNames {
    public static final class FqNames {
        public static final FqNames INSTANCE;
        public static final FqNameUnsafe _boolean;
        public static final FqNameUnsafe _byte;
        public static final FqNameUnsafe _char;
        public static final FqNameUnsafe _double;
        public static final FqNameUnsafe _enum;
        public static final FqNameUnsafe _float;
        public static final FqNameUnsafe _int;
        public static final FqNameUnsafe _long;
        public static final FqNameUnsafe _short;
        public static final FqName accessibleLateinitPropertyLiteral;
        public static final FqName annotation;
        public static final FqName annotationRetention;
        public static final FqName annotationTarget;
        public static final FqNameUnsafe any;
        public static final FqNameUnsafe array;
        public static final Map arrayClassFqNameToPrimitiveType;
        public static final FqNameUnsafe charSequence;
        public static final FqNameUnsafe cloneable;
        public static final FqName collection;
        public static final FqName comparable;
        public static final FqName contextFunctionTypeParams;
        public static final FqName deprecated;
        public static final FqName deprecatedSinceKotlin;
        public static final FqName deprecationLevel;
        public static final FqName extensionFunctionType;
        public static final Map fqNameToPrimitiveType;
        public static final FqNameUnsafe functionSupertype;
        public static final FqNameUnsafe intRange;
        public static final FqName iterable;
        public static final FqName iterator;
        public static final FqNameUnsafe kCallable;
        public static final FqNameUnsafe kClass;
        public static final FqNameUnsafe kDeclarationContainer;
        public static final FqNameUnsafe kMutableProperty0;
        public static final FqNameUnsafe kMutableProperty1;
        public static final FqNameUnsafe kMutableProperty2;
        public static final FqNameUnsafe kMutablePropertyFqName;
        public static final ClassId kProperty;
        public static final FqNameUnsafe kProperty0;
        public static final FqNameUnsafe kProperty1;
        public static final FqNameUnsafe kProperty2;
        public static final FqNameUnsafe kPropertyFqName;
        public static final FqName list;
        public static final FqName listIterator;
        public static final FqNameUnsafe longRange;
        public static final FqName map;
        public static final FqName mapEntry;
        public static final FqName mustBeDocumented;
        public static final FqName mutableCollection;
        public static final FqName mutableIterable;
        public static final FqName mutableIterator;
        public static final FqName mutableList;
        public static final FqName mutableListIterator;
        public static final FqName mutableMap;
        public static final FqName mutableMapEntry;
        public static final FqName mutableSet;
        public static final FqNameUnsafe nothing;
        public static final FqNameUnsafe number;
        public static final FqName parameterName;
        public static final ClassId parameterNameClassId;
        public static final Set primitiveArrayTypeShortNames;
        public static final Set primitiveTypeShortNames;
        public static final FqName publishedApi;
        public static final FqName repeatable;
        public static final ClassId repeatableClassId;
        public static final FqName replaceWith;
        public static final FqName retention;
        public static final ClassId retentionClassId;
        public static final FqName set;
        public static final FqNameUnsafe string;
        public static final FqName suppress;
        public static final FqName target;
        public static final ClassId targetClassId;
        public static final FqName throwable;
        public static final ClassId uByte;
        public static final FqName uByteArrayFqName;
        public static final FqName uByteFqName;
        public static final ClassId uInt;
        public static final FqName uIntArrayFqName;
        public static final FqName uIntFqName;
        public static final ClassId uLong;
        public static final FqName uLongArrayFqName;
        public static final FqName uLongFqName;
        public static final ClassId uShort;
        public static final FqName uShortArrayFqName;
        public static final FqName uShortFqName;
        public static final FqNameUnsafe unit;
        public static final FqName unsafeVariance;

        static {
            FqNames standardNames$FqNames0 = new FqNames();
            FqNames.INSTANCE = standardNames$FqNames0;
            FqNames.any = standardNames$FqNames0.fqNameUnsafe("Any");
            FqNames.nothing = standardNames$FqNames0.fqNameUnsafe("Nothing");
            FqNames.cloneable = standardNames$FqNames0.fqNameUnsafe("Cloneable");
            FqNames.suppress = standardNames$FqNames0.fqName("Suppress");
            FqNames.unit = standardNames$FqNames0.fqNameUnsafe("Unit");
            FqNames.charSequence = standardNames$FqNames0.fqNameUnsafe("CharSequence");
            FqNames.string = standardNames$FqNames0.fqNameUnsafe("String");
            FqNames.array = standardNames$FqNames0.fqNameUnsafe("Array");
            FqNames._boolean = standardNames$FqNames0.fqNameUnsafe("Boolean");
            FqNames._char = standardNames$FqNames0.fqNameUnsafe("Char");
            FqNames._byte = standardNames$FqNames0.fqNameUnsafe("Byte");
            FqNames._short = standardNames$FqNames0.fqNameUnsafe("Short");
            FqNames._int = standardNames$FqNames0.fqNameUnsafe("Int");
            FqNames._long = standardNames$FqNames0.fqNameUnsafe("Long");
            FqNames._float = standardNames$FqNames0.fqNameUnsafe("Float");
            FqNames._double = standardNames$FqNames0.fqNameUnsafe("Double");
            FqNames.number = standardNames$FqNames0.fqNameUnsafe("Number");
            FqNames._enum = standardNames$FqNames0.fqNameUnsafe("Enum");
            FqNames.functionSupertype = standardNames$FqNames0.fqNameUnsafe("Function");
            FqNames.throwable = standardNames$FqNames0.fqName("Throwable");
            FqNames.comparable = standardNames$FqNames0.fqName("Comparable");
            FqNames.intRange = standardNames$FqNames0.rangesFqName("IntRange");
            FqNames.longRange = standardNames$FqNames0.rangesFqName("LongRange");
            FqNames.deprecated = standardNames$FqNames0.fqName("Deprecated");
            FqNames.deprecatedSinceKotlin = standardNames$FqNames0.fqName("DeprecatedSinceKotlin");
            FqNames.deprecationLevel = standardNames$FqNames0.fqName("DeprecationLevel");
            FqNames.replaceWith = standardNames$FqNames0.fqName("ReplaceWith");
            FqNames.extensionFunctionType = standardNames$FqNames0.fqName("ExtensionFunctionType");
            FqNames.contextFunctionTypeParams = standardNames$FqNames0.fqName("ContextFunctionTypeParams");
            FqName fqName0 = standardNames$FqNames0.fqName("ParameterName");
            FqNames.parameterName = fqName0;
            ClassId classId0 = ClassId.topLevel(fqName0);
            Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(parameterName)");
            FqNames.parameterNameClassId = classId0;
            FqNames.annotation = standardNames$FqNames0.fqName("Annotation");
            FqName fqName1 = standardNames$FqNames0.annotationName("Target");
            FqNames.target = fqName1;
            ClassId classId1 = ClassId.topLevel(fqName1);
            Intrinsics.checkNotNullExpressionValue(classId1, "topLevel(target)");
            FqNames.targetClassId = classId1;
            FqNames.annotationTarget = standardNames$FqNames0.annotationName("AnnotationTarget");
            FqNames.annotationRetention = standardNames$FqNames0.annotationName("AnnotationRetention");
            FqName fqName2 = standardNames$FqNames0.annotationName("Retention");
            FqNames.retention = fqName2;
            ClassId classId2 = ClassId.topLevel(fqName2);
            Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(retention)");
            FqNames.retentionClassId = classId2;
            FqName fqName3 = standardNames$FqNames0.annotationName("Repeatable");
            FqNames.repeatable = fqName3;
            ClassId classId3 = ClassId.topLevel(fqName3);
            Intrinsics.checkNotNullExpressionValue(classId3, "topLevel(repeatable)");
            FqNames.repeatableClassId = classId3;
            FqNames.mustBeDocumented = standardNames$FqNames0.annotationName("MustBeDocumented");
            FqNames.unsafeVariance = standardNames$FqNames0.fqName("UnsafeVariance");
            FqNames.publishedApi = standardNames$FqNames0.fqName("PublishedApi");
            FqNames.accessibleLateinitPropertyLiteral = standardNames$FqNames0.internalName("AccessibleLateinitPropertyLiteral");
            FqNames.iterator = standardNames$FqNames0.collectionsFqName("Iterator");
            FqNames.iterable = standardNames$FqNames0.collectionsFqName("Iterable");
            FqNames.collection = standardNames$FqNames0.collectionsFqName("Collection");
            FqNames.list = standardNames$FqNames0.collectionsFqName("List");
            FqNames.listIterator = standardNames$FqNames0.collectionsFqName("ListIterator");
            FqNames.set = standardNames$FqNames0.collectionsFqName("Set");
            FqName fqName4 = standardNames$FqNames0.collectionsFqName("Map");
            FqNames.map = fqName4;
            FqName fqName5 = fqName4.child(Name.identifier("Entry"));
            Intrinsics.checkNotNullExpressionValue(fqName5, "map.child(Name.identifier(\"Entry\"))");
            FqNames.mapEntry = fqName5;
            FqNames.mutableIterator = standardNames$FqNames0.collectionsFqName("MutableIterator");
            FqNames.mutableIterable = standardNames$FqNames0.collectionsFqName("MutableIterable");
            FqNames.mutableCollection = standardNames$FqNames0.collectionsFqName("MutableCollection");
            FqNames.mutableList = standardNames$FqNames0.collectionsFqName("MutableList");
            FqNames.mutableListIterator = standardNames$FqNames0.collectionsFqName("MutableListIterator");
            FqNames.mutableSet = standardNames$FqNames0.collectionsFqName("MutableSet");
            FqName fqName6 = standardNames$FqNames0.collectionsFqName("MutableMap");
            FqNames.mutableMap = fqName6;
            FqName fqName7 = fqName6.child(Name.identifier("MutableEntry"));
            Intrinsics.checkNotNullExpressionValue(fqName7, "mutableMap.child(Name.identifier(\"MutableEntry\"))");
            FqNames.mutableMapEntry = fqName7;
            FqNames.kClass = FqNames.reflect("KClass");
            FqNames.kCallable = FqNames.reflect("KCallable");
            FqNames.kProperty0 = FqNames.reflect("KProperty0");
            FqNames.kProperty1 = FqNames.reflect("KProperty1");
            FqNames.kProperty2 = FqNames.reflect("KProperty2");
            FqNames.kMutableProperty0 = FqNames.reflect("KMutableProperty0");
            FqNames.kMutableProperty1 = FqNames.reflect("KMutableProperty1");
            FqNames.kMutableProperty2 = FqNames.reflect("KMutableProperty2");
            FqNameUnsafe fqNameUnsafe0 = FqNames.reflect("KProperty");
            FqNames.kPropertyFqName = fqNameUnsafe0;
            FqNames.kMutablePropertyFqName = FqNames.reflect("KMutableProperty");
            ClassId classId4 = ClassId.topLevel(fqNameUnsafe0.toSafe());
            Intrinsics.checkNotNullExpressionValue(classId4, "topLevel(kPropertyFqName.toSafe())");
            FqNames.kProperty = classId4;
            FqNames.kDeclarationContainer = FqNames.reflect("KDeclarationContainer");
            FqName fqName8 = standardNames$FqNames0.fqName("UByte");
            FqNames.uByteFqName = fqName8;
            FqName fqName9 = standardNames$FqNames0.fqName("UShort");
            FqNames.uShortFqName = fqName9;
            FqName fqName10 = standardNames$FqNames0.fqName("UInt");
            FqNames.uIntFqName = fqName10;
            FqName fqName11 = standardNames$FqNames0.fqName("ULong");
            FqNames.uLongFqName = fqName11;
            ClassId classId5 = ClassId.topLevel(fqName8);
            Intrinsics.checkNotNullExpressionValue(classId5, "topLevel(uByteFqName)");
            FqNames.uByte = classId5;
            ClassId classId6 = ClassId.topLevel(fqName9);
            Intrinsics.checkNotNullExpressionValue(classId6, "topLevel(uShortFqName)");
            FqNames.uShort = classId6;
            ClassId classId7 = ClassId.topLevel(fqName10);
            Intrinsics.checkNotNullExpressionValue(classId7, "topLevel(uIntFqName)");
            FqNames.uInt = classId7;
            ClassId classId8 = ClassId.topLevel(fqName11);
            Intrinsics.checkNotNullExpressionValue(classId8, "topLevel(uLongFqName)");
            FqNames.uLong = classId8;
            FqNames.uByteArrayFqName = standardNames$FqNames0.fqName("UByteArray");
            FqNames.uShortArrayFqName = standardNames$FqNames0.fqName("UShortArray");
            FqNames.uIntArrayFqName = standardNames$FqNames0.fqName("UIntArray");
            FqNames.uLongArrayFqName = standardNames$FqNames0.fqName("ULongArray");
            HashSet hashSet0 = CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] arr_primitiveType = PrimitiveType.values();
            for(int v1 = 0; v1 < arr_primitiveType.length; ++v1) {
                hashSet0.add(arr_primitiveType[v1].getTypeName());
            }
            FqNames.primitiveTypeShortNames = hashSet0;
            HashSet hashSet1 = CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] arr_primitiveType1 = PrimitiveType.values();
            for(int v2 = 0; v2 < arr_primitiveType1.length; ++v2) {
                hashSet1.add(arr_primitiveType1[v2].getArrayTypeName());
            }
            FqNames.primitiveArrayTypeShortNames = hashSet1;
            HashMap hashMap0 = CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] arr_primitiveType2 = PrimitiveType.values();
            for(int v3 = 0; v3 < arr_primitiveType2.length; ++v3) {
                PrimitiveType primitiveType0 = arr_primitiveType2[v3];
                String s = primitiveType0.getTypeName().asString();
                Intrinsics.checkNotNullExpressionValue(s, "primitiveType.typeName.asString()");
                hashMap0.put(FqNames.INSTANCE.fqNameUnsafe(s), primitiveType0);
            }
            FqNames.fqNameToPrimitiveType = hashMap0;
            HashMap hashMap1 = CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] arr_primitiveType3 = PrimitiveType.values();
            for(int v = 0; v < arr_primitiveType3.length; ++v) {
                PrimitiveType primitiveType1 = arr_primitiveType3[v];
                String s1 = primitiveType1.getArrayTypeName().asString();
                Intrinsics.checkNotNullExpressionValue(s1, "primitiveType.arrayTypeName.asString()");
                hashMap1.put(FqNames.INSTANCE.fqNameUnsafe(s1), primitiveType1);
            }
            FqNames.arrayClassFqNameToPrimitiveType = hashMap1;
        }

        private final FqName annotationName(String s) {
            Name name0 = Name.identifier(s);
            FqName fqName0 = StandardNames.ANNOTATION_PACKAGE_FQ_NAME.child(name0);
            Intrinsics.checkNotNullExpressionValue(fqName0, "ANNOTATION_PACKAGE_FQ_NA…e.identifier(simpleName))");
            return fqName0;
        }

        private final FqName collectionsFqName(String s) {
            Name name0 = Name.identifier(s);
            FqName fqName0 = StandardNames.COLLECTIONS_PACKAGE_FQ_NAME.child(name0);
            Intrinsics.checkNotNullExpressionValue(fqName0, "COLLECTIONS_PACKAGE_FQ_N…e.identifier(simpleName))");
            return fqName0;
        }

        private final FqName fqName(String s) {
            Name name0 = Name.identifier(s);
            FqName fqName0 = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(name0);
            Intrinsics.checkNotNullExpressionValue(fqName0, "BUILT_INS_PACKAGE_FQ_NAM…e.identifier(simpleName))");
            return fqName0;
        }

        private final FqNameUnsafe fqNameUnsafe(String s) {
            FqNameUnsafe fqNameUnsafe0 = this.fqName(s).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "fqName(simpleName).toUnsafe()");
            return fqNameUnsafe0;
        }

        private final FqName internalName(String s) {
            Name name0 = Name.identifier(s);
            FqName fqName0 = StandardNames.KOTLIN_INTERNAL_FQ_NAME.child(name0);
            Intrinsics.checkNotNullExpressionValue(fqName0, "KOTLIN_INTERNAL_FQ_NAME.…e.identifier(simpleName))");
            return fqName0;
        }

        private final FqNameUnsafe rangesFqName(String s) {
            Name name0 = Name.identifier(s);
            FqNameUnsafe fqNameUnsafe0 = StandardNames.RANGES_PACKAGE_FQ_NAME.child(name0).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "RANGES_PACKAGE_FQ_NAME.c…r(simpleName)).toUnsafe()");
            return fqNameUnsafe0;
        }

        @JvmStatic
        public static final FqNameUnsafe reflect(String s) {
            Intrinsics.checkNotNullParameter(s, "simpleName");
            Name name0 = Name.identifier(s);
            FqNameUnsafe fqNameUnsafe0 = StandardNames.KOTLIN_REFLECT_FQ_NAME.child(name0).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "KOTLIN_REFLECT_FQ_NAME.c…r(simpleName)).toUnsafe()");
            return fqNameUnsafe0;
        }
    }

    public static final FqName ANNOTATION_PACKAGE_FQ_NAME;
    public static final Name BACKING_FIELD;
    public static final FqName BUILT_INS_PACKAGE_FQ_NAME;
    public static final Set BUILT_INS_PACKAGE_FQ_NAMES;
    public static final Name BUILT_INS_PACKAGE_NAME;
    public static final Name CHAR_CODE;
    public static final FqName COLLECTIONS_PACKAGE_FQ_NAME;
    public static final Name CONTEXT_FUNCTION_TYPE_PARAMETER_COUNT_NAME;
    public static final FqName CONTINUATION_INTERFACE_FQ_NAME;
    public static final FqName COROUTINES_INTRINSICS_PACKAGE_FQ_NAME;
    public static final FqName COROUTINES_JVM_INTERNAL_PACKAGE_FQ_NAME;
    public static final FqName COROUTINES_PACKAGE_FQ_NAME;
    public static final String DATA_CLASS_COMPONENT_PREFIX;
    public static final Name DATA_CLASS_COPY;
    public static final Name DEFAULT_VALUE_PARAMETER;
    public static final FqName DYNAMIC_FQ_NAME;
    public static final Name ENUM_ENTRIES;
    public static final Name ENUM_VALUES;
    public static final Name ENUM_VALUE_OF;
    public static final Name HASHCODE_NAME;
    public static final StandardNames INSTANCE;
    public static final FqName KOTLIN_INTERNAL_FQ_NAME;
    public static final FqName KOTLIN_REFLECT_FQ_NAME;
    public static final Name NEXT_CHAR;
    private static final FqName NON_EXISTENT_CLASS;
    public static final List PREFIXES;
    public static final FqName RANGES_PACKAGE_FQ_NAME;
    public static final FqName RESULT_FQ_NAME;
    public static final FqName TEXT_PACKAGE_FQ_NAME;

    static {
        StandardNames.INSTANCE = new StandardNames();
        Name name0 = Name.identifier("field");
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(\"field\")");
        StandardNames.BACKING_FIELD = name0;
        Name name1 = Name.identifier("value");
        Intrinsics.checkNotNullExpressionValue(name1, "identifier(\"value\")");
        StandardNames.DEFAULT_VALUE_PARAMETER = name1;
        Name name2 = Name.identifier("values");
        Intrinsics.checkNotNullExpressionValue(name2, "identifier(\"values\")");
        StandardNames.ENUM_VALUES = name2;
        Name name3 = Name.identifier("entries");
        Intrinsics.checkNotNullExpressionValue(name3, "identifier(\"entries\")");
        StandardNames.ENUM_ENTRIES = name3;
        Name name4 = Name.identifier("valueOf");
        Intrinsics.checkNotNullExpressionValue(name4, "identifier(\"valueOf\")");
        StandardNames.ENUM_VALUE_OF = name4;
        Name name5 = Name.identifier("copy");
        Intrinsics.checkNotNullExpressionValue(name5, "identifier(\"copy\")");
        StandardNames.DATA_CLASS_COPY = name5;
        StandardNames.DATA_CLASS_COMPONENT_PREFIX = "component";
        Name name6 = Name.identifier("hashCode");
        Intrinsics.checkNotNullExpressionValue(name6, "identifier(\"hashCode\")");
        StandardNames.HASHCODE_NAME = name6;
        Name name7 = Name.identifier("code");
        Intrinsics.checkNotNullExpressionValue(name7, "identifier(\"code\")");
        StandardNames.CHAR_CODE = name7;
        Name name8 = Name.identifier("nextChar");
        Intrinsics.checkNotNullExpressionValue(name8, "identifier(\"nextChar\")");
        StandardNames.NEXT_CHAR = name8;
        Name name9 = Name.identifier("count");
        Intrinsics.checkNotNullExpressionValue(name9, "identifier(\"count\")");
        StandardNames.CONTEXT_FUNCTION_TYPE_PARAMETER_COUNT_NAME = name9;
        StandardNames.DYNAMIC_FQ_NAME = new FqName("<dynamic>");
        FqName fqName0 = new FqName("kotlin.coroutines");
        StandardNames.COROUTINES_PACKAGE_FQ_NAME = fqName0;
        StandardNames.COROUTINES_JVM_INTERNAL_PACKAGE_FQ_NAME = new FqName("kotlin.coroutines.jvm.internal");
        StandardNames.COROUTINES_INTRINSICS_PACKAGE_FQ_NAME = new FqName("kotlin.coroutines.intrinsics");
        FqName fqName1 = fqName0.child(Name.identifier("Continuation"));
        Intrinsics.checkNotNullExpressionValue(fqName1, "COROUTINES_PACKAGE_FQ_NA…entifier(\"Continuation\"))");
        StandardNames.CONTINUATION_INTERFACE_FQ_NAME = fqName1;
        StandardNames.RESULT_FQ_NAME = new FqName("kotlin.Result");
        FqName fqName2 = new FqName("kotlin.reflect");
        StandardNames.KOTLIN_REFLECT_FQ_NAME = fqName2;
        StandardNames.PREFIXES = kotlin.collections.CollectionsKt.listOf(new String[]{"KProperty", "KMutableProperty", "KFunction", "KSuspendFunction"});
        Name name10 = Name.identifier("kotlin");
        Intrinsics.checkNotNullExpressionValue(name10, "identifier(\"kotlin\")");
        StandardNames.BUILT_INS_PACKAGE_NAME = name10;
        FqName fqName3 = FqName.topLevel(name10);
        Intrinsics.checkNotNullExpressionValue(fqName3, "topLevel(BUILT_INS_PACKAGE_NAME)");
        StandardNames.BUILT_INS_PACKAGE_FQ_NAME = fqName3;
        FqName fqName4 = fqName3.child(Name.identifier("annotation"));
        Intrinsics.checkNotNullExpressionValue(fqName4, "BUILT_INS_PACKAGE_FQ_NAM…identifier(\"annotation\"))");
        StandardNames.ANNOTATION_PACKAGE_FQ_NAME = fqName4;
        FqName fqName5 = fqName3.child(Name.identifier("collections"));
        Intrinsics.checkNotNullExpressionValue(fqName5, "BUILT_INS_PACKAGE_FQ_NAM…dentifier(\"collections\"))");
        StandardNames.COLLECTIONS_PACKAGE_FQ_NAME = fqName5;
        FqName fqName6 = fqName3.child(Name.identifier("ranges"));
        Intrinsics.checkNotNullExpressionValue(fqName6, "BUILT_INS_PACKAGE_FQ_NAM…ame.identifier(\"ranges\"))");
        StandardNames.RANGES_PACKAGE_FQ_NAME = fqName6;
        FqName fqName7 = fqName3.child(Name.identifier("text"));
        Intrinsics.checkNotNullExpressionValue(fqName7, "BUILT_INS_PACKAGE_FQ_NAM…(Name.identifier(\"text\"))");
        StandardNames.TEXT_PACKAGE_FQ_NAME = fqName7;
        FqName fqName8 = fqName3.child(Name.identifier("internal"));
        Intrinsics.checkNotNullExpressionValue(fqName8, "BUILT_INS_PACKAGE_FQ_NAM…e.identifier(\"internal\"))");
        StandardNames.KOTLIN_INTERNAL_FQ_NAME = fqName8;
        StandardNames.NON_EXISTENT_CLASS = new FqName("error.NonExistentClass");
        StandardNames.BUILT_INS_PACKAGE_FQ_NAMES = SetsKt.setOf(new FqName[]{fqName3, fqName5, fqName6, fqName4, fqName2, fqName8, fqName0});
    }

    @JvmStatic
    public static final ClassId getFunctionClassId(int v) {
        Name name0 = Name.identifier(("Function" + v));
        return new ClassId(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, name0);
    }

    @JvmStatic
    public static final String getFunctionName(int v) [...] // Inlined contents

    @JvmStatic
    public static final FqName getPrimitiveFqName(PrimitiveType primitiveType0) {
        Intrinsics.checkNotNullParameter(primitiveType0, "primitiveType");
        FqName fqName0 = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(primitiveType0.getTypeName());
        Intrinsics.checkNotNullExpressionValue(fqName0, "BUILT_INS_PACKAGE_FQ_NAM…d(primitiveType.typeName)");
        return fqName0;
    }

    // 去混淆评级： 低(20)
    @JvmStatic
    public static final String getSuspendFunctionName(int v) {
        return "SuspendFunction" + v;
    }

    @JvmStatic
    public static final boolean isPrimitiveArray(FqNameUnsafe fqNameUnsafe0) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe0, "arrayFqName");
        return FqNames.arrayClassFqNameToPrimitiveType.get(fqNameUnsafe0) != null;
    }
}

