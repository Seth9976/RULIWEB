package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import kotlin.coroutines.Continuation;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Tag;
import retrofit2.http.Url;

final class RequestFactory {
    static final class Builder {
        private static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
        private static final Pattern PARAM_NAME_REGEX;
        private static final Pattern PARAM_URL_REGEX;
        @Nullable
        MediaType contentType;
        boolean gotBody;
        boolean gotField;
        boolean gotPart;
        boolean gotPath;
        boolean gotQuery;
        boolean gotQueryMap;
        boolean gotQueryName;
        boolean gotUrl;
        boolean hasBody;
        @Nullable
        Headers headers;
        @Nullable
        String httpMethod;
        boolean isFormEncoded;
        boolean isKotlinSuspendFunction;
        boolean isMultipart;
        final Method method;
        final Annotation[] methodAnnotations;
        final Annotation[][] parameterAnnotationsArray;
        @Nullable
        ParameterHandler[] parameterHandlers;
        final Type[] parameterTypes;
        @Nullable
        String relativeUrl;
        @Nullable
        Set relativeUrlParamNames;
        final Retrofit retrofit;

        static {
            Builder.PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
            Builder.PARAM_NAME_REGEX = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");
        }

        Builder(Retrofit retrofit0, Method method0) {
            this.retrofit = retrofit0;
            this.method = method0;
            this.methodAnnotations = method0.getAnnotations();
            this.parameterTypes = method0.getGenericParameterTypes();
            this.parameterAnnotationsArray = method0.getParameterAnnotations();
        }

        private static Class boxIfPrimitive(Class class0) {
            if(Boolean.TYPE == class0) {
                return Boolean.class;
            }
            if(Byte.TYPE == class0) {
                return Byte.class;
            }
            if(Character.TYPE == class0) {
                return Character.class;
            }
            if(Double.TYPE == class0) {
                return Double.class;
            }
            if(Float.TYPE == class0) {
                return Float.class;
            }
            if(Integer.TYPE == class0) {
                return Integer.class;
            }
            if(Long.TYPE == class0) {
                return Long.class;
            }
            return Short.TYPE == class0 ? Short.class : class0;
        }

