package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

public class AnnotatedImpl implements Annotated {
    private final Annotations annotations;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 1 ? 2 : 3)];
        arr_object[0] = v == 1 ? "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl" : "annotations";
        arr_object[1] = v == 1 ? "getAnnotations" : "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl";
        if(v != 1) {
            arr_object[2] = "<init>";
        }
        String s = String.format((v == 1 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalArgumentException illegalArgumentException0 = v == 1 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalArgumentException0;
    }

    public AnnotatedImpl(Annotations annotations0) {
        if(annotations0 == null) {
            AnnotatedImpl.$$$reportNull$$$0(0);
        }
        super();
        this.annotations = annotations0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        Annotations annotations0 = this.annotations;
        if(annotations0 == null) {
            AnnotatedImpl.$$$reportNull$$$0(1);
        }
        return annotations0;
    }
}

