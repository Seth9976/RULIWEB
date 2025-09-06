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
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ContextReceiver;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ImplicitContextReceiver;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public class PropertyDescriptorImpl extends VariableDescriptorWithInitializerImpl implements PropertyDescriptor {
    public class CopyConfiguration {
        private boolean copyOverrides;
        private ReceiverParameterDescriptor dispatchReceiverParameter;
        private Kind kind;
        private Modality modality;
        private Name name;
        private List newTypeParameters;
        private PropertyDescriptor original;
        private DeclarationDescriptor owner;
        private boolean preserveSourceElement;
        private KotlinType returnType;
        private TypeSubstitution substitution;
        private DescriptorVisibility visibility;

        private static void $$$reportNull$$$0(int v) {
            IllegalStateException illegalStateException0;
            int v1;
            String s;
            switch(v) {
                case 1: 
                case 2: 
                case 3: 
                case 5: 
                case 7: 
                case 9: 
                case 11: 
                case 13: 
                case 14: 
                case 16: 
                case 17: 
                case 19: {
                    s = "@NotNull method %s.%s must not return null";
                    break;
                }
                default: {
                    s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
                }
            }
            switch(v) {
                case 1: 
                case 2: 
                case 3: 
                case 5: 
                case 7: 
                case 9: 
                case 11: 
                case 13: 
                case 14: 
                case 16: 
                case 17: 
                case 19: {
                    v1 = 2;
                    break;
                }
                default: {
                    v1 = 3;
                }
            }
            Object[] arr_object = new Object[v1];
            switch(v) {
                case 4: {
                    arr_object[0] = "type";
                    break;
                }
                case 6: {
                    arr_object[0] = "modality";
                    break;
                }
                case 8: {
                    arr_object[0] = "visibility";
                    break;
                }
                case 10: {
                    arr_object[0] = "kind";
                    break;
                }
                case 12: {
                    arr_object[0] = "typeParameters";
                    break;
                }
                case 15: {
                    arr_object[0] = "substitution";
                    break;
                }
                case 18: {
                    arr_object[0] = "name";
                    break;
                }
                case 1: 
                case 2: 
                case 3: 
                case 5: 
                case 7: 
                case 9: 
                case 11: 
                case 13: 
                case 14: 
                case 16: 
                case 17: 
                case 19: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
                    break;
                }
                default: {
                    arr_object[0] = "owner";
                }
            }
            switch(v) {
                case 1: {
                    arr_object[1] = "setOwner";
                    break;
                }
                case 2: {
                    arr_object[1] = "setOriginal";
                    break;
                }
                case 3: {
                    arr_object[1] = "setPreserveSourceElement";
                    break;
                }
                case 5: {
                    arr_object[1] = "setReturnType";
                    break;
                }
                case 7: {
                    arr_object[1] = "setModality";
                    break;
                }
                case 9: {
                    arr_object[1] = "setVisibility";
                    break;
                }
                case 11: {
                    arr_object[1] = "setKind";
                    break;
                }
                case 13: {
                    arr_object[1] = "setTypeParameters";
                    break;
                }
                case 14: {
                    arr_object[1] = "setDispatchReceiverParameter";
                    break;
                }
                case 16: {
                    arr_object[1] = "setSubstitution";
                    break;
                }
                case 17: {
                    arr_object[1] = "setCopyOverrides";
                    break;
                }
                case 19: {
                    arr_object[1] = "setName";
                    break;
                }
                default: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
                }
            }
            switch(v) {
                case 4: {
                    arr_object[2] = "setReturnType";
                    break;
                }
                case 6: {
                    arr_object[2] = "setModality";
                    break;
                }
                case 8: {
                    arr_object[2] = "setVisibility";
                    break;
                }
                case 10: {
                    arr_object[2] = "setKind";
                    break;
                }
                case 12: {
                    arr_object[2] = "setTypeParameters";
                    break;
                }
                case 15: {
                    arr_object[2] = "setSubstitution";
                    break;
                }
                case 18: {
                    arr_object[2] = "setName";
                    break;
                }
                case 1: 
                case 2: 
                case 3: 
                case 5: 
                case 7: 
                case 9: 
                case 11: 
                case 13: 
                case 14: 
                case 16: 
                case 17: 
                case 19: {
                    break;
                }
                default: {
                    arr_object[2] = "setOwner";
                }
            }
            String s1 = String.format(s, arr_object);
            switch(v) {
                case 1: 
                case 2: 
                case 3: 
                case 5: 
                case 7: 
                case 9: 
                case 11: 
                case 13: 
                case 14: 
                case 16: 
                case 17: 
                case 19: {
                    illegalStateException0 = new IllegalStateException(s1);
                    break;
                }
                default: {
                    illegalStateException0 = new IllegalArgumentException(s1);
                }
            }
            throw illegalStateException0;
        }

        public CopyConfiguration() {
            this.owner = propertyDescriptorImpl0.getContainingDeclaration();
            this.modality = propertyDescriptorImpl0.getModality();
            this.visibility = propertyDescriptorImpl0.getVisibility();
            this.original = null;
            this.preserveSourceElement = false;
            this.kind = propertyDescriptorImpl0.getKind();
            this.substitution = TypeSubstitution.EMPTY;
            this.copyOverrides = true;
            this.dispatchReceiverParameter = propertyDescriptorImpl0.dispatchReceiverParameter;
            this.newTypeParameters = null;
            this.name = propertyDescriptorImpl0.getName();
            this.returnType = propertyDescriptorImpl0.getType();
        }

        static DeclarationDescriptor access$100(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.owner;
        }

        static KotlinType access$1000(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.returnType;
        }

        static ReceiverParameterDescriptor access$1100(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.dispatchReceiverParameter;
        }

        static boolean access$1200(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.copyOverrides;
        }

        static Modality access$200(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.modality;
        }

        static DescriptorVisibility access$300(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.visibility;
        }

        static PropertyDescriptor access$400(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.original;
        }

        static Kind access$500(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.kind;
        }

        static Name access$600(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.name;
        }

        static boolean access$700(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.preserveSourceElement;
        }

        static List access$800(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.newTypeParameters;
        }

        static TypeSubstitution access$900(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
            return propertyDescriptorImpl$CopyConfiguration0.substitution;
        }

        public PropertyDescriptor build() {
            return PropertyDescriptorImpl.this.doSubstitute(this);
        }

        PropertyGetterDescriptor getOriginalGetter() {
            return this.original == null ? null : this.original.getGetter();
        }

        PropertySetterDescriptor getOriginalSetter() {
            return this.original == null ? null : this.original.getSetter();
        }

        public CopyConfiguration setCopyOverrides(boolean z) {
            this.copyOverrides = z;
            return this;
        }

        public CopyConfiguration setKind(Kind callableMemberDescriptor$Kind0) {
            if(callableMemberDescriptor$Kind0 == null) {
                CopyConfiguration.$$$reportNull$$$0(10);
            }
            this.kind = callableMemberDescriptor$Kind0;
            return this;
        }

        public CopyConfiguration setModality(Modality modality0) {
            if(modality0 == null) {
                CopyConfiguration.$$$reportNull$$$0(6);
            }
            this.modality = modality0;
            return this;
        }

        public CopyConfiguration setOriginal(CallableMemberDescriptor callableMemberDescriptor0) {
            this.original = (PropertyDescriptor)callableMemberDescriptor0;
            return this;
        }

        public CopyConfiguration setOwner(DeclarationDescriptor declarationDescriptor0) {
            if(declarationDescriptor0 == null) {
                CopyConfiguration.$$$reportNull$$$0(0);
            }
            this.owner = declarationDescriptor0;
            return this;
        }

        public CopyConfiguration setSubstitution(TypeSubstitution typeSubstitution0) {
            if(typeSubstitution0 == null) {
                CopyConfiguration.$$$reportNull$$$0(15);
            }
            this.substitution = typeSubstitution0;
            return this;
        }

        public CopyConfiguration setVisibility(DescriptorVisibility descriptorVisibility0) {
            if(descriptorVisibility0 == null) {
                CopyConfiguration.$$$reportNull$$$0(8);
            }
            this.visibility = descriptorVisibility0;
            return this;
        }
    }

    private FieldDescriptor backingField;
    private List contextReceiverParameters;
    private FieldDescriptor delegateField;
    private ReceiverParameterDescriptor dispatchReceiverParameter;
    private ReceiverParameterDescriptor extensionReceiverParameter;
    private PropertyGetterDescriptorImpl getter;
    private final boolean isActual;
    private final boolean isConst;
    private final boolean isDelegated;
    private final boolean isExpect;
    private final boolean isExternal;
    private final Kind kind;
    private final boolean lateInit;
    private final Modality modality;
    private final PropertyDescriptor original;
    private Collection overriddenProperties;
    private PropertySetterDescriptor setter;
    private boolean setterProjectedOut;
    private List typeParameters;
    private DescriptorVisibility visibility;

    private static void $$$reportNull$$$0(int v) {
        IllegalStateException illegalStateException0;
        int v1;
        String s;
        if(v == 28 || v == 38 || v == 39 || v == 41 || v == 42) {
            s = "@NotNull method %s.%s must not return null";
        }
        else {
            switch(v) {
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: {
                    s = "@NotNull method %s.%s must not return null";
                    break;
                }
                default: {
                    s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
                }
            }
        }
        if(v == 28 || v == 38 || v == 39 || v == 41 || v == 42) {
            v1 = 2;
        }
        else {
            switch(v) {
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: {
                    v1 = 2;
                    break;
                }
                default: {
                    v1 = 3;
                }
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 1: 
            case 8: {
                arr_object[0] = "annotations";
                break;
            }
            case 2: 
            case 9: {
                arr_object[0] = "modality";
                break;
            }
            case 4: 
            case 11: {
                arr_object[0] = "name";
                break;
            }
            case 14: {
                arr_object[0] = "inType";
                break;
            }
            case 15: 
            case 17: {
                arr_object[0] = "outType";
                break;
            }
            case 16: 
            case 18: {
                arr_object[0] = "typeParameters";
                break;
            }
            case 19: {
                arr_object[0] = "contextReceiverParameters";
                break;
            }
            case 3: 
            case 10: 
            case 20: {
                arr_object[0] = "visibility";
                break;
            }
            case 27: {
                arr_object[0] = "originalSubstitutor";
                break;
            }
            case 29: {
                arr_object[0] = "copyConfiguration";
                break;
            }
            case 30: {
                arr_object[0] = "substitutor";
                break;
            }
            case 0x1F: {
                arr_object[0] = "accessorDescriptor";
                break;
            }
            case 0x20: {
                arr_object[0] = "newOwner";
                break;
            }
            case 33: {
                arr_object[0] = "newModality";
                break;
            }
            case 34: {
                arr_object[0] = "newVisibility";
                break;
            }
            case 5: 
            case 12: 
            case 35: {
                arr_object[0] = "kind";
                break;
            }
            case 36: {
                arr_object[0] = "newName";
                break;
            }
            case 6: 
            case 13: 
            case 37: {
                arr_object[0] = "source";
                break;
            }
            case 40: {
                arr_object[0] = "overriddenDescriptors";
                break;
            }
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 26: 
            case 28: 
            case 38: 
            case 39: 
            case 41: 
            case 42: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 21: {
                arr_object[1] = "getTypeParameters";
                break;
            }
            case 22: {
                arr_object[1] = "getContextReceiverParameters";
                break;
            }
            case 23: {
                arr_object[1] = "getReturnType";
                break;
            }
            case 24: {
                arr_object[1] = "getModality";
                break;
            }
            case 25: {
                arr_object[1] = "getVisibility";
                break;
            }
            case 26: {
                arr_object[1] = "getAccessors";
                break;
            }
            case 28: {
                arr_object[1] = "getSourceToUseForCopy";
                break;
            }
            case 38: {
                arr_object[1] = "getOriginal";
                break;
            }
            case 39: {
                arr_object[1] = "getKind";
                break;
            }
            case 41: {
                arr_object[1] = "getOverriddenDescriptors";
                break;
            }
            case 42: {
                arr_object[1] = "copy";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl";
            }
        }
        switch(v) {
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: {
                arr_object[2] = "create";
                break;
            }
            case 14: {
                arr_object[2] = "setInType";
                break;
            }
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: {
                arr_object[2] = "setType";
                break;
            }
            case 20: {
                arr_object[2] = "setVisibility";
                break;
            }
            case 27: {
                arr_object[2] = "substitute";
                break;
            }
            case 29: {
                arr_object[2] = "doSubstitute";
                break;
            }
            case 30: 
            case 0x1F: {
                arr_object[2] = "getSubstitutedInitialSignatureDescriptor";
                break;
            }
            case 0x20: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: {
                arr_object[2] = "createSubstitutedCopy";
                break;
            }
            case 40: {
                arr_object[2] = "setOverriddenDescriptors";
                break;
            }
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 26: 
            case 28: 
            case 38: 
            case 39: 
            case 41: 
            case 42: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s1 = String.format(s, arr_object);
        if(v == 28 || v == 38 || v == 39 || v == 41 || v == 42) {
            illegalStateException0 = new IllegalStateException(s1);
        }
        else {
            switch(v) {
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: {
                    illegalStateException0 = new IllegalStateException(s1);
                    break;
                }
                default: {
                    illegalStateException0 = new IllegalArgumentException(s1);
                }
            }
        }
        throw illegalStateException0;
    }

    protected PropertyDescriptorImpl(DeclarationDescriptor declarationDescriptor0, PropertyDescriptor propertyDescriptor0, Annotations annotations0, Modality modality0, DescriptorVisibility descriptorVisibility0, boolean z, Name name0, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0, boolean z1, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        if(declarationDescriptor0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(modality0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(2);
        }
        if(descriptorVisibility0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(3);
        }
        if(name0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(4);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(5);
        }
        if(sourceElement0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(6);
        }
        super(declarationDescriptor0, annotations0, name0, null, z, sourceElement0);
        this.overriddenProperties = null;
        this.contextReceiverParameters = Collections.EMPTY_LIST;
        this.modality = modality0;
        this.visibility = descriptorVisibility0;
        PropertyDescriptor propertyDescriptor1 = propertyDescriptor0 == null ? this : propertyDescriptor0;
        this.original = propertyDescriptor1;
        this.kind = callableMemberDescriptor$Kind0;
        this.lateInit = z1;
        this.isConst = z2;
        this.isExpect = z3;
        this.isActual = z4;
        this.isExternal = z5;
        this.isDelegated = z6;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return declarationDescriptorVisitor0.visitPropertyDescriptor(this, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    public PropertyDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        PropertyDescriptor propertyDescriptor0 = this.newCopyBuilder().setOwner(declarationDescriptor0).setOriginal(null).setModality(modality0).setVisibility(descriptorVisibility0).setKind(callableMemberDescriptor$Kind0).setCopyOverrides(z).build();
        if(propertyDescriptor0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(42);
        }
        return propertyDescriptor0;
    }

    public static PropertyDescriptorImpl create(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Modality modality0, DescriptorVisibility descriptorVisibility0, boolean z, Name name0, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0, boolean z1, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        if(declarationDescriptor0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(7);
        }
        if(annotations0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(8);
        }
        if(modality0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(9);
        }
        if(descriptorVisibility0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(10);
        }
        if(name0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(11);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(12);
        }
        if(sourceElement0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(13);
        }
        return new PropertyDescriptorImpl(declarationDescriptor0, null, annotations0, modality0, descriptorVisibility0, z, name0, callableMemberDescriptor$Kind0, sourceElement0, z1, z2, z3, z4, z5, z6);
    }

    protected PropertyDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, PropertyDescriptor propertyDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(0x20);
        }
        if(modality0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(33);
        }
        if(descriptorVisibility0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(34);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(35);
        }
        if(name0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(36);
        }
        if(sourceElement0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(37);
        }
        return new PropertyDescriptorImpl(declarationDescriptor0, propertyDescriptor0, this.getAnnotations(), modality0, descriptorVisibility0, this.isVar(), name0, callableMemberDescriptor$Kind0, sourceElement0, this.isLateInit(), this.isConst(), this.isExpect(), this.isActual(), this.isExternal(), this.isDelegated());
    }

    protected PropertyDescriptor doSubstitute(CopyConfiguration propertyDescriptorImpl$CopyConfiguration0) {
        ReceiverParameterDescriptor receiverParameterDescriptor2;
        if(propertyDescriptorImpl$CopyConfiguration0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(29);
        }
        PropertyDescriptorImpl propertyDescriptorImpl0 = this.createSubstitutedCopy(CopyConfiguration.access$100(propertyDescriptorImpl$CopyConfiguration0), CopyConfiguration.access$200(propertyDescriptorImpl$CopyConfiguration0), CopyConfiguration.access$300(propertyDescriptorImpl$CopyConfiguration0), CopyConfiguration.access$400(propertyDescriptorImpl$CopyConfiguration0), CopyConfiguration.access$500(propertyDescriptorImpl$CopyConfiguration0), CopyConfiguration.access$600(propertyDescriptorImpl$CopyConfiguration0), this.getSourceToUseForCopy(CopyConfiguration.access$700(propertyDescriptorImpl$CopyConfiguration0), CopyConfiguration.access$400(propertyDescriptorImpl$CopyConfiguration0)));
        List list0 = CopyConfiguration.access$800(propertyDescriptorImpl$CopyConfiguration0) == null ? this.getTypeParameters() : CopyConfiguration.access$800(propertyDescriptorImpl$CopyConfiguration0);
        ArrayList arrayList0 = new ArrayList(list0.size());
        TypeSubstitutor typeSubstitutor0 = DescriptorSubstitutor.substituteTypeParameters(list0, CopyConfiguration.access$900(propertyDescriptorImpl$CopyConfiguration0), propertyDescriptorImpl0, arrayList0);
        KotlinType kotlinType0 = CopyConfiguration.access$1000(propertyDescriptorImpl$CopyConfiguration0);
        KotlinType kotlinType1 = typeSubstitutor0.substitute(kotlinType0, Variance.OUT_VARIANCE);
        if(kotlinType1 == null) {
            return null;
        }
        KotlinType kotlinType2 = typeSubstitutor0.substitute(kotlinType0, Variance.IN_VARIANCE);
        if(kotlinType2 != null) {
            propertyDescriptorImpl0.setInType(kotlinType2);
        }
        ReceiverParameterDescriptor receiverParameterDescriptor0 = CopyConfiguration.access$1100(propertyDescriptorImpl$CopyConfiguration0);
        if(receiverParameterDescriptor0 == null) {
            receiverParameterDescriptor2 = null;
        }
        else {
            ReceiverParameterDescriptor receiverParameterDescriptor1 = receiverParameterDescriptor0.substitute(typeSubstitutor0);
            if(receiverParameterDescriptor1 == null) {
                return null;
            }
            receiverParameterDescriptor2 = receiverParameterDescriptor1;
        }
        ReceiverParameterDescriptor receiverParameterDescriptor3 = this.extensionReceiverParameter == null ? null : PropertyDescriptorImpl.substituteParameterDescriptor(typeSubstitutor0, propertyDescriptorImpl0, this.extensionReceiverParameter);
        ArrayList arrayList1 = new ArrayList();
        for(Object object0: this.contextReceiverParameters) {
            ReceiverParameterDescriptor receiverParameterDescriptor4 = PropertyDescriptorImpl.substituteContextParameterDescriptor(typeSubstitutor0, propertyDescriptorImpl0, ((ReceiverParameterDescriptor)object0));
            if(receiverParameterDescriptor4 != null) {
                arrayList1.add(receiverParameterDescriptor4);
            }
        }
        propertyDescriptorImpl0.setType(kotlinType1, arrayList0, receiverParameterDescriptor2, receiverParameterDescriptor3, arrayList1);
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0 = this.getter == null ? null : new PropertyGetterDescriptorImpl(propertyDescriptorImpl0, this.getter.getAnnotations(), CopyConfiguration.access$200(propertyDescriptorImpl$CopyConfiguration0), PropertyDescriptorImpl.normalizeVisibility(this.getter.getVisibility(), CopyConfiguration.access$500(propertyDescriptorImpl$CopyConfiguration0)), this.getter.isDefault(), this.getter.isExternal(), this.getter.isInline(), CopyConfiguration.access$500(propertyDescriptorImpl$CopyConfiguration0), propertyDescriptorImpl$CopyConfiguration0.getOriginalGetter(), SourceElement.NO_SOURCE);
        if(propertyGetterDescriptorImpl0 != null) {
            KotlinType kotlinType3 = this.getter.getReturnType();
            propertyGetterDescriptorImpl0.setInitialSignatureDescriptor(PropertyDescriptorImpl.getSubstitutedInitialSignatureDescriptor(typeSubstitutor0, this.getter));
            propertyGetterDescriptorImpl0.initialize((kotlinType3 == null ? null : typeSubstitutor0.substitute(kotlinType3, Variance.OUT_VARIANCE)));
        }
        PropertySetterDescriptorImpl propertySetterDescriptorImpl0 = this.setter == null ? null : new PropertySetterDescriptorImpl(propertyDescriptorImpl0, this.setter.getAnnotations(), CopyConfiguration.access$200(propertyDescriptorImpl$CopyConfiguration0), PropertyDescriptorImpl.normalizeVisibility(this.setter.getVisibility(), CopyConfiguration.access$500(propertyDescriptorImpl$CopyConfiguration0)), this.setter.isDefault(), this.setter.isExternal(), this.setter.isInline(), CopyConfiguration.access$500(propertyDescriptorImpl$CopyConfiguration0), propertyDescriptorImpl$CopyConfiguration0.getOriginalSetter(), SourceElement.NO_SOURCE);
        if(propertySetterDescriptorImpl0 != null) {
            List list1 = FunctionDescriptorImpl.getSubstitutedValueParameters(propertySetterDescriptorImpl0, this.setter.getValueParameters(), typeSubstitutor0, false, false, null);
            if(list1 == null) {
                propertyDescriptorImpl0.setSetterProjectedOut(true);
                list1 = Collections.singletonList(PropertySetterDescriptorImpl.createSetterParameter(propertySetterDescriptorImpl0, DescriptorUtilsKt.getBuiltIns(CopyConfiguration.access$100(propertyDescriptorImpl$CopyConfiguration0)).getNothingType(), ((ValueParameterDescriptor)this.setter.getValueParameters().get(0)).getAnnotations()));
            }
            if(list1.size() != 1) {
                throw new IllegalStateException();
            }
            propertySetterDescriptorImpl0.setInitialSignatureDescriptor(PropertyDescriptorImpl.getSubstitutedInitialSignatureDescriptor(typeSubstitutor0, this.setter));
            propertySetterDescriptorImpl0.initialize(((ValueParameterDescriptor)list1.get(0)));
        }
        propertyDescriptorImpl0.initialize(propertyGetterDescriptorImpl0, propertySetterDescriptorImpl0, (this.backingField == null ? null : new FieldDescriptorImpl(this.backingField.getAnnotations(), propertyDescriptorImpl0)), (this.delegateField == null ? null : new FieldDescriptorImpl(this.delegateField.getAnnotations(), propertyDescriptorImpl0)));
        if(CopyConfiguration.access$1200(propertyDescriptorImpl$CopyConfiguration0)) {
            SmartSet smartSet0 = SmartSet.create();
            for(Object object1: this.getOverriddenDescriptors()) {
                smartSet0.add(((PropertyDescriptor)object1).substitute(typeSubstitutor0));
            }
            propertyDescriptorImpl0.setOverriddenDescriptors(smartSet0);
        }
        if(this.isConst() && this.compileTimeInitializerFactory != null) {
            propertyDescriptorImpl0.setCompileTimeInitializer(this.compileTimeInitializer, this.compileTimeInitializerFactory);
        }
        return propertyDescriptorImpl0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public List getAccessors() {
        List list0 = new ArrayList(2);
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0 = this.getter;
        if(propertyGetterDescriptorImpl0 != null) {
            list0.add(propertyGetterDescriptorImpl0);
        }
        PropertySetterDescriptor propertySetterDescriptor0 = this.setter;
        if(propertySetterDescriptor0 != null) {
            list0.add(propertySetterDescriptor0);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public FieldDescriptor getBackingField() {
        return this.backingField;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getContextReceiverParameters() {
        List list0 = this.contextReceiverParameters;
        if(list0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(22);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public FieldDescriptor getDelegateField() {
        return this.delegateField;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return this.dispatchReceiverParameter;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return this.extensionReceiverParameter;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public PropertyGetterDescriptor getGetter() {
        return this.getGetter();
    }

    public PropertyGetterDescriptorImpl getGetter() {
        return this.getter;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public Kind getKind() {
        Kind callableMemberDescriptor$Kind0 = this.kind;
        if(callableMemberDescriptor$Kind0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(39);
        }
        return callableMemberDescriptor$Kind0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public Modality getModality() {
        Modality modality0 = this.modality;
        if(modality0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(24);
        }
        return modality0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public CallableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl
    public DeclarationDescriptorWithSource getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public PropertyDescriptor getOriginal() {
        PropertyDescriptor propertyDescriptor0 = this.original;
        PropertyDescriptor propertyDescriptor1 = propertyDescriptor0 == this ? this : propertyDescriptor0.getOriginal();
        if(propertyDescriptor1 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(38);
        }
        return propertyDescriptor1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl
    public VariableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl
    public Collection getOverriddenDescriptors() {
        Collection collection0 = this.overriddenProperties;
        if(collection0 == null) {
            collection0 = Collections.EMPTY_LIST;
        }
        if(collection0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(41);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public KotlinType getReturnType() {
        KotlinType kotlinType0 = this.getType();
        if(kotlinType0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(23);
        }
        return kotlinType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public PropertySetterDescriptor getSetter() {
        return this.setter;
    }

    private SourceElement getSourceToUseForCopy(boolean z, PropertyDescriptor propertyDescriptor0) {
        SourceElement sourceElement0;
        if(z) {
            if(propertyDescriptor0 == null) {
                propertyDescriptor0 = this.getOriginal();
            }
            sourceElement0 = propertyDescriptor0.getSource();
        }
        else {
            sourceElement0 = SourceElement.NO_SOURCE;
        }
        if(sourceElement0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(28);
        }
        return sourceElement0;
    }

    private static FunctionDescriptor getSubstitutedInitialSignatureDescriptor(TypeSubstitutor typeSubstitutor0, PropertyAccessorDescriptor propertyAccessorDescriptor0) {
        if(typeSubstitutor0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(30);
        }
        if(propertyAccessorDescriptor0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(0x1F);
        }
        return propertyAccessorDescriptor0.getInitialSignatureDescriptor() == null ? null : propertyAccessorDescriptor0.getInitialSignatureDescriptor().substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getTypeParameters() {
        List list0 = this.typeParameters;
        if(list0 == null) {
            throw new IllegalStateException("typeParameters == null for " + this.toString());
        }
        if(list0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(21);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public Object getUserData(UserDataKey callableDescriptor$UserDataKey0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility
    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility0 = this.visibility;
        if(descriptorVisibility0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(25);
        }
        return descriptorVisibility0;
    }

    public void initialize(PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0, PropertySetterDescriptor propertySetterDescriptor0) {
        this.initialize(propertyGetterDescriptorImpl0, propertySetterDescriptor0, null, null);
    }

    public void initialize(PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0, PropertySetterDescriptor propertySetterDescriptor0, FieldDescriptor fieldDescriptor0, FieldDescriptor fieldDescriptor1) {
        this.getter = propertyGetterDescriptorImpl0;
        this.setter = propertySetterDescriptor0;
        this.backingField = fieldDescriptor0;
        this.delegateField = fieldDescriptor1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return this.isActual;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isConst() {
        return this.isConst;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptorWithAccessors
    public boolean isDelegated() {
        return this.isDelegated;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return this.isExpect;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return this.isExternal;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isLateInit() {
        return this.lateInit;
    }

    public boolean isSetterProjectedOut() {
        return this.setterProjectedOut;
    }

    public CopyConfiguration newCopyBuilder() {
        return new CopyConfiguration(this);
    }

    private static DescriptorVisibility normalizeVisibility(DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0) {
        return callableMemberDescriptor$Kind0 != Kind.FAKE_OVERRIDE || !DescriptorVisibilities.isPrivate(descriptorVisibility0.normalize()) ? descriptorVisibility0 : DescriptorVisibilities.INVISIBLE_FAKE;
    }

    public void setInType(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(14);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(Collection collection0) {
        if(collection0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(40);
        }
        this.overriddenProperties = collection0;
    }

    public void setSetterProjectedOut(boolean z) {
        this.setterProjectedOut = z;
    }

    public void setType(KotlinType kotlinType0, List list0, ReceiverParameterDescriptor receiverParameterDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor1, List list1) {
        if(kotlinType0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(17);
        }
        if(list0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(18);
        }
        if(list1 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(19);
        }
        this.setOutType(kotlinType0);
        this.typeParameters = new ArrayList(list0);
        this.extensionReceiverParameter = receiverParameterDescriptor1;
        this.dispatchReceiverParameter = receiverParameterDescriptor0;
        this.contextReceiverParameters = list1;
    }

    public void setVisibility(DescriptorVisibility descriptorVisibility0) {
        if(descriptorVisibility0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(20);
        }
        this.visibility = descriptorVisibility0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor
    public PropertyDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        if(typeSubstitutor0 == null) {
            PropertyDescriptorImpl.$$$reportNull$$$0(27);
        }
        return typeSubstitutor0.isEmpty() ? this : this.newCopyBuilder().setSubstitution(typeSubstitutor0.getSubstitution()).setOriginal(this.getOriginal()).build();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public VariableDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    private static ReceiverParameterDescriptor substituteContextParameterDescriptor(TypeSubstitutor typeSubstitutor0, PropertyDescriptor propertyDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor0) {
        KotlinType kotlinType0 = typeSubstitutor0.substitute(receiverParameterDescriptor0.getType(), Variance.IN_VARIANCE);
        return kotlinType0 == null ? null : new ReceiverParameterDescriptorImpl(propertyDescriptor0, new ContextReceiver(propertyDescriptor0, kotlinType0, ((ImplicitContextReceiver)receiverParameterDescriptor0.getValue()).getCustomLabelName(), receiverParameterDescriptor0.getValue()), receiverParameterDescriptor0.getAnnotations());
    }

    private static ReceiverParameterDescriptor substituteParameterDescriptor(TypeSubstitutor typeSubstitutor0, PropertyDescriptor propertyDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor0) {
        KotlinType kotlinType0 = typeSubstitutor0.substitute(receiverParameterDescriptor0.getType(), Variance.IN_VARIANCE);
        return kotlinType0 == null ? null : new ReceiverParameterDescriptorImpl(propertyDescriptor0, new ExtensionReceiver(propertyDescriptor0, kotlinType0, receiverParameterDescriptor0.getValue()), receiverParameterDescriptor0.getAnnotations());
    }
}

