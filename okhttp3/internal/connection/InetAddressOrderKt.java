package okhttp3.internal.connection;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilCommonKt;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u001A\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0004"}, d2 = {"reorderForHappyEyeballs", "", "Ljava/net/InetAddress;", "addresses", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class InetAddressOrderKt {
    public static final List reorderForHappyEyeballs(List list0) {
        Intrinsics.checkNotNullParameter(list0, "addresses");
        if(list0.size() >= 2) {
            ArrayList arrayList0 = new ArrayList();
            ArrayList arrayList1 = new ArrayList();
            for(Object object0: list0) {
                if(((InetAddress)object0) instanceof Inet6Address) {
                    arrayList0.add(object0);
                }
                else {
                    arrayList1.add(object0);
                }
            }
            Pair pair0 = new Pair(arrayList0, arrayList1);
            List list1 = (List)pair0.component1();
            List list2 = (List)pair0.component2();
            return list1.isEmpty() || list2.isEmpty() ? list0 : _UtilCommonKt.interleave(list1, list2);
        }
        return list0;
    }
}

