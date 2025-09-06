package kotlin.jvm;

import java.lang.annotation.Annotation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u000B\n\u0002\u0010\u000B\n\u0002\u0010\u0011\n\u0002\b\u0002\u001A\u001F\u0010\u001F\u001A\u00020 \"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0014*\u0006\u0012\u0002\b\u00030!¢\u0006\u0002\u0010\"\"\'\u0010\u0000\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005\";\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u000E\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t*\b\u0012\u0004\u0012\u0002H\b0\t8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000B\u001A\u0004\b\f\u0010\r\"-\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018G¢\u0006\f\u0012\u0004\b\u000F\u0010\u0010\u001A\u0004\b\u0011\u0010\u0012\"&\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\u0002H\u00028Æ\u0002¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0015\";\u0010\u0013\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00018Ç\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0010\u001A\u0004\b\u0017\u0010\u0012\"+\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001A\u0004\b\u0019\u0010\u0012\"-\u0010\u001A\u001A\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001A\u0004\b\u001B\u0010\u0012\"+\u0010\u001C\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00078G¢\u0006\u0006\u001A\u0004\b\u001D\u0010\u001E¨\u0006#"}, d2 = {"annotationClass", "Lkotlin/reflect/KClass;", "T", "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "declaringJavaClass", "Ljava/lang/Class;", "E", "", "getDeclaringJavaClass$annotations", "(Ljava/lang/Enum;)V", "getDeclaringJavaClass", "(Ljava/lang/Enum;)Ljava/lang/Class;", "java", "getJavaClass$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaClass", "", "(Ljava/lang/Object;)Ljava/lang/Class;", "getRuntimeClassOfKClassInstance$annotations", "getRuntimeClassOfKClassInstance", "javaObjectType", "getJavaObjectType", "javaPrimitiveType", "getJavaPrimitiveType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class JvmClassMappingKt {
    public static final KClass getAnnotationClass(Annotation annotation0) {
        Intrinsics.checkNotNullParameter(annotation0, "<this>");
        Class class0 = annotation0.annotationType();
        Intrinsics.checkNotNullExpressionValue(class0, "this as java.lang.annota…otation).annotationType()");
        KClass kClass0 = JvmClassMappingKt.getKotlinClass(class0);
        Intrinsics.checkNotNull(kClass0, "null cannot be cast to non-null type kotlin.reflect.KClass<out T of kotlin.jvm.JvmClassMappingKt.<get-annotationClass>>");
        return kClass0;
    }

    private static final Class getDeclaringJavaClass(Enum enum0) {
        Intrinsics.checkNotNullParameter(enum0, "<this>");
        Class class0 = enum0.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(class0, "this as java.lang.Enum<E>).declaringClass");
        return class0;
    }

    public static void getDeclaringJavaClass$annotations(Enum enum0) {
    }

    public static final Class getJavaClass(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "<this>");
        Class class0 = object0.getClass();
        Intrinsics.checkNotNull(class0, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaClass>>");
        return class0;
    }

    public static final Class getJavaClass(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Class class0 = ((ClassBasedDeclarationContainer)kClass0).getJClass();
        Intrinsics.checkNotNull(class0, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return class0;
    }

    public static void getJavaClass$annotations(KClass kClass0) {
    }

    public static final Class getJavaObjectType(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Class class0 = ((ClassBasedDeclarationContainer)kClass0).getJClass();
        if(!class0.isPrimitive()) {
            Intrinsics.checkNotNull(class0, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
            return class0;
        }
        String s = class0.getName();
        if(s != null) {
            switch(s) {
                case "boolean": {
                    class0 = Boolean.class;
                    break;
                }
                case "byte": {
                    class0 = Byte.class;
                    break;
                }
                case "char": {
                    class0 = Character.class;
                    break;
                }
                case "double": {
                    class0 = Double.class;
                    break;
                }
                case "float": {
                    class0 = Float.class;
                    break;
                }
                case "int": {
                    class0 = Integer.class;
                    break;
                }
                case "long": {
                    class0 = Long.class;
                    break;
                }
                case "short": {
                    class0 = Short.class;
                    break;
                }
                case "void": {
                    class0 = Void.class;
                }
            }
        }
        Intrinsics.checkNotNull(class0, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
        return class0;
    }

    public static final Class getJavaPrimitiveType(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Class class0 = ((ClassBasedDeclarationContainer)kClass0).getJClass();
        if(class0.isPrimitive()) {
            Intrinsics.checkNotNull(class0, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaPrimitiveType>>");
            return class0;
        }
        String s = class0.getName();
        if(s != null) {
            switch(s) {
                case "java.lang.Boolean": {
                    return Boolean.TYPE;
                }
                case "java.lang.Byte": {
                    return Byte.TYPE;
                }
                case "java.lang.Character": {
                    return Character.TYPE;
                }
                case "java.lang.Double": {
                    return Double.TYPE;
                }
                case "java.lang.Float": {
                    return Float.TYPE;
                }
                case "java.lang.Integer": {
                    return Integer.TYPE;
                }
                case "java.lang.Long": {
                    return Long.TYPE;
                }
                case "java.lang.Short": {
                    return Short.TYPE;
                }
                case "java.lang.Void": {
                    return Void.TYPE;
                }
                default: {
                    return null;
                }
            }
        }
        return null;
    }

    public static final KClass getKotlinClass(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        return Reflection.getOrCreateKotlinClass(class0);
    }

    public static final Class getRuntimeClassOfKClassInstance(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Class class0 = kClass0.getClass();
        Intrinsics.checkNotNull(class0, "null cannot be cast to non-null type java.lang.Class<kotlin.reflect.KClass<T of kotlin.jvm.JvmClassMappingKt.<get-javaClass>>>");
        return class0;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use \'java\' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", replaceWith = @ReplaceWith(expression = "(this as Any).javaClass", imports = {}))
    public static void getRuntimeClassOfKClassInstance$annotations(KClass kClass0) {
    }

    public static final boolean isArrayOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "<this>");
        Intrinsics.reifiedOperationMarker(4, "T");
        Class class0 = arr_object.getClass();
        return Object.class.isAssignableFrom(class0.getComponentType());
    }
}

