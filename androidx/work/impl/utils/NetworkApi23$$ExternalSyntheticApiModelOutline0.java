package androidx.work.impl.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobInfo.TriggerContentUri;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.pm.PackageManager.ComponentInfoFlags;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.loader.ResourcesLoader;
import android.graphics.drawable.ColorStateListDrawable;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.NetworkSpecifier;
import android.net.Uri;
import android.view.DisplayCutout;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.autofill.AutofillManager;
import android.webkit.SafeBrowsingResponse;
import android.webkit.ServiceWorkerWebSettings;
import android.webkit.WebMessagePort;
import android.webkit.WebResourceError;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;
import dalvik.system.DelegateLastClassLoader;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public final class NetworkApi23..ExternalSyntheticApiModelOutline0 {
    public static int m(ApplicationExitInfo applicationExitInfo0) {
        return applicationExitInfo0.getReason();
    }

    public static int m(DisplayCutout displayCutout0) {
        return displayCutout0.getSafeInsetRight();
    }

    public static int m(WebSettings webSettings0) {
        return webSettings0.getForceDark();
    }

    public static long m(ApplicationExitInfo applicationExitInfo0) {
        return applicationExitInfo0.getTimestamp();
    }

    public static ApplicationExitInfo m(Object object0) {
        return (ApplicationExitInfo)object0;
    }

    public static JobInfo.Builder m(JobInfo.Builder jobInfo$Builder0, long v) {
        return jobInfo$Builder0.setTriggerContentUpdateDelay(v);
    }

    public static JobInfo.Builder m(JobInfo.Builder jobInfo$Builder0, JobInfo.TriggerContentUri jobInfo$TriggerContentUri0) {
        return jobInfo$Builder0.addTriggerContentUri(jobInfo$TriggerContentUri0);
    }

    public static JobInfo.Builder m(JobInfo.Builder jobInfo$Builder0, NetworkRequest networkRequest0) {
        return jobInfo$Builder0.setRequiredNetwork(networkRequest0);
    }

    public static JobInfo.Builder m(JobInfo.Builder jobInfo$Builder0, String s) {
        return jobInfo$Builder0.setTraceTag(s);
    }

    public static JobInfo.Builder m(JobInfo.Builder jobInfo$Builder0, boolean z) {
        return jobInfo$Builder0.setRequiresStorageNotLow(z);
    }

    public static JobInfo.TriggerContentUri m(Uri uri0, int v) {
        return new JobInfo.TriggerContentUri(uri0, v);
    }

    public static JobScheduler m(JobScheduler jobScheduler0, String s) {
        return jobScheduler0.forNamespace(s);
    }

    public static PackageManager.ComponentInfoFlags m(long v) {
        return PackageManager.ComponentInfoFlags.of(v);
    }

    public static ServiceInfo m(PackageManager packageManager0, ComponentName componentName0, PackageManager.ComponentInfoFlags packageManager$ComponentInfoFlags0) {
        return packageManager0.getServiceInfo(componentName0, packageManager$ComponentInfoFlags0);
    }

    public static ResourcesLoader m() {
        return new ResourcesLoader();
    }

    public static NetworkSpecifier m(NetworkRequest networkRequest0) {
        return networkRequest0.getNetworkSpecifier();
    }

    public static DisplayCutout m(Object object0) [...] // Inlined contents

    public static WindowMetrics m(WindowManager windowManager0) {
        return windowManager0.getMaximumWindowMetrics();
    }

    public static AutofillManager m(Object object0) {
        return (AutofillManager)object0;
    }

    public static SafeBrowsingResponse m(Object object0) {
        return (SafeBrowsingResponse)object0;
    }

    public static ServiceWorkerWebSettings m(Object object0) {
        return (ServiceWorkerWebSettings)object0;
    }

    public static WebMessagePort m(Object object0) {
        return (WebMessagePort)object0;
    }

    public static WebResourceError m(Object object0) {
        return (WebResourceError)object0;
    }

    public static WebViewRenderProcess m(Object object0) {
        return (WebViewRenderProcess)object0;
    }

    public static DelegateLastClassLoader m(String s, ClassLoader classLoader0) {
        return new DelegateLastClassLoader(s, classLoader0);
    }

    public static Class m() {
        return Consumer.class;
    }

    public static List m(ActivityManager activityManager0, String s, int v, int v1) {
        return activityManager0.getHistoricalProcessExitReasons(s, v, v1);
    }

    public static void m() {
    }

    public static void m(ConnectivityManager connectivityManager0, ConnectivityManager.NetworkCallback connectivityManager$NetworkCallback0) {
        connectivityManager0.registerDefaultNetworkCallback(connectivityManager$NetworkCallback0);
    }

    public static void m(WebSettings webSettings0, int v) {
        webSettings0.setForceDark(v);
    }

    public static void m(WebView webView0, Executor executor0, WebViewRenderProcessClient webViewRenderProcessClient0) {
        webView0.setWebViewRenderProcessClient(executor0, webViewRenderProcessClient0);
    }

    public static boolean m(Activity activity0) {
        return activity0.isInMultiWindowMode();
    }

    public static boolean m(NetworkRequest networkRequest0, int v) {
        return networkRequest0.hasTransport(v);
    }

    public static boolean m(NetworkRequest networkRequest0, NetworkCapabilities networkCapabilities0) {
        return networkRequest0.canBeSatisfiedBy(networkCapabilities0);
    }

    public static boolean m(Object object0) {
        return object0 instanceof DisplayCutout;
    }

    public static int[] m(NetworkRequest networkRequest0) {
        return networkRequest0.getTransportTypes();
    }

    public static int m$1(DisplayCutout displayCutout0) {
        return displayCutout0.getSafeInsetBottom();
    }

    public static JobInfo.Builder m$1(JobInfo.Builder jobInfo$Builder0, long v) {
        return jobInfo$Builder0.setTriggerContentMaxDelay(v);
    }

    public static JobInfo.Builder m$1(JobInfo.Builder jobInfo$Builder0, boolean z) {
        return jobInfo$Builder0.setExpedited(z);
    }

    public static void m$1() {
    }

    public static boolean m$1(NetworkRequest networkRequest0, int v) {
        return networkRequest0.hasCapability(v);
    }

    public static boolean m$1(Object object0) {
        return object0 instanceof ColorStateListDrawable;
    }

    public static int[] m$1(NetworkRequest networkRequest0) {
        return networkRequest0.getCapabilities();
    }

    public static int m$2(DisplayCutout displayCutout0) {
        return displayCutout0.getSafeInsetLeft();
    }

    public static JobInfo.Builder m$2(JobInfo.Builder jobInfo$Builder0, boolean z) {
        return jobInfo$Builder0.setImportantWhileForeground(z);
    }

    public static void m$2() {
    }

    public static int m$3(DisplayCutout displayCutout0) {
        return displayCutout0.getSafeInsetTop();
    }

    public static JobInfo.Builder m$3(JobInfo.Builder jobInfo$Builder0, boolean z) {
        return jobInfo$Builder0.setRequiresBatteryNotLow(z);
    }
}

