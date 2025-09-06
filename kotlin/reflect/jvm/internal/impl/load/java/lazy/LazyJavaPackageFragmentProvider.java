package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder..Util;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

public final class LazyJavaPackageFragmentProvider implements PackageFragmentProviderOptimized {
    private final LazyJavaResolverContext c;
    private final CacheWithNotNullValues packageFragments;

    public LazyJavaPackageFragmentProvider(JavaResolverComponents javaResolverComponents0) {
        Intrinsics.checkNotNullParameter(javaResolverComponents0, "components");
        super();
        Lazy lazy0 = LazyKt.lazyOf(null);
        LazyJavaResolverContext lazyJavaResolverContext0 = new LazyJavaResolverContext(javaResolverComponents0, EMPTY.INSTANCE, lazy0);
        this.c = lazyJavaResolverContext0;
        this.packageFragments = lazyJavaResolverContext0.getStorageManager().createCacheWithNotNullValues();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public void collectPackageFragments(FqName fqName0, Collection collection0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(collection0, "packageFragments");
        CollectionsKt.addIfNotNull(collection0, this.getPackageFragment(fqName0));
    }

    private final LazyJavaPackageFragment getPackageFragment(FqName fqName0) {
        JavaPackage javaPackage0 = JavaClassFinder..Util.findPackage$default(this.c.getComponents().getFinder(), fqName0, false, 2, null);
        if(javaPackage0 == null) {
            return null;
        }
        Function0 function00 = new Function0(javaPackage0) {
            final JavaPackage $jPackage;

            {
                LazyJavaPackageFragmentProvider.this = lazyJavaPackageFragmentProvider0;
                this.$jPackage = javaPackage0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final LazyJavaPackageFragment invoke() {
                return new LazyJavaPackageFragment(LazyJavaPackageFragmentProvider.this.c, this.$jPackage);
            }
        };
        return (LazyJavaPackageFragment)this.packageFragments.computeIfAbsent(fqName0, function00);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @Deprecated(message = "for usages use #packageFragments(FqName) at final point, for impl use #collectPackageFragments(FqName, MutableCollection<PackageFragmentDescriptor>)")
    public List getPackageFragments(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return kotlin.collections.CollectionsKt.listOfNotNull(this.getPackageFragment(fqName0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public Collection getSubPackagesOf(FqName fqName0, Function1 function10) {
        return this.getSubPackagesOf(fqName0, function10);
    }

    public List getSubPackagesOf(FqName fqName0, Function1 function10) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        LazyJavaPackageFragment lazyJavaPackageFragment0 = this.getPackageFragment(fqName0);
        List list0 = lazyJavaPackageFragment0 == null ? null : lazyJavaPackageFragment0.getSubPackageFqNames$descriptors_jvm();
        return list0 == null ? kotlin.collections.CollectionsKt.emptyList() : list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public boolean isEmpty(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return JavaClassFinder..Util.findPackage$default(this.c.getComponents().getFinder(), fqName0, false, 2, null) == null;
    }

    @Override
    public String toString() {
        return "LazyJavaPackageFragmentProvider of module " + this.c.getComponents().getModule();
    }
}

