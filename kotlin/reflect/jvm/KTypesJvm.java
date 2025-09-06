package kotlin.reflect.jvm;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u001C\u0010\u0000\u001A\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004\"\"\u0010\u0000\u001A\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001A\u0004\b\u0003\u0010\b¨\u0006\t"}, d2 = {"jvmErasure", "Lkotlin/reflect/KClass;", "Lkotlin/reflect/KClassifier;", "getJvmErasure", "(Lkotlin/reflect/KClassifier;)Lkotlin/reflect/KClass;", "Lkotlin/reflect/KType;", "getJvmErasure$annotations", "(Lkotlin/reflect/KType;)V", "(Lkotlin/reflect/KType;)Lkotlin/reflect/KClass;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class KTypesJvm {
    public static final KClass getJvmErasure(KClassifier kClassifier0) {
        ClassDescriptor classDescriptor0;
        Intrinsics.checkNotNullParameter(kClassifier0, "<this>");
        if(kClassifier0 instanceof KClass) {
            return (KClass)kClassifier0;
        }
        if(!(kClassifier0 instanceof KTypeParameter)) {
            throw new KotlinReflectionInternalError("Cannot calculate JVM erasure for type: " + kClassifier0);
        }
        List list0 = ((KTypeParameter)kClassifier0).getUpperBounds();
        Iterator iterator0 = list0.iterator();
        while(true) {
            classDescriptor0 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            Intrinsics.checkNotNull(((KType)object0), "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
            ClassifierDescriptor classifierDescriptor0 = ((KTypeImpl)(((KType)object0))).getType().getConstructor().getDeclarationDescriptor();
            if(classifierDescriptor0 instanceof ClassDescriptor) {
                classDescriptor0 = (ClassDescriptor)classifierDescriptor0;
            }
            if(classDescriptor0 != null && classDescriptor0.getKind() != ClassKind.INTERFACE && classDescriptor0.getKind() != ClassKind.ANNOTATION_CLASS) {
                classDescriptor0 = object0;
                break;
            }
        }
        KType kType0 = (KType)classDescriptor0;
        if(kType0 == null) {
            kType0 = (KType)CollectionsKt.firstOrNull(list0);
        }
        if(kType0 != null) {
            KClass kClass0 = KTypesJvm.getJvmErasure(kType0);
            return kClass0 == null ? Reflection.getOrCreateKotlinClass(Object.class) : kClass0;
        }
        return Reflection.getOrCreateKotlinClass(Object.class);
    }

    public static final KClass getJvmErasure(KType kType0) {
        Intrinsics.checkNotNullParameter(kType0, "<this>");
        KClassifier kClassifier0 = kType0.getClassifier();
        if(kClassifier0 != null) {
            KClass kClass0 = KTypesJvm.getJvmErasure(kClassifier0);
            if(kClass0 != null) {
                return kClass0;
            }
        }
        throw new KotlinReflectionInternalError("Cannot calculate JVM erasure for type: " + kType0);
    }

    public static void getJvmErasure$annotations(KType kType0) {
    }
}

