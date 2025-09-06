package okio;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import okio.internal.ResourceFileSystem;
import okio.internal._FileSystemKt;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000F\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 42\u00020\u0001:\u00014B\u0005\u00A2\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u001A\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\b\u0002\u0010\u0007\u001A\u00020\bH&J\u0018\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\u0006H&J\u0010\u0010\r\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u0006H&J\u0018\u0010\u000F\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\u0006H\u0016J\u000E\u0010\u0010\u001A\u00020\n2\u0006\u0010\u0011\u001A\u00020\u0006J\u0018\u0010\u0010\u001A\u00020\n2\u0006\u0010\u0011\u001A\u00020\u00062\b\b\u0002\u0010\u0012\u001A\u00020\bJ\u000E\u0010\u0013\u001A\u00020\n2\u0006\u0010\u0011\u001A\u00020\u0006J\u001A\u0010\u0013\u001A\u00020\n2\u0006\u0010\u0011\u001A\u00020\u00062\b\b\u0002\u0010\u0012\u001A\u00020\bH&J\u0018\u0010\u0014\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\u0006H&J\u000E\u0010\u0015\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\u0006J\u001A\u0010\u0015\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\u00062\b\b\u0002\u0010\u0007\u001A\u00020\bH&J\u000E\u0010\u0016\u001A\u00020\n2\u0006\u0010\u0017\u001A\u00020\u0006J\u001A\u0010\u0016\u001A\u00020\n2\u0006\u0010\u0017\u001A\u00020\u00062\b\b\u0002\u0010\u0007\u001A\u00020\bH\u0016J\u000E\u0010\u0018\u001A\u00020\b2\u0006\u0010\u000E\u001A\u00020\u0006J\u0016\u0010\u0019\u001A\b\u0012\u0004\u0012\u00020\u00060\u001A2\u0006\u0010\u0011\u001A\u00020\u0006H&J\u0018\u0010\u001B\u001A\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001A2\u0006\u0010\u0011\u001A\u00020\u0006H&J\u0014\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00060\u001D2\u0006\u0010\u0011\u001A\u00020\u0006J \u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00060\u001D2\u0006\u0010\u0011\u001A\u00020\u00062\b\b\u0002\u0010\u001E\u001A\u00020\bH\u0016J\u000E\u0010\u001F\u001A\u00020 2\u0006\u0010\u000E\u001A\u00020\u0006J\u0012\u0010!\u001A\u0004\u0018\u00010 2\u0006\u0010\u000E\u001A\u00020\u0006H&J\u0010\u0010\"\u001A\u00020#2\u0006\u0010\u0005\u001A\u00020\u0006H&J\u000E\u0010$\u001A\u00020#2\u0006\u0010\u0005\u001A\u00020\u0006J$\u0010$\u001A\u00020#2\u0006\u0010\u0005\u001A\u00020\u00062\b\b\u0002\u0010\u0012\u001A\u00020\b2\b\b\u0002\u0010\u0007\u001A\u00020\bH&J:\u0010%\u001A\u0002H&\"\u0004\b\u0000\u0010&2\u0006\u0010\u0005\u001A\u00020\u00062\u0017\u0010\'\u001A\u0013\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u0002H&0(\u00A2\u0006\u0002\b*H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b+\u0010,J\u000E\u0010-\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u001A\u0010-\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\b\u0002\u0010\u0012\u001A\u00020\bH&J\u0010\u0010\u000B\u001A\u00020.2\u0006\u0010\u0005\u001A\u00020\u0006H&JD\u0010/\u001A\u0002H&\"\u0004\b\u0000\u0010&2\u0006\u0010\u0005\u001A\u00020\u00062\b\b\u0002\u0010\u0012\u001A\u00020\b2\u0017\u00100\u001A\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u0002H&0(\u00A2\u0006\u0002\b*H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b2\u00103\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u00065"}, d2 = {"Lokio/FileSystem;", "", "()V", "appendingSink", "Lokio/Sink;", "file", "Lokio/Path;", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", "path", "copy", "createDirectories", "dir", "mustCreate", "createDirectory", "createSymlink", "delete", "deleteRecursively", "fileOrDirectory", "exists", "list", "", "listOrNull", "listRecursively", "Lkotlin/sequences/Sequence;", "followSymlinks", "metadata", "Lokio/FileMetadata;", "metadataOrNull", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "read", "T", "readerAction", "Lkotlin/Function1;", "Lokio/BufferedSource;", "Lkotlin/ExtensionFunctionType;", "-read", "(Lokio/Path;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sink", "Lokio/Source;", "write", "writerAction", "Lokio/BufferedSink;", "-write", "(Lokio/Path;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public abstract class FileSystem {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lokio/FileSystem$Companion;", "", "()V", "RESOURCES", "Lokio/FileSystem;", "SYSTEM", "SYSTEM_TEMPORARY_DIRECTORY", "Lokio/Path;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    public static final FileSystem RESOURCES;
    public static final FileSystem SYSTEM;
    public static final Path SYSTEM_TEMPORARY_DIRECTORY;

    public final Object -read(Path path0, Function1 function10) throws IOException {
        Object object0;
        Intrinsics.checkNotNullParameter(path0, "file");
        Intrinsics.checkNotNullParameter(function10, "readerAction");
        Closeable closeable0 = Okio.buffer(this.source(path0));
        Throwable throwable0 = null;
        try {
            object0 = function10.invoke(((BufferedSource)closeable0));
        }
        catch(Throwable throwable1) {
            throwable0 = throwable1;
            object0 = null;
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
        Intrinsics.checkNotNull(object0);
        return object0;
    }

    public final Object -write(Path path0, boolean z, Function1 function10) throws IOException {
        Object object0;
        Intrinsics.checkNotNullParameter(path0, "file");
        Intrinsics.checkNotNullParameter(function10, "writerAction");
        Closeable closeable0 = Okio.buffer(this.sink(path0, z));
        Throwable throwable0 = null;
        try {
            object0 = function10.invoke(((BufferedSink)closeable0));
        }
        catch(Throwable throwable1) {
            object0 = null;
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
        Intrinsics.checkNotNull(object0);
        return object0;
    }

    public static Object -write$default(FileSystem fileSystem0, Path path0, boolean z, Function1 function10, int v, Object object0) throws IOException {
        Object object1;
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: write");
        }
        if((v & 2) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(path0, "file");
        Intrinsics.checkNotNullParameter(function10, "writerAction");
        Closeable closeable0 = Okio.buffer(fileSystem0.sink(path0, z));
        Throwable throwable0 = null;
        try {
            object1 = function10.invoke(((BufferedSink)closeable0));
        }
        catch(Throwable throwable1) {
            object1 = null;
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
        Intrinsics.checkNotNull(object1);
        return object1;
    }

    static {
        JvmSystemFileSystem jvmSystemFileSystem0;
        FileSystem.Companion = new Companion(null);
        try {
            jvmSystemFileSystem0 = new NioSystemFileSystem();
        }
        catch(ClassNotFoundException unused_ex) {
            jvmSystemFileSystem0 = new JvmSystemFileSystem();
        }
        FileSystem.SYSTEM = jvmSystemFileSystem0;
        Intrinsics.checkNotNullExpressionValue("/data/user/0/com.ruliweb.www.ruliapp/cache", "getProperty(\"java.io.tmpdir\")");
        FileSystem.SYSTEM_TEMPORARY_DIRECTORY = okio.Path.Companion.get$default(Path.Companion, "/data/user/0/com.ruliweb.www.ruliapp/cache", false, 1, null);
        ClassLoader classLoader0 = ResourceFileSystem.class.getClassLoader();
        Intrinsics.checkNotNullExpressionValue(classLoader0, "ResourceFileSystem::class.java.classLoader");
        FileSystem.RESOURCES = new ResourceFileSystem(classLoader0, false);
    }

    public final Sink appendingSink(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "file");
        return this.appendingSink(path0, false);
    }

    public abstract Sink appendingSink(Path arg1, boolean arg2) throws IOException;

    public static Sink appendingSink$default(FileSystem fileSystem0, Path path0, boolean z, int v, Object object0) throws IOException {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: appendingSink");
        }
        if((v & 2) != 0) {
            z = false;
        }
        return fileSystem0.appendingSink(path0, z);
    }

    public abstract void atomicMove(Path arg1, Path arg2) throws IOException;

    public abstract Path canonicalize(Path arg1) throws IOException;

    public void copy(Path path0, Path path1) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        _FileSystemKt.commonCopy(this, path0, path1);
    }

    public final void createDirectories(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "dir");
        this.createDirectories(path0, false);
    }

    public final void createDirectories(Path path0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "dir");
        _FileSystemKt.commonCreateDirectories(this, path0, z);
    }

    public static void createDirectories$default(FileSystem fileSystem0, Path path0, boolean z, int v, Object object0) throws IOException {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectories");
        }
        if((v & 2) != 0) {
            z = false;
        }
        fileSystem0.createDirectories(path0, z);
    }

    public final void createDirectory(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "dir");
        this.createDirectory(path0, false);
    }

    public abstract void createDirectory(Path arg1, boolean arg2) throws IOException;

    public static void createDirectory$default(FileSystem fileSystem0, Path path0, boolean z, int v, Object object0) throws IOException {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectory");
        }
        if((v & 2) != 0) {
            z = false;
        }
        fileSystem0.createDirectory(path0, z);
    }

    public abstract void createSymlink(Path arg1, Path arg2) throws IOException;

    public final void delete(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "path");
        this.delete(path0, false);
    }

    public abstract void delete(Path arg1, boolean arg2) throws IOException;

    public static void delete$default(FileSystem fileSystem0, Path path0, boolean z, int v, Object object0) throws IOException {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
        }
        if((v & 2) != 0) {
            z = false;
        }
        fileSystem0.delete(path0, z);
    }

    public final void deleteRecursively(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "fileOrDirectory");
        this.deleteRecursively(path0, false);
    }

    public void deleteRecursively(Path path0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "fileOrDirectory");
        _FileSystemKt.commonDeleteRecursively(this, path0, z);
    }

    public static void deleteRecursively$default(FileSystem fileSystem0, Path path0, boolean z, int v, Object object0) throws IOException {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteRecursively");
        }
        if((v & 2) != 0) {
            z = false;
        }
        fileSystem0.deleteRecursively(path0, z);
    }

    public final boolean exists(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "path");
        return _FileSystemKt.commonExists(this, path0);
    }

    public abstract List list(Path arg1) throws IOException;

    public abstract List listOrNull(Path arg1);

    public final Sequence listRecursively(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        return this.listRecursively(path0, false);
    }

    public Sequence listRecursively(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        return _FileSystemKt.commonListRecursively(this, path0, z);
    }

    public static Sequence listRecursively$default(FileSystem fileSystem0, Path path0, boolean z, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listRecursively");
        }
        if((v & 2) != 0) {
            z = false;
        }
        return fileSystem0.listRecursively(path0, z);
    }

    public final FileMetadata metadata(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "path");
        return _FileSystemKt.commonMetadata(this, path0);
    }

    public abstract FileMetadata metadataOrNull(Path arg1) throws IOException;

    public abstract FileHandle openReadOnly(Path arg1) throws IOException;

    public final FileHandle openReadWrite(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "file");
        return this.openReadWrite(path0, false, false);
    }

    public abstract FileHandle openReadWrite(Path arg1, boolean arg2, boolean arg3) throws IOException;

    public static FileHandle openReadWrite$default(FileSystem fileSystem0, Path path0, boolean z, boolean z1, int v, Object object0) throws IOException {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openReadWrite");
        }
        if((v & 2) != 0) {
            z = false;
        }
        if((v & 4) != 0) {
            z1 = false;
        }
        return fileSystem0.openReadWrite(path0, z, z1);
    }

    public final Sink sink(Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(path0, "file");
        return this.sink(path0, false);
    }

    public abstract Sink sink(Path arg1, boolean arg2) throws IOException;

    public static Sink sink$default(FileSystem fileSystem0, Path path0, boolean z, int v, Object object0) throws IOException {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
        }
        if((v & 2) != 0) {
            z = false;
        }
        return fileSystem0.sink(path0, z);
    }

    public abstract Source source(Path arg1) throws IOException;
}

