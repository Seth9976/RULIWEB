package okio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000B\b\u0000\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B/\b\u0016\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\u0006\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\t¢\u0006\u0002\u0010\u000BJ\u0006\u0010\u000E\u001A\u00020\u000FJ\b\u0010\u0010\u001A\u0004\u0018\u00010\u0000J\u000E\u0010\u0011\u001A\u00020\u00002\u0006\u0010\u0012\u001A\u00020\u0000J\u0006\u0010\u0013\u001A\u00020\u0000J\u000E\u0010\u0014\u001A\u00020\u00002\u0006\u0010\u0015\u001A\u00020\u0006J\u0006\u0010\u0016\u001A\u00020\u0000J\u0016\u0010\u0017\u001A\u00020\u000F2\u0006\u0010\u0018\u001A\u00020\u00002\u0006\u0010\u0015\u001A\u00020\u0006R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001A\u00020\u00068\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001A\u00020\t8\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001A\u00020\u00068\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001A\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001A\u00020\t8\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Lokio/Segment;", "", "()V", "data", "", "pos", "", "limit", "shared", "", "owner", "([BIIZZ)V", "next", "prev", "compact", "", "pop", "push", "segment", "sharedCopy", "split", "byteCount", "unsharedCopy", "writeTo", "sink", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class Segment {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokio/Segment$Companion;", "", "()V", "SHARE_MINIMUM", "", "SIZE", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    public static final int SHARE_MINIMUM = 0x400;
    public static final int SIZE = 0x2000;
    public final byte[] data;
    public int limit;
    public Segment next;
    public boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;

    static {
        Segment.Companion = new Companion(null);
    }

    public Segment() {
        this.data = new byte[0x2000];
        this.owner = true;
        this.shared = false;
    }

    public Segment(byte[] arr_b, int v, int v1, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(arr_b, "data");
        super();
        this.data = arr_b;
        this.pos = v;
        this.limit = v1;
        this.shared = z;
        this.owner = z1;
    }

    public final void compact() {
        int v2;
        Segment segment0 = this.prev;
        if(segment0 == this) {
            throw new IllegalStateException("cannot compact");
        }
        Intrinsics.checkNotNull(segment0);
        if(segment0.owner) {
            int v = this.limit - this.pos;
            Segment segment1 = this.prev;
            Intrinsics.checkNotNull(segment1);
            int v1 = 0x2000 - segment1.limit;
            Segment segment2 = this.prev;
            Intrinsics.checkNotNull(segment2);
            if(segment2.shared) {
                v2 = 0;
            }
            else {
                Segment segment3 = this.prev;
                Intrinsics.checkNotNull(segment3);
                v2 = segment3.pos;
            }
            if(v <= v1 + v2) {
                Segment segment4 = this.prev;
                Intrinsics.checkNotNull(segment4);
                this.writeTo(segment4, v);
                this.pop();
                SegmentPool.recycle(this);
            }
        }
    }

    public final Segment pop() {
        Segment segment0 = this.next == this ? null : this.next;
        Segment segment1 = this.prev;
        Intrinsics.checkNotNull(segment1);
        segment1.next = this.next;
        Segment segment2 = this.next;
        Intrinsics.checkNotNull(segment2);
        segment2.prev = this.prev;
        this.next = null;
        this.prev = null;
        return segment0;
    }

    public final Segment push(Segment segment0) {
        Intrinsics.checkNotNullParameter(segment0, "segment");
        segment0.prev = this;
        segment0.next = this.next;
        Segment segment1 = this.next;
        Intrinsics.checkNotNull(segment1);
        segment1.prev = segment0;
        this.next = segment0;
        return segment0;
    }

    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    public final Segment split(int v) {
        Segment segment0;
        if(v <= 0 || v > this.limit - this.pos) {
            throw new IllegalArgumentException("byteCount out of range");
        }
        if(v >= 0x400) {
            segment0 = this.sharedCopy();
        }
        else {
            segment0 = SegmentPool.take();
            ArraysKt.copyInto$default(this.data, segment0.data, 0, this.pos, this.pos + v, 2, null);
        }
        segment0.limit = segment0.pos + v;
        this.pos += v;
        Segment segment1 = this.prev;
        Intrinsics.checkNotNull(segment1);
        segment1.push(segment0);
        return segment0;
    }

    public final Segment unsharedCopy() {
        byte[] arr_b = Arrays.copyOf(this.data, this.data.length);
        Intrinsics.checkNotNullExpressionValue(arr_b, "copyOf(this, size)");
        return new Segment(arr_b, this.pos, this.limit, false, true);
    }

    public final void writeTo(Segment segment0, int v) {
        Intrinsics.checkNotNullParameter(segment0, "sink");
        if(!segment0.owner) {
            throw new IllegalStateException("only owner can write");
        }
        int v1 = segment0.limit;
        if(v1 + v > 0x2000) {
            if(segment0.shared) {
                throw new IllegalArgumentException();
            }
            int v2 = segment0.pos;
            if(v1 + v - v2 > 0x2000) {
                throw new IllegalArgumentException();
            }
            ArraysKt.copyInto$default(segment0.data, segment0.data, 0, v2, v1, 2, null);
            segment0.limit -= segment0.pos;
            segment0.pos = 0;
        }
        ArraysKt.copyInto(this.data, segment0.data, segment0.limit, this.pos, this.pos + v);
        segment0.limit += v;
        this.pos += v;
    }
}

