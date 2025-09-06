package com.google.android.gms.cloudmessaging;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public final class CloudMessage extends AbstractSafeParcelable {
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    public @interface MessagePriority {
    }

    public static final Parcelable.Creator CREATOR = null;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN;
    Intent zza;
    private Map zzb;

    static {
        CloudMessage.CREATOR = new zza();
    }

    public CloudMessage(Intent intent0) {
        this.zza = intent0;
    }

    public String getCollapseKey() {
        return this.zza.getStringExtra("collapse_key");
    }

    public Map getData() {
        synchronized(this) {
            if(this.zzb == null) {
                Bundle bundle0 = this.zza.getExtras();
                ArrayMap arrayMap0 = new ArrayMap();
                if(bundle0 != null) {
                    for(Object object0: bundle0.keySet()) {
                        String s = (String)object0;
                        Object object1 = bundle0.get(s);
                        if(object1 instanceof String && !s.startsWith("google.") && !s.equals("from") && !s.equals("message_type") && !s.equals("collapse_key")) {
                            arrayMap0.put(s, ((String)object1));
                        }
                    }
                }
                this.zzb = arrayMap0;
            }
            return this.zzb;
        }
    }

    public String getFrom() {
        return this.zza.getStringExtra("from");
    }

    public Intent getIntent() {
        return this.zza;
    }

    public String getMessageId() {
        String s = this.zza.getStringExtra("google.message_id");
        return s == null ? this.zza.getStringExtra("message_id") : s;
    }

    public String getMessageType() {
        return this.zza.getStringExtra("message_type");
    }

    public int getOriginalPriority() {
        String s = this.zza.getStringExtra("google.original_priority");
        if(s == null) {
            s = this.zza.getStringExtra("google.priority");
        }
        return CloudMessage.zza(s);
    }

    public int getPriority() {
        String s = this.zza.getStringExtra("google.delivered_priority");
        if(s == null) {
            if("1".equals(this.zza.getStringExtra("google.priority_reduced"))) {
                return 2;
            }
            s = this.zza.getStringExtra("google.priority");
        }
        return CloudMessage.zza(s);
    }

    public byte[] getRawData() {
        return this.zza.getByteArrayExtra("rawData");
    }

    public String getSenderId() {
        return this.zza.getStringExtra("google.c.sender.id");
    }

    public long getSentTime() {
        Bundle bundle0 = this.zza.getExtras();
        Object object0 = bundle0 == null ? null : bundle0.get("google.sent_time");
        if(object0 instanceof Long) {
            return (long)(((Long)object0));
        }
        if(object0 instanceof String) {
            try {
                return Long.parseLong(((String)object0));
            }
            catch(NumberFormatException unused_ex) {
                Log.w("CloudMessage", "Invalid sent time: " + object0);
            }
        }
        return 0L;
    }

    public String getTo() {
        return this.zza.getStringExtra("google.to");
    }

    public int getTtl() {
        Bundle bundle0 = this.zza.getExtras();
        Object object0 = bundle0 == null ? null : bundle0.get("google.ttl");
        if(object0 instanceof Integer) {
            return (int)(((Integer)object0));
        }
        if(object0 instanceof String) {
            try {
                return Integer.parseInt(((String)object0));
            }
            catch(NumberFormatException unused_ex) {
                Log.w("CloudMessage", "Invalid TTL: " + object0);
            }
        }
        return 0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 1, this.zza, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    private static int zza(String s) {
        if("high".equals(s)) {
            return 1;
        }
        return "normal".equals(s) ? 2 : 0;
    }
}

