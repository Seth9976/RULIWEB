package androidx.webkit;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceResponse;
import androidx.core.util.Pair;
import androidx.webkit.internal.AssetHelper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class WebViewAssetLoader {
    public static final class AssetsPathHandler implements PathHandler {
        private final AssetHelper mAssetHelper;

        public AssetsPathHandler(Context context0) {
            this.mAssetHelper = new AssetHelper(context0);
        }

        AssetsPathHandler(AssetHelper assetHelper0) {
            this.mAssetHelper = assetHelper0;
        }

        @Override  // androidx.webkit.WebViewAssetLoader$PathHandler
        public WebResourceResponse handle(String s) {
            try {
                InputStream inputStream0 = this.mAssetHelper.openAsset(s);
                return new WebResourceResponse(AssetHelper.guessMimeType(s), null, inputStream0);
            }
            catch(IOException iOException0) {
                Log.e("WebViewAssetLoader", "Error opening asset path: " + s, iOException0);
                return new WebResourceResponse(null, null, null);
            }
        }
    }

    public static final class Builder {
        private String mDomain;
        private final List mHandlerList;
        private boolean mHttpAllowed;

        public Builder() {
            this.mDomain = "appassets.androidplatform.net";
            this.mHandlerList = new ArrayList();
        }

        public Builder addPathHandler(String s, PathHandler webViewAssetLoader$PathHandler0) {
            Pair pair0 = Pair.create(s, webViewAssetLoader$PathHandler0);
            this.mHandlerList.add(pair0);
            return this;
        }

        public WebViewAssetLoader build() {
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: this.mHandlerList) {
                arrayList0.add(new PathMatcher(this.mDomain, ((String)((Pair)object0).first), this.mHttpAllowed, ((PathHandler)((Pair)object0).second)));
            }
            return new WebViewAssetLoader(arrayList0);
        }

        public Builder setDomain(String s) {
            this.mDomain = s;
            return this;
        }

        public Builder setHttpAllowed(boolean z) {
            this.mHttpAllowed = z;
            return this;
        }
    }

    public static final class InternalStoragePathHandler implements PathHandler {
        private static final String[] FORBIDDEN_DATA_DIRS;
        private final File mDirectory;

        static {
            InternalStoragePathHandler.FORBIDDEN_DATA_DIRS = new String[]{"app_webview/", "databases/", "lib/", "shared_prefs/", "code_cache/"};
        }

        public InternalStoragePathHandler(Context context0, File file0) {
            try {
                this.mDirectory = new File(AssetHelper.getCanonicalDirPath(file0));
                if(!this.isAllowedInternalStorageDir(context0)) {
                    throw new IllegalArgumentException("The given directory \"" + file0 + "\" doesn\'t exist under an allowed app internal storage directory");
                }
            }
            catch(IOException iOException0) {
                throw new IllegalArgumentException("Failed to resolve the canonical path for the given directory: " + file0.getPath(), iOException0);
            }
        }

        @Override  // androidx.webkit.WebViewAssetLoader$PathHandler
        public WebResourceResponse handle(String s) {
            try {
                File file0 = AssetHelper.getCanonicalFileIfChild(this.mDirectory, s);
                if(file0 != null) {
                    InputStream inputStream0 = AssetHelper.openFile(file0);
                    return new WebResourceResponse(AssetHelper.guessMimeType(s), null, inputStream0);
                }
                Log.e("WebViewAssetLoader", String.format("The requested file: %s is outside the mounted directory: %s", s, this.mDirectory));
                return new WebResourceResponse(null, null, null);
            }
            catch(IOException iOException0) {
                Log.e("WebViewAssetLoader", "Error opening the requested path: " + s, iOException0);
                return new WebResourceResponse(null, null, null);
            }
        }

        private boolean isAllowedInternalStorageDir(Context context0) throws IOException {
            String s = AssetHelper.getCanonicalDirPath(this.mDirectory);
            String s1 = AssetHelper.getCanonicalDirPath(context0.getCacheDir());
            String s2 = AssetHelper.getCanonicalDirPath(AssetHelper.getDataDir(context0));
            if(!s.startsWith(s1) && !s.startsWith(s2)) {
                return false;
            }
            if(!s.equals(s1) && !s.equals(s2)) {
                String[] arr_s = InternalStoragePathHandler.FORBIDDEN_DATA_DIRS;
                for(int v = 0; v < arr_s.length; ++v) {
                    if(s.startsWith(s2 + arr_s[v])) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    }

    public interface PathHandler {
        WebResourceResponse handle(String arg1);
    }

    static class PathMatcher {
        static final String HTTPS_SCHEME = "https";
        static final String HTTP_SCHEME = "http";
        final String mAuthority;
        final PathHandler mHandler;
        final boolean mHttpEnabled;
        final String mPath;

        PathMatcher(String s, String s1, boolean z, PathHandler webViewAssetLoader$PathHandler0) {
            if(s1.isEmpty() || s1.charAt(0) != 0x2F) {
                throw new IllegalArgumentException("Path should start with a slash \'/\'.");
            }
            if(!s1.endsWith("/")) {
                throw new IllegalArgumentException("Path should end with a slash \'/\'");
            }
            this.mAuthority = s;
            this.mPath = s1;
            this.mHttpEnabled = z;
            this.mHandler = webViewAssetLoader$PathHandler0;
        }

        public String getSuffixPath(String s) {
            return s.replaceFirst(this.mPath, "");
        }

        public PathHandler match(Uri uri0) {
            if(uri0.getScheme().equals("http") && !this.mHttpEnabled) {
                return null;
            }
            if(!uri0.getScheme().equals("http") && !uri0.getScheme().equals("https")) {
                return null;
            }
            if(!uri0.getAuthority().equals(this.mAuthority)) {
                return null;
            }
            return uri0.getPath().startsWith(this.mPath) ? this.mHandler : null;
        }
    }

    public static final class ResourcesPathHandler implements PathHandler {
        private final AssetHelper mAssetHelper;

        public ResourcesPathHandler(Context context0) {
            this.mAssetHelper = new AssetHelper(context0);
        }

        ResourcesPathHandler(AssetHelper assetHelper0) {
            this.mAssetHelper = assetHelper0;
        }

        @Override  // androidx.webkit.WebViewAssetLoader$PathHandler
        public WebResourceResponse handle(String s) {
            try {
                InputStream inputStream0 = this.mAssetHelper.openResource(s);
                return new WebResourceResponse(AssetHelper.guessMimeType(s), null, inputStream0);
            }
            catch(Resources.NotFoundException resources$NotFoundException0) {
                Log.e("WebViewAssetLoader", "Resource not found from the path: " + s, resources$NotFoundException0);
                return new WebResourceResponse(null, null, null);
            }
            catch(IOException iOException0) {
                Log.e("WebViewAssetLoader", "Error opening resource from the path: " + s, iOException0);
                return new WebResourceResponse(null, null, null);
            }
        }
    }

    public static final String DEFAULT_DOMAIN = "appassets.androidplatform.net";
    private static final String TAG = "WebViewAssetLoader";
    private final List mMatchers;

    WebViewAssetLoader(List list0) {
        this.mMatchers = list0;
    }

    public WebResourceResponse shouldInterceptRequest(Uri uri0) {
        for(Object object0: this.mMatchers) {
            PathMatcher webViewAssetLoader$PathMatcher0 = (PathMatcher)object0;
            PathHandler webViewAssetLoader$PathHandler0 = webViewAssetLoader$PathMatcher0.match(uri0);
            if(webViewAssetLoader$PathHandler0 != null) {
                WebResourceResponse webResourceResponse0 = webViewAssetLoader$PathHandler0.handle(webViewAssetLoader$PathMatcher0.getSuffixPath(uri0.getPath()));
                if(webResourceResponse0 != null) {
                    return webResourceResponse0;
                }
                if(false) {
                    break;
                }
            }
        }
        return null;
    }
}

