package kotlin.io.path;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u00042\u0006\u0010\t\u001A\u00020\u0004R\u0016\u0010\u0003\u001A\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001A\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlin/io/path/PathRelativizer;", "", "()V", "emptyPath", "Ljava/nio/file/Path;", "kotlin.jvm.PlatformType", "parentPath", "tryRelativeTo", "path", "base", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class PathRelativizer {
    public static final PathRelativizer INSTANCE;
    private static final Path emptyPath;
    private static final Path parentPath;

    static {
        PathRelativizer.INSTANCE = new PathRelativizer();
        PathRelativizer.emptyPath = PathTreeWalk..ExternalSyntheticApiModelOutline0.m("", new String[0]);
        PathRelativizer.parentPath = PathTreeWalk..ExternalSyntheticApiModelOutline0.m("..", new String[0]);
    }

    public final Path tryRelativeTo(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "path");
        Intrinsics.checkNotNullParameter(path1, "base");
        Path path2 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path1);
        Path path3 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0);
        Path path4 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path2, path3);
        int v = Math.min(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path2), PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path3));
        for(int v1 = 0; v1 < v; ++v1) {
            Path path5 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path2, v1);
            Path path6 = PathRelativizer.parentPath;
            if(!Intrinsics.areEqual(path5, path6)) {
                break;
            }
            if(!Intrinsics.areEqual(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path3, v1), path6)) {
                throw new IllegalArgumentException("Unable to compute relative path");
            }
        }
        if(Intrinsics.areEqual(path3, path2) || !Intrinsics.areEqual(path2, PathRelativizer.emptyPath)) {
            String s = path4.toString();
            String s1 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path4));
            Intrinsics.checkNotNullExpressionValue(s1, "rn.fileSystem.separator");
            path3 = StringsKt.endsWith$default(s, s1, false, 2, null) ? PathTreeWalk..ExternalSyntheticApiModelOutline0.m(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path4), StringsKt.dropLast(s, PathTreeWalk..ExternalSyntheticApiModelOutline0.m(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path4)).length()), new String[0]) : path4;
        }
        Intrinsics.checkNotNullExpressionValue(path3, "r");
        return path3;
    }
}

