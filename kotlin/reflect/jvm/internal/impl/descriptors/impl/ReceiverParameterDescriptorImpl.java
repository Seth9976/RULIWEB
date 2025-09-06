package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;

public class ReceiverParameterDescriptorImpl extends AbstractReceiverParameterDescriptor {
    static final boolean $assertionsDisabled;
    private final DeclarationDescriptor containingDeclaration;
    private ReceiverValue value;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 7 || v == 8 ? 2 : 3)];
        switch(v) {
            case 1: 
            case 4: {
                arr_object[0] = "value";
                break;
            }
            case 2: 
            case 5: {
                arr_object[0] = "annotations";
                break;
            }
            case 6: {
                arr_object[0] = "name";
                break;
            }
            case 7: 
            case 8: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ReceiverParameterDescriptorImpl";
                break;
            }
            case 9: {
                arr_object[0] = "newOwner";
                break;
            }
            case 10: {
                arr_object[0] = "outType";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 7: {
                arr_object[1] = "getValue";
                break;
            }
            case 8: {
                arr_object[1] = "getContainingDeclaration";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ReceiverParameterDescriptorImpl";
            }
        }
        switch(v) {
            case 7: 
            case 8: {
                break;
            }
            case 9: {
                arr_object[2] = "copy";
                break;
            }
            case 10: {
                arr_object[2] = "setOutType";
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s = String.format((v == 7 || v == 8 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 7 || v == 8 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    static {
    }

    public ReceiverParameterDescriptorImpl(DeclarationDescriptor declarationDescriptor0, ReceiverValue receiverValue0, Annotations annotations0) {
        if(declarationDescriptor0 == null) {
            ReceiverParameterDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(receiverValue0 == null) {
            ReceiverParameterDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(annotations0 == null) {
            ReceiverParameterDescriptorImpl.$$$reportNull$$$0(2);
        }
        this(declarationDescriptor0, receiverValue0, annotations0, SpecialNames.THIS);
    }

    public ReceiverParameterDescriptorImpl(DeclarationDescriptor declarationDescriptor0, ReceiverValue receiverValue0, Annotations annotations0, Name name0) {
        if(declarationDescriptor0 == null) {
            ReceiverParameterDescriptorImpl.$$$reportNull$$$0(3);
        }
        if(receiverValue0 == null) {
            ReceiverParameterDescriptorImpl.$$$reportNull$$$0(4);
        }
        if(annotations0 == null) {
            ReceiverParameterDescriptorImpl.$$$reportNull$$$0(5);
        }
        if(name0 == null) {
            ReceiverParameterDescriptorImpl.$$$reportNull$$$0(6);
        }
        super(annotations0, name0);
        this.containingDeclaration = declarationDescriptor0;
        this.value = receiverValue0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ValueDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        DeclarationDescriptor declarationDescriptor0 = this.containingDeclaration;
        if(declarationDescriptor0 == null) {
            ReceiverParameterDescriptorImpl.$$$reportNull$$$0(8);
        }
        return declarationDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor
    public ReceiverValue getValue() {
        ReceiverValue receiverValue0 = this.value;
        if(receiverValue0 == null) {
            ReceiverParameterDescriptorImpl.$$$reportNull$$$0(7);
        }
        return receiverValue0;
    }
}

