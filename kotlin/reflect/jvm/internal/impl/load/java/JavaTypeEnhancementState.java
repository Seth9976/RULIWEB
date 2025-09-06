package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class JavaTypeEnhancementState {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final JavaTypeEnhancementState getDEFAULT() {
            return JavaTypeEnhancementState.DEFAULT;
        }
    }

    public static final Companion Companion;
    private static final JavaTypeEnhancementState DEFAULT;
    private final boolean disabledDefaultAnnotations;
    private final Function1 getReportLevelForAnnotation;
    private final Jsr305Settings jsr305;

    static {
        JavaTypeEnhancementState.Companion = new Companion(null);
        JavaTypeEnhancementState.DEFAULT = new JavaTypeEnhancementState(JavaNullabilityAnnotationSettingsKt.getDefaultJsr305Settings$default(null, 1, null), JavaTypeEnhancementState.Companion.DEFAULT.1.INSTANCE);
    }

    public JavaTypeEnhancementState(Jsr305Settings jsr305Settings0, Function1 function10) {
        Intrinsics.checkNotNullParameter(jsr305Settings0, "jsr305");
        Intrinsics.checkNotNullParameter(function10, "getReportLevelForAnnotation");
        super();
        this.jsr305 = jsr305Settings0;
        this.getReportLevelForAnnotation = function10;
        this.disabledDefaultAnnotations = jsr305Settings0.isDisabled() || function10.invoke(JavaNullabilityAnnotationSettingsKt.getJSPECIFY_ANNOTATIONS_PACKAGE()) == ReportLevel.IGNORE;
    }

    public final boolean getDisabledDefaultAnnotations() {
        return this.disabledDefaultAnnotations;
    }

    public final Function1 getGetReportLevelForAnnotation() {
        return this.getReportLevelForAnnotation;
    }

    public final Jsr305Settings getJsr305() {
        return this.jsr305;
    }

    @Override
    public String toString() {
        return "JavaTypeEnhancementState(jsr305=" + this.jsr305 + ", getReportLevelForAnnotation=" + this.getReportLevelForAnnotation + ')';
    }
}

