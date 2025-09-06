package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder;
import kotlin.reflect.jvm.internal.impl.utils.DeserializationHelpersKt;

public final class JavaClassDataFinder implements ClassDataFinder {
    private final DeserializedDescriptorResolver deserializedDescriptorResolver;
    private final KotlinClassFinder kotlinClassFinder;

    public JavaClassDataFinder(KotlinClassFinder kotlinClassFinder0, DeserializedDescriptorResolver deserializedDescriptorResolver0) {
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "kotlinClassFinder");
        Intrinsics.checkNotNullParameter(deserializedDescriptorResolver0, "deserializedDescriptorResolver");
        super();
        this.kotlinClassFinder = kotlinClassFinder0;
        this.deserializedDescriptorResolver = deserializedDescriptorResolver0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder
    public ClassData findClassData(ClassId classId0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        JvmMetadataVersion jvmMetadataVersion0 = DeserializationHelpersKt.jvmMetadataVersionOrDefault(this.deserializedDescriptorResolver.getComponents().getConfiguration());
        KotlinJvmBinaryClass kotlinJvmBinaryClass0 = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId0, jvmMetadataVersion0);
        if(kotlinJvmBinaryClass0 == null) {
            return null;
        }
        Intrinsics.areEqual(kotlinJvmBinaryClass0.getClassId(), classId0);
        return this.deserializedDescriptorResolver.readClassData$descriptors_jvm(kotlinJvmBinaryClass0);
    }
}

