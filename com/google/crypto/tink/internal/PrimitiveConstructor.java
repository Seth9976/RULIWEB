package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import java.security.GeneralSecurityException;

public abstract class PrimitiveConstructor {
    public interface PrimitiveConstructionFunction {
        Object constructPrimitive(Key arg1) throws GeneralSecurityException;
    }

    private final Class keyClass;
    private final Class primitiveClass;

    private PrimitiveConstructor(Class class0, Class class1) {
        this.keyClass = class0;
        this.primitiveClass = class1;
    }

    PrimitiveConstructor(Class class0, Class class1, com.google.crypto.tink.internal.PrimitiveConstructor.1 primitiveConstructor$10) {
        this(class0, class1);
    }

    public abstract Object constructPrimitive(Key arg1) throws GeneralSecurityException;

    public static PrimitiveConstructor create(PrimitiveConstructionFunction primitiveConstructor$PrimitiveConstructionFunction0, Class class0, Class class1) {
        return new PrimitiveConstructor(class0, class1, primitiveConstructor$PrimitiveConstructionFunction0) {
            final PrimitiveConstructionFunction val$function;

            {
                this.val$function = primitiveConstructor$PrimitiveConstructionFunction0;
                super(class0, class1, null);
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveConstructor
            public Object constructPrimitive(Key key0) throws GeneralSecurityException {
                return this.val$function.constructPrimitive(key0);
            }
        };
    }

    public Class getKeyClass() {
        return this.keyClass;
    }

    public Class getPrimitiveClass() {
        return this.primitiveClass;
    }
}

