package com.google.firebase.messaging;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map.Entry;
import java.util.Map;

public final class RemoteMessage extends AbstractSafeParcelable {
    public static class Builder {
        private final Bundle bundle;
        private final Map data;

        public Builder(String s) {
            Bundle bundle0 = new Bundle();
            this.bundle = bundle0;
            this.data = new ArrayMap();
            if(TextUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Invalid to: " + s);
            }
            bundle0.putString("google.to", s);
        }

        public Builder addData(String s, String s1) {
            this.data.put(s, s1);
            return this;
        }

        public RemoteMessage build() {
            Bundle bundle0 = new Bundle();
            for(Object object0: this.data.entrySet()) {
                bundle0.putString(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
            }
            bundle0.putAll(this.bundle);
            this.bundle.remove("from");
            return new RemoteMessage(bundle0);
        }

        public Builder clearData() {
            this.data.clear();
            return this;
        }

        public String getCollapseKey() {
            return this.bundle.getString("message_type");
        }

        public Map getData() {
            return this.data;
        }

        public String getMessageId() {
            return this.bundle.getString("google.message_id", "");
        }

        public String getMessageType() {
            return this.bundle.getString("message_type");
        }

        public int getTtl() {
            return Integer.parseInt(this.bundle.getString("message_type", "0"));
        }

        public Builder setCollapseKey(String s) {
            this.bundle.putString("collapse_key", s);
            return this;
        }

        public Builder setData(Map map0) {
            this.data.clear();
            this.data.putAll(map0);
            return this;
        }

        public Builder setMessageId(String s) {
            this.bundle.putString("google.message_id", s);
            return this;
        }

        public Builder setMessageType(String s) {
            this.bundle.putString("message_type", s);
            return this;
        }

        public Builder setRawData(byte[] arr_b) {
            this.bundle.putByteArray("rawData", arr_b);
            return this;
        }

        public Builder setTtl(int v) {
            this.bundle.putString("google.ttl", String.valueOf(v));
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MessagePriority {
    }

    public static class Notification {
        private final String body;
        private final String[] bodyLocArgs;
        private final String bodyLocKey;
        private final String channelId;
        private final String clickAction;
        private final String color;
        private final boolean defaultLightSettings;
        private final boolean defaultSound;
        private final boolean defaultVibrateTimings;
        private final Long eventTime;
        private final String icon;
        private final String imageUrl;
        private final int[] lightSettings;
        private final Uri link;
        private final boolean localOnly;
        private final Integer notificationCount;
        private final Integer notificationPriority;
        private final String sound;
        private final boolean sticky;
        private final String tag;
        private final String ticker;
        private final String title;
        private final String[] titleLocArgs;
        private final String titleLocKey;
        private final long[] vibrateTimings;
        private final Integer visibility;

        private Notification(NotificationParams notificationParams0) {
            this.title = notificationParams0.getString("gcm.n.title");
            this.titleLocKey = notificationParams0.getLocalizationResourceForKey("gcm.n.title");
            this.titleLocArgs = Notification.getLocalizationArgs(notificationParams0, "gcm.n.title");
            this.body = notificationParams0.getString("gcm.n.body");
            this.bodyLocKey = notificationParams0.getLocalizationResourceForKey("gcm.n.body");
            this.bodyLocArgs = Notification.getLocalizationArgs(notificationParams0, "gcm.n.body");
            this.icon = notificationParams0.getString("gcm.n.icon");
            this.sound = notificationParams0.getSoundResourceName();
            this.tag = notificationParams0.getString("gcm.n.tag");
            this.color = notificationParams0.getString("gcm.n.color");
            this.clickAction = notificationParams0.getString("gcm.n.click_action");
            this.channelId = notificationParams0.getString("gcm.n.android_channel_id");
            this.link = notificationParams0.getLink();
            this.imageUrl = notificationParams0.getString("gcm.n.image");
            this.ticker = notificationParams0.getString("gcm.n.ticker");
            this.notificationPriority = notificationParams0.getInteger("gcm.n.notification_priority");
            this.visibility = notificationParams0.getInteger("gcm.n.visibility");
            this.notificationCount = notificationParams0.getInteger("gcm.n.notification_count");
            this.sticky = notificationParams0.getBoolean("gcm.n.sticky");
            this.localOnly = notificationParams0.getBoolean("gcm.n.local_only");
            this.defaultSound = notificationParams0.getBoolean("gcm.n.default_sound");
            this.defaultVibrateTimings = notificationParams0.getBoolean("gcm.n.default_vibrate_timings");
            this.defaultLightSettings = notificationParams0.getBoolean("gcm.n.default_light_settings");
            this.eventTime = notificationParams0.getLong("gcm.n.event_time");
            this.lightSettings = notificationParams0.getLightSettings();
            this.vibrateTimings = notificationParams0.getVibrateTimings();
        }

        Notification(NotificationParams notificationParams0, com.google.firebase.messaging.RemoteMessage.1 remoteMessage$10) {
            this(notificationParams0);
        }

        public String getBody() {
            return this.body;
        }

        public String[] getBodyLocalizationArgs() {
            return this.bodyLocArgs;
        }

        public String getBodyLocalizationKey() {
            return this.bodyLocKey;
        }

        public String getChannelId() {
            return this.channelId;
        }

        public String getClickAction() {
            return this.clickAction;
        }

        public String getColor() {
            return this.color;
        }

        public boolean getDefaultLightSettings() {
            return this.defaultLightSettings;
        }

        public boolean getDefaultSound() {
            return this.defaultSound;
        }

        public boolean getDefaultVibrateSettings() {
            return this.defaultVibrateTimings;
        }

        public Long getEventTime() {
            return this.eventTime;
        }

        public String getIcon() {
            return this.icon;
        }

        public Uri getImageUrl() {
            return this.imageUrl == null ? null : Uri.parse(this.imageUrl);
        }

        public int[] getLightSettings() {
            return this.lightSettings;
        }

        public Uri getLink() {
            return this.link;
        }

        public boolean getLocalOnly() {
            return this.localOnly;
        }

        private static String[] getLocalizationArgs(NotificationParams notificationParams0, String s) {
            Object[] arr_object = notificationParams0.getLocalizationArgsForKey(s);
            if(arr_object == null) {
                return null;
            }
            String[] arr_s = new String[arr_object.length];
            for(int v = 0; v < arr_object.length; ++v) {
                arr_s[v] = String.valueOf(arr_object[v]);
            }
            return arr_s;
        }

        public Integer getNotificationCount() {
            return this.notificationCount;
        }

        public Integer getNotificationPriority() {
            return this.notificationPriority;
        }

        public String getSound() {
            return this.sound;
        }

        public boolean getSticky() {
            return this.sticky;
        }

        public String getTag() {
            return this.tag;
        }

        public String getTicker() {
            return this.ticker;
        }

        public String getTitle() {
            return this.title;
        }

        public String[] getTitleLocalizationArgs() {
            return this.titleLocArgs;
        }

        public String getTitleLocalizationKey() {
            return this.titleLocKey;
        }

        public long[] getVibrateTimings() {
            return this.vibrateTimings;
        }

        public Integer getVisibility() {
            return this.visibility;
        }
    }

    public static final Parcelable.Creator CREATOR = null;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN;
    Bundle bundle;
    private Map data;
    private Notification notification;

    static {
        RemoteMessage.CREATOR = new RemoteMessageCreator();
    }

    public RemoteMessage(Bundle bundle0) {
        this.bundle = bundle0;
    }

    public String getCollapseKey() {
        return this.bundle.getString("collapse_key");
    }

    public Map getData() {
        if(this.data == null) {
            this.data = MessagePayloadKeys.extractDeveloperDefinedPayload(this.bundle);
        }
        return this.data;
    }

    public String getFrom() {
        return this.bundle.getString("from");
    }

    public String getMessageId() {
        String s = this.bundle.getString("google.message_id");
        return s == null ? this.bundle.getString("message_id") : s;
    }

    private int getMessagePriority(String s) {
        if("high".equals(s)) {
            return 1;
        }
        return "normal".equals(s) ? 2 : 0;
    }

    public String getMessageType() {
        return this.bundle.getString("message_type");
    }

    public Notification getNotification() {
        if(this.notification == null && NotificationParams.isNotification(this.bundle)) {
            this.notification = new Notification(new NotificationParams(this.bundle), null);
        }
        return this.notification;
    }

    public int getOriginalPriority() {
        String s = this.bundle.getString("google.original_priority");
        if(s == null) {
            s = this.bundle.getString("google.priority");
        }
        return this.getMessagePriority(s);
    }

    public int getPriority() {
        String s = this.bundle.getString("google.delivered_priority");
        if(s == null) {
            if("1".equals(this.bundle.getString("google.priority_reduced"))) {
                return 2;
            }
            s = this.bundle.getString("google.priority");
        }
        return this.getMessagePriority(s);
    }

    public byte[] getRawData() {
        return this.bundle.getByteArray("rawData");
    }

    public String getSenderId() {
        return this.bundle.getString("google.c.sender.id");
    }

    public long getSentTime() {
        Object object0 = this.bundle.get("google.sent_time");
        if(object0 instanceof Long) {
            return (long)(((Long)object0));
        }
        if(object0 instanceof String) {
            try {
                return Long.parseLong(((String)object0));
            }
            catch(NumberFormatException unused_ex) {
                Log.w("FirebaseMessaging", "Invalid sent time: " + object0);
            }
        }
        return 0L;
    }

    public String getTo() {
        return this.bundle.getString("google.to");
    }

    public int getTtl() {
        Object object0 = this.bundle.get("google.ttl");
        if(object0 instanceof Integer) {
            return (int)(((Integer)object0));
        }
        if(object0 instanceof String) {
            try {
                return Integer.parseInt(((String)object0));
            }
            catch(NumberFormatException unused_ex) {
                Log.w("FirebaseMessaging", "Invalid TTL: " + object0);
            }
        }
        return 0;
    }

    void populateSendMessageIntent(Intent intent0) {
        intent0.putExtras(this.bundle);
    }

    public Intent toIntent() {
        Intent intent0 = new Intent();
        intent0.putExtras(this.bundle);
        return intent0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        RemoteMessageCreator.writeToParcel(this, parcel0, v);
    }

    class com.google.firebase.messaging.RemoteMessage.1 {
    }

}

