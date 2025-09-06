package com.navercorp.nid.oauth;

import kotlin.Deprecated;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u000E\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\bR\u001C\u0010\u0005\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\n\u0010\u000B\u001A\u0004\b\f\u0010\bj\u0002\b\rj\u0002\b\u000Ej\u0002\b\u000Fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthBehavior;", "", "allowsNaverApp", "", "allowsCustomTabs", "allowsWebView", "(Ljava/lang/String;IZZZ)V", "getAllowsCustomTabs", "()Z", "getAllowsNaverApp", "getAllowsWebView$annotations", "()V", "getAllowsWebView", "DEFAULT", "NAVERAPP", "CUSTOMTABS", "WEBVIEW", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public enum NidOAuthBehavior {
    DEFAULT(true, true, true),
    NAVERAPP(true, false, false),
    CUSTOMTABS(false, true, false),
    @Deprecated(message = "WebView is deprecated")
    WEBVIEW(false, false, true);

    private final boolean allowsCustomTabs;
    private final boolean allowsNaverApp;
    private final boolean allowsWebView;

    private static final NidOAuthBehavior[] $values() [...] // Inlined contents

    private NidOAuthBehavior(boolean z, boolean z1, boolean z2) {
        this.allowsNaverApp = z;
        this.allowsCustomTabs = z1;
        this.allowsWebView = z2;
    }

    public final boolean getAllowsCustomTabs() {
        return this.allowsCustomTabs;
    }

    public final boolean getAllowsNaverApp() {
        return this.allowsNaverApp;
    }

    public final boolean getAllowsWebView() {
        return this.allowsWebView;
    }

    @Deprecated(message = "WebView is deprecated")
    public static void getAllowsWebView$annotations() {
    }
}

