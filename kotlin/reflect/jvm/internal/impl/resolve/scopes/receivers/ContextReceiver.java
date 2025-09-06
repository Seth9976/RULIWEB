package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class ContextReceiver extends AbstractReceiverValue implements ImplicitContextReceiver {
    private final Name customLabelName;
    private final CallableDescriptor declarationDescriptor;

    public ContextReceiver(CallableDescriptor callableDescriptor0, KotlinType kotlinType0, Name name0, ReceiverValue receiverValue0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "declarationDescriptor");
        Intrinsics.checkNotNullParameter(kotlinType0, "receiverType");
        super(kotlinType0, receiverValue0);
        this.declarationDescriptor = callableDescriptor0;
        this.customLabelName = name0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ImplicitContextReceiver
    public Name getCustomLabelName() {
        return this.customLabelName;
    }

    public CallableDescriptor getDeclarationDescriptor() {
        return this.declarationDescriptor;
    }

    @Override
    public String toString() {
        return "Cxt { " + this.getDeclarationDescriptor() + " }";
    }
}

