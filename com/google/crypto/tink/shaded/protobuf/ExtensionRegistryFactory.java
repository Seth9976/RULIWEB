package com.google.crypto.tink.shaded.protobuf;

final class ExtensionRegistryFactory {
    static final Class EXTENSION_REGISTRY_CLASS = null;
    static final String FULL_REGISTRY_CLASS_NAME = "com.google.crypto.tink.shaded.protobuf.ExtensionRegistry";

    static {
        ExtensionRegistryFactory.EXTENSION_REGISTRY_CLASS = ExtensionRegistryFactory.reflectExtensionRegistry();
    }

    public static ExtensionRegistryLite create() {
        ExtensionRegistryLite extensionRegistryLite0 = ExtensionRegistryFactory.invokeSubclassFactory("newInstance");
        return extensionRegistryLite0 == null ? new ExtensionRegistryLite() : extensionRegistryLite0;
    }

    public static ExtensionRegistryLite createEmpty() {
        ExtensionRegistryLite extensionRegistryLite0 = ExtensionRegistryFactory.invokeSubclassFactory("getEmptyRegistry");
        return extensionRegistryLite0 == null ? ExtensionRegistryLite.EMPTY_REGISTRY_LITE : extensionRegistryLite0;
    }

    private static final ExtensionRegistryLite invokeSubclassFactory(String s) {
        Class class0 = ExtensionRegistryFactory.EXTENSION_REGISTRY_CLASS;
        if(class0 == null) {
            return null;
        }
        try {
            return (ExtensionRegistryLite)class0.getDeclaredMethod(s, null).invoke(null, null);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    static boolean isFullRegistry(ExtensionRegistryLite extensionRegistryLite0) {
        return ExtensionRegistryFactory.EXTENSION_REGISTRY_CLASS != null && ExtensionRegistryFactory.EXTENSION_REGISTRY_CLASS.isAssignableFrom(extensionRegistryLite0.getClass());
    }

    static Class reflectExtensionRegistry() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.ExtensionRegistry");
        }
        catch(ClassNotFoundException unused_ex) {
            return null;
        }
    }
}

