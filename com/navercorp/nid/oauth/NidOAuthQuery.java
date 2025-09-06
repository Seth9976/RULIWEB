package com.navercorp.nid.oauth;

import com.navercorp.nid.util.NidDeviceUtil;
import com.navercorp.nid.util.NidNetworkUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00042\u00020\u0001:\u0003\u0003\u0004\u0005B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthQuery;", "", "()V", "Builder", "Companion", "Method", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthQuery {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001A\u00020\u0004J\u0012\u0010\u000E\u001A\u00020\u00042\b\u0010\u000F\u001A\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0010\u001A\u00020\u0004H\u0002J\b\u0010\u0011\u001A\u00020\u0004H\u0002J\u001E\u0010\u0012\u001A\u00020\u00042\u0014\u0010\u0013\u001A\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0014H\u0002J\u0010\u0010\u0015\u001A\u00020\u00002\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004J\u000E\u0010\u0016\u001A\u00020\u00002\u0006\u0010\b\u001A\u00020\tR\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u0004\u0018\u00010\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u000B\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthQuery$Builder;", "", "()V", "authType", "", "callbackUrl", "clientId", "locale", "method", "Lcom/navercorp/nid/oauth/NidOAuthQuery$Method;", "network", "state", "version", "build", "encode", "s", "generateCustomTabsOAuthQuery", "generateQuery", "parametersToQuery", "parameters", "", "setAuthType", "setMethod", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Builder {
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 0x30)
        public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] arr_v = new int[Method.values().length];
                arr_v[Method.CUSTOM_TABS.ordinal()] = 1;
                WhenMappings.$EnumSwitchMapping$0 = arr_v;
            }
        }

        private String authType;
        private String callbackUrl;
        private String clientId;
        private String locale;
        private Method method;
        private String network;
        private String state;
        private String version;

        public Builder() {
            this.clientId = NidOAuthPreferencesManager.getClientId();
            this.state = NidOAuthPreferencesManager.INSTANCE.getInitState();
            this.callbackUrl = NidOAuthPreferencesManager.getCallbackUrl();
            this.locale = NidDeviceUtil.INSTANCE.getLocale();
            this.network = NidNetworkUtil.INSTANCE.getType();
            this.version = "5.10.0";
        }

        public final String build() {
            return this.generateQuery();
        }

        private final String encode(String s) {
            if(s == null) {
                return "";
            }
            String s1 = URLEncoder.encode(s, "UTF-8");
            Intrinsics.checkNotNullExpressionValue(s1, "encode(s, \"UTF-8\")");
            return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(s1, "+", "%20", false, 4, null), "*", "%2A", false, 4, null), "%7E", "~", false, 4, null);
        }

        private final String generateCustomTabsOAuthQuery() {
            Map map0 = MapsKt.hashMapOf(new Pair[]{TuplesKt.to("client_id", this.clientId), TuplesKt.to("inapp_view", "custom_tab"), TuplesKt.to("response_type", "code"), TuplesKt.to("oauth_os", "android"), TuplesKt.to("version", "android-" + this.version), TuplesKt.to("locale", this.locale), TuplesKt.to("redirect_uri", this.callbackUrl), TuplesKt.to("state", this.state)});
            map0.put("network", this.network);
            if(Intrinsics.areEqual(this.authType, "reprompt")) {
                map0.put("auth_type", "reprompt");
            }
            return "https://nid.naver.com/oauth2.0/authorize?" + this.parametersToQuery(map0);
        }

        // 去混淆评级： 低(20)
        private final String generateQuery() {
            return (this.method == null ? -1 : WhenMappings.$EnumSwitchMapping$0[this.method.ordinal()]) == 1 ? this.generateCustomTabsOAuthQuery() : this.generateCustomTabsOAuthQuery();
        }

        private final String parametersToQuery(Map map0) {
            Set set0 = map0.keySet();
            StringBuilder stringBuilder0 = new StringBuilder();
            for(Object object0: set0) {
                String s = (String)map0.get(((String)object0));
                if(stringBuilder0.length() > 0) {
                    stringBuilder0.append("&");
                }
                stringBuilder0.append(((String)object0) + "=");
                try {
                    stringBuilder0.append(this.encode(s));
                }
                catch(UnsupportedEncodingException unused_ex) {
                    stringBuilder0.append(s);
                }
            }
            String s1 = stringBuilder0.toString();
            Intrinsics.checkNotNullExpressionValue(s1, "query.toString()");
            return s1;
        }

        public final Builder setAuthType(String s) {
            this.authType = s;
            return this;
        }

        public final Builder setMethod(Method nidOAuthQuery$Method0) {
            Intrinsics.checkNotNullParameter(nidOAuthQuery$Method0, "method");
            this.method = nidOAuthQuery$Method0;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthQuery$Companion;", "", "()V", "REQUEST_AUTHORIZE_URL", "", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003¨\u0006\u0004"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthQuery$Method;", "", "(Ljava/lang/String;I)V", "CUSTOM_TABS", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static enum Method {
        CUSTOM_TABS;

        private static final Method[] $values() [...] // Inlined contents
    }

    public static final Companion Companion = null;
    public static final String REQUEST_AUTHORIZE_URL = "https://nid.naver.com/oauth2.0/authorize?";

    static {
        NidOAuthQuery.Companion = new Companion(null);
    }
}

