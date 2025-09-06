package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class KClassValue extends ConstantValue {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ConstantValue create(KotlinType kotlinType0) {
            Intrinsics.checkNotNullParameter(kotlinType0, "argumentType");
            if(KotlinTypeKt.isError(kotlinType0)) {
                return null;
            }
            KotlinType kotlinType1 = kotlinType0;
            int v;
            for(v = 0; KotlinBuiltIns.isArray(kotlinType1); ++v) {
                kotlinType1 = ((TypeProjection)CollectionsKt.single(kotlinType1.getArguments())).getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType1, "type.arguments.single().type");
            }
            ClassifierDescriptor classifierDescriptor0 = kotlinType1.getConstructor().getDeclarationDescriptor();
            if(classifierDescriptor0 instanceof ClassDescriptor) {
                ClassId classId0 = DescriptorUtilsKt.getClassId(classifierDescriptor0);
                return classId0 == null ? new KClassValue(new LocalClass(kotlinType0)) : new KClassValue(classId0, v);
            }
            if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
                ClassId classId1 = ClassId.topLevel(FqNames.any.toSafe());
                Intrinsics.checkNotNullExpressionValue(classId1, "topLevel(StandardNames.FqNames.any.toSafe())");
                return new KClassValue(classId1, 0);
            }
            return null;
        }
    }

    public static abstract class Value {
        public static final class LocalClass extends Value {
            private final KotlinType type;

            public LocalClass(KotlinType kotlinType0) {
                Intrinsics.checkNotNullParameter(kotlinType0, "type");
                super(null);
                this.type = kotlinType0;
            }

            @Override
            public boolean equals(Object object0) {
                if(this == object0) {
                    return true;
                }
                return object0 instanceof LocalClass ? Intrinsics.areEqual(this.type, ((LocalClass)object0).type) : false;
            }

            public final KotlinType getType() {
                return this.type;
            }

            @Override
            public int hashCode() {
                return this.type.hashCode();
            }

            @Override
            public String toString() {
                return "LocalClass(type=" + this.type + ')';
            }
        }

        public static final class NormalClass extends Value {
            private final ClassLiteralValue value;

            public NormalClass(ClassLiteralValue classLiteralValue0) {
                Intrinsics.checkNotNullParameter(classLiteralValue0, "value");
                super(null);
                this.value = classLiteralValue0;
            }

            @Override
            public boolean equals(Object object0) {
                if(this == object0) {
                    return true;
                }
                return object0 instanceof NormalClass ? Intrinsics.areEqual(this.value, ((NormalClass)object0).value) : false;
            }

            public final int getArrayDimensions() {
                return this.value.getArrayNestedness();
            }

            public final ClassId getClassId() {
                return this.value.getClassId();
            }

            public final ClassLiteralValue getValue() {
                return this.value;
            }

            @Override
            public int hashCode() {
                return this.value.hashCode();
            }

            @Override
            public String toString() {
                return "NormalClass(value=" + this.value + ')';
            }
        }

        private Value() {
        }

        public Value(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;

    static {
        KClassValue.Companion = new Companion(null);
    }

    public KClassValue(ClassId classId0, int v) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        this(new ClassLiteralValue(classId0, v));
    }

    public KClassValue(ClassLiteralValue classLiteralValue0) {
        Intrinsics.checkNotNullParameter(classLiteralValue0, "value");
        this(new NormalClass(classLiteralValue0));
    }

    public KClassValue(Value kClassValue$Value0) {
        Intrinsics.checkNotNullParameter(kClassValue$Value0, "value");
        super(kClassValue$Value0);
    }

    public final KotlinType getArgumentType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Value kClassValue$Value0 = (Value)this.getValue();
        if(kClassValue$Value0 instanceof LocalClass) {
            return ((LocalClass)this.getValue()).getType();
        }
        if(!(kClassValue$Value0 instanceof NormalClass)) {
            throw new NoWhenBranchMatchedException();
        }
        ClassLiteralValue classLiteralValue0 = ((NormalClass)this.getValue()).getValue();
        ClassId classId0 = classLiteralValue0.component1();
        int v = classLiteralValue0.component2();
        ClassDescriptor classDescriptor0 = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor0, classId0);
        if(classDescriptor0 == null) {
            String s = classId0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "classId.toString()");
            return ErrorUtils.createErrorType(ErrorTypeKind.UNRESOLVED_KCLASS_CONSTANT_VALUE, new String[]{s, String.valueOf(v)});
        }
        SimpleType simpleType0 = classDescriptor0.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "descriptor.defaultType");
        KotlinType kotlinType0 = TypeUtilsKt.replaceArgumentsWithStarProjections(simpleType0);
        for(int v1 = 0; v1 < v; ++v1) {
            kotlinType0 = moduleDescriptor0.getBuiltIns().getArrayType(Variance.INVARIANT, kotlinType0);
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "module.builtIns.getArray…Variance.INVARIANT, type)");
        }
        return kotlinType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        TypeAttributes typeAttributes0 = TypeAttributes.Companion.getEmpty();
        ClassDescriptor classDescriptor0 = moduleDescriptor0.getBuiltIns().getKClass();
        Intrinsics.checkNotNullExpressionValue(classDescriptor0, "module.builtIns.kClass");
        return KotlinTypeFactory.simpleNotNullType(typeAttributes0, classDescriptor0, CollectionsKt.listOf(new TypeProjectionImpl(this.getArgumentType(moduleDescriptor0))));
    }
}

