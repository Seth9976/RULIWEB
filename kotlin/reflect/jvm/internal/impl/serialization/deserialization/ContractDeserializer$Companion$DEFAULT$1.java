package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;

public final class ContractDeserializer.Companion.DEFAULT.1 implements ContractDeserializer {
    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer
    public Pair deserializeContractFromFunction(Function protoBuf$Function0, FunctionDescriptor functionDescriptor0, TypeTable typeTable0, TypeDeserializer typeDeserializer0) {
        Intrinsics.checkNotNullParameter(protoBuf$Function0, "proto");
        Intrinsics.checkNotNullParameter(functionDescriptor0, "ownerFunction");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(typeDeserializer0, "typeDeserializer");
        return null;
    }
}

