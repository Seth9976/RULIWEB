package androidx.work;

import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build.VERSION;
import androidx.work.impl.utils.DurationApi26Impl;
import androidx.work.impl.utils.NetworkRequest30;
import androidx.work.impl.utils.NetworkRequestCompat;
import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\u0018\u0000 +2\u00020\u0001:\u0003*+,B/\b\u0017\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\bB9\b\u0017\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\t\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\nB]\b\u0017\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\t\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u000B\u001A\u00020\f\u0012\b\b\u0002\u0010\r\u001A\u00020\f\u0012\u000E\b\u0002\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000F\u00A2\u0006\u0002\u0010\u0011Be\b\u0010\u0012\u0006\u0010\u0012\u001A\u00020\u0013\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\t\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u000B\u001A\u00020\f\u0012\b\b\u0002\u0010\r\u001A\u00020\f\u0012\u000E\b\u0002\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000F\u00A2\u0006\u0002\u0010\u0014B\u000F\b\u0017\u0012\u0006\u0010\u0015\u001A\u00020\u0000\u00A2\u0006\u0002\u0010\u0016J\u0013\u0010$\u001A\u00020\u00052\b\u0010\u0015\u001A\u0004\u0018\u00010\u0001H\u0097\u0002J\b\u0010%\u001A\u00020\u0005H\u0007J\b\u0010&\u001A\u00020\'H\u0017J\u0006\u0010\u0006\u001A\u00020\u0005J\u0006\u0010\u0004\u001A\u00020\u0005J\b\u0010\t\u001A\u00020\u0005H\u0007J\u0006\u0010\u0007\u001A\u00020\u0005J\b\u0010(\u001A\u00020)H\u0017R\u0016\u0010\r\u001A\u00020\f8GX\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u0016\u0010\u000B\u001A\u00020\f8GX\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u0018R\u001C\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000F8GX\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u001BR\u0013\u0010\u001C\u001A\u0004\u0018\u00010\u001D8G\u00A2\u0006\u0006\u001A\u0004\b\u001E\u0010\u001FR\u0016\u0010\u0012\u001A\u00020\u00138\u0000X\u0081\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b \u0010!R\u0016\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\"\u0010#R\u0010\u0010\u0006\u001A\u00020\u00058\u0002X\u0083\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001A\u00020\u00058\u0002X\u0083\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u00020\u00058\u0002X\u0083\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u00020\u00058\u0002X\u0083\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006-"}, d2 = {"Landroidx/work/Constraints;", "", "requiredNetworkType", "Landroidx/work/NetworkType;", "requiresCharging", "", "requiresBatteryNotLow", "requiresStorageNotLow", "(Landroidx/work/NetworkType;ZZZ)V", "requiresDeviceIdle", "(Landroidx/work/NetworkType;ZZZZ)V", "contentTriggerUpdateDelayMillis", "", "contentTriggerMaxDelayMillis", "contentUriTriggers", "", "Landroidx/work/Constraints$ContentUriTrigger;", "(Landroidx/work/NetworkType;ZZZZJJLjava/util/Set;)V", "requiredNetworkRequestCompat", "Landroidx/work/impl/utils/NetworkRequestCompat;", "(Landroidx/work/impl/utils/NetworkRequestCompat;Landroidx/work/NetworkType;ZZZZJJLjava/util/Set;)V", "other", "(Landroidx/work/Constraints;)V", "getContentTriggerMaxDelayMillis", "()J", "getContentTriggerUpdateDelayMillis", "getContentUriTriggers", "()Ljava/util/Set;", "requiredNetworkRequest", "Landroid/net/NetworkRequest;", "getRequiredNetworkRequest", "()Landroid/net/NetworkRequest;", "getRequiredNetworkRequestCompat$work_runtime_release", "()Landroidx/work/impl/utils/NetworkRequestCompat;", "getRequiredNetworkType", "()Landroidx/work/NetworkType;", "equals", "hasContentUriTriggers", "hashCode", "", "toString", "", "Builder", "Companion", "ContentUriTrigger", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Constraints {
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00A2\u0006\u0002\u0010\u0002B\u000F\b\u0017\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0018\u0010\u0015\u001A\u00020\u00002\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u000EH\u0007J\u0006\u0010\u0019\u001A\u00020\u0004J\u0018\u0010\u001A\u001A\u00020\u00002\u0006\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u001D\u001A\u00020\fH\u0007J\u000E\u0010\u001E\u001A\u00020\u00002\u0006\u0010\u001D\u001A\u00020\fJ\u000E\u0010\u001F\u001A\u00020\u00002\u0006\u0010\r\u001A\u00020\u000EJ\u000E\u0010 \u001A\u00020\u00002\u0006\u0010\u000F\u001A\u00020\u000EJ\u0010\u0010!\u001A\u00020\u00002\u0006\u0010\u0010\u001A\u00020\u000EH\u0007J\u000E\u0010\"\u001A\u00020\u00002\u0006\u0010\u0011\u001A\u00020\u000EJ\u0010\u0010#\u001A\u00020\u00002\u0006\u0010$\u001A\u00020%H\u0007J\u0018\u0010#\u001A\u00020\u00002\u0006\u0010$\u001A\u00020\u00132\u0006\u0010&\u001A\u00020\'H\u0007J\u0010\u0010(\u001A\u00020\u00002\u0006\u0010$\u001A\u00020%H\u0007J\u0018\u0010(\u001A\u00020\u00002\u0006\u0010$\u001A\u00020\u00132\u0006\u0010&\u001A\u00020\'H\u0007R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\fX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u0013X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0014\u001A\u00020\u0013X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006)"}, d2 = {"Landroidx/work/Constraints$Builder;", "", "()V", "constraints", "Landroidx/work/Constraints;", "(Landroidx/work/Constraints;)V", "contentUriTriggers", "", "Landroidx/work/Constraints$ContentUriTrigger;", "requiredNetworkRequest", "Landroidx/work/impl/utils/NetworkRequestCompat;", "requiredNetworkType", "Landroidx/work/NetworkType;", "requiresBatteryNotLow", "", "requiresCharging", "requiresDeviceIdle", "requiresStorageNotLow", "triggerContentMaxDelay", "", "triggerContentUpdateDelay", "addContentUriTrigger", "uri", "Landroid/net/Uri;", "triggerForDescendants", "build", "setRequiredNetworkRequest", "networkRequest", "Landroid/net/NetworkRequest;", "networkType", "setRequiredNetworkType", "setRequiresBatteryNotLow", "setRequiresCharging", "setRequiresDeviceIdle", "setRequiresStorageNotLow", "setTriggerContentMaxDelay", "duration", "Ljava/time/Duration;", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "setTriggerContentUpdateDelay", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Builder {
        private Set contentUriTriggers;
        private NetworkRequestCompat requiredNetworkRequest;
        private NetworkType requiredNetworkType;
        private boolean requiresBatteryNotLow;
        private boolean requiresCharging;
        private boolean requiresDeviceIdle;
        private boolean requiresStorageNotLow;
        private long triggerContentMaxDelay;
        private long triggerContentUpdateDelay;

        public Builder() {
            this.requiredNetworkRequest = new NetworkRequestCompat(null, 1, null);
            this.requiredNetworkType = NetworkType.NOT_REQUIRED;
            this.triggerContentUpdateDelay = -1L;
            this.triggerContentMaxDelay = -1L;
            this.contentUriTriggers = new LinkedHashSet();
        }

        public Builder(Constraints constraints0) {
            Intrinsics.checkNotNullParameter(constraints0, "constraints");
            super();
            boolean z = true;
            this.requiredNetworkRequest = new NetworkRequestCompat(null, 1, null);
            this.requiredNetworkType = NetworkType.NOT_REQUIRED;
            this.triggerContentUpdateDelay = -1L;
            this.triggerContentMaxDelay = -1L;
            this.contentUriTriggers = new LinkedHashSet();
            this.requiresCharging = constraints0.requiresCharging();
            if(Build.VERSION.SDK_INT < 23 || !constraints0.requiresDeviceIdle()) {
                z = false;
            }
            this.requiresDeviceIdle = z;
            this.requiredNetworkType = constraints0.getRequiredNetworkType();
            this.requiresBatteryNotLow = constraints0.requiresBatteryNotLow();
            this.requiresStorageNotLow = constraints0.requiresStorageNotLow();
            if(Build.VERSION.SDK_INT >= 24) {
                this.triggerContentUpdateDelay = constraints0.getContentTriggerUpdateDelayMillis();
                this.triggerContentMaxDelay = constraints0.getContentTriggerMaxDelayMillis();
                this.contentUriTriggers = CollectionsKt.toMutableSet(constraints0.getContentUriTriggers());
            }
        }

        public final Builder addContentUriTrigger(Uri uri0, boolean z) {
            Intrinsics.checkNotNullParameter(uri0, "uri");
            this.contentUriTriggers.add(new ContentUriTrigger(uri0, z));
            return this;
        }

        public final Constraints build() {
            long v1;
            long v;
            Set set0;
            if(Build.VERSION.SDK_INT >= 24) {
                set0 = CollectionsKt.toSet(this.contentUriTriggers);
                v = this.triggerContentUpdateDelay;
                v1 = this.triggerContentMaxDelay;
            }
            else {
                set0 = SetsKt.emptySet();
                v = -1L;
                v1 = -1L;
            }
            NetworkRequestCompat networkRequestCompat0 = this.requiredNetworkRequest;
            NetworkType networkType0 = this.requiredNetworkType;
            boolean z = this.requiresCharging;
            return Build.VERSION.SDK_INT < 23 || !this.requiresDeviceIdle ? new Constraints(networkRequestCompat0, networkType0, z, false, this.requiresBatteryNotLow, this.requiresStorageNotLow, v, v1, set0) : new Constraints(networkRequestCompat0, networkType0, z, true, this.requiresBatteryNotLow, this.requiresStorageNotLow, v, v1, set0);
        }

        public final Builder setRequiredNetworkRequest(NetworkRequest networkRequest0, NetworkType networkType0) {
            Intrinsics.checkNotNullParameter(networkRequest0, "networkRequest");
            Intrinsics.checkNotNullParameter(networkType0, "networkType");
            if(Build.VERSION.SDK_INT >= 28) {
                if(Build.VERSION.SDK_INT >= 0x1F && NetworkRequest30.INSTANCE.getNetworkSpecifier(networkRequest0) != null) {
                    throw new IllegalArgumentException("NetworkRequests with NetworkSpecifiers set aren\'t supported.");
                }
                this.requiredNetworkRequest = new NetworkRequestCompat(networkRequest0);
                this.requiredNetworkType = NetworkType.NOT_REQUIRED;
                return this;
            }
            this.requiredNetworkType = networkType0;
            return this;
        }

        public final Builder setRequiredNetworkType(NetworkType networkType0) {
            Intrinsics.checkNotNullParameter(networkType0, "networkType");
            this.requiredNetworkType = networkType0;
            this.requiredNetworkRequest = new NetworkRequestCompat(null, 1, null);
            return this;
        }

        public final Builder setRequiresBatteryNotLow(boolean z) {
            this.requiresBatteryNotLow = z;
            return this;
        }

        public final Builder setRequiresCharging(boolean z) {
            this.requiresCharging = z;
            return this;
        }

        public final Builder setRequiresDeviceIdle(boolean z) {
            this.requiresDeviceIdle = z;
            return this;
        }

        public final Builder setRequiresStorageNotLow(boolean z) {
            this.requiresStorageNotLow = z;
            return this;
        }

        public final Builder setTriggerContentMaxDelay(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            this.triggerContentMaxDelay = timeUnit0.toMillis(v);
            return this;
        }

        public final Builder setTriggerContentMaxDelay(Duration duration0) {
            Intrinsics.checkNotNullParameter(duration0, "duration");
            this.triggerContentMaxDelay = DurationApi26Impl.toMillisCompat(duration0);
            return this;
        }

        public final Builder setTriggerContentUpdateDelay(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            this.triggerContentUpdateDelay = timeUnit0.toMillis(v);
            return this;
        }

        public final Builder setTriggerContentUpdateDelay(Duration duration0) {
            Intrinsics.checkNotNullParameter(duration0, "duration");
            this.triggerContentUpdateDelay = DurationApi26Impl.toMillisCompat(duration0);
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/work/Constraints$Companion;", "", "()V", "NONE", "Landroidx/work/Constraints;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\n\u001A\u00020\u00052\b\u0010\u000B\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001A\u00020\rH\u0016R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\u000E"}, d2 = {"Landroidx/work/Constraints$ContentUriTrigger;", "", "uri", "Landroid/net/Uri;", "isTriggeredForDescendants", "", "(Landroid/net/Uri;Z)V", "()Z", "getUri", "()Landroid/net/Uri;", "equals", "other", "hashCode", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class ContentUriTrigger {
        private final boolean isTriggeredForDescendants;
        private final Uri uri;

        public ContentUriTrigger(Uri uri0, boolean z) {
            Intrinsics.checkNotNullParameter(uri0, "uri");
            super();
            this.uri = uri0;
            this.isTriggeredForDescendants = z;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!Intrinsics.areEqual(this.getClass(), (object0 == null ? null : object0.getClass()))) {
                return false;
            }
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type androidx.work.Constraints.ContentUriTrigger");
            return Intrinsics.areEqual(this.uri, ((ContentUriTrigger)object0).uri) ? this.isTriggeredForDescendants == ((ContentUriTrigger)object0).isTriggeredForDescendants : false;
        }

        public final Uri getUri() {
            return this.uri;
        }

        @Override
        public int hashCode() {
            return this.uri.hashCode() * 0x1F + UByte..ExternalSyntheticBackport0.m(this.isTriggeredForDescendants);
        }

        public final boolean isTriggeredForDescendants() {
            return this.isTriggeredForDescendants;
        }
    }

    public static final Companion Companion;
    public static final Constraints NONE;
    private final long contentTriggerMaxDelayMillis;
    private final long contentTriggerUpdateDelayMillis;
    private final Set contentUriTriggers;
    private final NetworkRequestCompat requiredNetworkRequestCompat;
    private final NetworkType requiredNetworkType;
    private final boolean requiresBatteryNotLow;
    private final boolean requiresCharging;
    private final boolean requiresDeviceIdle;
    private final boolean requiresStorageNotLow;

    static {
        Constraints.Companion = new Companion(null);
        Constraints.NONE = new Constraints(null, false, false, false, 15, null);
    }

    public Constraints(Constraints constraints0) {
        Intrinsics.checkNotNullParameter(constraints0, "other");
        super();
        this.requiresCharging = constraints0.requiresCharging;
        this.requiresDeviceIdle = constraints0.requiresDeviceIdle;
        this.requiredNetworkRequestCompat = constraints0.requiredNetworkRequestCompat;
        this.requiredNetworkType = constraints0.requiredNetworkType;
        this.requiresBatteryNotLow = constraints0.requiresBatteryNotLow;
        this.requiresStorageNotLow = constraints0.requiresStorageNotLow;
        this.contentUriTriggers = constraints0.contentUriTriggers;
        this.contentTriggerUpdateDelayMillis = constraints0.contentTriggerUpdateDelayMillis;
        this.contentTriggerMaxDelayMillis = constraints0.contentTriggerMaxDelayMillis;
    }

    public Constraints(NetworkType networkType0, boolean z, boolean z1, boolean z2) {
        Intrinsics.checkNotNullParameter(networkType0, "requiredNetworkType");
        this(networkType0, z, false, z1, z2);
    }

    public Constraints(NetworkType networkType0, boolean z, boolean z1, boolean z2, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            networkType0 = NetworkType.NOT_REQUIRED;
        }
        if((v & 2) != 0) {
            z = false;
        }
        if((v & 4) != 0) {
            z1 = false;
        }
        if((v & 8) != 0) {
            z2 = false;
        }
        this(networkType0, z, z1, z2);
    }

    public Constraints(NetworkType networkType0, boolean z, boolean z1, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(networkType0, "requiredNetworkType");
        this(networkType0, z, z1, z2, z3, -1L, 0L, null, 0xC0, null);
    }

    public Constraints(NetworkType networkType0, boolean z, boolean z1, boolean z2, boolean z3, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        boolean z7;
        NetworkType networkType1;
        boolean z6;
        boolean z5;
        boolean z4;
        if((v & 1) != 0) {
            networkType0 = NetworkType.NOT_REQUIRED;
        }
        if((v & 2) != 0) {
            z = false;
        }
        if((v & 4) != 0) {
            z1 = false;
        }
        if((v & 8) != 0) {
            z2 = false;
        }
        if((v & 16) == 0) {
            z4 = z3;
            z6 = z2;
            z7 = z;
            z5 = z1;
            networkType1 = networkType0;
        }
        else {
            z4 = false;
            z5 = z1;
            z6 = z2;
            networkType1 = networkType0;
            z7 = z;
        }
        this(networkType1, z7, z5, z6, z4);
    }

    public Constraints(NetworkType networkType0, boolean z, boolean z1, boolean z2, boolean z3, long v, long v1, Set set0) {
        Intrinsics.checkNotNullParameter(networkType0, "requiredNetworkType");
        Intrinsics.checkNotNullParameter(set0, "contentUriTriggers");
        super();
        this.requiredNetworkRequestCompat = new NetworkRequestCompat(null, 1, null);
        this.requiredNetworkType = networkType0;
        this.requiresCharging = z;
        this.requiresDeviceIdle = z1;
        this.requiresBatteryNotLow = z2;
        this.requiresStorageNotLow = z3;
        this.contentTriggerUpdateDelayMillis = v;
        this.contentTriggerMaxDelayMillis = v1;
        this.contentUriTriggers = set0;
    }

    public Constraints(NetworkType networkType0, boolean z, boolean z1, boolean z2, boolean z3, long v, long v1, Set set0, int v2, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v2 & 1) != 0) {
            networkType0 = NetworkType.NOT_REQUIRED;
        }
        if((v2 & 2) != 0) {
            z = false;
        }
        if((v2 & 4) != 0) {
            z1 = false;
        }
        if((v2 & 8) != 0) {
            z2 = false;
        }
        if((v2 & 16) != 0) {
            z3 = false;
        }
        if((v2 & 0x20) != 0) {
            v = -1L;
        }
        if((v2 & 0x40) != 0) {
            v1 = -1L;
        }
        if((v2 & 0x80) != 0) {
            set0 = SetsKt.emptySet();
        }
        this(networkType0, z, z1, z2, z3, v, v1, set0);
    }

    public Constraints(NetworkRequestCompat networkRequestCompat0, NetworkType networkType0, boolean z, boolean z1, boolean z2, boolean z3, long v, long v1, Set set0) {
        Intrinsics.checkNotNullParameter(networkRequestCompat0, "requiredNetworkRequestCompat");
        Intrinsics.checkNotNullParameter(networkType0, "requiredNetworkType");
        Intrinsics.checkNotNullParameter(set0, "contentUriTriggers");
        super();
        this.requiredNetworkRequestCompat = networkRequestCompat0;
        this.requiredNetworkType = networkType0;
        this.requiresCharging = z;
        this.requiresDeviceIdle = z1;
        this.requiresBatteryNotLow = z2;
        this.requiresStorageNotLow = z3;
        this.contentTriggerUpdateDelayMillis = v;
        this.contentTriggerMaxDelayMillis = v1;
        this.contentUriTriggers = set0;
    }

    public Constraints(NetworkRequestCompat networkRequestCompat0, NetworkType networkType0, boolean z, boolean z1, boolean z2, boolean z3, long v, long v1, Set set0, int v2, DefaultConstructorMarker defaultConstructorMarker0) {
        this(networkRequestCompat0, ((v2 & 2) == 0 ? networkType0 : NetworkType.NOT_REQUIRED), ((v2 & 4) == 0 ? z : false), ((v2 & 8) == 0 ? z1 : false), ((v2 & 16) == 0 ? z2 : false), ((v2 & 0x20) == 0 ? z3 : false), ((v2 & 0x40) == 0 ? v : -1L), ((v2 & 0x80) == 0 ? v1 : -1L), ((v2 & 0x100) == 0 ? set0 : SetsKt.emptySet()));
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null || !Intrinsics.areEqual(this.getClass(), object0.getClass()) || this.requiresCharging != ((Constraints)object0).requiresCharging) {
            return false;
        }
        if(this.requiresDeviceIdle != ((Constraints)object0).requiresDeviceIdle) {
            return false;
        }
        if(this.requiresBatteryNotLow != ((Constraints)object0).requiresBatteryNotLow) {
            return false;
        }
        if(this.requiresStorageNotLow != ((Constraints)object0).requiresStorageNotLow) {
            return false;
        }
        if(this.contentTriggerUpdateDelayMillis != ((Constraints)object0).contentTriggerUpdateDelayMillis) {
            return false;
        }
        if(this.contentTriggerMaxDelayMillis != ((Constraints)object0).contentTriggerMaxDelayMillis) {
            return false;
        }
        if(!Intrinsics.areEqual(this.getRequiredNetworkRequest(), ((Constraints)object0).getRequiredNetworkRequest())) {
            return false;
        }
        return this.requiredNetworkType == ((Constraints)object0).requiredNetworkType ? Intrinsics.areEqual(this.contentUriTriggers, ((Constraints)object0).contentUriTriggers) : false;
    }

    public final long getContentTriggerMaxDelayMillis() {
        return this.contentTriggerMaxDelayMillis;
    }

    public final long getContentTriggerUpdateDelayMillis() {
        return this.contentTriggerUpdateDelayMillis;
    }

    public final Set getContentUriTriggers() {
        return this.contentUriTriggers;
    }

    public final NetworkRequest getRequiredNetworkRequest() {
        return this.requiredNetworkRequestCompat.getNetworkRequest();
    }

    public final NetworkRequestCompat getRequiredNetworkRequestCompat$work_runtime_release() {
        return this.requiredNetworkRequestCompat;
    }

    public final NetworkType getRequiredNetworkType() {
        return this.requiredNetworkType;
    }

    public final boolean hasContentUriTriggers() {
        return Build.VERSION.SDK_INT < 24 || !this.contentUriTriggers.isEmpty();
    }

    @Override
    public int hashCode() {
        int v = (((((((this.requiredNetworkType.hashCode() * 0x1F + this.requiresCharging) * 0x1F + this.requiresDeviceIdle) * 0x1F + this.requiresBatteryNotLow) * 0x1F + this.requiresStorageNotLow) * 0x1F + ((int)(this.contentTriggerUpdateDelayMillis ^ this.contentTriggerUpdateDelayMillis >>> 0x20))) * 0x1F + ((int)(this.contentTriggerMaxDelayMillis ^ this.contentTriggerMaxDelayMillis >>> 0x20))) * 0x1F + this.contentUriTriggers.hashCode()) * 0x1F;
        NetworkRequest networkRequest0 = this.getRequiredNetworkRequest();
        return networkRequest0 == null ? v : v + networkRequest0.hashCode();
    }

    public final boolean requiresBatteryNotLow() {
        return this.requiresBatteryNotLow;
    }

    public final boolean requiresCharging() {
        return this.requiresCharging;
    }

    public final boolean requiresDeviceIdle() {
        return this.requiresDeviceIdle;
    }

    public final boolean requiresStorageNotLow() {
        return this.requiresStorageNotLow;
    }

    @Override
    public String toString() {
        return "Constraints{requiredNetworkType=" + this.requiredNetworkType + ", requiresCharging=" + this.requiresCharging + ", requiresDeviceIdle=" + this.requiresDeviceIdle + ", requiresBatteryNotLow=" + this.requiresBatteryNotLow + ", requiresStorageNotLow=" + this.requiresStorageNotLow + ", contentTriggerUpdateDelayMillis=" + this.contentTriggerUpdateDelayMillis + ", contentTriggerMaxDelayMillis=" + this.contentTriggerMaxDelayMillis + ", contentUriTriggers=" + this.contentUriTriggers + ", }";
    }
}

