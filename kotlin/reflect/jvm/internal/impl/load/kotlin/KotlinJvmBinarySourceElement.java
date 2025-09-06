package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerAbiStability;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;

public final class KotlinJvmBinarySourceElement implements DeserializedContainerSource {
    private final DeserializedContainerAbiStability abiStability;
    private final KotlinJvmBinaryClass binaryClass;
    private final IncompatibleVersionErrorData incompatibility;
    private final boolean isPreReleaseInvisible;

    public KotlinJvmBinarySourceElement(KotlinJvmBinaryClass kotlinJvmBinaryClass0, IncompatibleVersionErrorData incompatibleVersionErrorData0, boolean z, DeserializedContainerAbiStability deserializedContainerAbiStability0) {
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "binaryClass");
        Intrinsics.checkNotNullParameter(deserializedContainerAbiStability0, "abiStability");
        super();
        this.binaryClass = kotlinJvmBinaryClass0;
        this.incompatibility = incompatibleVersionErrorData0;
        this.isPreReleaseInvisible = z;
        this.abiStability = deserializedContainerAbiStability0;
    }

    public final KotlinJvmBinaryClass getBinaryClass() {
        return this.binaryClass;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
    public SourceFile getContainingFile() {
        Intrinsics.checkNotNullExpressionValue(SourceFile.NO_SOURCE_FILE, "NO_SOURCE_FILE");
        return SourceFile.NO_SOURCE_FILE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource
    public String getPresentableString() {
        return "Class \'" + this.binaryClass.getClassId().asSingleFqName().asString() + '\'';
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.binaryClass;
    }
}

