package com.ruliweb.www.ruliapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.Window;
import androidx.preference.PreferenceManager;
import com.android.volley.VolleyError;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/ruliweb/www/ruliapp/Utils;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class Utils {
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001A\u00020\u0007J\u0016\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rJ\u000E\u0010\u000E\u001A\u00020\t2\u0006\u0010\u000F\u001A\u00020\u0010¨\u0006\u0011"}, d2 = {"Lcom/ruliweb/www/ruliapp/Utils$Companion;", "", "()V", "checkDeviceType", "", "", "activity", "Landroid/app/Activity;", "setAlarmOptions", "", "context", "Landroid/content/Context;", "isLogin", "", "setUpViewLayout", "window", "Landroid/view/Window;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Map checkDeviceType(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            double f = (double)activity0.getApplication().getResources().getConfiguration().screenWidthDp;
            double f1 = (double)activity0.getApplication().getResources().getConfiguration().screenHeightDp;
            float f2 = activity0.getResources().getDisplayMetrics().density;
            double f3 = f1 / f;
            if(f3 < 1.0) {
                return MapsKt.mapOf(new Pair[]{TuplesKt.to("Type", "Tablet"), TuplesKt.to("Density", String.valueOf(f2)), TuplesKt.to("Width", String.valueOf(f)), TuplesKt.to("Height", String.valueOf(f1))});
            }
            return f3 < 1.5 ? MapsKt.mapOf(new Pair[]{TuplesKt.to("Type", "Foldable"), TuplesKt.to("Density", String.valueOf(f2)), TuplesKt.to("Width", String.valueOf(f)), TuplesKt.to("Height", String.valueOf(f1))}) : MapsKt.mapOf(new Pair[]{TuplesKt.to("Type", "SmartPhone"), TuplesKt.to("Density", String.valueOf(f2)), TuplesKt.to("Width", String.valueOf(f)), TuplesKt.to("Height", String.valueOf(f1))});
        }

        public final void setAlarmOptions(Context context0, boolean z) {
            String s1;
            Intrinsics.checkNotNullParameter(context0, "context");
            SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(context0);
            Map map0 = new LinkedHashMap();
            String s = "E";
            if(z) {
                s1 = sharedPreferences0.getBoolean("message_type", false) ? "M" : "";
                if(sharedPreferences0.getBoolean("comment_type", false)) {
                    s1 = s1 + "C";
                }
                if(sharedPreferences0.getBoolean("favorite_type", false)) {
                    s1 = s1 + "F";
                }
                if(sharedPreferences0.getBoolean("contents_type", false)) {
                    s1 = s1 + "E";
                }
            }
            else {
                if(!sharedPreferences0.getBoolean("contents_type", false)) {
                    s = "";
                }
                s1 = s;
            }
            map0.put("webpush", s1);
            String s2 = context0.getPackageManager().getPackageInfo("com.ruliweb.www.ruliapp", 0).versionName;
            if(s2 == null) {
                s2 = "";
            }
            Intrinsics.checkNotNull(s2);
            map0.put("version", s2);
            String s3 = sharedPreferences0.getString("gcm_token", "");
            String s4 = sharedPreferences0.getString("current_member_srl", "");
            if(s3 != null && !Intrinsics.areEqual(s3, "")) {
                map0.put("gcm_token", s3);
            }
            if(s4 != null && !Intrinsics.areEqual(s4, "")) {
                map0.put("member_srl", s4);
            }
            Log.d("datasss", map0.toString());
            map0.put("type", "aos");
            RestModule restModule0 = RestModule.Companion.getInstance();
            restModule0.setData(MapsKt.toMap(map0));
            restModule0.setMethod("POST");
            restModule0.setTarget("set_apppush_with_version");
            restModule0.request(context0, com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.2.INSTANCE, com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.3.INSTANCE);

            @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "res", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
            final class com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.2 extends Lambda implements Function1 {
                public static final com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.2 INSTANCE;

                static {
                    com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.2.INSTANCE = new com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.2();
                }

                com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.2() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    this.invoke(((String)object0));
                    return Unit.INSTANCE;
                }

                public final void invoke(String s) {
                    Intrinsics.checkNotNullParameter(s, "res");
                }
            }


            @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "e", "Lcom/android/volley/VolleyError;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
            final class com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.3 extends Lambda implements Function1 {
                public static final com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.3 INSTANCE;

                static {
                    com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.3.INSTANCE = new com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.3();
                }

                com.ruliweb.www.ruliapp.Utils.Companion.setAlarmOptions.3() {
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    this.invoke(((VolleyError)object0));
                    return Unit.INSTANCE;
                }

                public final void invoke(VolleyError volleyError0) {
                    Intrinsics.checkNotNullParameter(volleyError0, "e");
                    String s = volleyError0.getMessage();
                    Intrinsics.checkNotNull(s);
                    Log.e("jsonError", s);
                }
            }

        }

        public final void setUpViewLayout(Window window0) {
            Intrinsics.checkNotNullParameter(window0, "window");
            View view0 = window0.findViewById(0x7F090047);  // id:activity_main
            Intrinsics.checkNotNull(view0, "null cannot be cast to non-null type android.view.View");
            view0.setFitsSystemWindows(true);
        }
    }

    public static final Companion Companion;

    static {
        Utils.Companion = new Companion(null);
    }
}

