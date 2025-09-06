package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude.TopLevelPackages;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;

public class SubpackagesScope extends MemberScopeImpl {
    private final FqName fqName;
    private final ModuleDescriptor moduleDescriptor;

    public SubpackagesScope(ModuleDescriptor moduleDescriptor0, FqName fqName0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "moduleDescriptor");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        super();
        this.moduleDescriptor = moduleDescriptor0;
        this.fqName = fqName0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getClassifierNames() {
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        if(!descriptorKindFilter0.acceptsKinds(DescriptorKindFilter.Companion.getPACKAGES_MASK())) {
            return CollectionsKt.emptyList();
        }
        if(this.fqName.isRoot() && descriptorKindFilter0.getExcludes().contains(TopLevelPackages.INSTANCE)) {
            return CollectionsKt.emptyList();
        }
        Collection collection0 = this.moduleDescriptor.getSubPackagesOf(this.fqName, function10);
        ArrayList arrayList0 = new ArrayList(collection0.size());
        for(Object object0: collection0) {
            Name name0 = ((FqName)object0).shortName();
            Intrinsics.checkNotNullExpressionValue(name0, "subFqName.shortName()");
            if(((Boolean)function10.invoke(name0)).booleanValue()) {
                kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList0, this.getPackage(name0));
            }
        }
        return arrayList0;
    }

    protected final PackageViewDescriptor getPackage(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        if(name0.isSpecial()) {
            return null;
        }
        FqName fqName0 = this.fqName.child(name0);
        Intrinsics.checkNotNullExpressionValue(fqName0, "fqName.child(name)");
        PackageViewDescriptor packageViewDescriptor0 = this.moduleDescriptor.getPackage(fqName0);
        return packageViewDescriptor0.isEmpty() ? null : packageViewDescriptor0;
    }

    @Override
    public String toString() {
        return "subpackages of " + this.fqName + " from " + this.moduleDescriptor;
    }
}

