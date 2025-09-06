package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.5 extends Lambda implements Function1 {
    final String $JFBiFunction;

    PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.5.5(String s) {
        this.$JFBiFunction = s;
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        this.invoke(((FunctionEnhancementBuilder)object0));
        return Unit.INSTANCE;
    }

    public final void invoke(FunctionEnhancementBuilder signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0) {
        Intrinsics.checkNotNullParameter(signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0, "$this$function");
        signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0.parameter(this.$JFBiFunction, new JavaTypeQualifiers[]{PredefinedEnhancementInfoKt.access$getNOT_PLATFORM$p(), PredefinedEnhancementInfoKt.access$getNOT_PLATFORM$p(), PredefinedEnhancementInfoKt.access$getNOT_PLATFORM$p(), PredefinedEnhancementInfoKt.access$getNOT_PLATFORM$p()});
    }
}

