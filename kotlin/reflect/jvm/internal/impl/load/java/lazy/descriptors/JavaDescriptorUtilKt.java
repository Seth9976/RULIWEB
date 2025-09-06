package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;

public final class JavaDescriptorUtilKt {
    public static final boolean isJavaField(PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "<this>");
        return propertyDescriptor0.getGetter() == null;
    }
}

