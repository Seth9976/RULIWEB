package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;

public interface DeclarationDescriptor extends Named, Annotated {
    Object accept(DeclarationDescriptorVisitor arg1, Object arg2);

    DeclarationDescriptor getContainingDeclaration();

    DeclarationDescriptor getOriginal();
}

