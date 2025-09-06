package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public abstract class KotlinTypePreparator extends AbstractTypePreparator {
    public static final class Default extends KotlinTypePreparator {
        public static final Default INSTANCE;

        static {
            Default.INSTANCE = new Default();
        }
    }

    public UnwrappedType prepareType(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
        if(!(kotlinTypeMarker0 instanceof KotlinType)) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        UnwrappedType unwrappedType0 = ((KotlinType)kotlinTypeMarker0).unwrap();
        if(unwrappedType0 instanceof SimpleType) {
            return TypeWithEnhancementKt.inheritEnhancement(this.transformToNewType(((SimpleType)unwrappedType0)), unwrappedType0, new Function1() {
                {
                    super(1, object0);
                }

                @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
                public final String getName() {
                    return "prepareType";
                }

                @Override  // kotlin.jvm.internal.CallableReference
                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(KotlinTypePreparator.class);
                }

                @Override  // kotlin.jvm.internal.CallableReference
                public final String getSignature() {
                    return "prepareType(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Lorg/jetbrains/kotlin/types/UnwrappedType;";
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((KotlinTypeMarker)object0));
                }

                public final UnwrappedType invoke(KotlinTypeMarker kotlinTypeMarker0) {
                    Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "p0");
                    return ((KotlinTypePreparator)this.receiver).prepareType(kotlinTypeMarker0);
                }
            });
        }
        if(!(unwrappedType0 instanceof FlexibleType)) {
            throw new NoWhenBranchMatchedException();
        }
        SimpleType simpleType0 = this.transformToNewType(((FlexibleType)unwrappedType0).getLowerBound());
        SimpleType simpleType1 = this.transformToNewType(((FlexibleType)unwrappedType0).getUpperBound());
        return simpleType0 != ((FlexibleType)unwrappedType0).getLowerBound() || simpleType1 != ((FlexibleType)unwrappedType0).getUpperBound() ? TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(simpleType0, simpleType1), unwrappedType0, new Function1() {
            {
                super(1, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "prepareType";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(KotlinTypePreparator.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "prepareType(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Lorg/jetbrains/kotlin/types/UnwrappedType;";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinTypeMarker)object0));
            }

            public final UnwrappedType invoke(KotlinTypeMarker kotlinTypeMarker0) {
                Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "p0");
                return ((KotlinTypePreparator)this.receiver).prepareType(kotlinTypeMarker0);
            }
        }) : TypeWithEnhancementKt.inheritEnhancement(unwrappedType0, unwrappedType0, new Function1() {
            {
                super(1, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "prepareType";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(KotlinTypePreparator.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "prepareType(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Lorg/jetbrains/kotlin/types/UnwrappedType;";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinTypeMarker)object0));
            }

            public final UnwrappedType invoke(KotlinTypeMarker kotlinTypeMarker0) {
                Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "p0");
                return ((KotlinTypePreparator)this.receiver).prepareType(kotlinTypeMarker0);
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypePreparator
    public KotlinTypeMarker prepareType(KotlinTypeMarker kotlinTypeMarker0) {
        return this.prepareType(kotlinTypeMarker0);
    }

    private final SimpleType transformToNewType(SimpleType simpleType0) {
        boolean z = false;
        TypeConstructor typeConstructor0 = simpleType0.getConstructor();
        KotlinType kotlinType0 = null;
        if(typeConstructor0 instanceof CapturedTypeConstructorImpl) {
            TypeProjection typeProjection0 = ((CapturedTypeConstructorImpl)typeConstructor0).getProjection();
            if(typeProjection0.getProjectionKind() != Variance.IN_VARIANCE) {
                typeProjection0 = null;
            }
            if(typeProjection0 != null) {
                KotlinType kotlinType1 = typeProjection0.getType();
                if(kotlinType1 != null) {
                    kotlinType0 = kotlinType1.unwrap();
                }
            }
            if(((CapturedTypeConstructorImpl)typeConstructor0).getNewTypeConstructor() == null) {
                TypeProjection typeProjection1 = ((CapturedTypeConstructorImpl)typeConstructor0).getProjection();
                Iterable iterable0 = ((CapturedTypeConstructorImpl)typeConstructor0).getSupertypes();
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                for(Object object0: iterable0) {
                    arrayList0.add(((KotlinType)object0).unwrap());
                }
                ((CapturedTypeConstructorImpl)typeConstructor0).setNewTypeConstructor(new NewCapturedTypeConstructor(typeProjection1, arrayList0, null, 4, null));
            }
            NewCapturedTypeConstructor newCapturedTypeConstructor0 = ((CapturedTypeConstructorImpl)typeConstructor0).getNewTypeConstructor();
            Intrinsics.checkNotNull(newCapturedTypeConstructor0);
            TypeAttributes typeAttributes0 = simpleType0.getAttributes();
            boolean z1 = simpleType0.isMarkedNullable();
            return new NewCapturedType(CaptureStatus.FOR_SUBTYPING, newCapturedTypeConstructor0, kotlinType0, typeAttributes0, z1, false, 0x20, null);
        }
        if(typeConstructor0 instanceof IntegerValueTypeConstructor) {
            Iterable iterable1 = ((IntegerValueTypeConstructor)typeConstructor0).getSupertypes();
            ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable1, 10));
            for(Object object1: iterable1) {
                KotlinType kotlinType2 = TypeUtils.makeNullableAsSpecified(((KotlinType)object1), simpleType0.isMarkedNullable());
                Intrinsics.checkNotNullExpressionValue(kotlinType2, "makeNullableAsSpecified(it, type.isMarkedNullable)");
                arrayList1.add(kotlinType2);
            }
            IntersectionTypeConstructor intersectionTypeConstructor0 = new IntersectionTypeConstructor(arrayList1);
            return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(simpleType0.getAttributes(), intersectionTypeConstructor0, CollectionsKt.emptyList(), false, simpleType0.getMemberScope());
        }
        if(typeConstructor0 instanceof IntersectionTypeConstructor && simpleType0.isMarkedNullable()) {
            IntersectionTypeConstructor intersectionTypeConstructor1 = (IntersectionTypeConstructor)typeConstructor0;
            Iterable iterable2 = intersectionTypeConstructor1.getSupertypes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for(Object object2: iterable2) {
                arrayList2.add(TypeUtilsKt.makeNullable(((KotlinType)object2)));
                z = true;
            }
            if(z) {
                KotlinType kotlinType3 = intersectionTypeConstructor1.getAlternativeType();
                if(kotlinType3 != null) {
                    kotlinType0 = TypeUtilsKt.makeNullable(kotlinType3);
                }
                kotlinType0 = new IntersectionTypeConstructor(arrayList2).setAlternative(kotlinType0);
            }
            if(kotlinType0 != null) {
                intersectionTypeConstructor1 = kotlinType0;
            }
            return intersectionTypeConstructor1.createType();
        }
        return simpleType0;
    }
}

