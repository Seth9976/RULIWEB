package kotlin.jvm.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.ReflectionFactoryImpl;

public class Reflection {
    private static final KClass[] EMPTY_K_CLASS_ARRAY = null;
    static final String REFLECTION_NOT_AVAILABLE = " (Kotlin reflection is not available)";
    private static final ReflectionFactory factory;

    static {
        ReflectionFactory reflectionFactory0 = null;
        try {
            reflectionFactory0 = (ReflectionFactory)ReflectionFactoryImpl.class.newInstance();
        }
        catch(ClassCastException | ClassNotFoundException | InstantiationException | IllegalAccessException unused_ex) {
        }
        if(reflectionFactory0 == null) {
            reflectionFactory0 = new ReflectionFactory();
        }
        Reflection.factory = reflectionFactory0;
        Reflection.EMPTY_K_CLASS_ARRAY = new KClass[0];
    }

    public static KClass createKotlinClass(Class class0) {
        return Reflection.factory.createKotlinClass(class0);
    }

    public static KClass createKotlinClass(Class class0, String s) {
        return Reflection.factory.createKotlinClass(class0, s);
    }

    public static KFunction function(FunctionReference functionReference0) {
        return Reflection.factory.function(functionReference0);
    }

    public static KClass getOrCreateKotlinClass(Class class0) {
        return Reflection.factory.getOrCreateKotlinClass(class0);
    }

    public static KClass getOrCreateKotlinClass(Class class0, String s) {
        return Reflection.factory.getOrCreateKotlinClass(class0, s);
    }

    public static KClass[] getOrCreateKotlinClasses(Class[] arr_class) {
        if(arr_class.length == 0) {
            return Reflection.EMPTY_K_CLASS_ARRAY;
        }
        KClass[] arr_kClass = new KClass[arr_class.length];
        for(int v = 0; v < arr_class.length; ++v) {
            arr_kClass[v] = Reflection.getOrCreateKotlinClass(arr_class[v]);
        }
        return arr_kClass;
    }

    public static KDeclarationContainer getOrCreateKotlinPackage(Class class0) {
        return Reflection.factory.getOrCreateKotlinPackage(class0, "");
    }

    public static KDeclarationContainer getOrCreateKotlinPackage(Class class0, String s) {
        return Reflection.factory.getOrCreateKotlinPackage(class0, s);
    }

    public static KType mutableCollectionType(KType kType0) {
        return Reflection.factory.mutableCollectionType(kType0);
    }

    public static KMutableProperty0 mutableProperty0(MutablePropertyReference0 mutablePropertyReference00) {
        return Reflection.factory.mutableProperty0(mutablePropertyReference00);
    }

    public static KMutableProperty1 mutableProperty1(MutablePropertyReference1 mutablePropertyReference10) {
        return Reflection.factory.mutableProperty1(mutablePropertyReference10);
    }

    public static KMutableProperty2 mutableProperty2(MutablePropertyReference2 mutablePropertyReference20) {
        return Reflection.factory.mutableProperty2(mutablePropertyReference20);
    }

    public static KType nothingType(KType kType0) {
        return Reflection.factory.nothingType(kType0);
    }

    public static KType nullableTypeOf(Class class0) {
        KClass kClass0 = Reflection.getOrCreateKotlinClass(class0);
        return Reflection.factory.typeOf(kClass0, Collections.EMPTY_LIST, true);
    }

    public static KType nullableTypeOf(Class class0, KTypeProjection kTypeProjection0) {
        KClass kClass0 = Reflection.getOrCreateKotlinClass(class0);
        List list0 = Collections.singletonList(kTypeProjection0);
        return Reflection.factory.typeOf(kClass0, list0, true);
    }

    public static KType nullableTypeOf(Class class0, KTypeProjection kTypeProjection0, KTypeProjection kTypeProjection1) {
        KClass kClass0 = Reflection.getOrCreateKotlinClass(class0);
        List list0 = Arrays.asList(new KTypeProjection[]{kTypeProjection0, kTypeProjection1});
        return Reflection.factory.typeOf(kClass0, list0, true);
    }

    public static KType nullableTypeOf(Class class0, KTypeProjection[] arr_kTypeProjection) {
        KClass kClass0 = Reflection.getOrCreateKotlinClass(class0);
        List list0 = ArraysKt.toList(arr_kTypeProjection);
        return Reflection.factory.typeOf(kClass0, list0, true);
    }

    public static KType nullableTypeOf(KClassifier kClassifier0) {
        return Reflection.factory.typeOf(kClassifier0, Collections.EMPTY_LIST, true);
    }

    public static KType platformType(KType kType0, KType kType1) {
        return Reflection.factory.platformType(kType0, kType1);
    }

    public static KProperty0 property0(PropertyReference0 propertyReference00) {
        return Reflection.factory.property0(propertyReference00);
    }

    public static KProperty1 property1(PropertyReference1 propertyReference10) {
        return Reflection.factory.property1(propertyReference10);
    }

    public static KProperty2 property2(PropertyReference2 propertyReference20) {
        return Reflection.factory.property2(propertyReference20);
    }

    public static String renderLambdaToString(FunctionBase functionBase0) {
        return Reflection.factory.renderLambdaToString(functionBase0);
    }

    public static String renderLambdaToString(Lambda lambda0) {
        return Reflection.factory.renderLambdaToString(lambda0);
    }

    public static void setUpperBounds(KTypeParameter kTypeParameter0, KType kType0) {
        List list0 = Collections.singletonList(kType0);
        Reflection.factory.setUpperBounds(kTypeParameter0, list0);
    }

    public static void setUpperBounds(KTypeParameter kTypeParameter0, KType[] arr_kType) {
        List list0 = ArraysKt.toList(arr_kType);
        Reflection.factory.setUpperBounds(kTypeParameter0, list0);
    }

    public static KType typeOf(Class class0) {
        KClass kClass0 = Reflection.getOrCreateKotlinClass(class0);
        return Reflection.factory.typeOf(kClass0, Collections.EMPTY_LIST, false);
    }

    public static KType typeOf(Class class0, KTypeProjection kTypeProjection0) {
        KClass kClass0 = Reflection.getOrCreateKotlinClass(class0);
        List list0 = Collections.singletonList(kTypeProjection0);
        return Reflection.factory.typeOf(kClass0, list0, false);
    }

    public static KType typeOf(Class class0, KTypeProjection kTypeProjection0, KTypeProjection kTypeProjection1) {
        KClass kClass0 = Reflection.getOrCreateKotlinClass(class0);
        List list0 = Arrays.asList(new KTypeProjection[]{kTypeProjection0, kTypeProjection1});
        return Reflection.factory.typeOf(kClass0, list0, false);
    }

    public static KType typeOf(Class class0, KTypeProjection[] arr_kTypeProjection) {
        KClass kClass0 = Reflection.getOrCreateKotlinClass(class0);
        List list0 = ArraysKt.toList(arr_kTypeProjection);
        return Reflection.factory.typeOf(kClass0, list0, false);
    }

    public static KType typeOf(KClassifier kClassifier0) {
        return Reflection.factory.typeOf(kClassifier0, Collections.EMPTY_LIST, false);
    }

    public static KTypeParameter typeParameter(Object object0, String s, KVariance kVariance0, boolean z) {
        return Reflection.factory.typeParameter(object0, s, kVariance0, z);
    }
}

