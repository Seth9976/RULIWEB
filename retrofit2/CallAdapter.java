package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

public interface CallAdapter {
    public static abstract class Factory {
        @Nullable
        public abstract CallAdapter get(Type arg1, Annotation[] arg2, Retrofit arg3);

        protected static Type getParameterUpperBound(int v, ParameterizedType parameterizedType0) {
            return Utils.getParameterUpperBound(v, parameterizedType0);
        }

        protected static Class getRawType(Type type0) {
            return Utils.getRawType(type0);
        }
    }

    Object adapt(Call arg1);

    Type responseType();
}

