package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

final class EnhancedTypeAnnotationDescriptor implements AnnotationDescriptor {
    public static final EnhancedTypeAnnotationDescriptor INSTANCE;

    static {
        EnhancedTypeAnnotationDescriptor.INSTANCE = new EnhancedTypeAnnotationDescriptor();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public Map getAllValueArguments() {
        this.throwError();
        throw null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public FqName getFqName() {
        return DefaultImpls.getFqName(this);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public SourceElement getSource() {
        this.throwError();
        throw null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public KotlinType getType() {
        this.throwError();
        throw null;
    }

    // 去混淆评级： 低(20)
    private final Void throwError() {
        throw new IllegalStateException("No methods should be called on this descriptor. Only its presence matters");
    }

    @Override
    public String toString() {
        return "[EnhancedType]";
    }
}

