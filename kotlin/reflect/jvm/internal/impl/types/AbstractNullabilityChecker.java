package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;

public final class AbstractNullabilityChecker {
    public static final AbstractNullabilityChecker INSTANCE;

    static {
        AbstractNullabilityChecker.INSTANCE = new AbstractNullabilityChecker();
    }

    public final boolean hasNotNullSupertype(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, SupertypesPolicy typeCheckerState$SupertypesPolicy0) {
        Intrinsics.checkNotNullParameter(typeCheckerState0, "<this>");
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "type");
        Intrinsics.checkNotNullParameter(typeCheckerState$SupertypesPolicy0, "supertypesPolicy");
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        if(typeSystemContext0.isClassType(simpleTypeMarker0) && !typeSystemContext0.isMarkedNullable(simpleTypeMarker0) || typeSystemContext0.isDefinitelyNotNullType(simpleTypeMarker0)) {
            return true;
        }
        typeCheckerState0.initialize();
        ArrayDeque arrayDeque0 = typeCheckerState0.getSupertypesDeque();
        Intrinsics.checkNotNull(arrayDeque0);
        Set set0 = typeCheckerState0.getSupertypesSet();
        Intrinsics.checkNotNull(set0);
        arrayDeque0.push(simpleTypeMarker0);
        while(!arrayDeque0.isEmpty()) {
            if(set0.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker0 + ". Supertypes = " + CollectionsKt.joinToString$default(set0, null, null, null, 0, null, null, 0x3F, null)).toString());
            }
            SimpleTypeMarker simpleTypeMarker1 = (SimpleTypeMarker)arrayDeque0.pop();
            Intrinsics.checkNotNullExpressionValue(simpleTypeMarker1, "current");
            if(set0.add(simpleTypeMarker1)) {
                SupertypesPolicy typeCheckerState$SupertypesPolicy1 = typeSystemContext0.isMarkedNullable(simpleTypeMarker1) ? None.INSTANCE : typeCheckerState$SupertypesPolicy0;
                if(Intrinsics.areEqual(typeCheckerState$SupertypesPolicy1, None.INSTANCE)) {
                    typeCheckerState$SupertypesPolicy1 = null;
                }
                if(typeCheckerState$SupertypesPolicy1 != null) {
                    TypeSystemContext typeSystemContext1 = typeCheckerState0.getTypeSystemContext();
                    for(Object object0: typeSystemContext1.supertypes(typeSystemContext1.typeConstructor(simpleTypeMarker1))) {
                        SimpleTypeMarker simpleTypeMarker2 = typeCheckerState$SupertypesPolicy1.transformType(typeCheckerState0, ((KotlinTypeMarker)object0));
                        if(typeSystemContext0.isClassType(simpleTypeMarker2) && !typeSystemContext0.isMarkedNullable(simpleTypeMarker2) || typeSystemContext0.isDefinitelyNotNullType(simpleTypeMarker2)) {
                            typeCheckerState0.clear();
                            return true;
                        }
                        arrayDeque0.add(simpleTypeMarker2);
                    }
                }
            }
        }
        typeCheckerState0.clear();
        return false;
    }

    public final boolean hasPathByNotMarkedNullableNodes(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, TypeConstructorMarker typeConstructorMarker0) {
        Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "start");
        Intrinsics.checkNotNullParameter(typeConstructorMarker0, "end");
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        if(AbstractNullabilityChecker.INSTANCE.isApplicableAsEndNode(typeCheckerState0, simpleTypeMarker0, typeConstructorMarker0)) {
            return true;
        }
        typeCheckerState0.initialize();
        ArrayDeque arrayDeque0 = typeCheckerState0.getSupertypesDeque();
        Intrinsics.checkNotNull(arrayDeque0);
        Set set0 = typeCheckerState0.getSupertypesSet();
        Intrinsics.checkNotNull(set0);
        arrayDeque0.push(simpleTypeMarker0);
        while(!arrayDeque0.isEmpty()) {
            if(set0.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker0 + ". Supertypes = " + CollectionsKt.joinToString$default(set0, null, null, null, 0, null, null, 0x3F, null)).toString());
            }
            SimpleTypeMarker simpleTypeMarker1 = (SimpleTypeMarker)arrayDeque0.pop();
            Intrinsics.checkNotNullExpressionValue(simpleTypeMarker1, "current");
            if(set0.add(simpleTypeMarker1)) {
                None typeCheckerState$SupertypesPolicy$None0 = typeSystemContext0.isMarkedNullable(simpleTypeMarker1) ? None.INSTANCE : LowerIfFlexible.INSTANCE;
                SupertypesPolicy typeCheckerState$SupertypesPolicy0 = typeCheckerState$SupertypesPolicy$None0;
                if(Intrinsics.areEqual(typeCheckerState$SupertypesPolicy0, None.INSTANCE)) {
                    typeCheckerState$SupertypesPolicy0 = null;
                }
                if(typeCheckerState$SupertypesPolicy0 != null) {
                    TypeSystemContext typeSystemContext1 = typeCheckerState0.getTypeSystemContext();
                    for(Object object0: typeSystemContext1.supertypes(typeSystemContext1.typeConstructor(simpleTypeMarker1))) {
                        SimpleTypeMarker simpleTypeMarker2 = typeCheckerState$SupertypesPolicy0.transformType(typeCheckerState0, ((KotlinTypeMarker)object0));
                        if(AbstractNullabilityChecker.INSTANCE.isApplicableAsEndNode(typeCheckerState0, simpleTypeMarker2, typeConstructorMarker0)) {
                            typeCheckerState0.clear();
                            return true;
                        }
                        arrayDeque0.add(simpleTypeMarker2);
                    }
                }
            }
        }
        typeCheckerState0.clear();
        return false;
    }

    private final boolean isApplicableAsEndNode(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, TypeConstructorMarker typeConstructorMarker0) {
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        if(typeSystemContext0.isNothing(simpleTypeMarker0)) {
            return true;
        }
        if(typeSystemContext0.isMarkedNullable(simpleTypeMarker0)) {
            return false;
        }
        return !typeCheckerState0.isStubTypeEqualsToAnything() || !typeSystemContext0.isStubType(simpleTypeMarker0) ? typeSystemContext0.areEqualTypeConstructors(typeSystemContext0.typeConstructor(simpleTypeMarker0), typeConstructorMarker0) : true;
    }

    public final boolean isPossibleSubtype(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
        Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "subType");
        Intrinsics.checkNotNullParameter(simpleTypeMarker1, "superType");
        return this.runIsPossibleSubtype(typeCheckerState0, simpleTypeMarker0, simpleTypeMarker1);
    }

    private final boolean runIsPossibleSubtype(TypeCheckerState typeCheckerState0, SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
        TypeSystemContext typeSystemContext0 = typeCheckerState0.getTypeSystemContext();
        if(AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
            if(!typeSystemContext0.isSingleClassifierType(simpleTypeMarker0) && !typeSystemContext0.isIntersection(typeSystemContext0.typeConstructor(simpleTypeMarker0))) {
                typeCheckerState0.isAllowedTypeVariable(simpleTypeMarker0);
            }
            if(!typeSystemContext0.isSingleClassifierType(simpleTypeMarker1)) {
                typeCheckerState0.isAllowedTypeVariable(simpleTypeMarker1);
            }
        }
        if(typeSystemContext0.isMarkedNullable(simpleTypeMarker1)) {
            return true;
        }
        if(typeSystemContext0.isDefinitelyNotNullType(simpleTypeMarker0) || typeSystemContext0.isNotNullTypeParameter(simpleTypeMarker0) || simpleTypeMarker0 instanceof CapturedTypeMarker && typeSystemContext0.isProjectionNotNull(((CapturedTypeMarker)simpleTypeMarker0))) {
            return true;
        }
        AbstractNullabilityChecker abstractNullabilityChecker0 = AbstractNullabilityChecker.INSTANCE;
        if(abstractNullabilityChecker0.hasNotNullSupertype(typeCheckerState0, simpleTypeMarker0, LowerIfFlexible.INSTANCE)) {
            return true;
        }
        if(typeSystemContext0.isDefinitelyNotNullType(simpleTypeMarker1)) {
            return false;
        }
        if(abstractNullabilityChecker0.hasNotNullSupertype(typeCheckerState0, simpleTypeMarker1, UpperIfFlexible.INSTANCE)) {
            return false;
        }
        return typeSystemContext0.isClassType(simpleTypeMarker0) ? false : abstractNullabilityChecker0.hasPathByNotMarkedNullableNodes(typeCheckerState0, simpleTypeMarker0, typeSystemContext0.typeConstructor(simpleTypeMarker1));
    }
}

