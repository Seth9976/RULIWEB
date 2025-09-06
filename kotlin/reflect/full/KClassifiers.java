package kotlin.reflect.full;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.KClassifierImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionBase;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u001B\n\u0000\u001A.\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\f\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000F0\u000E2\u0006\u0010\u0010\u001A\u00020\u0011H\u0002\u001A6\u0010\u0012\u001A\u00020\u0001*\u00020\u00022\u000E\b\u0002\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000F0\u000E2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\u000E\b\u0002\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00140\u000EH\u0007\"\u001E\u0010\u0000\u001A\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"starProjectedType", "Lkotlin/reflect/KType;", "Lkotlin/reflect/KClassifier;", "getStarProjectedType$annotations", "(Lkotlin/reflect/KClassifier;)V", "getStarProjectedType", "(Lkotlin/reflect/KClassifier;)Lkotlin/reflect/KType;", "createKotlinType", "Lkotlin/reflect/jvm/internal/impl/types/SimpleType;", "attributes", "Lkotlin/reflect/jvm/internal/impl/types/TypeAttributes;", "typeConstructor", "Lkotlin/reflect/jvm/internal/impl/types/TypeConstructor;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "nullable", "", "createType", "annotations", "", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class KClassifiers {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[KVariance.values().length];
            try {
                arr_v[KVariance.INVARIANT.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[KVariance.IN.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[KVariance.OUT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private static final SimpleType createKotlinType(TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z) {
        TypeProjectionBase typeProjectionBase0;
        List list1 = typeConstructor0.getParameters();
        Intrinsics.checkNotNullExpressionValue(list1, "typeConstructor.parameters");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        int v = 0;
        for(Object object0: list0) {
            if(v < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KTypeImpl kTypeImpl0 = (KTypeImpl)((KTypeProjection)object0).getType();
            KotlinType kotlinType0 = kTypeImpl0 == null ? null : kTypeImpl0.getType();
            KVariance kVariance0 = ((KTypeProjection)object0).getVariance();
            switch((kVariance0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kVariance0.ordinal()])) {
                case -1: {
                    Object object1 = list1.get(v);
                    Intrinsics.checkNotNullExpressionValue(object1, "parameters[index]");
                    typeProjectionBase0 = new StarProjectionImpl(((TypeParameterDescriptor)object1));
                    break;
                }
                case 1: {
                    Intrinsics.checkNotNull(kotlinType0);
                    typeProjectionBase0 = new TypeProjectionImpl(Variance.INVARIANT, kotlinType0);
                    break;
                }
                case 2: {
                    Intrinsics.checkNotNull(kotlinType0);
                    typeProjectionBase0 = new TypeProjectionImpl(Variance.IN_VARIANCE, kotlinType0);
                    break;
                }
                case 3: {
                    Intrinsics.checkNotNull(kotlinType0);
                    typeProjectionBase0 = new TypeProjectionImpl(Variance.OUT_VARIANCE, kotlinType0);
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
            arrayList0.add(typeProjectionBase0);
            ++v;
        }
        return KotlinTypeFactory.simpleType$default(typeAttributes0, typeConstructor0, arrayList0, z, null, 16, null);
    }

    public static final KType createType(KClassifier kClassifier0, List list0, boolean z, List list1) {
        Intrinsics.checkNotNullParameter(kClassifier0, "<this>");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        Intrinsics.checkNotNullParameter(list1, "annotations");
        KClassifierImpl kClassifierImpl0 = kClassifier0 instanceof KClassifierImpl ? ((KClassifierImpl)kClassifier0) : null;
        if(kClassifierImpl0 != null) {
            ClassifierDescriptor classifierDescriptor0 = kClassifierImpl0.getDescriptor();
            if(classifierDescriptor0 != null) {
                TypeConstructor typeConstructor0 = classifierDescriptor0.getTypeConstructor();
                Intrinsics.checkNotNullExpressionValue(typeConstructor0, "descriptor.typeConstructor");
                List list2 = typeConstructor0.getParameters();
                Intrinsics.checkNotNullExpressionValue(list2, "typeConstructor.parameters");
                if(list2.size() != list0.size()) {
                    throw new IllegalArgumentException("Class declares " + list2.size() + " type parameters, but " + list0.size() + " were provided.");
                }
                return list1.isEmpty() ? new KTypeImpl(KClassifiers.createKotlinType(TypeAttributes.Companion.getEmpty(), typeConstructor0, list0, z), null, 2, null) : new KTypeImpl(KClassifiers.createKotlinType(TypeAttributes.Companion.getEmpty(), typeConstructor0, list0, z), null, 2, null);
            }
        }
        throw new KotlinReflectionInternalError("Cannot create type for an unsupported classifier: " + kClassifier0 + " (" + kClassifier0.getClass() + ')');
    }

    public static KType createType$default(KClassifier kClassifier0, List list0, boolean z, List list1, int v, Object object0) {
        if((v & 1) != 0) {
            list0 = CollectionsKt.emptyList();
        }
        if((v & 2) != 0) {
            z = false;
        }
        if((v & 4) != 0) {
            list1 = CollectionsKt.emptyList();
        }
        return KClassifiers.createType(kClassifier0, list0, z, list1);
    }

    public static final KType getStarProjectedType(KClassifier kClassifier0) {
        Intrinsics.checkNotNullParameter(kClassifier0, "<this>");
        KClassifierImpl kClassifierImpl0 = kClassifier0 instanceof KClassifierImpl ? ((KClassifierImpl)kClassifier0) : null;
        if(kClassifierImpl0 != null) {
            ClassifierDescriptor classifierDescriptor0 = kClassifierImpl0.getDescriptor();
            if(classifierDescriptor0 != null) {
                List list0 = classifierDescriptor0.getTypeConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(list0, "descriptor.typeConstructor.parameters");
                if(list0.isEmpty()) {
                    return KClassifiers.createType$default(kClassifier0, null, false, null, 7, null);
                }
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object0: list0) {
                    TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)object0;
                    arrayList0.add(KTypeProjection.Companion.getSTAR());
                }
                return KClassifiers.createType$default(kClassifier0, arrayList0, false, null, 6, null);
            }
        }
        return KClassifiers.createType$default(kClassifier0, null, false, null, 7, null);
    }

    public static void getStarProjectedType$annotations(KClassifier kClassifier0) {
    }
}

