package kotlin.io.path;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u00022\u0006\u0010\u000F\u001A\u00020\u0010H\u0016J\u0014\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u0006\u001A\u00020\u0007J\u0018\u0010\u0013\u001A\u00020\r2\u0006\u0010\u0014\u001A\u00020\u00022\u0006\u0010\u000F\u001A\u00020\u0010H\u0016R\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00070\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\u0015"}, d2 = {"Lkotlin/io/path/DirectoryEntriesReader;", "Ljava/nio/file/SimpleFileVisitor;", "Ljava/nio/file/Path;", "followLinks", "", "(Z)V", "directoryNode", "Lkotlin/io/path/PathNode;", "entries", "Lkotlin/collections/ArrayDeque;", "getFollowLinks", "()Z", "preVisitDirectory", "Ljava/nio/file/FileVisitResult;", "dir", "attrs", "Ljava/nio/file/attribute/BasicFileAttributes;", "readEntries", "", "visitFile", "file", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class DirectoryEntriesReader extends SimpleFileVisitor {
    private PathNode directoryNode;
    private ArrayDeque entries;
    private final boolean followLinks;

    public DirectoryEntriesReader(boolean z) {
        this.followLinks = z;
        this.entries = new ArrayDeque();
    }

    public final boolean getFollowLinks() {
        return this.followLinks;
    }

    @Override
    public FileVisitResult preVisitDirectory(Object object0, BasicFileAttributes basicFileAttributes0) {
        return this.preVisitDirectory(((Path)object0), basicFileAttributes0);
    }

    public FileVisitResult preVisitDirectory(Path path0, BasicFileAttributes basicFileAttributes0) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        Intrinsics.checkNotNullParameter(basicFileAttributes0, "attrs");
        PathNode pathNode0 = new PathNode(path0, LinkFollowing..ExternalSyntheticApiModelOutline0.m(basicFileAttributes0), this.directoryNode);
        this.entries.add(pathNode0);
        FileVisitResult fileVisitResult0 = super.preVisitDirectory(path0, basicFileAttributes0);
        Intrinsics.checkNotNullExpressionValue(fileVisitResult0, "super.preVisitDirectory(dir, attrs)");
        return fileVisitResult0;
    }

    public final List readEntries(PathNode pathNode0) {
        Intrinsics.checkNotNullParameter(pathNode0, "directoryNode");
        this.directoryNode = pathNode0;
        LinkFollowing..ExternalSyntheticApiModelOutline0.m(pathNode0.getPath(), LinkFollowing.INSTANCE.toVisitOptions(this.followLinks), 1, this);
        this.entries.removeFirst();
        ArrayDeque arrayDeque0 = this.entries;
        this.entries = new ArrayDeque();
        return arrayDeque0;
    }

    @Override
    public FileVisitResult visitFile(Object object0, BasicFileAttributes basicFileAttributes0) {
        return this.visitFile(((Path)object0), basicFileAttributes0);
    }

    public FileVisitResult visitFile(Path path0, BasicFileAttributes basicFileAttributes0) {
        Intrinsics.checkNotNullParameter(path0, "file");
        Intrinsics.checkNotNullParameter(basicFileAttributes0, "attrs");
        PathNode pathNode0 = new PathNode(path0, null, this.directoryNode);
        this.entries.add(pathNode0);
        FileVisitResult fileVisitResult0 = super.visitFile(path0, basicFileAttributes0);
        Intrinsics.checkNotNullExpressionValue(fileVisitResult0, "super.visitFile(file, attrs)");
        return fileVisitResult0;
    }
}

