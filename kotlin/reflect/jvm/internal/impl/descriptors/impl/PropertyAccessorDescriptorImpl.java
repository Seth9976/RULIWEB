package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public abstract class PropertyAccessorDescriptorImpl extends DeclarationDescriptorNonRootImpl implements PropertyAccessorDescriptor {
    static final boolean $assertionsDisabled;
    private final PropertyDescriptor correspondingProperty;
    private FunctionDescriptor initialSignatureDescriptor;
    private boolean isDefault;
    private final boolean isExternal;
    private final boolean isInline;
    private final Kind kind;
    private final Modality modality;
    private DescriptorVisibility visibility;

    private static void $$$reportNull$$$0(int v) {
        IllegalArgumentException illegalArgumentException0;
        int v1;
        String s;
        switch(v) {
            case 6: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 6: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                v1 = 2;
                break;
            }
            default: {
                v1 = 3;
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 1: {
                arr_object[0] = "visibility";
                break;
            }
            case 2: {
                arr_object[0] = "correspondingProperty";
                break;
            }
            case 3: {
                arr_object[0] = "annotations";
                break;
            }
            case 4: {
                arr_object[0] = "name";
                break;
            }
            case 5: {
                arr_object[0] = "source";
                break;
            }
            case 7: {
                arr_object[0] = "substitutor";
                break;
            }
            case 6: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyAccessorDescriptorImpl";
                break;
            }
            case 16: {
                arr_object[0] = "overriddenDescriptors";
                break;
            }
            default: {
                arr_object[0] = "modality";
            }
        }
        switch(v) {
            case 6: {
                arr_object[1] = "getKind";
                break;
            }
            case 8: {
                arr_object[1] = "substitute";
                break;
            }
            case 9: {
                arr_object[1] = "getTypeParameters";
                break;
            }
            case 10: {
                arr_object[1] = "getModality";
                break;
            }
            case 11: {
                arr_object[1] = "getVisibility";
                break;
            }
            case 12: {
                arr_object[1] = "getCorrespondingVariable";
                break;
            }
            case 13: {
                arr_object[1] = "getCorrespondingProperty";
                break;
            }
            case 14: {
                arr_object[1] = "getContextReceiverParameters";
                break;
            }
            case 15: {
                arr_object[1] = "getOverriddenDescriptors";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyAccessorDescriptorImpl";
            }
        }
        switch(v) {
            case 7: {
                arr_object[2] = "substitute";
                break;
            }
            case 6: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                break;
            }
            case 16: {
                arr_object[2] = "setOverriddenDescriptors";
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 6: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                illegalArgumentException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalArgumentException0 = new IllegalArgumentException(s1);
            }
        }
        throw illegalArgumentException0;
    }

    static {
    }

    public PropertyAccessorDescriptorImpl(Modality modality0, DescriptorVisibility descriptorVisibility0, PropertyDescriptor propertyDescriptor0, Annotations annotations0, Name name0, boolean z, boolean z1, boolean z2, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0) {
        if(modality0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(descriptorVisibility0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(propertyDescriptor0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(2);
        }
        if(annotations0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(3);
        }
        if(name0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(4);
        }
        if(sourceElement0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(5);
        }
        super(propertyDescriptor0.getContainingDeclaration(), annotations0, name0, sourceElement0);
        this.initialSignatureDescriptor = null;
        this.modality = modality0;
        this.visibility = descriptorVisibility0;
        this.correspondingProperty = propertyDescriptor0;
        this.isDefault = z;
        this.isExternal = z1;
        this.isInline = z2;
        this.kind = callableMemberDescriptor$Kind0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    public PropertyAccessorDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        throw new UnsupportedOperationException("Accessors must be copied by the corresponding property");
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getContextReceiverParameters() {
        List list0 = this.getCorrespondingProperty().getContextReceiverParameters();
        if(list0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(14);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor
    public PropertyDescriptor getCorrespondingProperty() {
        PropertyDescriptor propertyDescriptor0 = this.correspondingProperty;
        if(propertyDescriptor0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(13);
        }
        return propertyDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return this.getCorrespondingProperty().getDispatchReceiverParameter();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return this.getCorrespondingProperty().getExtensionReceiverParameter();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor getInitialSignatureDescriptor() {
        return this.initialSignatureDescriptor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public Kind getKind() {
        Kind callableMemberDescriptor$Kind0 = this.kind;
        if(callableMemberDescriptor$Kind0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(6);
        }
        return callableMemberDescriptor$Kind0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public Modality getModality() {
        Modality modality0 = this.modality;
        if(modality0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(10);
        }
        return modality0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public CallableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl
    public DeclarationDescriptorWithSource getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor getOriginal() {
        return this.getOriginal();
    }

    public abstract PropertyAccessorDescriptor getOriginal();

    protected Collection getOverriddenDescriptors(boolean z) {
        Collection collection0 = new ArrayList(0);
        for(Object object0: this.getCorrespondingProperty().getOverriddenDescriptors()) {
            PropertyDescriptor propertyDescriptor0 = (PropertyDescriptor)object0;
            PropertyGetterDescriptor propertyGetterDescriptor0 = z ? propertyDescriptor0.getGetter() : propertyDescriptor0.getSetter();
            if(propertyGetterDescriptor0 != null) {
                collection0.add(propertyGetterDescriptor0);
            }
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getTypeParameters() {
        List list0 = Collections.EMPTY_LIST;
        if(list0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(9);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public Object getUserData(UserDataKey callableDescriptor$UserDataKey0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility
    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility0 = this.visibility;
        if(descriptorVisibility0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(11);
        }
        return descriptorVisibility0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public boolean hasSynthesizedParameterNames() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor
    public boolean isDefault() {
        return this.isDefault;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return this.isExternal;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isHiddenForResolutionEverywhereBesideSupercalls() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isHiddenToOvercomeSignatureClash() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isInfix() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isInline() {
        return this.isInline;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isOperator() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isSuspend() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isTailrec() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public CopyBuilder newCopyBuilder() {
        throw new UnsupportedOperationException("Accessors must be copied by the corresponding property");
    }

    public void setDefault(boolean z) {
        this.isDefault = z;
    }

    public void setInitialSignatureDescriptor(FunctionDescriptor functionDescriptor0) {
        this.initialSignatureDescriptor = functionDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(Collection collection0) {
        if(collection0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(16);
        }
    }

    public void setVisibility(DescriptorVisibility descriptorVisibility0) {
        this.visibility = descriptorVisibility0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        if(typeSubstitutor0 == null) {
            PropertyAccessorDescriptorImpl.$$$reportNull$$$0(7);
        }
        return this;
    }
}

