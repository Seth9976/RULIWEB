package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import androidx.lifecycle.Lifecycle.State;

final class FragmentState implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final int mMaxLifecycleState;
    final boolean mRemoving;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;
    final String mWho;

    static {
        FragmentState.CREATOR = new Parcelable.Creator() {
            public FragmentState createFromParcel(Parcel parcel0) {
                return new FragmentState(parcel0);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public FragmentState[] newArray(int v) {
                return new FragmentState[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    FragmentState(Parcel parcel0) {
        this.mClassName = parcel0.readString();
        this.mWho = parcel0.readString();
        boolean z = true;
        this.mFromLayout = parcel0.readInt() != 0;
        this.mFragmentId = parcel0.readInt();
        this.mContainerId = parcel0.readInt();
        this.mTag = parcel0.readString();
        this.mRetainInstance = parcel0.readInt() != 0;
        this.mRemoving = parcel0.readInt() != 0;
        this.mDetached = parcel0.readInt() != 0;
        this.mArguments = parcel0.readBundle();
        if(parcel0.readInt() == 0) {
            z = false;
        }
        this.mHidden = z;
        this.mSavedFragmentState = parcel0.readBundle();
        this.mMaxLifecycleState = parcel0.readInt();
    }

    FragmentState(Fragment fragment0) {
        this.mClassName = fragment0.getClass().getName();
        this.mWho = fragment0.mWho;
        this.mFromLayout = fragment0.mFromLayout;
        this.mFragmentId = fragment0.mFragmentId;
        this.mContainerId = fragment0.mContainerId;
        this.mTag = fragment0.mTag;
        this.mRetainInstance = fragment0.mRetainInstance;
        this.mRemoving = fragment0.mRemoving;
        this.mDetached = fragment0.mDetached;
        this.mArguments = fragment0.mArguments;
        this.mHidden = fragment0.mHidden;
        this.mMaxLifecycleState = fragment0.mMaxState.ordinal();
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    Fragment instantiate(FragmentFactory fragmentFactory0, ClassLoader classLoader0) {
        Fragment fragment0 = fragmentFactory0.instantiate(classLoader0, this.mClassName);
        Bundle bundle0 = this.mArguments;
        if(bundle0 != null) {
            bundle0.setClassLoader(classLoader0);
        }
        fragment0.setArguments(this.mArguments);
        fragment0.mWho = this.mWho;
        fragment0.mFromLayout = this.mFromLayout;
        fragment0.mRestored = true;
        fragment0.mFragmentId = this.mFragmentId;
        fragment0.mContainerId = this.mContainerId;
        fragment0.mTag = this.mTag;
        fragment0.mRetainInstance = this.mRetainInstance;
        fragment0.mRemoving = this.mRemoving;
        fragment0.mDetached = this.mDetached;
        fragment0.mHidden = this.mHidden;
        State[] arr_lifecycle$State = State.values();
        fragment0.mMaxState = arr_lifecycle$State[this.mMaxLifecycleState];
        Bundle bundle1 = this.mSavedFragmentState;
        if(bundle1 != null) {
            fragment0.mSavedFragmentState = bundle1;
            return fragment0;
        }
        fragment0.mSavedFragmentState = new Bundle();
        return fragment0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x80);
        stringBuilder0.append("FragmentState{");
        stringBuilder0.append(this.mClassName);
        stringBuilder0.append(" (");
        stringBuilder0.append(this.mWho);
        stringBuilder0.append(")}:");
        if(this.mFromLayout) {
            stringBuilder0.append(" fromLayout");
        }
        if(this.mContainerId != 0) {
            stringBuilder0.append(" id=0x");
            stringBuilder0.append(Integer.toHexString(this.mContainerId));
        }
        if(this.mTag != null && !this.mTag.isEmpty()) {
            stringBuilder0.append(" tag=");
            stringBuilder0.append(this.mTag);
        }
        if(this.mRetainInstance) {
            stringBuilder0.append(" retainInstance");
        }
        if(this.mRemoving) {
            stringBuilder0.append(" removing");
        }
        if(this.mDetached) {
            stringBuilder0.append(" detached");
        }
        if(this.mHidden) {
            stringBuilder0.append(" hidden");
        }
        return stringBuilder0.toString();
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeString(this.mClassName);
        parcel0.writeString(this.mWho);
        parcel0.writeInt(((int)this.mFromLayout));
        parcel0.writeInt(this.mFragmentId);
        parcel0.writeInt(this.mContainerId);
        parcel0.writeString(this.mTag);
        parcel0.writeInt(((int)this.mRetainInstance));
        parcel0.writeInt(((int)this.mRemoving));
        parcel0.writeInt(((int)this.mDetached));
        parcel0.writeBundle(this.mArguments);
        parcel0.writeInt(((int)this.mHidden));
        parcel0.writeBundle(this.mSavedFragmentState);
        parcel0.writeInt(this.mMaxLifecycleState);
    }
}

