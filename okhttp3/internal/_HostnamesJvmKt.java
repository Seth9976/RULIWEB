package okhttp3.internal;

import java.net.IDN;
import java.net.InetAddress;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\f\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u00020\u0001¨\u0006\u0002"}, d2 = {"toCanonicalHost", "", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _HostnamesJvmKt {
    public static final String toCanonicalHost(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        if(StringsKt.contains$default(s, ":", false, 2, null)) {
            byte[] arr_b = !StringsKt.startsWith$default(s, "[", false, 2, null) || !StringsKt.endsWith$default(s, "]", false, 2, null) ? _HostnamesCommonKt.decodeIpv6(s, 0, s.length()) : _HostnamesCommonKt.decodeIpv6(s, 1, s.length() - 1);
            if(arr_b == null) {
                return null;
            }
            InetAddress inetAddress0 = InetAddress.getByAddress(arr_b);
            byte[] arr_b1 = inetAddress0.getAddress();
            if(arr_b1.length == 16) {
                Intrinsics.checkNotNullExpressionValue(arr_b1, "address");
                return _HostnamesCommonKt.inet6AddressToAscii(arr_b1);
            }
            if(arr_b1.length != 4) {
                throw new AssertionError("Invalid IPv6 address: \'" + s + '\'');
            }
            return inetAddress0.getHostAddress();
        }
        try {
            String s1 = IDN.toASCII(s);
            Intrinsics.checkNotNullExpressionValue(s1, "toASCII(host)");
            Locale locale0 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale0, "US");
            String s2 = s1.toLowerCase(locale0);
            Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).toLowerCase(locale)");
            if(s2.length() == 0) {
                return null;
            }
            if(_HostnamesCommonKt.containsInvalidHostnameAsciiCodes(s2)) {
                return null;
            }
            return _HostnamesCommonKt.containsInvalidLabelLengths(s2) ? null : s2;
        }
        catch(IllegalArgumentException unused_ex) {
            return null;
        }
    }
}

