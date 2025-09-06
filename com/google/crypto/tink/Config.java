package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyTypeEntry;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class Config {
    public static KeyTypeEntry getTinkKeyTypeEntry(String s, String s1, String s2, int v, boolean z) {
        return (KeyTypeEntry)KeyTypeEntry.newBuilder().setPrimitiveName(s1).setTypeUrl("type.googleapis.com/google.crypto.tink." + s2).setKeyManagerVersion(v).setNewKeyAllowed(z).setCatalogueName(s).build();
    }

    public static void register(RegistryConfig registryConfig0) throws GeneralSecurityException {
        for(Object object0: registryConfig0.getEntryList()) {
            Config.registerKeyType(((KeyTypeEntry)object0));
        }
    }

    // 去混淆评级： 中等(76)
    public static void registerKeyType(KeyTypeEntry keyTypeEntry0) throws GeneralSecurityException {
        Config.validate(keyTypeEntry0);
        Catalogue catalogue0 = Registry.getCatalogue("");
        Registry.registerPrimitiveWrapper(catalogue0.getPrimitiveWrapper());
        Registry.registerKeyManager(catalogue0.getKeyManager("", "", keyTypeEntry0.getKeyManagerVersion()), keyTypeEntry0.getNewKeyAllowed());
    }

    // 去混淆评级： 中等(120)
    private static void validate(KeyTypeEntry keyTypeEntry0) throws GeneralSecurityException {
        throw new GeneralSecurityException("Missing type_url.");
    }
}

