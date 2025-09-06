package androidx.lifecycle;

public final class ComputableLiveData..ExternalSyntheticLambda0 implements Runnable {
    public final ComputableLiveData f$0;

    public ComputableLiveData..ExternalSyntheticLambda0(ComputableLiveData computableLiveData0) {
        this.f$0 = computableLiveData0;
    }

    @Override
    public final void run() {
        ComputableLiveData.refreshRunnable$lambda$0(this.f$0);
    }
}

