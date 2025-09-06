package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.Deprecated;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class CompositePackageFragmentProvider implements PackageFragmentProviderOptimized {
    private final String debugName;
    private final List providers;

    public CompositePackageFragmentProvider(List list0, String s) {
        Intrinsics.checkNotNullParameter(list0, "providers");
        Intrinsics.checkNotNullParameter(s, "debugName");
        super();
        this.providers = list0;
        this.debugName = s;
        list0.size();
        CollectionsKt.toSet(list0).size();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public void collectPackageFragments(FqName fqName0, Collection collection0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(collection0, "packageFragments");
        for(Object object0: this.providers) {
            PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible(((PackageFragmentProvider)object0), fqName0, collection0);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @Deprecated(message = "for usages use #packageFragments(FqName) at final point, for impl use #collectPackageFragments(FqName, MutableCollection<PackageFragmentDescriptor>)")
    public List getPackageFragments(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: this.providers) {
            PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible(((PackageFragmentProvider)object0), fqName0, arrayList0);
        }
        return CollectionsKt.toList(arrayList0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public Collection getSubPackagesOf(FqName fqName0, Function1 function10) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        HashSet hashSet0 = new HashSet();
        for(Object object0: this.providers) {
            hashSet0.addAll(((PackageFragmentProvider)object0).getSubPackagesOf(fqName0, function10));
        }
        return hashSet0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public boolean isEmpty(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Iterable iterable0 = this.providers;
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return true;
        }
        for(Object object0: iterable0) {
            if(!PackageFragmentProviderKt.isEmpty(((PackageFragmentProvider)object0), fqName0)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return this.debugName;
    }
}

