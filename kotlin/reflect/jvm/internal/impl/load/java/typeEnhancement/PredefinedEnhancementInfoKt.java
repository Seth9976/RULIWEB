package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Map;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;

public final class PredefinedEnhancementInfoKt {
    private static final JavaTypeQualifiers NOT_NULLABLE;
    private static final JavaTypeQualifiers NOT_PLATFORM;
    private static final JavaTypeQualifiers NULLABLE;
    private static final Map PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;

    static {
        PredefinedEnhancementInfoKt.NULLABLE = new JavaTypeQualifiers(NullabilityQualifier.NULLABLE, null, false, false, 8, null);
        PredefinedEnhancementInfoKt.NOT_PLATFORM = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, false, false, 8, null);
        PredefinedEnhancementInfoKt.NOT_NULLABLE = new JavaTypeQualifiers(NullabilityQualifier.NOT_NULL, null, true, false, 8, null);
        SignatureEnhancementBuilder signatureEnhancementBuilder0 = new SignatureEnhancementBuilder();
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/Iterator").function("forEachRemaining", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.1.1("java/util/function/Consumer"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/lang/Iterable").function("spliterator", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.2.1(SignatureBuildingComponents.INSTANCE));
        ClassEnhancementBuilder signatureEnhancementBuilder$ClassEnhancementBuilder0 = new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/Collection");
        signatureEnhancementBuilder$ClassEnhancementBuilder0.function("removeIf", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.3.1("java/util/function/Predicate"));
        signatureEnhancementBuilder$ClassEnhancementBuilder0.function("stream", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.3.2("java/util/stream/Stream"));
        signatureEnhancementBuilder$ClassEnhancementBuilder0.function("parallelStream", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.3.3("java/util/stream/Stream"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/List").function("replaceAll", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.4.1("java/util/function/UnaryOperator"));
        ClassEnhancementBuilder signatureEnhancementBuilder$ClassEnhancementBuilder1 = new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/Map");
        signatureEnhancementBuilder$ClassEnhancementBuilder1.function("forEach", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.1("java/util/function/BiConsumer"));
        signatureEnhancementBuilder$ClassEnhancementBuilder1.function("putIfAbsent", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.2("java/lang/Object"));
        signatureEnhancementBuilder$ClassEnhancementBuilder1.function("replace", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.3("java/lang/Object"));
        signatureEnhancementBuilder$ClassEnhancementBuilder1.function("replace", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.4("java/lang/Object"));
        signatureEnhancementBuilder$ClassEnhancementBuilder1.function("replaceAll", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.5("java/util/function/BiFunction"));
        signatureEnhancementBuilder$ClassEnhancementBuilder1.function("compute", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.6("java/lang/Object", "java/util/function/BiFunction"));
        signatureEnhancementBuilder$ClassEnhancementBuilder1.function("computeIfAbsent", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.7("java/lang/Object", "java/util/function/Function"));
        signatureEnhancementBuilder$ClassEnhancementBuilder1.function("computeIfPresent", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.8("java/lang/Object", "java/util/function/BiFunction"));
        signatureEnhancementBuilder$ClassEnhancementBuilder1.function("merge", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.9("java/lang/Object", "java/util/function/BiFunction"));
        ClassEnhancementBuilder signatureEnhancementBuilder$ClassEnhancementBuilder2 = new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/Optional");
        signatureEnhancementBuilder$ClassEnhancementBuilder2.function("empty", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.6.1("java/util/Optional"));
        signatureEnhancementBuilder$ClassEnhancementBuilder2.function("of", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.6.2("java/lang/Object", "java/util/Optional"));
        signatureEnhancementBuilder$ClassEnhancementBuilder2.function("ofNullable", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.6.3("java/lang/Object", "java/util/Optional"));
        signatureEnhancementBuilder$ClassEnhancementBuilder2.function("get", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.6.4("java/lang/Object"));
        signatureEnhancementBuilder$ClassEnhancementBuilder2.function("ifPresent", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.6.5("java/util/function/Consumer"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/lang/ref/Reference").function("get", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.7.1("java/lang/Object"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/function/Predicate").function("test", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.8.1("java/lang/Object"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/function/BiPredicate").function("test", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.9.1("java/lang/Object"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/function/Consumer").function("accept", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.10.1("java/lang/Object"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/function/BiConsumer").function("accept", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.11.1("java/lang/Object"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/function/Function").function("apply", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.12.1("java/lang/Object"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/function/BiFunction").function("apply", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.13.1("java/lang/Object"));
        new ClassEnhancementBuilder(signatureEnhancementBuilder0, "java/util/function/Supplier").function("get", new PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.14.1("java/lang/Object"));
        PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE = signatureEnhancementBuilder0.build();
    }

    public static final JavaTypeQualifiers access$getNOT_NULLABLE$p() {
        return PredefinedEnhancementInfoKt.NOT_NULLABLE;
    }

    public static final JavaTypeQualifiers access$getNULLABLE$p() {
        return PredefinedEnhancementInfoKt.NULLABLE;
    }

    public static final Map getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE() {
        return PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE;
    }
}

