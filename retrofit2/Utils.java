package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.ResponseBody;
import okio.Buffer;

final class Utils {
    static final class GenericArrayTypeImpl implements GenericArrayType {
        private final Type componentType;

        GenericArrayTypeImpl(Type type0) {
            this.componentType = type0;
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof GenericArrayType && Utils.equals(this, ((GenericArrayType)object0));
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
            return Utils.typeToString(this.componentType) + "[]";
        }
    }

    static final class ParameterizedTypeImpl implements ParameterizedType {
        @Nullable
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        ParameterizedTypeImpl(@Nullable Type type0, Type type1, Type[] arr_type) {
            int v = 1;
            super();
            if(type1 instanceof Class) {
                if(((Class)type1).getEnclosingClass() != null) {
                    v = 0;
                }
                if((type0 == null ? 1 : 0) != v) {
                    throw new IllegalArgumentException();
                }
            }
            for(int v1 = 0; v1 < arr_type.length; ++v1) {
                Type type2 = arr_type[v1];
                Objects.requireNonNull(type2, "typeArgument == null");
                Utils.checkNotPrimitive(type2);
            }
            this.ownerType = type0;
            this.rawType = type1;
            this.typeArguments = (Type[])arr_type.clone();
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof ParameterizedType && Utils.equals(this, ((ParameterizedType)object0));
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
            int v = Arrays.hashCode(this.typeArguments);
            int v1 = this.rawType.hashCode();
            return this.ownerType == null ? v ^ v1 : v ^ v1 ^ this.ownerType.hashCode();
        }

        @Override
        public String toString() {
            if(this.typeArguments.length == 0) {
                return Utils.typeToString(this.rawType);
            }
            StringBuilder stringBuilder0 = new StringBuilder((this.typeArguments.length + 1) * 30);
            stringBuilder0.append(Utils.typeToString(this.rawType));
            stringBuilder0.append("<");
            stringBuilder0.append(Utils.typeToString(this.typeArguments[0]));
            for(int v = 1; v < this.typeArguments.length; ++v) {
                stringBuilder0.append(", ");
                stringBuilder0.append(Utils.typeToString(this.typeArguments[v]));
            }
            stringBuilder0.append(">");
            return stringBuilder0.toString();
        }
    }

    static final class WildcardTypeImpl implements WildcardType {
        @Nullable
        private final Type lowerBound;
        private final Type upperBound;

        WildcardTypeImpl(Type[] arr_type, Type[] arr_type1) {
            if(arr_type1.length > 1 || arr_type.length != 1) {
                throw new IllegalArgumentException();
            }
            if(arr_type1.length == 1) {
                arr_type1[0].getClass();
                Utils.checkNotPrimitive(arr_type1[0]);
                if(arr_type[0] != Object.class) {
                    throw new IllegalArgumentException();
                }
                this.lowerBound = arr_type1[0];
                this.upperBound = Object.class;
                return;
            }
            arr_type[0].getClass();
            Utils.checkNotPrimitive(arr_type[0]);
            this.lowerBound = null;
            this.upperBound = arr_type[0];
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof WildcardType && Utils.equals(this, ((WildcardType)object0));
        }

