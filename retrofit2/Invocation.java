package retrofit2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Invocation {
    private final List arguments;
    private final Method method;

    Invocation(Method method0, List list0) {
        this.method = method0;
        this.arguments = Collections.unmodifiableList(list0);
    }

    public List arguments() {
        return this.arguments;
    }

    public Method method() {
        return this.method;
    }

    public static Invocation of(Method method0, List list0) {
        Objects.requireNonNull(method0, "method == null");
        Objects.requireNonNull(list0, "arguments == null");
        return new Invocation(method0, new ArrayList(list0));
    }

    @Override
    public String toString() {
        return String.format("%s.%s() %s", this.method.getDeclaringClass().getName(), this.method.getName(), this.arguments);
    }
}

