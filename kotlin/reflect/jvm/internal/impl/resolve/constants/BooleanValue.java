package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class BooleanValue extends ConstantValue {
    public BooleanValue(boolean z) {
        super(Boolean.valueOf(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
        return this.getType(moduleDescriptor0);
    }

    public SimpleType getType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        SimpleType simpleType0 = moduleDescriptor0.getBuiltIns().getBooleanType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "module.builtIns.booleanType");
        return simpleType0;
    }
}

