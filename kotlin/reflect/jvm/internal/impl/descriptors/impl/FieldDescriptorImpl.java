package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

public final class FieldDescriptorImpl extends AnnotatedImpl implements FieldDescriptor {
    private final PropertyDescriptor correspondingProperty;

    public FieldDescriptorImpl(Annotations annotations0, PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "correspondingProperty");
        super(annotations0);
        this.correspondingProperty = propertyDescriptor0;
    }
}

