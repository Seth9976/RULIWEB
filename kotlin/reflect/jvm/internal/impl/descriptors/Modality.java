package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;

public enum Modality {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Modality convertFromFlags(boolean z, boolean z1, boolean z2) {
            if(z) {
                return Modality.SEALED;
            }
            if(z1) {
                return Modality.ABSTRACT;
            }
            return z2 ? Modality.OPEN : Modality.FINAL;
        }
    }

    FINAL,
    SEALED,
    OPEN,
    ABSTRACT;

    public static final Companion Companion;

    private static final Modality[] $values() [...] // Inlined contents

    static {
        Modality.Companion = new Companion(null);
    }
}

