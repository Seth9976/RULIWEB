package kotlinx.coroutines;

import java.io.Closeable;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000 \n2\u00020\u00012\u00020\u0002:\u0001\nB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001A\u00020\tH&R\u0012\u0010\u0004\u001A\u00020\u0005X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Ljava/io/Closeable;", "()V", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "close", "", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ExecutorCoroutineDispatcher extends CoroutineDispatcher implements Closeable, AutoCloseable {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0003\u0018\u00002\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/ExecutorCoroutineDispatcher$Key;", "Lkotlin/coroutines/AbstractCoroutineContextKey;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Key extends AbstractCoroutineContextKey {
        private Key() {
            super(CoroutineDispatcher.Key, kotlinx.coroutines.ExecutorCoroutineDispatcher.Key.1.INSTANCE);

            @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "it", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
            final class kotlinx.coroutines.ExecutorCoroutineDispatcher.Key.1 extends Lambda implements Function1 {
                public static final kotlinx.coroutines.ExecutorCoroutineDispatcher.Key.1 INSTANCE;

                static {
                    kotlinx.coroutines.ExecutorCoroutineDispatcher.Key.1.INSTANCE = new kotlinx.coroutines.ExecutorCoroutineDispatcher.Key.1();
                }

                kotlinx.coroutines.ExecutorCoroutineDispatcher.Key.1() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Element)object0));
                }

                // 去混淆评级： 低(20)
                public final ExecutorCoroutineDispatcher invoke(Element coroutineContext$Element0) {
                    return coroutineContext$Element0 instanceof ExecutorCoroutineDispatcher ? ((ExecutorCoroutineDispatcher)coroutineContext$Element0) : null;
                }
            }

        }

        public Key(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Key Key;

    static {
        ExecutorCoroutineDispatcher.Key = new Key(null);
    }

    @Override
    public abstract void close();

    public abstract Executor getExecutor();
}

