package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.PeekSource;
import okio.RealBufferedSource;
import okio.Sink;
import okio.Timeout;
import okio._UtilKt;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0080\b\u001A\r\u0010\u0003\u001A\u00020\u0004*\u00020\u0002H\u0080\b\u001A%\u0010\u0005\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u0006H\u0080\b\u001A\u001D\u0010\u0005\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\t\u001A\u00020\u0006H\u0080\b\u001A\u001D\u0010\r\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u000E\u001A\u00020\f2\u0006\u0010\t\u001A\u00020\u0006H\u0080\b\u001A\r\u0010\u000F\u001A\u00020\u0010*\u00020\u0002H\u0080\b\u001A-\u0010\u0011\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u0012\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0014H\u0080\b\u001A%\u0010\u0016\u001A\u00020\u0014*\u00020\u00022\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0012\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0014H\u0080\b\u001A\u001D\u0010\u0016\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0017\u001A\u00020\u00192\u0006\u0010\u0015\u001A\u00020\u0006H\u0080\b\u001A\u0015\u0010\u001A\u001A\u00020\u0006*\u00020\u00022\u0006\u0010\u0017\u001A\u00020\u001BH\u0080\b\u001A\r\u0010\u001C\u001A\u00020\b*\u00020\u0002H\u0080\b\u001A\r\u0010\u001D\u001A\u00020\u0018*\u00020\u0002H\u0080\b\u001A\u0015\u0010\u001D\u001A\u00020\u0018*\u00020\u00022\u0006\u0010\u0015\u001A\u00020\u0006H\u0080\b\u001A\r\u0010\u001E\u001A\u00020\f*\u00020\u0002H\u0080\b\u001A\u0015\u0010\u001E\u001A\u00020\f*\u00020\u00022\u0006\u0010\u0015\u001A\u00020\u0006H\u0080\b\u001A\r\u0010\u001F\u001A\u00020\u0006*\u00020\u0002H\u0080\b\u001A\u0015\u0010 \u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001A\u00020\u0018H\u0080\b\u001A\u001D\u0010 \u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001A\u00020\u00192\u0006\u0010\u0015\u001A\u00020\u0006H\u0080\b\u001A\r\u0010!\u001A\u00020\u0006*\u00020\u0002H\u0080\b\u001A\r\u0010\"\u001A\u00020\u0014*\u00020\u0002H\u0080\b\u001A\r\u0010#\u001A\u00020\u0014*\u00020\u0002H\u0080\b\u001A\r\u0010$\u001A\u00020\u0006*\u00020\u0002H\u0080\b\u001A\r\u0010%\u001A\u00020\u0006*\u00020\u0002H\u0080\b\u001A\r\u0010&\u001A\u00020\'*\u00020\u0002H\u0080\b\u001A\r\u0010(\u001A\u00020\'*\u00020\u0002H\u0080\b\u001A\r\u0010)\u001A\u00020**\u00020\u0002H\u0080\b\u001A\u0015\u0010)\u001A\u00020**\u00020\u00022\u0006\u0010\u0015\u001A\u00020\u0006H\u0080\b\u001A\r\u0010+\u001A\u00020\u0014*\u00020\u0002H\u0080\b\u001A\u000F\u0010,\u001A\u0004\u0018\u00010**\u00020\u0002H\u0080\b\u001A\u0015\u0010-\u001A\u00020**\u00020\u00022\u0006\u0010.\u001A\u00020\u0006H\u0080\b\u001A\u0015\u0010/\u001A\u00020\u0004*\u00020\u00022\u0006\u0010\u0015\u001A\u00020\u0006H\u0080\b\u001A\u0015\u00100\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0015\u001A\u00020\u0006H\u0080\b\u001A\u0015\u00101\u001A\u00020\u0014*\u00020\u00022\u0006\u00102\u001A\u000203H\u0080\b\u001A\u0015\u00104\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0015\u001A\u00020\u0006H\u0080\b\u001A\r\u00105\u001A\u000206*\u00020\u0002H\u0080\b\u001A\r\u00107\u001A\u00020**\u00020\u0002H\u0080\b\u00A8\u00068"}, d2 = {"commonClose", "", "Lokio/RealBufferedSource;", "commonExhausted", "", "commonIndexOf", "", "b", "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonPeek", "Lokio/BufferedSource;", "commonRangeEquals", "offset", "bytesOffset", "", "byteCount", "commonRead", "sink", "", "Lokio/Buffer;", "commonReadAll", "Lokio/Sink;", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadIntLe", "commonReadLong", "commonReadLongLe", "commonReadShort", "", "commonReadShortLe", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", "limit", "commonRequest", "commonRequire", "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonTimeout", "Lokio/Timeout;", "commonToString", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _RealBufferedSourceKt {
    public static final void commonClose(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        if(realBufferedSource0.closed) {
            return;
        }
        realBufferedSource0.closed = true;
        realBufferedSource0.source.close();
        realBufferedSource0.bufferField.clear();
    }

    public static final boolean commonExhausted(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        if(realBufferedSource0.closed) {
            throw new IllegalStateException("closed");
        }
        return realBufferedSource0.bufferField.exhausted() && realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) == -1L;
    }

    public static final long commonIndexOf(RealBufferedSource realBufferedSource0, byte b, long v, long v1) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        if(realBufferedSource0.closed) {
            throw new IllegalStateException("closed");
        }
        if(0L > v || v > v1) {
            throw new IllegalArgumentException(("fromIndex=" + v + " toIndex=" + v1).toString());
        }
        for(long v2 = v; v2 < v1; v2 = Math.max(v2, v4)) {
            long v3 = realBufferedSource0.bufferField.indexOf(b, v2, v1);
            if(v3 != -1L) {
                return v3;
            }
            long v4 = realBufferedSource0.bufferField.size();
            if(v4 >= v1 || realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) == -1L) {
                break;
            }
        }
        return -1L;
    }

    public static final long commonIndexOf(RealBufferedSource realBufferedSource0, ByteString byteString0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        if(!realBufferedSource0.closed) {
            while(true) {
                long v1 = realBufferedSource0.bufferField.indexOf(byteString0, v);
                if(v1 != -1L) {
                    return v1;
                }
                long v2 = realBufferedSource0.bufferField.size();
                if(realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) == -1L) {
                    return -1L;
                }
                v = Math.max(v, v2 - ((long)byteString0.size()) + 1L);
            }
        }
        throw new IllegalStateException("closed");
    }

    public static final long commonIndexOfElement(RealBufferedSource realBufferedSource0, ByteString byteString0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "targetBytes");
        if(!realBufferedSource0.closed) {
            while(true) {
                long v1 = realBufferedSource0.bufferField.indexOfElement(byteString0, v);
                if(v1 != -1L) {
                    return v1;
                }
                long v2 = realBufferedSource0.bufferField.size();
                if(realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) == -1L) {
                    return -1L;
                }
                v = Math.max(v, v2);
            }
        }
        throw new IllegalStateException("closed");
    }

    public static final BufferedSource commonPeek(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        return Okio.buffer(new PeekSource(realBufferedSource0));
    }

    public static final boolean commonRangeEquals(RealBufferedSource realBufferedSource0, long v, ByteString byteString0, int v1, int v2) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        if(realBufferedSource0.closed) {
            throw new IllegalStateException("closed");
        }
        if(v >= 0L && v1 >= 0 && v2 >= 0 && byteString0.size() - v1 >= v2) {
            for(int v3 = 0; v3 < v2; ++v3) {
                long v4 = ((long)v3) + v;
                if(!realBufferedSource0.request(v4 + 1L)) {
                    return false;
                }
                if(realBufferedSource0.bufferField.getByte(v4) != byteString0.getByte(v1 + v3)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static final int commonRead(RealBufferedSource realBufferedSource0, byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        _UtilKt.checkOffsetAndCount(arr_b.length, v, v1);
        return realBufferedSource0.bufferField.size() != 0L || realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) != -1L ? realBufferedSource0.bufferField.read(arr_b, v, ((int)Math.min(v1, realBufferedSource0.bufferField.size()))) : -1;
    }

    public static final long commonRead(RealBufferedSource realBufferedSource0, Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        if(v < 0L) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(realBufferedSource0.closed) {
            throw new IllegalStateException("closed");
        }
        return realBufferedSource0.bufferField.size() != 0L || realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) != -1L ? realBufferedSource0.bufferField.read(buffer0, Math.min(v, realBufferedSource0.bufferField.size())) : -1L;
    }

    public static final long commonReadAll(RealBufferedSource realBufferedSource0, Sink sink0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(sink0, "sink");
        long v = 0L;
        while(realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) != -1L) {
            long v1 = realBufferedSource0.bufferField.completeSegmentByteCount();
            if(v1 > 0L) {
                v += v1;
                sink0.write(realBufferedSource0.bufferField, v1);
            }
        }
        if(realBufferedSource0.bufferField.size() > 0L) {
            v += realBufferedSource0.bufferField.size();
            sink0.write(realBufferedSource0.bufferField, realBufferedSource0.bufferField.size());
        }
        return v;
    }

    public static final byte commonReadByte(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(1L);
        return realBufferedSource0.bufferField.readByte();
    }

    public static final byte[] commonReadByteArray(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.bufferField.writeAll(realBufferedSource0.source);
        return new byte[0];
    }

    public static final byte[] commonReadByteArray(RealBufferedSource realBufferedSource0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(v);
        return realBufferedSource0.bufferField.readByteArray(v);
    }

    public static final ByteString commonReadByteString(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.bufferField.writeAll(realBufferedSource0.source);
        return realBufferedSource0.bufferField.readByteString();
    }

    public static final ByteString commonReadByteString(RealBufferedSource realBufferedSource0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(v);
        return realBufferedSource0.bufferField.readByteString(v);
    }

    public static final long commonReadDecimalLong(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(1L);
        for(long v = 0L; realBufferedSource0.request(v + 1L); ++v) {
            int v1 = realBufferedSource0.bufferField.getByte(v);
            if(v1 < 0x30 || v1 > 57) {
                int v2 = Long.compare(v, 0L);
                if(v2 != 0 || v1 != 45) {
                    if(v2 != 0) {
                        break;
                    }
                    String s = Integer.toString(v1, CharsKt.checkRadix(CharsKt.checkRadix(16)));
                    Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
                    throw new NumberFormatException("Expected a digit or \'-\' but was 0x" + s);
                }
            }
        }
        return realBufferedSource0.bufferField.readDecimalLong();
    }

    public static final void commonReadFully(RealBufferedSource realBufferedSource0, Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        try {
            realBufferedSource0.require(v);
        }
        catch(EOFException eOFException0) {
            buffer0.writeAll(realBufferedSource0.bufferField);
            throw eOFException0;
        }
        realBufferedSource0.bufferField.readFully(buffer0, v);
    }

    public static final void commonReadFully(RealBufferedSource realBufferedSource0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        try {
            realBufferedSource0.require(((long)arr_b.length));
        }
        catch(EOFException eOFException0) {
            for(int v = 0; realBufferedSource0.bufferField.size() > 0L; v += v1) {
                int v1 = realBufferedSource0.bufferField.read(arr_b, v, ((int)realBufferedSource0.bufferField.size()));
                if(v1 == -1) {
                    throw new AssertionError();
                }
            }
            throw eOFException0;
        }
        realBufferedSource0.bufferField.readFully(arr_b);
    }

    public static final long commonReadHexadecimalUnsignedLong(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(1L);
        int v = 0;
        while(realBufferedSource0.request(((long)(v + 1)))) {
            int v1 = realBufferedSource0.bufferField.getByte(((long)v));
            if(v1 >= 0x30 && v1 <= 57 || v1 >= 97 && v1 <= 102 || v1 >= 65 && v1 <= 70) {
                ++v;
                continue;
            }
            if(v != 0) {
                break;
            }
            String s = Integer.toString(v1, CharsKt.checkRadix(CharsKt.checkRadix(16)));
            Intrinsics.checkNotNullExpressionValue(s, "toString(this, checkRadix(radix))");
            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + s);
        }
        return realBufferedSource0.bufferField.readHexadecimalUnsignedLong();
    }

    public static final int commonReadInt(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(4L);
        return realBufferedSource0.bufferField.readInt();
    }

    public static final int commonReadIntLe(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(4L);
        return realBufferedSource0.bufferField.readIntLe();
    }

    public static final long commonReadLong(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(8L);
        return realBufferedSource0.bufferField.readLong();
    }

    public static final long commonReadLongLe(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(8L);
        return realBufferedSource0.bufferField.readLongLe();
    }

    public static final short commonReadShort(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(2L);
        return realBufferedSource0.bufferField.readShort();
    }

    public static final short commonReadShortLe(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(2L);
        return realBufferedSource0.bufferField.readShortLe();
    }

    public static final String commonReadUtf8(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.bufferField.writeAll(realBufferedSource0.source);
        return "";
    }

    public static final String commonReadUtf8(RealBufferedSource realBufferedSource0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(v);
        return realBufferedSource0.bufferField.readUtf8(v);
    }

    public static final int commonReadUtf8CodePoint(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        realBufferedSource0.require(1L);
        int v = realBufferedSource0.bufferField.getByte(0L);
        if((v & 0xE0) == 0xC0) {
            realBufferedSource0.require(2L);
            return realBufferedSource0.bufferField.readUtf8CodePoint();
        }
        if((v & 0xF0) == 0xE0) {
            realBufferedSource0.require(3L);
            return realBufferedSource0.bufferField.readUtf8CodePoint();
        }
        if((v & 0xF8) == 0xF0) {
            realBufferedSource0.require(4L);
        }
        return realBufferedSource0.bufferField.readUtf8CodePoint();
    }

    public static final String commonReadUtf8Line(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        long v = realBufferedSource0.indexOf(10);
        if(v == -1L) {
            return realBufferedSource0.bufferField.size() == 0L ? null : realBufferedSource0.readUtf8(realBufferedSource0.bufferField.size());
        }
        return _BufferKt.readUtf8Line(realBufferedSource0.bufferField, v);
    }

    public static final String commonReadUtf8LineStrict(RealBufferedSource realBufferedSource0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        if(v < 0L) {
            throw new IllegalArgumentException(("limit < 0: " + v).toString());
        }
        long v1 = v == 0x7FFFFFFFFFFFFFFFL ? 0x7FFFFFFFFFFFFFFFL : v + 1L;
        long v2 = realBufferedSource0.indexOf(10, 0L, v1);
        if(v2 != -1L) {
            return _BufferKt.readUtf8Line(realBufferedSource0.bufferField, v2);
        }
        if(v1 < 0x7FFFFFFFFFFFFFFFL && realBufferedSource0.request(v1) && realBufferedSource0.bufferField.getByte(v1 - 1L) == 13 && realBufferedSource0.request(v1 + 1L) && realBufferedSource0.bufferField.getByte(v1) == 10) {
            return _BufferKt.readUtf8Line(realBufferedSource0.bufferField, v1);
        }
        Buffer buffer0 = new Buffer();
        realBufferedSource0.bufferField.copyTo(buffer0, 0L, Math.min(0x20L, realBufferedSource0.bufferField.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(realBufferedSource0.bufferField.size(), v) + " content=" + buffer0.readByteString().hex() + '…');
    }

    public static final boolean commonRequest(RealBufferedSource realBufferedSource0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        if(v < 0L) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(realBufferedSource0.closed) {
            throw new IllegalStateException("closed");
        }
        while(realBufferedSource0.bufferField.size() < v) {
            if(realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) == -1L) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public static final void commonRequire(RealBufferedSource realBufferedSource0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        if(!realBufferedSource0.request(v)) {
            throw new EOFException();
        }
    }

    public static final int commonSelect(RealBufferedSource realBufferedSource0, Options options0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        Intrinsics.checkNotNullParameter(options0, "options");
        if(realBufferedSource0.closed) {
            throw new IllegalStateException("closed");
        }
    alab1:
        while(true) {
            int v = _BufferKt.selectPrefix(realBufferedSource0.bufferField, options0, true);
            switch(v) {
                case -2: {
                    if(realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) != -1L) {
                        break;
                    }
                    break alab1;
                }
                case -1: {
                    return -1;
                }
                default: {
                    realBufferedSource0.bufferField.skip(((long)options0.getByteStrings$okio()[v].size()));
                    return v;
                }
            }
        }
        return -1;
    }

    public static final void commonSkip(RealBufferedSource realBufferedSource0, long v) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        if(realBufferedSource0.closed) {
            throw new IllegalStateException("closed");
        }
        while(v > 0L) {
            if(realBufferedSource0.bufferField.size() == 0L && realBufferedSource0.source.read(realBufferedSource0.bufferField, 0x2000L) == -1L) {
                throw new EOFException();
            }
            long v1 = Math.min(v, realBufferedSource0.bufferField.size());
            realBufferedSource0.bufferField.skip(v1);
            v -= v1;
        }
    }

    public static final Timeout commonTimeout(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        return realBufferedSource0.source.timeout();
    }

    public static final String commonToString(RealBufferedSource realBufferedSource0) {
        Intrinsics.checkNotNullParameter(realBufferedSource0, "<this>");
        return "buffer(" + realBufferedSource0.source + ')';
    }
}

