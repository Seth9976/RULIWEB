package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

public abstract class JvmType {
    public static final class Array extends JvmType {
        private final JvmType elementType;

        public Array(JvmType jvmType0) {
            Intrinsics.checkNotNullParameter(jvmType0, "elementType");
            super(null);
            this.elementType = jvmType0;
        }

        public final JvmType getElementType() {
            return this.elementType;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Primitive getBOOLEAN$descriptors_jvm() {
            return JvmType.BOOLEAN;
        }

        public final Primitive getBYTE$descriptors_jvm() {
            return JvmType.BYTE;
        }

        public final Primitive getCHAR$descriptors_jvm() {
            return JvmType.CHAR;
        }

        public final Primitive getDOUBLE$descriptors_jvm() {
            return JvmType.DOUBLE;
        }

        public final Primitive getFLOAT$descriptors_jvm() {
            return JvmType.FLOAT;
        }

        public final Primitive getINT$descriptors_jvm() {
            return JvmType.INT;
        }

        public final Primitive getLONG$descriptors_jvm() {
            return JvmType.LONG;
        }

        public final Primitive getSHORT$descriptors_jvm() {
            return JvmType.SHORT;
        }
    }

    public static final class Object extends JvmType {
        private final String internalName;

        public Object(String s) {
            Intrinsics.checkNotNullParameter(s, "internalName");
            super(null);
            this.internalName = s;
        }

        public final String getInternalName() {
            return this.internalName;
        }
    }

    public static final class Primitive extends JvmType {
        private final JvmPrimitiveType jvmPrimitiveType;

        public Primitive(JvmPrimitiveType jvmPrimitiveType0) {
            super(null);
            this.jvmPrimitiveType = jvmPrimitiveType0;
        }

        public final JvmPrimitiveType getJvmPrimitiveType() {
            return this.jvmPrimitiveType;
        }
    }

    private static final Primitive BOOLEAN;
    private static final Primitive BYTE;
    private static final Primitive CHAR;
    public static final Companion Companion;
    private static final Primitive DOUBLE;
    private static final Primitive FLOAT;
    private static final Primitive INT;
    private static final Primitive LONG;
    private static final Primitive SHORT;

    static {
        JvmType.Companion = new Companion(null);
        JvmType.BOOLEAN = new Primitive(JvmPrimitiveType.BOOLEAN);
        JvmType.CHAR = new Primitive(JvmPrimitiveType.CHAR);
        JvmType.BYTE = new Primitive(JvmPrimitiveType.BYTE);
        JvmType.SHORT = new Primitive(JvmPrimitiveType.SHORT);
        JvmType.INT = new Primitive(JvmPrimitiveType.INT);
        JvmType.FLOAT = new Primitive(JvmPrimitiveType.FLOAT);
        JvmType.LONG = new Primitive(JvmPrimitiveType.LONG);
        JvmType.DOUBLE = new Primitive(JvmPrimitiveType.DOUBLE);
    }

    private JvmType() {
    }

    public JvmType(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    @Override
    public String toString() {
        return JvmTypeFactoryImpl.INSTANCE.toString(this);
    }
}

