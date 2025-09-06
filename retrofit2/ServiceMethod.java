package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

abstract class ServiceMethod {
    @Nullable
    abstract Object invoke(Object[] arg1);

    static ServiceMethod parseAnnotations(Retrofit retrofit0, Method method0) {
        RequestFactory requestFactory0 = RequestFactory.parseAnnotations(retrofit0, method0);
        Type type0 = method0.getGenericReturnType();
        if(Utils.hasUnresolvableType(type0)) {
            throw Utils.methodError(method0, "Method return type must not include a type variable or wildcard: %s", new Object[]{type0});
        }
        if(type0 == Void.TYPE) {
            throw Utils.methodError(method0, "Service methods cannot return void.", new Object[0]);
        }
        return HttpServiceMethod.parseAnnotations(retrofit0, method0, requestFactory0);
    }
}

