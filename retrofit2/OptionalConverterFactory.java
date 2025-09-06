package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import javax.annotation.Nullable;
import okhttp3.ResponseBody;

final class OptionalConverterFactory extends Factory {
    static final class OptionalConverter implements Converter {
        final Converter delegate;

        OptionalConverter(Converter converter0) {
            this.delegate = converter0;
        }

        @Override  // retrofit2.Converter
        public Object convert(Object object0) throws IOException {
            return this.convert(((ResponseBody)object0));
        }

        public Optional convert(ResponseBody responseBody0) throws IOException {
            return Platform..ExternalSyntheticApiModelOutline0.m(this.delegate.convert(responseBody0));
        }
    }

    static final Factory INSTANCE;

    static {
        OptionalConverterFactory.INSTANCE = new OptionalConverterFactory();
    }

    @Override  // retrofit2.Converter$Factory
    @Nullable
    public Converter responseBodyConverter(Type type0, Annotation[] arr_annotation, Retrofit retrofit0) {
        return OptionalConverterFactory.getRawType(type0) != Platform..ExternalSyntheticApiModelOutline0.m$1() ? null : new OptionalConverter(retrofit0.responseBodyConverter(OptionalConverterFactory.getParameterUpperBound(0, ((ParameterizedType)type0)), arr_annotation));
    }
}

