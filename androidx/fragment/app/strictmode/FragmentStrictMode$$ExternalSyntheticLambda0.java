package androidx.fragment.app.strictmode;

public final class FragmentStrictMode..ExternalSyntheticLambda0 implements Runnable {
    public final Policy f$0;
    public final Violation f$1;

    public FragmentStrictMode..ExternalSyntheticLambda0(Policy fragmentStrictMode$Policy0, Violation violation0) {
        this.f$0 = fragmentStrictMode$Policy0;
        this.f$1 = violation0;
    }

    @Override
    public final void run() {
        FragmentStrictMode.handlePolicyViolation$lambda-0(this.f$0, this.f$1);
    }
}

