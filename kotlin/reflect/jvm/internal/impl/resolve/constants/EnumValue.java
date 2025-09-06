package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public final class EnumValue extends ConstantValue {
    private final ClassId enumClassId;
    private final Name enumEntryName;

    public EnumValue(ClassId classId0, Name name0) {
        Intrinsics.checkNotNullParameter(classId0, "enumClassId");
        Intrinsics.checkNotNullParameter(name0, "enumEntryName");
        super(TuplesKt.to(classId0, name0));
        this.enumClassId = classId0;
        this.enumEntryName = name0;
    }

    public final Name getEnumEntryName() {
        return this.enumEntryName;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        ClassDescriptor classDescriptor0 = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor0, this.enumClassId);
        SimpleType simpleType0 = null;
        if(classDescriptor0 != null) {
            if(!DescriptorUtils.isEnumClass(classDescriptor0)) {
                classDescriptor0 = null;
            }
            if(classDescriptor0 != null) {
                simpleType0 = classDescriptor0.getDefaultType();
            }
        }
        if(simpleType0 == null) {
            String s = this.enumClassId.toString();
            Intrinsics.checkNotNullExpressionValue(s, "enumClassId.toString()");
            String s1 = this.enumEntryName.toString();
            Intrinsics.checkNotNullExpressionValue(s1, "enumEntryName.toString()");
            return ErrorUtils.createErrorType(ErrorTypeKind.ERROR_ENUM_TYPE, new String[]{s, s1});
        }
        return simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public String toString() {
        return this.enumClassId.getShortClassName() + '.' + this.enumEntryName;
    }
}

