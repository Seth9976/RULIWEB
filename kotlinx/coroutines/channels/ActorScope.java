package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003R\u0018\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/channels/ActorScope;", "E", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "channel", "Lkotlinx/coroutines/channels/Channel;", "getChannel", "()Lkotlinx/coroutines/channels/Channel;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface ActorScope extends CoroutineScope, ReceiveChannel {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
        public static void cancel(ActorScope actorScope0) {
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.cancel(actorScope0);
        }

        public static SelectClause1 getOnReceiveOrNull(ActorScope actorScope0) {
            return kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.getOnReceiveOrNull(actorScope0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'tryReceive\'. Please note that the provided replacement does not rethrow channel\'s close cause as \'poll\' did, for the precise replacement please refer to the \'poll\' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
        public static Object poll(ActorScope actorScope0) {
            return kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.poll(actorScope0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of \'receiveCatching\'. Please note that the provided replacement does not rethrow channel\'s close cause as \'receiveOrNull\' did, for the detailed replacement please refer to the \'receiveOrNull\' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
        public static Object receiveOrNull(ActorScope actorScope0, Continuation continuation0) {
            return kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.receiveOrNull(actorScope0, continuation0);
        }
    }

    Channel getChannel();
}

