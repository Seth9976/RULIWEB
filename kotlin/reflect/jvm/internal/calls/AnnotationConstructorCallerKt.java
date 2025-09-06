package kotlin.reflect.jvm.internal.calls;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;

@Metadata(d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u001AI\u0010\u0000\u001A\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u00062\u000E\b\u0002\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0002\u0010\u000B\u001A$\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00072\n\u0010\u0011\u001A\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001A\u001C\u0010\u0012\u001A\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00022\n\u0010\u0013\u001A\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u0014²\u0006\u0014\u0010\u0015\u001A\u00020\u000F\"\b\b\u0000\u0010\u0001*\u00020\u0002X\u008A\u0084\u0002²\u0006\u0014\u0010\u0016\u001A\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0002X\u008A\u0084\u0002"}, d2 = {"createAnnotationInstance", "T", "", "annotationClass", "Ljava/lang/Class;", "values", "", "", "methods", "", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/util/Map;Ljava/util/List;)Ljava/lang/Object;", "throwIllegalArgumentType", "", "index", "", "name", "expectedJvmType", "transformKotlinToJvm", "expectedType", "kotlin-reflection", "hashCode", "toString"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class AnnotationConstructorCallerKt {
    public static final Void access$throwIllegalArgumentType(int v, String s, Class class0) {
        return AnnotationConstructorCallerKt.throwIllegalArgumentType(v, s, class0);
    }

    public static final Object access$transformKotlinToJvm(Object object0, Class class0) {
        return AnnotationConstructorCallerKt.transformKotlinToJvm(object0, class0);
    }

    // 检测为 Lambda 实现
    static Object accessor$AnnotationConstructorCallerKt$lambda0(Class class0, Map map0, Lazy lazy0, Lazy lazy1, List list0, Object object0, Method method0, Object[] arr_object) [...]

    public static final Object createAnnotationInstance(Class class0, Map map0, List list0) {
        Intrinsics.checkNotNullParameter(class0, "annotationClass");
        Intrinsics.checkNotNullParameter(map0, "values");
        Intrinsics.checkNotNullParameter(list0, "methods");
        Lazy lazy0 = LazyKt.lazy(new Function0(map0) {
            final Map $values;

            {
                this.$values = map0;
                super(0);
            }

            public final Integer invoke() {
                int v1;
                int v = 0;
                for(Object object0: this.$values.entrySet()) {
                    String s = (String)((Map.Entry)object0).getKey();
                    Object object1 = ((Map.Entry)object0).getValue();
                    if(object1 instanceof boolean[]) {
                        v1 = Arrays.hashCode(((boolean[])object1));
                    }
                    else if(object1 instanceof char[]) {
                        v1 = Arrays.hashCode(((char[])object1));
                    }
                    else if(object1 instanceof byte[]) {
                        v1 = Arrays.hashCode(((byte[])object1));
                    }
                    else if(object1 instanceof short[]) {
                        v1 = Arrays.hashCode(((short[])object1));
                    }
                    else if(object1 instanceof int[]) {
                        v1 = Arrays.hashCode(((int[])object1));
                    }
                    else if(object1 instanceof float[]) {
                        v1 = Arrays.hashCode(((float[])object1));
                    }
                    else if(object1 instanceof long[]) {
                        v1 = Arrays.hashCode(((long[])object1));
                    }
                    else if(object1 instanceof double[]) {
                        v1 = Arrays.hashCode(((double[])object1));
                    }
                    else {
                        v1 = object1 instanceof Object[] ? Arrays.hashCode(((Object[])object1)) : object1.hashCode();
                    }
                    v += v1 ^ s.hashCode() * 0x7F;
                }
                return v;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
        Lazy lazy1 = LazyKt.lazy(new Function0(class0, map0) {
            final Class $annotationClass;
            final Map $values;

            {
                this.$annotationClass = class0;
                this.$values = map0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final String invoke() {
                StringBuilder stringBuilder0 = new StringBuilder();
                stringBuilder0.append('@');
                stringBuilder0.append(this.$annotationClass.getCanonicalName());
                CollectionsKt.joinTo$default(this.$values.entrySet(), stringBuilder0, ", ", "(", ")", 0, null, kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance.toString.2.1.1.INSTANCE, 0x30, null);
                String s = stringBuilder0.toString();
                Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
                return s;

                @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010&\n\u0002\u0010\u000E\n\u0000\u0010\u0000\u001A\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "T", "", "entry", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
                final class kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance.toString.2.1.1 extends Lambda implements Function1 {
                    public static final kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance.toString.2.1.1 INSTANCE;

                    static {
                        kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance.toString.2.1.1.INSTANCE = new kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance.toString.2.1.1();
                    }

                    kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance.toString.2.1.1() {
                        super(1);
                    }

                    public final CharSequence invoke(Map.Entry map$Entry0) {
                        String s1;
                        Intrinsics.checkNotNullParameter(map$Entry0, "entry");
                        String s = (String)map$Entry0.getKey();
                        Object object0 = map$Entry0.getValue();
                        if(object0 instanceof boolean[]) {
                            s1 = Arrays.toString(((boolean[])object0));
                            Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                            return s + '=' + s1;
                        }
                        if(object0 instanceof char[]) {
                            s1 = Arrays.toString(((char[])object0));
                            Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                            return s + '=' + s1;
                        }
                        if(object0 instanceof byte[]) {
                            s1 = Arrays.toString(((byte[])object0));
                            Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                            return s + '=' + s1;
                        }
                        if(object0 instanceof short[]) {
                            s1 = Arrays.toString(((short[])object0));
                            Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                            return s + '=' + s1;
                        }
                        if(object0 instanceof int[]) {
                            s1 = Arrays.toString(((int[])object0));
                            Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                            return s + '=' + s1;
                        }
                        if(object0 instanceof float[]) {
                            s1 = Arrays.toString(((float[])object0));
                            Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                            return s + '=' + s1;
                        }
                        if(object0 instanceof long[]) {
                            s1 = Arrays.toString(((long[])object0));
                            Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                            return s + '=' + s1;
                        }
                        if(object0 instanceof double[]) {
                            s1 = Arrays.toString(((double[])object0));
                            Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                            return s + '=' + s1;
                        }
                        if(object0 instanceof Object[]) {
                            s1 = Arrays.toString(((Object[])object0));
                            Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                            return s + '=' + s1;
                        }
                        return s + '=' + object0.toString();
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        return this.invoke(((Map.Entry)object0));
                    }
                }

            }
        });
        Object object0 = Proxy.newProxyInstance(class0.getClassLoader(), new Class[]{class0}, (Object object0, Method method0, Object[] arr_object) -> AnnotationConstructorCallerKt.createAnnotationInstance$lambda$4(class0, map0, lazy1, lazy0, list0, object0, method0, arr_object));
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance");
        return object0;
    }

    public static Object createAnnotationInstance$default(Class class0, Map map0, List list0, int v, Object object0) {
        if((v & 4) != 0) {
            Iterable iterable0 = map0.keySet();
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
            for(Object object1: iterable0) {
                arrayList0.add(class0.getDeclaredMethod(((String)object1), null));
            }
            list0 = arrayList0;
        }
        return AnnotationConstructorCallerKt.createAnnotationInstance(class0, map0, list0);
    }

    private static final boolean createAnnotationInstance$equals(Class class0, List list0, Map map0, Object object0) {
        boolean z;
        Class class1;
        Annotation annotation0 = object0 instanceof Annotation ? ((Annotation)object0) : null;
        if(annotation0 == null) {
            class1 = null;
        }
        else {
            KClass kClass0 = JvmClassMappingKt.getAnnotationClass(annotation0);
            class1 = kClass0 == null ? null : JvmClassMappingKt.getJavaClass(kClass0);
        }
        if(Intrinsics.areEqual(class1, class0)) {
            if(!(list0 instanceof Collection) || !list0.isEmpty()) {
                for(Object object1: list0) {
                    Object object2 = map0.get(((Method)object1).getName());
                    Object object3 = ((Method)object1).invoke(object0, null);
                    if(object2 instanceof boolean[]) {
                        Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.BooleanArray");
                        z = Arrays.equals(((boolean[])object2), ((boolean[])object3));
                    }
                    else if(object2 instanceof char[]) {
                        Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.CharArray");
                        z = Arrays.equals(((char[])object2), ((char[])object3));
                    }
                    else if(object2 instanceof byte[]) {
                        Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.ByteArray");
                        z = Arrays.equals(((byte[])object2), ((byte[])object3));
                    }
                    else if(object2 instanceof short[]) {
                        Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.ShortArray");
                        z = Arrays.equals(((short[])object2), ((short[])object3));
                    }
                    else if(object2 instanceof int[]) {
                        Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.IntArray");
                        z = Arrays.equals(((int[])object2), ((int[])object3));
                    }
                    else if(object2 instanceof float[]) {
                        Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.FloatArray");
                        z = Arrays.equals(((float[])object2), ((float[])object3));
                    }
                    else if(object2 instanceof long[]) {
                        Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.LongArray");
                        z = Arrays.equals(((long[])object2), ((long[])object3));
                    }
                    else if(object2 instanceof double[]) {
                        Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.DoubleArray");
                        z = Arrays.equals(((double[])object2), ((double[])object3));
                    }
                    else if(object2 instanceof Object[]) {
                        Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.Array<*>");
                        z = Arrays.equals(((Object[])object2), ((Object[])object3));
                    }
                    else {
                        z = Intrinsics.areEqual(object2, object3);
                    }
                    if(!z) {
                        return false;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private static final int createAnnotationInstance$lambda$2(Lazy lazy0) {
        return ((Number)lazy0.getValue()).intValue();
    }

    private static final String createAnnotationInstance$lambda$3(Lazy lazy0) {
        return (String)lazy0.getValue();
    }

    private static final Object createAnnotationInstance$lambda$4(Class class0, Map map0, Lazy lazy0, Lazy lazy1, List list0, Object object0, Method method0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(class0, "$annotationClass");
        Intrinsics.checkNotNullParameter(map0, "$values");
        Intrinsics.checkNotNullParameter(lazy0, "$toString$delegate");
        Intrinsics.checkNotNullParameter(lazy1, "$hashCode$delegate");
        Intrinsics.checkNotNullParameter(list0, "$methods");
        String s = method0.getName();
        if(s != null) {
            switch(s) {
                case "annotationType": {
                    return class0;
                }
                case "hashCode": {
                    return AnnotationConstructorCallerKt.createAnnotationInstance$lambda$2(lazy1);
                }
                case "toString": {
                    return AnnotationConstructorCallerKt.createAnnotationInstance$lambda$3(lazy0);
                }
            }
        }
        if(Intrinsics.areEqual(s, "equals") && arr_object != null && arr_object.length == 1) {
            Intrinsics.checkNotNullExpressionValue(arr_object, "args");
            return Boolean.valueOf(AnnotationConstructorCallerKt.createAnnotationInstance$equals(class0, list0, map0, ArraysKt.single(arr_object)));
        }
        if(map0.containsKey(s)) {
            return map0.get(s);
        }
        StringBuilder stringBuilder0 = new StringBuilder("Method is not supported: ");
        stringBuilder0.append(method0);
        stringBuilder0.append(" (args: ");
        if(arr_object == null) {
            arr_object = new Object[0];
        }
        stringBuilder0.append(ArraysKt.toList(arr_object));
        stringBuilder0.append(')');
        throw new KotlinReflectionInternalError(stringBuilder0.toString());
    }

    private static final Void throwIllegalArgumentType(int v, String s, Class class0) {
        String s1;
        KClass kClass0;
        if(Intrinsics.areEqual(class0, Class.class)) {
            kClass0 = Reflection.getOrCreateKotlinClass(KClass.class);
        }
        else {
            kClass0 = !class0.isArray() || !Intrinsics.areEqual(class0.getComponentType(), Class.class) ? JvmClassMappingKt.getKotlinClass(class0) : Reflection.getOrCreateKotlinClass(KClass[].class);
        }
        if(Intrinsics.areEqual(kClass0.getQualifiedName(), Reflection.getOrCreateKotlinClass(Object[].class).getQualifiedName())) {
            Class class1 = JvmClassMappingKt.getJavaClass(kClass0).getComponentType();
            Intrinsics.checkNotNullExpressionValue(class1, "kotlinClass.java.componentType");
            s1 = kClass0.getQualifiedName() + '<' + JvmClassMappingKt.getKotlinClass(class1).getQualifiedName() + '>';
        }
        else {
            s1 = kClass0.getQualifiedName();
        }
        throw new IllegalArgumentException("Argument #" + v + ' ' + s + " is not of the required type " + s1);
    }

    private static final Object transformKotlinToJvm(Object object0, Class class0) {
        if(object0 instanceof Class) {
            return null;
        }
        if(object0 instanceof KClass) {
            object0 = JvmClassMappingKt.getJavaClass(((KClass)object0));
            return class0.isInstance(object0) ? object0 : null;
        }
        if(object0 instanceof Object[]) {
            if(((Object[])object0) instanceof Class[]) {
                return null;
            }
            if(((Object[])object0) instanceof KClass[]) {
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
                KClass[] arr_kClass = (KClass[])object0;
                ArrayList arrayList0 = new ArrayList(arr_kClass.length);
                for(int v = 0; v < arr_kClass.length; ++v) {
                    arrayList0.add(JvmClassMappingKt.getJavaClass(arr_kClass[v]));
                }
                object0 = arrayList0.toArray(new Class[0]);
                return class0.isInstance(object0) ? object0 : null;
            }
            object0 = (Object[])object0;
        }
        return class0.isInstance(object0) ? object0 : null;
    }
}

