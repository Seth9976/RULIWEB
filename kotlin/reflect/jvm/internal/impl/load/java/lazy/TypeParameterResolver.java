package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;

public interface TypeParameterResolver {
    public static final class EMPTY implements TypeParameterResolver {
        public static final EMPTY INSTANCE;

        static {
            EMPTY.INSTANCE = new EMPTY();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.TypeParameterResolver
        public TypeParameterDescriptor resolveTypeParameter(JavaTypeParameter javaTypeParameter0) {
            Intrinsics.checkNotNullParameter(javaTypeParameter0, "javaTypeParameter");
            return null;
        }
    }

    TypeParameterDescriptor resolveTypeParameter(JavaTypeParameter arg1);
}

