package retrofit2;

import java.lang.annotation.Annotation;

final class SkipCallbackExecutorImpl implements SkipCallbackExecutor {
    private static final SkipCallbackExecutor INSTANCE;

    static {
        SkipCallbackExecutorImpl.INSTANCE = new SkipCallbackExecutorImpl();
    }

    @Override
    public Class annotationType() {
        return SkipCallbackExecutor.class;
    }

    static Annotation[] ensurePresent(Annotation[] arr_annotation) {
        if(Utils.isAnnotationPresent(arr_annotation, SkipCallbackExecutor.class)) {
            return arr_annotation;
        }
        Annotation[] arr_annotation1 = new Annotation[arr_annotation.length + 1];
        arr_annotation1[0] = SkipCallbackExecutorImpl.INSTANCE;
        System.arraycopy(arr_annotation, 0, arr_annotation1, 1, arr_annotation.length);
        return arr_annotation1;
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof SkipCallbackExecutor;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    // 去混淆评级： 中等(60)
    @Override
    public String toString() {
        return "@retrofit2.SkipCallbackExecutor()";
    }
}

