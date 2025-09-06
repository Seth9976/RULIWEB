package androidx.webkit.internal;

import androidx.webkit.UserAgentMetadata;
import androidx.webkit.WebViewMediaIntegrityApiStatusConfig.Builder;
import androidx.webkit.WebViewMediaIntegrityApiStatusConfig;
import java.util.Map;
import java.util.Set;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;

public class WebSettingsAdapter {
    private final WebSettingsBoundaryInterface mBoundaryInterface;

    public WebSettingsAdapter(WebSettingsBoundaryInterface webSettingsBoundaryInterface0) {
        this.mBoundaryInterface = webSettingsBoundaryInterface0;
    }

    public int getAttributionRegistrationBehavior() {
        return this.mBoundaryInterface.getAttributionBehavior();
    }

    public boolean getBackForwardCacheEnabled() {
        return this.mBoundaryInterface.getBackForwardCacheEnabled();
    }

    public int getDisabledActionModeMenuItems() {
        return this.mBoundaryInterface.getDisabledActionModeMenuItems();
    }

    public boolean getEnterpriseAuthenticationAppLinkPolicyEnabled() {
        return this.mBoundaryInterface.getEnterpriseAuthenticationAppLinkPolicyEnabled();
    }

    public int getForceDark() {
        return this.mBoundaryInterface.getForceDark();
    }

    public int getForceDarkStrategy() {
        return this.mBoundaryInterface.getForceDarkBehavior();
    }

    public boolean getOffscreenPreRaster() {
        return this.mBoundaryInterface.getOffscreenPreRaster();
    }

    public Set getRequestedWithHeaderOriginAllowList() {
        return this.mBoundaryInterface.getRequestedWithHeaderOriginAllowList();
    }

    public boolean getSafeBrowsingEnabled() {
        return this.mBoundaryInterface.getSafeBrowsingEnabled();
    }

    public int getSpeculativeLoadingStatus() {
        return this.mBoundaryInterface.getSpeculativeLoadingStatus();
    }

    public UserAgentMetadata getUserAgentMetadata() {
        return UserAgentMetadataInternal.getUserAgentMetadataFromMap(this.mBoundaryInterface.getUserAgentMetadataMap());
    }

    public int getWebAuthenticationSupport() {
        return this.mBoundaryInterface.getWebauthnSupport();
    }

    public WebViewMediaIntegrityApiStatusConfig getWebViewMediaIntegrityApiStatus() {
        return new Builder(this.mBoundaryInterface.getWebViewMediaIntegrityApiDefaultStatus()).setOverrideRules(this.mBoundaryInterface.getWebViewMediaIntegrityApiOverrideRules()).build();
    }

    public boolean isAlgorithmicDarkeningAllowed() {
        return this.mBoundaryInterface.isAlgorithmicDarkeningAllowed();
    }

    public void setAlgorithmicDarkeningAllowed(boolean z) {
        this.mBoundaryInterface.setAlgorithmicDarkeningAllowed(z);
    }

    public void setAttributionRegistrationBehavior(int v) {
        this.mBoundaryInterface.setAttributionBehavior(v);
    }

    public void setBackForwardCacheEnabled(boolean z) {
        this.mBoundaryInterface.setBackForwardCacheEnabled(z);
    }

    public void setDisabledActionModeMenuItems(int v) {
        this.mBoundaryInterface.setDisabledActionModeMenuItems(v);
    }

    public void setEnterpriseAuthenticationAppLinkPolicyEnabled(boolean z) {
        this.mBoundaryInterface.setEnterpriseAuthenticationAppLinkPolicyEnabled(z);
    }

    public void setForceDark(int v) {
        this.mBoundaryInterface.setForceDark(v);
    }

    public void setForceDarkStrategy(int v) {
        this.mBoundaryInterface.setForceDarkBehavior(v);
    }

    public void setOffscreenPreRaster(boolean z) {
        this.mBoundaryInterface.setOffscreenPreRaster(z);
    }

    public void setRequestedWithHeaderOriginAllowList(Set set0) {
        this.mBoundaryInterface.setRequestedWithHeaderOriginAllowList(set0);
    }

    public void setSafeBrowsingEnabled(boolean z) {
        this.mBoundaryInterface.setSafeBrowsingEnabled(z);
    }

    public void setSpeculativeLoadingStatus(int v) {
        this.mBoundaryInterface.setSpeculativeLoadingStatus(v);
    }

    public void setUserAgentMetadata(UserAgentMetadata userAgentMetadata0) {
        Map map0 = UserAgentMetadataInternal.convertUserAgentMetadataToMap(userAgentMetadata0);
        this.mBoundaryInterface.setUserAgentMetadataFromMap(map0);
    }

    public void setWebAuthenticationSupport(int v) {
        this.mBoundaryInterface.setWebauthnSupport(v);
    }

    public void setWebViewMediaIntegrityApiStatus(WebViewMediaIntegrityApiStatusConfig webViewMediaIntegrityApiStatusConfig0) {
        this.mBoundaryInterface.setWebViewMediaIntegrityApiStatus(webViewMediaIntegrityApiStatusConfig0.getDefaultStatus(), webViewMediaIntegrityApiStatusConfig0.getOverrideRules());
    }
}

