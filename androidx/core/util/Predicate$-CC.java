package androidx.core.util;

import java.util.Objects;

public final class Predicate.-CC {
    public static Predicate $default$and(Predicate _this, Predicate predicate1) {
        Objects.requireNonNull(predicate1);
        return new Predicate..ExternalSyntheticLambda4(_this, predicate1);
    }

    public static Predicate $default$negate(Predicate _this) {
        return new Predicate..ExternalSyntheticLambda5(_this);
    }

    public static Predicate $default$or(Predicate _this, Predicate predicate1) {
        Objects.requireNonNull(predicate1);
        return new Predicate..ExternalSyntheticLambda1(_this, predicate1);
    }

    // 去混淆评级： 低(20)
    public static boolean $private$lambda$and$0(Predicate _this, Predicate predicate1, Object object0) {
        return _this.test(object0) && predicate1.test(object0);
    }

    public static boolean $private$lambda$negate$1(Predicate _this, Object object0) {
        return !_this.test(object0);
    }

    // 去混淆评级： 低(20)
    public static boolean $private$lambda$or$2(Predicate _this, Predicate predicate1, Object object0) {
        return _this.test(object0) || predicate1.test(object0);
    }

    public static Predicate isEqual(Object object0) {
        return object0 == null ? new Predicate..ExternalSyntheticLambda2() : new Predicate..ExternalSyntheticLambda3(object0);
    }

    public static Predicate not(Predicate predicate0) {
        Objects.requireNonNull(predicate0);
        return predicate0.negate();
    }
}

