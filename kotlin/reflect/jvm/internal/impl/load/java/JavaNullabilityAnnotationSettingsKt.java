package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.KotlinVersion;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class JavaNullabilityAnnotationSettingsKt {
    private static final FqName CHECKER_FRAMEWORK_COMPATQUAL_ANNOTATIONS_PACKAGE;
    private static final FqName JSPECIFY_ANNOTATIONS_PACKAGE;
    private static final FqName JSPECIFY_OLD_ANNOTATIONS_PACKAGE;
    private static final JavaNullabilityAnnotationsStatus JSR_305_DEFAULT_SETTINGS;
    private static final NullabilityAnnotationStates NULLABILITY_ANNOTATION_SETTINGS;
    private static final FqName[] RXJAVA3_ANNOTATIONS;
    private static final FqName RXJAVA3_ANNOTATIONS_PACKAGE;
    private static final String RXJAVA3_ANNOTATIONS_PACKAGE_NAME;

    static {
        FqName fqName0 = new FqName("org.jspecify.nullness");
        JavaNullabilityAnnotationSettingsKt.JSPECIFY_OLD_ANNOTATIONS_PACKAGE = fqName0;
        FqName fqName1 = new FqName("org.jspecify.annotations");
        JavaNullabilityAnnotationSettingsKt.JSPECIFY_ANNOTATIONS_PACKAGE = fqName1;
        FqName fqName2 = new FqName("io.reactivex.rxjava3.annotations");
        JavaNullabilityAnnotationSettingsKt.RXJAVA3_ANNOTATIONS_PACKAGE = fqName2;
        FqName fqName3 = new FqName("org.checkerframework.checker.nullness.compatqual");
        JavaNullabilityAnnotationSettingsKt.CHECKER_FRAMEWORK_COMPATQUAL_ANNOTATIONS_PACKAGE = fqName3;
        Intrinsics.checkNotNullExpressionValue("io.reactivex.rxjava3.annotations", "RXJAVA3_ANNOTATIONS_PACKAGE.asString()");
        JavaNullabilityAnnotationSettingsKt.RXJAVA3_ANNOTATIONS_PACKAGE_NAME = "io.reactivex.rxjava3.annotations";
        JavaNullabilityAnnotationSettingsKt.RXJAVA3_ANNOTATIONS = new FqName[]{new FqName("io.reactivex.rxjava3.annotations.Nullable"), new FqName("io.reactivex.rxjava3.annotations.NonNull")};
        Pair[] arr_pair = new Pair[17];
        arr_pair[0] = TuplesKt.to(new FqName("org.jetbrains.annotations"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[1] = TuplesKt.to(new FqName("androidx.annotation"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[2] = TuplesKt.to(new FqName("android.support.annotation"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[3] = TuplesKt.to(new FqName("android.annotation"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[4] = TuplesKt.to(new FqName("com.android.annotations"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[5] = TuplesKt.to(new FqName("org.eclipse.jdt.annotation"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[6] = TuplesKt.to(new FqName("org.checkerframework.checker.nullness.qual"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[7] = TuplesKt.to(fqName3, JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[8] = TuplesKt.to(new FqName("javax.annotation"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[9] = TuplesKt.to(new FqName("edu.umd.cs.findbugs.annotations"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[10] = TuplesKt.to(new FqName("io.reactivex.annotations"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        arr_pair[11] = TuplesKt.to(new FqName("androidx.annotation.RecentlyNullable"), new JavaNullabilityAnnotationsStatus(ReportLevel.WARN, null, null, 4, null));
        arr_pair[12] = TuplesKt.to(new FqName("androidx.annotation.RecentlyNonNull"), new JavaNullabilityAnnotationsStatus(ReportLevel.WARN, null, null, 4, null));
        arr_pair[13] = TuplesKt.to(new FqName("lombok"), JavaNullabilityAnnotationsStatus.Companion.getDEFAULT());
        KotlinVersion kotlinVersion0 = new KotlinVersion(1, 9);
        arr_pair[14] = TuplesKt.to(fqName0, new JavaNullabilityAnnotationsStatus(ReportLevel.WARN, kotlinVersion0, ReportLevel.STRICT));
        KotlinVersion kotlinVersion1 = new KotlinVersion(1, 9);
        arr_pair[15] = TuplesKt.to(fqName1, new JavaNullabilityAnnotationsStatus(ReportLevel.WARN, kotlinVersion1, ReportLevel.STRICT));
        KotlinVersion kotlinVersion2 = new KotlinVersion(1, 8);
        arr_pair[16] = TuplesKt.to(fqName2, new JavaNullabilityAnnotationsStatus(ReportLevel.WARN, kotlinVersion2, ReportLevel.STRICT));
        JavaNullabilityAnnotationSettingsKt.NULLABILITY_ANNOTATION_SETTINGS = new NullabilityAnnotationStatesImpl(MapsKt.mapOf(arr_pair));
        JavaNullabilityAnnotationSettingsKt.JSR_305_DEFAULT_SETTINGS = new JavaNullabilityAnnotationsStatus(ReportLevel.WARN, null, null, 4, null);
    }

    public static final Jsr305Settings getDefaultJsr305Settings(KotlinVersion kotlinVersion0) {
        ReportLevel reportLevel0;
        Intrinsics.checkNotNullParameter(kotlinVersion0, "configuredKotlinVersion");
        JavaNullabilityAnnotationsStatus javaNullabilityAnnotationsStatus0 = JavaNullabilityAnnotationSettingsKt.JSR_305_DEFAULT_SETTINGS;
        if(javaNullabilityAnnotationsStatus0.getSinceVersion() != null && javaNullabilityAnnotationsStatus0.getSinceVersion().compareTo(kotlinVersion0) <= 0) {
            reportLevel0 = javaNullabilityAnnotationsStatus0.getReportLevelAfter();
            return new Jsr305Settings(reportLevel0, JavaNullabilityAnnotationSettingsKt.getDefaultMigrationJsr305ReportLevelForGivenGlobal(reportLevel0), null, 4, null);
        }
        reportLevel0 = javaNullabilityAnnotationsStatus0.getReportLevelBefore();
        return new Jsr305Settings(reportLevel0, JavaNullabilityAnnotationSettingsKt.getDefaultMigrationJsr305ReportLevelForGivenGlobal(reportLevel0), null, 4, null);
    }

    public static Jsr305Settings getDefaultJsr305Settings$default(KotlinVersion kotlinVersion0, int v, Object object0) {
        if((v & 1) != 0) {
            kotlinVersion0 = KotlinVersion.CURRENT;
        }
        return JavaNullabilityAnnotationSettingsKt.getDefaultJsr305Settings(kotlinVersion0);
    }

    public static final ReportLevel getDefaultMigrationJsr305ReportLevelForGivenGlobal(ReportLevel reportLevel0) {
        Intrinsics.checkNotNullParameter(reportLevel0, "globalReportLevel");
        return reportLevel0 == ReportLevel.WARN ? null : reportLevel0;
    }

    public static final ReportLevel getDefaultReportLevelForAnnotation(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "annotationFqName");
        return JavaNullabilityAnnotationSettingsKt.getReportLevelForAnnotation$default(fqName0, NullabilityAnnotationStates.Companion.getEMPTY(), null, 4, null);
    }

    public static final FqName getJSPECIFY_ANNOTATIONS_PACKAGE() {
        return JavaNullabilityAnnotationSettingsKt.JSPECIFY_ANNOTATIONS_PACKAGE;
    }

    public static final FqName[] getRXJAVA3_ANNOTATIONS() {
        return JavaNullabilityAnnotationSettingsKt.RXJAVA3_ANNOTATIONS;
    }

    public static final ReportLevel getReportLevelForAnnotation(FqName fqName0, NullabilityAnnotationStates nullabilityAnnotationStates0, KotlinVersion kotlinVersion0) {
        Intrinsics.checkNotNullParameter(fqName0, "annotation");
        Intrinsics.checkNotNullParameter(nullabilityAnnotationStates0, "configuredReportLevels");
        Intrinsics.checkNotNullParameter(kotlinVersion0, "configuredKotlinVersion");
        ReportLevel reportLevel0 = (ReportLevel)nullabilityAnnotationStates0.get(fqName0);
        if(reportLevel0 != null) {
            return reportLevel0;
        }
        JavaNullabilityAnnotationsStatus javaNullabilityAnnotationsStatus0 = (JavaNullabilityAnnotationsStatus)JavaNullabilityAnnotationSettingsKt.NULLABILITY_ANNOTATION_SETTINGS.get(fqName0);
        if(javaNullabilityAnnotationsStatus0 == null) {
            return ReportLevel.IGNORE;
        }
        return javaNullabilityAnnotationsStatus0.getSinceVersion() == null || javaNullabilityAnnotationsStatus0.getSinceVersion().compareTo(kotlinVersion0) > 0 ? javaNullabilityAnnotationsStatus0.getReportLevelBefore() : javaNullabilityAnnotationsStatus0.getReportLevelAfter();
    }

    public static ReportLevel getReportLevelForAnnotation$default(FqName fqName0, NullabilityAnnotationStates nullabilityAnnotationStates0, KotlinVersion kotlinVersion0, int v, Object object0) {
        if((v & 4) != 0) {
            kotlinVersion0 = new KotlinVersion(1, 7, 20);
        }
        return JavaNullabilityAnnotationSettingsKt.getReportLevelForAnnotation(fqName0, nullabilityAnnotationStates0, kotlinVersion0);
    }
}

