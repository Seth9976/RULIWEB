package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.Set;

public interface DescriptorRendererOptions {
    public static final class DefaultImpls {
        public static boolean getIncludeAnnotationArguments(DescriptorRendererOptions descriptorRendererOptions0) {
            return descriptorRendererOptions0.getAnnotationArgumentsRenderingPolicy().getIncludeAnnotationArguments();
        }

        public static boolean getIncludeEmptyAnnotationArguments(DescriptorRendererOptions descriptorRendererOptions0) {
            return descriptorRendererOptions0.getAnnotationArgumentsRenderingPolicy().getIncludeEmptyAnnotationArguments();
        }
    }

    AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy();

    boolean getDebugMode();

    boolean getEnhancedTypes();

    Set getExcludedTypeAnnotationClasses();

    void setAnnotationArgumentsRenderingPolicy(AnnotationArgumentsRenderingPolicy arg1);

    void setClassifierNamePolicy(ClassifierNamePolicy arg1);

    void setDebugMode(boolean arg1);

    void setExcludedTypeAnnotationClasses(Set arg1);

    void setModifiers(Set arg1);

    void setParameterNameRenderingPolicy(ParameterNameRenderingPolicy arg1);

    void setReceiverAfterName(boolean arg1);

    void setRenderCompanionObjectName(boolean arg1);

    void setStartFromName(boolean arg1);

    void setTextFormat(RenderingFormat arg1);

    void setVerbose(boolean arg1);

    void setWithDefinedIn(boolean arg1);

    void setWithoutSuperTypes(boolean arg1);

    void setWithoutTypeParameters(boolean arg1);
}

