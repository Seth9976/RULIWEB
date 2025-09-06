package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ComponentDiscovery {
    static class MetadataRegistrarNameRetriever implements RegistrarNameRetriever {
        private final Class discoveryService;

        private MetadataRegistrarNameRetriever(Class class0) {
            this.discoveryService = class0;
        }

        MetadataRegistrarNameRetriever(Class class0, com.google.firebase.components.ComponentDiscovery.1 componentDiscovery$10) {
            this(class0);
        }

        private Bundle getMetadata(Context context0) {
            try {
                PackageManager packageManager0 = context0.getPackageManager();
                if(packageManager0 == null) {
                    Log.w("ComponentDiscovery", "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo0 = packageManager0.getServiceInfo(new ComponentName(context0, this.discoveryService), 0x80);
                if(serviceInfo0 == null) {
                    Log.w("ComponentDiscovery", this.discoveryService + " has no service info.");
                    return null;
                }
                return serviceInfo0.metaData;
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                Log.w("ComponentDiscovery", "Application info not found.");
                return null;
            }
        }

        public List retrieve(Context context0) {
            Bundle bundle0 = this.getMetadata(context0);
            if(bundle0 == null) {
                Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
                return Collections.EMPTY_LIST;
            }
            List list0 = new ArrayList();
            for(Object object0: bundle0.keySet()) {
                String s = (String)object0;
                if("com.google.firebase.components.ComponentRegistrar".equals(bundle0.get(s)) && s.startsWith("com.google.firebase.components:")) {
                    list0.add(s.substring(0x1F));
                }
            }
            return list0;
        }

        @Override  // com.google.firebase.components.ComponentDiscovery$RegistrarNameRetriever
        public List retrieve(Object object0) {
            return this.retrieve(((Context)object0));
        }
    }

    interface RegistrarNameRetriever {
        List retrieve(Object arg1);
    }

    private static final String COMPONENT_KEY_PREFIX = "com.google.firebase.components:";
    private static final String COMPONENT_SENTINEL_VALUE = "com.google.firebase.components.ComponentRegistrar";
    static final String TAG = "ComponentDiscovery";
    private final Object context;
    private final RegistrarNameRetriever retriever;

    ComponentDiscovery(Object object0, RegistrarNameRetriever componentDiscovery$RegistrarNameRetriever0) {
        this.context = object0;
        this.retriever = componentDiscovery$RegistrarNameRetriever0;
    }

    @Deprecated
    public List discover() {
        List list0 = new ArrayList();
        for(Object object0: this.retriever.retrieve(this.context)) {
            String s = (String)object0;
            try {
                ComponentRegistrar componentRegistrar0 = ComponentDiscovery.instantiate(s);
                if(componentRegistrar0 == null) {
                    continue;
                }
                list0.add(componentRegistrar0);
            }
            catch(InvalidRegistrarException invalidRegistrarException0) {
                Log.w("ComponentDiscovery", "Invalid component registrar.", invalidRegistrarException0);
            }
        }
        return list0;
    }

    public List discoverLazy() {
        List list0 = new ArrayList();
        for(Object object0: this.retriever.retrieve(this.context)) {
            list0.add(() -> ComponentDiscovery.instantiate(((String)object0)));
        }
        return list0;
    }

    public static ComponentDiscovery forContext(Context context0, Class class0) {
        return new ComponentDiscovery(context0, new MetadataRegistrarNameRetriever(class0, null));
    }

    private static ComponentRegistrar instantiate(String s) {
        try {
            Class class0 = Class.forName(s);
            if(!ComponentRegistrar.class.isAssignableFrom(class0)) {
                throw new InvalidRegistrarException(String.format("Class %s is not an instance of %s", s, "com.google.firebase.components.ComponentRegistrar"));
            }
            return (ComponentRegistrar)class0.getDeclaredConstructor(null).newInstance(null);
        }
        catch(ClassNotFoundException unused_ex) {
            Log.w("ComponentDiscovery", String.format("Class %s is not an found.", s));
            return null;
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s.", s), illegalAccessException0);
        }
        catch(InstantiationException instantiationException0) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s.", s), instantiationException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s", s), noSuchMethodException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s", s), invocationTargetException0);
        }
    }

    // 检测为 Lambda 实现
    static ComponentRegistrar lambda$discoverLazy$0(String s) [...]

    class com.google.firebase.components.ComponentDiscovery.1 {
    }

}

