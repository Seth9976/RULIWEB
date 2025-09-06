package androidx.core.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.ColorKt..ExternalSyntheticApiModelOutline0;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.net.UriCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortcutInfoCompat {
    static class Api33Impl {
        static void setExcludedFromSurfaces(ShortcutInfo.Builder shortcutInfo$Builder0, int v) {
            shortcutInfo$Builder0.setExcludedFromSurfaces(v);
        }
    }

    public static class Builder {
        private Map mCapabilityBindingParams;
        private Set mCapabilityBindings;
        private final ShortcutInfoCompat mInfo;
        private boolean mIsConversation;
        private Uri mSliceUri;

        public Builder(Context context0, ShortcutInfo shortcutInfo0) {
            ShortcutInfoCompat shortcutInfoCompat0 = new ShortcutInfoCompat();
            this.mInfo = shortcutInfoCompat0;
            shortcutInfoCompat0.mContext = context0;
            shortcutInfoCompat0.mId = shortcutInfo0.getId();
            shortcutInfoCompat0.mPackageName = shortcutInfo0.getPackage();
            Intent[] arr_intent = shortcutInfo0.getIntents();
            shortcutInfoCompat0.mIntents = (Intent[])Arrays.copyOf(arr_intent, arr_intent.length);
            shortcutInfoCompat0.mActivity = shortcutInfo0.getActivity();
            shortcutInfoCompat0.mLabel = shortcutInfo0.getShortLabel();
            shortcutInfoCompat0.mLongLabel = shortcutInfo0.getLongLabel();
            shortcutInfoCompat0.mDisabledMessage = shortcutInfo0.getDisabledMessage();
            if(Build.VERSION.SDK_INT >= 28) {
                shortcutInfoCompat0.mDisabledReason = shortcutInfo0.getDisabledReason();
            }
            else {
                shortcutInfoCompat0.mDisabledReason = shortcutInfo0.isEnabled() ? 0 : 3;
            }
            shortcutInfoCompat0.mCategories = shortcutInfo0.getCategories();
            shortcutInfoCompat0.mPersons = ShortcutInfoCompat.getPersonsFromExtra(shortcutInfo0.getExtras());
            shortcutInfoCompat0.mUser = shortcutInfo0.getUserHandle();
            shortcutInfoCompat0.mLastChangedTimestamp = shortcutInfo0.getLastChangedTimestamp();
            if(Build.VERSION.SDK_INT >= 30) {
                shortcutInfoCompat0.mIsCached = shortcutInfo0.isCached();
            }
            shortcutInfoCompat0.mIsDynamic = shortcutInfo0.isDynamic();
            shortcutInfoCompat0.mIsPinned = shortcutInfo0.isPinned();
            shortcutInfoCompat0.mIsDeclaredInManifest = shortcutInfo0.isDeclaredInManifest();
            shortcutInfoCompat0.mIsImmutable = shortcutInfo0.isImmutable();
            shortcutInfoCompat0.mIsEnabled = shortcutInfo0.isEnabled();
            shortcutInfoCompat0.mHasKeyFieldsOnly = shortcutInfo0.hasKeyFieldsOnly();
            shortcutInfoCompat0.mLocusId = ShortcutInfoCompat.getLocusId(shortcutInfo0);
            shortcutInfoCompat0.mRank = shortcutInfo0.getRank();
            shortcutInfoCompat0.mExtras = shortcutInfo0.getExtras();
        }

        public Builder(Context context0, String s) {
            ShortcutInfoCompat shortcutInfoCompat0 = new ShortcutInfoCompat();
            this.mInfo = shortcutInfoCompat0;
            shortcutInfoCompat0.mContext = context0;
            shortcutInfoCompat0.mId = s;
        }

        public Builder(ShortcutInfoCompat shortcutInfoCompat0) {
            ShortcutInfoCompat shortcutInfoCompat1 = new ShortcutInfoCompat();
            this.mInfo = shortcutInfoCompat1;
            shortcutInfoCompat1.mContext = shortcutInfoCompat0.mContext;
            shortcutInfoCompat1.mId = shortcutInfoCompat0.mId;
            shortcutInfoCompat1.mPackageName = shortcutInfoCompat0.mPackageName;
            shortcutInfoCompat1.mIntents = (Intent[])Arrays.copyOf(shortcutInfoCompat0.mIntents, shortcutInfoCompat0.mIntents.length);
            shortcutInfoCompat1.mActivity = shortcutInfoCompat0.mActivity;
            shortcutInfoCompat1.mLabel = shortcutInfoCompat0.mLabel;
            shortcutInfoCompat1.mLongLabel = shortcutInfoCompat0.mLongLabel;
            shortcutInfoCompat1.mDisabledMessage = shortcutInfoCompat0.mDisabledMessage;
            shortcutInfoCompat1.mDisabledReason = shortcutInfoCompat0.mDisabledReason;
            shortcutInfoCompat1.mIcon = shortcutInfoCompat0.mIcon;
            shortcutInfoCompat1.mIsAlwaysBadged = shortcutInfoCompat0.mIsAlwaysBadged;
            shortcutInfoCompat1.mUser = shortcutInfoCompat0.mUser;
            shortcutInfoCompat1.mLastChangedTimestamp = shortcutInfoCompat0.mLastChangedTimestamp;
            shortcutInfoCompat1.mIsCached = shortcutInfoCompat0.mIsCached;
            shortcutInfoCompat1.mIsDynamic = shortcutInfoCompat0.mIsDynamic;
            shortcutInfoCompat1.mIsPinned = shortcutInfoCompat0.mIsPinned;
            shortcutInfoCompat1.mIsDeclaredInManifest = shortcutInfoCompat0.mIsDeclaredInManifest;
            shortcutInfoCompat1.mIsImmutable = shortcutInfoCompat0.mIsImmutable;
            shortcutInfoCompat1.mIsEnabled = shortcutInfoCompat0.mIsEnabled;
            shortcutInfoCompat1.mLocusId = shortcutInfoCompat0.mLocusId;
            shortcutInfoCompat1.mIsLongLived = shortcutInfoCompat0.mIsLongLived;
            shortcutInfoCompat1.mHasKeyFieldsOnly = shortcutInfoCompat0.mHasKeyFieldsOnly;
            shortcutInfoCompat1.mRank = shortcutInfoCompat0.mRank;
            if(shortcutInfoCompat0.mPersons != null) {
                shortcutInfoCompat1.mPersons = (Person[])Arrays.copyOf(shortcutInfoCompat0.mPersons, shortcutInfoCompat0.mPersons.length);
            }
            if(shortcutInfoCompat0.mCategories != null) {
                shortcutInfoCompat1.mCategories = new HashSet(shortcutInfoCompat0.mCategories);
            }
            if(shortcutInfoCompat0.mExtras != null) {
                shortcutInfoCompat1.mExtras = shortcutInfoCompat0.mExtras;
            }
            shortcutInfoCompat1.mExcludedSurfaces = shortcutInfoCompat0.mExcludedSurfaces;
        }

        public Builder addCapabilityBinding(String s) {
            if(this.mCapabilityBindings == null) {
                this.mCapabilityBindings = new HashSet();
            }
            this.mCapabilityBindings.add(s);
            return this;
        }

        public Builder addCapabilityBinding(String s, String s1, List list0) {
            this.addCapabilityBinding(s);
            if(!list0.isEmpty()) {
                if(this.mCapabilityBindingParams == null) {
                    this.mCapabilityBindingParams = new HashMap();
                }
                if(this.mCapabilityBindingParams.get(s) == null) {
                    this.mCapabilityBindingParams.put(s, new HashMap());
                }
                ((Map)this.mCapabilityBindingParams.get(s)).put(s1, list0);
            }
            return this;
        }

        public ShortcutInfoCompat build() {
            if(TextUtils.isEmpty(this.mInfo.mLabel)) {
                throw new IllegalArgumentException("Shortcut must have a non-empty label");
            }
            if(this.mInfo.mIntents == null || this.mInfo.mIntents.length == 0) {
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
            if(this.mIsConversation) {
                if(this.mInfo.mLocusId == null) {
                    this.mInfo.mLocusId = new LocusIdCompat(this.mInfo.mId);
                }
                this.mInfo.mIsLongLived = true;
            }
            if(this.mCapabilityBindings != null) {
                if(this.mInfo.mCategories == null) {
                    this.mInfo.mCategories = new HashSet();
                }
                this.mInfo.mCategories.addAll(this.mCapabilityBindings);
            }
            if(this.mCapabilityBindingParams != null) {
                if(this.mInfo.mExtras == null) {
                    this.mInfo.mExtras = new PersistableBundle();
                }
                for(Object object0: this.mCapabilityBindingParams.keySet()) {
                    String s = (String)object0;
                    Map map0 = (Map)this.mCapabilityBindingParams.get(s);
                    Set set0 = map0.keySet();
                    this.mInfo.mExtras.putStringArray(s, ((String[])set0.toArray(new String[0])));
                    for(Object object1: map0.keySet()) {
                        List list0 = (List)map0.get(((String)object1));
                        this.mInfo.mExtras.putStringArray(s + "/" + ((String)object1), (list0 == null ? new String[0] : ((String[])list0.toArray(new String[0]))));
                    }
                }
            }
            if(this.mSliceUri != null) {
                if(this.mInfo.mExtras == null) {
                    this.mInfo.mExtras = new PersistableBundle();
                }
                this.mInfo.mExtras.putString("extraSliceUri", UriCompat.toSafeString(this.mSliceUri));
            }
            return this.mInfo;
        }

        public Builder setActivity(ComponentName componentName0) {
            this.mInfo.mActivity = componentName0;
            return this;
        }

        public Builder setAlwaysBadged() {
            this.mInfo.mIsAlwaysBadged = true;
            return this;
        }

        public Builder setCategories(Set set0) {
            ArraySet arraySet0 = new ArraySet();
            arraySet0.addAll(set0);
            this.mInfo.mCategories = arraySet0;
            return this;
        }

        public Builder setDisabledMessage(CharSequence charSequence0) {
            this.mInfo.mDisabledMessage = charSequence0;
            return this;
        }

        public Builder setExcludedFromSurfaces(int v) {
            this.mInfo.mExcludedSurfaces = v;
            return this;
        }

        public Builder setExtras(PersistableBundle persistableBundle0) {
            this.mInfo.mExtras = persistableBundle0;
            return this;
        }

        public Builder setIcon(IconCompat iconCompat0) {
            this.mInfo.mIcon = iconCompat0;
            return this;
        }

        public Builder setIntent(Intent intent0) {
            return this.setIntents(new Intent[]{intent0});
        }

        public Builder setIntents(Intent[] arr_intent) {
            this.mInfo.mIntents = arr_intent;
            return this;
        }

        public Builder setIsConversation() {
            this.mIsConversation = true;
            return this;
        }

        public Builder setLocusId(LocusIdCompat locusIdCompat0) {
            this.mInfo.mLocusId = locusIdCompat0;
            return this;
        }

        public Builder setLongLabel(CharSequence charSequence0) {
            this.mInfo.mLongLabel = charSequence0;
            return this;
        }

        @Deprecated
        public Builder setLongLived() {
            this.mInfo.mIsLongLived = true;
            return this;
        }

        public Builder setLongLived(boolean z) {
            this.mInfo.mIsLongLived = z;
            return this;
        }

        public Builder setPerson(Person person0) {
            return this.setPersons(new Person[]{person0});
        }

        public Builder setPersons(Person[] arr_person) {
            this.mInfo.mPersons = arr_person;
            return this;
        }

        public Builder setRank(int v) {
            this.mInfo.mRank = v;
            return this;
        }

        public Builder setShortLabel(CharSequence charSequence0) {
            this.mInfo.mLabel = charSequence0;
            return this;
        }

        public Builder setSliceUri(Uri uri0) {
            this.mSliceUri = uri0;
            return this;
        }

        public Builder setTransientExtras(Bundle bundle0) {
            this.mInfo.mTransientExtras = (Bundle)Preconditions.checkNotNull(bundle0);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Surface {
    }

    private static final String EXTRA_LOCUS_ID = "extraLocusId";
    private static final String EXTRA_LONG_LIVED = "extraLongLived";
    private static final String EXTRA_PERSON_ = "extraPerson_";
    private static final String EXTRA_PERSON_COUNT = "extraPersonCount";
    private static final String EXTRA_SLICE_URI = "extraSliceUri";
    public static final int SURFACE_LAUNCHER = 1;
    ComponentName mActivity;
    Set mCategories;
    Context mContext;
    CharSequence mDisabledMessage;
    int mDisabledReason;
    int mExcludedSurfaces;
    PersistableBundle mExtras;
    boolean mHasKeyFieldsOnly;
    IconCompat mIcon;
    String mId;
    Intent[] mIntents;
    boolean mIsAlwaysBadged;
    boolean mIsCached;
    boolean mIsDeclaredInManifest;
    boolean mIsDynamic;
    boolean mIsEnabled;
    boolean mIsImmutable;
    boolean mIsLongLived;
    boolean mIsPinned;
    CharSequence mLabel;
    long mLastChangedTimestamp;
    LocusIdCompat mLocusId;
    CharSequence mLongLabel;
    String mPackageName;
    Person[] mPersons;
    int mRank;
    Bundle mTransientExtras;
    UserHandle mUser;

    ShortcutInfoCompat() {
        this.mIsEnabled = true;
    }

    Intent addToIntent(Intent intent0) {
        Drawable drawable0 = null;
        intent0.putExtra("android.intent.extra.shortcut.INTENT", this.mIntents[this.mIntents.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
        if(this.mIcon != null) {
            if(this.mIsAlwaysBadged) {
                PackageManager packageManager0 = this.mContext.getPackageManager();
                ComponentName componentName0 = this.mActivity;
                if(componentName0 != null) {
                    try {
                        drawable0 = packageManager0.getActivityIcon(componentName0);
                    }
                    catch(PackageManager.NameNotFoundException unused_ex) {
                    }
                }
                if(drawable0 == null) {
                    drawable0 = this.mContext.getApplicationInfo().loadIcon(packageManager0);
                }
            }
            this.mIcon.addToShortcutIntent(intent0, drawable0, this.mContext);
        }
        return intent0;
    }

    private PersistableBundle buildLegacyExtrasBundle() {
        if(this.mExtras == null) {
            this.mExtras = new PersistableBundle();
        }
        Person[] arr_person = this.mPersons;
        if(arr_person != null && arr_person.length > 0) {
            this.mExtras.putInt("extraPersonCount", arr_person.length);
            for(int v = 0; v < this.mPersons.length; ++v) {
                this.mExtras.putPersistableBundle("extraPerson_" + (v + 1), this.mPersons[v].toPersistableBundle());
            }
        }
        LocusIdCompat locusIdCompat0 = this.mLocusId;
        if(locusIdCompat0 != null) {
            this.mExtras.putString("extraLocusId", locusIdCompat0.getId());
        }
        this.mExtras.putBoolean("extraLongLived", this.mIsLongLived);
        return this.mExtras;
    }

    static List fromShortcuts(Context context0, List list0) {
        List list1 = new ArrayList(list0.size());
        for(Object object0: list0) {
            list1.add(new Builder(context0, ((ShortcutInfo)object0)).build());
        }
        return list1;
    }

    public ComponentName getActivity() {
        return this.mActivity;
    }

    public Set getCategories() {
        return this.mCategories;
    }

    public CharSequence getDisabledMessage() {
        return this.mDisabledMessage;
    }

    public int getDisabledReason() {
        return this.mDisabledReason;
    }

    public int getExcludedFromSurfaces() {
        return this.mExcludedSurfaces;
    }

    public PersistableBundle getExtras() {
        return this.mExtras;
    }

    public IconCompat getIcon() {
        return this.mIcon;
    }

    public String getId() {
        return this.mId;
    }

    public Intent getIntent() {
        return this.mIntents[this.mIntents.length - 1];
    }

    public Intent[] getIntents() {
        return (Intent[])Arrays.copyOf(this.mIntents, this.mIntents.length);
    }

    public long getLastChangedTimestamp() {
        return this.mLastChangedTimestamp;
    }

    static LocusIdCompat getLocusId(ShortcutInfo shortcutInfo0) {
        if(Build.VERSION.SDK_INT >= 29) {
            return shortcutInfo0.getLocusId() == null ? null : LocusIdCompat.toLocusIdCompat(shortcutInfo0.getLocusId());
        }
        return ShortcutInfoCompat.getLocusIdFromExtra(shortcutInfo0.getExtras());
    }

    public LocusIdCompat getLocusId() {
        return this.mLocusId;
    }

    private static LocusIdCompat getLocusIdFromExtra(PersistableBundle persistableBundle0) {
        if(persistableBundle0 == null) {
            return null;
        }
        String s = persistableBundle0.getString("extraLocusId");
        return s == null ? null : new LocusIdCompat(s);
    }

    public CharSequence getLongLabel() {
        return this.mLongLabel;
    }

    static boolean getLongLivedFromExtra(PersistableBundle persistableBundle0) {
        return persistableBundle0 == null || !persistableBundle0.containsKey("extraLongLived") ? false : persistableBundle0.getBoolean("extraLongLived");
    }

    public String getPackage() {
        return this.mPackageName;
    }

    static Person[] getPersonsFromExtra(PersistableBundle persistableBundle0) {
        if(persistableBundle0 != null && persistableBundle0.containsKey("extraPersonCount")) {
            int v = persistableBundle0.getInt("extraPersonCount");
            Person[] arr_person = new Person[v];
            for(int v1 = 0; v1 < v; ++v1) {
                arr_person[v1] = Person.fromPersistableBundle(persistableBundle0.getPersistableBundle("extraPerson_" + (v1 + 1)));
            }
            return arr_person;
        }
        return null;
    }

    public int getRank() {
        return this.mRank;
    }

    public CharSequence getShortLabel() {
        return this.mLabel;
    }

    public Bundle getTransientExtras() {
        return this.mTransientExtras;
    }

    public UserHandle getUserHandle() {
        return this.mUser;
    }

    public boolean hasKeyFieldsOnly() {
        return this.mHasKeyFieldsOnly;
    }

    public boolean isCached() {
        return this.mIsCached;
    }

    public boolean isDeclaredInManifest() {
        return this.mIsDeclaredInManifest;
    }

    public boolean isDynamic() {
        return this.mIsDynamic;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public boolean isExcludedFromSurfaces(int v) {
        return (v & this.mExcludedSurfaces) != 0;
    }

    public boolean isImmutable() {
        return this.mIsImmutable;
    }

    public boolean isPinned() {
        return this.mIsPinned;
    }

    public ShortcutInfo toShortcutInfo() {
        ShortcutInfo.Builder shortcutInfo$Builder0 = ColorKt..ExternalSyntheticApiModelOutline0.m(this.mContext, this.mId).setShortLabel(this.mLabel).setIntents(this.mIntents);
        IconCompat iconCompat0 = this.mIcon;
        if(iconCompat0 != null) {
            shortcutInfo$Builder0.setIcon(iconCompat0.toIcon(this.mContext));
        }
        if(!TextUtils.isEmpty(this.mLongLabel)) {
            shortcutInfo$Builder0.setLongLabel(this.mLongLabel);
        }
        if(!TextUtils.isEmpty(this.mDisabledMessage)) {
            shortcutInfo$Builder0.setDisabledMessage(this.mDisabledMessage);
        }
        ComponentName componentName0 = this.mActivity;
        if(componentName0 != null) {
            shortcutInfo$Builder0.setActivity(componentName0);
        }
        Set set0 = this.mCategories;
        if(set0 != null) {
            shortcutInfo$Builder0.setCategories(set0);
        }
        shortcutInfo$Builder0.setRank(this.mRank);
        PersistableBundle persistableBundle0 = this.mExtras;
        if(persistableBundle0 != null) {
            shortcutInfo$Builder0.setExtras(persistableBundle0);
        }
        if(Build.VERSION.SDK_INT >= 29) {
            Person[] arr_person = this.mPersons;
            if(arr_person != null && arr_person.length > 0) {
                android.app.Person[] arr_person1 = new android.app.Person[arr_person.length];
                for(int v = 0; v < arr_person.length; ++v) {
                    arr_person1[v] = this.mPersons[v].toAndroidPerson();
                }
                shortcutInfo$Builder0.setPersons(arr_person1);
            }
            LocusIdCompat locusIdCompat0 = this.mLocusId;
            if(locusIdCompat0 != null) {
                shortcutInfo$Builder0.setLocusId(locusIdCompat0.toLocusId());
            }
            shortcutInfo$Builder0.setLongLived(this.mIsLongLived);
        }
        else {
            shortcutInfo$Builder0.setExtras(this.buildLegacyExtrasBundle());
        }
        if(Build.VERSION.SDK_INT >= 33) {
            Api33Impl.setExcludedFromSurfaces(shortcutInfo$Builder0, this.mExcludedSurfaces);
        }
        return shortcutInfo$Builder0.build();
    }
}

