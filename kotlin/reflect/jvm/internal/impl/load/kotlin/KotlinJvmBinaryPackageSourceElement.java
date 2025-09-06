package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;

public final class KotlinJvmBinaryPackageSourceElement implements SourceElement {
    private final LazyJavaPackageFragment packageFragment;

    public KotlinJvmBinaryPackageSourceElement(LazyJavaPackageFragment lazyJavaPackageFragment0) {
        Intrinsics.checkNotNullParameter(lazyJavaPackageFragment0, "packageFragment");
        super();
        this.packageFragment = lazyJavaPackageFragment0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
    public SourceFile getContainingFile() {
        Intrinsics.checkNotNullExpressionValue(SourceFile.NO_SOURCE_FILE, "NO_SOURCE_FILE");
        return SourceFile.NO_SOURCE_FILE;
    }

    @Override
    public String toString() {
        return this.packageFragment + ": " + this.packageFragment.getBinaryClasses$descriptors_jvm().keySet();
    }
}

