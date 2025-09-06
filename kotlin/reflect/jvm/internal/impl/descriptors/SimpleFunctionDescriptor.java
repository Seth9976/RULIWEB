package kotlin.reflect.jvm.internal.impl.descriptors;

public interface SimpleFunctionDescriptor extends FunctionDescriptor {
    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    CopyBuilder newCopyBuilder();
}

