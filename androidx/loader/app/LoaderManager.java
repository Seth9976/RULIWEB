package androidx.loader.app;

import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class LoaderManager {
    public interface LoaderCallbacks {
        Loader onCreateLoader(int arg1, Bundle arg2);

        void onLoadFinished(Loader arg1, Object arg2);

        void onLoaderReset(Loader arg1);
    }

    public abstract void destroyLoader(int arg1);

    @Deprecated
    public abstract void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4);

    public static void enableDebugLogging(boolean z) {
        LoaderManagerImpl.DEBUG = z;
    }

    public static LoaderManager getInstance(LifecycleOwner lifecycleOwner0) {
        return new LoaderManagerImpl(lifecycleOwner0, ((ViewModelStoreOwner)lifecycleOwner0).getViewModelStore());
    }

    public abstract Loader getLoader(int arg1);

    public boolean hasRunningLoaders() {
        return false;
    }

    public abstract Loader initLoader(int arg1, Bundle arg2, LoaderCallbacks arg3);

    public abstract void markForRedelivery();

    public abstract Loader restartLoader(int arg1, Bundle arg2, LoaderCallbacks arg3);
}

