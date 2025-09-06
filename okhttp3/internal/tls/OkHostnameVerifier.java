package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal._HostnamesCommonKt;
import okhttp3.internal._HostnamesJvmKt;
import okio.Utf8;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001A\u00020\nJ\u001E\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\f\u001A\u00020\u0004H\u0002J\u0016\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nJ\u0018\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\b2\u0006\u0010\u0010\u001A\u00020\u0011H\u0016J\u0018\u0010\u0012\u001A\u00020\u000E2\u0006\u0010\u0013\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0002J\u001C\u0010\u0012\u001A\u00020\u000E2\b\u0010\u0013\u001A\u0004\u0018\u00010\b2\b\u0010\u0014\u001A\u0004\u0018\u00010\bH\u0002J\u0018\u0010\u0015\u001A\u00020\u000E2\u0006\u0010\u0016\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0002J\f\u0010\u0017\u001A\u00020\b*\u00020\bH\u0002J\f\u0010\u0018\u001A\u00020\u000E*\u00020\bH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/tls/OkHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "()V", "ALT_DNS_NAME", "", "ALT_IPA_NAME", "allSubjectAltNames", "", "", "certificate", "Ljava/security/cert/X509Certificate;", "getSubjectAltNames", "type", "verify", "", "host", "session", "Ljavax/net/ssl/SSLSession;", "verifyHostname", "hostname", "pattern", "verifyIpAddress", "ipAddress", "asciiToLowercase", "isAscii", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class OkHostnameVerifier implements HostnameVerifier {
    private static final int ALT_DNS_NAME = 2;
    private static final int ALT_IPA_NAME = 7;
    public static final OkHostnameVerifier INSTANCE;

    static {
        OkHostnameVerifier.INSTANCE = new OkHostnameVerifier();
    }

    public final List allSubjectAltNames(X509Certificate x509Certificate0) {
        Intrinsics.checkNotNullParameter(x509Certificate0, "certificate");
        return CollectionsKt.plus(this.getSubjectAltNames(x509Certificate0, 7), this.getSubjectAltNames(x509Certificate0, 2));
    }

    private final String asciiToLowercase(String s) {
        if(this.isAscii(s)) {
            Locale locale0 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale0, "US");
            s = s.toLowerCase(locale0);
            Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String).toLowerCase(locale)");
        }
        return s;
    }

    private final List getSubjectAltNames(X509Certificate x509Certificate0, int v) {
        try {
            Collection collection0 = x509Certificate0.getSubjectAlternativeNames();
            if(collection0 == null) {
                return CollectionsKt.emptyList();
            }
            List list0 = new ArrayList();
            for(Object object0: collection0) {
                List list1 = (List)object0;
                if(list1 != null && list1.size() >= 2 && Intrinsics.areEqual(list1.get(0), v)) {
                    Object object1 = list1.get(1);
                    if(object1 != null) {
                        list0.add(((String)object1));
                    }
                }
            }
            return list0;
        }
        catch(CertificateParsingException unused_ex) {
            return CollectionsKt.emptyList();
        }
    }

    private final boolean isAscii(String s) {
        return s.length() == ((int)Utf8.size$default(s, 0, 0, 3, null));
    }

    public final boolean verify(String s, X509Certificate x509Certificate0) {
        Intrinsics.checkNotNullParameter(s, "host");
        Intrinsics.checkNotNullParameter(x509Certificate0, "certificate");
        return _HostnamesCommonKt.canParseAsIpAddress(s) ? this.verifyIpAddress(s, x509Certificate0) : this.verifyHostname(s, x509Certificate0);
    }

    @Override  // javax.net.ssl.HostnameVerifier
    public boolean verify(String s, SSLSession sSLSession0) {
        Intrinsics.checkNotNullParameter(s, "host");
        Intrinsics.checkNotNullParameter(sSLSession0, "session");
        if(!this.isAscii(s)) {
            return false;
        }
        try {
            Certificate certificate0 = sSLSession0.getPeerCertificates()[0];
            Intrinsics.checkNotNull(certificate0, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            return this.verify(s, ((X509Certificate)certificate0));
        }
        catch(SSLException unused_ex) {
            return false;
        }
    }

    private final boolean verifyHostname(String s, String s1) {
        if(s != null && s.length() != 0 && !StringsKt.startsWith$default(s, ".", false, 2, null) && !StringsKt.endsWith$default(s, "..", false, 2, null) && s1 != null && s1.length() != 0 && !StringsKt.startsWith$default(s1, ".", false, 2, null) && !StringsKt.endsWith$default(s1, "..", false, 2, null)) {
            if(!StringsKt.endsWith$default(s, ".", false, 2, null)) {
                s = s + '.';
            }
            if(!StringsKt.endsWith$default(s1, ".", false, 2, null)) {
                s1 = s1 + '.';
            }
            String s2 = this.asciiToLowercase(s1);
            if(!StringsKt.contains$default(s2, "*", false, 2, null)) {
                return Intrinsics.areEqual(s, s2);
            }
            if(!StringsKt.startsWith$default(s2, "*.", false, 2, null) || StringsKt.indexOf$default(s2, '*', 1, false, 4, null) != -1 || s.length() < s2.length()) {
                return false;
            }
            if(Intrinsics.areEqual("*.", s2)) {
                return false;
            }
            String s3 = s2.substring(1);
            Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).substring(startIndex)");
            if(!StringsKt.endsWith$default(s, s3, false, 2, null)) {
                return false;
            }
            int v = s.length() - s3.length();
            return v <= 0 || StringsKt.lastIndexOf$default(s, '.', v - 1, false, 4, null) == -1;
        }
        return false;
    }

    private final boolean verifyHostname(String s, X509Certificate x509Certificate0) {
        String s1 = this.asciiToLowercase(s);
        Iterable iterable0 = this.getSubjectAltNames(x509Certificate0, 2);
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object0: iterable0) {
            if(OkHostnameVerifier.INSTANCE.verifyHostname(s1, ((String)object0))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private final boolean verifyIpAddress(String s, X509Certificate x509Certificate0) {
        String s1 = _HostnamesJvmKt.toCanonicalHost(s);
        Iterable iterable0 = this.getSubjectAltNames(x509Certificate0, 7);
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object0: iterable0) {
            if(Intrinsics.areEqual(s1, _HostnamesJvmKt.toCanonicalHost(((String)object0)))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }
}

