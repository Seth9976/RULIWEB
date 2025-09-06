package androidx.core.content;

public final class PackageManagerCompat..ExternalSyntheticLambda0 implements Runnable {
    public final UnusedAppRestrictionsBackportServiceConnection f$0;

    public PackageManagerCompat..ExternalSyntheticLambda0(UnusedAppRestrictionsBackportServiceConnection unusedAppRestrictionsBackportServiceConnection0) {
        this.f$0 = unusedAppRestrictionsBackportServiceConnection0;
    }

    @Override
    public final void run() {
        this.f$0.disconnectFromService();
    }
}

