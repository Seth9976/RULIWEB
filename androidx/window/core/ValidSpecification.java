package androidx.window.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B%\u0012\u0006\u0010\u0004\u001A\u00028\u0000\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\u0006\u0010\t\u001A\u00020\n¢\u0006\u0002\u0010\u000BJ\r\u0010\u0015\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J/\u0010\u0016\u001A\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0017\u001A\u00020\u00062\u0017\u0010\u0018\u001A\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001A0\u0019¢\u0006\u0002\b\u001BH\u0016R\u0011\u0010\t\u001A\u00020\n¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0013\u0010\u0004\u001A\u00028\u0000¢\u0006\n\n\u0002\u0010\u0012\u001A\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001A\u00020\b¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014¨\u0006\u001C"}, d2 = {"Landroidx/window/core/ValidSpecification;", "T", "", "Landroidx/window/core/SpecificationComputer;", "value", "tag", "", "verificationMode", "Landroidx/window/core/SpecificationComputer$VerificationMode;", "logger", "Landroidx/window/core/Logger;", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/window/core/SpecificationComputer$VerificationMode;Landroidx/window/core/Logger;)V", "getLogger", "()Landroidx/window/core/Logger;", "getTag", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getVerificationMode", "()Landroidx/window/core/SpecificationComputer$VerificationMode;", "compute", "require", "message", "condition", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
final class ValidSpecification extends SpecificationComputer {
    private final Logger logger;
    private final String tag;
    private final Object value;
    private final VerificationMode verificationMode;

    public ValidSpecification(Object object0, String s, VerificationMode specificationComputer$VerificationMode0, Logger logger0) {
        Intrinsics.checkNotNullParameter(object0, "value");
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(specificationComputer$VerificationMode0, "verificationMode");
        Intrinsics.checkNotNullParameter(logger0, "logger");
        super();
        this.value = object0;
        this.tag = s;
        this.verificationMode = specificationComputer$VerificationMode0;
        this.logger = logger0;
    }

    @Override  // androidx.window.core.SpecificationComputer
    public Object compute() {
        return this.value;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final String getTag() {
        return this.tag;
    }

    public final Object getValue() {
        return this.value;
    }

    public final VerificationMode getVerificationMode() {
        return this.verificationMode;
    }

    @Override  // androidx.window.core.SpecificationComputer
    public SpecificationComputer require(String s, Function1 function10) {
        Intrinsics.checkNotNullParameter(s, "message");
        Intrinsics.checkNotNullParameter(function10, "condition");
        return ((Boolean)function10.invoke(this.value)).booleanValue() ? this : new FailedSpecification(this.value, this.tag, s, this.logger, this.verificationMode);
    }
}

