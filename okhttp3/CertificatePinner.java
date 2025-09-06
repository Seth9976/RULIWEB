package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal._HostnamesJvmKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0003!\"#B!\b\u0000\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J)\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0012\u0010\u0010\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011H\u0000¢\u0006\u0002\b\u0014J)\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0012\u0010\u0015\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0016\"\u00020\u0017H\u0007¢\u0006\u0002\u0010\u0018J\u001C\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00170\u0012J\u0013\u0010\u0019\u001A\u00020\u001A2\b\u0010\u001B\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\u0014\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u000E\u001A\u00020\u000FJ\b\u0010\u001D\u001A\u00020\u001EH\u0016J\u0015\u0010\u001F\u001A\u00020\u00002\u0006\u0010\u0005\u001A\u00020\u0006H\u0000¢\u0006\u0002\b R\u0016\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006$"}, d2 = {"Lokhttp3/CertificatePinner;", "", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "getPins", "()Ljava/util/Set;", "check", "", "hostname", "", "cleanedPeerCertificatesFn", "Lkotlin/Function0;", "", "Ljava/security/cert/X509Certificate;", "check$okhttp", "peerCertificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "equals", "", "other", "findMatchingPins", "hashCode", "", "withCertificateChainCleaner", "withCertificateChainCleaner$okhttp", "Builder", "Companion", "Pin", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class CertificatePinner {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\'\u0010\b\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\n2\u0012\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00020\n0\u000B\"\u00020\n¢\u0006\u0002\u0010\fJ\u0006\u0010\r\u001A\u00020\u000ER\u0017\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007¨\u0006\u000F"}, d2 = {"Lokhttp3/CertificatePinner$Builder;", "", "()V", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "getPins", "()Ljava/util/List;", "add", "pattern", "", "", "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", "build", "Lokhttp3/CertificatePinner;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Builder {
        private final List pins;

        public Builder() {
            this.pins = new ArrayList();
        }

        public final Builder add(String s, String[] arr_s) {
            Intrinsics.checkNotNullParameter(s, "pattern");
            Intrinsics.checkNotNullParameter(arr_s, "pins");
            for(int v = 0; v < arr_s.length; ++v) {
                Pin certificatePinner$Pin0 = new Pin(s, arr_s[v]);
                this.pins.add(certificatePinner$Pin0);
            }
            return this;
        }

        public final CertificatePinner build() {
            return new CertificatePinner(CollectionsKt.toSet(this.pins), null, 2, null);
        }

        public final List getPins() {
            return this.pins;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J\f\u0010\t\u001A\u00020\n*\u00020\u000BH\u0007J\f\u0010\f\u001A\u00020\n*\u00020\u000BH\u0007R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/CertificatePinner$Companion;", "", "()V", "DEFAULT", "Lokhttp3/CertificatePinner;", "pin", "", "certificate", "Ljava/security/cert/Certificate;", "sha1Hash", "Lokio/ByteString;", "Ljava/security/cert/X509Certificate;", "sha256Hash", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final String pin(Certificate certificate0) {
            Intrinsics.checkNotNullParameter(certificate0, "certificate");
            if(!(certificate0 instanceof X509Certificate)) {
                throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
            }
            return "sha256/" + this.sha256Hash(((X509Certificate)certificate0)).base64();
        }

        @JvmStatic
        public final ByteString sha1Hash(X509Certificate x509Certificate0) {
            Intrinsics.checkNotNullParameter(x509Certificate0, "<this>");
            byte[] arr_b = x509Certificate0.getPublicKey().getEncoded();
            Intrinsics.checkNotNullExpressionValue(arr_b, "publicKey.encoded");
            return okio.ByteString.Companion.of$default(ByteString.Companion, arr_b, 0, 0, 3, null).sha1();
        }

        @JvmStatic
        public final ByteString sha256Hash(X509Certificate x509Certificate0) {
            Intrinsics.checkNotNullParameter(x509Certificate0, "<this>");
            byte[] arr_b = x509Certificate0.getPublicKey().getEncoded();
            Intrinsics.checkNotNullExpressionValue(arr_b, "publicKey.encoded");
            return okio.ByteString.Companion.of$default(ByteString.Companion, arr_b, 0, 0, 3, null).sha256();
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\u000E\u0010\u0013\u001A\u00020\u000F2\u0006\u0010\u0014\u001A\u00020\u0015J\u000E\u0010\u0016\u001A\u00020\u000F2\u0006\u0010\u0017\u001A\u00020\u0003J\b\u0010\u0018\u001A\u00020\u0003H\u0016R\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\n\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lokhttp3/CertificatePinner$Pin;", "", "pattern", "", "pin", "(Ljava/lang/String;Ljava/lang/String;)V", "hash", "Lokio/ByteString;", "getHash", "()Lokio/ByteString;", "hashAlgorithm", "getHashAlgorithm", "()Ljava/lang/String;", "getPattern", "equals", "", "other", "hashCode", "", "matchesCertificate", "certificate", "Ljava/security/cert/X509Certificate;", "matchesHostname", "hostname", "toString", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Pin {
        private final ByteString hash;
        private final String hashAlgorithm;
        private final String pattern;

        public Pin(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "pattern");
            Intrinsics.checkNotNullParameter(s1, "pin");
            super();
            if((!StringsKt.startsWith$default(s, "*.", false, 2, null) || StringsKt.indexOf$default(s, "*", 1, false, 4, null) != -1) && (!StringsKt.startsWith$default(s, "**.", false, 2, null) || StringsKt.indexOf$default(s, "*", 2, false, 4, null) != -1) && StringsKt.indexOf$default(s, "*", 0, false, 6, null) != -1) {
                throw new IllegalArgumentException(("Unexpected pattern: " + s).toString());
            }
            String s2 = _HostnamesJvmKt.toCanonicalHost(s);
            if(s2 == null) {
                throw new IllegalArgumentException("Invalid pattern: " + s);
            }
            this.pattern = s2;
            if(StringsKt.startsWith$default(s1, "sha1/", false, 2, null)) {
                this.hashAlgorithm = "sha1";
                String s3 = s1.substring(5);
                Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).substring(startIndex)");
                ByteString byteString0 = ByteString.Companion.decodeBase64(s3);
                if(byteString0 == null) {
                    throw new IllegalArgumentException("Invalid pin hash: " + s1);
                }
                this.hash = byteString0;
                return;
            }
            if(!StringsKt.startsWith$default(s1, "sha256/", false, 2, null)) {
                throw new IllegalArgumentException("pins must start with \'sha256/\' or \'sha1/\': " + s1);
            }
            this.hashAlgorithm = "sha256";
            String s4 = s1.substring(7);
            Intrinsics.checkNotNullExpressionValue(s4, "this as java.lang.String).substring(startIndex)");
            ByteString byteString1 = ByteString.Companion.decodeBase64(s4);
            if(byteString1 == null) {
                throw new IllegalArgumentException("Invalid pin hash: " + s1);
            }
            this.hash = byteString1;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof Pin)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.pattern, ((Pin)object0).pattern)) {
                return false;
            }
            return Intrinsics.areEqual(this.hashAlgorithm, ((Pin)object0).hashAlgorithm) ? Intrinsics.areEqual(this.hash, ((Pin)object0).hash) : false;
        }

        public final ByteString getHash() {
            return this.hash;
        }

        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        public final String getPattern() {
            return this.pattern;
        }

        @Override
        public int hashCode() {
            return (this.pattern.hashCode() * 0x1F + this.hashAlgorithm.hashCode()) * 0x1F + this.hash.hashCode();
        }

        public final boolean matchesCertificate(X509Certificate x509Certificate0) {
            Intrinsics.checkNotNullParameter(x509Certificate0, "certificate");
            String s = this.hashAlgorithm;
            if(Intrinsics.areEqual(s, "sha256")) {
                ByteString byteString0 = CertificatePinner.Companion.sha256Hash(x509Certificate0);
                return Intrinsics.areEqual(this.hash, byteString0);
            }
            if(Intrinsics.areEqual(s, "sha1")) {
                ByteString byteString1 = CertificatePinner.Companion.sha1Hash(x509Certificate0);
                return Intrinsics.areEqual(this.hash, byteString1);
            }
            return false;
        }

        public final boolean matchesHostname(String s) {
            Intrinsics.checkNotNullParameter(s, "hostname");
            if(StringsKt.startsWith$default(this.pattern, "**.", false, 2, null)) {
                int v = this.pattern.length();
                int v1 = s.length() - (v - 3);
                return StringsKt.regionMatches$default(s, s.length() - (v - 3), this.pattern, 3, v - 3, false, 16, null) && (v1 == 0 || s.charAt(v1 - 1) == 46);
            }
            if(StringsKt.startsWith$default(this.pattern, "*.", false, 2, null)) {
                int v2 = this.pattern.length();
                return StringsKt.regionMatches$default(s, s.length() - (v2 - 1), this.pattern, 1, v2 - 1, false, 16, null) && StringsKt.lastIndexOf$default(s, '.', s.length() - (v2 - 1) - 1, false, 4, null) == -1;
            }
            return Intrinsics.areEqual(s, this.pattern);
        }

        @Override
        public String toString() {
            return this.hashAlgorithm + '/' + this.hash.base64();
        }
    }

    public static final Companion Companion;
    public static final CertificatePinner DEFAULT;
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set pins;

    static {
        CertificatePinner.Companion = new Companion(null);
        CertificatePinner.DEFAULT = new Builder().build();
    }

    public CertificatePinner(Set set0, CertificateChainCleaner certificateChainCleaner0) {
        Intrinsics.checkNotNullParameter(set0, "pins");
        super();
        this.pins = set0;
        this.certificateChainCleaner = certificateChainCleaner0;
    }

    public CertificatePinner(Set set0, CertificateChainCleaner certificateChainCleaner0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            certificateChainCleaner0 = null;
        }
        this(set0, certificateChainCleaner0);
    }

    public final void check(String s, List list0) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(s, "hostname");
        Intrinsics.checkNotNullParameter(list0, "peerCertificates");
        this.check$okhttp(s, new Function0(list0, s) {
            final String $hostname;
            final List $peerCertificates;

            {
                CertificatePinner.this = certificatePinner0;
                this.$peerCertificates = list0;
                this.$hostname = s;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                List list0;
                CertificateChainCleaner certificateChainCleaner0 = CertificatePinner.this.getCertificateChainCleaner$okhttp();
                if(certificateChainCleaner0 == null) {
                    list0 = this.$peerCertificates;
                }
                else {
                    list0 = certificateChainCleaner0.clean(this.$peerCertificates, this.$hostname);
                    if(list0 == null) {
                        list0 = this.$peerCertificates;
                    }
                }
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object0: list0) {
                    Intrinsics.checkNotNull(((Certificate)object0), "null cannot be cast to non-null type java.security.cert.X509Certificate");
                    arrayList0.add(((X509Certificate)(((Certificate)object0))));
                }
                return arrayList0;
            }
        });
    }

    @Deprecated(message = "replaced with {@link #check(String, List)}.", replaceWith = @ReplaceWith(expression = "check(hostname, peerCertificates.toList())", imports = {}))
    public final void check(String s, Certificate[] arr_certificate) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(s, "hostname");
        Intrinsics.checkNotNullParameter(arr_certificate, "peerCertificates");
        this.check(s, ArraysKt.toList(arr_certificate));
    }

    public final void check$okhttp(String s, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "hostname");
        Intrinsics.checkNotNullParameter(function00, "cleanedPeerCertificatesFn");
        List list0 = this.findMatchingPins(s);
        if(!list0.isEmpty()) {
            List list1 = (List)function00.invoke();
            for(Object object0: list1) {
                X509Certificate x509Certificate0 = (X509Certificate)object0;
                ByteString byteString0 = null;
                ByteString byteString1 = null;
                Iterator iterator1 = list0.iterator();
            label_12:
                if(iterator1.hasNext()) {
                    Object object1 = iterator1.next();
                    Pin certificatePinner$Pin0 = (Pin)object1;
                    String s1 = certificatePinner$Pin0.getHashAlgorithm();
                    if(Intrinsics.areEqual(s1, "sha256")) {
                        if(byteString0 == null) {
                            byteString0 = CertificatePinner.Companion.sha256Hash(x509Certificate0);
                        }
                        if(!Intrinsics.areEqual(certificatePinner$Pin0.getHash(), byteString0)) {
                            goto label_12;
                        }
                    }
                    else {
                        if(!Intrinsics.areEqual(s1, "sha1")) {
                            throw new AssertionError("unsupported hashAlgorithm: " + certificatePinner$Pin0.getHashAlgorithm());
                        }
                        if(byteString1 == null) {
                            byteString1 = CertificatePinner.Companion.sha1Hash(x509Certificate0);
                        }
                        if(!Intrinsics.areEqual(certificatePinner$Pin0.getHash(), byteString1)) {
                            goto label_12;
                        }
                    }
                    return;
                }
            }
            StringBuilder stringBuilder0 = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
            for(Object object2: list1) {
                stringBuilder0.append("\n    ");
                stringBuilder0.append(CertificatePinner.Companion.pin(((X509Certificate)object2)));
                stringBuilder0.append(": ");
                stringBuilder0.append(((X509Certificate)object2).getSubjectDN().getName());
            }
            stringBuilder0.append("\n  Pinned certificates for ");
            stringBuilder0.append(s);
            stringBuilder0.append(":");
            for(Object object3: list0) {
                stringBuilder0.append("\n    ");
                stringBuilder0.append(((Pin)object3));
            }
            String s2 = stringBuilder0.toString();
            Intrinsics.checkNotNullExpressionValue(s2, "StringBuilder().apply(builderAction).toString()");
            throw new SSLPeerUnverifiedException(s2);
        }
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof CertificatePinner && Intrinsics.areEqual(((CertificatePinner)object0).pins, this.pins) && Intrinsics.areEqual(((CertificatePinner)object0).certificateChainCleaner, this.certificateChainCleaner);
    }

    public final List findMatchingPins(String s) {
        Intrinsics.checkNotNullParameter(s, "hostname");
        List list0 = CollectionsKt.emptyList();
        for(Object object0: this.pins) {
            if(((Pin)object0).matchesHostname(s)) {
                if(list0.isEmpty()) {
                    list0 = new ArrayList();
                }
                Intrinsics.checkNotNull(list0, "null cannot be cast to non-null type kotlin.collections.MutableList<T of okhttp3.internal._UtilCommonKt.filterList>");
                TypeIntrinsics.asMutableList(list0).add(object0);
            }
        }
        return list0;
    }

    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
        return this.certificateChainCleaner;
    }

    public final Set getPins() {
        return this.pins;
    }

    @Override
    public int hashCode() {
        int v = this.pins.hashCode();
        return this.certificateChainCleaner == null ? (v + 0x5ED) * 41 : (v + 0x5ED) * 41 + this.certificateChainCleaner.hashCode();
    }

    @JvmStatic
    public static final String pin(Certificate certificate0) {
        return CertificatePinner.Companion.pin(certificate0);
    }

    @JvmStatic
    public static final ByteString sha1Hash(X509Certificate x509Certificate0) {
        return CertificatePinner.Companion.sha1Hash(x509Certificate0);
    }

    @JvmStatic
    public static final ByteString sha256Hash(X509Certificate x509Certificate0) {
        return CertificatePinner.Companion.sha256Hash(x509Certificate0);
    }

    public final CertificatePinner withCertificateChainCleaner$okhttp(CertificateChainCleaner certificateChainCleaner0) {
        Intrinsics.checkNotNullParameter(certificateChainCleaner0, "certificateChainCleaner");
        return Intrinsics.areEqual(this.certificateChainCleaner, certificateChainCleaner0) ? this : new CertificatePinner(this.pins, certificateChainCleaner0);
    }
}

