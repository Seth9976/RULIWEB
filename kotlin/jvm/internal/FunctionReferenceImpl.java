package kotlin.jvm.internal;

import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class FunctionReferenceImpl extends FunctionReference {
    public FunctionReferenceImpl(int v, Class class0, String s, String s1, int v1) {
        super(v, FunctionReferenceImpl.NO_RECEIVER, class0, s, s1, v1);
    }

    public FunctionReferenceImpl(int v, Object object0, Class class0, String s, String s1, int v1) {
        super(v, object0, class0, s, s1, v1);
    }

    public FunctionReferenceImpl(int v, KDeclarationContainer kDeclarationContainer0, String s, String s1) {
        super(v, FunctionReferenceImpl.NO_RECEIVER, ((ClassBasedDeclarationContainer)kDeclarationContainer0).getJClass(), s, s1, !(kDeclarationContainer0 instanceof KClass));
    }
}

