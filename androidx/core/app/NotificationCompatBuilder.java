package androidx.core.app;

import android.app.Notification.Action.Builder;
import android.app.Notification.Action;
import android.app.Notification.BubbleMetadata;
import android.app.Notification.Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.app.RemoteInput;
import android.content.Context;
import android.content.LocusId;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.collection.ArraySet;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.List;

class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    static class Api20Impl {
        static Notification.Builder addAction(Notification.Builder notification$Builder0, Notification.Action notification$Action0) {
            return notification$Builder0.addAction(notification$Action0);
        }

        static Notification.Action.Builder addExtras(Notification.Action.Builder notification$Action$Builder0, Bundle bundle0) {
            return notification$Action$Builder0.addExtras(bundle0);
        }

        static Notification.Action.Builder addRemoteInput(Notification.Action.Builder notification$Action$Builder0, RemoteInput remoteInput0) {
            return notification$Action$Builder0.addRemoteInput(remoteInput0);
        }

        static Notification.Action build(Notification.Action.Builder notification$Action$Builder0) {
            return notification$Action$Builder0.build();
        }

        static Notification.Action.Builder createBuilder(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
            return new Notification.Action.Builder(v, charSequence0, pendingIntent0);
        }

        static String getGroup(Notification notification0) {
            return notification0.getGroup();
        }

        static Notification.Builder setGroup(Notification.Builder notification$Builder0, String s) {
            return notification$Builder0.setGroup(s);
        }

        static Notification.Builder setGroupSummary(Notification.Builder notification$Builder0, boolean z) {
            return notification$Builder0.setGroupSummary(z);
        }

        static Notification.Builder setLocalOnly(Notification.Builder notification$Builder0, boolean z) {
            return notification$Builder0.setLocalOnly(z);
        }

