package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import androidx.tracing.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AppInitializer {
    private static final String SECTION_NAME = "Startup";
    final Context mContext;
    final Set mDiscovered;
    final Map mInitialized;
    private static volatile AppInitializer sInstance;
    private static final Object sLock;

    static {
        AppInitializer.sLock = new Object();
    }

    AppInitializer(Context context0) {
        this.mContext = context0.getApplicationContext();
        this.mDiscovered = new HashSet();
        this.mInitialized = new HashMap();
    }

    void discoverAndInitialize() {
        try {
            Trace.beginSection("Startup");
            ComponentName componentName0 = new ComponentName("com.ruliweb.www.ruliapp", "androidx.startup.InitializationProvider");
            this.discoverAndInitialize(this.mContext.getPackageManager().getProviderInfo(componentName0, 0x80).metaData);
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            throw new StartupException(packageManager$NameNotFoundException0);
        }
        finally {
            Trace.endSection();
        }
    }

    void discoverAndInitialize(Bundle bundle0) {
        String s = this.mContext.getString(string.androidx_startup);
        if(bundle0 == null) {
            return;
        }
        else {
            try {
                HashSet hashSet0 = new HashSet();
                for(Object object0: bundle0.keySet()) {
                    String s1 = (String)object0;
                    if(s.equals(bundle0.getString(s1, null))) {
                        Class class0 = Class.forName(s1);
                        if(Initializer.class.isAssignableFrom(class0)) {
                            this.mDiscovered.add(class0);
                        }
                    }
                }
                for(Object object1: this.mDiscovered) {
                    this.doInitialize(((Class)object1), hashSet0);
                }
                return;
            }
            catch(ClassNotFoundException classNotFoundException0) {
            }
        }
        throw new StartupException(classNotFoundException0);
    }

    private Object doInitialize(Class class0, Set set0) {
        Object object1;
        boolean z = !Trace.isEnabled();
        try {
            if(!z) {
                Trace.beginSection(class0.getSimpleName());
            }
            if(!set0.contains(class0)) {
                if(this.mInitialized.containsKey(class0)) {
                    object1 = this.mInitialized.get(class0);
                }
                else {
                    set0.add(class0);
                    try {
                        Initializer initializer0 = (Initializer)class0.getDeclaredConstructor(null).newInstance(null);
                        List list0 = initializer0.dependencies();
                        if(!list0.isEmpty()) {
                            for(Object object0: list0) {
                                Class class1 = (Class)object0;
                                if(!this.mInitialized.containsKey(class1)) {
                                    this.doInitialize(class1, set0);
                                }
                            }
                        }
                        object1 = initializer0.create(this.mContext);
                        set0.remove(class0);
                        this.mInitialized.put(class0, object1);
                    }
                    catch(Throwable throwable0) {
                        throw new StartupException(throwable0);
                    }
                }
                return object1;
            }
        }
        finally {
            Trace.endSection();
        }
        throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", class0.getName()));
    }

    Object doInitialize(Class class0) {
        synchronized(AppInitializer.sLock) {
            Object object1 = this.mInitialized.get(class0);
            if(object1 == null) {
                object1 = this.doInitialize(class0, new HashSet());
            }
            return object1;
        }
    }

    public static AppInitializer getInstance(Context context0) {
        if(AppInitializer.sInstance == null) {
            Object object0 = AppInitializer.sLock;
            synchronized(object0) {
                if(AppInitializer.sInstance == null) {
                    AppInitializer.sInstance = new AppInitializer(context0);
                }
                return AppInitializer.sInstance;
            }
        }
        return AppInitializer.sInstance;
    }

    public Object initializeComponent(Class class0) {
        return this.doInitialize(class0);
    }

    public boolean isEagerlyInitialized(Class class0) {
        return this.mDiscovered.contains(class0);
    }

    static void setDelegate(AppInitializer appInitializer0) {
        synchronized(AppInitializer.sLock) {
            AppInitializer.sInstance = appInitializer0;
        }
    }
}

