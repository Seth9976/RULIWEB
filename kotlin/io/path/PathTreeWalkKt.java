package kotlin.io.path;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\u001A%\u0010\u0000\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002¢\u0006\u0002\u0010\u0007\u001A\f\u0010\b\u001A\u00020\t*\u00020\nH\u0002¨\u0006\u000B"}, d2 = {"keyOf", "", "path", "Ljava/nio/file/Path;", "linkOptions", "", "Ljava/nio/file/LinkOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/lang/Object;", "createsCycle", "", "Lkotlin/io/path/PathNode;", "kotlin-stdlib-jdk7"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class PathTreeWalkKt {
    private static final boolean createsCycle(PathNode pathNode0) {
        for(PathNode pathNode1 = pathNode0.getParent(); pathNode1 != null; pathNode1 = pathNode1.getParent()) {
            if(pathNode1.getKey() == null || pathNode0.getKey() == null) {
                try {
                    if(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(pathNode1.getPath(), pathNode0.getPath())) {
                        return true;
                    }
                }
                catch(IOException | SecurityException unused_ex) {
                }
            }
            else if(Intrinsics.areEqual(pathNode1.getKey(), pathNode0.getKey())) {
                return true;
            }
        }
        return false;
    }

    private static final Object keyOf(Path path0, LinkOption[] arr_linkOption) {
        try {
            LinkOption[] arr_linkOption1 = (LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length);
            BasicFileAttributes basicFileAttributes0 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0, BasicFileAttributes.class, ((LinkOption[])Arrays.copyOf(arr_linkOption1, arr_linkOption1.length)));
            Intrinsics.checkNotNullExpressionValue(basicFileAttributes0, "readAttributes(this, A::class.java, *options)");
            return LinkFollowing..ExternalSyntheticApiModelOutline0.m(basicFileAttributes0);
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }
}

