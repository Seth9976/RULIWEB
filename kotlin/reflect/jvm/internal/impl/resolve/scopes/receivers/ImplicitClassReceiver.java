package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public class ImplicitClassReceiver implements ImplicitReceiver, ThisClassReceiver {
    private final ClassDescriptor classDescriptor;
    private final ClassDescriptor declarationDescriptor;
    private final ImplicitClassReceiver original;

    public ImplicitClassReceiver(ClassDescriptor classDescriptor0, ImplicitClassReceiver implicitClassReceiver0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        super();
        this.classDescriptor = classDescriptor0;
        if(implicitClassReceiver0 == null) {
            implicitClassReceiver0 = this;
        }
        this.original = implicitClassReceiver0;
        this.declarationDescriptor = classDescriptor0;
    }

    @Override
    public boolean equals(Object object0) {
        ClassDescriptor classDescriptor0 = this.classDescriptor;
        ClassDescriptor classDescriptor1 = null;
        ImplicitClassReceiver implicitClassReceiver0 = object0 instanceof ImplicitClassReceiver ? ((ImplicitClassReceiver)object0) : null;
        if(implicitClassReceiver0 != null) {
            classDescriptor1 = implicitClassReceiver0.classDescriptor;
        }
        return Intrinsics.areEqual(classDescriptor0, classDescriptor1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver
    public final ClassDescriptor getClassDescriptor() {
        return this.classDescriptor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
    public KotlinType getType() {
        return this.getType();
    }

    public SimpleType getType() {
        SimpleType simpleType0 = this.classDescriptor.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "classDescriptor.defaultType");
        return simpleType0;
    }

    @Override
    public int hashCode() {
        return this.classDescriptor.hashCode();
    }

    @Override
    public String toString() {
        return "Class{" + this.getType() + '}';
    }
}

