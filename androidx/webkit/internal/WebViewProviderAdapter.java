package androidx.webkit.internal;

import android.net.Uri;
import android.os.CancellationSignal;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import androidx.webkit.PrerenderException;
import androidx.webkit.PrerenderOperationCallback;
import androidx.webkit.Profile;
import androidx.webkit.SpeculativeLoadingParameters;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebMessagePortCompat;
import androidx.webkit.WebViewCompat.VisualStateCallback;
import androidx.webkit.WebViewCompat.WebMessageListener;
import androidx.webkit.WebViewRenderProcess;
import androidx.webkit.WebViewRenderProcessClient;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.ProfileBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebViewProviderAdapter {
    final WebViewProviderBoundaryInterface mImpl;

    public WebViewProviderAdapter(WebViewProviderBoundaryInterface webViewProviderBoundaryInterface0) {
        this.mImpl = webViewProviderBoundaryInterface0;
    }

    public ScriptHandlerImpl addDocumentStartJavaScript(String s, String[] arr_s) {
        return ScriptHandlerImpl.toScriptHandler(this.mImpl.addDocumentStartJavaScript(s, arr_s));
    }

    public void addWebMessageListener(String s, String[] arr_s, WebMessageListener webViewCompat$WebMessageListener0) {
        InvocationHandler invocationHandler0 = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessageListenerAdapter(webViewCompat$WebMessageListener0));
        this.mImpl.addWebMessageListener(s, arr_s, invocationHandler0);
    }

    public WebMessagePortCompat[] createWebMessageChannel() {
        InvocationHandler[] arr_invocationHandler = this.mImpl.createWebMessageChannel();
        WebMessagePortCompat[] arr_webMessagePortCompat = new WebMessagePortCompat[arr_invocationHandler.length];
        for(int v = 0; v < arr_invocationHandler.length; ++v) {
            arr_webMessagePortCompat[v] = new WebMessagePortImpl(arr_invocationHandler[v]);
        }
        return arr_webMessagePortCompat;
    }

    public Profile getProfile() {
        InvocationHandler invocationHandler0 = this.mImpl.getProfile();
        return new ProfileImpl(((ProfileBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(ProfileBoundaryInterface.class, invocationHandler0)));
    }

    public WebChromeClient getWebChromeClient() {
        return this.mImpl.getWebChromeClient();
    }

    public WebViewClient getWebViewClient() {
        return this.mImpl.getWebViewClient();
    }

    public WebViewRenderProcess getWebViewRenderProcess() {
        return WebViewRenderProcessImpl.forInvocationHandler(this.mImpl.getWebViewRenderer());
    }

    public WebViewRenderProcessClient getWebViewRenderProcessClient() {
        InvocationHandler invocationHandler0 = this.mImpl.getWebViewRendererClient();
        return invocationHandler0 == null ? null : ((WebViewRenderProcessClientAdapter)BoundaryInterfaceReflectionUtil.getDelegateFromInvocationHandler(invocationHandler0)).getWebViewRenderProcessClient();
    }

    public void insertVisualStateCallback(long v, VisualStateCallback webViewCompat$VisualStateCallback0) {
        InvocationHandler invocationHandler0 = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new VisualStateCallbackAdapter(webViewCompat$VisualStateCallback0));
        this.mImpl.insertVisualStateCallback(v, invocationHandler0);
    }

    public boolean isAudioMuted() {
        return this.mImpl.isAudioMuted();
    }

    // 检测为 Lambda 实现
    static void lambda$prerenderUrlAsync$0(PrerenderOperationCallback prerenderOperationCallback0, Void void0) [...]

    // 检测为 Lambda 实现
    static void lambda$prerenderUrlAsync$1(PrerenderOperationCallback prerenderOperationCallback0, Throwable throwable0) [...]

    // 检测为 Lambda 实现
    static void lambda$prerenderUrlAsync$2(PrerenderOperationCallback prerenderOperationCallback0, Void void0) [...]

    // 检测为 Lambda 实现
    static void lambda$prerenderUrlAsync$3(PrerenderOperationCallback prerenderOperationCallback0, Throwable throwable0) [...]

    public void postWebMessage(WebMessageCompat webMessageCompat0, Uri uri0) {
        InvocationHandler invocationHandler0 = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessageAdapter(webMessageCompat0));
        this.mImpl.postMessageToMainFrame(invocationHandler0, uri0);
    }

    public void prerenderUrlAsync(String s, CancellationSignal cancellationSignal0, Executor executor0, PrerenderOperationCallback prerenderOperationCallback0) {
        WebViewProviderAdapter..ExternalSyntheticLambda2 webViewProviderAdapter$$ExternalSyntheticLambda20 = (Void void0) -> prerenderOperationCallback0.onPrerenderActivated();
        WebViewProviderAdapter..ExternalSyntheticLambda3 webViewProviderAdapter$$ExternalSyntheticLambda30 = (Throwable throwable0) -> prerenderOperationCallback0.onError(new PrerenderException("Prerender operation failed", throwable0));
        this.mImpl.prerenderUrl(s, cancellationSignal0, executor0, webViewProviderAdapter$$ExternalSyntheticLambda20, webViewProviderAdapter$$ExternalSyntheticLambda30);
    }

    public void prerenderUrlAsync(String s, CancellationSignal cancellationSignal0, Executor executor0, SpeculativeLoadingParameters speculativeLoadingParameters0, PrerenderOperationCallback prerenderOperationCallback0) {
        InvocationHandler invocationHandler0 = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new SpeculativeLoadingParametersAdapter(speculativeLoadingParameters0));
        WebViewProviderAdapter..ExternalSyntheticLambda0 webViewProviderAdapter$$ExternalSyntheticLambda00 = (Void void0) -> prerenderOperationCallback0.onPrerenderActivated();
        WebViewProviderAdapter..ExternalSyntheticLambda1 webViewProviderAdapter$$ExternalSyntheticLambda10 = (Throwable throwable0) -> prerenderOperationCallback0.onError(new PrerenderException("Prerender operation failed", throwable0));
        this.mImpl.prerenderUrl(s, cancellationSignal0, executor0, invocationHandler0, webViewProviderAdapter$$ExternalSyntheticLambda00, webViewProviderAdapter$$ExternalSyntheticLambda10);
    }

    public void removeWebMessageListener(String s) {
        this.mImpl.removeWebMessageListener(s);
    }

    public void setAudioMuted(boolean z) {
        this.mImpl.setAudioMuted(z);
    }

    public void setProfileWithName(String s) {
        this.mImpl.setProfile(s);
    }

    public void setWebViewRenderProcessClient(Executor executor0, WebViewRenderProcessClient webViewRenderProcessClient0) {
        InvocationHandler invocationHandler0 = webViewRenderProcessClient0 == null ? null : BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebViewRenderProcessClientAdapter(executor0, webViewRenderProcessClient0));
        this.mImpl.setWebViewRendererClient(invocationHandler0);
    }
}

