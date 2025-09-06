package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import androidx.core.util.Preconditions;

public class NotificationChannelCompat {
    static class Api26Impl {
        static boolean canBypassDnd(NotificationChannel notificationChannel0) {
            return notificationChannel0.canBypassDnd();
        }

        static boolean canShowBadge(NotificationChannel notificationChannel0) {
            return notificationChannel0.canShowBadge();
        }

        static NotificationChannel createNotificationChannel(String s, CharSequence charSequence0, int v) {
            return new NotificationChannel(s, charSequence0, v);
        }

        static void enableLights(NotificationChannel notificationChannel0, boolean z) {
            notificationChannel0.enableLights(z);
        }

        static void enableVibration(NotificationChannel notificationChannel0, boolean z) {
            notificationChannel0.enableVibration(z);
        }

        static AudioAttributes getAudioAttributes(NotificationChannel notificationChannel0) {
            return notificationChannel0.getAudioAttributes();
        }

        static String getDescription(NotificationChannel notificationChannel0) {
            return notificationChannel0.getDescription();
        }

        static String getGroup(NotificationChannel notificationChannel0) {
            return notificationChannel0.getGroup();
        }

        static String getId(NotificationChannel notificationChannel0) {
            return notificationChannel0.getId();
        }

        static int getImportance(NotificationChannel notificationChannel0) {
            return notificationChannel0.getImportance();
        }

        static int getLightColor(NotificationChannel notificationChannel0) {
            return notificationChannel0.getLightColor();
        }

        static int getLockscreenVisibility(NotificationChannel notificationChannel0) {
            return notificationChannel0.getLockscreenVisibility();
        }

        static CharSequence getName(NotificationChannel notificationChannel0) {
            return notificationChannel0.getName();
        }

        static Uri getSound(NotificationChannel notificationChannel0) {
            return notificationChannel0.getSound();
        }

        static long[] getVibrationPattern(NotificationChannel notificationChannel0) {
            return notificationChannel0.getVibrationPattern();
        }

        static void setDescription(NotificationChannel notificationChannel0, String s) {
            notificationChannel0.setDescription(s);
        }

        static void setGroup(NotificationChannel notificationChannel0, String s) {
            notificationChannel0.setGroup(s);
        }

        static void setLightColor(NotificationChannel notificationChannel0, int v) {
            notificationChannel0.setLightColor(v);
        }

        static void setShowBadge(NotificationChannel notificationChannel0, boolean z) {
            notificationChannel0.setShowBadge(z);
        }

        static void setSound(NotificationChannel notificationChannel0, Uri uri0, AudioAttributes audioAttributes0) {
            notificationChannel0.setSound(uri0, audioAttributes0);
        }

        static void setVibrationPattern(NotificationChannel notificationChannel0, long[] arr_v) {
            notificationChannel0.setVibrationPattern(arr_v);
        }

        static boolean shouldShowLights(NotificationChannel notificationChannel0) {
            return notificationChannel0.shouldShowLights();
        }

        static boolean shouldVibrate(NotificationChannel notificationChannel0) {
            return notificationChannel0.shouldVibrate();
        }
    }

    static class Api29Impl {
        static boolean canBubble(NotificationChannel notificationChannel0) {
            return notificationChannel0.canBubble();
        }
    }

    static class Api30Impl {
        static String getConversationId(NotificationChannel notificationChannel0) {
            return notificationChannel0.getConversationId();
        }

        static String getParentChannelId(NotificationChannel notificationChannel0) {
            return notificationChannel0.getParentChannelId();
        }

        static boolean isImportantConversation(NotificationChannel notificationChannel0) {
            return notificationChannel0.isImportantConversation();
        }

        static void setConversationId(NotificationChannel notificationChannel0, String s, String s1) {
            notificationChannel0.setConversationId(s, s1);
        }
    }

    public static class Builder {
        private final NotificationChannelCompat mChannel;

        public Builder(String s, int v) {
            this.mChannel = new NotificationChannelCompat(s, v);
        }

        public NotificationChannelCompat build() {
            return this.mChannel;
        }

        public Builder setConversationId(String s, String s1) {
            if(Build.VERSION.SDK_INT >= 30) {
                this.mChannel.mParentId = s;
                this.mChannel.mConversationId = s1;
            }
            return this;
        }

        public Builder setDescription(String s) {
            this.mChannel.mDescription = s;
            return this;
        }

        public Builder setGroup(String s) {
            this.mChannel.mGroupId = s;
            return this;
        }

        public Builder setImportance(int v) {
            this.mChannel.mImportance = v;
            return this;
        }

        public Builder setLightColor(int v) {
            this.mChannel.mLightColor = v;
            return this;
        }

        public Builder setLightsEnabled(boolean z) {
            this.mChannel.mLights = z;
            return this;
        }

        public Builder setName(CharSequence charSequence0) {
            this.mChannel.mName = charSequence0;
            return this;
        }

        public Builder setShowBadge(boolean z) {
            this.mChannel.mShowBadge = z;
            return this;
        }

        public Builder setSound(Uri uri0, AudioAttributes audioAttributes0) {
            this.mChannel.mSound = uri0;
            this.mChannel.mAudioAttributes = audioAttributes0;
            return this;
        }

        public Builder setVibrationEnabled(boolean z) {
            this.mChannel.mVibrationEnabled = z;
            return this;
        }

