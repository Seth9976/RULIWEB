package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public abstract class ErrorValue extends ConstantValue {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ErrorValue create(String s) {
            Intrinsics.checkNotNullParameter(s, "message");
            return new ErrorValueWithMessage(s);
        }
    }

    public static final class ErrorValueWithMessage extends ErrorValue {
        private final String message;

        public ErrorValueWithMessage(String s) {
            Intrinsics.checkNotNullParameter(s, "message");
            super();
            this.message = s;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
        public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
            return this.getType(moduleDescriptor0);
        }

        public ErrorType getType(ModuleDescriptor moduleDescriptor0) {
            Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
            return ErrorUtils.createErrorType(ErrorTypeKind.ERROR_CONSTANT_VALUE, new String[]{this.message});
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
        public String toString() {
            return this.message;
        }
    }

    public static final Companion Companion;

    static {
        ErrorValue.Companion = new Companion(null);
    }

    public ErrorValue() {
        super(Unit.INSTANCE);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public Object getValue() {
        return this.getValue();
    }

    public Unit getValue() {
        throw new UnsupportedOperationException();
    }
}

