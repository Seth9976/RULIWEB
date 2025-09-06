package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;

public class PreferenceManager {
    public interface OnDisplayPreferenceDialogListener {
        void onDisplayPreferenceDialog(Preference arg1);
    }

    public interface OnNavigateToScreenListener {
        void onNavigateToScreen(PreferenceScreen arg1);
    }

    public interface OnPreferenceTreeClickListener {
        boolean onPreferenceTreeClick(Preference arg1);
    }

    public static abstract class PreferenceComparisonCallback {
        public abstract boolean arePreferenceContentsTheSame(Preference arg1, Preference arg2);

        public abstract boolean arePreferenceItemsTheSame(Preference arg1, Preference arg2);
    }

    public static class SimplePreferenceComparisonCallback extends PreferenceComparisonCallback {
        @Override  // androidx.preference.PreferenceManager$PreferenceComparisonCallback
        public boolean arePreferenceContentsTheSame(Preference preference0, Preference preference1) {
            if(preference0.getClass() != preference1.getClass()) {
                return false;
            }
            if(preference0 == preference1 && preference0.wasDetached()) {
                return false;
            }
            if(!TextUtils.equals(preference0.getTitle(), preference1.getTitle())) {
                return false;
            }
            if(!TextUtils.equals(preference0.getSummary(), preference1.getSummary())) {
                return false;
            }
            Drawable drawable0 = preference0.getIcon();
            Drawable drawable1 = preference1.getIcon();
            if(drawable0 != drawable1 && (drawable0 == null || !drawable0.equals(drawable1))) {
                return false;
            }
            if(preference0.isEnabled() != preference1.isEnabled()) {
                return false;
            }
            if(preference0.isSelectable() != preference1.isSelectable()) {
                return false;
            }
            return !(preference0 instanceof TwoStatePreference) || ((TwoStatePreference)preference0).isChecked() == ((TwoStatePreference)preference1).isChecked() ? !(preference0 instanceof DropDownPreference) || preference0 == preference1 : false;
        }

        @Override  // androidx.preference.PreferenceManager$PreferenceComparisonCallback
        public boolean arePreferenceItemsTheSame(Preference preference0, Preference preference1) {
            return preference0.getId() == preference1.getId();
        }
    }

    public static final String KEY_HAS_SET_DEFAULT_VALUES = "_has_set_default_values";
    private static final int STORAGE_DEFAULT = 0;
    private static final int STORAGE_DEVICE_PROTECTED = 1;
    private final Context mContext;
    private SharedPreferences.Editor mEditor;
    private long mNextId;
    private boolean mNoCommit;
    private OnDisplayPreferenceDialogListener mOnDisplayPreferenceDialogListener;
    private OnNavigateToScreenListener mOnNavigateToScreenListener;
    private OnPreferenceTreeClickListener mOnPreferenceTreeClickListener;
    private PreferenceComparisonCallback mPreferenceComparisonCallback;
    private PreferenceDataStore mPreferenceDataStore;
    private PreferenceScreen mPreferenceScreen;
    private SharedPreferences mSharedPreferences;
    private int mSharedPreferencesMode;
    private String mSharedPreferencesName;
    private int mStorage;

    public PreferenceManager(Context context0) {
        this.mNextId = 0L;
        this.mStorage = 0;
        this.mContext = context0;
        this.setSharedPreferencesName(PreferenceManager.getDefaultSharedPreferencesName(context0));
    }

    public PreferenceScreen createPreferenceScreen(Context context0) {
        PreferenceScreen preferenceScreen0 = new PreferenceScreen(context0, null);
        preferenceScreen0.onAttachedToHierarchy(this);
        return preferenceScreen0;
    }

    public Preference findPreference(CharSequence charSequence0) {
        return this.mPreferenceScreen == null ? null : this.mPreferenceScreen.findPreference(charSequence0);
    }

    public Context getContext() {
        return this.mContext;
    }

    public static SharedPreferences getDefaultSharedPreferences(Context context0) {
        return context0.getSharedPreferences(PreferenceManager.getDefaultSharedPreferencesName(context0), 0);
    }

    private static int getDefaultSharedPreferencesMode() [...] // Inlined contents

    // 去混淆评级： 低(20)
    private static String getDefaultSharedPreferencesName(Context context0) {
        return "com.ruliweb.www.ruliapp_preferences";
    }

    SharedPreferences.Editor getEditor() {
        if(this.mPreferenceDataStore != null) {
            return null;
        }
        if(this.mNoCommit) {
            if(this.mEditor == null) {
                this.mEditor = this.getSharedPreferences().edit();
            }
            return this.mEditor;
        }
        return this.getSharedPreferences().edit();
    }

    long getNextId() {
        long v;
        synchronized(this) {
            v = this.mNextId;
            this.mNextId = v + 1L;
        }
        return v;
    }

    public OnDisplayPreferenceDialogListener getOnDisplayPreferenceDialogListener() {
        return this.mOnDisplayPreferenceDialogListener;
    }

    public OnNavigateToScreenListener getOnNavigateToScreenListener() {
        return this.mOnNavigateToScreenListener;
    }

    public OnPreferenceTreeClickListener getOnPreferenceTreeClickListener() {
        return this.mOnPreferenceTreeClickListener;
    }

    public PreferenceComparisonCallback getPreferenceComparisonCallback() {
        return this.mPreferenceComparisonCallback;
    }

