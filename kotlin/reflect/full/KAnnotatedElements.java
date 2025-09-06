package kotlin.reflect.full;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KAnnotatedElement;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\"\n\u0002\b\u0002\n\u0002\u0010\u001B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\u001A \u0010\u0000\u001A\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\u0004\u001A\u001F\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0006\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0087\b\u001A*\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\u00010\bH\u0007\u001A\u0019\u0010\t\u001A\u00020\n\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0087\b¨\u0006\u000B"}, d2 = {"findAnnotation", "T", "", "Lkotlin/reflect/KAnnotatedElement;", "(Lkotlin/reflect/KAnnotatedElement;)Ljava/lang/annotation/Annotation;", "findAnnotations", "", "klass", "Lkotlin/reflect/KClass;", "hasAnnotation", "", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class KAnnotatedElements {
    public static final Annotation findAnnotation(KAnnotatedElement kAnnotatedElement0) {
        Intrinsics.checkNotNullParameter(kAnnotatedElement0, "<this>");
        for(Object object0: kAnnotatedElement0.getAnnotations()) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if(!(((Annotation)object0) instanceof Annotation)) {
                continue;
            }
            Intrinsics.reifiedOperationMarker(1, "T?");
            return (Annotation)object0;
        }
        Intrinsics.reifiedOperationMarker(1, "T?");
        return null;
    }

    public static final List findAnnotations(KAnnotatedElement kAnnotatedElement0) {
        Intrinsics.checkNotNullParameter(kAnnotatedElement0, "<this>");
        Intrinsics.reifiedOperationMarker(4, "T");
        return KAnnotatedElements.findAnnotations(kAnnotatedElement0, Reflection.getOrCreateKotlinClass(Annotation.class));
    }

    public static final List findAnnotations(KAnnotatedElement kAnnotatedElement0, KClass kClass0) {
        Intrinsics.checkNotNullParameter(kAnnotatedElement0, "<this>");
        Intrinsics.checkNotNullParameter(kClass0, "klass");
        List list0 = CollectionsKt.filterIsInstance(kAnnotatedElement0.getAnnotations(), JvmClassMappingKt.getJavaClass(kClass0));
        if(!list0.isEmpty()) {
            return list0;
        }
        Class class0 = JvmClassMappingKt.getJavaClass(kClass0);
        Class class1 = Java8RepeatableContainerLoader.INSTANCE.loadRepeatableContainer(class0);
        if(class1 != null) {
            Object object0 = null;
            for(Object object1: kAnnotatedElement0.getAnnotations()) {
                if(Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(((Annotation)object1))), class1)) {
                    object0 = object1;
                    break;
                }
            }
            if(((Annotation)object0) != null) {
                Object object2 = ((Annotation)object0).getClass().getMethod("value", null).invoke(((Annotation)object0), null);
                Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.reflect.full.KAnnotatedElements.findAnnotations>");
                return ArraysKt.asList(((Annotation[])object2));
            }
        }
        return CollectionsKt.emptyList();
    }

    public static final boolean hasAnnotation(KAnnotatedElement kAnnotatedElement0) {
        Intrinsics.checkNotNullParameter(kAnnotatedElement0, "<this>");
        for(Object object0: kAnnotatedElement0.getAnnotations()) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if(!(((Annotation)object0) instanceof Annotation)) {
                continue;
            }
            Intrinsics.reifiedOperationMarker(1, "T?");
            return ((Annotation)object0) != null;
        }
        object0 = null;
        Intrinsics.reifiedOperationMarker(1, "T?");
        return ((Annotation)object0) != null;
    }
}

