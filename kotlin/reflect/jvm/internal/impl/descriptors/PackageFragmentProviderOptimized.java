package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface PackageFragmentProviderOptimized extends PackageFragmentProvider {
    void collectPackageFragments(FqName arg1, Collection arg2);

    boolean isEmpty(FqName arg1);
}

