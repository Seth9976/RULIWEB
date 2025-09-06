package com.ruliweb.www.ruliapp;

import android.content.ClipData.Item;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View.DragShadowBuilder;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat.OnPreferenceStartFragmentCallback;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.VolleyError;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002:\u0004\u0017\u0018\u0019\u001AB\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\rH\u0015J\u0018\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0013H\u0016J\u0010\u0010\u0014\u001A\u00020\u000B2\u0006\u0010\u0015\u001A\u00020\rH\u0014J\b\u0010\u0016\u001A\u00020\u000FH\u0016R\u001A\u0010\u0004\u001A\u00020\u0005X\u0086.¢\u0006\u000E\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u001B"}, d2 = {"Lcom/ruliweb/www/ruliapp/SettingsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroidx/preference/PreferenceFragmentCompat$OnPreferenceStartFragmentCallback;", "()V", "activityTitle", "Landroid/widget/TextView;", "getActivityTitle", "()Landroid/widget/TextView;", "setActivityTitle", "(Landroid/widget/TextView;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPreferenceStartFragment", "", "caller", "Landroidx/preference/PreferenceFragmentCompat;", "pref", "Landroidx/preference/Preference;", "onSaveInstanceState", "outState", "onSupportNavigateUp", "AlarmFragment", "ButtonSettingFragment", "FavorFragment", "HeaderFragment", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class SettingsActivity extends AppCompatActivity implements OnPreferenceStartFragmentCallback {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001C\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\b2\b\u0010\t\u001A\u0004\u0018\u00010\nH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lcom/ruliweb/www/ruliapp/SettingsActivity$AlarmFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "()V", "activity", "Landroidx/fragment/app/FragmentActivity;", "onCreatePreferences", "", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class AlarmFragment extends PreferenceFragmentCompat {
        private FragmentActivity activity;

        // 检测为 Lambda 实现
        public static void $r8$lambda$txE0wwL3RUyNG6BtEVVtfsAr4Kk(Context context0, SharedPreferences sharedPreferences0, AlarmFragment settingsActivity$AlarmFragment0, SharedPreferences sharedPreferences1, String s) [...]

        @Override  // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle0, String s) {
            Context context0 = this.requireActivity().getBaseContext();
            FragmentActivity fragmentActivity0 = this.requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivity0, "requireActivity(...)");
            this.activity = fragmentActivity0;
            this.setPreferencesFromResource(0x7F150000, s);  // xml:alarm_preferences
            SwitchPreferenceCompat switchPreferenceCompat0 = (SwitchPreferenceCompat)this.findPreference("message_type");
            SwitchPreferenceCompat switchPreferenceCompat1 = (SwitchPreferenceCompat)this.findPreference("comment_type");
            SwitchPreferenceCompat switchPreferenceCompat2 = (SwitchPreferenceCompat)this.findPreference("favorite_type");
            SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(context0);
            String s1 = sharedPreferences0.getString("current_member_srl", "");
            Intrinsics.checkNotNull(s1);
            if(Intrinsics.areEqual(s1, "0")) {
            label_15:
                Intrinsics.checkNotNull(switchPreferenceCompat0);
                switchPreferenceCompat0.setEnabled(false);
                Intrinsics.checkNotNull(switchPreferenceCompat1);
                switchPreferenceCompat1.setEnabled(false);
                Intrinsics.checkNotNull(switchPreferenceCompat2);
                switchPreferenceCompat2.setEnabled(false);
            }
            else {
                String s2 = sharedPreferences0.getString("current_member_srl", "");
                Intrinsics.checkNotNull(s2);
                if(Intrinsics.areEqual(s2, "")) {
                    goto label_15;
                }
            }
            sharedPreferences0.registerOnSharedPreferenceChangeListener((SharedPreferences sharedPreferences1, String s) -> AlarmFragment.onCreatePreferences$lambda$0(context0, sharedPreferences0, this, sharedPreferences1, s));
        }

        private static final void onCreatePreferences$lambda$0(Context context0, SharedPreferences sharedPreferences0, AlarmFragment settingsActivity$AlarmFragment0, SharedPreferences sharedPreferences1, String s) {
            Intrinsics.checkNotNullParameter(settingsActivity$AlarmFragment0, "this$0");
            if(s != null && Build.VERSION.SDK_INT >= 33 && ContextCompat.checkSelfPermission(context0, "android.permission.POST_NOTIFICATIONS") != 0) {
                sharedPreferences0.edit().putBoolean(s, false).apply();
                FragmentActivity fragmentActivity0 = settingsActivity$AlarmFragment0.activity;
                if(fragmentActivity0 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activity");
                    fragmentActivity0 = null;
                }
                Toast.makeText(fragmentActivity0, "앱 설정에서 알림 권한을 허용해주시기 바랍니다.", 0).show();
                FragmentActivity fragmentActivity1 = settingsActivity$AlarmFragment0.activity;
                if(fragmentActivity1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activity");
                    fragmentActivity1 = null;
                }
                fragmentActivity1.finish();
                FragmentActivity fragmentActivity2 = settingsActivity$AlarmFragment0.activity;
                if(fragmentActivity2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activity");
                    fragmentActivity2 = null;
                }
                settingsActivity$AlarmFragment0.startActivity(fragmentActivity2.getIntent());
                Intent intent0 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent0.addFlags(0x10000000);
                Uri uri0 = Uri.fromParts("package", "com.ruliweb.www.ruliapp", null);
                Intrinsics.checkNotNullExpressionValue(uri0, "fromParts(...)");
                intent0.setData(uri0);
                settingsActivity$AlarmFragment0.startActivity(intent0);
                return;
            }
            Intrinsics.checkNotNull(context0);
            String s1 = sharedPreferences0.getString("current_member_srl", "");
            Intrinsics.checkNotNull(s1);
            boolean z = Intrinsics.areEqual(s1, "0");
            Utils.Companion.setAlarmOptions(context0, !z);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000EB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001A\u00020\bJ\u001C\u0010\t\u001A\u00020\b2\b\u0010\n\u001A\u0004\u0018\u00010\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\rH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lcom/ruliweb/www/ruliapp/SettingsActivity$ButtonSettingFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "()V", "activity", "Landroidx/fragment/app/FragmentActivity;", "btnFragment", "Lcom/ruliweb/www/ruliapp/ButtonContainer;", "initButtonSettings", "", "onCreatePreferences", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "", "MyDragShadowBuilder", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class ButtonSettingFragment extends PreferenceFragmentCompat {
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\rH\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lcom/ruliweb/www/ruliapp/SettingsActivity$ButtonSettingFragment$MyDragShadowBuilder;", "Landroid/view/View$DragShadowBuilder;", "v", "Landroid/view/View;", "(Landroid/view/View;)V", "shadow", "Landroid/graphics/drawable/ColorDrawable;", "onDrawShadow", "", "canvas", "Landroid/graphics/Canvas;", "onProvideShadowMetrics", "size", "Landroid/graphics/Point;", "touch", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        static final class MyDragShadowBuilder extends View.DragShadowBuilder {
            private final ColorDrawable shadow;

            public MyDragShadowBuilder(View view0) {
                Intrinsics.checkNotNullParameter(view0, "v");
                super(view0);
                this.shadow = new ColorDrawable(0xFFCCCCCC);
            }

            @Override  // android.view.View$DragShadowBuilder
            public void onDrawShadow(Canvas canvas0) {
                Intrinsics.checkNotNullParameter(canvas0, "canvas");
                this.shadow.draw(canvas0);
            }

            @Override  // android.view.View$DragShadowBuilder
            public void onProvideShadowMetrics(Point point0, Point point1) {
                Intrinsics.checkNotNullParameter(point0, "size");
                Intrinsics.checkNotNullParameter(point1, "touch");
                int v = this.getView().getWidth();
                int v1 = this.getView().getHeight();
                this.shadow.setBounds(0, 0, v / 2, v1 / 2);
                point0.set(v / 2, v1 / 2);
                point1.set(v / 2 / 2, v1 / 2 / 2);
            }
        }

        private FragmentActivity activity;
        private ButtonContainer btnFragment;

        // 检测为 Lambda 实现
        public static boolean $r8$lambda$6xiyAopCLOC2B2mjqxim_POWcnw(Button button0, View view0) [...]

        // 检测为 Lambda 实现
        public static boolean $r8$lambda$GHKSEpcCHSCHPOewNsf94wHGL6k(ButtonSettingFragment settingsActivity$ButtonSettingFragment0, View view0, DragEvent dragEvent0) [...]

        public final void initButtonSettings() {
            ButtonContainer buttonContainer0 = this.btnFragment;
            if(buttonContainer0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnFragment");
                buttonContainer0 = null;
            }
            Button[] arr_button = buttonContainer0.getButtons();
            for(int v = 0; v < arr_button.length; ++v) {
                Button button0 = arr_button[v];
                button0.setOnLongClickListener((View view0) -> ButtonSettingFragment.initButtonSettings$lambda$2$lambda$0(button0, view0));
                button0.setOnDragListener((View view0, DragEvent dragEvent0) -> ButtonSettingFragment.initButtonSettings$lambda$2$lambda$1(this, view0, dragEvent0));
            }
        }

        private static final boolean initButtonSettings$lambda$2$lambda$0(Button button0, View view0) {
            Intrinsics.checkNotNullParameter(button0, "$element");
            Object object0 = view0.getTag();
            CharSequence charSequence0 = null;
            ClipData.Item clipData$Item0 = new ClipData.Item((object0 instanceof CharSequence ? ((CharSequence)object0) : null));
            Object object1 = view0.getTag();
            if(object1 instanceof CharSequence) {
                charSequence0 = (CharSequence)object1;
            }
            new ClipData(charSequence0, new String[]{"text/plain"}, clipData$Item0);
            new MyDragShadowBuilder(button0);
            return true;
        }

        private static final boolean initButtonSettings$lambda$2$lambda$1(ButtonSettingFragment settingsActivity$ButtonSettingFragment0, View view0, DragEvent dragEvent0) {
            Toast toast0;
            Intrinsics.checkNotNullParameter(settingsActivity$ButtonSettingFragment0, "this$0");
            ImageButton imageButton0 = null;
            switch(dragEvent0.getAction()) {
                case 1: {
                    if(dragEvent0.getClipDescription().hasMimeType("text/plain")) {
                        if(view0 instanceof ImageButton) {
                            imageButton0 = (ImageButton)view0;
                        }
                        if(imageButton0 != null) {
                            imageButton0.setColorFilter(0xFF0000FF);
                        }
                        view0.invalidate();
                        return true;
                    }
                    return false;
                }
                case 2: {
                    break;
                }
                case 3: {
                    ClipData.Item clipData$Item0 = dragEvent0.getClipData().getItemAt(0);
                    Intrinsics.checkNotNullExpressionValue(clipData$Item0, "getItemAt(...)");
                    CharSequence charSequence0 = clipData$Item0.getText();
                    FragmentActivity fragmentActivity2 = settingsActivity$ButtonSettingFragment0.activity;
                    if(fragmentActivity2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activity");
                        fragmentActivity2 = null;
                    }
                    Toast.makeText(fragmentActivity2, "Dragged data is " + charSequence0, 1).show();
                    if(view0 instanceof ImageButton) {
                        imageButton0 = (ImageButton)view0;
                    }
                    if(imageButton0 != null) {
                        imageButton0.clearColorFilter();
                    }
                    view0.invalidate();
                    break;
                }
                case 4: {
                    ImageButton imageButton1 = view0 instanceof ImageButton ? ((ImageButton)view0) : null;
                    if(imageButton1 != null) {
                        imageButton1.clearColorFilter();
                    }
                    view0.invalidate();
                    if(dragEvent0.getResult()) {
                        FragmentActivity fragmentActivity0 = settingsActivity$ButtonSettingFragment0.activity;
                        if(fragmentActivity0 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activity");
                        }
                        else {
                            imageButton0 = fragmentActivity0;
                        }
                        toast0 = Toast.makeText(((Context)imageButton0), "The drop was handled.", 1);
                    }
                    else {
                        FragmentActivity fragmentActivity1 = settingsActivity$ButtonSettingFragment0.activity;
                        if(fragmentActivity1 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("activity");
                        }
                        else {
                            imageButton0 = fragmentActivity1;
                        }
                        toast0 = Toast.makeText(((Context)imageButton0), "The drop didn\'t work.", 1);
                    }
                    toast0.show();
                    return true;
                }
                case 5: {
                    if(view0 instanceof ImageButton) {
                        imageButton0 = (ImageButton)view0;
                    }
                    if(imageButton0 != null) {
                        imageButton0.setColorFilter(0xFF00FF00);
                    }
                    view0.invalidate();
                    return true;
                }
                case 6: {
                    if(view0 instanceof ImageButton) {
                        imageButton0 = (ImageButton)view0;
                    }
                    if(imageButton0 != null) {
                        imageButton0.setColorFilter(0xFF0000FF);
                    }
                    view0.invalidate();
                    return true;
                }
                default: {
                    Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.");
                    return false;
                }
            }
            return true;
        }

        @Override  // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle0, String s) {
            this.setPreferencesFromResource(0x7F150001, s);  // xml:buttons_preferences
            FragmentContainerView fragmentContainerView0 = (FragmentContainerView)this.requireActivity().findViewById(0x7F090072);  // id:buttonContainerPreView
            GridLayout gridLayout0 = (GridLayout)fragmentContainerView0.findViewById(0x7F090071);  // id:btnContainerLayout
            fragmentContainerView0.setVisibility(0);
            gridLayout0.setVisibility(0);
            FragmentActivity fragmentActivity0 = this.requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivity0, "requireActivity(...)");
            this.activity = fragmentActivity0;
            ButtonContainer buttonContainer0 = null;
            if(fragmentActivity0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
                fragmentActivity0 = null;
            }
            PreferenceManager.getDefaultSharedPreferences(fragmentActivity0.getBaseContext());
            FragmentActivity fragmentActivity1 = this.activity;
            if(fragmentActivity1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
                fragmentActivity1 = null;
            }
            Fragment fragment0 = fragmentActivity1.getSupportFragmentManager().findFragmentById(0x7F090072);  // id:buttonContainerPreView
            Intrinsics.checkNotNull(fragment0, "null cannot be cast to non-null type com.ruliweb.www.ruliapp.ButtonContainer");
            ButtonContainer buttonContainer1 = (ButtonContainer)fragment0;
            this.btnFragment = buttonContainer1;
            if(buttonContainer1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnFragment");
                buttonContainer1 = null;
            }
            buttonContainer1.initContainerView();
            this.initButtonSettings();
            FragmentActivity fragmentActivity2 = this.activity;
            if(fragmentActivity2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
                fragmentActivity2 = null;
            }
            View view0 = fragmentActivity2.findViewById(0x7F0901BB);  // id:settings
            Intrinsics.checkNotNullExpressionValue(view0, "findViewById(...)");
            FragmentActivity fragmentActivity3 = this.activity;
            if(fragmentActivity3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activity");
                fragmentActivity3 = null;
            }
            GridLayout gridLayout1 = new GridLayout(fragmentActivity3);
            ButtonContainer buttonContainer2 = this.btnFragment;
            if(buttonContainer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnFragment");
            }
            else {
                buttonContainer0 = buttonContainer2;
            }
            gridLayout1.setColumnCount(4);
            gridLayout1.setRowCount(((int)Math.max(Math.floor(((double)buttonContainer0.getFunctionList().length) / 4.0), 1.0)));
            ((FrameLayout)view0).addView(gridLayout1);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001A\u00020\bH\u0002J\u001C\u0010\t\u001A\u00020\b2\b\u0010\n\u001A\u0004\u0018\u00010\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\rH\u0016J\u001A\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u00020\u00102\b\u0010\n\u001A\u0004\u0018\u00010\u000BH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ruliweb/www/ruliapp/SettingsActivity$FavorFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "()V", "currentPage", "", "isMax", "", "getFavoriteList", "", "onCreatePreferences", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "", "onViewCreated", "view", "Landroid/view/View;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class FavorFragment extends PreferenceFragmentCompat {
        private int currentPage;
        private boolean isMax;

        public FavorFragment() {
            this.currentPage = 1;
        }

        private final void getFavoriteList() {
            Context context0 = this.requireActivity().getBaseContext();
            RestModule restModule0 = RestModule.Companion.getInstance();
            restModule0.setMethod("POST");
            restModule0.setTarget("favorite_list_for_alarm");
            Map map0 = new LinkedHashMap();
            SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(context0);
            String s = sharedPreferences0.getString("current_member_srl", "");
            if(s == null) {
                s = "";
            }
            map0.put("member_srl", s);
            String s1 = sharedPreferences0.getString("gcm_token", "");
            if(s1 == null) {
                s1 = "";
            }
            map0.put("token_value", s1);
            map0.put("type", "aos");
            map0.put("page", String.valueOf(this.currentPage));
            ++this.currentPage;
            restModule0.setData(MapsKt.toMap(map0));
            if(!Intrinsics.areEqual(map0.get("member_srl"), "") && !Intrinsics.areEqual(map0.get("token_value"), "")) {
                Intrinsics.checkNotNull(context0);
                restModule0.request(context0, new Function1(context0, restModule0, sharedPreferences0) {
                    final Context $baseContext;
                    final RestModule $rest;
                    final SharedPreferences $shared;

                    // 检测为 Lambda 实现
                    public static boolean $r8$lambda$8xIcIRwEqngeavQcdZCaVYKsIzQ(RestModule restModule0, SharedPreferences sharedPreferences0, FavorFragment settingsActivity$FavorFragment0, Context context0, SwitchPreference switchPreference0, Preference preference0, Object object0) [...]

                    {
                        FavorFragment.this = settingsActivity$FavorFragment0;
                        this.$baseContext = context0;
                        this.$rest = restModule0;
                        this.$shared = sharedPreferences0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((String)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(String s) {
                        Unit unit0;
                        Intrinsics.checkNotNullParameter(s, "res");
                        JSONObject jSONObject0 = new JSONObject(s);
                        Object object0 = jSONObject0.get("success");
                        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Boolean");
                        if(((Boolean)object0).booleanValue()) {
                            Object object1 = jSONObject0.get("message");
                            Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.String");
                            JSONObject jSONObject1 = new JSONObject(((String)object1));
                            Object object2 = jSONObject1.get("is_max");
                            Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type kotlin.Boolean");
                            FavorFragment.this.isMax = ((Boolean)object2).booleanValue();
                            PreferenceScreen preferenceScreen0 = FavorFragment.this.getPreferenceScreen();
                            Context context0 = this.$baseContext;
                            RestModule restModule0 = this.$rest;
                            SharedPreferences sharedPreferences0 = this.$shared;
                            FavorFragment settingsActivity$FavorFragment0 = FavorFragment.this;
                            try {
                                Object object3 = jSONObject1.get("list");
                                Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type org.json.JSONArray");
                                int v = ((JSONArray)object3).length();
                                for(int v1 = 0; v1 < v; ++v1) {
                                    JSONObject jSONObject2 = ((JSONArray)object3).getJSONObject(v1);
                                    SwitchPreference switchPreference0 = new SwitchPreference(context0);
                                    preferenceScreen0.addPreference(switchPreference0);
                                    switchPreference0.setTitle(jSONObject2.get("board_name").toString());
                                    switchPreference0.setKey(jSONObject2.get("board_id").toString());
                                    switchPreference0.setChecked(Intrinsics.areEqual(jSONObject2.get("status").toString(), "S"));
                                    switchPreference0.setOnPreferenceChangeListener((Preference preference0, Object object0) -> com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.invoke$lambda$2$lambda$1(restModule0, sharedPreferences0, settingsActivity$FavorFragment0, context0, switchPreference0, preference0, object0));
                                }
                                unit0 = Unit.INSTANCE;
                            }
                            catch(Throwable throwable0) {
                                unit0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
                            }
                            Context context1 = this.$baseContext;
                            if(Result.exceptionOrNull-impl(unit0) != null) {
                                Toast.makeText(context1, "에러가 발생했습니다.", 0).show();
                            }
                            return;
                        }
                        Toast.makeText(this.$baseContext, "에러가 발생했습니다.", 0).show();

                        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "res", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
                        final class com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.1.1.2 extends Lambda implements Function1 {
                            final Context $baseContext;
                            final SwitchPreference $item;
                            final Object $newValue;

                            com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.1.1.2(Context context0, SwitchPreference switchPreference0, Object object0) {
                                this.$baseContext = context0;
                                this.$item = switchPreference0;
                                this.$newValue = object0;
                                super(1);
                            }

                            @Override  // kotlin.jvm.functions.Function1
                            public Object invoke(Object object0) {
                                this.invoke(((String)object0));
                                return Unit.INSTANCE;
                            }

                            public final void invoke(String s) {
                                Intrinsics.checkNotNullParameter(s, "res");
                                Object object0 = new JSONObject(s).get("success");
                                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Boolean");
                                if(!((Boolean)object0).booleanValue()) {
                                    Toast.makeText(this.$baseContext, "에러가 발생했습니다.", 0).show();
                                    Intrinsics.checkNotNull(this.$newValue, "null cannot be cast to non-null type kotlin.Boolean");
                                    this.$item.setChecked(!((Boolean)this.$newValue).booleanValue());
                                }
                            }
                        }


                        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/android/volley/VolleyError;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
                        final class com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.1.1.3 extends Lambda implements Function1 {
                            public static final com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.1.1.3 INSTANCE;

                            static {
                                com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.1.1.3.INSTANCE = new com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.1.1.3();
                            }

                            com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.1.1.3() {
                                super(1);
                            }

                            @Override  // kotlin.jvm.functions.Function1
                            public Object invoke(Object object0) {
                                this.invoke(((VolleyError)object0));
                                return Unit.INSTANCE;
                            }

                            public final void invoke(VolleyError volleyError0) {
                                Intrinsics.checkNotNullParameter(volleyError0, "it");
                            }
                        }

                    }

                    private static final boolean invoke$lambda$2$lambda$1(RestModule restModule0, SharedPreferences sharedPreferences0, FavorFragment settingsActivity$FavorFragment0, Context context0, SwitchPreference switchPreference0, Preference preference0, Object object0) {
                        Intrinsics.checkNotNullParameter(restModule0, "$rest");
                        Intrinsics.checkNotNullParameter(settingsActivity$FavorFragment0, "this$0");
                        Intrinsics.checkNotNullParameter(switchPreference0, "$item");
                        Intrinsics.checkNotNullParameter(preference0, "pref");
                        restModule0.setMethod("POST");
                        restModule0.setTarget("update_favorite_push");
                        Map map0 = new LinkedHashMap();
                        String s = preference0.getKey();
                        Intrinsics.checkNotNullExpressionValue(s, "getKey(...)");
                        map0.put("board_id", s);
                        String s1 = sharedPreferences0.getString("current_member_srl", "");
                        if(s1 == null) {
                            s1 = "";
                        }
                        map0.put("member_srl", s1);
                        String s2 = sharedPreferences0.getString("gcm_token", "");
                        if(s2 == null) {
                            s2 = "";
                        }
                        map0.put("token_value", s2);
                        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Boolean");
                        map0.put("status", (((Boolean)object0).booleanValue() ? "S" : "W"));
                        map0.put("type", "aos");
                        String s3 = settingsActivity$FavorFragment0.requireContext().getPackageManager().getPackageInfo("com.ruliweb.www.ruliapp", 0).versionName;
                        if(s3 == null) {
                            s3 = "";
                        }
                        Intrinsics.checkNotNull(s3);
                        map0.put("version", s3);
                        if(!Intrinsics.areEqual(map0.get("member_srl"), "") && !Intrinsics.areEqual(map0.get("token_value"), "")) {
                            restModule0.setData(MapsKt.toMap(map0));
                            Intrinsics.checkNotNull(context0);
                            restModule0.request(context0, new com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.1.1.2(context0, switchPreference0, object0), com.ruliweb.www.ruliapp.SettingsActivity.FavorFragment.getFavoriteList.1.1.1.3.INSTANCE);
                            return true;
                        }
                        Toast.makeText(context0, "에러가 발생했습니다.", 0).show();
                        return false;
                    }
                }, new Function1() {
                    final Context $baseContext;

                    {
                        this.$baseContext = context0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((VolleyError)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(VolleyError volleyError0) {
                        Intrinsics.checkNotNullParameter(volleyError0, "error");
                        Log.e("list error", volleyError0.toString());
                        Toast.makeText(this.$baseContext, "에러가 발생했습니다.", 0).show();
                    }
                });
                return;
            }
            Toast.makeText(context0, "에러가 발생했습니다.", 0).show();
        }

        @Override  // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle0, String s) {
            this.requireActivity().getBaseContext();
            this.setPreferencesFromResource(0x7F150002, s);  // xml:favorite_preferences
            this.getFavoriteList();
        }

        @Override  // androidx.preference.PreferenceFragmentCompat
        public void onViewCreated(View view0, Bundle bundle0) {
            Intrinsics.checkNotNullParameter(view0, "view");
            super.onViewCreated(view0, bundle0);
            if(Build.VERSION.SDK_INT >= 23) {
                this.getListView().addOnScrollListener(new OnScrollListener() {
                    @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
                    public void onScrollStateChanged(RecyclerView recyclerView0, int v) {
                        Intrinsics.checkNotNullParameter(recyclerView0, "recyclerView");
                        super.onScrollStateChanged(recyclerView0, v);
                        if(!FavorFragment.this.isMax && recyclerView0.getChildAt(recyclerView0.getChildCount() - 1).getBottom() - (recyclerView0.getHeight() + recyclerView0.getScrollY()) == 0 && v == 0) {
                            FavorFragment.this.getFavoriteList();
                        }
                    }

                    @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
                    public void onScrolled(RecyclerView recyclerView0, int v, int v1) {
                        Intrinsics.checkNotNullParameter(recyclerView0, "recyclerView");
                        super.onScrolled(recyclerView0, v, v1);
                    }
                });
                return;
            }
            this.getListView().setOnScrollListener(new OnScrollListener() {
                @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView0, int v) {
                    Intrinsics.checkNotNullParameter(recyclerView0, "recyclerView");
                    super.onScrollStateChanged(recyclerView0, v);
                    if(!FavorFragment.this.isMax && recyclerView0.getChildAt(recyclerView0.getChildCount() - 1).getBottom() - (recyclerView0.getHeight() + recyclerView0.getScrollY()) == 0) {
                        FavorFragment.this.getFavoriteList();
                    }
                }
            });
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001C\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/ruliweb/www/ruliapp/SettingsActivity$HeaderFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "()V", "onCreatePreferences", "", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class HeaderFragment extends PreferenceFragmentCompat {
        @Override  // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle0, String s) {
            this.setPreferencesFromResource(0x7F150004, s);  // xml:header_preferences
        }
    }

    public TextView activityTitle;

    // 检测为 Lambda 实现
    public static void $r8$lambda$CZhdqugfvwPXyj_xGmLzSkdnMqg(SettingsActivity settingsActivity0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$zwXwwCYimN9L4PzSIHoLt0_4u2c(SettingsActivity settingsActivity0, View view0) [...]

    public final TextView getActivityTitle() {
        TextView textView0 = this.activityTitle;
        if(textView0 != null) {
            return textView0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("activityTitle");
        return null;
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        switch(this.getApplicationContext().getResources().getConfiguration().uiMode & 0x30) {
            case 0: 
            case 16: {
                this.setTheme(0x7F130125);  // style:LightTheme
                break;
            }
            case 0x20: {
                this.setTheme(0x7F130124);  // style:DarkTheme
            }
        }
        this.setContentView(0x7F0C0086);  // layout:settings_activity
        Window window0 = this.getWindow();
        Intrinsics.checkNotNullExpressionValue(window0, "getWindow(...)");
        Utils.Companion.setUpViewLayout(window0);
        View view0 = this.findViewById(0x7F0901BC);  // id:settingsTitleText
        Intrinsics.checkNotNullExpressionValue(view0, "findViewById(...)");
        this.setActivityTitle(((TextView)view0));
        this.getActivityTitle().setText("설정");
        this.getSupportFragmentManager().beginTransaction().replace(0x7F0901BB, new HeaderFragment()).commit();  // id:settings
        if(Intrinsics.areEqual(this.getIntent().getStringExtra("target"), "alarm")) {
            Fragment fragment0 = this.getSupportFragmentManager().getFragmentFactory().instantiate(this.getClassLoader(), "com.ruliweb.www.ruliapp.SettingsActivity$AlarmFragment");
            Intrinsics.checkNotNullExpressionValue(fragment0, "apply(...)");
            this.getSupportFragmentManager().beginTransaction().replace(0x7F0901BB, fragment0).addToBackStack("알람 설정").commit();  // id:settings
            this.getActivityTitle().setText("알람 설정");
        }
        this.getSupportFragmentManager().addOnBackStackChangedListener(() -> SettingsActivity.onCreate$lambda$1(this));
        View view1 = this.findViewById(0x7F090088);  // id:close_settings
        Intrinsics.checkNotNullExpressionValue(view1, "findViewById(...)");
        ((ImageButton)view1).setOnClickListener((View view0) -> SettingsActivity.onCreate$lambda$2(this, view0));
        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback() {
            final ImageButton $close_btn;

            {
                this.$close_btn = imageButton0;
                super(true);
            }

            @Override  // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                this.$close_btn.callOnClick();
            }
        });
    }

    private static final void onCreate$lambda$1(SettingsActivity settingsActivity0) {
        Intrinsics.checkNotNullParameter(settingsActivity0, "this$0");
        if(settingsActivity0.getSupportFragmentManager().getBackStackEntryCount() == 0) {
            settingsActivity0.getActivityTitle().setText("설정");
        }
    }

    private static final void onCreate$lambda$2(SettingsActivity settingsActivity0, View view0) {
        Intrinsics.checkNotNullParameter(settingsActivity0, "this$0");
        if(settingsActivity0.getSupportFragmentManager().getBackStackEntryCount() == 0) {
            settingsActivity0.finish();
            return;
        }
        settingsActivity0.getSupportFragmentManager().popBackStack();
    }

    @Override  // androidx.preference.PreferenceFragmentCompat$OnPreferenceStartFragmentCallback
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat preferenceFragmentCompat0, Preference preference0) {
        Intrinsics.checkNotNullParameter(preferenceFragmentCompat0, "caller");
        Intrinsics.checkNotNullParameter(preference0, "pref");
        Bundle bundle0 = preference0.getExtras();
        Intrinsics.checkNotNullExpressionValue(bundle0, "getExtras(...)");
        Fragment fragment0 = this.getSupportFragmentManager().getFragmentFactory().instantiate(this.getClassLoader(), (preference0.getFragment() == null ? "error" : preference0.getFragment()));
        fragment0.setArguments(bundle0);
        fragment0.setTargetFragment(preferenceFragmentCompat0, 0);
        Intrinsics.checkNotNullExpressionValue(fragment0, "apply(...)");
        this.getSupportFragmentManager().beginTransaction().replace(0x7F0901BB, fragment0).addToBackStack(String.valueOf(preference0.getTitle())).commit();  // id:settings
        this.getActivityTitle().setText(preference0.getTitle());
        return true;
    }

    @Override  // androidx.activity.ComponentActivity
    protected void onSaveInstanceState(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "outState");
        super.onSaveInstanceState(bundle0);
        bundle0.putCharSequence("settingsActivity", this.getTitle());
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        return this.getSupportFragmentManager().popBackStackImmediate() ? true : super.onSupportNavigateUp();
    }

    public final void setActivityTitle(TextView textView0) {
        Intrinsics.checkNotNullParameter(textView0, "<set-?>");
        this.activityTitle = textView0;
    }
}

