package com.squareup.moshi;

import com.squareup.moshi.internal.Util.GenericArrayTypeImpl;
import com.squareup.moshi.internal.Util.ParameterizedTypeImpl;
import com.squareup.moshi.internal.Util.WildcardTypeImpl;
import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
public final class Types {
    static Type arrayComponentType(Type type0) {
        if(type0 instanceof GenericArrayType) {
            return ((GenericArrayType)type0).getGenericComponentType();
        }
        return type0 instanceof Class ? ((Class)type0).getComponentType() : null;
    }

    public static GenericArrayType arrayOf(Type type0) {
        return new GenericArrayTypeImpl(type0);
    }

    public static Type collectionElementType(Type type0, Class class0) {
        Type type1 = Types.getSupertype(type0, class0, Collection.class);
        if(type1 instanceof WildcardType) {
            type1 = ((WildcardType)type1).getUpperBounds()[0];
        }
        return type1 instanceof ParameterizedType ? ((ParameterizedType)type1).getActualTypeArguments()[0] : Object.class;
    }

    static Annotation createJsonQualifierImplementation(Class class0) {
        if(!class0.isAnnotation()) {
            throw new IllegalArgumentException(class0 + " must be an annotation.");
        }
        if(!class0.isAnnotationPresent(JsonQualifier.class)) {
            throw new IllegalArgumentException(class0 + " must have @JsonQualifier.");
        }
        if(class0.getDeclaredMethods().length != 0) {
            throw new IllegalArgumentException(class0 + " must not declare methods.");
        }
        return (Annotation)Proxy.newProxyInstance(class0.getClassLoader(), new Class[]{class0}, new InvocationHandler() {
            @Override
            public Object invoke(Object object0, Method method0, Object[] arr_object) throws Throwable {
                String s = method0.getName();
                s.hashCode();
                switch(s) {
                    case "annotationType": {
                        return class0;
                    }
                    case "equals": {
                        return Boolean.valueOf(class0.isInstance(arr_object[0]));
                    }
                    case "hashCode": {
                        return 0;
                    }
                    case "toString": {
                        return "@" + class0.getName() + "()";
                    }
                    default: {
                        return method0.invoke(object0, arr_object);
                    }
                }
            }
        });
    }

    public static boolean equals(@Nullable Type type0, @Nullable Type type1) {
        if(type0 == type1) {
            return true;
        }
        if(type0 instanceof Class) {
            return type1 instanceof GenericArrayType ? Types.equals(((Class)type0).getComponentType(), ((GenericArrayType)type1).getGenericComponentType()) : type0.equals(type1);
        }
        if(type0 instanceof ParameterizedType) {
            if(!(type1 instanceof ParameterizedType)) {
                return false;
            }
            Type[] arr_type = ((ParameterizedType)type0) instanceof ParameterizedTypeImpl ? ((ParameterizedTypeImpl)(((ParameterizedType)type0))).typeArguments : ((ParameterizedType)type0).getActualTypeArguments();
            Type[] arr_type1 = ((ParameterizedType)type1) instanceof ParameterizedTypeImpl ? ((ParameterizedTypeImpl)(((ParameterizedType)type1))).typeArguments : ((ParameterizedType)type1).getActualTypeArguments();
            return Types.equals(((ParameterizedType)type0).getOwnerType(), ((ParameterizedType)type1).getOwnerType()) && ((ParameterizedType)type0).getRawType().equals(((ParameterizedType)type1).getRawType()) && Arrays.equals(arr_type, arr_type1);
        }
        if(type0 instanceof GenericArrayType) {
            if(type1 instanceof Class) {
                return Types.equals(((Class)type1).getComponentType(), ((GenericArrayType)type0).getGenericComponentType());
            }
            return type1 instanceof GenericArrayType ? Types.equals(((GenericArrayType)type0).getGenericComponentType(), ((GenericArrayType)type1).getGenericComponentType()) : false;
        }
        if(type0 instanceof WildcardType) {
            return type1 instanceof WildcardType ? Arrays.equals(((WildcardType)type0).getUpperBounds(), ((WildcardType)type1).getUpperBounds()) && Arrays.equals(((WildcardType)type0).getLowerBounds(), ((WildcardType)type1).getLowerBounds()) : false;
        }
        return type0 instanceof TypeVariable && type1 instanceof TypeVariable ? ((TypeVariable)type0).getGenericDeclaration() == ((TypeVariable)type1).getGenericDeclaration() && ((TypeVariable)type0).getName().equals(((TypeVariable)type1).getName()) : false;
    }

