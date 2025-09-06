package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import kotlin.coroutines.Continuation;
import okhttp3.Call.Factory;

abstract class HttpServiceMethod extends ServiceMethod {
    static final class CallAdapted extends HttpServiceMethod {
        private final CallAdapter callAdapter;

        CallAdapted(RequestFactory requestFactory0, Factory call$Factory0, Converter converter0, CallAdapter callAdapter0) {
            super(requestFactory0, call$Factory0, converter0);
            this.callAdapter = callAdapter0;
        }

        @Override  // retrofit2.HttpServiceMethod
        protected Object adapt(Call call0, Object[] arr_object) {
            return this.callAdapter.adapt(call0);
        }
    }

    static final class SuspendForBody extends HttpServiceMethod {
        private final CallAdapter callAdapter;
        private final boolean isNullable;

        SuspendForBody(RequestFactory requestFactory0, Factory call$Factory0, Converter converter0, CallAdapter callAdapter0, boolean z) {
            super(requestFactory0, call$Factory0, converter0);
            this.callAdapter = callAdapter0;
            this.isNullable = z;
        }

        @Override  // retrofit2.HttpServiceMethod
        protected Object adapt(Call call0, Object[] arr_object) {
            Call call1 = (Call)this.callAdapter.adapt(call0);
            Continuation continuation0 = (Continuation)arr_object[arr_object.length - 1];
            try {
                return this.isNullable ? KotlinExtensions.awaitNullable(call1, continuation0) : KotlinExtensions.await(call1, continuation0);
            }
            catch(Exception exception0) {
                return KotlinExtensions.suspendAndThrow(exception0, continuation0);
            }
        }
    }

    static final class SuspendForResponse extends HttpServiceMethod {
        private final CallAdapter callAdapter;

        SuspendForResponse(RequestFactory requestFactory0, Factory call$Factory0, Converter converter0, CallAdapter callAdapter0) {
            super(requestFactory0, call$Factory0, converter0);
            this.callAdapter = callAdapter0;
        }

        @Override  // retrofit2.HttpServiceMethod
        protected Object adapt(Call call0, Object[] arr_object) {
            Call call1 = (Call)this.callAdapter.adapt(call0);
            Continuation continuation0 = (Continuation)arr_object[arr_object.length - 1];
            try {
                return KotlinExtensions.awaitResponse(call1, continuation0);
            }
            catch(Exception exception0) {
                return KotlinExtensions.suspendAndThrow(exception0, continuation0);
            }
        }
    }

    private final Factory callFactory;
    private final RequestFactory requestFactory;
    private final Converter responseConverter;

    HttpServiceMethod(RequestFactory requestFactory0, Factory call$Factory0, Converter converter0) {
        this.requestFactory = requestFactory0;
        this.callFactory = call$Factory0;
        this.responseConverter = converter0;
    }

    @Nullable
    protected abstract Object adapt(Call arg1, Object[] arg2);

    private static CallAdapter createCallAdapter(Retrofit retrofit0, Method method0, Type type0, Annotation[] arr_annotation) {
        try {
            return retrofit0.callAdapter(type0, arr_annotation);
        }
        catch(RuntimeException runtimeException0) {
            throw Utils.methodError(method0, runtimeException0, "Unable to create call adapter for %s", new Object[]{type0});
        }
    }

    private static Converter createResponseConverter(Retrofit retrofit0, Method method0, Type type0) {
        Annotation[] arr_annotation = method0.getAnnotations();
        try {
            return retrofit0.responseBodyConverter(type0, arr_annotation);
        }
        catch(RuntimeException runtimeException0) {
            throw Utils.methodError(method0, runtimeException0, "Unable to create converter for %s", new Object[]{type0});
        }
    }

    @Override  // retrofit2.ServiceMethod
    @Nullable
    final Object invoke(Object[] arr_object) {
        return this.adapt(new OkHttpCall(this.requestFactory, arr_object, this.callFactory, this.responseConverter), arr_object);
    }

    static HttpServiceMethod parseAnnotations(Retrofit retrofit0, Method method0, RequestFactory requestFactory0) {
        Type type1;
        boolean z1;
        boolean z = requestFactory0.isKotlinSuspendFunction;
        Annotation[] arr_annotation = method0.getAnnotations();
        if(z) {
            Type[] arr_type = method0.getGenericParameterTypes();
            Type type0 = Utils.getParameterLowerBound(0, ((ParameterizedType)arr_type[arr_type.length - 1]));
            if(Utils.getRawType(type0) != Response.class || !(type0 instanceof ParameterizedType)) {
                z1 = false;
            }
            else {
                type0 = Utils.getParameterUpperBound(0, ((ParameterizedType)type0));
                z1 = true;
            }
            type1 = new ParameterizedTypeImpl(null, Call.class, new Type[]{type0});
            arr_annotation = SkipCallbackExecutorImpl.ensurePresent(arr_annotation);
        }
        else {
            type1 = method0.getGenericReturnType();
            z1 = false;
        }
        CallAdapter callAdapter0 = HttpServiceMethod.createCallAdapter(retrofit0, method0, type1, arr_annotation);
        Type type2 = callAdapter0.responseType();
        if(type2 == okhttp3.Response.class) {
            throw Utils.methodError(method0, "\'" + Utils.getRawType(type2).getName() + "\' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
        }
        if(type2 == Response.class) {
            throw Utils.methodError(method0, "Response must include generic type (e.g., Response<String>)", new Object[0]);
        }
        if(requestFactory0.httpMethod.equals("HEAD") && !Void.class.equals(type2)) {
            throw Utils.methodError(method0, "HEAD method must use Void as response type.", new Object[0]);
        }
        Converter converter0 = HttpServiceMethod.createResponseConverter(retrofit0, method0, type2);
        Factory call$Factory0 = retrofit0.callFactory;
        if(!z) {
            return new CallAdapted(requestFactory0, call$Factory0, converter0, callAdapter0);
        }
        return z1 ? new SuspendForResponse(requestFactory0, call$Factory0, converter0, callAdapter0) : new SuspendForBody(requestFactory0, call$Factory0, converter0, callAdapter0, false);
    }
}

