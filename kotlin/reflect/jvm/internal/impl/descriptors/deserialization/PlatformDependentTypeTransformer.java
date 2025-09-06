package kotlin.reflect.jvm.internal.impl.descriptors.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public interface PlatformDependentTypeTransformer {
    public static final class None implements PlatformDependentTypeTransformer {
        public static final None INSTANCE;

        static {
            None.INSTANCE = new None();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentTypeTransformer
        public SimpleType transformPlatformType(ClassId classId0, SimpleType simpleType0) {
            Intrinsics.checkNotNullParameter(classId0, "classId");
            Intrinsics.checkNotNullParameter(simpleType0, "computedType");
            return simpleType0;
        }
    }

    SimpleType transformPlatformType(ClassId arg1, SimpleType arg2);
}

