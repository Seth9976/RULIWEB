package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0001¢\u0006\u0002\u0010\u0003J \u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0012H\u0002J\b\u0010\u0014\u001A\u00020\u000EH\u0016J\b\u0010\u0015\u001A\u00020\u000EH\u0002J\b\u0010\u0016\u001A\u00020\u000EH\u0002J\u0018\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\u0018H\u0016J\b\u0010\u001C\u001A\u00020\u001DH\u0016J \u0010\u001E\u001A\u00020\u000E2\u0006\u0010\u001F\u001A\u00020\u001A2\u0006\u0010 \u001A\u00020\u00182\u0006\u0010\u001B\u001A\u00020\u0018H\u0002R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lokio/GzipSource;", "Lokio/Source;", "source", "(Lokio/Source;)V", "crc", "Ljava/util/zip/CRC32;", "inflater", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "section", "", "Lokio/RealBufferedSource;", "checkEqual", "", "name", "", "expected", "", "actual", "close", "consumeHeader", "consumeTrailer", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "offset", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class GzipSource implements AutoCloseable, Source {
    private final CRC32 crc;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private byte section;
    private final RealBufferedSource source;

    public GzipSource(Source source0) {
        Intrinsics.checkNotNullParameter(source0, "source");
        super();
        RealBufferedSource realBufferedSource0 = new RealBufferedSource(source0);
        this.source = realBufferedSource0;
        Inflater inflater0 = new Inflater(true);
        this.inflater = inflater0;
        this.inflaterSource = new InflaterSource(realBufferedSource0, inflater0);
        this.crc = new CRC32();
    }

    private final void checkEqual(String s, int v, int v1) {
        if(v1 == v) {
            return;
        }
        String s1 = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{s, v1, v}, 3));
        Intrinsics.checkNotNullExpressionValue(s1, "format(this, *args)");
        throw new IOException(s1);
    }

    @Override  // okio.Source
    public void close() throws IOException {
        this.inflaterSource.close();
    }

    private final void consumeHeader() throws IOException {
        this.source.require(10L);
        int v = this.source.bufferField.getByte(3L);
        boolean z = (v >> 1 & 1) == 1;
        if(z) {
            this.updateCrc(this.source.bufferField, 0L, 10L);
        }
        this.checkEqual("ID1ID2", 8075, this.source.readShort());
        this.source.skip(8L);
        if((v >> 2 & 1) == 1) {
            this.source.require(2L);
            if(z) {
                this.updateCrc(this.source.bufferField, 0L, 2L);
            }
            long v1 = (long)this.source.bufferField.readShortLe();
            this.source.require(v1);
            if(z) {
                this.updateCrc(this.source.bufferField, 0L, v1);
            }
            this.source.skip(v1);
        }
        if((v >> 3 & 1) == 1) {
            long v2 = this.source.indexOf(0);
            if(v2 == -1L) {
                throw new EOFException();
            }
            if(z) {
                this.updateCrc(this.source.bufferField, 0L, v2 + 1L);
            }
            this.source.skip(v2 + 1L);
        }
        if((v >> 4 & 1) == 1) {
            long v3 = this.source.indexOf(0);
            if(v3 == -1L) {
                throw new EOFException();
            }
            if(z) {
                this.updateCrc(this.source.bufferField, 0L, v3 + 1L);
            }
            this.source.skip(v3 + 1L);
        }
        if(z) {
            this.checkEqual("FHCRC", this.source.readShortLe(), ((int)(((short)(((int)this.crc.getValue()))))));
            this.crc.reset();
        }
    }

    private final void consumeTrailer() throws IOException {
        this.checkEqual("CRC", this.source.readIntLe(), ((int)this.crc.getValue()));
        this.checkEqual("ISIZE", this.source.readIntLe(), ((int)this.inflater.getBytesWritten()));
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        int v1 = Long.compare(v, 0L);
        if(v1 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(v1 == 0) {
            return 0L;
        }
        if(this.section == 0) {
            this.consumeHeader();
            this.section = 1;
        }
        if(this.section == 1) {
            long v2 = buffer0.size();
            long v3 = this.inflaterSource.read(buffer0, v);
            if(v3 != -1L) {
                this.updateCrc(buffer0, v2, v3);
                return v3;
            }
            this.section = 2;
        }
        if(this.section == 2) {
            this.consumeTrailer();
            this.section = 3;
            if(!this.source.exhausted()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override  // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    private final void updateCrc(Buffer buffer0, long v, long v1) {
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        while(v >= ((long)(segment0.limit - segment0.pos))) {
            v -= (long)(segment0.limit - segment0.pos);
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
        }
        while(v1 > 0L) {
            int v2 = (int)(((long)segment0.pos) + v);
            int v3 = (int)Math.min(segment0.limit - v2, v1);
            this.crc.update(segment0.data, v2, v3);
            v1 -= (long)v3;
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v = 0L;
        }
    }
}

