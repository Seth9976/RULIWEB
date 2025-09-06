package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public final class ErrorPropertyDescriptor implements PropertyDescriptor {
    private final PropertyDescriptorImpl $$delegate_0;

    public ErrorPropertyDescriptor() {
        Name name0 = Name.special("<Error property>");
        PropertyDescriptorImpl propertyDescriptorImpl0 = PropertyDescriptorImpl.create(ErrorUtils.INSTANCE.getErrorClass(), Annotations.Companion.getEMPTY(), Modality.OPEN, DescriptorVisibilities.PUBLIC, true, name0, Kind.DECLARATION, SourceElement.NO_SOURCE, false, false, false, false, false, false);
        propertyDescriptorImpl0.setType(ErrorUtils.INSTANCE.getErrorPropertyType(), CollectionsKt.emptyList(), null, null, CollectionsKt.emptyList());
        this.$$delegate_0 = propertyDescriptorImpl0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return this.$$delegate_0.accept(declarationDescriptorVisitor0, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.$$delegate_0.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public List getAccessors() {
        return this.$$delegate_0.getAccessors();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        Annotations annotations0 = this.$$delegate_0.getAnnotations();
        Intrinsics.checkNotNullExpressionValue(annotations0, "<get-annotations>(...)");
        return annotations0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public FieldDescriptor getBackingField() {
        return this.$$delegate_0.getBackingField();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public ConstantValue getCompileTimeInitializer() {
        return this.$$delegate_0.getCompileTimeInitializer();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.ValueDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return this.$$delegate_0.getContainingDeclaration();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getContextReceiverParameters() {
        return this.$$delegate_0.getContextReceiverParameters();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public FieldDescriptor getDelegateField() {
        return this.$$delegate_0.getDelegateField();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return this.$$delegate_0.getDispatchReceiverParameter();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return this.$$delegate_0.getExtensionReceiverParameter();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public PropertyGetterDescriptor getGetter() {
        return this.$$delegate_0.getGetter();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public Kind getKind() {
        return this.$$delegate_0.getKind();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public Modality getModality() {
        return this.$$delegate_0.getModality();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Named
    public Name getName() {
        return this.$$delegate_0.getName();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public CallableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public PropertyDescriptor getOriginal() {
        return this.$$delegate_0.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public Collection getOverriddenDescriptors() {
        return this.$$delegate_0.getOverriddenDescriptors();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public KotlinType getReturnType() {
        return this.$$delegate_0.getReturnType();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public PropertySetterDescriptor getSetter() {
        return this.$$delegate_0.getSetter();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        return this.$$delegate_0.getSource();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueDescriptor
    public KotlinType getType() {
        return this.$$delegate_0.getType();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getTypeParameters() {
        return this.$$delegate_0.getTypeParameters();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public Object getUserData(UserDataKey callableDescriptor$UserDataKey0) {
        return this.$$delegate_0.getUserData(callableDescriptor$UserDataKey0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getValueParameters() {
        return this.$$delegate_0.getValueParameters();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility
    public DescriptorVisibility getVisibility() {
        return this.$$delegate_0.getVisibility();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public boolean hasSynthesizedParameterNames() {
        return this.$$delegate_0.hasSynthesizedParameterNames();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return this.$$delegate_0.isActual();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isConst() {
        return this.$$delegate_0.isConst();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptorWithAccessors
    public boolean isDelegated() {
        return this.$$delegate_0.isDelegated();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return this.$$delegate_0.isExpect();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return this.$$delegate_0.isExternal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isLateInit() {
        return this.$$delegate_0.isLateInit();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isVar() {
        return this.$$delegate_0.isVar();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "overriddenDescriptors");
        this.$$delegate_0.setOverriddenDescriptors(collection0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public PropertyDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        Intrinsics.checkNotNullParameter(typeSubstitutor0, "substitutor");
        return this.$$delegate_0.substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public VariableDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }
}

