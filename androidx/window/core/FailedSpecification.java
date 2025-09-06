package androidx.window.core;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B-\u0012\u0006\u0010\u0004\u001A\u00028\u0000\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\u0006\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\u000F\u0010\u001B\u001A\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0017J/\u0010\u001C\u001A\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0007\u001A\u00020\u00062\u0017\u0010\u001D\u001A\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001F0\u001E¢\u0006\u0002\b H\u0016R\u0011\u0010\r\u001A\u00020\u000E¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\b\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0014R\u0013\u0010\u0004\u001A\u00028\u0000¢\u0006\n\n\u0002\u0010\u0018\u001A\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001A\u00020\u000B¢\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u001A¨\u0006!"}, d2 = {"Landroidx/window/core/FailedSpecification;", "T", "", "Landroidx/window/core/SpecificationComputer;", "value", "tag", "", "message", "logger", "Landroidx/window/core/Logger;", "verificationMode", "Landroidx/window/core/SpecificationComputer$VerificationMode;", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Landroidx/window/core/Logger;Landroidx/window/core/SpecificationComputer$VerificationMode;)V", "exception", "Landroidx/window/core/WindowStrictModeException;", "getException", "()Landroidx/window/core/WindowStrictModeException;", "getLogger", "()Landroidx/window/core/Logger;", "getMessage", "()Ljava/lang/String;", "getTag", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getVerificationMode", "()Landroidx/window/core/SpecificationComputer$VerificationMode;", "compute", "require", "condition", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
final class FailedSpecification extends SpecificationComputer {
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[VerificationMode.values().length];
            arr_v[VerificationMode.STRICT.ordinal()] = 1;
            arr_v[VerificationMode.LOG.ordinal()] = 2;
            arr_v[VerificationMode.QUIET.ordinal()] = 3;
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private final WindowStrictModeException exception;
    private final Logger logger;
    private final String message;
    private final String tag;
    private final Object value;
    private final VerificationMode verificationMode;

    public FailedSpecification(Object object0, String s, String s1, Logger logger0, VerificationMode specificationComputer$VerificationMode0) {
        Intrinsics.checkNotNullParameter(object0, "value");
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(s1, "message");
        Intrinsics.checkNotNullParameter(logger0, "logger");
        Intrinsics.checkNotNullParameter(specificationComputer$VerificationMode0, "verificationMode");
        super();
        this.value = object0;
        this.tag = s;
        this.message = s1;
        this.logger = logger0;
        this.verificationMode = specificationComputer$VerificationMode0;
        WindowStrictModeException windowStrictModeException0 = new WindowStrictModeException(this.createMessage(object0, s1));
        StackTraceElement[] arr_stackTraceElement = windowStrictModeException0.getStackTrace();
        Intrinsics.checkNotNullExpressionValue(arr_stackTraceElement, "stackTrace");
        Object[] arr_object = ArraysKt.drop(arr_stackTraceElement, 2).toArray(new StackTraceElement[0]);
        if(arr_object == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        windowStrictModeException0.setStackTrace(((StackTraceElement[])arr_object));
        this.exception = windowStrictModeException0;
    }

    @Override  // androidx.window.core.SpecificationComputer
    public Object compute() {
        switch(WhenMappings.$EnumSwitchMapping$0[this.verificationMode.ordinal()]) {
            case 1: {
                throw this.exception;
            }
            case 2: {
                String s = this.createMessage(this.value, this.message);
                this.logger.debug(this.tag, s);
                return null;
            }
            case 3: {
                return null;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    public final WindowStrictModeException getException() {
        return this.exception;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final String getMessage() {
        return this.message;
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
        return this;
    }
}