        @Override
        public Type[] getLowerBounds() {
            return this.lowerBound == null ? Utils.EMPTY_TYPE_ARRAY : new Type[]{this.lowerBound};
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
                return "? super " + Utils.typeToString(this.lowerBound);
            }
            return this.upperBound == Object.class ? "?" : "? extends " + Utils.typeToString(this.upperBound);
        }
    }

    static final Type[] EMPTY_TYPE_ARRAY;

    static {
        Utils.EMPTY_TYPE_ARRAY = new Type[0];
    }

    static ResponseBody buffer(ResponseBody responseBody0) throws IOException {
        Buffer buffer0 = new Buffer();
        responseBody0.source().readAll(buffer0);
        return ResponseBody.create(responseBody0.contentType(), responseBody0.contentLength(), buffer0);
    }

    static void checkNotPrimitive(Type type0) {
        if(type0 instanceof Class && ((Class)type0).isPrimitive()) {
            throw new IllegalArgumentException();
        }
    }

    @Nullable
    private static Class declaringClassOf(TypeVariable typeVariable0) {
        GenericDeclaration genericDeclaration0 = typeVariable0.getGenericDeclaration();
        return genericDeclaration0 instanceof Class ? ((Class)genericDeclaration0) : null;
    }

    static boolean equals(Type type0, Type type1) {
        if(type0 == type1) {
            return true;
        }
        if(type0 instanceof Class) {
            return type0.equals(type1);
        }
        if(type0 instanceof ParameterizedType) {
            if(!(type1 instanceof ParameterizedType)) {
                return false;
            }
            Type type2 = ((ParameterizedType)type0).getOwnerType();
            Type type3 = ((ParameterizedType)type1).getOwnerType();
            return (type2 == type3 || type2 != null && type2.equals(type3)) && ((ParameterizedType)type0).getRawType().equals(((ParameterizedType)type1).getRawType()) && Arrays.equals(((ParameterizedType)type0).getActualTypeArguments(), ((ParameterizedType)type1).getActualTypeArguments());
        }
        if(type0 instanceof GenericArrayType) {
            return type1 instanceof GenericArrayType ? Utils.equals(((GenericArrayType)type0).getGenericComponentType(), ((GenericArrayType)type1).getGenericComponentType()) : false;
        }
        if(type0 instanceof WildcardType) {
            return type1 instanceof WildcardType ? Arrays.equals(((WildcardType)type0).getUpperBounds(), ((WildcardType)type1).getUpperBounds()) && Arrays.equals(((WildcardType)type0).getLowerBounds(), ((WildcardType)type1).getLowerBounds()) : false;
        }
        return type0 instanceof TypeVariable && type1 instanceof TypeVariable ? ((TypeVariable)type0).getGenericDeclaration() == ((TypeVariable)type1).getGenericDeclaration() && ((TypeVariable)type0).getName().equals(((TypeVariable)type1).getName()) : false;
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
                    return Utils.getGenericSupertype(class0.getGenericInterfaces()[v], arr_class[v], class1);
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
                    return Utils.getGenericSupertype(class0.getGenericSuperclass(), class3, class1);
                }
                class0 = class3;
            }
        }
        return class1;
    }

    static Type getParameterLowerBound(int v, ParameterizedType parameterizedType0) {
        Type type0 = parameterizedType0.getActualTypeArguments()[v];
        return type0 instanceof WildcardType ? ((WildcardType)type0).getLowerBounds()[0] : type0;
    }

    static Type getParameterUpperBound(int v, ParameterizedType parameterizedType0) {
        Type[] arr_type = parameterizedType0.getActualTypeArguments();
        if(v < 0 || v >= arr_type.length) {
            throw new IllegalArgumentException("Index " + v + " not in range [0," + arr_type.length + ") for " + parameterizedType0);
        }
        Type type0 = arr_type[v];
        return type0 instanceof WildcardType ? ((WildcardType)type0).getUpperBounds()[0] : type0;
    }

    static Class getRawType(Type type0) {
        Objects.requireNonNull(type0, "type == null");
        if(type0 instanceof Class) {
            return (Class)type0;
        }
        if(type0 instanceof ParameterizedType) {
            Type type1 = ((ParameterizedType)type0).getRawType();
            if(!(type1 instanceof Class)) {
                throw new IllegalArgumentException();
            }
            return (Class)type1;
        }
        if(type0 instanceof GenericArrayType) {
            return Array.newInstance(Utils.getRawType(((GenericArrayType)type0).getGenericComponentType()), 0).getClass();
        }
        if(type0 instanceof TypeVariable) {
            return Object.class;
        }
        if(!(type0 instanceof WildcardType)) {
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type0 + "> is of type " + type0.getClass().getName());
        }
        return Utils.getRawType(((WildcardType)type0).getUpperBounds()[0]);
    }

    static Type getSupertype(Type type0, Class class0, Class class1) {
        if(!class1.isAssignableFrom(class0)) {
            throw new IllegalArgumentException();
        }
        return Utils.resolve(type0, class0, Utils.getGenericSupertype(type0, class0, class1));
    }

    static boolean hasUnresolvableType(@Nullable Type type0) {
        if(type0 instanceof Class) {
            return false;
        }
        if(type0 instanceof ParameterizedType) {
            Type[] arr_type = ((ParameterizedType)type0).getActualTypeArguments();
            for(int v = 0; v < arr_type.length; ++v) {
                if(Utils.hasUnresolvableType(arr_type[v])) {
                    return true;
                }
            }
            return false;
        }
        if(type0 instanceof GenericArrayType) {
            return Utils.hasUnresolvableType(((GenericArrayType)type0).getGenericComponentType());
        }
        if(type0 instanceof TypeVariable) {
            return true;
        }
        if(!(type0 instanceof WildcardType)) {
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type0 + "> is of type " + (type0 == null ? "null" : type0.getClass().getName()));
        }
        return true;
    }

    private static int indexOf(Object[] arr_object, Object object0) {
        for(int v = 0; v < arr_object.length; ++v) {
            if(object0.equals(arr_object[v])) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }

    static boolean isAnnotationPresent(Annotation[] arr_annotation, Class class0) {
        for(int v = 0; v < arr_annotation.length; ++v) {
            if(class0.isInstance(arr_annotation[v])) {
                return true;
            }
        }
        return false;
    }

    static RuntimeException methodError(Method method0, String s, Object[] arr_object) {
        return Utils.methodError(method0, null, s, arr_object);
    }

    static RuntimeException methodError(Method method0, @Nullable Throwable throwable0, String s, Object[] arr_object) {
        return new IllegalArgumentException(String.format(s, arr_object) + "\n    for method " + method0.getDeclaringClass().getSimpleName() + "." + method0.getName(), throwable0);
    }

    static RuntimeException parameterError(Method method0, int v, String s, Object[] arr_object) {
        return Utils.methodError(method0, s + " (parameter #" + (v + 1) + ")", arr_object);
    }

    static RuntimeException parameterError(Method method0, Throwable throwable0, int v, String s, Object[] arr_object) {
        return Utils.methodError(method0, throwable0, s + " (parameter #" + (v + 1) + ")", arr_object);
    }

    static Type resolve(Type type0, Class class0, Type type1) {
        while(type1 instanceof TypeVariable) {
            Type type2 = Utils.resolveTypeVariable(type0, class0, ((TypeVariable)type1));
            if(type2 == ((TypeVariable)type1)) {
                return type2;
            }
            type1 = type2;
        }
        if(type1 instanceof Class && ((Class)type1).isArray()) {
            Class class1 = ((Class)type1).getComponentType();
            Type type3 = Utils.resolve(type0, class0, class1);
            return class1 == type3 ? ((Class)type1) : new GenericArrayTypeImpl(type3);
        }
        if(type1 instanceof GenericArrayType) {
            Type type4 = ((GenericArrayType)type1).getGenericComponentType();
            Type type5 = Utils.resolve(type0, class0, type4);
            return type4 == type5 ? ((GenericArrayType)type1) : new GenericArrayTypeImpl(type5);
        }
        if(type1 instanceof ParameterizedType) {
            Type type6 = ((ParameterizedType)type1).getOwnerType();
            Type type7 = Utils.resolve(type0, class0, type6);
            boolean z = type7 != type6;
            Type[] arr_type = ((ParameterizedType)type1).getActualTypeArguments();
            int v1 = arr_type.length;
            for(int v = 0; v < v1; ++v) {
                Type type8 = Utils.resolve(type0, class0, arr_type[v]);
                if(type8 != arr_type[v]) {
                    if(!z) {
                        arr_type = (Type[])arr_type.clone();
                        z = true;
                    }
                    arr_type[v] = type8;
                }
            }
            return z ? new ParameterizedTypeImpl(type7, ((ParameterizedType)type1).getRawType(), arr_type) : ((ParameterizedType)type1);
        }
        if(type1 instanceof WildcardType) {
            type1 = (WildcardType)type1;
            Type[] arr_type1 = ((WildcardType)type1).getLowerBounds();
            Type[] arr_type2 = ((WildcardType)type1).getUpperBounds();
            if(arr_type1.length == 1) {
                Type type9 = Utils.resolve(type0, class0, arr_type1[0]);
                if(type9 != arr_type1[0]) {
                    return new WildcardTypeImpl(new Type[]{Object.class}, new Type[]{type9});
                }
            }
            else if(arr_type2.length == 1) {
                Type type10 = Utils.resolve(type0, class0, arr_type2[0]);
                if(type10 != arr_type2[0]) {
                    return new WildcardTypeImpl(new Type[]{type10}, Utils.EMPTY_TYPE_ARRAY);
                }
            }
        }
        return type1;
    }

    private static Type resolveTypeVariable(Type type0, Class class0, TypeVariable typeVariable0) {
        Class class1 = Utils.declaringClassOf(typeVariable0);
        if(class1 != null) {
            Type type1 = Utils.getGenericSupertype(type0, class0, class1);
            if(type1 instanceof ParameterizedType) {
                return ((ParameterizedType)type1).getActualTypeArguments()[Utils.indexOf(class1.getTypeParameters(), typeVariable0)];
            }
        }
        return typeVariable0;
    }

    static void throwIfFatal(Throwable throwable0) {
        if(throwable0 instanceof VirtualMachineError) {
            throw (VirtualMachineError)throwable0;
        }
        if(throwable0 instanceof ThreadDeath) {
            throw (ThreadDeath)throwable0;
        }
        if(throwable0 instanceof LinkageError) {
            throw (LinkageError)throwable0;
        }
    }

    // 去混淆评级： 低(20)
    static String typeToString(Type type0) {
        return type0 instanceof Class ? ((Class)type0).getName() : type0.toString();
    }
}

