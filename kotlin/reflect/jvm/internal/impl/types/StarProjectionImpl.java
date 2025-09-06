package kotlin.reflect.jvm.internal.impl.types;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class StarProjectionImpl extends TypeProjectionBase {
    private final Lazy _type$delegate;
    private final TypeParameterDescriptor typeParameter;

    public StarProjectionImpl(TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        super();
        this.typeParameter = typeParameterDescriptor0;
        Function0 function00 = new Function0() {
            {
                StarProjectionImpl.this = starProjectionImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final KotlinType invoke() {
                return StarProjectionImplKt.starProjectionType(StarProjectionImpl.this.typeParameter);
            }
        };
        this._type$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public Variance getProjectionKind() {
        return Variance.OUT_VARIANCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public KotlinType getType() {
        return this.get_type();
    }

    private final KotlinType get_type() {
        return (KotlinType)this._type$delegate.getValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public boolean isStarProjection() {
        return true;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public TypeProjection refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return this;
    }
}

