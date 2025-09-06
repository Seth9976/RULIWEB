package okhttp3;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000 \n2\u00020\u0001:\u0001\nJ\u0016\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001A\u00020\u0006H&J\u001E\u0010\u0007\u001A\u00020\b2\u0006\u0010\u0005\u001A\u00020\u00062\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000BÀ\u0006\u0001"}, d2 = {"Lokhttp3/CookieJar;", "", "loadForRequest", "", "Lokhttp3/Cookie;", "url", "Lokhttp3/HttpUrl;", "saveFromResponse", "", "cookies", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public interface CookieJar {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0006"}, d2 = {"Lokhttp3/CookieJar$Companion;", "", "()V", "NO_COOKIES", "Lokhttp3/CookieJar;", "NoCookies", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001A\u00020\u0007H\u0016J\u001E\u0010\b\u001A\u00020\t2\u0006\u0010\u0006\u001A\u00020\u00072\f\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¨\u0006\u000B"}, d2 = {"Lokhttp3/CookieJar$Companion$NoCookies;", "Lokhttp3/CookieJar;", "()V", "loadForRequest", "", "Lokhttp3/Cookie;", "url", "Lokhttp3/HttpUrl;", "saveFromResponse", "", "cookies", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
        static final class NoCookies implements CookieJar {
            @Override  // okhttp3.CookieJar
            public List loadForRequest(HttpUrl httpUrl0) {
                Intrinsics.checkNotNullParameter(httpUrl0, "url");
                return CollectionsKt.emptyList();
            }

            @Override  // okhttp3.CookieJar
            public void saveFromResponse(HttpUrl httpUrl0, List list0) {
                Intrinsics.checkNotNullParameter(httpUrl0, "url");
                Intrinsics.checkNotNullParameter(list0, "cookies");
            }
        }

        static final Companion $$INSTANCE;

        static {
            Companion.$$INSTANCE = new Companion();
        }
    }

    public static final Companion Companion;
    public static final CookieJar NO_COOKIES;

    static {
        CookieJar.Companion = Companion.$$INSTANCE;
        CookieJar.NO_COOKIES = new NoCookies();
    }

    List loadForRequest(HttpUrl arg1);

    void saveFromResponse(HttpUrl arg1, List arg2);
}

