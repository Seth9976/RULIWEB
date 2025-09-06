package okio;

import java.io.Closeable;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0002()B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0006\u0010\u000B\u001A\u00020\fJ\u0006\u0010\r\u001A\u00020\u000EJ\u0006\u0010\u000F\u001A\u00020\u000EJ\u000E\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\fJ\u000E\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0013\u001A\u00020\u0014J\b\u0010\u0015\u001A\u00020\u000EH$J\b\u0010\u0016\u001A\u00020\u000EH$J(\u0010\u0017\u001A\u00020\b2\u0006\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\b2\u0006\u0010\u001C\u001A\u00020\bH$J\u0010\u0010\u001D\u001A\u00020\u000E2\u0006\u0010\u001E\u001A\u00020\u0011H$J\b\u0010\u001F\u001A\u00020\u0011H$J(\u0010 \u001A\u00020\u000E2\u0006\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\b2\u0006\u0010\u001C\u001A\u00020\bH$J&\u0010!\u001A\u00020\b2\u0006\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\b2\u0006\u0010\u001C\u001A\u00020\bJ\u001E\u0010!\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\"2\u0006\u0010\u001C\u001A\u00020\u0011J \u0010#\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\"2\u0006\u0010\u001C\u001A\u00020\u0011H\u0002J\u0016\u0010$\u001A\u00020\u000E2\u0006\u0010\u0012\u001A\u00020\f2\u0006\u0010\u0010\u001A\u00020\u0011J\u0016\u0010$\u001A\u00020\u000E2\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0010\u001A\u00020\u0011J\u000E\u0010%\u001A\u00020\u000E2\u0006\u0010\u001E\u001A\u00020\u0011J\u0010\u0010\u0012\u001A\u00020\f2\b\b\u0002\u0010\u0018\u001A\u00020\u0011J\u0006\u0010\u001E\u001A\u00020\u0011J\u0010\u0010\u0013\u001A\u00020\u00142\b\b\u0002\u0010\u0018\u001A\u00020\u0011J&\u0010&\u001A\u00020\u000E2\u0006\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\b2\u0006\u0010\u001C\u001A\u00020\bJ\u001E\u0010&\u001A\u00020\u000E2\u0006\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0013\u001A\u00020\"2\u0006\u0010\u001C\u001A\u00020\u0011J \u0010\'\u001A\u00020\u000E2\u0006\u0010\u0018\u001A\u00020\u00112\u0006\u0010\u0013\u001A\u00020\"2\u0006\u0010\u001C\u001A\u00020\u0011H\u0002R\u000E\u0010\u0006\u001A\u00020\u0004X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n\u00A8\u0006*"}, d2 = {"Lokio/FileHandle;", "Ljava/io/Closeable;", "Lokio/Closeable;", "readWrite", "", "(Z)V", "closed", "openStreamCount", "", "getReadWrite", "()Z", "appendingSink", "Lokio/Sink;", "close", "", "flush", "position", "", "sink", "source", "Lokio/Source;", "protectedClose", "protectedFlush", "protectedRead", "fileOffset", "array", "", "arrayOffset", "byteCount", "protectedResize", "size", "protectedSize", "protectedWrite", "read", "Lokio/Buffer;", "readNoCloseCheck", "reposition", "resize", "write", "writeNoCloseCheck", "FileHandleSink", "FileHandleSource", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public abstract class FileHandle implements Closeable, AutoCloseable {
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u000B\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001A\u00020\u0014H\u0016J\b\u0010\u0015\u001A\u00020\u0014H\u0016J\b\u0010\u0016\u001A\u00020\u0017H\u0016J\u0018\u0010\u0018\u001A\u00020\u00142\u0006\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\u0005H\u0016R\u001A\u0010\u0007\u001A\u00020\bX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\t\u0010\n\"\u0004\b\u000B\u0010\fR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u001A\u0010\u0004\u001A\u00020\u0005X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000F\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001C"}, d2 = {"Lokio/FileHandle$FileHandleSink;", "Lokio/Sink;", "fileHandle", "Lokio/FileHandle;", "position", "", "(Lokio/FileHandle;J)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "getFileHandle", "()Lokio/FileHandle;", "getPosition", "()J", "setPosition", "(J)V", "close", "", "flush", "timeout", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class FileHandleSink implements AutoCloseable, Sink {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSink(FileHandle fileHandle0, long v) {
            Intrinsics.checkNotNullParameter(fileHandle0, "fileHandle");
            super();
            this.fileHandle = fileHandle0;
            this.position = v;
        }

        @Override  // okio.Sink
        public void close() {
            if(this.closed) {
                return;
            }
            this.closed = true;
            synchronized(this.fileHandle) {
                --this.fileHandle.openStreamCount;
                if(this.fileHandle.openStreamCount == 0 && this.fileHandle.closed) {
                    this.fileHandle.protectedClose();
                }
            }
        }

        @Override  // okio.Sink
        public void flush() {
            if(this.closed) {
                throw new IllegalStateException("closed");
            }
            this.fileHandle.protectedFlush();
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final FileHandle getFileHandle() {
            return this.fileHandle;
        }

        public final long getPosition() {
            return this.position;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public final void setPosition(long v) {
            this.position = v;
        }

        @Override  // okio.Sink
        public Timeout timeout() {
            return Timeout.NONE;
        }

        @Override  // okio.Sink
        public void write(Buffer buffer0, long v) {
            Intrinsics.checkNotNullParameter(buffer0, "source");
            if(this.closed) {
                throw new IllegalStateException("closed");
            }
            this.fileHandle.writeNoCloseCheck(this.position, buffer0, v);
            this.position += v;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u000B\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001A\u00020\u0014H\u0016J\u0018\u0010\u0015\u001A\u00020\u00052\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0005H\u0016J\b\u0010\u0019\u001A\u00020\u001AH\u0016R\u001A\u0010\u0007\u001A\u00020\bX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\t\u0010\n\"\u0004\b\u000B\u0010\fR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u001A\u0010\u0004\u001A\u00020\u0005X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000F\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001B"}, d2 = {"Lokio/FileHandle$FileHandleSource;", "Lokio/Source;", "fileHandle", "Lokio/FileHandle;", "position", "", "(Lokio/FileHandle;J)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "getFileHandle", "()Lokio/FileHandle;", "getPosition", "()J", "setPosition", "(J)V", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class FileHandleSource implements AutoCloseable, Source {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSource(FileHandle fileHandle0, long v) {
            Intrinsics.checkNotNullParameter(fileHandle0, "fileHandle");
            super();
            this.fileHandle = fileHandle0;
            this.position = v;
        }

        @Override  // okio.Source
        public void close() {
            if(this.closed) {
                return;
            }
            this.closed = true;
            synchronized(this.fileHandle) {
                --this.fileHandle.openStreamCount;
                if(this.fileHandle.openStreamCount == 0 && this.fileHandle.closed) {
                    this.fileHandle.protectedClose();
                }
            }
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final FileHandle getFileHandle() {
            return this.fileHandle;
        }

        public final long getPosition() {
            return this.position;
        }

        @Override  // okio.Source
        public long read(Buffer buffer0, long v) {
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            if(this.closed) {
                throw new IllegalStateException("closed");
            }
            long v1 = this.fileHandle.readNoCloseCheck(this.position, buffer0, v);
            if(v1 != -1L) {
                this.position += v1;
            }
            return v1;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public final void setPosition(long v) {
            this.position = v;
        }

        @Override  // okio.Source
        public Timeout timeout() {
            return Timeout.NONE;
        }
    }

    private boolean closed;
    private int openStreamCount;
    private final boolean readWrite;

    public FileHandle(boolean z) {
        this.readWrite = z;
    }

    public final Sink appendingSink() throws IOException {
        return this.sink(this.size());
    }

    @Override
    public final void close() throws IOException {
        synchronized(this) {
            if(this.closed) {
                return;
            }
            this.closed = true;
            if(this.openStreamCount != 0) {
                return;
            }
        }
        this.protectedClose();
    }

    public final void flush() throws IOException {
        if(!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        synchronized(this) {
            if(!this.closed) {
                this.protectedFlush();
                return;
            }
        }
        throw new IllegalStateException("closed");
    }

    public final boolean getReadWrite() {
        return this.readWrite;
    }

    public final long position(Sink sink0) throws IOException {
        long v;
        Intrinsics.checkNotNullParameter(sink0, "sink");
        if(sink0 instanceof RealBufferedSink) {
            v = ((RealBufferedSink)sink0).bufferField.size();
            sink0 = ((RealBufferedSink)sink0).sink;
        }
        else {
            v = 0L;
        }
        if(!(sink0 instanceof FileHandleSink) || ((FileHandleSink)sink0).getFileHandle() != this) {
            throw new IllegalArgumentException("sink was not created by this FileHandle");
        }
        if(((FileHandleSink)sink0).getClosed()) {
            throw new IllegalStateException("closed");
        }
        return ((FileHandleSink)sink0).getPosition() + v;
    }

    public final long position(Source source0) throws IOException {
        long v;
        Intrinsics.checkNotNullParameter(source0, "source");
        if(source0 instanceof RealBufferedSource) {
            v = ((RealBufferedSource)source0).bufferField.size();
            source0 = ((RealBufferedSource)source0).source;
        }
        else {
            v = 0L;
        }
        if(!(source0 instanceof FileHandleSource) || ((FileHandleSource)source0).getFileHandle() != this) {
            throw new IllegalArgumentException("source was not created by this FileHandle");
        }
        if(((FileHandleSource)source0).getClosed()) {
            throw new IllegalStateException("closed");
        }
        return ((FileHandleSource)source0).getPosition() - v;
    }

    protected abstract void protectedClose() throws IOException;

    protected abstract void protectedFlush() throws IOException;

    protected abstract int protectedRead(long arg1, byte[] arg2, int arg3, int arg4) throws IOException;

    protected abstract void protectedResize(long arg1) throws IOException;

    protected abstract long protectedSize() throws IOException;

    protected abstract void protectedWrite(long arg1, byte[] arg2, int arg3, int arg4) throws IOException;

    public final int read(long v, byte[] arr_b, int v1, int v2) throws IOException {
        Intrinsics.checkNotNullParameter(arr_b, "array");
        synchronized(this) {
            if(!this.closed) {
                return this.protectedRead(v, arr_b, v1, v2);
            }
        }
        throw new IllegalStateException("closed");
    }

    public final long read(long v, Buffer buffer0, long v1) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        synchronized(this) {
            if(!this.closed) {
                return this.readNoCloseCheck(v, buffer0, v1);
            }
        }
        throw new IllegalStateException("closed");
    }

    private final long readNoCloseCheck(long v, Buffer buffer0, long v1) {
        if(v1 < 0L) {
            throw new IllegalArgumentException(("byteCount < 0: " + v1).toString());
        }
        long v2 = v1 + v;
        long v3 = v;
        while(v3 < v2) {
            Segment segment0 = buffer0.writableSegment$okio(1);
            int v4 = this.protectedRead(v3, segment0.data, segment0.limit, ((int)Math.min(v2 - v3, 0x2000 - segment0.limit)));
            if(v4 == -1) {
                if(segment0.pos == segment0.limit) {
                    buffer0.head = segment0.pop();
                    SegmentPool.recycle(segment0);
                }
                return v == v3 ? -1L : v3 - v;
            }
            segment0.limit += v4;
            v3 += (long)v4;
            buffer0.setSize$okio(buffer0.size() + ((long)v4));
        }
        return v3 - v;
    }

    public final void reposition(Sink sink0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        if(sink0 instanceof RealBufferedSink) {
            Sink sink1 = ((RealBufferedSink)sink0).sink;
            if(!(sink1 instanceof FileHandleSink) || ((FileHandleSink)sink1).getFileHandle() != this) {
                throw new IllegalArgumentException("sink was not created by this FileHandle");
            }
            if(((FileHandleSink)sink1).getClosed()) {
                throw new IllegalStateException("closed");
            }
            ((RealBufferedSink)sink0).emit();
            ((FileHandleSink)sink1).setPosition(v);
            return;
        }
        if(!(sink0 instanceof FileHandleSink) || ((FileHandleSink)sink0).getFileHandle() != this) {
            throw new IllegalArgumentException("sink was not created by this FileHandle");
        }
        if(((FileHandleSink)sink0).getClosed()) {
            throw new IllegalStateException("closed");
        }
        ((FileHandleSink)sink0).setPosition(v);
    }

    public final void reposition(Source source0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(source0, "source");
        if(source0 instanceof RealBufferedSource) {
            Source source1 = ((RealBufferedSource)source0).source;
            if(!(source1 instanceof FileHandleSource) || ((FileHandleSource)source1).getFileHandle() != this) {
                throw new IllegalArgumentException("source was not created by this FileHandle");
            }
            if(((FileHandleSource)source1).getClosed()) {
                throw new IllegalStateException("closed");
            }
            long v1 = ((RealBufferedSource)source0).bufferField.size();
            long v2 = v - (((FileHandleSource)source1).getPosition() - v1);
            if(0L <= v2 && v2 < v1) {
                ((RealBufferedSource)source0).skip(v2);
                return;
            }
            ((RealBufferedSource)source0).bufferField.clear();
            ((FileHandleSource)source1).setPosition(v);
            return;
        }
        if(!(source0 instanceof FileHandleSource) || ((FileHandleSource)source0).getFileHandle() != this) {
            throw new IllegalArgumentException("source was not created by this FileHandle");
        }
        if(((FileHandleSource)source0).getClosed()) {
            throw new IllegalStateException("closed");
        }
        ((FileHandleSource)source0).setPosition(v);
    }

    public final void resize(long v) throws IOException {
        if(!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        synchronized(this) {
            if(!this.closed) {
                this.protectedResize(v);
                return;
            }
        }
        throw new IllegalStateException("closed");
    }

    public final Sink sink(long v) throws IOException {
        if(!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        synchronized(this) {
            if(!this.closed) {
                ++this.openStreamCount;
                return new FileHandleSink(this, v);
            }
        }
        throw new IllegalStateException("closed");
    }

    public static Sink sink$default(FileHandle fileHandle0, long v, int v1, Object object0) throws IOException {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
        }
        if((v1 & 1) != 0) {
            v = 0L;
        }
        return fileHandle0.sink(v);
    }

    public final long size() throws IOException {
        synchronized(this) {
            if(!this.closed) {
                return this.protectedSize();
            }
        }
        throw new IllegalStateException("closed");
    }

    public final Source source(long v) throws IOException {
        synchronized(this) {
            if(!this.closed) {
                ++this.openStreamCount;
                return new FileHandleSource(this, v);
            }
        }
        throw new IllegalStateException("closed");
    }

    public static Source source$default(FileHandle fileHandle0, long v, int v1, Object object0) throws IOException {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: source");
        }
        if((v1 & 1) != 0) {
            v = 0L;
        }
        return fileHandle0.source(v);
    }

    public final void write(long v, Buffer buffer0, long v1) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        if(!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        synchronized(this) {
            if(!this.closed) {
                this.writeNoCloseCheck(v, buffer0, v1);
                return;
            }
        }
        throw new IllegalStateException("closed");
    }

    public final void write(long v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_b, "array");
        if(!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        synchronized(this) {
            if(!this.closed) {
                this.protectedWrite(v, arr_b, v1, v2);
                return;
            }
        }
        throw new IllegalStateException("closed");
    }

    private final void writeNoCloseCheck(long v, Buffer buffer0, long v1) {
        _UtilKt.checkOffsetAndCount(buffer0.size(), 0L, v1);
        long v2 = v + v1;
        long v3 = v;
        while(v3 < v2) {
            Segment segment0 = buffer0.head;
            Intrinsics.checkNotNull(segment0);
            int v4 = (int)Math.min(v2 - v3, segment0.limit - segment0.pos);
            this.protectedWrite(v3, segment0.data, segment0.pos, v4);
            segment0.pos += v4;
            v3 += (long)v4;
            buffer0.setSize$okio(buffer0.size() - ((long)v4));
            if(segment0.pos == segment0.limit) {
                buffer0.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
        }
    }
}

