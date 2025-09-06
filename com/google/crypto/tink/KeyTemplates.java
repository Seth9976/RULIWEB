package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public final class KeyTemplates {
    public static KeyTemplate get(String s) throws GeneralSecurityException {
        KeyTemplate keyTemplate0 = (KeyTemplate)Registry.keyTemplateMap().get(s);
        if(keyTemplate0 == null) {
            throw new GeneralSecurityException("cannot find key template: " + s);
        }
        return keyTemplate0;
    }
}

