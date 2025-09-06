package androidx.lifecycle;

public final class DispatchQueue..ExternalSyntheticLambda0 implements Runnable {
    public final DispatchQueue f$0;
    public final Runnable f$1;

    public DispatchQueue..ExternalSyntheticLambda0(DispatchQueue dispatchQueue0, Runnable runnable0) {
        this.f$0 = dispatchQueue0;
        this.f$1 = runnable0;
    }

    @Override
    public final void run() {
        DispatchQueue.dispatchAndEnqueue$lambda$2$lambda$1(this.f$0, this.f$1);
    }
}

