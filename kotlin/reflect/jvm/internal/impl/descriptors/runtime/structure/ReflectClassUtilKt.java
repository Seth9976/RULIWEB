package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

public final class ReflectClassUtilKt {
    private static final Map FUNCTION_CLASSES;
    private static final List PRIMITIVE_CLASSES;
    private static final Map PRIMITIVE_TO_WRAPPER;
    private static final Map WRAPPER_TO_PRIMITIVE;

    static {
        KClass[] arr_kClass = new KClass[8];
        int v = 0;
        arr_kClass[0] = Reflection.getOrCreateKotlinClass(Boolean.TYPE);
        arr_kClass[1] = Reflection.getOrCreateKotlinClass(Byte.TYPE);
        arr_kClass[2] = Reflection.getOrCreateKotlinClass(Character.TYPE);
        arr_kClass[3] = Reflection.getOrCreateKotlinClass(Double.TYPE);
        arr_kClass[4] = Reflection.getOrCreateKotlinClass(Float.TYPE);
        arr_kClass[5] = Reflection.getOrCreateKotlinClass(Integer.TYPE);
        arr_kClass[6] = Reflection.getOrCreateKotlinClass(Long.TYPE);
        arr_kClass[7] = Reflection.getOrCreateKotlinClass(Short.TYPE);
        List list0 = CollectionsKt.listOf(arr_kClass);
        ReflectClassUtilKt.PRIMITIVE_CLASSES = list0;
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(TuplesKt.to(JvmClassMappingKt.getJavaObjectType(((KClass)object0)), JvmClassMappingKt.getJavaPrimitiveType(((KClass)object0))));
        }
        ReflectClassUtilKt.WRAPPER_TO_PRIMITIVE = MapsKt.toMap(arrayList0);
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(ReflectClassUtilKt.PRIMITIVE_CLASSES, 10));
        for(Object object1: ReflectClassUtilKt.PRIMITIVE_CLASSES) {
            arrayList1.add(TuplesKt.to(JvmClassMappingKt.getJavaPrimitiveType(((KClass)object1)), JvmClassMappingKt.getJavaObjectType(((KClass)object1))));
        }
        ReflectClassUtilKt.PRIMITIVE_TO_WRAPPER = MapsKt.toMap(arrayList1);
        Iterable iterable0 = CollectionsKt.listOf(new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object2: iterable0) {
            if(v < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList2.add(TuplesKt.to(((Class)object2), v));
            ++v;
        }
        ReflectClassUtilKt.FUNCTION_CLASSES = MapsKt.toMap(arrayList2);
    }

    public static final ClassId getClassId(Class class0) {
        ClassId classId1;
        Intrinsics.checkNotNullParameter(class0, "<this>");
        if(class0.isPrimitive()) {
            throw new IllegalArgumentException("Can\'t compute ClassId for primitive type: " + class0);
        }
        if(class0.isArray()) {
            throw new IllegalArgumentException("Can\'t compute ClassId for array type: " + class0);
        }
        if(class0.getEnclosingMethod() == null && class0.getEnclosingConstructor() == null) {
            String s = class0.getSimpleName();
            Intrinsics.checkNotNullExpressionValue(s, "simpleName");
            if(s.length() != 0) {
                Class class1 = class0.getDeclaringClass();
                if(class1 == null) {
                    classId1 = ClassId.topLevel(new FqName(class0.getName()));
                }
                else {
                    ClassId classId0 = ReflectClassUtilKt.getClassId(class1);
                    if(classId0 == null) {
                        classId1 = ClassId.topLevel(new FqName(class0.getName()));
                    }
                    else {
                        classId1 = classId0.createNestedClassId(Name.identifier(class0.getSimpleName()));
                        if(classId1 == null) {
                            classId1 = ClassId.topLevel(new FqName(class0.getName()));
                        }
                    }
                }
                Intrinsics.checkNotNullExpressionValue(classId1, "declaringClass?.classId?…Id.topLevel(FqName(name))");
                return classId1;
            }
        }
        FqName fqName0 = new FqName(class0.getName());
        return new ClassId(fqName0.parent(), FqName.topLevel(fqName0.shortName()), true);
    }

    public static final String getDesc(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        if(class0.isPrimitive()) {
            String s = class0.getName();
            if(s != null) {
                switch(s) {
                    case "boolean": {
                        return "Z";
                    }
                    case "byte": {
                        return "B";
                    }
                    case "char": {
                        return "C";
                    }
                    case "double": {
                        return "D";
                    }
                    case "float": {
                        return "F";
                    }
                    case "int": {
                        return "I";
                    }
                    case "long": {
                        return "J";
                    }
                    case "short": {
                        return "S";
                    }
                    case "void": {
                        return "V";
                    }
                }
            }
            throw new IllegalArgumentException("Unsupported primitive type: " + class0);
        }
        if(class0.isArray()) {
            String s1 = class0.getName();
            Intrinsics.checkNotNullExpressionValue(s1, "name");
            return StringsKt.replace$default(s1, '.', '/', false, 4, null);
        }
        String s2 = class0.getName();
        Intrinsics.checkNotNullExpressionValue(s2, "name");
        return "L" + StringsKt.replace$default(s2, '.', '/', false, 4, null) + ';';
    }

    public static final Integer getFunctionClassArity(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        return (Integer)ReflectClassUtilKt.FUNCTION_CLASSES.get(class0);
    }

    public static final List getParameterizedTypeArguments(Type type0) {
        Intrinsics.checkNotNullParameter(type0, "<this>");
        if(!(type0 instanceof ParameterizedType)) {
            return CollectionsKt.emptyList();
        }
        if(((ParameterizedType)type0).getOwnerType() == null) {
            Type[] arr_type = ((ParameterizedType)type0).getActualTypeArguments();
            Intrinsics.checkNotNullExpressionValue(arr_type, "actualTypeArguments");
            return ArraysKt.toList(arr_type);
        }
        return SequencesKt.toList(SequencesKt.flatMap(SequencesKt.generateSequence(type0, kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.1.INSTANCE), kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.2.INSTANCE));

        final class kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.1();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ParameterizedType)object0));
            }

            public final ParameterizedType invoke(ParameterizedType parameterizedType0) {
                Intrinsics.checkNotNullParameter(parameterizedType0, "it");
                Type type0 = parameterizedType0.getOwnerType();
                return type0 instanceof ParameterizedType ? ((ParameterizedType)type0) : null;
            }
        }


        final class kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.2();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt.parameterizedTypeArguments.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ParameterizedType)object0));
            }

            public final Sequence invoke(ParameterizedType parameterizedType0) {
                Intrinsics.checkNotNullParameter(parameterizedType0, "it");
                Type[] arr_type = parameterizedType0.getActualTypeArguments();
                Intrinsics.checkNotNullExpressionValue(arr_type, "it.actualTypeArguments");
                return ArraysKt.asSequence(arr_type);
            }
        }

    }

    public static final Class getPrimitiveByWrapper(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        return (Class)ReflectClassUtilKt.WRAPPER_TO_PRIMITIVE.get(class0);
    }

    public static final ClassLoader getSafeClassLoader(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        ClassLoader classLoader0 = class0.getClassLoader();
        if(classLoader0 == null) {
            classLoader0 = ClassLoader.getSystemClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader0, "getSystemClassLoader()");
        }
        return classLoader0;
    }

    public static final Class getWrapperByPrimitive(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        return (Class)ReflectClassUtilKt.PRIMITIVE_TO_WRAPPER.get(class0);
    }

    public static final boolean isEnumClassOrSpecializedEnumEntryClass(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        return Enum.class.isAssignableFrom(class0);
    }
}

