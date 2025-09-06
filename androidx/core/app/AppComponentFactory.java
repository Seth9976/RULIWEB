package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import java.lang.reflect.InvocationTargetException;

public class AppComponentFactory extends android.app.AppComponentFactory {
    @Override  // android.app.AppComponentFactory
    public final Activity instantiateActivity(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Activity)CoreComponentFactory.checkCompatWrapper(this.instantiateActivityCompat(classLoader0, s, intent0));
    }

    public Activity instantiateActivityCompat(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (Activity)Class.forName(s, false, classLoader0).asSubclass(Activity.class).getDeclaredConstructor(null).newInstance(null);
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }

    @Override  // android.app.AppComponentFactory
    public final Application instantiateApplication(ClassLoader classLoader0, String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Application)CoreComponentFactory.checkCompatWrapper(this.instantiateApplicationCompat(classLoader0, s));
    }

    public Application instantiateApplicationCompat(ClassLoader classLoader0, String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (Application)Class.forName(s, false, classLoader0).asSubclass(Application.class).getDeclaredConstructor(null).newInstance(null);
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }

    @Override  // android.app.AppComponentFactory
    public final ContentProvider instantiateProvider(ClassLoader classLoader0, String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ContentProvider)CoreComponentFactory.checkCompatWrapper(this.instantiateProviderCompat(classLoader0, s));
    }

    public ContentProvider instantiateProviderCompat(ClassLoader classLoader0, String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (ContentProvider)Class.forName(s, false, classLoader0).asSubclass(ContentProvider.class).getDeclaredConstructor(null).newInstance(null);
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }

    @Override  // android.app.AppComponentFactory
    public final BroadcastReceiver instantiateReceiver(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (BroadcastReceiver)CoreComponentFactory.checkCompatWrapper(this.instantiateReceiverCompat(classLoader0, s, intent0));
    }

    public BroadcastReceiver instantiateReceiverCompat(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (BroadcastReceiver)Class.forName(s, false, classLoader0).asSubclass(BroadcastReceiver.class).getDeclaredConstructor(null).newInstance(null);
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }

    @Override  // android.app.AppComponentFactory
    public final Service instantiateService(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Service)CoreComponentFactory.checkCompatWrapper(this.instantiateServiceCompat(classLoader0, s, intent0));
    }

    public Service instantiateServiceCompat(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (Service)Class.forName(s, false, classLoader0).asSubclass(Service.class).getDeclaredConstructor(null).newInstance(null);
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }
}

