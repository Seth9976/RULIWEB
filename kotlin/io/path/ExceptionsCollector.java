package kotlin.io.path;

import java.nio.file.FileSystemException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0015\u001A\u00020\u00162\n\u0010\u0017\u001A\u00060\u0007j\u0002`\bJ\u000E\u0010\u0018\u001A\u00020\u00162\u0006\u0010\u0019\u001A\u00020\fJ\u000E\u0010\u001A\u001A\u00020\u00162\u0006\u0010\u0019\u001A\u00020\fR\u001B\u0010\u0005\u001A\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0006¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\u000B\u001A\u0004\u0018\u00010\fX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010R\u001E\u0010\u0012\u001A\u00020\u00032\u0006\u0010\u0011\u001A\u00020\u0003@BX\u0086\u000E¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014¨\u0006\u001B"}, d2 = {"Lkotlin/io/path/ExceptionsCollector;", "", "limit", "", "(I)V", "collectedExceptions", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "getCollectedExceptions", "()Ljava/util/List;", "path", "Ljava/nio/file/Path;", "getPath", "()Ljava/nio/file/Path;", "setPath", "(Ljava/nio/file/Path;)V", "<set-?>", "totalExceptions", "getTotalExceptions", "()I", "collect", "", "exception", "enterEntry", "name", "exitEntry", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class ExceptionsCollector {
    private final List collectedExceptions;
    private final int limit;
    private Path path;
    private int totalExceptions;

    public ExceptionsCollector() {
        this(0, 1, null);
    }

    public ExceptionsCollector(int v) {
        this.limit = v;
        this.collectedExceptions = new ArrayList();
    }

    public ExceptionsCollector(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 0x40;
        }
        this(v);
    }

    public final void collect(Exception exception0) {
        Intrinsics.checkNotNullParameter(exception0, "exception");
        ++this.totalExceptions;
        if(this.collectedExceptions.size() < this.limit) {
            if(this.path != null) {
                Throwable throwable0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(LinkFollowing..ExternalSyntheticApiModelOutline0.m(String.valueOf(this.path)), exception0);
                Intrinsics.checkNotNull(throwable0, "null cannot be cast to non-null type java.nio.file.FileSystemException");
                exception0 = (FileSystemException)throwable0;
            }
            this.collectedExceptions.add(exception0);
        }
    }

    public final void enterEntry(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "name");
        this.path = this.path == null ? null : LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.path, path0);
    }

    public final void exitEntry(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "name");
        Path path1 = null;
        if(!Intrinsics.areEqual(path0, (this.path == null ? null : LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.path)))) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        Path path2 = this.path;
        if(path2 != null) {
            path1 = LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(path2);
        }
        this.path = path1;
    }

    public final List getCollectedExceptions() {
        return this.collectedExceptions;
    }

    public final Path getPath() {
        return this.path;
    }

    public final int getTotalExceptions() {
        return this.totalExceptions;
    }

    public final void setPath(Path path0) {
        this.path = path0;
    }
}

