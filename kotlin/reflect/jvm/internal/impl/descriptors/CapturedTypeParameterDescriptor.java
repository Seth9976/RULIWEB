package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.Variance;

final class CapturedTypeParameterDescriptor implements TypeParameterDescriptor {
    private final DeclarationDescriptor declarationDescriptor;
    private final int declaredTypeParametersCount;
    private final TypeParameterDescriptor originalDescriptor;

    public CapturedTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor0, DeclarationDescriptor declarationDescriptor0, int v) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "originalDescriptor");
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "declarationDescriptor");
        super();
        this.originalDescriptor = typeParameterDescriptor0;
        this.declarationDescriptor = declarationDescriptor0;
        this.declaredTypeParametersCount = v;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return this.originalDescriptor.accept(declarationDescriptorVisitor0, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return this.originalDescriptor.getAnnotations();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot
    public DeclarationDescriptor getContainingDeclaration() {
        return this.declarationDescriptor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public SimpleType getDefaultType() {
        return this.originalDescriptor.getDefaultType();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public int getIndex() {
        int v = this.originalDescriptor.getIndex();
        return this.declaredTypeParametersCount + v;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Named
    public Name getName() {
        return this.originalDescriptor.getName();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public ClassifierDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public TypeParameterDescriptor getOriginal() {
        TypeParameterDescriptor typeParameterDescriptor0 = this.originalDescriptor.getOriginal();
        Intrinsics.checkNotNullExpressionValue(typeParameterDescriptor0, "originalDescriptor.original");
        return typeParameterDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        return this.originalDescriptor.getSource();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public StorageManager getStorageManager() {
        return this.originalDescriptor.getStorageManager();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public TypeConstructor getTypeConstructor() {
        return this.originalDescriptor.getTypeConstructor();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public List getUpperBounds() {
        return this.originalDescriptor.getUpperBounds();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public Variance getVariance() {
        return this.originalDescriptor.getVariance();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public boolean isCapturedFromOuterDeclaration() {
        return true;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public boolean isReified() {
        return this.originalDescriptor.isReified();
    }

    @Override
    public String toString() {
        return this.originalDescriptor + "[inner-copy]";
    }
}

