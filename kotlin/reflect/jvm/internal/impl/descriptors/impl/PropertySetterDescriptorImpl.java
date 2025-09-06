package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public class PropertySetterDescriptorImpl extends PropertyAccessorDescriptorImpl implements PropertySetterDescriptor {
    static final boolean $assertionsDisabled;
    private final PropertySetterDescriptor original;
    private ValueParameterDescriptor parameter;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 10 || v == 11 || v == 12 || v == 13 ? 2 : 3)];
        switch(v) {
            case 2: {
                arr_object[0] = "modality";
                break;
            }
            case 3: {
                arr_object[0] = "visibility";
                break;
            }
            case 4: {
                arr_object[0] = "kind";
                break;
            }
            case 5: {
                arr_object[0] = "source";
                break;
            }
            case 6: {
                arr_object[0] = "parameter";
                break;
            }
            case 7: {
                arr_object[0] = "setterDescriptor";
                break;
            }
            case 8: {
                arr_object[0] = "type";
                break;
            }
            case 1: 
            case 9: {
                arr_object[0] = "annotations";
                break;
            }
            case 10: 
            case 11: 
            case 12: 
            case 13: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertySetterDescriptorImpl";
                break;
            }
            default: {
                arr_object[0] = "correspondingProperty";
            }
        }
        switch(v) {
            case 10: {
                arr_object[1] = "getOverriddenDescriptors";
                break;
            }
            case 11: {
                arr_object[1] = "getValueParameters";
                break;
            }
            case 12: {
                arr_object[1] = "getReturnType";
                break;
            }
            case 13: {
                arr_object[1] = "getOriginal";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertySetterDescriptorImpl";
            }
        }
        switch(v) {
            case 6: {
                arr_object[2] = "initialize";
                break;
            }
            case 7: 
            case 8: 
            case 9: {
                arr_object[2] = "createSetterParameter";
                break;
            }
            case 10: 
            case 11: 
            case 12: 
            case 13: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s = String.format((v == 10 || v == 11 || v == 12 || v == 13 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalArgumentException illegalArgumentException0 = v == 10 || v == 11 || v == 12 || v == 13 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalArgumentException0;
    }

    static {
    }

    public PropertySetterDescriptorImpl(PropertyDescriptor propertyDescriptor0, Annotations annotations0, Modality modality0, DescriptorVisibility descriptorVisibility0, boolean z, boolean z1, boolean z2, Kind callableMemberDescriptor$Kind0, PropertySetterDescriptor propertySetterDescriptor0, SourceElement sourceElement0) {
        if(propertyDescriptor0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(modality0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(2);
        }
        if(descriptorVisibility0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(3);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(4);
        }
        if(sourceElement0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(5);
        }
        super(modality0, descriptorVisibility0, propertyDescriptor0, annotations0, Name.special(("<set-" + propertyDescriptor0.getName() + ">")), z, z1, z2, callableMemberDescriptor$Kind0, sourceElement0);
        PropertySetterDescriptor propertySetterDescriptor1 = propertySetterDescriptor0 == null ? this : propertySetterDescriptor0;
        this.original = propertySetterDescriptor1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return declarationDescriptorVisitor0.visitPropertySetterDescriptor(this, object0);
    }

    public static ValueParameterDescriptorImpl createSetterParameter(PropertySetterDescriptor propertySetterDescriptor0, KotlinType kotlinType0, Annotations annotations0) {
        if(propertySetterDescriptor0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(7);
        }
        if(kotlinType0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(8);
        }
        if(annotations0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(9);
        }
        return new ValueParameterDescriptorImpl(propertySetterDescriptor0, null, 0, annotations0, SpecialNames.IMPLICIT_SET_PARAMETER, kotlinType0, false, false, false, null, SourceElement.NO_SOURCE);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public CallableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl
    public DeclarationDescriptorWithSource getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl
    public PropertyAccessorDescriptor getOriginal() {
        return this.getOriginal();
    }

    public PropertySetterDescriptor getOriginal() {
        PropertySetterDescriptor propertySetterDescriptor0 = this.original;
        if(propertySetterDescriptor0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(13);
        }
        return propertySetterDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public Collection getOverriddenDescriptors() {
        Collection collection0 = super.getOverriddenDescriptors(false);
        if(collection0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(10);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public KotlinType getReturnType() {
        KotlinType kotlinType0 = DescriptorUtilsKt.getBuiltIns(this).getUnitType();
        if(kotlinType0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(12);
        }
        return kotlinType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getValueParameters() {
        ValueParameterDescriptor valueParameterDescriptor0 = this.parameter;
        if(valueParameterDescriptor0 == null) {
            throw new IllegalStateException();
        }
        List list0 = Collections.singletonList(valueParameterDescriptor0);
        if(list0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(11);
        }
        return list0;
    }

    public void initialize(ValueParameterDescriptor valueParameterDescriptor0) {
        if(valueParameterDescriptor0 == null) {
            PropertySetterDescriptorImpl.$$$reportNull$$$0(6);
        }
        this.parameter = valueParameterDescriptor0;
    }
}

