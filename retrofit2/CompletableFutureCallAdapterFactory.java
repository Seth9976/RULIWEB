package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

final class CompletableFutureCallAdapterFactory extends Factory {
    static final class BodyCallAdapter implements CallAdapter {
        class BodyCallback implements Callback {
            private final CompletableFuture future;

            public BodyCallback(CompletableFuture completableFuture0) {
                this.future = completableFuture0;
            }

            @Override  // retrofit2.Callback
            public void onFailure(Call call0, Throwable throwable0) {
                Platform..ExternalSyntheticApiModelOutline0.m(this.future, throwable0);
            }

            @Override  // retrofit2.Callback
            public void onResponse(Call call0, Response response0) {
                if(response0.isSuccessful()) {
                    this.future.complete(response0.body());
                    return;
                }
                HttpException httpException0 = new HttpException(response0);
                this.future.completeExceptionally(httpException0);
            }
        }

        private final Type responseType;

        BodyCallAdapter(Type type0) {
            this.responseType = type0;
        }

        @Override  // retrofit2.CallAdapter
        public Object adapt(Call call0) {
            return this.adapt(call0);
        }

        public CompletableFuture adapt(Call call0) {
            CompletableFuture completableFuture0 = new CallCancelCompletableFuture(call0);
            call0.enqueue(new BodyCallback(this, completableFuture0));
            return completableFuture0;
        }

        @Override  // retrofit2.CallAdapter
        public Type responseType() {
            return this.responseType;
        }
    }

    static final class CallCancelCompletableFuture extends CompletableFuture {
        private final Call call;

        CallCancelCompletableFuture(Call call0) {
            this.call = call0;
        }

        @Override
        public boolean cancel(boolean z) {
            if(z) {
                this.call.cancel();
            }
            return super.cancel(z);
        }
    }

    static final class ResponseCallAdapter implements CallAdapter {
        class ResponseCallback implements Callback {
            private final CompletableFuture future;

            public ResponseCallback(CompletableFuture completableFuture0) {
                this.future = completableFuture0;
            }

            @Override  // retrofit2.Callback
            public void onFailure(Call call0, Throwable throwable0) {
                Platform..ExternalSyntheticApiModelOutline0.m(this.future, throwable0);
            }

            @Override  // retrofit2.Callback
            public void onResponse(Call call0, Response response0) {
                this.future.complete(response0);
            }
        }

        private final Type responseType;

        ResponseCallAdapter(Type type0) {
            this.responseType = type0;
        }

        @Override  // retrofit2.CallAdapter
        public Object adapt(Call call0) {
            return this.adapt(call0);
        }

        public CompletableFuture adapt(Call call0) {
            CompletableFuture completableFuture0 = new CallCancelCompletableFuture(call0);
            call0.enqueue(new ResponseCallback(this, completableFuture0));
            return completableFuture0;
        }

        @Override  // retrofit2.CallAdapter
        public Type responseType() {
            return this.responseType;
        }
    }

    static final Factory INSTANCE;

    static {
        CompletableFutureCallAdapterFactory.INSTANCE = new CompletableFutureCallAdapterFactory();
    }

    @Override  // retrofit2.CallAdapter$Factory
    @Nullable
    public CallAdapter get(Type type0, Annotation[] arr_annotation, Retrofit retrofit0) {
        if(CompletableFutureCallAdapterFactory.getRawType(type0) != Platform..ExternalSyntheticApiModelOutline0.m()) {
            return null;
        }
        if(!(type0 instanceof ParameterizedType)) {
            throw new IllegalStateException("CompletableFuture return type must be parameterized as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
        }
        Type type1 = CompletableFutureCallAdapterFactory.getParameterUpperBound(0, ((ParameterizedType)type0));
        if(CompletableFutureCallAdapterFactory.getRawType(type1) != Response.class) {
            return new BodyCallAdapter(type1);
        }
        if(!(type1 instanceof ParameterizedType)) {
            throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
        }
        return new ResponseCallAdapter(CompletableFutureCallAdapterFactory.getParameterUpperBound(0, ((ParameterizedType)type1)));
    }
}

