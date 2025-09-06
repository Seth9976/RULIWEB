package retrofit2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.RequestBody;

abstract class ParameterHandler {
    static final class Body extends ParameterHandler {
        private final Converter converter;
        private final Method method;
        private final int p;

        Body(Method method0, int v, Converter converter0) {
            this.method = method0;
            this.p = v;
            this.converter = converter0;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) {
            RequestBody requestBody0;
            if(object0 == null) {
                throw Utils.parameterError(this.method, this.p, "Body parameter value must not be null.", new Object[0]);
            }
            try {
                requestBody0 = (RequestBody)this.converter.convert(object0);
            }
            catch(IOException iOException0) {
                throw Utils.parameterError(this.method, iOException0, this.p, "Unable to convert " + object0 + " to RequestBody", new Object[0]);
            }
            requestBuilder0.setBody(requestBody0);
        }
    }

    static final class Field extends ParameterHandler {
        private final boolean encoded;
        private final String name;
        private final Converter valueConverter;

        Field(String s, Converter converter0, boolean z) {
            this.name = (String)Objects.requireNonNull(s, "name == null");
            this.valueConverter = converter0;
            this.encoded = z;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            if(object0 != null) {
                String s = (String)this.valueConverter.convert(object0);
                if(s != null) {
                    requestBuilder0.addFormField(this.name, s, this.encoded);
                }
            }
        }
    }

    static final class FieldMap extends ParameterHandler {
        private final boolean encoded;
        private final Method method;
        private final int p;
        private final Converter valueConverter;

        FieldMap(Method method0, int v, Converter converter0, boolean z) {
            this.method = method0;
            this.p = v;
            this.valueConverter = converter0;
            this.encoded = z;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            this.apply(requestBuilder0, ((Map)object0));
        }

        void apply(RequestBuilder requestBuilder0, @Nullable Map map0) throws IOException {
            if(map0 == null) {
                throw Utils.parameterError(this.method, this.p, "Field map was null.", new Object[0]);
            }
            for(Object object0: map0.entrySet()) {
                String s = (String)((Map.Entry)object0).getKey();
                if(s == null) {
                    throw Utils.parameterError(this.method, this.p, "Field map contained null key.", new Object[0]);
                }
                Object object1 = ((Map.Entry)object0).getValue();
                if(object1 == null) {
                    throw Utils.parameterError(this.method, this.p, "Field map contained null value for key \'" + s + "\'.", new Object[0]);
                }
                String s1 = (String)this.valueConverter.convert(object1);
                if(s1 == null) {
                    throw Utils.parameterError(this.method, this.p, "Field map value \'" + object1 + "\' converted to null by " + this.valueConverter.getClass().getName() + " for key \'" + s + "\'.", new Object[0]);
                }
                requestBuilder0.addFormField(s, s1, this.encoded);
            }
        }
    }

    static final class Header extends ParameterHandler {
        private final String name;
        private final Converter valueConverter;

        Header(String s, Converter converter0) {
            this.name = (String)Objects.requireNonNull(s, "name == null");
            this.valueConverter = converter0;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            if(object0 != null) {
                String s = (String)this.valueConverter.convert(object0);
                if(s != null) {
                    requestBuilder0.addHeader(this.name, s);
                }
            }
        }
    }

    static final class HeaderMap extends ParameterHandler {
        private final Method method;
        private final int p;
        private final Converter valueConverter;

        HeaderMap(Method method0, int v, Converter converter0) {
            this.method = method0;
            this.p = v;
            this.valueConverter = converter0;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            this.apply(requestBuilder0, ((Map)object0));
        }

        void apply(RequestBuilder requestBuilder0, @Nullable Map map0) throws IOException {
            if(map0 == null) {
                throw Utils.parameterError(this.method, this.p, "Header map was null.", new Object[0]);
            }
            for(Object object0: map0.entrySet()) {
                String s = (String)((Map.Entry)object0).getKey();
                if(s == null) {
                    throw Utils.parameterError(this.method, this.p, "Header map contained null key.", new Object[0]);
                }
                Object object1 = ((Map.Entry)object0).getValue();
                if(object1 == null) {
                    throw Utils.parameterError(this.method, this.p, "Header map contained null value for key \'" + s + "\'.", new Object[0]);
                }
                requestBuilder0.addHeader(s, ((String)this.valueConverter.convert(object1)));
            }
        }
    }

