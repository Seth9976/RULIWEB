package kotlin.internal.jdk8;

import android.os.Build.VERSION;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.text.MatchGroup;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u000FB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H\u0016J\u001A\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0010\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002¨\u0006\u0010"}, d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations;", "Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "()V", "defaultPlatformRandom", "Lkotlin/random/Random;", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "sdkIsNullOrAtLeast", "", "version", "", "ReflectSdkVersion", "kotlin-stdlib-jdk8"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public class JDK8PlatformImplementations extends JDK7PlatformImplementations {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001A\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations$ReflectSdkVersion;", "", "()V", "sdkVersion", "", "Ljava/lang/Integer;", "kotlin-stdlib-jdk8"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class ReflectSdkVersion {
        public static final ReflectSdkVersion INSTANCE;
        public static final Integer sdkVersion;

        static {
            ReflectSdkVersion.INSTANCE = new ReflectSdkVersion();
            Integer integer0 = null;
            Integer integer1 = Build.VERSION.SDK_INT;
            Integer integer2 = integer1 instanceof Integer ? integer1 : null;
            if(integer2 != null && integer2.intValue() > 0) {
                integer0 = integer2;
            }
            ReflectSdkVersion.sdkVersion = integer0;
        }
    }

    // 去混淆评级： 低(30)
    @Override  // kotlin.internal.PlatformImplementations
    public Random defaultPlatformRandom() {
        return super.defaultPlatformRandom();
    }

    @Override  // kotlin.internal.PlatformImplementations
    public MatchGroup getMatchResultNamedGroup(MatchResult matchResult0, String s) {
        Intrinsics.checkNotNullParameter(matchResult0, "matchResult");
        Intrinsics.checkNotNullParameter(s, "name");
        Matcher matcher0 = matchResult0 instanceof Matcher ? ((Matcher)matchResult0) : null;
        if(matcher0 == null) {
            throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
        }
        IntRange intRange0 = new IntRange(LinkFollowing..ExternalSyntheticApiModelOutline0.m(matcher0, s), LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(matcher0, s) - 1);
        if(((int)intRange0.getStart()) >= 0) {
            String s1 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(matcher0, s);
            Intrinsics.checkNotNullExpressionValue(s1, "matcher.group(name)");
            return new MatchGroup(s1, intRange0);
        }
        return null;
    }

    private final boolean sdkIsNullOrAtLeast(int v) [...] // 潜在的解密器
}

