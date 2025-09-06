package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzfd {
    final zzfi zza;
    private final String zzb;
    private final Bundle zzc;
    private Bundle zzd;

    public zzfd(zzfi zzfi0, String s, Bundle bundle0) {
        this.zza = zzfi0;
        super();
        new String("default_event_parameters");
        this.zzb = "default_event_parameters";
        this.zzc = new Bundle();
    }

    // This method was un-flattened
    public final Bundle zza() {
        if(this.zzd == null) {
            String s = this.zza.zza().getString(this.zzb, null);
            if(s != null) {
                try {
                    Bundle bundle0 = new Bundle();
                    JSONArray jSONArray0 = new JSONArray(s);
                    for(int v = 0; true; ++v) {
                        if(v >= jSONArray0.length()) {
                            this.zzd = bundle0;
                            break;
                        }
                        try {
                            JSONObject jSONObject0 = jSONArray0.getJSONObject(v);
                            String s1 = jSONObject0.getString("n");
                            String s2 = jSONObject0.getString("t");
                            switch(s2) {
                                case "d": {
                                    bundle0.putDouble(s1, Double.parseDouble(jSONObject0.getString("v")));
                                    break;
                                }
                                case "l": {
                                    bundle0.putLong(s1, Long.parseLong(jSONObject0.getString("v")));
                                    break;
                                }
                                case "s": {
                                    bundle0.putString(s1, jSONObject0.getString("v"));
                                    break;
                                }
                                default: {
                                    this.zza.zzt.zzaA().zzd().zzb("Unrecognized persisted bundle type. Type", s2);
                                }
                            }
                        }
                        catch(JSONException | NumberFormatException unused_ex) {
                            this.zza.zzt.zzaA().zzd().zza("Error reading value from SharedPreferences. Value dropped");
                        }
                    }
                }
                catch(JSONException unused_ex) {
                    this.zza.zzt.zzaA().zzd().zza("Error loading bundle from SharedPreferences. Values will be lost");
                }
            }
            if(this.zzd == null) {
                this.zzd = this.zzc;
            }
        }
        return this.zzd;
    }

    public final void zzb(Bundle bundle0) {
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        SharedPreferences.Editor sharedPreferences$Editor0 = this.zza.zza().edit();
        if(bundle0.size() == 0) {
            sharedPreferences$Editor0.remove(this.zzb);
        }
        else {
            String s = this.zzb;
            JSONArray jSONArray0 = new JSONArray();
            for(Object object0: bundle0.keySet()) {
                String s1 = (String)object0;
                Object object1 = bundle0.get(s1);
                if(object1 != null) {
                    try {
                        JSONObject jSONObject0 = new JSONObject();
                        jSONObject0.put("n", s1);
                        jSONObject0.put("v", object1.toString());
                        if(object1 instanceof String) {
                        label_26:
                            jSONObject0.put("t", "s");
                        }
                        else {
                            if(object1 instanceof Long) {
                                jSONObject0.put("t", "l");
                                goto label_27;
                            }
                            else if(object1 instanceof Double) {
                                jSONObject0.put("t", "d");
                                goto label_27;
                            }
                            else {
                                this.zza.zzt.zzaA().zzd().zzb("Cannot serialize bundle value to SharedPreferences. Type", object1.getClass());
                                continue;
                            }
                            goto label_26;
                        }
                    label_27:
                        jSONArray0.put(jSONObject0);
                    }
                    catch(JSONException jSONException0) {
                        this.zza.zzt.zzaA().zzd().zzb("Cannot serialize bundle value to SharedPreferences", jSONException0);
                    }
                }
            }
            sharedPreferences$Editor0.putString(s, jSONArray0.toString());
        }
        sharedPreferences$Editor0.apply();
        this.zzd = bundle0;
    }
}

