package kotlin.reflect.jvm.internal.impl.types;

import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public class TypeProjectionImpl extends TypeProjectionBase {
    private final Variance projection;
    private final KotlinType type;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 4 || v == 5 ? 2 : 3)];
        switch(v) {
            case 1: 
            case 2: 
            case 3: {
                arr_object[0] = "type";
                break;
            }
            case 4: 
            case 5: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
                break;
            }
            case 6: {
                arr_object[0] = "kotlinTypeRefiner";
                break;
            }
            default: {
                arr_object[0] = "projection";
            }
        }
        switch(v) {
            case 4: {
                arr_object[1] = "getProjectionKind";
                break;
            }
            case 5: {
                arr_object[1] = "getType";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/types/TypeProjectionImpl";
            }
        }
        switch(v) {
            case 3: {
                arr_object[2] = "replaceType";
                break;
            }
            case 4: 
            case 5: {
                break;
            }
            default: {
                arr_object[2] = v == 6 ? "refine" : "<init>";
            }
        }
        String s = String.format((v == 4 || v == 5 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 4 || v == 5 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    public TypeProjectionImpl(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeProjectionImpl.$$$reportNull$$$0(2);
        }
        this(Variance.INVARIANT, kotlinType0);
    }

    public TypeProjectionImpl(Variance variance0, KotlinType kotlinType0) {
        if(variance0 == null) {
            TypeProjectionImpl.$$$reportNull$$$0(0);
        }
        if(kotlinType0 == null) {
            TypeProjectionImpl.$$$reportNull$$$0(1);
        }
        super();
        this.projection = variance0;
        this.type = kotlinType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public Variance getProjectionKind() {
        Variance variance0 = this.projection;
        if(variance0 == null) {
            TypeProjectionImpl.$$$reportNull$$$0(4);
        }
        return variance0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public KotlinType getType() {
        KotlinType kotlinType0 = this.type;
        if(kotlinType0 == null) {
            TypeProjectionImpl.$$$reportNull$$$0(5);
        }
        return kotlinType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public boolean isStarProjection() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeProjection
    public TypeProjection refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        if(kotlinTypeRefiner0 == null) {
            TypeProjectionImpl.$$$reportNull$$$0(6);
        }
        KotlinType kotlinType0 = kotlinTypeRefiner0.refineType(this.type);
        return new TypeProjectionImpl(this.projection, kotlinType0);
    }
}

