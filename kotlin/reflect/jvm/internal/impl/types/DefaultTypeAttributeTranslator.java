package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

public final class DefaultTypeAttributeTranslator implements TypeAttributeTranslator {
    public static final DefaultTypeAttributeTranslator INSTANCE;

    static {
        DefaultTypeAttributeTranslator.INSTANCE = new DefaultTypeAttributeTranslator();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeAttributeTranslator
    public TypeAttributes toAttributes(Annotations annotations0, TypeConstructor typeConstructor0, DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        if(annotations0.isEmpty()) {
            return TypeAttributes.Companion.getEmpty();
        }
        List list0 = CollectionsKt.listOf(new AnnotationsTypeAttribute(annotations0));
        return TypeAttributes.Companion.create(list0);
    }
}