    public static String generatedJsonAdapterName(Class class0) {
        if(class0.getAnnotation(JsonClass.class) == null) {
            throw new IllegalArgumentException("Class does not have a JsonClass annotation: " + class0);
        }
        return Types.generatedJsonAdapterName(class0.getName());
    }

    public static String generatedJsonAdapterName(String s) {
        return s.replace("$", "_") + "JsonAdapter";
    }

    public static Set getFieldJsonQualifierAnnotations(Class class0, String s) {
        try {
            Field field0 = class0.getDeclaredField(s);
            field0.setAccessible(true);
            Annotation[] arr_annotation = field0.getDeclaredAnnotations();
            LinkedHashSet linkedHashSet0 = new LinkedHashSet(arr_annotation.length);
            for(int v = 0; v < arr_annotation.length; ++v) {
                Annotation annotation0 = arr_annotation[v];
                if(annotation0.annotationType().isAnnotationPresent(JsonQualifier.class)) {
                    linkedHashSet0.add(annotation0);
                }
            }
            return Collections.unmodifiableSet(linkedHashSet0);
        }
        catch(NoSuchFieldException noSuchFieldException0) {
            throw new IllegalArgumentException("Could not access field " + s + " on class " + class0.getCanonicalName(), noSuchFieldException0);
        }
    }

    static Type getGenericSuperclass(Type type0) {
        Class class0 = Types.getRawType(type0);
        return Util.resolve(type0, class0, class0.getGenericSuperclass());
    }

    public static Class getRawType(Type type0) {
        if(type0 instanceof Class) {
            return (Class)type0;
        }
        if(type0 instanceof ParameterizedType) {
            return (Class)((ParameterizedType)type0).getRawType();
        }
        if(type0 instanceof GenericArrayType) {
            return Array.newInstance(Types.getRawType(((GenericArrayType)type0).getGenericComponentType()), 0).getClass();
        }
        if(type0 instanceof TypeVariable) {
            return Object.class;
        }
        if(!(type0 instanceof WildcardType)) {
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type0 + "> is of type " + (type0 == null ? "null" : type0.getClass().getName()));
        }
        return Types.getRawType(((WildcardType)type0).getUpperBounds()[0]);
    }

    static Type getSupertype(Type type0, Class class0, Class class1) {
        if(!class1.isAssignableFrom(class0)) {
            throw new IllegalArgumentException();
        }
        return Util.resolve(type0, class0, Util.getGenericSupertype(type0, class0, class1));
    }

    static Type[] mapKeyAndValueTypes(Type type0, Class class0) {
        if(type0 == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type type1 = Types.getSupertype(type0, class0, Map.class);
        return type1 instanceof ParameterizedType ? ((ParameterizedType)type1).getActualTypeArguments() : new Type[]{Object.class, Object.class};
    }

    public static ParameterizedType newParameterizedType(Type type0, Type[] arr_type) {
        if(arr_type.length == 0) {
            throw new IllegalArgumentException("Missing type arguments for " + type0);
        }
        return new ParameterizedTypeImpl(null, type0, arr_type);
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type0, Type type1, Type[] arr_type) {
        if(arr_type.length == 0) {
            throw new IllegalArgumentException("Missing type arguments for " + type1);
        }
        return new ParameterizedTypeImpl(type0, type1, arr_type);
    }

    @Nullable
    public static Set nextAnnotations(Set set0, Class class0) {
        if(!class0.isAnnotationPresent(JsonQualifier.class)) {
            throw new IllegalArgumentException(class0 + " is not a JsonQualifier.");
        }
        if(set0.isEmpty()) {
            return null;
        }
        for(Object object0: set0) {
            Annotation annotation0 = (Annotation)object0;
            if(class0.equals(annotation0.annotationType())) {
                LinkedHashSet linkedHashSet0 = new LinkedHashSet(set0);
                linkedHashSet0.remove(annotation0);
                return Collections.unmodifiableSet(linkedHashSet0);
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    // 去混淆评级： 低(20)
    public static WildcardType subtypeOf(Type type0) {
        return type0 instanceof WildcardType ? new WildcardTypeImpl(((WildcardType)type0).getUpperBounds(), Util.EMPTY_TYPE_ARRAY) : new WildcardTypeImpl(new Type[]{type0}, Util.EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type type0) {
        if(type0 instanceof WildcardType) {
            Type[] arr_type = ((WildcardType)type0).getLowerBounds();
            return new WildcardTypeImpl(new Type[]{Object.class}, arr_type);
        }
        return new WildcardTypeImpl(new Type[]{Object.class}, new Type[]{type0});
    }
}

