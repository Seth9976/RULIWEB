package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;

public final class MemberSignature {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final MemberSignature fromFieldNameAndDesc(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "desc");
            return new MemberSignature(s + '#' + s1, null);
        }

        @JvmStatic
        public final MemberSignature fromJvmMemberSignature(JvmMemberSignature jvmMemberSignature0) {
            Intrinsics.checkNotNullParameter(jvmMemberSignature0, "signature");
            if(jvmMemberSignature0 instanceof Method) {
                return this.fromMethodNameAndDesc(jvmMemberSignature0.getName(), jvmMemberSignature0.getDesc());
            }
            if(!(jvmMemberSignature0 instanceof Field)) {
                throw new NoWhenBranchMatchedException();
            }
            return this.fromFieldNameAndDesc(jvmMemberSignature0.getName(), jvmMemberSignature0.getDesc());
        }

        @JvmStatic
        public final MemberSignature fromMethod(NameResolver nameResolver0, JvmMethodSignature jvmProtoBuf$JvmMethodSignature0) {
            Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
            Intrinsics.checkNotNullParameter(jvmProtoBuf$JvmMethodSignature0, "signature");
            return this.fromMethodNameAndDesc(nameResolver0.getString(jvmProtoBuf$JvmMethodSignature0.getName()), nameResolver0.getString(jvmProtoBuf$JvmMethodSignature0.getDesc()));
        }

        @JvmStatic
        public final MemberSignature fromMethodNameAndDesc(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "desc");
            return new MemberSignature(s + s1, null);
        }

        @JvmStatic
        public final MemberSignature fromMethodSignatureAndParameterIndex(MemberSignature memberSignature0, int v) {
            Intrinsics.checkNotNullParameter(memberSignature0, "signature");
            return new MemberSignature(memberSignature0.getSignature() + '@' + v, null);
        }
    }

    public static final Companion Companion;
    private final String signature;

    static {
        MemberSignature.Companion = new Companion(null);
    }

    private MemberSignature(String s) {
        this.signature = s;
    }

    public MemberSignature(String s, DefaultConstructorMarker defaultConstructorMarker0) {
        this(s);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof MemberSignature ? Intrinsics.areEqual(this.signature, ((MemberSignature)object0).signature) : false;
    }

    public final String getSignature() {
        return this.signature;
    }

    @Override
    public int hashCode() {
        return this.signature.hashCode();
    }

    @Override
    public String toString() {
        return "MemberSignature(signature=" + this.signature + ')';
    }
}

