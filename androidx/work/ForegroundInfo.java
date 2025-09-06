package androidx.work;

import android.app.Notification;

public final class ForegroundInfo {
    private final int mForegroundServiceType;
    private final Notification mNotification;
    private final int mNotificationId;

    public ForegroundInfo(int v, Notification notification0) {
        this(v, notification0, 0);
    }

    public ForegroundInfo(int v, Notification notification0, int v1) {
        this.mNotificationId = v;
        this.mNotification = notification0;
        this.mForegroundServiceType = v1;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null || this.getClass() != object0.getClass() || this.mNotificationId != ((ForegroundInfo)object0).mNotificationId) {
            return false;
        }
        return this.mForegroundServiceType == ((ForegroundInfo)object0).mForegroundServiceType ? this.mNotification.equals(((ForegroundInfo)object0).mNotification) : false;
    }

    public int getForegroundServiceType() {
        return this.mForegroundServiceType;
    }

    public Notification getNotification() {
        return this.mNotification;
    }

    public int getNotificationId() {
        return this.mNotificationId;
    }

    @Override
    public int hashCode() {
        return (this.mNotificationId * 0x1F + this.mForegroundServiceType) * 0x1F + this.mNotification.hashCode();
    }

    @Override
    public String toString() {
        return "ForegroundInfo{mNotificationId=" + this.mNotificationId + ", mForegroundServiceType=" + this.mForegroundServiceType + ", mNotification=" + this.mNotification + '}';
    }
}

