package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;

public final class ClassicTypeCheckerStateKt {
    public static final TypeCheckerState createClassicTypeCheckerState(boolean z, boolean z1, ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypePreparator kotlinTypePreparator0, KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(classicTypeSystemContext0, "typeSystemContext");
        Intrinsics.checkNotNullParameter(kotlinTypePreparator0, "kotlinTypePreparator");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return new TypeCheckerState(z, z1, true, classicTypeSystemContext0, kotlinTypePreparator0, kotlinTypeRefiner0);
    }

    public static TypeCheckerState createClassicTypeCheckerState$default(boolean z, boolean z1, ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypePreparator kotlinTypePreparator0, KotlinTypeRefiner kotlinTypeRefiner0, int v, Object object0) {
        if((v & 2) != 0) {
            z1 = true;
        }
        if((v & 4) != 0) {
            classicTypeSystemContext0 = SimpleClassicTypeSystemContext.INSTANCE;
        }
        if((v & 8) != 0) {
            kotlinTypePreparator0 = Default.INSTANCE;
        }
        if((v & 16) != 0) {
            kotlinTypeRefiner0 = kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner.Default.INSTANCE;
        }
        return ClassicTypeCheckerStateKt.createClassicTypeCheckerState(z, z1, classicTypeSystemContext0, kotlinTypePreparator0, kotlinTypeRefiner0);
    }
}

