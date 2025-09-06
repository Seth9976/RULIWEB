package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class ContextClassReceiver extends AbstractReceiverValue implements ImplicitContextReceiver {
    private final ClassDescriptor classDescriptor;
    private final Name customLabelName;

    public ContextClassReceiver(ClassDescriptor classDescriptor0, KotlinType kotlinType0, Name name0, ReceiverValue receiverValue0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        Intrinsics.checkNotNullParameter(kotlinType0, "receiverType");
        super(kotlinType0, receiverValue0);
        this.classDescriptor = classDescriptor0;
        this.customLabelName = name0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ImplicitContextReceiver
    public Name getCustomLabelName() {
        return this.customLabelName;
    }

    @Override
    public String toString() {
        return this.getType() + ": Ctx { " + this.classDescriptor + " }";
    }
}

