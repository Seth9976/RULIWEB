package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class NewCapturedTypeKt {
    private static final List captureArguments(UnwrappedType unwrappedType0, CaptureStatus captureStatus0) {
        if(unwrappedType0.getArguments().size() != unwrappedType0.getConstructor().getParameters().size()) {
            return null;
        }
        List list0 = unwrappedType0.getArguments();
        if(!(list0 instanceof Collection) || !list0.isEmpty()) {
            for(Object object0: list0) {
                if(((TypeProjection)object0).getProjectionKind() != Variance.INVARIANT) {
                    List list1 = unwrappedType0.getConstructor().getParameters();
                    Intrinsics.checkNotNullExpressionValue(list1, "type.constructor.parameters");
                    Iterable iterable0 = CollectionsKt.zip(list0, list1);
                    ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                    for(Object object1: iterable0) {
                        TypeProjection typeProjection0 = (TypeProjection)((Pair)object1).component1();
                        TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)((Pair)object1).component2();
                        if(typeProjection0.getProjectionKind() != Variance.INVARIANT) {
                            UnwrappedType unwrappedType1 = typeProjection0.isStarProjection() || typeProjection0.getProjectionKind() != Variance.IN_VARIANCE ? null : typeProjection0.getType().unwrap();
                            Intrinsics.checkNotNullExpressionValue(typeParameterDescriptor0, "parameter");
                            typeProjection0 = TypeUtilsKt.asTypeProjection(new NewCapturedType(captureStatus0, unwrappedType1, typeProjection0, typeParameterDescriptor0));
                        }
                        arrayList0.add(typeProjection0);
                    }
                    TypeConstructor typeConstructor0 = unwrappedType0.getConstructor();
                    TypeSubstitutor typeSubstitutor0 = TypeConstructorSubstitution.Companion.create(typeConstructor0, arrayList0).buildSubstitutor();
                    int v = list0.size();
                    for(int v1 = 0; v1 < v; ++v1) {
                        TypeProjection typeProjection1 = (TypeProjection)list0.get(v1);
                        TypeProjection typeProjection2 = (TypeProjection)arrayList0.get(v1);
                        if(typeProjection1.getProjectionKind() != Variance.INVARIANT) {
                            List list2 = ((TypeParameterDescriptor)unwrappedType0.getConstructor().getParameters().get(v1)).getUpperBounds();
                            Intrinsics.checkNotNullExpressionValue(list2, "type.constructor.parameters[index].upperBounds");
                            Collection collection0 = new ArrayList();
                            for(Object object2: list2) {
                                KotlinTypeMarker kotlinTypeMarker0 = typeSubstitutor0.safeSubstitute(((KotlinType)object2), Variance.INVARIANT).unwrap();
                                collection0.add(Default.INSTANCE.prepareType(kotlinTypeMarker0));
                            }
                            if(!typeProjection1.isStarProjection() && typeProjection1.getProjectionKind() == Variance.OUT_VARIANCE) {
                                KotlinTypeMarker kotlinTypeMarker1 = typeProjection1.getType().unwrap();
                                ((List)collection0).add(Default.INSTANCE.prepareType(kotlinTypeMarker1));
                            }
                            KotlinType kotlinType0 = typeProjection2.getType();
                            Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedType");
                            ((NewCapturedType)kotlinType0).getConstructor().initializeSupertypes(((List)collection0));
                        }
                    }
                    return arrayList0;
                }
                if(false) {
                    break;
                }
            }
        }
        return null;
    }

    public static final SimpleType captureFromArguments(SimpleType simpleType0, CaptureStatus captureStatus0) {
        Intrinsics.checkNotNullParameter(simpleType0, "type");
        Intrinsics.checkNotNullParameter(captureStatus0, "status");
        List list0 = NewCapturedTypeKt.captureArguments(simpleType0, captureStatus0);
        return list0 == null ? null : NewCapturedTypeKt.replaceArguments(simpleType0, list0);
    }

    private static final SimpleType replaceArguments(UnwrappedType unwrappedType0, List list0) {
        return KotlinTypeFactory.simpleType$default(unwrappedType0.getAttributes(), unwrappedType0.getConstructor(), list0, unwrappedType0.isMarkedNullable(), null, 16, null);
    }
}

