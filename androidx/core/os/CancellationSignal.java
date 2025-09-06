package androidx.core.os;

@Deprecated
public final class CancellationSignal {
    public interface OnCancelListener {
        void onCancel();
    }

    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;

    public void cancel() {
        Object object0;
        OnCancelListener cancellationSignal$OnCancelListener0;
        synchronized(this) {
            if(this.mIsCanceled) {
                return;
            }
            this.mIsCanceled = true;
            this.mCancelInProgress = true;
            cancellationSignal$OnCancelListener0 = this.mOnCancelListener;
            object0 = this.mCancellationSignalObj;
        }
        try {
            if(cancellationSignal$OnCancelListener0 != null) {
                cancellationSignal$OnCancelListener0.onCancel();
            }
            if(object0 != null) {
                ((android.os.CancellationSignal)object0).cancel();
            }
            goto label_24;
        }
        catch(Throwable throwable0) {
        }
        synchronized(this) {
            this.mCancelInProgress = false;
            this.notifyAll();
        }
        throw throwable0;
    label_24:
        synchronized(this) {
            this.mCancelInProgress = false;
            this.notifyAll();
        }
    }

    public Object getCancellationSignalObject() {
        synchronized(this) {
            if(this.mCancellationSignalObj == null) {
                android.os.CancellationSignal cancellationSignal0 = new android.os.CancellationSignal();
                this.mCancellationSignalObj = cancellationSignal0;
                if(this.mIsCanceled) {
                    cancellationSignal0.cancel();
                }
            }
            return this.mCancellationSignalObj;
        }
    }

    public boolean isCanceled() {
        synchronized(this) {
        }
        return this.mIsCanceled;
    }

    public void setOnCancelListener(OnCancelListener cancellationSignal$OnCancelListener0) {
        synchronized(this) {
            this.waitForCancelFinishedLocked();
            if(this.mOnCancelListener == cancellationSignal$OnCancelListener0) {
                return;
            }
            this.mOnCancelListener = cancellationSignal$OnCancelListener0;
            if(this.mIsCanceled && cancellationSignal$OnCancelListener0 != null) {
                cancellationSignal$OnCancelListener0.onCancel();
            }
        }
    }

    public void throwIfCanceled() {
        if(this.isCanceled()) {
            throw new OperationCanceledException();
        }
    }

    private void waitForCancelFinishedLocked() {
        while(this.mCancelInProgress) {
            try {
                this.wait();
            }
            catch(InterruptedException unused_ex) {
            }
        }
    }
}

