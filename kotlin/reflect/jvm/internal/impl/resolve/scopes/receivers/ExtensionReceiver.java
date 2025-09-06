package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public class ExtensionReceiver extends AbstractReceiverValue implements ImplicitReceiver {
    private final CallableDescriptor descriptor;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 2 ? 2 : 3)];
        switch(v) {
            case 1: {
                arr_object[0] = "receiverType";
                break;
            }
            case 2: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/ExtensionReceiver";
                break;
            }
            case 3: {
                arr_object[0] = "newType";
                break;
            }
            default: {
                arr_object[0] = "callableDescriptor";
            }
        }
        arr_object[1] = v == 2 ? "getDeclarationDescriptor" : "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/ExtensionReceiver";
        if(v != 2) {
            arr_object[2] = v == 3 ? "replaceType" : "<init>";
        }
        String s = String.format((v == 2 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalArgumentException illegalArgumentException0 = v == 2 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalArgumentException0;
    }

    public ExtensionReceiver(CallableDescriptor callableDescriptor0, KotlinType kotlinType0, ReceiverValue receiverValue0) {
        if(callableDescriptor0 == null) {
            ExtensionReceiver.$$$reportNull$$$0(0);
        }
        if(kotlinType0 == null) {
            ExtensionReceiver.$$$reportNull$$$0(1);
        }
        super(kotlinType0, receiverValue0);
        this.descriptor = callableDescriptor0;
    }

    @Override
    public String toString() {
        return this.getType() + ": Ext {" + this.descriptor + "}";
    }
}

