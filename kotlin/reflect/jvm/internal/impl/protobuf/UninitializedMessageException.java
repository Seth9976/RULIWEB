package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.List;

public class UninitializedMessageException extends RuntimeException {
    private final List missingFields;

    public UninitializedMessageException(MessageLite messageLite0) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.missingFields = null;
    }

    public InvalidProtocolBufferException asInvalidProtocolBufferException() {
        return new InvalidProtocolBufferException(this.getMessage());
    }
}

