package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErasureProjectionComputer;
import kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeParameterUpperBoundEraser;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public final class RawSubstitution extends TypeSubstitution {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    private static final JavaTypeAttributes lowerTypeAttr;
    private final RawProjectionComputer projectionComputer;
    private final TypeParameterUpperBoundEraser typeParameterUpperBoundEraser;
    private static final JavaTypeAttributes upperTypeAttr;

    static {
        RawSubstitution.Companion = new Companion(null);
        RawSubstitution.lowerTypeAttr = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, true, null, 5, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_LOWER_BOUND);
        RawSubstitution.upperTypeAttr = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, true, null, 5, null).withFlexibility(JavaTypeFlexibility.FLEXIBLE_UPPER_BOUND);
    }

    public RawSubstitution() {
        this(null, 1, null);
    }

    public RawSubstitution(TypeParameterUpperBoundEraser typeParameterUpperBoundEraser0) {
        RawProjectionComputer rawProjectionComputer0 = new RawProjectionComputer();
        this.projectionComputer = rawProjectionComputer0;
        if(typeParameterUpperBoundEraser0 == null) {
            typeParameterUpperBoundEraser0 = new TypeParameterUpperBoundEraser(rawProjectionComputer0, null, 2, null);
        }
        this.typeParameterUpperBoundEraser = typeParameterUpperBoundEraser0;
    }

    public RawSubstitution(TypeParameterUpperBoundEraser typeParameterUpperBoundEraser0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            typeParameterUpperBoundEraser0 = null;
        }
        this(typeParameterUpperBoundEraser0);
    }

    private final Pair eraseInflexibleBasedOnClassDescriptor(SimpleType simpleType0, ClassDescriptor classDescriptor0, JavaTypeAttributes javaTypeAttributes0) {
        if(simpleType0.getConstructor().getParameters().isEmpty()) {
            return TuplesKt.to(simpleType0, Boolean.FALSE);
        }
        if(KotlinBuiltIns.isArray(simpleType0)) {
            TypeProjection typeProjection0 = (TypeProjection)simpleType0.getArguments().get(0);
            Variance variance0 = typeProjection0.getProjectionKind();
            KotlinType kotlinType0 = typeProjection0.getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "componentTypeProjection.type");
            List list0 = CollectionsKt.listOf(new TypeProjectionImpl(variance0, this.eraseType(kotlinType0, javaTypeAttributes0)));
            return TuplesKt.to(KotlinTypeFactory.simpleType$default(simpleType0.getAttributes(), simpleType0.getConstructor(), list0, simpleType0.isMarkedNullable(), null, 16, null), Boolean.FALSE);
        }
        if(KotlinTypeKt.isError(simpleType0)) {
            String[] arr_s = {simpleType0.getConstructor().toString()};
            return TuplesKt.to(ErrorUtils.createErrorType(ErrorTypeKind.ERROR_RAW_TYPE, arr_s), Boolean.FALSE);
        }
        MemberScope memberScope0 = classDescriptor0.getMemberScope(this);
        Intrinsics.checkNotNullExpressionValue(memberScope0, "declaration.getMemberScope(this)");
        TypeAttributes typeAttributes0 = simpleType0.getAttributes();
        TypeConstructor typeConstructor0 = classDescriptor0.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor0, "declaration.typeConstructor");
        List list1 = classDescriptor0.getTypeConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(list1, "declaration.typeConstructor.parameters");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
        for(Object object0: list1) {
            Intrinsics.checkNotNullExpressionValue(((TypeParameterDescriptor)object0), "parameter");
            arrayList0.add(ErasureProjectionComputer.computeProjection$default(this.projectionComputer, ((TypeParameterDescriptor)object0), javaTypeAttributes0, this.typeParameterUpperBoundEraser, null, 8, null));
        }
        return TuplesKt.to(KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(typeAttributes0, typeConstructor0, arrayList0, simpleType0.isMarkedNullable(), memberScope0, new Function1(this, simpleType0, javaTypeAttributes0) {
            final JavaTypeAttributes $attr;
            final ClassDescriptor $declaration;
            final SimpleType $type;

            {
                this.$declaration = classDescriptor0;
                RawSubstitution.this = rawSubstitution0;
                this.$type = simpleType0;
                this.$attr = javaTypeAttributes0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinTypeRefiner)object0));
            }

            public final SimpleType invoke(KotlinTypeRefiner kotlinTypeRefiner0) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
                ClassDescriptor classDescriptor0 = this.$declaration instanceof ClassDescriptor ? this.$declaration : null;
                if(classDescriptor0 != null) {
                    ClassId classId0 = DescriptorUtilsKt.getClassId(classDescriptor0);
                    if(classId0 != null) {
                        ClassDescriptor classDescriptor1 = kotlinTypeRefiner0.findClassAcrossModuleDependencies(classId0);
                        if(classDescriptor1 == null) {
                            return null;
                        }
                        return Intrinsics.areEqual(classDescriptor1, this.$declaration) ? null : ((SimpleType)RawSubstitution.this.eraseInflexibleBasedOnClassDescriptor(this.$type, classDescriptor1, this.$attr).getFirst());
                    }
                }
                return null;
            }
        }), Boolean.TRUE);
    }

    private final KotlinType eraseType(KotlinType kotlinType0, JavaTypeAttributes javaTypeAttributes0) {
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
            ErasureTypeAttributes erasureTypeAttributes0 = javaTypeAttributes0.markIsRaw(true);
            return this.eraseType(this.typeParameterUpperBoundEraser.getErasedUpperBound(((TypeParameterDescriptor)classifierDescriptor0), erasureTypeAttributes0), javaTypeAttributes0);
        }
        if(!(classifierDescriptor0 instanceof ClassDescriptor)) {
            throw new IllegalStateException(("Unexpected declaration kind: " + classifierDescriptor0).toString());
        }
        ClassifierDescriptor classifierDescriptor1 = FlexibleTypesKt.upperIfFlexible(kotlinType0).getConstructor().getDeclarationDescriptor();
        if(!(classifierDescriptor1 instanceof ClassDescriptor)) {
            throw new IllegalStateException(("For some reason declaration for upper bound is not a class but \"" + classifierDescriptor1 + "\" while for lower it\'s \"" + classifierDescriptor0 + '\"').toString());
        }
        Pair pair0 = this.eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.lowerIfFlexible(kotlinType0), ((ClassDescriptor)classifierDescriptor0), RawSubstitution.lowerTypeAttr);
        SimpleType simpleType0 = (SimpleType)pair0.component1();
        Pair pair1 = this.eraseInflexibleBasedOnClassDescriptor(FlexibleTypesKt.upperIfFlexible(kotlinType0), ((ClassDescriptor)classifierDescriptor1), RawSubstitution.upperTypeAttr);
        SimpleType simpleType1 = (SimpleType)pair1.component1();
        return !((Boolean)pair0.component2()).booleanValue() && !((Boolean)pair1.component2()).booleanValue() ? KotlinTypeFactory.flexibleType(simpleType0, simpleType1) : new RawTypeImpl(simpleType0, simpleType1);
    }

    static KotlinType eraseType$default(RawSubstitution rawSubstitution0, KotlinType kotlinType0, JavaTypeAttributes javaTypeAttributes0, int v, Object object0) {
        if((v & 2) != 0) {
            javaTypeAttributes0 = new JavaTypeAttributes(TypeUsage.COMMON, null, false, false, null, null, 62, null);
        }
        return rawSubstitution0.eraseType(kotlinType0, javaTypeAttributes0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public TypeProjection get(KotlinType kotlinType0) {
        return this.get(kotlinType0);
    }

    public TypeProjectionImpl get(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "key");
        return new TypeProjectionImpl(RawSubstitution.eraseType$default(this, kotlinType0, null, 2, null));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return false;
    }
}

