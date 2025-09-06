package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class JvmAnnotationNamesKt {
    private static final FqName ANDROIDX_RECENTLY_NON_NULL_ANNOTATION;
    private static final FqName ANDROIDX_RECENTLY_NULLABLE_ANNOTATION;
    private static final FqName COMPATQUAL_NONNULL_ANNOTATION;
    private static final FqName COMPATQUAL_NULLABLE_ANNOTATION;
    private static final FqName JAVAX_CHECKFORNULL_ANNOTATION;
    private static final FqName JAVAX_NONNULL_ANNOTATION;
    private static final FqName JSPECIFY_NULLABLE;
    private static final FqName JSPECIFY_NULLNESS_UNKNOWN;
    private static final FqName JSPECIFY_NULL_MARKED;
    private static final FqName JSPECIFY_OLD_NULLABLE;
    private static final FqName JSPECIFY_OLD_NULLNESS_UNKNOWN;
    private static final FqName JSPECIFY_OLD_NULL_MARKED;
    private static final Set MUTABLE_ANNOTATIONS;
    private static final List NOT_NULL_ANNOTATIONS;
    private static final Set NULLABILITY_ANNOTATIONS;
    private static final List NULLABLE_ANNOTATIONS;
    private static final Set READ_ONLY_ANNOTATIONS;
    private static final Map javaToKotlinNameMap;

    static {
        FqName fqName0 = new FqName("org.jspecify.nullness.Nullable");
        JvmAnnotationNamesKt.JSPECIFY_OLD_NULLABLE = fqName0;
        JvmAnnotationNamesKt.JSPECIFY_OLD_NULLNESS_UNKNOWN = new FqName("org.jspecify.nullness.NullnessUnspecified");
        FqName fqName1 = new FqName("org.jspecify.nullness.NullMarked");
        JvmAnnotationNamesKt.JSPECIFY_OLD_NULL_MARKED = fqName1;
        FqName fqName2 = new FqName("org.jspecify.annotations.Nullable");
        JvmAnnotationNamesKt.JSPECIFY_NULLABLE = fqName2;
        JvmAnnotationNamesKt.JSPECIFY_NULLNESS_UNKNOWN = new FqName("org.jspecify.annotations.NullnessUnspecified");
        FqName fqName3 = new FqName("org.jspecify.annotations.NullMarked");
        JvmAnnotationNamesKt.JSPECIFY_NULL_MARKED = fqName3;
        List list0 = CollectionsKt.listOf(new FqName[]{JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION, new FqName("androidx.annotation.Nullable"), new FqName("android.support.annotation.Nullable"), new FqName("android.annotation.Nullable"), new FqName("com.android.annotations.Nullable"), new FqName("org.eclipse.jdt.annotation.Nullable"), new FqName("org.checkerframework.checker.nullness.qual.Nullable"), new FqName("javax.annotation.Nullable"), new FqName("javax.annotation.CheckForNull"), new FqName("edu.umd.cs.findbugs.annotations.CheckForNull"), new FqName("edu.umd.cs.findbugs.annotations.Nullable"), new FqName("edu.umd.cs.findbugs.annotations.PossiblyNull"), new FqName("io.reactivex.annotations.Nullable"), new FqName("io.reactivex.rxjava3.annotations.Nullable")});
        JvmAnnotationNamesKt.NULLABLE_ANNOTATIONS = list0;
        FqName fqName4 = new FqName("javax.annotation.Nonnull");
        JvmAnnotationNamesKt.JAVAX_NONNULL_ANNOTATION = fqName4;
        JvmAnnotationNamesKt.JAVAX_CHECKFORNULL_ANNOTATION = new FqName("javax.annotation.CheckForNull");
        List list1 = CollectionsKt.listOf(new FqName[]{JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION, new FqName("edu.umd.cs.findbugs.annotations.NonNull"), new FqName("androidx.annotation.NonNull"), new FqName("android.support.annotation.NonNull"), new FqName("android.annotation.NonNull"), new FqName("com.android.annotations.NonNull"), new FqName("org.eclipse.jdt.annotation.NonNull"), new FqName("org.checkerframework.checker.nullness.qual.NonNull"), new FqName("lombok.NonNull"), new FqName("io.reactivex.annotations.NonNull"), new FqName("io.reactivex.rxjava3.annotations.NonNull")});
        JvmAnnotationNamesKt.NOT_NULL_ANNOTATIONS = list1;
        FqName fqName5 = new FqName("org.checkerframework.checker.nullness.compatqual.NullableDecl");
        JvmAnnotationNamesKt.COMPATQUAL_NULLABLE_ANNOTATION = fqName5;
        FqName fqName6 = new FqName("org.checkerframework.checker.nullness.compatqual.NonNullDecl");
        JvmAnnotationNamesKt.COMPATQUAL_NONNULL_ANNOTATION = fqName6;
        FqName fqName7 = new FqName("androidx.annotation.RecentlyNullable");
        JvmAnnotationNamesKt.ANDROIDX_RECENTLY_NULLABLE_ANNOTATION = fqName7;
        FqName fqName8 = new FqName("androidx.annotation.RecentlyNonNull");
        JvmAnnotationNamesKt.ANDROIDX_RECENTLY_NON_NULL_ANNOTATION = fqName8;
        JvmAnnotationNamesKt.NULLABILITY_ANNOTATIONS = SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(new LinkedHashSet(), list0), fqName4), list1), fqName5), fqName6), fqName7), fqName8), fqName0), fqName1), fqName2), fqName3);
        JvmAnnotationNamesKt.READ_ONLY_ANNOTATIONS = SetsKt.setOf(new FqName[]{JvmAnnotationNames.JETBRAINS_READONLY_ANNOTATION, JvmAnnotationNames.READONLY_ANNOTATION});
        JvmAnnotationNamesKt.MUTABLE_ANNOTATIONS = SetsKt.setOf(new FqName[]{JvmAnnotationNames.JETBRAINS_MUTABLE_ANNOTATION, JvmAnnotationNames.MUTABLE_ANNOTATION});
        JvmAnnotationNamesKt.javaToKotlinNameMap = MapsKt.mapOf(new Pair[]{TuplesKt.to(JvmAnnotationNames.TARGET_ANNOTATION, FqNames.target), TuplesKt.to(JvmAnnotationNames.RETENTION_ANNOTATION, FqNames.retention), TuplesKt.to(JvmAnnotationNames.DEPRECATED_ANNOTATION, FqNames.deprecated), TuplesKt.to(JvmAnnotationNames.DOCUMENTED_ANNOTATION, FqNames.mustBeDocumented)});
    }

    public static final FqName getANDROIDX_RECENTLY_NON_NULL_ANNOTATION() {
        return JvmAnnotationNamesKt.ANDROIDX_RECENTLY_NON_NULL_ANNOTATION;
    }

    public static final FqName getANDROIDX_RECENTLY_NULLABLE_ANNOTATION() {
        return JvmAnnotationNamesKt.ANDROIDX_RECENTLY_NULLABLE_ANNOTATION;
    }

    public static final FqName getCOMPATQUAL_NONNULL_ANNOTATION() {
        return JvmAnnotationNamesKt.COMPATQUAL_NONNULL_ANNOTATION;
    }

    public static final FqName getCOMPATQUAL_NULLABLE_ANNOTATION() {
        return JvmAnnotationNamesKt.COMPATQUAL_NULLABLE_ANNOTATION;
    }

    public static final FqName getJAVAX_CHECKFORNULL_ANNOTATION() {
        return JvmAnnotationNamesKt.JAVAX_CHECKFORNULL_ANNOTATION;
    }

    public static final FqName getJAVAX_NONNULL_ANNOTATION() {
        return JvmAnnotationNamesKt.JAVAX_NONNULL_ANNOTATION;
    }

    public static final FqName getJSPECIFY_NULLABLE() {
        return JvmAnnotationNamesKt.JSPECIFY_NULLABLE;
    }

    public static final FqName getJSPECIFY_NULLNESS_UNKNOWN() {
        return JvmAnnotationNamesKt.JSPECIFY_NULLNESS_UNKNOWN;
    }

    public static final FqName getJSPECIFY_NULL_MARKED() {
        return JvmAnnotationNamesKt.JSPECIFY_NULL_MARKED;
    }

    public static final FqName getJSPECIFY_OLD_NULLABLE() {
        return JvmAnnotationNamesKt.JSPECIFY_OLD_NULLABLE;
    }

    public static final FqName getJSPECIFY_OLD_NULLNESS_UNKNOWN() {
        return JvmAnnotationNamesKt.JSPECIFY_OLD_NULLNESS_UNKNOWN;
    }

    public static final FqName getJSPECIFY_OLD_NULL_MARKED() {
        return JvmAnnotationNamesKt.JSPECIFY_OLD_NULL_MARKED;
    }

    public static final Set getMUTABLE_ANNOTATIONS() {
        return JvmAnnotationNamesKt.MUTABLE_ANNOTATIONS;
    }

    public static final List getNOT_NULL_ANNOTATIONS() {
        return JvmAnnotationNamesKt.NOT_NULL_ANNOTATIONS;
    }

    public static final List getNULLABLE_ANNOTATIONS() {
        return JvmAnnotationNamesKt.NULLABLE_ANNOTATIONS;
    }

    public static final Set getREAD_ONLY_ANNOTATIONS() {
        return JvmAnnotationNamesKt.READ_ONLY_ANNOTATIONS;
    }
}

