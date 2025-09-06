package kotlin.io.path;

import java.nio.file.Path;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u000B\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0001\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\u0006R\"\u0010\u0007\u001A\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\bX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\t\u0010\n\"\u0004\b\u000B\u0010\fR\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lkotlin/io/path/PathNode;", "", "path", "Ljava/nio/file/Path;", "key", "parent", "(Ljava/nio/file/Path;Ljava/lang/Object;Lkotlin/io/path/PathNode;)V", "contentIterator", "", "getContentIterator", "()Ljava/util/Iterator;", "setContentIterator", "(Ljava/util/Iterator;)V", "getKey", "()Ljava/lang/Object;", "getParent", "()Lkotlin/io/path/PathNode;", "getPath", "()Ljava/nio/file/Path;", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class PathNode {
    private Iterator contentIterator;
    private final Object key;
    private final PathNode parent;
    private final Path path;

    public PathNode(Path path0, Object object0, PathNode pathNode0) {
        Intrinsics.checkNotNullParameter(path0, "path");
        super();
        this.path = path0;
        this.key = object0;
        this.parent = pathNode0;
    }

    public final Iterator getContentIterator() {
        return this.contentIterator;
    }

    public final Object getKey() {
        return this.key;
    }

    public final PathNode getParent() {
        return this.parent;
    }

    public final Path getPath() {
        return this.path;
    }

    public final void setContentIterator(Iterator iterator0) {
        this.contentIterator = iterator0;
    }
}

