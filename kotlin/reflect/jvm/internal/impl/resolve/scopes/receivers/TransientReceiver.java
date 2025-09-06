package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public class TransientReceiver extends AbstractReceiverValue {
    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[3];
        arr_object[0] = v == 2 ? "newType" : "type";
        arr_object[1] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/TransientReceiver";
        arr_object[2] = v == 2 ? "replaceType" : "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
    }

    public TransientReceiver(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TransientReceiver.$$$reportNull$$$0(0);
        }
        this(kotlinType0, null);
    }

    private TransientReceiver(KotlinType kotlinType0, ReceiverValue receiverValue0) {
        if(kotlinType0 == null) {
            TransientReceiver.$$$reportNull$$$0(1);
        }
        super(kotlinType0, receiverValue0);
    }

    @Override
    public String toString() {
        return "{Transient} : " + this.getType();
    }
}

