package retrofit2;

public final class DefaultCallAdapterFactory.ExecutorCallbackCall.1..ExternalSyntheticLambda1 implements Runnable {
    public final retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1 f$0;
    public final Callback f$1;
    public final Throwable f$2;

    public DefaultCallAdapterFactory.ExecutorCallbackCall.1..ExternalSyntheticLambda1(retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1 defaultCallAdapterFactory$ExecutorCallbackCall$10, Callback callback0, Throwable throwable0) {
        this.f$0 = defaultCallAdapterFactory$ExecutorCallbackCall$10;
        this.f$1 = callback0;
        this.f$2 = throwable0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onFailure$1$retrofit2-DefaultCallAdapterFactory$ExecutorCallbackCall$1(this.f$1, this.f$2);
    }
}

