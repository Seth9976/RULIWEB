package com.google.crypto.tink.aead;

import com.google.errorprone.annotations.Immutable;
import java.util.Objects;

public final class ChaCha20Poly1305Parameters extends AeadParameters {
    @Immutable
    public static final class Variant {
        public static final Variant CRUNCHY;
        public static final Variant NO_PREFIX;
        public static final Variant TINK;
        private final String name;

        static {
            Variant.TINK = new Variant("TINK");
            Variant.CRUNCHY = new Variant("CRUNCHY");
            Variant.NO_PREFIX = new Variant("NO_PREFIX");
        }

        private Variant(String s) {
            this.name = s;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private final Variant variant;

    private ChaCha20Poly1305Parameters(Variant chaCha20Poly1305Parameters$Variant0) {
        this.variant = chaCha20Poly1305Parameters$Variant0;
    }

    public static ChaCha20Poly1305Parameters create() {
        return new ChaCha20Poly1305Parameters(Variant.NO_PREFIX);
    }

    public static ChaCha20Poly1305Parameters create(Variant chaCha20Poly1305Parameters$Variant0) {
        return new ChaCha20Poly1305Parameters(chaCha20Poly1305Parameters$Variant0);
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ChaCha20Poly1305Parameters ? ((ChaCha20Poly1305Parameters)object0).getVariant() == this.getVariant() : false;
    }

    public Variant getVariant() {
        return this.variant;
    }

    @Override  // com.google.crypto.tink.Parameters
    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.variant);
    }

    @Override
    public String toString() {
        return "ChaCha20Poly1305 Parameters (variant: " + this.variant + ")";
    }
}

