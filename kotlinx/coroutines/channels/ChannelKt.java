package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u001A\u001E\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004H\u0007\u001A>\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u00062\u0016\b\u0002\u0010\u0007\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u001AX\u0010\n\u001A\u0002H\u000B\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\f2#\u0010\r\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u000E\u00A2\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u000B0\bH\u0086\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00A2\u0006\u0004\b\u0012\u0010\u0013\u001A^\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u000B0\f\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\f2#\u0010\u0015\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u000E\u00A2\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\bH\u0086\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00A2\u0006\u0004\b\u0016\u0010\u0013\u001A^\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\u000B0\f\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\f2#\u0010\u0015\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u000E\u00A2\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\bH\u0086\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00A2\u0006\u0004\b\u0017\u0010\u0013\u001A\\\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u000B0\f\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\f2!\u0010\u0015\u001A\u001D\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\t0\bH\u0086\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00A2\u0006\u0004\b\u001A\u0010\u0013\u0082\u0002\u000B\n\u0005\b\u00A1\u001E0\u0001\n\u0002\b\u0019\u00A8\u0006\u001B"}, d2 = {"Channel", "Lkotlinx/coroutines/channels/Channel;", "E", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "onUndeliveredElement", "Lkotlin/Function1;", "", "getOrElse", "T", "Lkotlinx/coroutines/channels/ChannelResult;", "onFailure", "", "Lkotlin/ParameterName;", "name", "exception", "getOrElse-WpGqRn0", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "onClosed", "action", "onClosed-WpGqRn0", "onFailure-WpGqRn0", "onSuccess", "value", "onSuccess-WpGqRn0", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ChannelKt {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.4.0, binary compatibility with earlier versions")
    public static final Channel Channel(int v) {
        return ChannelKt.Channel$default(v, null, null, 6, null);
    }

    public static final Channel Channel(int v, BufferOverflow bufferOverflow0, Function1 function10) {
        if(v != -2) {
            switch(v) {
                case -1: {
                    if(bufferOverflow0 != BufferOverflow.SUSPEND) {
                        throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
                    }
                    return new ConflatedBufferedChannel(1, BufferOverflow.DROP_OLDEST, function10);
                }
                case 0: {
                    return bufferOverflow0 == BufferOverflow.SUSPEND ? new BufferedChannel(0, function10) : new ConflatedBufferedChannel(1, bufferOverflow0, function10);
                }
                default: {
                    if(v != 0x7FFFFFFF) {
                        return bufferOverflow0 == BufferOverflow.SUSPEND ? new BufferedChannel(v, function10) : new ConflatedBufferedChannel(v, bufferOverflow0, function10);
                    }
                    return new BufferedChannel(0x7FFFFFFF, function10);
                }
            }
        }
        return bufferOverflow0 == BufferOverflow.SUSPEND ? new BufferedChannel(0x40, function10) : new ConflatedBufferedChannel(1, bufferOverflow0, function10);
    }

    public static Channel Channel$default(int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0;
        }
        return ChannelKt.Channel(v);
    }

    public static Channel Channel$default(int v, BufferOverflow bufferOverflow0, Function1 function10, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0;
        }
        if((v1 & 2) != 0) {
            bufferOverflow0 = BufferOverflow.SUSPEND;
        }
        if((v1 & 4) != 0) {
            function10 = null;
        }
        return ChannelKt.Channel(v, bufferOverflow0, function10);
    }

    // 去混淆评级： 低(20)
    public static final Object getOrElse-WpGqRn0(Object object0, Function1 function10) {
        return object0 instanceof Failed ? function10.invoke(ChannelResult.exceptionOrNull-impl(object0)) : object0;
    }

    public static final Object onClosed-WpGqRn0(Object object0, Function1 function10) {
        if(object0 instanceof Closed) {
            function10.invoke(ChannelResult.exceptionOrNull-impl(object0));
        }
        return object0;
    }

    public static final Object onFailure-WpGqRn0(Object object0, Function1 function10) {
        if(object0 instanceof Failed) {
            function10.invoke(ChannelResult.exceptionOrNull-impl(object0));
        }
        return object0;
    }

    public static final Object onSuccess-WpGqRn0(Object object0, Function1 function10) {
        if(!(object0 instanceof Failed)) {
            function10.invoke(object0);
        }
        return object0;
    }
}

