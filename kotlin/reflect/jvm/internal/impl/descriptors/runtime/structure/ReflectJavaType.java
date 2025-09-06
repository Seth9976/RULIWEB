package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public abstract class ReflectJavaType implements JavaType {
    public static final class Factory {
        private Factory() {
        }

        public Factory(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ReflectJavaType create(Type type0) {
            Intrinsics.checkNotNullParameter(type0, "type");
            if(type0 instanceof Class && ((Class)type0).isPrimitive()) {
                return new ReflectJavaPrimitiveType(((Class)type0));
            }
            if(!(type0 instanceof GenericArrayType) && (!(type0 instanceof Class) || !((Class)type0).isArray())) {
                return type0 instanceof WildcardType ? new ReflectJavaWildcardType(((WildcardType)type0)) : new ReflectJavaClassifierType(type0);
            }
            return new ReflectJavaArrayType(type0);
        }
    }

    public static final Factory Factory;

    static {
        ReflectJavaType.Factory = new Factory(null);
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ReflectJavaType && Intrinsics.areEqual(this.getReflectType(), ((ReflectJavaType)object0).getReflectType());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner
    public JavaAnnotation findAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Iterator iterator0 = this.getAnnotations().iterator();
        while(true) {
            FqName fqName1 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            ClassId classId0 = ((JavaAnnotation)object0).getClassId();
            if(classId0 != null) {
                fqName1 = classId0.asSingleFqName();
            }
            if(Intrinsics.areEqual(fqName1, fqName0)) {
                return (JavaAnnotation)object0;
            }
        }
        return null;
    }

    protected abstract Type getReflectType();

    @Override
    public int hashCode() {
        return this.getReflectType().hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.getReflectType();
    }
}

