package com.ruliweb.www.ruliapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001E2\u00020\u0001:\u0004\u001E\u001F !B\u0005¢\u0006\u0002\u0010\u0002J6\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00192\u0012\u0010\u001A\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00170\u001B2\u0012\u0010\u001C\u001A\u000E\u0012\u0004\u0012\u00020\u001D\u0012\u0004\u0012\u00020\u00170\u001BR(\u0010\u0003\u001A\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001A\u0010\n\u001A\u00020\u0005X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\f\"\u0004\b\r\u0010\u000ER\u0010\u0010\u000F\u001A\u0004\u0018\u00010\u0010X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u001A\u0010\u0012\u001A\u00020\u0005X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000ER\u000E\u0010\u0015\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/ruliweb/www/ruliapp/RestModule;", "", "()V", "data", "", "", "getData", "()Ljava/util/Map;", "setData", "(Ljava/util/Map;)V", "method", "getMethod", "()Ljava/lang/String;", "setMethod", "(Ljava/lang/String;)V", "module", "Lcom/ruliweb/www/ruliapp/RestModule$RequestModule;", "referer", "target", "getTarget", "setTarget", "url", "request", "", "context", "Landroid/content/Context;", "resultCallback", "Lkotlin/Function1;", "errorCallback", "Lcom/android/volley/VolleyError;", "Companion", "CurrentRestModule", "RegacyRestModule", "RequestModule", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class RestModule {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001A\u00020\u0004H\u0007R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ruliweb/www/ruliapp/RestModule$Companion;", "", "()V", "instance", "Lcom/ruliweb/www/ruliapp/RestModule;", "getInstance", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final RestModule getInstance() {
            RestModule restModule0 = RestModule.instance;
            if(restModule0 == null) {
                synchronized(this) {
                    RestModule restModule1 = RestModule.instance;
                    if(restModule1 == null) {
                        restModule1 = new RestModule();
                        RestModule.instance = restModule1;
                    }
                    return restModule1;
                }
            }
            return restModule0;
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000E\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EJV\u0010\u000F\u001A\u00020\f2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00112\u0014\u0010\u0013\u001A\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00142\u0012\u0010\u0015\u001A\u000E\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\f0\u00162\u0012\u0010\u0017\u001A\u000E\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\f0\u0016H\u0016R\u001B\u0010\u0005\u001A\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001A\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/ruliweb/www/ruliapp/RestModule$CurrentRestModule;", "Lcom/ruliweb/www/ruliapp/RestModule$RequestModule;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "requestQueue", "Lcom/android/volley/RequestQueue;", "getRequestQueue", "()Lcom/android/volley/RequestQueue;", "requestQueue$delegate", "Lkotlin/Lazy;", "addToRequestQueue", "", "req", "Lcom/android/volley/toolbox/JsonObjectRequest;", "sendRequest", "url", "", "method", "data", "", "resultCallback", "Lkotlin/Function1;", "errorCallback", "Lcom/android/volley/VolleyError;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class CurrentRestModule implements RequestModule {
        private final Lazy requestQueue$delegate;

        // 检测为 Lambda 实现
        public static void $r8$lambda$0Skzq9um4qK5Qf9rXUwRTm7RHDc(VolleyError volleyError0) [...]

        // 检测为 Lambda 实现
        public static void $r8$lambda$dXDCVSuuSNHYEnvpxbp-4LdeR64(Function1 function10, String s) [...]

        public CurrentRestModule(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            super();
            this.requestQueue$delegate = LazyKt.lazy(new Function0() {
                final Context $context;

                {
                    this.$context = context0;
                    super(0);
                }

                public final RequestQueue invoke() {
                    RequestQueue requestQueue0 = Volley.newRequestQueue(this.$context.getApplicationContext());
                    Intrinsics.checkNotNullExpressionValue(requestQueue0, "newRequestQueue(...)");
                    return requestQueue0;
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }
            });
        }

        public final void addToRequestQueue(JsonObjectRequest jsonObjectRequest0) {
            Intrinsics.checkNotNullParameter(jsonObjectRequest0, "req");
            this.getRequestQueue().add(jsonObjectRequest0);
        }

        public final RequestQueue getRequestQueue() {
            return (RequestQueue)this.requestQueue$delegate.getValue();
        }

        @Override  // com.ruliweb.www.ruliapp.RestModule$RequestModule
        public void sendRequest(String s, String s1, Map map0, Function1 function10, Function1 function11) {
            Intrinsics.checkNotNullParameter(s, "url");
            Intrinsics.checkNotNullParameter(s1, "method");
            Intrinsics.checkNotNullParameter(function10, "resultCallback");
            Intrinsics.checkNotNullParameter(function11, "errorCallback");
            DefaultImpls.sendRequest(this, s, s1, map0, function10, function11);
            IntRef ref$IntRef0 = new IntRef();
            String s2 = s1.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            ref$IntRef0.element = Intrinsics.areEqual(s2, "GET") || !Intrinsics.areEqual(s2, "POST") ? 0 : 1;
            com.ruliweb.www.ruliapp.RestModule.CurrentRestModule.sendRequest.stringRequest.1 restModule$CurrentRestModule$sendRequest$stringRequest$10 = new StringRequest(s, map0, (String s) -> CurrentRestModule.sendRequest$lambda$0(function10, s), (VolleyError volleyError0) -> CurrentRestModule.sendRequest$lambda$1(volleyError0)) {
                final Map $data;

                // 检测为 Lambda 实现
                public static void $r8$lambda$RVTIjsupYFZjqPyDaQmc4rKnZhA(Function2 function20, Object object0, Object object1) [...]

                {
                    this.$data = map0;
                    super(ref$IntRef0.element, s, response$Listener0, response$ErrorListener0);
                }

                @Override  // com.android.volley.Request
                public Map getHeaders() throws AuthFailureError {
                    Map map0 = super.getHeaders();
                    Intrinsics.checkNotNullExpressionValue(map0, "getHeaders(...)");
                    Map map1 = MapsKt.toMutableMap(map0);
                    map1.put("Origin", "https://m.ruliweb.com/");
                    map1.put("Referer", "https://m.ruliweb.com/");
                    map1.put("Accept", "application/json, text/javascript, */*;");
                    map1.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                    map1.put("User-Agent", "ruliweb_android_app");
                    return MapsKt.toMap(map1);
                }

                @Override  // com.android.volley.Request
                protected Map getParams() {
                    ObjectRef ref$ObjectRef0 = new ObjectRef();
                    Map map0 = super.getParams();
                    ref$ObjectRef0.element = map0 == null ? null : MapsKt.toMutableMap(map0);
                    if(this.$data != null) {
                        if(ref$ObjectRef0.element == null) {
                            ref$ObjectRef0.element = MapsKt.toMutableMap(MapsKt.emptyMap());
                        }
                        RestModule.CurrentRestModule.sendRequest.stringRequest.1..ExternalSyntheticLambda1 restModule$CurrentRestModule$sendRequest$stringRequest$1$$ExternalSyntheticLambda10 = (Object object0, Object object1) -> com.ruliweb.www.ruliapp.RestModule.CurrentRestModule.sendRequest.stringRequest.1.getParams$lambda$0(new com.ruliweb.www.ruliapp.RestModule.CurrentRestModule.sendRequest.stringRequest.1.getParams.1(ref$ObjectRef0), object0, object1);
                        this.$data.forEach(restModule$CurrentRestModule$sendRequest$stringRequest$1$$ExternalSyntheticLambda10);
                    }
                    return (Map)ref$ObjectRef0.element;

                    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "key", "", "value", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
                    final class com.ruliweb.www.ruliapp.RestModule.CurrentRestModule.sendRequest.stringRequest.1.getParams.1 extends Lambda implements Function2 {
                        final ObjectRef $params;

                        com.ruliweb.www.ruliapp.RestModule.CurrentRestModule.sendRequest.stringRequest.1.getParams.1(ObjectRef ref$ObjectRef0) {
                            this.$params = ref$ObjectRef0;
                            super(2);
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            this.invoke(((String)object0), ((String)object1));
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String s, String s1) {
                            Intrinsics.checkNotNullParameter(s, "key");
                            Intrinsics.checkNotNullParameter(s1, "value");
                            ((Map)this.$params.element).put(s, s1);
                        }
                    }

                }

                private static final void getParams$lambda$0(Function2 function20, Object object0, Object object1) {
                    Intrinsics.checkNotNullParameter(function20, "$tmp0");
                    function20.invoke(object0, object1);
                }
            };
            this.getRequestQueue().add(restModule$CurrentRestModule$sendRequest$stringRequest$10);
        }

        private static final void sendRequest$lambda$0(Function1 function10, String s) {
            Intrinsics.checkNotNullParameter(function10, "$tmp0");
            function10.invoke(s);
        }

        private static final void sendRequest$lambda$1(VolleyError volleyError0) {
            Intrinsics.checkNotNull(volleyError0);
            Log.e("ERRORMSG", ExceptionsKt.stackTraceToString(volleyError0));
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001A\u00020\u00022\u0016\u0010\u0006\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0007\"\u0004\u0018\u00010\u0002H\u0015¢\u0006\u0002\u0010\bJV\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u00022\u0006\u0010\f\u001A\u00020\u00022\u0014\u0010\r\u001A\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000E2\u0012\u0010\u000F\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u00102\u0012\u0010\u0011\u001A\u000E\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\n0\u0010H\u0016¨\u0006\u0013"}, d2 = {"Lcom/ruliweb/www/ruliapp/RestModule$RegacyRestModule;", "Landroid/os/AsyncTask;", "", "Lcom/ruliweb/www/ruliapp/RestModule$RequestModule;", "()V", "doInBackground", "params", "", "([Ljava/lang/String;)Ljava/lang/String;", "sendRequest", "", "url", "method", "data", "", "resultCallback", "Lkotlin/Function1;", "errorCallback", "Lcom/android/volley/VolleyError;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class RegacyRestModule extends AsyncTask implements RequestModule {
        @Override  // android.os.AsyncTask
        public Object doInBackground(Object[] arr_object) {
            return this.doInBackground(((String[])arr_object));
        }

        @Deprecated(message = "Deprecated in Java")
        protected String doInBackground(String[] arr_s) {
            Intrinsics.checkNotNullParameter(arr_s, "params");
            String s = arr_s[0];
            String s1 = arr_s[1];
            String s2 = arr_s[2];
            if(s == null) {
                return "failed - URL MISSING";
            }
            if(s1 == null) {
                s1 = "GET";
            }
            try {
                String s3 = s1.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                if(Intrinsics.areEqual(s3, "GET") && s2 != null) {
                    byte[] arr_b = s2.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
                    s = s + "?" + arr_b;
                }
                URLConnection uRLConnection0 = new URL(s).openConnection();
                Intrinsics.checkNotNull(uRLConnection0, "null cannot be cast to non-null type java.net.HttpURLConnection");
                if(s2 != null && s2.length() > 0) {
                    String s4 = s1.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(s4, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                    if(Intrinsics.areEqual(s4, "POST")) {
                        ((HttpURLConnection)uRLConnection0).setRequestMethod("POST");
                        BufferedOutputStream bufferedOutputStream0 = new BufferedOutputStream(((HttpURLConnection)uRLConnection0).getOutputStream());
                        BufferedWriter bufferedWriter0 = new BufferedWriter(new OutputStreamWriter(bufferedOutputStream0, "UTF-8"));
                        bufferedWriter0.write(s2);
                        bufferedWriter0.flush();
                        bufferedWriter0.close();
                        bufferedOutputStream0.close();
                    }
                }
                ((HttpURLConnection)uRLConnection0).connect();
                ((HttpURLConnection)uRLConnection0).disconnect();
                if(((HttpURLConnection)uRLConnection0).getResponseCode() < 400) {
                    String s5 = ((HttpURLConnection)uRLConnection0).getResponseMessage();
                    System.out.println(s5);
                    String s6 = ((HttpURLConnection)uRLConnection0).getResponseMessage();
                    Intrinsics.checkNotNullExpressionValue(s6, "getResponseMessage(...)");
                    return s6;
                }
                return "failed with server connection";
            }
            catch(Exception exception0) {
                System.out.println(exception0.getMessage());
                return "failed";
            }
        }

        @Override  // com.ruliweb.www.ruliapp.RestModule$RequestModule
        public void sendRequest(String s, String s1, Map map0, Function1 function10, Function1 function11) {
            Intrinsics.checkNotNullParameter(s, "url");
            Intrinsics.checkNotNullParameter(s1, "method");
            Intrinsics.checkNotNullParameter(function10, "resultCallback");
            Intrinsics.checkNotNullParameter(function11, "errorCallback");
            DefaultImpls.sendRequest(this, s, s1, map0, function10, function11);
            if(map0 != null) {
                String s2 = "";
                for(Object object0: map0.keySet()) {
                    s2 = s2 + ((String)object0) + "=" + map0.get(((String)object0)) + "&";
                }
                StringsKt.dropLast(s2, 1);
            }
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bb\u0018\u00002\u00020\u0001JZ\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u00052\u0014\u0010\u0007\u001A\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\u0012\u0010\t\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\n2\u0012\u0010\u000B\u001A\u000E\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\nH\u0016¨\u0006\r"}, d2 = {"Lcom/ruliweb/www/ruliapp/RestModule$RequestModule;", "", "sendRequest", "", "url", "", "method", "data", "", "resultCallback", "Lkotlin/Function1;", "errorCallback", "Lcom/android/volley/VolleyError;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    interface RequestModule {
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
        public static final class DefaultImpls {
            public static void sendRequest(RequestModule restModule$RequestModule0, String s, String s1, Map map0, Function1 function10, Function1 function11) {
                Intrinsics.checkNotNullParameter(s, "url");
                Intrinsics.checkNotNullParameter(s1, "method");
                Intrinsics.checkNotNullParameter(function10, "resultCallback");
                Intrinsics.checkNotNullParameter(function11, "errorCallback");
            }

            public static void sendRequest$default(RequestModule restModule$RequestModule0, String s, String s1, Map map0, Function1 function10, Function1 function11, int v, Object object0) {
                if(object0 != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendRequest");
                }
                if((v & 1) != 0) {
                    s = "https://api.ruliweb.com";
                }
                if((v & 2) != 0) {
                    s1 = "GET";
                }
                restModule$RequestModule0.sendRequest(s, s1, map0, function10, function11);
            }
        }

        void sendRequest(String arg1, String arg2, Map arg3, Function1 arg4, Function1 arg5);
    }

    public static final Companion Companion;
    private Map data;
    private static volatile RestModule instance;
    private String method;
    private RequestModule module;
    private String referer;
    private String target;
    private String url;

    static {
        RestModule.Companion = new Companion(null);
    }

    public RestModule() {
        this.url = "https://api.ruliweb.com/";
        this.referer = "https://m.ruliweb.com/";
        this.target = "";
        this.method = "GET";
    }

    public final Map getData() {
        return this.data;
    }

    @JvmStatic
    public static final RestModule getInstance() {
        return RestModule.Companion.getInstance();
    }

    public final String getMethod() {
        return this.method;
    }

    public final String getTarget() {
        return this.target;
    }

    public final void request(Context context0, Function1 function10, Function1 function11) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(function10, "resultCallback");
        Intrinsics.checkNotNullParameter(function11, "errorCallback");
        if(this.module == null) {
            this.module = 30 > Build.VERSION.SDK_INT ? new RegacyRestModule() : new CurrentRestModule(context0);
        }
        RequestModule restModule$RequestModule0 = this.module;
        Intrinsics.checkNotNull(restModule$RequestModule0);
        restModule$RequestModule0.sendRequest(this.url + this.target, this.method, this.data, function10, function11);
    }

    public final void setData(Map map0) {
        this.data = map0;
    }

    public final void setMethod(String s) {
        Intrinsics.checkNotNullParameter(s, "<set-?>");
        this.method = s;
    }

    public final void setTarget(String s) {
        Intrinsics.checkNotNullParameter(s, "<set-?>");
        this.target = s;
    }
}