    public PreferenceDataStore getPreferenceDataStore() {
        return this.mPreferenceDataStore;
    }

    public PreferenceScreen getPreferenceScreen() {
        return this.mPreferenceScreen;
    }

    public SharedPreferences getSharedPreferences() {
        if(this.getPreferenceDataStore() != null) {
            return null;
        }
        if(this.mSharedPreferences == null) {
            this.mSharedPreferences = (this.mStorage == 1 ? ContextCompat.createDeviceProtectedStorageContext(this.mContext) : this.mContext).getSharedPreferences(this.mSharedPreferencesName, this.mSharedPreferencesMode);
        }
        return this.mSharedPreferences;
    }

    public int getSharedPreferencesMode() {
        return this.mSharedPreferencesMode;
    }

    public String getSharedPreferencesName() {
        return this.mSharedPreferencesName;
    }

    public PreferenceScreen inflateFromResource(Context context0, int v, PreferenceScreen preferenceScreen0) {
        this.setNoCommit(true);
        PreferenceScreen preferenceScreen1 = (PreferenceScreen)new PreferenceInflater(context0, this).inflate(v, preferenceScreen0);
        preferenceScreen1.onAttachedToHierarchy(this);
        this.setNoCommit(false);
        return preferenceScreen1;
    }

    public boolean isStorageDefault() {
        return Build.VERSION.SDK_INT < 24 || this.mStorage == 0;
    }

    public boolean isStorageDeviceProtected() {
        return Build.VERSION.SDK_INT >= 24 && this.mStorage == 1;
    }

    public static void setDefaultValues(Context context0, int v, boolean z) {
        PreferenceManager.setDefaultValues(context0, PreferenceManager.getDefaultSharedPreferencesName(context0), 0, v, z);
    }

    public static void setDefaultValues(Context context0, String s, int v, int v1, boolean z) {
        SharedPreferences sharedPreferences0 = context0.getSharedPreferences("_has_set_default_values", 0);
        if(!z && sharedPreferences0.getBoolean("_has_set_default_values", false)) {
            return;
        }
        PreferenceManager preferenceManager0 = new PreferenceManager(context0);
        preferenceManager0.setSharedPreferencesName(s);
        preferenceManager0.setSharedPreferencesMode(v);
        preferenceManager0.inflateFromResource(context0, v1, null);
        sharedPreferences0.edit().putBoolean("_has_set_default_values", true).apply();
    }

    private void setNoCommit(boolean z) {
        if(!z) {
            SharedPreferences.Editor sharedPreferences$Editor0 = this.mEditor;
            if(sharedPreferences$Editor0 != null) {
                sharedPreferences$Editor0.apply();
            }
        }
        this.mNoCommit = z;
    }

    public void setOnDisplayPreferenceDialogListener(OnDisplayPreferenceDialogListener preferenceManager$OnDisplayPreferenceDialogListener0) {
        this.mOnDisplayPreferenceDialogListener = preferenceManager$OnDisplayPreferenceDialogListener0;
    }

    public void setOnNavigateToScreenListener(OnNavigateToScreenListener preferenceManager$OnNavigateToScreenListener0) {
        this.mOnNavigateToScreenListener = preferenceManager$OnNavigateToScreenListener0;
    }

    public void setOnPreferenceTreeClickListener(OnPreferenceTreeClickListener preferenceManager$OnPreferenceTreeClickListener0) {
        this.mOnPreferenceTreeClickListener = preferenceManager$OnPreferenceTreeClickListener0;
    }

    public void setPreferenceComparisonCallback(PreferenceComparisonCallback preferenceManager$PreferenceComparisonCallback0) {
        this.mPreferenceComparisonCallback = preferenceManager$PreferenceComparisonCallback0;
    }

    public void setPreferenceDataStore(PreferenceDataStore preferenceDataStore0) {
        this.mPreferenceDataStore = preferenceDataStore0;
    }

    public boolean setPreferences(PreferenceScreen preferenceScreen0) {
        PreferenceScreen preferenceScreen1 = this.mPreferenceScreen;
        if(preferenceScreen0 != preferenceScreen1) {
            if(preferenceScreen1 != null) {
                preferenceScreen1.onDetached();
            }
            this.mPreferenceScreen = preferenceScreen0;
            return true;
        }
        return false;
    }

    public void setSharedPreferencesMode(int v) {
        this.mSharedPreferencesMode = v;
        this.mSharedPreferences = null;
    }

    public void setSharedPreferencesName(String s) {
        this.mSharedPreferencesName = s;
        this.mSharedPreferences = null;
    }

    public void setStorageDefault() {
        if(Build.VERSION.SDK_INT >= 24) {
            this.mStorage = 0;
            this.mSharedPreferences = null;
        }
    }

    public void setStorageDeviceProtected() {
        if(Build.VERSION.SDK_INT >= 24) {
            this.mStorage = 1;
            this.mSharedPreferences = null;
        }
    }

    boolean shouldCommit() {
        return !this.mNoCommit;
    }

    public void showDialog(Preference preference0) {
        OnDisplayPreferenceDialogListener preferenceManager$OnDisplayPreferenceDialogListener0 = this.mOnDisplayPreferenceDialogListener;
        if(preferenceManager$OnDisplayPreferenceDialogListener0 != null) {
            preferenceManager$OnDisplayPreferenceDialogListener0.onDisplayPreferenceDialog(preference0);
        }
    }
}

