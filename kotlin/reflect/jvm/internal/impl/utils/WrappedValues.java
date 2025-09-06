package kotlin.reflect.jvm.internal.impl.utils;

public class WrappedValues {
    static final class ThrowableWrapper {
        private final Throwable throwable;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 1 ? 2 : 3)];
            arr_object[0] = v == 1 ? "kotlin/reflect/jvm/internal/impl/utils/WrappedValues$ThrowableWrapper" : "throwable";
            arr_object[1] = v == 1 ? "getThrowable" : "kotlin/reflect/jvm/internal/impl/utils/WrappedValues$ThrowableWrapper";
            if(v != 1) {
                arr_object[2] = "<init>";
            }
            String s = String.format((v == 1 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalArgumentException illegalArgumentException0 = v == 1 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalArgumentException0;
        }

        private ThrowableWrapper(Throwable throwable0) {
            if(throwable0 == null) {
                ThrowableWrapper.$$$reportNull$$$0(0);
            }
            super();
            this.throwable = throwable0;
        }

        ThrowableWrapper(Throwable throwable0, kotlin.reflect.jvm.internal.impl.utils.WrappedValues.1 wrappedValues$10) {
            this(throwable0);
        }

        public Throwable getThrowable() {
            Throwable throwable0 = this.throwable;
            if(throwable0 == null) {
                ThrowableWrapper.$$$reportNull$$$0(1);
            }
            return throwable0;
        }

        @Override
        public String toString() {
            return this.throwable.toString();
        }
    }

    public static class WrappedProcessCanceledException extends RuntimeException {
        public WrappedProcessCanceledException(Throwable throwable0) {
            super("Rethrow stored exception", throwable0);
        }
    }

    private static final Object NULL_VALUE;
    public static volatile boolean throwWrappedProcessCanceledException;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 1 || v == 2 ? 2 : 3)];
        switch(v) {
            case 1: 
            case 2: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/utils/WrappedValues";
                break;
            }
            case 3: {
                arr_object[0] = "throwable";
                break;
            }
            default: {
                arr_object[0] = "value";
            }
        }
        arr_object[1] = v == 1 || v == 2 ? "escapeNull" : "kotlin/reflect/jvm/internal/impl/utils/WrappedValues";
        switch(v) {
            case 1: 
            case 2: {
                break;
            }
            case 3: {
                arr_object[2] = "escapeThrowable";
                break;
            }
            default: {
                arr_object[2] = v == 4 ? "unescapeExceptionOrNull" : "unescapeNull";
            }
        }
        String s = String.format((v == 1 || v == 2 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 1 || v == 2 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    static {
        WrappedValues.NULL_VALUE = new Object() {
            @Override
            public String toString() {
                return "NULL_VALUE";
            }
        };
        WrappedValues.throwWrappedProcessCanceledException = false;
    }

    public static Object escapeNull(Object object0) {
        if(object0 == null) {
            Object object1 = WrappedValues.NULL_VALUE;
            if(object1 == null) {
                WrappedValues.$$$reportNull$$$0(1);
            }
            return object1;
        }
        if(object0 == null) {
            WrappedValues.$$$reportNull$$$0(2);
        }
        return object0;
    }

    public static Object escapeThrowable(Throwable throwable0) {
        if(throwable0 == null) {
            WrappedValues.$$$reportNull$$$0(3);
        }
        return new ThrowableWrapper(throwable0, null);
    }

    public static Object unescapeExceptionOrNull(Object object0) {
        if(object0 == null) {
            WrappedValues.$$$reportNull$$$0(4);
        }
        return WrappedValues.unescapeNull(WrappedValues.unescapeThrowable(object0));
    }

    public static Object unescapeNull(Object object0) {
        if(object0 == null) {
            WrappedValues.$$$reportNull$$$0(0);
        }
        return object0 == WrappedValues.NULL_VALUE ? null : object0;
    }

    public static Object unescapeThrowable(Object object0) {
        if(object0 instanceof ThrowableWrapper) {
            Throwable throwable0 = ((ThrowableWrapper)object0).getThrowable();
            if(!WrappedValues.throwWrappedProcessCanceledException || !ExceptionUtilsKt.isProcessCanceledException(throwable0)) {
                throw ExceptionUtilsKt.rethrow(throwable0);
            }
            throw new WrappedProcessCanceledException(throwable0);
        }
        return object0;
    }
}

