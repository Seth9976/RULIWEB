package okio;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.internal.FixedLengthSource;
import okio.internal.ZipEntry;
import okio.internal.ZipKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000B\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \'2\u00020\u0001:\u0001\'B5\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0001\u0012\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001A\u0004\u0018\u00010\t\u00A2\u0006\u0002\u0010\nJ\u0018\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u00032\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\u0018\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00032\u0006\u0010\u0013\u001A\u00020\u0003H\u0016J\u0010\u0010\u0014\u001A\u00020\u00032\u0006\u0010\u0015\u001A\u00020\u0003H\u0016J\u0010\u0010\u0016\u001A\u00020\u00032\u0006\u0010\u0015\u001A\u00020\u0003H\u0002J\u0018\u0010\u0017\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u00032\u0006\u0010\u0019\u001A\u00020\u000FH\u0016J\u0018\u0010\u001A\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00032\u0006\u0010\u0013\u001A\u00020\u0003H\u0016J\u0018\u0010\u001B\u001A\u00020\u00112\u0006\u0010\u0015\u001A\u00020\u00032\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\u0016\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00030\u001D2\u0006\u0010\u0018\u001A\u00020\u0003H\u0016J \u0010\u001C\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001D2\u0006\u0010\u0018\u001A\u00020\u00032\u0006\u0010\u001E\u001A\u00020\u000FH\u0002J\u0018\u0010\u001F\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001D2\u0006\u0010\u0018\u001A\u00020\u0003H\u0016J\u0012\u0010 \u001A\u0004\u0018\u00010!2\u0006\u0010\u0015\u001A\u00020\u0003H\u0016J\u0010\u0010\"\u001A\u00020#2\u0006\u0010\r\u001A\u00020\u0003H\u0016J \u0010$\u001A\u00020#2\u0006\u0010\r\u001A\u00020\u00032\u0006\u0010\u0019\u001A\u00020\u000F2\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\u0018\u0010%\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u00032\u0006\u0010\u0019\u001A\u00020\u000FH\u0016J\u0010\u0010\u0012\u001A\u00020&2\u0006\u0010\r\u001A\u00020\u0003H\u0016R\u0010\u0010\b\u001A\u0004\u0018\u00010\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006("}, d2 = {"Lokio/ZipFileSystem;", "Lokio/FileSystem;", "zipPath", "Lokio/Path;", "fileSystem", "entries", "", "Lokio/internal/ZipEntry;", "comment", "", "(Lokio/Path;Lokio/FileSystem;Ljava/util/Map;Ljava/lang/String;)V", "appendingSink", "Lokio/Sink;", "file", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", "path", "canonicalizeInternal", "createDirectory", "dir", "mustCreate", "createSymlink", "delete", "list", "", "throwOnFailure", "listOrNull", "metadataOrNull", "Lokio/FileMetadata;", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "sink", "Lokio/Source;", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ZipFileSystem extends FileSystem {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lokio/ZipFileSystem$Companion;", "", "()V", "ROOT", "Lokio/Path;", "getROOT", "()Lokio/Path;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Path getROOT() {
            return ZipFileSystem.ROOT;
        }
    }

    private static final Companion Companion;
    @Deprecated
    private static final Path ROOT;
    private final String comment;
    private final Map entries;
    private final FileSystem fileSystem;
    private final Path zipPath;

    static {
        ZipFileSystem.Companion = new Companion(null);
        ZipFileSystem.ROOT = okio.Path.Companion.get$default(Path.Companion, "/", false, 1, null);
    }

    public ZipFileSystem(Path path0, FileSystem fileSystem0, Map map0, String s) {
        Intrinsics.checkNotNullParameter(path0, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem0, "fileSystem");
        Intrinsics.checkNotNullParameter(map0, "entries");
        super();
        this.zipPath = path0;
        this.fileSystem = fileSystem0;
        this.entries = map0;
        this.comment = s;
    }

    @Override  // okio.FileSystem
    public Sink appendingSink(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "file");
        throw new IOException("zip file systems are read-only");
    }

    @Override  // okio.FileSystem
    public void atomicMove(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        throw new IOException("zip file systems are read-only");
    }

    @Override  // okio.FileSystem
    public Path canonicalize(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "path");
        Path path1 = this.canonicalizeInternal(path0);
        if(!this.entries.containsKey(path1)) {
            throw new FileNotFoundException(String.valueOf(path0));
        }
        return path1;
    }

    private final Path canonicalizeInternal(Path path0) {
        return ZipFileSystem.ROOT.resolve(path0, true);
    }

    @Override  // okio.FileSystem
    public void createDirectory(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        throw new IOException("zip file systems are read-only");
    }

    @Override  // okio.FileSystem
    public void createSymlink(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        throw new IOException("zip file systems are read-only");
    }

    @Override  // okio.FileSystem
    public void delete(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "path");
        throw new IOException("zip file systems are read-only");
    }

    private final List list(Path path0, boolean z) {
        Path path1 = this.canonicalizeInternal(path0);
        ZipEntry zipEntry0 = (ZipEntry)this.entries.get(path1);
        if(zipEntry0 == null) {
            if(z) {
                throw new IOException("not a directory: " + path0);
            }
            return null;
        }
        return CollectionsKt.toList(zipEntry0.getChildren());
    }

    @Override  // okio.FileSystem
    public List list(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        List list0 = this.list(path0, true);
        Intrinsics.checkNotNull(list0);
        return list0;
    }

    @Override  // okio.FileSystem
    public List listOrNull(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        return this.list(path0, false);
    }

    @Override  // okio.FileSystem
    public FileMetadata metadataOrNull(Path path0) {
        BufferedSource bufferedSource0;
        Intrinsics.checkNotNullParameter(path0, "path");
        Path path1 = this.canonicalizeInternal(path0);
        ZipEntry zipEntry0 = (ZipEntry)this.entries.get(path1);
        Throwable throwable0 = null;
        if(zipEntry0 == null) {
            return null;
        }
        FileMetadata fileMetadata0 = new FileMetadata(!zipEntry0.isDirectory(), zipEntry0.isDirectory(), null, (zipEntry0.isDirectory() ? null : zipEntry0.getSize()), null, zipEntry0.getLastModifiedAtMillis(), null, null, 0x80, null);
        if(zipEntry0.getOffset() == -1L) {
            return fileMetadata0;
        }
        Closeable closeable0 = this.fileSystem.openReadOnly(this.zipPath);
        try {
            bufferedSource0 = Okio.buffer(((FileHandle)closeable0).source(zipEntry0.getOffset()));
        }
        catch(Throwable throwable1) {
            throwable0 = throwable1;
            bufferedSource0 = null;
        }
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(Throwable throwable2) {
                if(throwable0 == null) {
                    throwable0 = throwable2;
                }
                else {
                    ExceptionsKt.addSuppressed(throwable0, throwable2);
                }
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
        Intrinsics.checkNotNull(bufferedSource0);
        return ZipKt.readLocalHeader(bufferedSource0, fileMetadata0);
    }

    @Override  // okio.FileSystem
    public FileHandle openReadOnly(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "file");
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override  // okio.FileSystem
    public FileHandle openReadWrite(Path path0, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(path0, "file");
        throw new IOException("zip entries are not writable");
    }

    @Override  // okio.FileSystem
    public Sink sink(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "file");
        throw new IOException("zip file systems are read-only");
    }

    @Override  // okio.FileSystem
    public Source source(Path path0) throws IOException {
        BufferedSource bufferedSource0;
        Intrinsics.checkNotNullParameter(path0, "file");
        Path path1 = this.canonicalizeInternal(path0);
        ZipEntry zipEntry0 = (ZipEntry)this.entries.get(path1);
        if(zipEntry0 == null) {
            throw new FileNotFoundException("no such file: " + path0);
        }
        Closeable closeable0 = this.fileSystem.openReadOnly(this.zipPath);
        Throwable throwable0 = null;
        try {
            bufferedSource0 = Okio.buffer(((FileHandle)closeable0).source(zipEntry0.getOffset()));
        }
        catch(Throwable throwable1) {
            bufferedSource0 = null;
            throwable0 = throwable1;
        }
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(Throwable throwable2) {
                if(throwable0 == null) {
                    throwable0 = throwable2;
                }
                else {
                    ExceptionsKt.addSuppressed(throwable0, throwable2);
                }
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
        Intrinsics.checkNotNull(bufferedSource0);
        ZipKt.skipLocalHeader(bufferedSource0);
        return zipEntry0.getCompressionMethod() == 0 ? new FixedLengthSource(bufferedSource0, zipEntry0.getSize(), true) : new FixedLengthSource(new InflaterSource(new FixedLengthSource(bufferedSource0, zipEntry0.getCompressedSize(), true), new Inflater(true)), zipEntry0.getSize(), false);
    }
}

