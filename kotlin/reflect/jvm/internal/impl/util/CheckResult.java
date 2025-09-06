package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class CheckResult {
    public static final class IllegalFunctionName extends CheckResult {
        public static final IllegalFunctionName INSTANCE;

        static {
            IllegalFunctionName.INSTANCE = new IllegalFunctionName();
        }

        private IllegalFunctionName() {
            super(false, null);
        }
    }

    public static final class IllegalSignature extends CheckResult {
        private final String error;

        public IllegalSignature(String s) {
            Intrinsics.checkNotNullParameter(s, "error");
            super(false, null);
            this.error = s;
        }
    }

    public static final class SuccessCheck extends CheckResult {
        public static final SuccessCheck INSTANCE;

        static {
            SuccessCheck.INSTANCE = new SuccessCheck();
        }

        private SuccessCheck() {
            super(true, null);
        }
    }

    private final boolean isSuccess;

    private CheckResult(boolean z) {
        this.isSuccess = z;
    }

    public CheckResult(boolean z, DefaultConstructorMarker defaultConstructorMarker0) {
        this(z);
    }

    public final boolean isSuccess() {
        return this.isSuccess;
    }
}

