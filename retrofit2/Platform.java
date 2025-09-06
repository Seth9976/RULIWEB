package retrofit2;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

class Platform {
    static final class Android extends Platform {
        static final class MainThreadExecutor implements Executor {
            private final Handler handler;

            MainThreadExecutor() {
                this.handler = new Handler(Looper.getMainLooper());
            }

            @Override
            public void execute(Runnable runnable0) {
                this.handler.post(runnable0);
            }
        }

        Android() {
            super(Build.VERSION.SDK_INT >= 24);
        }

        @Override  // retrofit2.Platform
        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        @Override  // retrofit2.Platform
        @Nullable
        Object invokeDefaultMethod(Method method0, Class class0, Object object0, Object[] arr_object) throws Throwable {
            if(Build.VERSION.SDK_INT < 26) {
                throw new UnsupportedOperationException("Calling default methods on API 24 and 25 is not supported");
            }
            return super.invokeDefaultMethod(method0, class0, object0, arr_object);
        }
    }

    private static final Platform PLATFORM;
    private final boolean hasJava8Types;
    @Nullable
    private final Constructor lookupConstructor;

    static {
        Platform.PLATFORM = Platform.findPlatform();
    }

    Platform(boolean z) {
        this.hasJava8Types = z;
        Constructor constructor0 = null;
        if(z) {
            try {
                constructor0 = Platform..ExternalSyntheticApiModelOutline0.m$2().getDeclaredConstructor(Class.class, Integer.TYPE);
                constructor0.setAccessible(true);
            }
            catch(NoClassDefFoundError | NoSuchMethodException unused_ex) {
            }
        }
        this.lookupConstructor = constructor0;
    }

    List defaultCallAdapterFactories(@Nullable Executor executor0) {
        DefaultCallAdapterFactory defaultCallAdapterFactory0 = new DefaultCallAdapterFactory(executor0);
        return this.hasJava8Types ? Arrays.asList(new Factory[]{CompletableFutureCallAdapterFactory.INSTANCE, defaultCallAdapterFactory0}) : Collections.singletonList(defaultCallAdapterFactory0);
    }

    // 去混淆评级： 低(20)
    int defaultCallAdapterFactoriesSize() {
        return this.hasJava8Types ? 2 : 1;
    }

    @Nullable
    Executor defaultCallbackExecutor() {
        return null;
    }

    // 去混淆评级： 低(20)
    List defaultConverterFactories() {
        return this.hasJava8Types ? Collections.singletonList(OptionalConverterFactory.INSTANCE) : Collections.EMPTY_LIST;
    }

    int defaultConverterFactoriesSize() {
        return this.hasJava8Types;
    }

    // 去混淆评级： 低(40)
    private static Platform findPlatform() {
        return new Android();
    }

    static Platform get() {
        return Platform.PLATFORM;
    }

    @Nullable
    Object invokeDefaultMethod(Method method0, Class class0, Object object0, Object[] arr_object) throws Throwable {
        return this.lookupConstructor == null ? MethodHandles.lookup().unreflectSpecial(method0, class0).bindTo(object0).invokeWithArguments(arr_object) : Platform..ExternalSyntheticApiModelOutline0.m(this.lookupConstructor.newInstance(class0, -1)).unreflectSpecial(method0, class0).bindTo(object0).invokeWithArguments(arr_object);
    }

    // 去混淆评级： 低(20)
    boolean isDefaultMethod(Method method0) {
        return this.hasJava8Types && Platform..ExternalSyntheticApiModelOutline0.m(method0);
    }
}

