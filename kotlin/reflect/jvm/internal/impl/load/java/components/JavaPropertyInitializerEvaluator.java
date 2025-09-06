package kotlin.reflect.jvm.internal.impl.load.java.components;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;

public interface JavaPropertyInitializerEvaluator {
    public static final class DoNothing implements JavaPropertyInitializerEvaluator {
        public static final DoNothing INSTANCE;

        static {
            DoNothing.INSTANCE = new DoNothing();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator
        public ConstantValue getInitializerConstant(JavaField javaField0, PropertyDescriptor propertyDescriptor0) {
            Intrinsics.checkNotNullParameter(javaField0, "field");
            Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
            return null;
        }
    }

    ConstantValue getInitializerConstant(JavaField arg1, PropertyDescriptor arg2);
}

