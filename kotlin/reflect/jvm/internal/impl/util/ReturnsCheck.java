package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public abstract class ReturnsCheck implements Check {
    public static final class ReturnsBoolean extends ReturnsCheck {
        public static final ReturnsBoolean INSTANCE;

        static {
            ReturnsBoolean.INSTANCE = new ReturnsBoolean();
        }

        private ReturnsBoolean() {
            super("Boolean", kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsBoolean.1.INSTANCE, null);

            final class kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsBoolean.1 extends Lambda implements Function1 {
                public static final kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsBoolean.1 INSTANCE;

                static {
                    kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsBoolean.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsBoolean.1();
                }

                kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsBoolean.1() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((KotlinBuiltIns)object0));
                }

                public final KotlinType invoke(KotlinBuiltIns kotlinBuiltIns0) {
                    Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "$this$null");
                    SimpleType simpleType0 = kotlinBuiltIns0.getBooleanType();
                    Intrinsics.checkNotNullExpressionValue(simpleType0, "booleanType");
                    return simpleType0;
                }
            }

        }
    }

    public static final class ReturnsInt extends ReturnsCheck {
        public static final ReturnsInt INSTANCE;

        static {
            ReturnsInt.INSTANCE = new ReturnsInt();
        }

        private ReturnsInt() {
            super("Int", kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsInt.1.INSTANCE, null);

            final class kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsInt.1 extends Lambda implements Function1 {
                public static final kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsInt.1 INSTANCE;

                static {
                    kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsInt.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsInt.1();
                }

                kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsInt.1() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((KotlinBuiltIns)object0));
                }

                public final KotlinType invoke(KotlinBuiltIns kotlinBuiltIns0) {
                    Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "$this$null");
                    SimpleType simpleType0 = kotlinBuiltIns0.getIntType();
                    Intrinsics.checkNotNullExpressionValue(simpleType0, "intType");
                    return simpleType0;
                }
            }

        }
    }

    public static final class ReturnsUnit extends ReturnsCheck {
        public static final ReturnsUnit INSTANCE;

        static {
            ReturnsUnit.INSTANCE = new ReturnsUnit();
        }

        private ReturnsUnit() {
            super("Unit", kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsUnit.1.INSTANCE, null);

            final class kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsUnit.1 extends Lambda implements Function1 {
                public static final kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsUnit.1 INSTANCE;

                static {
                    kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsUnit.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsUnit.1();
                }

                kotlin.reflect.jvm.internal.impl.util.ReturnsCheck.ReturnsUnit.1() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((KotlinBuiltIns)object0));
                }

                public final KotlinType invoke(KotlinBuiltIns kotlinBuiltIns0) {
                    Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "$this$null");
                    SimpleType simpleType0 = kotlinBuiltIns0.getUnitType();
                    Intrinsics.checkNotNullExpressionValue(simpleType0, "unitType");
                    return simpleType0;
                }
            }

        }
    }

    private final String description;
    private final String name;
    private final Function1 type;

    private ReturnsCheck(String s, Function1 function10) {
        this.name = s;
        this.type = function10;
        this.description = "must return " + s;
    }

    public ReturnsCheck(String s, Function1 function10, DefaultConstructorMarker defaultConstructorMarker0) {
        this(s, function10);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.Check
    public boolean check(FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
        KotlinType kotlinType0 = functionDescriptor0.getReturnType();
        KotlinBuiltIns kotlinBuiltIns0 = DescriptorUtilsKt.getBuiltIns(functionDescriptor0);
        return Intrinsics.areEqual(kotlinType0, this.type.invoke(kotlinBuiltIns0));
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

