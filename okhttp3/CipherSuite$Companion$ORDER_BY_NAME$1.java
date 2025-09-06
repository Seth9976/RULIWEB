package okhttp3;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003J\u0018\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"okhttp3/CipherSuite$Companion$ORDER_BY_NAME$1", "Ljava/util/Comparator;", "", "Lkotlin/Comparator;", "compare", "", "a", "b", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class CipherSuite.Companion.ORDER_BY_NAME.1 implements Comparator {
    @Override
    public int compare(Object object0, Object object1) {
        return this.compare(((String)object0), ((String)object1));
    }

    public int compare(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "a");
        Intrinsics.checkNotNullParameter(s1, "b");
        int v = Math.min(s.length(), s1.length());
        for(int v1 = 4; v1 < v; ++v1) {
            int v2 = s.charAt(v1);
            int v3 = s1.charAt(v1);
            if(v2 != v3) {
                return Intrinsics.compare(v2, v3) >= 0 ? 1 : -1;
            }
        }
        int v4 = s.length();
        int v5 = s1.length();
        if(v4 != v5) {
            return v4 >= v5 ? 1 : -1;
        }
        return 0;
    }
}

