package com.google.crypto.tink.internal;

import com.google.crypto.tink.Parameters;
import java.security.GeneralSecurityException;

public abstract class ParametersSerializer {
    public interface ParametersSerializationFunction {
        Serialization serializeParameters(Parameters arg1) throws GeneralSecurityException;
    }

    private final Class parametersClass;
    private final Class serializationClass;

    private ParametersSerializer(Class class0, Class class1) {
        this.parametersClass = class0;
        this.serializationClass = class1;
    }

    ParametersSerializer(Class class0, Class class1, com.google.crypto.tink.internal.ParametersSerializer.1 parametersSerializer$10) {
        this(class0, class1);
    }

    public static ParametersSerializer create(ParametersSerializationFunction parametersSerializer$ParametersSerializationFunction0, Class class0, Class class1) {
        return new ParametersSerializer(class0, class1, parametersSerializer$ParametersSerializationFunction0) {
            final ParametersSerializationFunction val$function;

            {
                this.val$function = parametersSerializer$ParametersSerializationFunction0;
                super(class0, class1, null);
            }

            @Override  // com.google.crypto.tink.internal.ParametersSerializer
            public Serialization serializeParameters(Parameters parameters0) throws GeneralSecurityException {
                return this.val$function.serializeParameters(parameters0);
            }
        };
    }

    public Class getParametersClass() {
        return this.parametersClass;
    }

    public Class getSerializationClass() {
        return this.serializationClass;
    }

    public abstract Serialization serializeParameters(Parameters arg1) throws GeneralSecurityException;
}

