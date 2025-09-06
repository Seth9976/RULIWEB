package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public abstract class AbstractAnnotationTypeQualifierResolver {
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final Companion Companion;
    private static final Map JAVA_APPLICABILITY_TYPES;
    private final JavaTypeEnhancementState javaTypeEnhancementState;
    private final ConcurrentHashMap resolvedNicknames;

    static {
        AbstractAnnotationTypeQualifierResolver.Companion = new Companion(null);
        Map map0 = new LinkedHashMap();
        AnnotationQualifierApplicabilityType[] arr_annotationQualifierApplicabilityType = AnnotationQualifierApplicabilityType.values();
        for(int v = 0; v < arr_annotationQualifierApplicabilityType.length; ++v) {
            AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType0 = arr_annotationQualifierApplicabilityType[v];
            if(map0.get("TYPE_PARAMETER") == null) {
                map0.put("TYPE_PARAMETER", annotationQualifierApplicabilityType0);
            }
        }
        AbstractAnnotationTypeQualifierResolver.JAVA_APPLICABILITY_TYPES = map0;
    }

    public AbstractAnnotationTypeQualifierResolver(JavaTypeEnhancementState javaTypeEnhancementState0) {
        Intrinsics.checkNotNullParameter(javaTypeEnhancementState0, "javaTypeEnhancementState");
        super();
        this.javaTypeEnhancementState = javaTypeEnhancementState0;
        this.resolvedNicknames = new ConcurrentHashMap();
    }

    // 去混淆评级： 低(20)
    private final Set allIfTypeUse(Set set0) {
        return set0.contains(AnnotationQualifierApplicabilityType.TYPE_USE) ? SetsKt.plus(SetsKt.minus(ArraysKt.toSet(AnnotationQualifierApplicabilityType.values()), AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS), set0) : set0;
    }

    protected abstract Iterable enumArguments(Object arg1, boolean arg2);

    public final JavaTypeQualifiersByElementType extractAndMergeDefaultQualifiers(JavaTypeQualifiersByElementType javaTypeQualifiersByElementType0, Iterable iterable0) {
        EnumMap enumMap1;
        Intrinsics.checkNotNullParameter(iterable0, "annotations");
        if(!this.javaTypeEnhancementState.getDisabledDefaultAnnotations()) {
            Collection collection0 = new ArrayList();
            for(Object object0: iterable0) {
                JavaDefaultQualifiers javaDefaultQualifiers0 = this.extractDefaultQualifiers(object0);
                if(javaDefaultQualifiers0 != null) {
                    collection0.add(javaDefaultQualifiers0);
                }
            }
            if(!((List)collection0).isEmpty()) {
                if(javaTypeQualifiersByElementType0 == null) {
                    enumMap1 = new EnumMap(AnnotationQualifierApplicabilityType.class);
                }
                else {
                    EnumMap enumMap0 = javaTypeQualifiersByElementType0.getDefaultQualifiers();
                    enumMap1 = enumMap0 == null ? new EnumMap(AnnotationQualifierApplicabilityType.class) : new EnumMap(enumMap0);
                }
                boolean z = false;
                for(Object object1: ((List)collection0)) {
                    JavaDefaultQualifiers javaDefaultQualifiers1 = (JavaDefaultQualifiers)object1;
                    for(Object object2: javaDefaultQualifiers1.getQualifierApplicabilityTypes()) {
                        enumMap1.put(((AnnotationQualifierApplicabilityType)object2), javaDefaultQualifiers1);
                        z = true;
                    }
                }
                return z ? new JavaTypeQualifiersByElementType(enumMap1) : javaTypeQualifiersByElementType0;
            }
        }
        return javaTypeQualifiersByElementType0;
    }

    private final JavaDefaultQualifiers extractDefaultQualifiers(Object object0) {
        JavaDefaultQualifiers javaDefaultQualifiers0 = this.resolveQualifierBuiltInDefaultAnnotation(object0);
        if(javaDefaultQualifiers0 != null) {
            return javaDefaultQualifiers0;
        }
        Pair pair0 = this.resolveTypeQualifierDefaultAnnotation(object0);
        if(pair0 == null) {
            return null;
        }
        Object object1 = pair0.component1();
        Set set0 = (Set)pair0.component2();
        if(this.resolveJsr305CustomState(object0) == null) {
            this.resolveJsr305AnnotationState(object1);
        }
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0 = this.extractNullability(object1, kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver.extractDefaultQualifiers.nullabilityQualifier.1.INSTANCE);
        return nullabilityQualifierWithMigrationStatus0 == null ? null : new JavaDefaultQualifiers(NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatus0, null, false, 1, null), set0, false, 4, null);

        final class kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver.extractDefaultQualifiers.nullabilityQualifier.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver.extractDefaultQualifiers.nullabilityQualifier.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver.extractDefaultQualifiers.nullabilityQualifier.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver.extractDefaultQualifiers.nullabilityQualifier.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver.extractDefaultQualifiers.nullabilityQualifier.1() {
                super(1);
            }

            public final Boolean invoke(Object object0) {
                Intrinsics.checkNotNullParameter(object0, "$this$extractNullability");
                return false;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(object0);
            }
        }

    }

    public final MutabilityQualifier extractMutability(Iterable iterable0) {
        MutabilityQualifier mutabilityQualifier1;
        Intrinsics.checkNotNullParameter(iterable0, "annotations");
        MutabilityQualifier mutabilityQualifier0 = null;
        for(Object object0: iterable0) {
            FqName fqName0 = this.getFqName(object0);
            if(JvmAnnotationNamesKt.getREAD_ONLY_ANNOTATIONS().contains(fqName0)) {
                mutabilityQualifier1 = MutabilityQualifier.READ_ONLY;
            }
            else {
                if(!JvmAnnotationNamesKt.getMUTABLE_ANNOTATIONS().contains(fqName0)) {
                    continue;
                }
                mutabilityQualifier1 = MutabilityQualifier.MUTABLE;
            }
            if(mutabilityQualifier0 != null && mutabilityQualifier0 != mutabilityQualifier1) {
                return null;
            }
            mutabilityQualifier0 = mutabilityQualifier1;
        }
        return mutabilityQualifier0;
    }

    private final NullabilityQualifierWithMigrationStatus extractNullability(Object object0, Function1 function10) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0 = this.knownNullability(object0, ((Boolean)function10.invoke(object0)).booleanValue());
        if(nullabilityQualifierWithMigrationStatus0 != null) {
            return nullabilityQualifierWithMigrationStatus0;
        }
        Object object1 = this.resolveTypeQualifierAnnotation(object0);
        if(object1 == null) {
            return null;
        }
        this.resolveJsr305AnnotationState(object0);
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus1 = this.knownNullability(object1, ((Boolean)function10.invoke(object1)).booleanValue());
        return nullabilityQualifierWithMigrationStatus1 == null ? null : NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatus1, null, false, 1, null);
    }

    public final NullabilityQualifierWithMigrationStatus extractNullability(Iterable iterable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(iterable0, "annotations");
        Intrinsics.checkNotNullParameter(function10, "forceWarning");
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0 = null;
        for(Object object0: iterable0) {
            NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus1 = this.extractNullability(object0, function10);
            if(nullabilityQualifierWithMigrationStatus0 != null) {
                if(nullabilityQualifierWithMigrationStatus1 == null || Intrinsics.areEqual(nullabilityQualifierWithMigrationStatus1, nullabilityQualifierWithMigrationStatus0) || nullabilityQualifierWithMigrationStatus1.isForWarningOnly() && !nullabilityQualifierWithMigrationStatus0.isForWarningOnly()) {
                    continue;
                }
                if(nullabilityQualifierWithMigrationStatus1.isForWarningOnly() || !nullabilityQualifierWithMigrationStatus0.isForWarningOnly()) {
                    return null;
                }
            }
            nullabilityQualifierWithMigrationStatus0 = nullabilityQualifierWithMigrationStatus1;
        }
        return nullabilityQualifierWithMigrationStatus0;
    }

    private final Object findAnnotation(Object object0, FqName fqName0) {
        for(Object object1: this.getMetaAnnotations(object0)) {
            if(Intrinsics.areEqual(this.getFqName(object1), fqName0)) {
                return object1;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    protected abstract FqName getFqName(Object arg1);

    protected abstract Object getKey(Object arg1);

    protected abstract Iterable getMetaAnnotations(Object arg1);

    private final boolean hasAnnotation(Object object0, FqName fqName0) {
        Iterable iterable0 = this.getMetaAnnotations(object0);
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object1: iterable0) {
            if(Intrinsics.areEqual(this.getFqName(object1), fqName0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public final boolean isTypeUseAnnotation(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "annotation");
        Object object1 = this.findAnnotation(object0, FqNames.target);
        if(object1 == null) {
            return false;
        }
        Iterable iterable0 = this.enumArguments(object1, false);
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object2: iterable0) {
            if(Intrinsics.areEqual(((String)object2), "TYPE")) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private final NullabilityQualifierWithMigrationStatus knownNullability(Object object0, boolean z) {
        NullabilityQualifier nullabilityQualifier0;
        FqName fqName0 = this.getFqName(object0);
        if(fqName0 == null) {
            return null;
        }
        boolean z1 = false;
        ReportLevel reportLevel0 = (ReportLevel)this.javaTypeEnhancementState.getGetReportLevelForAnnotation().invoke(fqName0);
        if(JvmAnnotationNamesKt.getNULLABLE_ANNOTATIONS().contains(fqName0)) {
            nullabilityQualifier0 = NullabilityQualifier.NULLABLE;
        }
        else {
            if(JvmAnnotationNamesKt.getNOT_NULL_ANNOTATIONS().contains(fqName0)) {
                nullabilityQualifier0 = NullabilityQualifier.NOT_NULL;
                goto label_40;
            }
            if((Intrinsics.areEqual(fqName0, JvmAnnotationNamesKt.getJSPECIFY_OLD_NULLABLE()) ? true : Intrinsics.areEqual(fqName0, JvmAnnotationNamesKt.getJSPECIFY_NULLABLE()))) {
                nullabilityQualifier0 = NullabilityQualifier.NULLABLE;
                goto label_40;
            }
            if((Intrinsics.areEqual(fqName0, JvmAnnotationNamesKt.getJSPECIFY_OLD_NULLNESS_UNKNOWN()) ? true : Intrinsics.areEqual(fqName0, JvmAnnotationNamesKt.getJSPECIFY_NULLNESS_UNKNOWN()))) {
                nullabilityQualifier0 = NullabilityQualifier.FORCE_FLEXIBILITY;
                goto label_40;
            }
            if(Intrinsics.areEqual(fqName0, JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION())) {
                String s = (String)CollectionsKt.firstOrNull(this.enumArguments(object0, false));
                if(s != null) {
                    switch(s) {
                        case "ALWAYS": {
                            break;
                        }
                        case "MAYBE": 
                        case "NEVER": {
                            nullabilityQualifier0 = NullabilityQualifier.NULLABLE;
                            goto label_40;
                        }
                        case "UNKNOWN": {
                            nullabilityQualifier0 = NullabilityQualifier.FORCE_FLEXIBILITY;
                            goto label_40;
                        }
                        default: {
                            return null;
                        }
                    }
                }
                nullabilityQualifier0 = NullabilityQualifier.NOT_NULL;
                goto label_40;
            }
            if(Intrinsics.areEqual(fqName0, JvmAnnotationNamesKt.getCOMPATQUAL_NULLABLE_ANNOTATION())) {
                nullabilityQualifier0 = NullabilityQualifier.NULLABLE;
                goto label_40;
            }
            if(Intrinsics.areEqual(fqName0, JvmAnnotationNamesKt.getCOMPATQUAL_NONNULL_ANNOTATION())) {
                nullabilityQualifier0 = NullabilityQualifier.NOT_NULL;
                goto label_40;
            }
            if(Intrinsics.areEqual(fqName0, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NON_NULL_ANNOTATION())) {
                nullabilityQualifier0 = NullabilityQualifier.NOT_NULL;
            }
            else if(Intrinsics.areEqual(fqName0, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NULLABLE_ANNOTATION())) {
                nullabilityQualifier0 = NullabilityQualifier.NULLABLE;
            }
            else {
                return null;
            }
        }
    label_40:
        if(z) {
            z1 = true;
        }
        return new NullabilityQualifierWithMigrationStatus(nullabilityQualifier0, z1);
    }

    private final ReportLevel resolveDefaultAnnotationState(Object object0) {
        FqName fqName0 = this.getFqName(object0);
        return fqName0 == null || !AnnotationQualifiersFqNamesKt.getJSPECIFY_DEFAULT_ANNOTATIONS().containsKey(fqName0) ? this.resolveJsr305AnnotationState(object0) : ((ReportLevel)this.javaTypeEnhancementState.getGetReportLevelForAnnotation().invoke(fqName0));
    }

    private final ReportLevel resolveJsr305AnnotationState(Object object0) {
        ReportLevel reportLevel0 = this.resolveJsr305CustomState(object0);
        return reportLevel0 == null ? this.javaTypeEnhancementState.getJsr305().getGlobalLevel() : reportLevel0;
    }

    private final ReportLevel resolveJsr305CustomState(Object object0) {
        ReportLevel reportLevel0 = (ReportLevel)this.javaTypeEnhancementState.getJsr305().getUserDefinedLevelForSpecificAnnotation().get(this.getFqName(object0));
        if(reportLevel0 != null) {
            return reportLevel0;
        }
        Object object1 = this.findAnnotation(object0, AnnotationQualifiersFqNamesKt.getMIGRATION_ANNOTATION_FQNAME());
        if(object1 != null) {
            Iterable iterable0 = this.enumArguments(object1, false);
            if(iterable0 != null) {
                String s = (String)CollectionsKt.firstOrNull(iterable0);
                if(s != null) {
                    ReportLevel reportLevel1 = this.javaTypeEnhancementState.getJsr305().getMigrationLevel();
                    if(reportLevel1 == null) {
                        switch(s) {
                            case "IGNORE": {
                                return ReportLevel.IGNORE;
                            }
                            case "STRICT": {
                                return ReportLevel.STRICT;
                            }
                            case "WARN": {
                                return ReportLevel.WARN;
                            }
                            default: {
                                return null;
                            }
                        }
                    }
                    return reportLevel1;
                }
            }
        }
        return null;
    }

    private final JavaDefaultQualifiers resolveQualifierBuiltInDefaultAnnotation(Object object0) {
        if(this.javaTypeEnhancementState.getDisabledDefaultAnnotations()) {
            return null;
        }
        Object object1 = AnnotationQualifiersFqNamesKt.getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS().get(this.getFqName(object0));
        if(((JavaDefaultQualifiers)object1) != null) {
            ReportLevel reportLevel0 = this.resolveDefaultAnnotationState(object0);
            if(reportLevel0 == ReportLevel.IGNORE) {
                reportLevel0 = null;
            }
            return reportLevel0 == null ? null : JavaDefaultQualifiers.copy$default(((JavaDefaultQualifiers)object1), NullabilityQualifierWithMigrationStatus.copy$default(((JavaDefaultQualifiers)object1).getNullabilityQualifier(), null, false, 1, null), null, false, 6, null);
        }
        return null;
    }

    public final Object resolveTypeQualifierAnnotation(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "annotation");
        if(this.javaTypeEnhancementState.getJsr305().isDisabled()) {
            return null;
        }
        if(!CollectionsKt.contains(AnnotationQualifiersFqNamesKt.getBUILT_IN_TYPE_QUALIFIER_FQ_NAMES(), this.getFqName(object0)) && !this.hasAnnotation(object0, AnnotationQualifiersFqNamesKt.getTYPE_QUALIFIER_FQNAME())) {
            if(!this.hasAnnotation(object0, AnnotationQualifiersFqNamesKt.getTYPE_QUALIFIER_NICKNAME_FQNAME())) {
                return null;
            }
            ConcurrentMap concurrentMap0 = this.resolvedNicknames;
            Object object1 = this.getKey(object0);
            Object object2 = concurrentMap0.get(object1);
            if(object2 == null) {
                Object object3 = null;
                for(Object object4: this.getMetaAnnotations(object0)) {
                    Object object5 = this.resolveTypeQualifierAnnotation(object4);
                    if(object5 != null) {
                        object3 = object5;
                        break;
                    }
                }
                if(object3 == null) {
                    return null;
                }
                Object object6 = concurrentMap0.putIfAbsent(object1, object3);
                return object6 == null ? object3 : object6;
            }
            return object2;
        }
        return object0;
    }

    private final Pair resolveTypeQualifierDefaultAnnotation(Object object0) {
        if(this.javaTypeEnhancementState.getJsr305().isDisabled()) {
            return null;
        }
        Object object1 = this.findAnnotation(object0, AnnotationQualifiersFqNamesKt.getTYPE_QUALIFIER_DEFAULT_FQNAME());
        if(object1 == null) {
            return null;
        }
        Object object2 = null;
        for(Object object3: this.getMetaAnnotations(object0)) {
            if(this.resolveTypeQualifierAnnotation(object3) != null) {
                object2 = object3;
                break;
            }
        }
        if(object2 == null) {
            return null;
        }
        Iterable iterable0 = this.enumArguments(object1, true);
        Collection collection0 = new LinkedHashSet();
        for(Object object4: iterable0) {
            AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType0 = (AnnotationQualifierApplicabilityType)AbstractAnnotationTypeQualifierResolver.JAVA_APPLICABILITY_TYPES.get(((String)object4));
            if(annotationQualifierApplicabilityType0 != null) {
                collection0.add(annotationQualifierApplicabilityType0);
            }
        }
        return new Pair(object2, this.allIfTypeUse(((Set)collection0)));
    }
}

