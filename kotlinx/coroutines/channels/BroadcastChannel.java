package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

@Deprecated(level = DeprecationLevel.WARNING, message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported")
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0014\u0010\u0003\u001A\u00020\u00042\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\'J\u001A\u0010\u0003\u001A\u00020\u00072\u0010\b\u0002\u0010\u0005\u001A\n\u0018\u00010\bj\u0004\u0018\u0001`\tH&J\u000E\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u000BH&¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "Lkotlinx/coroutines/channels/SendChannel;", "cancel", "", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface BroadcastChannel extends SendChannel {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static void cancel$default(BroadcastChannel broadcastChannel0, CancellationException cancellationException0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if((v & 1) != 0) {
                cancellationException0 = null;
            }
            broadcastChannel0.cancel(cancellationException0);
        }

        public static boolean cancel$default(BroadcastChannel broadcastChannel0, Throwable throwable0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if((v & 1) != 0) {
                throwable0 = null;
            }
            return broadcastChannel0.cancel(throwable0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'trySend\' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
        public static boolean offer(BroadcastChannel broadcastChannel0, Object object0) {
            return kotlinx.coroutines.channels.SendChannel.DefaultImpls.offer(broadcastChannel0, object0);
        }
    }

    void cancel(CancellationException arg1);

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility only")
    boolean cancel(Throwable arg1);

    ReceiveChannel openSubscription();
}

