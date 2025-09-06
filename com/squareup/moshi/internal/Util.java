package com.squareup.moshi.internal;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonClass;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonQualifier;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class Util {
    public static final class GenericArrayTypeImpl implements GenericArrayType {
        private final Type componentType;

        public GenericArrayTypeImpl(Type type0) {
            this.componentType = Util.canonicalize(type0);
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof GenericArrayType && Types.equals(this, ((GenericArrayType)object0));
        }

        @Override
        public Type getGenericComponentType() {
            return this.componentType;
        }

        @Override
        public int hashCode() {
            return this.componentType.hashCode();
        }

        @Override
        public String toString() {
            return Util.typeToString(this.componentType) + "[]";
        }
    }

    public static final class ParameterizedTypeImpl implements ParameterizedType {
        @Nullable
        private final Type ownerType;
        private final Type rawType;
        public final Type[] typeArguments;

        public ParameterizedTypeImpl(@Nullable Type type0, Type type1, Type[] arr_type) {
            if(type1 instanceof Class) {
                Class class0 = ((Class)type1).getEnclosingClass();
                if(type0 != null) {
                    if(class0 == null || Types.getRawType(type0) != class0) {
                        throw new IllegalArgumentException("unexpected owner type for " + type1 + ": " + type0);
                    }
                }
                else if(class0 != null) {
                    throw new IllegalArgumentException("unexpected owner type for " + type1 + ": null");
                }
            }
            this.ownerType = type0 == null ? null : Util.canonicalize(type0);
            this.rawType = Util.canonicalize(type1);
            this.typeArguments = (Type[])arr_type.clone();
            for(int v = 0; true; ++v) {
                Type[] arr_type1 = this.typeArguments;
                if(v >= arr_type1.length) {
                    break;
                }
                arr_type1[v].getClass();
                Util.checkNotPrimitive(this.typeArguments[v]);
                this.typeArguments[v] = Util.canonicalize(this.typeArguments[v]);
            }
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof ParameterizedType && Types.equals(this, ((ParameterizedType)object0));
        }

        @Override
        public Type[] getActualTypeArguments() {
            return (Type[])this.typeArguments.clone();
        }

        @Override
        @Nullable
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override
        public Type getRawType() {
            return this.rawType;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ Util.hashCodeOrZero(this.ownerType);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder((this.typeArguments.length + 1) * 30);
            stringBuilder0.append(Util.typeToString(this.rawType));
            if(this.typeArguments.length == 0) {
                return stringBuilder0.toString();
            }
            stringBuilder0.append("<");
            stringBuilder0.append(Util.typeToString(this.typeArguments[0]));
            for(int v = 1; v < this.typeArguments.length; ++v) {
                stringBuilder0.append(", ");
                stringBuilder0.append(Util.typeToString(this.typeArguments[v]));
            }
            stringBuilder0.append(">");
            return stringBuilder0.toString();
        }
    }

    public static final class WildcardTypeImpl implements WildcardType {
        @Nullable
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] arr_type, Type[] arr_type1) {
            if(arr_type1.length > 1 || arr_type.length != 1) {
                throw new IllegalArgumentException();
            }
            if(arr_type1.length == 1) {
                arr_type1[0].getClass();
                Util.checkNotPrimitive(arr_type1[0]);
                if(arr_type[0] != Object.class) {
                    throw new IllegalArgumentException();
                }
                this.lowerBound = Util.canonicalize(arr_type1[0]);
                this.upperBound = Object.class;
                return;
            }
            arr_type[0].getClass();
            Util.checkNotPrimitive(arr_type[0]);
            this.lowerBound = null;
            this.upperBound = Util.canonicalize(arr_type[0]);
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof WildcardType && Types.equals(this, ((WildcardType)object0));
        }

        @Override
        public Type[] getLowerBounds() {
            return this.lowerBound == null ? Util.EMPTY_TYPE_ARRAY : new Type[]{this.lowerBound};
        }

        @Override
        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        @Override
        public int hashCode() {
            return this.lowerBound == null ? 1 ^ this.upperBound.hashCode() + 0x1F : this.lowerBound.hashCode() + 0x1F ^ this.upperBound.hashCode() + 0x1F;
        }

        @Override
        public String toString() {
            if(this.lowerBound != null) {
                return "? super " + Util.typeToString(this.lowerBound);
            }
            return this.upperBound == Object.class ? "?" : "? extends " + Util.typeToString(this.upperBound);
        }
    }

    @Nullable
    public static final Class DEFAULT_CONSTRUCTOR_MARKER;
    public static final Type[] EMPTY_TYPE_ARRAY;
    @Nullable
    private static final Class METADATA;
    public static final Set NO_ANNOTATIONS;
    private static final Map PRIMITIVE_TO_WRAPPER_TYPE;

    static {
        Util.NO_ANNOTATIONS = Collections.EMPTY_SET;
        Util.EMPTY_TYPE_ARRAY = new Type[0];
        Util.METADATA = Metadata.class;
        Util.DEFAULT_CONSTRUCTOR_MARKER = DefaultConstructorMarker.class;
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(16);
        linkedHashMap0.put(Boolean.TYPE, Boolean.class);
        linkedHashMap0.put(Byte.TYPE, Byte.class);
        linkedHashMap0.put(Character.TYPE, Character.class);
        linkedHashMap0.put(Double.TYPE, Double.class);
        linkedHashMap0.put(Float.TYPE, Float.class);
        linkedHashMap0.put(Integer.TYPE, Integer.class);
        linkedHashMap0.put(Long.TYPE, Long.class);
        linkedHashMap0.put(Short.TYPE, Short.class);
        linkedHashMap0.put(Void.TYPE, Void.class);
        Util.PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(linkedHashMap0);
    }

    public static Class boxIfPrimitive(Class class0) {
        Class class1 = (Class)Util.PRIMITIVE_TO_WRAPPER_TYPE.get(class0);
        return class1 == null ? class0 : class1;
    }

    public static Type canonicalize(Type type0) {
        if(type0 instanceof Class) {
            return ((Class)type0).isArray() ? new GenericArrayTypeImpl(Util.canonicalize(((Class)type0).getComponentType())) : ((Class)type0);
        }
        if(type0 instanceof ParameterizedType) {
            if(!(type0 instanceof ParameterizedTypeImpl)) {
                return new ParameterizedTypeImpl(((ParameterizedType)type0).getOwnerType(), ((ParameterizedType)type0).getRawType(), ((ParameterizedType)type0).getActualTypeArguments());
            }
        }
        else if(type0 instanceof GenericArrayType) {
            if(!(type0 instanceof GenericArrayTypeImpl)) {
                return new GenericArrayTypeImpl(((GenericArrayType)type0).getGenericComponentType());
            }
        }
        else if(type0 instanceof WildcardType && !(type0 instanceof WildcardTypeImpl)) {
            return new WildcardTypeImpl(((WildcardType)type0).getUpperBounds(), ((WildcardType)type0).getLowerBounds());
        }
        return type0;
    }

    static void checkNotPrimitive(Type type0) {
        if(type0 instanceof Class && ((Class)type0).isPrimitive()) {
            throw new IllegalArgumentException("Unexpected primitive " + type0 + ". Use the boxed type.");
        }
    }

    @Nullable
    static Class declaringClassOf(TypeVariable typeVariable0) {
        GenericDeclaration genericDeclaration0 = typeVariable0.getGenericDeclaration();
        return genericDeclaration0 instanceof Class ? ((Class)genericDeclaration0) : null;
    }

    private static Constructor findConstructor(Class class0) {
        Constructor[] arr_constructor = class0.getDeclaredConstructors();
        for(int v = 0; v < arr_constructor.length; ++v) {
            Constructor constructor0 = arr_constructor[v];
            Class[] arr_class = constructor0.getParameterTypes();
            if(arr_class.length != 0 && arr_class[arr_class.length - 1].equals(Util.DEFAULT_CONSTRUCTOR_MARKER)) {
                return constructor0;
            }
        }
        throw new IllegalStateException("No defaults constructor found for " + class0);
    }

    @Nullable
    public static JsonAdapter generatedAdapter(Moshi moshi0, Type type0, Class class0) {
        Object[] arr_object;
        Constructor constructor0;
        Type[] arr_type;
        Class class2;
        JsonClass jsonClass0 = (JsonClass)class0.getAnnotation(JsonClass.class);
        Class class1 = null;
        if(jsonClass0 != null && jsonClass0.generateAdapter()) {
            String s = Types.generatedJsonAdapterName(class0.getName());
            try {
                class2 = Class.forName(s, true, class0.getClassLoader());
                goto label_8;
            }
            catch(ClassNotFoundException classNotFoundException0) {
                throw new RuntimeException("Failed to find the generated JsonAdapter class for " + type0, classNotFoundException0);
            }
            catch(NoSuchMethodException noSuchMethodException0) {
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new RuntimeException("Failed to access the generated JsonAdapter for " + type0, illegalAccessException0);
            }
            catch(InstantiationException instantiationException0) {
                throw new RuntimeException("Failed to instantiate the generated JsonAdapter for " + type0, instantiationException0);
            }
            catch(InvocationTargetException invocationTargetException0) {
                throw Util.rethrowCause(invocationTargetException0);
            }
            throw type0 instanceof ParameterizedType || class1.getTypeParameters().length == 0 ? new RuntimeException("Failed to find the generated JsonAdapter constructor for " + type0, noSuchMethodException0) : new RuntimeException("Failed to find the generated JsonAdapter constructor for \'" + type0 + "\'. Suspiciously, the type was not parameterized but the target class \'" + class1.getCanonicalName() + "\' is generic. Consider using Types#newParameterizedType() to define these missing type variables.", noSuchMethodException0);
            try {
                try {
                label_8:
                    if(type0 instanceof ParameterizedType) {
                        arr_type = ((ParameterizedType)type0).getActualTypeArguments();
                        constructor0 = class2.getDeclaredConstructor(Moshi.class, Type[].class);
                        arr_object = new Object[]{moshi0, arr_type};
                    }
                    else {
                        constructor0 = class2.getDeclaredConstructor(Moshi.class);
                        arr_object = new Object[]{moshi0};
                    }
                    goto label_21;
                }
                catch(ClassNotFoundException classNotFoundException0) {
                    throw new RuntimeException("Failed to find the generated JsonAdapter class for " + type0, classNotFoundException0);
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    class1 = class2;
                    throw type0 instanceof ParameterizedType || class1.getTypeParameters().length == 0 ? new RuntimeException("Failed to find the generated JsonAdapter constructor for " + type0, noSuchMethodException0) : new RuntimeException("Failed to find the generated JsonAdapter constructor for \'" + type0 + "\'. Suspiciously, the type was not parameterized but the target class \'" + class1.getCanonicalName() + "\' is generic. Consider using Types#newParameterizedType() to define these missing type variables.", noSuchMethodException0);
                }
                try {
                    constructor0 = class2.getDeclaredConstructor(Moshi.class, Type[].class);
                    arr_object = new Object[]{moshi0, arr_type};
                    goto label_21;
                }
                catch(NoSuchMethodException unused_ex) {
                }
                catch(ClassNotFoundException classNotFoundException0) {
                    throw new RuntimeException("Failed to find the generated JsonAdapter class for " + type0, classNotFoundException0);
                }
                try {
                    constructor0 = class2.getDeclaredConstructor(Type[].class);
                    arr_object = new Object[]{arr_type};
                    goto label_21;
                }
                catch(ClassNotFoundException classNotFoundException0) {
                    throw new RuntimeException("Failed to find the generated JsonAdapter class for " + type0, classNotFoundException0);
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    class1 = class2;
                    throw type0 instanceof ParameterizedType || class1.getTypeParameters().length == 0 ? new RuntimeException("Failed to find the generated JsonAdapter constructor for " + type0, noSuchMethodException0) : new RuntimeException("Failed to find the generated JsonAdapter constructor for \'" + type0 + "\'. Suspiciously, the type was not parameterized but the target class \'" + class1.getCanonicalName() + "\' is generic. Consider using Types#newParameterizedType() to define these missing type variables.", noSuchMethodException0);
                }
                try {
                    constructor0 = class2.getDeclaredConstructor(Moshi.class);
                    arr_object = new Object[]{moshi0};
                    goto label_21;
                }
                catch(NoSuchMethodException unused_ex) {
                }
                catch(ClassNotFoundException classNotFoundException0) {
                    throw new RuntimeException("Failed to find the generated JsonAdapter class for " + type0, classNotFoundException0);
                }
                try {
                    constructor0 = class2.getDeclaredConstructor(null);
                    arr_object = new Object[0];
                label_21:
                    constructor0.setAccessible(true);
                    return ((JsonAdapter)constructor0.newInstance(arr_object)).nullSafe();
                }
                catch(ClassNotFoundException classNotFoundException0) {
                }
                catch(NoSuchMethodException noSuchMethodException0) {
                    class1 = class2;
                    throw type0 instanceof ParameterizedType || class1.getTypeParameters().length == 0 ? new RuntimeException("Failed to find the generated JsonAdapter constructor for " + type0, noSuchMethodException0) : new RuntimeException("Failed to find the generated JsonAdapter constructor for \'" + type0 + "\'. Suspiciously, the type was not parameterized but the target class \'" + class1.getCanonicalName() + "\' is generic. Consider using Types#newParameterizedType() to define these missing type variables.", noSuchMethodException0);
                }
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new RuntimeException("Failed to access the generated JsonAdapter for " + type0, illegalAccessException0);
            }
            catch(InstantiationException instantiationException0) {
                throw new RuntimeException("Failed to instantiate the generated JsonAdapter for " + type0, instantiationException0);
            }
            catch(InvocationTargetException invocationTargetException0) {
                throw Util.rethrowCause(invocationTargetException0);
            }
            throw new RuntimeException("Failed to find the generated JsonAdapter class for " + type0, classNotFoundException0);
        }
        return null;
    }

    public static Type getGenericSupertype(Type type0, Class class0, Class class1) {
        if(class1 == class0) {
            return type0;
        }
        if(class1.isInterface()) {
            Class[] arr_class = class0.getInterfaces();
            for(int v = 0; v < arr_class.length; ++v) {
                Class class2 = arr_class[v];
                if(class2 == class1) {
                    return class0.getGenericInterfaces()[v];
                }
                if(class1.isAssignableFrom(class2)) {
                    return Util.getGenericSupertype(class0.getGenericInterfaces()[v], arr_class[v], class1);
                }
            }
        }
        if(!class0.isInterface()) {
            while(class0 != Object.class) {
                Class class3 = class0.getSuperclass();
                if(class3 == class1) {
                    return class0.getGenericSuperclass();
                }
                if(class1.isAssignableFrom(class3)) {
                    return Util.getGenericSupertype(class0.getGenericSuperclass(), class3, class1);
                }
                class0 = class3;
            }
        }
        return class1;
    }

    private static String getKotlinMetadataClassName() [...] // Inlined contents

    public static boolean hasNullable(Annotation[] arr_annotation) {
        for(int v = 0; v < arr_annotation.length; ++v) {
            if(arr_annotation[v].annotationType().getSimpleName().equals("Nullable")) {
                return true;
            }
        }
        return false;
    }

    static int hashCodeOrZero(@Nullable Object object0) {
        return object0 == null ? 0 : object0.hashCode();
    }

    static int indexOf(Object[] arr_object, Object object0) {
        for(int v = 0; v < arr_object.length; ++v) {
            if(object0.equals(arr_object[v])) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    public static boolean isAnnotationPresent(Set set0, Class class0) {
        if(set0.isEmpty()) {
            return false;
        }
        for(Object object0: set0) {
            if(((Annotation)object0).annotationType() == class0) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static boolean isKotlin(Class class0) {
        return Util.METADATA != null && class0.isAnnotationPresent(Util.METADATA);
    }

    // 去混淆评级： 低(35)
    public static boolean isPlatformType(Class class0) {
        String s = class0.getName();
        return s.startsWith("android.") || s.startsWith("androidx.") || s.startsWith("java.") || s.startsWith("javax.") || s.startsWith("kotlin.") || s.startsWith("kotlinx.") || s.startsWith("scala.");
    }

    public static Set jsonAnnotations(AnnotatedElement annotatedElement0) {
        return Util.jsonAnnotations(annotatedElement0.getAnnotations());
    }

    public static Set jsonAnnotations(Annotation[] arr_annotation) {
        Set set0 = null;
        for(int v = 0; v < arr_annotation.length; ++v) {
            Annotation annotation0 = arr_annotation[v];
            if(annotation0.annotationType().isAnnotationPresent(JsonQualifier.class)) {
                if(set0 == null) {
                    set0 = new LinkedHashSet();
                }
                set0.add(annotation0);
            }
        }
        return set0 == null ? Util.NO_ANNOTATIONS : Collections.unmodifiableSet(set0);
    }

    public static Constructor lookupDefaultsConstructor(Class class0) {
        if(Util.DEFAULT_CONSTRUCTOR_MARKER == null) {
            throw new IllegalStateException("DefaultConstructorMarker not on classpath. Make sure the Kotlin stdlib is on the classpath.");
        }
        Constructor constructor0 = Util.findConstructor(class0);
        constructor0.setAccessible(true);
        return constructor0;
    }

    // 去混淆评级： 低(40)
    public static JsonDataException missingProperty(String s, String s1, JsonReader jsonReader0) {
        return s1.equals(s) ? new JsonDataException(String.format("Required value \'%s\' missing at %s", s, "$")) : new JsonDataException(String.format("Required value \'%s\' (JSON name \'%s\') missing at %s", s, s1, "$"));
    }

    public static Type removeSubtypeWildcard(Type type0) {
        if(!(type0 instanceof WildcardType) || ((WildcardType)type0).getLowerBounds().length != 0) {
            return type0;
        }
        Type[] arr_type = ((WildcardType)type0).getUpperBounds();
        if(arr_type.length != 1) {
            throw new IllegalArgumentException();
        }
        return arr_type[0];
    }

    public static Type resolve(Type type0, Class class0, Type type1) {
        return Util.resolve(type0, class0, type1, new LinkedHashSet());
    }

    private static Type resolve(Type type0, Class class0, Type type1, Collection collection0) {
        while(type1 instanceof TypeVariable) {
            TypeVariable typeVariable0 = (TypeVariable)type1;
            if(collection0.contains(typeVariable0)) {
                return type1;
            }
            collection0.add(typeVariable0);
            type1 = Util.resolveTypeVariable(type0, class0, typeVariable0);
            if(type1 != typeVariable0) {
                continue;
            }
            return type1;
        }
        if(type1 instanceof Class && ((Class)type1).isArray()) {
            Class class1 = ((Class)type1).getComponentType();
            Type type2 = Util.resolve(type0, class0, class1, collection0);
            return class1 == type2 ? ((Class)type1) : Types.arrayOf(type2);
        }
        if(type1 instanceof GenericArrayType) {
            Type type3 = ((GenericArrayType)type1).getGenericComponentType();
            Type type4 = Util.resolve(type0, class0, type3, collection0);
            return type3 == type4 ? ((GenericArrayType)type1) : Types.arrayOf(type4);
        }
        if(type1 instanceof ParameterizedType) {
            Type type5 = ((ParameterizedType)type1).getOwnerType();
            Type type6 = Util.resolve(type0, class0, type5, collection0);
            boolean z = type6 != type5;
            Type[] arr_type = ((ParameterizedType)type1).getActualTypeArguments();
            int v1 = arr_type.length;
            for(int v = 0; v < v1; ++v) {
                Type type7 = Util.resolve(type0, class0, arr_type[v], collection0);
                if(type7 != arr_type[v]) {
                    if(!z) {
                        arr_type = (Type[])arr_type.clone();
                        z = true;
                    }
                    arr_type[v] = type7;
                }
            }
            return z ? new ParameterizedTypeImpl(type6, ((ParameterizedType)type1).getRawType(), arr_type) : ((ParameterizedType)type1);
        }
        if(type1 instanceof WildcardType) {
            type1 = (WildcardType)type1;
            Type[] arr_type1 = ((WildcardType)type1).getLowerBounds();
            Type[] arr_type2 = ((WildcardType)type1).getUpperBounds();
            if(arr_type1.length == 1) {
                Type type8 = Util.resolve(type0, class0, arr_type1[0], collection0);
                if(type8 != arr_type1[0]) {
                    return Types.supertypeOf(type8);
                }
            }
            else if(arr_type2.length == 1) {
                Type type9 = Util.resolve(type0, class0, arr_type2[0], collection0);
                if(type9 != arr_type2[0]) {
                    return Types.subtypeOf(type9);
                }
            }
        }
        return type1;
    }

    static Type resolveTypeVariable(Type type0, Class class0, TypeVariable typeVariable0) {
        Class class1 = Util.declaringClassOf(typeVariable0);
        if(class1 != null) {
            Type type1 = Util.getGenericSupertype(type0, class0, class1);
            if(type1 instanceof ParameterizedType) {
                return ((ParameterizedType)type1).getActualTypeArguments()[Util.indexOf(class1.getTypeParameters(), typeVariable0)];
            }
        }
        return typeVariable0;
    }

    public static RuntimeException rethrowCause(InvocationTargetException invocationTargetException0) {
        Throwable throwable0 = invocationTargetException0.getTargetException();
        if(throwable0 instanceof RuntimeException) {
            throw (RuntimeException)throwable0;
        }
        if(!(throwable0 instanceof Error)) {
            throw new RuntimeException(throwable0);
        }
        throw (Error)throwable0;
    }

    // 去混淆评级： 低(20)
    public static String typeAnnotatedWithAnnotations(Type type0, Set set0) {
        return type0 + (set0.isEmpty() ? " (with no annotations)" : " annotated " + set0);
    }

    // 去混淆评级： 低(20)
    static String typeToString(Type type0) {
        return type0 instanceof Class ? ((Class)type0).getName() : type0.toString();
    }

    public static boolean typesMatch(Type type0, Type type1) {
        return Types.equals(type0, type1);
    }

    // 去混淆评级： 低(40)
    public static JsonDataException unexpectedNull(String s, String s1, JsonReader jsonReader0) {
        return s1.equals(s) ? new JsonDataException(String.format("Non-null value \'%s\' was null at %s", s, "$")) : new JsonDataException(String.format("Non-null value \'%s\' (JSON name \'%s\') was null at %s", s, s1, "$"));
    }
}

