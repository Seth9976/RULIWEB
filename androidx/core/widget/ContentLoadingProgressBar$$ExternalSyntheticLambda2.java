package androidx.core.widget;

public final class ContentLoadingProgressBar..ExternalSyntheticLambda2 implements Runnable {
    public final ContentLoadingProgressBar f$0;

    public ContentLoadingProgressBar..ExternalSyntheticLambda2(ContentLoadingProgressBar contentLoadingProgressBar0) {
        this.f$0 = contentLoadingProgressBar0;
    }

    @Override
    public final void run() {
        this.f$0.showOnUiThread();
    }
}

