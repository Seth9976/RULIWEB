package androidx.fragment.app.strictmode;

public final class FragmentStrictMode..ExternalSyntheticLambda1 implements Runnable {
    public final String f$0;
    public final Violation f$1;

    public FragmentStrictMode..ExternalSyntheticLambda1(String s, Violation violation0) {
        this.f$0 = s;
        this.f$1 = violation0;
    }

    @Override
    public final void run() {
        FragmentStrictMode.handlePolicyViolation$lambda-1(this.f$0, this.f$1);
    }
}

