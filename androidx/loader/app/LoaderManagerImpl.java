package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.collection.SparseArrayCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory.-CC;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.loader.content.Loader.OnLoadCompleteListener;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl extends LoaderManager {
    public static class LoaderInfo extends MutableLiveData implements OnLoadCompleteListener {
        private final Bundle mArgs;
        private final int mId;
        private LifecycleOwner mLifecycleOwner;
        private final Loader mLoader;
        private LoaderObserver mObserver;
        private Loader mPriorLoader;

        LoaderInfo(int v, Bundle bundle0, Loader loader0, Loader loader1) {
            this.mId = v;
            this.mArgs = bundle0;
            this.mLoader = loader0;
            this.mPriorLoader = loader1;
            loader0.registerListener(v, this);
        }

        Loader destroy(boolean z) {
            if(LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.mLoader.cancelLoad();
            this.mLoader.abandon();
            LoaderObserver loaderManagerImpl$LoaderObserver0 = this.mObserver;
            if(loaderManagerImpl$LoaderObserver0 != null) {
                this.removeObserver(loaderManagerImpl$LoaderObserver0);
                if(z) {
                    loaderManagerImpl$LoaderObserver0.reset();
                }
            }
            this.mLoader.unregisterListener(this);
            if(loaderManagerImpl$LoaderObserver0 != null && !loaderManagerImpl$LoaderObserver0.hasDeliveredData() || z) {
                this.mLoader.reset();
                return this.mPriorLoader;
            }
            return this.mLoader;
        }

        public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
            printWriter0.print(s);
            printWriter0.print("mId=");
            printWriter0.print(this.mId);
            printWriter0.print(" mArgs=");
            printWriter0.println(this.mArgs);
            printWriter0.print(s);
            printWriter0.print("mLoader=");
            printWriter0.println(this.mLoader);
            this.mLoader.dump(s + "  ", fileDescriptor0, printWriter0, arr_s);
            if(this.mObserver != null) {
                printWriter0.print(s);
                printWriter0.print("mCallbacks=");
                printWriter0.println(this.mObserver);
                this.mObserver.dump(s + "  ", printWriter0);
            }
            printWriter0.print(s);
            printWriter0.print("mData=");
            printWriter0.println(this.getLoader().dataToString(this.getValue()));
            printWriter0.print(s);
            printWriter0.print("mStarted=");
            printWriter0.println(this.hasActiveObservers());
        }

        Loader getLoader() {
            return this.mLoader;
        }

        // 去混淆评级： 低(20)
        boolean isCallbackWaitingForData() {
            return this.hasActiveObservers() ? this.mObserver != null && !this.mObserver.hasDeliveredData() : false;
        }

        void markForRedelivery() {
            LifecycleOwner lifecycleOwner0 = this.mLifecycleOwner;
            LoaderObserver loaderManagerImpl$LoaderObserver0 = this.mObserver;
            if(lifecycleOwner0 != null && loaderManagerImpl$LoaderObserver0 != null) {
                super.removeObserver(loaderManagerImpl$LoaderObserver0);
                this.observe(lifecycleOwner0, loaderManagerImpl$LoaderObserver0);
            }
        }

        @Override  // androidx.lifecycle.LiveData
        protected void onActive() {
            if(LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.mLoader.startLoading();
        }

        @Override  // androidx.lifecycle.LiveData
        protected void onInactive() {
            if(LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.mLoader.stopLoading();
        }

        @Override  // androidx.loader.content.Loader$OnLoadCompleteListener
        public void onLoadComplete(Loader loader0, Object object0) {
            if(LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if(Looper.myLooper() == Looper.getMainLooper()) {
                this.setValue(object0);
                return;
            }
            if(LoaderManagerImpl.DEBUG) {
                Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
            }
            this.postValue(object0);
        }

        @Override  // androidx.lifecycle.LiveData
        public void removeObserver(Observer observer0) {
            super.removeObserver(observer0);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        Loader setCallback(LifecycleOwner lifecycleOwner0, LoaderCallbacks loaderManager$LoaderCallbacks0) {
            LoaderObserver loaderManagerImpl$LoaderObserver0 = new LoaderObserver(this.mLoader, loaderManager$LoaderCallbacks0);
            this.observe(lifecycleOwner0, loaderManagerImpl$LoaderObserver0);
            LoaderObserver loaderManagerImpl$LoaderObserver1 = this.mObserver;
            if(loaderManagerImpl$LoaderObserver1 != null) {
                this.removeObserver(loaderManagerImpl$LoaderObserver1);
            }
            this.mLifecycleOwner = lifecycleOwner0;
            this.mObserver = loaderManagerImpl$LoaderObserver0;
            return this.mLoader;
        }

        @Override  // androidx.lifecycle.MutableLiveData
        public void setValue(Object object0) {
            super.setValue(object0);
            Loader loader0 = this.mPriorLoader;
            if(loader0 != null) {
                loader0.reset();
                this.mPriorLoader = null;
            }
        }

        @Override
        public String toString() {
            Class class0 = this.mLoader.getClass();
            return "LoaderInfo{" + Integer.toHexString(System.identityHashCode(this)) + " #" + this.mId + " : " + class0.getSimpleName() + "{" + Integer.toHexString(System.identityHashCode(class0)) + "}}";
        }
    }

    static class LoaderObserver implements Observer {
        private final LoaderCallbacks mCallback;
        private boolean mDeliveredData;
        private final Loader mLoader;

        LoaderObserver(Loader loader0, LoaderCallbacks loaderManager$LoaderCallbacks0) {
            this.mDeliveredData = false;
            this.mLoader = loader0;
            this.mCallback = loaderManager$LoaderCallbacks0;
        }

        public void dump(String s, PrintWriter printWriter0) {
            printWriter0.print(s);
            printWriter0.print("mDeliveredData=");
            printWriter0.println(this.mDeliveredData);
        }

        boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        @Override  // androidx.lifecycle.Observer
        public void onChanged(Object object0) {
            if(LoaderManagerImpl.DEBUG) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.mLoader + ": " + this.mLoader.dataToString(object0));
            }
            this.mDeliveredData = true;
            this.mCallback.onLoadFinished(this.mLoader, object0);
        }

        void reset() {
            if(this.mDeliveredData) {
                if(LoaderManagerImpl.DEBUG) {
                    Log.v("LoaderManager", "  Resetting: " + this.mLoader);
                }
                this.mCallback.onLoaderReset(this.mLoader);
            }
        }

        @Override
        public String toString() {
            return this.mCallback.toString();
        }
    }

    static class LoaderViewModel extends ViewModel {
        private static final Factory FACTORY;
        private boolean mCreatingLoader;
        private SparseArrayCompat mLoaders;

        static {
            LoaderViewModel.FACTORY = new Factory() {
                @Override  // androidx.lifecycle.ViewModelProvider$Factory
                public ViewModel create(Class class0) {
                    return new LoaderViewModel();
                }

                @Override  // androidx.lifecycle.ViewModelProvider$Factory
                public ViewModel create(Class class0, CreationExtras creationExtras0) {
                    return ViewModelProvider.Factory.-CC.$default$create(this, class0, creationExtras0);
                }
            };
        }

        LoaderViewModel() {
            this.mLoaders = new SparseArrayCompat();
            this.mCreatingLoader = false;
        }

        public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
            if(this.mLoaders.size() > 0) {
                printWriter0.print(s);
                printWriter0.println("Loaders:");
                for(int v = 0; v < this.mLoaders.size(); ++v) {
                    LoaderInfo loaderManagerImpl$LoaderInfo0 = (LoaderInfo)this.mLoaders.valueAt(v);
                    printWriter0.print(s);
                    printWriter0.print("  #");
                    printWriter0.print(this.mLoaders.keyAt(v));
                    printWriter0.print(": ");
                    printWriter0.println(loaderManagerImpl$LoaderInfo0.toString());
                    loaderManagerImpl$LoaderInfo0.dump(s + "    ", fileDescriptor0, printWriter0, arr_s);
                }
            }
        }

        void finishCreatingLoader() {
            this.mCreatingLoader = false;
        }

        static LoaderViewModel getInstance(ViewModelStore viewModelStore0) {
            return (LoaderViewModel)new ViewModelProvider(viewModelStore0, LoaderViewModel.FACTORY).get(LoaderViewModel.class);
        }

        LoaderInfo getLoader(int v) {
            return (LoaderInfo)this.mLoaders.get(v);
        }

        boolean hasRunningLoaders() {
            int v = this.mLoaders.size();
            for(int v1 = 0; v1 < v; ++v1) {
                if(((LoaderInfo)this.mLoaders.valueAt(v1)).isCallbackWaitingForData()) {
                    return true;
                }
            }
            return false;
        }

        boolean isCreatingLoader() {
            return this.mCreatingLoader;
        }

        void markForRedelivery() {
            int v = this.mLoaders.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ((LoaderInfo)this.mLoaders.valueAt(v1)).markForRedelivery();
            }
        }

        @Override  // androidx.lifecycle.ViewModel
        protected void onCleared() {
            super.onCleared();
            int v = this.mLoaders.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ((LoaderInfo)this.mLoaders.valueAt(v1)).destroy(true);
            }
            this.mLoaders.clear();
        }

        void putLoader(int v, LoaderInfo loaderManagerImpl$LoaderInfo0) {
            this.mLoaders.put(v, loaderManagerImpl$LoaderInfo0);
        }

        void removeLoader(int v) {
            this.mLoaders.remove(v);
        }

        void startCreatingLoader() {
            this.mCreatingLoader = true;
        }
    }

    static boolean DEBUG = false;
    private static final String TAG = "LoaderManager";
    private final LifecycleOwner mLifecycleOwner;
    private final LoaderViewModel mLoaderViewModel;

    static {
    }

    LoaderManagerImpl(LifecycleOwner lifecycleOwner0, ViewModelStore viewModelStore0) {
        this.mLifecycleOwner = lifecycleOwner0;
        this.mLoaderViewModel = LoaderViewModel.getInstance(viewModelStore0);
    }

    private Loader createAndInstallLoader(int v, Bundle bundle0, LoaderCallbacks loaderManager$LoaderCallbacks0, Loader loader0) {
        try {
            this.mLoaderViewModel.startCreatingLoader();
            Loader loader1 = loaderManager$LoaderCallbacks0.onCreateLoader(v, bundle0);
            if(loader1 != null) {
                if(loader1.getClass().isMemberClass() && !Modifier.isStatic(loader1.getClass().getModifiers())) {
                    throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + loader1);
                }
                LoaderInfo loaderManagerImpl$LoaderInfo0 = new LoaderInfo(v, bundle0, loader1, loader0);
                if(LoaderManagerImpl.DEBUG) {
                    Log.v("LoaderManager", "  Created new loader " + loaderManagerImpl$LoaderInfo0);
                }
                this.mLoaderViewModel.putLoader(v, loaderManagerImpl$LoaderInfo0);
                return loaderManagerImpl$LoaderInfo0.setCallback(this.mLifecycleOwner, loaderManager$LoaderCallbacks0);
            }
        }
        finally {
            this.mLoaderViewModel.finishCreatingLoader();
        }
        throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
    }

    @Override  // androidx.loader.app.LoaderManager
    public void destroyLoader(int v) {
        if(this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if(Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
        if(LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "destroyLoader in " + this + " of " + v);
        }
        LoaderInfo loaderManagerImpl$LoaderInfo0 = this.mLoaderViewModel.getLoader(v);
        if(loaderManagerImpl$LoaderInfo0 != null) {
            loaderManagerImpl$LoaderInfo0.destroy(true);
            this.mLoaderViewModel.removeLoader(v);
        }
    }

    @Override  // androidx.loader.app.LoaderManager
    @Deprecated
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        this.mLoaderViewModel.dump(s, fileDescriptor0, printWriter0, arr_s);
    }

    @Override  // androidx.loader.app.LoaderManager
    public Loader getLoader(int v) {
        if(this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        LoaderInfo loaderManagerImpl$LoaderInfo0 = this.mLoaderViewModel.getLoader(v);
        return loaderManagerImpl$LoaderInfo0 == null ? null : loaderManagerImpl$LoaderInfo0.getLoader();
    }

    @Override  // androidx.loader.app.LoaderManager
    public boolean hasRunningLoaders() {
        return this.mLoaderViewModel.hasRunningLoaders();
    }

    @Override  // androidx.loader.app.LoaderManager
    public Loader initLoader(int v, Bundle bundle0, LoaderCallbacks loaderManager$LoaderCallbacks0) {
        if(this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if(Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        LoaderInfo loaderManagerImpl$LoaderInfo0 = this.mLoaderViewModel.getLoader(v);
        if(LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle0);
        }
        if(loaderManagerImpl$LoaderInfo0 == null) {
            return this.createAndInstallLoader(v, bundle0, loaderManager$LoaderCallbacks0, null);
        }
        if(LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Re-using existing loader " + loaderManagerImpl$LoaderInfo0);
        }
        return loaderManagerImpl$LoaderInfo0.setCallback(this.mLifecycleOwner, loaderManager$LoaderCallbacks0);
    }

    @Override  // androidx.loader.app.LoaderManager
    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    @Override  // androidx.loader.app.LoaderManager
    public Loader restartLoader(int v, Bundle bundle0, LoaderCallbacks loaderManager$LoaderCallbacks0) {
        if(this.mLoaderViewModel.isCreatingLoader()) {
            throw new IllegalStateException("Called while creating a loader");
        }
        if(Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("restartLoader must be called on the main thread");
        }
        if(LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "restartLoader in " + this + ": args=" + bundle0);
        }
        LoaderInfo loaderManagerImpl$LoaderInfo0 = this.mLoaderViewModel.getLoader(v);
        return loaderManagerImpl$LoaderInfo0 == null ? this.createAndInstallLoader(v, bundle0, loaderManager$LoaderCallbacks0, null) : this.createAndInstallLoader(v, bundle0, loaderManager$LoaderCallbacks0, loaderManagerImpl$LoaderInfo0.destroy(false));
    }

    @Override
    public String toString() {
        Class class0 = this.mLifecycleOwner.getClass();
        return "LoaderManager{" + Integer.toHexString(System.identityHashCode(this)) + " in " + class0.getSimpleName() + "{" + Integer.toHexString(System.identityHashCode(class0)) + "}}";
    }
}

