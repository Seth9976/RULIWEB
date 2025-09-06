package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public final class UByteValue extends UnsignedValueConstant {
    public UByteValue(byte b) {
        super(b);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        ClassDescriptor classDescriptor0 = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor0, FqNames.uByte);
        SimpleType simpleType0 = classDescriptor0 == null ? null : classDescriptor0.getDefaultType();
        return simpleType0 == null ? ErrorUtils.createErrorType(ErrorTypeKind.NOT_FOUND_UNSIGNED_TYPE, new String[]{"UByte"}) : simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public String toString() {
        return ((Number)this.getValue()).intValue() + ".toUByte()";
    }
}

