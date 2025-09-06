package org.chromium.support_lib_boundary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Set;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface WebSettingsBoundaryInterface {
    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributionBehavior {
        public static final int APP_SOURCE_AND_APP_TRIGGER = 3;
        public static final int APP_SOURCE_AND_WEB_TRIGGER = 1;
        public static final int DISABLED = 0;
        public static final int WEB_SOURCE_AND_WEB_TRIGGER = 2;

    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ForceDarkBehavior {
        public static final int FORCE_DARK_ONLY = 0;
        public static final int MEDIA_QUERY_ONLY = 1;
        public static final int PREFER_MEDIA_QUERY_OVER_FORCE_DARK = 2;

    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SpeculativeLoadingStatus {
        public static final int DISABLED = 0;
        public static final int PRERENDER_ENABLED = 1;

    }

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.TYPE_USE})
    public @interface WebViewMediaIntegrityApiStatus {
        public static final int DISABLED = 0;
        public static final int ENABLED = 2;
        public static final int ENABLED_WITHOUT_APP_IDENTITY = 1;

    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WebauthnSupport {
        public static final int APP = 1;
        public static final int BROWSER = 2;
        public static final int NONE;

    }

    int getAttributionBehavior();

    boolean getBackForwardCacheEnabled();

    int getDisabledActionModeMenuItems();

    boolean getEnterpriseAuthenticationAppLinkPolicyEnabled();

    int getForceDark();

    int getForceDarkBehavior();

    boolean getOffscreenPreRaster();

    Set getRequestedWithHeaderOriginAllowList();

    boolean getSafeBrowsingEnabled();

    int getSpeculativeLoadingStatus();

    Map getUserAgentMetadataMap();

    int getWebViewMediaIntegrityApiDefaultStatus();

    Map getWebViewMediaIntegrityApiOverrideRules();

    int getWebauthnSupport();

    boolean getWillSuppressErrorPage();

    boolean isAlgorithmicDarkeningAllowed();

    void setAlgorithmicDarkeningAllowed(boolean arg1);

    void setAttributionBehavior(int arg1);

    void setBackForwardCacheEnabled(boolean arg1);

    void setDisabledActionModeMenuItems(int arg1);

    void setEnterpriseAuthenticationAppLinkPolicyEnabled(boolean arg1);

    void setForceDark(int arg1);

    void setForceDarkBehavior(int arg1);

    void setOffscreenPreRaster(boolean arg1);

    void setRequestedWithHeaderOriginAllowList(Set arg1);

    void setSafeBrowsingEnabled(boolean arg1);

    void setSpeculativeLoadingStatus(int arg1);

    void setUserAgentMetadataFromMap(Map arg1);

    void setWebViewMediaIntegrityApiStatus(int arg1, Map arg2);

    void setWebauthnSupport(int arg1);

    void setWillSuppressErrorPage(boolean arg1);
}

