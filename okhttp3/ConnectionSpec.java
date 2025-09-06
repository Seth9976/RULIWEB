package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Internal;
import okhttp3.internal._UtilCommonKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 &2\u00020\u0001:\u0002%&B7\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u000E\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000E\u0010\b\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\u0010\tJ\u001D\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0003H\u0000¢\u0006\u0002\b\u0019J\u0015\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000BH\u0007¢\u0006\u0002\b\u001AJ\u0013\u0010\u001B\u001A\u00020\u00032\b\u0010\u001C\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001D\u001A\u00020\u001EH\u0016J\u000E\u0010\u001F\u001A\u00020\u00032\u0006\u0010 \u001A\u00020\u0017J\u0018\u0010!\u001A\u00020\u00002\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0003H\u0002J\r\u0010\u0004\u001A\u00020\u0003H\u0007¢\u0006\u0002\b\"J\u0015\u0010\u0012\u001A\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000BH\u0007¢\u0006\u0002\b#J\b\u0010$\u001A\u00020\u0007H\u0016R\u0019\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B8G¢\u0006\u0006\u001A\u0004\b\n\u0010\rR\u001E\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0010\u001A\u0004\b\u000E\u0010\u000FR\u0013\u0010\u0002\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0011R\u0013\u0010\u0004\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0011R\u0019\u0010\u0012\u001A\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000B8G¢\u0006\u0006\u001A\u0004\b\u0012\u0010\rR\u0018\u0010\b\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\'"}, d2 = {"Lokhttp3/ConnectionSpec;", "", "isTls", "", "supportsTlsExtensions", "cipherSuitesAsString", "", "", "tlsVersionsAsString", "(ZZ[Ljava/lang/String;[Ljava/lang/String;)V", "cipherSuites", "", "Lokhttp3/CipherSuite;", "()Ljava/util/List;", "getCipherSuitesAsString$okhttp", "()[Ljava/lang/String;", "[Ljava/lang/String;", "()Z", "tlsVersions", "Lokhttp3/TlsVersion;", "apply", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "isFallback", "apply$okhttp", "-deprecated_cipherSuites", "equals", "other", "hashCode", "", "isCompatible", "socket", "supportedSpec", "-deprecated_supportsTlsExtensions", "-deprecated_tlsVersions", "toString", "Builder", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class ConnectionSpec {
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000F\b\u0010\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000F\b\u0016\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0019\u001A\u00020\u0000J\u0006\u0010\u001A\u001A\u00020\u0000J\u0006\u0010\u001B\u001A\u00020\u0006J\u001F\u0010\b\u001A\u00020\u00002\u0012\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\n¢\u0006\u0002\u0010\u001CJ\u001F\u0010\b\u001A\u00020\u00002\u0012\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u001D0\t\"\u00020\u001D¢\u0006\u0002\u0010\u001EJ\u0010\u0010\u0010\u001A\u00020\u00002\u0006\u0010\u0010\u001A\u00020\u0003H\u0007J\u001F\u0010\u0016\u001A\u00020\u00002\u0012\u0010\u0016\u001A\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\n¢\u0006\u0002\u0010\u001CJ\u001F\u0010\u0016\u001A\u00020\u00002\u0012\u0010\u0016\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u001F0\t\"\u00020\u001F¢\u0006\u0002\u0010 R$\u0010\b\u001A\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0080\u000E¢\u0006\u0010\n\u0002\u0010\u000F\u001A\u0004\b\u000B\u0010\f\"\u0004\b\r\u0010\u000ER\u001A\u0010\u0010\u001A\u00020\u0003X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0004R\u001A\u0010\u0002\u001A\u00020\u0003X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0014\u0010\u0012\"\u0004\b\u0015\u0010\u0004R$\u0010\u0016\u001A\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0080\u000E¢\u0006\u0010\n\u0002\u0010\u000F\u001A\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000E¨\u0006!"}, d2 = {"Lokhttp3/ConnectionSpec$Builder;", "", "tls", "", "(Z)V", "connectionSpec", "Lokhttp3/ConnectionSpec;", "(Lokhttp3/ConnectionSpec;)V", "cipherSuites", "", "", "getCipherSuites$okhttp", "()[Ljava/lang/String;", "setCipherSuites$okhttp", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "supportsTlsExtensions", "getSupportsTlsExtensions$okhttp", "()Z", "setSupportsTlsExtensions$okhttp", "getTls$okhttp", "setTls$okhttp", "tlsVersions", "getTlsVersions$okhttp", "setTlsVersions$okhttp", "allEnabledCipherSuites", "allEnabledTlsVersions", "build", "([Ljava/lang/String;)Lokhttp3/ConnectionSpec$Builder;", "Lokhttp3/CipherSuite;", "([Lokhttp3/CipherSuite;)Lokhttp3/ConnectionSpec$Builder;", "Lokhttp3/TlsVersion;", "([Lokhttp3/TlsVersion;)Lokhttp3/ConnectionSpec$Builder;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Builder {
        private String[] cipherSuites;
        private boolean supportsTlsExtensions;
        private boolean tls;
        private String[] tlsVersions;

        public Builder(ConnectionSpec connectionSpec0) {
            Intrinsics.checkNotNullParameter(connectionSpec0, "connectionSpec");
            super();
            this.tls = connectionSpec0.isTls();
            this.cipherSuites = connectionSpec0.getCipherSuitesAsString$okhttp();
            this.tlsVersions = connectionSpec0.tlsVersionsAsString;
            this.supportsTlsExtensions = connectionSpec0.supportsTlsExtensions();
        }

        public Builder(boolean z) {
            this.tls = z;
        }

        public final Builder allEnabledCipherSuites() {
            if(!this.tls) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections");
            }
            this.cipherSuites = null;
            return this;
        }

        public final Builder allEnabledTlsVersions() {
            if(!this.tls) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections");
            }
            this.tlsVersions = null;
            return this;
        }

        public final ConnectionSpec build() {
            return new ConnectionSpec(this.tls, this.supportsTlsExtensions, this.cipherSuites, this.tlsVersions);
        }

        public final Builder cipherSuites(String[] arr_s) {
            Intrinsics.checkNotNullParameter(arr_s, "cipherSuites");
            if(!this.tls) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections");
            }
            if(arr_s.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            Object object0 = arr_s.clone();
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            this.cipherSuites = (String[])object0;
            return this;
        }

        public final Builder cipherSuites(CipherSuite[] arr_cipherSuite) {
            Intrinsics.checkNotNullParameter(arr_cipherSuite, "cipherSuites");
            if(!this.tls) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections");
            }
            ArrayList arrayList0 = new ArrayList(arr_cipherSuite.length);
            for(int v = 0; v < arr_cipherSuite.length; ++v) {
                arrayList0.add(arr_cipherSuite[v].javaName());
            }
            Object[] arr_object = arrayList0.toArray(new String[0]);
            Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            return this.cipherSuites(((String[])Arrays.copyOf(((String[])arr_object), ((String[])arr_object).length)));
        }

        public final String[] getCipherSuites$okhttp() {
            return this.cipherSuites;
        }

        public final boolean getSupportsTlsExtensions$okhttp() {
            return this.supportsTlsExtensions;
        }

        public final boolean getTls$okhttp() {
            return this.tls;
        }

        public final String[] getTlsVersions$okhttp() {
            return this.tlsVersions;
        }

        public final void setCipherSuites$okhttp(String[] arr_s) {
            this.cipherSuites = arr_s;
        }

        public final void setSupportsTlsExtensions$okhttp(boolean z) {
            this.supportsTlsExtensions = z;
        }

        public final void setTls$okhttp(boolean z) {
            this.tls = z;
        }

        public final void setTlsVersions$okhttp(String[] arr_s) {
            this.tlsVersions = arr_s;
        }

        @Deprecated(message = "since OkHttp 3.13 all TLS-connections are expected to support TLS extensions.\nIn a future release setting this to true will be unnecessary and setting it to false\nwill have no effect.")
        public final Builder supportsTlsExtensions(boolean z) {
            if(!this.tls) {
                throw new IllegalArgumentException("no TLS extensions for cleartext connections");
            }
            this.supportsTlsExtensions = z;
            return this;
        }

        public final Builder tlsVersions(String[] arr_s) {
            Intrinsics.checkNotNullParameter(arr_s, "tlsVersions");
            if(!this.tls) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections");
            }
            if(arr_s.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            Object object0 = arr_s.clone();
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            this.tlsVersions = (String[])object0;
            return this;
        }

        public final Builder tlsVersions(TlsVersion[] arr_tlsVersion) {
            Intrinsics.checkNotNullParameter(arr_tlsVersion, "tlsVersions");
            if(!this.tls) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections");
            }
            ArrayList arrayList0 = new ArrayList(arr_tlsVersion.length);
            for(int v = 0; v < arr_tlsVersion.length; ++v) {
                arrayList0.add(arr_tlsVersion[v].javaName());
            }
            Object[] arr_object = arrayList0.toArray(new String[0]);
            Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            return this.tlsVersions(((String[])Arrays.copyOf(((String[])arr_object), ((String[])arr_object).length)));
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001A\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001A\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\f\u001A\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/ConnectionSpec$Companion;", "", "()V", "APPROVED_CIPHER_SUITES", "", "Lokhttp3/CipherSuite;", "[Lokhttp3/CipherSuite;", "CLEARTEXT", "Lokhttp3/ConnectionSpec;", "COMPATIBLE_TLS", "MODERN_TLS", "RESTRICTED_CIPHER_SUITES", "RESTRICTED_TLS", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final CipherSuite[] APPROVED_CIPHER_SUITES;
    public static final ConnectionSpec CLEARTEXT;
    public static final ConnectionSpec COMPATIBLE_TLS;
    public static final Companion Companion;
    public static final ConnectionSpec MODERN_TLS;
    private static final CipherSuite[] RESTRICTED_CIPHER_SUITES;
    public static final ConnectionSpec RESTRICTED_TLS;
    private final String[] cipherSuitesAsString;
    private final boolean isTls;
    private final boolean supportsTlsExtensions;
    private final String[] tlsVersionsAsString;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cipherSuites", imports = {}))
    public final List -deprecated_cipherSuites() {
        return this.cipherSuites();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "supportsTlsExtensions", imports = {}))
    public final boolean -deprecated_supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "tlsVersions", imports = {}))
    public final List -deprecated_tlsVersions() {
        return this.tlsVersions();
    }

    static {
        ConnectionSpec.Companion = new Companion(null);
        CipherSuite[] arr_cipherSuite = {CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256};
        ConnectionSpec.RESTRICTED_CIPHER_SUITES = arr_cipherSuite;
        CipherSuite[] arr_cipherSuite1 = {CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
        ConnectionSpec.APPROVED_CIPHER_SUITES = arr_cipherSuite1;
        ConnectionSpec.RESTRICTED_TLS = new Builder(true).cipherSuites(((CipherSuite[])Arrays.copyOf(arr_cipherSuite, 9))).tlsVersions(new TlsVersion[]{TlsVersion.TLS_1_3, TlsVersion.TLS_1_2}).supportsTlsExtensions(true).build();
        ConnectionSpec.MODERN_TLS = new Builder(true).cipherSuites(((CipherSuite[])Arrays.copyOf(arr_cipherSuite1, 16))).tlsVersions(new TlsVersion[]{TlsVersion.TLS_1_3, TlsVersion.TLS_1_2}).supportsTlsExtensions(true).build();
        ConnectionSpec.COMPATIBLE_TLS = new Builder(true).cipherSuites(((CipherSuite[])Arrays.copyOf(arr_cipherSuite1, 16))).tlsVersions(new TlsVersion[]{TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0}).supportsTlsExtensions(true).build();
        ConnectionSpec.CLEARTEXT = new Builder(false).build();
    }

    public ConnectionSpec(boolean z, boolean z1, String[] arr_s, String[] arr_s1) {
        this.isTls = z;
        this.supportsTlsExtensions = z1;
        this.cipherSuitesAsString = arr_s;
        this.tlsVersionsAsString = arr_s1;
    }

    public final void apply$okhttp(SSLSocket sSLSocket0, boolean z) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        ConnectionSpec connectionSpec0 = this.supportedSpec(sSLSocket0, z);
        if(connectionSpec0.tlsVersions() != null) {
            sSLSocket0.setEnabledProtocols(connectionSpec0.tlsVersionsAsString);
        }
        if(connectionSpec0.cipherSuites() != null) {
            sSLSocket0.setEnabledCipherSuites(connectionSpec0.cipherSuitesAsString);
        }
    }

    public final List cipherSuites() {
        String[] arr_s = this.cipherSuitesAsString;
        if(arr_s != null) {
            ArrayList arrayList0 = new ArrayList(arr_s.length);
            for(int v = 0; v < arr_s.length; ++v) {
                arrayList0.add(CipherSuite.Companion.forJavaName(arr_s[v]));
            }
            return CollectionsKt.toList(arrayList0);
        }
        return null;
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof ConnectionSpec)) {
            return false;
        }
        if(object0 == this) {
            return true;
        }
        boolean z = this.isTls;
        if(z != ((ConnectionSpec)object0).isTls) {
            return false;
        }
        if(z) {
            if(!Arrays.equals(this.cipherSuitesAsString, ((ConnectionSpec)object0).cipherSuitesAsString)) {
                return false;
            }
            return Arrays.equals(this.tlsVersionsAsString, ((ConnectionSpec)object0).tlsVersionsAsString) ? this.supportsTlsExtensions == ((ConnectionSpec)object0).supportsTlsExtensions : false;
        }
        return true;
    }

    public final String[] getCipherSuitesAsString$okhttp() {
        return this.cipherSuitesAsString;
    }

    @Override
    public int hashCode() {
        if(this.isTls) {
            int v = 0;
            int v1 = this.cipherSuitesAsString == null ? 0 : Arrays.hashCode(this.cipherSuitesAsString);
            String[] arr_s = this.tlsVersionsAsString;
            if(arr_s != null) {
                v = Arrays.hashCode(arr_s);
            }
            return ((v1 + 0x20F) * 0x1F + v) * 0x1F + !this.supportsTlsExtensions;
        }
        return 17;
    }

    public final boolean isCompatible(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "socket");
        if(!this.isTls) {
            return false;
        }
        return this.tlsVersionsAsString == null || _UtilCommonKt.hasIntersection(this.tlsVersionsAsString, sSLSocket0.getEnabledProtocols(), ComparisonsKt.naturalOrder()) ? this.cipherSuitesAsString == null || _UtilCommonKt.hasIntersection(this.cipherSuitesAsString, sSLSocket0.getEnabledCipherSuites(), CipherSuite.Companion.getORDER_BY_NAME$okhttp()) : false;
    }

    public final boolean isTls() {
        return this.isTls;
    }

    private final ConnectionSpec supportedSpec(SSLSocket sSLSocket0, boolean z) {
        String[] arr_s3;
        String[] arr_s = sSLSocket0.getEnabledCipherSuites();
        Intrinsics.checkNotNullExpressionValue(arr_s, "socketEnabledCipherSuites");
        String[] arr_s1 = Internal.effectiveCipherSuites(this, arr_s);
        if(this.tlsVersionsAsString == null) {
            arr_s3 = sSLSocket0.getEnabledProtocols();
        }
        else {
            String[] arr_s2 = sSLSocket0.getEnabledProtocols();
            Intrinsics.checkNotNullExpressionValue(arr_s2, "sslSocket.enabledProtocols");
            Comparator comparator0 = ComparisonsKt.naturalOrder();
            arr_s3 = _UtilCommonKt.intersect(arr_s2, this.tlsVersionsAsString, comparator0);
        }
        String[] arr_s4 = sSLSocket0.getSupportedCipherSuites();
        Intrinsics.checkNotNullExpressionValue(arr_s4, "supportedCipherSuites");
        int v = _UtilCommonKt.indexOf(arr_s4, "TLS_FALLBACK_SCSV", CipherSuite.Companion.getORDER_BY_NAME$okhttp());
        if(z && v != -1) {
            String s = arr_s4[v];
            Intrinsics.checkNotNullExpressionValue(s, "supportedCipherSuites[indexOfFallbackScsv]");
            arr_s1 = _UtilCommonKt.concat(arr_s1, s);
        }
        Builder connectionSpec$Builder0 = new Builder(this).cipherSuites(((String[])Arrays.copyOf(arr_s1, arr_s1.length)));
        Intrinsics.checkNotNullExpressionValue(arr_s3, "tlsVersionsIntersection");
        return connectionSpec$Builder0.tlsVersions(((String[])Arrays.copyOf(arr_s3, arr_s3.length))).build();
    }

    public final boolean supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }

    public final List tlsVersions() {
        String[] arr_s = this.tlsVersionsAsString;
        if(arr_s != null) {
            ArrayList arrayList0 = new ArrayList(arr_s.length);
            for(int v = 0; v < arr_s.length; ++v) {
                arrayList0.add(TlsVersion.Companion.forJavaName(arr_s[v]));
            }
            return CollectionsKt.toList(arrayList0);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.isTls ? "ConnectionSpec(cipherSuites=" + Objects.toString(this.cipherSuites(), "[all enabled]") + ", tlsVersions=" + Objects.toString(this.tlsVersions(), "[all enabled]") + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ')' : "ConnectionSpec()";
    }
}

