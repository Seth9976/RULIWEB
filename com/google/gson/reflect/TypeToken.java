package com.google.gson.reflect;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal..Gson.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken {
    final int hashCode;
    final Class rawType;
    final Type type;

    protected TypeToken() {
        Type type0 = TypeToken.getSuperclassTypeParameter(this.getClass());
        this.type = type0;
        this.rawType = .Gson.Types.getRawType(type0);
        this.hashCode = type0.hashCode();
    }

    TypeToken(Type type0) {
        Type type1 = .Gson.Types.canonicalize(((Type).Gson.Preconditions.checkNotNull(type0)));
        this.type = type1;
        this.rawType = .Gson.Types.getRawType(type1);
        this.hashCode = type1.hashCode();
    }

    private static AssertionError buildUnexpectedTypeError(Type type0, Class[] arr_class) {
        StringBuilder stringBuilder0 = new StringBuilder("Unexpected type. Expected one of: ");
        for(int v = 0; v < arr_class.length; ++v) {
            stringBuilder0.append(arr_class[v].getName());
            stringBuilder0.append(", ");
        }
        stringBuilder0.append("but got: ");
        stringBuilder0.append(type0.getClass().getName());
        stringBuilder0.append(", for type token: ");
        stringBuilder0.append(type0.toString());
        stringBuilder0.append('.');
        return new AssertionError(stringBuilder0.toString());
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof TypeToken && .Gson.Types.equals(this.type, ((TypeToken)object0).type);
    }

    public static TypeToken get(Class class0) {
        return new TypeToken(class0);
    }

    public static TypeToken get(Type type0) {
        return new TypeToken(type0);
    }

    public static TypeToken getArray(Type type0) {
        return new TypeToken(.Gson.Types.arrayOf(type0));
    }

    public static TypeToken getParameterized(Type type0, Type[] arr_type) {
        return new TypeToken(.Gson.Types.newParameterizedTypeWithOwner(null, type0, arr_type));
    }

    public final Class getRawType() {
        return this.rawType;
    }

    static Type getSuperclassTypeParameter(Class class0) {
        Type type0 = class0.getGenericSuperclass();
        if(type0 instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return .Gson.Types.canonicalize(((ParameterizedType)type0).getActualTypeArguments()[0]);
    }

    public final Type getType() {
        return this.type;
    }

    @Override
    public final int hashCode() {
        return this.hashCode;
    }

    private static boolean isAssignableFrom(Type type0, GenericArrayType genericArrayType0) {
        Type type1 = genericArrayType0.getGenericComponentType();
        if(type1 instanceof ParameterizedType) {
            if(type0 instanceof GenericArrayType) {
                return TypeToken.isAssignableFrom(((GenericArrayType)type0).getGenericComponentType(), ((ParameterizedType)type1), new HashMap());
            }
            if(type0 instanceof Class) {
                for(type0 = (Class)type0; ((Class)type0).isArray(); type0 = ((Class)type0).getComponentType()) {
                }
            }
            return TypeToken.isAssignableFrom(type0, ((ParameterizedType)type1), new HashMap());
        }
        return true;
    }

    private static boolean isAssignableFrom(Type type0, ParameterizedType parameterizedType0, Map map0) {
        if(type0 == null) {
            return false;
        }
        if(parameterizedType0.equals(type0)) {
            return true;
        }
        Class class0 = .Gson.Types.getRawType(type0);
        ParameterizedType parameterizedType1 = type0 instanceof ParameterizedType ? ((ParameterizedType)type0) : null;
        if(parameterizedType1 != null) {
            Type[] arr_type = parameterizedType1.getActualTypeArguments();
            TypeVariable[] arr_typeVariable = class0.getTypeParameters();
            for(int v1 = 0; v1 < arr_type.length; ++v1) {
                Type type1 = arr_type[v1];
                TypeVariable typeVariable0 = arr_typeVariable[v1];
                while(type1 instanceof TypeVariable) {
                    type1 = (Type)map0.get(((TypeVariable)type1).getName());
                }
                map0.put(typeVariable0.getName(), type1);
            }
            if(TypeToken.typeEquals(parameterizedType1, parameterizedType0, map0)) {
                return true;
            }
        }
        Type[] arr_type1 = class0.getGenericInterfaces();
        for(int v = 0; v < arr_type1.length; ++v) {
            if(TypeToken.isAssignableFrom(arr_type1[v], parameterizedType0, new HashMap(map0))) {
                return true;
            }
        }
        return TypeToken.isAssignableFrom(class0.getGenericSuperclass(), parameterizedType0, new HashMap(map0));
    }

    @Deprecated
    public boolean isAssignableFrom(TypeToken typeToken0) {
        return this.isAssignableFrom(typeToken0.getType());
    }

    @Deprecated
    public boolean isAssignableFrom(Class class0) {
        return this.isAssignableFrom(class0);
    }

    @Deprecated
    public boolean isAssignableFrom(Type type0) {
        if(type0 == null) {
            return false;
        }
        if(this.type.equals(type0)) {
            return true;
        }
        Type type1 = this.type;
        if(type1 instanceof Class) {
            Class class0 = .Gson.Types.getRawType(type0);
            return this.rawType.isAssignableFrom(class0);
        }
        if(type1 instanceof ParameterizedType) {
            return TypeToken.isAssignableFrom(type0, ((ParameterizedType)type1), new HashMap());
        }
        if(!(type1 instanceof GenericArrayType)) {
            throw TypeToken.buildUnexpectedTypeError(type1, new Class[]{Class.class, ParameterizedType.class, GenericArrayType.class});
        }
        Class class1 = .Gson.Types.getRawType(type0);
        return this.rawType.isAssignableFrom(class1) && TypeToken.isAssignableFrom(type0, ((GenericArrayType)this.type));
    }

    // 去混淆评级： 低(30)
    private static boolean matches(Type type0, Type type1, Map map0) {
        return type1.equals(type0) || type0 instanceof TypeVariable && type1.equals(map0.get(((TypeVariable)type0).getName()));
    }

    @Override
    public final String toString() {
        return .Gson.Types.typeToString(this.type);
    }

    private static boolean typeEquals(ParameterizedType parameterizedType0, ParameterizedType parameterizedType1, Map map0) {
        if(parameterizedType0.getRawType().equals(parameterizedType1.getRawType())) {
            Type[] arr_type = parameterizedType0.getActualTypeArguments();
            Type[] arr_type1 = parameterizedType1.getActualTypeArguments();
            for(int v = 0; v < arr_type.length; ++v) {
                if(!TypeToken.matches(arr_type[v], arr_type1[v], map0)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

