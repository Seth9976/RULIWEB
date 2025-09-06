package kotlin.reflect.jvm.internal.impl.load.java;

public enum AnnotationQualifierApplicabilityType {
    METHOD_RETURN_TYPE("METHOD"),
    VALUE_PARAMETER("PARAMETER"),
    FIELD("FIELD"),
    TYPE_USE("TYPE_USE"),
    TYPE_PARAMETER_BOUNDS("TYPE_USE"),
    TYPE_PARAMETER("TYPE_PARAMETER");

    private final String javaTarget;

    private static final AnnotationQualifierApplicabilityType[] $values() [...] // Inlined contents

    private AnnotationQualifierApplicabilityType(String s1) {
        this.javaTarget = s1;
    }

    public final String getJavaTarget() {
        return this.javaTarget;
    }
}

