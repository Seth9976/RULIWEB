package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;

public abstract class MemberKindCheck implements Check {
    public static final class Member extends MemberKindCheck {
        public static final Member INSTANCE;

        static {
            Member.INSTANCE = new Member();
        }

        private Member() {
            super("must be a member function", null);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.util.Check
        public boolean check(FunctionDescriptor functionDescriptor0) {
            Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
            return functionDescriptor0.getDispatchReceiverParameter() != null;
        }
    }

    public static final class MemberOrExtension extends MemberKindCheck {
        public static final MemberOrExtension INSTANCE;

        static {
            MemberOrExtension.INSTANCE = new MemberOrExtension();
        }

        private MemberOrExtension() {
            super("must be a member or an extension function", null);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.util.Check
        public boolean check(FunctionDescriptor functionDescriptor0) {
            Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
            return functionDescriptor0.getDispatchReceiverParameter() != null || functionDescriptor0.getExtensionReceiverParameter() != null;
        }
    }

    private final String description;

    private MemberKindCheck(String s) {
        this.description = s;
    }

    public MemberKindCheck(String s, DefaultConstructorMarker defaultConstructorMarker0) {
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

