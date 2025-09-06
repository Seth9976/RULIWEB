package androidx.core.provider;

import android.graphics.Typeface;
import java.util.concurrent.Executor;

class CallbackWrapper {
    private final FontRequestCallback mCallback;
    private final Executor mExecutor;

    CallbackWrapper(FontRequestCallback fontsContractCompat$FontRequestCallback0) {
        this(fontsContractCompat$FontRequestCallback0, RequestExecutor.createHandlerExecutor(CalleeHandler.create()));
    }

    CallbackWrapper(FontRequestCallback fontsContractCompat$FontRequestCallback0, Executor executor0) {
        this.mCallback = fontsContractCompat$FontRequestCallback0;
        this.mExecutor = executor0;
    }

    private void onTypefaceRequestFailed(int v) {
        androidx.core.provider.CallbackWrapper.2 callbackWrapper$20 = new Runnable() {
            @Override
            public void run() {
                this.mCallback.onTypefaceRequestFailed(v);
            }
        };
        this.mExecutor.execute(callbackWrapper$20);
    }

    void onTypefaceResult(TypefaceResult fontRequestWorker$TypefaceResult0) {
        if(fontRequestWorker$TypefaceResult0.isSuccess()) {
            this.onTypefaceRetrieved(fontRequestWorker$TypefaceResult0.mTypeface);
            return;
        }
        this.onTypefaceRequestFailed(fontRequestWorker$TypefaceResult0.mResult);
    }

    private void onTypefaceRetrieved(Typeface typeface0) {
        androidx.core.provider.CallbackWrapper.1 callbackWrapper$10 = new Runnable() {
            @Override
            public void run() {
                this.mCallback.onTypefaceRetrieved(typeface0);
            }
        };
        this.mExecutor.execute(callbackWrapper$10);
    }
}

