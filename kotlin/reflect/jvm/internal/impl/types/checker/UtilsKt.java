package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayDeque;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.typesApproximation.CapturedTypeApproximationKt;

public final class UtilsKt {
    private static final KotlinType approximate(KotlinType kotlinType0) {
        return (KotlinType)CapturedTypeApproximationKt.approximateCapturedTypes(kotlinType0).getUpper();
    }

    private static final String debugInfo(TypeConstructor typeConstructor0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        UtilsKt.debugInfo$lambda$1$unaryPlus(("type: " + typeConstructor0), stringBuilder0);
        UtilsKt.debugInfo$lambda$1$unaryPlus(("hashCode: " + typeConstructor0.hashCode()), stringBuilder0);
        UtilsKt.debugInfo$lambda$1$unaryPlus(("javaClass: " + typeConstructor0.getClass().getCanonicalName()), stringBuilder0);
        for(DeclarationDescriptor declarationDescriptor0 = typeConstructor0.getDeclarationDescriptor(); declarationDescriptor0 != null; declarationDescriptor0 = declarationDescriptor0.getContainingDeclaration()) {
            UtilsKt.debugInfo$lambda$1$unaryPlus(("fqName: " + DescriptorRenderer.FQ_NAMES_IN_TYPES.render(declarationDescriptor0)), stringBuilder0);
            UtilsKt.debugInfo$lambda$1$unaryPlus(("javaClass: " + declarationDescriptor0.getClass().getCanonicalName()), stringBuilder0);
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    private static final StringBuilder debugInfo$lambda$1$unaryPlus(String s, StringBuilder stringBuilder0) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        stringBuilder0.append(s);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        return stringBuilder0;
    }

    public static final KotlinType findCorrespondingSupertype(KotlinType kotlinType0, KotlinType kotlinType1, TypeCheckingProcedureCallbacks typeCheckingProcedureCallbacks0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "subtype");
        Intrinsics.checkNotNullParameter(kotlinType1, "supertype");
        Intrinsics.checkNotNullParameter(typeCheckingProcedureCallbacks0, "typeCheckingProcedureCallbacks");
        ArrayDeque arrayDeque0 = new ArrayDeque();
        arrayDeque0.add(new SubtypePathNode(kotlinType0, null));
        TypeConstructor typeConstructor0 = kotlinType1.getConstructor();
        while(!arrayDeque0.isEmpty()) {
            SubtypePathNode subtypePathNode0 = (SubtypePathNode)arrayDeque0.poll();
            KotlinType kotlinType2 = subtypePathNode0.getType();
            TypeConstructor typeConstructor1 = kotlinType2.getConstructor();
            if(typeCheckingProcedureCallbacks0.assertEqualTypeConstructors(typeConstructor1, typeConstructor0)) {
                boolean z = kotlinType2.isMarkedNullable();
                SubtypePathNode subtypePathNode1 = subtypePathNode0.getPrevious();
                while(subtypePathNode1 != null) {
                    KotlinType kotlinType3 = subtypePathNode1.getType();
                    Iterable iterable0 = kotlinType3.getArguments();
                    if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                        for(Object object0: iterable0) {
                            if(((TypeProjection)object0).getProjectionKind() == Variance.INVARIANT) {
                                continue;
                            }
                            KotlinType kotlinType4 = CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(TypeConstructorSubstitution.Companion.create(kotlinType3), false, 1, null).buildSubstitutor().safeSubstitute(kotlinType2, Variance.INVARIANT);
                            Intrinsics.checkNotNullExpressionValue(kotlinType4, "TypeConstructorSubstitut…uted, Variance.INVARIANT)");
                            kotlinType2 = UtilsKt.approximate(kotlinType4);
                            goto label_27;
                        }
                    }
                    kotlinType2 = TypeConstructorSubstitution.Companion.create(kotlinType3).buildSubstitutor().safeSubstitute(kotlinType2, Variance.INVARIANT);
                    Intrinsics.checkNotNullExpressionValue(kotlinType2, "{\n                    Ty…ARIANT)\n                }");
                label_27:
                    z = z || kotlinType3.isMarkedNullable();
                    subtypePathNode1 = subtypePathNode1.getPrevious();
                }
                TypeConstructor typeConstructor2 = kotlinType2.getConstructor();
                if(!typeCheckingProcedureCallbacks0.assertEqualTypeConstructors(typeConstructor2, typeConstructor0)) {
                    throw new AssertionError("Type constructors should be equals!\nsubstitutedSuperType: " + UtilsKt.debugInfo(typeConstructor2) + ", \n\nsupertype: " + UtilsKt.debugInfo(typeConstructor0) + " \n" + typeCheckingProcedureCallbacks0.assertEqualTypeConstructors(typeConstructor2, typeConstructor0));
                }
                return TypeUtils.makeNullableAsSpecified(kotlinType2, z);
            }
            for(Object object1: typeConstructor1.getSupertypes()) {
                Intrinsics.checkNotNullExpressionValue(((KotlinType)object1), "immediateSupertype");
                arrayDeque0.add(new SubtypePathNode(((KotlinType)object1), subtypePathNode0));
            }
        }
        return null;
    }
}

