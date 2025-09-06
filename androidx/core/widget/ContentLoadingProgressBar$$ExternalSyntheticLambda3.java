package androidx.core.widget;

public final class ContentLoadingProgressBar..ExternalSyntheticLambda3 implements Runnable {
    public final ContentLoadingProgressBar f$0;

    public ContentLoadingProgressBar..ExternalSyntheticLambda3(ContentLoadingProgressBar contentLoadingProgressBar0) {
        this.f$0 = contentLoadingProgressBar0;
    }

    @Override
    public final void run() {
        this.f$0.hideOnUiThread();
    }
}

