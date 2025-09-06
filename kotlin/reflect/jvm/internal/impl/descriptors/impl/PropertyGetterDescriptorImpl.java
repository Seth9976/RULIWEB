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
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public class PropertyGetterDescriptorImpl extends PropertyAccessorDescriptorImpl implements PropertyGetterDescriptor {
    private final PropertyGetterDescriptor original;
    private KotlinType returnType;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 6 || v == 7 || v == 8 ? 2 : 3)];
        switch(v) {
            case 1: {
                arr_object[0] = "annotations";
                break;
            }
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
            case 6: 
            case 7: 
            case 8: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyGetterDescriptorImpl";
                break;
            }
            default: {
                arr_object[0] = "correspondingProperty";
            }
        }
        switch(v) {
            case 6: {
                arr_object[1] = "getOverriddenDescriptors";
                break;
            }
            case 7: {
                arr_object[1] = "getValueParameters";
                break;
            }
            case 8: {
                arr_object[1] = "getOriginal";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyGetterDescriptorImpl";
            }
        }
        if(v != 6 && v != 7 && v != 8) {
            arr_object[2] = "<init>";
        }
        String s = String.format((v == 6 || v == 7 || v == 8 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 6 || v == 7 || v == 8 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    public PropertyGetterDescriptorImpl(PropertyDescriptor propertyDescriptor0, Annotations annotations0, Modality modality0, DescriptorVisibility descriptorVisibility0, boolean z, boolean z1, boolean z2, Kind callableMemberDescriptor$Kind0, PropertyGetterDescriptor propertyGetterDescriptor0, SourceElement sourceElement0) {
        if(propertyDescriptor0 == null) {
            PropertyGetterDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            PropertyGetterDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(modality0 == null) {
            PropertyGetterDescriptorImpl.$$$reportNull$$$0(2);
        }
        if(descriptorVisibility0 == null) {
            PropertyGetterDescriptorImpl.$$$reportNull$$$0(3);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            PropertyGetterDescriptorImpl.$$$reportNull$$$0(4);
        }
        if(sourceElement0 == null) {
            PropertyGetterDescriptorImpl.$$$reportNull$$$0(5);
        }
        super(modality0, descriptorVisibility0, propertyDescriptor0, annotations0, Name.special(("<get-" + propertyDescriptor0.getName() + ">")), z, z1, z2, callableMemberDescriptor$Kind0, sourceElement0);
        PropertyGetterDescriptor propertyGetterDescriptor1 = propertyGetterDescriptor0 == null ? this : propertyGetterDescriptor0;
        this.original = propertyGetterDescriptor1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return declarationDescriptorVisitor0.visitPropertyGetterDescriptor(this, object0);
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

    public PropertyGetterDescriptor getOriginal() {
        PropertyGetterDescriptor propertyGetterDescriptor0 = this.original;
        if(propertyGetterDescriptor0 == null) {
            PropertyGetterDescriptorImpl.$$$reportNull$$$0(8);
        }
        return propertyGetterDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public Collection getOverriddenDescriptors() {
        Collection collection0 = super.getOverriddenDescriptors(true);
        if(collection0 == null) {
            PropertyGetterDescriptorImpl.$$$reportNull$$$0(6);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public KotlinType getReturnType() {
        return this.returnType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getValueParameters() {
        List list0 = Collections.EMPTY_LIST;
        if(list0 == null) {
            PropertyGetterDescriptorImpl.$$$reportNull$$$0(7);
        }
        return list0;
    }

    public void initialize(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            kotlinType0 = this.getCorrespondingProperty().getType();
        }
        this.returnType = kotlinType0;
    }
}

