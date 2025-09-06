package kotlin.reflect.jvm.internal.impl;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class SpecialJvmAnnotations {
    public static final SpecialJvmAnnotations INSTANCE;
    private static final ClassId JAVA_LANG_ANNOTATION_REPEATABLE;
    private static final Set SPECIAL_ANNOTATIONS;

    static {
        SpecialJvmAnnotations.INSTANCE = new SpecialJvmAnnotations();
        Iterable iterable0 = CollectionsKt.listOf(new FqName[]{JvmAnnotationNames.METADATA_FQ_NAME, JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION, JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION, JvmAnnotationNames.TARGET_ANNOTATION, JvmAnnotationNames.RETENTION_ANNOTATION, JvmAnnotationNames.DOCUMENTED_ANNOTATION});
        Collection collection0 = new LinkedHashSet();
        for(Object object0: iterable0) {
            collection0.add(ClassId.topLevel(((FqName)object0)));
        }
        SpecialJvmAnnotations.SPECIAL_ANNOTATIONS = (Set)collection0;
        ClassId classId0 = ClassId.topLevel(JvmAnnotationNames.REPEATABLE_ANNOTATION);
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(JvmAnnotationNames.REPEATABLE_ANNOTATION)");
        SpecialJvmAnnotations.JAVA_LANG_ANNOTATION_REPEATABLE = classId0;
    }

    public final ClassId getJAVA_LANG_ANNOTATION_REPEATABLE() {
        return SpecialJvmAnnotations.JAVA_LANG_ANNOTATION_REPEATABLE;
    }

    public final Set getSPECIAL_ANNOTATIONS() {
        return SpecialJvmAnnotations.SPECIAL_ANNOTATIONS;
    }

    public final boolean isAnnotatedWithContainerMetaAnnotation(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "klass");
        BooleanRef ref$BooleanRef0 = new BooleanRef();
        kotlinJvmBinaryClass0.loadClassAnnotations(new AnnotationVisitor() {
            @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationVisitor
            public AnnotationArgumentVisitor visitAnnotation(ClassId classId0, SourceElement sourceElement0) {
                Intrinsics.checkNotNullParameter(classId0, "classId");
                Intrinsics.checkNotNullParameter(sourceElement0, "source");
                if(Intrinsics.areEqual(classId0, JvmAbi.INSTANCE.getREPEATABLE_ANNOTATION_CONTAINER_META_ANNOTATION())) {
                    this.$result.element = true;
                }
                return null;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass$AnnotationVisitor
            public void visitEnd() {
            }
        }, null);
        return ref$BooleanRef0.element;
    }
}

