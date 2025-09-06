package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilCommonKt;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 :2\u00020\u0001:\u0002:;B3\b\u0002\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u0007¢\u0006\u0002\u0010\u000BJ\u000E\u00102\u001A\u0002032\u0006\u00104\u001A\u00020\u0007J\u0006\u0010\b\u001A\u00020\tJ\b\u00105\u001A\u0004\u0018\u00010\u0005J \u00106\u001A\u0002032\u0006\u00107\u001A\u00020\t2\u0006\u00104\u001A\u00020\u00072\u0006\u00108\u001A\u00020\u0007H\u0002J\u0010\u00109\u001A\u0002032\u0006\u00104\u001A\u00020\u0007H\u0002R\u0011\u0010\f\u001A\u00020\r¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0011\u0010\n\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u001A\u0010\u0012\u001A\u00020\u0013X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001C\u0010\u0002\u001A\u0004\u0018\u00010\u0003X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0018\u0010\u0019\"\u0004\b\u001A\u0010\u001BR\u0011\u0010\u001C\u001A\u00020\u00138F¢\u0006\u0006\u001A\u0004\b\u001C\u0010\u0015R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u001D\u001A\u00020\u001EX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u001F\u0010 \"\u0004\b!\u0010\"R\u001C\u0010\u0004\u001A\u0004\u0018\u00010\u0005X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010\'\u001A\u00020\r¢\u0006\b\n\u0000\u001A\u0004\b(\u0010\u000FR\u001A\u0010\u0006\u001A\u00020\u0007X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b)\u0010\u0011\"\u0004\b*\u0010+R\u001C\u0010,\u001A\u0004\u0018\u00010-X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b.\u0010/\"\u0004\b0\u00101¨\u0006<"}, d2 = {"Lokhttp3/internal/cache2/Relay;", "", "file", "Ljava/io/RandomAccessFile;", "upstream", "Lokio/Source;", "upstreamPos", "", "metadata", "Lokio/ByteString;", "bufferMaxSize", "(Ljava/io/RandomAccessFile;Lokio/Source;JLokio/ByteString;J)V", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "getBufferMaxSize", "()J", "complete", "", "getComplete", "()Z", "setComplete", "(Z)V", "getFile", "()Ljava/io/RandomAccessFile;", "setFile", "(Ljava/io/RandomAccessFile;)V", "isClosed", "sourceCount", "", "getSourceCount", "()I", "setSourceCount", "(I)V", "getUpstream", "()Lokio/Source;", "setUpstream", "(Lokio/Source;)V", "upstreamBuffer", "getUpstreamBuffer", "getUpstreamPos", "setUpstreamPos", "(J)V", "upstreamReader", "Ljava/lang/Thread;", "getUpstreamReader", "()Ljava/lang/Thread;", "setUpstreamReader", "(Ljava/lang/Thread;)V", "commit", "", "upstreamSize", "newSource", "writeHeader", "prefix", "metadataSize", "writeMetadata", "Companion", "RelaySource", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Relay {
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00062\u0006\u0010\u0012\u001A\u00020\u0004J\u000E\u0010\u0013\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000ER\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lokhttp3/internal/cache2/Relay$Companion;", "", "()V", "FILE_HEADER_SIZE", "", "PREFIX_CLEAN", "Lokio/ByteString;", "PREFIX_DIRTY", "SOURCE_FILE", "", "SOURCE_UPSTREAM", "edit", "Lokhttp3/internal/cache2/Relay;", "file", "Ljava/io/File;", "upstream", "Lokio/Source;", "metadata", "bufferMaxSize", "read", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Relay edit(File file0, Source source0, ByteString byteString0, long v) throws IOException {
            Intrinsics.checkNotNullParameter(file0, "file");
            Intrinsics.checkNotNullParameter(source0, "upstream");
            Intrinsics.checkNotNullParameter(byteString0, "metadata");
            RandomAccessFile randomAccessFile0 = new RandomAccessFile(file0, "rw");
            Relay relay0 = new Relay(randomAccessFile0, source0, 0L, byteString0, v, null);
            randomAccessFile0.setLength(0L);
            relay0.writeHeader(Relay.PREFIX_DIRTY, -1L, -1L);
            return relay0;
        }

        public final Relay read(File file0) throws IOException {
            Intrinsics.checkNotNullParameter(file0, "file");
            RandomAccessFile randomAccessFile0 = new RandomAccessFile(file0, "rw");
            FileChannel fileChannel0 = randomAccessFile0.getChannel();
            Intrinsics.checkNotNullExpressionValue(fileChannel0, "randomAccessFile.channel");
            FileOperator fileOperator0 = new FileOperator(fileChannel0);
            Buffer buffer0 = new Buffer();
            fileOperator0.read(0L, buffer0, 0x20L);
            if(!Intrinsics.areEqual(buffer0.readByteString(((long)Relay.PREFIX_CLEAN.size())), Relay.PREFIX_CLEAN)) {
                throw new IOException("unreadable cache file");
            }
            long v = buffer0.readLong();
            long v1 = buffer0.readLong();
            Buffer buffer1 = new Buffer();
            fileOperator0.read(v + 0x20L, buffer1, v1);
            return new Relay(randomAccessFile0, null, v, buffer1.readByteString(), 0L, null);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u0006H\u0016J\b\u0010\u0007\u001A\u00020\bH\u0016R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lokhttp3/internal/cache2/Relay$RelaySource;", "Lokio/Source;", "(Lokhttp3/internal/cache2/Relay;)V", "fileOperator", "Lokhttp3/internal/cache2/FileOperator;", "sourcePos", "", "timeout", "Lokio/Timeout;", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class RelaySource implements AutoCloseable, Source {
        private FileOperator fileOperator;
        private long sourcePos;
        private final Timeout timeout;

        public RelaySource() {
            this.timeout = new Timeout();
            RandomAccessFile randomAccessFile0 = relay0.getFile();
            Intrinsics.checkNotNull(randomAccessFile0);
            FileChannel fileChannel0 = randomAccessFile0.getChannel();
            Intrinsics.checkNotNullExpressionValue(fileChannel0, "file!!.channel");
            this.fileOperator = new FileOperator(fileChannel0);
        }

        @Override  // okio.Source
        public void close() throws IOException {
            if(this.fileOperator != null) {
                RandomAccessFile randomAccessFile0 = null;
                this.fileOperator = null;
                Relay relay0 = Relay.this;
                synchronized(relay0) {
                    relay0.setSourceCount(relay0.getSourceCount() - 1);
                    if(relay0.getSourceCount() == 0) {
                        relay0.setFile(null);
                        randomAccessFile0 = relay0.getFile();
                    }
                }
                if(randomAccessFile0 != null) {
                    _UtilCommonKt.closeQuietly(randomAccessFile0);
                }
            }
        }

        @Override  // okio.Source
        public long read(Buffer buffer0, long v) throws IOException {
            int v2;
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            if(this.fileOperator != null) {
                Relay relay0 = Relay.this;
                synchronized(relay0) {
                    while(this.sourcePos == relay0.getUpstreamPos()) {
                        if(relay0.getComplete()) {
                            return -1L;
                        }
                        if(relay0.getUpstreamReader() != null) {
                            this.timeout.waitUntilNotified(relay0);
                            continue;
                        }
                        relay0.setUpstreamReader(Thread.currentThread());
                        v2 = 1;
                        goto label_20;
                    }
                    long v3 = relay0.getUpstreamPos() - relay0.getBuffer().size();
                    if(this.sourcePos < v3) {
                        v2 = 2;
                    label_20:
                        if(v2 == 2) {
                            long v4 = Math.min(v, Relay.this.getUpstreamPos() - this.sourcePos);
                            FileOperator fileOperator0 = this.fileOperator;
                            Intrinsics.checkNotNull(fileOperator0);
                            fileOperator0.read(this.sourcePos + 0x20L, buffer0, v4);
                            this.sourcePos += v4;
                            return v4;
                        }
                        try {
                            Source source0 = Relay.this.getUpstream();
                            Intrinsics.checkNotNull(source0);
                            long v6 = source0.read(Relay.this.getUpstreamBuffer(), Relay.this.getBufferMaxSize());
                            if(v6 == -1L) {
                                Relay.this.commit(Relay.this.getUpstreamPos());
                                return -1L;
                            }
                            long v8 = Math.min(v6, v);
                            Relay.this.getUpstreamBuffer().copyTo(buffer0, 0L, v8);
                            this.sourcePos += v8;
                            FileOperator fileOperator1 = this.fileOperator;
                            Intrinsics.checkNotNull(fileOperator1);
                            fileOperator1.write(Relay.this.getUpstreamPos() + 0x20L, Relay.this.getUpstreamBuffer().clone(), v6);
                            Relay relay1 = Relay.this;
                            synchronized(relay1) {
                                relay1.getBuffer().write(relay1.getUpstreamBuffer(), v6);
                                if(relay1.getBuffer().size() > relay1.getBufferMaxSize()) {
                                    relay1.getBuffer().skip(relay1.getBuffer().size() - relay1.getBufferMaxSize());
                                }
                                relay1.setUpstreamPos(relay1.getUpstreamPos() + v6);
                                return v8;
                            }
                        }
                        finally {
                            synchronized(Relay.this) {
                                Relay.this.setUpstreamReader(null);
                                Intrinsics.checkNotNull(Relay.this, "null cannot be cast to non-null type java.lang.Object");
                                Relay.this.notifyAll();
                            }
                        }
                    }
                    long v10 = Math.min(v, relay0.getUpstreamPos() - this.sourcePos);
                    relay0.getBuffer().copyTo(buffer0, this.sourcePos - v3, v10);
                    this.sourcePos += v10;
                    return v10;
                }
            }
            throw new IllegalStateException("Check failed.");
        }

        @Override  // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    public static final Companion Companion = null;
    private static final long FILE_HEADER_SIZE = 0x20L;
    public static final ByteString PREFIX_CLEAN = null;
    public static final ByteString PREFIX_DIRTY = null;
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    private final Buffer buffer;
    private final long bufferMaxSize;
    private boolean complete;
    private RandomAccessFile file;
    private final ByteString metadata;
    private int sourceCount;
    private Source upstream;
    private final Buffer upstreamBuffer;
    private long upstreamPos;
    private Thread upstreamReader;

    static {
        Relay.Companion = new Companion(null);
        Relay.PREFIX_CLEAN = ByteString.Companion.encodeUtf8("OkHttp cache v1\n");
        Relay.PREFIX_DIRTY = ByteString.Companion.encodeUtf8("OkHttp DIRTY :(\n");
    }

    private Relay(RandomAccessFile randomAccessFile0, Source source0, long v, ByteString byteString0, long v1) {
        this.file = randomAccessFile0;
        this.upstream = source0;
        this.upstreamPos = v;
        this.metadata = byteString0;
        this.bufferMaxSize = v1;
        this.upstreamBuffer = new Buffer();
        this.complete = this.upstream == null;
        this.buffer = new Buffer();
    }

    public Relay(RandomAccessFile randomAccessFile0, Source source0, long v, ByteString byteString0, long v1, DefaultConstructorMarker defaultConstructorMarker0) {
        this(randomAccessFile0, source0, v, byteString0, v1);
    }

    public final void commit(long v) throws IOException {
        this.writeMetadata(v);
        RandomAccessFile randomAccessFile0 = this.file;
        Intrinsics.checkNotNull(randomAccessFile0);
        randomAccessFile0.getChannel().force(false);
        this.writeHeader(Relay.PREFIX_CLEAN, v, ((long)this.metadata.size()));
        RandomAccessFile randomAccessFile1 = this.file;
        Intrinsics.checkNotNull(randomAccessFile1);
        randomAccessFile1.getChannel().force(false);
        synchronized(this) {
            this.complete = true;
        }
        Source source0 = this.upstream;
        if(source0 != null) {
            _UtilCommonKt.closeQuietly(source0);
        }
        this.upstream = null;
    }

    public final Buffer getBuffer() {
        return this.buffer;
    }

    public final long getBufferMaxSize() {
        return this.bufferMaxSize;
    }

    public final boolean getComplete() {
        return this.complete;
    }

    public final RandomAccessFile getFile() {
        return this.file;
    }

    public final int getSourceCount() {
        return this.sourceCount;
    }

    public final Source getUpstream() {
        return this.upstream;
    }

    public final Buffer getUpstreamBuffer() {
        return this.upstreamBuffer;
    }

    public final long getUpstreamPos() {
        return this.upstreamPos;
    }

    public final Thread getUpstreamReader() {
        return this.upstreamReader;
    }

    public final boolean isClosed() {
        return this.file == null;
    }

    public final ByteString metadata() {
        return this.metadata;
    }

    public final Source newSource() {
        synchronized(this) {
            if(this.file == null) {
                return null;
            }
            ++this.sourceCount;
        }
        return new RelaySource(this);
    }

    public final void setComplete(boolean z) {
        this.complete = z;
    }

    public final void setFile(RandomAccessFile randomAccessFile0) {
        this.file = randomAccessFile0;
    }

    public final void setSourceCount(int v) {
        this.sourceCount = v;
    }

    public final void setUpstream(Source source0) {
        this.upstream = source0;
    }

    public final void setUpstreamPos(long v) {
        this.upstreamPos = v;
    }

    public final void setUpstreamReader(Thread thread0) {
        this.upstreamReader = thread0;
    }

    private final void writeHeader(ByteString byteString0, long v, long v1) throws IOException {
        Buffer buffer0 = new Buffer();
        buffer0.write(byteString0);
        buffer0.writeLong(v);
        buffer0.writeLong(v1);
        if(buffer0.size() != 0x20L) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        RandomAccessFile randomAccessFile0 = this.file;
        Intrinsics.checkNotNull(randomAccessFile0);
        FileChannel fileChannel0 = randomAccessFile0.getChannel();
        Intrinsics.checkNotNullExpressionValue(fileChannel0, "file!!.channel");
        new FileOperator(fileChannel0).write(0L, buffer0, 0x20L);
    }

    private final void writeMetadata(long v) throws IOException {
        Buffer buffer0 = new Buffer();
        buffer0.write(this.metadata);
        RandomAccessFile randomAccessFile0 = this.file;
        Intrinsics.checkNotNull(randomAccessFile0);
        FileChannel fileChannel0 = randomAccessFile0.getChannel();
        Intrinsics.checkNotNullExpressionValue(fileChannel0, "file!!.channel");
        new FileOperator(fileChannel0).write(v + 0x20L, buffer0, ((long)this.metadata.size()));
    }
}

