package androidx.core.app;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Build.VERSION;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationChannelGroupCompat {
    static class Api26Impl {
        static NotificationChannelGroup createNotificationChannelGroup(String s, CharSequence charSequence0) {
            return new NotificationChannelGroup(s, charSequence0);
        }

        static List getChannels(NotificationChannelGroup notificationChannelGroup0) {
            return notificationChannelGroup0.getChannels();
        }

        static String getGroup(NotificationChannel notificationChannel0) {
            return notificationChannel0.getGroup();
        }

        static String getId(NotificationChannelGroup notificationChannelGroup0) {
            return notificationChannelGroup0.getId();
        }

        static CharSequence getName(NotificationChannelGroup notificationChannelGroup0) {
            return notificationChannelGroup0.getName();
        }
    }

    static class Api28Impl {
        static String getDescription(NotificationChannelGroup notificationChannelGroup0) {
            return notificationChannelGroup0.getDescription();
        }

        static boolean isBlocked(NotificationChannelGroup notificationChannelGroup0) {
            return notificationChannelGroup0.isBlocked();
        }

        static void setDescription(NotificationChannelGroup notificationChannelGroup0, String s) {
            notificationChannelGroup0.setDescription(s);
        }
    }

    public static class Builder {
        final NotificationChannelGroupCompat mGroup;

        public Builder(String s) {
            this.mGroup = new NotificationChannelGroupCompat(s);
        }

        public NotificationChannelGroupCompat build() {
            return this.mGroup;
        }

        public Builder setDescription(String s) {
            this.mGroup.mDescription = s;
            return this;
        }

        public Builder setName(CharSequence charSequence0) {
            this.mGroup.mName = charSequence0;
            return this;
        }
    }

    private boolean mBlocked;
    private List mChannels;
    String mDescription;
    final String mId;
    CharSequence mName;

    NotificationChannelGroupCompat(NotificationChannelGroup notificationChannelGroup0) {
        this(notificationChannelGroup0, Collections.EMPTY_LIST);
    }

    NotificationChannelGroupCompat(NotificationChannelGroup notificationChannelGroup0, List list0) {
        this(Api26Impl.getId(notificationChannelGroup0));
        this.mName = Api26Impl.getName(notificationChannelGroup0);
        if(Build.VERSION.SDK_INT >= 28) {
            this.mDescription = Api28Impl.getDescription(notificationChannelGroup0);
        }
        if(Build.VERSION.SDK_INT >= 28) {
            this.mBlocked = Api28Impl.isBlocked(notificationChannelGroup0);
            this.mChannels = this.getChannelsCompat(Api26Impl.getChannels(notificationChannelGroup0));
            return;
        }
        this.mChannels = this.getChannelsCompat(list0);
    }

    NotificationChannelGroupCompat(String s) {
        this.mChannels = Collections.EMPTY_LIST;
        this.mId = (String)Preconditions.checkNotNull(s);
    }

    public List getChannels() {
        return this.mChannels;
    }

    private List getChannelsCompat(List list0) {
        List list1 = new ArrayList();
        for(Object object0: list0) {
            NotificationChannel notificationChannel0 = (NotificationChannel)object0;
            String s = Api26Impl.getGroup(notificationChannel0);
            if(this.mId.equals(s)) {
                list1.add(new NotificationChannelCompat(notificationChannel0));
            }
        }
        return list1;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getId() {
        return this.mId;
    }

    public CharSequence getName() {
        return this.mName;
    }

    NotificationChannelGroup getNotificationChannelGroup() {
        if(Build.VERSION.SDK_INT < 26) {
            return null;
        }
        NotificationChannelGroup notificationChannelGroup0 = Api26Impl.createNotificationChannelGroup(this.mId, this.mName);
        if(Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setDescription(notificationChannelGroup0, this.mDescription);
        }
        return notificationChannelGroup0;
    }

    public boolean isBlocked() {
        return this.mBlocked;
    }

    public Builder toBuilder() {
        return new Builder(this.mId).setName(this.mName).setDescription(this.mDescription);
    }
}

