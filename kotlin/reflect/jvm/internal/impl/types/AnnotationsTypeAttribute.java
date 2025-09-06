package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;

public final class AnnotationsTypeAttribute extends TypeAttribute {
    private final Annotations annotations;

    public AnnotationsTypeAttribute(Annotations annotations0) {
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        super();
        this.annotations = annotations0;
    }

    public AnnotationsTypeAttribute add(AnnotationsTypeAttribute annotationsTypeAttribute0) {
        return annotationsTypeAttribute0 == null ? this : new AnnotationsTypeAttribute(AnnotationsKt.composeAnnotations(this.annotations, annotationsTypeAttribute0.annotations));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeAttribute
    public TypeAttribute add(TypeAttribute typeAttribute0) {
        return this.add(((AnnotationsTypeAttribute)typeAttribute0));
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AnnotationsTypeAttribute ? Intrinsics.areEqual(((AnnotationsTypeAttribute)object0).annotations, this.annotations) : false;
    }

    public final Annotations getAnnotations() {
        return this.annotations;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeAttribute
    public KClass getKey() {
        return Reflection.getOrCreateKotlinClass(AnnotationsTypeAttribute.class);
    }

    @Override
    public int hashCode() {
        return this.annotations.hashCode();
    }

    // 去混淆评级： 低(20)
    public AnnotationsTypeAttribute intersect(AnnotationsTypeAttribute annotationsTypeAttribute0) {
        return Intrinsics.areEqual(annotationsTypeAttribute0, this) ? this : null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeAttribute
    public TypeAttribute intersect(TypeAttribute typeAttribute0) {
        return this.intersect(((AnnotationsTypeAttribute)typeAttribute0));
    }
}

