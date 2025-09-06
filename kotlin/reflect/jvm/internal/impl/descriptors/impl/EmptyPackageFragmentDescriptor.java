package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

public final class EmptyPackageFragmentDescriptor extends PackageFragmentDescriptorImpl {
    public EmptyPackageFragmentDescriptor(ModuleDescriptor moduleDescriptor0, FqName fqName0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        super(moduleDescriptor0, fqName0);
    }

    public Empty getMemberScope() {
        return Empty.INSTANCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
    public MemberScope getMemberScope() {
        return this.getMemberScope();
    }
}

