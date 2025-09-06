package com.ruliweb.www.ruliapp;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;

public final class SettingsActivity.FavorFragment.getFavoriteList.1..ExternalSyntheticLambda0 implements OnPreferenceChangeListener {
    public final RestModule f$0;
    public final SharedPreferences f$1;
    public final FavorFragment f$2;
    public final Context f$3;
    public final SwitchPreference f$4;

    public SettingsActivity.FavorFragment.getFavoriteList.1..ExternalSyntheticLambda0(RestModule restModule0, SharedPreferences sharedPreferences0, FavorFragment settingsActivity$FavorFragment0, Context context0, SwitchPreference switchPreference0) {
        this.f$0 = restModule0;
        this.f$1 = sharedPreferences0;
        this.f$2 = settingsActivity$FavorFragment0;
        this.f$3 = context0;
        this.f$4 = switchPreference0;
    }

    @Override  // androidx.preference.Preference$OnPreferenceChangeListener
    public final boolean onPreferenceChange(Preference preference0, Object object0) {
        return com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.$r8$lambda$8xIcIRwEqngeavQcdZCaVYKsIzQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, preference0, object0);
    }
}

