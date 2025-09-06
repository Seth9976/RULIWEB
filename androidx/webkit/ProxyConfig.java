package androidx.webkit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ProxyConfig {
    public static final class Builder {
        private final List mBypassRules;
        private final List mProxyRules;
        private boolean mReverseBypass;

        public Builder() {
            this.mReverseBypass = false;
            this.mProxyRules = new ArrayList();
            this.mBypassRules = new ArrayList();
        }

        public Builder(ProxyConfig proxyConfig0) {
            this.mReverseBypass = false;
            this.mProxyRules = proxyConfig0.getProxyRules();
            this.mBypassRules = proxyConfig0.getBypassRules();
            this.mReverseBypass = proxyConfig0.isReverseBypassEnabled();
        }

        public Builder addBypassRule(String s) {
            this.mBypassRules.add(s);
            return this;
        }

        public Builder addDirect() {
            return this.addDirect("*");
        }

        public Builder addDirect(String s) {
            ProxyRule proxyConfig$ProxyRule0 = new ProxyRule(s, "direct://");
            this.mProxyRules.add(proxyConfig$ProxyRule0);
            return this;
        }

        public Builder addProxyRule(String s) {
            ProxyRule proxyConfig$ProxyRule0 = new ProxyRule(s);
            this.mProxyRules.add(proxyConfig$ProxyRule0);
            return this;
        }

        public Builder addProxyRule(String s, String s1) {
            ProxyRule proxyConfig$ProxyRule0 = new ProxyRule(s1, s);
            this.mProxyRules.add(proxyConfig$ProxyRule0);
            return this;
        }

        public ProxyConfig build() {
            return new ProxyConfig(this.proxyRules(), this.bypassRules(), this.reverseBypass());
        }

        private List bypassRules() {
            return this.mBypassRules;
        }

        public Builder bypassSimpleHostnames() {
            return this.addBypassRule("<local>");
        }

        private List proxyRules() {
            return this.mProxyRules;
        }

        public Builder removeImplicitRules() {
            return this.addBypassRule("<-loopback>");
        }

        private boolean reverseBypass() {
            return this.mReverseBypass;
        }

        public Builder setReverseBypassEnabled(boolean z) {
            this.mReverseBypass = z;
            return this;
        }
    }

    public static final class ProxyRule {
        private final String mSchemeFilter;
        private final String mUrl;

        public ProxyRule(String s) {
            this("*", s);
        }

        public ProxyRule(String s, String s1) {
            this.mSchemeFilter = s;
            this.mUrl = s1;
        }

        public String getSchemeFilter() {
            return this.mSchemeFilter;
        }

        public String getUrl() {
            return this.mUrl;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProxyScheme {
    }

    private static final String BYPASS_RULE_REMOVE_IMPLICIT = "<-loopback>";
    private static final String BYPASS_RULE_SIMPLE_NAMES = "<local>";
    private static final String DIRECT = "direct://";
    public static final String MATCH_ALL_SCHEMES = "*";
    public static final String MATCH_HTTP = "http";
    public static final String MATCH_HTTPS = "https";
    private final List mBypassRules;
    private final List mProxyRules;
    private final boolean mReverseBypass;

    public ProxyConfig(List list0, List list1, boolean z) {
        this.mProxyRules = list0;
        this.mBypassRules = list1;
        this.mReverseBypass = z;
    }

    public List getBypassRules() {
        return Collections.unmodifiableList(this.mBypassRules);
    }

    public List getProxyRules() {
        return Collections.unmodifiableList(this.mProxyRules);
    }

    public boolean isReverseBypassEnabled() {
        return this.mReverseBypass;
    }
}