    static final class Headers extends ParameterHandler {
        private final Method method;
        private final int p;

        Headers(Method method0, int v) {
            this.method = method0;
            this.p = v;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            this.apply(requestBuilder0, ((okhttp3.Headers)object0));
        }

        void apply(RequestBuilder requestBuilder0, @Nullable okhttp3.Headers headers0) {
            if(headers0 == null) {
                throw Utils.parameterError(this.method, this.p, "Headers parameter must not be null.", new Object[0]);
            }
            requestBuilder0.addHeaders(headers0);
        }
    }

    static final class Part extends ParameterHandler {
        private final Converter converter;
        private final okhttp3.Headers headers;
        private final Method method;
        private final int p;

        Part(Method method0, int v, okhttp3.Headers headers0, Converter converter0) {
            this.method = method0;
            this.p = v;
            this.headers = headers0;
            this.converter = converter0;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) {
            RequestBody requestBody0;
            if(object0 == null) {
                return;
            }
            try {
                requestBody0 = (RequestBody)this.converter.convert(object0);
            }
            catch(IOException iOException0) {
                throw Utils.parameterError(this.method, this.p, "Unable to convert " + object0 + " to RequestBody", new Object[]{iOException0});
            }
            requestBuilder0.addPart(this.headers, requestBody0);
        }
    }

    static final class PartMap extends ParameterHandler {
        private final Method method;
        private final int p;
        private final String transferEncoding;
        private final Converter valueConverter;

        PartMap(Method method0, int v, Converter converter0, String s) {
            this.method = method0;
            this.p = v;
            this.valueConverter = converter0;
            this.transferEncoding = s;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            this.apply(requestBuilder0, ((Map)object0));
        }

        void apply(RequestBuilder requestBuilder0, @Nullable Map map0) throws IOException {
            if(map0 == null) {
                throw Utils.parameterError(this.method, this.p, "Part map was null.", new Object[0]);
            }
            for(Object object0: map0.entrySet()) {
                String s = (String)((Map.Entry)object0).getKey();
                if(s == null) {
                    throw Utils.parameterError(this.method, this.p, "Part map contained null key.", new Object[0]);
                }
                Object object1 = ((Map.Entry)object0).getValue();
                if(object1 == null) {
                    throw Utils.parameterError(this.method, this.p, "Part map contained null value for key \'" + s + "\'.", new Object[0]);
                }
                requestBuilder0.addPart(okhttp3.Headers.of(new String[]{"Content-Disposition", "form-data; name=\"" + s + "\"", "Content-Transfer-Encoding", this.transferEncoding}), ((RequestBody)this.valueConverter.convert(object1)));
            }
        }
    }

    static final class Path extends ParameterHandler {
        private final boolean encoded;
        private final Method method;
        private final String name;
        private final int p;
        private final Converter valueConverter;

        Path(Method method0, int v, String s, Converter converter0, boolean z) {
            this.method = method0;
            this.p = v;
            this.name = (String)Objects.requireNonNull(s, "name == null");
            this.valueConverter = converter0;
            this.encoded = z;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            if(object0 == null) {
                throw Utils.parameterError(this.method, this.p, "Path parameter \"" + this.name + "\" value must not be null.", new Object[0]);
            }
            String s = (String)this.valueConverter.convert(object0);
            requestBuilder0.addPathParam(this.name, s, this.encoded);
        }
    }

    static final class Query extends ParameterHandler {
        private final boolean encoded;
        private final String name;
        private final Converter valueConverter;

        Query(String s, Converter converter0, boolean z) {
            this.name = (String)Objects.requireNonNull(s, "name == null");
            this.valueConverter = converter0;
            this.encoded = z;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            if(object0 != null) {
                String s = (String)this.valueConverter.convert(object0);
                if(s != null) {
                    requestBuilder0.addQueryParam(this.name, s, this.encoded);
                }
            }
        }
    }

