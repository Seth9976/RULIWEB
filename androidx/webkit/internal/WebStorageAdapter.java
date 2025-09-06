package androidx.webkit.internal;

import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebStorageBoundaryInterface;

public class WebStorageAdapter implements WebStorageBoundaryInterface {
    final WebStorageBoundaryInterface mImpl;

    public WebStorageAdapter(WebStorageBoundaryInterface webStorageBoundaryInterface0) {
        this.mImpl = webStorageBoundaryInterface0;
    }

    @Override  // org.chromium.support_lib_boundary.WebStorageBoundaryInterface
    public void deleteBrowsingData(Executor executor0, Runnable runnable0) {
        this.mImpl.deleteBrowsingData(executor0, runnable0);
    }

    @Override  // org.chromium.support_lib_boundary.WebStorageBoundaryInterface
    public String deleteBrowsingDataForSite(String s, Executor executor0, Runnable runnable0) {
        return this.mImpl.deleteBrowsingDataForSite(s, executor0, runnable0);
    }
}

