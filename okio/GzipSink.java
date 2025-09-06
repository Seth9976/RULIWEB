package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\r\u0010\b\u001A\u00020\tH\u0007¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001A\u00020\u000FH\u0016J\b\u0010\u0012\u001A\u00020\u0013H\u0016J\u0018\u0010\u0014\u001A\u00020\u000F2\u0006\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0018H\u0002J\u0018\u0010\u0019\u001A\u00020\u000F2\u0006\u0010\u001A\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0018H\u0016J\b\u0010\u001B\u001A\u00020\u000FH\u0002R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001A\u00020\t8G¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\nR\u000E\u0010\u000B\u001A\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Lokio/GzipSink;", "Lokio/Sink;", "sink", "(Lokio/Sink;)V", "closed", "", "crc", "Ljava/util/zip/CRC32;", "deflater", "Ljava/util/zip/Deflater;", "()Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "Lokio/RealBufferedSink;", "close", "", "-deprecated_deflater", "flush", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "Lokio/Buffer;", "byteCount", "", "write", "source", "writeFooter", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class GzipSink implements AutoCloseable, Sink {
    private boolean closed;
    private final CRC32 crc;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final RealBufferedSink sink;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "deflater", imports = {}))
    public final Deflater -deprecated_deflater() {
        return this.deflater;
    }

    public GzipSink(Sink sink0) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        super();
        RealBufferedSink realBufferedSink0 = new RealBufferedSink(sink0);
        this.sink = realBufferedSink0;
        Deflater deflater0 = new Deflater(-1, true);
        this.deflater = deflater0;
        this.deflaterSink = new DeflaterSink(realBufferedSink0, deflater0);
        this.crc = new CRC32();
        realBufferedSink0.bufferField.writeShort(8075);
        realBufferedSink0.bufferField.writeByte(8);
        realBufferedSink0.bufferField.writeByte(0);
        realBufferedSink0.bufferField.writeInt(0);
        realBufferedSink0.bufferField.writeByte(0);
        realBufferedSink0.bufferField.writeByte(0);
    }

    @Override  // okio.Sink
    public void close() throws IOException {
        if(!this.closed) {
            try {
                this.deflaterSink.finishDeflate$okio();
                this.writeFooter();
                throwable0 = null;
            }
            catch(Throwable throwable0) {
            }
            try {
                this.deflater.end();
            }
            catch(Throwable throwable1) {
                if(throwable0 == null) {
                    throwable0 = throwable1;
                }
            }
            try {
                this.sink.close();
            }
            catch(Throwable throwable2) {
                if(throwable0 == null) {
                    throwable0 = throwable2;
                }
            }
            this.closed = true;
            if(throwable0 != null) {
                throw throwable0;
            }
        }
    }

    public final Deflater deflater() {
        return this.deflater;
    }

    @Override  // okio.Sink
    public void flush() throws IOException {
        this.deflaterSink.flush();
    }

    @Override  // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    private final void updateCrc(Buffer buffer0, long v) {
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        while(v > 0L) {
            int v1 = (int)Math.min(v, segment0.limit - segment0.pos);
            this.crc.update(segment0.data, segment0.pos, v1);
            v -= (long)v1;
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
        }
    }

    @Override  // okio.Sink
    public void write(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        int v1 = Long.compare(v, 0L);
        if(v1 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(v1 == 0) {
            return;
        }
        this.updateCrc(buffer0, v);
        this.deflaterSink.write(buffer0, v);
    }

    private final void writeFooter() {
        int v = (int)this.crc.getValue();
        this.sink.writeIntLe(v);
        int v1 = (int)this.deflater.getBytesRead();
        this.sink.writeIntLe(v1);
    }
}

