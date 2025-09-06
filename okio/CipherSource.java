package okio;

import java.io.IOException;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001A\u00020\u0011H\u0016J\b\u0010\u0012\u001A\u00020\u0011H\u0002J\u0018\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\n2\u0006\u0010\u0016\u001A\u00020\u0014H\u0016J\b\u0010\u0017\u001A\u00020\u0011H\u0002J\b\u0010\u0018\u001A\u00020\u0019H\u0016J\b\u0010\u001A\u001A\u00020\u0011H\u0002R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\r\u001A\u00020\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001B"}, d2 = {"Lokio/CipherSource;", "Lokio/Source;", "source", "Lokio/BufferedSource;", "cipher", "Ljavax/crypto/Cipher;", "(Lokio/BufferedSource;Ljavax/crypto/Cipher;)V", "blockSize", "", "buffer", "Lokio/Buffer;", "getCipher", "()Ljavax/crypto/Cipher;", "closed", "", "final", "close", "", "doFinal", "read", "", "sink", "byteCount", "refill", "timeout", "Lokio/Timeout;", "update", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class CipherSource implements AutoCloseable, Source {
    private final int blockSize;
    private final Buffer buffer;
    private final Cipher cipher;
    private boolean closed;
    private boolean final;
    private final BufferedSource source;

    public CipherSource(BufferedSource bufferedSource0, Cipher cipher0) {
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        Intrinsics.checkNotNullParameter(cipher0, "cipher");
        super();
        this.source = bufferedSource0;
        this.cipher = cipher0;
        int v = cipher0.getBlockSize();
        this.blockSize = v;
        this.buffer = new Buffer();
        if(v <= 0) {
            throw new IllegalArgumentException(("Block cipher required " + cipher0).toString());
        }
    }

    @Override  // okio.Source
    public void close() throws IOException {
        this.closed = true;
        this.source.close();
    }

    private final void doFinal() {
        int v = this.cipher.getOutputSize(0);
        if(v != 0) {
            Segment segment0 = this.buffer.writableSegment$okio(v);
            int v1 = this.cipher.doFinal(segment0.data, segment0.pos);
            segment0.limit += v1;
            this.buffer.setSize$okio(this.buffer.size() + ((long)v1));
            if(segment0.pos == segment0.limit) {
                this.buffer.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
        }
    }

    public final Cipher getCipher() {
        return this.cipher;
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        int v1 = Long.compare(v, 0L);
        if(v1 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        if(v1 == 0) {
            return 0L;
        }
        this.refill();
        return this.buffer.read(buffer0, v);
    }

    private final void refill() {
        while(this.buffer.size() == 0L && !this.final) {
            if(this.source.exhausted()) {
                this.final = true;
                this.doFinal();
                return;
            }
            this.update();
        }
    }

    @Override  // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    private final void update() {
        Segment segment0 = this.source.getBuffer().head;
        Intrinsics.checkNotNull(segment0);
        int v = segment0.limit - segment0.pos;
        int v1 = this.cipher.getOutputSize(v);
        int v2 = v;
        while(v1 > 0x2000) {
            int v3 = this.blockSize;
            if(v2 <= v3) {
                this.final = true;
                byte[] arr_b = this.source.readByteArray();
                byte[] arr_b1 = this.cipher.doFinal(arr_b);
                Intrinsics.checkNotNullExpressionValue(arr_b1, "cipher.doFinal(source.readByteArray())");
                this.buffer.write(arr_b1);
                return;
            }
            v2 -= v3;
            v1 = this.cipher.getOutputSize(v2);
        }
        Segment segment1 = this.buffer.writableSegment$okio(v1);
        int v4 = this.cipher.update(segment0.data, segment0.pos, v2, segment1.data, segment1.pos);
        this.source.skip(((long)v2));
        segment1.limit += v4;
        this.buffer.setSize$okio(this.buffer.size() + ((long)v4));
        if(segment1.pos == segment1.limit) {
            this.buffer.head = segment1.pop();
            SegmentPool.recycle(segment1);
        }
    }
}

