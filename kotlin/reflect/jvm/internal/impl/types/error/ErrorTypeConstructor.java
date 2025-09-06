package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.DefaultBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class ErrorTypeConstructor implements TypeConstructor {
    private final String debugText;
    private final String[] formatParams;
    private final ErrorTypeKind kind;

    public ErrorTypeConstructor(ErrorTypeKind errorTypeKind0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(errorTypeKind0, "kind");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        super();
        this.kind = errorTypeKind0;
        this.formatParams = arr_s;
        Object[] arr_object = Arrays.copyOf(arr_s, arr_s.length);
        String s = String.format(errorTypeKind0.getDebugMessage(), Arrays.copyOf(arr_object, arr_object.length));
        Intrinsics.checkNotNullExpressionValue(s, "format(this, *args)");
        String s1 = String.format("[Error type: %s]", Arrays.copyOf(new Object[]{s}, 1));
        Intrinsics.checkNotNullExpressionValue(s1, "format(this, *args)");
        this.debugText = s1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        return DefaultBuiltIns.Companion.getInstance();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public ClassifierDescriptor getDeclarationDescriptor() {
        return ErrorUtils.INSTANCE.getErrorClass();
    }

    public final ErrorTypeKind getKind() {
        return this.kind;
    }

    public final String getParam(int v) {
        return this.formatParams[v];
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List getParameters() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public Collection getSupertypes() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return this;
    }

    @Override
    public String toString() {
        return this.debugText;
    }
}

