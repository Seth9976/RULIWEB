package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.InflaterSource;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000B\u001A\u00020\fH\u0016J\u000E\u0010\r\u001A\u00020\f2\u0006\u0010\u000E\u001A\u00020\u0006R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lokhttp3/internal/ws/MessageInflater;", "Ljava/io/Closeable;", "noContextTakeover", "", "(Z)V", "deflatedBytes", "Lokio/Buffer;", "inflater", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "close", "", "inflate", "buffer", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class MessageInflater implements Closeable, AutoCloseable {
    private final Buffer deflatedBytes;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private final boolean noContextTakeover;

    public MessageInflater(boolean z) {
        this.noContextTakeover = z;
        Buffer buffer0 = new Buffer();
        this.deflatedBytes = buffer0;
        Inflater inflater0 = new Inflater(true);
        this.inflater = inflater0;
        this.inflaterSource = new InflaterSource(buffer0, inflater0);
    }

    @Override
    public void close() throws IOException {
        this.inflaterSource.close();
    }

    public final void inflate(Buffer buffer0) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "buffer");
        if(this.deflatedBytes.size() != 0L) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if(this.noContextTakeover) {
            this.inflater.reset();
        }
        this.deflatedBytes.writeAll(buffer0);
        this.deflatedBytes.writeInt(0xFFFF);
        long v = this.inflater.getBytesRead();
        long v1 = this.deflatedBytes.size();
        do {
            this.inflaterSource.readOrInflate(buffer0, 0x7FFFFFFFFFFFFFFFL);
        }
        while(this.inflater.getBytesRead() < v + v1);
    }
}

