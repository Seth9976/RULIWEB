package kotlin.reflect.jvm.internal.impl.types;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.text.StringsKt;

public abstract class SimpleType extends UnwrappedType implements SimpleTypeMarker, TypeArgumentListMarker {
    public SimpleType() {
        super(null);
    }

    public abstract SimpleType makeNullableAsSpecified(boolean arg1);

    public abstract SimpleType replaceAttributes(TypeAttributes arg1);

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        for(Object object0: this.getAnnotations()) {
            StringsKt.append(stringBuilder0, new String[]{"[", DescriptorRenderer.renderAnnotation$default(DescriptorRenderer.DEBUG_TEXT, ((AnnotationDescriptor)object0), null, 2, null), "] "});
        }
        stringBuilder0.append(this.getConstructor());
        if(!this.getArguments().isEmpty()) {
            CollectionsKt.joinTo$default(this.getArguments(), stringBuilder0, ", ", "<", ">", 0, null, null, 0x70, null);
        }
        if(this.isMarkedNullable()) {
            stringBuilder0.append("?");
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }
}

