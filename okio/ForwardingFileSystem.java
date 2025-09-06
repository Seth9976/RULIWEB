package okio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0001\u00A2\u0006\u0002\u0010\u0003J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\b2\u0006\u0010\u000E\u001A\u00020\bH\u0016J\u0010\u0010\u000F\u001A\u00020\b2\u0006\u0010\u0010\u001A\u00020\bH\u0016J\u0018\u0010\u0011\u001A\u00020\f2\u0006\u0010\u0012\u001A\u00020\b2\u0006\u0010\u0013\u001A\u00020\nH\u0016J\u0018\u0010\u0014\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\b2\u0006\u0010\u000E\u001A\u00020\bH\u0016J\u0018\u0010\u0015\u001A\u00020\f2\u0006\u0010\u0010\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0016\u0010\u0016\u001A\b\u0012\u0004\u0012\u00020\b0\u00172\u0006\u0010\u0012\u001A\u00020\bH\u0016J\u0018\u0010\u0018\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00172\u0006\u0010\u0012\u001A\u00020\bH\u0016J\u001E\u0010\u0019\u001A\b\u0012\u0004\u0012\u00020\b0\u001A2\u0006\u0010\u0012\u001A\u00020\b2\u0006\u0010\u001B\u001A\u00020\nH\u0016J\u0012\u0010\u001C\u001A\u0004\u0018\u00010\u001D2\u0006\u0010\u0010\u001A\u00020\bH\u0016J \u0010\u001E\u001A\u00020\b2\u0006\u0010\u0010\u001A\u00020\b2\u0006\u0010\u001F\u001A\u00020 2\u0006\u0010!\u001A\u00020 H\u0016J\u0018\u0010\"\u001A\u00020\b2\u0006\u0010\u0010\u001A\u00020\b2\u0006\u0010\u001F\u001A\u00020 H\u0016J\u0010\u0010#\u001A\u00020$2\u0006\u0010\u0007\u001A\u00020\bH\u0016J \u0010%\u001A\u00020$2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\u0013\u001A\u00020\n2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010&\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\u0013\u001A\u00020\nH\u0016J\u0010\u0010\r\u001A\u00020\'2\u0006\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010(\u001A\u00020 H\u0016R\u0013\u0010\u0002\u001A\u00020\u00018\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0004\u00A8\u0006)"}, d2 = {"Lokio/ForwardingFileSystem;", "Lokio/FileSystem;", "delegate", "(Lokio/FileSystem;)V", "()Lokio/FileSystem;", "appendingSink", "Lokio/Sink;", "file", "Lokio/Path;", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", "path", "createDirectory", "dir", "mustCreate", "createSymlink", "delete", "list", "", "listOrNull", "listRecursively", "Lkotlin/sequences/Sequence;", "followSymlinks", "metadataOrNull", "Lokio/FileMetadata;", "onPathParameter", "functionName", "", "parameterName", "onPathResult", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "sink", "Lokio/Source;", "toString", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public abstract class ForwardingFileSystem extends FileSystem {
    private final FileSystem delegate;

    public ForwardingFileSystem(FileSystem fileSystem0) {
        Intrinsics.checkNotNullParameter(fileSystem0, "delegate");
        super();
        this.delegate = fileSystem0;
    }

    @Override  // okio.FileSystem
    public Sink appendingSink(Path path0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "file");
        Path path1 = this.onPathParameter(path0, "appendingSink", "file");
        return this.delegate.appendingSink(path1, z);
    }

    @Override  // okio.FileSystem
    public void atomicMove(Path path0, Path path1) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        Path path2 = this.onPathParameter(path0, "atomicMove", "source");
        Path path3 = this.onPathParameter(path1, "atomicMove", "target");
        this.delegate.atomicMove(path2, path3);
    }

    @Override  // okio.FileSystem
    public Path canonicalize(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "path");
        Path path1 = this.onPathParameter(path0, "canonicalize", "path");
        return this.onPathResult(this.delegate.canonicalize(path1), "canonicalize");
    }

    @Override  // okio.FileSystem
    public void createDirectory(Path path0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "dir");
        Path path1 = this.onPathParameter(path0, "createDirectory", "dir");
        this.delegate.createDirectory(path1, z);
    }

    @Override  // okio.FileSystem
    public void createSymlink(Path path0, Path path1) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        Path path2 = this.onPathParameter(path0, "createSymlink", "source");
        Path path3 = this.onPathParameter(path1, "createSymlink", "target");
        this.delegate.createSymlink(path2, path3);
    }

    public final FileSystem delegate() {
        return this.delegate;
    }

    @Override  // okio.FileSystem
    public void delete(Path path0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "path");
        Path path1 = this.onPathParameter(path0, "delete", "path");
        this.delegate.delete(path1, z);
    }

    @Override  // okio.FileSystem
    public List list(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "dir");
        Path path1 = this.onPathParameter(path0, "list", "dir");
        Iterable iterable0 = this.delegate.list(path1);
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            collection0.add(this.onPathResult(((Path)object0), "list"));
        }
        CollectionsKt.sort(((List)collection0));
        return (List)collection0;
    }

    @Override  // okio.FileSystem
    public List listOrNull(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        Path path1 = this.onPathParameter(path0, "listOrNull", "dir");
        List list0 = this.delegate.listOrNull(path1);
        if(list0 == null) {
            return null;
        }
        Collection collection0 = new ArrayList();
        for(Object object0: list0) {
            collection0.add(this.onPathResult(((Path)object0), "listOrNull"));
        }
        CollectionsKt.sort(((List)collection0));
        return (List)collection0;
    }

    @Override  // okio.FileSystem
    public Sequence listRecursively(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        Path path1 = this.onPathParameter(path0, "listRecursively", "dir");
        return SequencesKt.map(this.delegate.listRecursively(path1, z), new Function1() {
            {
                ForwardingFileSystem.this = forwardingFileSystem0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Path)object0));
            }

            public final Path invoke(Path path0) {
                Intrinsics.checkNotNullParameter(path0, "it");
                return ForwardingFileSystem.this.onPathResult(path0, "listRecursively");
            }
        });
    }

    @Override  // okio.FileSystem
    public FileMetadata metadataOrNull(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "path");
        Path path1 = this.onPathParameter(path0, "metadataOrNull", "path");
        FileMetadata fileMetadata0 = this.delegate.metadataOrNull(path1);
        if(fileMetadata0 == null) {
            return null;
        }
        return fileMetadata0.getSymlinkTarget() == null ? fileMetadata0 : FileMetadata.copy$default(fileMetadata0, false, false, this.onPathResult(fileMetadata0.getSymlinkTarget(), "metadataOrNull"), null, null, null, null, null, 0xFB, null);
    }

    public Path onPathParameter(Path path0, String s, String s1) {
        Intrinsics.checkNotNullParameter(path0, "path");
        Intrinsics.checkNotNullParameter(s, "functionName");
        Intrinsics.checkNotNullParameter(s1, "parameterName");
        return path0;
    }

    public Path onPathResult(Path path0, String s) {
        Intrinsics.checkNotNullParameter(path0, "path");
        Intrinsics.checkNotNullParameter(s, "functionName");
        return path0;
    }

    @Override  // okio.FileSystem
    public FileHandle openReadOnly(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "file");
        Path path1 = this.onPathParameter(path0, "openReadOnly", "file");
        return this.delegate.openReadOnly(path1);
    }

    @Override  // okio.FileSystem
    public FileHandle openReadWrite(Path path0, boolean z, boolean z1) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "file");
        Path path1 = this.onPathParameter(path0, "openReadWrite", "file");
        return this.delegate.openReadWrite(path1, z, z1);
    }

    @Override  // okio.FileSystem
    public Sink sink(Path path0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "file");
        Path path1 = this.onPathParameter(path0, "sink", "file");
        return this.delegate.sink(path1, z);
    }

    @Override  // okio.FileSystem
    public Source source(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "file");
        Path path1 = this.onPathParameter(path0, "source", "file");
        return this.delegate.source(path1);
    }

    @Override
    public String toString() {
        return Reflection.getOrCreateKotlinClass(this.getClass()).getSimpleName() + '(' + this.delegate + ')';
    }
}

