package okhttp3.internal.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B5\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003\u0012\n\u0010\u0006\u001A\u0006\u0012\u0002\b\u00030\u0007\u0012\n\u0010\b\u001A\u0006\u0012\u0002\b\u00030\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0016J(\u0010\u000E\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\b\u0010\u000F\u001A\u0004\u0018\u00010\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0012\u0010\u0014\u001A\u0004\u0018\u00010\u00102\u0006\u0010\f\u001A\u00020\rH\u0016R\u0012\u0010\u0006\u001A\u0006\u0012\u0002\b\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001A\u0006\u0012\u0002\b\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform;", "Lokhttp3/internal/platform/Platform;", "putMethod", "Ljava/lang/reflect/Method;", "getMethod", "removeMethod", "clientProviderClass", "Ljava/lang/Class;", "serverProviderClass", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Class;)V", "afterHandshake", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "configureTlsExtensions", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "AlpnProvider", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Jdk8WithJettyBootPlatform extends Platform {
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J0\u0010\u0011\u001A\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001A\u00020\u00122\u0006\u0010\u0014\u001A\u00020\u00152\u000E\u0010\u0016\u001A\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0017H\u0096\u0002¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\u0006\u001A\u0004\u0018\u00010\u0004X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001A\u0010\u000B\u001A\u00020\fX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$AlpnProvider;", "Ljava/lang/reflect/InvocationHandler;", "protocols", "", "", "(Ljava/util/List;)V", "selected", "getSelected", "()Ljava/lang/String;", "setSelected", "(Ljava/lang/String;)V", "unsupported", "", "getUnsupported", "()Z", "setUnsupported", "(Z)V", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    static final class AlpnProvider implements InvocationHandler {
        private final List protocols;
        private String selected;
        private boolean unsupported;

        public AlpnProvider(List list0) {
            Intrinsics.checkNotNullParameter(list0, "protocols");
            super();
            this.protocols = list0;
        }

        public final String getSelected() {
            return this.selected;
        }

        public final boolean getUnsupported() {
            return this.unsupported;
        }

        @Override
        public Object invoke(Object object0, Method method0, Object[] arr_object) throws Throwable {
            Intrinsics.checkNotNullParameter(object0, "proxy");
            Intrinsics.checkNotNullParameter(method0, "method");
            if(arr_object == null) {
                arr_object = new Object[0];
            }
            String s = method0.getName();
            Class class0 = method0.getReturnType();
            if(Intrinsics.areEqual(s, "supports") && Intrinsics.areEqual(Boolean.TYPE, class0)) {
                return true;
            }
            if(Intrinsics.areEqual(s, "unsupported") && Intrinsics.areEqual(Void.TYPE, class0)) {
                this.unsupported = true;
                return null;
            }
            if(Intrinsics.areEqual(s, "protocols") && arr_object.length == 0) {
                return this.protocols;
            }
            if((Intrinsics.areEqual(s, "selectProtocol") || Intrinsics.areEqual(s, "select")) && Intrinsics.areEqual(String.class, class0) && arr_object.length == 1) {
                Object object1 = arr_object[0];
                if(object1 instanceof List) {
                    Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.collections.List<*>");
                    int v = ((List)object1).size();
                    if(v >= 0) {
                        for(int v1 = 0; true; ++v1) {
                            Object object2 = ((List)object1).get(v1);
                            Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type kotlin.String");
                            String s1 = (String)object2;
                            if(this.protocols.contains(s1)) {
                                this.selected = s1;
                                return s1;
                            }
                            if(v1 == v) {
                                break;
                            }
                        }
                    }
                    String s2 = (String)this.protocols.get(0);
                    this.selected = s2;
                    return s2;
                }
            }
            if((Intrinsics.areEqual(s, "protocolSelected") || Intrinsics.areEqual(s, "selected")) && arr_object.length == 1) {
                Object object3 = arr_object[0];
                Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.String");
                this.selected = (String)object3;
                return null;
            }
            return method0.invoke(this, Arrays.copyOf(arr_object, arr_object.length));
        }

        public final void setSelected(String s) {
            this.selected = s;
        }

        public final void setUnsupported(boolean z) {
            this.unsupported = z;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/platform/Jdk8WithJettyBootPlatform$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Platform buildIfSupported() {
            try {
                Intrinsics.checkNotNullExpressionValue("0.9", "jvmVersion");
                if(Integer.parseInt("0.9") >= 9) {
                    return null;
                }
            }
            catch(NumberFormatException unused_ex) {
            }
            try {
                Class class0 = Class.forName("org.eclipse.jetty.alpn.ALPN", true, null);
                Class class1 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider", true, null);
                Class class2 = Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider", true, null);
                Class class3 = Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider", true, null);
                Method method0 = class0.getMethod("put", SSLSocket.class, class1);
                Method method1 = class0.getMethod("get", SSLSocket.class);
                Method method2 = class0.getMethod("remove", SSLSocket.class);
                Intrinsics.checkNotNullExpressionValue(method0, "putMethod");
                Intrinsics.checkNotNullExpressionValue(method1, "getMethod");
                Intrinsics.checkNotNullExpressionValue(method2, "removeMethod");
                Intrinsics.checkNotNullExpressionValue(class2, "clientProviderClass");
                Intrinsics.checkNotNullExpressionValue(class3, "serverProviderClass");
                return new Jdk8WithJettyBootPlatform(method0, method1, method2, class2, class3);
            }
            catch(ClassNotFoundException | NoSuchMethodException unused_ex) {
                return null;
            }
        }
    }

    public static final Companion Companion;
    private final Class clientProviderClass;
    private final Method getMethod;
    private final Method putMethod;
    private final Method removeMethod;
    private final Class serverProviderClass;

    static {
        Jdk8WithJettyBootPlatform.Companion = new Companion(null);
    }

    public Jdk8WithJettyBootPlatform(Method method0, Method method1, Method method2, Class class0, Class class1) {
        Intrinsics.checkNotNullParameter(method0, "putMethod");
        Intrinsics.checkNotNullParameter(method1, "getMethod");
        Intrinsics.checkNotNullParameter(method2, "removeMethod");
        Intrinsics.checkNotNullParameter(class0, "clientProviderClass");
        Intrinsics.checkNotNullParameter(class1, "serverProviderClass");
        super();
        this.putMethod = method0;
        this.getMethod = method1;
        this.removeMethod = method2;
        this.clientProviderClass = class0;
        this.serverProviderClass = class1;
    }

    @Override  // okhttp3.internal.platform.Platform
    public void afterHandshake(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        try {
            this.removeMethod.invoke(null, sSLSocket0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new AssertionError("failed to remove ALPN", illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new AssertionError("failed to remove ALPN", invocationTargetException0);
        }
    }

    @Override  // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket sSLSocket0, String s, List list0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        List list1 = Platform.Companion.alpnProtocolNames(list0);
        try {
            Object[] arr_object = {sSLSocket0, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new AlpnProvider(list1))};
            this.putMethod.invoke(null, arr_object);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new AssertionError("failed to set ALPN", invocationTargetException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new AssertionError("failed to set ALPN", illegalAccessException0);
        }
    }

    @Override  // okhttp3.internal.platform.Platform
    public String getSelectedProtocol(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        try {
            InvocationHandler invocationHandler0 = Proxy.getInvocationHandler(this.getMethod.invoke(null, sSLSocket0));
            Intrinsics.checkNotNull(invocationHandler0, "null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
            if(!((AlpnProvider)invocationHandler0).getUnsupported() && ((AlpnProvider)invocationHandler0).getSelected() == null) {
                Platform.log$default(this, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 0, null, 6, null);
                return null;
            }
            return ((AlpnProvider)invocationHandler0).getUnsupported() ? null : ((AlpnProvider)invocationHandler0).getSelected();
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new AssertionError("failed to get ALPN selected protocol", invocationTargetException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new AssertionError("failed to get ALPN selected protocol", illegalAccessException0);
        }
    }
}

