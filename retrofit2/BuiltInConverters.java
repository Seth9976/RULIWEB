package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import kotlin.Unit;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Streaming;

final class BuiltInConverters extends Factory {
    static final class BufferingResponseBodyConverter implements Converter {
        static final BufferingResponseBodyConverter INSTANCE;

        static {
            BufferingResponseBodyConverter.INSTANCE = new BufferingResponseBodyConverter();
        }

        // 检测为 Lambda 实现
        @Override  // retrofit2.Converter
        public Object convert(Object object0) throws IOException [...]

        public ResponseBody convert(ResponseBody responseBody0) throws IOException {
            try(responseBody0) {
                return Utils.buffer(responseBody0);
            }
        }
    }

    static final class RequestBodyConverter implements Converter {
        static final RequestBodyConverter INSTANCE;

        static {
            RequestBodyConverter.INSTANCE = new RequestBodyConverter();
        }

        // 检测为 Lambda 实现
        @Override  // retrofit2.Converter
        public Object convert(Object object0) throws IOException [...]

        public RequestBody convert(RequestBody requestBody0) {
            return requestBody0;
        }
    }

    static final class StreamingResponseBodyConverter implements Converter {
        static final StreamingResponseBodyConverter INSTANCE;

        static {
            StreamingResponseBodyConverter.INSTANCE = new StreamingResponseBodyConverter();
        }

        // 检测为 Lambda 实现
        @Override  // retrofit2.Converter
        public Object convert(Object object0) throws IOException [...]

        public ResponseBody convert(ResponseBody responseBody0) {
            return responseBody0;
        }
    }

    static final class ToStringConverter implements Converter {
        static final ToStringConverter INSTANCE;

        static {
            ToStringConverter.INSTANCE = new ToStringConverter();
        }

        // 检测为 Lambda 实现
        @Override  // retrofit2.Converter
        public Object convert(Object object0) throws IOException [...]

        public String convert(Object object0) {
            return object0.toString();
        }
    }

    static final class UnitResponseBodyConverter implements Converter {
        static final UnitResponseBodyConverter INSTANCE;

        static {
            UnitResponseBodyConverter.INSTANCE = new UnitResponseBodyConverter();
        }

        // 检测为 Lambda 实现
        @Override  // retrofit2.Converter
        public Object convert(Object object0) throws IOException [...]

        public Unit convert(ResponseBody responseBody0) {
            responseBody0.close();
            return Unit.INSTANCE;
        }
    }

    static final class VoidResponseBodyConverter implements Converter {
        static final VoidResponseBodyConverter INSTANCE;

        static {
            VoidResponseBodyConverter.INSTANCE = new VoidResponseBodyConverter();
        }

        // 检测为 Lambda 实现
        @Override  // retrofit2.Converter
        public Object convert(Object object0) throws IOException [...]

        public Void convert(ResponseBody responseBody0) {
            responseBody0.close();
            return null;
        }
    }

    private boolean checkForKotlinUnit;

    BuiltInConverters() {
        this.checkForKotlinUnit = true;
    }

    @Override  // retrofit2.Converter$Factory
    @Nullable
    public Converter requestBodyConverter(Type type0, Annotation[] arr_annotation, Annotation[] arr_annotation1, Retrofit retrofit0) {
        Class class0 = Utils.getRawType(type0);
        return RequestBody.class.isAssignableFrom(class0) ? (Object object0) -> this.convert(((RequestBody)object0)) : null;
    }

    @Override  // retrofit2.Converter$Factory
    @Nullable
    public Converter responseBodyConverter(Type type0, Annotation[] arr_annotation, Retrofit retrofit0) {
        if(type0 == ResponseBody.class) {
            return Utils.isAnnotationPresent(arr_annotation, Streaming.class) ? (Object object0) -> this.convert(((ResponseBody)object0)) : (Object object0) -> this.convert(((ResponseBody)object0));
        }
        if(type0 == Void.class) {
            return (Object object0) -> this.convert(((ResponseBody)object0));
        }
        return this.checkForKotlinUnit && type0 == Unit.class ? (Object object0) -> this.convert(((ResponseBody)object0)) : null;
    }
}

