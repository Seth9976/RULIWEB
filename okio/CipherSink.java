package okio;

import java.io.IOException;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001A\u00020\u000EH\u0016J\n\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0002J\b\u0010\u0011\u001A\u00020\u000EH\u0016J\b\u0010\u0012\u001A\u00020\u0013H\u0016J\u0018\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0018H\u0002J\u0018\u0010\u0019\u001A\u00020\u000E2\u0006\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u001A\u001A\u00020\u0018H\u0016R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u000E\u0010\u000B\u001A\u00020\fX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001B"}, d2 = {"Lokio/CipherSink;", "Lokio/Sink;", "sink", "Lokio/BufferedSink;", "cipher", "Ljavax/crypto/Cipher;", "(Lokio/BufferedSink;Ljavax/crypto/Cipher;)V", "blockSize", "", "getCipher", "()Ljavax/crypto/Cipher;", "closed", "", "close", "", "doFinal", "", "flush", "timeout", "Lokio/Timeout;", "update", "source", "Lokio/Buffer;", "remaining", "", "write", "byteCount", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class CipherSink implements AutoCloseable, Sink {
    private final int blockSize;
    private final Cipher cipher;
    private boolean closed;
    private final BufferedSink sink;

    public CipherSink(BufferedSink bufferedSink0, Cipher cipher0) {
        Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
        Intrinsics.checkNotNullParameter(cipher0, "cipher");
        super();
        this.sink = bufferedSink0;
        this.cipher = cipher0;
        int v = cipher0.getBlockSize();
        this.blockSize = v;
        if(v <= 0) {
            throw new IllegalArgumentException(("Block cipher required " + cipher0).toString());
        }
    }

    @Override  // okio.Sink
    public void close() throws IOException {
        if(!this.closed) {
            this.closed = true;
            Throwable throwable0 = this.doFinal();
            try {
                this.sink.close();
            }
            catch(Throwable throwable1) {
                if(throwable0 == null) {
                    throwable0 = throwable1;
                }
            }
            if(throwable0 != null) {
                throw throwable0;
            }
        }
    }

    private final Throwable doFinal() {
        int v = this.cipher.getOutputSize(0);
        Throwable throwable0 = null;
        if(v == 0) {
            return null;
        }
        if(v > 0x2000) {
            try {
                byte[] arr_b = this.cipher.doFinal();
                Intrinsics.checkNotNullExpressionValue(arr_b, "cipher.doFinal()");
                this.sink.write(arr_b);
                return null;
            }
            catch(Throwable throwable1) {
                return throwable1;
            }
        }
        Buffer buffer0 = this.sink.getBuffer();
        Segment segment0 = buffer0.writableSegment$okio(v);
        try {
            int v1 = this.cipher.doFinal(segment0.data, segment0.limit);
            segment0.limit += v1;
            buffer0.setSize$okio(buffer0.size() + ((long)v1));
        }
        catch(Throwable throwable0) {
        }
        if(segment0.pos == segment0.limit) {
            buffer0.head = segment0.pop();
            SegmentPool.recycle(segment0);
        }
        return throwable0;
    }

    @Override  // okio.Sink
    public void flush() {
        this.sink.flush();
    }

    public final Cipher getCipher() {
        return this.cipher;
    }

    @Override  // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    private final int update(Buffer buffer0, long v) {
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        int v1 = (int)Math.min(v, segment0.limit - segment0.pos);
        Buffer buffer1 = this.sink.getBuffer();
        int v2 = this.cipher.getOutputSize(v1);
        int v3 = v1;
        while(v2 > 0x2000) {
            int v4 = this.blockSize;
            if(v3 <= v4) {
                byte[] arr_b = buffer0.readByteArray(v);
                byte[] arr_b1 = this.cipher.update(arr_b);
                Intrinsics.checkNotNullExpressionValue(arr_b1, "cipher.update(source.readByteArray(remaining))");
                this.sink.write(arr_b1);
                return (int)v;
            }
            v3 -= v4;
            v2 = this.cipher.getOutputSize(v3);
        }
        Segment segment1 = buffer1.writableSegment$okio(v2);
        int v5 = this.cipher.update(segment0.data, segment0.pos, v3, segment1.data, segment1.limit);
        segment1.limit += v5;
        buffer1.setSize$okio(buffer1.size() + ((long)v5));
        if(segment1.pos == segment1.limit) {
            buffer1.head = segment1.pop();
            SegmentPool.recycle(segment1);
        }
        this.sink.emitCompleteSegments();
        buffer0.setSize$okio(buffer0.size() - ((long)v3));
        segment0.pos += v3;
        if(segment0.pos == segment0.limit) {
            buffer0.head = segment0.pop();
            SegmentPool.recycle(segment0);
        }
        return v3;
    }

    @Override  // okio.Sink
    public void write(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        _UtilKt.checkOffsetAndCount(buffer0.size(), 0L, v);
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        for(long v1 = v; v1 > 0L; v1 -= (long)this.update(buffer0, v1)) {
        }
    }
}

