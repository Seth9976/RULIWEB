package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.LightClassOriginKind;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

public final class JavaDescriptorResolver {
    private final JavaResolverCache javaResolverCache;
    private final LazyJavaPackageFragmentProvider packageFragmentProvider;

    public JavaDescriptorResolver(LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider0, JavaResolverCache javaResolverCache0) {
        Intrinsics.checkNotNullParameter(lazyJavaPackageFragmentProvider0, "packageFragmentProvider");
        Intrinsics.checkNotNullParameter(javaResolverCache0, "javaResolverCache");
        super();
        this.packageFragmentProvider = lazyJavaPackageFragmentProvider0;
        this.javaResolverCache = javaResolverCache0;
    }

    public final LazyJavaPackageFragmentProvider getPackageFragmentProvider() {
        return this.packageFragmentProvider;
    }

    public final ClassDescriptor resolveClass(JavaClass javaClass0) {
        Intrinsics.checkNotNullParameter(javaClass0, "javaClass");
        FqName fqName0 = javaClass0.getFqName();
        if(fqName0 != null && javaClass0.getLightClassOriginKind() == LightClassOriginKind.SOURCE) {
            return this.javaResolverCache.getClassResolvedFromSource(fqName0);
        }
        JavaClass javaClass1 = javaClass0.getOuterClass();
        if(javaClass1 != null) {
            ClassDescriptor classDescriptor0 = this.resolveClass(javaClass1);
            MemberScope memberScope0 = classDescriptor0 == null ? null : classDescriptor0.getUnsubstitutedInnerClassesScope();
            ClassifierDescriptor classifierDescriptor0 = memberScope0 == null ? null : memberScope0.getContributedClassifier(javaClass0.getName(), NoLookupLocation.FROM_JAVA_LOADER);
            return classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
        }
        if(fqName0 == null) {
            return null;
        }
        FqName fqName1 = fqName0.parent();
        Intrinsics.checkNotNullExpressionValue(fqName1, "fqName.parent()");
        LazyJavaPackageFragment lazyJavaPackageFragment0 = (LazyJavaPackageFragment)CollectionsKt.firstOrNull(this.packageFragmentProvider.getPackageFragments(fqName1));
        return lazyJavaPackageFragment0 == null ? null : lazyJavaPackageFragment0.findClassifierByJavaClass$descriptors_jvm(javaClass0);
    }
}

