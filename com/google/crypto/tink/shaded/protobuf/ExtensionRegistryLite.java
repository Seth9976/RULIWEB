package com.google.crypto.tink.shaded.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtensionRegistryLite {
    static class ExtensionClassHolder {
        static final Class INSTANCE;

        static {
            ExtensionClassHolder.INSTANCE = ExtensionClassHolder.resolveExtensionClass();
        }

        static Class resolveExtensionClass() {
            try {
                return Class.forName("com.google.crypto.tink.shaded.protobuf.Extension");
            }
            catch(ClassNotFoundException unused_ex) {
                return null;
            }
        }
    }

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

    static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = null;
    static final String EXTENSION_CLASS_NAME = "com.google.crypto.tink.shaded.protobuf.Extension";
    private static boolean doFullRuntimeInheritanceCheck = true;
    private static volatile boolean eagerlyParseMessageSets = false;
    private static volatile ExtensionRegistryLite emptyRegistry;
    private final Map extensionsByNumber;

    static {
        ExtensionRegistryLite.EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);
    }

    ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    ExtensionRegistryLite(ExtensionRegistryLite extensionRegistryLite0) {
        if(extensionRegistryLite0 == ExtensionRegistryLite.EMPTY_REGISTRY_LITE) {
            this.extensionsByNumber = Collections.EMPTY_MAP;
            return;
        }
        this.extensionsByNumber = Collections.unmodifiableMap(extensionRegistryLite0.extensionsByNumber);
    }

    ExtensionRegistryLite(boolean z) {
        this.extensionsByNumber = Collections.EMPTY_MAP;
    }

    public final void add(ExtensionLite extensionLite0) {
        Class class0 = extensionLite0.getClass();
        if(GeneratedExtension.class.isAssignableFrom(class0)) {
            this.add(((GeneratedExtension)extensionLite0));
        }
        if(ExtensionRegistryLite.doFullRuntimeInheritanceCheck && ExtensionRegistryFactory.isFullRegistry(this)) {
            try {
                this.getClass().getMethod("add", ExtensionClassHolder.INSTANCE).invoke(this, extensionLite0);
            }
            catch(Exception exception0) {
                throw new IllegalArgumentException(String.format("Could not invoke ExtensionRegistry#add for %s", extensionLite0), exception0);
            }
        }
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
        ExtensionRegistryLite extensionRegistryLite0 = ExtensionRegistryLite.emptyRegistry;
        if(extensionRegistryLite0 == null) {
            synchronized(ExtensionRegistryLite.class) {
                ExtensionRegistryLite extensionRegistryLite1 = ExtensionRegistryLite.emptyRegistry;
                if(extensionRegistryLite1 == null) {
                    extensionRegistryLite1 = ExtensionRegistryLite.doFullRuntimeInheritanceCheck ? ExtensionRegistryFactory.createEmpty() : ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
                    ExtensionRegistryLite.emptyRegistry = extensionRegistryLite1;
                }
                return extensionRegistryLite1;
            }
        }
        return extensionRegistryLite0;
    }

    public ExtensionRegistryLite getUnmodifiable() {
        return new ExtensionRegistryLite(this);
    }

    public static boolean isEagerlyParseMessageSets() {
        return ExtensionRegistryLite.eagerlyParseMessageSets;
    }

    // 去混淆评级： 低(20)
    public static ExtensionRegistryLite newInstance() {
        return ExtensionRegistryLite.doFullRuntimeInheritanceCheck ? ExtensionRegistryFactory.create() : new ExtensionRegistryLite();
    }

    public static void setEagerlyParseMessageSets(boolean z) {
        ExtensionRegistryLite.eagerlyParseMessageSets = z;
    }
}

