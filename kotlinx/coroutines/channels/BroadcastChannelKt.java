package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001A\u001C\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"NO_ELEMENT", "Lkotlinx/coroutines/internal/Symbol;", "BroadcastChannel", "Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "capacity", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class BroadcastChannelKt {
    private static final Symbol NO_ELEMENT;

    static {
        BroadcastChannelKt.NO_ELEMENT = new Symbol("NO_ELEMENT");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "BroadcastChannel is deprecated in the favour of SharedFlow and StateFlow, and is no longer supported")
    public static final BroadcastChannel BroadcastChannel(int v) {
        if(v != -2) {
            switch(v) {
                case -1: {
                    return new ConflatedBroadcastChannel();
                }
                case 0: {
                    throw new IllegalArgumentException("Unsupported 0 capacity for BroadcastChannel");
                }
                default: {
                    if(v == 0x7FFFFFFF) {
                        throw new IllegalArgumentException("Unsupported UNLIMITED capacity for BroadcastChannel");
                    }
                    return new BroadcastChannelImpl(v);
                }
            }
        }
        return new BroadcastChannelImpl(0x40);
    }
}

