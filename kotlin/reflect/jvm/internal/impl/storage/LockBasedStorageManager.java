package kotlin.reflect.jvm.internal.impl.storage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import jeb.synthetic.FIN;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.utils.ExceptionUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.WrappedValues;
import kotlin.text.StringsKt;

public class LockBasedStorageManager implements StorageManager {
    static class CacheWithNotNullValuesBasedOnMemoizedFunction extends CacheWithNullableValuesBasedOnMemoizedFunction implements CacheWithNotNullValues {
        static final boolean $assertionsDisabled;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 3 ? 2 : 3)];
            switch(v) {
                case 1: {
                    arr_object[0] = "map";
                    break;
                }
                case 2: {
                    arr_object[0] = "computation";
                    break;
                }
                case 3: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction";
                    break;
                }
                default: {
                    arr_object[0] = "storageManager";
                }
            }
            arr_object[1] = v == 3 ? "computeIfAbsent" : "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction";
            if(v == 2) {
                arr_object[2] = "computeIfAbsent";
            }
            else if(v != 3) {
                arr_object[2] = "<init>";
            }
            String s = String.format((v == 3 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalArgumentException illegalArgumentException0 = v == 3 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalArgumentException0;
        }

        static {
        }

        private CacheWithNotNullValuesBasedOnMemoizedFunction(LockBasedStorageManager lockBasedStorageManager0, ConcurrentMap concurrentMap0) {
            if(lockBasedStorageManager0 == null) {
                CacheWithNotNullValuesBasedOnMemoizedFunction.$$$reportNull$$$0(0);
            }
            if(concurrentMap0 == null) {
                CacheWithNotNullValuesBasedOnMemoizedFunction.$$$reportNull$$$0(1);
            }
            super(lockBasedStorageManager0, concurrentMap0, null);
        }

        CacheWithNotNullValuesBasedOnMemoizedFunction(LockBasedStorageManager lockBasedStorageManager0, ConcurrentMap concurrentMap0, kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.1 lockBasedStorageManager$10) {
            this(lockBasedStorageManager0, concurrentMap0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$CacheWithNullableValuesBasedOnMemoizedFunction, kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues
        public Object computeIfAbsent(Object object0, Function0 function00) {
            if(function00 == null) {
                CacheWithNotNullValuesBasedOnMemoizedFunction.$$$reportNull$$$0(2);
            }
            Object object1 = super.computeIfAbsent(object0, function00);
            if(object1 == null) {
                CacheWithNotNullValuesBasedOnMemoizedFunction.$$$reportNull$$$0(3);
            }
            return object1;
        }
    }

    static class CacheWithNullableValuesBasedOnMemoizedFunction extends MapBasedMemoizedFunction implements CacheWithNullableValues {
        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[3];
            switch(v) {
                case 1: {
                    arr_object[0] = "map";
                    break;
                }
                case 2: {
                    arr_object[0] = "computation";
                    break;
                }
                default: {
                    arr_object[0] = "storageManager";
                }
            }
            arr_object[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNullableValuesBasedOnMemoizedFunction";
            arr_object[2] = v == 2 ? "computeIfAbsent" : "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
        }

        private CacheWithNullableValuesBasedOnMemoizedFunction(LockBasedStorageManager lockBasedStorageManager0, ConcurrentMap concurrentMap0) {
            if(lockBasedStorageManager0 == null) {
                CacheWithNullableValuesBasedOnMemoizedFunction.$$$reportNull$$$0(0);
            }
            if(concurrentMap0 == null) {
                CacheWithNullableValuesBasedOnMemoizedFunction.$$$reportNull$$$0(1);
            }
            super(lockBasedStorageManager0, concurrentMap0, new kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.CacheWithNullableValuesBasedOnMemoizedFunction.1());

            class kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.CacheWithNullableValuesBasedOnMemoizedFunction.1 implements Function1 {
                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((KeyWithComputation)object0));
                }

                public Object invoke(KeyWithComputation lockBasedStorageManager$KeyWithComputation0) {
                    return KeyWithComputation.access$400(lockBasedStorageManager$KeyWithComputation0).invoke();
                }
            }

        }

        CacheWithNullableValuesBasedOnMemoizedFunction(LockBasedStorageManager lockBasedStorageManager0, ConcurrentMap concurrentMap0, kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.1 lockBasedStorageManager$10) {
            this(lockBasedStorageManager0, concurrentMap0);
        }

        public Object computeIfAbsent(Object object0, Function0 function00) {
            if(function00 == null) {
                CacheWithNullableValuesBasedOnMemoizedFunction.$$$reportNull$$$0(2);
            }
            return this.invoke(new KeyWithComputation(object0, function00));
        }
    }

    public interface ExceptionHandlingStrategy {
        public static final ExceptionHandlingStrategy THROW;

        static {
            ExceptionHandlingStrategy.THROW = new ExceptionHandlingStrategy() {
                // 去混淆评级： 低(20)
                private static void $$$reportNull$$$0(int v) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter \'throwable\' of kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$ExceptionHandlingStrategy$1.handleException must not be null");
                }

                @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$ExceptionHandlingStrategy
                public RuntimeException handleException(Throwable throwable0) {
                    if(throwable0 == null) {
                        kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.ExceptionHandlingStrategy.1.$$$reportNull$$$0(0);
                    }
                    throw ExceptionUtilsKt.rethrow(throwable0);
                }
            };
        }

        RuntimeException handleException(Throwable arg1);
    }

    static class KeyWithComputation {
        private final Function0 computation;
        private final Object key;

        public KeyWithComputation(Object object0, Function0 function00) {
            this.key = object0;
            this.computation = function00;
        }

        static Function0 access$400(KeyWithComputation lockBasedStorageManager$KeyWithComputation0) {
            return lockBasedStorageManager$KeyWithComputation0.computation;
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean equals(Object object0) {
            return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.key.equals(((KeyWithComputation)object0).key);
        }

        @Override
        public int hashCode() {
            return this.key.hashCode();
        }
    }

    static class LockBasedLazyValue implements NullableLazyValue {
        private final Function0 computable;
        private final LockBasedStorageManager storageManager;
        private volatile Object value;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 2 || v == 3 ? 2 : 3)];
            switch(v) {
                case 1: {
                    arr_object[0] = "computable";
                    break;
                }
                case 2: 
                case 3: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue";
                    break;
                }
                default: {
                    arr_object[0] = "storageManager";
                }
            }
            switch(v) {
                case 2: {
                    arr_object[1] = "recursionDetected";
                    break;
                }
                case 3: {
                    arr_object[1] = "renderDebugInformation";
                    break;
                }
                default: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue";
                }
            }
            if(v != 2 && v != 3) {
                arr_object[2] = "<init>";
            }
            String s = String.format((v == 2 || v == 3 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalStateException illegalStateException0 = v == 2 || v == 3 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalStateException0;
        }

        public LockBasedLazyValue(LockBasedStorageManager lockBasedStorageManager0, Function0 function00) {
            if(lockBasedStorageManager0 == null) {
                LockBasedLazyValue.$$$reportNull$$$0(0);
            }
            if(function00 == null) {
                LockBasedLazyValue.$$$reportNull$$$0(1);
            }
            super();
            this.value = NotValue.NOT_COMPUTED;
            this.storageManager = lockBasedStorageManager0;
            this.computable = function00;
        }

        @Override  // kotlin.jvm.functions.Function0
        public Object invoke() {
            Object object2;
            Object object0 = this.value;
            if(!(object0 instanceof NotValue)) {
                return WrappedValues.unescapeThrowable(object0);
            }
            this.storageManager.lock.lock();
            try {
                Object object1 = this.value;
                if(!(object1 instanceof NotValue)) {
                    object2 = WrappedValues.unescapeThrowable(object1);
                }
                else if(object1 == NotValue.COMPUTING) {
                    this.value = NotValue.RECURSION_WAS_DETECTED;
                    RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult0 = this.recursionDetected(true);
                    if(!lockBasedStorageManager$RecursionDetectedResult0.isFallThrough()) {
                        return lockBasedStorageManager$RecursionDetectedResult0.getValue();
                    }
                    goto label_15;
                }
                else {
                label_15:
                    if(object1 == NotValue.RECURSION_WAS_DETECTED) {
                        RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult1 = this.recursionDetected(false);
                        if(!lockBasedStorageManager$RecursionDetectedResult1.isFallThrough()) {
                            return lockBasedStorageManager$RecursionDetectedResult1.getValue();
                        }
                        goto label_20;
                    }
                    else {
                        try {
                        label_20:
                            this.value = NotValue.COMPUTING;
                            object2 = this.computable.invoke();
                            this.postCompute(object2);
                            this.value = object2;
                        }
                        catch(Throwable throwable0) {
                            if(!ExceptionUtilsKt.isProcessCanceledException(throwable0)) {
                                if(this.value == NotValue.COMPUTING) {
                                    this.value = WrappedValues.escapeThrowable(throwable0);
                                }
                                throw this.storageManager.exceptionHandlingStrategy.handleException(throwable0);
                            }
                            this.value = NotValue.NOT_COMPUTED;
                            throw (RuntimeException)throwable0;
                        }
                    }
                }
                return object2;
            }
            finally {
                this.storageManager.lock.unlock();
            }
        }

        public boolean isComputed() {
            return this.value != NotValue.NOT_COMPUTED && this.value != NotValue.COMPUTING;
        }

        protected void postCompute(Object object0) {
        }

        protected RecursionDetectedResult recursionDetected(boolean z) {
            RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult0 = this.storageManager.recursionDetectedDefault("in a lazy value", null);
            if(lockBasedStorageManager$RecursionDetectedResult0 == null) {
                LockBasedLazyValue.$$$reportNull$$$0(2);
            }
            return lockBasedStorageManager$RecursionDetectedResult0;
        }
    }

    static abstract class LockBasedLazyValueWithPostCompute extends LockBasedLazyValue {
        private volatile SingleThreadValue valuePostCompute;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[3];
            arr_object[0] = v == 1 ? "computable" : "storageManager";
            arr_object[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValueWithPostCompute";
            arr_object[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
        }

        public LockBasedLazyValueWithPostCompute(LockBasedStorageManager lockBasedStorageManager0, Function0 function00) {
            if(lockBasedStorageManager0 == null) {
                LockBasedLazyValueWithPostCompute.$$$reportNull$$$0(0);
            }
            if(function00 == null) {
                LockBasedLazyValueWithPostCompute.$$$reportNull$$$0(1);
            }
            super(lockBasedStorageManager0, function00);
            this.valuePostCompute = null;
        }

        protected abstract void doPostCompute(Object arg1);

        @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValue
        public Object invoke() {
            SingleThreadValue singleThreadValue0 = this.valuePostCompute;
            return singleThreadValue0 == null || !singleThreadValue0.hasValue() ? super.invoke() : singleThreadValue0.getValue();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValue
        protected final void postCompute(Object object0) {
            this.valuePostCompute = new SingleThreadValue(object0);
            try {
                this.doPostCompute(object0);
                this.valuePostCompute = null;
            }
            catch(Throwable throwable0) {
                this.valuePostCompute = null;
                throw throwable0;
            }
        }
    }

    static class LockBasedNotNullLazyValue extends LockBasedLazyValue implements NotNullLazyValue {
        static final boolean $assertionsDisabled;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 2 ? 2 : 3)];
            switch(v) {
                case 1: {
                    arr_object[0] = "computable";
                    break;
                }
                case 2: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValue";
                    break;
                }
                default: {
                    arr_object[0] = "storageManager";
                }
            }
            arr_object[1] = v == 2 ? "invoke" : "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValue";
            if(v != 2) {
                arr_object[2] = "<init>";
            }
            String s = String.format((v == 2 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalArgumentException illegalArgumentException0 = v == 2 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalArgumentException0;
        }

        static {
        }

        public LockBasedNotNullLazyValue(LockBasedStorageManager lockBasedStorageManager0, Function0 function00) {
            if(lockBasedStorageManager0 == null) {
                LockBasedNotNullLazyValue.$$$reportNull$$$0(0);
            }
            if(function00 == null) {
                LockBasedNotNullLazyValue.$$$reportNull$$$0(1);
            }
            super(lockBasedStorageManager0, function00);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValue, kotlin.jvm.functions.Function0
        public Object invoke() {
            Object object0 = super.invoke();
            if(object0 == null) {
                LockBasedNotNullLazyValue.$$$reportNull$$$0(2);
            }
            return object0;
        }
    }

    static abstract class LockBasedNotNullLazyValueWithPostCompute extends LockBasedLazyValueWithPostCompute implements NotNullLazyValue {
        static final boolean $assertionsDisabled;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 2 ? 2 : 3)];
            switch(v) {
                case 1: {
                    arr_object[0] = "computable";
                    break;
                }
                case 2: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute";
                    break;
                }
                default: {
                    arr_object[0] = "storageManager";
                }
            }
            arr_object[1] = v == 2 ? "invoke" : "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute";
            if(v != 2) {
                arr_object[2] = "<init>";
            }
            String s = String.format((v == 2 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalArgumentException illegalArgumentException0 = v == 2 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalArgumentException0;
        }

        static {
        }

        public LockBasedNotNullLazyValueWithPostCompute(LockBasedStorageManager lockBasedStorageManager0, Function0 function00) {
            if(lockBasedStorageManager0 == null) {
                LockBasedNotNullLazyValueWithPostCompute.$$$reportNull$$$0(0);
            }
            if(function00 == null) {
                LockBasedNotNullLazyValueWithPostCompute.$$$reportNull$$$0(1);
            }
            super(lockBasedStorageManager0, function00);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValueWithPostCompute, kotlin.jvm.functions.Function0
        public Object invoke() {
            Object object0 = super.invoke();
            if(object0 == null) {
                LockBasedNotNullLazyValueWithPostCompute.$$$reportNull$$$0(2);
            }
            return object0;
        }
    }

    static class MapBasedMemoizedFunction implements MemoizedFunctionToNullable {
        private final ConcurrentMap cache;
        private final Function1 compute;
        private final LockBasedStorageManager storageManager;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 3 || v == 4 ? 2 : 3)];
            switch(v) {
                case 1: {
                    arr_object[0] = "map";
                    break;
                }
                case 2: {
                    arr_object[0] = "compute";
                    break;
                }
                case 3: 
                case 4: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
                    break;
                }
                default: {
                    arr_object[0] = "storageManager";
                }
            }
            switch(v) {
                case 3: {
                    arr_object[1] = "recursionDetected";
                    break;
                }
                case 4: {
                    arr_object[1] = "raceCondition";
                    break;
                }
                default: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
                }
            }
            if(v != 3 && v != 4) {
                arr_object[2] = "<init>";
            }
            String s = String.format((v == 3 || v == 4 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalStateException illegalStateException0 = v == 3 || v == 4 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalStateException0;
        }

        public MapBasedMemoizedFunction(LockBasedStorageManager lockBasedStorageManager0, ConcurrentMap concurrentMap0, Function1 function10) {
            if(lockBasedStorageManager0 == null) {
                MapBasedMemoizedFunction.$$$reportNull$$$0(0);
            }
            if(concurrentMap0 == null) {
                MapBasedMemoizedFunction.$$$reportNull$$$0(1);
            }
            if(function10 == null) {
                MapBasedMemoizedFunction.$$$reportNull$$$0(2);
            }
            super();
            this.storageManager = lockBasedStorageManager0;
            this.cache = concurrentMap0;
            this.compute = function10;
        }

        protected LockBasedStorageManager getStorageManager() {
            return this.storageManager;
        }

        @Override  // kotlin.jvm.functions.Function1
        public Object invoke(Object object0) {
            Object object2;
            Object object1 = this.cache.get(object0);
            if(object1 != null && object1 != NotValue.COMPUTING) {
                return WrappedValues.unescapeExceptionOrNull(object1);
            }
            this.storageManager.lock.lock();
            int v = FIN.finallyOpen$NT();
            NotValue lockBasedStorageManager$NotValue0 = this.cache.get(object0);
            if(lockBasedStorageManager$NotValue0 == NotValue.COMPUTING) {
                lockBasedStorageManager$NotValue0 = NotValue.RECURSION_WAS_DETECTED;
                RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult0 = this.recursionDetected(object0, true);
                if(!lockBasedStorageManager$RecursionDetectedResult0.isFallThrough()) {
                    object2 = lockBasedStorageManager$RecursionDetectedResult0.getValue();
                    goto label_19;
                }
            }
            if(lockBasedStorageManager$NotValue0 == NotValue.RECURSION_WAS_DETECTED) {
                RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult1 = this.recursionDetected(object0, false);
                if(!lockBasedStorageManager$RecursionDetectedResult1.isFallThrough()) {
                    object2 = lockBasedStorageManager$RecursionDetectedResult1.getValue();
                    goto label_19;
                }
            }
            if(lockBasedStorageManager$NotValue0 != null) {
                object2 = WrappedValues.unescapeExceptionOrNull(lockBasedStorageManager$NotValue0);
            label_19:
                FIN.finallyCodeBegin$NT(v);
                this.storageManager.lock.unlock();
                FIN.finallyCodeEnd$NT(v);
                return object2;
            }
            try {
                AssertionError assertionError0 = null;
                this.cache.put(object0, NotValue.COMPUTING);
                Object object3 = this.compute.invoke(object0);
                Object object4 = WrappedValues.escapeNull(object3);
                Object object5 = this.cache.put(object0, object4);
                if(object5 != NotValue.COMPUTING) {
                    assertionError0 = this.raceCondition(object0, object5);
                    throw assertionError0;
                }
                FIN.finallyExec$NT(v);
                return object3;
            }
            catch(Throwable throwable0) {
                if(!ExceptionUtilsKt.isProcessCanceledException(throwable0)) {
                    if(throwable0 != assertionError0) {
                        Object object6 = WrappedValues.escapeThrowable(throwable0);
                        Object object7 = this.cache.put(object0, object6);
                        if(object7 != NotValue.COMPUTING) {
                            FIN.finallyExec$NT(v);
                            throw this.raceCondition(object0, object7);
                        }
                        FIN.finallyExec$NT(v);
                        throw this.storageManager.exceptionHandlingStrategy.handleException(throwable0);
                    }
                    FIN.finallyExec$NT(v);
                    throw this.storageManager.exceptionHandlingStrategy.handleException(throwable0);
                }
                this.cache.remove(object0);
                FIN.finallyExec$NT(v);
                throw (RuntimeException)throwable0;
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable
        public boolean isComputed(Object object0) {
            Object object1 = this.cache.get(object0);
            return object1 != null && object1 != NotValue.COMPUTING;
        }

        private AssertionError raceCondition(Object object0, Object object1) {
            AssertionError assertionError0 = (AssertionError)LockBasedStorageManager.sanitizeStackTrace(new AssertionError("Race condition detected on input " + object0 + ". Old value is " + object1 + " under " + this.storageManager));
            if(assertionError0 == null) {
                MapBasedMemoizedFunction.$$$reportNull$$$0(4);
            }
            return assertionError0;
        }

        protected RecursionDetectedResult recursionDetected(Object object0, boolean z) {
            RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult0 = this.storageManager.recursionDetectedDefault("", object0);
            if(lockBasedStorageManager$RecursionDetectedResult0 == null) {
                MapBasedMemoizedFunction.$$$reportNull$$$0(3);
            }
            return lockBasedStorageManager$RecursionDetectedResult0;
        }
    }

    static class MapBasedMemoizedFunctionToNotNull extends MapBasedMemoizedFunction implements MemoizedFunctionToNotNull {
        static final boolean $assertionsDisabled;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 3 ? 2 : 3)];
            switch(v) {
                case 1: {
                    arr_object[0] = "map";
                    break;
                }
                case 2: {
                    arr_object[0] = "compute";
                    break;
                }
                case 3: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunctionToNotNull";
                    break;
                }
                default: {
                    arr_object[0] = "storageManager";
                }
            }
            arr_object[1] = v == 3 ? "invoke" : "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunctionToNotNull";
            if(v != 3) {
                arr_object[2] = "<init>";
            }
            String s = String.format((v == 3 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalArgumentException illegalArgumentException0 = v == 3 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalArgumentException0;
        }

        static {
        }

        public MapBasedMemoizedFunctionToNotNull(LockBasedStorageManager lockBasedStorageManager0, ConcurrentMap concurrentMap0, Function1 function10) {
            if(lockBasedStorageManager0 == null) {
                MapBasedMemoizedFunctionToNotNull.$$$reportNull$$$0(0);
            }
            if(concurrentMap0 == null) {
                MapBasedMemoizedFunctionToNotNull.$$$reportNull$$$0(1);
            }
            if(function10 == null) {
                MapBasedMemoizedFunctionToNotNull.$$$reportNull$$$0(2);
            }
            super(lockBasedStorageManager0, concurrentMap0, function10);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$MapBasedMemoizedFunction, kotlin.jvm.functions.Function1
        public Object invoke(Object object0) {
            Object object1 = super.invoke(object0);
            if(object1 == null) {
                MapBasedMemoizedFunctionToNotNull.$$$reportNull$$$0(3);
            }
            return object1;
        }
    }

    static enum NotValue {
        NOT_COMPUTED,
        COMPUTING,
        RECURSION_WAS_DETECTED;

    }

    static class RecursionDetectedResult {
        static final boolean $assertionsDisabled;
        private final boolean fallThrough;
        private final Object value;

        static {
        }

        private RecursionDetectedResult(Object object0, boolean z) {
            this.value = object0;
            this.fallThrough = z;
        }

        public static RecursionDetectedResult fallThrough() {
            return new RecursionDetectedResult(null, true);
        }

        public Object getValue() {
            return this.value;
        }

        public boolean isFallThrough() {
            return this.fallThrough;
        }

        // 去混淆评级： 低(20)
        @Override
        public String toString() {
            return this.isFallThrough() ? "FALL_THROUGH" : String.valueOf(this.value);
        }

        public static RecursionDetectedResult value(Object object0) {
            return new RecursionDetectedResult(object0, false);
        }
    }

    static final boolean $assertionsDisabled;
    public static final StorageManager NO_LOCKS;
    private static final String PACKAGE_NAME;
    private final String debugText;
    private final ExceptionHandlingStrategy exceptionHandlingStrategy;
    protected final SimpleLock lock;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 10 || v == 13 || v == 20 || v == 37 ? 2 : 3)];
        if(v == 1 || v == 3) {
            arr_object[0] = "exceptionHandlingStrategy";
        }
        else {
            switch(v) {
                case 6: {
                    arr_object[0] = "lock";
                    break;
                }
                case 5: 
                case 8: {
                    arr_object[0] = "exceptionHandlingStrategy";
                    break;
                }
                case 9: 
                case 11: 
                case 14: 
                case 16: 
                case 19: 
                case 21: {
                    arr_object[0] = "compute";
                    break;
                }
                case 15: 
                case 18: 
                case 22: {
                    arr_object[0] = "map";
                    break;
                }
                case 12: 
                case 17: 
                case 25: 
                case 27: {
                    arr_object[0] = "onRecursiveCall";
                    break;
                }
                case 29: 
                case 33: {
                    arr_object[0] = "postCompute";
                    break;
                }
                case 23: 
                case 24: 
                case 26: 
                case 28: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 34: {
                    arr_object[0] = "computable";
                    break;
                }
                case 35: {
                    arr_object[0] = "source";
                    break;
                }
                case 36: {
                    arr_object[0] = "throwable";
                    break;
                }
                case 10: 
                case 13: 
                case 20: 
                case 37: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager";
                    break;
                }
                default: {
                    arr_object[0] = "debugText";
                }
            }
        }
        switch(v) {
            case 10: 
            case 13: {
                arr_object[1] = "createMemoizedFunction";
                break;
            }
            case 20: {
                arr_object[1] = "createMemoizedFunctionWithNullableValues";
                break;
            }
            case 37: {
                arr_object[1] = "sanitizeStackTrace";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager";
            }
        }
        switch(v) {
            case 4: 
            case 5: 
            case 6: {
                arr_object[2] = "<init>";
                break;
            }
            case 7: 
            case 8: {
                arr_object[2] = "replaceExceptionHandling";
                break;
            }
            case 9: 
            case 11: 
            case 12: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: {
                arr_object[2] = "createMemoizedFunction";
                break;
            }
            case 19: 
            case 21: 
            case 22: {
                arr_object[2] = "createMemoizedFunctionWithNullableValues";
                break;
            }
            case 23: 
            case 24: 
            case 25: {
                arr_object[2] = "createLazyValue";
                break;
            }
            case 26: 
            case 27: {
                arr_object[2] = "createRecursionTolerantLazyValue";
                break;
            }
            case 28: 
            case 29: {
                arr_object[2] = "createLazyValueWithPostCompute";
                break;
            }
            case 30: {
                arr_object[2] = "createNullableLazyValue";
                break;
            }
            case 0x1F: {
                arr_object[2] = "createRecursionTolerantNullableLazyValue";
                break;
            }
            case 0x20: 
            case 33: {
                arr_object[2] = "createNullableLazyValueWithPostCompute";
                break;
            }
            case 34: {
                arr_object[2] = "compute";
                break;
            }
            case 35: {
                arr_object[2] = "recursionDetectedDefault";
                break;
            }
            case 36: {
                arr_object[2] = "sanitizeStackTrace";
                break;
            }
            case 10: 
            case 13: 
            case 20: 
            case 37: {
                break;
            }
            default: {
                arr_object[2] = "createWithExceptionHandling";
            }
        }
        String s = String.format((v == 10 || v == 13 || v == 20 || v == 37 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 10 || v == 13 || v == 20 || v == 37 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    static {
        LockBasedStorageManager.PACKAGE_NAME = StringsKt.substringBeforeLast(LockBasedStorageManager.class.getCanonicalName(), ".", "");
        LockBasedStorageManager.NO_LOCKS = new LockBasedStorageManager("NO_LOCKS", ExceptionHandlingStrategy.THROW, EmptySimpleLock.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[(v == 1 ? 2 : 3)];
                arr_object[0] = v == 1 ? "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$1" : "source";
                arr_object[1] = v == 1 ? "recursionDetectedDefault" : "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$1";
                if(v != 1) {
                    arr_object[2] = "recursionDetectedDefault";
                }
                String s = String.format((v == 1 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
                IllegalArgumentException illegalArgumentException0 = v == 1 ? new IllegalStateException(s) : new IllegalArgumentException(s);
                throw illegalArgumentException0;
            }

            {
                super(s, lockBasedStorageManager$ExceptionHandlingStrategy0, simpleLock0, null);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager
            protected RecursionDetectedResult recursionDetectedDefault(String s, Object object0) {
                if(s == null) {
                    kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.1.$$$reportNull$$$0(0);
                }
                RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult0 = RecursionDetectedResult.fallThrough();
                if(lockBasedStorageManager$RecursionDetectedResult0 == null) {
                    kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.1.$$$reportNull$$$0(1);
                }
                return lockBasedStorageManager$RecursionDetectedResult0;
            }
        };
    }

    public LockBasedStorageManager(String s) {
        this(s, null, null);
    }

    public LockBasedStorageManager(String s, Runnable runnable0, Function1 function10) {
        DefaultSimpleLock defaultSimpleLock0 = SimpleLock.Companion.simpleLock(runnable0, function10);
        this(s, ExceptionHandlingStrategy.THROW, defaultSimpleLock0);
    }

    private LockBasedStorageManager(String s, ExceptionHandlingStrategy lockBasedStorageManager$ExceptionHandlingStrategy0, SimpleLock simpleLock0) {
        if(s == null) {
            LockBasedStorageManager.$$$reportNull$$$0(4);
        }
        if(lockBasedStorageManager$ExceptionHandlingStrategy0 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(5);
        }
        if(simpleLock0 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(6);
        }
        super();
        this.lock = simpleLock0;
        this.exceptionHandlingStrategy = lockBasedStorageManager$ExceptionHandlingStrategy0;
        this.debugText = s;
    }

    LockBasedStorageManager(String s, ExceptionHandlingStrategy lockBasedStorageManager$ExceptionHandlingStrategy0, SimpleLock simpleLock0, kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.1 lockBasedStorageManager$10) {
        this(s, lockBasedStorageManager$ExceptionHandlingStrategy0, simpleLock0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public Object compute(Function0 function00) {
        Object object0;
        if(function00 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(34);
        }
        this.lock.lock();
        try {
            object0 = function00.invoke();
        }
        catch(Throwable throwable0) {
            try {
                throw this.exceptionHandlingStrategy.handleException(throwable0);
            }
            catch(Throwable throwable1) {
                this.lock.unlock();
                throw throwable1;
            }
        }
        this.lock.unlock();
        return object0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public CacheWithNotNullValues createCacheWithNotNullValues() {
        return new CacheWithNotNullValuesBasedOnMemoizedFunction(this, LockBasedStorageManager.createConcurrentHashMap(), null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public CacheWithNullableValues createCacheWithNullableValues() {
        return new CacheWithNullableValuesBasedOnMemoizedFunction(this, LockBasedStorageManager.createConcurrentHashMap(), null);
    }

    private static ConcurrentMap createConcurrentHashMap() {
        return new ConcurrentHashMap(3, 1.0f, 2);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public NotNullLazyValue createLazyValue(Function0 function00) {
        if(function00 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(23);
        }
        return new LockBasedNotNullLazyValue(this, function00);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public NotNullLazyValue createLazyValueWithPostCompute(Function0 function00, Function1 function10, Function1 function11) {
        if(function00 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(28);
        }
        if(function11 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(29);
        }
        return new LockBasedNotNullLazyValueWithPostCompute(this, function00) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[(v == 2 ? 3 : 2)];
                arr_object[0] = v == 2 ? "value" : "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5";
                arr_object[1] = v == 2 ? "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5" : "recursionDetected";
                if(v == 2) {
                    arr_object[2] = "doPostCompute";
                }
                String s = String.format((v == 2 ? "Argument for @NotNull parameter \'%s\' of %s.%s must not be null" : "@NotNull method %s.%s must not return null"), arr_object);
                IllegalStateException illegalStateException0 = v == 2 ? new IllegalArgumentException(s) : new IllegalStateException(s);
                throw illegalStateException0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValueWithPostCompute
            protected void doPostCompute(Object object0) {
                if(object0 == null) {
                    kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.5.$$$reportNull$$$0(2);
                }
                function11.invoke(object0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValue
            protected RecursionDetectedResult recursionDetected(boolean z) {
                Function1 function10 = function10;
                if(function10 == null) {
                    RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult0 = super.recursionDetected(z);
                    if(lockBasedStorageManager$RecursionDetectedResult0 == null) {
                        kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.5.$$$reportNull$$$0(0);
                    }
                    return lockBasedStorageManager$RecursionDetectedResult0;
                }
                RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult1 = RecursionDetectedResult.value(function10.invoke(Boolean.valueOf(z)));
                if(lockBasedStorageManager$RecursionDetectedResult1 == null) {
                    kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.5.$$$reportNull$$$0(1);
                }
                return lockBasedStorageManager$RecursionDetectedResult1;
            }
        };
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public MemoizedFunctionToNotNull createMemoizedFunction(Function1 function10) {
        if(function10 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(9);
        }
        MemoizedFunctionToNotNull memoizedFunctionToNotNull0 = this.createMemoizedFunction(function10, LockBasedStorageManager.createConcurrentHashMap());
        if(memoizedFunctionToNotNull0 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(10);
        }
        return memoizedFunctionToNotNull0;
    }

    public MemoizedFunctionToNotNull createMemoizedFunction(Function1 function10, ConcurrentMap concurrentMap0) {
        if(function10 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(14);
        }
        if(concurrentMap0 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(15);
        }
        return new MapBasedMemoizedFunctionToNotNull(this, concurrentMap0, function10);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public MemoizedFunctionToNullable createMemoizedFunctionWithNullableValues(Function1 function10) {
        if(function10 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(19);
        }
        MemoizedFunctionToNullable memoizedFunctionToNullable0 = this.createMemoizedFunctionWithNullableValues(function10, LockBasedStorageManager.createConcurrentHashMap());
        if(memoizedFunctionToNullable0 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(20);
        }
        return memoizedFunctionToNullable0;
    }

    public MemoizedFunctionToNullable createMemoizedFunctionWithNullableValues(Function1 function10, ConcurrentMap concurrentMap0) {
        if(function10 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(21);
        }
        if(concurrentMap0 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(22);
        }
        return new MapBasedMemoizedFunction(this, concurrentMap0, function10);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public NullableLazyValue createNullableLazyValue(Function0 function00) {
        if(function00 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(30);
        }
        return new LockBasedLazyValue(this, function00);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.StorageManager
    public NotNullLazyValue createRecursionTolerantLazyValue(Function0 function00, Object object0) {
        if(function00 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(26);
        }
        if(object0 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(27);
        }
        return new LockBasedNotNullLazyValue(this, function00) {
            // 去混淆评级： 低(20)
            private static void $$$reportNull$$$0(int v) {
                throw new IllegalStateException("@NotNull method kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$4.recursionDetected must not return null");
            }

            @Override  // kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager$LockBasedLazyValue
            protected RecursionDetectedResult recursionDetected(boolean z) {
                RecursionDetectedResult lockBasedStorageManager$RecursionDetectedResult0 = RecursionDetectedResult.value(object0);
                if(lockBasedStorageManager$RecursionDetectedResult0 == null) {
                    kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager.4.$$$reportNull$$$0(0);
                }
                return lockBasedStorageManager$RecursionDetectedResult0;
            }
        };
    }

    protected RecursionDetectedResult recursionDetectedDefault(String s, Object object0) {
        if(s == null) {
            LockBasedStorageManager.$$$reportNull$$$0(35);
        }
        throw (AssertionError)LockBasedStorageManager.sanitizeStackTrace(new AssertionError("Recursion detected " + s + (object0 == null ? "" : "on input: " + object0) + " under " + this));
    }

    private static Throwable sanitizeStackTrace(Throwable throwable0) {
        if(throwable0 == null) {
            LockBasedStorageManager.$$$reportNull$$$0(36);
        }
        StackTraceElement[] arr_stackTraceElement = throwable0.getStackTrace();
        int v;
        for(v = 0; true; ++v) {
            if(v >= arr_stackTraceElement.length) {
                v = -1;
                break;
            }
            if(!arr_stackTraceElement[v].getClassName().startsWith(LockBasedStorageManager.PACKAGE_NAME)) {
                break;
            }
        }
        List list0 = Arrays.asList(arr_stackTraceElement).subList(v, arr_stackTraceElement.length);
        throwable0.setStackTrace(((StackTraceElement[])list0.toArray(new StackTraceElement[list0.size()])));
        return throwable0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode()) + " (" + this.debugText + ")";
    }
}

