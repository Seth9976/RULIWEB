package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public class SimpleFunctionDescriptorImpl extends FunctionDescriptorImpl implements SimpleFunctionDescriptor {
    private static void $$$reportNull$$$0(int v) {
        IllegalStateException illegalStateException0;
        int v1;
        String s;
        switch(v) {
            case 13: 
            case 18: 
            case 23: 
            case 24: 
            case 29: 
            case 30: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 13: 
            case 18: 
            case 23: 
            case 24: 
            case 29: 
            case 30: {
                v1 = 2;
                break;
            }
            default: {
                v1 = 3;
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 2: 
            case 7: {
                arr_object[0] = "name";
                break;
            }
            case 14: 
            case 19: {
                arr_object[0] = "contextReceiverParameters";
                break;
            }
            case 10: 
            case 15: 
            case 20: {
                arr_object[0] = "typeParameters";
                break;
            }
            case 11: 
            case 16: 
            case 21: {
                arr_object[0] = "unsubstitutedValueParameters";
                break;
            }
            case 12: 
            case 17: 
            case 22: {
                arr_object[0] = "visibility";
                break;
            }
            case 25: {
                arr_object[0] = "newOwner";
                break;
            }
            case 3: 
            case 8: 
            case 26: {
                arr_object[0] = "kind";
                break;
            }
            case 1: 
            case 6: 
            case 27: {
                arr_object[0] = "annotations";
                break;
            }
            case 4: 
            case 9: 
            case 28: {
                arr_object[0] = "source";
                break;
            }
            case 13: 
            case 18: 
            case 23: 
            case 24: 
            case 29: 
            case 30: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 13: 
            case 18: 
            case 23: {
                arr_object[1] = "initialize";
                break;
            }
            case 24: {
                arr_object[1] = "getOriginal";
                break;
            }
            case 29: {
                arr_object[1] = "copy";
                break;
            }
            case 30: {
                arr_object[1] = "newCopyBuilder";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/SimpleFunctionDescriptorImpl";
            }
        }
        switch(v) {
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: {
                arr_object[2] = "create";
                break;
            }
            case 10: 
            case 11: 
            case 12: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 19: 
            case 20: 
            case 21: 
            case 22: {
                arr_object[2] = "initialize";
                break;
            }
            case 25: 
            case 26: 
            case 27: 
            case 28: {
                arr_object[2] = "createSubstitutedCopy";
                break;
            }
            case 13: 
            case 18: 
            case 23: 
            case 24: 
            case 29: 
            case 30: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 13: 
            case 18: 
            case 23: 
            case 24: 
            case 29: 
            case 30: {
                illegalStateException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalStateException0 = new IllegalArgumentException(s1);
            }
        }
        throw illegalStateException0;
    }

    protected SimpleFunctionDescriptorImpl(DeclarationDescriptor declarationDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor0, Annotations annotations0, Name name0, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(name0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(2);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(3);
        }
        if(sourceElement0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(4);
        }
        super(declarationDescriptor0, simpleFunctionDescriptor0, annotations0, name0, callableMemberDescriptor$Kind0, sourceElement0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public FunctionDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    public SimpleFunctionDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        SimpleFunctionDescriptor simpleFunctionDescriptor0 = (SimpleFunctionDescriptor)super.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
        if(simpleFunctionDescriptor0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(29);
        }
        return simpleFunctionDescriptor0;
    }

    public static SimpleFunctionDescriptorImpl create(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Name name0, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(5);
        }
        if(annotations0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(6);
        }
        if(name0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(7);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(8);
        }
        if(sourceElement0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(9);
        }
        return new SimpleFunctionDescriptorImpl(declarationDescriptor0, null, annotations0, name0, callableMemberDescriptor$Kind0, sourceElement0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    protected FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(25);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(26);
        }
        if(annotations0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(27);
        }
        if(sourceElement0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(28);
        }
        if(name0 == null) {
            name0 = this.getName();
        }
        return new SimpleFunctionDescriptorImpl(declarationDescriptor0, ((SimpleFunctionDescriptor)functionDescriptor0), annotations0, name0, callableMemberDescriptor$Kind0, sourceElement0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public CallableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor getOriginal() {
        return this.getOriginal();
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

    public SimpleFunctionDescriptor getOriginal() {
        SimpleFunctionDescriptor simpleFunctionDescriptor0 = (SimpleFunctionDescriptor)super.getOriginal();
        if(simpleFunctionDescriptor0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(24);
        }
        return simpleFunctionDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public FunctionDescriptorImpl initialize(ReceiverParameterDescriptor receiverParameterDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor1, List list0, List list1, List list2, KotlinType kotlinType0, Modality modality0, DescriptorVisibility descriptorVisibility0) {
        return this.initialize(receiverParameterDescriptor0, receiverParameterDescriptor1, list0, list1, list2, kotlinType0, modality0, descriptorVisibility0);
    }

    public SimpleFunctionDescriptorImpl initialize(ReceiverParameterDescriptor receiverParameterDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor1, List list0, List list1, List list2, KotlinType kotlinType0, Modality modality0, DescriptorVisibility descriptorVisibility0) {
        if(list0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(14);
        }
        if(list1 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(15);
        }
        if(list2 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(16);
        }
        if(descriptorVisibility0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(17);
        }
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImpl0 = this.initialize(receiverParameterDescriptor0, receiverParameterDescriptor1, list0, list1, list2, kotlinType0, modality0, descriptorVisibility0, null);
        if(simpleFunctionDescriptorImpl0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(18);
        }
        return simpleFunctionDescriptorImpl0;
    }

    public SimpleFunctionDescriptorImpl initialize(ReceiverParameterDescriptor receiverParameterDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor1, List list0, List list1, List list2, KotlinType kotlinType0, Modality modality0, DescriptorVisibility descriptorVisibility0, Map map0) {
        if(list0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(19);
        }
        if(list1 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(20);
        }
        if(list2 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(21);
        }
        if(descriptorVisibility0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(22);
        }
        super.initialize(receiverParameterDescriptor0, receiverParameterDescriptor1, list0, list1, list2, kotlinType0, modality0, descriptorVisibility0);
        if(map0 != null && !map0.isEmpty()) {
            this.userDataMap = new LinkedHashMap(map0);
        }
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor
    public CopyBuilder newCopyBuilder() {
        CopyBuilder functionDescriptor$CopyBuilder0 = super.newCopyBuilder();
        if(functionDescriptor$CopyBuilder0 == null) {
            SimpleFunctionDescriptorImpl.$$$reportNull$$$0(30);
        }
        return functionDescriptor$CopyBuilder0;
    }
}

