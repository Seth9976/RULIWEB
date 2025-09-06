package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class ReflectJavaAnnotationOwnerKt {
    public static final ReflectJavaAnnotation findAnnotation(Annotation[] arr_annotation, FqName fqName0) {
        Intrinsics.checkNotNullParameter(arr_annotation, "<this>");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        for(int v = 0; v < arr_annotation.length; ++v) {
            Annotation annotation0 = arr_annotation[v];
            if(Intrinsics.areEqual(ReflectClassUtilKt.getClassId(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation0))).asSingleFqName(), fqName0)) {
                return annotation0 == null ? null : new ReflectJavaAnnotation(annotation0);
            }
        }
        return null;
    }

    public static final List getAnnotations(Annotation[] arr_annotation) {
        Intrinsics.checkNotNullParameter(arr_annotation, "<this>");
        ArrayList arrayList0 = new ArrayList(arr_annotation.length);
        for(int v = 0; v < arr_annotation.length; ++v) {
            arrayList0.add(new ReflectJavaAnnotation(arr_annotation[v]));
        }
        return arrayList0;
    }
}

