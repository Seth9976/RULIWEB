package kotlin.io.path;

import java.nio.file.CopyOption;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001C\u0010\u0003\u001A\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lkotlin/io/path/DefaultCopyActionContext;", "Lkotlin/io/path/CopyActionContext;", "()V", "copyToIgnoringExistingDirectory", "Lkotlin/io/path/CopyActionResult;", "Ljava/nio/file/Path;", "target", "followLinks", "", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class DefaultCopyActionContext implements CopyActionContext {
    public static final DefaultCopyActionContext INSTANCE;

    static {
        DefaultCopyActionContext.INSTANCE = new DefaultCopyActionContext();
    }

    @Override  // kotlin.io.path.CopyActionContext
    public CopyActionResult copyToIgnoringExistingDirectory(Path path0, Path path1, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        LinkOption[] arr_linkOption = LinkFollowing.INSTANCE.toLinkOptions(z);
        LinkOption[] arr_linkOption1 = (LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length);
        if(!LinkFollowing..ExternalSyntheticApiModelOutline0.m(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption1, arr_linkOption1.length))) || !LinkFollowing..ExternalSyntheticApiModelOutline0.m(path1, ((LinkOption[])Arrays.copyOf(new LinkOption[]{LinkFollowing..ExternalSyntheticApiModelOutline0.m()}, 1)))) {
            CopyOption[] arr_copyOption = (CopyOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length);
            Intrinsics.checkNotNullExpressionValue(LinkFollowing..ExternalSyntheticApiModelOutline0.m(path0, path1, ((CopyOption[])Arrays.copyOf(arr_copyOption, arr_copyOption.length))), "copy(this, target, *options)");
        }
        return CopyActionResult.CONTINUE;
    }
}

