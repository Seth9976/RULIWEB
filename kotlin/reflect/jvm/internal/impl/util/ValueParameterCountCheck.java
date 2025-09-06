package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;

public abstract class ValueParameterCountCheck implements Check {
    public static final class AtLeast extends ValueParameterCountCheck {
        private final int n;

        public AtLeast(int v) {
            super("must have at least " + v + " value parameter" + (v <= 1 ? "" : "s"), null);
            this.n = v;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.util.Check
        public boolean check(FunctionDescriptor functionDescriptor0) {
            Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
            return functionDescriptor0.getValueParameters().size() >= this.n;
        }
    }

    public static final class Equals extends ValueParameterCountCheck {
        private final int n;

        public Equals(int v) {
            super("must have exactly " + v + " value parameters", null);
            this.n = v;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.util.Check
        public boolean check(FunctionDescriptor functionDescriptor0) {
            Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
            return functionDescriptor0.getValueParameters().size() == this.n;
        }
    }

    public static final class NoValueParameters extends ValueParameterCountCheck {
        public static final NoValueParameters INSTANCE;

        static {
            NoValueParameters.INSTANCE = new NoValueParameters();
        }

        private NoValueParameters() {
            super("must have no value parameters", null);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.util.Check
        public boolean check(FunctionDescriptor functionDescriptor0) {
            Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
            return functionDescriptor0.getValueParameters().isEmpty();
        }
    }

    public static final class SingleValueParameter extends ValueParameterCountCheck {
        public static final SingleValueParameter INSTANCE;

        static {
            SingleValueParameter.INSTANCE = new SingleValueParameter();
        }

        private SingleValueParameter() {
            super("must have a single value parameter", null);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.util.Check
        public boolean check(FunctionDescriptor functionDescriptor0) {
            Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
            return functionDescriptor0.getValueParameters().size() == 1;
        }
    }

    private final String description;

    private ValueParameterCountCheck(String s) {
        this.description = s;
    }

    public ValueParameterCountCheck(String s, DefaultConstructorMarker defaultConstructorMarker0) {
        this(s);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.Check
    public String getDescription() {
        return this.description;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.Check
    public String invoke(FunctionDescriptor functionDescriptor0) {
        return DefaultImpls.invoke(this, functionDescriptor0);
    }
}

