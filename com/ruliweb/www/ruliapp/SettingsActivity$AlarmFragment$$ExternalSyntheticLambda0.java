package com.ruliweb.www.ruliapp;

import android.content.Context;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.SharedPreferences;

public final class SettingsActivity.AlarmFragment..ExternalSyntheticLambda0 implements SharedPreferences.OnSharedPreferenceChangeListener {
    public final Context f$0;
    public final SharedPreferences f$1;
    public final AlarmFragment f$2;

    public SettingsActivity.AlarmFragment..ExternalSyntheticLambda0(Context context0, SharedPreferences sharedPreferences0, AlarmFragment settingsActivity$AlarmFragment0) {
        this.f$0 = context0;
        this.f$1 = sharedPreferences0;
        this.f$2 = settingsActivity$AlarmFragment0;
    }

    @Override  // android.content.SharedPreferences$OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences0, String s) {
        AlarmFragment.$r8$lambda$txE0wwL3RUyNG6BtEVVtfsAr4Kk(this.f$0, this.f$1, this.f$2, sharedPreferences0, s);
    }
}

