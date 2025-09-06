package com.google.crypto.tink.internal;

import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;

public abstract class ParametersParser {
    public interface ParametersParsingFunction {
        Parameters parseParameters(Serialization arg1) throws GeneralSecurityException;
    }

    private final Bytes objectIdentifier;
    private final Class serializationClass;

    private ParametersParser(Bytes bytes0, Class class0) {
        this.objectIdentifier = bytes0;
        this.serializationClass = class0;
    }

    ParametersParser(Bytes bytes0, Class class0, com.google.crypto.tink.internal.ParametersParser.1 parametersParser$10) {
        this(bytes0, class0);
    }

    public static ParametersParser create(ParametersParsingFunction parametersParser$ParametersParsingFunction0, Bytes bytes0, Class class0) {
        return new ParametersParser(bytes0, class0, parametersParser$ParametersParsingFunction0) {
            final ParametersParsingFunction val$function;

            {
                this.val$function = parametersParser$ParametersParsingFunction0;
                super(bytes0, class0, null);
            }

            @Override  // com.google.crypto.tink.internal.ParametersParser
            public Parameters parseParameters(Serialization serialization0) throws GeneralSecurityException {
                return this.val$function.parseParameters(serialization0);
            }
        };
    }

    public final Bytes getObjectIdentifier() {
        return this.objectIdentifier;
    }

    public final Class getSerializationClass() {
        return this.serializationClass;
    }

    public abstract Parameters parseParameters(Serialization arg1) throws GeneralSecurityException;
}