        public Builder setVibrationPattern(long[] arr_v) {
            this.mChannel.mVibrationEnabled = arr_v != null && arr_v.length > 0;
            this.mChannel.mVibrationPattern = arr_v;
            return this;
        }
    }

    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    private static final int DEFAULT_LIGHT_COLOR = 0;
    private static final boolean DEFAULT_SHOW_BADGE = true;
    AudioAttributes mAudioAttributes;
    private boolean mBypassDnd;
    private boolean mCanBubble;
    String mConversationId;
    String mDescription;
    String mGroupId;
    final String mId;
    int mImportance;
    private boolean mImportantConversation;
    int mLightColor;
    boolean mLights;
    private int mLockscreenVisibility;
    CharSequence mName;
    String mParentId;
    boolean mShowBadge;
    Uri mSound;
    boolean mVibrationEnabled;
    long[] mVibrationPattern;

    NotificationChannelCompat(NotificationChannel notificationChannel0) {
        this(Api26Impl.getId(notificationChannel0), Api26Impl.getImportance(notificationChannel0));
        this.mName = Api26Impl.getName(notificationChannel0);
        this.mDescription = Api26Impl.getDescription(notificationChannel0);
        this.mGroupId = Api26Impl.getGroup(notificationChannel0);
        this.mShowBadge = Api26Impl.canShowBadge(notificationChannel0);
        this.mSound = Api26Impl.getSound(notificationChannel0);
        this.mAudioAttributes = Api26Impl.getAudioAttributes(notificationChannel0);
        this.mLights = Api26Impl.shouldShowLights(notificationChannel0);
        this.mLightColor = Api26Impl.getLightColor(notificationChannel0);
        this.mVibrationEnabled = Api26Impl.shouldVibrate(notificationChannel0);
        this.mVibrationPattern = Api26Impl.getVibrationPattern(notificationChannel0);
        if(Build.VERSION.SDK_INT >= 30) {
            this.mParentId = Api30Impl.getParentChannelId(notificationChannel0);
            this.mConversationId = Api30Impl.getConversationId(notificationChannel0);
        }
        this.mBypassDnd = Api26Impl.canBypassDnd(notificationChannel0);
        this.mLockscreenVisibility = Api26Impl.getLockscreenVisibility(notificationChannel0);
        if(Build.VERSION.SDK_INT >= 29) {
            this.mCanBubble = Api29Impl.canBubble(notificationChannel0);
        }
        if(Build.VERSION.SDK_INT >= 30) {
            this.mImportantConversation = Api30Impl.isImportantConversation(notificationChannel0);
        }
    }

    NotificationChannelCompat(String s, int v) {
        this.mShowBadge = true;
        this.mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.mLightColor = 0;
        this.mId = (String)Preconditions.checkNotNull(s);
        this.mImportance = v;
        this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
    }

    public boolean canBubble() {
        return this.mCanBubble;
    }

    public boolean canBypassDnd() {
        return this.mBypassDnd;
    }

    public boolean canShowBadge() {
        return this.mShowBadge;
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public String getConversationId() {
        return this.mConversationId;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getGroup() {
        return this.mGroupId;
    }

    public String getId() {
        return this.mId;
    }

    public int getImportance() {
        return this.mImportance;
    }

    public int getLightColor() {
        return this.mLightColor;
    }

    public int getLockscreenVisibility() {
        return this.mLockscreenVisibility;
    }

    public CharSequence getName() {
        return this.mName;
    }

    NotificationChannel getNotificationChannel() {
        if(Build.VERSION.SDK_INT < 26) {
            return null;
        }
        NotificationChannel notificationChannel0 = Api26Impl.createNotificationChannel(this.mId, this.mName, this.mImportance);
        Api26Impl.setDescription(notificationChannel0, this.mDescription);
        Api26Impl.setGroup(notificationChannel0, this.mGroupId);
        Api26Impl.setShowBadge(notificationChannel0, this.mShowBadge);
        Api26Impl.setSound(notificationChannel0, this.mSound, this.mAudioAttributes);
        Api26Impl.enableLights(notificationChannel0, this.mLights);
        Api26Impl.setLightColor(notificationChannel0, this.mLightColor);
        Api26Impl.setVibrationPattern(notificationChannel0, this.mVibrationPattern);
        Api26Impl.enableVibration(notificationChannel0, this.mVibrationEnabled);
        if(Build.VERSION.SDK_INT >= 30) {
            String s = this.mParentId;
            if(s != null) {
                String s1 = this.mConversationId;
                if(s1 != null) {
                    Api30Impl.setConversationId(notificationChannel0, s, s1);
                }
            }
        }
        return notificationChannel0;
    }

    public String getParentChannelId() {
        return this.mParentId;
    }

    public Uri getSound() {
        return this.mSound;
    }

    public long[] getVibrationPattern() {
        return this.mVibrationPattern;
    }

    public boolean isImportantConversation() {
        return this.mImportantConversation;
    }

    public boolean shouldShowLights() {
        return this.mLights;
    }

    public boolean shouldVibrate() {
        return this.mVibrationEnabled;
    }

    public Builder toBuilder() {
        return new Builder(this.mId, this.mImportance).setName(this.mName).setDescription(this.mDescription).setGroup(this.mGroupId).setShowBadge(this.mShowBadge).setSound(this.mSound, this.mAudioAttributes).setLightsEnabled(this.mLights).setLightColor(this.mLightColor).setVibrationEnabled(this.mVibrationEnabled).setVibrationPattern(this.mVibrationPattern).setConversationId(this.mParentId, this.mConversationId);
    }
}

