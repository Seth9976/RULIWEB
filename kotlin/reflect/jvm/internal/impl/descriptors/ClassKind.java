package kotlin.reflect.jvm.internal.impl.descriptors;

public enum ClassKind {
    CLASS("class"),
    INTERFACE("interface"),
    ENUM_CLASS("enum class"),
    ENUM_ENTRY(null),
    ANNOTATION_CLASS("annotation class"),
    OBJECT("object");

    private final String codeRepresentation;

    private static final ClassKind[] $values() [...] // Inlined contents

    private ClassKind(String s1) {
        this.codeRepresentation = s1;
    }

    public final boolean isSingleton() {
        return this == ClassKind.OBJECT || this == ClassKind.ENUM_ENTRY;
    }
}

