package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ImplicitClassReceiver;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;

public class LazyClassReceiverParameterDescriptor extends AbstractReceiverParameterDescriptor {
    private final ClassDescriptor descriptor;
    private final ImplicitClassReceiver receiverValue;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 1 || v == 2 ? 2 : 3)];
        switch(v) {
            case 1: 
            case 2: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazyClassReceiverParameterDescriptor";
                break;
            }
            case 3: {
                arr_object[0] = "newOwner";
                break;
            }
            default: {
                arr_object[0] = "descriptor";
            }
        }
        switch(v) {
            case 1: {
                arr_object[1] = "getValue";
                break;
            }
            case 2: {
                arr_object[1] = "getContainingDeclaration";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazyClassReceiverParameterDescriptor";
            }
        }
        switch(v) {
            case 1: 
            case 2: {
                break;
            }
            case 3: {
                arr_object[2] = "copy";
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s = String.format((v == 1 || v == 2 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 1 || v == 2 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    public LazyClassReceiverParameterDescriptor(ClassDescriptor classDescriptor0) {
        if(classDescriptor0 == null) {
            LazyClassReceiverParameterDescriptor.$$$reportNull$$$0(0);
        }
        super(Annotations.Companion.getEMPTY());
        this.descriptor = classDescriptor0;
        this.receiverValue = new ImplicitClassReceiver(classDescriptor0, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ValueDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        DeclarationDescriptor declarationDescriptor0 = this.descriptor;
        if(declarationDescriptor0 == null) {
            LazyClassReceiverParameterDescriptor.$$$reportNull$$$0(2);
        }
        return declarationDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor
    public ReceiverValue getValue() {
        ReceiverValue receiverValue0 = this.receiverValue;
        if(receiverValue0 == null) {
            LazyClassReceiverParameterDescriptor.$$$reportNull$$$0(1);
        }
        return receiverValue0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl
    public String toString() {
        return "class " + this.descriptor.getName() + "::this";
    }
}

