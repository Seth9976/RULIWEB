package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public class ClassConstructorDescriptorImpl extends FunctionDescriptorImpl implements ClassConstructorDescriptor {
    static final boolean $assertionsDisabled;
    protected final boolean isPrimary;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 21 || v == 27 || (v == 15 || v == 16 || v == 17 || v == 18 || v == 19) ? 2 : 3)];
        switch(v) {
            case 12: {
                arr_object[0] = "typeParameterDescriptors";
                break;
            }
            case 10: 
            case 13: {
                arr_object[0] = "unsubstitutedValueParameters";
                break;
            }
            case 11: 
            case 14: {
                arr_object[0] = "visibility";
                break;
            }
            case 20: {
                arr_object[0] = "originalSubstitutor";
                break;
            }
            case 22: {
                arr_object[0] = "overriddenDescriptors";
                break;
            }
            case 23: {
                arr_object[0] = "newOwner";
                break;
            }
            case 2: 
            case 24: {
                arr_object[0] = "kind";
                break;
            }
            case 1: 
            case 5: 
            case 8: 
            case 25: {
                arr_object[0] = "annotations";
                break;
            }
            case 3: 
            case 6: 
            case 9: 
            case 26: {
                arr_object[0] = "source";
                break;
            }
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 21: 
            case 27: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassConstructorDescriptorImpl";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 15: 
            case 16: {
                arr_object[1] = "calculateContextReceiverParameters";
                break;
            }
            case 17: {
                arr_object[1] = "getContainingDeclaration";
                break;
            }
            case 18: {
                arr_object[1] = "getConstructedClass";
                break;
            }
            case 19: {
                arr_object[1] = "getOriginal";
                break;
            }
            case 21: {
                arr_object[1] = "getOverriddenDescriptors";
                break;
            }
            case 27: {
                arr_object[1] = "copy";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassConstructorDescriptorImpl";
            }
        }
        switch(v) {
            case 4: 
            case 5: 
            case 6: {
                arr_object[2] = "create";
                break;
            }
            case 7: 
            case 8: 
            case 9: {
                arr_object[2] = "createSynthesized";
                break;
            }
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: {
                arr_object[2] = "initialize";
                break;
            }
            case 20: {
                arr_object[2] = "substitute";
                break;
            }
            case 22: {
                arr_object[2] = "setOverriddenDescriptors";
                break;
            }
            case 23: 
            case 24: 
            case 25: 
            case 26: {
                arr_object[2] = "createSubstitutedCopy";
                break;
            }
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 21: 
            case 27: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s = String.format((v == 21 || v == 27 || (v == 15 || v == 16 || v == 17 || v == 18 || v == 19) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 21 || v == 27 || (v == 15 || v == 16 || v == 17 || v == 18 || v == 19) ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    static {
    }

    protected ClassConstructorDescriptorImpl(ClassDescriptor classDescriptor0, ConstructorDescriptor constructorDescriptor0, Annotations annotations0, boolean z, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0) {
        if(classDescriptor0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(2);
        }
        if(sourceElement0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(3);
        }
        super(classDescriptor0, constructorDescriptor0, annotations0, SpecialNames.INIT, callableMemberDescriptor$Kind0, sourceElement0);
        this.isPrimary = z;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return declarationDescriptorVisitor0.visitConstructorDescriptor(this, object0);
    }

    private List calculateContextReceiverParameters() {
        ClassDescriptor classDescriptor0 = this.getContainingDeclaration();
        if(!classDescriptor0.getContextReceivers().isEmpty()) {
            List list0 = classDescriptor0.getContextReceivers();
            if(list0 == null) {
                ClassConstructorDescriptorImpl.$$$reportNull$$$0(15);
            }
            return list0;
        }
        List list1 = Collections.EMPTY_LIST;
        if(list1 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(16);
        }
        return list1;
    }

    public ReceiverParameterDescriptor calculateDispatchReceiverParameter() {
        ClassDescriptor classDescriptor0 = this.getContainingDeclaration();
        if(classDescriptor0.isInner()) {
            DeclarationDescriptor declarationDescriptor0 = classDescriptor0.getContainingDeclaration();
            return declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0).getThisAsReceiverParameter() : null;
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    public ClassConstructorDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        ClassConstructorDescriptor classConstructorDescriptor0 = (ClassConstructorDescriptor)super.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
        if(classConstructorDescriptor0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(27);
        }
        return classConstructorDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public FunctionDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    public static ClassConstructorDescriptorImpl create(ClassDescriptor classDescriptor0, Annotations annotations0, boolean z, SourceElement sourceElement0) {
        if(classDescriptor0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(4);
        }
        if(annotations0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(5);
        }
        if(sourceElement0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(6);
        }
        return new ClassConstructorDescriptorImpl(classDescriptor0, null, annotations0, z, Kind.DECLARATION, sourceElement0);
    }

    protected ClassConstructorDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(23);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(24);
        }
        if(annotations0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(25);
        }
        if(sourceElement0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(26);
        }
        if(callableMemberDescriptor$Kind0 != Kind.DECLARATION && callableMemberDescriptor$Kind0 != Kind.SYNTHESIZED) {
            throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + declarationDescriptor0 + "\nkind: " + callableMemberDescriptor$Kind0);
        }
        return new ClassConstructorDescriptorImpl(((ClassDescriptor)declarationDescriptor0), this, annotations0, this.isPrimary, Kind.DECLARATION, sourceElement0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    protected FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        return this.createSubstitutedCopy(declarationDescriptor0, functionDescriptor0, callableMemberDescriptor$Kind0, name0, annotations0, sourceElement0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    public ClassDescriptor getConstructedClass() {
        ClassDescriptor classDescriptor0 = this.getContainingDeclaration();
        if(classDescriptor0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(18);
        }
        return classDescriptor0;
    }

    public ClassDescriptor getContainingDeclaration() {
        ClassDescriptor classDescriptor0 = (ClassDescriptor)super.getContainingDeclaration();
        if(classDescriptor0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(17);
        }
        return classDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    public ClassifierDescriptorWithTypeParameters getContainingDeclaration() {
        return this.getContainingDeclaration();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return this.getContainingDeclaration();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public CallableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor
    public ClassConstructorDescriptor getOriginal() {
        ClassConstructorDescriptor classConstructorDescriptor0 = (ClassConstructorDescriptor)super.getOriginal();
        if(classConstructorDescriptor0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(19);
        }
        return classConstructorDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public DeclarationDescriptorWithSource getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public Collection getOverriddenDescriptors() {
        Collection collection0 = Collections.EMPTY_SET;
        if(collection0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(21);
        }
        return collection0;
    }

    public ClassConstructorDescriptorImpl initialize(List list0, DescriptorVisibility descriptorVisibility0) {
        if(list0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(13);
        }
        if(descriptorVisibility0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(14);
        }
        this.initialize(list0, descriptorVisibility0, this.getContainingDeclaration().getDeclaredTypeParameters());
        return this;
    }

    public ClassConstructorDescriptorImpl initialize(List list0, DescriptorVisibility descriptorVisibility0, List list1) {
        if(list0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(10);
        }
        if(descriptorVisibility0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(11);
        }
        if(list1 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(12);
        }
        super.initialize(null, this.calculateDispatchReceiverParameter(), this.calculateContextReceiverParameters(), list1, list0, null, Modality.FINAL, descriptorVisibility0);
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    public boolean isPrimary() {
        return this.isPrimary;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(Collection collection0) {
        if(collection0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(22);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor
    public ClassConstructorDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        if(typeSubstitutor0 == null) {
            ClassConstructorDescriptorImpl.$$$reportNull$$$0(20);
        }
        return (ClassConstructorDescriptor)super.substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor
    public ConstructorDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }
}

