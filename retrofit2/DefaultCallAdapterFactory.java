package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Request;
import okio.Timeout;

final class DefaultCallAdapterFactory extends Factory {
    static final class ExecutorCallbackCall implements Call {
        final Executor callbackExecutor;
        final Call delegate;

        ExecutorCallbackCall(Executor executor0, Call call0) {
            this.callbackExecutor = executor0;
            this.delegate = call0;
        }

        @Override  // retrofit2.Call
        public void cancel() {
            this.delegate.cancel();
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        @Override  // retrofit2.Call
        public Call clone() {
            Call call0 = this.delegate.clone();
            return new ExecutorCallbackCall(this.callbackExecutor, call0);
        }

        @Override  // retrofit2.Call
        public void enqueue(Callback callback0) {
            Objects.requireNonNull(callback0, "callback == null");
            retrofit2.DefaultCallAdapterFactory.ExecutorCallbackCall.1 defaultCallAdapterFactory$ExecutorCallbackCall$10 = new Callback() {
                // 检测为 Lambda 实现
                void lambda$onFailure$1$retrofit2-DefaultCallAdapterFactory$ExecutorCallbackCall$1(Callback callback0, Throwable throwable0) [...]

                // 检测为 Lambda 实现
                void lambda$onResponse$0$retrofit2-DefaultCallAdapterFactory$ExecutorCallbackCall$1(Callback callback0, Response response0) [...]

                @Override  // retrofit2.Callback
                public void onFailure(Call call0, Throwable throwable0) {
                    DefaultCallAdapterFactory.ExecutorCallbackCall.1..ExternalSyntheticLambda1 defaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda10 = () -> callback0.onFailure(ExecutorCallbackCall.this, throwable0);
                    ExecutorCallbackCall.this.callbackExecutor.execute(defaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda10);
                }

                @Override  // retrofit2.Callback
                public void onResponse(Call call0, Response response0) {
                    DefaultCallAdapterFactory.ExecutorCallbackCall.1..ExternalSyntheticLambda0 defaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda00 = () -> {
                        if(ExecutorCallbackCall.this.delegate.isCanceled()) {
                            IOException iOException0 = new IOException("Canceled");
                            callback0.onFailure(ExecutorCallbackCall.this, iOException0);
                            return;
                        }
                        callback0.onResponse(ExecutorCallbackCall.this, response0);
                    };
                    ExecutorCallbackCall.this.callbackExecutor.execute(defaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda00);
                }
            };
            this.delegate.enqueue(defaultCallAdapterFactory$ExecutorCallbackCall$10);
        }

        @Override  // retrofit2.Call
        public Response execute() throws IOException {
            return this.delegate.execute();
        }

        @Override  // retrofit2.Call
        public boolean isCanceled() {
            return this.delegate.isCanceled();
        }

        @Override  // retrofit2.Call
        public boolean isExecuted() {
            return this.delegate.isExecuted();
        }

        @Override  // retrofit2.Call
        public Request request() {
            return this.delegate.request();
        }

        @Override  // retrofit2.Call
        public Timeout timeout() {
            return this.delegate.timeout();
        }
    }

    @Nullable
    private final Executor callbackExecutor;

    DefaultCallAdapterFactory(@Nullable Executor executor0) {
        this.callbackExecutor = executor0;
    }

    @Override  // retrofit2.CallAdapter$Factory
    @Nullable
    public CallAdapter get(Type type0, Annotation[] arr_annotation, Retrofit retrofit0) {
        Executor executor0 = null;
        if(DefaultCallAdapterFactory.getRawType(type0) != Call.class) {
            return null;
        }
        if(!(type0 instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
        }
        Type type1 = Utils.getParameterUpperBound(0, ((ParameterizedType)type0));
        if(!Utils.isAnnotationPresent(arr_annotation, SkipCallbackExecutor.class)) {
            executor0 = this.callbackExecutor;
        }
        return new CallAdapter() {
            @Override  // retrofit2.CallAdapter
            public Object adapt(Call call0) {
                return this.adapt(call0);
            }

            public Call adapt(Call call0) {
                return executor0 == null ? call0 : new ExecutorCallbackCall(executor0, call0);
            }

            @Override  // retrofit2.CallAdapter
            public Type responseType() {
                return type1;
            }
        };
    }
}

