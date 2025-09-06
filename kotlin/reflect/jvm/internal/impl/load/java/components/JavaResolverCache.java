package kotlin.reflect.jvm.internal.impl.load.java.components;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface JavaResolverCache {
    public static final JavaResolverCache EMPTY;

    static {
        JavaResolverCache.EMPTY = new JavaResolverCache() {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                switch(v) {
                    case 1: {
                        arr_object[0] = "member";
                        break;
                    }
                    case 3: {
                        arr_object[0] = "element";
                        break;
                    }
                    case 5: {
                        arr_object[0] = "field";
                        break;
                    }
                    case 7: {
                        arr_object[0] = "javaClass";
                        break;
                    }
                    case 2: 
                    case 4: 
                    case 6: 
                    case 8: {
                        arr_object[0] = "descriptor";
                        break;
                    }
                    default: {
                        arr_object[0] = "fqName";
                    }
                }
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/JavaResolverCache$1";
                switch(v) {
                    case 1: 
                    case 2: {
                        arr_object[2] = "recordMethod";
                        break;
                    }
                    case 3: 
                    case 4: {
                        arr_object[2] = "recordConstructor";
                        break;
                    }
                    case 5: 
                    case 6: {
                        arr_object[2] = "recordField";
                        break;
                    }
                    case 7: 
                    case 8: {
                        arr_object[2] = "recordClass";
                        break;
                    }
                    default: {
                        arr_object[2] = "getClassResolvedFromSource";
                    }
                }
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
            public ClassDescriptor getClassResolvedFromSource(FqName fqName0) {
                if(fqName0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1.$$$reportNull$$$0(0);
                }
                return null;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
            public void recordClass(JavaClass javaClass0, ClassDescriptor classDescriptor0) {
                if(javaClass0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1.$$$reportNull$$$0(7);
                }
                if(classDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1.$$$reportNull$$$0(8);
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
            public void recordConstructor(JavaElement javaElement0, ConstructorDescriptor constructorDescriptor0) {
                if(javaElement0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1.$$$reportNull$$$0(3);
                }
                if(constructorDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1.$$$reportNull$$$0(4);
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
            public void recordField(JavaField javaField0, PropertyDescriptor propertyDescriptor0) {
                if(javaField0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1.$$$reportNull$$$0(5);
                }
                if(propertyDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1.$$$reportNull$$$0(6);
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache
            public void recordMethod(JavaMember javaMember0, SimpleFunctionDescriptor simpleFunctionDescriptor0) {
                if(javaMember0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1.$$$reportNull$$$0(1);
                }
                if(simpleFunctionDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache.1.$$$reportNull$$$0(2);
                }
            }
        };
    }

    ClassDescriptor getClassResolvedFromSource(FqName arg1);

    void recordClass(JavaClass arg1, ClassDescriptor arg2);

    void recordConstructor(JavaElement arg1, ConstructorDescriptor arg2);

    void recordField(JavaField arg1, PropertyDescriptor arg2);

    void recordMethod(JavaMember arg1, SimpleFunctionDescriptor arg2);
}