        static Notification.Builder setSortKey(Notification.Builder notification$Builder0, String s) {
            return notification$Builder0.setSortKey(s);
        }
    }

    static class Api21Impl {
        static Notification.Builder addPerson(Notification.Builder notification$Builder0, String s) {
            return notification$Builder0.addPerson(s);
        }

        static Notification.Builder setCategory(Notification.Builder notification$Builder0, String s) {
            return notification$Builder0.setCategory(s);
        }

        static Notification.Builder setColor(Notification.Builder notification$Builder0, int v) {
            return notification$Builder0.setColor(v);
        }

        static Notification.Builder setPublicVersion(Notification.Builder notification$Builder0, Notification notification0) {
            return notification$Builder0.setPublicVersion(notification0);
        }

        static Notification.Builder setSound(Notification.Builder notification$Builder0, Uri uri0, Object object0) {
            return notification$Builder0.setSound(uri0, ((AudioAttributes)object0));
        }

        static Notification.Builder setVisibility(Notification.Builder notification$Builder0, int v) {
            return notification$Builder0.setVisibility(v);
        }
    }

    static class Api23Impl {
        static Notification.Action.Builder createBuilder(Icon icon0, CharSequence charSequence0, PendingIntent pendingIntent0) {
            return new Notification.Action.Builder(icon0, charSequence0, pendingIntent0);
        }

        static Notification.Builder setLargeIcon(Notification.Builder notification$Builder0, Icon icon0) {
            return notification$Builder0.setLargeIcon(icon0);
        }

        static Notification.Builder setSmallIcon(Notification.Builder notification$Builder0, Object object0) {
            return notification$Builder0.setSmallIcon(((Icon)object0));
        }
    }

    static class Api24Impl {
        static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder notification$Action$Builder0, boolean z) {
            return notification$Action$Builder0.setAllowGeneratedReplies(z);
        }

        static Notification.Builder setCustomBigContentView(Notification.Builder notification$Builder0, RemoteViews remoteViews0) {
            return notification$Builder0.setCustomBigContentView(remoteViews0);
        }

        static Notification.Builder setCustomContentView(Notification.Builder notification$Builder0, RemoteViews remoteViews0) {
            return notification$Builder0.setCustomContentView(remoteViews0);
        }

        static Notification.Builder setCustomHeadsUpContentView(Notification.Builder notification$Builder0, RemoteViews remoteViews0) {
            return notification$Builder0.setCustomHeadsUpContentView(remoteViews0);
        }

        static Notification.Builder setRemoteInputHistory(Notification.Builder notification$Builder0, CharSequence[] arr_charSequence) {
            return notification$Builder0.setRemoteInputHistory(arr_charSequence);
        }
    }

    static class Api26Impl {
        static Notification.Builder createBuilder(Context context0, String s) {
            return new Notification.Builder(context0, s);
        }

        static Notification.Builder setBadgeIconType(Notification.Builder notification$Builder0, int v) {
            return notification$Builder0.setBadgeIconType(v);
        }

        static Notification.Builder setColorized(Notification.Builder notification$Builder0, boolean z) {
            return notification$Builder0.setColorized(z);
        }

        static Notification.Builder setGroupAlertBehavior(Notification.Builder notification$Builder0, int v) {
            return notification$Builder0.setGroupAlertBehavior(v);
        }

        static Notification.Builder setSettingsText(Notification.Builder notification$Builder0, CharSequence charSequence0) {
            return notification$Builder0.setSettingsText(charSequence0);
        }

        static Notification.Builder setShortcutId(Notification.Builder notification$Builder0, String s) {
            return notification$Builder0.setShortcutId(s);
        }

        static Notification.Builder setTimeoutAfter(Notification.Builder notification$Builder0, long v) {
            return notification$Builder0.setTimeoutAfter(v);
        }
    }

    static class Api28Impl {
        static Notification.Builder addPerson(Notification.Builder notification$Builder0, Person person0) {
            return notification$Builder0.addPerson(person0);
        }

        static Notification.Action.Builder setSemanticAction(Notification.Action.Builder notification$Action$Builder0, int v) {
            return notification$Action$Builder0.setSemanticAction(v);
        }
    }

    static class Api29Impl {
        static Notification.Builder setAllowSystemGeneratedContextualActions(Notification.Builder notification$Builder0, boolean z) {
            return notification$Builder0.setAllowSystemGeneratedContextualActions(z);
        }

        static Notification.Builder setBubbleMetadata(Notification.Builder notification$Builder0, Notification.BubbleMetadata notification$BubbleMetadata0) {
            return notification$Builder0.setBubbleMetadata(notification$BubbleMetadata0);
        }

        static Notification.Action.Builder setContextual(Notification.Action.Builder notification$Action$Builder0, boolean z) {
            return notification$Action$Builder0.setContextual(z);
        }

        static Notification.Builder setLocusId(Notification.Builder notification$Builder0, Object object0) {
            return notification$Builder0.setLocusId(((LocusId)object0));
        }
    }

    static class Api31Impl {
        static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder notification$Action$Builder0, boolean z) {
            return notification$Action$Builder0.setAuthenticationRequired(z);
        }

        static Notification.Builder setForegroundServiceBehavior(Notification.Builder notification$Builder0, int v) {
            return notification$Builder0.setForegroundServiceBehavior(v);
        }
    }

    private final List mActionExtrasList;
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final Builder mBuilderCompat;
    private RemoteViews mContentView;
    private final Context mContext;
    private final Bundle mExtras;
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;

    NotificationCompatBuilder(Builder notificationCompat$Builder0) {
        this.mActionExtrasList = new ArrayList();
        this.mExtras = new Bundle();
        this.mBuilderCompat = notificationCompat$Builder0;
        Context context0 = notificationCompat$Builder0.mContext;
        this.mContext = context0;
        this.mBuilder = Build.VERSION.SDK_INT >= 26 ? Api26Impl.createBuilder(notificationCompat$Builder0.mContext, notificationCompat$Builder0.mChannelId) : new Notification.Builder(notificationCompat$Builder0.mContext);
        Notification notification0 = notificationCompat$Builder0.mNotification;
        this.mBuilder.setWhen(notification0.when).setSmallIcon(notification0.icon, notification0.iconLevel).setContent(notification0.contentView).setTicker(notification0.tickerText, notificationCompat$Builder0.mTickerView).setVibrate(notification0.vibrate).setLights(notification0.ledARGB, notification0.ledOnMS, notification0.ledOffMS).setOngoing((notification0.flags & 2) != 0).setOnlyAlertOnce((notification0.flags & 8) != 0).setAutoCancel((notification0.flags & 16) != 0).setDefaults(notification0.defaults).setContentTitle(notificationCompat$Builder0.mContentTitle).setContentText(notificationCompat$Builder0.mContentText).setContentInfo(notificationCompat$Builder0.mContentInfo).setContentIntent(notificationCompat$Builder0.mContentIntent).setDeleteIntent(notification0.deleteIntent).setFullScreenIntent(notificationCompat$Builder0.mFullScreenIntent, (notification0.flags & 0x80) != 0).setNumber(notificationCompat$Builder0.mNumber).setProgress(notificationCompat$Builder0.mProgressMax, notificationCompat$Builder0.mProgress, notificationCompat$Builder0.mProgressIndeterminate);
        if(Build.VERSION.SDK_INT < 23) {
            Bitmap bitmap0 = notificationCompat$Builder0.mLargeIcon == null ? null : notificationCompat$Builder0.mLargeIcon.getBitmap();
            this.mBuilder.setLargeIcon(bitmap0);
        }
        else {
            Icon icon0 = notificationCompat$Builder0.mLargeIcon == null ? null : notificationCompat$Builder0.mLargeIcon.toIcon(context0);
            Api23Impl.setLargeIcon(this.mBuilder, icon0);
        }
        this.mBuilder.setSubText(notificationCompat$Builder0.mSubText).setUsesChronometer(notificationCompat$Builder0.mUseChronometer).setPriority(notificationCompat$Builder0.mPriority);
        if(notificationCompat$Builder0.mStyle instanceof CallStyle) {
            for(Object object0: ((CallStyle)notificationCompat$Builder0.mStyle).getActionsListWithSystemActions()) {
                this.addAction(((Action)object0));
            }
        }
        else {
            for(Object object1: notificationCompat$Builder0.mActions) {
                this.addAction(((Action)object1));
            }
        }
        if(notificationCompat$Builder0.mExtras != null) {
            this.mExtras.putAll(notificationCompat$Builder0.mExtras);
        }
        this.mContentView = notificationCompat$Builder0.mContentView;
        this.mBigContentView = notificationCompat$Builder0.mBigContentView;
        this.mBuilder.setShowWhen(notificationCompat$Builder0.mShowWhen);
        Api20Impl.setLocalOnly(this.mBuilder, notificationCompat$Builder0.mLocalOnly);
        Api20Impl.setGroup(this.mBuilder, notificationCompat$Builder0.mGroupKey);
        Api20Impl.setSortKey(this.mBuilder, notificationCompat$Builder0.mSortKey);
        Api20Impl.setGroupSummary(this.mBuilder, notificationCompat$Builder0.mGroupSummary);
        this.mGroupAlertBehavior = notificationCompat$Builder0.mGroupAlertBehavior;
        Api21Impl.setCategory(this.mBuilder, notificationCompat$Builder0.mCategory);
        Api21Impl.setColor(this.mBuilder, notificationCompat$Builder0.mColor);
        Api21Impl.setVisibility(this.mBuilder, notificationCompat$Builder0.mVisibility);
        Api21Impl.setPublicVersion(this.mBuilder, notificationCompat$Builder0.mPublicVersion);
        Api21Impl.setSound(this.mBuilder, notification0.sound, notification0.audioAttributes);
        List list0 = Build.VERSION.SDK_INT < 28 ? NotificationCompatBuilder.combineLists(NotificationCompatBuilder.getPeople(notificationCompat$Builder0.mPersonList), notificationCompat$Builder0.mPeople) : notificationCompat$Builder0.mPeople;
        if(list0 != null && !list0.isEmpty()) {
            for(Object object2: list0) {
                Api21Impl.addPerson(this.mBuilder, ((String)object2));
            }
        }
        this.mHeadsUpContentView = notificationCompat$Builder0.mHeadsUpContentView;
        if(notificationCompat$Builder0.mInvisibleActions.size() > 0) {
            Bundle bundle0 = notificationCompat$Builder0.getExtras().getBundle("android.car.EXTENSIONS");
            if(bundle0 == null) {
                bundle0 = new Bundle();
            }
            Bundle bundle1 = new Bundle(bundle0);
            Bundle bundle2 = new Bundle();
            for(int v = 0; v < notificationCompat$Builder0.mInvisibleActions.size(); ++v) {
                bundle2.putBundle(Integer.toString(v), NotificationCompatJellybean.getBundleForAction(((Action)notificationCompat$Builder0.mInvisibleActions.get(v))));
            }
            bundle0.putBundle("invisible_actions", bundle2);
            bundle1.putBundle("invisible_actions", bundle2);
            notificationCompat$Builder0.getExtras().putBundle("android.car.EXTENSIONS", bundle0);
            this.mExtras.putBundle("android.car.EXTENSIONS", bundle1);
        }
        if(Build.VERSION.SDK_INT >= 23 && notificationCompat$Builder0.mSmallIcon != null) {
            Api23Impl.setSmallIcon(this.mBuilder, notificationCompat$Builder0.mSmallIcon);
        }
        if(Build.VERSION.SDK_INT >= 24) {
            this.mBuilder.setExtras(notificationCompat$Builder0.mExtras);
            Api24Impl.setRemoteInputHistory(this.mBuilder, notificationCompat$Builder0.mRemoteInputHistory);
            if(notificationCompat$Builder0.mContentView != null) {
                Api24Impl.setCustomContentView(this.mBuilder, notificationCompat$Builder0.mContentView);
            }
            if(notificationCompat$Builder0.mBigContentView != null) {
                Api24Impl.setCustomBigContentView(this.mBuilder, notificationCompat$Builder0.mBigContentView);
            }
            if(notificationCompat$Builder0.mHeadsUpContentView != null) {
                Api24Impl.setCustomHeadsUpContentView(this.mBuilder, notificationCompat$Builder0.mHeadsUpContentView);
            }
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setBadgeIconType(this.mBuilder, notificationCompat$Builder0.mBadgeIcon);
            Api26Impl.setSettingsText(this.mBuilder, notificationCompat$Builder0.mSettingsText);
            Api26Impl.setShortcutId(this.mBuilder, notificationCompat$Builder0.mShortcutId);
            Api26Impl.setTimeoutAfter(this.mBuilder, notificationCompat$Builder0.mTimeout);
            Api26Impl.setGroupAlertBehavior(this.mBuilder, notificationCompat$Builder0.mGroupAlertBehavior);
            if(notificationCompat$Builder0.mColorizedSet) {
                Api26Impl.setColorized(this.mBuilder, notificationCompat$Builder0.mColorized);
            }
            if(!TextUtils.isEmpty(notificationCompat$Builder0.mChannelId)) {
                this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if(Build.VERSION.SDK_INT >= 28) {
            for(Object object3: notificationCompat$Builder0.mPersonList) {
                Person person0 = ((androidx.core.app.Person)object3).toAndroidPerson();
                Api28Impl.addPerson(this.mBuilder, person0);
            }
        }
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setAllowSystemGeneratedContextualActions(this.mBuilder, notificationCompat$Builder0.mAllowSystemGeneratedContextualActions);
            Notification.BubbleMetadata notification$BubbleMetadata0 = BubbleMetadata.toPlatform(notificationCompat$Builder0.mBubbleMetadata);
            Api29Impl.setBubbleMetadata(this.mBuilder, notification$BubbleMetadata0);
            if(notificationCompat$Builder0.mLocusId != null) {
                Api29Impl.setLocusId(this.mBuilder, notificationCompat$Builder0.mLocusId.toLocusId());
            }
        }
        if(Build.VERSION.SDK_INT >= 0x1F && notificationCompat$Builder0.mFgsDeferBehavior != 0) {
            Api31Impl.setForegroundServiceBehavior(this.mBuilder, notificationCompat$Builder0.mFgsDeferBehavior);
        }
        if(notificationCompat$Builder0.mSilent) {
            this.mGroupAlertBehavior = this.mBuilderCompat.mGroupSummary ? 2 : 1;
            this.mBuilder.setVibrate(null);
            this.mBuilder.setSound(null);
            notification0.defaults &= -3;
            this.mBuilder.setDefaults(notification0.defaults);
            if(Build.VERSION.SDK_INT >= 26) {
                if(TextUtils.isEmpty(this.mBuilderCompat.mGroupKey)) {
                    Api20Impl.setGroup(this.mBuilder, "silent");
                }
                Api26Impl.setGroupAlertBehavior(this.mBuilder, this.mGroupAlertBehavior);
            }
        }
    }

    private void addAction(Action notificationCompat$Action0) {
        IconCompat iconCompat0 = notificationCompat$Action0.getIconCompat();
        Notification.Action.Builder notification$Action$Builder0 = Build.VERSION.SDK_INT < 23 ? Api20Impl.createBuilder((iconCompat0 == null ? 0 : iconCompat0.getResId()), notificationCompat$Action0.getTitle(), notificationCompat$Action0.getActionIntent()) : Api23Impl.createBuilder((iconCompat0 == null ? null : iconCompat0.toIcon()), notificationCompat$Action0.getTitle(), notificationCompat$Action0.getActionIntent());
        if(notificationCompat$Action0.getRemoteInputs() != null) {
            RemoteInput[] arr_remoteInput = androidx.core.app.RemoteInput.fromCompat(notificationCompat$Action0.getRemoteInputs());
            for(int v = 0; v < arr_remoteInput.length; ++v) {
                Api20Impl.addRemoteInput(notification$Action$Builder0, arr_remoteInput[v]);
            }
        }
        Bundle bundle0 = notificationCompat$Action0.getExtras() == null ? new Bundle() : new Bundle(notificationCompat$Action0.getExtras());
        bundle0.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action0.getAllowGeneratedReplies());
        if(Build.VERSION.SDK_INT >= 24) {
            Api24Impl.setAllowGeneratedReplies(notification$Action$Builder0, notificationCompat$Action0.getAllowGeneratedReplies());
        }
        bundle0.putInt("android.support.action.semanticAction", notificationCompat$Action0.getSemanticAction());
        if(Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setSemanticAction(notification$Action$Builder0, notificationCompat$Action0.getSemanticAction());
        }
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setContextual(notification$Action$Builder0, notificationCompat$Action0.isContextual());
        }
        if(Build.VERSION.SDK_INT >= 0x1F) {
            Api31Impl.setAuthenticationRequired(notification$Action$Builder0, notificationCompat$Action0.isAuthenticationRequired());
        }
        bundle0.putBoolean("android.support.action.showsUserInterface", notificationCompat$Action0.getShowsUserInterface());
        Api20Impl.addExtras(notification$Action$Builder0, bundle0);
        Notification.Action notification$Action0 = Api20Impl.build(notification$Action$Builder0);
        Api20Impl.addAction(this.mBuilder, notification$Action0);
    }

    public Notification build() {
        Style notificationCompat$Style0 = this.mBuilderCompat.mStyle;
        if(notificationCompat$Style0 != null) {
            notificationCompat$Style0.apply(this);
        }
        RemoteViews remoteViews0 = notificationCompat$Style0 == null ? null : notificationCompat$Style0.makeContentView(this);
        Notification notification0 = this.buildInternal();
        if(remoteViews0 != null) {
            notification0.contentView = remoteViews0;
        }
        else if(this.mBuilderCompat.mContentView != null) {
            notification0.contentView = this.mBuilderCompat.mContentView;
        }
        if(notificationCompat$Style0 != null) {
            RemoteViews remoteViews1 = notificationCompat$Style0.makeBigContentView(this);
            if(remoteViews1 != null) {
                notification0.bigContentView = remoteViews1;
            }
        }
        if(notificationCompat$Style0 != null) {
            RemoteViews remoteViews2 = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this);
            if(remoteViews2 != null) {
                notification0.headsUpContentView = remoteViews2;
            }
        }
        if(notificationCompat$Style0 != null) {
            Bundle bundle0 = NotificationCompat.getExtras(notification0);
            if(bundle0 != null) {
                notificationCompat$Style0.addCompatExtras(bundle0);
            }
        }
        return notification0;
    }

    protected Notification buildInternal() {
        if(Build.VERSION.SDK_INT >= 26) {
            return this.mBuilder.build();
        }
        if(Build.VERSION.SDK_INT >= 24) {
            Notification notification0 = this.mBuilder.build();
            if(this.mGroupAlertBehavior != 0) {
                if(Api20Impl.getGroup(notification0) != null && (notification0.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2) {
                    this.removeSoundAndVibration(notification0);
                }
                if(Api20Impl.getGroup(notification0) != null && (notification0.flags & 0x200) == 0 && this.mGroupAlertBehavior == 1) {
                    this.removeSoundAndVibration(notification0);
                }
            }
            return notification0;
        }
        this.mBuilder.setExtras(this.mExtras);
        Notification notification1 = this.mBuilder.build();
        RemoteViews remoteViews0 = this.mContentView;
        if(remoteViews0 != null) {
            notification1.contentView = remoteViews0;
        }
        RemoteViews remoteViews1 = this.mBigContentView;
        if(remoteViews1 != null) {
            notification1.bigContentView = remoteViews1;
        }
        RemoteViews remoteViews2 = this.mHeadsUpContentView;
        if(remoteViews2 != null) {
            notification1.headsUpContentView = remoteViews2;
        }
        if(this.mGroupAlertBehavior != 0) {
            if(Api20Impl.getGroup(notification1) != null && (notification1.flags & 0x200) != 0 && this.mGroupAlertBehavior == 2) {
                this.removeSoundAndVibration(notification1);
            }
            if(Api20Impl.getGroup(notification1) != null && (notification1.flags & 0x200) == 0 && this.mGroupAlertBehavior == 1) {
                this.removeSoundAndVibration(notification1);
            }
        }
        return notification1;
    }

    private static List combineLists(List list0, List list1) {
        if(list0 == null) {
            return list1;
        }
        if(list1 == null) {
            return list0;
        }
        ArraySet arraySet0 = new ArraySet(list0.size() + list1.size());
        arraySet0.addAll(list0);
        arraySet0.addAll(list1);
        return new ArrayList(arraySet0);
    }

    @Override  // androidx.core.app.NotificationBuilderWithBuilderAccessor
    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }

    Context getContext() {
        return this.mContext;
    }

    private static List getPeople(List list0) {
        if(list0 == null) {
            return null;
        }
        List list1 = new ArrayList(list0.size());
        for(Object object0: list0) {
            ((ArrayList)list1).add(((androidx.core.app.Person)object0).resolveToLegacyUri());
        }
        return list1;
    }

    private void removeSoundAndVibration(Notification notification0) {
        notification0.sound = null;
        notification0.vibrate = null;
        notification0.defaults &= -3;
    }
}