        RequestFactory build() {
            Annotation[] arr_annotation = this.methodAnnotations;
            for(int v = 0; v < arr_annotation.length; ++v) {
                this.parseMethodAnnotation(arr_annotation[v]);
            }
            if(this.httpMethod == null) {
                throw Utils.methodError(this.method, "HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
            if(!this.hasBody) {
                if(this.isMultipart) {
                    throw Utils.methodError(this.method, "Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
                if(this.isFormEncoded) {
                    throw Utils.methodError(this.method, "FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
            }
            this.parameterHandlers = new ParameterHandler[this.parameterAnnotationsArray.length];
            int v1 = this.parameterAnnotationsArray.length - 1;
            for(int v2 = 0; true; ++v2) {
                boolean z = true;
                if(v2 >= this.parameterAnnotationsArray.length) {
                    break;
                }
                ParameterHandler[] arr_parameterHandler = this.parameterHandlers;
                Type type0 = this.parameterTypes[v2];
                Annotation[] arr_annotation1 = this.parameterAnnotationsArray[v2];
                if(v2 != v1) {
                    z = false;
                }
                arr_parameterHandler[v2] = this.parseParameter(v2, type0, arr_annotation1, z);
            }
            if(this.relativeUrl == null && !this.gotUrl) {
                throw Utils.methodError(this.method, "Missing either @%s URL or @Url parameter.", new Object[]{this.httpMethod});
            }
            boolean z1 = this.isFormEncoded;
            if(!z1 && !this.isMultipart && !this.hasBody && this.gotBody) {
                throw Utils.methodError(this.method, "Non-body HTTP method cannot contain @Body.", new Object[0]);
            }
            if(z1 && !this.gotField) {
                throw Utils.methodError(this.method, "Form-encoded method must contain at least one @Field.", new Object[0]);
            }
            if(this.isMultipart && !this.gotPart) {
                throw Utils.methodError(this.method, "Multipart method must contain at least one @Part.", new Object[0]);
            }
            return new RequestFactory(this);
        }

        private Headers parseHeaders(String[] arr_s) {
            okhttp3.Headers.Builder headers$Builder0 = new okhttp3.Headers.Builder();
            for(int v = 0; v < arr_s.length; ++v) {
                String s = arr_s[v];
                int v1 = s.indexOf(58);
                if(v1 == -1 || v1 == 0 || v1 == s.length() - 1) {
                    throw Utils.methodError(this.method, "@Headers value must be in the form \"Name: Value\". Found: \"%s\"", new Object[]{s});
                }
                String s1 = s.substring(0, v1);
                String s2 = s.substring(v1 + 1).trim();
                if("Content-Type".equalsIgnoreCase(s1)) {
                    try {
                        this.contentType = MediaType.get(s2);
                    }
                    catch(IllegalArgumentException illegalArgumentException0) {
                        throw Utils.methodError(this.method, illegalArgumentException0, "Malformed content type: %s", new Object[]{s2});
                    }
                }
                else {
                    headers$Builder0.add(s1, s2);
                }
            }
            return headers$Builder0.build();
        }

        private void parseHttpMethodAndPath(String s, String s1, boolean z) {
            String s2 = this.httpMethod;
            if(s2 != null) {
                throw Utils.methodError(this.method, "Only one HTTP method is allowed. Found: %s and %s.", new Object[]{s2, s});
            }
            this.httpMethod = s;
            this.hasBody = z;
            if(s1.isEmpty()) {
                return;
            }
            int v = s1.indexOf(0x3F);
            if(v != -1 && v < s1.length() - 1) {
                String s3 = s1.substring(v + 1);
                if(Builder.PARAM_URL_REGEX.matcher(s3).find()) {
                    throw Utils.methodError(this.method, "URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", new Object[]{s3});
                }
            }
            this.relativeUrl = s1;
            this.relativeUrlParamNames = Builder.parsePathParameters(s1);
        }

        private void parseMethodAnnotation(Annotation annotation0) {
            if(annotation0 instanceof DELETE) {
                this.parseHttpMethodAndPath("DELETE", ((DELETE)annotation0).value(), false);
                return;
            }
            if(annotation0 instanceof GET) {
                this.parseHttpMethodAndPath("GET", ((GET)annotation0).value(), false);
                return;
            }
            if(annotation0 instanceof HEAD) {
                this.parseHttpMethodAndPath("HEAD", ((HEAD)annotation0).value(), false);
                return;
            }
            if(annotation0 instanceof PATCH) {
                this.parseHttpMethodAndPath("PATCH", ((PATCH)annotation0).value(), true);
                return;
            }
            if(annotation0 instanceof POST) {
                this.parseHttpMethodAndPath("POST", ((POST)annotation0).value(), true);
                return;
            }
            if(annotation0 instanceof PUT) {
                this.parseHttpMethodAndPath("PUT", ((PUT)annotation0).value(), true);
                return;
            }
            if(annotation0 instanceof OPTIONS) {
                this.parseHttpMethodAndPath("OPTIONS", ((OPTIONS)annotation0).value(), false);
                return;
            }
            if(annotation0 instanceof HTTP) {
                this.parseHttpMethodAndPath(((HTTP)annotation0).method(), ((HTTP)annotation0).path(), ((HTTP)annotation0).hasBody());
                return;
            }
            if(annotation0 instanceof retrofit2.http.Headers) {
                String[] arr_s = ((retrofit2.http.Headers)annotation0).value();
                if(arr_s.length == 0) {
                    throw Utils.methodError(this.method, "@Headers annotation is empty.", new Object[0]);
                }
                this.headers = this.parseHeaders(arr_s);
                return;
            }
            if(annotation0 instanceof Multipart) {
                if(this.isFormEncoded) {
                    throw Utils.methodError(this.method, "Only one encoding annotation is allowed.", new Object[0]);
                }
                this.isMultipart = true;
                return;
            }
            if(annotation0 instanceof FormUrlEncoded) {
                if(this.isMultipart) {
                    throw Utils.methodError(this.method, "Only one encoding annotation is allowed.", new Object[0]);
                }
                this.isFormEncoded = true;
            }
        }

        @Nullable
        private ParameterHandler parseParameter(int v, Type type0, @Nullable Annotation[] arr_annotation, boolean z) {
            ParameterHandler parameterHandler0;
            if(arr_annotation == null) {
                parameterHandler0 = null;
            }
            else {
                parameterHandler0 = null;
                for(int v1 = 0; v1 < arr_annotation.length; ++v1) {
                    ParameterHandler parameterHandler1 = this.parseParameterAnnotation(v, type0, arr_annotation, arr_annotation[v1]);
                    if(parameterHandler1 != null) {
                        if(parameterHandler0 != null) {
                            throw Utils.parameterError(this.method, v, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                        }
                        parameterHandler0 = parameterHandler1;
                        continue;
                    }
                }
            }
            if(parameterHandler0 == null) {
                if(z) {
                    try {
                        if(Utils.getRawType(type0) == Continuation.class) {
                            this.isKotlinSuspendFunction = true;
                            return null;
                        }
                    }
                    catch(NoClassDefFoundError unused_ex) {
                    }
                }
                throw Utils.parameterError(this.method, v, "No Retrofit annotation found.", new Object[0]);
            }
            return parameterHandler0;
        }

        @Nullable
        private ParameterHandler parseParameterAnnotation(int v, Type type0, Annotation[] arr_annotation, Annotation annotation0) {
            Converter converter8;
            if(annotation0 instanceof Url) {
                this.validateResolvableType(v, type0);
                if(this.gotUrl) {
                    throw Utils.parameterError(this.method, v, "Multiple @Url method annotations found.", new Object[0]);
                }
                if(this.gotPath) {
                    throw Utils.parameterError(this.method, v, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if(this.gotQuery) {
                    throw Utils.parameterError(this.method, v, "A @Url parameter must not come after a @Query.", new Object[0]);
                }
                if(this.gotQueryName) {
                    throw Utils.parameterError(this.method, v, "A @Url parameter must not come after a @QueryName.", new Object[0]);
                }
                if(this.gotQueryMap) {
                    throw Utils.parameterError(this.method, v, "A @Url parameter must not come after a @QueryMap.", new Object[0]);
                }
                if(this.relativeUrl != null) {
                    throw Utils.parameterError(this.method, v, "@Url cannot be used with @%s URL", new Object[]{this.httpMethod});
                }
                this.gotUrl = true;
                if(type0 != HttpUrl.class && type0 != String.class && type0 != URI.class && (!(type0 instanceof Class) || !"android.net.Uri".equals(((Class)type0).getName()))) {
                    throw Utils.parameterError(this.method, v, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                }
                return new RelativeUrl(this.method, v);
            }
            if(annotation0 instanceof Path) {
                this.validateResolvableType(v, type0);
                if(this.gotQuery) {
                    throw Utils.parameterError(this.method, v, "A @Path parameter must not come after a @Query.", new Object[0]);
                }
                if(this.gotQueryName) {
                    throw Utils.parameterError(this.method, v, "A @Path parameter must not come after a @QueryName.", new Object[0]);
                }
                if(this.gotQueryMap) {
                    throw Utils.parameterError(this.method, v, "A @Path parameter must not come after a @QueryMap.", new Object[0]);
                }
                if(this.gotUrl) {
                    throw Utils.parameterError(this.method, v, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if(this.relativeUrl == null) {
                    throw Utils.parameterError(this.method, v, "@Path can only be used with relative url on @%s", new Object[]{this.httpMethod});
                }
                this.gotPath = true;
                String s = ((Path)annotation0).value();
                this.validatePathName(v, s);
                Converter converter0 = this.retrofit.stringConverter(type0, arr_annotation);
                boolean z = ((Path)annotation0).encoded();
                return new retrofit2.ParameterHandler.Path(this.method, v, s, converter0, z);
            }
            if(annotation0 instanceof Query) {
                this.validateResolvableType(v, type0);
                String s1 = ((Query)annotation0).value();
                boolean z1 = ((Query)annotation0).encoded();
                Class class0 = Utils.getRawType(type0);
                this.gotQuery = true;
                if(Iterable.class.isAssignableFrom(class0)) {
                    if(!(type0 instanceof ParameterizedType)) {
                        throw Utils.parameterError(this.method, v, class0.getSimpleName() + " must include generic type (e.g., " + class0.getSimpleName() + "<String>)", new Object[0]);
                    }
                    Type type1 = Utils.getParameterUpperBound(0, ((ParameterizedType)type0));
                    return new retrofit2.ParameterHandler.Query(s1, this.retrofit.stringConverter(type1, arr_annotation), z1).iterable();
                }
                if(class0.isArray()) {
                    Class class1 = Builder.boxIfPrimitive(class0.getComponentType());
                    return new retrofit2.ParameterHandler.Query(s1, this.retrofit.stringConverter(class1, arr_annotation), z1).array();
                }
                return new retrofit2.ParameterHandler.Query(s1, this.retrofit.stringConverter(type0, arr_annotation), z1);
            }
            if(annotation0 instanceof QueryName) {
                this.validateResolvableType(v, type0);
                boolean z2 = ((QueryName)annotation0).encoded();
                Class class2 = Utils.getRawType(type0);
                this.gotQueryName = true;
                if(Iterable.class.isAssignableFrom(class2)) {
                    if(!(type0 instanceof ParameterizedType)) {
                        throw Utils.parameterError(this.method, v, class2.getSimpleName() + " must include generic type (e.g., " + class2.getSimpleName() + "<String>)", new Object[0]);
                    }
                    Type type2 = Utils.getParameterUpperBound(0, ((ParameterizedType)type0));
                    return new retrofit2.ParameterHandler.QueryName(this.retrofit.stringConverter(type2, arr_annotation), z2).iterable();
                }
                if(class2.isArray()) {
                    Class class3 = Builder.boxIfPrimitive(class2.getComponentType());
                    return new retrofit2.ParameterHandler.QueryName(this.retrofit.stringConverter(class3, arr_annotation), z2).array();
                }
                return new retrofit2.ParameterHandler.QueryName(this.retrofit.stringConverter(type0, arr_annotation), z2);
            }
            if(annotation0 instanceof QueryMap) {
                this.validateResolvableType(v, type0);
                Class class4 = Utils.getRawType(type0);
                this.gotQueryMap = true;
                if(!Map.class.isAssignableFrom(class4)) {
                    throw Utils.parameterError(this.method, v, "@QueryMap parameter type must be Map.", new Object[0]);
                }
                Type type3 = Utils.getSupertype(type0, class4, Map.class);
                if(!(type3 instanceof ParameterizedType)) {
                    throw Utils.parameterError(this.method, v, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                Type type4 = Utils.getParameterUpperBound(0, ((ParameterizedType)type3));
                if(String.class != type4) {
                    throw Utils.parameterError(this.method, v, "@QueryMap keys must be of type String: " + type4, new Object[0]);
                }
                Type type5 = Utils.getParameterUpperBound(1, ((ParameterizedType)type3));
                Converter converter1 = this.retrofit.stringConverter(type5, arr_annotation);
                boolean z3 = ((QueryMap)annotation0).encoded();
                return new retrofit2.ParameterHandler.QueryMap(this.method, v, converter1, z3);
            }
            if(annotation0 instanceof Header) {
                this.validateResolvableType(v, type0);
                String s2 = ((Header)annotation0).value();
                Class class5 = Utils.getRawType(type0);
                if(Iterable.class.isAssignableFrom(class5)) {
                    if(!(type0 instanceof ParameterizedType)) {
                        throw Utils.parameterError(this.method, v, class5.getSimpleName() + " must include generic type (e.g., " + class5.getSimpleName() + "<String>)", new Object[0]);
                    }
                    Type type6 = Utils.getParameterUpperBound(0, ((ParameterizedType)type0));
                    return new retrofit2.ParameterHandler.Header(s2, this.retrofit.stringConverter(type6, arr_annotation)).iterable();
                }
                if(class5.isArray()) {
                    Class class6 = Builder.boxIfPrimitive(class5.getComponentType());
                    return new retrofit2.ParameterHandler.Header(s2, this.retrofit.stringConverter(class6, arr_annotation)).array();
                }
                return new retrofit2.ParameterHandler.Header(s2, this.retrofit.stringConverter(type0, arr_annotation));
            }
            if(annotation0 instanceof HeaderMap) {
                if(type0 == Headers.class) {
                    return new retrofit2.ParameterHandler.Headers(this.method, v);
                }
                this.validateResolvableType(v, type0);
                Class class7 = Utils.getRawType(type0);
                if(!Map.class.isAssignableFrom(class7)) {
                    throw Utils.parameterError(this.method, v, "@HeaderMap parameter type must be Map.", new Object[0]);
                }
                Type type7 = Utils.getSupertype(type0, class7, Map.class);
                if(!(type7 instanceof ParameterizedType)) {
                    throw Utils.parameterError(this.method, v, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                Type type8 = Utils.getParameterUpperBound(0, ((ParameterizedType)type7));
                if(String.class != type8) {
                    throw Utils.parameterError(this.method, v, "@HeaderMap keys must be of type String: " + type8, new Object[0]);
                }
                Type type9 = Utils.getParameterUpperBound(1, ((ParameterizedType)type7));
                Converter converter2 = this.retrofit.stringConverter(type9, arr_annotation);
                return new retrofit2.ParameterHandler.HeaderMap(this.method, v, converter2);
            }
            if(annotation0 instanceof Field) {
                this.validateResolvableType(v, type0);
                if(!this.isFormEncoded) {
                    throw Utils.parameterError(this.method, v, "@Field parameters can only be used with form encoding.", new Object[0]);
                }
                String s3 = ((Field)annotation0).value();
                boolean z4 = ((Field)annotation0).encoded();
                this.gotField = true;
                Class class8 = Utils.getRawType(type0);
                if(Iterable.class.isAssignableFrom(class8)) {
                    if(!(type0 instanceof ParameterizedType)) {
                        throw Utils.parameterError(this.method, v, class8.getSimpleName() + " must include generic type (e.g., " + class8.getSimpleName() + "<String>)", new Object[0]);
                    }
                    Type type10 = Utils.getParameterUpperBound(0, ((ParameterizedType)type0));
                    return new retrofit2.ParameterHandler.Field(s3, this.retrofit.stringConverter(type10, arr_annotation), z4).iterable();
                }
                if(class8.isArray()) {
                    Class class9 = Builder.boxIfPrimitive(class8.getComponentType());
                    return new retrofit2.ParameterHandler.Field(s3, this.retrofit.stringConverter(class9, arr_annotation), z4).array();
                }
                return new retrofit2.ParameterHandler.Field(s3, this.retrofit.stringConverter(type0, arr_annotation), z4);
            }
            if(annotation0 instanceof FieldMap) {
                this.validateResolvableType(v, type0);
                if(!this.isFormEncoded) {
                    throw Utils.parameterError(this.method, v, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
                }
                Class class10 = Utils.getRawType(type0);
                if(!Map.class.isAssignableFrom(class10)) {
                    throw Utils.parameterError(this.method, v, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                Type type11 = Utils.getSupertype(type0, class10, Map.class);
                if(!(type11 instanceof ParameterizedType)) {
                    throw Utils.parameterError(this.method, v, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                Type type12 = Utils.getParameterUpperBound(0, ((ParameterizedType)type11));
                if(String.class != type12) {
                    throw Utils.parameterError(this.method, v, "@FieldMap keys must be of type String: " + type12, new Object[0]);
                }
                Type type13 = Utils.getParameterUpperBound(1, ((ParameterizedType)type11));
                Converter converter3 = this.retrofit.stringConverter(type13, arr_annotation);
                this.gotField = true;
                boolean z5 = ((FieldMap)annotation0).encoded();
                return new retrofit2.ParameterHandler.FieldMap(this.method, v, converter3, z5);
            }
            if(annotation0 instanceof Part) {
                this.validateResolvableType(v, type0);
                if(!this.isMultipart) {
                    throw Utils.parameterError(this.method, v, "@Part parameters can only be used with multipart encoding.", new Object[0]);
                }
                this.gotPart = true;
                String s4 = ((Part)annotation0).value();
                Class class11 = Utils.getRawType(type0);
                if(s4.isEmpty()) {
                    if(Iterable.class.isAssignableFrom(class11)) {
                        if(!(type0 instanceof ParameterizedType)) {
                            throw Utils.parameterError(this.method, v, class11.getSimpleName() + " must include generic type (e.g., " + class11.getSimpleName() + "<String>)", new Object[0]);
                        }
                        Class class12 = Utils.getRawType(Utils.getParameterUpperBound(0, ((ParameterizedType)type0)));
                        if(!okhttp3.MultipartBody.Part.class.isAssignableFrom(class12)) {
                            throw Utils.parameterError(this.method, v, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                        return RawPart.INSTANCE.iterable();
                    }
                    if(class11.isArray()) {
                        if(!okhttp3.MultipartBody.Part.class.isAssignableFrom(class11.getComponentType())) {
                            throw Utils.parameterError(this.method, v, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                        return RawPart.INSTANCE.array();
                    }
                    if(!okhttp3.MultipartBody.Part.class.isAssignableFrom(class11)) {
                        throw Utils.parameterError(this.method, v, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                    return RawPart.INSTANCE;
                }
                Headers headers0 = Headers.of(new String[]{"Content-Disposition", "form-data; name=\"" + s4 + "\"", "Content-Transfer-Encoding", ((Part)annotation0).encoding()});
                if(Iterable.class.isAssignableFrom(class11)) {
                    if(!(type0 instanceof ParameterizedType)) {
                        throw Utils.parameterError(this.method, v, class11.getSimpleName() + " must include generic type (e.g., " + class11.getSimpleName() + "<String>)", new Object[0]);
                    }
                    Type type14 = Utils.getParameterUpperBound(0, ((ParameterizedType)type0));
                    Class class13 = Utils.getRawType(type14);
                    if(okhttp3.MultipartBody.Part.class.isAssignableFrom(class13)) {
                        throw Utils.parameterError(this.method, v, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                    Converter converter4 = this.retrofit.requestBodyConverter(type14, arr_annotation, this.methodAnnotations);
                    return new retrofit2.ParameterHandler.Part(this.method, v, headers0, converter4).iterable();
                }
                if(class11.isArray()) {
                    Class class14 = Builder.boxIfPrimitive(class11.getComponentType());
                    if(okhttp3.MultipartBody.Part.class.isAssignableFrom(class14)) {
                        throw Utils.parameterError(this.method, v, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                    Converter converter5 = this.retrofit.requestBodyConverter(class14, arr_annotation, this.methodAnnotations);
                    return new retrofit2.ParameterHandler.Part(this.method, v, headers0, converter5).array();
                }
                if(okhttp3.MultipartBody.Part.class.isAssignableFrom(class11)) {
                    throw Utils.parameterError(this.method, v, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                }
                Converter converter6 = this.retrofit.requestBodyConverter(type0, arr_annotation, this.methodAnnotations);
                return new retrofit2.ParameterHandler.Part(this.method, v, headers0, converter6);
            }
            if(annotation0 instanceof PartMap) {
                this.validateResolvableType(v, type0);
                if(!this.isMultipart) {
                    throw Utils.parameterError(this.method, v, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
                }
                this.gotPart = true;
                Class class15 = Utils.getRawType(type0);
                if(!Map.class.isAssignableFrom(class15)) {
                    throw Utils.parameterError(this.method, v, "@PartMap parameter type must be Map.", new Object[0]);
                }
                Type type15 = Utils.getSupertype(type0, class15, Map.class);
                if(!(type15 instanceof ParameterizedType)) {
                    throw Utils.parameterError(this.method, v, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                Type type16 = Utils.getParameterUpperBound(0, ((ParameterizedType)type15));
                if(String.class != type16) {
                    throw Utils.parameterError(this.method, v, "@PartMap keys must be of type String: " + type16, new Object[0]);
                }
                Type type17 = Utils.getParameterUpperBound(1, ((ParameterizedType)type15));
                Class class16 = Utils.getRawType(type17);
                if(okhttp3.MultipartBody.Part.class.isAssignableFrom(class16)) {
                    throw Utils.parameterError(this.method, v, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                }
                Converter converter7 = this.retrofit.requestBodyConverter(type17, arr_annotation, this.methodAnnotations);
                String s5 = ((PartMap)annotation0).encoding();
                return new retrofit2.ParameterHandler.PartMap(this.method, v, converter7, s5);
            }
            if(annotation0 instanceof Body) {
                this.validateResolvableType(v, type0);
                if(this.isFormEncoded || this.isMultipart) {
                    throw Utils.parameterError(this.method, v, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                }
                if(this.gotBody) {
                    throw Utils.parameterError(this.method, v, "Multiple @Body method annotations found.", new Object[0]);
                }
                try {
                    converter8 = this.retrofit.requestBodyConverter(type0, arr_annotation, this.methodAnnotations);
                }
                catch(RuntimeException runtimeException0) {
                    throw Utils.parameterError(this.method, runtimeException0, v, "Unable to create @Body converter for %s", new Object[]{type0});
                }
                this.gotBody = true;
                return new retrofit2.ParameterHandler.Body(this.method, v, converter8);
            }
            if(annotation0 instanceof Tag) {
                this.validateResolvableType(v, type0);
                Class class17 = Utils.getRawType(type0);
                for(int v1 = v - 1; v1 >= 0; --v1) {
                    ParameterHandler parameterHandler0 = this.parameterHandlers[v1];
                    if(parameterHandler0 instanceof retrofit2.ParameterHandler.Tag && ((retrofit2.ParameterHandler.Tag)parameterHandler0).cls.equals(class17)) {
                        throw Utils.parameterError(this.method, v, "@Tag type " + class17.getName() + " is duplicate of parameter #" + (v1 + 1) + " and would always overwrite its value.", new Object[0]);
                    }
                }
                return new retrofit2.ParameterHandler.Tag(class17);
            }
            return null;
        }

        static Set parsePathParameters(String s) {
            Matcher matcher0 = Builder.PARAM_URL_REGEX.matcher(s);
            Set set0 = new LinkedHashSet();
            while(matcher0.find()) {
                set0.add(matcher0.group(1));
            }
            return set0;
        }

        private void validatePathName(int v, String s) {
            if(!Builder.PARAM_NAME_REGEX.matcher(s).matches()) {
                throw Utils.parameterError(this.method, v, "@Path parameter name must match %s. Found: %s", new Object[]{"\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}", s});
            }
            if(!this.relativeUrlParamNames.contains(s)) {
                throw Utils.parameterError(this.method, v, "URL \"%s\" does not contain \"{%s}\".", new Object[]{this.relativeUrl, s});
            }
        }

        private void validateResolvableType(int v, Type type0) {
            if(Utils.hasUnresolvableType(type0)) {
                throw Utils.parameterError(this.method, v, "Parameter type must not include a type variable or wildcard: %s", new Object[]{type0});
            }
        }
    }

    private final HttpUrl baseUrl;
    @Nullable
    private final MediaType contentType;
    private final boolean hasBody;
    @Nullable
    private final Headers headers;
    final String httpMethod;
    private final boolean isFormEncoded;
    final boolean isKotlinSuspendFunction;
    private final boolean isMultipart;
    private final Method method;
    private final ParameterHandler[] parameterHandlers;
    @Nullable
    private final String relativeUrl;

    RequestFactory(Builder requestFactory$Builder0) {
        this.method = requestFactory$Builder0.method;
        this.baseUrl = requestFactory$Builder0.retrofit.baseUrl;
        this.httpMethod = requestFactory$Builder0.httpMethod;
        this.relativeUrl = requestFactory$Builder0.relativeUrl;
        this.headers = requestFactory$Builder0.headers;
        this.contentType = requestFactory$Builder0.contentType;
        this.hasBody = requestFactory$Builder0.hasBody;
        this.isFormEncoded = requestFactory$Builder0.isFormEncoded;
        this.isMultipart = requestFactory$Builder0.isMultipart;
        this.parameterHandlers = requestFactory$Builder0.parameterHandlers;
        this.isKotlinSuspendFunction = requestFactory$Builder0.isKotlinSuspendFunction;
    }

    Request create(Object[] arr_object) throws IOException {
        ParameterHandler[] arr_parameterHandler = this.parameterHandlers;
        int v = arr_object.length;
        if(v != arr_parameterHandler.length) {
            throw new IllegalArgumentException("Argument count (" + v + ") doesn\'t match expected count (" + arr_parameterHandler.length + ")");
        }
        RequestBuilder requestBuilder0 = new RequestBuilder(this.httpMethod, this.baseUrl, this.relativeUrl, this.headers, this.contentType, this.hasBody, this.isFormEncoded, this.isMultipart);
        if(this.isKotlinSuspendFunction) {
            --v;
        }
        ArrayList arrayList0 = new ArrayList(v);
        for(int v1 = 0; v1 < v; ++v1) {
            arrayList0.add(arr_object[v1]);
            arr_parameterHandler[v1].apply(requestBuilder0, arr_object[v1]);
        }
        okhttp3.Request.Builder request$Builder0 = requestBuilder0.get();
        Invocation invocation0 = new Invocation(this.method, arrayList0);
        return request$Builder0.tag(Invocation.class, invocation0).build();
    }

    static RequestFactory parseAnnotations(Retrofit retrofit0, Method method0) {
        return new Builder(retrofit0, method0).build();
    }
}

