package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.sequences.SequencesKt;

public final class PackageFragmentProviderImpl implements PackageFragmentProviderOptimized {
    private final Collection packageFragments;

    public PackageFragmentProviderImpl(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "packageFragments");
        super();
        this.packageFragments = collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public void collectPackageFragments(FqName fqName0, Collection collection0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(collection0, "packageFragments");
        for(Object object0: this.packageFragments) {
            if(Intrinsics.areEqual(((PackageFragmentDescriptor)object0).getFqName(), fqName0)) {
                collection0.add(object0);
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @Deprecated(message = "for usages use #packageFragments(FqName) at final point, for impl use #collectPackageFragments(FqName, MutableCollection<PackageFragmentDescriptor>)")
    public List getPackageFragments(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Collection collection0 = new ArrayList();
        for(Object object0: this.packageFragments) {
            if(Intrinsics.areEqual(((PackageFragmentDescriptor)object0).getFqName(), fqName0)) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public Collection getSubPackagesOf(FqName fqName0, Function1 function10) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return SequencesKt.toList(SequencesKt.filter(SequencesKt.map(CollectionsKt.asSequence(this.packageFragments), kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl.getSubPackagesOf.1.INSTANCE), new Function1() {
            final FqName $fqName;

            {
                this.$fqName = fqName0;
                super(1);
            }

            public final Boolean invoke(FqName fqName0) {
                Intrinsics.checkNotNullParameter(fqName0, "it");
                return fqName0.isRoot() || !Intrinsics.areEqual(fqName0.parent(), this.$fqName) ? false : true;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FqName)object0));
            }
        }));

        final class kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl.getSubPackagesOf.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl.getSubPackagesOf.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl.getSubPackagesOf.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl.getSubPackagesOf.1();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl.getSubPackagesOf.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((PackageFragmentDescriptor)object0));
            }

            public final FqName invoke(PackageFragmentDescriptor packageFragmentDescriptor0) {
                Intrinsics.checkNotNullParameter(packageFragmentDescriptor0, "it");
                return packageFragmentDescriptor0.getFqName();
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public boolean isEmpty(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Iterable iterable0 = this.packageFragments;
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return true;
        }
        for(Object object0: iterable0) {
            if(Intrinsics.areEqual(((PackageFragmentDescriptor)object0).getFqName(), fqName0)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }
}

