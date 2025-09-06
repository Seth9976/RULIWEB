package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class JvmMemberSignature {
    public static final class Field extends JvmMemberSignature {
        private final String desc;
        private final String name;

        public Field(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "desc");
            super(null);
            this.name = s;
            this.desc = s1;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        public String asString() {
            return this.getName() + ':' + this.getDesc();
        }

        public final String component1() {
            return this.name;
        }

        public final String component2() {
            return this.desc;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof Field)) {
                return false;
            }
            return Intrinsics.areEqual(this.name, ((Field)object0).name) ? Intrinsics.areEqual(this.desc, ((Field)object0).desc) : false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        public String getDesc() {
            return this.desc;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        public String getName() {
            return this.name;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() * 0x1F + this.desc.hashCode();
        }
    }

    public static final class Method extends JvmMemberSignature {
        private final String desc;
        private final String name;

        public Method(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "desc");
            super(null);
            this.name = s;
            this.desc = s1;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        public String asString() {
            return this.getName() + this.getDesc();
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof Method)) {
                return false;
            }
            return Intrinsics.areEqual(this.name, ((Method)object0).name) ? Intrinsics.areEqual(this.desc, ((Method)object0).desc) : false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        public String getDesc() {
            return this.desc;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature
        public String getName() {
            return this.name;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() * 0x1F + this.desc.hashCode();
        }
    }

    private JvmMemberSignature() {
    }

    public JvmMemberSignature(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public abstract String asString();

    public abstract String getDesc();

    public abstract String getName();

    @Override
    public final String toString() {
        return this.asString();
    }
}

