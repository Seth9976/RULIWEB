package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class ExcludedTypeAnnotations {
    public static final ExcludedTypeAnnotations INSTANCE;
    private static final Set internalAnnotationsForResolve;

    static {
        ExcludedTypeAnnotations.INSTANCE = new ExcludedTypeAnnotations();
        ExcludedTypeAnnotations.internalAnnotationsForResolve = SetsKt.setOf(new FqName[]{new FqName("kotlin.internal.NoInfer"), new FqName("kotlin.internal.Exact")});
    }

    public final Set getInternalAnnotationsForResolve() {
        return ExcludedTypeAnnotations.internalAnnotationsForResolve;
    }
}

