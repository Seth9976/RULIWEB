package kotlin.reflect.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KParameter.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0002J\u000E\u0010\t\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\nJ\u000E\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\nJ\u000E\u0010\r\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u000FJ\u000E\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0011J\u000E\u0010\u0012\u001A\u00020\u00062\u0006\u0010\u0013\u001A\u00020\u0014J\u001A\u0010\u0015\u001A\u00020\u0016*\u00060\u0017j\u0002`\u00182\b\u0010\u0019\u001A\u0004\u0018\u00010\u001AH\u0002J\u0018\u0010\u001B\u001A\u00020\u0016*\u00060\u0017j\u0002`\u00182\u0006\u0010\u001C\u001A\u00020\bH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001D"}, d2 = {"Lkotlin/reflect/jvm/internal/ReflectionObjectRenderer;", "", "()V", "renderer", "Lkotlin/reflect/jvm/internal/impl/renderer/DescriptorRenderer;", "renderCallable", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "renderFunction", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "renderLambda", "invoke", "renderParameter", "parameter", "Lkotlin/reflect/jvm/internal/KParameterImpl;", "renderProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "renderType", "type", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "appendReceiverType", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "receiver", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "appendReceivers", "callable", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ReflectionObjectRenderer {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Kind.values().length];
            try {
                arr_v[Kind.EXTENSION_RECEIVER.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.INSTANCE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.VALUE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final ReflectionObjectRenderer INSTANCE;
    private static final DescriptorRenderer renderer;

    static {
        ReflectionObjectRenderer.INSTANCE = new ReflectionObjectRenderer();
        ReflectionObjectRenderer.renderer = DescriptorRenderer.FQ_NAMES_IN_TYPES;
    }

    private final void appendReceiverType(StringBuilder stringBuilder0, ReceiverParameterDescriptor receiverParameterDescriptor0) {
        if(receiverParameterDescriptor0 != null) {
            KotlinType kotlinType0 = receiverParameterDescriptor0.getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "receiver.type");
            stringBuilder0.append(this.renderType(kotlinType0));
            stringBuilder0.append(".");
        }
    }

    private final void appendReceivers(StringBuilder stringBuilder0, CallableDescriptor callableDescriptor0) {
        ReceiverParameterDescriptor receiverParameterDescriptor0 = UtilKt.getInstanceReceiverParameter(callableDescriptor0);
        ReceiverParameterDescriptor receiverParameterDescriptor1 = callableDescriptor0.getExtensionReceiverParameter();
        this.appendReceiverType(stringBuilder0, receiverParameterDescriptor0);
        boolean z = receiverParameterDescriptor0 != null && receiverParameterDescriptor1 != null;
        if(z) {
            stringBuilder0.append("(");
        }
        this.appendReceiverType(stringBuilder0, receiverParameterDescriptor1);
        if(z) {
            stringBuilder0.append(")");
        }
    }

    private final String renderCallable(CallableDescriptor callableDescriptor0) {
        if(callableDescriptor0 instanceof PropertyDescriptor) {
            return this.renderProperty(((PropertyDescriptor)callableDescriptor0));
        }
        if(!(callableDescriptor0 instanceof FunctionDescriptor)) {
            throw new IllegalStateException(("Illegal callable: " + callableDescriptor0).toString());
        }
        return this.renderFunction(((FunctionDescriptor)callableDescriptor0));
    }

    public final String renderFunction(FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "descriptor");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("fun ");
        ReflectionObjectRenderer.INSTANCE.appendReceivers(stringBuilder0, functionDescriptor0);
        Name name0 = functionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "descriptor.name");
        stringBuilder0.append(ReflectionObjectRenderer.renderer.renderName(name0, true));
        List list0 = functionDescriptor0.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "descriptor.valueParameters");
        CollectionsKt.joinTo$default(list0, stringBuilder0, ", ", "(", ")", 0, null, kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderFunction.1.1.INSTANCE, 0x30, null);
        stringBuilder0.append(": ");
        KotlinType kotlinType0 = functionDescriptor0.getReturnType();
        Intrinsics.checkNotNull(kotlinType0);
        stringBuilder0.append(ReflectionObjectRenderer.INSTANCE.renderType(kotlinType0));
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u000E\u0010\u0002\u001A\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlin/reflect/jvm/internal/impl/descriptors/ValueParameterDescriptor;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderFunction.1.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderFunction.1.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderFunction.1.1.INSTANCE = new kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderFunction.1.1();
            }

            kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderFunction.1.1() {
                super(1);
            }

            public final CharSequence invoke(ValueParameterDescriptor valueParameterDescriptor0) {
                KotlinType kotlinType0 = valueParameterDescriptor0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "it.type");
                return ReflectionObjectRenderer.INSTANCE.renderType(kotlinType0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ValueParameterDescriptor)object0));
            }
        }

    }

    public final String renderLambda(FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "invoke");
        StringBuilder stringBuilder0 = new StringBuilder();
        ReflectionObjectRenderer.INSTANCE.appendReceivers(stringBuilder0, functionDescriptor0);
        List list0 = functionDescriptor0.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "invoke.valueParameters");
        CollectionsKt.joinTo$default(list0, stringBuilder0, ", ", "(", ")", 0, null, kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderLambda.1.1.INSTANCE, 0x30, null);
        stringBuilder0.append(" -> ");
        KotlinType kotlinType0 = functionDescriptor0.getReturnType();
        Intrinsics.checkNotNull(kotlinType0);
        stringBuilder0.append(ReflectionObjectRenderer.INSTANCE.renderType(kotlinType0));
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u000E\u0010\u0002\u001A\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlin/reflect/jvm/internal/impl/descriptors/ValueParameterDescriptor;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderLambda.1.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderLambda.1.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderLambda.1.1.INSTANCE = new kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderLambda.1.1();
            }

            kotlin.reflect.jvm.internal.ReflectionObjectRenderer.renderLambda.1.1() {
                super(1);
            }

            public final CharSequence invoke(ValueParameterDescriptor valueParameterDescriptor0) {
                KotlinType kotlinType0 = valueParameterDescriptor0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "it.type");
                return ReflectionObjectRenderer.INSTANCE.renderType(kotlinType0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ValueParameterDescriptor)object0));
            }
        }

    }

    public final String renderParameter(KParameterImpl kParameterImpl0) {
        Intrinsics.checkNotNullParameter(kParameterImpl0, "parameter");
        StringBuilder stringBuilder0 = new StringBuilder();
        switch(WhenMappings.$EnumSwitchMapping$0[kParameterImpl0.getKind().ordinal()]) {
            case 1: {
                stringBuilder0.append("extension receiver parameter");
                break;
            }
            case 2: {
                stringBuilder0.append("instance parameter");
                break;
            }
            case 3: {
                stringBuilder0.append("parameter #" + kParameterImpl0.getIndex() + ' ' + kParameterImpl0.getName());
            }
        }
        stringBuilder0.append(" of ");
        CallableDescriptor callableDescriptor0 = kParameterImpl0.getCallable().getDescriptor();
        stringBuilder0.append(ReflectionObjectRenderer.INSTANCE.renderCallable(callableDescriptor0));
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String renderProperty(PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append((propertyDescriptor0.isVar() ? "var " : "val "));
        ReflectionObjectRenderer.INSTANCE.appendReceivers(stringBuilder0, propertyDescriptor0);
        Name name0 = propertyDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "descriptor.name");
        stringBuilder0.append(ReflectionObjectRenderer.renderer.renderName(name0, true));
        stringBuilder0.append(": ");
        KotlinType kotlinType0 = propertyDescriptor0.getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "descriptor.type");
        stringBuilder0.append(ReflectionObjectRenderer.INSTANCE.renderType(kotlinType0));
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String renderType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        return ReflectionObjectRenderer.renderer.renderType(kotlinType0);
    }
}

