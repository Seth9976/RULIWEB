package androidx.appcompat.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import androidx.appcompat.R.style;

public class ContextThemeWrapper extends ContextWrapper {
    private LayoutInflater mInflater;
    private Configuration mOverrideConfiguration;
    private Resources mResources;
    private Resources.Theme mTheme;
    private int mThemeResource;
    private static Configuration sEmptyConfig;

    public ContextThemeWrapper() {
        super(null);
    }

    public ContextThemeWrapper(Context context0, int v) {
        super(context0);
        this.mThemeResource = v;
    }

    public ContextThemeWrapper(Context context0, Resources.Theme resources$Theme0) {
        super(context0);
        this.mTheme = resources$Theme0;
    }

    public void applyOverrideConfiguration(Configuration configuration0) {
        if(this.mResources != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if(this.mOverrideConfiguration != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.mOverrideConfiguration = new Configuration(configuration0);
    }

    @Override  // android.content.ContextWrapper
    protected void attachBaseContext(Context context0) {
        super.attachBaseContext(context0);
    }

    @Override  // android.content.ContextWrapper
    public AssetManager getAssets() {
        return this.getResources().getAssets();
    }

    @Override  // android.content.ContextWrapper
    public Resources getResources() {
        return this.getResourcesInternal();
    }

    private Resources getResourcesInternal() {
        if(this.mResources == null) {
            if(this.mOverrideConfiguration != null && (Build.VERSION.SDK_INT < 26 || !ContextThemeWrapper.isEmptyConfiguration(this.mOverrideConfiguration))) {
                this.mResources = this.createConfigurationContext(this.mOverrideConfiguration).getResources();
                return this.mResources;
            }
            this.mResources = super.getResources();
        }
        return this.mResources;
    }

    @Override  // android.content.ContextWrapper
    public Object getSystemService(String s) {
        if("layout_inflater".equals(s)) {
            if(this.mInflater == null) {
                this.mInflater = LayoutInflater.from(this.getBaseContext()).cloneInContext(this);
            }
            return this.mInflater;
        }
        return this.getBaseContext().getSystemService(s);
    }

    @Override  // android.content.ContextWrapper
    public Resources.Theme getTheme() {
        Resources.Theme resources$Theme0 = this.mTheme;
        if(resources$Theme0 != null) {
            return resources$Theme0;
        }
        if(this.mThemeResource == 0) {
            this.mThemeResource = style.Theme_AppCompat_Light;
        }
        this.initializeTheme();
        return this.mTheme;
    }

    public int getThemeResId() {
        return this.mThemeResource;
    }

    private void initializeTheme() {
        boolean z = this.mTheme == null;
        if(z) {
            this.mTheme = this.getResources().newTheme();
            Resources.Theme resources$Theme0 = this.getBaseContext().getTheme();
            if(resources$Theme0 != null) {
                this.mTheme.setTo(resources$Theme0);
            }
        }
        this.onApplyThemeResource(this.mTheme, this.mThemeResource, z);
    }

    private static boolean isEmptyConfiguration(Configuration configuration0) {
        if(configuration0 == null) {
            return true;
        }
        if(ContextThemeWrapper.sEmptyConfig == null) {
            Configuration configuration1 = new Configuration();
            configuration1.fontScale = 0.0f;
            ContextThemeWrapper.sEmptyConfig = configuration1;
        }
        return configuration0.equals(ContextThemeWrapper.sEmptyConfig);
    }

    protected void onApplyThemeResource(Resources.Theme resources$Theme0, int v, boolean z) {
        resources$Theme0.applyStyle(v, true);
    }

    @Override  // android.content.ContextWrapper
    public void setTheme(int v) {
        if(this.mThemeResource != v) {
            this.mThemeResource = v;
            this.initializeTheme();
        }
    }
}

