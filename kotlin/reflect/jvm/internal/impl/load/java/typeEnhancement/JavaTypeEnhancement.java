package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class JavaTypeEnhancement {
    static final class Result {
        private final int subtreeSize;
        private final KotlinType type;

        public Result(KotlinType kotlinType0, int v) {
            this.type = kotlinType0;
            this.subtreeSize = v;
        }

        public final int getSubtreeSize() {
            return this.subtreeSize;
        }

        public final KotlinType getType() {
            return this.type;
        }
    }

    static final class SimpleResult {
        private final boolean forWarnings;
        private final int subtreeSize;
        private final SimpleType type;

        public SimpleResult(SimpleType simpleType0, int v, boolean z) {
            this.type = simpleType0;
            this.subtreeSize = v;
            this.forWarnings = z;
        }

        public final boolean getForWarnings() {
            return this.forWarnings;
        }

        public final int getSubtreeSize() {
            return this.subtreeSize;
        }

        public final SimpleType getType() {
            return this.type;
        }
    }

    private final JavaResolverSettings javaResolverSettings;

    public JavaTypeEnhancement(JavaResolverSettings javaResolverSettings0) {
        Intrinsics.checkNotNullParameter(javaResolverSettings0, "javaResolverSettings");
        super();
        this.javaResolverSettings = javaResolverSettings0;
    }

    public final KotlinType enhance(KotlinType kotlinType0, Function1 function10, boolean z) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "qualifiers");
        return this.enhancePossiblyFlexible(kotlinType0.unwrap(), function10, 0, z).getType();
    }

    private final SimpleResult enhanceInflexible(SimpleType simpleType0, Function1 function10, int v, TypeComponentPosition typeComponentPosition0, boolean z, boolean z1) {
        TypeProjection typeProjection1;
        Result javaTypeEnhancement$Result0;
        Boolean boolean1;
        TypeConstructor typeConstructor0;
        if(!TypeComponentPositionKt.shouldEnhance(typeComponentPosition0) && simpleType0.getArguments().isEmpty()) {
            return new SimpleResult(null, 1, false);
        }
        ClassifierDescriptor classifierDescriptor0 = simpleType0.getConstructor().getDeclarationDescriptor();
        if(classifierDescriptor0 == null) {
            return new SimpleResult(null, 1, false);
        }
        JavaTypeQualifiers javaTypeQualifiers0 = (JavaTypeQualifiers)function10.invoke(v);
        ClassifierDescriptor classifierDescriptor1 = TypeEnhancementKt.access$enhanceMutability(classifierDescriptor0, javaTypeQualifiers0, typeComponentPosition0);
        Boolean boolean0 = TypeEnhancementKt.access$getEnhancedNullability(javaTypeQualifiers0, typeComponentPosition0);
        if(classifierDescriptor1 == null) {
            typeConstructor0 = simpleType0.getConstructor();
        }
        else {
            typeConstructor0 = classifierDescriptor1.getTypeConstructor();
            if(typeConstructor0 == null) {
                typeConstructor0 = simpleType0.getConstructor();
            }
        }
        Intrinsics.checkNotNullExpressionValue(typeConstructor0, "enhancedClassifier?.typeConstructor ?: constructor");
        int v1 = v + 1;
        Iterable iterable0 = simpleType0.getArguments();
        List list0 = typeConstructor0.getParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "typeConstructor.parameters");
        Iterator iterator0 = iterable0.iterator();
        Iterator iterator1 = list0.iterator();
        ArrayList arrayList0 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(iterable0, 10), CollectionsKt.collectionSizeOrDefault(list0, 10)));
        while(iterator0.hasNext() && iterator1.hasNext()) {
            Object object0 = iterator0.next();
            Object object1 = iterator1.next();
            TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)object1;
            TypeProjection typeProjection0 = (TypeProjection)object0;
            if(!z1 || !z) {
                boolean1 = boolean0;
                if(!typeProjection0.isStarProjection()) {
                    javaTypeEnhancement$Result0 = this.enhancePossiblyFlexible(typeProjection0.getType().unwrap(), function10, v1, z1);
                }
                else if(((JavaTypeQualifiers)function10.invoke(v1)).getNullability() == NullabilityQualifier.FORCE_FLEXIBILITY) {
                    KotlinType kotlinType0 = typeProjection0.getType().unwrap();
                    javaTypeEnhancement$Result0 = new Result(KotlinTypeFactory.flexibleType(FlexibleTypesKt.lowerIfFlexible(kotlinType0).makeNullableAsSpecified(false), FlexibleTypesKt.upperIfFlexible(kotlinType0).makeNullableAsSpecified(true)), 1);
                }
                else {
                    javaTypeEnhancement$Result0 = new Result(null, 1);
                }
            }
            else {
                boolean1 = boolean0;
                javaTypeEnhancement$Result0 = new Result(null, 0);
            }
            v1 += javaTypeEnhancement$Result0.getSubtreeSize();
            if(javaTypeEnhancement$Result0.getType() != null) {
                Variance variance0 = typeProjection0.getProjectionKind();
                Intrinsics.checkNotNullExpressionValue(variance0, "arg.projectionKind");
                typeProjection1 = TypeUtilsKt.createProjection(javaTypeEnhancement$Result0.getType(), variance0, typeParameterDescriptor0);
            }
            else if(classifierDescriptor1 == null || typeProjection0.isStarProjection()) {
                typeProjection1 = classifierDescriptor1 == null ? null : TypeUtils.makeStarProjection(typeParameterDescriptor0);
            }
            else {
                KotlinType kotlinType1 = typeProjection0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType1, "arg.type");
                Variance variance1 = typeProjection0.getProjectionKind();
                Intrinsics.checkNotNullExpressionValue(variance1, "arg.projectionKind");
                typeProjection1 = TypeUtilsKt.createProjection(kotlinType1, variance1, typeParameterDescriptor0);
            }
            arrayList0.add(typeProjection1);
            boolean0 = boolean1;
        }
        int v2 = v1 - v;
        if(classifierDescriptor1 == null && boolean0 == null) {
            if(!(arrayList0 instanceof Collection) || !arrayList0.isEmpty()) {
                for(Object object2: arrayList0) {
                    if(((TypeProjection)object2) != null) {
                        goto label_66;
                    }
                }
            }
            return new SimpleResult(null, v2, false);
        }
    label_66:
        TypeAttributes typeAttributes0 = TypeAttributesKt.toDefaultAttributes(TypeEnhancementKt.access$compositeAnnotationsOrSingle(CollectionsKt.listOfNotNull(new Annotations[]{simpleType0.getAnnotations(), (classifierDescriptor1 == null ? null : TypeEnhancementKt.access$getENHANCED_MUTABILITY_ANNOTATIONS$p()), (boolean0 == null ? null : TypeEnhancementKt.getENHANCED_NULLABILITY_ANNOTATIONS())})));
        Iterable iterable1 = simpleType0.getArguments();
        Iterator iterator3 = arrayList0.iterator();
        Iterator iterator4 = iterable1.iterator();
        ArrayList arrayList1 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(arrayList0, 10), CollectionsKt.collectionSizeOrDefault(iterable1, 10)));
        while(iterator3.hasNext() && iterator4.hasNext()) {
            Object object3 = iterator3.next();
            Object object4 = iterator4.next();
            TypeProjection typeProjection2 = (TypeProjection)object4;
            TypeProjection typeProjection3 = (TypeProjection)object3;
            if(typeProjection3 != null) {
                typeProjection2 = typeProjection3;
            }
            arrayList1.add(typeProjection2);
        }
        SimpleType simpleType1 = KotlinTypeFactory.simpleType$default(typeAttributes0, typeConstructor0, arrayList1, (boolean0 == null ? simpleType0.isMarkedNullable() : boolean0.booleanValue()), null, 16, null);
        if(javaTypeQualifiers0.getDefinitelyNotNull()) {
            simpleType1 = this.notNullTypeParameter(simpleType1);
        }
        return boolean0 == null || !javaTypeQualifiers0.isNullabilityQualifierForWarning() ? new SimpleResult(simpleType1, v2, false) : new SimpleResult(simpleType1, v2, true);
    }

    static SimpleResult enhanceInflexible$default(JavaTypeEnhancement javaTypeEnhancement0, SimpleType simpleType0, Function1 function10, int v, TypeComponentPosition typeComponentPosition0, boolean z, boolean z1, int v1, Object object0) {
        if((v1 & 8) != 0) {
            z = false;
        }
        if((v1 & 16) != 0) {
            z1 = false;
        }
        return javaTypeEnhancement0.enhanceInflexible(simpleType0, function10, v, typeComponentPosition0, z, z1);
    }

    private final Result enhancePossiblyFlexible(UnwrappedType unwrappedType0, Function1 function10, int v, boolean z) {
        UnwrappedType unwrappedType2;
        UnwrappedType unwrappedType1 = null;
        if(KotlinTypeKt.isError(unwrappedType0)) {
            return new Result(null, 1);
        }
        if(unwrappedType0 instanceof FlexibleType) {
            SimpleResult javaTypeEnhancement$SimpleResult0 = this.enhanceInflexible(((FlexibleType)unwrappedType0).getLowerBound(), function10, v, TypeComponentPosition.FLEXIBLE_LOWER, unwrappedType0 instanceof RawType, z);
            SimpleResult javaTypeEnhancement$SimpleResult1 = this.enhanceInflexible(((FlexibleType)unwrappedType0).getUpperBound(), function10, v, TypeComponentPosition.FLEXIBLE_UPPER, unwrappedType0 instanceof RawType, z);
            if(javaTypeEnhancement$SimpleResult0.getType() != null || javaTypeEnhancement$SimpleResult1.getType() != null) {
                if(!javaTypeEnhancement$SimpleResult0.getForWarnings() && !javaTypeEnhancement$SimpleResult1.getForWarnings()) {
                    return unwrappedType0 instanceof RawType ? new Result(new RawTypeImpl((javaTypeEnhancement$SimpleResult0.getType() == null ? ((FlexibleType)unwrappedType0).getLowerBound() : javaTypeEnhancement$SimpleResult0.getType()), (javaTypeEnhancement$SimpleResult1.getType() == null ? ((FlexibleType)unwrappedType0).getUpperBound() : javaTypeEnhancement$SimpleResult1.getType())), javaTypeEnhancement$SimpleResult0.getSubtreeSize()) : new Result(KotlinTypeFactory.flexibleType((javaTypeEnhancement$SimpleResult0.getType() == null ? ((FlexibleType)unwrappedType0).getLowerBound() : javaTypeEnhancement$SimpleResult0.getType()), (javaTypeEnhancement$SimpleResult1.getType() == null ? ((FlexibleType)unwrappedType0).getUpperBound() : javaTypeEnhancement$SimpleResult1.getType())), javaTypeEnhancement$SimpleResult0.getSubtreeSize());
                }
                SimpleType simpleType0 = javaTypeEnhancement$SimpleResult1.getType();
                if(simpleType0 == null) {
                label_13:
                    SimpleType simpleType1 = javaTypeEnhancement$SimpleResult0.getType();
                    Intrinsics.checkNotNull(simpleType1);
                    unwrappedType2 = simpleType1;
                }
                else {
                    unwrappedType2 = KotlinTypeFactory.flexibleType((javaTypeEnhancement$SimpleResult0.getType() == null ? simpleType0 : javaTypeEnhancement$SimpleResult0.getType()), simpleType0);
                    if(unwrappedType2 == null) {
                        goto label_13;
                    }
                }
                unwrappedType1 = TypeWithEnhancementKt.wrapEnhancement(unwrappedType0, unwrappedType2);
            }
            return new Result(unwrappedType1, javaTypeEnhancement$SimpleResult0.getSubtreeSize());
        }
        if(!(unwrappedType0 instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        }
        SimpleResult javaTypeEnhancement$SimpleResult2 = JavaTypeEnhancement.enhanceInflexible$default(this, ((SimpleType)unwrappedType0), function10, v, TypeComponentPosition.INFLEXIBLE, false, z, 8, null);
        return javaTypeEnhancement$SimpleResult2.getForWarnings() ? new Result(TypeWithEnhancementKt.wrapEnhancement(unwrappedType0, javaTypeEnhancement$SimpleResult2.getType()), javaTypeEnhancement$SimpleResult2.getSubtreeSize()) : new Result(javaTypeEnhancement$SimpleResult2.getType(), javaTypeEnhancement$SimpleResult2.getSubtreeSize());
    }

    private final SimpleType notNullTypeParameter(SimpleType simpleType0) {
        return this.javaResolverSettings.getCorrectNullabilityForNotNullTypeParameter() ? SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType0, true) : new NotNullTypeParameterImpl(simpleType0);
    }
}

