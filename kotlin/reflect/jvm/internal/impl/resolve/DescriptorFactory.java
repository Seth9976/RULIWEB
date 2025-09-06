package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ReceiverParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.NameUtils;
import kotlin.reflect.jvm.internal.impl.name.StandardClassIds;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ContextClassReceiver;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ContextReceiver;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public class DescriptorFactory {
    static class DefaultClassConstructorDescriptor extends ClassConstructorDescriptorImpl {
        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[3];
            arr_object[0] = v == 1 ? "source" : "containingClass";
            arr_object[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory$DefaultClassConstructorDescriptor";
            arr_object[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
        }

        public DefaultClassConstructorDescriptor(ClassDescriptor classDescriptor0, SourceElement sourceElement0, boolean z) {
            if(classDescriptor0 == null) {
                DefaultClassConstructorDescriptor.$$$reportNull$$$0(0);
            }
            if(sourceElement0 == null) {
                DefaultClassConstructorDescriptor.$$$reportNull$$$0(1);
            }
            super(classDescriptor0, null, Annotations.Companion.getEMPTY(), true, Kind.DECLARATION, sourceElement0);
            this.initialize(Collections.EMPTY_LIST, DescriptorUtils.getDefaultConstructorVisibility(classDescriptor0, z));
        }
    }

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 12 || v == 23 || v == 25 ? 2 : 3)];
        switch(v) {
            case 2: 
            case 5: 
            case 9: {
                arr_object[0] = "parameterAnnotations";
                break;
            }
            case 10: {
                arr_object[0] = "visibility";
                break;
            }
            case 6: 
            case 11: 
            case 19: {
                arr_object[0] = "sourceElement";
                break;
            }
            case 20: {
                arr_object[0] = "containingClass";
                break;
            }
            case 21: {
                arr_object[0] = "source";
                break;
            }
            case 12: 
            case 23: 
            case 25: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
                break;
            }
            case 22: 
            case 24: 
            case 26: {
                arr_object[0] = "enumClass";
                break;
            }
            case 27: 
            case 28: 
            case 29: {
                arr_object[0] = "descriptor";
                break;
            }
            case 30: 
            case 0x20: 
            case 34: {
                arr_object[0] = "owner";
                break;
            }
            case 1: 
            case 4: 
            case 8: 
            case 14: 
            case 16: 
            case 18: 
            case 0x1F: 
            case 33: 
            case 35: {
                arr_object[0] = "annotations";
                break;
            }
            default: {
                arr_object[0] = "propertyDescriptor";
            }
        }
        switch(v) {
            case 12: {
                arr_object[1] = "createSetter";
                break;
            }
            case 23: {
                arr_object[1] = "createEnumValuesMethod";
                break;
            }
            case 25: {
                arr_object[1] = "createEnumValueOfMethod";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
            }
        }
        switch(v) {
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                arr_object[2] = "createSetter";
                break;
            }
            case 13: 
            case 14: {
                arr_object[2] = "createDefaultGetter";
                break;
            }
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: {
                arr_object[2] = "createGetter";
                break;
            }
            case 20: 
            case 21: {
                arr_object[2] = "createPrimaryConstructorForObject";
                break;
            }
            case 22: {
                arr_object[2] = "createEnumValuesMethod";
                break;
            }
            case 24: {
                arr_object[2] = "createEnumValueOfMethod";
                break;
            }
            case 12: 
            case 23: 
            case 25: {
                break;
            }
            case 26: {
                arr_object[2] = "createEnumEntriesProperty";
                break;
            }
            case 27: {
                arr_object[2] = "isEnumValuesMethod";
                break;
            }
            case 28: {
                arr_object[2] = "isEnumValueOfMethod";
                break;
            }
            case 29: {
                arr_object[2] = "isEnumSpecialMethod";
                break;
            }
            case 30: 
            case 0x1F: {
                arr_object[2] = "createExtensionReceiverParameterForCallable";
                break;
            }
            case 0x20: 
            case 33: {
                arr_object[2] = "createContextReceiverParameterForCallable";
                break;
            }
            case 34: 
            case 35: {
                arr_object[2] = "createContextReceiverParameterForClass";
                break;
            }
            default: {
                arr_object[2] = "createDefaultSetter";
            }
        }
        String s = String.format((v == 12 || v == 23 || v == 25 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 12 || v == 23 || v == 25 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    public static ReceiverParameterDescriptor createContextReceiverParameterForCallable(CallableDescriptor callableDescriptor0, KotlinType kotlinType0, Name name0, Annotations annotations0, int v) {
        if(callableDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(0x20);
        }
        if(annotations0 == null) {
            DescriptorFactory.$$$reportNull$$$0(33);
        }
        return kotlinType0 == null ? null : new ReceiverParameterDescriptorImpl(callableDescriptor0, new ContextReceiver(callableDescriptor0, kotlinType0, name0, null), annotations0, NameUtils.contextReceiverName(v));
    }

    public static ReceiverParameterDescriptor createContextReceiverParameterForClass(ClassDescriptor classDescriptor0, KotlinType kotlinType0, Name name0, Annotations annotations0, int v) {
        if(classDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(34);
        }
        if(annotations0 == null) {
            DescriptorFactory.$$$reportNull$$$0(35);
        }
        return kotlinType0 == null ? null : new ReceiverParameterDescriptorImpl(classDescriptor0, new ContextClassReceiver(classDescriptor0, kotlinType0, name0, null), annotations0, NameUtils.contextReceiverName(v));
    }

    public static PropertyGetterDescriptorImpl createDefaultGetter(PropertyDescriptor propertyDescriptor0, Annotations annotations0) {
        if(propertyDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(13);
        }
        if(annotations0 == null) {
            DescriptorFactory.$$$reportNull$$$0(14);
        }
        return DescriptorFactory.createGetter(propertyDescriptor0, annotations0, true, false, false);
    }

    public static PropertySetterDescriptorImpl createDefaultSetter(PropertyDescriptor propertyDescriptor0, Annotations annotations0, Annotations annotations1) {
        if(propertyDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            DescriptorFactory.$$$reportNull$$$0(1);
        }
        if(annotations1 == null) {
            DescriptorFactory.$$$reportNull$$$0(2);
        }
        return DescriptorFactory.createSetter(propertyDescriptor0, annotations0, annotations1, true, false, false, propertyDescriptor0.getSource());
    }

    public static PropertyDescriptor createEnumEntriesProperty(ClassDescriptor classDescriptor0) {
        if(classDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(26);
        }
        ClassDescriptor classDescriptor1 = FindClassInModuleKt.findClassAcrossModuleDependencies(DescriptorUtils.getContainingModule(classDescriptor0), StandardClassIds.INSTANCE.getEnumEntries());
        if(classDescriptor1 == null) {
            return null;
        }
        SourceElement sourceElement0 = classDescriptor0.getSource();
        PropertyDescriptorImpl propertyDescriptorImpl0 = PropertyDescriptorImpl.create(classDescriptor0, Annotations.Companion.getEMPTY(), Modality.FINAL, DescriptorVisibilities.PUBLIC, false, StandardNames.ENUM_ENTRIES, Kind.SYNTHESIZED, sourceElement0, false, false, false, false, false, false);
        SourceElement sourceElement1 = classDescriptor0.getSource();
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0 = new PropertyGetterDescriptorImpl(propertyDescriptorImpl0, Annotations.Companion.getEMPTY(), Modality.FINAL, DescriptorVisibilities.PUBLIC, false, false, false, Kind.SYNTHESIZED, null, sourceElement1);
        propertyDescriptorImpl0.initialize(propertyGetterDescriptorImpl0, null);
        propertyDescriptorImpl0.setType(KotlinTypeFactory.simpleType(TypeAttributes.Companion.getEmpty(), classDescriptor1.getTypeConstructor(), Collections.singletonList(new TypeProjectionImpl(classDescriptor0.getDefaultType())), false), Collections.EMPTY_LIST, null, null, Collections.EMPTY_LIST);
        propertyGetterDescriptorImpl0.initialize(propertyDescriptorImpl0.getReturnType());
        return propertyDescriptorImpl0;
    }

    public static SimpleFunctionDescriptor createEnumValueOfMethod(ClassDescriptor classDescriptor0) {
        if(classDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(24);
        }
        SourceElement sourceElement0 = classDescriptor0.getSource();
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImpl0 = SimpleFunctionDescriptorImpl.create(classDescriptor0, Annotations.Companion.getEMPTY(), StandardNames.ENUM_VALUE_OF, Kind.SYNTHESIZED, sourceElement0);
        Name name0 = Name.identifier("value");
        SimpleType simpleType0 = DescriptorUtilsKt.getBuiltIns(classDescriptor0).getStringType();
        SourceElement sourceElement1 = classDescriptor0.getSource();
        ValueParameterDescriptorImpl valueParameterDescriptorImpl0 = new ValueParameterDescriptorImpl(simpleFunctionDescriptorImpl0, null, 0, Annotations.Companion.getEMPTY(), name0, simpleType0, false, false, false, null, sourceElement1);
        SimpleFunctionDescriptor simpleFunctionDescriptor0 = simpleFunctionDescriptorImpl0.initialize(null, null, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.singletonList(valueParameterDescriptorImpl0), classDescriptor0.getDefaultType(), Modality.FINAL, DescriptorVisibilities.PUBLIC);
        if(simpleFunctionDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(25);
        }
        return simpleFunctionDescriptor0;
    }

    public static SimpleFunctionDescriptor createEnumValuesMethod(ClassDescriptor classDescriptor0) {
        if(classDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(22);
        }
        SourceElement sourceElement0 = classDescriptor0.getSource();
        SimpleFunctionDescriptorImpl simpleFunctionDescriptorImpl0 = SimpleFunctionDescriptorImpl.create(classDescriptor0, Annotations.Companion.getEMPTY(), StandardNames.ENUM_VALUES, Kind.SYNTHESIZED, sourceElement0);
        List list0 = Collections.EMPTY_LIST;
        List list1 = Collections.EMPTY_LIST;
        List list2 = Collections.EMPTY_LIST;
        KotlinBuiltIns kotlinBuiltIns0 = DescriptorUtilsKt.getBuiltIns(classDescriptor0);
        SimpleType simpleType0 = classDescriptor0.getDefaultType();
        SimpleFunctionDescriptor simpleFunctionDescriptor0 = simpleFunctionDescriptorImpl0.initialize(null, null, list0, list1, list2, kotlinBuiltIns0.getArrayType(Variance.INVARIANT, simpleType0), Modality.FINAL, DescriptorVisibilities.PUBLIC);
        if(simpleFunctionDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(23);
        }
        return simpleFunctionDescriptor0;
    }

    public static ReceiverParameterDescriptor createExtensionReceiverParameterForCallable(CallableDescriptor callableDescriptor0, KotlinType kotlinType0, Annotations annotations0) {
        if(callableDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(30);
        }
        if(annotations0 == null) {
            DescriptorFactory.$$$reportNull$$$0(0x1F);
        }
        return kotlinType0 == null ? null : new ReceiverParameterDescriptorImpl(callableDescriptor0, new ExtensionReceiver(callableDescriptor0, kotlinType0, null), annotations0);
    }

    public static PropertyGetterDescriptorImpl createGetter(PropertyDescriptor propertyDescriptor0, Annotations annotations0, boolean z, boolean z1, boolean z2) {
        if(propertyDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(15);
        }
        if(annotations0 == null) {
            DescriptorFactory.$$$reportNull$$$0(16);
        }
        return DescriptorFactory.createGetter(propertyDescriptor0, annotations0, z, z1, z2, propertyDescriptor0.getSource());
    }

    public static PropertyGetterDescriptorImpl createGetter(PropertyDescriptor propertyDescriptor0, Annotations annotations0, boolean z, boolean z1, boolean z2, SourceElement sourceElement0) {
        if(propertyDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(17);
        }
        if(annotations0 == null) {
            DescriptorFactory.$$$reportNull$$$0(18);
        }
        if(sourceElement0 == null) {
            DescriptorFactory.$$$reportNull$$$0(19);
        }
        return new PropertyGetterDescriptorImpl(propertyDescriptor0, annotations0, propertyDescriptor0.getModality(), propertyDescriptor0.getVisibility(), z, z1, z2, Kind.DECLARATION, null, sourceElement0);
    }

    public static ClassConstructorDescriptorImpl createPrimaryConstructorForObject(ClassDescriptor classDescriptor0, SourceElement sourceElement0) {
        if(classDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(20);
        }
        if(sourceElement0 == null) {
            DescriptorFactory.$$$reportNull$$$0(21);
        }
        return new DefaultClassConstructorDescriptor(classDescriptor0, sourceElement0, false);
    }

    public static PropertySetterDescriptorImpl createSetter(PropertyDescriptor propertyDescriptor0, Annotations annotations0, Annotations annotations1, boolean z, boolean z1, boolean z2, DescriptorVisibility descriptorVisibility0, SourceElement sourceElement0) {
        if(propertyDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(7);
        }
        if(annotations0 == null) {
            DescriptorFactory.$$$reportNull$$$0(8);
        }
        if(annotations1 == null) {
            DescriptorFactory.$$$reportNull$$$0(9);
        }
        if(descriptorVisibility0 == null) {
            DescriptorFactory.$$$reportNull$$$0(10);
        }
        if(sourceElement0 == null) {
            DescriptorFactory.$$$reportNull$$$0(11);
        }
        PropertySetterDescriptorImpl propertySetterDescriptorImpl0 = new PropertySetterDescriptorImpl(propertyDescriptor0, annotations0, propertyDescriptor0.getModality(), descriptorVisibility0, z, z1, z2, Kind.DECLARATION, null, sourceElement0);
        propertySetterDescriptorImpl0.initialize(PropertySetterDescriptorImpl.createSetterParameter(propertySetterDescriptorImpl0, propertyDescriptor0.getType(), annotations1));
        return propertySetterDescriptorImpl0;
    }

    public static PropertySetterDescriptorImpl createSetter(PropertyDescriptor propertyDescriptor0, Annotations annotations0, Annotations annotations1, boolean z, boolean z1, boolean z2, SourceElement sourceElement0) {
        if(propertyDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(3);
        }
        if(annotations0 == null) {
            DescriptorFactory.$$$reportNull$$$0(4);
        }
        if(annotations1 == null) {
            DescriptorFactory.$$$reportNull$$$0(5);
        }
        if(sourceElement0 == null) {
            DescriptorFactory.$$$reportNull$$$0(6);
        }
        return DescriptorFactory.createSetter(propertyDescriptor0, annotations0, annotations1, z, z1, z2, propertyDescriptor0.getVisibility(), sourceElement0);
    }

    private static boolean isEnumSpecialMethod(FunctionDescriptor functionDescriptor0) {
        if(functionDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(29);
        }
        return functionDescriptor0.getKind() == Kind.SYNTHESIZED && DescriptorUtils.isEnumClass(functionDescriptor0.getContainingDeclaration());
    }

    public static boolean isEnumValueOfMethod(FunctionDescriptor functionDescriptor0) {
        if(functionDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(28);
        }
        return functionDescriptor0.getName().equals(StandardNames.ENUM_VALUE_OF) && DescriptorFactory.isEnumSpecialMethod(functionDescriptor0);
    }

    public static boolean isEnumValuesMethod(FunctionDescriptor functionDescriptor0) {
        if(functionDescriptor0 == null) {
            DescriptorFactory.$$$reportNull$$$0(27);
        }
        return functionDescriptor0.getName().equals(StandardNames.ENUM_VALUES) && DescriptorFactory.isEnumSpecialMethod(functionDescriptor0);
    }
}

