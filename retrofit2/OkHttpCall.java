package retrofit2;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nullable;
import jeb.synthetic.TWR;
import okhttp3.Call.Factory;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Timeout;

final class OkHttpCall implements Call {
    static final class ExceptionCatchingResponseBody extends ResponseBody {
        private final ResponseBody delegate;
        private final BufferedSource delegateSource;
        @Nullable
        IOException thrownException;

        ExceptionCatchingResponseBody(ResponseBody responseBody0) {
            this.delegate = responseBody0;
            this.delegateSource = Okio.buffer(new ForwardingSource(responseBody0.source()) {
                @Override  // okio.ForwardingSource
                public long read(Buffer buffer0, long v) throws IOException {
                    try {
                        return super.read(buffer0, v);
                    }
                    catch(IOException iOException0) {
                        ExceptionCatchingResponseBody.this.thrownException = iOException0;
                        throw iOException0;
                    }
                }
            });
        }

        @Override  // okhttp3.ResponseBody
        public void close() {
            this.delegate.close();
        }

        @Override  // okhttp3.ResponseBody
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override  // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.delegate.contentType();
        }

        @Override  // okhttp3.ResponseBody
        public BufferedSource source() {
            return this.delegateSource;
        }

        void throwIfCaught() throws IOException {
            IOException iOException0 = this.thrownException;
            if(iOException0 != null) {
                throw iOException0;
            }
        }
    }

    static final class NoContentResponseBody extends ResponseBody {
        private final long contentLength;
        @Nullable
        private final MediaType contentType;

        NoContentResponseBody(@Nullable MediaType mediaType0, long v) {
            this.contentType = mediaType0;
            this.contentLength = v;
        }

        @Override  // okhttp3.ResponseBody
        public long contentLength() {
            return this.contentLength;
        }

        @Override  // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override  // okhttp3.ResponseBody
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    private final Object[] args;
    private final Factory callFactory;
    private volatile boolean canceled;
    @Nullable
    private Throwable creationFailure;
    private boolean executed;
    @Nullable
    private okhttp3.Call rawCall;
    private final RequestFactory requestFactory;
    private final Converter responseConverter;

    OkHttpCall(RequestFactory requestFactory0, Object[] arr_object, Factory call$Factory0, Converter converter0) {
        this.requestFactory = requestFactory0;
        this.args = arr_object;
        this.callFactory = call$Factory0;
        this.responseConverter = converter0;
    }

    @Override  // retrofit2.Call
    public void cancel() {
        okhttp3.Call call0;
        this.canceled = true;
        synchronized(this) {
            call0 = this.rawCall;
        }
        if(call0 != null) {
            call0.cancel();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // retrofit2.Call
    public Call clone() {
        return this.clone();
    }

    public OkHttpCall clone() {
        return new OkHttpCall(this.requestFactory, this.args, this.callFactory, this.responseConverter);
    }

    private okhttp3.Call createRawCall() throws IOException {
        Request request0 = this.requestFactory.create(this.args);
        okhttp3.Call call0 = this.callFactory.newCall(request0);
        if(call0 == null) {
            throw new NullPointerException("Call.Factory returned null.");
        }
        return call0;
    }

    @Override  // retrofit2.Call
    public void enqueue(Callback callback0) {
        Objects.requireNonNull(callback0, "callback == null");
        synchronized(this) {
            if(!this.executed) {
                this.executed = true;
                okhttp3.Call call0 = this.rawCall;
                Throwable throwable0 = this.creationFailure;
                if(call0 == null && throwable0 == null) {
                    try {
                        okhttp3.Call call1 = this.createRawCall();
                        this.rawCall = call1;
                        call0 = call1;
                    }
                    catch(Throwable throwable0) {
                        Utils.throwIfFatal(throwable0);
                        this.creationFailure = throwable0;
                    }
                }
                if(throwable0 != null) {
                    callback0.onFailure(this, throwable0);
                    return;
                }
                if(this.canceled) {
                    call0.cancel();
                }
                call0.enqueue(new okhttp3.Callback() {
                    private void callFailure(Throwable throwable0) {
                        try {
                            callback0.onFailure(OkHttpCall.this, throwable0);
                        }
                        catch(Throwable throwable1) {
                            Utils.throwIfFatal(throwable1);
                            throwable1.printStackTrace();
                        }
                    }

                    @Override  // okhttp3.Callback
                    public void onFailure(okhttp3.Call call0, IOException iOException0) {
                        this.callFailure(iOException0);
                    }

                    @Override  // okhttp3.Callback
                    public void onResponse(okhttp3.Call call0, Response response0) {
                        retrofit2.Response response1;
                        try {
                            response1 = OkHttpCall.this.parseResponse(response0);
                        }
                        catch(Throwable throwable0) {
                            Utils.throwIfFatal(throwable0);
                            this.callFailure(throwable0);
                            return;
                        }
                        try {
                            callback0.onResponse(OkHttpCall.this, response1);
                        }
                        catch(Throwable throwable1) {
                            Utils.throwIfFatal(throwable1);
                            throwable1.printStackTrace();
                        }
                    }
                });
                return;
            }
        }
        throw new IllegalStateException("Already executed.");
    }

    @Override  // retrofit2.Call
    public retrofit2.Response execute() throws IOException {
        synchronized(this) {
            if(!this.executed) {
                this.executed = true;
                okhttp3.Call call0 = this.getRawCall();
                if(this.canceled) {
                    call0.cancel();
                }
                return this.parseResponse(call0.execute());
            }
        }
        throw new IllegalStateException("Already executed.");
    }

    private okhttp3.Call getRawCall() throws IOException {
        okhttp3.Call call0 = this.rawCall;
        if(call0 != null) {
            return call0;
        }
        Throwable throwable0 = this.creationFailure;
        if(throwable0 != null) {
            if(throwable0 instanceof IOException) {
                throw (IOException)throwable0;
            }
            if(throwable0 instanceof RuntimeException) {
                throw (RuntimeException)throwable0;
            }
            throw (Error)throwable0;
        }
        try {
            okhttp3.Call call1 = this.createRawCall();
            this.rawCall = call1;
            return call1;
        }
        catch(RuntimeException | Error | IOException runtimeException0) {
            Utils.throwIfFatal(runtimeException0);
            this.creationFailure = runtimeException0;
            throw runtimeException0;
        }
    }

    @Override  // retrofit2.Call
    public boolean isCanceled() {
        boolean z = true;
        if(this.canceled) {
            return true;
        }
        synchronized(this) {
            if(this.rawCall == null || !this.rawCall.isCanceled()) {
                z = false;
            }
            return z;
        }
    }

    @Override  // retrofit2.Call
    public boolean isExecuted() {
        synchronized(this) {
        }
        return this.executed;
    }

    retrofit2.Response parseResponse(Response response0) throws IOException {
        retrofit2.Response response2;
        ResponseBody responseBody0 = response0.body();
        Response response1 = response0.newBuilder().body(new NoContentResponseBody(responseBody0.contentType(), responseBody0.contentLength())).build();
        int v = response1.code();
        if(v >= 200 && v < 300) {
            if(v != 204 && v != 205) {
                ExceptionCatchingResponseBody okHttpCall$ExceptionCatchingResponseBody0 = new ExceptionCatchingResponseBody(responseBody0);
                try {
                    return retrofit2.Response.success(this.responseConverter.convert(okHttpCall$ExceptionCatchingResponseBody0), response1);
                }
                catch(RuntimeException runtimeException0) {
                    okHttpCall$ExceptionCatchingResponseBody0.throwIfCaught();
                    throw runtimeException0;
                }
            }
            responseBody0.close();
            return retrofit2.Response.success(null, response1);
        }
        try {
            response2 = retrofit2.Response.error(Utils.buffer(responseBody0), response1);
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(responseBody0, throwable0);
            throw throwable0;
        }
        responseBody0.close();
        return response2;
    }

    @Override  // retrofit2.Call
    public Request request() {
        synchronized(this) {
            try {
                return this.getRawCall().request();
            }
            catch(IOException iOException0) {
                throw new RuntimeException("Unable to create request.", iOException0);
            }
        }
    }

    @Override  // retrofit2.Call
    public Timeout timeout() {
        synchronized(this) {
            try {
                return this.getRawCall().timeout();
            }
            catch(IOException iOException0) {
                throw new RuntimeException("Unable to create call.", iOException0);
            }
        }
    }
}

