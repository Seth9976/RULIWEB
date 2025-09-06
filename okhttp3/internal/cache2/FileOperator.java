package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001E\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\bJ\u001E\u0010\f\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\bR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lokhttp3/internal/cache2/FileOperator;", "", "fileChannel", "Ljava/nio/channels/FileChannel;", "(Ljava/nio/channels/FileChannel;)V", "read", "", "pos", "", "sink", "Lokio/Buffer;", "byteCount", "write", "source", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class FileOperator {
    private final FileChannel fileChannel;

    public FileOperator(FileChannel fileChannel0) {
        Intrinsics.checkNotNullParameter(fileChannel0, "fileChannel");
        super();
        this.fileChannel = fileChannel0;
    }

    public final void read(long v, Buffer buffer0, long v1) {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        if(v1 < 0L) {
            throw new IndexOutOfBoundsException();
        }
        long v2 = v;
        for(long v3 = v1; v3 > 0L; v3 -= v4) {
            long v4 = this.fileChannel.transferTo(v2, v3, buffer0);
            v2 += v4;
        }
    }

    public final void write(long v, Buffer buffer0, long v1) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        if(v1 < 0L || v1 > buffer0.size()) {
            throw new IndexOutOfBoundsException();
        }
        long v2 = v;
        for(long v3 = v1; v3 > 0L; v3 -= v4) {
            long v4 = this.fileChannel.transferFrom(buffer0, v2, v3);
            v2 += v4;
        }
    }
}

