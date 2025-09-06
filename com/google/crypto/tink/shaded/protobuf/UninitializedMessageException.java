package com.google.crypto.tink.shaded.protobuf;

import java.util.Collections;
import java.util.List;

public class UninitializedMessageException extends RuntimeException {
    private final List missingFields;
    private static final long serialVersionUID = 0x986022C4D65DB14DL;

    public UninitializedMessageException(MessageLite messageLite0) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.missingFields = null;
    }

    public UninitializedMessageException(List list0) {
        super(UninitializedMessageException.buildDescription(list0));
        this.missingFields = list0;
    }

    public InvalidProtocolBufferException asInvalidProtocolBufferException() {
        return new InvalidProtocolBufferException(this.getMessage());
    }

    private static String buildDescription(List list0) {
        StringBuilder stringBuilder0 = new StringBuilder("Message missing required fields: ");
        boolean z = true;
        for(Object object0: list0) {
            if(z) {
                z = false;
            }
            else {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(((String)object0));
        }
        return stringBuilder0.toString();
    }

    public List getMissingFields() {
        return Collections.unmodifiableList(this.missingFields);
    }
}

