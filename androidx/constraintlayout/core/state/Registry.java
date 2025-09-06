package androidx.constraintlayout.core.state;

import java.util.HashMap;
import java.util.Set;

public class Registry {
    private HashMap mCallbacks;
    private static final Registry sRegistry;

    static {
        Registry.sRegistry = new Registry();
    }

    public Registry() {
        this.mCallbacks = new HashMap();
    }

    public String currentContent(String s) {
        RegistryCallback registryCallback0 = (RegistryCallback)this.mCallbacks.get(s);
        return registryCallback0 == null ? null : registryCallback0.currentMotionScene();
    }

    public String currentLayoutInformation(String s) {
        RegistryCallback registryCallback0 = (RegistryCallback)this.mCallbacks.get(s);
        return registryCallback0 == null ? null : registryCallback0.currentLayoutInformation();
    }

    public static Registry getInstance() {
        return Registry.sRegistry;
    }

    public long getLastModified(String s) {
        RegistryCallback registryCallback0 = (RegistryCallback)this.mCallbacks.get(s);
        return registryCallback0 == null ? 0x7FFFFFFFFFFFFFFFL : registryCallback0.getLastModified();
    }

    public Set getLayoutList() {
        return this.mCallbacks.keySet();
    }

    public void register(String s, RegistryCallback registryCallback0) {
        this.mCallbacks.put(s, registryCallback0);
    }

    public void setDrawDebug(String s, int v) {
        RegistryCallback registryCallback0 = (RegistryCallback)this.mCallbacks.get(s);
        if(registryCallback0 != null) {
            registryCallback0.setDrawDebug(v);
        }
    }

    public void setLayoutInformationMode(String s, int v) {
        RegistryCallback registryCallback0 = (RegistryCallback)this.mCallbacks.get(s);
        if(registryCallback0 != null) {
            registryCallback0.setLayoutInformationMode(v);
        }
    }

    public void unregister(String s, RegistryCallback registryCallback0) {
        this.mCallbacks.remove(s);
    }

    public void updateContent(String s, String s1) {
        RegistryCallback registryCallback0 = (RegistryCallback)this.mCallbacks.get(s);
        if(registryCallback0 != null) {
            registryCallback0.onNewMotionScene(s1);
        }
    }

    public void updateDimensions(String s, int v, int v1) {
        RegistryCallback registryCallback0 = (RegistryCallback)this.mCallbacks.get(s);
        if(registryCallback0 != null) {
            registryCallback0.onDimensions(v, v1);
        }
    }

    public void updateProgress(String s, float f) {
        RegistryCallback registryCallback0 = (RegistryCallback)this.mCallbacks.get(s);
        if(registryCallback0 != null) {
            registryCallback0.onProgress(f);
        }
    }
}

