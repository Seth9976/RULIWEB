package okio;

import java.io.RandomAccessFile;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001A\u00020\bH\u0014J\b\u0010\t\u001A\u00020\bH\u0014J(\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u000B2\u0006\u0010\u0011\u001A\u00020\u000BH\u0014J\u0010\u0010\u0012\u001A\u00020\b2\u0006\u0010\u0013\u001A\u00020\rH\u0014J\b\u0010\u0014\u001A\u00020\rH\u0014J(\u0010\u0015\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u000B2\u0006\u0010\u0011\u001A\u00020\u000BH\u0014R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lokio/JvmFileHandle;", "Lokio/FileHandle;", "readWrite", "", "randomAccessFile", "Ljava/io/RandomAccessFile;", "(ZLjava/io/RandomAccessFile;)V", "protectedClose", "", "protectedFlush", "protectedRead", "", "fileOffset", "", "array", "", "arrayOffset", "byteCount", "protectedResize", "size", "protectedSize", "protectedWrite", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class JvmFileHandle extends FileHandle {
    private final RandomAccessFile randomAccessFile;

    public JvmFileHandle(boolean z, RandomAccessFile randomAccessFile0) {
        Intrinsics.checkNotNullParameter(randomAccessFile0, "randomAccessFile");
        super(z);
        this.randomAccessFile = randomAccessFile0;
    }

    @Override  // okio.FileHandle
    protected void protectedClose() {
        synchronized(this) {
            this.randomAccessFile.close();
        }
    }

    @Override  // okio.FileHandle
    protected void protectedFlush() {
        synchronized(this) {
            this.randomAccessFile.getFD().sync();
        }
    }

    @Override  // okio.FileHandle
    protected int protectedRead(long v, byte[] arr_b, int v1, int v2) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(arr_b, "array");
            this.randomAccessFile.seek(v);
            int v4;
            for(v4 = 0; v4 < v2; v4 += v5) {
                int v5 = this.randomAccessFile.read(arr_b, v1, v2 - v4);
                if(v5 == -1) {
                    if(v4 != 0) {
                        break;
                    }
                    return -1;
                }
            }
            return v4;
        }
    }

    @Override  // okio.FileHandle
    protected void protectedResize(long v) {
        synchronized(this) {
            long v2 = this.size();
            long v3 = v - v2;
            if(v3 > 0L) {
                this.protectedWrite(v2, new byte[((int)v3)], 0, ((int)v3));
            }
            else {
                this.randomAccessFile.setLength(v);
            }
        }
    }

    @Override  // okio.FileHandle
    protected long protectedSize() {
        synchronized(this) {
            return this.randomAccessFile.length();
        }
    }

    @Override  // okio.FileHandle
    protected void protectedWrite(long v, byte[] arr_b, int v1, int v2) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(arr_b, "array");
            this.randomAccessFile.seek(v);
            this.randomAccessFile.write(arr_b, v1, v2);
        }
    }
}

