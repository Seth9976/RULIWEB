package com.google.crypto.tink.tinkkey.internal;

import com.google.crypto.tink.internal.KeyStatusTypeProtoConverter;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.tinkkey.KeyHandle;
import com.google.crypto.tink.tinkkey.TinkKey;

public final class InternalKeyHandle extends KeyHandle {
    public InternalKeyHandle(TinkKey tinkKey0, KeyStatusType keyStatusType0, int v) {
        super(tinkKey0, KeyStatusTypeProtoConverter.fromProto(keyStatusType0), v);
    }
}

