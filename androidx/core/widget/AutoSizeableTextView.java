package androidx.core.widget;

import android.os.Build.VERSION;

public interface AutoSizeableTextView {
    @Deprecated
    public static final boolean PLATFORM_SUPPORTS_AUTOSIZE;

    static {
        AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE = Build.VERSION.SDK_INT >= 27;
    }

    int getAutoSizeMaxTextSize();

    int getAutoSizeMinTextSize();

    int getAutoSizeStepGranularity();

    int[] getAutoSizeTextAvailableSizes();

    int getAutoSizeTextType();

    void setAutoSizeTextTypeUniformWithConfiguration(int arg1, int arg2, int arg3, int arg4) throws IllegalArgumentException;

    void setAutoSizeTextTypeUniformWithPresetSizes(int[] arg1, int arg2) throws IllegalArgumentException;

    void setAutoSizeTextTypeWithDefaults(int arg1);
}

