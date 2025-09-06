package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.ArrayList;

final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    ArrayList mActive;
    ArrayList mAdded;
    BackStackRecordState[] mBackStack;
    int mBackStackIndex;
    ArrayList mBackStackStateKeys;
    ArrayList mBackStackStates;
    ArrayList mLaunchedFragments;
    String mPrimaryNavActiveWho;

    static {
        FragmentManagerState.CREATOR = new Parcelable.Creator() {
            public FragmentManagerState createFromParcel(Parcel parcel0) {
                return new FragmentManagerState(parcel0);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public FragmentManagerState[] newArray(int v) {
                return new FragmentManagerState[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    public FragmentManagerState() {
        this.mPrimaryNavActiveWho = null;
        this.mBackStackStateKeys = new ArrayList();
        this.mBackStackStates = new ArrayList();
    }

    public FragmentManagerState(Parcel parcel0) {
        this.mPrimaryNavActiveWho = null;
        this.mBackStackStateKeys = new ArrayList();
        this.mBackStackStates = new ArrayList();
        this.mActive = parcel0.createStringArrayList();
        this.mAdded = parcel0.createStringArrayList();
        this.mBackStack = (BackStackRecordState[])parcel0.createTypedArray(BackStackRecordState.CREATOR);
        this.mBackStackIndex = parcel0.readInt();
        this.mPrimaryNavActiveWho = parcel0.readString();
        this.mBackStackStateKeys = parcel0.createStringArrayList();
        this.mBackStackStates = parcel0.createTypedArrayList(BackStackState.CREATOR);
        this.mLaunchedFragments = parcel0.createTypedArrayList(LaunchedFragmentInfo.CREATOR);
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeStringList(this.mActive);
        parcel0.writeStringList(this.mAdded);
        parcel0.writeTypedArray(this.mBackStack, v);
        parcel0.writeInt(this.mBackStackIndex);
        parcel0.writeString(this.mPrimaryNavActiveWho);
        parcel0.writeStringList(this.mBackStackStateKeys);
        parcel0.writeTypedList(this.mBackStackStates);
        parcel0.writeTypedList(this.mLaunchedFragments);
    }
}

