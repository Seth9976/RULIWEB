package okhttp3.internal.platform.android;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.internal.platform.AndroidPlatform;
import okhttp3.internal.platform.Platform;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0015\u0012\u000E\u0010\u0002\u001A\n\u0012\u0006\b\u0000\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J(\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u00042\b\u0010\u000F\u001A\u0004\u0018\u00010\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0012\u0010\u0014\u001A\u0004\u0018\u00010\u00102\u0006\u0010\u000E\u001A\u00020\u0004H\u0016J\b\u0010\u0015\u001A\u00020\u0016H\u0016J\u0010\u0010\u0017\u001A\u00020\u00162\u0006\u0010\u000E\u001A\u00020\u0004H\u0016R\u0016\u0010\u0006\u001A\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001A\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001A\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001A\n\u0012\u0006\b\u0000\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "sslSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "(Ljava/lang/Class;)V", "getAlpnSelectedProtocol", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "setAlpnProtocols", "setHostname", "setUseSessionTickets", "configureTlsExtensions", "", "sslSocket", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public class AndroidSocketAdapter implements SocketAdapter {
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001A\u00020\b2\u000E\u0010\t\u001A\n\u0012\u0006\b\u0000\u0012\u00020\u000B0\nH\u0002J\u000E\u0010\f\u001A\u00020\u00042\u0006\u0010\r\u001A\u00020\u000ER\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u000F"}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter$Companion;", "", "()V", "playProviderFactory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "getPlayProviderFactory", "()Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "build", "Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "actualSSLSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "factory", "packageName", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        private final AndroidSocketAdapter build(Class class0) {
            Class class1 = class0;
            while(class1 != null && !Intrinsics.areEqual(class1.getSimpleName(), "OpenSSLSocketImpl")) {
                class1 = class1.getSuperclass();
                if(class1 == null) {
                    throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + class0);
                }
                if(false) {
                    break;
                }
            }
            Intrinsics.checkNotNull(class1);
            return new AndroidSocketAdapter(class1);
        }

        public final Factory factory(String s) {
            Intrinsics.checkNotNullParameter(s, "packageName");
            return new Factory() {
                @Override  // okhttp3.internal.platform.android.DeferredSocketAdapter$Factory
                public SocketAdapter create(SSLSocket sSLSocket0) {
                    Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
                    Class class0 = sSLSocket0.getClass();
                    return AndroidSocketAdapter.Companion.build(class0);
                }

                @Override  // okhttp3.internal.platform.android.DeferredSocketAdapter$Factory
                public boolean matchesSocket(SSLSocket sSLSocket0) {
                    Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
                    String s = sSLSocket0.getClass().getName();
                    Intrinsics.checkNotNullExpressionValue(s, "sslSocket.javaClass.name");
                    return StringsKt.startsWith$default(s, this.$packageName + '.', false, 2, null);
                }
            };
        }

        public final Factory getPlayProviderFactory() {
            return AndroidSocketAdapter.playProviderFactory;
        }
    }

    public static final Companion Companion;
    private final Method getAlpnSelectedProtocol;
    private static final Factory playProviderFactory;
    private final Method setAlpnProtocols;
    private final Method setHostname;
    private final Method setUseSessionTickets;
    private final Class sslSocketClass;

    static {
        Companion androidSocketAdapter$Companion0 = new Companion(null);
        AndroidSocketAdapter.Companion = androidSocketAdapter$Companion0;
        AndroidSocketAdapter.playProviderFactory = androidSocketAdapter$Companion0.factory("com.google.android.gms.org.conscrypt");
    }

    public AndroidSocketAdapter(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "sslSocketClass");
        super();
        this.sslSocketClass = class0;
        Method method0 = class0.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        Intrinsics.checkNotNullExpressionValue(method0, "sslSocketClass.getDeclar…:class.javaPrimitiveType)");
        this.setUseSessionTickets = method0;
        this.setHostname = class0.getMethod("setHostname", String.class);
        this.getAlpnSelectedProtocol = class0.getMethod("getAlpnSelectedProtocol", null);
        this.setAlpnProtocols = class0.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public void configureTlsExtensions(SSLSocket sSLSocket0, String s, List list0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        if(this.matchesSocket(sSLSocket0)) {
            try {
                this.setUseSessionTickets.invoke(sSLSocket0, Boolean.TRUE);
                if(s != null) {
                    this.setHostname.invoke(sSLSocket0, s);
                }
                Object[] arr_object = {Platform.Companion.concatLengthPrefixed(list0)};
                this.setAlpnProtocols.invoke(sSLSocket0, arr_object);
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new AssertionError(illegalAccessException0);
            }
            catch(InvocationTargetException invocationTargetException0) {
                throw new AssertionError(invocationTargetException0);
            }
        }
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public String getSelectedProtocol(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        if(!this.matchesSocket(sSLSocket0)) {
            return null;
        }
        try {
            byte[] arr_b = (byte[])this.getAlpnSelectedProtocol.invoke(sSLSocket0, null);
            return arr_b == null ? null : new String(arr_b, Charsets.UTF_8);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new AssertionError(illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Throwable throwable0 = invocationTargetException0.getCause();
            if(!(throwable0 instanceof NullPointerException) || !Intrinsics.areEqual(((NullPointerException)throwable0).getMessage(), "ssl == null")) {
                throw new AssertionError(invocationTargetException0);
            }
            return null;
        }
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean isSupported() {
        return AndroidPlatform.Companion.isSupported();
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocket(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        return this.sslSocketClass.isInstance(sSLSocket0);
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocketFactory(SSLSocketFactory sSLSocketFactory0) {
        return SocketAdapter.-CC.$default$matchesSocketFactory(this, sSLSocketFactory0);
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory0) {
        return SocketAdapter.-CC.$default$trustManager(this, sSLSocketFactory0);
    }
}

