package androidx.lifecycle;

public final class ComputableLiveData..ExternalSyntheticLambda1 implements Runnable {
    public final ComputableLiveData f$0;

    public ComputableLiveData..ExternalSyntheticLambda1(ComputableLiveData computableLiveData0) {
        this.f$0 = computableLiveData0;
    }

    @Override
    public final void run() {
        ComputableLiveData.invalidationRunnable$lambda$1(this.f$0);
    }
}

