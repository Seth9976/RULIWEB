package kotlin.io.path;

import java.nio.file.LinkOption;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\r\u001A\u00020\u000E¢\u0006\u0002\u0010\u000FJ\u0014\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\r\u001A\u00020\u000ER\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0014\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0014\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlin/io/path/LinkFollowing;", "", "()V", "followLinkOption", "", "Ljava/nio/file/LinkOption;", "[Ljava/nio/file/LinkOption;", "followVisitOption", "", "Ljava/nio/file/FileVisitOption;", "nofollowLinkOption", "nofollowVisitOption", "toLinkOptions", "followLinks", "", "(Z)[Ljava/nio/file/LinkOption;", "toVisitOptions", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class LinkFollowing {
    public static final LinkFollowing INSTANCE;
    private static final LinkOption[] followLinkOption;
    private static final Set followVisitOption;
    private static final LinkOption[] nofollowLinkOption;
    private static final Set nofollowVisitOption;

    static {
        LinkFollowing.INSTANCE = new LinkFollowing();
        LinkFollowing.nofollowLinkOption = new LinkOption[]{LinkFollowing..ExternalSyntheticApiModelOutline0.m()};
        LinkFollowing.followLinkOption = new LinkOption[0];
        LinkFollowing.nofollowVisitOption = SetsKt.emptySet();
        LinkFollowing.followVisitOption = SetsKt.setOf(LinkFollowing..ExternalSyntheticApiModelOutline0.m());
    }

    // 去混淆评级： 低(20)
    public final LinkOption[] toLinkOptions(boolean z) {
        return z ? LinkFollowing.followLinkOption : LinkFollowing.nofollowLinkOption;
    }

    // 去混淆评级： 低(20)
    public final Set toVisitOptions(boolean z) {
        return z ? LinkFollowing.followVisitOption : LinkFollowing.nofollowVisitOption;
    }
}

