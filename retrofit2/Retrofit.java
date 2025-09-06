package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Call.Factory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public final class Retrofit {
    public static final class Builder {
        @Nullable
        private HttpUrl baseUrl;
        private final List callAdapterFactories;
        @Nullable
        private Factory callFactory;
        @Nullable
        private Executor callbackExecutor;
        private final List converterFactories;
        private final Platform platform;
        private boolean validateEagerly;

        public Builder() {
            this(Platform.get());
        }

        Builder(Platform platform0) {
            this.converterFactories = new ArrayList();
            this.callAdapterFactories = new ArrayList();
            this.platform = platform0;
        }

        Builder(Retrofit retrofit0) {
            this.converterFactories = new ArrayList();
            this.callAdapterFactories = new ArrayList();
            Platform platform0 = Platform.get();
            this.platform = platform0;
            this.callFactory = retrofit0.callFactory;
            this.baseUrl = retrofit0.baseUrl;
            int v = retrofit0.converterFactories.size();
            int v1 = platform0.defaultConverterFactoriesSize();
            for(int v2 = 1; v2 < v - v1; ++v2) {
                retrofit2.Converter.Factory converter$Factory0 = (retrofit2.Converter.Factory)retrofit0.converterFactories.get(v2);
                this.converterFactories.add(converter$Factory0);
            }
            int v3 = retrofit0.callAdapterFactories.size();
            int v4 = this.platform.defaultCallAdapterFactoriesSize();
            for(int v5 = 0; v5 < v3 - v4; ++v5) {
                retrofit2.CallAdapter.Factory callAdapter$Factory0 = (retrofit2.CallAdapter.Factory)retrofit0.callAdapterFactories.get(v5);
                this.callAdapterFactories.add(callAdapter$Factory0);
            }
            this.callbackExecutor = retrofit0.callbackExecutor;
            this.validateEagerly = retrofit0.validateEagerly;
        }

        public Builder addCallAdapterFactory(retrofit2.CallAdapter.Factory callAdapter$Factory0) {
            retrofit2.CallAdapter.Factory callAdapter$Factory1 = (retrofit2.CallAdapter.Factory)Objects.requireNonNull(callAdapter$Factory0, "factory == null");
            this.callAdapterFactories.add(callAdapter$Factory1);
            return this;
        }

        public Builder addConverterFactory(retrofit2.Converter.Factory converter$Factory0) {
            retrofit2.Converter.Factory converter$Factory1 = (retrofit2.Converter.Factory)Objects.requireNonNull(converter$Factory0, "factory == null");
            this.converterFactories.add(converter$Factory1);
            return this;
        }

        public Builder baseUrl(String s) {
            Objects.requireNonNull(s, "baseUrl == null");
            return this.baseUrl(HttpUrl.get(s));
        }

        public Builder baseUrl(URL uRL0) {
            Objects.requireNonNull(uRL0, "baseUrl == null");
            return this.baseUrl(HttpUrl.get(uRL0.toString()));
        }

        public Builder baseUrl(HttpUrl httpUrl0) {
            Objects.requireNonNull(httpUrl0, "baseUrl == null");
            List list0 = httpUrl0.pathSegments();
            if(!"".equals(list0.get(list0.size() - 1))) {
                throw new IllegalArgumentException("baseUrl must end in /: " + httpUrl0);
            }
            this.baseUrl = httpUrl0;
            return this;
        }

        public Retrofit build() {
            if(this.baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }
            Factory call$Factory0 = this.callFactory;
            if(call$Factory0 == null) {
                call$Factory0 = new OkHttpClient();
            }
            Executor executor0 = this.callbackExecutor == null ? this.platform.defaultCallbackExecutor() : this.callbackExecutor;
            ArrayList arrayList0 = new ArrayList(this.callAdapterFactories);
            arrayList0.addAll(this.platform.defaultCallAdapterFactories(executor0));
            ArrayList arrayList1 = new ArrayList(this.converterFactories.size() + 1 + this.platform.defaultConverterFactoriesSize());
            arrayList1.add(new BuiltInConverters());
            arrayList1.addAll(this.converterFactories);
            arrayList1.addAll(this.platform.defaultConverterFactories());
            return new Retrofit(call$Factory0, this.baseUrl, Collections.unmodifiableList(arrayList1), Collections.unmodifiableList(arrayList0), executor0, this.validateEagerly);
        }

        public List callAdapterFactories() {
            return this.callAdapterFactories;
        }

        public Builder callFactory(Factory call$Factory0) {
            this.callFactory = (Factory)Objects.requireNonNull(call$Factory0, "factory == null");
            return this;
        }

        public Builder callbackExecutor(Executor executor0) {
            this.callbackExecutor = (Executor)Objects.requireNonNull(executor0, "executor == null");
            return this;
        }

        public Builder client(OkHttpClient okHttpClient0) {
            return this.callFactory(((Factory)Objects.requireNonNull(okHttpClient0, "client == null")));
        }

        public List converterFactories() {
            return this.converterFactories;
        }

        public Builder validateEagerly(boolean z) {
            this.validateEagerly = z;
            return this;
        }
    }

    final HttpUrl baseUrl;
    final List callAdapterFactories;
    final Factory callFactory;
    @Nullable
    final Executor callbackExecutor;
    final List converterFactories;
    private final Map serviceMethodCache;
    final boolean validateEagerly;

    Retrofit(Factory call$Factory0, HttpUrl httpUrl0, List list0, List list1, @Nullable Executor executor0, boolean z) {
        this.serviceMethodCache = new ConcurrentHashMap();
        this.callFactory = call$Factory0;
        this.baseUrl = httpUrl0;
        this.converterFactories = list0;
        this.callAdapterFactories = list1;
        this.callbackExecutor = executor0;
        this.validateEagerly = z;
    }

    public HttpUrl baseUrl() {
        return this.baseUrl;
    }

    public CallAdapter callAdapter(Type type0, Annotation[] arr_annotation) {
        return this.nextCallAdapter(null, type0, arr_annotation);
    }

    public List callAdapterFactories() {
        return this.callAdapterFactories;
    }

    public Factory callFactory() {
        return this.callFactory;
    }

    @Nullable
    public Executor callbackExecutor() {
        return this.callbackExecutor;
    }

    public List converterFactories() {
        return this.converterFactories;
    }

    public Object create(Class class0) {
        this.validateServiceInterface(class0);
        return Proxy.newProxyInstance(class0.getClassLoader(), new Class[]{class0}, new InvocationHandler() {
            private final Object[] emptyArgs;
            private final Platform platform;

            {
                Class class0 = class0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.platform = Platform.get();
                this.emptyArgs = new Object[0];
            }

            @Override
            @Nullable
            public Object invoke(Object object0, Method method0, @Nullable Object[] arr_object) throws Throwable {
                if(method0.getDeclaringClass() == Object.class) {
                    return method0.invoke(this, arr_object);
                }
                if(arr_object == null) {
                    arr_object = this.emptyArgs;
                }
                return this.platform.isDefaultMethod(method0) ? this.platform.invokeDefaultMethod(method0, class0, object0, arr_object) : Retrofit.this.loadServiceMethod(method0).invoke(arr_object);
            }
        });
    }

    ServiceMethod loadServiceMethod(Method method0) {
        ServiceMethod serviceMethod0 = (ServiceMethod)this.serviceMethodCache.get(method0);
        if(serviceMethod0 != null) {
            return serviceMethod0;
        }
        synchronized(this.serviceMethodCache) {
            ServiceMethod serviceMethod1 = (ServiceMethod)this.serviceMethodCache.get(method0);
            if(serviceMethod1 == null) {
                serviceMethod1 = ServiceMethod.parseAnnotations(this, method0);
                this.serviceMethodCache.put(method0, serviceMethod1);
            }
            return serviceMethod1;
        }
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public CallAdapter nextCallAdapter(@Nullable retrofit2.CallAdapter.Factory callAdapter$Factory0, Type type0, Annotation[] arr_annotation) {
        Objects.requireNonNull(type0, "returnType == null");
        Objects.requireNonNull(arr_annotation, "annotations == null");
        int v = this.callAdapterFactories.indexOf(callAdapter$Factory0) + 1;
        int v1 = this.callAdapterFactories.size();
        for(int v2 = v; v2 < v1; ++v2) {
            CallAdapter callAdapter0 = ((retrofit2.CallAdapter.Factory)this.callAdapterFactories.get(v2)).get(type0, arr_annotation, this);
            if(callAdapter0 != null) {
                return callAdapter0;
            }
        }
        StringBuilder stringBuilder0 = new StringBuilder("Could not locate call adapter for ");
        stringBuilder0.append(type0);
        stringBuilder0.append(".\n");
        if(callAdapter$Factory0 != null) {
            stringBuilder0.append("  Skipped:");
            for(int v3 = 0; v3 < v; ++v3) {
                stringBuilder0.append("\n   * ");
                stringBuilder0.append(((retrofit2.CallAdapter.Factory)this.callAdapterFactories.get(v3)).getClass().getName());
            }
            stringBuilder0.append('\n');
        }
        stringBuilder0.append("  Tried:");
        int v4 = this.callAdapterFactories.size();
        while(v < v4) {
            stringBuilder0.append("\n   * ");
            stringBuilder0.append(((retrofit2.CallAdapter.Factory)this.callAdapterFactories.get(v)).getClass().getName());
            ++v;
        }
        throw new IllegalArgumentException(stringBuilder0.toString());
    }

    public Converter nextRequestBodyConverter(@Nullable retrofit2.Converter.Factory converter$Factory0, Type type0, Annotation[] arr_annotation, Annotation[] arr_annotation1) {
        Objects.requireNonNull(type0, "type == null");
        Objects.requireNonNull(arr_annotation, "parameterAnnotations == null");
        Objects.requireNonNull(arr_annotation1, "methodAnnotations == null");
        int v = this.converterFactories.indexOf(converter$Factory0) + 1;
        int v1 = this.converterFactories.size();
        for(int v2 = v; v2 < v1; ++v2) {
            Converter converter0 = ((retrofit2.Converter.Factory)this.converterFactories.get(v2)).requestBodyConverter(type0, arr_annotation, arr_annotation1, this);
            if(converter0 != null) {
                return converter0;
            }
        }
        StringBuilder stringBuilder0 = new StringBuilder("Could not locate RequestBody converter for ");
        stringBuilder0.append(type0);
        stringBuilder0.append(".\n");
        if(converter$Factory0 != null) {
            stringBuilder0.append("  Skipped:");
            for(int v3 = 0; v3 < v; ++v3) {
                stringBuilder0.append("\n   * ");
                stringBuilder0.append(((retrofit2.Converter.Factory)this.converterFactories.get(v3)).getClass().getName());
            }
            stringBuilder0.append('\n');
        }
        stringBuilder0.append("  Tried:");
        int v4 = this.converterFactories.size();
        while(v < v4) {
            stringBuilder0.append("\n   * ");
            stringBuilder0.append(((retrofit2.Converter.Factory)this.converterFactories.get(v)).getClass().getName());
            ++v;
        }
        throw new IllegalArgumentException(stringBuilder0.toString());
    }

    public Converter nextResponseBodyConverter(@Nullable retrofit2.Converter.Factory converter$Factory0, Type type0, Annotation[] arr_annotation) {
        Objects.requireNonNull(type0, "type == null");
        Objects.requireNonNull(arr_annotation, "annotations == null");
        int v = this.converterFactories.indexOf(converter$Factory0) + 1;
        int v1 = this.converterFactories.size();
        for(int v2 = v; v2 < v1; ++v2) {
            Converter converter0 = ((retrofit2.Converter.Factory)this.converterFactories.get(v2)).responseBodyConverter(type0, arr_annotation, this);
            if(converter0 != null) {
                return converter0;
            }
        }
        StringBuilder stringBuilder0 = new StringBuilder("Could not locate ResponseBody converter for ");
        stringBuilder0.append(type0);
        stringBuilder0.append(".\n");
        if(converter$Factory0 != null) {
            stringBuilder0.append("  Skipped:");
            for(int v3 = 0; v3 < v; ++v3) {
                stringBuilder0.append("\n   * ");
                stringBuilder0.append(((retrofit2.Converter.Factory)this.converterFactories.get(v3)).getClass().getName());
            }
            stringBuilder0.append('\n');
        }
        stringBuilder0.append("  Tried:");
        int v4 = this.converterFactories.size();
        while(v < v4) {
            stringBuilder0.append("\n   * ");
            stringBuilder0.append(((retrofit2.Converter.Factory)this.converterFactories.get(v)).getClass().getName());
            ++v;
        }
        throw new IllegalArgumentException(stringBuilder0.toString());
    }

    public Converter requestBodyConverter(Type type0, Annotation[] arr_annotation, Annotation[] arr_annotation1) {
        return this.nextRequestBodyConverter(null, type0, arr_annotation, arr_annotation1);
    }

    public Converter responseBodyConverter(Type type0, Annotation[] arr_annotation) {
        return this.nextResponseBodyConverter(null, type0, arr_annotation);
    }

    public Converter stringConverter(Type type0, Annotation[] arr_annotation) {
        Objects.requireNonNull(type0, "type == null");
        Objects.requireNonNull(arr_annotation, "annotations == null");
        int v = this.converterFactories.size();
        for(int v1 = 0; v1 < v; ++v1) {
            Converter converter0 = ((retrofit2.Converter.Factory)this.converterFactories.get(v1)).stringConverter(type0, arr_annotation, this);
            if(converter0 != null) {
                return converter0;
            }
        }
        return (Object object0) -> this.convert(object0);
    }

    private void validateServiceInterface(Class class0) {
        if(!class0.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        ArrayDeque arrayDeque0 = new ArrayDeque(1);
        arrayDeque0.add(class0);
        while(!arrayDeque0.isEmpty()) {
            Class class1 = (Class)arrayDeque0.removeFirst();
            if(class1.getTypeParameters().length != 0) {
                StringBuilder stringBuilder0 = new StringBuilder("Type parameters are unsupported on ");
                stringBuilder0.append(class1.getName());
                if(class1 != class0) {
                    stringBuilder0.append(" which is an interface of ");
                    stringBuilder0.append(class0.getName());
                }
                throw new IllegalArgumentException(stringBuilder0.toString());
            }
            Collections.addAll(arrayDeque0, class1.getInterfaces());
        }
        if(this.validateEagerly) {
            Platform platform0 = Platform.get();
            Method[] arr_method = class0.getDeclaredMethods();
            for(int v = 0; v < arr_method.length; ++v) {
                Method method0 = arr_method[v];
                if(!platform0.isDefaultMethod(method0) && !Modifier.isStatic(method0.getModifiers())) {
                    this.loadServiceMethod(method0);
                }
            }
        }
    }
}

