package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.util.OperatorChecks;

public class JavaMethodDescriptor extends SimpleFunctionDescriptorImpl implements JavaCallableMemberDescriptor {
    static enum ParameterNamesStatus {
        NON_STABLE_DECLARED(false, false),
        STABLE_DECLARED(true, false),
        NON_STABLE_SYNTHESIZED(false, true),
        STABLE_SYNTHESIZED(true, true);

        public final boolean isStable;
        public final boolean isSynthesized;

        // 去混淆评级： 低(20)
        private static void $$$reportNull$$$0(int v) {
            throw new IllegalStateException("@NotNull method kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor$ParameterNamesStatus.get must not return null");
        }

        private ParameterNamesStatus(boolean z, boolean z1) {
            this.isStable = z;
            this.isSynthesized = z1;
        }

        public static ParameterNamesStatus get(boolean z, boolean z1) {
            ParameterNamesStatus javaMethodDescriptor$ParameterNamesStatus0;
            if(!z) {
                javaMethodDescriptor$ParameterNamesStatus0 = z1 ? ParameterNamesStatus.NON_STABLE_SYNTHESIZED : ParameterNamesStatus.NON_STABLE_DECLARED;
            }
            else if(z1) {
                javaMethodDescriptor$ParameterNamesStatus0 = ParameterNamesStatus.STABLE_SYNTHESIZED;
            }
            else {
                javaMethodDescriptor$ParameterNamesStatus0 = ParameterNamesStatus.STABLE_DECLARED;
            }
            if(javaMethodDescriptor$ParameterNamesStatus0 == null) {
                ParameterNamesStatus.$$$reportNull$$$0(0);
            }
            return javaMethodDescriptor$ParameterNamesStatus0;
        }
    }

