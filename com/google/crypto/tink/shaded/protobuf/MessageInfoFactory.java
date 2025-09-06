package com.google.crypto.tink.shaded.protobuf;

@CheckReturnValue
interface MessageInfoFactory {
    boolean isSupported(Class arg1);

    MessageInfo messageInfoFor(Class arg1);
}

