package okhttp3;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \t2\u00020\u0001:\u0003\b\t\nJ\u0018\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000BÀ\u0006\u0001"}, d2 = {"Lokhttp3/AsyncDns;", "", "query", "", "hostname", "", "callback", "Lokhttp3/AsyncDns$Callback;", "Callback", "Companion", "DnsClass", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public interface AsyncDns {
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001C\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\n\u0010\u0006\u001A\u00060\u0007j\u0002`\bH&J\u001E\u0010\t\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\f\u0010\n\u001A\b\u0012\u0004\u0012\u00020\f0\u000BH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lokhttp3/AsyncDns$Callback;", "", "onFailure", "", "hostname", "", "e", "Ljava/io/IOException;", "Lokio/IOException;", "onResponse", "addresses", "", "Ljava/net/InetAddress;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface Callback {
        void onFailure(String arg1, IOException arg2);

        void onResponse(String arg1, List arg2);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001F\u0010\u0006\u001A\u00020\u00072\u0012\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\n¢\u0006\u0002\u0010\u000BR\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokhttp3/AsyncDns$Companion;", "", "()V", "TYPE_A", "", "TYPE_AAAA", "toDns", "Lokhttp3/Dns;", "asyncDns", "", "Lokhttp3/AsyncDns;", "([Lokhttp3/AsyncDns;)Lokhttp3/Dns;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE = null;
        public static final int TYPE_A = 1;
        public static final int TYPE_AAAA = 28;

        // 检测为 Lambda 实现
        public static List $r8$lambda$3RJYQJeJV97k3dG5UF8VuztPz5g(AsyncDns[] arr_asyncDns, String s) [...]

        static {
            Companion.$$INSTANCE = new Companion();
        }

        public final Dns toDns(AsyncDns[] arr_asyncDns) {
            Intrinsics.checkNotNullParameter(arr_asyncDns, "asyncDns");
            return (String s) -> Companion.toDns$lambda-2(arr_asyncDns, s);
        }

        private static final List toDns$lambda-2(AsyncDns[] arr_asyncDns, String s) {
            Intrinsics.checkNotNullParameter(arr_asyncDns, "$asyncDns");
            Intrinsics.checkNotNullParameter(s, "hostname");
            List list0 = new ArrayList();
            List list1 = new ArrayList();
            CountDownLatch countDownLatch0 = new CountDownLatch(arr_asyncDns.length);
            for(int v = 0; v < arr_asyncDns.length; ++v) {
                arr_asyncDns[v].query(s, new Callback() {
                    @Override  // okhttp3.AsyncDns$Callback
                    public void onFailure(String s, IOException iOException0) {
                        Intrinsics.checkNotNullParameter(s, "hostname");
                        Intrinsics.checkNotNullParameter(iOException0, "e");
                        synchronized(list1) {
                            list1.add(iOException0);
                        }
                        countDownLatch0.countDown();
                    }

                    @Override  // okhttp3.AsyncDns$Callback
                    public void onResponse(String s, List list0) {
                        Intrinsics.checkNotNullParameter(s, "hostname");
                        Intrinsics.checkNotNullParameter(list0, "addresses");
                        synchronized(list0) {
                            list0.addAll(list0);
                        }
                        countDownLatch0.countDown();
                    }
                });
            }
            countDownLatch0.await();
            if(list0.isEmpty()) {
                IOException iOException0 = (IOException)CollectionsKt.firstOrNull(list1);
                if(iOException0 == null) {
                    iOException0 = new UnknownHostException("No results for " + s);
                }
                for(Object object0: CollectionsKt.drop(list1, 1)) {
                    ExceptionsKt.addSuppressed(iOException0, ((IOException)object0));
                }
                throw iOException0;
            }
            return list0;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lokhttp3/AsyncDns$DnsClass;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "IPV4", "IPV6", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static enum DnsClass {
        IPV4(1),
        IPV6(28);

        private final int type;

        private static final DnsClass[] $values() [...] // Inlined contents

        private DnsClass(int v1) {
            this.type = v1;
        }

        public final int getType() {
            return this.type;
        }
    }

    public static final Companion Companion = null;
    public static final int TYPE_A = 1;
    public static final int TYPE_AAAA = 28;

    static {
        AsyncDns.Companion = Companion.$$INSTANCE;
    }

    void query(String arg1, Callback arg2);
}

