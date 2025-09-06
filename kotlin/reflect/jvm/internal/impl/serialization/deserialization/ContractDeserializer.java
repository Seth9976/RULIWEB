package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.Pair;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;

public interface ContractDeserializer {
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final ContractDeserializer DEFAULT;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.DEFAULT = new ContractDeserializer.Companion.DEFAULT.1();
        }

        public final ContractDeserializer getDEFAULT() {
            return Companion.DEFAULT;
        }
    }

    public static final Companion Companion;

    static {
        ContractDeserializer.Companion = Companion.$$INSTANCE;
    }

    Pair deserializeContractFromFunction(Function arg1, FunctionDescriptor arg2, TypeTable arg3, TypeDeserializer arg4);
}

