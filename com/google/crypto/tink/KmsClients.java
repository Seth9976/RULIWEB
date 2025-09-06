package com.google.crypto.tink;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

public final class KmsClients {
    private static List autoClients;
    private static final CopyOnWriteArrayList clients;

    static {
        KmsClients.clients = new CopyOnWriteArrayList();
    }

    public static void add(KmsClient kmsClient0) {
        KmsClients.clients.add(kmsClient0);
    }

    public static KmsClient get(String s) throws GeneralSecurityException {
        for(Object object0: KmsClients.clients) {
            KmsClient kmsClient0 = (KmsClient)object0;
            if(kmsClient0.doesSupport(s)) {
                return kmsClient0;
            }
            if(false) {
                break;
            }
        }
        throw new GeneralSecurityException("No KMS client does support: " + s);
    }

    public static KmsClient getAutoLoaded(String s) throws GeneralSecurityException {
        synchronized(KmsClients.class) {
            if(KmsClients.autoClients == null) {
                KmsClients.autoClients = KmsClients.loadAutoKmsClients();
            }
            for(Object object0: KmsClients.autoClients) {
                KmsClient kmsClient0 = (KmsClient)object0;
                if(kmsClient0.doesSupport(s)) {
                    return kmsClient0;
                }
                if(false) {
                    break;
                }
            }
        }
        throw new GeneralSecurityException("No KMS client does support: " + s);
    }

    private static List loadAutoKmsClients() {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: ServiceLoader.load(KmsClient.class)) {
            arrayList0.add(((KmsClient)object0));
        }
        return Collections.unmodifiableList(arrayList0);
    }

    static void reset() {
        KmsClients.clients.clear();
    }
}

