package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

public final class NewKotlinTypeCheckerImpl implements NewKotlinTypeChecker {
    private final KotlinTypePreparator kotlinTypePreparator;
    private final KotlinTypeRefiner kotlinTypeRefiner;
    private final OverridingUtil overridingUtil;

    public NewKotlinTypeCheckerImpl(KotlinTypeRefiner kotlinTypeRefiner0, KotlinTypePreparator kotlinTypePreparator0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        Intrinsics.checkNotNullParameter(kotlinTypePreparator0, "kotlinTypePreparator");
        super();
        this.kotlinTypeRefiner = kotlinTypeRefiner0;
        this.kotlinTypePreparator = kotlinTypePreparator0;
        OverridingUtil overridingUtil0 = OverridingUtil.createWithTypeRefiner(this.getKotlinTypeRefiner());
        Intrinsics.checkNotNullExpressionValue(overridingUtil0, "createWithTypeRefiner(kotlinTypeRefiner)");
        this.overridingUtil = overridingUtil0;
    }

    public NewKotlinTypeCheckerImpl(KotlinTypeRefiner kotlinTypeRefiner0, KotlinTypePreparator kotlinTypePreparator0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            kotlinTypePreparator0 = Default.INSTANCE;
        }
        this(kotlinTypeRefiner0, kotlinTypePreparator0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean equalTypes(KotlinType kotlinType0, KotlinType kotlinType1) {
        Intrinsics.checkNotNullParameter(kotlinType0, "a");
        Intrinsics.checkNotNullParameter(kotlinType1, "b");
        return this.equalTypes(ClassicTypeCheckerStateKt.createClassicTypeCheckerState$default(false, false, null, this.getKotlinTypePreparator(), this.getKotlinTypeRefiner(), 6, null), kotlinType0.unwrap(), kotlinType1.unwrap());
    }

    public final boolean equalTypes(TypeCheckerState typeCheckerState0, UnwrappedType unwrappedType0, UnwrappedType unwrappedType1) {
        Intrinsics.checkNotNullParameter(typeCheckerState0, "<this>");
        Intrinsics.checkNotNullParameter(unwrappedType0, "a");
        Intrinsics.checkNotNullParameter(unwrappedType1, "b");
        return AbstractTypeChecker.INSTANCE.equalTypes(typeCheckerState0, unwrappedType0, unwrappedType1);
    }

    public KotlinTypePreparator getKotlinTypePreparator() {
        return this.kotlinTypePreparator;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    public KotlinTypeRefiner getKotlinTypeRefiner() {
        return this.kotlinTypeRefiner;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker
    public OverridingUtil getOverridingUtil() {
        return this.overridingUtil;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker
    public boolean isSubtypeOf(KotlinType kotlinType0, KotlinType kotlinType1) {
        Intrinsics.checkNotNullParameter(kotlinType0, "subtype");
        Intrinsics.checkNotNullParameter(kotlinType1, "supertype");
        return this.isSubtypeOf(ClassicTypeCheckerStateKt.createClassicTypeCheckerState$default(true, false, null, this.getKotlinTypePreparator(), this.getKotlinTypeRefiner(), 6, null), kotlinType0.unwrap(), kotlinType1.unwrap());
    }

    public final boolean isSubtypeOf(TypeCheckerState typeCheckerState0, UnwrappedType unwrappedType0, UnwrappedType unwrappedType1) {
        Intrinsics.checkNotNullParameter(typeCheckerState0, "<this>");
        Intrinsics.checkNotNullParameter(unwrappedType0, "subType");
        Intrinsics.checkNotNullParameter(unwrappedType1, "superType");
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerState0, unwrappedType0, unwrappedType1, false, 8, null);
    }
}

