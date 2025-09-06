package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;

public final class ErrorFunctionDescriptor extends SimpleFunctionDescriptorImpl {
    public ErrorFunctionDescriptor(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "containingDeclaration");
        Name name0 = Name.special("<Error function>");
        super(classDescriptor0, null, Annotations.Companion.getEMPTY(), name0, Kind.DECLARATION, SourceElement.NO_SOURCE);
        this.initialize(null, null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), ErrorUtils.createErrorType(ErrorTypeKind.RETURN_TYPE_FOR_FUNCTION, new String[0]), Modality.OPEN, DescriptorVisibilities.PUBLIC);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    public CallableMemberDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    public FunctionDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    public SimpleFunctionDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "newOwner");
        Intrinsics.checkNotNullParameter(modality0, "modality");
        Intrinsics.checkNotNullParameter(descriptorVisibility0, "visibility");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor$Kind0, "kind");
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    protected FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "newOwner");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor$Kind0, "kind");
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(sourceElement0, "source");
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public Object getUserData(UserDataKey callableDescriptor$UserDataKey0) {
        Intrinsics.checkNotNullParameter(callableDescriptor$UserDataKey0, "key");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isSuspend() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    public CopyBuilder newCopyBuilder() {
        return new CopyBuilder() {
            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public FunctionDescriptor build() {
                return this.build();
            }

            public SimpleFunctionDescriptor build() {
                return ErrorFunctionDescriptor.this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder putUserData(UserDataKey callableDescriptor$UserDataKey0, Object object0) {
                Intrinsics.checkNotNullParameter(callableDescriptor$UserDataKey0, "userDataKey");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setAdditionalAnnotations(Annotations annotations0) {
                Intrinsics.checkNotNullParameter(annotations0, "additionalAnnotations");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setCopyOverrides(boolean z) {
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setDispatchReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor0) {
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setDropOriginalInContainingParts() {
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setExtensionReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor0) {
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setHiddenForResolutionEverywhereBesideSupercalls() {
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setHiddenToOvercomeSignatureClash() {
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setKind(Kind callableMemberDescriptor$Kind0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor$Kind0, "kind");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setModality(Modality modality0) {
                Intrinsics.checkNotNullParameter(modality0, "modality");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setName(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "name");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setOriginal(CallableMemberDescriptor callableMemberDescriptor0) {
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setOwner(DeclarationDescriptor declarationDescriptor0) {
                Intrinsics.checkNotNullParameter(declarationDescriptor0, "owner");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setPreserveSourceElement() {
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setReturnType(KotlinType kotlinType0) {
                Intrinsics.checkNotNullParameter(kotlinType0, "type");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setSignatureChange() {
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setSubstitution(TypeSubstitution typeSubstitution0) {
                Intrinsics.checkNotNullParameter(typeSubstitution0, "substitution");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setTypeParameters(List list0) {
                Intrinsics.checkNotNullParameter(list0, "parameters");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setValueParameters(List list0) {
                Intrinsics.checkNotNullParameter(list0, "parameters");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
            public CopyBuilder setVisibility(DescriptorVisibility descriptorVisibility0) {
                Intrinsics.checkNotNullParameter(descriptorVisibility0, "visibility");
                return this;
            }
        };
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "overriddenDescriptors");
    }
}

