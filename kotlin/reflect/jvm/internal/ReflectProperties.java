package kotlin.reflect.jvm.internal;

import java.lang.ref.SoftReference;
import kotlin.jvm.functions.Function0;

public class ReflectProperties {
    public static class LazySoftVal extends Val implements Function0 {
        private final Function0 initializer;
        private volatile SoftReference value;

        // 去混淆评级： 低(20)
        private static void $$$reportNull$$$0(int v) {
            throw new IllegalArgumentException("Argument for @NotNull parameter \'initializer\' of kotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal.<init> must not be null");
        }

        public LazySoftVal(Object object0, Function0 function00) {
            if(function00 == null) {
                LazySoftVal.$$$reportNull$$$0(0);
            }
            super();
            this.value = null;
            this.initializer = function00;
            if(object0 != null) {
                this.value = new SoftReference(this.escape(object0));
            }
        }

        @Override  // kotlin.reflect.jvm.internal.ReflectProperties$Val, kotlin.jvm.functions.Function0
        public Object invoke() {
            SoftReference softReference0 = this.value;
            if(softReference0 != null) {
                Object object0 = softReference0.get();
                if(object0 != null) {
                    return this.unescape(object0);
                }
            }
            Object object1 = this.initializer.invoke();
            this.value = new SoftReference(this.escape(object1));
            return object1;
        }
    }

    public static class LazyVal extends Val {
        private final Function0 initializer;
        private volatile Object value;

        // 去混淆评级： 低(20)
        private static void $$$reportNull$$$0(int v) {
            throw new IllegalArgumentException("Argument for @NotNull parameter \'initializer\' of kotlin/reflect/jvm/internal/ReflectProperties$LazyVal.<init> must not be null");
        }

        public LazyVal(Function0 function00) {
            if(function00 == null) {
                LazyVal.$$$reportNull$$$0(0);
            }
            super();
            this.value = null;
            this.initializer = function00;
        }

        @Override  // kotlin.reflect.jvm.internal.ReflectProperties$Val
        public Object invoke() {
            Object object0 = this.value;
            if(object0 != null) {
                return this.unescape(object0);
            }
            Object object1 = this.initializer.invoke();
            this.value = this.escape(object1);
            return object1;
        }
    }

    public static abstract class Val {
        private static final Object NULL_VALUE;

        static {
            Val.NULL_VALUE = new Object() {
            };
        }

        protected Object escape(Object object0) {
            return object0 == null ? Val.NULL_VALUE : object0;
        }

        public final Object getValue(Object object0, Object object1) {
            return this.invoke();
        }

        public abstract Object invoke();

        protected Object unescape(Object object0) {
            return object0 == Val.NULL_VALUE ? null : object0;
        }
    }

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = {"initializer", "kotlin/reflect/jvm/internal/ReflectProperties", null};
        arr_object[2] = v == 1 || v == 2 ? "lazySoft" : "lazy";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
    }

    public static LazyVal lazy(Function0 function00) {
        if(function00 == null) {
            ReflectProperties.$$$reportNull$$$0(0);
        }
        return new LazyVal(function00);
    }

    public static LazySoftVal lazySoft(Object object0, Function0 function00) {
        if(function00 == null) {
            ReflectProperties.$$$reportNull$$$0(1);
        }
        return new LazySoftVal(object0, function00);
    }

    public static LazySoftVal lazySoft(Function0 function00) {
        if(function00 == null) {
            ReflectProperties.$$$reportNull$$$0(2);
        }
        return ReflectProperties.lazySoft(null, function00);
    }
}

