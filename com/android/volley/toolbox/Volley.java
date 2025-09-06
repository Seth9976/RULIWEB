package com.android.volley.toolbox;

import android.content.Context;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import java.io.File;

public class Volley {
    private static final String DEFAULT_CACHE_DIR = "volley";

    public static RequestQueue newRequestQueue(Context context0) {
        return Volley.newRequestQueue(context0, null);
    }

    private static RequestQueue newRequestQueue(Context context0, Network network0) {
        RequestQueue requestQueue0 = new RequestQueue(new DiskBasedCache(new FileSupplier() {
            private File cacheDir;

            {
                Context context0 = context0.getApplicationContext();  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.cacheDir = null;
            }

            @Override  // com.android.volley.toolbox.DiskBasedCache$FileSupplier
            public File get() {
                if(this.cacheDir == null) {
                    this.cacheDir = new File(context0.getApplicationContext().getCacheDir(), "volley");
                }
                return this.cacheDir;
            }
        }), network0);
        requestQueue0.start();
        return requestQueue0;
    }

    public static RequestQueue newRequestQueue(Context context0, BaseHttpStack baseHttpStack0) {
        return baseHttpStack0 == null ? Volley.newRequestQueue(context0, new BasicNetwork(new HurlStack())) : Volley.newRequestQueue(context0, new BasicNetwork(baseHttpStack0));
    }

    @Deprecated
    public static RequestQueue newRequestQueue(Context context0, HttpStack httpStack0) {
        return httpStack0 == null ? Volley.newRequestQueue(context0, null) : Volley.newRequestQueue(context0, new BasicNetwork(httpStack0));
    }
}

