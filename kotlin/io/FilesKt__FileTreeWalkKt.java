package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u0014\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0004\u001A\n\u0010\u0005\u001A\u00020\u0001*\u00020\u0002\u001A\n\u0010\u0006\u001A\u00020\u0001*\u00020\u0002¨\u0006\u0007"}, d2 = {"walk", "Lkotlin/io/FileTreeWalk;", "Ljava/io/File;", "direction", "Lkotlin/io/FileWalkDirection;", "walkBottomUp", "walkTopDown", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/io/FilesKt")
class FilesKt__FileTreeWalkKt extends FilesKt__FileReadWriteKt {
    public static final FileTreeWalk walk(File file0, FileWalkDirection fileWalkDirection0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(fileWalkDirection0, "direction");
        return new FileTreeWalk(file0, fileWalkDirection0);
    }

    public static FileTreeWalk walk$default(File file0, FileWalkDirection fileWalkDirection0, int v, Object object0) {
        if((v & 1) != 0) {
            fileWalkDirection0 = FileWalkDirection.TOP_DOWN;
        }
        return FilesKt.walk(file0, fileWalkDirection0);
    }

    public static final FileTreeWalk walkBottomUp(File file0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        return FilesKt.walk(file0, FileWalkDirection.BOTTOM_UP);
    }

    public static final FileTreeWalk walkTopDown(File file0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        return FilesKt.walk(file0, FileWalkDirection.TOP_DOWN);
    }
}

