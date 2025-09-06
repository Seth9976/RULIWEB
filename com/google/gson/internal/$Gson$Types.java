package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class .Gson.Types {
    static final class GenericArrayTypeImpl implements Serializable, GenericArrayType {
        private final Type componentType;
        private static final long serialVersionUID;

        public GenericArrayTypeImpl(Type type0) {
            this.componentType = .Gson.Types.canonicalize(type0);
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof GenericArrayType && .Gson.Types.equals(this, ((GenericArrayType)object0));
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
            return .Gson.Types.typeToString(this.componentType) + "[]";
        }
    }

    static final class ParameterizedTypeImpl implements Serializable, ParameterizedType {
        private final Type ownerType;
        private final Type rawType;
        private static final long serialVersionUID;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type type0, Type type1, Type[] arr_type) {
            boolean z = true;
            super();
            if(type1 instanceof Class) {
                if(type0 == null && (!Modifier.isStatic(((Class)type1).getModifiers()) && ((Class)type1).getEnclosingClass() != null)) {
                    z = false;
                }
                .Gson.Preconditions.checkArgument(z);
            }
            this.ownerType = type0 == null ? null : .Gson.Types.canonicalize(type0);
            this.rawType = .Gson.Types.canonicalize(type1);
            Type[] arr_type1 = (Type[])arr_type.clone();
            this.typeArguments = arr_type1;
            for(int v = 0; v < arr_type1.length; ++v) {
                .Gson.Preconditions.checkNotNull(this.typeArguments[v]);
                .Gson.Types.checkNotPrimitive(this.typeArguments[v]);
                this.typeArguments[v] = .Gson.Types.canonicalize(this.typeArguments[v]);
            }
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof ParameterizedType && .Gson.Types.equals(this, ((ParameterizedType)object0));
        }

        @Override
        public Type[] getActualTypeArguments() {
            return (Type[])this.typeArguments.clone();
        }

        @Override
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override
        public Type getRawType() {
            return this.rawType;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ .Gson.Types.hashCodeOrZero(this.ownerType);
        }

        @Override
        public String toString() {
            if(this.typeArguments.length == 0) {
                return .Gson.Types.typeToString(this.rawType);
            }
            StringBuilder stringBuilder0 = new StringBuilder((this.typeArguments.length + 1) * 30);
            stringBuilder0.append(.Gson.Types.typeToString(this.rawType));
            stringBuilder0.append("<");
            stringBuilder0.append(.Gson.Types.typeToString(this.typeArguments[0]));
            for(int v = 1; v < this.typeArguments.length; ++v) {
                stringBuilder0.append(", ");
                stringBuilder0.append(.Gson.Types.typeToString(this.typeArguments[v]));
            }
            stringBuilder0.append(">");
            return stringBuilder0.toString();
        }
    }

    static final class WildcardTypeImpl implements Serializable, WildcardType {
        private final Type lowerBound;
        private static final long serialVersionUID;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] arr_type, Type[] arr_type1) {
            boolean z = true;
            .Gson.Preconditions.checkArgument(arr_type1.length <= 1);
            .Gson.Preconditions.checkArgument(arr_type.length == 1);
            if(arr_type1.length == 1) {
                .Gson.Preconditions.checkNotNull(arr_type1[0]);
                .Gson.Types.checkNotPrimitive(arr_type1[0]);
                if(arr_type[0] != Object.class) {
                    z = false;
                }
                .Gson.Preconditions.checkArgument(z);
                this.lowerBound = .Gson.Types.canonicalize(arr_type1[0]);
                this.upperBound = Object.class;
                return;
            }
            .Gson.Preconditions.checkNotNull(arr_type[0]);
            .Gson.Types.checkNotPrimitive(arr_type[0]);
            this.lowerBound = null;
            this.upperBound = .Gson.Types.canonicalize(arr_type[0]);
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof WildcardType && .Gson.Types.equals(this, ((WildcardType)object0));
        }

        @Override
        public Type[] getLowerBounds() {
            return this.lowerBound == null ? .Gson.Types.EMPTY_TYPE_ARRAY : new Type[]{this.lowerBound};
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
                return "? super " + .Gson.Types.typeToString(this.lowerBound);
            }
            return this.upperBound == Object.class ? "?" : "? extends " + .Gson.Types.typeToString(this.upperBound);
        }
    }

    static final Type[] EMPTY_TYPE_ARRAY;

    static {
        .Gson.Types.EMPTY_TYPE_ARRAY = new Type[0];
    }

    private .Gson.Types() {
        throw new UnsupportedOperationException();
    }

    public static GenericArrayType arrayOf(Type type0) {
        return new GenericArrayTypeImpl(type0);
    }

    public static Type canonicalize(Type type0) {
        if(type0 instanceof Class) {
            return ((Class)type0).isArray() ? new GenericArrayTypeImpl(.Gson.Types.canonicalize(((Class)type0).getComponentType())) : ((Class)type0);
        }
        if(type0 instanceof ParameterizedType) {
            return new ParameterizedTypeImpl(((ParameterizedType)type0).getOwnerType(), ((ParameterizedType)type0).getRawType(), ((ParameterizedType)type0).getActualTypeArguments());
        }
        if(type0 instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(((GenericArrayType)type0).getGenericComponentType());
        }
        return type0 instanceof WildcardType ? new WildcardTypeImpl(((WildcardType)type0).getUpperBounds(), ((WildcardType)type0).getLowerBounds()) : type0;
    }

    static void checkNotPrimitive(Type type0) {
        .Gson.Preconditions.checkArgument(!(type0 instanceof Class) || !((Class)type0).isPrimitive());
    }

    private static Class declaringClassOf(TypeVariable typeVariable0) {
        GenericDeclaration genericDeclaration0 = typeVariable0.getGenericDeclaration();
        return genericDeclaration0 instanceof Class ? ((Class)genericDeclaration0) : null;
    }

    // 去混淆评级： 低(20)
    static boolean equal(Object object0, Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }

    public static boolean equals(Type type0, Type type1) {
        if(type0 == type1) {
            return true;
        }
        if(type0 instanceof Class) {
            return type0.equals(type1);
        }
        if(type0 instanceof ParameterizedType) {
            return type1 instanceof ParameterizedType ? .Gson.Types.equal(((ParameterizedType)type0).getOwnerType(), ((ParameterizedType)type1).getOwnerType()) && ((ParameterizedType)type0).getRawType().equals(((ParameterizedType)type1).getRawType()) && Arrays.equals(((ParameterizedType)type0).getActualTypeArguments(), ((ParameterizedType)type1).getActualTypeArguments()) : false;
        }
        if(type0 instanceof GenericArrayType) {
            return type1 instanceof GenericArrayType ? .Gson.Types.equals(((GenericArrayType)type0).getGenericComponentType(), ((GenericArrayType)type1).getGenericComponentType()) : false;
        }
        if(type0 instanceof WildcardType) {
            return type1 instanceof WildcardType ? Arrays.equals(((WildcardType)type0).getUpperBounds(), ((WildcardType)type1).getUpperBounds()) && Arrays.equals(((WildcardType)type0).getLowerBounds(), ((WildcardType)type1).getLowerBounds()) : false;
        }
        return type0 instanceof TypeVariable && type1 instanceof TypeVariable ? ((TypeVariable)type0).getGenericDeclaration() == ((TypeVariable)type1).getGenericDeclaration() && ((TypeVariable)type0).getName().equals(((TypeVariable)type1).getName()) : false;
    }

    public static Type getArrayComponentType(Type type0) {
        return type0 instanceof GenericArrayType ? ((GenericArrayType)type0).getGenericComponentType() : ((Class)type0).getComponentType();
    }

    public static Type getCollectionElementType(Type type0, Class class0) {
        Type type1 = .Gson.Types.getSupertype(type0, class0, Collection.class);
        if(type1 instanceof WildcardType) {
            type1 = ((WildcardType)type1).getUpperBounds()[0];
        }
        return type1 instanceof ParameterizedType ? ((ParameterizedType)type1).getActualTypeArguments()[0] : Object.class;
    }

    static Type getGenericSupertype(Type type0, Class class0, Class class1) {
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
                    return .Gson.Types.getGenericSupertype(class0.getGenericInterfaces()[v], arr_class[v], class1);
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
                    return .Gson.Types.getGenericSupertype(class0.getGenericSuperclass(), class3, class1);
                }
                class0 = class3;
            }
        }
        return class1;
    }

    public static Type[] getMapKeyAndValueTypes(Type type0, Class class0) {
        if(type0 == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type type1 = .Gson.Types.getSupertype(type0, class0, Map.class);
        return type1 instanceof ParameterizedType ? ((ParameterizedType)type1).getActualTypeArguments() : new Type[]{Object.class, Object.class};
    }

    public static Class getRawType(Type type0) {
        if(type0 instanceof Class) {
            return (Class)type0;
        }
        if(type0 instanceof ParameterizedType) {
            Type type1 = ((ParameterizedType)type0).getRawType();
            .Gson.Preconditions.checkArgument(type1 instanceof Class);
            return (Class)type1;
        }
        if(type0 instanceof GenericArrayType) {
            return Array.newInstance(.Gson.Types.getRawType(((GenericArrayType)type0).getGenericComponentType()), 0).getClass();
        }
        if(type0 instanceof TypeVariable) {
            return Object.class;
        }
        if(!(type0 instanceof WildcardType)) {
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type0 + "> is of type " + (type0 == null ? "null" : type0.getClass().getName()));
        }
        return .Gson.Types.getRawType(((WildcardType)type0).getUpperBounds()[0]);
    }

    static Type getSupertype(Type type0, Class class0, Class class1) {
        if(type0 instanceof WildcardType) {
            type0 = ((WildcardType)type0).getUpperBounds()[0];
        }
        .Gson.Preconditions.checkArgument(class1.isAssignableFrom(class0));
        return .Gson.Types.resolve(type0, class0, .Gson.Types.getGenericSupertype(type0, class0, class1));
    }

    static int hashCodeOrZero(Object object0) {
        return object0 == null ? 0 : object0.hashCode();
    }

    private static int indexOf(Object[] arr_object, Object object0) {
        for(int v = 0; v < arr_object.length; ++v) {
            if(object0.equals(arr_object[v])) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type0, Type type1, Type[] arr_type) {
        return new ParameterizedTypeImpl(type0, type1, arr_type);
    }

    public static Type resolve(Type type0, Class class0, Type type1) {
        return .Gson.Types.resolve(type0, class0, type1, new HashMap());
    }

    private static Type resolve(Type type0, Class class0, Type type1, Map map0) {
        TypeVariable typeVariable0 = null;
        do {
            if(!(type1 instanceof TypeVariable)) {
                goto label_13;
            }
            TypeVariable typeVariable1 = (TypeVariable)type1;
            Type type2 = (Type)map0.get(typeVariable1);
            if(type2 != null) {
                return type2 == Void.TYPE ? type1 : type2;
            }
            map0.put(typeVariable1, Void.TYPE);
            if(typeVariable0 == null) {
                typeVariable0 = typeVariable1;
            }
            type1 = .Gson.Types.resolveTypeVariable(type0, class0, typeVariable1);
        }
        while(type1 != typeVariable1);
        goto label_60;
    label_13:
        if(type1 instanceof Class && ((Class)type1).isArray()) {
            Class class1 = ((Class)type1).getComponentType();
            Type type3 = .Gson.Types.resolve(type0, class0, class1, map0);
            type1 = .Gson.Types.equal(class1, type3) ? ((Class)type1) : .Gson.Types.arrayOf(type3);
        }
        else if(type1 instanceof GenericArrayType) {
            type1 = (GenericArrayType)type1;
            Type type4 = ((GenericArrayType)type1).getGenericComponentType();
            Type type5 = .Gson.Types.resolve(type0, class0, type4, map0);
            if(!.Gson.Types.equal(type4, type5)) {
                type1 = .Gson.Types.arrayOf(type5);
            }
        }
        else if(type1 instanceof ParameterizedType) {
            type1 = (ParameterizedType)type1;
            Type type6 = ((ParameterizedType)type1).getOwnerType();
            Type type7 = .Gson.Types.resolve(type0, class0, type6, map0);
            int v1 = !.Gson.Types.equal(type7, type6);
            Type[] arr_type = ((ParameterizedType)type1).getActualTypeArguments();
            int v2 = arr_type.length;
            for(int v = 0; v < v2; ++v) {
                Type type8 = .Gson.Types.resolve(type0, class0, arr_type[v], map0);
                if(!.Gson.Types.equal(type8, arr_type[v])) {
                    if(v1 == 0) {
                        arr_type = (Type[])arr_type.clone();
                        v1 = 1;
                    }
                    arr_type[v] = type8;
                }
            }
            if(v1 != 0) {
                type1 = .Gson.Types.newParameterizedTypeWithOwner(type7, ((ParameterizedType)type1).getRawType(), arr_type);
            }
        }
        else if(type1 instanceof WildcardType) {
            type1 = (WildcardType)type1;
            Type[] arr_type1 = ((WildcardType)type1).getLowerBounds();
            Type[] arr_type2 = ((WildcardType)type1).getUpperBounds();
            if(arr_type1.length == 1) {
                Type type9 = .Gson.Types.resolve(type0, class0, arr_type1[0], map0);
                if(type9 != arr_type1[0]) {
                    type1 = .Gson.Types.supertypeOf(type9);
                }
            }
            else if(arr_type2.length == 1) {
                Type type10 = .Gson.Types.resolve(type0, class0, arr_type2[0], map0);
                if(type10 != arr_type2[0]) {
                    type1 = .Gson.Types.subtypeOf(type10);
                }
            }
        }
    label_60:
        if(typeVariable0 != null) {
            map0.put(typeVariable0, type1);
        }
        return type1;
    }

    static Type resolveTypeVariable(Type type0, Class class0, TypeVariable typeVariable0) {
        Class class1 = .Gson.Types.declaringClassOf(typeVariable0);
        if(class1 != null) {
            Type type1 = .Gson.Types.getGenericSupertype(type0, class0, class1);
            if(type1 instanceof ParameterizedType) {
                return ((ParameterizedType)type1).getActualTypeArguments()[.Gson.Types.indexOf(class1.getTypeParameters(), typeVariable0)];
            }
        }
        return typeVariable0;
    }

    // 去混淆评级： 低(20)
    public static WildcardType subtypeOf(Type type0) {
        return type0 instanceof WildcardType ? new WildcardTypeImpl(((WildcardType)type0).getUpperBounds(), .Gson.Types.EMPTY_TYPE_ARRAY) : new WildcardTypeImpl(new Type[]{type0}, .Gson.Types.EMPTY_TYPE_ARRAY);
    }

    public static WildcardType supertypeOf(Type type0) {
        if(type0 instanceof WildcardType) {
            Type[] arr_type = ((WildcardType)type0).getLowerBounds();
            return new WildcardTypeImpl(new Type[]{Object.class}, arr_type);
        }
        return new WildcardTypeImpl(new Type[]{Object.class}, new Type[]{type0});
    }

    // 去混淆评级： 低(20)
    public static String typeToString(Type type0) {
        return type0 instanceof Class ? ((Class)type0).getName() : type0.toString();
    }
}

