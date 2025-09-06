package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;

public final class TypeEnhancementUtilsKt {
    public static final JavaTypeQualifiers computeQualifiersForOverride(JavaTypeQualifiers javaTypeQualifiers0, Collection collection0, boolean z, boolean z1, boolean z2) {
        NullabilityQualifier nullabilityQualifier3;
        Intrinsics.checkNotNullParameter(javaTypeQualifiers0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "superQualifiers");
        Collection collection1 = new ArrayList();
        for(Object object0: collection0) {
            NullabilityQualifier nullabilityQualifier0 = TypeEnhancementUtilsKt.getNullabilityForErrors(((JavaTypeQualifiers)object0));
            if(nullabilityQualifier0 != null) {
                collection1.add(nullabilityQualifier0);
            }
        }
        NullabilityQualifier nullabilityQualifier1 = TypeEnhancementUtilsKt.select(CollectionsKt.toSet(((List)collection1)), TypeEnhancementUtilsKt.getNullabilityForErrors(javaTypeQualifiers0), z);
        if(nullabilityQualifier1 == null) {
            Collection collection2 = new ArrayList();
            for(Object object1: collection0) {
                NullabilityQualifier nullabilityQualifier2 = ((JavaTypeQualifiers)object1).getNullability();
                if(nullabilityQualifier2 != null) {
                    collection2.add(nullabilityQualifier2);
                }
            }
            nullabilityQualifier3 = TypeEnhancementUtilsKt.select(CollectionsKt.toSet(((List)collection2)), javaTypeQualifiers0.getNullability(), z);
        }
        else {
            nullabilityQualifier3 = nullabilityQualifier1;
        }
        Collection collection3 = new ArrayList();
        for(Object object2: collection0) {
            MutabilityQualifier mutabilityQualifier0 = ((JavaTypeQualifiers)object2).getMutability();
            if(mutabilityQualifier0 != null) {
                collection3.add(mutabilityQualifier0);
            }
        }
        boolean z3 = false;
        MutabilityQualifier mutabilityQualifier1 = (MutabilityQualifier)TypeEnhancementUtilsKt.select(CollectionsKt.toSet(((List)collection3)), MutabilityQualifier.MUTABLE, MutabilityQualifier.READ_ONLY, javaTypeQualifiers0.getMutability(), z);
        NullabilityQualifier nullabilityQualifier4 = nullabilityQualifier3 == null || z2 || z1 && nullabilityQualifier3 == NullabilityQualifier.NULLABLE ? null : nullabilityQualifier3;
        boolean z4 = true;
        if(nullabilityQualifier4 == NullabilityQualifier.NOT_NULL) {
            if(javaTypeQualifiers0.getDefinitelyNotNull()) {
                z3 = true;
            }
            else if(!collection0.isEmpty()) {
                for(Object object3: collection0) {
                    if(((JavaTypeQualifiers)object3).getDefinitelyNotNull()) {
                        z3 = true;
                        break;
                    }
                }
            }
        }
        if(nullabilityQualifier4 == null || nullabilityQualifier1 == nullabilityQualifier3) {
            z4 = false;
        }
        return new JavaTypeQualifiers(nullabilityQualifier4, mutabilityQualifier1, z3, z4);
    }

    // 去混淆评级： 低(20)
    private static final NullabilityQualifier getNullabilityForErrors(JavaTypeQualifiers javaTypeQualifiers0) {
        return javaTypeQualifiers0.isNullabilityQualifierForWarning() ? null : javaTypeQualifiers0.getNullability();
    }

    public static final boolean hasEnhancedNullability(TypeSystemCommonBackendContext typeSystemCommonBackendContext0, KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(typeSystemCommonBackendContext0, "<this>");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
        Intrinsics.checkNotNullExpressionValue(JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION, "ENHANCED_NULLABILITY_ANNOTATION");
        return typeSystemCommonBackendContext0.hasAnnotation(kotlinTypeMarker0, JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION);
    }

    private static final Object select(Set set0, Object object0, Object object1, Object object2, boolean z) {
        Object object3;
        if(z) {
            if(set0.contains(object0)) {
                object3 = object0;
            }
            else {
                object3 = set0.contains(object1) ? object1 : null;
            }
            if(Intrinsics.areEqual(object3, object0) && Intrinsics.areEqual(object2, object1)) {
                return null;
            }
            return object2 == null ? object3 : object2;
        }
        if(object2 != null) {
            Set set1 = CollectionsKt.toSet(SetsKt.plus(set0, object2));
            if(set1 != null) {
                set0 = set1;
            }
        }
        return CollectionsKt.singleOrNull(set0);
    }

    private static final NullabilityQualifier select(Set set0, NullabilityQualifier nullabilityQualifier0, boolean z) {
        return nullabilityQualifier0 == NullabilityQualifier.FORCE_FLEXIBILITY ? NullabilityQualifier.FORCE_FLEXIBILITY : ((NullabilityQualifier)TypeEnhancementUtilsKt.select(set0, NullabilityQualifier.NOT_NULL, NullabilityQualifier.NULLABLE, nullabilityQualifier0, z));
    }
}

