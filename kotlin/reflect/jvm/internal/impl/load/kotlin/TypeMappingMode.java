package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public final class TypeMappingMode {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Variance.values().length];
            try {
                arr_v[Variance.IN_VARIANCE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Variance.INVARIANT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final TypeMappingMode CLASS_DECLARATION;
    public static final Companion Companion;
    public static final TypeMappingMode DEFAULT;
    public static final TypeMappingMode DEFAULT_UAST;
    public static final TypeMappingMode GENERIC_ARGUMENT;
    public static final TypeMappingMode GENERIC_ARGUMENT_UAST;
    public static final TypeMappingMode RETURN_TYPE_BOXED;
    public static final TypeMappingMode SUPER_TYPE;
    public static final TypeMappingMode SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS;
    public static final TypeMappingMode VALUE_FOR_ANNOTATION;
    private final TypeMappingMode genericArgumentMode;
    private final TypeMappingMode genericContravariantArgumentMode;
    private final TypeMappingMode genericInvariantArgumentMode;
    private final boolean isForAnnotationParameter;
    private final boolean kotlinCollectionsToJavaCollections;
    private final boolean mapTypeAliases;
    private final boolean needInlineClassWrapping;
    private final boolean needPrimitiveBoxing;
    private final boolean skipDeclarationSiteWildcards;
    private final boolean skipDeclarationSiteWildcardsIfPossible;

    static {
        TypeMappingMode.Companion = new Companion(null);
        TypeMappingMode typeMappingMode0 = new TypeMappingMode(false, false, false, false, false, null, false, null, null, false, 0x3FF, null);
        TypeMappingMode.GENERIC_ARGUMENT = typeMappingMode0;
        TypeMappingMode typeMappingMode1 = new TypeMappingMode(false, false, false, false, false, null, false, null, null, true, 0x1FF, null);
        TypeMappingMode.GENERIC_ARGUMENT_UAST = typeMappingMode1;
        TypeMappingMode.RETURN_TYPE_BOXED = new TypeMappingMode(false, true, false, false, false, null, false, null, null, false, 0x3FD, null);
        TypeMappingMode.DEFAULT = new TypeMappingMode(false, false, false, false, false, typeMappingMode0, false, null, null, false, 988, null);
        TypeMappingMode.DEFAULT_UAST = new TypeMappingMode(false, false, false, false, false, typeMappingMode1, false, null, null, true, 476, null);
        TypeMappingMode.CLASS_DECLARATION = new TypeMappingMode(false, true, false, false, false, typeMappingMode0, false, null, null, false, 988, null);
        TypeMappingMode.SUPER_TYPE = new TypeMappingMode(false, false, false, true, false, typeMappingMode0, false, null, null, false, 983, null);
        TypeMappingMode.SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS = new TypeMappingMode(false, false, false, true, false, typeMappingMode0, false, null, null, false, 919, null);
        TypeMappingMode.VALUE_FOR_ANNOTATION = new TypeMappingMode(false, false, true, false, false, typeMappingMode0, false, null, null, false, 984, null);
    }

    public TypeMappingMode() {
        this(false, false, false, false, false, null, false, null, null, false, 0x3FF, null);
    }

    public TypeMappingMode(boolean z, boolean z1, boolean z2, boolean z3, boolean z4, TypeMappingMode typeMappingMode0, boolean z5, TypeMappingMode typeMappingMode1, TypeMappingMode typeMappingMode2, boolean z6) {
        this.needPrimitiveBoxing = z;
        this.needInlineClassWrapping = z1;
        this.isForAnnotationParameter = z2;
        this.skipDeclarationSiteWildcards = z3;
        this.skipDeclarationSiteWildcardsIfPossible = z4;
        this.genericArgumentMode = typeMappingMode0;
        this.kotlinCollectionsToJavaCollections = z5;
        this.genericContravariantArgumentMode = typeMappingMode1;
        this.genericInvariantArgumentMode = typeMappingMode2;
        this.mapTypeAliases = z6;
    }

    public TypeMappingMode(boolean z, boolean z1, boolean z2, boolean z3, boolean z4, TypeMappingMode typeMappingMode0, boolean z5, TypeMappingMode typeMappingMode1, TypeMappingMode typeMappingMode2, boolean z6, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            z = true;
        }
        if((v & 2) != 0) {
            z1 = true;
        }
        if((v & 4) != 0) {
            z2 = false;
        }
        if((v & 8) != 0) {
            z3 = false;
        }
        if((v & 16) != 0) {
            z4 = false;
        }
        if((v & 0x20) != 0) {
            typeMappingMode0 = null;
        }
        if((v & 0x40) != 0) {
            z5 = true;
        }
        if((v & 0x80) != 0) {
            typeMappingMode1 = typeMappingMode0;
        }
        if((v & 0x100) != 0) {
            typeMappingMode2 = typeMappingMode0;
        }
        this(z, z1, z2, z3, z4, typeMappingMode0, z5, typeMappingMode1, typeMappingMode2, ((v & 0x200) == 0 ? z6 : false));
    }

    public final boolean getKotlinCollectionsToJavaCollections() {
        return this.kotlinCollectionsToJavaCollections;
    }

    public final boolean getMapTypeAliases() {
        return this.mapTypeAliases;
    }

    public final boolean getNeedInlineClassWrapping() {
        return this.needInlineClassWrapping;
    }

    public final boolean getNeedPrimitiveBoxing() {
        return this.needPrimitiveBoxing;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    public final TypeMappingMode toGenericArgumentMode(Variance variance0, boolean z) {
        Intrinsics.checkNotNullParameter(variance0, "effectiveVariance");
        if(!z || !this.isForAnnotationParameter) {
            switch(WhenMappings.$EnumSwitchMapping$0[variance0.ordinal()]) {
                case 1: {
                    return this.genericContravariantArgumentMode == null ? this : this.genericContravariantArgumentMode;
                }
                case 2: {
                    return this.genericInvariantArgumentMode == null ? this : this.genericInvariantArgumentMode;
                }
                default: {
                    return this.genericArgumentMode == null ? this : this.genericArgumentMode;
                }
            }
        }
        return this;
    }

    public final TypeMappingMode wrapInlineClassesMode() {
        return new TypeMappingMode(this.needPrimitiveBoxing, true, this.isForAnnotationParameter, this.skipDeclarationSiteWildcards, this.skipDeclarationSiteWildcardsIfPossible, this.genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode, false, 0x200, null);
    }
}

