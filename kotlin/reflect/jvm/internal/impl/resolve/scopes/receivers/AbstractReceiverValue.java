package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public abstract class AbstractReceiverValue implements ReceiverValue {
    private final ReceiverValue original;
    protected final KotlinType receiverType;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 1 || v == 2 ? 2 : 3)];
        arr_object[0] = v == 1 || v == 2 ? "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue" : "receiverType";
        switch(v) {
            case 1: {
                arr_object[1] = "getType";
                break;
            }
            case 2: {
                arr_object[1] = "getOriginal";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue";
            }
        }
        if(v != 1 && v != 2) {
            arr_object[2] = "<init>";
        }
        String s = String.format((v == 1 || v == 2 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 1 || v == 2 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    public AbstractReceiverValue(KotlinType kotlinType0, ReceiverValue receiverValue0) {
        if(kotlinType0 == null) {
            AbstractReceiverValue.$$$reportNull$$$0(0);
        }
        super();
        this.receiverType = kotlinType0;
        if(receiverValue0 == null) {
            receiverValue0 = this;
        }
        this.original = receiverValue0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
    public KotlinType getType() {
        KotlinType kotlinType0 = this.receiverType;
        if(kotlinType0 == null) {
            AbstractReceiverValue.$$$reportNull$$$0(1);
        }
        return kotlinType0;
    }
}

