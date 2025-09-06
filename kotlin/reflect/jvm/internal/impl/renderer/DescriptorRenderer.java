package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;

public abstract class DescriptorRenderer {
    public static final class Companion {
        public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] arr_v = new int[ClassKind.values().length];
                try {
                    arr_v[ClassKind.CLASS.ordinal()] = 1;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[ClassKind.INTERFACE.ordinal()] = 2;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[ClassKind.ENUM_CLASS.ordinal()] = 3;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[ClassKind.OBJECT.ordinal()] = 4;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[ClassKind.ANNOTATION_CLASS.ordinal()] = 5;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[ClassKind.ENUM_ENTRY.ordinal()] = 6;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                WhenMappings.$EnumSwitchMapping$0 = arr_v;
            }
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final String getClassifierKindPrefix(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters0) {
            Intrinsics.checkNotNullParameter(classifierDescriptorWithTypeParameters0, "classifier");
            if(classifierDescriptorWithTypeParameters0 instanceof TypeAliasDescriptor) {
                return "typealias";
            }
            if(classifierDescriptorWithTypeParameters0 instanceof ClassDescriptor) {
                if(((ClassDescriptor)classifierDescriptorWithTypeParameters0).isCompanionObject()) {
                    return "companion object";
                }
                switch(WhenMappings.$EnumSwitchMapping$0[((ClassDescriptor)classifierDescriptorWithTypeParameters0).getKind().ordinal()]) {
                    case 1: {
                        return "class";
                    }
                    case 2: {
                        return "interface";
                    }
                    case 3: {
                        return "enum class";
                    }
                    case 4: {
                        return "object";
                    }
                    case 5: {
                        return "annotation class";
                    }
                    case 6: {
                        return "enum entry";
                    }
                    default: {
                        throw new NoWhenBranchMatchedException();
                    }
                }
            }
            throw new AssertionError("Unexpected classifier: " + classifierDescriptorWithTypeParameters0);
        }

