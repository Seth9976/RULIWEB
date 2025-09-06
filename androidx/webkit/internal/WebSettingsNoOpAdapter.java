package androidx.webkit.internal;

import androidx.webkit.UserAgentMetadata;
import androidx.webkit.WebViewMediaIntegrityApiStatusConfig.Builder;
import androidx.webkit.WebViewMediaIntegrityApiStatusConfig;
import java.util.Collections;
import java.util.Set;

public class WebSettingsNoOpAdapter extends WebSettingsAdapter {
    public WebSettingsNoOpAdapter() {
        super(null);
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public int getAttributionRegistrationBehavior() {
        return 1;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public boolean getBackForwardCacheEnabled() {
        return false;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public int getDisabledActionModeMenuItems() {
        return 0;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public boolean getEnterpriseAuthenticationAppLinkPolicyEnabled() {
        return false;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public int getForceDark() {
        return 1;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public int getForceDarkStrategy() {
        return 2;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public boolean getOffscreenPreRaster() {
        return false;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public Set getRequestedWithHeaderOriginAllowList() {
        return Collections.EMPTY_SET;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public boolean getSafeBrowsingEnabled() {
        return true;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public int getSpeculativeLoadingStatus() {
        return 0;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public UserAgentMetadata getUserAgentMetadata() {
        return UserAgentMetadataInternal.getUserAgentMetadataFromMap(Collections.EMPTY_MAP);
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public int getWebAuthenticationSupport() {
        return 0;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public WebViewMediaIntegrityApiStatusConfig getWebViewMediaIntegrityApiStatus() {
        return new Builder(2).build();
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public boolean isAlgorithmicDarkeningAllowed() {
        return false;
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setAlgorithmicDarkeningAllowed(boolean z) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setAttributionRegistrationBehavior(int v) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setBackForwardCacheEnabled(boolean z) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setDisabledActionModeMenuItems(int v) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setEnterpriseAuthenticationAppLinkPolicyEnabled(boolean z) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setForceDark(int v) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setForceDarkStrategy(int v) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setOffscreenPreRaster(boolean z) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setRequestedWithHeaderOriginAllowList(Set set0) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setSafeBrowsingEnabled(boolean z) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setSpeculativeLoadingStatus(int v) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setUserAgentMetadata(UserAgentMetadata userAgentMetadata0) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setWebAuthenticationSupport(int v) {
    }

    @Override  // androidx.webkit.internal.WebSettingsAdapter
    public void setWebViewMediaIntegrityApiStatus(WebViewMediaIntegrityApiStatusConfig webViewMediaIntegrityApiStatusConfig0) {
    }
}

