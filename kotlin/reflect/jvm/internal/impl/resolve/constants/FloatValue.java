package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class FloatValue extends ConstantValue {
    public FloatValue(float f) {
        super(f);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
        return this.getType(moduleDescriptor0);
    }

    public SimpleType getType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        SimpleType simpleType0 = moduleDescriptor0.getBuiltIns().getFloatType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "module.builtIns.floatType");
        return simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public String toString() {
        return ((Number)this.getValue()).floatValue() + ".toFloat()";
    }
}

