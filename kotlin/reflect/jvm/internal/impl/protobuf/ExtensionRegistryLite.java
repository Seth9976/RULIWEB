package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtensionRegistryLite {
    static final class ObjectIntPair {
        private final int number;
        private final Object object;

        ObjectIntPair(Object object0, int v) {
            this.object = object0;
            this.number = v;
        }

        @Override
        public boolean equals(Object object0) {
            return object0 instanceof ObjectIntPair ? this.object == ((ObjectIntPair)object0).object && this.number == ((ObjectIntPair)object0).number : false;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(this.object) * 0xFFFF + this.number;
        }
    }

    private static final ExtensionRegistryLite EMPTY = null;
    private static volatile boolean eagerlyParseMessageSets = false;
    private final Map extensionsByNumber;

    static {
        ExtensionRegistryLite.EMPTY = new ExtensionRegistryLite(true);
    }

    ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    private ExtensionRegistryLite(boolean z) {
        this.extensionsByNumber = Collections.EMPTY_MAP;
    }

    public final void add(GeneratedExtension generatedMessageLite$GeneratedExtension0) {
        ObjectIntPair extensionRegistryLite$ObjectIntPair0 = new ObjectIntPair(generatedMessageLite$GeneratedExtension0.getContainingTypeDefaultInstance(), generatedMessageLite$GeneratedExtension0.getNumber());
        this.extensionsByNumber.put(extensionRegistryLite$ObjectIntPair0, generatedMessageLite$GeneratedExtension0);
    }

    public GeneratedExtension findLiteExtensionByNumber(MessageLite messageLite0, int v) {
        ObjectIntPair extensionRegistryLite$ObjectIntPair0 = new ObjectIntPair(messageLite0, v);
        return (GeneratedExtension)this.extensionsByNumber.get(extensionRegistryLite$ObjectIntPair0);
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        return ExtensionRegistryLite.EMPTY;
    }

    public static ExtensionRegistryLite newInstance() {
        return new ExtensionRegistryLite();
    }
}

