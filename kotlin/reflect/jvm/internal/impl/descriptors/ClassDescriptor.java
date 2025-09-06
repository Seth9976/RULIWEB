package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;

public interface ClassDescriptor extends ClassOrPackageFragmentDescriptor, ClassifierDescriptorWithTypeParameters {
    ClassDescriptor getCompanionObjectDescriptor();

    Collection getConstructors();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    DeclarationDescriptor getContainingDeclaration();

    List getContextReceivers();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    List getDeclaredTypeParameters();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    SimpleType getDefaultType();

    ClassKind getKind();

    MemberScope getMemberScope(TypeSubstitution arg1);

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    Modality getModality();

    ClassDescriptor getOriginal();

    Collection getSealedSubclasses();

    MemberScope getStaticScope();

    ReceiverParameterDescriptor getThisAsReceiverParameter();

    MemberScope getUnsubstitutedInnerClassesScope();

    MemberScope getUnsubstitutedMemberScope();

    ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor();

    ValueClassRepresentation getValueClassRepresentation();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    DescriptorVisibility getVisibility();

    boolean isCompanionObject();

    boolean isData();

    boolean isFun();

    boolean isInline();

    boolean isValue();
}

