package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.name.StandardClassIds;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.text.StringsKt;

public final class JavaToKotlinClassMap {
    public static final class PlatformMutabilityMapping {
        private final ClassId javaClass;
        private final ClassId kotlinMutable;
        private final ClassId kotlinReadOnly;

        public PlatformMutabilityMapping(ClassId classId0, ClassId classId1, ClassId classId2) {
            Intrinsics.checkNotNullParameter(classId0, "javaClass");
            Intrinsics.checkNotNullParameter(classId1, "kotlinReadOnly");
            Intrinsics.checkNotNullParameter(classId2, "kotlinMutable");
            super();
            this.javaClass = classId0;
            this.kotlinReadOnly = classId1;
            this.kotlinMutable = classId2;
        }

        public final ClassId component1() {
            return this.javaClass;
        }

        public final ClassId component2() {
            return this.kotlinReadOnly;
        }

        public final ClassId component3() {
            return this.kotlinMutable;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof PlatformMutabilityMapping)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.javaClass, ((PlatformMutabilityMapping)object0).javaClass)) {
                return false;
            }
            return Intrinsics.areEqual(this.kotlinReadOnly, ((PlatformMutabilityMapping)object0).kotlinReadOnly) ? Intrinsics.areEqual(this.kotlinMutable, ((PlatformMutabilityMapping)object0).kotlinMutable) : false;
        }

        public final ClassId getJavaClass() {
            return this.javaClass;
        }

        @Override
        public int hashCode() {
            return (this.javaClass.hashCode() * 0x1F + this.kotlinReadOnly.hashCode()) * 0x1F + this.kotlinMutable.hashCode();
        }

        @Override
        public String toString() {
            return "PlatformMutabilityMapping(javaClass=" + this.javaClass + ", kotlinReadOnly=" + this.kotlinReadOnly + ", kotlinMutable=" + this.kotlinMutable + ')';
        }
    }

    private static final ClassId CLASS_CLASS_ID;
    private static final ClassId FUNCTION_N_CLASS_ID;
    private static final FqName FUNCTION_N_FQ_NAME;
    public static final JavaToKotlinClassMap INSTANCE;
    private static final ClassId K_CLASS_CLASS_ID;
    private static final ClassId K_FUNCTION_CLASS_ID;
    private static final String NUMBERED_FUNCTION_PREFIX;
    private static final String NUMBERED_K_FUNCTION_PREFIX;
    private static final String NUMBERED_K_SUSPEND_FUNCTION_PREFIX;
    private static final String NUMBERED_SUSPEND_FUNCTION_PREFIX;
    private static final HashMap javaToKotlin;
    private static final HashMap kotlinToJava;
    private static final List mutabilityMappings;
    private static final HashMap mutableToReadOnly;
    private static final HashMap mutableToReadOnlyClassId;
    private static final HashMap readOnlyToMutable;
    private static final HashMap readOnlyToMutableClassId;

    static {
        JavaToKotlinClassMap javaToKotlinClassMap0 = new JavaToKotlinClassMap();
        JavaToKotlinClassMap.INSTANCE = javaToKotlinClassMap0;
        JavaToKotlinClassMap.NUMBERED_FUNCTION_PREFIX = "kotlin" + '.' + "Function";
        JavaToKotlinClassMap.NUMBERED_K_FUNCTION_PREFIX = "kotlin.reflect" + '.' + "KFunction";
        JavaToKotlinClassMap.NUMBERED_SUSPEND_FUNCTION_PREFIX = "kotlin.coroutines" + '.' + "SuspendFunction";
        JavaToKotlinClassMap.NUMBERED_K_SUSPEND_FUNCTION_PREFIX = "kotlin.reflect" + '.' + "KSuspendFunction";
        ClassId classId0 = ClassId.topLevel(new FqName("kotlin.jvm.functions.FunctionN"));
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(FqName(\"kotlin.jvm.functions.FunctionN\"))");
        JavaToKotlinClassMap.FUNCTION_N_CLASS_ID = classId0;
        FqName fqName0 = classId0.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqName0, "FUNCTION_N_CLASS_ID.asSingleFqName()");
        JavaToKotlinClassMap.FUNCTION_N_FQ_NAME = fqName0;
        JavaToKotlinClassMap.K_FUNCTION_CLASS_ID = StandardClassIds.INSTANCE.getKFunction();
        JavaToKotlinClassMap.K_CLASS_CLASS_ID = StandardClassIds.INSTANCE.getKClass();
        JavaToKotlinClassMap.CLASS_CLASS_ID = javaToKotlinClassMap0.classId(Class.class);
        JavaToKotlinClassMap.javaToKotlin = new HashMap();
        JavaToKotlinClassMap.kotlinToJava = new HashMap();
        JavaToKotlinClassMap.mutableToReadOnly = new HashMap();
        JavaToKotlinClassMap.readOnlyToMutable = new HashMap();
        JavaToKotlinClassMap.mutableToReadOnlyClassId = new HashMap();
        JavaToKotlinClassMap.readOnlyToMutableClassId = new HashMap();
        PlatformMutabilityMapping[] arr_javaToKotlinClassMap$PlatformMutabilityMapping = new PlatformMutabilityMapping[8];
        ClassId classId1 = ClassId.topLevel(FqNames.iterable);
        Intrinsics.checkNotNullExpressionValue(classId1, "topLevel(FqNames.iterable)");
        FqName fqName1 = classId1.getPackageFqName();
        FqName fqName2 = classId1.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName2, "kotlinReadOnly.packageFqName");
        ClassId classId2 = new ClassId(fqName1, FqNamesUtilKt.tail(FqNames.mutableIterable, fqName2), false);
        arr_javaToKotlinClassMap$PlatformMutabilityMapping[0] = new PlatformMutabilityMapping(javaToKotlinClassMap0.classId(Iterable.class), classId1, classId2);
        ClassId classId3 = ClassId.topLevel(FqNames.iterator);
        Intrinsics.checkNotNullExpressionValue(classId3, "topLevel(FqNames.iterator)");
        FqName fqName3 = classId3.getPackageFqName();
        FqName fqName4 = classId3.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName4, "kotlinReadOnly.packageFqName");
        ClassId classId4 = new ClassId(fqName3, FqNamesUtilKt.tail(FqNames.mutableIterator, fqName4), false);
        arr_javaToKotlinClassMap$PlatformMutabilityMapping[1] = new PlatformMutabilityMapping(javaToKotlinClassMap0.classId(Iterator.class), classId3, classId4);
        ClassId classId5 = ClassId.topLevel(FqNames.collection);
        Intrinsics.checkNotNullExpressionValue(classId5, "topLevel(FqNames.collection)");
        FqName fqName5 = classId5.getPackageFqName();
        FqName fqName6 = classId5.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName6, "kotlinReadOnly.packageFqName");
        ClassId classId6 = new ClassId(fqName5, FqNamesUtilKt.tail(FqNames.mutableCollection, fqName6), false);
        arr_javaToKotlinClassMap$PlatformMutabilityMapping[2] = new PlatformMutabilityMapping(javaToKotlinClassMap0.classId(Collection.class), classId5, classId6);
        ClassId classId7 = ClassId.topLevel(FqNames.list);
        Intrinsics.checkNotNullExpressionValue(classId7, "topLevel(FqNames.list)");
        FqName fqName7 = classId7.getPackageFqName();
        FqName fqName8 = classId7.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName8, "kotlinReadOnly.packageFqName");
        ClassId classId8 = new ClassId(fqName7, FqNamesUtilKt.tail(FqNames.mutableList, fqName8), false);
        arr_javaToKotlinClassMap$PlatformMutabilityMapping[3] = new PlatformMutabilityMapping(javaToKotlinClassMap0.classId(List.class), classId7, classId8);
        ClassId classId9 = ClassId.topLevel(FqNames.set);
        Intrinsics.checkNotNullExpressionValue(classId9, "topLevel(FqNames.set)");
        FqName fqName9 = classId9.getPackageFqName();
        FqName fqName10 = classId9.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName10, "kotlinReadOnly.packageFqName");
        ClassId classId10 = new ClassId(fqName9, FqNamesUtilKt.tail(FqNames.mutableSet, fqName10), false);
        arr_javaToKotlinClassMap$PlatformMutabilityMapping[4] = new PlatformMutabilityMapping(javaToKotlinClassMap0.classId(Set.class), classId9, classId10);
        ClassId classId11 = ClassId.topLevel(FqNames.listIterator);
        Intrinsics.checkNotNullExpressionValue(classId11, "topLevel(FqNames.listIterator)");
        FqName fqName11 = classId11.getPackageFqName();
        FqName fqName12 = classId11.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName12, "kotlinReadOnly.packageFqName");
        ClassId classId12 = new ClassId(fqName11, FqNamesUtilKt.tail(FqNames.mutableListIterator, fqName12), false);
        arr_javaToKotlinClassMap$PlatformMutabilityMapping[5] = new PlatformMutabilityMapping(javaToKotlinClassMap0.classId(ListIterator.class), classId11, classId12);
        ClassId classId13 = ClassId.topLevel(FqNames.map);
        Intrinsics.checkNotNullExpressionValue(classId13, "topLevel(FqNames.map)");
        FqName fqName13 = classId13.getPackageFqName();
        FqName fqName14 = classId13.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName14, "kotlinReadOnly.packageFqName");
        ClassId classId14 = new ClassId(fqName13, FqNamesUtilKt.tail(FqNames.mutableMap, fqName14), false);
        arr_javaToKotlinClassMap$PlatformMutabilityMapping[6] = new PlatformMutabilityMapping(javaToKotlinClassMap0.classId(Map.class), classId13, classId14);
        ClassId classId15 = ClassId.topLevel(FqNames.map).createNestedClassId(FqNames.mapEntry.shortName());
        Intrinsics.checkNotNullExpressionValue(classId15, "topLevel(FqNames.map).cr…mes.mapEntry.shortName())");
        FqName fqName15 = classId15.getPackageFqName();
        FqName fqName16 = classId15.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName16, "kotlinReadOnly.packageFqName");
        ClassId classId16 = new ClassId(fqName15, FqNamesUtilKt.tail(FqNames.mutableMapEntry, fqName16), false);
        arr_javaToKotlinClassMap$PlatformMutabilityMapping[7] = new PlatformMutabilityMapping(javaToKotlinClassMap0.classId(Map.Entry.class), classId15, classId16);
        List list0 = CollectionsKt.listOf(arr_javaToKotlinClassMap$PlatformMutabilityMapping);
        JavaToKotlinClassMap.mutabilityMappings = list0;
        javaToKotlinClassMap0.addTopLevel(Object.class, FqNames.any);
        javaToKotlinClassMap0.addTopLevel(String.class, FqNames.string);
        javaToKotlinClassMap0.addTopLevel(CharSequence.class, FqNames.charSequence);
        javaToKotlinClassMap0.addTopLevel(Throwable.class, FqNames.throwable);
        javaToKotlinClassMap0.addTopLevel(Cloneable.class, FqNames.cloneable);
        javaToKotlinClassMap0.addTopLevel(Number.class, FqNames.number);
        javaToKotlinClassMap0.addTopLevel(Comparable.class, FqNames.comparable);
        javaToKotlinClassMap0.addTopLevel(Enum.class, FqNames._enum);
        javaToKotlinClassMap0.addTopLevel(Annotation.class, FqNames.annotation);
        for(Object object0: list0) {
            JavaToKotlinClassMap.INSTANCE.addMapping(((PlatformMutabilityMapping)object0));
        }
        JvmPrimitiveType[] arr_jvmPrimitiveType = JvmPrimitiveType.values();
        for(int v1 = 0; v1 < arr_jvmPrimitiveType.length; ++v1) {
            JvmPrimitiveType jvmPrimitiveType0 = arr_jvmPrimitiveType[v1];
            ClassId classId17 = ClassId.topLevel(jvmPrimitiveType0.getWrapperFqName());
            Intrinsics.checkNotNullExpressionValue(classId17, "topLevel(jvmType.wrapperFqName)");
            PrimitiveType primitiveType0 = jvmPrimitiveType0.getPrimitiveType();
            Intrinsics.checkNotNullExpressionValue(primitiveType0, "jvmType.primitiveType");
            ClassId classId18 = ClassId.topLevel(StandardNames.getPrimitiveFqName(primitiveType0));
            Intrinsics.checkNotNullExpressionValue(classId18, "topLevel(StandardNames.g…e(jvmType.primitiveType))");
            JavaToKotlinClassMap.INSTANCE.add(classId17, classId18);
        }
        for(Object object1: CompanionObjectMapping.INSTANCE.allClassesWithIntrinsicCompanions()) {
            ClassId classId19 = ClassId.topLevel(new FqName("kotlin.jvm.internal." + ((ClassId)object1).getShortClassName().asString() + "CompanionObject"));
            Intrinsics.checkNotNullExpressionValue(classId19, "topLevel(FqName(\"kotlin.…g() + \"CompanionObject\"))");
            ClassId classId20 = ((ClassId)object1).createNestedClassId(SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT);
            Intrinsics.checkNotNullExpressionValue(classId20, "classId.createNestedClas…AME_FOR_COMPANION_OBJECT)");
            JavaToKotlinClassMap.INSTANCE.add(classId19, classId20);
        }
        for(int v2 = 0; v2 < 23; ++v2) {
            ClassId classId21 = ClassId.topLevel(new FqName("kotlin.jvm.functions.Function" + v2));
            Intrinsics.checkNotNullExpressionValue(classId21, "topLevel(FqName(\"kotlin.…m.functions.Function$i\"))");
            ClassId classId22 = StandardNames.getFunctionClassId(v2);
            JavaToKotlinClassMap.INSTANCE.add(classId21, classId22);
            FqName fqName17 = new FqName("kotlin.reflect.KFunction" + v2);
            JavaToKotlinClassMap.INSTANCE.addKotlinToJava(fqName17, JavaToKotlinClassMap.K_FUNCTION_CLASS_ID);
        }
        for(int v = 0; v < 22; ++v) {
            FqName fqName18 = new FqName("kotlin.reflect" + '.' + "KSuspendFunction" + v);
            JavaToKotlinClassMap.INSTANCE.addKotlinToJava(fqName18, JavaToKotlinClassMap.K_FUNCTION_CLASS_ID);
        }
        FqName fqName19 = FqNames.nothing.toSafe();
        Intrinsics.checkNotNullExpressionValue(fqName19, "nothing.toSafe()");
        ClassId classId23 = JavaToKotlinClassMap.INSTANCE.classId(Void.class);
        JavaToKotlinClassMap.INSTANCE.addKotlinToJava(fqName19, classId23);
    }

    private final void add(ClassId classId0, ClassId classId1) {
        this.addJavaToKotlin(classId0, classId1);
        FqName fqName0 = classId1.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqName0, "kotlinClassId.asSingleFqName()");
        this.addKotlinToJava(fqName0, classId0);
    }

    private final void addJavaToKotlin(ClassId classId0, ClassId classId1) {
        FqNameUnsafe fqNameUnsafe0 = classId0.asSingleFqName().toUnsafe();
        Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "javaClassId.asSingleFqName().toUnsafe()");
        JavaToKotlinClassMap.javaToKotlin.put(fqNameUnsafe0, classId1);
    }

    private final void addKotlinToJava(FqName fqName0, ClassId classId0) {
        FqNameUnsafe fqNameUnsafe0 = fqName0.toUnsafe();
        Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "kotlinFqNameUnsafe.toUnsafe()");
        JavaToKotlinClassMap.kotlinToJava.put(fqNameUnsafe0, classId0);
    }

    private final void addMapping(PlatformMutabilityMapping javaToKotlinClassMap$PlatformMutabilityMapping0) {
        ClassId classId0 = javaToKotlinClassMap$PlatformMutabilityMapping0.component1();
        ClassId classId1 = javaToKotlinClassMap$PlatformMutabilityMapping0.component2();
        ClassId classId2 = javaToKotlinClassMap$PlatformMutabilityMapping0.component3();
        this.add(classId0, classId1);
        FqName fqName0 = classId2.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqName0, "mutableClassId.asSingleFqName()");
        this.addKotlinToJava(fqName0, classId0);
        JavaToKotlinClassMap.mutableToReadOnlyClassId.put(classId2, classId1);
        JavaToKotlinClassMap.readOnlyToMutableClassId.put(classId1, classId2);
        FqName fqName1 = classId1.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqName1, "readOnlyClassId.asSingleFqName()");
        FqName fqName2 = classId2.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqName2, "mutableClassId.asSingleFqName()");
        FqNameUnsafe fqNameUnsafe0 = classId2.asSingleFqName().toUnsafe();
        Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "mutableClassId.asSingleFqName().toUnsafe()");
        JavaToKotlinClassMap.mutableToReadOnly.put(fqNameUnsafe0, fqName1);
        FqNameUnsafe fqNameUnsafe1 = fqName1.toUnsafe();
        Intrinsics.checkNotNullExpressionValue(fqNameUnsafe1, "readOnlyFqName.toUnsafe()");
        JavaToKotlinClassMap.readOnlyToMutable.put(fqNameUnsafe1, fqName2);
    }

    private final void addTopLevel(Class class0, FqName fqName0) {
        ClassId classId0 = this.classId(class0);
        ClassId classId1 = ClassId.topLevel(fqName0);
        Intrinsics.checkNotNullExpressionValue(classId1, "topLevel(kotlinFqName)");
        this.add(classId0, classId1);
    }

    private final void addTopLevel(Class class0, FqNameUnsafe fqNameUnsafe0) {
        FqName fqName0 = fqNameUnsafe0.toSafe();
        Intrinsics.checkNotNullExpressionValue(fqName0, "kotlinFqName.toSafe()");
        this.addTopLevel(class0, fqName0);
    }

    private final ClassId classId(Class class0) {
        class0.isPrimitive();
        Class class1 = class0.getDeclaringClass();
        if(class1 == null) {
            ClassId classId0 = ClassId.topLevel(new FqName(class0.getCanonicalName()));
            Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(FqName(clazz.canonicalName))");
            return classId0;
        }
        ClassId classId1 = this.classId(class1).createNestedClassId(Name.identifier(class0.getSimpleName()));
        Intrinsics.checkNotNullExpressionValue(classId1, "classId(outer).createNes…tifier(clazz.simpleName))");
        return classId1;
    }

    public final FqName getFUNCTION_N_FQ_NAME() {
        return JavaToKotlinClassMap.FUNCTION_N_FQ_NAME;
    }

    public final List getMutabilityMappings() {
        return JavaToKotlinClassMap.mutabilityMappings;
    }

    private final boolean isKotlinFunctionWithBigArity(FqNameUnsafe fqNameUnsafe0, String s) {
        String s1 = fqNameUnsafe0.asString();
        Intrinsics.checkNotNullExpressionValue(s1, "kotlinFqName.asString()");
        String s2 = StringsKt.substringAfter(s1, s, "");
        if(s2.length() > 0 && !StringsKt.startsWith$default(s2, '0', false, 2, null)) {
            Integer integer0 = StringsKt.toIntOrNull(s2);
            return integer0 != null && ((int)integer0) >= 23;
        }
        return false;
    }

    public final boolean isMutable(FqNameUnsafe fqNameUnsafe0) {
        return JavaToKotlinClassMap.mutableToReadOnly.containsKey(fqNameUnsafe0);
    }

    public final boolean isReadOnly(FqNameUnsafe fqNameUnsafe0) {
        return JavaToKotlinClassMap.readOnlyToMutable.containsKey(fqNameUnsafe0);
    }

    public final ClassId mapJavaToKotlin(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        FqNameUnsafe fqNameUnsafe0 = fqName0.toUnsafe();
        return (ClassId)JavaToKotlinClassMap.javaToKotlin.get(fqNameUnsafe0);
    }

    public final ClassId mapKotlinToJava(FqNameUnsafe fqNameUnsafe0) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe0, "kotlinFqName");
        if(this.isKotlinFunctionWithBigArity(fqNameUnsafe0, "kotlin.Function")) {
            return JavaToKotlinClassMap.FUNCTION_N_CLASS_ID;
        }
        if(this.isKotlinFunctionWithBigArity(fqNameUnsafe0, "kotlin.coroutines.SuspendFunction")) {
            return JavaToKotlinClassMap.FUNCTION_N_CLASS_ID;
        }
        if(this.isKotlinFunctionWithBigArity(fqNameUnsafe0, "kotlin.reflect.KFunction")) {
            return JavaToKotlinClassMap.K_FUNCTION_CLASS_ID;
        }
        return this.isKotlinFunctionWithBigArity(fqNameUnsafe0, "kotlin.reflect.KSuspendFunction") ? JavaToKotlinClassMap.K_FUNCTION_CLASS_ID : ((ClassId)JavaToKotlinClassMap.kotlinToJava.get(fqNameUnsafe0));
    }

    public final FqName mutableToReadOnly(FqNameUnsafe fqNameUnsafe0) {
        return (FqName)JavaToKotlinClassMap.mutableToReadOnly.get(fqNameUnsafe0);
    }

    public final FqName readOnlyToMutable(FqNameUnsafe fqNameUnsafe0) {
        return (FqName)JavaToKotlinClassMap.readOnlyToMutable.get(fqNameUnsafe0);
    }
}

