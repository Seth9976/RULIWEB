package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public class DeserializedAnnotations implements Annotations {
    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue annotations$delegate;

    static {
        DeserializedAnnotations.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedAnnotations.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    }

    public DeserializedAnnotations(StorageManager storageManager0, Function0 function00) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(function00, "compute");
        super();
        this.annotations$delegate = storageManager0.createLazyValue(function00);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public AnnotationDescriptor findAnnotation(FqName fqName0) {
        return DefaultImpls.findAnnotation(this, fqName0);
    }

    private final List getAnnotations() {
        return (List)StorageKt.getValue(this.annotations$delegate, this, DeserializedAnnotations.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(FqName fqName0) {
        return DefaultImpls.hasAnnotation(this, fqName0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        return this.getAnnotations().isEmpty();
    }

    @Override
    public Iterator iterator() {
        return this.getAnnotations().iterator();
    }
}

