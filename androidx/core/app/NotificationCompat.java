package androidx.core.app;

import android.app.Notification.Action.Builder;
import android.app.Notification.Action;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.BubbleMetadata.Builder;
import android.app.Notification.BubbleMetadata;
import android.app.Notification.Builder;
import android.app.Notification.CallStyle;
import android.app.Notification.DecoratedCustomViewStyle;
import android.app.Notification.InboxStyle;
import android.app.Notification.MessagingStyle.Message;
import android.app.Notification.MessagingStyle;
import android.app.Notification.Style;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput.Builder;
import android.app.RemoteInput;
import android.content.Context;
import android.content.LocusId;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes.Builder;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.annotation.ReplaceWith;
import androidx.core.R.color;
import androidx.core.R.dimen;
import androidx.core.R.id;
import androidx.core.R.integer;
import androidx.core.R.layout;
import androidx.core.R.string;
import androidx.core.content.ContextCompat;
import androidx.core.content.LocusIdCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.text.BidiFormatter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NotificationCompat {
    public static class Action {
        public static final class Builder {
            static class Api20Impl {
                static Bundle getExtras(Notification.Action notification$Action0) {
                    return notification$Action0.getExtras();
                }

                static RemoteInput[] getRemoteInputs(Notification.Action notification$Action0) {
                    return notification$Action0.getRemoteInputs();
                }
            }

            static class Api23Impl {
                static Icon getIcon(Notification.Action notification$Action0) {
                    return notification$Action0.getIcon();
                }
            }

            static class Api24Impl {
                static boolean getAllowGeneratedReplies(Notification.Action notification$Action0) {
                    return notification$Action0.getAllowGeneratedReplies();
                }
            }

            static class Api28Impl {
                static int getSemanticAction(Notification.Action notification$Action0) {
                    return notification$Action0.getSemanticAction();
                }
            }

            static class Api29Impl {
                static boolean isContextual(Notification.Action notification$Action0) {
                    return notification$Action0.isContextual();
                }
            }

            static class Api31Impl {
                static boolean isAuthenticationRequired(Notification.Action notification$Action0) {
                    return notification$Action0.isAuthenticationRequired();
                }
            }

            private boolean mAllowGeneratedReplies;
            private boolean mAuthenticationRequired;
            private final Bundle mExtras;
            private final IconCompat mIcon;
            private final PendingIntent mIntent;
            private boolean mIsContextual;
            private ArrayList mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private final CharSequence mTitle;

            public Builder(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
                this((v == 0 ? null : IconCompat.createWithResource(null, "", v)), charSequence0, pendingIntent0, new Bundle(), null, true, 0, true, false, false);
            }

            public Builder(Action notificationCompat$Action0) {
                this(notificationCompat$Action0.getIconCompat(), notificationCompat$Action0.title, notificationCompat$Action0.actionIntent, new Bundle(notificationCompat$Action0.mExtras), notificationCompat$Action0.getRemoteInputs(), notificationCompat$Action0.getAllowGeneratedReplies(), notificationCompat$Action0.getSemanticAction(), notificationCompat$Action0.mShowsUserInterface, notificationCompat$Action0.isContextual(), notificationCompat$Action0.isAuthenticationRequired());
            }

            public Builder(IconCompat iconCompat0, CharSequence charSequence0, PendingIntent pendingIntent0) {
                this(iconCompat0, charSequence0, pendingIntent0, new Bundle(), null, true, 0, true, false, false);
            }

            private Builder(IconCompat iconCompat0, CharSequence charSequence0, PendingIntent pendingIntent0, Bundle bundle0, androidx.core.app.RemoteInput[] arr_remoteInput, boolean z, int v, boolean z1, boolean z2, boolean z3) {
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = iconCompat0;
                this.mTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
                this.mIntent = pendingIntent0;
                this.mExtras = bundle0;
                this.mRemoteInputs = arr_remoteInput == null ? null : new ArrayList(Arrays.asList(arr_remoteInput));
                this.mAllowGeneratedReplies = z;
                this.mSemanticAction = v;
                this.mShowsUserInterface = z1;
                this.mIsContextual = z2;
                this.mAuthenticationRequired = z3;
            }

            public Builder addExtras(Bundle bundle0) {
                if(bundle0 != null) {
                    this.mExtras.putAll(bundle0);
                }
                return this;
            }

            public Builder addRemoteInput(androidx.core.app.RemoteInput remoteInput0) {
                if(this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList();
                }
                if(remoteInput0 != null) {
                    this.mRemoteInputs.add(remoteInput0);
                }
                return this;
            }

            public Action build() {
                this.checkContextualActionNullFields();
                ArrayList arrayList0 = new ArrayList();
                ArrayList arrayList1 = new ArrayList();
                ArrayList arrayList2 = this.mRemoteInputs;
                if(arrayList2 != null) {
                    for(Object object0: arrayList2) {
                        androidx.core.app.RemoteInput remoteInput0 = (androidx.core.app.RemoteInput)object0;
                        if(remoteInput0.isDataOnly()) {
                            arrayList0.add(remoteInput0);
                        }
                        else {
                            arrayList1.add(remoteInput0);
                        }
                    }
                }
                androidx.core.app.RemoteInput[] arr_remoteInput = null;
                androidx.core.app.RemoteInput[] arr_remoteInput1 = arrayList0.isEmpty() ? null : ((androidx.core.app.RemoteInput[])arrayList0.toArray(new androidx.core.app.RemoteInput[arrayList0.size()]));
                if(!arrayList1.isEmpty()) {
                    arr_remoteInput = (androidx.core.app.RemoteInput[])arrayList1.toArray(new androidx.core.app.RemoteInput[arrayList1.size()]);
                }
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arr_remoteInput, arr_remoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface, this.mIsContextual, this.mAuthenticationRequired);
            }

            private void checkContextualActionNullFields() {
                if(this.mIsContextual && this.mIntent == null) {
                    throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
                }
            }

            public Builder extend(Extender notificationCompat$Action$Extender0) {
                notificationCompat$Action$Extender0.extend(this);
                return this;
            }

            public static Builder fromAndroidAction(Notification.Action notification$Action0) {
                Builder notificationCompat$Action$Builder0 = Build.VERSION.SDK_INT < 23 || Api23Impl.getIcon(notification$Action0) == null ? new Builder(notification$Action0.icon, notification$Action0.title, notification$Action0.actionIntent) : new Builder(IconCompat.createFromIconOrNullIfZeroResId(Api23Impl.getIcon(notification$Action0)), notification$Action0.title, notification$Action0.actionIntent);
                RemoteInput[] arr_remoteInput = Api20Impl.getRemoteInputs(notification$Action0);
                if(arr_remoteInput != null && arr_remoteInput.length != 0) {
                    for(int v = 0; v < arr_remoteInput.length; ++v) {
                        notificationCompat$Action$Builder0.addRemoteInput(androidx.core.app.RemoteInput.fromPlatform(arr_remoteInput[v]));
                    }
                }
                if(Build.VERSION.SDK_INT >= 24) {
                    notificationCompat$Action$Builder0.mAllowGeneratedReplies = Api24Impl.getAllowGeneratedReplies(notification$Action0);
                }
                if(Build.VERSION.SDK_INT >= 28) {
                    notificationCompat$Action$Builder0.setSemanticAction(Api28Impl.getSemanticAction(notification$Action0));
                }
                if(Build.VERSION.SDK_INT >= 29) {
                    notificationCompat$Action$Builder0.setContextual(Api29Impl.isContextual(notification$Action0));
                }
                if(Build.VERSION.SDK_INT >= 0x1F) {
                    notificationCompat$Action$Builder0.setAuthenticationRequired(Api31Impl.isAuthenticationRequired(notification$Action0));
                }
                notificationCompat$Action$Builder0.addExtras(Api20Impl.getExtras(notification$Action0));
                return notificationCompat$Action$Builder0;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder setAllowGeneratedReplies(boolean z) {
                this.mAllowGeneratedReplies = z;
                return this;
            }

            public Builder setAuthenticationRequired(boolean z) {
                this.mAuthenticationRequired = z;
                return this;
            }

            public Builder setContextual(boolean z) {
                this.mIsContextual = z;
                return this;
            }

            public Builder setSemanticAction(int v) {
                this.mSemanticAction = v;
                return this;
            }

            public Builder setShowsUserInterface(boolean z) {
                this.mShowsUserInterface = z;
                return this;
            }
        }

        public interface Extender {
            Builder extend(Builder arg1);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags;
            private CharSequence mInProgressLabel;

            public WearableExtender() {
                this.mFlags = 1;
            }

            public WearableExtender(Action notificationCompat$Action0) {
                this.mFlags = 1;
                Bundle bundle0 = notificationCompat$Action0.getExtras().getBundle("android.wearable.EXTENSIONS");
                if(bundle0 != null) {
                    this.mFlags = bundle0.getInt("flags", 1);
                    this.mInProgressLabel = bundle0.getCharSequence("inProgressLabel");
                    this.mConfirmLabel = bundle0.getCharSequence("confirmLabel");
                    this.mCancelLabel = bundle0.getCharSequence("cancelLabel");
                }
            }

            public WearableExtender clone() {
                WearableExtender notificationCompat$Action$WearableExtender0 = new WearableExtender();
                notificationCompat$Action$WearableExtender0.mFlags = this.mFlags;
                notificationCompat$Action$WearableExtender0.mInProgressLabel = this.mInProgressLabel;
                notificationCompat$Action$WearableExtender0.mConfirmLabel = this.mConfirmLabel;
                notificationCompat$Action$WearableExtender0.mCancelLabel = this.mCancelLabel;
                return notificationCompat$Action$WearableExtender0;
            }

            @Override
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            @Override  // androidx.core.app.NotificationCompat$Action$Extender
            public Builder extend(Builder notificationCompat$Action$Builder0) {
                Bundle bundle0 = new Bundle();
                int v = this.mFlags;
                if(v != 1) {
                    bundle0.putInt("flags", v);
                }
                CharSequence charSequence0 = this.mInProgressLabel;
                if(charSequence0 != null) {
                    bundle0.putCharSequence("inProgressLabel", charSequence0);
                }
                CharSequence charSequence1 = this.mConfirmLabel;
                if(charSequence1 != null) {
                    bundle0.putCharSequence("confirmLabel", charSequence1);
                }
                CharSequence charSequence2 = this.mCancelLabel;
                if(charSequence2 != null) {
                    bundle0.putCharSequence("cancelLabel", charSequence2);
                }
                notificationCompat$Action$Builder0.getExtras().putBundle("android.wearable.EXTENSIONS", bundle0);
                return notificationCompat$Action$Builder0;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            public WearableExtender setAvailableOffline(boolean z) {
                this.setFlag(1, z);
                return this;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence charSequence0) {
                this.mCancelLabel = charSequence0;
                return this;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence charSequence0) {
                this.mConfirmLabel = charSequence0;
                return this;
            }

            private void setFlag(int v, boolean z) {
                if(z) {
                    this.mFlags |= v;
                    return;
                }
                this.mFlags &= ~v;
            }

            public WearableExtender setHintDisplayActionInline(boolean z) {
                this.setFlag(4, z);
                return this;
            }

            public WearableExtender setHintLaunchesActivity(boolean z) {
                this.setFlag(2, z);
                return this;
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence charSequence0) {
                this.mInProgressLabel = charSequence0;
                return this;
            }
        }

        static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
        static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public PendingIntent actionIntent;
        @Deprecated
        public int icon;
        private boolean mAllowGeneratedReplies;
        private boolean mAuthenticationRequired;
        private final androidx.core.app.RemoteInput[] mDataOnlyRemoteInputs;
        final Bundle mExtras;
        private IconCompat mIcon;
        private final boolean mIsContextual;
        private final androidx.core.app.RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        boolean mShowsUserInterface;
        public CharSequence title;

        public Action(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
            this((v == 0 ? null : IconCompat.createWithResource(null, "", v)), charSequence0, pendingIntent0);
        }

        Action(int v, CharSequence charSequence0, PendingIntent pendingIntent0, Bundle bundle0, androidx.core.app.RemoteInput[] arr_remoteInput, androidx.core.app.RemoteInput[] arr_remoteInput1, boolean z, int v1, boolean z1, boolean z2, boolean z3) {
            this((v == 0 ? null : IconCompat.createWithResource(null, "", v)), charSequence0, pendingIntent0, bundle0, arr_remoteInput, arr_remoteInput1, z, v1, z1, z2, z3);
        }

        public Action(IconCompat iconCompat0, CharSequence charSequence0, PendingIntent pendingIntent0) {
            this(iconCompat0, charSequence0, pendingIntent0, new Bundle(), null, null, true, 0, true, false, false);
        }

        Action(IconCompat iconCompat0, CharSequence charSequence0, PendingIntent pendingIntent0, Bundle bundle0, androidx.core.app.RemoteInput[] arr_remoteInput, androidx.core.app.RemoteInput[] arr_remoteInput1, boolean z, int v, boolean z1, boolean z2, boolean z3) {
            this.mShowsUserInterface = true;
            this.mIcon = iconCompat0;
            if(iconCompat0 != null && iconCompat0.getType() == 2) {
                this.icon = iconCompat0.getResId();
            }
            this.title = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.actionIntent = pendingIntent0;
            if(bundle0 == null) {
                bundle0 = new Bundle();
            }
            this.mExtras = bundle0;
            this.mRemoteInputs = arr_remoteInput;
            this.mDataOnlyRemoteInputs = arr_remoteInput1;
            this.mAllowGeneratedReplies = z;
            this.mSemanticAction = v;
            this.mShowsUserInterface = z1;
            this.mIsContextual = z2;
            this.mAuthenticationRequired = z3;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public androidx.core.app.RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        @Deprecated
        public int getIcon() {
            return this.icon;
        }

        public IconCompat getIconCompat() {
            if(this.mIcon == null) {
                int v = this.icon;
                if(v != 0) {
                    this.mIcon = IconCompat.createWithResource(null, "", v);
                }
            }
            return this.mIcon;
        }

        public androidx.core.app.RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public boolean isAuthenticationRequired() {
            return this.mAuthenticationRequired;
        }

        public boolean isContextual() {
            return this.mIsContextual;
        }
    }

    static class androidx.core.app.NotificationCompat.Api20Impl {
        static boolean getAllowFreeFormInput(RemoteInput remoteInput0) {
            return remoteInput0.getAllowFreeFormInput();
        }

        static CharSequence[] getChoices(RemoteInput remoteInput0) {
            return remoteInput0.getChoices();
        }

        static Bundle getExtras(Notification.Action notification$Action0) {
            return notification$Action0.getExtras();
        }

        static Bundle getExtras(RemoteInput remoteInput0) {
            return remoteInput0.getExtras();
        }

        static String getGroup(Notification notification0) {
            return notification0.getGroup();
        }

        static CharSequence getLabel(RemoteInput remoteInput0) {
            return remoteInput0.getLabel();
        }

        static RemoteInput[] getRemoteInputs(Notification.Action notification$Action0) {
            return notification$Action0.getRemoteInputs();
        }

        static String getResultKey(RemoteInput remoteInput0) {
            return remoteInput0.getResultKey();
        }

        static String getSortKey(Notification notification0) {
            return notification0.getSortKey();
        }
    }

    static class androidx.core.app.NotificationCompat.Api23Impl {
        static Icon getIcon(Notification.Action notification$Action0) {
            return notification$Action0.getIcon();
        }
    }

    static class androidx.core.app.NotificationCompat.Api24Impl {
        static boolean getAllowGeneratedReplies(Notification.Action notification$Action0) {
            return notification$Action0.getAllowGeneratedReplies();
        }
    }

    static class Api26Impl {
        static int getBadgeIconType(Notification notification0) {
            return notification0.getBadgeIconType();
        }

        static String getChannelId(Notification notification0) {
            return notification0.getChannelId();
        }

        static int getGroupAlertBehavior(Notification notification0) {
            return notification0.getGroupAlertBehavior();
        }

        static CharSequence getSettingsText(Notification notification0) {
            return notification0.getSettingsText();
        }

        static String getShortcutId(Notification notification0) {
            return notification0.getShortcutId();
        }

        static long getTimeoutAfter(Notification notification0) {
            return notification0.getTimeoutAfter();
        }
    }

    static class androidx.core.app.NotificationCompat.Api28Impl {
        static int getSemanticAction(Notification.Action notification$Action0) {
            return notification$Action0.getSemanticAction();
        }
    }

    static class androidx.core.app.NotificationCompat.Api29Impl {
        static boolean getAllowSystemGeneratedContextualActions(Notification notification0) {
            return notification0.getAllowSystemGeneratedContextualActions();
        }

        static Notification.BubbleMetadata getBubbleMetadata(Notification notification0) {
            return notification0.getBubbleMetadata();
        }

        static int getEditChoicesBeforeSending(RemoteInput remoteInput0) {
            return remoteInput0.getEditChoicesBeforeSending();
        }

        static LocusId getLocusId(Notification notification0) {
            return notification0.getLocusId();
        }

        static boolean isContextual(Notification.Action notification$Action0) {
            return notification$Action0.isContextual();
        }
    }

    static class androidx.core.app.NotificationCompat.Api31Impl {
        static boolean isAuthenticationRequired(Notification.Action notification$Action0) {
            return notification$Action0.isAuthenticationRequired();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeIconType {
    }

    public static class BigPictureStyle extends Style {
        static class androidx.core.app.NotificationCompat.BigPictureStyle.Api23Impl {
            static void setBigLargeIcon(Notification.BigPictureStyle notification$BigPictureStyle0, Icon icon0) {
                notification$BigPictureStyle0.bigLargeIcon(icon0);
            }
        }

        static class androidx.core.app.NotificationCompat.BigPictureStyle.Api31Impl {
            static void setBigPicture(Notification.BigPictureStyle notification$BigPictureStyle0, Icon icon0) {
                notification$BigPictureStyle0.bigPicture(icon0);
            }

            static void setContentDescription(Notification.BigPictureStyle notification$BigPictureStyle0, CharSequence charSequence0) {
                notification$BigPictureStyle0.setContentDescription(charSequence0);
            }

            static void showBigPictureWhenCollapsed(Notification.BigPictureStyle notification$BigPictureStyle0, boolean z) {
                notification$BigPictureStyle0.showBigPictureWhenCollapsed(z);
            }
        }

        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigPictureStyle";
        private IconCompat mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private CharSequence mPictureContentDescription;
        private IconCompat mPictureIcon;
        private boolean mShowBigPictureWhenCollapsed;

        public BigPictureStyle() {
        }

        public BigPictureStyle(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            this.setBuilder(notificationCompat$Builder0);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            Notification.BigPictureStyle notification$BigPictureStyle0 = new Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor0.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            Context context0 = null;
            if(this.mPictureIcon != null) {
                if(Build.VERSION.SDK_INT >= 0x1F) {
                    Context context1 = notificationBuilderWithBuilderAccessor0 instanceof NotificationCompatBuilder ? ((NotificationCompatBuilder)notificationBuilderWithBuilderAccessor0).getContext() : null;
                    androidx.core.app.NotificationCompat.BigPictureStyle.Api31Impl.setBigPicture(notification$BigPictureStyle0, this.mPictureIcon.toIcon(context1));
                }
                else if(this.mPictureIcon.getType() == 1) {
                    notification$BigPictureStyle0 = notification$BigPictureStyle0.bigPicture(this.mPictureIcon.getBitmap());
                }
            }
            if(this.mBigLargeIconSet) {
                if(this.mBigLargeIcon == null) {
                    notification$BigPictureStyle0.bigLargeIcon(null);
                }
                else if(Build.VERSION.SDK_INT >= 23) {
                    if(notificationBuilderWithBuilderAccessor0 instanceof NotificationCompatBuilder) {
                        context0 = ((NotificationCompatBuilder)notificationBuilderWithBuilderAccessor0).getContext();
                    }
                    androidx.core.app.NotificationCompat.BigPictureStyle.Api23Impl.setBigLargeIcon(notification$BigPictureStyle0, this.mBigLargeIcon.toIcon(context0));
                }
                else if(this.mBigLargeIcon.getType() == 1) {
                    notification$BigPictureStyle0.bigLargeIcon(this.mBigLargeIcon.getBitmap());
                }
                else {
                    notification$BigPictureStyle0.bigLargeIcon(null);
                }
            }
            if(this.mSummaryTextSet) {
                notification$BigPictureStyle0.setSummaryText(this.mSummaryText);
            }
            if(Build.VERSION.SDK_INT >= 0x1F) {
                androidx.core.app.NotificationCompat.BigPictureStyle.Api31Impl.showBigPictureWhenCollapsed(notification$BigPictureStyle0, this.mShowBigPictureWhenCollapsed);
                androidx.core.app.NotificationCompat.BigPictureStyle.Api31Impl.setContentDescription(notification$BigPictureStyle0, this.mPictureContentDescription);
            }
        }

        private static IconCompat asIconCompat(Parcelable parcelable0) {
            if(parcelable0 != null) {
                if(Build.VERSION.SDK_INT >= 23 && ComponentDialog..ExternalSyntheticApiModelOutline0.m$1(parcelable0)) {
                    return IconCompat.createFromIcon(((Icon)parcelable0));
                }
                return parcelable0 instanceof Bitmap ? IconCompat.createWithBitmap(((Bitmap)parcelable0)) : null;
            }
            return null;
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap0) {
            this.mBigLargeIcon = bitmap0 == null ? null : IconCompat.createWithBitmap(bitmap0);
            this.mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigLargeIcon(Icon icon0) {
            this.mBigLargeIcon = icon0 == null ? null : IconCompat.createFromIcon(icon0);
            this.mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap0) {
            this.mPictureIcon = bitmap0 == null ? null : IconCompat.createWithBitmap(bitmap0);
            return this;
        }

        public BigPictureStyle bigPicture(Icon icon0) {
            this.mPictureIcon = IconCompat.createFromIcon(icon0);
            return this;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected void clearCompatExtraKeys(Bundle bundle0) {
            super.clearCompatExtraKeys(bundle0);
            bundle0.remove("android.largeIcon.big");
            bundle0.remove("android.picture");
            bundle0.remove("android.pictureIcon");
            bundle0.remove("android.showBigPictureWhenCollapsed");
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected String getClassName() {
            return "androidx.core.app.NotificationCompat$BigPictureStyle";
        }

        public static IconCompat getPictureIcon(Bundle bundle0) {
            if(bundle0 == null) {
                return null;
            }
            Parcelable parcelable0 = bundle0.getParcelable("android.picture");
            return parcelable0 == null ? BigPictureStyle.asIconCompat(bundle0.getParcelable("android.pictureIcon")) : BigPictureStyle.asIconCompat(parcelable0);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected void restoreFromCompatExtras(Bundle bundle0) {
            super.restoreFromCompatExtras(bundle0);
            if(bundle0.containsKey("android.largeIcon.big")) {
                this.mBigLargeIcon = BigPictureStyle.asIconCompat(bundle0.getParcelable("android.largeIcon.big"));
                this.mBigLargeIconSet = true;
            }
            this.mPictureIcon = BigPictureStyle.getPictureIcon(bundle0);
            this.mShowBigPictureWhenCollapsed = bundle0.getBoolean("android.showBigPictureWhenCollapsed");
        }

        public BigPictureStyle setBigContentTitle(CharSequence charSequence0) {
            this.mBigContentTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public BigPictureStyle setContentDescription(CharSequence charSequence0) {
            this.mPictureContentDescription = charSequence0;
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charSequence0) {
            this.mSummaryText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigPictureStyle showBigPictureWhenCollapsed(boolean z) {
            this.mShowBigPictureWhenCollapsed = z;
            return this;
        }
    }

    public static class BigTextStyle extends Style {
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigTextStyle";
        private CharSequence mBigText;

        public BigTextStyle() {
        }

        public BigTextStyle(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            this.setBuilder(notificationCompat$Builder0);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public void addCompatExtras(Bundle bundle0) {
            super.addCompatExtras(bundle0);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            Notification.BigTextStyle notification$BigTextStyle0 = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor0.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
            if(this.mSummaryTextSet) {
                notification$BigTextStyle0.setSummaryText(this.mSummaryText);
            }
        }

        public BigTextStyle bigText(CharSequence charSequence0) {
            this.mBigText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected void clearCompatExtraKeys(Bundle bundle0) {
            super.clearCompatExtraKeys(bundle0);
            bundle0.remove("android.bigText");
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected String getClassName() {
            return "androidx.core.app.NotificationCompat$BigTextStyle";
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected void restoreFromCompatExtras(Bundle bundle0) {
            super.restoreFromCompatExtras(bundle0);
            this.mBigText = bundle0.getCharSequence("android.bigText");
        }

        public BigTextStyle setBigContentTitle(CharSequence charSequence0) {
            this.mBigContentTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charSequence0) {
            this.mSummaryText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.mSummaryTextSet = true;
            return this;
        }
    }

    public static final class BubbleMetadata {
        static class androidx.core.app.NotificationCompat.BubbleMetadata.Api29Impl {
            static BubbleMetadata fromPlatform(Notification.BubbleMetadata notification$BubbleMetadata0) {
                if(notification$BubbleMetadata0 == null) {
                    return null;
                }
                if(notification$BubbleMetadata0.getIntent() == null) {
                    return null;
                }
                androidx.core.app.NotificationCompat.BubbleMetadata.Builder notificationCompat$BubbleMetadata$Builder0 = new androidx.core.app.NotificationCompat.BubbleMetadata.Builder(notification$BubbleMetadata0.getIntent(), IconCompat.createFromIcon(notification$BubbleMetadata0.getIcon())).setAutoExpandBubble(notification$BubbleMetadata0.getAutoExpandBubble()).setDeleteIntent(notification$BubbleMetadata0.getDeleteIntent()).setSuppressNotification(notification$BubbleMetadata0.isNotificationSuppressed());
                if(notification$BubbleMetadata0.getDesiredHeight() != 0) {
                    notificationCompat$BubbleMetadata$Builder0.setDesiredHeight(notification$BubbleMetadata0.getDesiredHeight());
                }
                if(notification$BubbleMetadata0.getDesiredHeightResId() != 0) {
                    notificationCompat$BubbleMetadata$Builder0.setDesiredHeightResId(notification$BubbleMetadata0.getDesiredHeightResId());
                }
                return notificationCompat$BubbleMetadata$Builder0.build();
            }

            static Notification.BubbleMetadata toPlatform(BubbleMetadata notificationCompat$BubbleMetadata0) {
                if(notificationCompat$BubbleMetadata0 == null) {
                    return null;
                }
                if(notificationCompat$BubbleMetadata0.getIntent() == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder notification$BubbleMetadata$Builder0 = new Notification.BubbleMetadata.Builder().setIcon(notificationCompat$BubbleMetadata0.getIcon().toIcon()).setIntent(notificationCompat$BubbleMetadata0.getIntent()).setDeleteIntent(notificationCompat$BubbleMetadata0.getDeleteIntent()).setAutoExpandBubble(notificationCompat$BubbleMetadata0.getAutoExpandBubble()).setSuppressNotification(notificationCompat$BubbleMetadata0.isNotificationSuppressed());
                if(notificationCompat$BubbleMetadata0.getDesiredHeight() != 0) {
                    notification$BubbleMetadata$Builder0.setDesiredHeight(notificationCompat$BubbleMetadata0.getDesiredHeight());
                }
                if(notificationCompat$BubbleMetadata0.getDesiredHeightResId() != 0) {
                    notification$BubbleMetadata$Builder0.setDesiredHeightResId(notificationCompat$BubbleMetadata0.getDesiredHeightResId());
                }
                return notification$BubbleMetadata$Builder0.build();
            }
        }

        static class Api30Impl {
            static BubbleMetadata fromPlatform(Notification.BubbleMetadata notification$BubbleMetadata0) {
                if(notification$BubbleMetadata0 == null) {
                    return null;
                }
                androidx.core.app.NotificationCompat.BubbleMetadata.Builder notificationCompat$BubbleMetadata$Builder0 = notification$BubbleMetadata0.getShortcutId() == null ? new androidx.core.app.NotificationCompat.BubbleMetadata.Builder(notification$BubbleMetadata0.getIntent(), IconCompat.createFromIcon(notification$BubbleMetadata0.getIcon())) : new androidx.core.app.NotificationCompat.BubbleMetadata.Builder(notification$BubbleMetadata0.getShortcutId());
                notificationCompat$BubbleMetadata$Builder0.setAutoExpandBubble(notification$BubbleMetadata0.getAutoExpandBubble()).setDeleteIntent(notification$BubbleMetadata0.getDeleteIntent()).setSuppressNotification(notification$BubbleMetadata0.isNotificationSuppressed());
                if(notification$BubbleMetadata0.getDesiredHeight() != 0) {
                    notificationCompat$BubbleMetadata$Builder0.setDesiredHeight(notification$BubbleMetadata0.getDesiredHeight());
                }
                if(notification$BubbleMetadata0.getDesiredHeightResId() != 0) {
                    notificationCompat$BubbleMetadata$Builder0.setDesiredHeightResId(notification$BubbleMetadata0.getDesiredHeightResId());
                }
                return notificationCompat$BubbleMetadata$Builder0.build();
            }

            static Notification.BubbleMetadata toPlatform(BubbleMetadata notificationCompat$BubbleMetadata0) {
                if(notificationCompat$BubbleMetadata0 == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder notification$BubbleMetadata$Builder0 = notificationCompat$BubbleMetadata0.getShortcutId() == null ? new Notification.BubbleMetadata.Builder(notificationCompat$BubbleMetadata0.getIntent(), notificationCompat$BubbleMetadata0.getIcon().toIcon()) : new Notification.BubbleMetadata.Builder(notificationCompat$BubbleMetadata0.getShortcutId());
                notification$BubbleMetadata$Builder0.setDeleteIntent(notificationCompat$BubbleMetadata0.getDeleteIntent()).setAutoExpandBubble(notificationCompat$BubbleMetadata0.getAutoExpandBubble()).setSuppressNotification(notificationCompat$BubbleMetadata0.isNotificationSuppressed());
                if(notificationCompat$BubbleMetadata0.getDesiredHeight() != 0) {
                    notification$BubbleMetadata$Builder0.setDesiredHeight(notificationCompat$BubbleMetadata0.getDesiredHeight());
                }
                if(notificationCompat$BubbleMetadata0.getDesiredHeightResId() != 0) {
                    notification$BubbleMetadata$Builder0.setDesiredHeightResId(notificationCompat$BubbleMetadata0.getDesiredHeightResId());
                }
                return notification$BubbleMetadata$Builder0.build();
            }
        }

        public static final class androidx.core.app.NotificationCompat.BubbleMetadata.Builder {
            private PendingIntent mDeleteIntent;
            private int mDesiredHeight;
            private int mDesiredHeightResId;
            private int mFlags;
            private IconCompat mIcon;
            private PendingIntent mPendingIntent;
            private String mShortcutId;

            @Deprecated
            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder() {
            }

            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder(PendingIntent pendingIntent0, IconCompat iconCompat0) {
                if(pendingIntent0 == null) {
                    throw new NullPointerException("Bubble requires non-null pending intent");
                }
                if(iconCompat0 == null) {
                    throw new NullPointerException("Bubbles require non-null icon");
                }
                this.mPendingIntent = pendingIntent0;
                this.mIcon = iconCompat0;
            }

            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder(String s) {
                if(TextUtils.isEmpty(s)) {
                    throw new NullPointerException("Bubble requires a non-null shortcut id");
                }
                this.mShortcutId = s;
            }

            public BubbleMetadata build() {
                String s = this.mShortcutId;
                if(s == null && this.mPendingIntent == null) {
                    throw new NullPointerException("Must supply pending intent or shortcut to bubble");
                }
                if(s == null && this.mIcon == null) {
                    throw new NullPointerException("Must supply an icon or shortcut for the bubble");
                }
                BubbleMetadata notificationCompat$BubbleMetadata0 = new BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mFlags, this.mShortcutId, null);
                notificationCompat$BubbleMetadata0.setFlags(this.mFlags);
                return notificationCompat$BubbleMetadata0;
            }

            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder setAutoExpandBubble(boolean z) {
                this.setFlag(1, z);
                return this;
            }

            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder setDeleteIntent(PendingIntent pendingIntent0) {
                this.mDeleteIntent = pendingIntent0;
                return this;
            }

            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder setDesiredHeight(int v) {
                this.mDesiredHeight = Math.max(v, 0);
                this.mDesiredHeightResId = 0;
                return this;
            }

            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder setDesiredHeightResId(int v) {
                this.mDesiredHeightResId = v;
                this.mDesiredHeight = 0;
                return this;
            }

            private androidx.core.app.NotificationCompat.BubbleMetadata.Builder setFlag(int v, boolean z) {
                if(z) {
                    this.mFlags |= v;
                    return this;
                }
                this.mFlags &= ~v;
                return this;
            }

            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder setIcon(IconCompat iconCompat0) {
                if(this.mShortcutId != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                }
                if(iconCompat0 == null) {
                    throw new NullPointerException("Bubbles require non-null icon");
                }
                this.mIcon = iconCompat0;
                return this;
            }

            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder setIntent(PendingIntent pendingIntent0) {
                if(this.mShortcutId != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                }
                if(pendingIntent0 == null) {
                    throw new NullPointerException("Bubble requires non-null pending intent");
                }
                this.mPendingIntent = pendingIntent0;
                return this;
            }

            public androidx.core.app.NotificationCompat.BubbleMetadata.Builder setSuppressNotification(boolean z) {
                this.setFlag(2, z);
                return this;
            }
        }

        private static final int FLAG_AUTO_EXPAND_BUBBLE = 1;
        private static final int FLAG_SUPPRESS_NOTIFICATION = 2;
        private PendingIntent mDeleteIntent;
        private int mDesiredHeight;
        private int mDesiredHeightResId;
        private int mFlags;
        private IconCompat mIcon;
        private PendingIntent mPendingIntent;
        private String mShortcutId;

        private BubbleMetadata(PendingIntent pendingIntent0, PendingIntent pendingIntent1, IconCompat iconCompat0, int v, int v1, int v2, String s) {
            this.mPendingIntent = pendingIntent0;
            this.mIcon = iconCompat0;
            this.mDesiredHeight = v;
            this.mDesiredHeightResId = v1;
            this.mDeleteIntent = pendingIntent1;
            this.mFlags = v2;
            this.mShortcutId = s;
        }

        BubbleMetadata(PendingIntent pendingIntent0, PendingIntent pendingIntent1, IconCompat iconCompat0, int v, int v1, int v2, String s, androidx.core.app.NotificationCompat.1 notificationCompat$10) {
            this(pendingIntent0, pendingIntent1, iconCompat0, v, v1, v2, s);
        }

        public static BubbleMetadata fromPlatform(Notification.BubbleMetadata notification$BubbleMetadata0) {
            if(notification$BubbleMetadata0 == null) {
                return null;
            }
            if(Build.VERSION.SDK_INT >= 30) {
                return Api30Impl.fromPlatform(notification$BubbleMetadata0);
            }
            return Build.VERSION.SDK_INT == 29 ? androidx.core.app.NotificationCompat.BubbleMetadata.Api29Impl.fromPlatform(notification$BubbleMetadata0) : null;
        }

        public boolean getAutoExpandBubble() {
            return (this.mFlags & 1) != 0;
        }

        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        public int getDesiredHeight() {
            return this.mDesiredHeight;
        }

        public int getDesiredHeightResId() {
            return this.mDesiredHeightResId;
        }

        public IconCompat getIcon() {
            return this.mIcon;
        }

        public PendingIntent getIntent() {
            return this.mPendingIntent;
        }

        public String getShortcutId() {
            return this.mShortcutId;
        }

        public boolean isNotificationSuppressed() {
            return (this.mFlags & 2) != 0;
        }

        public void setFlags(int v) {
            this.mFlags = v;
        }

        public static Notification.BubbleMetadata toPlatform(BubbleMetadata notificationCompat$BubbleMetadata0) {
            if(notificationCompat$BubbleMetadata0 == null) {
                return null;
            }
            if(Build.VERSION.SDK_INT >= 30) {
                return Api30Impl.toPlatform(notificationCompat$BubbleMetadata0);
            }
            return Build.VERSION.SDK_INT == 29 ? androidx.core.app.NotificationCompat.BubbleMetadata.Api29Impl.toPlatform(notificationCompat$BubbleMetadata0) : null;
        }
    }

    public static class androidx.core.app.NotificationCompat.Builder {
        static class Api21Impl {
            static AudioAttributes build(AudioAttributes.Builder audioAttributes$Builder0) {
                return audioAttributes$Builder0.build();
            }

            static AudioAttributes.Builder createBuilder() {
                return new AudioAttributes.Builder();
            }

            static AudioAttributes.Builder setContentType(AudioAttributes.Builder audioAttributes$Builder0, int v) {
                return audioAttributes$Builder0.setContentType(v);
            }

            static AudioAttributes.Builder setLegacyStreamType(AudioAttributes.Builder audioAttributes$Builder0, int v) {
                return audioAttributes$Builder0.setLegacyStreamType(v);
            }

            static AudioAttributes.Builder setUsage(AudioAttributes.Builder audioAttributes$Builder0, int v) {
                return audioAttributes$Builder0.setUsage(v);
            }
        }

        static class androidx.core.app.NotificationCompat.Builder.Api23Impl {
            static Icon getLargeIcon(Notification notification0) {
                return notification0.getLargeIcon();
            }

            static Icon getSmallIcon(Notification notification0) {
                return notification0.getSmallIcon();
            }
        }

        static class androidx.core.app.NotificationCompat.Builder.Api24Impl {
            static RemoteViews createBigContentView(Notification.Builder notification$Builder0) {
                return notification$Builder0.createHeadsUpContentView();
            }

            static RemoteViews createContentView(Notification.Builder notification$Builder0) {
                return notification$Builder0.createContentView();
            }

            static RemoteViews createHeadsUpContentView(Notification.Builder notification$Builder0) {
                return notification$Builder0.createHeadsUpContentView();
            }

            static Notification.Builder recoverBuilder(Context context0, Notification notification0) {
                return Notification.Builder.recoverBuilder(context0, notification0);
            }
        }

        private static final int MAX_CHARSEQUENCE_LENGTH = 0x1400;
        public ArrayList mActions;
        boolean mAllowSystemGeneratedContextualActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        BubbleMetadata mBubbleMetadata;
        String mCategory;
        String mChannelId;
        boolean mChronometerCountDown;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        public Context mContext;
        Bundle mExtras;
        int mFgsDeferBehavior;
        PendingIntent mFullScreenIntent;
        int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        ArrayList mInvisibleActions;
        IconCompat mLargeIcon;
        boolean mLocalOnly;
        LocusIdCompat mLocusId;
        Notification mNotification;
        int mNumber;
        @Deprecated
        public ArrayList mPeople;
        public ArrayList mPersonList;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        CharSequence mSettingsText;
        String mShortcutId;
        boolean mShowWhen;
        boolean mSilent;
        Object mSmallIcon;
        String mSortKey;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        int mVisibility;

        @Deprecated
        public androidx.core.app.NotificationCompat.Builder(Context context0) {
            this(context0, null);
        }

        public androidx.core.app.NotificationCompat.Builder(Context context0, Notification notification0) {
            this(context0, NotificationCompat.getChannelId(notification0));
            Bundle bundle0 = notification0.extras;
            Style notificationCompat$Style0 = Style.extractStyleFromNotification(notification0);
            this.setContentTitle(NotificationCompat.getContentTitle(notification0)).setContentText(NotificationCompat.getContentText(notification0)).setContentInfo(NotificationCompat.getContentInfo(notification0)).setSubText(NotificationCompat.getSubText(notification0)).setSettingsText(NotificationCompat.getSettingsText(notification0)).setStyle(notificationCompat$Style0).setGroup(NotificationCompat.getGroup(notification0)).setGroupSummary(NotificationCompat.isGroupSummary(notification0)).setLocusId(NotificationCompat.getLocusId(notification0)).setWhen(notification0.when).setShowWhen(NotificationCompat.getShowWhen(notification0)).setUsesChronometer(NotificationCompat.getUsesChronometer(notification0)).setAutoCancel(NotificationCompat.getAutoCancel(notification0)).setOnlyAlertOnce(NotificationCompat.getOnlyAlertOnce(notification0)).setOngoing(NotificationCompat.getOngoing(notification0)).setLocalOnly(NotificationCompat.getLocalOnly(notification0)).setLargeIcon(notification0.largeIcon).setBadgeIconType(NotificationCompat.getBadgeIconType(notification0)).setCategory(NotificationCompat.getCategory(notification0)).setBubbleMetadata(NotificationCompat.getBubbleMetadata(notification0)).setNumber(notification0.number).setTicker(notification0.tickerText).setContentIntent(notification0.contentIntent).setDeleteIntent(notification0.deleteIntent).setFullScreenIntent(notification0.fullScreenIntent, NotificationCompat.getHighPriority(notification0)).setSound(notification0.sound, notification0.audioStreamType).setVibrate(notification0.vibrate).setLights(notification0.ledARGB, notification0.ledOnMS, notification0.ledOffMS).setDefaults(notification0.defaults).setPriority(notification0.priority).setColor(NotificationCompat.getColor(notification0)).setVisibility(NotificationCompat.getVisibility(notification0)).setPublicVersion(NotificationCompat.getPublicVersion(notification0)).setSortKey(NotificationCompat.getSortKey(notification0)).setTimeoutAfter(NotificationCompat.getTimeoutAfter(notification0)).setShortcutId(NotificationCompat.getShortcutId(notification0)).setProgress(bundle0.getInt("android.progressMax"), bundle0.getInt("android.progress"), bundle0.getBoolean("android.progressIndeterminate")).setAllowSystemGeneratedContextualActions(NotificationCompat.getAllowSystemGeneratedContextualActions(notification0)).setSmallIcon(notification0.icon, notification0.iconLevel).addExtras(androidx.core.app.NotificationCompat.Builder.getExtrasWithoutDuplicateData(notification0, notificationCompat$Style0));
            if(Build.VERSION.SDK_INT >= 23) {
                this.mSmallIcon = androidx.core.app.NotificationCompat.Builder.Api23Impl.getSmallIcon(notification0);
                Icon icon0 = androidx.core.app.NotificationCompat.Builder.Api23Impl.getLargeIcon(notification0);
                if(icon0 != null) {
                    this.mLargeIcon = IconCompat.createFromIcon(icon0);
                }
            }
            if(notification0.actions != null && notification0.actions.length != 0) {
                Notification.Action[] arr_notification$Action = notification0.actions;
                for(int v1 = 0; v1 < arr_notification$Action.length; ++v1) {
                    this.addAction(Builder.fromAndroidAction(arr_notification$Action[v1]).build());
                }
            }
            List list0 = NotificationCompat.getInvisibleActions(notification0);
            if(!list0.isEmpty()) {
                for(Object object0: list0) {
                    this.addInvisibleAction(((Action)object0));
                }
            }
            String[] arr_s = notification0.extras.getStringArray("android.people");
            if(arr_s != null && arr_s.length != 0) {
                for(int v = 0; v < arr_s.length; ++v) {
                    this.addPerson(arr_s[v]);
                }
            }
            if(Build.VERSION.SDK_INT >= 28) {
                ArrayList arrayList0 = notification0.extras.getParcelableArrayList("android.people.list");
                if(arrayList0 != null && !arrayList0.isEmpty()) {
                    for(Object object1: arrayList0) {
                        this.addPerson(Person.fromAndroidPerson(((android.app.Person)object1)));
                    }
                }
            }
            if(Build.VERSION.SDK_INT >= 24 && bundle0.containsKey("android.chronometerCountDown")) {
                this.setChronometerCountDown(bundle0.getBoolean("android.chronometerCountDown"));
            }
            if(Build.VERSION.SDK_INT >= 26 && bundle0.containsKey("android.colorized")) {
                this.setColorized(bundle0.getBoolean("android.colorized"));
            }
        }

        public androidx.core.app.NotificationCompat.Builder(Context context0, String s) {
            this.mActions = new ArrayList();
            this.mPersonList = new ArrayList();
            this.mInvisibleActions = new ArrayList();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            this.mFgsDeferBehavior = 0;
            Notification notification0 = new Notification();
            this.mNotification = notification0;
            this.mContext = context0;
            this.mChannelId = s;
            notification0.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList();
            this.mAllowSystemGeneratedContextualActions = true;
        }

        public androidx.core.app.NotificationCompat.Builder addAction(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
            this.mActions.add(new Action(v, charSequence0, pendingIntent0));
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder addAction(Action notificationCompat$Action0) {
            if(notificationCompat$Action0 != null) {
                this.mActions.add(notificationCompat$Action0);
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder addExtras(Bundle bundle0) {
            if(bundle0 != null) {
                Bundle bundle1 = this.mExtras;
                if(bundle1 == null) {
                    this.mExtras = new Bundle(bundle0);
                    return this;
                }
                bundle1.putAll(bundle0);
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder addInvisibleAction(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
            this.mInvisibleActions.add(new Action(v, charSequence0, pendingIntent0));
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder addInvisibleAction(Action notificationCompat$Action0) {
            if(notificationCompat$Action0 != null) {
                this.mInvisibleActions.add(notificationCompat$Action0);
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder addPerson(Person person0) {
            if(person0 != null) {
                this.mPersonList.add(person0);
            }
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.Builder addPerson(String s) {
            if(s != null && !s.isEmpty()) {
                this.mPeople.add(s);
            }
            return this;
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        public androidx.core.app.NotificationCompat.Builder clearActions() {
            this.mActions.clear();
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder clearInvisibleActions() {
            this.mInvisibleActions.clear();
            Bundle bundle0 = this.mExtras.getBundle("android.car.EXTENSIONS");
            if(bundle0 != null) {
                Bundle bundle1 = new Bundle(bundle0);
                bundle1.remove("invisible_actions");
                this.mExtras.putBundle("android.car.EXTENSIONS", bundle1);
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder clearPeople() {
            this.mPersonList.clear();
            this.mPeople.clear();
            return this;
        }

        public RemoteViews createBigContentView() {
            if(this.mBigContentView != null && this.useExistingRemoteView()) {
                return this.mBigContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder0 = new NotificationCompatBuilder(this);
            Style notificationCompat$Style0 = this.mStyle;
            if(notificationCompat$Style0 != null) {
                RemoteViews remoteViews0 = notificationCompat$Style0.makeBigContentView(notificationCompatBuilder0);
                if(remoteViews0 != null) {
                    return remoteViews0;
                }
            }
            Notification notification0 = notificationCompatBuilder0.build();
            return Build.VERSION.SDK_INT < 24 ? notification0.bigContentView : androidx.core.app.NotificationCompat.Builder.Api24Impl.createBigContentView(androidx.core.app.NotificationCompat.Builder.Api24Impl.recoverBuilder(this.mContext, notification0));
        }

        public RemoteViews createContentView() {
            if(this.mContentView != null && this.useExistingRemoteView()) {
                return this.mContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder0 = new NotificationCompatBuilder(this);
            Style notificationCompat$Style0 = this.mStyle;
            if(notificationCompat$Style0 != null) {
                RemoteViews remoteViews0 = notificationCompat$Style0.makeContentView(notificationCompatBuilder0);
                if(remoteViews0 != null) {
                    return remoteViews0;
                }
            }
            Notification notification0 = notificationCompatBuilder0.build();
            return Build.VERSION.SDK_INT < 24 ? notification0.contentView : androidx.core.app.NotificationCompat.Builder.Api24Impl.createContentView(androidx.core.app.NotificationCompat.Builder.Api24Impl.recoverBuilder(this.mContext, notification0));
        }

        public RemoteViews createHeadsUpContentView() {
            if(this.mHeadsUpContentView != null && this.useExistingRemoteView()) {
                return this.mHeadsUpContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder0 = new NotificationCompatBuilder(this);
            Style notificationCompat$Style0 = this.mStyle;
            if(notificationCompat$Style0 != null) {
                RemoteViews remoteViews0 = notificationCompat$Style0.makeHeadsUpContentView(notificationCompatBuilder0);
                if(remoteViews0 != null) {
                    return remoteViews0;
                }
            }
            Notification notification0 = notificationCompatBuilder0.build();
            return Build.VERSION.SDK_INT < 24 ? notification0.headsUpContentView : androidx.core.app.NotificationCompat.Builder.Api24Impl.createHeadsUpContentView(androidx.core.app.NotificationCompat.Builder.Api24Impl.recoverBuilder(this.mContext, notification0));
        }

        public androidx.core.app.NotificationCompat.Builder extend(androidx.core.app.NotificationCompat.Extender notificationCompat$Extender0) {
            notificationCompat$Extender0.extend(this);
            return this;
        }

        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        public BubbleMetadata getBubbleMetadata() {
            return this.mBubbleMetadata;
        }

        public int getColor() {
            return this.mColor;
        }

        public RemoteViews getContentView() {
            return this.mContentView;
        }

        public Bundle getExtras() {
            if(this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        private static Bundle getExtrasWithoutDuplicateData(Notification notification0, Style notificationCompat$Style0) {
            if(notification0.extras == null) {
                return null;
            }
            Bundle bundle0 = new Bundle(notification0.extras);
            bundle0.remove("android.title");
            bundle0.remove("android.text");
            bundle0.remove("android.infoText");
            bundle0.remove("android.subText");
            bundle0.remove("android.intent.extra.CHANNEL_ID");
            bundle0.remove("android.intent.extra.CHANNEL_GROUP_ID");
            bundle0.remove("android.showWhen");
            bundle0.remove("android.progress");
            bundle0.remove("android.progressMax");
            bundle0.remove("android.progressIndeterminate");
            bundle0.remove("android.chronometerCountDown");
            bundle0.remove("android.colorized");
            bundle0.remove("android.people.list");
            bundle0.remove("android.people");
            bundle0.remove("android.support.sortKey");
            bundle0.remove("android.support.groupKey");
            bundle0.remove("android.support.isGroupSummary");
            bundle0.remove("android.support.localOnly");
            bundle0.remove("android.support.actionExtras");
            Bundle bundle1 = bundle0.getBundle("android.car.EXTENSIONS");
            if(bundle1 != null) {
                Bundle bundle2 = new Bundle(bundle1);
                bundle2.remove("invisible_actions");
                bundle0.putBundle("android.car.EXTENSIONS", bundle2);
            }
            if(notificationCompat$Style0 != null) {
                notificationCompat$Style0.clearCompatExtraKeys(bundle0);
            }
            return bundle0;
        }

        public int getForegroundServiceBehavior() {
            return this.mFgsDeferBehavior;
        }

        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        @Deprecated
        public Notification getNotification() {
            return this.build();
        }

        public int getPriority() {
            return this.mPriority;
        }

        // 去混淆评级： 低(20)
        public long getWhenIfShowing() {
            return this.mShowWhen ? this.mNotification.when : 0L;
        }

        protected static CharSequence limitCharSequenceLength(CharSequence charSequence0) {
            return charSequence0 == null || charSequence0.length() <= 0x1400 ? charSequence0 : charSequence0.subSequence(0, 0x1400);
        }

        public androidx.core.app.NotificationCompat.Builder setAllowSystemGeneratedContextualActions(boolean z) {
            this.mAllowSystemGeneratedContextualActions = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setAutoCancel(boolean z) {
            this.setFlag(16, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setBadgeIconType(int v) {
            this.mBadgeIcon = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setBubbleMetadata(BubbleMetadata notificationCompat$BubbleMetadata0) {
            this.mBubbleMetadata = notificationCompat$BubbleMetadata0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setCategory(String s) {
            this.mCategory = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setChannelId(String s) {
            this.mChannelId = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setChronometerCountDown(boolean z) {
            this.mChronometerCountDown = z;
            this.getExtras().putBoolean("android.chronometerCountDown", z);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setColor(int v) {
            this.mColor = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setColorized(boolean z) {
            this.mColorized = z;
            this.mColorizedSet = true;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContent(RemoteViews remoteViews0) {
            this.mNotification.contentView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContentInfo(CharSequence charSequence0) {
            this.mContentInfo = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContentIntent(PendingIntent pendingIntent0) {
            this.mContentIntent = pendingIntent0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContentText(CharSequence charSequence0) {
            this.mContentText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setContentTitle(CharSequence charSequence0) {
            this.mContentTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setCustomBigContentView(RemoteViews remoteViews0) {
            this.mBigContentView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setCustomContentView(RemoteViews remoteViews0) {
            this.mContentView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setCustomHeadsUpContentView(RemoteViews remoteViews0) {
            this.mHeadsUpContentView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setDefaults(int v) {
            this.mNotification.defaults = v;
            if((v & 4) != 0) {
                this.mNotification.flags |= 1;
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setDeleteIntent(PendingIntent pendingIntent0) {
            this.mNotification.deleteIntent = pendingIntent0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setExtras(Bundle bundle0) {
            this.mExtras = bundle0;
            return this;
        }

        private void setFlag(int v, boolean z) {
            if(z) {
                this.mNotification.flags |= v;
                return;
            }
            this.mNotification.flags &= ~v;
        }

        public androidx.core.app.NotificationCompat.Builder setForegroundServiceBehavior(int v) {
            this.mFgsDeferBehavior = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setFullScreenIntent(PendingIntent pendingIntent0, boolean z) {
            this.mFullScreenIntent = pendingIntent0;
            this.setFlag(0x80, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setGroup(String s) {
            this.mGroupKey = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setGroupAlertBehavior(int v) {
            this.mGroupAlertBehavior = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setGroupSummary(boolean z) {
            this.mGroupSummary = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setLargeIcon(Bitmap bitmap0) {
            this.mLargeIcon = bitmap0 == null ? null : IconCompat.createWithBitmap(NotificationCompat.reduceLargeIconSize(this.mContext, bitmap0));
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setLargeIcon(Icon icon0) {
            this.mLargeIcon = icon0 == null ? null : IconCompat.createFromIcon(icon0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setLights(int v, int v1, int v2) {
            this.mNotification.ledARGB = v;
            this.mNotification.ledOnMS = v1;
            this.mNotification.ledOffMS = v2;
            this.mNotification.flags = (this.mNotification.ledOnMS == 0 || this.mNotification.ledOffMS == 0 ? 0 : 1) | this.mNotification.flags & -2;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setLocalOnly(boolean z) {
            this.mLocalOnly = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setLocusId(LocusIdCompat locusIdCompat0) {
            this.mLocusId = locusIdCompat0;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.Builder setNotificationSilent() {
            this.mSilent = true;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setNumber(int v) {
            this.mNumber = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setOngoing(boolean z) {
            this.setFlag(2, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setOnlyAlertOnce(boolean z) {
            this.setFlag(8, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setPriority(int v) {
            this.mPriority = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setProgress(int v, int v1, boolean z) {
            this.mProgressMax = v;
            this.mProgress = v1;
            this.mProgressIndeterminate = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setPublicVersion(Notification notification0) {
            this.mPublicVersion = notification0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setRemoteInputHistory(CharSequence[] arr_charSequence) {
            this.mRemoteInputHistory = arr_charSequence;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSettingsText(CharSequence charSequence0) {
            this.mSettingsText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setShortcutId(String s) {
            this.mShortcutId = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setShortcutInfo(ShortcutInfoCompat shortcutInfoCompat0) {
            if(shortcutInfoCompat0 != null) {
                this.mShortcutId = shortcutInfoCompat0.getId();
                if(this.mLocusId == null) {
                    if(shortcutInfoCompat0.getLocusId() != null) {
                        this.mLocusId = shortcutInfoCompat0.getLocusId();
                    }
                    else if(shortcutInfoCompat0.getId() != null) {
                        this.mLocusId = new LocusIdCompat(shortcutInfoCompat0.getId());
                    }
                }
                if(this.mContentTitle == null) {
                    this.setContentTitle(shortcutInfoCompat0.getShortLabel());
                }
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setShowWhen(boolean z) {
            this.mShowWhen = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSilent(boolean z) {
            this.mSilent = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSmallIcon(int v) {
            this.mNotification.icon = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSmallIcon(int v, int v1) {
            this.mNotification.icon = v;
            this.mNotification.iconLevel = v1;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSmallIcon(IconCompat iconCompat0) {
            this.mSmallIcon = iconCompat0.toIcon(this.mContext);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSortKey(String s) {
            this.mSortKey = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSound(Uri uri0) {
            this.mNotification.sound = uri0;
            this.mNotification.audioStreamType = -1;
            AudioAttributes.Builder audioAttributes$Builder0 = Api21Impl.setUsage(Api21Impl.setContentType(Api21Impl.createBuilder(), 4), 5);
            Notification notification0 = this.mNotification;
            notification0.audioAttributes = Api21Impl.build(audioAttributes$Builder0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSound(Uri uri0, int v) {
            this.mNotification.sound = uri0;
            this.mNotification.audioStreamType = v;
            AudioAttributes.Builder audioAttributes$Builder0 = Api21Impl.setLegacyStreamType(Api21Impl.setContentType(Api21Impl.createBuilder(), 4), v);
            Notification notification0 = this.mNotification;
            notification0.audioAttributes = Api21Impl.build(audioAttributes$Builder0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setStyle(Style notificationCompat$Style0) {
            if(this.mStyle != notificationCompat$Style0) {
                this.mStyle = notificationCompat$Style0;
                if(notificationCompat$Style0 != null) {
                    notificationCompat$Style0.setBuilder(this);
                }
            }
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setSubText(CharSequence charSequence0) {
            this.mSubText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setTicker(CharSequence charSequence0) {
            Notification notification0 = this.mNotification;
            notification0.tickerText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.Builder setTicker(CharSequence charSequence0, RemoteViews remoteViews0) {
            Notification notification0 = this.mNotification;
            notification0.tickerText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.mTickerView = remoteViews0;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setTimeoutAfter(long v) {
            this.mTimeout = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setUsesChronometer(boolean z) {
            this.mUseChronometer = z;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setVibrate(long[] arr_v) {
            this.mNotification.vibrate = arr_v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setVisibility(int v) {
            this.mVisibility = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.Builder setWhen(long v) {
            this.mNotification.when = v;
            return this;
        }

        private boolean useExistingRemoteView() {
            return this.mStyle == null || !this.mStyle.displayCustomViewInline();
        }
    }

    public static class CallStyle extends Style {
        static class androidx.core.app.NotificationCompat.CallStyle.Api20Impl {
            static Notification.Action.Builder addExtras(Notification.Action.Builder notification$Action$Builder0, Bundle bundle0) {
                return notification$Action$Builder0.addExtras(bundle0);
            }

            static Notification.Action.Builder addRemoteInput(Notification.Action.Builder notification$Action$Builder0, RemoteInput remoteInput0) {
                return notification$Action$Builder0.addRemoteInput(remoteInput0);
            }

            static Notification.Action build(Notification.Action.Builder notification$Action$Builder0) {
                return notification$Action$Builder0.build();
            }

            static Notification.Action.Builder createActionBuilder(int v, CharSequence charSequence0, PendingIntent pendingIntent0) {
                return new Notification.Action.Builder(v, charSequence0, pendingIntent0);
            }
        }

        static class androidx.core.app.NotificationCompat.CallStyle.Api21Impl {
            static Notification.Builder addPerson(Notification.Builder notification$Builder0, String s) {
                return notification$Builder0.addPerson(s);
            }

            static Notification.Builder setCategory(Notification.Builder notification$Builder0, String s) {
                return notification$Builder0.setCategory(s);
            }
        }

        static class androidx.core.app.NotificationCompat.CallStyle.Api23Impl {
            static Parcelable castToParcelable(Icon icon0) {
                return icon0;
            }

            static Notification.Action.Builder createActionBuilder(Icon icon0, CharSequence charSequence0, PendingIntent pendingIntent0) {
                return new Notification.Action.Builder(icon0, charSequence0, pendingIntent0);
            }

            static void setLargeIcon(Notification.Builder notification$Builder0, Icon icon0) {
                notification$Builder0.setLargeIcon(icon0);
            }
        }

        static class androidx.core.app.NotificationCompat.CallStyle.Api24Impl {
            static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder notification$Action$Builder0, boolean z) {
                return notification$Action$Builder0.setAllowGeneratedReplies(z);
            }
        }

        static class androidx.core.app.NotificationCompat.CallStyle.Api28Impl {
            static Notification.Builder addPerson(Notification.Builder notification$Builder0, android.app.Person person0) {
                return notification$Builder0.addPerson(person0);
            }

            static Parcelable castToParcelable(android.app.Person person0) {
                return person0;
            }
        }

        static class androidx.core.app.NotificationCompat.CallStyle.Api31Impl {
            static Notification.CallStyle forIncomingCall(android.app.Person person0, PendingIntent pendingIntent0, PendingIntent pendingIntent1) {
                return Notification.CallStyle.forIncomingCall(person0, pendingIntent0, pendingIntent1);
            }

            static Notification.CallStyle forOngoingCall(android.app.Person person0, PendingIntent pendingIntent0) {
                return Notification.CallStyle.forOngoingCall(person0, pendingIntent0);
            }

            static Notification.CallStyle forScreeningCall(android.app.Person person0, PendingIntent pendingIntent0, PendingIntent pendingIntent1) {
                return Notification.CallStyle.forScreeningCall(person0, pendingIntent0, pendingIntent1);
            }

            static Notification.CallStyle setAnswerButtonColorHint(Notification.CallStyle notification$CallStyle0, int v) {
                return notification$CallStyle0.setAnswerButtonColorHint(v);
            }

            static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder notification$Action$Builder0, boolean z) {
                return notification$Action$Builder0.setAuthenticationRequired(z);
            }

            static Notification.CallStyle setDeclineButtonColorHint(Notification.CallStyle notification$CallStyle0, int v) {
                return notification$CallStyle0.setDeclineButtonColorHint(v);
            }

            static Notification.CallStyle setIsVideo(Notification.CallStyle notification$CallStyle0, boolean z) {
                return notification$CallStyle0.setIsVideo(z);
            }

            static Notification.CallStyle setVerificationIcon(Notification.CallStyle notification$CallStyle0, Icon icon0) {
                return notification$CallStyle0.setVerificationIcon(icon0);
            }

            static Notification.CallStyle setVerificationText(Notification.CallStyle notification$CallStyle0, CharSequence charSequence0) {
                return notification$CallStyle0.setVerificationText(charSequence0);
            }
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface CallType {
        }

        public static final int CALL_TYPE_INCOMING = 1;
        public static final int CALL_TYPE_ONGOING = 2;
        public static final int CALL_TYPE_SCREENING = 3;
        public static final int CALL_TYPE_UNKNOWN = 0;
        private static final String KEY_ACTION_PRIORITY = "key_action_priority";
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$CallStyle";
        private Integer mAnswerButtonColor;
        private PendingIntent mAnswerIntent;
        private int mCallType;
        private Integer mDeclineButtonColor;
        private PendingIntent mDeclineIntent;
        private PendingIntent mHangUpIntent;
        private boolean mIsVideo;
        private Person mPerson;
        private IconCompat mVerificationIcon;
        private CharSequence mVerificationText;

        public CallStyle() {
        }

        private CallStyle(int v, Person person0, PendingIntent pendingIntent0, PendingIntent pendingIntent1, PendingIntent pendingIntent2) {
            if(person0 == null || TextUtils.isEmpty(person0.getName())) {
                throw new IllegalArgumentException("person must have a non-empty a name");
            }
            this.mCallType = v;
            this.mPerson = person0;
            this.mAnswerIntent = pendingIntent2;
            this.mDeclineIntent = pendingIntent1;
            this.mHangUpIntent = pendingIntent0;
        }

        public CallStyle(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            this.setBuilder(notificationCompat$Builder0);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public void addCompatExtras(Bundle bundle0) {
            super.addCompatExtras(bundle0);
            bundle0.putInt("android.callType", this.mCallType);
            bundle0.putBoolean("android.callIsVideo", this.mIsVideo);
            if(this.mPerson != null) {
                if(Build.VERSION.SDK_INT >= 28) {
                    bundle0.putParcelable("android.callPerson", androidx.core.app.NotificationCompat.CallStyle.Api28Impl.castToParcelable(this.mPerson.toAndroidPerson()));
                }
                else {
                    bundle0.putParcelable("android.callPersonCompat", this.mPerson.toBundle());
                }
            }
            if(this.mVerificationIcon != null) {
                if(Build.VERSION.SDK_INT >= 23) {
                    bundle0.putParcelable("android.verificationIcon", androidx.core.app.NotificationCompat.CallStyle.Api23Impl.castToParcelable(this.mVerificationIcon.toIcon(this.mBuilder.mContext)));
                }
                else {
                    bundle0.putParcelable("android.verificationIconCompat", this.mVerificationIcon.toBundle());
                }
            }
            bundle0.putCharSequence("android.verificationText", this.mVerificationText);
            bundle0.putParcelable("android.answerIntent", this.mAnswerIntent);
            bundle0.putParcelable("android.declineIntent", this.mDeclineIntent);
            bundle0.putParcelable("android.hangUpIntent", this.mHangUpIntent);
            Integer integer0 = this.mAnswerButtonColor;
            if(integer0 != null) {
                bundle0.putInt("android.answerColor", ((int)integer0));
            }
            Integer integer1 = this.mDeclineButtonColor;
            if(integer1 != null) {
                bundle0.putInt("android.declineColor", ((int)integer1));
            }
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            Notification.CallStyle notification$CallStyle0 = null;
            if(Build.VERSION.SDK_INT >= 0x1F) {
                switch(this.mCallType) {
                    case 1: {
                        notification$CallStyle0 = androidx.core.app.NotificationCompat.CallStyle.Api31Impl.forIncomingCall(this.mPerson.toAndroidPerson(), this.mDeclineIntent, this.mAnswerIntent);
                        break;
                    }
                    case 2: {
                        notification$CallStyle0 = androidx.core.app.NotificationCompat.CallStyle.Api31Impl.forOngoingCall(this.mPerson.toAndroidPerson(), this.mHangUpIntent);
                        break;
                    }
                    case 3: {
                        notification$CallStyle0 = androidx.core.app.NotificationCompat.CallStyle.Api31Impl.forScreeningCall(this.mPerson.toAndroidPerson(), this.mHangUpIntent, this.mAnswerIntent);
                        break;
                    }
                    default: {
                        if(Log.isLoggable("NotifCompat", 3)) {
                            Log.d("NotifCompat", "Unrecognized call type in CallStyle: " + this.mCallType);
                        }
                    }
                }
                if(notification$CallStyle0 != null) {
                    notification$CallStyle0.setBuilder(notificationBuilderWithBuilderAccessor0.getBuilder());
                    Integer integer0 = this.mAnswerButtonColor;
                    if(integer0 != null) {
                        androidx.core.app.NotificationCompat.CallStyle.Api31Impl.setAnswerButtonColorHint(notification$CallStyle0, ((int)integer0));
                    }
                    Integer integer1 = this.mDeclineButtonColor;
                    if(integer1 != null) {
                        androidx.core.app.NotificationCompat.CallStyle.Api31Impl.setDeclineButtonColorHint(notification$CallStyle0, ((int)integer1));
                    }
                    androidx.core.app.NotificationCompat.CallStyle.Api31Impl.setVerificationText(notification$CallStyle0, this.mVerificationText);
                    IconCompat iconCompat0 = this.mVerificationIcon;
                    if(iconCompat0 != null) {
                        androidx.core.app.NotificationCompat.CallStyle.Api31Impl.setVerificationIcon(notification$CallStyle0, iconCompat0.toIcon(this.mBuilder.mContext));
                    }
                    androidx.core.app.NotificationCompat.CallStyle.Api31Impl.setIsVideo(notification$CallStyle0, this.mIsVideo);
                }
                return;
            }
            Notification.Builder notification$Builder0 = notificationBuilderWithBuilderAccessor0.getBuilder();
            notification$Builder0.setContentTitle((this.mPerson == null ? null : this.mPerson.getName()));
            if(this.mBuilder.mExtras != null && this.mBuilder.mExtras.containsKey("android.text")) {
                notification$CallStyle0 = this.mBuilder.mExtras.getCharSequence("android.text");
            }
            if(notification$CallStyle0 == null) {
                notification$CallStyle0 = this.getDefaultText();
            }
            notification$Builder0.setContentText(((CharSequence)notification$CallStyle0));
            if(this.mPerson != null) {
                if(Build.VERSION.SDK_INT >= 23 && this.mPerson.getIcon() != null) {
                    androidx.core.app.NotificationCompat.CallStyle.Api23Impl.setLargeIcon(notification$Builder0, this.mPerson.getIcon().toIcon(this.mBuilder.mContext));
                }
                if(Build.VERSION.SDK_INT >= 28) {
                    androidx.core.app.NotificationCompat.CallStyle.Api28Impl.addPerson(notification$Builder0, this.mPerson.toAndroidPerson());
                }
                else {
                    androidx.core.app.NotificationCompat.CallStyle.Api21Impl.addPerson(notification$Builder0, this.mPerson.getUri());
                }
            }
            androidx.core.app.NotificationCompat.CallStyle.Api21Impl.setCategory(notification$Builder0, "call");
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public boolean displayCustomViewInline() {
            return true;
        }

        public static CallStyle forIncomingCall(Person person0, PendingIntent pendingIntent0, PendingIntent pendingIntent1) {
            return new CallStyle(1, person0, null, ((PendingIntent)Objects.requireNonNull(pendingIntent0, "declineIntent is required")), ((PendingIntent)Objects.requireNonNull(pendingIntent1, "answerIntent is required")));
        }

        public static CallStyle forOngoingCall(Person person0, PendingIntent pendingIntent0) {
            return new CallStyle(2, person0, ((PendingIntent)Objects.requireNonNull(pendingIntent0, "hangUpIntent is required")), null, null);
        }

        public static CallStyle forScreeningCall(Person person0, PendingIntent pendingIntent0, PendingIntent pendingIntent1) {
            return new CallStyle(3, person0, ((PendingIntent)Objects.requireNonNull(pendingIntent0, "hangUpIntent is required")), null, ((PendingIntent)Objects.requireNonNull(pendingIntent1, "answerIntent is required")));
        }

        public ArrayList getActionsListWithSystemActions() {
            Action notificationCompat$Action0 = this.makeNegativeAction();
            Action notificationCompat$Action1 = this.makeAnswerAction();
            ArrayList arrayList0 = new ArrayList(3);
            arrayList0.add(notificationCompat$Action0);
            ArrayList arrayList1 = this.mBuilder.mActions;
            int v = 2;
            if(arrayList1 != null) {
                for(Object object0: arrayList1) {
                    Action notificationCompat$Action2 = (Action)object0;
                    if(notificationCompat$Action2.isContextual()) {
                        arrayList0.add(notificationCompat$Action2);
                    }
                    else if(!this.isActionAddedByCallStyle(notificationCompat$Action2) && v > 1) {
                        arrayList0.add(notificationCompat$Action2);
                        --v;
                    }
                    if(notificationCompat$Action1 != null && v == 1) {
                        arrayList0.add(notificationCompat$Action1);
                        v = 0;
                    }
                }
            }
            if(notificationCompat$Action1 != null && v >= 1) {
                arrayList0.add(notificationCompat$Action1);
            }
            return arrayList0;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected String getClassName() {
            return "androidx.core.app.NotificationCompat$CallStyle";
        }

        private String getDefaultText() {
            switch(this.mCallType) {
                case 1: {
                    return this.mBuilder.mContext.getResources().getString(string.call_notification_incoming_text);
                }
                case 2: {
                    return this.mBuilder.mContext.getResources().getString(string.call_notification_ongoing_text);
                }
                case 3: {
                    return this.mBuilder.mContext.getResources().getString(string.call_notification_screening_text);
                }
                default: {
                    return null;
                }
            }
        }

        private boolean isActionAddedByCallStyle(Action notificationCompat$Action0) {
            return notificationCompat$Action0 != null && notificationCompat$Action0.getExtras().getBoolean("key_action_priority");
        }

        private Action makeAction(int v, int v1, Integer integer0, int v2, PendingIntent pendingIntent0) {
            if(integer0 == null) {
                integer0 = ContextCompat.getColor(this.mBuilder.mContext, v2);
            }
            SpannableStringBuilder spannableStringBuilder0 = new SpannableStringBuilder();
            spannableStringBuilder0.append(this.mBuilder.mContext.getResources().getString(v1));
            spannableStringBuilder0.setSpan(new ForegroundColorSpan(((int)integer0)), 0, spannableStringBuilder0.length(), 18);
            Action notificationCompat$Action0 = new Builder(IconCompat.createWithResource(this.mBuilder.mContext, v), spannableStringBuilder0, pendingIntent0).build();
            notificationCompat$Action0.getExtras().putBoolean("key_action_priority", true);
            return notificationCompat$Action0;
        }

        private Action makeAnswerAction() {
            if(this.mAnswerIntent == null) {
                return null;
            }
            int v = this.mIsVideo ? 0x7F0800B2 : 0x7F0800B0;  // drawable:ic_call_answer_video
            return this.mIsVideo ? this.makeAction(v, string.call_notification_answer_video_action, this.mAnswerButtonColor, color.call_notification_answer_color, this.mAnswerIntent) : this.makeAction(v, string.call_notification_answer_action, this.mAnswerButtonColor, color.call_notification_answer_color, this.mAnswerIntent);
        }

        private Action makeNegativeAction() {
            return this.mDeclineIntent == null ? this.makeAction(0x7F0800B4, string.call_notification_hang_up_action, this.mDeclineButtonColor, color.call_notification_decline_color, this.mHangUpIntent) : this.makeAction(0x7F0800B4, string.call_notification_decline_action, this.mDeclineButtonColor, color.call_notification_decline_color, this.mDeclineIntent);  // drawable:ic_call_decline
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected void restoreFromCompatExtras(Bundle bundle0) {
            super.restoreFromCompatExtras(bundle0);
            this.mCallType = bundle0.getInt("android.callType");
            this.mIsVideo = bundle0.getBoolean("android.callIsVideo");
            if(Build.VERSION.SDK_INT >= 28 && bundle0.containsKey("android.callPerson")) {
                this.mPerson = Person.fromAndroidPerson(ComponentDialog..ExternalSyntheticApiModelOutline0.m(bundle0.getParcelable("android.callPerson")));
            }
            else if(bundle0.containsKey("android.callPersonCompat")) {
                this.mPerson = Person.fromBundle(bundle0.getBundle("android.callPersonCompat"));
            }
            if(Build.VERSION.SDK_INT >= 23 && bundle0.containsKey("android.verificationIcon")) {
                this.mVerificationIcon = IconCompat.createFromIcon(ComponentDialog..ExternalSyntheticApiModelOutline0.m(bundle0.getParcelable("android.verificationIcon")));
            }
            else if(bundle0.containsKey("android.verificationIconCompat")) {
                this.mVerificationIcon = IconCompat.createFromBundle(bundle0.getBundle("android.verificationIconCompat"));
            }
            this.mVerificationText = bundle0.getCharSequence("android.verificationText");
            this.mAnswerIntent = (PendingIntent)bundle0.getParcelable("android.answerIntent");
            this.mDeclineIntent = (PendingIntent)bundle0.getParcelable("android.declineIntent");
            this.mHangUpIntent = (PendingIntent)bundle0.getParcelable("android.hangUpIntent");
            Integer integer0 = null;
            this.mAnswerButtonColor = bundle0.containsKey("android.answerColor") ? bundle0.getInt("android.answerColor") : null;
            if(bundle0.containsKey("android.declineColor")) {
                integer0 = bundle0.getInt("android.declineColor");
            }
            this.mDeclineButtonColor = integer0;
        }

        public CallStyle setAnswerButtonColorHint(int v) {
            this.mAnswerButtonColor = v;
            return this;
        }

        public CallStyle setDeclineButtonColorHint(int v) {
            this.mDeclineButtonColor = v;
            return this;
        }

        public CallStyle setIsVideo(boolean z) {
            this.mIsVideo = z;
            return this;
        }

        public CallStyle setVerificationIcon(Bitmap bitmap0) {
            this.mVerificationIcon = IconCompat.createWithBitmap(bitmap0);
            return this;
        }

        public CallStyle setVerificationIcon(Icon icon0) {
            this.mVerificationIcon = icon0 == null ? null : IconCompat.createFromIcon(icon0);
            return this;
        }

        public CallStyle setVerificationText(CharSequence charSequence0) {
            this.mVerificationText = charSequence0;
            return this;
        }
    }

    public static final class CarExtender implements androidx.core.app.NotificationCompat.Extender {
        static class androidx.core.app.NotificationCompat.CarExtender.Api20Impl {
            static RemoteInput.Builder addExtras(RemoteInput.Builder remoteInput$Builder0, Bundle bundle0) {
                return remoteInput$Builder0.addExtras(bundle0);
            }

            static RemoteInput build(RemoteInput.Builder remoteInput$Builder0) {
                return remoteInput$Builder0.build();
            }

            static Parcelable castToParcelable(RemoteInput remoteInput0) {
                return remoteInput0;
            }

            static RemoteInput.Builder createBuilder(String s) {
                return new RemoteInput.Builder(s);
            }

            static boolean getAllowFreeFormInput(RemoteInput remoteInput0) {
                return remoteInput0.getAllowFreeFormInput();
            }

            static CharSequence[] getChoices(RemoteInput remoteInput0) {
                return remoteInput0.getChoices();
            }

            static Bundle getExtras(RemoteInput remoteInput0) {
                return remoteInput0.getExtras();
            }

            static CharSequence getLabel(RemoteInput remoteInput0) {
                return remoteInput0.getLabel();
            }

            static String getResultKey(RemoteInput remoteInput0) {
                return remoteInput0.getResultKey();
            }

            static RemoteInput.Builder setAllowFreeFormInput(RemoteInput.Builder remoteInput$Builder0, boolean z) {
                return remoteInput$Builder0.setAllowFreeFormInput(z);
            }

            static RemoteInput.Builder setChoices(RemoteInput.Builder remoteInput$Builder0, CharSequence[] arr_charSequence) {
                return remoteInput$Builder0.setChoices(arr_charSequence);
            }

            static RemoteInput.Builder setLabel(RemoteInput.Builder remoteInput$Builder0, CharSequence charSequence0) {
                return remoteInput$Builder0.setLabel(charSequence0);
            }
        }

        static class androidx.core.app.NotificationCompat.CarExtender.Api29Impl {
            static int getEditChoicesBeforeSending(RemoteInput remoteInput0) {
                return remoteInput0.getEditChoicesBeforeSending();
            }
        }

        @Deprecated
        public static class UnreadConversation {
            public static class androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder {
                private long mLatestTimestamp;
                private final List mMessages;
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private androidx.core.app.RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder(String s) {
                    this.mMessages = new ArrayList();
                    this.mParticipant = s;
                }

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder addMessage(String s) {
                    if(s != null) {
                        this.mMessages.add(s);
                    }
                    return this;
                }

                public UnreadConversation build() {
                    return new UnreadConversation(((String[])this.mMessages.toArray(new String[this.mMessages.size()])), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                }

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder setLatestTimestamp(long v) {
                    this.mLatestTimestamp = v;
                    return this;
                }

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder setReadPendingIntent(PendingIntent pendingIntent0) {
                    this.mReadPendingIntent = pendingIntent0;
                    return this;
                }

                public androidx.core.app.NotificationCompat.CarExtender.UnreadConversation.Builder setReplyAction(PendingIntent pendingIntent0, androidx.core.app.RemoteInput remoteInput0) {
                    this.mRemoteInput = remoteInput0;
                    this.mReplyPendingIntent = pendingIntent0;
                    return this;
                }
            }

            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final androidx.core.app.RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            UnreadConversation(String[] arr_s, androidx.core.app.RemoteInput remoteInput0, PendingIntent pendingIntent0, PendingIntent pendingIntent1, String[] arr_s1, long v) {
                this.mMessages = arr_s;
                this.mRemoteInput = remoteInput0;
                this.mReadPendingIntent = pendingIntent1;
                this.mReplyPendingIntent = pendingIntent0;
                this.mParticipants = arr_s1;
                this.mLatestTimestamp = v;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public String getParticipant() {
                return this.mParticipants.length <= 0 ? null : this.mParticipants[0];
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public androidx.core.app.RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }
        }

        static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public CarExtender() {
            this.mColor = 0;
        }

        public CarExtender(Notification notification0) {
            this.mColor = 0;
            Bundle bundle0 = NotificationCompat.getExtras(notification0) == null ? null : NotificationCompat.getExtras(notification0).getBundle("android.car.EXTENSIONS");
            if(bundle0 != null) {
                this.mLargeIcon = (Bitmap)bundle0.getParcelable("large_icon");
                this.mColor = bundle0.getInt("app_color", 0);
                this.mUnreadConversation = CarExtender.getUnreadConversationFromBundle(bundle0.getBundle("car_conversation"));
            }
        }

        @Override  // androidx.core.app.NotificationCompat$Extender
        public androidx.core.app.NotificationCompat.Builder extend(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            Bundle bundle0 = new Bundle();
            Bitmap bitmap0 = this.mLargeIcon;
            if(bitmap0 != null) {
                bundle0.putParcelable("large_icon", bitmap0);
            }
            int v = this.mColor;
            if(v != 0) {
                bundle0.putInt("app_color", v);
            }
            UnreadConversation notificationCompat$CarExtender$UnreadConversation0 = this.mUnreadConversation;
            if(notificationCompat$CarExtender$UnreadConversation0 != null) {
                bundle0.putBundle("car_conversation", CarExtender.getBundleForUnreadConversation(notificationCompat$CarExtender$UnreadConversation0));
            }
            notificationCompat$Builder0.getExtras().putBundle("android.car.EXTENSIONS", bundle0);
            return notificationCompat$Builder0;
        }

        private static Bundle getBundleForUnreadConversation(UnreadConversation notificationCompat$CarExtender$UnreadConversation0) {
            Bundle bundle0 = new Bundle();
            String s = notificationCompat$CarExtender$UnreadConversation0.getParticipants() == null || notificationCompat$CarExtender$UnreadConversation0.getParticipants().length <= 1 ? null : notificationCompat$CarExtender$UnreadConversation0.getParticipants()[0];
            String[] arr_s = notificationCompat$CarExtender$UnreadConversation0.getMessages();
            Parcelable[] arr_parcelable = new Parcelable[arr_s.length];
            for(int v = 0; v < arr_s.length; ++v) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("text", notificationCompat$CarExtender$UnreadConversation0.getMessages()[v]);
                bundle1.putString("author", s);
                arr_parcelable[v] = bundle1;
            }
            bundle0.putParcelableArray("messages", arr_parcelable);
            androidx.core.app.RemoteInput remoteInput0 = notificationCompat$CarExtender$UnreadConversation0.getRemoteInput();
            if(remoteInput0 != null) {
                RemoteInput.Builder remoteInput$Builder0 = androidx.core.app.NotificationCompat.CarExtender.Api20Impl.createBuilder(remoteInput0.getResultKey());
                androidx.core.app.NotificationCompat.CarExtender.Api20Impl.setLabel(remoteInput$Builder0, remoteInput0.getLabel());
                androidx.core.app.NotificationCompat.CarExtender.Api20Impl.setChoices(remoteInput$Builder0, remoteInput0.getChoices());
                androidx.core.app.NotificationCompat.CarExtender.Api20Impl.setAllowFreeFormInput(remoteInput$Builder0, remoteInput0.getAllowFreeFormInput());
                androidx.core.app.NotificationCompat.CarExtender.Api20Impl.addExtras(remoteInput$Builder0, remoteInput0.getExtras());
                bundle0.putParcelable("remote_input", androidx.core.app.NotificationCompat.CarExtender.Api20Impl.castToParcelable(androidx.core.app.NotificationCompat.CarExtender.Api20Impl.build(remoteInput$Builder0)));
            }
            bundle0.putParcelable("on_reply", notificationCompat$CarExtender$UnreadConversation0.getReplyPendingIntent());
            bundle0.putParcelable("on_read", notificationCompat$CarExtender$UnreadConversation0.getReadPendingIntent());
            bundle0.putStringArray("participants", notificationCompat$CarExtender$UnreadConversation0.getParticipants());
            bundle0.putLong("timestamp", notificationCompat$CarExtender$UnreadConversation0.getLatestTimestamp());
            return bundle0;
        }

        public int getColor() {
            return this.mColor;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        @Deprecated
        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        private static UnreadConversation getUnreadConversationFromBundle(Bundle bundle0) {
            String[] arr_s1;
            if(bundle0 == null) {
                return null;
            }
            Parcelable[] arr_parcelable = bundle0.getParcelableArray("messages");
            if(arr_parcelable == null) {
                arr_s1 = null;
            }
            else {
                String[] arr_s = new String[arr_parcelable.length];
                int v = 0;
                while(v < arr_parcelable.length) {
                    Parcelable parcelable0 = arr_parcelable[v];
                    if(parcelable0 instanceof Bundle) {
                        String s = ((Bundle)parcelable0).getString("text");
                        arr_s[v] = s;
                        if(s == null) {
                            return null;
                        }
                        ++v;
                        continue;
                    }
                    return null;
                }
                arr_s1 = arr_s;
            }
            Parcelable parcelable1 = bundle0.getParcelable("on_read");
            Parcelable parcelable2 = bundle0.getParcelable("on_reply");
            RemoteInput remoteInput0 = (RemoteInput)bundle0.getParcelable("remote_input");
            String[] arr_s2 = bundle0.getStringArray("participants");
            if(arr_s2 != null && arr_s2.length == 1) {
                if(remoteInput0 != null) {
                    String s1 = androidx.core.app.NotificationCompat.CarExtender.Api20Impl.getResultKey(remoteInput0);
                    CharSequence charSequence0 = androidx.core.app.NotificationCompat.CarExtender.Api20Impl.getLabel(remoteInput0);
                    CharSequence[] arr_charSequence = androidx.core.app.NotificationCompat.CarExtender.Api20Impl.getChoices(remoteInput0);
                    boolean z = androidx.core.app.NotificationCompat.CarExtender.Api20Impl.getAllowFreeFormInput(remoteInput0);
                    return Build.VERSION.SDK_INT < 29 ? new UnreadConversation(arr_s1, new androidx.core.app.RemoteInput(s1, charSequence0, arr_charSequence, z, 0, androidx.core.app.NotificationCompat.CarExtender.Api20Impl.getExtras(remoteInput0), null), ((PendingIntent)parcelable2), ((PendingIntent)parcelable1), arr_s2, bundle0.getLong("timestamp")) : new UnreadConversation(arr_s1, new androidx.core.app.RemoteInput(s1, charSequence0, arr_charSequence, z, androidx.core.app.NotificationCompat.CarExtender.Api29Impl.getEditChoicesBeforeSending(remoteInput0), androidx.core.app.NotificationCompat.CarExtender.Api20Impl.getExtras(remoteInput0), null), ((PendingIntent)parcelable2), ((PendingIntent)parcelable1), arr_s2, bundle0.getLong("timestamp"));
                }
                return new UnreadConversation(arr_s1, null, ((PendingIntent)parcelable2), ((PendingIntent)parcelable1), arr_s2, bundle0.getLong("timestamp"));
            }
            return null;
        }

        public CarExtender setColor(int v) {
            this.mColor = v;
            return this;
        }

        public CarExtender setLargeIcon(Bitmap bitmap0) {
            this.mLargeIcon = bitmap0;
            return this;
        }

        @Deprecated
        public CarExtender setUnreadConversation(UnreadConversation notificationCompat$CarExtender$UnreadConversation0) {
            this.mUnreadConversation = notificationCompat$CarExtender$UnreadConversation0;
            return this;
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        static class androidx.core.app.NotificationCompat.DecoratedCustomViewStyle.Api24Impl {
            static Notification.Style createDecoratedCustomViewStyle() {
                return new Notification.DecoratedCustomViewStyle();
            }
        }

        private static final int MAX_ACTION_BUTTONS = 3;
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";

        @Override  // androidx.core.app.NotificationCompat$Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor0.getBuilder().setStyle(androidx.core.app.NotificationCompat.DecoratedCustomViewStyle.Api24Impl.createDecoratedCustomViewStyle());
            }
        }

        private RemoteViews createRemoteViews(RemoteViews remoteViews0, boolean z) {
            int v = 0;
            RemoteViews remoteViews1 = this.applyStandardTemplate(true, layout.notification_template_custom_big, false);
            remoteViews1.removeAllViews(id.actions);
            List list0 = DecoratedCustomViewStyle.getNonContextualActions(this.mBuilder.mActions);
            if(!z || list0 == null) {
                v = 8;
            }
            else {
                int v1 = Math.min(list0.size(), 3);
                if(v1 > 0) {
                    for(int v2 = 0; v2 < v1; ++v2) {
                        RemoteViews remoteViews2 = this.generateActionButton(((Action)list0.get(v2)));
                        remoteViews1.addView(id.actions, remoteViews2);
                    }
                }
                else {
                    v = 8;
                }
            }
            remoteViews1.setViewVisibility(id.actions, v);
            remoteViews1.setViewVisibility(id.action_divider, v);
            this.buildIntoRemoteViews(remoteViews1, remoteViews0);
            return remoteViews1;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public boolean displayCustomViewInline() {
            return true;
        }

        private RemoteViews generateActionButton(Action notificationCompat$Action0) {
            boolean z = notificationCompat$Action0.actionIntent == null;
            RemoteViews remoteViews0 = new RemoteViews("com.ruliweb.www.ruliapp", (z ? layout.notification_action_tombstone : layout.notification_action));
            IconCompat iconCompat0 = notificationCompat$Action0.getIconCompat();
            if(iconCompat0 != null) {
                Bitmap bitmap0 = this.createColoredBitmap(iconCompat0, color.notification_action_color_filter);
                remoteViews0.setImageViewBitmap(id.action_image, bitmap0);
            }
            remoteViews0.setTextViewText(id.action_text, notificationCompat$Action0.title);
            if(!z) {
                remoteViews0.setOnClickPendingIntent(id.action_container, notificationCompat$Action0.actionIntent);
            }
            remoteViews0.setContentDescription(id.action_container, notificationCompat$Action0.title);
            return remoteViews0;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected String getClassName() {
            return "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";
        }

        private static List getNonContextualActions(List list0) {
            if(list0 == null) {
                return null;
            }
            List list1 = new ArrayList();
            for(Object object0: list0) {
                Action notificationCompat$Action0 = (Action)object0;
                if(!notificationCompat$Action0.isContextual()) {
                    list1.add(notificationCompat$Action0);
                }
            }
            return list1;
        }

        public static List getTextsFromContentView(Context context0, Notification notification0) {
            ApplicationInfo applicationInfo0;
            Context context1;
            RemoteViews remoteViews0;
            if(!"android.app.Notification$DecoratedCustomViewStyle".equals(notification0.extras.getString("android.template"))) {
                return Collections.EMPTY_LIST;
            }
            if(notification0.contentView == null && notification0.bigContentView == null && notification0.headsUpContentView == null) {
                return Collections.EMPTY_LIST;
            }
            if(notification0.bigContentView == null) {
                remoteViews0 = notification0.contentView == null ? notification0.headsUpContentView : notification0.contentView;
            }
            else {
                remoteViews0 = notification0.bigContentView;
            }
            String s = remoteViews0.getPackage();
            try {
                context1 = context0.createPackageContext(s, 0);
                applicationInfo0 = context0.getPackageManager().getApplicationInfo(s, 0);
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                throw new RuntimeException(packageManager$NameNotFoundException0);
            }
            context1.setTheme(applicationInfo0.theme);
            View view0 = remoteViews0.apply(context1, null);
            List list0 = new ArrayList();
            DecoratedCustomViewStyle.getTextsFromViewTraversal(view0, ((ArrayList)list0));
            return list0;
        }

        private static void getTextsFromViewTraversal(View view0, ArrayList arrayList0) {
            if(view0 instanceof ViewGroup) {
                for(int v = 0; v < ((ViewGroup)view0).getChildCount(); ++v) {
                    View view1 = ((ViewGroup)view0).getChildAt(v);
                    if(view1 instanceof TextView) {
                        CharSequence charSequence0 = ((TextView)view1).getText();
                        if(charSequence0 != null && charSequence0.length() > 0) {
                            arrayList0.add(charSequence0);
                        }
                    }
                    if(view1 instanceof ViewGroup) {
                        DecoratedCustomViewStyle.getTextsFromViewTraversal(view1, arrayList0);
                    }
                }
            }
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews remoteViews0 = this.mBuilder.getBigContentView();
            if(remoteViews0 == null) {
                remoteViews0 = this.mBuilder.getContentView();
            }
            return remoteViews0 == null ? null : this.createRemoteViews(remoteViews0, true);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            return this.mBuilder.getContentView() == null ? null : this.createRemoteViews(this.mBuilder.getContentView(), false);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            if(Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews remoteViews0 = this.mBuilder.getHeadsUpContentView();
            return remoteViews0 == null ? null : this.createRemoteViews((remoteViews0 == null ? this.mBuilder.getContentView() : remoteViews0), true);
        }
    }

    public interface androidx.core.app.NotificationCompat.Extender {
        androidx.core.app.NotificationCompat.Builder extend(androidx.core.app.NotificationCompat.Builder arg1);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupAlertBehavior {
    }

    public static class InboxStyle extends Style {
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$InboxStyle";
        private ArrayList mTexts;

        public InboxStyle() {
            this.mTexts = new ArrayList();
        }

        public InboxStyle(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            this.mTexts = new ArrayList();
            this.setBuilder(notificationCompat$Builder0);
        }

        public InboxStyle addLine(CharSequence charSequence0) {
            if(charSequence0 != null) {
                this.mTexts.add(androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0));
            }
            return this;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            Notification.InboxStyle notification$InboxStyle0 = new Notification.InboxStyle(notificationBuilderWithBuilderAccessor0.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            if(this.mSummaryTextSet) {
                notification$InboxStyle0.setSummaryText(this.mSummaryText);
            }
            for(Object object0: this.mTexts) {
                notification$InboxStyle0.addLine(((CharSequence)object0));
            }
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected void clearCompatExtraKeys(Bundle bundle0) {
            super.clearCompatExtraKeys(bundle0);
            bundle0.remove("android.textLines");
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected String getClassName() {
            return "androidx.core.app.NotificationCompat$InboxStyle";
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected void restoreFromCompatExtras(Bundle bundle0) {
            super.restoreFromCompatExtras(bundle0);
            this.mTexts.clear();
            if(bundle0.containsKey("android.textLines")) {
                Collections.addAll(this.mTexts, bundle0.getCharSequenceArray("android.textLines"));
            }
        }

        public InboxStyle setBigContentTitle(CharSequence charSequence0) {
            this.mBigContentTitle = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charSequence0) {
            this.mSummaryText = androidx.core.app.NotificationCompat.Builder.limitCharSequenceLength(charSequence0);
            this.mSummaryTextSet = true;
            return this;
        }
    }

    public static class MessagingStyle extends Style {
        static class androidx.core.app.NotificationCompat.MessagingStyle.Api24Impl {
            static Notification.MessagingStyle addMessage(Notification.MessagingStyle notification$MessagingStyle0, Notification.MessagingStyle.Message notification$MessagingStyle$Message0) {
                return notification$MessagingStyle0.addMessage(notification$MessagingStyle$Message0);
            }

            static Notification.MessagingStyle createMessagingStyle(CharSequence charSequence0) {
                return new Notification.MessagingStyle(charSequence0);
            }

            static Notification.MessagingStyle setConversationTitle(Notification.MessagingStyle notification$MessagingStyle0, CharSequence charSequence0) {
                return notification$MessagingStyle0.setConversationTitle(charSequence0);
            }
        }

        static class androidx.core.app.NotificationCompat.MessagingStyle.Api26Impl {
            static Notification.MessagingStyle addHistoricMessage(Notification.MessagingStyle notification$MessagingStyle0, Notification.MessagingStyle.Message notification$MessagingStyle$Message0) {
                return notification$MessagingStyle0.addHistoricMessage(notification$MessagingStyle$Message0);
            }
        }

        static class androidx.core.app.NotificationCompat.MessagingStyle.Api28Impl {
            static Notification.MessagingStyle createMessagingStyle(android.app.Person person0) {
                return new Notification.MessagingStyle(person0);
            }

            static Notification.MessagingStyle setGroupConversation(Notification.MessagingStyle notification$MessagingStyle0, boolean z) {
                return notification$MessagingStyle0.setGroupConversation(z);
            }
        }

        public static final class Message {
            static class androidx.core.app.NotificationCompat.MessagingStyle.Message.Api24Impl {
                static Notification.MessagingStyle.Message createMessage(CharSequence charSequence0, long v, CharSequence charSequence1) {
                    return new Notification.MessagingStyle.Message(charSequence0, v, charSequence1);
                }

                static Notification.MessagingStyle.Message setData(Notification.MessagingStyle.Message notification$MessagingStyle$Message0, String s, Uri uri0) {
                    return notification$MessagingStyle$Message0.setData(s, uri0);
                }
            }

            static class androidx.core.app.NotificationCompat.MessagingStyle.Message.Api28Impl {
                static Parcelable castToParcelable(android.app.Person person0) {
                    return person0;
                }

                static Notification.MessagingStyle.Message createMessage(CharSequence charSequence0, long v, android.app.Person person0) {
                    return new Notification.MessagingStyle.Message(charSequence0, v, person0);
                }
            }

            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_EXTRAS_BUNDLE = "extras";
            static final String KEY_NOTIFICATION_PERSON = "sender_person";
            static final String KEY_PERSON = "person";
            static final String KEY_SENDER = "sender";
            static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            private String mDataMimeType;
            private Uri mDataUri;
            private Bundle mExtras;
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence charSequence0, long v, Person person0) {
                this.mExtras = new Bundle();
                this.mText = charSequence0;
                this.mTimestamp = v;
                this.mPerson = person0;
            }

            @Deprecated
            public Message(CharSequence charSequence0, long v, CharSequence charSequence1) {
                this(charSequence0, v, new androidx.core.app.Person.Builder().setName(charSequence1).build());
            }

            static Bundle[] getBundleArrayForMessages(List list0) {
                Bundle[] arr_bundle = new Bundle[list0.size()];
                int v = list0.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    arr_bundle[v1] = ((Message)list0.get(v1)).toBundle();
                }
                return arr_bundle;
            }

            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            public Uri getDataUri() {
                return this.mDataUri;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            static Message getMessageFromBundle(Bundle bundle0) {
                Person person0;
                try {
                    if(bundle0.containsKey("text") && bundle0.containsKey("time")) {
                        if(bundle0.containsKey("person")) {
                            person0 = Person.fromBundle(bundle0.getBundle("person"));
                        }
                        else if(bundle0.containsKey("sender_person") && Build.VERSION.SDK_INT >= 28) {
                            person0 = Person.fromAndroidPerson(ComponentDialog..ExternalSyntheticApiModelOutline0.m(bundle0.getParcelable("sender_person")));
                        }
                        else {
                            person0 = bundle0.containsKey("sender") ? new androidx.core.app.Person.Builder().setName(bundle0.getCharSequence("sender")).build() : null;
                        }
                        Message notificationCompat$MessagingStyle$Message0 = new Message(bundle0.getCharSequence("text"), bundle0.getLong("time"), person0);
                        if(bundle0.containsKey("type") && bundle0.containsKey("uri")) {
                            notificationCompat$MessagingStyle$Message0.setData(bundle0.getString("type"), ((Uri)bundle0.getParcelable("uri")));
                        }
                        if(bundle0.containsKey("extras")) {
                            notificationCompat$MessagingStyle$Message0.getExtras().putAll(bundle0.getBundle("extras"));
                        }
                        return notificationCompat$MessagingStyle$Message0;
                    }
                }
                catch(ClassCastException unused_ex) {
                }
                return null;
            }

            static List getMessagesFromBundleArray(Parcelable[] arr_parcelable) {
                List list0 = new ArrayList(arr_parcelable.length);
                for(int v = 0; v < arr_parcelable.length; ++v) {
                    Parcelable parcelable0 = arr_parcelable[v];
                    if(parcelable0 instanceof Bundle) {
                        Message notificationCompat$MessagingStyle$Message0 = Message.getMessageFromBundle(((Bundle)parcelable0));
                        if(notificationCompat$MessagingStyle$Message0 != null) {
                            list0.add(notificationCompat$MessagingStyle$Message0);
                        }
                    }
                }
                return list0;
            }

            public Person getPerson() {
                return this.mPerson;
            }

            @Deprecated
            public CharSequence getSender() {
                return this.mPerson == null ? null : this.mPerson.getName();
            }

            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            public Message setData(String s, Uri uri0) {
                this.mDataMimeType = s;
                this.mDataUri = uri0;
                return this;
            }

            Notification.MessagingStyle.Message toAndroidMessage() {
                Notification.MessagingStyle.Message notification$MessagingStyle$Message0;
                Person person0 = this.getPerson();
                android.app.Person person1 = null;
                if(Build.VERSION.SDK_INT >= 28) {
                    CharSequence charSequence0 = this.getText();
                    long v = this.getTimestamp();
                    if(person0 != null) {
                        person1 = person0.toAndroidPerson();
                    }
                    notification$MessagingStyle$Message0 = androidx.core.app.NotificationCompat.MessagingStyle.Message.Api28Impl.createMessage(charSequence0, v, person1);
                }
                else {
                    CharSequence charSequence1 = this.getText();
                    long v1 = this.getTimestamp();
                    if(person0 != null) {
                        person1 = person0.getName();
                    }
                    notification$MessagingStyle$Message0 = androidx.core.app.NotificationCompat.MessagingStyle.Message.Api24Impl.createMessage(charSequence1, v1, ((CharSequence)person1));
                }
                if(this.getDataMimeType() != null) {
                    androidx.core.app.NotificationCompat.MessagingStyle.Message.Api24Impl.setData(notification$MessagingStyle$Message0, this.getDataMimeType(), this.getDataUri());
                }
                return notification$MessagingStyle$Message0;
            }

            private Bundle toBundle() {
                Bundle bundle0 = new Bundle();
                CharSequence charSequence0 = this.mText;
                if(charSequence0 != null) {
                    bundle0.putCharSequence("text", charSequence0);
                }
                bundle0.putLong("time", this.mTimestamp);
                Person person0 = this.mPerson;
                if(person0 != null) {
                    bundle0.putCharSequence("sender", person0.getName());
                    if(Build.VERSION.SDK_INT >= 28) {
                        bundle0.putParcelable("sender_person", androidx.core.app.NotificationCompat.MessagingStyle.Message.Api28Impl.castToParcelable(this.mPerson.toAndroidPerson()));
                    }
                    else {
                        bundle0.putBundle("person", this.mPerson.toBundle());
                    }
                }
                String s = this.mDataMimeType;
                if(s != null) {
                    bundle0.putString("type", s);
                }
                Uri uri0 = this.mDataUri;
                if(uri0 != null) {
                    bundle0.putParcelable("uri", uri0);
                }
                Bundle bundle1 = this.mExtras;
                if(bundle1 != null) {
                    bundle0.putBundle("extras", bundle1);
                }
                return bundle0;
            }
        }

        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$MessagingStyle";
        private CharSequence mConversationTitle;
        private final List mHistoricMessages;
        private Boolean mIsGroupConversation;
        private final List mMessages;
        private Person mUser;

        MessagingStyle() {
            this.mMessages = new ArrayList();
            this.mHistoricMessages = new ArrayList();
        }

        public MessagingStyle(Person person0) {
            this.mMessages = new ArrayList();
            this.mHistoricMessages = new ArrayList();
            if(TextUtils.isEmpty(person0.getName())) {
                throw new IllegalArgumentException("User\'s name must not be empty.");
            }
            this.mUser = person0;
        }

        @Deprecated
        public MessagingStyle(CharSequence charSequence0) {
            this.mMessages = new ArrayList();
            this.mHistoricMessages = new ArrayList();
            this.mUser = new androidx.core.app.Person.Builder().setName(charSequence0).build();
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public void addCompatExtras(Bundle bundle0) {
            super.addCompatExtras(bundle0);
            bundle0.putCharSequence("android.selfDisplayName", this.mUser.getName());
            bundle0.putBundle("android.messagingStyleUser", this.mUser.toBundle());
            bundle0.putCharSequence("android.hiddenConversationTitle", this.mConversationTitle);
            if(this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                bundle0.putCharSequence("android.conversationTitle", this.mConversationTitle);
            }
            if(!this.mMessages.isEmpty()) {
                bundle0.putParcelableArray("android.messages", Message.getBundleArrayForMessages(this.mMessages));
            }
            if(!this.mHistoricMessages.isEmpty()) {
                bundle0.putParcelableArray("android.messages.historic", Message.getBundleArrayForMessages(this.mHistoricMessages));
            }
            Boolean boolean0 = this.mIsGroupConversation;
            if(boolean0 != null) {
                bundle0.putBoolean("android.isGroupConversation", boolean0.booleanValue());
            }
        }

        public MessagingStyle addHistoricMessage(Message notificationCompat$MessagingStyle$Message0) {
            if(notificationCompat$MessagingStyle$Message0 != null) {
                this.mHistoricMessages.add(notificationCompat$MessagingStyle$Message0);
                if(this.mHistoricMessages.size() > 25) {
                    this.mHistoricMessages.remove(0);
                }
            }
            return this;
        }

        public MessagingStyle addMessage(Message notificationCompat$MessagingStyle$Message0) {
            if(notificationCompat$MessagingStyle$Message0 != null) {
                this.mMessages.add(notificationCompat$MessagingStyle$Message0);
                if(this.mMessages.size() > 25) {
                    this.mMessages.remove(0);
                }
            }
            return this;
        }

        public MessagingStyle addMessage(CharSequence charSequence0, long v, Person person0) {
            this.addMessage(new Message(charSequence0, v, person0));
            return this;
        }

        @Deprecated
        public MessagingStyle addMessage(CharSequence charSequence0, long v, CharSequence charSequence1) {
            Message notificationCompat$MessagingStyle$Message0 = new Message(charSequence0, v, new androidx.core.app.Person.Builder().setName(charSequence1).build());
            this.mMessages.add(notificationCompat$MessagingStyle$Message0);
            if(this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            this.setGroupConversation(this.isGroupConversation());
            if(Build.VERSION.SDK_INT >= 24) {
                Notification.MessagingStyle notification$MessagingStyle0 = Build.VERSION.SDK_INT < 28 ? androidx.core.app.NotificationCompat.MessagingStyle.Api24Impl.createMessagingStyle(this.mUser.getName()) : androidx.core.app.NotificationCompat.MessagingStyle.Api28Impl.createMessagingStyle(this.mUser.toAndroidPerson());
                for(Object object0: this.mMessages) {
                    androidx.core.app.NotificationCompat.MessagingStyle.Api24Impl.addMessage(notification$MessagingStyle0, ((Message)object0).toAndroidMessage());
                }
                if(Build.VERSION.SDK_INT >= 26) {
                    for(Object object1: this.mHistoricMessages) {
                        androidx.core.app.NotificationCompat.MessagingStyle.Api26Impl.addHistoricMessage(notification$MessagingStyle0, ((Message)object1).toAndroidMessage());
                    }
                }
                if(this.mIsGroupConversation.booleanValue() || Build.VERSION.SDK_INT >= 28) {
                    androidx.core.app.NotificationCompat.MessagingStyle.Api24Impl.setConversationTitle(notification$MessagingStyle0, this.mConversationTitle);
                }
                if(Build.VERSION.SDK_INT >= 28) {
                    androidx.core.app.NotificationCompat.MessagingStyle.Api28Impl.setGroupConversation(notification$MessagingStyle0, this.mIsGroupConversation.booleanValue());
                }
                notification$MessagingStyle0.setBuilder(notificationBuilderWithBuilderAccessor0.getBuilder());
                return;
            }
            Message notificationCompat$MessagingStyle$Message0 = this.findLatestIncomingMessage();
            if(this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                notificationBuilderWithBuilderAccessor0.getBuilder().setContentTitle(this.mConversationTitle);
            }
            else if(notificationCompat$MessagingStyle$Message0 != null) {
                notificationBuilderWithBuilderAccessor0.getBuilder().setContentTitle("");
                if(notificationCompat$MessagingStyle$Message0.getPerson() != null) {
                    notificationBuilderWithBuilderAccessor0.getBuilder().setContentTitle(notificationCompat$MessagingStyle$Message0.getPerson().getName());
                }
            }
            if(notificationCompat$MessagingStyle$Message0 != null) {
                notificationBuilderWithBuilderAccessor0.getBuilder().setContentText((this.mConversationTitle == null ? notificationCompat$MessagingStyle$Message0.getText() : this.makeMessageLine(notificationCompat$MessagingStyle$Message0)));
            }
            SpannableStringBuilder spannableStringBuilder0 = new SpannableStringBuilder();
            boolean z = this.mConversationTitle != null || this.hasMessagesWithoutSender();
            for(int v = this.mMessages.size() - 1; v >= 0; --v) {
                Message notificationCompat$MessagingStyle$Message1 = (Message)this.mMessages.get(v);
                CharSequence charSequence0 = z ? this.makeMessageLine(notificationCompat$MessagingStyle$Message1) : notificationCompat$MessagingStyle$Message1.getText();
                if(v != this.mMessages.size() - 1) {
                    spannableStringBuilder0.insert(0, "\n");
                }
                spannableStringBuilder0.insert(0, charSequence0);
            }
            new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor0.getBuilder()).setBigContentTitle(null).bigText(spannableStringBuilder0);
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected void clearCompatExtraKeys(Bundle bundle0) {
            super.clearCompatExtraKeys(bundle0);
            bundle0.remove("android.messagingStyleUser");
            bundle0.remove("android.selfDisplayName");
            bundle0.remove("android.conversationTitle");
            bundle0.remove("android.hiddenConversationTitle");
            bundle0.remove("android.messages");
            bundle0.remove("android.messages.historic");
            bundle0.remove("android.isGroupConversation");
        }

        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification0) {
            Style notificationCompat$Style0 = Style.extractStyleFromNotification(notification0);
            return notificationCompat$Style0 instanceof MessagingStyle ? ((MessagingStyle)notificationCompat$Style0) : null;
        }

        private Message findLatestIncomingMessage() {
            for(int v = this.mMessages.size() - 1; v >= 0; --v) {
                Message notificationCompat$MessagingStyle$Message0 = (Message)this.mMessages.get(v);
                if(notificationCompat$MessagingStyle$Message0.getPerson() != null && !TextUtils.isEmpty(notificationCompat$MessagingStyle$Message0.getPerson().getName())) {
                    return notificationCompat$MessagingStyle$Message0;
                }
            }
            return this.mMessages.isEmpty() ? null : ((Message)this.mMessages.get(this.mMessages.size() - 1));
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected String getClassName() {
            return "androidx.core.app.NotificationCompat$MessagingStyle";
        }

        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        public List getHistoricMessages() {
            return this.mHistoricMessages;
        }

        public List getMessages() {
            return this.mMessages;
        }

        public Person getUser() {
            return this.mUser;
        }

        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        private boolean hasMessagesWithoutSender() {
            for(int v = this.mMessages.size() - 1; v >= 0; --v) {
                Message notificationCompat$MessagingStyle$Message0 = (Message)this.mMessages.get(v);
                if(notificationCompat$MessagingStyle$Message0.getPerson() != null && notificationCompat$MessagingStyle$Message0.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        public boolean isGroupConversation() {
            return this.mIsGroupConversation == null ? false : this.mIsGroupConversation.booleanValue();
        }

        private TextAppearanceSpan makeFontColorSpan(int v) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(v), null);
        }

        private CharSequence makeMessageLine(Message notificationCompat$MessagingStyle$Message0) {
            BidiFormatter bidiFormatter0 = BidiFormatter.getInstance();
            CharSequence charSequence0 = new SpannableStringBuilder();
            CharSequence charSequence1 = "";
            CharSequence charSequence2 = notificationCompat$MessagingStyle$Message0.getPerson() == null ? "" : notificationCompat$MessagingStyle$Message0.getPerson().getName();
            int v = 0xFF000000;
            if(TextUtils.isEmpty(charSequence2)) {
                charSequence2 = this.mUser.getName();
                if(this.mBuilder.getColor() != 0) {
                    v = this.mBuilder.getColor();
                }
            }
            CharSequence charSequence3 = bidiFormatter0.unicodeWrap(charSequence2);
            ((SpannableStringBuilder)charSequence0).append(charSequence3);
            ((SpannableStringBuilder)charSequence0).setSpan(this.makeFontColorSpan(v), ((SpannableStringBuilder)charSequence0).length() - charSequence3.length(), ((SpannableStringBuilder)charSequence0).length(), 33);
            if(notificationCompat$MessagingStyle$Message0.getText() != null) {
                charSequence1 = notificationCompat$MessagingStyle$Message0.getText();
            }
            ((SpannableStringBuilder)charSequence0).append("  ").append(bidiFormatter0.unicodeWrap(charSequence1));
            return charSequence0;
        }

        @Override  // androidx.core.app.NotificationCompat$Style
        protected void restoreFromCompatExtras(Bundle bundle0) {
            super.restoreFromCompatExtras(bundle0);
            this.mMessages.clear();
            this.mUser = bundle0.containsKey("android.messagingStyleUser") ? Person.fromBundle(bundle0.getBundle("android.messagingStyleUser")) : new androidx.core.app.Person.Builder().setName(bundle0.getString("android.selfDisplayName")).build();
            CharSequence charSequence0 = bundle0.getCharSequence("android.conversationTitle");
            this.mConversationTitle = charSequence0;
            if(charSequence0 == null) {
                this.mConversationTitle = bundle0.getCharSequence("android.hiddenConversationTitle");
            }
            Parcelable[] arr_parcelable = bundle0.getParcelableArray("android.messages");
            if(arr_parcelable != null) {
                List list0 = Message.getMessagesFromBundleArray(arr_parcelable);
                this.mMessages.addAll(list0);
            }
            Parcelable[] arr_parcelable1 = bundle0.getParcelableArray("android.messages.historic");
            if(arr_parcelable1 != null) {
                List list1 = Message.getMessagesFromBundleArray(arr_parcelable1);
                this.mHistoricMessages.addAll(list1);
            }
            if(bundle0.containsKey("android.isGroupConversation")) {
                this.mIsGroupConversation = Boolean.valueOf(bundle0.getBoolean("android.isGroupConversation"));
            }
        }

        public MessagingStyle setConversationTitle(CharSequence charSequence0) {
            this.mConversationTitle = charSequence0;
            return this;
        }

        public MessagingStyle setGroupConversation(boolean z) {
            this.mIsGroupConversation = Boolean.valueOf(z);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceNotificationBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    public static abstract class Style {
        static class androidx.core.app.NotificationCompat.Style.Api24Impl {
            static void setChronometerCountDown(RemoteViews remoteViews0, int v, boolean z) {
                remoteViews0.setChronometerCountDown(v, z);
            }
        }

        CharSequence mBigContentTitle;
        protected androidx.core.app.NotificationCompat.Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet;

        public Style() {
            this.mSummaryTextSet = false;
        }

        public void addCompatExtras(Bundle bundle0) {
            if(this.mSummaryTextSet) {
                bundle0.putCharSequence("android.summaryText", this.mSummaryText);
            }
            CharSequence charSequence0 = this.mBigContentTitle;
            if(charSequence0 != null) {
                bundle0.putCharSequence("android.title.big", charSequence0);
            }
            String s = this.getClassName();
            if(s != null) {
                bundle0.putString("androidx.core.app.extra.COMPAT_TEMPLATE", s);
            }
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
        }

        public RemoteViews applyStandardTemplate(boolean z, int v, boolean z1) {
            int v8;
            boolean z2;
            Resources resources0 = this.mBuilder.mContext.getResources();
            RemoteViews remoteViews0 = new RemoteViews("com.ruliweb.www.ruliapp", v);
            int v1 = 0;
            if(this.mBuilder.mLargeIcon != null) {
                remoteViews0.setViewVisibility(id.icon, 0);
                Bitmap bitmap0 = this.createColoredBitmap(this.mBuilder.mLargeIcon, 0);
                remoteViews0.setImageViewBitmap(id.icon, bitmap0);
                if(z && this.mBuilder.mNotification.icon != 0) {
                    int v2 = resources0.getDimensionPixelSize(dimen.notification_right_icon_size);
                    int v3 = resources0.getDimensionPixelSize(dimen.notification_small_icon_background_padding);
                    Bitmap bitmap1 = this.createIconWithBackground(this.mBuilder.mNotification.icon, v2, v2 - v3 * 2, this.mBuilder.getColor());
                    remoteViews0.setImageViewBitmap(id.right_icon, bitmap1);
                    remoteViews0.setViewVisibility(id.right_icon, 0);
                }
            }
            else if(z && this.mBuilder.mNotification.icon != 0) {
                remoteViews0.setViewVisibility(id.icon, 0);
                int v4 = resources0.getDimensionPixelSize(dimen.notification_large_icon_width);
                int v5 = resources0.getDimensionPixelSize(dimen.notification_big_circle_margin);
                int v6 = resources0.getDimensionPixelSize(dimen.notification_small_icon_size_as_large);
                Bitmap bitmap2 = this.createIconWithBackground(this.mBuilder.mNotification.icon, v4 - v5, v6, this.mBuilder.getColor());
                remoteViews0.setImageViewBitmap(id.icon, bitmap2);
            }
            if(this.mBuilder.mContentTitle != null) {
                remoteViews0.setTextViewText(id.title, this.mBuilder.mContentTitle);
            }
            int v7 = 1;
            if(this.mBuilder.mContentText == null) {
                z2 = false;
            }
            else {
                remoteViews0.setTextViewText(id.text, this.mBuilder.mContentText);
                z2 = true;
            }
            if(this.mBuilder.mContentInfo != null) {
                remoteViews0.setTextViewText(id.info, this.mBuilder.mContentInfo);
                remoteViews0.setViewVisibility(id.info, 0);
                z2 = true;
                v8 = 1;
            }
            else if(this.mBuilder.mNumber > 0) {
                int v9 = resources0.getInteger(integer.status_bar_notification_info_maxnum);
                if(this.mBuilder.mNumber > v9) {
                    String s = resources0.getString(string.status_bar_notification_info_overflow);
                    remoteViews0.setTextViewText(id.info, s);
                }
                else {
                    String s1 = NumberFormat.getIntegerInstance().format(((long)this.mBuilder.mNumber));
                    remoteViews0.setTextViewText(id.info, s1);
                }
                remoteViews0.setViewVisibility(id.info, 0);
                z2 = true;
                v8 = 1;
            }
            else {
                remoteViews0.setViewVisibility(id.info, 8);
                v8 = 0;
            }
            if(this.mBuilder.mSubText != null) {
                remoteViews0.setTextViewText(id.text, this.mBuilder.mSubText);
                if(this.mBuilder.mContentText == null) {
                    remoteViews0.setViewVisibility(id.text2, 8);
                }
                else {
                    remoteViews0.setTextViewText(id.text2, this.mBuilder.mContentText);
                    remoteViews0.setViewVisibility(id.text2, 0);
                    if(z1) {
                        float f = (float)resources0.getDimensionPixelSize(dimen.notification_subtext_size);
                        remoteViews0.setTextViewTextSize(id.text, 0, f);
                    }
                    remoteViews0.setViewPadding(id.line1, 0, 0, 0, 0);
                }
            }
            if(this.mBuilder.getWhenIfShowing() == 0L) {
                v7 = v8;
            }
            else if(this.mBuilder.mUseChronometer) {
                remoteViews0.setViewVisibility(id.chronometer, 0);
                long v10 = this.mBuilder.getWhenIfShowing();
                long v11 = SystemClock.elapsedRealtime();
                remoteViews0.setLong(id.chronometer, "setBase", v10 + (v11 - System.currentTimeMillis()));
                remoteViews0.setBoolean(id.chronometer, "setStarted", true);
                if(this.mBuilder.mChronometerCountDown && Build.VERSION.SDK_INT >= 24) {
                    androidx.core.app.NotificationCompat.Style.Api24Impl.setChronometerCountDown(remoteViews0, id.chronometer, this.mBuilder.mChronometerCountDown);
                }
            }
            else {
                remoteViews0.setViewVisibility(id.time, 0);
                long v12 = this.mBuilder.getWhenIfShowing();
                remoteViews0.setLong(id.time, "setTime", v12);
            }
            remoteViews0.setViewVisibility(0x7F090198, (v7 == 0 ? 8 : 0));  // id:right_side
            if(!z2) {
                v1 = 8;
            }
            remoteViews0.setViewVisibility(0x7F090108, v1);  // id:line3
            return remoteViews0;
        }

        public Notification build() {
            return this.mBuilder == null ? null : this.mBuilder.build();
        }

        public void buildIntoRemoteViews(RemoteViews remoteViews0, RemoteViews remoteViews1) {
            this.hideNormalContent(remoteViews0);
            remoteViews0.removeAllViews(id.notification_main_column);
            RemoteViews remoteViews2 = remoteViews1.clone();
            remoteViews0.addView(id.notification_main_column, remoteViews2);
            remoteViews0.setViewVisibility(id.notification_main_column, 0);
            int v = this.calculateTopPadding();
            remoteViews0.setViewPadding(id.notification_main_column_container, 0, v, 0, 0);
        }

        private int calculateTopPadding() {
            Resources resources0 = this.mBuilder.mContext.getResources();
            int v = resources0.getDimensionPixelSize(dimen.notification_top_pad);
            int v1 = resources0.getDimensionPixelSize(dimen.notification_top_pad_large_text);
            float f = (Style.constrain(resources0.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.3f;
            return Math.round((1.0f - f) * ((float)v) + f * ((float)v1));
        }

        protected void clearCompatExtraKeys(Bundle bundle0) {
            bundle0.remove("android.summaryText");
            bundle0.remove("android.title.big");
            bundle0.remove("androidx.core.app.extra.COMPAT_TEMPLATE");
        }

        private static float constrain(float f, float f1, float f2) {
            if(f < f1) {
                return f1;
            }
            return f > f2 ? f2 : f;
        }

        static Style constructCompatStyleByName(String s) {
            if(s != null) {
                s.hashCode();
                switch(s) {
                    case "androidx.core.app.NotificationCompat$BigPictureStyle": {
                        return new BigPictureStyle();
                    }
                    case "androidx.core.app.NotificationCompat$BigTextStyle": {
                        return new BigTextStyle();
                    }
                    case "androidx.core.app.NotificationCompat$CallStyle": {
                        return new CallStyle();
                    }
                    case "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle": {
                        return new DecoratedCustomViewStyle();
                    }
                    case "androidx.core.app.NotificationCompat$InboxStyle": {
                        return new InboxStyle();
                    }
                    case "androidx.core.app.NotificationCompat$MessagingStyle": {
                        return new MessagingStyle();
                    }
                    default: {
                        return null;
                    }
                }
            }
            return null;
        }

        private static Style constructCompatStyleByPlatformName(String s) {
            if(s == null) {
                return null;
            }
            if(s.equals("android.app.Notification$BigPictureStyle")) {
                return new BigPictureStyle();
            }
            if(s.equals("android.app.Notification$BigTextStyle")) {
                return new BigTextStyle();
            }
            if(s.equals("android.app.Notification$InboxStyle")) {
                return new InboxStyle();
            }
            if(Build.VERSION.SDK_INT >= 24) {
                if(s.equals("android.app.Notification$MessagingStyle")) {
                    return new MessagingStyle();
                }
                if(s.equals("android.app.Notification$DecoratedCustomViewStyle")) {
                    return new DecoratedCustomViewStyle();
                }
            }
            return null;
        }

        static Style constructCompatStyleForBundle(Bundle bundle0) {
            Style notificationCompat$Style0 = Style.constructCompatStyleByName(bundle0.getString("androidx.core.app.extra.COMPAT_TEMPLATE"));
            if(notificationCompat$Style0 != null) {
                return notificationCompat$Style0;
            }
            if(!bundle0.containsKey("android.selfDisplayName") && !bundle0.containsKey("android.messagingStyleUser")) {
                if(!bundle0.containsKey("android.picture") && !bundle0.containsKey("android.pictureIcon")) {
                    if(bundle0.containsKey("android.bigText")) {
                        return new BigTextStyle();
                    }
                    if(bundle0.containsKey("android.textLines")) {
                        return new InboxStyle();
                    }
                    return bundle0.containsKey("android.callType") ? new CallStyle() : Style.constructCompatStyleByPlatformName(bundle0.getString("android.template"));
                }
                return new BigPictureStyle();
            }
            return new MessagingStyle();
        }

        static Style constructStyleForExtras(Bundle bundle0) {
            Style notificationCompat$Style0 = Style.constructCompatStyleForBundle(bundle0);
            if(notificationCompat$Style0 == null) {
                return null;
            }
            try {
                notificationCompat$Style0.restoreFromCompatExtras(bundle0);
                return notificationCompat$Style0;
            }
            catch(ClassCastException unused_ex) {
                return null;
            }
        }

        private Bitmap createColoredBitmap(int v, int v1, int v2) {
            return this.createColoredBitmap(IconCompat.createWithResource(this.mBuilder.mContext, v), v1, v2);
        }

        private Bitmap createColoredBitmap(IconCompat iconCompat0, int v, int v1) {
            Drawable drawable0 = iconCompat0.loadDrawable(this.mBuilder.mContext);
            int v2 = v1 == 0 ? drawable0.getIntrinsicWidth() : v1;
            if(v1 == 0) {
                v1 = drawable0.getIntrinsicHeight();
            }
            Bitmap bitmap0 = Bitmap.createBitmap(v2, v1, Bitmap.Config.ARGB_8888);
            drawable0.setBounds(0, 0, v2, v1);
            if(v != 0) {
                drawable0.mutate().setColorFilter(new PorterDuffColorFilter(v, PorterDuff.Mode.SRC_IN));
            }
            drawable0.draw(new Canvas(bitmap0));
            return bitmap0;
        }

        public Bitmap createColoredBitmap(int v, int v1) {
            return this.createColoredBitmap(v, v1, 0);
        }

        Bitmap createColoredBitmap(IconCompat iconCompat0, int v) {
            return this.createColoredBitmap(iconCompat0, v, 0);
        }

        private Bitmap createIconWithBackground(int v, int v1, int v2, int v3) {
            if(v3 == 0) {
                v3 = 0;
            }
            Bitmap bitmap0 = this.createColoredBitmap(0x7F08010B, v3, v1);  // drawable:notification_icon_background
            Canvas canvas0 = new Canvas(bitmap0);
            Drawable drawable0 = this.mBuilder.mContext.getResources().getDrawable(v).mutate();
            drawable0.setFilterBitmap(true);
            int v4 = (v1 - v2) / 2;
            drawable0.setBounds(v4, v4, v2 + v4, v2 + v4);
            drawable0.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            drawable0.draw(canvas0);
            return bitmap0;
        }

        public boolean displayCustomViewInline() {
            return false;
        }

        public static Style extractStyleFromNotification(Notification notification0) {
            Bundle bundle0 = NotificationCompat.getExtras(notification0);
            return bundle0 == null ? null : Style.constructStyleForExtras(bundle0);
        }

        protected String getClassName() {
            return null;
        }

        private void hideNormalContent(RemoteViews remoteViews0) {
            remoteViews0.setViewVisibility(id.title, 8);
            remoteViews0.setViewVisibility(id.text2, 8);
            remoteViews0.setViewVisibility(id.text, 8);
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            return null;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            return null;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor0) {
            return null;
        }

        protected void restoreFromCompatExtras(Bundle bundle0) {
            if(bundle0.containsKey("android.summaryText")) {
                this.mSummaryText = bundle0.getCharSequence("android.summaryText");
                this.mSummaryTextSet = true;
            }
            this.mBigContentTitle = bundle0.getCharSequence("android.title.big");
        }

        public void setBuilder(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            if(this.mBuilder != notificationCompat$Builder0) {
                this.mBuilder = notificationCompat$Builder0;
                if(notificationCompat$Builder0 != null) {
                    notificationCompat$Builder0.setStyle(this);
                }
            }
        }
    }

    public static final class TvExtender implements androidx.core.app.NotificationCompat.Extender {
        static final String EXTRA_CHANNEL_ID = "channel_id";
        static final String EXTRA_CONTENT_INTENT = "content_intent";
        static final String EXTRA_DELETE_INTENT = "delete_intent";
        private static final String EXTRA_FLAGS = "flags";
        static final String EXTRA_SUPPRESS_SHOW_OVER_APPS = "suppressShowOverApps";
        static final String EXTRA_TV_EXTENDER = "android.tv.EXTENSIONS";
        private static final int FLAG_AVAILABLE_ON_TV = 1;
        private static final String TAG = "TvExtender";
        private String mChannelId;
        private PendingIntent mContentIntent;
        private PendingIntent mDeleteIntent;
        private int mFlags;
        private boolean mSuppressShowOverApps;

        public TvExtender() {
            this.mFlags = 1;
        }

        public TvExtender(Notification notification0) {
            if(Build.VERSION.SDK_INT >= 26) {
                Bundle bundle0 = notification0.extras == null ? null : notification0.extras.getBundle("android.tv.EXTENSIONS");
                if(bundle0 != null) {
                    this.mFlags = bundle0.getInt("flags");
                    this.mChannelId = bundle0.getString("channel_id");
                    this.mSuppressShowOverApps = bundle0.getBoolean("suppressShowOverApps");
                    this.mContentIntent = (PendingIntent)bundle0.getParcelable("content_intent");
                    this.mDeleteIntent = (PendingIntent)bundle0.getParcelable("delete_intent");
                }
            }
        }

        @Override  // androidx.core.app.NotificationCompat$Extender
        public androidx.core.app.NotificationCompat.Builder extend(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            if(Build.VERSION.SDK_INT < 26) {
                return notificationCompat$Builder0;
            }
            Bundle bundle0 = new Bundle();
            bundle0.putInt("flags", this.mFlags);
            bundle0.putString("channel_id", this.mChannelId);
            bundle0.putBoolean("suppressShowOverApps", this.mSuppressShowOverApps);
            PendingIntent pendingIntent0 = this.mContentIntent;
            if(pendingIntent0 != null) {
                bundle0.putParcelable("content_intent", pendingIntent0);
            }
            PendingIntent pendingIntent1 = this.mDeleteIntent;
            if(pendingIntent1 != null) {
                bundle0.putParcelable("delete_intent", pendingIntent1);
            }
            notificationCompat$Builder0.getExtras().putBundle("android.tv.EXTENSIONS", bundle0);
            return notificationCompat$Builder0;
        }

        public String getChannelId() {
            return this.mChannelId;
        }

        public PendingIntent getContentIntent() {
            return this.mContentIntent;
        }

        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        public boolean isAvailableOnTv() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isSuppressShowOverApps() {
            return this.mSuppressShowOverApps;
        }

        public TvExtender setChannelId(String s) {
            this.mChannelId = s;
            return this;
        }

        public TvExtender setContentIntent(PendingIntent pendingIntent0) {
            this.mContentIntent = pendingIntent0;
            return this;
        }

        public TvExtender setDeleteIntent(PendingIntent pendingIntent0) {
            this.mDeleteIntent = pendingIntent0;
            return this;
        }

        public TvExtender setSuppressShowOverApps(boolean z) {
            this.mSuppressShowOverApps = z;
            return this;
        }
    }

    public static final class androidx.core.app.NotificationCompat.WearableExtender implements androidx.core.app.NotificationCompat.Extender {
        static class androidx.core.app.NotificationCompat.WearableExtender.Api20Impl {
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

            public static Action getActionCompatFromAction(ArrayList arrayList0, int v) {
                return NotificationCompat.getActionCompatFromAction(((Notification.Action)arrayList0.get(v)));
            }
        }

        static class androidx.core.app.NotificationCompat.WearableExtender.Api23Impl {
            static Notification.Action.Builder createBuilder(Icon icon0, CharSequence charSequence0, PendingIntent pendingIntent0) {
                return new Notification.Action.Builder(icon0, charSequence0, pendingIntent0);
            }
        }

        static class androidx.core.app.NotificationCompat.WearableExtender.Api24Impl {
            static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder notification$Action$Builder0, boolean z) {
                return notification$Action$Builder0.setAllowGeneratedReplies(z);
            }
        }

        static class androidx.core.app.NotificationCompat.WearableExtender.Api31Impl {
            static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder notification$Action$Builder0, boolean z) {
                return notification$Action$Builder0.setAuthenticationRequired(z);
            }
        }

        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 0x800005;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 0x20;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 0x40;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        @Deprecated
        public static final int SCREEN_TIMEOUT_LONG = -1;
        @Deprecated
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        @Deprecated
        public static final int SIZE_DEFAULT = 0;
        @Deprecated
        public static final int SIZE_FULL_SCREEN = 5;
        @Deprecated
        public static final int SIZE_LARGE = 4;
        @Deprecated
        public static final int SIZE_MEDIUM = 3;
        @Deprecated
        public static final int SIZE_SMALL = 2;
        @Deprecated
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList mPages;

        public androidx.core.app.NotificationCompat.WearableExtender() {
            this.mActions = new ArrayList();
            this.mFlags = 1;
            this.mPages = new ArrayList();
            this.mContentIconGravity = 0x800005;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }

        public androidx.core.app.NotificationCompat.WearableExtender(Notification notification0) {
            this.mActions = new ArrayList();
            this.mFlags = 1;
            this.mPages = new ArrayList();
            this.mContentIconGravity = 0x800005;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
            Bundle bundle0 = NotificationCompat.getExtras(notification0);
            Bundle bundle1 = bundle0 == null ? null : bundle0.getBundle("android.wearable.EXTENSIONS");
            if(bundle1 != null) {
                ArrayList arrayList0 = bundle1.getParcelableArrayList("actions");
                if(arrayList0 != null) {
                    int v = arrayList0.size();
                    Action[] arr_notificationCompat$Action = new Action[v];
                    for(int v1 = 0; v1 < v; ++v1) {
                        arr_notificationCompat$Action[v1] = androidx.core.app.NotificationCompat.WearableExtender.Api20Impl.getActionCompatFromAction(arrayList0, v1);
                    }
                    Collections.addAll(this.mActions, arr_notificationCompat$Action);
                }
                this.mFlags = bundle1.getInt("flags", 1);
                this.mDisplayIntent = (PendingIntent)bundle1.getParcelable("displayIntent");
                Notification[] arr_notification = NotificationCompat.getNotificationArrayFromBundle(bundle1, "pages");
                if(arr_notification != null) {
                    Collections.addAll(this.mPages, arr_notification);
                }
                this.mBackground = (Bitmap)bundle1.getParcelable("background");
                this.mContentIcon = bundle1.getInt("contentIcon");
                this.mContentIconGravity = bundle1.getInt("contentIconGravity", 0x800005);
                this.mContentActionIndex = bundle1.getInt("contentActionIndex", -1);
                this.mCustomSizePreset = bundle1.getInt("customSizePreset", 0);
                this.mCustomContentHeight = bundle1.getInt("customContentHeight");
                this.mGravity = bundle1.getInt("gravity", 80);
                this.mHintScreenTimeout = bundle1.getInt("hintScreenTimeout");
                this.mDismissalId = bundle1.getString("dismissalId");
                this.mBridgeTag = bundle1.getString("bridgeTag");
            }
        }

        public androidx.core.app.NotificationCompat.WearableExtender addAction(Action notificationCompat$Action0) {
            this.mActions.add(notificationCompat$Action0);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender addActions(List list0) {
            this.mActions.addAll(list0);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender addPage(Notification notification0) {
            this.mPages.add(notification0);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender addPages(List list0) {
            this.mPages.addAll(list0);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender clone() {
            androidx.core.app.NotificationCompat.WearableExtender notificationCompat$WearableExtender0 = new androidx.core.app.NotificationCompat.WearableExtender();
            notificationCompat$WearableExtender0.mActions = new ArrayList(this.mActions);
            notificationCompat$WearableExtender0.mFlags = this.mFlags;
            notificationCompat$WearableExtender0.mDisplayIntent = this.mDisplayIntent;
            notificationCompat$WearableExtender0.mPages = new ArrayList(this.mPages);
            notificationCompat$WearableExtender0.mBackground = this.mBackground;
            notificationCompat$WearableExtender0.mContentIcon = this.mContentIcon;
            notificationCompat$WearableExtender0.mContentIconGravity = this.mContentIconGravity;
            notificationCompat$WearableExtender0.mContentActionIndex = this.mContentActionIndex;
            notificationCompat$WearableExtender0.mCustomSizePreset = this.mCustomSizePreset;
            notificationCompat$WearableExtender0.mCustomContentHeight = this.mCustomContentHeight;
            notificationCompat$WearableExtender0.mGravity = this.mGravity;
            notificationCompat$WearableExtender0.mHintScreenTimeout = this.mHintScreenTimeout;
            notificationCompat$WearableExtender0.mDismissalId = this.mDismissalId;
            notificationCompat$WearableExtender0.mBridgeTag = this.mBridgeTag;
            return notificationCompat$WearableExtender0;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        @Override  // androidx.core.app.NotificationCompat$Extender
        public androidx.core.app.NotificationCompat.Builder extend(androidx.core.app.NotificationCompat.Builder notificationCompat$Builder0) {
            Bundle bundle0 = new Bundle();
            if(!this.mActions.isEmpty()) {
                ArrayList arrayList0 = new ArrayList(this.mActions.size());
                for(Object object0: this.mActions) {
                    arrayList0.add(androidx.core.app.NotificationCompat.WearableExtender.getActionFromActionCompat(((Action)object0)));
                }
                bundle0.putParcelableArrayList("actions", arrayList0);
            }
            int v = this.mFlags;
            if(v != 1) {
                bundle0.putInt("flags", v);
            }
            PendingIntent pendingIntent0 = this.mDisplayIntent;
            if(pendingIntent0 != null) {
                bundle0.putParcelable("displayIntent", pendingIntent0);
            }
            if(!this.mPages.isEmpty()) {
                bundle0.putParcelableArray("pages", ((Parcelable[])this.mPages.toArray(new Notification[this.mPages.size()])));
            }
            Bitmap bitmap0 = this.mBackground;
            if(bitmap0 != null) {
                bundle0.putParcelable("background", bitmap0);
            }
            int v1 = this.mContentIcon;
            if(v1 != 0) {
                bundle0.putInt("contentIcon", v1);
            }
            int v2 = this.mContentIconGravity;
            if(v2 != 0x800005) {
                bundle0.putInt("contentIconGravity", v2);
            }
            int v3 = this.mContentActionIndex;
            if(v3 != -1) {
                bundle0.putInt("contentActionIndex", v3);
            }
            int v4 = this.mCustomSizePreset;
            if(v4 != 0) {
                bundle0.putInt("customSizePreset", v4);
            }
            int v5 = this.mCustomContentHeight;
            if(v5 != 0) {
                bundle0.putInt("customContentHeight", v5);
            }
            int v6 = this.mGravity;
            if(v6 != 80) {
                bundle0.putInt("gravity", v6);
            }
            int v7 = this.mHintScreenTimeout;
            if(v7 != 0) {
                bundle0.putInt("hintScreenTimeout", v7);
            }
            String s = this.mDismissalId;
            if(s != null) {
                bundle0.putString("dismissalId", s);
            }
            String s1 = this.mBridgeTag;
            if(s1 != null) {
                bundle0.putString("bridgeTag", s1);
            }
            notificationCompat$Builder0.getExtras().putBundle("android.wearable.EXTENSIONS", bundle0);
            return notificationCompat$Builder0;
        }

        private static Notification.Action getActionFromActionCompat(Action notificationCompat$Action0) {
            Notification.Action.Builder notification$Action$Builder0;
            if(Build.VERSION.SDK_INT >= 23) {
                IconCompat iconCompat0 = notificationCompat$Action0.getIconCompat();
                notification$Action$Builder0 = androidx.core.app.NotificationCompat.WearableExtender.Api23Impl.createBuilder((iconCompat0 == null ? null : iconCompat0.toIcon()), notificationCompat$Action0.getTitle(), notificationCompat$Action0.getActionIntent());
            }
            else {
                IconCompat iconCompat1 = notificationCompat$Action0.getIconCompat();
                notification$Action$Builder0 = androidx.core.app.NotificationCompat.WearableExtender.Api20Impl.createBuilder((iconCompat1 == null || iconCompat1.getType() != 2 ? 0 : iconCompat1.getResId()), notificationCompat$Action0.getTitle(), notificationCompat$Action0.getActionIntent());
            }
            Bundle bundle0 = notificationCompat$Action0.getExtras() == null ? new Bundle() : new Bundle(notificationCompat$Action0.getExtras());
            bundle0.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action0.getAllowGeneratedReplies());
            if(Build.VERSION.SDK_INT >= 24) {
                androidx.core.app.NotificationCompat.WearableExtender.Api24Impl.setAllowGeneratedReplies(notification$Action$Builder0, notificationCompat$Action0.getAllowGeneratedReplies());
            }
            if(Build.VERSION.SDK_INT >= 0x1F) {
                androidx.core.app.NotificationCompat.WearableExtender.Api31Impl.setAuthenticationRequired(notification$Action$Builder0, notificationCompat$Action0.isAuthenticationRequired());
            }
            androidx.core.app.NotificationCompat.WearableExtender.Api20Impl.addExtras(notification$Action$Builder0, bundle0);
            androidx.core.app.RemoteInput[] arr_remoteInput = notificationCompat$Action0.getRemoteInputs();
            if(arr_remoteInput != null) {
                RemoteInput[] arr_remoteInput1 = androidx.core.app.RemoteInput.fromCompat(arr_remoteInput);
                for(int v = 0; v < arr_remoteInput1.length; ++v) {
                    androidx.core.app.NotificationCompat.WearableExtender.Api20Impl.addRemoteInput(notification$Action$Builder0, arr_remoteInput1[v]);
                }
            }
            return androidx.core.app.NotificationCompat.WearableExtender.Api20Impl.build(notification$Action$Builder0);
        }

        public List getActions() {
            return this.mActions;
        }

        @Deprecated
        public Bitmap getBackground() {
            return this.mBackground;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        @Deprecated
        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        @Deprecated
        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 0x20) != 0;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 0x40) != 0;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        @Deprecated
        public List getPages() {
            return this.mPages;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setBackground(Bitmap bitmap0) {
            this.mBackground = bitmap0;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setBridgeTag(String s) {
            this.mBridgeTag = s;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setContentAction(int v) {
            this.mContentActionIndex = v;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setContentIcon(int v) {
            this.mContentIcon = v;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setContentIconGravity(int v) {
            this.mContentIconGravity = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setContentIntentAvailableOffline(boolean z) {
            this.setFlag(1, z);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setCustomContentHeight(int v) {
            this.mCustomContentHeight = v;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setCustomSizePreset(int v) {
            this.mCustomSizePreset = v;
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setDismissalId(String s) {
            this.mDismissalId = s;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setDisplayIntent(PendingIntent pendingIntent0) {
            this.mDisplayIntent = pendingIntent0;
            return this;
        }

        private void setFlag(int v, boolean z) {
            if(z) {
                this.mFlags |= v;
                return;
            }
            this.mFlags &= ~v;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setGravity(int v) {
            this.mGravity = v;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setHintAmbientBigPicture(boolean z) {
            this.setFlag(0x20, z);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setHintAvoidBackgroundClipping(boolean z) {
            this.setFlag(16, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
            this.setFlag(0x40, z);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setHintHideIcon(boolean z) {
            this.setFlag(2, z);
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setHintScreenTimeout(int v) {
            this.mHintScreenTimeout = v;
            return this;
        }

        @Deprecated
        public androidx.core.app.NotificationCompat.WearableExtender setHintShowBackgroundOnly(boolean z) {
            this.setFlag(4, z);
            return this;
        }

        public androidx.core.app.NotificationCompat.WearableExtender setStartScrollBottom(boolean z) {
            this.setFlag(8, z);
            return this;
        }
    }

    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_LOCATION_SHARING = "location_sharing";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_MISSED_CALL = "missed_call";
    public static final String CATEGORY_NAVIGATION = "navigation";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_STOPWATCH = "stopwatch";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final String CATEGORY_VOICEMAIL = "voicemail";
    public static final String CATEGORY_WORKOUT = "workout";
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_ANSWER_COLOR = "android.answerColor";
    public static final String EXTRA_ANSWER_INTENT = "android.answerIntent";
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_CALL_IS_VIDEO = "android.callIsVideo";
    public static final String EXTRA_CALL_PERSON = "android.callPerson";
    public static final String EXTRA_CALL_PERSON_COMPAT = "android.callPersonCompat";
    public static final String EXTRA_CALL_TYPE = "android.callType";
    public static final String EXTRA_CHANNEL_GROUP_ID = "android.intent.extra.CHANNEL_GROUP_ID";
    public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";
    public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";
    public static final String EXTRA_COLORIZED = "android.colorized";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_COMPAT_TEMPLATE = "androidx.core.app.extra.COMPAT_TEMPLATE";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_DECLINE_COLOR = "android.declineColor";
    public static final String EXTRA_DECLINE_INTENT = "android.declineIntent";
    public static final String EXTRA_HANG_UP_INTENT = "android.hangUpIntent";
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
    public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
    public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";
    public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";
    @Deprecated
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PEOPLE_LIST = "android.people.list";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PICTURE_CONTENT_DESCRIPTION = "android.pictureContentDescription";
    public static final String EXTRA_PICTURE_ICON = "android.pictureIcon";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_BIG_PICTURE_WHEN_COLLAPSED = "android.showBigPictureWhenCollapsed";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final String EXTRA_VERIFICATION_ICON = "android.verificationIcon";
    public static final String EXTRA_VERIFICATION_ICON_COMPAT = "android.verificationIconCompat";
    public static final String EXTRA_VERIFICATION_TEXT = "android.verificationText";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_BUBBLE = 0x1000;
    public static final int FLAG_FOREGROUND_SERVICE = 0x40;
    public static final int FLAG_GROUP_SUMMARY = 0x200;
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 0x80;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 0x100;
    public static final int FLAG_NO_CLEAR = 0x20;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int FOREGROUND_SERVICE_DEFAULT = 0;
    public static final int FOREGROUND_SERVICE_DEFERRED = 2;
    public static final int FOREGROUND_SERVICE_IMMEDIATE = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final String GROUP_KEY_SILENT = "silent";
    public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";
    public static final int MAX_ACTION_BUTTONS = 3;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    private static final String TAG = "NotifCompat";
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    public static Action getAction(Notification notification0, int v) {
        return NotificationCompat.getActionCompatFromAction(notification0.actions[v]);
    }

    static Action getActionCompatFromAction(Notification.Action notification$Action0) {
        boolean z;
        androidx.core.app.RemoteInput[] arr_remoteInput1;
        RemoteInput[] arr_remoteInput = androidx.core.app.NotificationCompat.Api20Impl.getRemoteInputs(notification$Action0);
        IconCompat iconCompat0 = null;
        if(arr_remoteInput == null) {
            arr_remoteInput1 = null;
        }
        else {
            androidx.core.app.RemoteInput[] arr_remoteInput2 = new androidx.core.app.RemoteInput[arr_remoteInput.length];
            for(int v = 0; v < arr_remoteInput.length; ++v) {
                RemoteInput remoteInput0 = arr_remoteInput[v];
                arr_remoteInput2[v] = new androidx.core.app.RemoteInput(androidx.core.app.NotificationCompat.Api20Impl.getResultKey(remoteInput0), androidx.core.app.NotificationCompat.Api20Impl.getLabel(remoteInput0), androidx.core.app.NotificationCompat.Api20Impl.getChoices(remoteInput0), androidx.core.app.NotificationCompat.Api20Impl.getAllowFreeFormInput(remoteInput0), (Build.VERSION.SDK_INT < 29 ? 0 : androidx.core.app.NotificationCompat.Api29Impl.getEditChoicesBeforeSending(remoteInput0)), androidx.core.app.NotificationCompat.Api20Impl.getExtras(remoteInput0), null);
            }
            arr_remoteInput1 = arr_remoteInput2;
        }
        if(Build.VERSION.SDK_INT < 24) {
            z = androidx.core.app.NotificationCompat.Api20Impl.getExtras(notification$Action0).getBoolean("android.support.allowGeneratedReplies");
        }
        else if(!androidx.core.app.NotificationCompat.Api20Impl.getExtras(notification$Action0).getBoolean("android.support.allowGeneratedReplies") && !androidx.core.app.NotificationCompat.Api24Impl.getAllowGeneratedReplies(notification$Action0)) {
            z = false;
        }
        else {
            z = true;
        }
        boolean z1 = androidx.core.app.NotificationCompat.Api20Impl.getExtras(notification$Action0).getBoolean("android.support.action.showsUserInterface", true);
        int v1 = Build.VERSION.SDK_INT < 28 ? androidx.core.app.NotificationCompat.Api20Impl.getExtras(notification$Action0).getInt("android.support.action.semanticAction", 0) : androidx.core.app.NotificationCompat.Api28Impl.getSemanticAction(notification$Action0);
        boolean z2 = Build.VERSION.SDK_INT < 29 ? false : androidx.core.app.NotificationCompat.Api29Impl.isContextual(notification$Action0);
        boolean z3 = Build.VERSION.SDK_INT < 0x1F ? false : androidx.core.app.NotificationCompat.Api31Impl.isAuthenticationRequired(notification$Action0);
        if(Build.VERSION.SDK_INT < 23 || androidx.core.app.NotificationCompat.Api23Impl.getIcon(notification$Action0) == null && notification$Action0.icon != 0) {
            return new Action(notification$Action0.icon, notification$Action0.title, notification$Action0.actionIntent, androidx.core.app.NotificationCompat.Api20Impl.getExtras(notification$Action0), arr_remoteInput1, null, z, v1, z1, z2, z3);
        }
        if(androidx.core.app.NotificationCompat.Api23Impl.getIcon(notification$Action0) != null) {
            iconCompat0 = IconCompat.createFromIconOrNullIfZeroResId(androidx.core.app.NotificationCompat.Api23Impl.getIcon(notification$Action0));
        }
        return new Action(iconCompat0, notification$Action0.title, notification$Action0.actionIntent, androidx.core.app.NotificationCompat.Api20Impl.getExtras(notification$Action0), arr_remoteInput1, null, z, v1, z1, z2, z3);
    }

    public static int getActionCount(Notification notification0) {
        return notification0.actions == null ? 0 : notification0.actions.length;
    }

    public static boolean getAllowSystemGeneratedContextualActions(Notification notification0) {
        return Build.VERSION.SDK_INT < 29 ? false : androidx.core.app.NotificationCompat.Api29Impl.getAllowSystemGeneratedContextualActions(notification0);
    }

    public static boolean getAutoCancel(Notification notification0) {
        return (notification0.flags & 16) != 0;
    }

    public static int getBadgeIconType(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? 0 : Api26Impl.getBadgeIconType(notification0);
    }

    public static BubbleMetadata getBubbleMetadata(Notification notification0) {
        return Build.VERSION.SDK_INT < 29 ? null : BubbleMetadata.fromPlatform(androidx.core.app.NotificationCompat.Api29Impl.getBubbleMetadata(notification0));
    }

    public static String getCategory(Notification notification0) {
        return notification0.category;
    }

    public static String getChannelId(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? null : Api26Impl.getChannelId(notification0);
    }

    public static int getColor(Notification notification0) {
        return notification0.color;
    }

    public static CharSequence getContentInfo(Notification notification0) {
        return notification0.extras.getCharSequence("android.infoText");
    }

    public static CharSequence getContentText(Notification notification0) {
        return notification0.extras.getCharSequence("android.text");
    }

    public static CharSequence getContentTitle(Notification notification0) {
        return notification0.extras.getCharSequence("android.title");
    }

    @ReplaceWith(expression = "notification.extras")
    @Deprecated
    public static Bundle getExtras(Notification notification0) {
        return notification0.extras;
    }

    public static String getGroup(Notification notification0) {
        return androidx.core.app.NotificationCompat.Api20Impl.getGroup(notification0);
    }

    public static int getGroupAlertBehavior(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? 0 : Api26Impl.getGroupAlertBehavior(notification0);
    }

    static boolean getHighPriority(Notification notification0) {
        return (notification0.flags & 0x80) != 0;
    }

    public static List getInvisibleActions(Notification notification0) {
        List list0 = new ArrayList();
        Bundle bundle0 = notification0.extras.getBundle("android.car.EXTENSIONS");
        if(bundle0 != null) {
            Bundle bundle1 = bundle0.getBundle("invisible_actions");
            if(bundle1 != null) {
                for(int v = 0; v < bundle1.size(); ++v) {
                    ((ArrayList)list0).add(NotificationCompatJellybean.getActionFromBundle(bundle1.getBundle(Integer.toString(v))));
                }
            }
        }
        return list0;
    }

    public static boolean getLocalOnly(Notification notification0) {
        return (notification0.flags & 0x100) != 0;
    }

    public static LocusIdCompat getLocusId(Notification notification0) {
        if(Build.VERSION.SDK_INT >= 29) {
            LocusId locusId0 = androidx.core.app.NotificationCompat.Api29Impl.getLocusId(notification0);
            return locusId0 == null ? null : LocusIdCompat.toLocusIdCompat(locusId0);
        }
        return null;
    }

    static Notification[] getNotificationArrayFromBundle(Bundle bundle0, String s) {
        Parcelable[] arr_parcelable = bundle0.getParcelableArray(s);
        if(!(arr_parcelable instanceof Notification[]) && arr_parcelable != null) {
            Notification[] arr_notification = new Notification[arr_parcelable.length];
            for(int v = 0; v < arr_parcelable.length; ++v) {
                arr_notification[v] = (Notification)arr_parcelable[v];
            }
            bundle0.putParcelableArray(s, arr_notification);
            return arr_notification;
        }
        return (Notification[])arr_parcelable;
    }

    public static boolean getOngoing(Notification notification0) {
        return (notification0.flags & 2) != 0;
    }

    public static boolean getOnlyAlertOnce(Notification notification0) {
        return (notification0.flags & 8) != 0;
    }

    public static List getPeople(Notification notification0) {
        List list0 = new ArrayList();
        if(Build.VERSION.SDK_INT >= 28) {
            ArrayList arrayList0 = notification0.extras.getParcelableArrayList("android.people.list");
            if(arrayList0 != null && !arrayList0.isEmpty()) {
                for(Object object0: arrayList0) {
                    ((ArrayList)list0).add(Person.fromAndroidPerson(((android.app.Person)object0)));
                }
                return list0;
            }
        }
        else {
            String[] arr_s = notification0.extras.getStringArray("android.people");
            if(arr_s != null && arr_s.length != 0) {
                for(int v = 0; v < arr_s.length; ++v) {
                    String s = arr_s[v];
                    ((ArrayList)list0).add(new androidx.core.app.Person.Builder().setUri(s).build());
                }
            }
        }
        return list0;
    }

    public static Notification getPublicVersion(Notification notification0) {
        return notification0.publicVersion;
    }

    public static CharSequence getSettingsText(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? null : Api26Impl.getSettingsText(notification0);
    }

    public static String getShortcutId(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? null : Api26Impl.getShortcutId(notification0);
    }

    public static boolean getShowWhen(Notification notification0) {
        return notification0.extras.getBoolean("android.showWhen");
    }

    public static String getSortKey(Notification notification0) {
        return androidx.core.app.NotificationCompat.Api20Impl.getSortKey(notification0);
    }

    public static CharSequence getSubText(Notification notification0) {
        return notification0.extras.getCharSequence("android.subText");
    }

    public static long getTimeoutAfter(Notification notification0) {
        return Build.VERSION.SDK_INT < 26 ? 0L : Api26Impl.getTimeoutAfter(notification0);
    }

    public static boolean getUsesChronometer(Notification notification0) {
        return notification0.extras.getBoolean("android.showChronometer");
    }

    public static int getVisibility(Notification notification0) {
        return notification0.visibility;
    }

    public static boolean isGroupSummary(Notification notification0) {
        return (notification0.flags & 0x200) != 0;
    }

    public static Bitmap reduceLargeIconSize(Context context0, Bitmap bitmap0) {
        if(bitmap0 != null && Build.VERSION.SDK_INT < 27) {
            Resources resources0 = context0.getResources();
            int v = resources0.getDimensionPixelSize(dimen.compat_notification_large_icon_max_width);
            int v1 = resources0.getDimensionPixelSize(dimen.compat_notification_large_icon_max_height);
            if(bitmap0.getWidth() > v || bitmap0.getHeight() > v1) {
                double f = Math.min(((double)v) / ((double)Math.max(1, bitmap0.getWidth())), ((double)v1) / ((double)Math.max(1, bitmap0.getHeight())));
                return Bitmap.createScaledBitmap(bitmap0, ((int)Math.ceil(((double)bitmap0.getWidth()) * f)), ((int)Math.ceil(((double)bitmap0.getHeight()) * f)), true);
            }
        }
        return bitmap0;
    }

    class androidx.core.app.NotificationCompat.1 {
    }

}

