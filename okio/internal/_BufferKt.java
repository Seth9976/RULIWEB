package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer.UnsafeCursor;
import okio.Buffer;
import okio.ByteString;
import okio.Options;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio._UtilKt;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A0\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\n2\u0006\u0010\u0010\u001A\u00020\u00012\u0006\u0010\u0011\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\nH\u0000\u001A\r\u0010\u0013\u001A\u00020\u0014*\u00020\u0015H\u0080\b\u001A\r\u0010\u0016\u001A\u00020\u0014*\u00020\u0017H\u0080\b\u001A\r\u0010\u0018\u001A\u00020\u0007*\u00020\u0015H\u0080\b\u001A\r\u0010\u0019\u001A\u00020\u0015*\u00020\u0015H\u0080\b\u001A%\u0010\u001A\u001A\u00020\u0015*\u00020\u00152\u0006\u0010\u001B\u001A\u00020\u00152\u0006\u0010\u001C\u001A\u00020\u00072\u0006\u0010\u001D\u001A\u00020\u0007H\u0080\b\u001A\u0017\u0010\u001E\u001A\u00020\f*\u00020\u00152\b\u0010\u001F\u001A\u0004\u0018\u00010 H\u0080\b\u001A\u0015\u0010!\u001A\u00020\u0007*\u00020\u00172\u0006\u0010\"\u001A\u00020\nH\u0080\b\u001A\u0015\u0010#\u001A\u00020$*\u00020\u00152\u0006\u0010%\u001A\u00020\u0007H\u0080\b\u001A\r\u0010&\u001A\u00020\n*\u00020\u0015H\u0080\b\u001A%\u0010\'\u001A\u00020\u0007*\u00020\u00152\u0006\u0010(\u001A\u00020$2\u0006\u0010)\u001A\u00020\u00072\u0006\u0010*\u001A\u00020\u0007H\u0080\b\u001A\u001D\u0010\'\u001A\u00020\u0007*\u00020\u00152\u0006\u0010\u0010\u001A\u00020+2\u0006\u0010)\u001A\u00020\u0007H\u0080\b\u001A\u001D\u0010,\u001A\u00020\u0007*\u00020\u00152\u0006\u0010-\u001A\u00020+2\u0006\u0010)\u001A\u00020\u0007H\u0080\b\u001A\r\u0010.\u001A\u00020\n*\u00020\u0017H\u0080\b\u001A-\u0010/\u001A\u00020\f*\u00020\u00152\u0006\u0010\u001C\u001A\u00020\u00072\u0006\u0010\u0010\u001A\u00020+2\u0006\u0010\u0011\u001A\u00020\n2\u0006\u0010\u001D\u001A\u00020\nH\u0080\b\u001A\u0015\u00100\u001A\u00020\n*\u00020\u00152\u0006\u00101\u001A\u00020\u0001H\u0080\b\u001A%\u00100\u001A\u00020\n*\u00020\u00152\u0006\u00101\u001A\u00020\u00012\u0006\u0010\u001C\u001A\u00020\n2\u0006\u0010\u001D\u001A\u00020\nH\u0080\b\u001A\u001D\u00100\u001A\u00020\u0007*\u00020\u00152\u0006\u00101\u001A\u00020\u00152\u0006\u0010\u001D\u001A\u00020\u0007H\u0080\b\u001A\u0015\u00102\u001A\u00020\u0007*\u00020\u00152\u0006\u00101\u001A\u000203H\u0080\b\u001A\u0014\u00104\u001A\u00020\u0017*\u00020\u00152\u0006\u00105\u001A\u00020\u0017H\u0000\u001A\r\u00106\u001A\u00020$*\u00020\u0015H\u0080\b\u001A\r\u00107\u001A\u00020\u0001*\u00020\u0015H\u0080\b\u001A\u0015\u00107\u001A\u00020\u0001*\u00020\u00152\u0006\u0010\u001D\u001A\u00020\u0007H\u0080\b\u001A\r\u00108\u001A\u00020+*\u00020\u0015H\u0080\b\u001A\u0015\u00108\u001A\u00020+*\u00020\u00152\u0006\u0010\u001D\u001A\u00020\u0007H\u0080\b\u001A\r\u00109\u001A\u00020\u0007*\u00020\u0015H\u0080\b\u001A\u0015\u0010:\u001A\u00020\u0014*\u00020\u00152\u0006\u00101\u001A\u00020\u0001H\u0080\b\u001A\u001D\u0010:\u001A\u00020\u0014*\u00020\u00152\u0006\u00101\u001A\u00020\u00152\u0006\u0010\u001D\u001A\u00020\u0007H\u0080\b\u001A\r\u0010;\u001A\u00020\u0007*\u00020\u0015H\u0080\b\u001A\r\u0010<\u001A\u00020\n*\u00020\u0015H\u0080\b\u001A\r\u0010=\u001A\u00020\u0007*\u00020\u0015H\u0080\b\u001A\r\u0010>\u001A\u00020?*\u00020\u0015H\u0080\b\u001A\u0014\u0010@\u001A\u00020\u0017*\u00020\u00152\u0006\u00105\u001A\u00020\u0017H\u0000\u001A\u0015\u0010A\u001A\u00020B*\u00020\u00152\u0006\u0010\u001D\u001A\u00020\u0007H\u0080\b\u001A\r\u0010C\u001A\u00020\n*\u00020\u0015H\u0080\b\u001A\u000F\u0010D\u001A\u0004\u0018\u00010B*\u00020\u0015H\u0080\b\u001A\u0015\u0010E\u001A\u00020B*\u00020\u00152\u0006\u0010F\u001A\u00020\u0007H\u0080\b\u001A\u0015\u0010G\u001A\u00020\u0007*\u00020\u00172\u0006\u0010H\u001A\u00020\u0007H\u0080\b\u001A\u0015\u0010I\u001A\u00020\n*\u00020\u00172\u0006\u0010\u001C\u001A\u00020\u0007H\u0080\b\u001A\u0015\u0010J\u001A\u00020\n*\u00020\u00152\u0006\u0010K\u001A\u00020LH\u0080\b\u001A\u0015\u0010M\u001A\u00020\u0014*\u00020\u00152\u0006\u0010\u001D\u001A\u00020\u0007H\u0080\b\u001A\r\u0010N\u001A\u00020+*\u00020\u0015H\u0080\b\u001A\u0015\u0010N\u001A\u00020+*\u00020\u00152\u0006\u0010\u001D\u001A\u00020\nH\u0080\b\u001A\u0015\u0010O\u001A\u00020\u000E*\u00020\u00152\u0006\u0010P\u001A\u00020\nH\u0080\b\u001A\u0015\u0010Q\u001A\u00020\u0015*\u00020\u00152\u0006\u0010R\u001A\u00020\u0001H\u0080\b\u001A%\u0010Q\u001A\u00020\u0015*\u00020\u00152\u0006\u0010R\u001A\u00020\u00012\u0006\u0010\u001C\u001A\u00020\n2\u0006\u0010\u001D\u001A\u00020\nH\u0080\b\u001A\u001D\u0010Q\u001A\u00020\u0014*\u00020\u00152\u0006\u0010R\u001A\u00020\u00152\u0006\u0010\u001D\u001A\u00020\u0007H\u0080\b\u001A)\u0010Q\u001A\u00020\u0015*\u00020\u00152\u0006\u0010S\u001A\u00020+2\b\b\u0002\u0010\u001C\u001A\u00020\n2\b\b\u0002\u0010\u001D\u001A\u00020\nH\u0080\b\u001A\u001D\u0010Q\u001A\u00020\u0015*\u00020\u00152\u0006\u0010R\u001A\u00020T2\u0006\u0010\u001D\u001A\u00020\u0007H\u0080\b\u001A\u0015\u0010U\u001A\u00020\u0007*\u00020\u00152\u0006\u0010R\u001A\u00020TH\u0080\b\u001A\u0015\u0010V\u001A\u00020\u0015*\u00020\u00152\u0006\u0010(\u001A\u00020\nH\u0080\b\u001A\u0015\u0010W\u001A\u00020\u0015*\u00020\u00152\u0006\u0010X\u001A\u00020\u0007H\u0080\b\u001A\u0015\u0010Y\u001A\u00020\u0015*\u00020\u00152\u0006\u0010X\u001A\u00020\u0007H\u0080\b\u001A\u0015\u0010Z\u001A\u00020\u0015*\u00020\u00152\u0006\u0010[\u001A\u00020\nH\u0080\b\u001A\u0015\u0010\\\u001A\u00020\u0015*\u00020\u00152\u0006\u0010X\u001A\u00020\u0007H\u0080\b\u001A\u0015\u0010]\u001A\u00020\u0015*\u00020\u00152\u0006\u0010^\u001A\u00020\nH\u0080\b\u001A%\u0010_\u001A\u00020\u0015*\u00020\u00152\u0006\u0010`\u001A\u00020B2\u0006\u0010a\u001A\u00020\n2\u0006\u0010b\u001A\u00020\nH\u0080\b\u001A\u0015\u0010c\u001A\u00020\u0015*\u00020\u00152\u0006\u0010d\u001A\u00020\nH\u0080\b\u001A\u0014\u0010e\u001A\u00020B*\u00020\u00152\u0006\u0010f\u001A\u00020\u0007H\u0000\u001A?\u0010g\u001A\u0002Hh\"\u0004\b\u0000\u0010h*\u00020\u00152\u0006\u0010)\u001A\u00020\u00072\u001A\u0010i\u001A\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002Hh0jH\u0080\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010k\u001A\u001E\u0010l\u001A\u00020\n*\u00020\u00152\u0006\u0010K\u001A\u00020L2\b\b\u0002\u0010m\u001A\u00020\fH\u0000\"\u001C\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004\u00A2\u0006\u000E\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001A\u0004\b\u0004\u0010\u0005\"\u000E\u0010\u0006\u001A\u00020\u0007X\u0080T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\b\u001A\u00020\u0007X\u0080T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\t\u001A\u00020\nX\u0080T\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006n"}, d2 = {"HEX_DIGIT_BYTES", "", "getHEX_DIGIT_BYTES$annotations", "()V", "getHEX_DIGIT_BYTES", "()[B", "OVERFLOW_DIGIT_START", "", "OVERFLOW_ZONE", "SEGMENTING_THRESHOLD", "", "rangeEquals", "", "segment", "Lokio/Segment;", "segmentPos", "bytes", "bytesOffset", "bytesLimit", "commonClear", "", "Lokio/Buffer;", "commonClose", "Lokio/Buffer$UnsafeCursor;", "commonCompleteSegmentByteCount", "commonCopy", "commonCopyTo", "out", "offset", "byteCount", "commonEquals", "other", "", "commonExpandBuffer", "minByteCount", "commonGet", "", "pos", "commonHashCode", "commonIndexOf", "b", "fromIndex", "toIndex", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonNext", "commonRangeEquals", "commonRead", "sink", "commonReadAll", "Lokio/Sink;", "commonReadAndWriteUnsafe", "unsafeCursor", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadLong", "commonReadShort", "", "commonReadUnsafe", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", "limit", "commonResizeBuffer", "newSize", "commonSeek", "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonSnapshot", "commonWritableSegment", "minimumCapacity", "commonWrite", "source", "byteString", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteLong", "commonWriteShort", "s", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "readUtf8Line", "newline", "seek", "T", "lambda", "Lkotlin/Function2;", "(Lokio/Buffer;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "selectPrefix", "selectTruncated", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _BufferKt {
    private static final byte[] HEX_DIGIT_BYTES = null;
    public static final long OVERFLOW_DIGIT_START = -7L;
    public static final long OVERFLOW_ZONE = -922337203685477580L;
    public static final int SEGMENTING_THRESHOLD = 0x1000;

    static {
        _BufferKt.HEX_DIGIT_BYTES = new byte[]{0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    }

    public static final void commonClear(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        buffer0.skip(buffer0.size());
    }

    public static final void commonClose(UnsafeCursor buffer$UnsafeCursor0) {
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "<this>");
        if(buffer$UnsafeCursor0.buffer == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        buffer$UnsafeCursor0.buffer = null;
        buffer$UnsafeCursor0.setSegment$okio(null);
        buffer$UnsafeCursor0.offset = -1L;
        buffer$UnsafeCursor0.data = null;
        buffer$UnsafeCursor0.start = -1;
        buffer$UnsafeCursor0.end = -1;
    }

    public static final long commonCompleteSegmentByteCount(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        long v = buffer0.size();
        if(v == 0L) {
            return 0L;
        }
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        Segment segment1 = segment0.prev;
        Intrinsics.checkNotNull(segment1);
        return segment1.limit >= 0x2000 || !segment1.owner ? v : v - ((long)(segment1.limit - segment1.pos));
    }

    public static final Buffer commonCopy(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Buffer buffer1 = new Buffer();
        if(buffer0.size() == 0L) {
            return buffer1;
        }
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        Segment segment1 = segment0.sharedCopy();
        buffer1.head = segment1;
        segment1.prev = buffer1.head;
        segment1.next = segment1.prev;
        for(Segment segment2 = segment0.next; segment2 != segment0; segment2 = segment2.next) {
            Segment segment3 = segment1.prev;
            Intrinsics.checkNotNull(segment3);
            Intrinsics.checkNotNull(segment2);
            segment3.push(segment2.sharedCopy());
        }
        buffer1.setSize$okio(buffer0.size());
        return buffer1;
    }

    public static final Buffer commonCopyTo(Buffer buffer0, Buffer buffer1, long v, long v1) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(buffer1, "out");
        long v2 = v;
        _UtilKt.checkOffsetAndCount(buffer0.size(), v2, v1);
        if(v1 != 0L) {
            buffer1.setSize$okio(buffer1.size() + v1);
            Segment segment0;
            for(segment0 = buffer0.head; true; segment0 = segment0.next) {
                Intrinsics.checkNotNull(segment0);
                if(v2 < ((long)(segment0.limit - segment0.pos))) {
                    break;
                }
                v2 -= (long)(segment0.limit - segment0.pos);
            }
            Segment segment1 = segment0;
            long v3 = v1;
            while(v3 > 0L) {
                Intrinsics.checkNotNull(segment1);
                Segment segment2 = segment1.sharedCopy();
                segment2.pos += (int)v2;
                segment2.limit = Math.min(segment2.pos + ((int)v3), segment2.limit);
                if(buffer1.head == null) {
                    segment2.prev = segment2;
                    segment2.next = segment2.prev;
                    buffer1.head = segment2.next;
                }
                else {
                    Segment segment3 = buffer1.head;
                    Intrinsics.checkNotNull(segment3);
                    Segment segment4 = segment3.prev;
                    Intrinsics.checkNotNull(segment4);
                    segment4.push(segment2);
                }
                v3 -= (long)(segment2.limit - segment2.pos);
                segment1 = segment1.next;
                v2 = 0L;
            }
        }
        return buffer0;
    }

    public static final boolean commonEquals(Buffer buffer0, Object object0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(buffer0 == object0) {
            return true;
        }
        if(!(object0 instanceof Buffer)) {
            return false;
        }
        if(buffer0.size() != ((Buffer)object0).size()) {
            return false;
        }
        if(buffer0.size() == 0L) {
            return true;
        }
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        Segment segment1 = ((Buffer)object0).head;
        Intrinsics.checkNotNull(segment1);
        int v = segment0.pos;
        int v1 = segment1.pos;
        for(long v2 = 0L; v2 < buffer0.size(); v2 += v3) {
            long v3 = (long)Math.min(segment0.limit - v, segment1.limit - v1);
            long v4 = 0L;
            while(v4 < v3) {
                if(segment0.data[v] != segment1.data[v1]) {
                    return false;
                }
                ++v4;
                ++v;
                ++v1;
            }
            if(v == segment0.limit) {
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = segment0.pos;
            }
            if(v1 == segment1.limit) {
                segment1 = segment1.next;
                Intrinsics.checkNotNull(segment1);
                v1 = segment1.pos;
            }
        }
        return true;
    }

    public static final long commonExpandBuffer(UnsafeCursor buffer$UnsafeCursor0, int v) {
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "<this>");
        if(v <= 0) {
            throw new IllegalArgumentException(("minByteCount <= 0: " + v).toString());
        }
        if(v > 0x2000) {
            throw new IllegalArgumentException(("minByteCount > Segment.SIZE: " + v).toString());
        }
        Buffer buffer0 = buffer$UnsafeCursor0.buffer;
        if(buffer0 == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        if(!buffer$UnsafeCursor0.readWrite) {
            throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
        }
        long v1 = buffer0.size();
        Segment segment0 = buffer0.writableSegment$okio(v);
        int v2 = 0x2000 - segment0.limit;
        segment0.limit = 0x2000;
        buffer0.setSize$okio(v1 + ((long)v2));
        buffer$UnsafeCursor0.setSegment$okio(segment0);
        buffer$UnsafeCursor0.offset = v1;
        buffer$UnsafeCursor0.data = segment0.data;
        buffer$UnsafeCursor0.start = 0x2000 - v2;
        buffer$UnsafeCursor0.end = 0x2000;
        return (long)v2;
    }

    public static final byte commonGet(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        _UtilKt.checkOffsetAndCount(buffer0.size(), v, 1L);
        Segment segment0 = buffer0.head;
        if(segment0 != null) {
            if(buffer0.size() - v < v) {
                long v1;
                for(v1 = buffer0.size(); v1 > v; v1 -= (long)(segment0.limit - segment0.pos)) {
                    segment0 = segment0.prev;
                    Intrinsics.checkNotNull(segment0);
                }
                Intrinsics.checkNotNull(segment0);
                return segment0.data[((int)(((long)segment0.pos) + v - v1))];
            }
            long v2 = 0L;
            long v3;
            while((v3 = ((long)(segment0.limit - segment0.pos)) + v2) <= v) {
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v2 = v3;
            }
            Intrinsics.checkNotNull(segment0);
            return segment0.data[((int)(((long)segment0.pos) + v - v2))];
        }
        Intrinsics.checkNotNull(null);
        throw null;
    }

    public static final int commonHashCode(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Segment segment0 = buffer0.head;
        if(segment0 == null) {
            return 0;
        }
        int v = 1;
        do {
            int v1 = segment0.pos;
            int v2 = segment0.limit;
            while(v1 < v2) {
                v = v * 0x1F + segment0.data[v1];
                ++v1;
            }
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
        }
        while(segment0 != buffer0.head);
        return v;
    }

    public static final long commonIndexOf(Buffer buffer0, byte b, long v, long v1) {
        int v4;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        long v2 = 0L;
        if(0L > v || v > v1) {
            throw new IllegalArgumentException(("size=" + buffer0.size() + " fromIndex=" + v + " toIndex=" + v1).toString());
        }
        if(v1 > buffer0.size()) {
            v1 = buffer0.size();
        }
        if(v == v1) {
            return -1L;
        }
        Segment segment0 = buffer0.head;
        if(segment0 == null) {
            return -1L;
        }
        if(buffer0.size() - v < v) {
            for(v2 = buffer0.size(); v2 > v; v2 -= (long)(segment0.limit - segment0.pos)) {
                segment0 = segment0.prev;
                Intrinsics.checkNotNull(segment0);
            }
            if(segment0 == null) {
                return -1L;
            }
            while(v2 < v1) {
                byte[] arr_b = segment0.data;
                int v3 = (int)Math.min(segment0.limit, ((long)segment0.pos) + v1 - v2);
                for(v4 = (int)(((long)segment0.pos) + v - v2); v4 < v3; ++v4) {
                    if(arr_b[v4] == b) {
                        return ((long)(v4 - segment0.pos)) + v2;
                    }
                }
                v2 += (long)(segment0.limit - segment0.pos);
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = v2;
            }
            return -1L;
        }
        long v5;
        while((v5 = ((long)(segment0.limit - segment0.pos)) + v2) <= v) {
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v2 = v5;
        }
        if(segment0 == null) {
            return -1L;
        }
        while(v2 < v1) {
            byte[] arr_b1 = segment0.data;
            int v6 = (int)Math.min(segment0.limit, ((long)segment0.pos) + v1 - v2);
            v4 = (int)(((long)segment0.pos) + v - v2);
            while(v4 < v6) {
                if(arr_b1[v4] != b) {
                    ++v4;
                    continue;
                }
                return ((long)(v4 - segment0.pos)) + v2;
            }
            v2 += (long)(segment0.limit - segment0.pos);
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v = v2;
        }
        return -1L;
    }

    public static final long commonIndexOf(Buffer buffer0, ByteString byteString0, long v) {
        int v6;
        long v1 = 0L;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        if(byteString0.size() <= 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        if(v < 0L) {
            throw new IllegalArgumentException(("fromIndex < 0: " + v).toString());
        }
        Segment segment0 = buffer0.head;
        if(segment0 == null) {
            return -1L;
        }
        if(buffer0.size() - v < v) {
            for(v1 = buffer0.size(); v1 > v; v1 -= (long)(segment0.limit - segment0.pos)) {
                segment0 = segment0.prev;
                Intrinsics.checkNotNull(segment0);
            }
            if(segment0 == null) {
                return -1L;
            }
            byte[] arr_b = byteString0.internalArray$okio();
            int v2 = arr_b[0];
            int v3 = byteString0.size();
            long v4 = buffer0.size() - ((long)v3) + 1L;
            while(v1 < v4) {
                byte[] arr_b1 = segment0.data;
                int v5 = (int)Math.min(segment0.limit, ((long)segment0.pos) + v4 - v1);
                for(v6 = (int)(((long)segment0.pos) + v - v1); v6 < v5; ++v6) {
                    if(arr_b1[v6] == v2 && _BufferKt.rangeEquals(segment0, v6 + 1, arr_b, 1, v3)) {
                        return ((long)(v6 - segment0.pos)) + v1;
                    }
                }
                v1 += (long)(segment0.limit - segment0.pos);
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = v1;
            }
            return -1L;
        }
        long v7;
        while((v7 = ((long)(segment0.limit - segment0.pos)) + v1) <= v) {
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v1 = v7;
        }
        if(segment0 == null) {
            return -1L;
        }
        byte[] arr_b2 = byteString0.internalArray$okio();
        int v8 = arr_b2[0];
        int v9 = byteString0.size();
        long v10 = buffer0.size() - ((long)v9) + 1L;
        while(v1 < v10) {
            byte[] arr_b3 = segment0.data;
            int v11 = (int)Math.min(segment0.limit, ((long)segment0.pos) + v10 - v1);
            v6 = (int)(((long)segment0.pos) + v - v1);
            while(v6 < v11) {
                if(arr_b3[v6] != v8 || !_BufferKt.rangeEquals(segment0, v6 + 1, arr_b2, 1, v9)) {
                    ++v6;
                    continue;
                }
                return ((long)(v6 - segment0.pos)) + v1;
            }
            v1 += (long)(segment0.limit - segment0.pos);
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v = v1;
        }
        return -1L;
    }

    public static final long commonIndexOfElement(Buffer buffer0, ByteString byteString0, long v) {
        int v4;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "targetBytes");
        long v1 = 0L;
        if(v < 0L) {
            throw new IllegalArgumentException(("fromIndex < 0: " + v).toString());
        }
        Segment segment0 = buffer0.head;
        if(segment0 == null) {
            return -1L;
        }
        if(buffer0.size() - v < v) {
            for(v1 = buffer0.size(); v1 > v; v1 -= (long)(segment0.limit - segment0.pos)) {
                segment0 = segment0.prev;
                Intrinsics.checkNotNull(segment0);
            }
            if(segment0 == null) {
                return -1L;
            }
            if(byteString0.size() == 2) {
                int v2 = byteString0.getByte(0);
                int v3 = byteString0.getByte(1);
                while(v1 < buffer0.size()) {
                    byte[] arr_b = segment0.data;
                    v4 = (int)(((long)segment0.pos) + v - v1);
                    int v5 = segment0.limit;
                    while(v4 < v5) {
                        int v6 = arr_b[v4];
                        if(v6 == v2 || v6 == v3) {
                            return ((long)(v4 - segment0.pos)) + v1;
                        }
                        ++v4;
                    }
                    v1 += (long)(segment0.limit - segment0.pos);
                    segment0 = segment0.next;
                    Intrinsics.checkNotNull(segment0);
                    v = v1;
                }
                return -1L;
            }
            byte[] arr_b1 = byteString0.internalArray$okio();
            while(v1 < buffer0.size()) {
                byte[] arr_b2 = segment0.data;
                v4 = (int)(((long)segment0.pos) + v - v1);
                int v7 = segment0.limit;
                while(v4 < v7) {
                    int v8 = arr_b2[v4];
                    for(int v9 = 0; v9 < arr_b1.length; ++v9) {
                        if(v8 == arr_b1[v9]) {
                            return ((long)(v4 - segment0.pos)) + v1;
                        }
                    }
                    ++v4;
                }
                v1 += (long)(segment0.limit - segment0.pos);
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = v1;
            }
            return -1L;
        }
        long v10;
        while((v10 = ((long)(segment0.limit - segment0.pos)) + v1) <= v) {
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v1 = v10;
        }
        if(segment0 == null) {
            return -1L;
        }
        if(byteString0.size() == 2) {
            int v11 = byteString0.getByte(0);
            int v12 = byteString0.getByte(1);
            while(v1 < buffer0.size()) {
                byte[] arr_b3 = segment0.data;
                v4 = (int)(((long)segment0.pos) + v - v1);
                int v13 = segment0.limit;
                while(v4 < v13) {
                    int v14 = arr_b3[v4];
                    if(v14 != v11 && v14 != v12) {
                        ++v4;
                        continue;
                    }
                    return ((long)(v4 - segment0.pos)) + v1;
                }
                v1 += (long)(segment0.limit - segment0.pos);
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = v1;
            }
            return -1L;
        }
        byte[] arr_b4 = byteString0.internalArray$okio();
        while(v1 < buffer0.size()) {
            byte[] arr_b5 = segment0.data;
            v4 = (int)(((long)segment0.pos) + v - v1);
            int v15 = segment0.limit;
            while(v4 < v15) {
                int v16 = arr_b5[v4];
                int v17 = 0;
                while(v17 < arr_b4.length) {
                    if(v16 != arr_b4[v17]) {
                        ++v17;
                        continue;
                    }
                    return ((long)(v4 - segment0.pos)) + v1;
                }
                ++v4;
            }
            v1 += (long)(segment0.limit - segment0.pos);
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v = v1;
        }
        return -1L;
    }

    public static final int commonNext(UnsafeCursor buffer$UnsafeCursor0) {
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "<this>");
        long v = buffer$UnsafeCursor0.offset;
        Buffer buffer0 = buffer$UnsafeCursor0.buffer;
        Intrinsics.checkNotNull(buffer0);
        if(v == buffer0.size()) {
            throw new IllegalStateException("no more bytes");
        }
        return buffer$UnsafeCursor0.offset == -1L ? buffer$UnsafeCursor0.seek(0L) : buffer$UnsafeCursor0.seek(buffer$UnsafeCursor0.offset + ((long)(buffer$UnsafeCursor0.end - buffer$UnsafeCursor0.start)));
    }

    public static final boolean commonRangeEquals(Buffer buffer0, long v, ByteString byteString0, int v1, int v2) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        if(v >= 0L && v1 >= 0 && v2 >= 0 && buffer0.size() - v >= ((long)v2) && byteString0.size() - v1 >= v2) {
            for(int v3 = 0; v3 < v2; ++v3) {
                if(buffer0.getByte(((long)v3) + v) != byteString0.getByte(v1 + v3)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static final int commonRead(Buffer buffer0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        return buffer0.read(arr_b, 0, arr_b.length);
    }

    public static final int commonRead(Buffer buffer0, byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        _UtilKt.checkOffsetAndCount(arr_b.length, v, v1);
        Segment segment0 = buffer0.head;
        if(segment0 == null) {
            return -1;
        }
        int v2 = Math.min(v1, segment0.limit - segment0.pos);
        ArraysKt.copyInto(segment0.data, arr_b, v, segment0.pos, segment0.pos + v2);
        segment0.pos += v2;
        buffer0.setSize$okio(buffer0.size() - ((long)v2));
        if(segment0.pos == segment0.limit) {
            buffer0.head = segment0.pop();
            SegmentPool.recycle(segment0);
        }
        return v2;
    }

    public static final long commonRead(Buffer buffer0, Buffer buffer1, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(buffer1, "sink");
        if(v < 0L) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(buffer0.size() == 0L) {
            return -1L;
        }
        if(v > buffer0.size()) {
            v = buffer0.size();
        }
        buffer1.write(buffer0, v);
        return v;
    }

    public static final long commonReadAll(Buffer buffer0, Sink sink0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(sink0, "sink");
        long v = buffer0.size();
        if(v > 0L) {
            sink0.write(buffer0, v);
        }
        return v;
    }

    public static final UnsafeCursor commonReadAndWriteUnsafe(Buffer buffer0, UnsafeCursor buffer$UnsafeCursor0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "unsafeCursor");
        UnsafeCursor buffer$UnsafeCursor1 = _UtilKt.resolveDefaultParameter(buffer$UnsafeCursor0);
        if(buffer$UnsafeCursor1.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        buffer$UnsafeCursor1.buffer = buffer0;
        buffer$UnsafeCursor1.readWrite = true;
        return buffer$UnsafeCursor1;
    }

    public static final byte commonReadByte(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(buffer0.size() == 0L) {
            throw new EOFException();
        }
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        int v = segment0.limit;
        int v1 = segment0.pos + 1;
        byte b = segment0.data[segment0.pos];
        buffer0.setSize$okio(buffer0.size() - 1L);
        if(v1 == v) {
            buffer0.head = segment0.pop();
            SegmentPool.recycle(segment0);
            return b;
        }
        segment0.pos = v1;
        return b;
    }

    public static final byte[] commonReadByteArray(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        return buffer0.readByteArray(buffer0.size());
    }

    public static final byte[] commonReadByteArray(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(v < 0L || v > 0x7FFFFFFFL) {
            throw new IllegalArgumentException(("byteCount: " + v).toString());
        }
        if(buffer0.size() < v) {
            throw new EOFException();
        }
        byte[] arr_b = new byte[((int)v)];
        buffer0.readFully(arr_b);
        return arr_b;
    }

    public static final ByteString commonReadByteString(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        return buffer0.readByteString(buffer0.size());
    }

    public static final ByteString commonReadByteString(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(v < 0L || v > 0x7FFFFFFFL) {
            throw new IllegalArgumentException(("byteCount: " + v).toString());
        }
        if(buffer0.size() < v) {
            throw new EOFException();
        }
        if(v >= 0x1000L) {
            ByteString byteString0 = buffer0.snapshot(((int)v));
            buffer0.skip(v);
            return byteString0;
        }
        return new ByteString(buffer0.readByteArray(v));
    }

    public static final long commonReadDecimalLong(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(buffer0.size() == 0L) {
            throw new EOFException();
        }
        int v = 0;
        long v1 = 0L;
        long v2 = -7L;
        boolean z = false;
        boolean z1 = false;
        do {
            Segment segment0 = buffer0.head;
            Intrinsics.checkNotNull(segment0);
            byte[] arr_b = segment0.data;
            int v3 = segment0.pos;
            int v4 = segment0.limit;
            while(v3 < v4) {
                int v5 = arr_b[v3];
                if(v5 >= 0x30 && v5 <= 57) {
                    int v6 = Long.compare(v1, -922337203685477580L);
                    if(v6 >= 0 && (v6 != 0 || ((long)(0x30 - v5)) >= v2)) {
                        v1 = v1 * 10L + ((long)(0x30 - v5));
                        goto label_26;
                    }
                    Buffer buffer1 = new Buffer().writeDecimalLong(v1).writeByte(v5);
                    if(!z) {
                        buffer1.readByte();
                    }
                    throw new NumberFormatException("Number too large: ");
                }
                else if(v5 == 45 && v == 0) {
                    --v2;
                    z = true;
                }
                else {
                    goto label_29;
                }
            label_26:
                ++v3;
                ++v;
                continue;
            label_29:
                z1 = true;
                if(true) {
                    break;
                }
            }
            if(v3 == v4) {
                buffer0.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
            else {
                segment0.pos = v3;
            }
        }
        while(!z1 && buffer0.head != null);
        buffer0.setSize$okio(buffer0.size() - ((long)v));
        if(v < (z ? 2 : 1)) {
            if(buffer0.size() == 0L) {
                throw new EOFException();
            }
            throw new NumberFormatException((z ? "Expected a digit" : "Expected a digit or \'-\'") + " but was 0x" + _UtilKt.toHexString(buffer0.getByte(0L)));
        }
        return z ? v1 : -v1;
    }

    public static final void commonReadFully(Buffer buffer0, Buffer buffer1, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(buffer1, "sink");
        if(buffer0.size() >= v) {
            buffer1.write(buffer0, v);
            return;
        }
        buffer1.write(buffer0, buffer0.size());
        throw new EOFException();
    }

    public static final void commonReadFully(Buffer buffer0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        for(int v = 0; v < arr_b.length; v += v1) {
            int v1 = buffer0.read(arr_b, v, arr_b.length - v);
            if(v1 == -1) {
                throw new EOFException();
            }
        }
    }

    public static final long commonReadHexadecimalUnsignedLong(Buffer buffer0) {
        int v6;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(buffer0.size() == 0L) {
            throw new EOFException();
        }
        int v = 0;
        long v1 = 0L;
        boolean z = false;
        do {
            Segment segment0 = buffer0.head;
            Intrinsics.checkNotNull(segment0);
            byte[] arr_b = segment0.data;
            int v2 = segment0.pos;
            int v3 = segment0.limit;
            while(v2 < v3) {
                int v4 = 97;
                int v5 = arr_b[v2];
                if(v5 < 0x30 || v5 > 57) {
                    if(v5 < 97 || v5 > 102) {
                        v4 = 65;
                        if(v5 < 65 || v5 > 70) {
                            if(v == 0) {
                                throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + _UtilKt.toHexString(((byte)v5)));
                            }
                            z = true;
                            break;
                        }
                    }
                    v6 = v5 - v4 + 10;
                }
                else {
                    v6 = v5 - 0x30;
                }
                if((0xF000000000000000L & v1) != 0L) {
                    throw new NumberFormatException("Number too large: ");
                }
                v1 = v1 << 4 | ((long)v6);
                ++v2;
                ++v;
            }
            if(v2 == v3) {
                buffer0.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
            else {
                segment0.pos = v2;
            }
        }
        while(!z && buffer0.head != null);
        buffer0.setSize$okio(buffer0.size() - ((long)v));
        return v1;
    }

    public static final int commonReadInt(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(buffer0.size() < 4L) {
            throw new EOFException();
        }
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        int v = segment0.pos;
        int v1 = segment0.limit;
        if(((long)(v1 - v)) < 4L) {
            int v2 = buffer0.readByte();
            int v3 = buffer0.readByte();
            int v4 = buffer0.readByte();
            return buffer0.readByte() & 0xFF | ((v2 & 0xFF) << 24 | (v3 & 0xFF) << 16 | (v4 & 0xFF) << 8);
        }
        int v5 = segment0.data[v + 3] & 0xFF | ((segment0.data[v + 1] & 0xFF) << 16 | (segment0.data[v] & 0xFF) << 24 | (segment0.data[v + 2] & 0xFF) << 8);
        buffer0.setSize$okio(buffer0.size() - 4L);
        if(v + 4 == v1) {
            buffer0.head = segment0.pop();
            SegmentPool.recycle(segment0);
            return v5;
        }
        segment0.pos = v + 4;
        return v5;
    }

    public static final long commonReadLong(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(buffer0.size() < 8L) {
            throw new EOFException();
        }
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        int v = segment0.pos;
        int v1 = segment0.limit;
        if(((long)(v1 - v)) < 8L) {
            return (((long)buffer0.readInt()) & 0xFFFFFFFFL) << 0x20 | 0xFFFFFFFFL & ((long)buffer0.readInt());
        }
        long v2 = (((long)segment0.data[v + 3]) & 0xFFL) << 0x20 | ((((long)segment0.data[v]) & 0xFFL) << 56 | (((long)segment0.data[v + 1]) & 0xFFL) << 0x30 | (((long)segment0.data[v + 2]) & 0xFFL) << 40) | (((long)segment0.data[v + 4]) & 0xFFL) << 24 | (((long)segment0.data[v + 5]) & 0xFFL) << 16 | (((long)segment0.data[v + 6]) & 0xFFL) << 8 | ((long)segment0.data[v + 7]) & 0xFFL;
        buffer0.setSize$okio(buffer0.size() - 8L);
        if(v + 8 == v1) {
            buffer0.head = segment0.pop();
            SegmentPool.recycle(segment0);
            return v2;
        }
        segment0.pos = v + 8;
        return v2;
    }

    public static final short commonReadShort(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(buffer0.size() < 2L) {
            throw new EOFException();
        }
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        int v = segment0.pos;
        int v1 = segment0.limit;
        if(v1 - v < 2) {
            int v2 = buffer0.readByte();
            return (short)(buffer0.readByte() & 0xFF | (v2 & 0xFF) << 8);
        }
        int v3 = segment0.data[v + 1] & 0xFF | (segment0.data[v] & 0xFF) << 8;
        buffer0.setSize$okio(buffer0.size() - 2L);
        if(v + 2 == v1) {
            buffer0.head = segment0.pop();
            SegmentPool.recycle(segment0);
            return (short)v3;
        }
        segment0.pos = v + 2;
        return (short)v3;
    }

    public static final UnsafeCursor commonReadUnsafe(Buffer buffer0, UnsafeCursor buffer$UnsafeCursor0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "unsafeCursor");
        UnsafeCursor buffer$UnsafeCursor1 = _UtilKt.resolveDefaultParameter(buffer$UnsafeCursor0);
        if(buffer$UnsafeCursor1.buffer != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        buffer$UnsafeCursor1.buffer = buffer0;
        buffer$UnsafeCursor1.readWrite = false;
        return buffer$UnsafeCursor1;
    }

    public static final String commonReadUtf8(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        int v1 = Long.compare(v, 0L);
        if(v1 < 0 || v > 0x7FFFFFFFL) {
            throw new IllegalArgumentException(("byteCount: " + v).toString());
        }
        if(buffer0.size() < v) {
            throw new EOFException();
        }
        if(v1 == 0) {
            return "";
        }
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        if(((long)segment0.pos) + v > ((long)segment0.limit)) {
            return _Utf8Kt.commonToUtf8String$default(buffer0.readByteArray(v), 0, 0, 3, null);
        }
        String s = _Utf8Kt.commonToUtf8String(segment0.data, segment0.pos, segment0.pos + ((int)v));
        segment0.pos += (int)v;
        buffer0.setSize$okio(buffer0.size() - v);
        if(segment0.pos == segment0.limit) {
            buffer0.head = segment0.pop();
            SegmentPool.recycle(segment0);
        }
        return s;
    }

    public static final int commonReadUtf8CodePoint(Buffer buffer0) {
        int v4;
        int v3;
        int v2;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(buffer0.size() == 0L) {
            throw new EOFException();
        }
        int v = buffer0.getByte(0L);
        int v1 = 1;
        if((v & 0x80) == 0) {
            v2 = v & 0x7F;
            v3 = 1;
            v4 = 0;
            goto label_23;
        }
        if((v & 0xE0) == 0xC0) {
            v2 = v & 0x1F;
            v3 = 2;
            v4 = 0x80;
            goto label_23;
        }
        boolean z = false;
        if((v & 0xF0) == 0xE0) {
            z = true;
            v2 = v & 15;
            v3 = 3;
            v4 = 0x800;
        }
        else if((v & 0xF8) == 0xF0) {
            z = true;
            v2 = v & 7;
            v3 = 4;
            v4 = 0x10000;
        }
        if(z) {
        label_23:
            if(buffer0.size() < ((long)v3)) {
                throw new EOFException("size < " + v3 + ": " + buffer0.size() + " (to read code point prefixed 0x" + _UtilKt.toHexString(((byte)v)) + ')');
            }
            while(v1 < v3) {
                int v5 = buffer0.getByte(((long)v1));
                if((v5 & 0xC0) == 0x80) {
                    v2 = v2 << 6 | v5 & 0x3F;
                    ++v1;
                    continue;
                }
                buffer0.skip(((long)v1));
                return 0xFFFD;
            }
            buffer0.skip(((long)v3));
            if(v2 > 0x10FFFF) {
                return 0xFFFD;
            }
            if(0xD800 <= v2 && v2 < 0xE000) {
                return 0xFFFD;
            }
            return v2 >= v4 ? v2 : 0xFFFD;
        }
        buffer0.skip(1L);
        return 0xFFFD;
    }

    public static final String commonReadUtf8Line(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        long v = buffer0.indexOf(10);
        if(v != -1L) {
            return _BufferKt.readUtf8Line(buffer0, v);
        }
        return buffer0.size() == 0L ? null : buffer0.readUtf8(buffer0.size());
    }

    public static final String commonReadUtf8LineStrict(Buffer buffer0, long v) {
        long v1 = 0x7FFFFFFFFFFFFFFFL;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(v < 0L) {
            throw new IllegalArgumentException(("limit < 0: " + v).toString());
        }
        if(v != 0x7FFFFFFFFFFFFFFFL) {
            v1 = v + 1L;
        }
        long v2 = buffer0.indexOf(10, 0L, v1);
        if(v2 != -1L) {
            return _BufferKt.readUtf8Line(buffer0, v2);
        }
        if(v1 < buffer0.size() && buffer0.getByte(v1 - 1L) == 13 && buffer0.getByte(v1) == 10) {
            return _BufferKt.readUtf8Line(buffer0, v1);
        }
        Buffer buffer1 = new Buffer();
        buffer0.copyTo(buffer1, 0L, Math.min(0x20L, buffer0.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(buffer0.size(), v) + " content=" + buffer1.readByteString().hex() + '…');
    }

    public static final long commonResizeBuffer(UnsafeCursor buffer$UnsafeCursor0, long v) {
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "<this>");
        Buffer buffer0 = buffer$UnsafeCursor0.buffer;
        if(buffer0 == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        if(!buffer$UnsafeCursor0.readWrite) {
            throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
        }
        long v1 = buffer0.size();
        if(Long.compare(v, v1) <= 0) {
            if(v < 0L) {
                throw new IllegalArgumentException(("newSize < 0: " + v).toString());
            }
            long v2 = v1 - v;
            while(v2 > 0L) {
                Segment segment0 = buffer0.head;
                Intrinsics.checkNotNull(segment0);
                Segment segment1 = segment0.prev;
                Intrinsics.checkNotNull(segment1);
                long v3 = (long)(segment1.limit - segment1.pos);
                if(v3 <= v2) {
                    buffer0.head = segment1.pop();
                    SegmentPool.recycle(segment1);
                    v2 -= v3;
                }
                else {
                    segment1.limit -= (int)v2;
                    if(true) {
                        break;
                    }
                }
            }
            buffer$UnsafeCursor0.setSegment$okio(null);
            buffer$UnsafeCursor0.offset = v;
            buffer$UnsafeCursor0.data = null;
            buffer$UnsafeCursor0.start = -1;
            buffer$UnsafeCursor0.end = -1;
        }
        else {
            long v4 = v - v1;
            boolean z = true;
            while(v4 > 0L) {
                Segment segment2 = buffer0.writableSegment$okio(1);
                int v5 = (int)Math.min(v4, 0x2000 - segment2.limit);
                segment2.limit += v5;
                v4 -= (long)v5;
                if(z) {
                    buffer$UnsafeCursor0.setSegment$okio(segment2);
                    buffer$UnsafeCursor0.offset = v1;
                    buffer$UnsafeCursor0.data = segment2.data;
                    buffer$UnsafeCursor0.start = segment2.limit - v5;
                    buffer$UnsafeCursor0.end = segment2.limit;
                    z = false;
                }
            }
        }
        buffer0.setSize$okio(v);
        return v1;
    }

    public static final int commonSeek(UnsafeCursor buffer$UnsafeCursor0, long v) {
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "<this>");
        Buffer buffer0 = buffer$UnsafeCursor0.buffer;
        if(buffer0 == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        int v1 = Long.compare(v, -1L);
        if(v1 < 0 || v > buffer0.size()) {
            throw new ArrayIndexOutOfBoundsException("offset=" + v + " > size=" + buffer0.size());
        }
        if(v1 != 0 && v != buffer0.size()) {
            long v2 = buffer0.size();
            Segment segment0 = buffer0.head;
            Segment segment1 = buffer0.head;
            long v3 = 0L;
            if(buffer$UnsafeCursor0.getSegment$okio() != null) {
                long v4 = buffer$UnsafeCursor0.offset;
                int v5 = buffer$UnsafeCursor0.start;
                Segment segment2 = buffer$UnsafeCursor0.getSegment$okio();
                Intrinsics.checkNotNull(segment2);
                long v6 = v4 - ((long)(v5 - segment2.pos));
                if(v6 > v) {
                    segment1 = buffer$UnsafeCursor0.getSegment$okio();
                    v2 = v6;
                }
                else {
                    segment0 = buffer$UnsafeCursor0.getSegment$okio();
                    v3 = v6;
                }
            }
            if(v2 - v > v - v3) {
                while(true) {
                    Intrinsics.checkNotNull(segment0);
                    if(v < ((long)(segment0.limit - segment0.pos)) + v3) {
                        break;
                    }
                    v3 += (long)(segment0.limit - segment0.pos);
                    segment0 = segment0.next;
                }
            }
            else {
                while(v2 > v) {
                    Intrinsics.checkNotNull(segment1);
                    segment1 = segment1.prev;
                    Intrinsics.checkNotNull(segment1);
                    v2 -= (long)(segment1.limit - segment1.pos);
                }
                v3 = v2;
                segment0 = segment1;
            }
            if(buffer$UnsafeCursor0.readWrite) {
                Intrinsics.checkNotNull(segment0);
                if(segment0.shared) {
                    Segment segment3 = segment0.unsharedCopy();
                    if(buffer0.head == segment0) {
                        buffer0.head = segment3;
                    }
                    segment0 = segment0.push(segment3);
                    Segment segment4 = segment0.prev;
                    Intrinsics.checkNotNull(segment4);
                    segment4.pop();
                }
            }
            buffer$UnsafeCursor0.setSegment$okio(segment0);
            buffer$UnsafeCursor0.offset = v;
            Intrinsics.checkNotNull(segment0);
            buffer$UnsafeCursor0.data = segment0.data;
            buffer$UnsafeCursor0.start = segment0.pos + ((int)(v - v3));
            buffer$UnsafeCursor0.end = segment0.limit;
            return buffer$UnsafeCursor0.end - buffer$UnsafeCursor0.start;
        }
        buffer$UnsafeCursor0.setSegment$okio(null);
        buffer$UnsafeCursor0.offset = v;
        buffer$UnsafeCursor0.data = null;
        buffer$UnsafeCursor0.start = -1;
        buffer$UnsafeCursor0.end = -1;
        return -1;
    }

    public static final int commonSelect(Buffer buffer0, Options options0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(options0, "options");
        int v = _BufferKt.selectPrefix$default(buffer0, options0, false, 2, null);
        if(v == -1) {
            return -1;
        }
        buffer0.skip(((long)options0.getByteStrings$okio()[v].size()));
        return v;
    }

    public static final void commonSkip(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        while(v > 0L) {
            Segment segment0 = buffer0.head;
            if(segment0 == null) {
                throw new EOFException();
            }
            int v1 = (int)Math.min(v, segment0.limit - segment0.pos);
            buffer0.setSize$okio(buffer0.size() - ((long)v1));
            v -= (long)v1;
            segment0.pos += v1;
            if(segment0.pos == segment0.limit) {
                buffer0.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
        }
    }

    public static final ByteString commonSnapshot(Buffer buffer0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(buffer0.size() > 0x7FFFFFFFL) {
            throw new IllegalStateException(("size > Int.MAX_VALUE: " + buffer0.size()).toString());
        }
        return buffer0.snapshot(((int)buffer0.size()));
    }

    public static final ByteString commonSnapshot(Buffer buffer0, int v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(v == 0) {
            return ByteString.EMPTY;
        }
        _UtilKt.checkOffsetAndCount(buffer0.size(), 0L, v);
        Segment segment0 = buffer0.head;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v) {
            Intrinsics.checkNotNull(segment0);
            if(segment0.limit == segment0.pos) {
                throw new AssertionError("s.limit == s.pos");
            }
            v2 += segment0.limit - segment0.pos;
            ++v3;
            segment0 = segment0.next;
        }
        byte[][] arr2_b = new byte[v3][];
        int[] arr_v = new int[v3 * 2];
        Segment segment1 = buffer0.head;
        int v4 = 0;
        while(v1 < v) {
            Intrinsics.checkNotNull(segment1);
            arr2_b[v4] = segment1.data;
            v1 += segment1.limit - segment1.pos;
            arr_v[v4] = Math.min(v1, v);
            arr_v[arr2_b.length + v4] = segment1.pos;
            segment1.shared = true;
            ++v4;
            segment1 = segment1.next;
        }
        return new SegmentedByteString(arr2_b, arr_v);
    }

    public static final Segment commonWritableSegment(Buffer buffer0, int v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(v < 1 || v > 0x2000) {
            throw new IllegalArgumentException("unexpected capacity");
        }
        if(buffer0.head == null) {
            Segment segment0 = SegmentPool.take();
            buffer0.head = segment0;
            segment0.prev = segment0;
            segment0.next = segment0;
            return segment0;
        }
        Segment segment1 = buffer0.head;
        Intrinsics.checkNotNull(segment1);
        Segment segment2 = segment1.prev;
        Intrinsics.checkNotNull(segment2);
        return segment2.limit + v > 0x2000 || !segment2.owner ? segment2.push(SegmentPool.take()) : segment2;
    }

    public static final Buffer commonWrite(Buffer buffer0, ByteString byteString0, int v, int v1) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "byteString");
        byteString0.write$okio(buffer0, v, v1);
        return buffer0;
    }

    public static final Buffer commonWrite(Buffer buffer0, Source source0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(source0, "source");
        while(v > 0L) {
            long v1 = source0.read(buffer0, v);
            if(v1 == -1L) {
                throw new EOFException();
            }
            v -= v1;
        }
        return buffer0;
    }

    public static final Buffer commonWrite(Buffer buffer0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "source");
        return buffer0.write(arr_b, 0, arr_b.length);
    }

    public static final Buffer commonWrite(Buffer buffer0, byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "source");
        _UtilKt.checkOffsetAndCount(arr_b.length, v, v1);
        int v2 = v1 + v;
        while(v < v2) {
            Segment segment0 = buffer0.writableSegment$okio(1);
            int v3 = Math.min(v2 - v, 0x2000 - segment0.limit);
            int v4 = v + v3;
            ArraysKt.copyInto(arr_b, segment0.data, segment0.limit, v, v4);
            segment0.limit += v3;
            v = v4;
        }
        buffer0.setSize$okio(buffer0.size() + ((long)v1));
        return buffer0;
    }

    public static final void commonWrite(Buffer buffer0, Buffer buffer1, long v) {
        Segment segment3;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(buffer1, "source");
        if(buffer1 == buffer0) {
            throw new IllegalArgumentException("source == this");
        }
        _UtilKt.checkOffsetAndCount(buffer1.size(), 0L, v);
        while(v > 0L) {
            Segment segment0 = buffer1.head;
            Intrinsics.checkNotNull(segment0);
            int v1 = segment0.limit;
            Segment segment1 = buffer1.head;
            Intrinsics.checkNotNull(segment1);
            if(v < ((long)(v1 - segment1.pos))) {
                if(buffer0.head == null) {
                    segment3 = null;
                }
                else {
                    Segment segment2 = buffer0.head;
                    Intrinsics.checkNotNull(segment2);
                    segment3 = segment2.prev;
                }
                if(segment3 != null && segment3.owner && ((long)segment3.limit) + v - ((long)(segment3.shared ? 0 : segment3.pos)) <= 0x2000L) {
                    Segment segment4 = buffer1.head;
                    Intrinsics.checkNotNull(segment4);
                    segment4.writeTo(segment3, ((int)v));
                    buffer1.setSize$okio(buffer1.size() - v);
                    buffer0.setSize$okio(buffer0.size() + v);
                    return;
                }
                Segment segment5 = buffer1.head;
                Intrinsics.checkNotNull(segment5);
                buffer1.head = segment5.split(((int)v));
            }
            Segment segment6 = buffer1.head;
            Intrinsics.checkNotNull(segment6);
            long v2 = (long)(segment6.limit - segment6.pos);
            buffer1.head = segment6.pop();
            if(buffer0.head == null) {
                buffer0.head = segment6;
                segment6.prev = segment6;
                segment6.next = segment6.prev;
            }
            else {
                Segment segment7 = buffer0.head;
                Intrinsics.checkNotNull(segment7);
                Segment segment8 = segment7.prev;
                Intrinsics.checkNotNull(segment8);
                segment8.push(segment6).compact();
            }
            buffer1.setSize$okio(buffer1.size() - v2);
            buffer0.setSize$okio(buffer0.size() + v2);
            v -= v2;
        }
    }

    public static Buffer commonWrite$default(Buffer buffer0, ByteString byteString0, int v, int v1, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = byteString0.size();
        }
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "byteString");
        byteString0.write$okio(buffer0, v, v1);
        return buffer0;
    }

    public static final long commonWriteAll(Buffer buffer0, Source source0) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(source0, "source");
        long v = 0L;
        long v1;
        while((v1 = source0.read(buffer0, 0x2000L)) != -1L) {
            v += v1;
        }
        return v;
    }

    public static final Buffer commonWriteByte(Buffer buffer0, int v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Segment segment0 = buffer0.writableSegment$okio(1);
        int v1 = segment0.limit;
        segment0.limit = v1 + 1;
        segment0.data[v1] = (byte)v;
        buffer0.setSize$okio(buffer0.size() + 1L);
        return buffer0;
    }

    public static final Buffer commonWriteDecimalLong(Buffer buffer0, long v) {
        boolean z;
        int v1 = 1;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        int v2 = Long.compare(v, 0L);
        if(v2 == 0) {
            return buffer0.writeByte(0x30);
        }
        if(v2 < 0) {
            v = -v;
            if(v < 0L) {
                return buffer0.writeUtf8("-9223372036854775808");
            }
            z = true;
        }
        else {
            z = false;
        }
        if(v >= 100000000L) {
            if(v >= 1000000000000L) {
                if(v >= 1000000000000000L) {
                    if(v >= 100000000000000000L) {
                        v1 = v >= 1000000000000000000L ? 19 : 18;
                    }
                    else if(v < 10000000000000000L) {
                        v1 = 16;
                    }
                    else {
                        v1 = 17;
                    }
                }
                else if(v < 10000000000000L) {
                    v1 = 13;
                }
                else if(v < 100000000000000L) {
                    v1 = 14;
                }
                else {
                    v1 = 15;
                }
            }
            else if(v >= 10000000000L) {
                v1 = v < 100000000000L ? 11 : 12;
            }
            else if(v < 1000000000L) {
                v1 = 9;
            }
            else {
                v1 = 10;
            }
        }
        else if(v >= 10000L) {
            if(v >= 1000000L) {
                v1 = v < 10000000L ? 7 : 8;
            }
            else if(v < 100000L) {
                v1 = 5;
            }
            else {
                v1 = 6;
            }
        }
        else if(v >= 100L) {
            v1 = v < 1000L ? 3 : 4;
        }
        else if(v >= 10L) {
            v1 = 2;
        }
        if(z) {
            ++v1;
        }
        Segment segment0 = buffer0.writableSegment$okio(v1);
        byte[] arr_b = segment0.data;
        int v3 = segment0.limit + v1;
        while(v != 0L) {
            --v3;
            arr_b[v3] = new byte[]{0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102}[((int)(v % 10L))];
            v /= 10L;
        }
        if(z) {
            arr_b[v3 - 1] = 45;
        }
        segment0.limit += v1;
        buffer0.setSize$okio(buffer0.size() + ((long)v1));
        return buffer0;
    }

    public static final Buffer commonWriteHexadecimalUnsignedLong(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(v == 0L) {
            return buffer0.writeByte(0x30);
        }
        long v1 = v >>> 1 | v;
        long v2 = v1 | v1 >>> 2;
        long v3 = v2 | v2 >>> 4;
        long v4 = v3 | v3 >>> 8;
        long v5 = v4 | v4 >>> 16;
        long v6 = v5 | v5 >>> 0x20;
        long v7 = v6 - (v6 >>> 1 & 0x5555555555555555L);
        long v8 = (v7 >>> 2 & 0x3333333333333333L) + (v7 & 0x3333333333333333L);
        long v9 = (v8 >>> 4) + v8 & 0xF0F0F0F0F0F0F0FL;
        long v10 = v9 + (v9 >>> 8);
        long v11 = v10 + (v10 >>> 16);
        int v12 = (int)(((v11 & 0x3FL) + (v11 >>> 0x20 & 0x3FL) + 3L) / 4L);
        Segment segment0 = buffer0.writableSegment$okio(v12);
        byte[] arr_b = segment0.data;
        int v13 = segment0.limit + v12 - 1;
        int v14 = segment0.limit;
        while(v13 >= v14) {
            arr_b[v13] = new byte[]{0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102}[((int)(15L & v))];
            v >>>= 4;
            --v13;
        }
        segment0.limit += v12;
        buffer0.setSize$okio(buffer0.size() + ((long)v12));
        return buffer0;
    }

    public static final Buffer commonWriteInt(Buffer buffer0, int v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Segment segment0 = buffer0.writableSegment$okio(4);
        int v1 = segment0.limit;
        segment0.data[v1] = (byte)(v >>> 24 & 0xFF);
        segment0.data[v1 + 1] = (byte)(v >>> 16 & 0xFF);
        segment0.data[v1 + 2] = (byte)(v >>> 8 & 0xFF);
        segment0.data[v1 + 3] = (byte)(v & 0xFF);
        segment0.limit = v1 + 4;
        buffer0.setSize$okio(buffer0.size() + 4L);
        return buffer0;
    }

    public static final Buffer commonWriteLong(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Segment segment0 = buffer0.writableSegment$okio(8);
        int v1 = segment0.limit;
        segment0.data[v1] = (byte)(((int)(v >>> 56 & 0xFFL)));
        segment0.data[v1 + 1] = (byte)(((int)(v >>> 0x30 & 0xFFL)));
        segment0.data[v1 + 2] = (byte)(((int)(v >>> 40 & 0xFFL)));
        segment0.data[v1 + 3] = (byte)(((int)(v >>> 0x20 & 0xFFL)));
        segment0.data[v1 + 4] = (byte)(((int)(v >>> 24 & 0xFFL)));
        segment0.data[v1 + 5] = (byte)(((int)(v >>> 16 & 0xFFL)));
        segment0.data[v1 + 6] = (byte)(((int)(v >>> 8 & 0xFFL)));
        segment0.data[v1 + 7] = (byte)(((int)(v & 0xFFL)));
        segment0.limit = v1 + 8;
        buffer0.setSize$okio(buffer0.size() + 8L);
        return buffer0;
    }

    public static final Buffer commonWriteShort(Buffer buffer0, int v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Segment segment0 = buffer0.writableSegment$okio(2);
        int v1 = segment0.limit;
        segment0.data[v1] = (byte)(v >>> 8 & 0xFF);
        segment0.data[v1 + 1] = (byte)(v & 0xFF);
        segment0.limit = v1 + 2;
        buffer0.setSize$okio(buffer0.size() + 2L);
        return buffer0;
    }

    public static final Buffer commonWriteUtf8(Buffer buffer0, String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(s, "string");
        if(v < 0) {
            throw new IllegalArgumentException(("beginIndex < 0: " + v).toString());
        }
        if(v1 < v) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + v1 + " < " + v).toString());
        }
        if(v1 > s.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + v1 + " > " + s.length()).toString());
        }
        while(v < v1) {
            int v2 = s.charAt(v);
            if(v2 < 0x80) {
                Segment segment0 = buffer0.writableSegment$okio(1);
                byte[] arr_b = segment0.data;
                int v3 = segment0.limit - v;
                int v4 = Math.min(v1, 0x2000 - v3);
                int v5 = v + 1;
                arr_b[v + v3] = (byte)v2;
                while(true) {
                    v = v5;
                    if(v >= v4) {
                        break;
                    }
                    int v6 = s.charAt(v);
                    if(v6 >= 0x80) {
                        break;
                    }
                    v5 = v + 1;
                    arr_b[v + v3] = (byte)v6;
                }
                int v7 = v3 + v - segment0.limit;
                segment0.limit += v7;
                buffer0.setSize$okio(buffer0.size() + ((long)v7));
            }
            else {
                if(v2 < 0x800) {
                    Segment segment1 = buffer0.writableSegment$okio(2);
                    segment1.data[segment1.limit] = (byte)(v2 >> 6 | 0xC0);
                    segment1.data[segment1.limit + 1] = (byte)(v2 & 0x3F | 0x80);
                    segment1.limit += 2;
                    buffer0.setSize$okio(buffer0.size() + 2L);
                }
                else if(v2 < 0xD800 || v2 > 0xDFFF) {
                    Segment segment3 = buffer0.writableSegment$okio(3);
                    segment3.data[segment3.limit] = (byte)(v2 >> 12 | 0xE0);
                    segment3.data[segment3.limit + 1] = (byte)(v2 >> 6 & 0x3F | 0x80);
                    segment3.data[segment3.limit + 2] = (byte)(v2 & 0x3F | 0x80);
                    segment3.limit += 3;
                    buffer0.setSize$okio(buffer0.size() + 3L);
                }
                else {
                    int v8 = v + 1 >= v1 ? 0 : s.charAt(v + 1);
                    if(v2 > 0xDBFF || 0xDC00 > v8 || v8 >= 0xE000) {
                        buffer0.writeByte(0x3F);
                        ++v;
                    }
                    else {
                        int v9 = ((v2 & 0x3FF) << 10 | v8 & 0x3FF) + 0x10000;
                        Segment segment2 = buffer0.writableSegment$okio(4);
                        segment2.data[segment2.limit] = (byte)(v9 >> 18 | 0xF0);
                        segment2.data[segment2.limit + 1] = (byte)(v9 >> 12 & 0x3F | 0x80);
                        segment2.data[segment2.limit + 2] = (byte)(v9 >> 6 & 0x3F | 0x80);
                        segment2.data[segment2.limit + 3] = (byte)(v9 & 0x3F | 0x80);
                        segment2.limit += 4;
                        buffer0.setSize$okio(buffer0.size() + 4L);
                        v += 2;
                    }
                    continue;
                }
                ++v;
            }
        }
        return buffer0;
    }

    public static final Buffer commonWriteUtf8CodePoint(Buffer buffer0, int v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(v < 0x80) {
            buffer0.writeByte(v);
            return buffer0;
        }
        if(v < 0x800) {
            Segment segment0 = buffer0.writableSegment$okio(2);
            segment0.data[segment0.limit] = (byte)(v >> 6 | 0xC0);
            segment0.data[segment0.limit + 1] = (byte)(v & 0x3F | 0x80);
            segment0.limit += 2;
            buffer0.setSize$okio(buffer0.size() + 2L);
            return buffer0;
        }
        if(0xD800 <= v && v < 0xE000) {
            buffer0.writeByte(0x3F);
            return buffer0;
        }
        if(v < 0x10000) {
            Segment segment1 = buffer0.writableSegment$okio(3);
            segment1.data[segment1.limit] = (byte)(v >> 12 | 0xE0);
            segment1.data[segment1.limit + 1] = (byte)(v >> 6 & 0x3F | 0x80);
            segment1.data[segment1.limit + 2] = (byte)(v & 0x3F | 0x80);
            segment1.limit += 3;
            buffer0.setSize$okio(buffer0.size() + 3L);
            return buffer0;
        }
        if(v > 0x10FFFF) {
            throw new IllegalArgumentException("Unexpected code point: 0x" + _UtilKt.toHexString(v));
        }
        Segment segment2 = buffer0.writableSegment$okio(4);
        segment2.data[segment2.limit] = (byte)(v >> 18 | 0xF0);
        segment2.data[segment2.limit + 1] = (byte)(v >> 12 & 0x3F | 0x80);
        segment2.data[segment2.limit + 2] = (byte)(v >> 6 & 0x3F | 0x80);
        segment2.data[segment2.limit + 3] = (byte)(v & 0x3F | 0x80);
        segment2.limit += 4;
        buffer0.setSize$okio(buffer0.size() + 4L);
        return buffer0;
    }

    public static final byte[] getHEX_DIGIT_BYTES() [...] // 潜在的解密器

    public static void getHEX_DIGIT_BYTES$annotations() {
    }

    public static final boolean rangeEquals(Segment segment0, int v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(segment0, "segment");
        Intrinsics.checkNotNullParameter(arr_b, "bytes");
        int v3 = segment0.limit;
        byte[] arr_b1 = segment0.data;
        while(v1 < v2) {
            if(v == v3) {
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                arr_b1 = segment0.data;
                v = segment0.pos;
                v3 = segment0.limit;
            }
            if(arr_b1[v] != arr_b[v1]) {
                return false;
            }
            ++v;
            ++v1;
        }
        return true;
    }

    public static final String readUtf8Line(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        if(v > 0L && buffer0.getByte(v - 1L) == 13) {
            String s = buffer0.readUtf8(v - 1L);
            buffer0.skip(2L);
            return s;
        }
        String s1 = buffer0.readUtf8(v);
        buffer0.skip(1L);
        return s1;
    }

    public static final Object seek(Buffer buffer0, long v, Function2 function20) {
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "lambda");
        Segment segment0 = buffer0.head;
        if(segment0 == null) {
            return function20.invoke(null, -1L);
        }
        if(buffer0.size() - v < v) {
            long v1;
            for(v1 = buffer0.size(); v1 > v; v1 -= (long)(segment0.limit - segment0.pos)) {
                segment0 = segment0.prev;
                Intrinsics.checkNotNull(segment0);
            }
            return function20.invoke(segment0, v1);
        }
        long v2 = 0L;
        long v3;
        while((v3 = ((long)(segment0.limit - segment0.pos)) + v2) <= v) {
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v2 = v3;
        }
        return function20.invoke(segment0, v2);
    }

    public static final int selectPrefix(Buffer buffer0, Options options0, boolean z) {
        int v11;
        int v10;
        Segment segment3;
        int v9;
        int v8;
        Intrinsics.checkNotNullParameter(buffer0, "<this>");
        Intrinsics.checkNotNullParameter(options0, "options");
        Segment segment0 = buffer0.head;
        if(segment0 == null) {
            return z ? -2 : -1;
        }
        byte[] arr_b = segment0.data;
        int v = segment0.pos;
        int v1 = segment0.limit;
        int[] arr_v = options0.getTrie$okio();
        Segment segment1 = segment0;
        int v2 = 0;
        int v3 = -1;
    alab1:
        while(true) {
            int v4 = arr_v[v2];
            int v5 = v2 + 2;
            int v6 = arr_v[v2 + 1];
            if(v6 != -1) {
                v3 = v6;
            }
            if(segment1 == null) {
                break;
            }
            if(v4 < 0) {
                int v7 = v5 - v4;
                while((arr_b[v] & 0xFF) == arr_v[v5]) {
                    boolean z1 = v5 + 1 == v7;
                    if(v + 1 == v1) {
                        Intrinsics.checkNotNull(segment1);
                        Segment segment2 = segment1.next;
                        Intrinsics.checkNotNull(segment2);
                        v8 = segment2.pos;
                        byte[] arr_b1 = segment2.data;
                        v9 = segment2.limit;
                        if(segment2 == segment0) {
                            if(!z1) {
                                break alab1;
                            }
                            arr_b = arr_b1;
                            segment3 = null;
                        }
                        else {
                            segment3 = segment2;
                            arr_b = arr_b1;
                        }
                    }
                    else {
                        segment3 = segment1;
                        v9 = v1;
                        v8 = v + 1;
                    }
                    if(z1) {
                        v10 = arr_v[v5 + 1];
                        v11 = v8;
                        v1 = v9;
                        segment1 = segment3;
                        goto label_69;
                    }
                    v = v8;
                    v1 = v9;
                    segment1 = segment3;
                    ++v5;
                }
                return v3;
            }
            else {
                v11 = v + 1;
                int v12 = arr_b[v] & 0xFF;
                int v13 = v5 + v4;
                while(true) {
                    if(v5 == v13) {
                        return v3;
                    }
                    if(v12 == arr_v[v5]) {
                        break;
                    }
                    ++v5;
                }
                v10 = arr_v[v5 + v4];
                if(v11 == v1) {
                    segment1 = segment1.next;
                    Intrinsics.checkNotNull(segment1);
                    v11 = segment1.pos;
                    arr_b = segment1.data;
                    v1 = segment1.limit;
                    if(segment1 == segment0) {
                        segment1 = null;
                    }
                }
            }
        label_69:
            if(v10 >= 0) {
                return v10;
            }
            v2 = -v10;
            v = v11;
        }
        return z ? -2 : v3;
    }

    public static int selectPrefix$default(Buffer buffer0, Options options0, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = false;
        }
        return _BufferKt.selectPrefix(buffer0, options0, z);
    }
}

