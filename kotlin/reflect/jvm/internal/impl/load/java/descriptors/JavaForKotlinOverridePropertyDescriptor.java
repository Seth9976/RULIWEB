package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

public final class JavaForKotlinOverridePropertyDescriptor extends JavaPropertyDescriptor {
    private final SimpleFunctionDescriptor getterMethod;
    private final PropertyDescriptor overriddenProperty;
    private final SimpleFunctionDescriptor setterMethod;

    public JavaForKotlinOverridePropertyDescriptor(ClassDescriptor classDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor1, PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "ownerDescriptor");
        Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "getterMethod");
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "overriddenProperty");
        Modality modality0 = simpleFunctionDescriptor0.getModality();
        DescriptorVisibility descriptorVisibility0 = simpleFunctionDescriptor0.getVisibility();
        super(classDescriptor0, Annotations.Companion.getEMPTY(), modality0, descriptorVisibility0, simpleFunctionDescriptor1 != null, propertyDescriptor0.getName(), simpleFunctionDescriptor0.getSource(), null, Kind.DECLARATION, false, null);
        this.getterMethod = simpleFunctionDescriptor0;
        this.setterMethod = simpleFunctionDescriptor1;
        this.overriddenProperty = propertyDescriptor0;
    }
}

