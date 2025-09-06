package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer.UnsafeCursor;
import okio.Buffer;
import okio.ByteString;
import okio.DeflaterSink;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000B\u001A\u00020\fH\u0016J\u000E\u0010\r\u001A\u00020\f2\u0006\u0010\u000E\u001A\u00020\u0006J\u0014\u0010\u000F\u001A\u00020\u0003*\u00020\u00062\u0006\u0010\u0010\u001A\u00020\u0011H\u0002R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/internal/ws/MessageDeflater;", "Ljava/io/Closeable;", "noContextTakeover", "", "(Z)V", "deflatedBytes", "Lokio/Buffer;", "deflater", "Ljava/util/zip/Deflater;", "deflaterSink", "Lokio/DeflaterSink;", "close", "", "deflate", "buffer", "endsWith", "suffix", "Lokio/ByteString;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class MessageDeflater implements Closeable, AutoCloseable {
    private final Buffer deflatedBytes;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final boolean noContextTakeover;

    public MessageDeflater(boolean z) {
        this.noContextTakeover = z;
        Buffer buffer0 = new Buffer();
        this.deflatedBytes = buffer0;
        Deflater deflater0 = new Deflater(-1, true);
        this.deflater = deflater0;
        this.deflaterSink = new DeflaterSink(buffer0, deflater0);
    }

    @Override
    public void close() throws IOException {
        this.deflaterSink.close();
    }

    public final void deflate(Buffer buffer0) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "buffer");
        if(this.deflatedBytes.size() != 0L) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if(this.noContextTakeover) {
            this.deflater.reset();
        }
        this.deflaterSink.write(buffer0, buffer0.size());
        this.deflaterSink.flush();
        if(this.endsWith(this.deflatedBytes, MessageDeflaterKt.access$getEMPTY_DEFLATE_BLOCK$p())) {
            long v = this.deflatedBytes.size();
            Closeable closeable0 = Buffer.readAndWriteUnsafe$default(this.deflatedBytes, null, 1, null);
            try {
                ((UnsafeCursor)closeable0).resizeBuffer(v - 4L);
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(closeable0, throwable0);
                throw throwable0;
            }
            CloseableKt.closeFinally(closeable0, null);
        }
        else {
            this.deflatedBytes.writeByte(0);
        }
        buffer0.write(this.deflatedBytes, this.deflatedBytes.size());
    }

    private final boolean endsWith(Buffer buffer0, ByteString byteString0) {
        return buffer0.rangeEquals(buffer0.size() - ((long)byteString0.size()), byteString0);
    }
}

