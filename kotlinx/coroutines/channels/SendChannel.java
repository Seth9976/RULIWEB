package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.selects.SelectClause2;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J\u0014\u0010\f\u001A\u00020\u00042\n\b\u0002\u0010\r\u001A\u0004\u0018\u00010\u000EH&J-\u0010\u000F\u001A\u00020\u00102#\u0010\u0011\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u000E¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00100\u0012H&J\u0015\u0010\u0015\u001A\u00020\u00042\u0006\u0010\u0016\u001A\u00028\u0000H\u0017¢\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001A\u00020\u00102\u0006\u0010\u0016\u001A\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J&\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u00100\u001B2\u0006\u0010\u0016\u001A\u00028\u0000H&ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b\u001C\u0010\u001DR\u001A\u0010\u0003\u001A\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001A\u0004\b\u0003\u0010\u0007R$\u0010\b\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00000\tX¦\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000B\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001E0\u0001¨\u0006\u001E"}, d2 = {"Lkotlinx/coroutines/channels/SendChannel;", "E", "", "isClosedForSend", "", "isClosedForSend$annotations", "()V", "()Z", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "close", "cause", "", "invokeOnClose", "", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "offer", "element", "(Ljava/lang/Object;)Z", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface SendChannel {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static boolean close$default(SendChannel sendChannel0, Throwable throwable0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: close");
            }
            if((v & 1) != 0) {
                throwable0 = null;
            }
            return sendChannel0.close(throwable0);
        }

        public static void isClosedForSend$annotations() {
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'trySend\' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
        public static boolean offer(SendChannel sendChannel0, Object object0) {
            Object object1 = sendChannel0.trySend-JP2dKIU(object0);
            if(ChannelResult.isSuccess-impl(object1)) {
                return true;
            }
            Throwable throwable0 = ChannelResult.exceptionOrNull-impl(object1);
            if(throwable0 != null) {
                throw StackTraceRecoveryKt.recoverStackTrace(throwable0);
            }
            return false;
        }
    }

    boolean close(Throwable arg1);

    SelectClause2 getOnSend();

    void invokeOnClose(Function1 arg1);

    boolean isClosedForSend();

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'trySend\' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    boolean offer(Object arg1);

    Object send(Object arg1, Continuation arg2);

    Object trySend-JP2dKIU(Object arg1);
}

