package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public abstract class PackageFragmentDescriptorImpl extends DeclarationDescriptorNonRootImpl implements PackageFragmentDescriptor {
    private final String debugString;
    private final FqName fqName;

    public PackageFragmentDescriptorImpl(ModuleDescriptor moduleDescriptor0, FqName fqName0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Name name0 = fqName0.shortNameOrSpecial();
        super(moduleDescriptor0, Annotations.Companion.getEMPTY(), name0, SourceElement.NO_SOURCE);
        this.fqName = fqName0;
        this.debugString = "package " + fqName0 + " of " + moduleDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        Intrinsics.checkNotNullParameter(declarationDescriptorVisitor0, "visitor");
        return declarationDescriptorVisitor0.visitPackageFragmentDescriptor(this, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot
    public DeclarationDescriptor getContainingDeclaration() {
        return this.getContainingDeclaration();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
    public ModuleDescriptor getContainingDeclaration() {
        DeclarationDescriptor declarationDescriptor0 = super.getContainingDeclaration();
        Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ModuleDescriptor");
        return (ModuleDescriptor)declarationDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
    public final FqName getFqName() {
        return this.fqName;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
        return SourceElement.NO_SOURCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl
    public String toString() {
        return this.debugString;
    }
}

