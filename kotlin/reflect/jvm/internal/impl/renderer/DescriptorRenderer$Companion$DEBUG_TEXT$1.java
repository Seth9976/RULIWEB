package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class DescriptorRenderer.Companion.DEBUG_TEXT.1 extends Lambda implements Function1 {
    public static final DescriptorRenderer.Companion.DEBUG_TEXT.1 INSTANCE;

    static {
        DescriptorRenderer.Companion.DEBUG_TEXT.1.INSTANCE = new DescriptorRenderer.Companion.DEBUG_TEXT.1();
    }

    DescriptorRenderer.Companion.DEBUG_TEXT.1() {
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        this.invoke(((DescriptorRendererOptions)object0));
        return Unit.INSTANCE;
    }

    public final void invoke(DescriptorRendererOptions descriptorRendererOptions0) {
        Intrinsics.checkNotNullParameter(descriptorRendererOptions0, "$this$withOptions");
        descriptorRendererOptions0.setDebugMode(true);
        descriptorRendererOptions0.setClassifierNamePolicy(FULLY_QUALIFIED.INSTANCE);
        descriptorRendererOptions0.setModifiers(DescriptorRendererModifier.ALL);
    }
}

