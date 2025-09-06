package androidx.webkit;

import java.util.HashMap;
import java.util.Map;

public class WebViewMediaIntegrityApiStatusConfig {
    public static final class Builder {
        private final int mDefaultStatus;
        private Map mOverrideRules;

        public Builder(int v) {
            this.mDefaultStatus = v;
            this.mOverrideRules = new HashMap();
        }

        public Builder addOverrideRule(String s, int v) {
            this.mOverrideRules.put(s, v);
            return this;
        }

        public WebViewMediaIntegrityApiStatusConfig build() {
            return new WebViewMediaIntegrityApiStatusConfig(this);
        }

        public Builder setOverrideRules(Map map0) {
            this.mOverrideRules = map0;
            return this;
        }
    }

    public static final int WEBVIEW_MEDIA_INTEGRITY_API_DISABLED = 0;
    public static final int WEBVIEW_MEDIA_INTEGRITY_API_ENABLED = 2;
    public static final int WEBVIEW_MEDIA_INTEGRITY_API_ENABLED_WITHOUT_APP_IDENTITY = 1;
    private final int mDefaultStatus;
    private final Map mOverrideRules;

    public WebViewMediaIntegrityApiStatusConfig(Builder webViewMediaIntegrityApiStatusConfig$Builder0) {
        this.mDefaultStatus = webViewMediaIntegrityApiStatusConfig$Builder0.mDefaultStatus;
        this.mOverrideRules = webViewMediaIntegrityApiStatusConfig$Builder0.mOverrideRules;
    }

    public int getDefaultStatus() {
        return this.mDefaultStatus;
    }

    public Map getOverrideRules() {
        return this.mOverrideRules;
    }
}

