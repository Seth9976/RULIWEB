package kotlin.reflect.jvm.internal.impl.types.checker;

public abstract class TypeRefinementSupport {
    public static final class Enabled extends TypeRefinementSupport {
        private final KotlinTypeRefiner typeRefiner;

        public final KotlinTypeRefiner getTypeRefiner() {
            return this.typeRefiner;
        }
    }

    private final boolean isEnabled;

    public final boolean isEnabled() {
        return this.isEnabled;
    }
}

