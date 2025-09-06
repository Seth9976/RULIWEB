package kotlin.text.jdk8;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchNamedGroupCollection;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\u0017\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004H\u0087\u0002¨\u0006\u0005"}, d2 = {"get", "Lkotlin/text/MatchGroup;", "Lkotlin/text/MatchGroupCollection;", "name", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, pn = "kotlin.text", xi = 0x30)
public final class RegexExtensionsJDK8Kt {
    public static final MatchGroup get(MatchGroupCollection matchGroupCollection0, String s) {
        Intrinsics.checkNotNullParameter(matchGroupCollection0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        MatchNamedGroupCollection matchNamedGroupCollection0 = matchGroupCollection0 instanceof MatchNamedGroupCollection ? ((MatchNamedGroupCollection)matchGroupCollection0) : null;
        if(matchNamedGroupCollection0 == null) {
            throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
        }
        return matchNamedGroupCollection0.get(s);
    }
}

