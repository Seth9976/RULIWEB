package com.google.android.datatransport.runtime.backends;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class MetadataBackendRegistry implements BackendRegistry {
    static class BackendFactoryProvider {
        private final Context applicationContext;
        private Map backendProviders;

        BackendFactoryProvider(Context context0) {
            this.backendProviders = null;
            this.applicationContext = context0;
        }

        private Map discover(Context context0) {
            Bundle bundle0 = BackendFactoryProvider.getMetadata(context0);
            if(bundle0 == null) {
                Log.w("BackendRegistry", "Could not retrieve metadata, returning empty list of transport backends.");
                return Collections.EMPTY_MAP;
            }
            Map map0 = new HashMap();
            for(Object object0: bundle0.keySet()) {
                String s = (String)object0;
                Object object1 = bundle0.get(s);
                if(object1 instanceof String && s.startsWith("backend:")) {
                    String[] arr_s = ((String)object1).split(",", -1);
                    for(int v = 0; v < arr_s.length; ++v) {
                        String s1 = arr_s[v].trim();
                        if(!s1.isEmpty()) {
                            map0.put(s1, s.substring(8));
                        }
                    }
                }
            }
            return map0;
        }

        BackendFactory get(String s) {
            String s1 = (String)this.getBackendProviders().get(s);
            if(s1 == null) {
                return null;
            }
            try {
                return (BackendFactory)Class.forName(s1).asSubclass(BackendFactory.class).getDeclaredConstructor(null).newInstance(null);
            }
            catch(ClassNotFoundException classNotFoundException0) {
                Log.w("BackendRegistry", String.format("Class %s is not found.", s1), classNotFoundException0);
                return null;
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s.", s1), illegalAccessException0);
                return null;
            }
            catch(InstantiationException instantiationException0) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s.", s1), instantiationException0);
                return null;
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s", s1), noSuchMethodException0);
                return null;
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s", s1), invocationTargetException0);
                return null;
            }
        }

        private Map getBackendProviders() {
            if(this.backendProviders == null) {
                this.backendProviders = this.discover(this.applicationContext);
            }
            return this.backendProviders;
        }

        private static Bundle getMetadata(Context context0) {
            try {
                PackageManager packageManager0 = context0.getPackageManager();
                if(packageManager0 == null) {
                    Log.w("BackendRegistry", "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo0 = packageManager0.getServiceInfo(new ComponentName(context0, TransportBackendDiscovery.class), 0x80);
                if(serviceInfo0 == null) {
                    Log.w("BackendRegistry", "TransportBackendDiscovery has no service info.");
                    return null;
                }
                return serviceInfo0.metaData;
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                Log.w("BackendRegistry", "Application info not found.");
                return null;
            }
        }
    }

    private static final String BACKEND_KEY_PREFIX = "backend:";
    private static final String TAG = "BackendRegistry";
    private final BackendFactoryProvider backendFactoryProvider;
    private final Map backends;
    private final CreationContextFactory creationContextFactory;

    @Inject
    MetadataBackendRegistry(Context context0, CreationContextFactory creationContextFactory0) {
        this(new BackendFactoryProvider(context0), creationContextFactory0);
    }

    MetadataBackendRegistry(BackendFactoryProvider metadataBackendRegistry$BackendFactoryProvider0, CreationContextFactory creationContextFactory0) {
        this.backends = new HashMap();
        this.backendFactoryProvider = metadataBackendRegistry$BackendFactoryProvider0;
        this.creationContextFactory = creationContextFactory0;
    }

    @Override  // com.google.android.datatransport.runtime.backends.BackendRegistry
    public TransportBackend get(String s) {
        synchronized(this) {
            if(this.backends.containsKey(s)) {
                return (TransportBackend)this.backends.get(s);
            }
            BackendFactory backendFactory0 = this.backendFactoryProvider.get(s);
            if(backendFactory0 == null) {
                return null;
            }
            TransportBackend transportBackend1 = backendFactory0.create(this.creationContextFactory.create(s));
            this.backends.put(s, transportBackend1);
            return transportBackend1;
        }
    }
}

