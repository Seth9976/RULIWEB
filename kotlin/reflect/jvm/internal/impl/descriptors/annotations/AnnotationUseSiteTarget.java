package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

public enum AnnotationUseSiteTarget {
    FIELD(null, 1, null),
    FILE(null, 1, null),
    PROPERTY(null, 1, null),
    PROPERTY_GETTER("get"),
    PROPERTY_SETTER("set"),
    RECEIVER(null, 1, null),
    CONSTRUCTOR_PARAMETER("param"),
    SETTER_PARAMETER("setparam"),
    PROPERTY_DELEGATE_FIELD("delegate");

    private final String renderName;

    private static final AnnotationUseSiteTarget[] $values() [...] // Inlined contents

    private AnnotationUseSiteTarget(String s1) {
        if(s1 == null) {
            s1 = CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(this.name());
        }
        this.renderName = s1;
    }

    AnnotationUseSiteTarget(String s1, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            s1 = null;
        }
        this(s1);
    }

    public final String getRenderName() {
        return this.renderName;
    }
}