    static final class QueryMap extends ParameterHandler {
        private final boolean encoded;
        private final Method method;
        private final int p;
        private final Converter valueConverter;

        QueryMap(Method method0, int v, Converter converter0, boolean z) {
            this.method = method0;
            this.p = v;
            this.valueConverter = converter0;
            this.encoded = z;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            this.apply(requestBuilder0, ((Map)object0));
        }

        void apply(RequestBuilder requestBuilder0, @Nullable Map map0) throws IOException {
            if(map0 == null) {
                throw Utils.parameterError(this.method, this.p, "Query map was null", new Object[0]);
            }
            for(Object object0: map0.entrySet()) {
                String s = (String)((Map.Entry)object0).getKey();
                if(s == null) {
                    throw Utils.parameterError(this.method, this.p, "Query map contained null key.", new Object[0]);
                }
                Object object1 = ((Map.Entry)object0).getValue();
                if(object1 == null) {
                    throw Utils.parameterError(this.method, this.p, "Query map contained null value for key \'" + s + "\'.", new Object[0]);
                }
                String s1 = (String)this.valueConverter.convert(object1);
                if(s1 == null) {
                    throw Utils.parameterError(this.method, this.p, "Query map value \'" + object1 + "\' converted to null by " + this.valueConverter.getClass().getName() + " for key \'" + s + "\'.", new Object[0]);
                }
                requestBuilder0.addQueryParam(s, s1, this.encoded);
            }
        }
    }

    static final class QueryName extends ParameterHandler {
        private final boolean encoded;
        private final Converter nameConverter;

        QueryName(Converter converter0, boolean z) {
            this.nameConverter = converter0;
            this.encoded = z;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            if(object0 == null) {
                return;
            }
            requestBuilder0.addQueryParam(((String)this.nameConverter.convert(object0)), null, this.encoded);
        }
    }

    static final class RawPart extends ParameterHandler {
        static final RawPart INSTANCE;

        static {
            RawPart.INSTANCE = new RawPart();
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
            this.apply(requestBuilder0, ((okhttp3.MultipartBody.Part)object0));
        }

        void apply(RequestBuilder requestBuilder0, @Nullable okhttp3.MultipartBody.Part multipartBody$Part0) {
            if(multipartBody$Part0 != null) {
                requestBuilder0.addPart(multipartBody$Part0);
            }
        }
    }

    static final class RelativeUrl extends ParameterHandler {
        private final Method method;
        private final int p;

        RelativeUrl(Method method0, int v) {
            this.method = method0;
            this.p = v;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) {
            if(object0 == null) {
                throw Utils.parameterError(this.method, this.p, "@Url parameter is null.", new Object[0]);
            }
            requestBuilder0.setRelativeUrl(object0);
        }
    }

    static final class Tag extends ParameterHandler {
        final Class cls;

        Tag(Class class0) {
            this.cls = class0;
        }

        @Override  // retrofit2.ParameterHandler
        void apply(RequestBuilder requestBuilder0, @Nullable Object object0) {
            requestBuilder0.addTag(this.cls, object0);
        }
    }

    abstract void apply(RequestBuilder arg1, @Nullable Object arg2) throws IOException;

    final ParameterHandler array() {
        return new ParameterHandler() {
            @Override  // retrofit2.ParameterHandler
            void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
                if(object0 != null) {
                    int v = Array.getLength(object0);
                    for(int v1 = 0; v1 < v; ++v1) {
                        Object object1 = Array.get(object0, v1);
                        ParameterHandler.this.apply(requestBuilder0, object1);
                    }
                }
            }
        };
    }

    final ParameterHandler iterable() {
        return new ParameterHandler() {
            void apply(RequestBuilder requestBuilder0, @Nullable Iterable iterable0) throws IOException {
                if(iterable0 != null) {
                    for(Object object0: iterable0) {
                        ParameterHandler.this.apply(requestBuilder0, object0);
                    }
                }
            }

            @Override  // retrofit2.ParameterHandler
            void apply(RequestBuilder requestBuilder0, @Nullable Object object0) throws IOException {
                this.apply(requestBuilder0, ((Iterable)object0));
            }
        };
    }
}

