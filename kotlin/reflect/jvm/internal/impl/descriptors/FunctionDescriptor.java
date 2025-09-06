package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public interface FunctionDescriptor extends CallableMemberDescriptor {
    public interface CopyBuilder {
        FunctionDescriptor build();

        CopyBuilder putUserData(UserDataKey arg1, Object arg2);

        CopyBuilder setAdditionalAnnotations(Annotations arg1);

        CopyBuilder setCopyOverrides(boolean arg1);

        CopyBuilder setDispatchReceiverParameter(ReceiverParameterDescriptor arg1);

        CopyBuilder setDropOriginalInContainingParts();

        CopyBuilder setExtensionReceiverParameter(ReceiverParameterDescriptor arg1);

        CopyBuilder setHiddenForResolutionEverywhereBesideSupercalls();

        CopyBuilder setHiddenToOvercomeSignatureClash();

        CopyBuilder setKind(Kind arg1);

        CopyBuilder setModality(Modality arg1);

        CopyBuilder setName(Name arg1);

        CopyBuilder setOriginal(CallableMemberDescriptor arg1);

        CopyBuilder setOwner(DeclarationDescriptor arg1);

        CopyBuilder setPreserveSourceElement();

        CopyBuilder setReturnType(KotlinType arg1);

        CopyBuilder setSignatureChange();

        CopyBuilder setSubstitution(TypeSubstitution arg1);

        CopyBuilder setTypeParameters(List arg1);

        CopyBuilder setValueParameters(List arg1);

        CopyBuilder setVisibility(DescriptorVisibility arg1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    DeclarationDescriptor getContainingDeclaration();

    FunctionDescriptor getInitialSignatureDescriptor();

    FunctionDescriptor getOriginal();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    Collection getOverriddenDescriptors();

    boolean isHiddenForResolutionEverywhereBesideSupercalls();

    boolean isHiddenToOvercomeSignatureClash();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    boolean isSuspend();

    boolean isTailrec();

    CopyBuilder newCopyBuilder();

    FunctionDescriptor substitute(TypeSubstitutor arg1);
}

