package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0010\u000B\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001A\u0018\u00106\u001A\u00020\u00032\u0006\u00107\u001A\u00020\u00032\u0006\u00108\u001A\u00020.H\u0002\u001A\u0018\u00109\u001A\u00020\u00032\u0006\u00107\u001A\u00020\u00032\u0006\u0010:\u001A\u00020\u000BH\u0002\u001A*\u0010;\u001A\b\u0012\u0004\u0012\u0002H<0\u001A\"\u0004\b\u0000\u0010<2\u0006\u0010=\u001A\u00020\u00032\f\u0010>\u001A\b\u0012\u0004\u0012\u0002H<0\u001AH\u0002\u001A,\u0010?\u001A \u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H<0\u001A\u0012\n\u0012\b\u0012\u0004\u0012\u0002H<0\u001A0@\"\u0004\b\u0000\u0010<H\u0000\u001A\u0010\u0010A\u001A\u00020\u00032\u0006\u0010B\u001A\u00020\u000BH\u0002\u001AL\u0010C\u001A\u00020.\"\u0004\b\u0000\u0010D*\b\u0012\u0004\u0012\u0002HD0E2\u0006\u0010F\u001A\u0002HD2%\b\u0002\u0010G\u001A\u001F\u0012\u0013\u0012\u00110I\u00A2\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020M\u0018\u00010HH\u0002\u00A2\u0006\u0002\u0010N\"\u0010\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0003X\u0082T\u00A2\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001A\u00020\u0001X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u000E\u0010\b\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\t\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\n\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\f\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\r\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u000E\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u000F\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0010\u001A\u00020\u0003X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0011\u001A\u00020\u0003X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0012\u001A\u00020\u000BX\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0013\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0014\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0015\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0016\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0017\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0018\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u0016\u0010\u0019\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u001B0\u001AX\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u001C\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u001D\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u001E\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u001F\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010 \u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010!\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\"\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010#\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010$\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u0010\u0010%\u001A\u00020\u000B8\u0000X\u0081\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010&\u001A\u00020\u000BX\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\'\u001A\u00020\u0003X\u0082T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010(\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010)\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u0019\u0010*\u001A\u00020\u0003*\u00020\u00038\u00C2\u0002X\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b+\u0010,\"\u0019\u0010-\u001A\u00020.*\u00020\u00038\u00C2\u0002X\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b/\u00100\"\u0019\u00101\u001A\u00020\u000B*\u00020\u00038\u00C2\u0002X\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b2\u00103\"\u0019\u00104\u001A\u00020\u0003*\u00020\u00038\u00C2\u0002X\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b5\u0010,\u00A8\u0006O"}, d2 = {"BUFFERED", "Lkotlinx/coroutines/internal/Symbol;", "BUFFER_END_RENDEZVOUS", "", "BUFFER_END_UNLIMITED", "CHANNEL_CLOSED", "getCHANNEL_CLOSED", "()Lkotlinx/coroutines/internal/Symbol;", "CLOSE_HANDLER_CLOSED", "CLOSE_HANDLER_INVOKED", "CLOSE_STATUS_ACTIVE", "", "CLOSE_STATUS_CANCELLATION_STARTED", "CLOSE_STATUS_CANCELLED", "CLOSE_STATUS_CLOSED", "DONE_RCV", "EB_COMPLETED_COUNTER_MASK", "EB_COMPLETED_PAUSE_EXPAND_BUFFERS_BIT", "EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS", "FAILED", "INTERRUPTED_RCV", "INTERRUPTED_SEND", "IN_BUFFER", "NO_CLOSE_CAUSE", "NO_RECEIVE_RESULT", "NULL_SEGMENT", "Lkotlinx/coroutines/channels/ChannelSegment;", "", "POISONED", "RESULT_BUFFERED", "RESULT_CLOSED", "RESULT_FAILED", "RESULT_RENDEZVOUS", "RESULT_SUSPEND", "RESULT_SUSPEND_NO_WAITER", "RESUMING_BY_EB", "RESUMING_BY_RCV", "SEGMENT_SIZE", "SENDERS_CLOSE_STATUS_SHIFT", "SENDERS_COUNTER_MASK", "SUSPEND", "SUSPEND_NO_WAITER", "ebCompletedCounter", "getEbCompletedCounter", "(J)J", "ebPauseExpandBuffers", "", "getEbPauseExpandBuffers", "(J)Z", "sendersCloseStatus", "getSendersCloseStatus", "(J)I", "sendersCounter", "getSendersCounter", "constructEBCompletedAndPauseFlag", "counter", "pauseEB", "constructSendersAndCloseStatus", "closeStatus", "createSegment", "E", "id", "prev", "createSegmentFunction", "Lkotlin/reflect/KFunction2;", "initialBufferEnd", "capacity", "tryResume0", "T", "Lkotlinx/coroutines/CancellableContinuation;", "value", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "(Lkotlinx/coroutines/CancellableContinuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Z", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class BufferedChannelKt {
    public static final Symbol BUFFERED = null;
    private static final long BUFFER_END_RENDEZVOUS = 0L;
    private static final long BUFFER_END_UNLIMITED = 0x7FFFFFFFFFFFFFFFL;
    private static final Symbol CHANNEL_CLOSED = null;
    private static final Symbol CLOSE_HANDLER_CLOSED = null;
    private static final Symbol CLOSE_HANDLER_INVOKED = null;
    private static final int CLOSE_STATUS_ACTIVE = 0;
    private static final int CLOSE_STATUS_CANCELLATION_STARTED = 1;
    private static final int CLOSE_STATUS_CANCELLED = 3;
    private static final int CLOSE_STATUS_CLOSED = 2;
    private static final Symbol DONE_RCV = null;
    private static final long EB_COMPLETED_COUNTER_MASK = 0x3FFFFFFFFFFFFFFFL;
    private static final long EB_COMPLETED_PAUSE_EXPAND_BUFFERS_BIT = 0x4000000000000000L;
    private static final int EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS = 0;
    private static final Symbol FAILED = null;
    private static final Symbol INTERRUPTED_RCV = null;
    private static final Symbol INTERRUPTED_SEND = null;
    private static final Symbol IN_BUFFER = null;
    private static final Symbol NO_CLOSE_CAUSE = null;
    private static final Symbol NO_RECEIVE_RESULT = null;
    private static final ChannelSegment NULL_SEGMENT = null;
    private static final Symbol POISONED = null;
    private static final int RESULT_BUFFERED = 1;
    private static final int RESULT_CLOSED = 4;
    private static final int RESULT_FAILED = 5;
    private static final int RESULT_RENDEZVOUS = 0;
    private static final int RESULT_SUSPEND = 2;
    private static final int RESULT_SUSPEND_NO_WAITER = 3;
    private static final Symbol RESUMING_BY_EB = null;
    private static final Symbol RESUMING_BY_RCV = null;
    public static final int SEGMENT_SIZE = 0;
    private static final int SENDERS_CLOSE_STATUS_SHIFT = 60;
    private static final long SENDERS_COUNTER_MASK = 0xFFFFFFFFFFFFFFFL;
    private static final Symbol SUSPEND;
    private static final Symbol SUSPEND_NO_WAITER;

    static {
        BufferedChannelKt.NULL_SEGMENT = new ChannelSegment(-1L, null, null, 0);
        BufferedChannelKt.SEGMENT_SIZE = SystemPropsKt.systemProp$default("kotlinx.coroutines.bufferedChannel.segmentSize", 0x20, 0, 0, 12, null);
        BufferedChannelKt.EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS = SystemPropsKt.systemProp$default("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 0, 0, 12, null);
        BufferedChannelKt.BUFFERED = new Symbol("BUFFERED");
        BufferedChannelKt.IN_BUFFER = new Symbol("SHOULD_BUFFER");
        BufferedChannelKt.RESUMING_BY_RCV = new Symbol("S_RESUMING_BY_RCV");
        BufferedChannelKt.RESUMING_BY_EB = new Symbol("RESUMING_BY_EB");
        BufferedChannelKt.POISONED = new Symbol("POISONED");
        BufferedChannelKt.DONE_RCV = new Symbol("DONE_RCV");
        BufferedChannelKt.INTERRUPTED_SEND = new Symbol("INTERRUPTED_SEND");
        BufferedChannelKt.INTERRUPTED_RCV = new Symbol("INTERRUPTED_RCV");
        BufferedChannelKt.CHANNEL_CLOSED = new Symbol("CHANNEL_CLOSED");
        BufferedChannelKt.SUSPEND = new Symbol("SUSPEND");
        BufferedChannelKt.SUSPEND_NO_WAITER = new Symbol("SUSPEND_NO_WAITER");
        BufferedChannelKt.FAILED = new Symbol("FAILED");
        BufferedChannelKt.NO_RECEIVE_RESULT = new Symbol("NO_RECEIVE_RESULT");
        BufferedChannelKt.CLOSE_HANDLER_CLOSED = new Symbol("CLOSE_HANDLER_CLOSED");
        BufferedChannelKt.CLOSE_HANDLER_INVOKED = new Symbol("CLOSE_HANDLER_INVOKED");
        BufferedChannelKt.NO_CLOSE_CAUSE = new Symbol("NO_CLOSE_CAUSE");
    }

    public static final long access$constructSendersAndCloseStatus(long v, int v1) {
        return (((long)v1) << 60) + v;
    }

    public static final int access$getEXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS$p() [...] // 潜在的解密器

    public static final long access$initialBufferEnd(int v) {
        return BufferedChannelKt.initialBufferEnd(v);
    }

    // 去混淆评级： 低(20)
    private static final long constructEBCompletedAndPauseFlag(long v, boolean z) {
        return z ? v + 0x4000000000000000L : v;
    }

    private static final long constructSendersAndCloseStatus(long v, int v1) [...] // Inlined contents

    private static final ChannelSegment createSegment(long v, ChannelSegment channelSegment0) {
        return new ChannelSegment(v, channelSegment0, channelSegment0.getChannel(), 0);
    }

    public static final KFunction createSegmentFunction() {
        return kotlinx.coroutines.channels.BufferedChannelKt.createSegmentFunction.1.INSTANCE;

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.channels.BufferedChannelKt.createSegmentFunction.1 extends FunctionReferenceImpl implements Function2 {
            public static final kotlinx.coroutines.channels.BufferedChannelKt.createSegmentFunction.1 INSTANCE;

            static {
                kotlinx.coroutines.channels.BufferedChannelKt.createSegmentFunction.1.INSTANCE = new kotlinx.coroutines.channels.BufferedChannelKt.createSegmentFunction.1();
            }

            kotlinx.coroutines.channels.BufferedChannelKt.createSegmentFunction.1() {
                super(2, BufferedChannelKt.class, "createSegment", "createSegment(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", 1);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((Number)object0).longValue(), ((ChannelSegment)object1));
            }

            public final ChannelSegment invoke(long v, ChannelSegment channelSegment0) {
                return BufferedChannelKt.createSegment(v, channelSegment0);
            }
        }

    }

    public static final Symbol getCHANNEL_CLOSED() {
        return BufferedChannelKt.CHANNEL_CLOSED;
    }

    private static final long getEbCompletedCounter(long v) {
        return v & 0x3FFFFFFFFFFFFFFFL;
    }

    private static final boolean getEbPauseExpandBuffers(long v) {
        return (v & 0x4000000000000000L) != 0L;
    }

    private static final int getSendersCloseStatus(long v) {
        return (int)(v >> 60);
    }

    private static final long getSendersCounter(long v) {
        return v & 0xFFFFFFFFFFFFFFFL;
    }

    private static final long initialBufferEnd(int v) {
        switch(v) {
            case 0: {
                return 0L;
            }
            case 0x7FFFFFFF: {
                return 0x7FFFFFFFFFFFFFFFL;
            }
            default: {
                return (long)v;
            }
        }
    }

    private static final boolean tryResume0(CancellableContinuation cancellableContinuation0, Object object0, Function1 function10) {
        Object object1 = cancellableContinuation0.tryResume(object0, null, function10);
        if(object1 != null) {
            cancellableContinuation0.completeResume(object1);
            return true;
        }
        return false;
    }

    static boolean tryResume0$default(CancellableContinuation cancellableContinuation0, Object object0, Function1 function10, int v, Object object1) {
        if((v & 2) != 0) {
            function10 = null;
        }
        return BufferedChannelKt.tryResume0(cancellableContinuation0, object0, function10);
    }
}

