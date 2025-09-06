package okhttp3;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilJvmKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 &2\u00020\u0001:\u0001&B9\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n¢\u0006\u0002\u0010\u000BJ\r\u0010\u0004\u001A\u00020\u0005H\u0007¢\u0006\u0002\b\u001AJ\u0013\u0010\u001B\u001A\u00020\u001C2\b\u0010\u001D\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001E\u001A\u00020\u001FH\u0016J\u0013\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\b J\u000F\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0007¢\u0006\u0002\b!J\u0013\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\b\"J\u000F\u0010\u0014\u001A\u0004\u0018\u00010\u000FH\u0007¢\u0006\u0002\b#J\r\u0010\u0002\u001A\u00020\u0003H\u0007¢\u0006\u0002\b$J\b\u0010%\u001A\u00020\u0017H\u0016R\u0013\u0010\u0004\u001A\u00020\u00058\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\fR\u0019\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u00078\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\rR\u0013\u0010\u000E\u001A\u0004\u0018\u00010\u000F8G¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u0010R!\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\b0\u00078GX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001A\u0004\b\u0011\u0010\rR\u0013\u0010\u0014\u001A\u0004\u0018\u00010\u000F8G¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u0010R\u0013\u0010\u0002\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0015R\u0018\u0010\u0016\u001A\u00020\u0017*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0018\u0010\u0019¨\u0006\'"}, d2 = {"Lokhttp3/Handshake;", "", "tlsVersion", "Lokhttp3/TlsVersion;", "cipherSuite", "Lokhttp3/CipherSuite;", "localCertificates", "", "Ljava/security/cert/Certificate;", "peerCertificatesFn", "Lkotlin/Function0;", "(Lokhttp3/TlsVersion;Lokhttp3/CipherSuite;Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "()Lokhttp3/CipherSuite;", "()Ljava/util/List;", "localPrincipal", "Ljava/security/Principal;", "()Ljava/security/Principal;", "peerCertificates", "peerCertificates$delegate", "Lkotlin/Lazy;", "peerPrincipal", "()Lokhttp3/TlsVersion;", "name", "", "getName", "(Ljava/security/cert/Certificate;)Ljava/lang/String;", "-deprecated_cipherSuite", "equals", "", "other", "hashCode", "", "-deprecated_localCertificates", "-deprecated_localPrincipal", "-deprecated_peerCertificates", "-deprecated_peerPrincipal", "-deprecated_tlsVersion", "toString", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Handshake {
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¢\u0006\u0002\b\u0007J4\u0010\u0003\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u000E0\r2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u000E0\rH\u0007J\u0011\u0010\u0010\u001A\u00020\u0004*\u00020\u0006H\u0007¢\u0006\u0002\b\u0003J!\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u000E0\r*\f\u0012\u0006\b\u0001\u0012\u00020\u000E\u0018\u00010\u0012H\u0002¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lokhttp3/Handshake$Companion;", "", "()V", "get", "Lokhttp3/Handshake;", "sslSession", "Ljavax/net/ssl/SSLSession;", "-deprecated_get", "tlsVersion", "Lokhttp3/TlsVersion;", "cipherSuite", "Lokhttp3/CipherSuite;", "peerCertificates", "", "Ljava/security/cert/Certificate;", "localCertificates", "handshake", "toImmutableList", "", "([Ljava/security/cert/Certificate;)Ljava/util/List;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "sslSession.handshake()", imports = {}))
        public final Handshake -deprecated_get(SSLSession sSLSession0) throws IOException {
            Intrinsics.checkNotNullParameter(sSLSession0, "sslSession");
            return this.get(sSLSession0);
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final Handshake get(SSLSession sSLSession0) throws IOException {
            List list0;
            Intrinsics.checkNotNullParameter(sSLSession0, "<this>");
            String s = sSLSession0.getCipherSuite();
            if(s == null) {
                throw new IllegalStateException("cipherSuite == null");
            }
            if((Intrinsics.areEqual(s, "TLS_NULL_WITH_NULL_NULL") ? true : Intrinsics.areEqual(s, "SSL_NULL_WITH_NULL_NULL"))) {
                throw new IOException("cipherSuite == " + s);
            }
            CipherSuite cipherSuite0 = CipherSuite.Companion.forJavaName(s);
            String s1 = sSLSession0.getProtocol();
            if(s1 == null) {
                throw new IllegalStateException("tlsVersion == null");
            }
            if(!Intrinsics.areEqual("NONE", s1)) {
                TlsVersion tlsVersion0 = TlsVersion.Companion.forJavaName(s1);
                try {
                    list0 = this.toImmutableList(sSLSession0.getPeerCertificates());
                    return new Handshake(tlsVersion0, cipherSuite0, this.toImmutableList(sSLSession0.getLocalCertificates()), new Function0() {
                        final List $peerCertificatesCopy;

                        {
                            this.$peerCertificatesCopy = list0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final List invoke() {
                            return this.$peerCertificatesCopy;
                        }
                    });
                }
                catch(SSLPeerUnverifiedException unused_ex) {
                    list0 = CollectionsKt.emptyList();
                    return new Handshake(tlsVersion0, cipherSuite0, this.toImmutableList(sSLSession0.getLocalCertificates()), new Function0() {
                        final List $peerCertificatesCopy;

                        {
                            this.$peerCertificatesCopy = list0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final List invoke() {
                            return this.$peerCertificatesCopy;
                        }
                    });
                }
            }
            throw new IOException("tlsVersion == NONE");
        }

        @JvmStatic
        public final Handshake get(TlsVersion tlsVersion0, CipherSuite cipherSuite0, List list0, List list1) {
            Intrinsics.checkNotNullParameter(tlsVersion0, "tlsVersion");
            Intrinsics.checkNotNullParameter(cipherSuite0, "cipherSuite");
            Intrinsics.checkNotNullParameter(list0, "peerCertificates");
            Intrinsics.checkNotNullParameter(list1, "localCertificates");
            List list2 = _UtilJvmKt.toImmutableList(list0);
            return new Handshake(tlsVersion0, cipherSuite0, _UtilJvmKt.toImmutableList(list1), new Function0() {
                final List $peerCertificatesCopy;

                {
                    this.$peerCertificatesCopy = list0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return this.$peerCertificatesCopy;
                }
            });
        }

        private final List toImmutableList(Certificate[] arr_certificate) {
            return arr_certificate == null ? CollectionsKt.emptyList() : _UtilJvmKt.immutableListOf(Arrays.copyOf(arr_certificate, arr_certificate.length));
        }
    }

    public static final Companion Companion;
    private final CipherSuite cipherSuite;
    private final List localCertificates;
    private final Lazy peerCertificates$delegate;
    private final TlsVersion tlsVersion;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cipherSuite", imports = {}))
    public final CipherSuite -deprecated_cipherSuite() {
        return this.cipherSuite;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "localCertificates", imports = {}))
    public final List -deprecated_localCertificates() {
        return this.localCertificates;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "localPrincipal", imports = {}))
    public final Principal -deprecated_localPrincipal() {
        return this.localPrincipal();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "peerCertificates", imports = {}))
    public final List -deprecated_peerCertificates() {
        return this.peerCertificates();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "peerPrincipal", imports = {}))
    public final Principal -deprecated_peerPrincipal() {
        return this.peerPrincipal();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "tlsVersion", imports = {}))
    public final TlsVersion -deprecated_tlsVersion() {
        return this.tlsVersion;
    }

    static {
        Handshake.Companion = new Companion(null);
    }

    public Handshake(TlsVersion tlsVersion0, CipherSuite cipherSuite0, List list0, Function0 function00) {
        Intrinsics.checkNotNullParameter(tlsVersion0, "tlsVersion");
        Intrinsics.checkNotNullParameter(cipherSuite0, "cipherSuite");
        Intrinsics.checkNotNullParameter(list0, "localCertificates");
        Intrinsics.checkNotNullParameter(function00, "peerCertificatesFn");
        super();
        this.tlsVersion = tlsVersion0;
        this.cipherSuite = cipherSuite0;
        this.localCertificates = list0;
        this.peerCertificates$delegate = LazyKt.lazy(new Function0() {
            final Function0 $peerCertificatesFn;

            {
                this.$peerCertificatesFn = function00;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                try {
                    return (List)this.$peerCertificatesFn.invoke();
                }
                catch(SSLPeerUnverifiedException unused_ex) {
                    return CollectionsKt.emptyList();
                }
            }
        });
    }

    public final CipherSuite cipherSuite() {
        return this.cipherSuite;
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Handshake && ((Handshake)object0).tlsVersion == this.tlsVersion && Intrinsics.areEqual(((Handshake)object0).cipherSuite, this.cipherSuite) && Intrinsics.areEqual(((Handshake)object0).peerCertificates(), this.peerCertificates()) && Intrinsics.areEqual(((Handshake)object0).localCertificates, this.localCertificates);
    }

    @JvmStatic
    public static final Handshake get(SSLSession sSLSession0) throws IOException {
        return Handshake.Companion.get(sSLSession0);
    }

    @JvmStatic
    public static final Handshake get(TlsVersion tlsVersion0, CipherSuite cipherSuite0, List list0, List list1) {
        return Handshake.Companion.get(tlsVersion0, cipherSuite0, list0, list1);
    }

    private final String getName(Certificate certificate0) {
        if(certificate0 instanceof X509Certificate) {
            return ((X509Certificate)certificate0).getSubjectDN().toString();
        }
        String s = certificate0.getType();
        Intrinsics.checkNotNullExpressionValue(s, "type");
        return s;
    }

    @Override
    public int hashCode() {
        int v = this.peerCertificates().hashCode();
        return (((this.tlsVersion.hashCode() + 0x20F) * 0x1F + this.cipherSuite.hashCode()) * 0x1F + v) * 0x1F + this.localCertificates.hashCode();
    }

    public final List localCertificates() {
        return this.localCertificates;
    }

    public final Principal localPrincipal() {
        Object object0 = CollectionsKt.firstOrNull(this.localCertificates);
        X509Certificate x509Certificate0 = object0 instanceof X509Certificate ? ((X509Certificate)object0) : null;
        return x509Certificate0 == null ? null : x509Certificate0.getSubjectX500Principal();
    }

    public final List peerCertificates() {
        return (List)this.peerCertificates$delegate.getValue();
    }

    public final Principal peerPrincipal() {
        Object object0 = CollectionsKt.firstOrNull(this.peerCertificates());
        X509Certificate x509Certificate0 = object0 instanceof X509Certificate ? ((X509Certificate)object0) : null;
        return x509Certificate0 == null ? null : x509Certificate0.getSubjectX500Principal();
    }

    public final TlsVersion tlsVersion() {
        return this.tlsVersion;
    }

    @Override
    public String toString() {
        Iterable iterable0 = this.peerCertificates();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(this.getName(((Certificate)object0)));
        }
        StringBuilder stringBuilder0 = new StringBuilder("Handshake{tlsVersion=");
        stringBuilder0.append(this.tlsVersion);
        stringBuilder0.append(" cipherSuite=");
        stringBuilder0.append(this.cipherSuite);
        stringBuilder0.append(" peerCertificates=");
        stringBuilder0.append(arrayList0.toString());
        stringBuilder0.append(" localCertificates=");
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.localCertificates, 10));
        for(Object object1: this.localCertificates) {
            arrayList1.add(this.getName(((Certificate)object1)));
        }
        stringBuilder0.append(arrayList1);
        stringBuilder0.append('}');
        return stringBuilder0.toString();
    }
}

