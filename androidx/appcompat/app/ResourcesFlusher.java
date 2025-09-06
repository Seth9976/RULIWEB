package androidx.appcompat.app;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher {
    private static final String TAG = "ResourcesFlusher";
    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    static void flush(Resources resources0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return;
        }
        if(Build.VERSION.SDK_INT >= 24) {
            ResourcesFlusher.flushNougats(resources0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 23) {
            ResourcesFlusher.flushMarshmallows(resources0);
            return;
        }
        ResourcesFlusher.flushLollipops(resources0);
    }

    private static void flushLollipops(Resources resources0) {
        Map map0;
        if(!ResourcesFlusher.sDrawableCacheFieldFetched) {
            try {
                Field field0 = Resources.class.getDeclaredField("mDrawableCache");
                ResourcesFlusher.sDrawableCacheField = field0;
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", noSuchFieldException0);
            }
            ResourcesFlusher.sDrawableCacheFieldFetched = true;
        }
        Field field1 = ResourcesFlusher.sDrawableCacheField;
        if(field1 != null) {
            try {
                map0 = (Map)field1.get(resources0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", illegalAccessException0);
                map0 = null;
            }
            if(map0 != null) {
                map0.clear();
            }
        }
    }

    private static void flushMarshmallows(Resources resources0) {
        Object object0;
        if(!ResourcesFlusher.sDrawableCacheFieldFetched) {
            try {
                Field field0 = Resources.class.getDeclaredField("mDrawableCache");
                ResourcesFlusher.sDrawableCacheField = field0;
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", noSuchFieldException0);
            }
            ResourcesFlusher.sDrawableCacheFieldFetched = true;
        }
        Field field1 = ResourcesFlusher.sDrawableCacheField;
        if(field1 == null) {
            object0 = null;
        }
        else {
            try {
                object0 = field1.get(resources0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", illegalAccessException0);
                object0 = null;
            }
        }
        if(object0 == null) {
            return;
        }
        ResourcesFlusher.flushThemedResourcesCache(object0);
    }

    private static void flushNougats(Resources resources0) {
        Object object1;
        Object object0;
        if(!ResourcesFlusher.sResourcesImplFieldFetched) {
            try {
                Field field0 = Resources.class.getDeclaredField("mResourcesImpl");
                ResourcesFlusher.sResourcesImplField = field0;
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", noSuchFieldException0);
            }
            ResourcesFlusher.sResourcesImplFieldFetched = true;
        }
        Field field1 = ResourcesFlusher.sResourcesImplField;
        if(field1 != null) {
            try {
                object0 = null;
                object1 = field1.get(resources0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", illegalAccessException0);
                object1 = null;
            }
            if(object1 != null) {
                if(!ResourcesFlusher.sDrawableCacheFieldFetched) {
                    try {
                        Field field2 = object1.getClass().getDeclaredField("mDrawableCache");
                        ResourcesFlusher.sDrawableCacheField = field2;
                        field2.setAccessible(true);
                    }
                    catch(NoSuchFieldException noSuchFieldException1) {
                        Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", noSuchFieldException1);
                    }
                    ResourcesFlusher.sDrawableCacheFieldFetched = true;
                }
                Field field3 = ResourcesFlusher.sDrawableCacheField;
                if(field3 != null) {
                    try {
                        object0 = field3.get(object1);
                    }
                    catch(IllegalAccessException illegalAccessException1) {
                        Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", illegalAccessException1);
                    }
                }
                if(object0 != null) {
                    ResourcesFlusher.flushThemedResourcesCache(object0);
                }
            }
        }
    }

    private static void flushThemedResourcesCache(Object object0) {
        LongSparseArray longSparseArray0;
        if(!ResourcesFlusher.sThemedResourceCacheClazzFetched) {
            try {
                ResourcesFlusher.sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
            }
            catch(ClassNotFoundException classNotFoundException0) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", classNotFoundException0);
            }
            ResourcesFlusher.sThemedResourceCacheClazzFetched = true;
        }
        Class class0 = ResourcesFlusher.sThemedResourceCacheClazz;
        if(class0 != null) {
            if(!ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesFieldFetched) {
                try {
                    Field field0 = class0.getDeclaredField("mUnthemedEntries");
                    ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesField = field0;
                    field0.setAccessible(true);
                }
                catch(NoSuchFieldException noSuchFieldException0) {
                    Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", noSuchFieldException0);
                }
                ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
            }
            Field field1 = ResourcesFlusher.sThemedResourceCache_mUnthemedEntriesField;
            if(field1 != null) {
                try {
                    longSparseArray0 = (LongSparseArray)field1.get(object0);
                }
                catch(IllegalAccessException illegalAccessException0) {
                    Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", illegalAccessException0);
                    longSparseArray0 = null;
                }
                if(longSparseArray0 != null) {
                    longSparseArray0.clear();
                }
            }
        }
    }
}