        public final DescriptorRenderer withOptions(Function1 function10) {
            Intrinsics.checkNotNullParameter(function10, "changeOptions");
            DescriptorRendererOptionsImpl descriptorRendererOptionsImpl0 = new DescriptorRendererOptionsImpl();
            function10.invoke(descriptorRendererOptionsImpl0);
            descriptorRendererOptionsImpl0.lock();
            return new DescriptorRendererImpl(descriptorRendererOptionsImpl0);
        }
    }

    public interface ValueParametersHandler {
        public static final class DEFAULT implements ValueParametersHandler {
            public static final DEFAULT INSTANCE;

            static {
                DEFAULT.INSTANCE = new DEFAULT();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer$ValueParametersHandler
            public void appendAfterValueParameter(ValueParameterDescriptor valueParameterDescriptor0, int v, int v1, StringBuilder stringBuilder0) {
                Intrinsics.checkNotNullParameter(valueParameterDescriptor0, "parameter");
                Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
                if(v != v1 - 1) {
                    stringBuilder0.append(", ");
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer$ValueParametersHandler
            public void appendAfterValueParameters(int v, StringBuilder stringBuilder0) {
                Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
                stringBuilder0.append(")");
            }

            @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer$ValueParametersHandler
            public void appendBeforeValueParameter(ValueParameterDescriptor valueParameterDescriptor0, int v, int v1, StringBuilder stringBuilder0) {
                Intrinsics.checkNotNullParameter(valueParameterDescriptor0, "parameter");
                Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            }

            @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer$ValueParametersHandler
            public void appendBeforeValueParameters(int v, StringBuilder stringBuilder0) {
                Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
                stringBuilder0.append("(");
            }
        }

        void appendAfterValueParameter(ValueParameterDescriptor arg1, int arg2, int arg3, StringBuilder arg4);

        void appendAfterValueParameters(int arg1, StringBuilder arg2);

        void appendBeforeValueParameter(ValueParameterDescriptor arg1, int arg2, int arg3, StringBuilder arg4);

        void appendBeforeValueParameters(int arg1, StringBuilder arg2);
    }

    public static final DescriptorRenderer COMPACT;
    public static final DescriptorRenderer COMPACT_WITHOUT_SUPERTYPES;
    public static final DescriptorRenderer COMPACT_WITH_MODIFIERS;
    public static final DescriptorRenderer COMPACT_WITH_SHORT_TYPES;
    public static final Companion Companion;
    public static final DescriptorRenderer DEBUG_TEXT;
    public static final DescriptorRenderer FQ_NAMES_IN_TYPES;
    public static final DescriptorRenderer FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS;
    public static final DescriptorRenderer HTML;
    public static final DescriptorRenderer ONLY_NAMES_WITH_SHORT_TYPES;
    public static final DescriptorRenderer SHORT_NAMES_IN_TYPES;

    static {
        Companion descriptorRenderer$Companion0 = new Companion(null);
        DescriptorRenderer.Companion = descriptorRenderer$Companion0;
        DescriptorRenderer.COMPACT_WITH_MODIFIERS = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.COMPACT_WITH_MODIFIERS.1.INSTANCE);
        DescriptorRenderer.COMPACT = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.COMPACT.1.INSTANCE);
        DescriptorRenderer.COMPACT_WITHOUT_SUPERTYPES = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.COMPACT_WITHOUT_SUPERTYPES.1.INSTANCE);
        DescriptorRenderer.COMPACT_WITH_SHORT_TYPES = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.COMPACT_WITH_SHORT_TYPES.1.INSTANCE);
        DescriptorRenderer.ONLY_NAMES_WITH_SHORT_TYPES = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.ONLY_NAMES_WITH_SHORT_TYPES.1.INSTANCE);
        DescriptorRenderer.FQ_NAMES_IN_TYPES = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.FQ_NAMES_IN_TYPES.1.INSTANCE);
        DescriptorRenderer.FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS.1.INSTANCE);
        DescriptorRenderer.SHORT_NAMES_IN_TYPES = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.SHORT_NAMES_IN_TYPES.1.INSTANCE);
        DescriptorRenderer.DEBUG_TEXT = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.DEBUG_TEXT.1.INSTANCE);
        DescriptorRenderer.HTML = descriptorRenderer$Companion0.withOptions(DescriptorRenderer.Companion.HTML.1.INSTANCE);
    }

    public abstract String render(DeclarationDescriptor arg1);

    public abstract String renderAnnotation(AnnotationDescriptor arg1, AnnotationUseSiteTarget arg2);

    public static String renderAnnotation$default(DescriptorRenderer descriptorRenderer0, AnnotationDescriptor annotationDescriptor0, AnnotationUseSiteTarget annotationUseSiteTarget0, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: renderAnnotation");
        }
        if((v & 2) != 0) {
            annotationUseSiteTarget0 = null;
        }
        return descriptorRenderer0.renderAnnotation(annotationDescriptor0, annotationUseSiteTarget0);
    }

    public abstract String renderFlexibleType(String arg1, String arg2, KotlinBuiltIns arg3);

    public abstract String renderFqName(FqNameUnsafe arg1);

    public abstract String renderName(Name arg1, boolean arg2);

    public abstract String renderType(KotlinType arg1);

    public abstract String renderTypeProjection(TypeProjection arg1);

    public final DescriptorRenderer withOptions(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "changeOptions");
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type org.jetbrains.kotlin.renderer.DescriptorRendererImpl");
        DescriptorRendererOptionsImpl descriptorRendererOptionsImpl0 = ((DescriptorRendererImpl)this).getOptions().copy();
        function10.invoke(descriptorRendererOptionsImpl0);
        descriptorRendererOptionsImpl0.lock();
        return new DescriptorRendererImpl(descriptorRendererOptionsImpl0);
    }
}

