package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class PackageFragmentProviderKt {
    public static final void collectPackageFragmentsOptimizedIfPossible(PackageFragmentProvider packageFragmentProvider0, FqName fqName0, Collection collection0) {
        Intrinsics.checkNotNullParameter(packageFragmentProvider0, "<this>");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(collection0, "packageFragments");
        if(packageFragmentProvider0 instanceof PackageFragmentProviderOptimized) {
            ((PackageFragmentProviderOptimized)packageFragmentProvider0).collectPackageFragments(fqName0, collection0);
            return;
        }
        collection0.addAll(packageFragmentProvider0.getPackageFragments(fqName0));
    }

    public static final boolean isEmpty(PackageFragmentProvider packageFragmentProvider0, FqName fqName0) {
        Intrinsics.checkNotNullParameter(packageFragmentProvider0, "<this>");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return packageFragmentProvider0 instanceof PackageFragmentProviderOptimized ? ((PackageFragmentProviderOptimized)packageFragmentProvider0).isEmpty(fqName0) : PackageFragmentProviderKt.packageFragments(packageFragmentProvider0, fqName0).isEmpty();
    }

    public static final List packageFragments(PackageFragmentProvider packageFragmentProvider0, FqName fqName0) {
        Intrinsics.checkNotNullParameter(packageFragmentProvider0, "<this>");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        List list0 = new ArrayList();
        PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible(packageFragmentProvider0, fqName0, list0);
        return list0;
    }
}

