package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public abstract class ClassifierBasedTypeConstructor implements TypeConstructor {
    private int hashCode;

    protected final boolean areFqNamesEqual(ClassifierDescriptor classifierDescriptor0, ClassifierDescriptor classifierDescriptor1) {
        Intrinsics.checkNotNullParameter(classifierDescriptor0, "first");
        Intrinsics.checkNotNullParameter(classifierDescriptor1, "second");
        if(!Intrinsics.areEqual(classifierDescriptor0.getName(), classifierDescriptor1.getName())) {
            return false;
        }
        DeclarationDescriptor declarationDescriptor0 = classifierDescriptor0.getContainingDeclaration();
        for(DeclarationDescriptor declarationDescriptor1 = classifierDescriptor1.getContainingDeclaration(); declarationDescriptor0 != null && declarationDescriptor1 != null; declarationDescriptor1 = declarationDescriptor1.getContainingDeclaration()) {
            if(declarationDescriptor0 instanceof ModuleDescriptor) {
                return declarationDescriptor1 instanceof ModuleDescriptor;
            }
            if(declarationDescriptor1 instanceof ModuleDescriptor) {
                return false;
            }
            if(declarationDescriptor0 instanceof PackageFragmentDescriptor) {
                return declarationDescriptor1 instanceof PackageFragmentDescriptor && Intrinsics.areEqual(((PackageFragmentDescriptor)declarationDescriptor0).getFqName(), ((PackageFragmentDescriptor)declarationDescriptor1).getFqName());
            }
            if(declarationDescriptor1 instanceof PackageFragmentDescriptor) {
                return false;
            }
            if(!Intrinsics.areEqual(declarationDescriptor0.getName(), declarationDescriptor1.getName())) {
                return false;
            }
            declarationDescriptor0 = declarationDescriptor0.getContainingDeclaration();
        }
        return true;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof TypeConstructor)) {
            return false;
        }
        if(object0.hashCode() != this.hashCode()) {
            return false;
        }
        if(((TypeConstructor)object0).getParameters().size() != this.getParameters().size()) {
            return false;
        }
        ClassifierDescriptor classifierDescriptor0 = this.getDeclarationDescriptor();
        ClassifierDescriptor classifierDescriptor1 = ((TypeConstructor)object0).getDeclarationDescriptor();
        if(classifierDescriptor1 == null) {
            return false;
        }
        return !this.hasMeaningfulFqName(classifierDescriptor0) || !this.hasMeaningfulFqName(classifierDescriptor1) ? false : this.isSameClassifier(classifierDescriptor1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public abstract ClassifierDescriptor getDeclarationDescriptor();

    // 去混淆评级： 低(20)
    private final boolean hasMeaningfulFqName(ClassifierDescriptor classifierDescriptor0) {
        return !ErrorUtils.isError(classifierDescriptor0) && !DescriptorUtils.isLocal(classifierDescriptor0);
    }

    @Override
    public int hashCode() {
        int v = this.hashCode;
        if(v != 0) {
            return v;
        }
        ClassifierDescriptor classifierDescriptor0 = this.getDeclarationDescriptor();
        int v1 = this.hasMeaningfulFqName(classifierDescriptor0) ? DescriptorUtils.getFqName(classifierDescriptor0).hashCode() : System.identityHashCode(this);
        this.hashCode = v1;
        return v1;
    }

    protected abstract boolean isSameClassifier(ClassifierDescriptor arg1);
}

