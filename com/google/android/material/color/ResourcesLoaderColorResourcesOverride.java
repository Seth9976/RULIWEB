package com.google.android.material.color;

import android.content.Context;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import com.google.android.material.R.style;
import java.util.Map;

class ResourcesLoaderColorResourcesOverride implements ColorResourcesOverride {
    static class ResourcesLoaderColorResourcesOverrideSingleton {
        private static final ResourcesLoaderColorResourcesOverride INSTANCE;

        static {
            ResourcesLoaderColorResourcesOverrideSingleton.INSTANCE = new ResourcesLoaderColorResourcesOverride(null);
        }

        static ResourcesLoaderColorResourcesOverride access$000() {
            return ResourcesLoaderColorResourcesOverrideSingleton.INSTANCE;
        }
    }

    private ResourcesLoaderColorResourcesOverride() {
    }

    ResourcesLoaderColorResourcesOverride(com.google.android.material.color.ResourcesLoaderColorResourcesOverride.1 resourcesLoaderColorResourcesOverride$10) {
    }

    @Override  // com.google.android.material.color.ColorResourcesOverride
    public boolean applyIfPossible(Context context0, Map map0) {
        if(ResourcesLoaderUtils.addResourcesLoaderToContext(context0, map0)) {
            ThemeUtils.applyThemeOverlay(context0, style.ThemeOverlay_Material3_PersonalizedColors);
            return true;
        }
        return false;
    }

    static ColorResourcesOverride getInstance() {
        return ResourcesLoaderColorResourcesOverrideSingleton.access$000();
    }

    @Override  // com.google.android.material.color.ColorResourcesOverride
    public Context wrapContextIfPossible(Context context0, Map map0) {
        Context context1 = new ContextThemeWrapper(context0, style.ThemeOverlay_Material3_PersonalizedColors);
        ((ContextThemeWrapper)context1).applyOverrideConfiguration(new Configuration());
        return ResourcesLoaderUtils.addResourcesLoaderToContext(context1, map0) ? context1 : context0;
    }

    class com.google.android.material.color.ResourcesLoaderColorResourcesOverride.1 {
    }

}

