package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class AnnotationQualifiersFqNamesKt {
    private static final Map BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS;
    private static final Set BUILT_IN_TYPE_QUALIFIER_FQ_NAMES;
    private static final List DEFAULT_JSPECIFY_APPLICABILITY;
    private static final Map JSPECIFY_DEFAULT_ANNOTATIONS;
    private static final FqName MIGRATION_ANNOTATION_FQNAME;
    private static final FqName TYPE_QUALIFIER_DEFAULT_FQNAME;
    private static final FqName TYPE_QUALIFIER_FQNAME;
    private static final FqName TYPE_QUALIFIER_NICKNAME_FQNAME;

    static {
        AnnotationQualifiersFqNamesKt.TYPE_QUALIFIER_NICKNAME_FQNAME = new FqName("javax.annotation.meta.TypeQualifierNickname");
        AnnotationQualifiersFqNamesKt.TYPE_QUALIFIER_FQNAME = new FqName("javax.annotation.meta.TypeQualifier");
        AnnotationQualifiersFqNamesKt.TYPE_QUALIFIER_DEFAULT_FQNAME = new FqName("javax.annotation.meta.TypeQualifierDefault");
        AnnotationQualifiersFqNamesKt.MIGRATION_ANNOTATION_FQNAME = new FqName("kotlin.annotations.jvm.UnderMigration");
        List list0 = CollectionsKt.listOf(new AnnotationQualifierApplicabilityType[]{AnnotationQualifierApplicabilityType.FIELD, AnnotationQualifierApplicabilityType.METHOD_RETURN_TYPE, AnnotationQualifierApplicabilityType.VALUE_PARAMETER, AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS, AnnotationQualifierApplicabilityType.TYPE_USE});
        AnnotationQualifiersFqNamesKt.DEFAULT_JSPECIFY_APPLICABILITY = list0;
        Map map0 = MapsKt.mapOf(new Pair[]{TuplesKt.to(JvmAnnotationNamesKt.getJSPECIFY_OLD_NULL_MARKED(), new JavaDefaultQualifiers(new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null), list0, false)), TuplesKt.to(JvmAnnotationNamesKt.getJSPECIFY_NULL_MARKED(), new JavaDefaultQualifiers(new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null), list0, false))});
        AnnotationQualifiersFqNamesKt.JSPECIFY_DEFAULT_ANNOTATIONS = map0;
        AnnotationQualifiersFqNamesKt.BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS = MapsKt.plus(MapsKt.mapOf(new Pair[]{TuplesKt.to(new FqName("javax.annotation.ParametersAreNullableByDefault"), new JavaDefaultQualifiers(new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null), CollectionsKt.listOf(AnnotationQualifierApplicabilityType.VALUE_PARAMETER), false, 4, null)), TuplesKt.to(new FqName("javax.annotation.ParametersAreNonnullByDefault"), new JavaDefaultQualifiers(new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null), CollectionsKt.listOf(AnnotationQualifierApplicabilityType.VALUE_PARAMETER), false, 4, null))}), map0);
        AnnotationQualifiersFqNamesKt.BUILT_IN_TYPE_QUALIFIER_FQ_NAMES = SetsKt.setOf(new FqName[]{JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION(), JvmAnnotationNamesKt.getJAVAX_CHECKFORNULL_ANNOTATION()});
    }

    public static final Map getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS() {
        return AnnotationQualifiersFqNamesKt.BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS;
    }

    public static final Set getBUILT_IN_TYPE_QUALIFIER_FQ_NAMES() {
        return AnnotationQualifiersFqNamesKt.BUILT_IN_TYPE_QUALIFIER_FQ_NAMES;
    }

    public static final Map getJSPECIFY_DEFAULT_ANNOTATIONS() {
        return AnnotationQualifiersFqNamesKt.JSPECIFY_DEFAULT_ANNOTATIONS;
    }

    public static final FqName getMIGRATION_ANNOTATION_FQNAME() {
        return AnnotationQualifiersFqNamesKt.MIGRATION_ANNOTATION_FQNAME;
    }

    public static final FqName getTYPE_QUALIFIER_DEFAULT_FQNAME() {
        return AnnotationQualifiersFqNamesKt.TYPE_QUALIFIER_DEFAULT_FQNAME;
    }

    public static final FqName getTYPE_QUALIFIER_FQNAME() {
        return AnnotationQualifiersFqNamesKt.TYPE_QUALIFIER_FQNAME;
    }

    public static final FqName getTYPE_QUALIFIER_NICKNAME_FQNAME() {
        return AnnotationQualifiersFqNamesKt.TYPE_QUALIFIER_NICKNAME_FQNAME;
    }
}

