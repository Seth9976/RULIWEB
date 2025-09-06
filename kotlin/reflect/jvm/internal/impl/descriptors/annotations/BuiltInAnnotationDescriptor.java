package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class BuiltInAnnotationDescriptor implements AnnotationDescriptor {
    private final Map allValueArguments;
    private final KotlinBuiltIns builtIns;
    private final FqName fqName;
    private final Lazy type$delegate;

    public BuiltInAnnotationDescriptor(KotlinBuiltIns kotlinBuiltIns0, FqName fqName0, Map map0) {
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(map0, "allValueArguments");
        super();
        this.builtIns = kotlinBuiltIns0;
        this.fqName = fqName0;
        this.allValueArguments = map0;
        Function0 function00 = new Function0() {
            {
                BuiltInAnnotationDescriptor.this = builtInAnnotationDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final SimpleType invoke() {
                return BuiltInAnnotationDescriptor.access$getBuiltIns$p(BuiltInAnnotationDescriptor.this).getBuiltInClassByFqName(BuiltInAnnotationDescriptor.this.getFqName()).getDefaultType();
            }
        };
        this.type$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
    }

    public static final KotlinBuiltIns access$getBuiltIns$p(BuiltInAnnotationDescriptor builtInAnnotationDescriptor0) {
        return builtInAnnotationDescriptor0.builtIns;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public Map getAllValueArguments() {
        return this.allValueArguments;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public FqName getFqName() {
        return this.fqName;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public SourceElement getSource() {
        Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
        return SourceElement.NO_SOURCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public KotlinType getType() {
        Object object0 = this.type$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(object0, "<get-type>(...)");
        return (KotlinType)object0;
    }
}

