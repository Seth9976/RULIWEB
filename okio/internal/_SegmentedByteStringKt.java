package okio.internal;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.Segment;
import okio.SegmentedByteString;
import okio._UtilKt;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A$\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0000\u001A-\u0010\u0006\u001A\u00020\u0007*\u00020\b2\u0006\u0010\t\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u00012\u0006\u0010\r\u001A\u00020\u0001H\u0080\b\u001A\u0017\u0010\u000E\u001A\u00020\u000F*\u00020\b2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011H\u0080\b\u001A\r\u0010\u0012\u001A\u00020\u0001*\u00020\bH\u0080\b\u001A\r\u0010\u0013\u001A\u00020\u0001*\u00020\bH\u0080\b\u001A\u0015\u0010\u0014\u001A\u00020\u0015*\u00020\b2\u0006\u0010\u0016\u001A\u00020\u0001H\u0080\b\u001A-\u0010\u0017\u001A\u00020\u000F*\u00020\b2\u0006\u0010\t\u001A\u00020\u00012\u0006\u0010\u0010\u001A\u00020\u000B2\u0006\u0010\u0018\u001A\u00020\u00012\u0006\u0010\r\u001A\u00020\u0001H\u0080\b\u001A-\u0010\u0017\u001A\u00020\u000F*\u00020\b2\u0006\u0010\t\u001A\u00020\u00012\u0006\u0010\u0010\u001A\u00020\u00192\u0006\u0010\u0018\u001A\u00020\u00012\u0006\u0010\r\u001A\u00020\u0001H\u0080\b\u001A\u001D\u0010\u001A\u001A\u00020\u0019*\u00020\b2\u0006\u0010\u001B\u001A\u00020\u00012\u0006\u0010\u001C\u001A\u00020\u0001H\u0080\b\u001A\r\u0010\u001D\u001A\u00020\u000B*\u00020\bH\u0080\b\u001A%\u0010\u001E\u001A\u00020\u0007*\u00020\b2\u0006\u0010\u001F\u001A\u00020 2\u0006\u0010\t\u001A\u00020\u00012\u0006\u0010\r\u001A\u00020\u0001H\u0080\b\u001A]\u0010!\u001A\u00020\u0007*\u00020\b2K\u0010\"\u001AG\u0012\u0013\u0012\u00110\u000B\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u0001\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0001\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070#H\u0080\b\u00F8\u0001\u0000\u001Aj\u0010!\u001A\u00020\u0007*\u00020\b2\u0006\u0010\u001B\u001A\u00020\u00012\u0006\u0010\u001C\u001A\u00020\u00012K\u0010\"\u001AG\u0012\u0013\u0012\u00110\u000B\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u0001\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0001\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070#H\u0082\b\u001A\u0014\u0010\'\u001A\u00020\u0001*\u00020\b2\u0006\u0010\u0016\u001A\u00020\u0001H\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006("}, d2 = {"binarySearch", "", "", "value", "fromIndex", "toIndex", "commonCopyInto", "", "Lokio/SegmentedByteString;", "offset", "target", "", "targetOffset", "byteCount", "commonEquals", "", "other", "", "commonGetSize", "commonHashCode", "commonInternalGet", "", "pos", "commonRangeEquals", "otherOffset", "Lokio/ByteString;", "commonSubstring", "beginIndex", "endIndex", "commonToByteArray", "commonWrite", "buffer", "Lokio/Buffer;", "forEachSegment", "action", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "data", "segment", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _SegmentedByteStringKt {
    public static final int binarySearch(int[] arr_v, int v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_v, "<this>");
        int v3 = v2 - 1;
        while(v1 <= v3) {
            int v4 = v1 + v3 >>> 1;
            int v5 = arr_v[v4];
            if(v5 < v) {
                v1 = v4 + 1;
                continue;
            }
            if(v5 > v) {
                v3 = v4 - 1;
                continue;
            }
            return v4;
        }
        return -v1 - 1;
    }

    public static final void commonCopyInto(SegmentedByteString segmentedByteString0, int v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "target");
        _UtilKt.checkOffsetAndCount(segmentedByteString0.size(), v, v2);
        _UtilKt.checkOffsetAndCount(arr_b.length, v1, v2);
        int v3 = v2 + v;
        for(int v4 = _SegmentedByteStringKt.segment(segmentedByteString0, v); v < v3; ++v4) {
            int v5 = v4 == 0 ? 0 : segmentedByteString0.getDirectory$okio()[v4 - 1];
            int v6 = Math.min(v3, segmentedByteString0.getDirectory$okio()[v4]) - v;
            int v7 = segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length + v4] + (v - v5);
            ArraysKt.copyInto(segmentedByteString0.getSegments$okio()[v4], arr_b, v1, v7, v7 + v6);
            v1 += v6;
            v += v6;
        }
    }

    public static final boolean commonEquals(SegmentedByteString segmentedByteString0, Object object0) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        return object0 == segmentedByteString0 ? true : object0 instanceof ByteString && ((ByteString)object0).size() == segmentedByteString0.size() && segmentedByteString0.rangeEquals(0, ((ByteString)object0), 0, segmentedByteString0.size());
    }

    public static final int commonGetSize(SegmentedByteString segmentedByteString0) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        return segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length - 1];
    }

    public static final int commonHashCode(SegmentedByteString segmentedByteString0) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        int v = segmentedByteString0.getHashCode$okio();
        if(v != 0) {
            return v;
        }
        int v1 = segmentedByteString0.getSegments$okio().length;
        int v2 = 0;
        int v4 = 1;
        for(int v3 = 0; v2 < v1; v3 = v6) {
            int v5 = segmentedByteString0.getDirectory$okio()[v1 + v2];
            int v6 = segmentedByteString0.getDirectory$okio()[v2];
            byte[] arr_b = segmentedByteString0.getSegments$okio()[v2];
            int v7 = v6 - v3 + v5;
            while(v5 < v7) {
                v4 = v4 * 0x1F + arr_b[v5];
                ++v5;
            }
            ++v2;
        }
        segmentedByteString0.setHashCode$okio(v4);
        return v4;
    }

    public static final byte commonInternalGet(SegmentedByteString segmentedByteString0, int v) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        _UtilKt.checkOffsetAndCount(segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length - 1], v, 1L);
        int v1 = _SegmentedByteStringKt.segment(segmentedByteString0, v);
        return v1 == 0 ? segmentedByteString0.getSegments$okio()[0][v + segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length]] : segmentedByteString0.getSegments$okio()[v1][v - segmentedByteString0.getDirectory$okio()[v1 - 1] + segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length + v1]];
    }

    public static final boolean commonRangeEquals(SegmentedByteString segmentedByteString0, int v, ByteString byteString0, int v1, int v2) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "other");
        if(v >= 0 && v <= segmentedByteString0.size() - v2) {
            int v3 = v2 + v;
            for(int v4 = _SegmentedByteStringKt.segment(segmentedByteString0, v); v < v3; ++v4) {
                int v5 = v4 == 0 ? 0 : segmentedByteString0.getDirectory$okio()[v4 - 1];
                int v6 = Math.min(v3, segmentedByteString0.getDirectory$okio()[v4]) - v;
                if(!byteString0.rangeEquals(v1, segmentedByteString0.getSegments$okio()[v4], segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length + v4] + (v - v5), v6)) {
                    return false;
                }
                v1 += v6;
                v += v6;
            }
            return true;
        }
        return false;
    }

    public static final boolean commonRangeEquals(SegmentedByteString segmentedByteString0, int v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "other");
        if(v >= 0 && v <= segmentedByteString0.size() - v2 && v1 >= 0 && v1 <= arr_b.length - v2) {
            int v3 = v2 + v;
            for(int v4 = _SegmentedByteStringKt.segment(segmentedByteString0, v); v < v3; ++v4) {
                int v5 = v4 == 0 ? 0 : segmentedByteString0.getDirectory$okio()[v4 - 1];
                int v6 = Math.min(v3, segmentedByteString0.getDirectory$okio()[v4]) - v;
                if(!_UtilKt.arrayRangeEquals(segmentedByteString0.getSegments$okio()[v4], segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length + v4] + (v - v5), arr_b, v1, v6)) {
                    return false;
                }
                v1 += v6;
                v += v6;
            }
            return true;
        }
        return false;
    }

    public static final ByteString commonSubstring(SegmentedByteString segmentedByteString0, int v, int v1) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        int v2 = _UtilKt.resolveDefaultParameter(segmentedByteString0, v1);
        if(v < 0) {
            throw new IllegalArgumentException(("beginIndex=" + v + " < 0").toString());
        }
        if(v2 > segmentedByteString0.size()) {
            throw new IllegalArgumentException(("endIndex=" + v2 + " > length(" + segmentedByteString0.size() + ')').toString());
        }
        int v3 = v2 - v;
        if(v3 < 0) {
            throw new IllegalArgumentException(("endIndex=" + v2 + " < beginIndex=" + v).toString());
        }
        if(v == 0 && v2 == segmentedByteString0.size()) {
            return segmentedByteString0;
        }
        if(v == v2) {
            return ByteString.EMPTY;
        }
        int v4 = _SegmentedByteStringKt.segment(segmentedByteString0, v);
        int v5 = _SegmentedByteStringKt.segment(segmentedByteString0, v2 - 1);
        byte[][] arr2_b = (byte[][])ArraysKt.copyOfRange(segmentedByteString0.getSegments$okio(), v4, v5 + 1);
        int[] arr_v = new int[arr2_b.length * 2];
        int v6 = 0;
        if(v4 <= v5) {
            int v7 = v4;
            for(int v8 = 0; true; ++v8) {
                arr_v[v8] = Math.min(segmentedByteString0.getDirectory$okio()[v7] - v, v3);
                arr_v[v8 + arr2_b.length] = segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length + v7];
                if(v7 == v5) {
                    break;
                }
                ++v7;
            }
        }
        if(v4 != 0) {
            v6 = segmentedByteString0.getDirectory$okio()[v4 - 1];
        }
        arr_v[arr2_b.length] += v - v6;
        return new SegmentedByteString(arr2_b, arr_v);
    }

    public static final byte[] commonToByteArray(SegmentedByteString segmentedByteString0) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        byte[] arr_b = new byte[segmentedByteString0.size()];
        int v = segmentedByteString0.getSegments$okio().length;
        int v1 = 0;
        int v3 = 0;
        for(int v2 = 0; v1 < v; v2 = v5) {
            int v4 = segmentedByteString0.getDirectory$okio()[v + v1];
            int v5 = segmentedByteString0.getDirectory$okio()[v1];
            int v6 = v5 - v2;
            ArraysKt.copyInto(segmentedByteString0.getSegments$okio()[v1], arr_b, v3, v4, v4 + v6);
            v3 += v6;
            ++v1;
        }
        return arr_b;
    }

    public static final void commonWrite(SegmentedByteString segmentedByteString0, Buffer buffer0, int v, int v1) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        Intrinsics.checkNotNullParameter(buffer0, "buffer");
        int v2 = v + v1;
        for(int v3 = _SegmentedByteStringKt.segment(segmentedByteString0, v); v < v2; ++v3) {
            int v4 = v3 == 0 ? 0 : segmentedByteString0.getDirectory$okio()[v3 - 1];
            int v5 = Math.min(v2, segmentedByteString0.getDirectory$okio()[v3]) - v;
            int v6 = segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length + v3] + (v - v4);
            Segment segment0 = new Segment(segmentedByteString0.getSegments$okio()[v3], v6, v6 + v5, true, false);
            if(buffer0.head == null) {
                segment0.prev = segment0;
                segment0.next = segment0.prev;
                buffer0.head = segment0.next;
            }
            else {
                Segment segment1 = buffer0.head;
                Intrinsics.checkNotNull(segment1);
                Segment segment2 = segment1.prev;
                Intrinsics.checkNotNull(segment2);
                segment2.push(segment0);
            }
            v += v5;
        }
        buffer0.setSize$okio(buffer0.size() + ((long)v1));
    }

    private static final void forEachSegment(SegmentedByteString segmentedByteString0, int v, int v1, Function3 function30) {
        for(int v2 = _SegmentedByteStringKt.segment(segmentedByteString0, v); v < v1; ++v2) {
            int v3 = v2 == 0 ? 0 : segmentedByteString0.getDirectory$okio()[v2 - 1];
            int v4 = Math.min(v1, segmentedByteString0.getDirectory$okio()[v2]) - v;
            function30.invoke(segmentedByteString0.getSegments$okio()[v2], ((int)(segmentedByteString0.getDirectory$okio()[segmentedByteString0.getSegments$okio().length + v2] + (v - v3))), v4);
            v += v4;
        }
    }

    public static final void forEachSegment(SegmentedByteString segmentedByteString0, Function3 function30) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        Intrinsics.checkNotNullParameter(function30, "action");
        int v = segmentedByteString0.getSegments$okio().length;
        int v1 = 0;
        for(int v2 = 0; v1 < v; v2 = v4) {
            int v3 = segmentedByteString0.getDirectory$okio()[v + v1];
            int v4 = segmentedByteString0.getDirectory$okio()[v1];
            function30.invoke(segmentedByteString0.getSegments$okio()[v1], v3, ((int)(v4 - v2)));
            ++v1;
        }
    }

    public static final int segment(SegmentedByteString segmentedByteString0, int v) {
        Intrinsics.checkNotNullParameter(segmentedByteString0, "<this>");
        int v1 = _SegmentedByteStringKt.binarySearch(segmentedByteString0.getDirectory$okio(), v + 1, 0, segmentedByteString0.getSegments$okio().length);
        return v1 < 0 ? ~v1 : v1;
    }
}

