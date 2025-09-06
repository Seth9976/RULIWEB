package androidx.loader.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader {
    public final class ForceLoadContentObserver extends ContentObserver {
        public ForceLoadContentObserver() {
            super(new Handler());
        }

        @Override  // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override  // android.database.ContentObserver
        public void onChange(boolean z) {
            Loader.this.onContentChanged();
        }
    }

    public interface OnLoadCanceledListener {
        void onLoadCanceled(Loader arg1);
    }

    public interface OnLoadCompleteListener {
        void onLoadComplete(Loader arg1, Object arg2);
    }

    private boolean mAbandoned;
    private boolean mContentChanged;
    private Context mContext;
    private int mId;
    private OnLoadCompleteListener mListener;
    private OnLoadCanceledListener mOnLoadCanceledListener;
    private boolean mProcessingChange;
    private boolean mReset;
    private boolean mStarted;

    public Loader(Context context0) {
        this.mStarted = false;
        this.mAbandoned = false;
        this.mReset = true;
        this.mContentChanged = false;
        this.mProcessingChange = false;
        this.mContext = context0.getApplicationContext();
    }

    public void abandon() {
        this.mAbandoned = true;
    }

    public boolean cancelLoad() {
        return this.onCancelLoad();
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    public String dataToString(Object object0) {
        StringBuilder stringBuilder0 = new StringBuilder(0x40);
        if(object0 == null) {
            stringBuilder0.append("null");
            return stringBuilder0.toString();
        }
        Class class0 = object0.getClass();
        stringBuilder0.append(class0.getSimpleName());
        stringBuilder0.append("{");
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(class0)));
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    public void deliverCancellation() {
        OnLoadCanceledListener loader$OnLoadCanceledListener0 = this.mOnLoadCanceledListener;
        if(loader$OnLoadCanceledListener0 != null) {
            loader$OnLoadCanceledListener0.onLoadCanceled(this);
        }
    }

    public void deliverResult(Object object0) {
        OnLoadCompleteListener loader$OnLoadCompleteListener0 = this.mListener;
        if(loader$OnLoadCompleteListener0 != null) {
            loader$OnLoadCompleteListener0.onLoadComplete(this, object0);
        }
    }

    @Deprecated
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        printWriter0.print(s);
        printWriter0.print("mId=");
        printWriter0.print(this.mId);
        printWriter0.print(" mListener=");
        printWriter0.println(this.mListener);
        if(this.mStarted || this.mContentChanged || this.mProcessingChange) {
            printWriter0.print(s);
            printWriter0.print("mStarted=");
            printWriter0.print(this.mStarted);
            printWriter0.print(" mContentChanged=");
            printWriter0.print(this.mContentChanged);
            printWriter0.print(" mProcessingChange=");
            printWriter0.println(this.mProcessingChange);
        }
        if(!this.mAbandoned && !this.mReset) {
            return;
        }
        printWriter0.print(s);
        printWriter0.print("mAbandoned=");
        printWriter0.print(this.mAbandoned);
        printWriter0.print(" mReset=");
        printWriter0.println(this.mReset);
    }

    public void forceLoad() {
        this.onForceLoad();
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getId() {
        return this.mId;
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isReset() {
        return this.mReset;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    protected void onAbandon() {
    }

    protected boolean onCancelLoad() {
        return false;
    }

    public void onContentChanged() {
        if(this.mStarted) {
            this.forceLoad();
            return;
        }
        this.mContentChanged = true;
    }

    protected void onForceLoad() {
    }

    protected void onReset() {
    }

    protected void onStartLoading() {
    }

    protected void onStopLoading() {
    }

    public void registerListener(int v, OnLoadCompleteListener loader$OnLoadCompleteListener0) {
        if(this.mListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mListener = loader$OnLoadCompleteListener0;
        this.mId = v;
    }

    public void registerOnLoadCanceledListener(OnLoadCanceledListener loader$OnLoadCanceledListener0) {
        if(this.mOnLoadCanceledListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        this.mOnLoadCanceledListener = loader$OnLoadCanceledListener0;
    }

    public void reset() {
        this.onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if(this.mProcessingChange) {
            this.onContentChanged();
        }
    }

    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        this.onStartLoading();
    }

    public void stopLoading() {
        this.mStarted = false;
        this.onStopLoading();
    }

    public boolean takeContentChanged() {
        boolean z = this.mContentChanged;
        this.mContentChanged = false;
        this.mProcessingChange |= z;
        return z;
    }

    @Override
    public String toString() {
        Class class0 = this.getClass();
        return class0.getSimpleName() + "{" + Integer.toHexString(System.identityHashCode(class0)) + " id=" + this.mId + "}";
    }

    public void unregisterListener(OnLoadCompleteListener loader$OnLoadCompleteListener0) {
        OnLoadCompleteListener loader$OnLoadCompleteListener1 = this.mListener;
        if(loader$OnLoadCompleteListener1 == null) {
            throw new IllegalStateException("No listener register");
        }
        if(loader$OnLoadCompleteListener1 != loader$OnLoadCompleteListener0) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mListener = null;
    }

    public void unregisterOnLoadCanceledListener(OnLoadCanceledListener loader$OnLoadCanceledListener0) {
        OnLoadCanceledListener loader$OnLoadCanceledListener1 = this.mOnLoadCanceledListener;
        if(loader$OnLoadCanceledListener1 == null) {
            throw new IllegalStateException("No listener register");
        }
        if(loader$OnLoadCanceledListener1 != loader$OnLoadCanceledListener0) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        this.mOnLoadCanceledListener = null;
    }
}

