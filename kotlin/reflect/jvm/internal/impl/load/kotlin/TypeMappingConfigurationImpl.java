package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class TypeMappingConfigurationImpl implements TypeMappingConfiguration {
    public static final TypeMappingConfigurationImpl INSTANCE;

    static {
        TypeMappingConfigurationImpl.INSTANCE = new TypeMappingConfigurationImpl();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration
    public KotlinType commonSupertype(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "types");
        throw new AssertionError("There should be no intersection type in existing descriptors, but found: " + CollectionsKt.joinToString$default(collection0, null, null, null, 0, null, null, 0x3F, null));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration
    public String getPredefinedFullInternalNameForClass(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration
    public String getPredefinedInternalNameForClass(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration
    public Object getPredefinedTypeForClass(ClassDescriptor classDescriptor0) {
        return this.getPredefinedTypeForClass(classDescriptor0);
    }

    public JvmType getPredefinedTypeForClass(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration
    public KotlinType preprocessType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "kotlinType");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration
    public void processErrorType(KotlinType kotlinType0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "kotlinType");
        Intrinsics.checkNotNullParameter(classDescriptor0, "descriptor");
    }
}

