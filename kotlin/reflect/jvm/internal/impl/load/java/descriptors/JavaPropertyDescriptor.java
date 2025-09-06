package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstUtil;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public class JavaPropertyDescriptor extends PropertyDescriptorImpl implements JavaCallableMemberDescriptor {
    private KotlinType inType;
    private final boolean isStaticFinal;
    private final Pair singleUserData;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 21 ? 2 : 3)];
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
            case 3: 
            case 10: {
                arr_object[0] = "visibility";
                break;
            }
            case 4: 
            case 11: {
                arr_object[0] = "name";
                break;
            }
            case 13: {
                arr_object[0] = "newOwner";
                break;
            }
            case 14: {
                arr_object[0] = "newModality";
                break;
            }
            case 15: {
                arr_object[0] = "newVisibility";
                break;
            }
            case 6: 
            case 16: {
                arr_object[0] = "kind";
                break;
            }
            case 17: {
                arr_object[0] = "newName";
                break;
            }
            case 5: 
            case 12: 
            case 18: {
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
            case 21: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaPropertyDescriptor";
                break;
            }
            case 22: {
                arr_object[0] = "inType";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        arr_object[1] = v == 21 ? "enhance" : "kotlin/reflect/jvm/internal/impl/load/java/descriptors/JavaPropertyDescriptor";
        switch(v) {
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: {
                arr_object[2] = "create";
                break;
            }
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: {
                arr_object[2] = "createSubstitutedCopy";
                break;
            }
            case 19: 
            case 20: {
                arr_object[2] = "enhance";
                break;
            }
            case 21: {
                break;
            }
            case 22: {
                arr_object[2] = "setInType";
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s = String.format((v == 21 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalArgumentException illegalArgumentException0 = v == 21 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalArgumentException0;
    }

    protected JavaPropertyDescriptor(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Modality modality0, DescriptorVisibility descriptorVisibility0, boolean z, Name name0, SourceElement sourceElement0, PropertyDescriptor propertyDescriptor0, Kind callableMemberDescriptor$Kind0, boolean z1, Pair pair0) {
        if(declarationDescriptor0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(1);
        }
        if(modality0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(2);
        }
        if(descriptorVisibility0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(3);
        }
        if(name0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(4);
        }
        if(sourceElement0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(5);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(6);
        }
        super(declarationDescriptor0, propertyDescriptor0, annotations0, modality0, descriptorVisibility0, z, name0, callableMemberDescriptor$Kind0, sourceElement0, false, false, false, false, false, false);
        this.inType = null;
        this.isStaticFinal = z1;
        this.singleUserData = pair0;
    }

    public static JavaPropertyDescriptor create(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Modality modality0, DescriptorVisibility descriptorVisibility0, boolean z, Name name0, SourceElement sourceElement0, boolean z1) {
        if(declarationDescriptor0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(7);
        }
        if(annotations0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(8);
        }
        if(modality0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(9);
        }
        if(descriptorVisibility0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(10);
        }
        if(name0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(11);
        }
        if(sourceElement0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(12);
        }
        return new JavaPropertyDescriptor(declarationDescriptor0, annotations0, modality0, descriptorVisibility0, z, name0, sourceElement0, null, Kind.DECLARATION, z1, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl
    protected PropertyDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, PropertyDescriptor propertyDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(13);
        }
        if(modality0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(14);
        }
        if(descriptorVisibility0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(15);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(16);
        }
        if(name0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(17);
        }
        if(sourceElement0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(18);
        }
        return new JavaPropertyDescriptor(declarationDescriptor0, this.getAnnotations(), modality0, descriptorVisibility0, this.isVar(), name0, sourceElement0, propertyDescriptor0, callableMemberDescriptor$Kind0, this.isStaticFinal, this.singleUserData);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor
    public JavaCallableMemberDescriptor enhance(KotlinType kotlinType0, List list0, KotlinType kotlinType1, Pair pair0) {
        PropertySetterDescriptorImpl propertySetterDescriptorImpl0;
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl2;
        if(list0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(19);
        }
        if(kotlinType1 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(20);
        }
        ReceiverParameterDescriptor receiverParameterDescriptor0 = null;
        PropertyDescriptor propertyDescriptor0 = this.getOriginal() == this ? null : this.getOriginal();
        JavaPropertyDescriptor javaPropertyDescriptor0 = new JavaPropertyDescriptor(this.getContainingDeclaration(), this.getAnnotations(), this.getModality(), this.getVisibility(), this.isVar(), this.getName(), this.getSource(), propertyDescriptor0, this.getKind(), this.isStaticFinal, pair0);
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0 = this.getGetter();
        if(propertyGetterDescriptorImpl0 == null) {
            propertyGetterDescriptorImpl2 = null;
        }
        else {
            PropertyGetterDescriptorImpl propertyGetterDescriptorImpl1 = new PropertyGetterDescriptorImpl(javaPropertyDescriptor0, propertyGetterDescriptorImpl0.getAnnotations(), propertyGetterDescriptorImpl0.getModality(), propertyGetterDescriptorImpl0.getVisibility(), propertyGetterDescriptorImpl0.isDefault(), propertyGetterDescriptorImpl0.isExternal(), propertyGetterDescriptorImpl0.isInline(), this.getKind(), (propertyDescriptor0 == null ? null : propertyDescriptor0.getGetter()), propertyGetterDescriptorImpl0.getSource());
            propertyGetterDescriptorImpl1.setInitialSignatureDescriptor(propertyGetterDescriptorImpl0.getInitialSignatureDescriptor());
            propertyGetterDescriptorImpl1.initialize(kotlinType1);
            propertyGetterDescriptorImpl2 = propertyGetterDescriptorImpl1;
        }
        PropertySetterDescriptor propertySetterDescriptor0 = this.getSetter();
        if(propertySetterDescriptor0 == null) {
            propertySetterDescriptorImpl0 = null;
        }
        else {
            propertySetterDescriptorImpl0 = new PropertySetterDescriptorImpl(javaPropertyDescriptor0, propertySetterDescriptor0.getAnnotations(), propertySetterDescriptor0.getModality(), propertySetterDescriptor0.getVisibility(), propertySetterDescriptor0.isDefault(), propertySetterDescriptor0.isExternal(), propertySetterDescriptor0.isInline(), this.getKind(), (propertyDescriptor0 == null ? null : propertyDescriptor0.getSetter()), propertySetterDescriptor0.getSource());
            propertySetterDescriptorImpl0.setInitialSignatureDescriptor(propertySetterDescriptorImpl0.getInitialSignatureDescriptor());
            propertySetterDescriptorImpl0.initialize(((ValueParameterDescriptor)propertySetterDescriptor0.getValueParameters().get(0)));
        }
        javaPropertyDescriptor0.initialize(propertyGetterDescriptorImpl2, propertySetterDescriptorImpl0, this.getBackingField(), this.getDelegateField());
        javaPropertyDescriptor0.setSetterProjectedOut(this.isSetterProjectedOut());
        if(this.compileTimeInitializerFactory != null) {
            javaPropertyDescriptor0.setCompileTimeInitializer(this.compileTimeInitializer, this.compileTimeInitializerFactory);
        }
        javaPropertyDescriptor0.setOverriddenDescriptors(this.getOverriddenDescriptors());
        if(kotlinType0 != null) {
            receiverParameterDescriptor0 = DescriptorFactory.createExtensionReceiverParameterForCallable(this, kotlinType0, Annotations.Companion.getEMPTY());
        }
        javaPropertyDescriptor0.setType(kotlinType1, this.getTypeParameters(), this.getDispatchReceiverParameter(), receiverParameterDescriptor0, CollectionsKt.emptyList());
        return javaPropertyDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public Object getUserData(UserDataKey callableDescriptor$UserDataKey0) {
        return this.singleUserData == null || !((UserDataKey)this.singleUserData.getFirst()).equals(callableDescriptor$UserDataKey0) ? null : this.singleUserData.getSecond();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public boolean hasSynthesizedParameterNames() {
        return false;
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl
    public boolean isConst() {
        KotlinType kotlinType0 = this.getType();
        return this.isStaticFinal && ConstUtil.canBeUsedForConstVal(kotlinType0) && (!TypeEnhancementKt.hasEnhancedNullability(kotlinType0) || KotlinBuiltIns.isString(kotlinType0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl
    public void setInType(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            JavaPropertyDescriptor.$$$reportNull$$$0(22);
        }
        this.inType = kotlinType0;
    }
}

