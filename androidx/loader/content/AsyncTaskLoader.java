package androidx.loader.content;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateUtils;
import androidx.core.os.OperationCanceledException;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public abstract class AsyncTaskLoader extends Loader {
    final class LoadTask extends ModernAsyncTask implements Runnable {
        boolean waiting;

        @Override  // androidx.loader.content.ModernAsyncTask
        protected Object doInBackground() {
            try {
                return AsyncTaskLoader.this.onLoadInBackground();
            }
            catch(OperationCanceledException operationCanceledException0) {
                if(!this.isCancelled()) {
                    throw operationCanceledException0;
                }
                return null;
            }
        }

        @Override  // androidx.loader.content.ModernAsyncTask
        protected void onCancelled(Object object0) {
            AsyncTaskLoader.this.dispatchOnCancelled(this, object0);
        }

        @Override  // androidx.loader.content.ModernAsyncTask
        protected void onPostExecute(Object object0) {
            AsyncTaskLoader.this.dispatchOnLoadComplete(this, object0);
        }

        @Override
        public void run() {
            this.waiting = false;
            AsyncTaskLoader.this.executePendingTask();
        }
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "AsyncTaskLoader";
    private volatile LoadTask mCancellingTask;
    private Executor mExecutor;
    private Handler mHandler;
    private long mLastLoadCompleteTime;
    private volatile LoadTask mTask;
    private long mUpdateThrottle;

    public AsyncTaskLoader(Context context0) {
        super(context0);
        this.mLastLoadCompleteTime = -10000L;
    }

    public void cancelLoadInBackground() {
    }

    void dispatchOnCancelled(LoadTask asyncTaskLoader$LoadTask0, Object object0) {
        this.onCanceled(object0);
        if(this.mCancellingTask == asyncTaskLoader$LoadTask0) {
            this.rollbackContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            this.deliverCancellation();
            this.executePendingTask();
        }
    }

    void dispatchOnLoadComplete(LoadTask asyncTaskLoader$LoadTask0, Object object0) {
        if(this.mTask != asyncTaskLoader$LoadTask0) {
            this.dispatchOnCancelled(asyncTaskLoader$LoadTask0, object0);
            return;
        }
        if(this.isAbandoned()) {
            this.onCanceled(object0);
            return;
        }
        this.commitContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mTask = null;
        this.deliverResult(object0);
    }

    @Override  // androidx.loader.content.Loader
    @Deprecated
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        super.dump(s, fileDescriptor0, printWriter0, arr_s);
        if(this.mTask != null) {
            printWriter0.print(s);
            printWriter0.print("mTask=");
            printWriter0.print(this.mTask);
            printWriter0.print(" waiting=");
            printWriter0.println(this.mTask.waiting);
        }
        if(this.mCancellingTask != null) {
            printWriter0.print(s);
            printWriter0.print("mCancellingTask=");
            printWriter0.print(this.mCancellingTask);
            printWriter0.print(" waiting=");
            printWriter0.println(this.mCancellingTask.waiting);
        }
        if(this.mUpdateThrottle != 0L) {
            printWriter0.print(s);
            printWriter0.print("mUpdateThrottle=");
            printWriter0.print(DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(this.mUpdateThrottle)));
            printWriter0.print(" mLastLoadCompleteTime=");
            printWriter0.print((this.mLastLoadCompleteTime == -10000L ? "--" : "-" + DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(SystemClock.uptimeMillis() - this.mLastLoadCompleteTime))));
            printWriter0.println();
        }
    }

    void executePendingTask() {
        if(this.mCancellingTask == null && this.mTask != null) {
            if(this.mTask.waiting) {
                this.mTask.waiting = false;
                this.mHandler.removeCallbacks(this.mTask);
            }
            if(this.mUpdateThrottle > 0L && SystemClock.uptimeMillis() < this.mLastLoadCompleteTime + this.mUpdateThrottle) {
                this.mTask.waiting = true;
                this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
                return;
            }
            if(this.mExecutor == null) {
                this.mExecutor = this.getExecutor();
            }
            this.mTask.executeOnExecutor(this.mExecutor);
        }
    }

    protected Executor getExecutor() {
        return AsyncTask.THREAD_POOL_EXECUTOR;
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.mCancellingTask != null;
    }

    public abstract Object loadInBackground();

    @Override  // androidx.loader.content.Loader
    protected boolean onCancelLoad() {
        if(this.mTask != null) {
            if(!this.isStarted()) {
                this.onContentChanged();
            }
            if(this.mCancellingTask != null) {
                if(this.mTask.waiting) {
                    this.mTask.waiting = false;
                    this.mHandler.removeCallbacks(this.mTask);
                }
                this.mTask = null;
                return false;
            }
            if(this.mTask.waiting) {
                this.mTask.waiting = false;
                this.mHandler.removeCallbacks(this.mTask);
                this.mTask = null;
                return false;
            }
            boolean z = this.mTask.cancel(false);
            if(z) {
                this.mCancellingTask = this.mTask;
                this.cancelLoadInBackground();
            }
            this.mTask = null;
            return z;
        }
        return false;
    }

    public void onCanceled(Object object0) {
    }

    @Override  // androidx.loader.content.Loader
    protected void onForceLoad() {
        super.onForceLoad();
        this.cancelLoad();
        this.mTask = new LoadTask(this);
        this.executePendingTask();
    }

    protected Object onLoadInBackground() {
        return this.loadInBackground();
    }

    public void setUpdateThrottle(long v) {
        this.mUpdateThrottle = v;
        if(v != 0L) {
            this.mHandler = new Handler();
        }
    }
}

