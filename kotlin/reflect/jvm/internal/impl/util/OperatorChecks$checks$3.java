package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

final class OperatorChecks.checks.3 extends Lambda implements Function1 {
    public static final OperatorChecks.checks.3 INSTANCE;

    static {
        OperatorChecks.checks.3.INSTANCE = new OperatorChecks.checks.3();
    }

    OperatorChecks.checks.3() {
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.invoke(((FunctionDescriptor)object0));
    }

    public final String invoke(FunctionDescriptor functionDescriptor0) {
        boolean z1;
        Intrinsics.checkNotNullParameter(functionDescriptor0, "$this$$receiver");
        ReceiverParameterDescriptor receiverParameterDescriptor0 = functionDescriptor0.getDispatchReceiverParameter();
        if(receiverParameterDescriptor0 == null) {
            receiverParameterDescriptor0 = functionDescriptor0.getExtensionReceiverParameter();
        }
        boolean z = false;
        if(receiverParameterDescriptor0 != null) {
            KotlinType kotlinType0 = functionDescriptor0.getReturnType();
            if(kotlinType0 == null) {
                z1 = false;
            }
            else {
                KotlinType kotlinType1 = receiverParameterDescriptor0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType1, "receiver.type");
                z1 = TypeUtilsKt.isSubtypeOf(kotlinType0, kotlinType1);
            }
            if(z1 || OperatorChecks.INSTANCE.incDecCheckForExpectClass(functionDescriptor0, receiverParameterDescriptor0)) {
                z = true;
            }
        }
        return z ? null : "receiver must be a supertype of the return type";
    }
}

