package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.DefaultConstructorMarker;

public enum ReportLevel {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    IGNORE("ignore"),
    WARN("warn"),
    STRICT("strict");

    public static final Companion Companion;
    private final String description;

    private static final ReportLevel[] $values() [...] // Inlined contents

    static {
        ReportLevel.Companion = new Companion(null);
    }

    private ReportLevel(String s1) {
        this.description = s1;
    }

    public final String getDescription() {
        return this.description;
    }

    public final boolean isIgnore() [...] // 潜在的解密器

    public final boolean isWarning() [...] // 潜在的解密器
}

