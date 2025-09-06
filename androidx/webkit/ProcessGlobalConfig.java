package androidx.webkit;

import android.content.Context;
import androidx.lifecycle.LifecycleKt..ExternalSyntheticBackportWithForwarding0;
import androidx.webkit.internal.ApiHelperForP;
import androidx.webkit.internal.WebViewFeatureInternal;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ProcessGlobalConfig {
    String mCacheDirectoryBasePath;
    String mDataDirectoryBasePath;
    String mDataDirectorySuffix;
    Boolean mPartitionedCookiesEnabled;
    private static boolean sApplyCalled;
    private static final Object sLock;
    private static final AtomicReference sProcessGlobalConfig;

    static {
        ProcessGlobalConfig.sProcessGlobalConfig = new AtomicReference();
        ProcessGlobalConfig.sLock = new Object();
        ProcessGlobalConfig.sApplyCalled = false;
    }

    public static void apply(ProcessGlobalConfig processGlobalConfig0) {
        synchronized(ProcessGlobalConfig.sLock) {
            if(!ProcessGlobalConfig.sApplyCalled) {
                ProcessGlobalConfig.sApplyCalled = true;
                HashMap hashMap0 = new HashMap();
                if(ProcessGlobalConfig.webViewCurrentlyLoaded()) {
                    throw new IllegalStateException("WebView has already been loaded in the current process, so any attempt to apply the settings in ProcessGlobalConfig will have no effect. ProcessGlobalConfig#apply needs to be called before any calls to android.webkit APIs, such as during early app startup.");
                }
                if(processGlobalConfig0.mDataDirectorySuffix != null) {
                    ApiHelperForP.setDataDirectorySuffix(processGlobalConfig0.mDataDirectorySuffix);
                }
                String s = processGlobalConfig0.mDataDirectoryBasePath;
                if(s != null) {
                    hashMap0.put("DATA_DIRECTORY_BASE_PATH", s);
                }
                String s1 = processGlobalConfig0.mCacheDirectoryBasePath;
                if(s1 != null) {
                    hashMap0.put("CACHE_DIRECTORY_BASE_PATH", s1);
                }
                Boolean boolean0 = processGlobalConfig0.mPartitionedCookiesEnabled;
                if(boolean0 != null) {
                    hashMap0.put("CONFIGURE_PARTITIONED_COOKIES", boolean0);
                }
                if(!LifecycleKt..ExternalSyntheticBackportWithForwarding0.m(ProcessGlobalConfig.sProcessGlobalConfig, null, hashMap0)) {
                    throw new RuntimeException("Attempting to set ProcessGlobalConfig#sProcessGlobalConfig when it was already set");
                }
                return;
            }
        }
        throw new IllegalStateException("ProcessGlobalConfig#apply was called more than once, which is an illegal operation. The configuration settings provided by ProcessGlobalConfig take effect only once, when WebView is first loaded into the current process. Every process should only ever create a single instance of ProcessGlobalConfig and apply it once, before any calls to android.webkit APIs, such as during early app startup.");
    }

    public ProcessGlobalConfig setDataDirectorySuffix(Context context0, String s) {
        if(!WebViewFeatureInternal.STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX.isSupported(context0)) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        if(s.equals("")) {
            throw new IllegalArgumentException("Suffix cannot be an empty string");
        }
        if(s.indexOf(((int)File.separatorChar)) >= 0) {
            throw new IllegalArgumentException("Suffix " + s + " contains a path separator");
        }
        this.mDataDirectorySuffix = s;
        return this;
    }

    public ProcessGlobalConfig setDirectoryBasePaths(Context context0, File file0, File file1) {
        if(!WebViewFeatureInternal.STARTUP_FEATURE_SET_DIRECTORY_BASE_PATH.isSupported(context0)) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        if(!file0.isAbsolute()) {
            throw new IllegalArgumentException("dataDirectoryBasePath must be a non-empty absolute path");
        }
        if(!file1.isAbsolute()) {
            throw new IllegalArgumentException("cacheDirectoryBasePath must be a non-empty absolute path");
        }
        this.mDataDirectoryBasePath = file0.getAbsolutePath();
        this.mCacheDirectoryBasePath = file1.getAbsolutePath();
        return this;
    }

    public ProcessGlobalConfig setPartitionedCookiesEnabled(Context context0, boolean z) {
        if(!WebViewFeatureInternal.STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES.isSupported(context0)) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        this.mPartitionedCookiesEnabled = Boolean.valueOf(z);
        return this;
    }

    private static boolean webViewCurrentlyLoaded() {
        try {
            Field field0 = Class.forName("android.webkit.WebViewFactory").getDeclaredField("sProviderInstance");
            field0.setAccessible(true);
            if(field0.get(null) != null) {
                return true;
            }
        }
        catch(Exception unused_ex) {
        }
        return false;
    }
}

