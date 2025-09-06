package okio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0018\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\u0006H\u0016J\u0010\u0010\r\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u0006H\u0016J\u0018\u0010\u000F\u001A\u00020\n2\u0006\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00020\bH\u0016J\u0018\u0010\u0012\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\u0006H\u0016J\u0018\u0010\u0013\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0016\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00060\u00152\u0006\u0010\u0010\u001A\u00020\u0006H\u0016J \u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00152\u0006\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0016\u001A\u00020\bH\u0002J\u0018\u0010\u0017\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00152\u0006\u0010\u0010\u001A\u00020\u0006H\u0016J\u0012\u0010\u0018\u001A\u0004\u0018\u00010\u00192\u0006\u0010\u000E\u001A\u00020\u0006H\u0016J\u0010\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J \u0010\u001C\u001A\u00020\u001B2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0018\u0010\u001D\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00020\bH\u0016J\u0010\u0010\u000B\u001A\u00020\u001E2\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\b\u0010\u001F\u001A\u00020 H\u0016J\f\u0010!\u001A\u00020\n*\u00020\u0006H\u0002J\f\u0010\"\u001A\u00020\n*\u00020\u0006H\u0002¨\u0006#"}, d2 = {"Lokio/JvmSystemFileSystem;", "Lokio/FileSystem;", "()V", "appendingSink", "Lokio/Sink;", "file", "Lokio/Path;", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", "path", "createDirectory", "dir", "mustCreate", "createSymlink", "delete", "list", "", "throwOnFailure", "listOrNull", "metadataOrNull", "Lokio/FileMetadata;", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "sink", "Lokio/Source;", "toString", "", "requireCreate", "requireExist", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public class JvmSystemFileSystem extends FileSystem {
    @Override  // okio.FileSystem
    public Sink appendingSink(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "file");
        if(z) {
            this.requireExist(path0);
        }
        return Okio.sink(path0.toFile(), true);
    }

    @Override  // okio.FileSystem
    public void atomicMove(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        if(!path0.toFile().renameTo(path1.toFile())) {
            throw new IOException("failed to move " + path0 + " to " + path1);
        }
    }

    @Override  // okio.FileSystem
    public Path canonicalize(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "path");
        File file0 = path0.toFile().getCanonicalFile();
        if(!file0.exists()) {
            throw new FileNotFoundException("no such file");
        }
        Intrinsics.checkNotNullExpressionValue(file0, "canonicalFile");
        return Companion.get$default(Path.Companion, file0, false, 1, null);
    }

    @Override  // okio.FileSystem
    public void createDirectory(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        if(!path0.toFile().mkdir()) {
            FileMetadata fileMetadata0 = this.metadataOrNull(path0);
            if(fileMetadata0 == null || !fileMetadata0.isDirectory()) {
                throw new IOException("failed to create directory: " + path0);
            }
            if(z) {
                throw new IOException(path0 + " already exist.");
            }
        }
    }

    @Override  // okio.FileSystem
    public void createSymlink(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        throw new IOException("unsupported");
    }

    @Override  // okio.FileSystem
    public void delete(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "path");
        File file0 = path0.toFile();
        if(!file0.delete()) {
            if(file0.exists()) {
                throw new IOException("failed to delete " + path0);
            }
            if(z) {
                throw new FileNotFoundException("no such file: " + path0);
            }
        }
    }

    private final List list(Path path0, boolean z) {
        File file0 = path0.toFile();
        String[] arr_s = file0.list();
        if(arr_s == null) {
            if(z) {
                if(!file0.exists()) {
                    throw new FileNotFoundException("no such file: " + path0);
                }
                throw new IOException("failed to list " + path0);
            }
            return null;
        }
        Collection collection0 = new ArrayList();
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            Intrinsics.checkNotNullExpressionValue(s, "it");
            collection0.add(path0.resolve(s));
        }
        CollectionsKt.sort(((List)collection0));
        return (List)collection0;
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
        Intrinsics.checkNotNullParameter(path0, "path");
        File file0 = path0.toFile();
        boolean z = file0.isFile();
        boolean z1 = file0.isDirectory();
        long v = file0.lastModified();
        long v1 = file0.length();
        return z || z1 || v != 0L || v1 != 0L || file0.exists() ? new FileMetadata(z, z1, null, v1, null, v, null, null, 0x80, null) : null;
    }

    @Override  // okio.FileSystem
    public FileHandle openReadOnly(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "file");
        return new JvmFileHandle(false, new RandomAccessFile(path0.toFile(), "r"));
    }

    @Override  // okio.FileSystem
    public FileHandle openReadWrite(Path path0, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(path0, "file");
        if(z && z1) {
            throw new IllegalArgumentException("Cannot require mustCreate and mustExist at the same time.");
        }
        if(z) {
            this.requireCreate(path0);
        }
        if(z1) {
            this.requireExist(path0);
        }
        return new JvmFileHandle(true, new RandomAccessFile(path0.toFile(), "rw"));
    }

    private final void requireCreate(Path path0) {
        if(this.exists(path0)) {
            throw new IOException(path0 + " already exists.");
        }
    }

    private final void requireExist(Path path0) {
        if(!this.exists(path0)) {
            throw new IOException(path0 + " doesn\'t exist.");
        }
    }

    @Override  // okio.FileSystem
    public Sink sink(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "file");
        if(z) {
            this.requireCreate(path0);
        }
        return Okio.sink$default(path0.toFile(), false, 1, null);
    }

    @Override  // okio.FileSystem
    public Source source(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "file");
        return Okio.source(path0.toFile());
    }

    @Override
    public String toString() {
        return "JvmSystemFileSystem";
    }
}

