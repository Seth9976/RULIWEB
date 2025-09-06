package com.google.android.material.color;

import android.content.Context;
import android.content.res.loader.ResourcesLoader;
import java.util.Map;

final class ResourcesLoaderUtils {
    static boolean addResourcesLoaderToContext(Context context0, Map map0) {
        ResourcesLoader resourcesLoader0 = ColorResourcesLoaderCreator.create(context0, map0);
        if(resourcesLoader0 != null) {
            context0.getResources().addLoaders(new ResourcesLoader[]{resourcesLoader0});
            return true;
        }
        return false;
    }

    static boolean isColorResource(int v) {
        return 28 <= v && v <= 0x1F;
    }
}

