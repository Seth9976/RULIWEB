package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public class JavaClassConstructorDescriptor extends ClassConstructorDescriptorImpl implements JavaCallableMemberDescriptor {
    static final boolean $assertionsDisabled;
    private Boolean hasStableParameterNames;
    private Boolean hasSynthesizedParameterNames;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 11 || v == 18 ? 2 : 3)];
        switch(v) {
            case 3: 
            case 6: 
            case 10: {
                arr_object[0] = "source";
                break;
            }
            case 7: 
            case 12: {
                arr_object[0] = "newOwner";
                break;
            }
            case 2: 
            case 8: 
            case 13: {
                arr_object[0] = "kind";
                break;
            }
            case 14: {
                arr_object[0] = "sourceElement";
                break;
            }
            case 1: 
            case 5: 
            case 9: 
            case 15: {
                arr_object[0] = "annotations";
                break;
            }
            case 16: {
                arr_object[0] = "enhancedValueParameterTypes";
                break;
            }
            case 17: {
                arr_object[0] = "enhancedReturnType";
                break;
            }
            case 11: 
            case 18: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 11: {
                arr_object[1] = "createSubstitutedCopy";
                break;
            }
            case 18: {
                arr_object[1] = "enhance";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaClassConstructorDescriptor";
            }
        }
        switch(v) {
            case 4: 
            case 5: 
            case 6: {
                arr_object[2] = "createJavaConstructor";
                break;
            }
            case 7: 
            case 8: 
            case 9: 
            case 10: {
                arr_object[2] = "createSubstitutedCopy";
                break;
            }
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                arr_object[2] = "createDescriptor";
                break;
            }
            case 16: 
            case 17: {
                arr_object[2] = "enhance";
                break;
            }
            case 11: 
            case 18: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s = String.format((v == 11 || v == 18 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 11 || v == 18 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    static {
    }

    protected JavaClassConstructorDescriptor(ClassDescriptor classDescriptor0, JavaClassConstructorDescriptor javaClassConstructorDescriptor0, Annotations annotations0, boolean z, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0) {
        if(classDescriptor0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(1);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(2);
        }
        if(sourceElement0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(3);
        }
        super(classDescriptor0, javaClassConstructorDescriptor0, annotations0, z, callableMemberDescriptor$Kind0, sourceElement0);
        this.hasStableParameterNames = null;
        this.hasSynthesizedParameterNames = null;
    }

    protected JavaClassConstructorDescriptor createDescriptor(ClassDescriptor classDescriptor0, JavaClassConstructorDescriptor javaClassConstructorDescriptor0, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0, Annotations annotations0) {
        if(classDescriptor0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(12);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(13);
        }
        if(sourceElement0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(14);
        }
        if(annotations0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(15);
        }
        return new JavaClassConstructorDescriptor(classDescriptor0, javaClassConstructorDescriptor0, annotations0, this.isPrimary, callableMemberDescriptor$Kind0, sourceElement0);
    }

    public static JavaClassConstructorDescriptor createJavaConstructor(ClassDescriptor classDescriptor0, Annotations annotations0, boolean z, SourceElement sourceElement0) {
        if(classDescriptor0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(4);
        }
        if(annotations0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(5);
        }
        if(sourceElement0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(6);
        }
        return new JavaClassConstructorDescriptor(classDescriptor0, null, annotations0, z, Kind.DECLARATION, sourceElement0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl
    protected ClassConstructorDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        return this.createSubstitutedCopy(declarationDescriptor0, functionDescriptor0, callableMemberDescriptor$Kind0, name0, annotations0, sourceElement0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl
    protected FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        return this.createSubstitutedCopy(declarationDescriptor0, functionDescriptor0, callableMemberDescriptor$Kind0, name0, annotations0, sourceElement0);
    }

    protected JavaClassConstructorDescriptor createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(7);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(8);
        }
        if(annotations0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(9);
        }
        if(sourceElement0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(10);
        }
        if(callableMemberDescriptor$Kind0 != Kind.DECLARATION && callableMemberDescriptor$Kind0 != Kind.SYNTHESIZED) {
            throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + declarationDescriptor0 + "\nkind: " + callableMemberDescriptor$Kind0);
        }
        JavaClassConstructorDescriptor javaClassConstructorDescriptor0 = this.createDescriptor(((ClassDescriptor)declarationDescriptor0), ((JavaClassConstructorDescriptor)functionDescriptor0), callableMemberDescriptor$Kind0, sourceElement0, annotations0);
        javaClassConstructorDescriptor0.setHasStableParameterNames(this.hasStableParameterNames());
        javaClassConstructorDescriptor0.setHasSynthesizedParameterNames(this.hasSynthesizedParameterNames());
        return javaClassConstructorDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor
    public JavaCallableMemberDescriptor enhance(KotlinType kotlinType0, List list0, KotlinType kotlinType1, Pair pair0) {
        return this.enhance(kotlinType0, list0, kotlinType1, pair0);
    }

    public JavaClassConstructorDescriptor enhance(KotlinType kotlinType0, List list0, KotlinType kotlinType1, Pair pair0) {
        if(list0 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(16);
        }
        if(kotlinType1 == null) {
            JavaClassConstructorDescriptor.$$$reportNull$$$0(17);
        }
        JavaClassConstructorDescriptor javaClassConstructorDescriptor0 = this.createSubstitutedCopy(this.getContainingDeclaration(), null, this.getKind(), null, this.getAnnotations(), this.getSource());
        javaClassConstructorDescriptor0.initialize((kotlinType0 == null ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(javaClassConstructorDescriptor0, kotlinType0, Annotations.Companion.getEMPTY())), this.getDispatchReceiverParameter(), CollectionsKt.emptyList(), this.getTypeParameters(), UtilKt.copyValueParameters(list0, this.getValueParameters(), javaClassConstructorDescriptor0), kotlinType1, this.getModality(), this.getVisibility());
        if(pair0 != null) {
            javaClassConstructorDescriptor0.putInUserDataMap(((UserDataKey)pair0.getFirst()), pair0.getSecond());
        }
        return javaClassConstructorDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public boolean hasStableParameterNames() {
        return this.hasStableParameterNames.booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public boolean hasSynthesizedParameterNames() {
        return this.hasSynthesizedParameterNames.booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public void setHasStableParameterNames(boolean z) {
        this.hasStableParameterNames = Boolean.valueOf(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    public void setHasSynthesizedParameterNames(boolean z) {
        this.hasSynthesizedParameterNames = Boolean.valueOf(z);
    }
}

