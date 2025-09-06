package androidx.browser.customtabs;

import android.os.Bundle;

public final class CustomTabColorSchemeParams {
    public static final class Builder {
        private Integer mNavigationBarColor;
        private Integer mNavigationBarDividerColor;
        private Integer mSecondaryToolbarColor;
        private Integer mToolbarColor;

        public CustomTabColorSchemeParams build() {
            return new CustomTabColorSchemeParams(this.mToolbarColor, this.mSecondaryToolbarColor, this.mNavigationBarColor, this.mNavigationBarDividerColor);
        }

        public Builder setNavigationBarColor(int v) {
            this.mNavigationBarColor = (int)(v | 0xFF000000);
            return this;
        }

        public Builder setNavigationBarDividerColor(int v) {
            this.mNavigationBarDividerColor = v;
            return this;
        }

        public Builder setSecondaryToolbarColor(int v) {
            this.mSecondaryToolbarColor = v;
            return this;
        }

        public Builder setToolbarColor(int v) {
            this.mToolbarColor = (int)(v | 0xFF000000);
            return this;
        }
    }

    public final Integer navigationBarColor;
    public final Integer navigationBarDividerColor;
    public final Integer secondaryToolbarColor;
    public final Integer toolbarColor;

    CustomTabColorSchemeParams(Integer integer0, Integer integer1, Integer integer2, Integer integer3) {
        this.toolbarColor = integer0;
        this.secondaryToolbarColor = integer1;
        this.navigationBarColor = integer2;
        this.navigationBarDividerColor = integer3;
    }

    static CustomTabColorSchemeParams fromBundle(Bundle bundle0) {
        if(bundle0 == null) {
            bundle0 = new Bundle(0);
        }
        return new CustomTabColorSchemeParams(((Integer)bundle0.get("android.support.customtabs.extra.TOOLBAR_COLOR")), ((Integer)bundle0.get("android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR")), ((Integer)bundle0.get("androidx.browser.customtabs.extra.NAVIGATION_BAR_COLOR")), ((Integer)bundle0.get("androidx.browser.customtabs.extra.NAVIGATION_BAR_DIVIDER_COLOR")));
    }

    Bundle toBundle() {
        Bundle bundle0 = new Bundle();
        Integer integer0 = this.toolbarColor;
        if(integer0 != null) {
            bundle0.putInt("android.support.customtabs.extra.TOOLBAR_COLOR", ((int)integer0));
        }
        Integer integer1 = this.secondaryToolbarColor;
        if(integer1 != null) {
            bundle0.putInt("android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR", ((int)integer1));
        }
        Integer integer2 = this.navigationBarColor;
        if(integer2 != null) {
            bundle0.putInt("androidx.browser.customtabs.extra.NAVIGATION_BAR_COLOR", ((int)integer2));
        }
        Integer integer3 = this.navigationBarDividerColor;
        if(integer3 != null) {
            bundle0.putInt("androidx.browser.customtabs.extra.NAVIGATION_BAR_DIVIDER_COLOR", ((int)integer3));
        }
        return bundle0;
    }

    // 去混淆评级： 低(40)
    CustomTabColorSchemeParams withDefaults(CustomTabColorSchemeParams customTabColorSchemeParams0) {
        return new CustomTabColorSchemeParams((this.toolbarColor == null ? customTabColorSchemeParams0.toolbarColor : this.toolbarColor), (this.secondaryToolbarColor == null ? customTabColorSchemeParams0.secondaryToolbarColor : this.secondaryToolbarColor), (this.navigationBarColor == null ? customTabColorSchemeParams0.navigationBarColor : this.navigationBarColor), (this.navigationBarDividerColor == null ? customTabColorSchemeParams0.navigationBarDividerColor : this.navigationBarDividerColor));
    }
}