    static final boolean $assertionsDisabled;
    public static final UserDataKey HAS_ERASED_VALUE_PARAMETERS;
    public static final UserDataKey ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER;
    private final boolean isForRecordComponent;
    private ParameterNamesStatus parameterNamesStatus;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 13 || v == 18 || v == 21 ? 2 : 3)];
        switch(v) {
            case 2: 
            case 7: {
                arr_object[0] = "name";
                break;
            }
            case 9: {
                arr_object[0] = "contextReceiverParameters";
                break;
            }
            case 10: {
                arr_object[0] = "typeParameters";
                break;
            }
            case 11: {
                arr_object[0] = "unsubstitutedValueParameters";
                break;
            }
            case 12: {
                arr_object[0] = "visibility";
                break;
            }
            case 14: {
                arr_object[0] = "newOwner";
                break;
            }
            case 3: 
            case 15: {
                arr_object[0] = "kind";
                break;
            }
            case 1: 
            case 6: 
            case 16: {
                arr_object[0] = "annotations";
                break;
            }
            case 4: 
            case 8: 
            case 17: {
                arr_object[0] = "source";
                break;
            }
            case 19: {
                arr_object[0] = "enhancedValueParameterTypes";
                break;
            }
            case 20: {
                arr_object[0] = "enhancedReturnType";
                break;
            }
            case 13: 
            case 18: 
            case 21: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 13: {
                arr_object[1] = "initialize";
                break;
            }
            case 18: {
                arr_object[1] = "createSubstitutedCopy";
                break;
            }
            case 21: {
                arr_object[1] = "enhance";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaMethodDescriptor";
            }
        }
        switch(v) {
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                arr_object[2] = "createJavaMethod";
                break;
            }
            case 9: 
            case 10: 
            case 11: 
            case 12: {
                arr_object[2] = "initialize";
                break;
            }
            case 14: 
            case 15: 
            case 16: 
            case 17: {
                arr_object[2] = "createSubstitutedCopy";
                break;
            }
            case 19: 
            case 20: {
                arr_object[2] = "enhance";
                break;
            }
            case 13: 
            case 18: 
            case 21: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s = String.format((v == 13 || v == 18 || v == 21 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 13 || v == 18 || v == 21 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    static {
        JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER = new UserDataKey() {
        };
        JavaMethodDescriptor.HAS_ERASED_VALUE_PARAMETERS = new UserDataKey() {
        };
    }

    protected JavaMethodDescriptor(DeclarationDescriptor declarationDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor0, Annotations annotations0, Name name0, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0, boolean z) {
        if(declarationDescriptor0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(1);
        }
        if(name0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(2);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(3);
        }
        if(sourceElement0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(4);
        }
        super(declarationDescriptor0, simpleFunctionDescriptor0, annotations0, name0, callableMemberDescriptor$Kind0, sourceElement0);
        this.parameterNamesStatus = null;
        this.isForRecordComponent = z;
    }

    public static JavaMethodDescriptor createJavaMethod(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Name name0, SourceElement sourceElement0, boolean z) {
        if(declarationDescriptor0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(5);
        }
        if(annotations0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(6);
        }
        if(name0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(7);
        }
        if(sourceElement0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(8);
        }
        return new JavaMethodDescriptor(declarationDescriptor0, null, annotations0, name0, Kind.DECLARATION, sourceElement0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    protected FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        return this.createSubstitutedCopy(declarationDescriptor0, functionDescriptor0, callableMemberDescriptor$Kind0, name0, annotations0, sourceElement0);
    }

    protected JavaMethodDescriptor createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(14);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(15);
        }
        if(annotations0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(16);
        }
        if(sourceElement0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(17);
        }
        if(name0 == null) {
            name0 = this.getName();
        }
        JavaMethodDescriptor javaMethodDescriptor0 = new JavaMethodDescriptor(declarationDescriptor0, ((SimpleFunctionDescriptor)functionDescriptor0), annotations0, name0, callableMemberDescriptor$Kind0, sourceElement0, this.isForRecordComponent);
        javaMethodDescriptor0.setParameterNamesStatus(this.hasStableParameterNames(), this.hasSynthesizedParameterNames());
        return javaMethodDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor
    public JavaCallableMemberDescriptor enhance(KotlinType kotlinType0, List list0, KotlinType kotlinType1, Pair pair0) {
        return this.enhance(kotlinType0, list0, kotlinType1, pair0);
    }

    public JavaMethodDescriptor enhance(KotlinType kotlinType0, List list0, KotlinType kotlinType1, Pair pair0) {
        if(list0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(19);
        }
        if(kotlinType1 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(20);
        }
        List list1 = UtilKt.copyValueParameters(list0, this.getValueParameters(), this);
        ReceiverParameterDescriptor receiverParameterDescriptor0 = kotlinType0 == null ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(this, kotlinType0, Annotations.Companion.getEMPTY());
        JavaMethodDescriptor javaMethodDescriptor0 = (JavaMethodDescriptor)this.newCopyBuilder().setValueParameters(list1).setReturnType(kotlinType1).setExtensionReceiverParameter(receiverParameterDescriptor0).setDropOriginalInContainingParts().setPreserveSourceElement().build();
        if(pair0 != null) {
            javaMethodDescriptor0.putInUserDataMap(((UserDataKey)pair0.getFirst()), pair0.getSecond());
        }
        if(javaMethodDescriptor0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(21);
        }
        return javaMethodDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public boolean hasStableParameterNames() {
        return this.parameterNamesStatus.isStable;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public boolean hasSynthesizedParameterNames() {
        return this.parameterNamesStatus.isSynthesized;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    public SimpleFunctionDescriptorImpl initialize(ReceiverParameterDescriptor receiverParameterDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor1, List list0, List list1, List list2, KotlinType kotlinType0, Modality modality0, DescriptorVisibility descriptorVisibility0, Map map0) {
        if(list0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(9);
        }
        if(list1 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(10);
        }
        if(list2 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(11);
        }
        if(descriptorVisibility0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(12);
        }
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImpl0 = super.initialize(receiverParameterDescriptor0, receiverParameterDescriptor1, list0, list1, list2, kotlinType0, modality0, descriptorVisibility0, map0);
        this.setOperator(OperatorChecks.INSTANCE.check(simpleFunctionDescriptorImpl0).isSuccess());
        if(simpleFunctionDescriptorImpl0 == null) {
            JavaMethodDescriptor.$$$reportNull$$$0(13);
        }
        return simpleFunctionDescriptorImpl0;
    }

    public void setParameterNamesStatus(boolean z, boolean z1) {
        this.parameterNamesStatus = ParameterNamesStatus.get(z, z1);
    }
}

