package androidx.core.os;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u00072\u00020\u0001:\u0003\u0007\b\tB\u000F\b\u0004\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\n\u000B¨\u0006\f"}, d2 = {"Landroidx/core/os/BufferFillPolicy;", "", "value", "", "(I)V", "getValue$core_release", "()I", "Companion", "Discard", "RingBuffer", "Landroidx/core/os/BufferFillPolicy$Discard;", "Landroidx/core/os/BufferFillPolicy$RingBuffer;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class BufferFillPolicy {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/core/os/BufferFillPolicy$Companion;", "", "()V", "DISCARD", "Landroidx/core/os/BufferFillPolicy;", "RING_BUFFER", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/core/os/BufferFillPolicy$Discard;", "Landroidx/core/os/BufferFillPolicy;", "()V", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Discard extends BufferFillPolicy {
        public Discard() {
            super(1, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/core/os/BufferFillPolicy$RingBuffer;", "Landroidx/core/os/BufferFillPolicy;", "()V", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class RingBuffer extends BufferFillPolicy {
        public RingBuffer() {
            super(2, null);
        }
    }

    public static final Companion Companion;
    public static final BufferFillPolicy DISCARD;
    public static final BufferFillPolicy RING_BUFFER;
    private final int value;

    static {
        BufferFillPolicy.Companion = new Companion(null);
        BufferFillPolicy.DISCARD = new Discard();
        BufferFillPolicy.RING_BUFFER = new RingBuffer();
    }

    private BufferFillPolicy(int v) {
        this.value = v;
    }

    public BufferFillPolicy(int v, DefaultConstructorMarker defaultConstructorMarker0) {
        this(v);
    }

    public final int getValue$core_release() {
        return this.value;
    }
}

