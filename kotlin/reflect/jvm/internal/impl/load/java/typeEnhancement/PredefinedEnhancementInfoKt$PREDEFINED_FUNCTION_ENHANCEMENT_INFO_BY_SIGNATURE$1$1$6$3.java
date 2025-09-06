package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.6.3 extends Lambda implements Function1 {
    final String $JLObject;
    final String $JUOptional;

    PredefinedEnhancementInfoKt.PREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE.1.1.6.3(String s, String s1) {
        this.$JLObject = s;
        this.$JUOptional = s1;
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        this.invoke(((FunctionEnhancementBuilder)object0));
        return Unit.INSTANCE;
    }

    public final void invoke(FunctionEnhancementBuilder signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0) {
        Intrinsics.checkNotNullParameter(signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0, "$this$function");
        signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0.parameter(this.$JLObject, new JavaTypeQualifiers[]{PredefinedEnhancementInfoKt.access$getNULLABLE$p()});
        signatureEnhancementBuilder$ClassEnhancementBuilder$FunctionEnhancementBuilder0.returns(this.$JUOptional, new JavaTypeQualifiers[]{PredefinedEnhancementInfoKt.access$getNOT_PLATFORM$p(), PredefinedEnhancementInfoKt.access$getNOT_NULLABLE$p()});
    }
}

