package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle.State;
import java.util.ArrayList;
import java.util.Map;

final class BackStackRecordState implements Parcelable {
    public static final Parcelable.Creator CREATOR = null;
    private static final String TAG = "FragmentManager";
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int[] mCurrentMaxLifecycleStates;
    final ArrayList mFragmentWhos;
    final int mIndex;
    final String mName;
    final int[] mOldMaxLifecycleStates;
    final int[] mOps;
    final boolean mReorderingAllowed;
    final ArrayList mSharedElementSourceNames;
    final ArrayList mSharedElementTargetNames;
    final int mTransition;

    static {
        BackStackRecordState.CREATOR = new Parcelable.Creator() {
            public BackStackRecordState createFromParcel(Parcel parcel0) {
                return new BackStackRecordState(parcel0);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public BackStackRecordState[] newArray(int v) {
                return new BackStackRecordState[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    BackStackRecordState(Parcel parcel0) {
        this.mOps = parcel0.createIntArray();
        this.mFragmentWhos = parcel0.createStringArrayList();
        this.mOldMaxLifecycleStates = parcel0.createIntArray();
        this.mCurrentMaxLifecycleStates = parcel0.createIntArray();
        this.mTransition = parcel0.readInt();
        this.mName = parcel0.readString();
        this.mIndex = parcel0.readInt();
        this.mBreadCrumbTitleRes = parcel0.readInt();
        this.mBreadCrumbTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel0);
        this.mBreadCrumbShortTitleRes = parcel0.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel0);
        this.mSharedElementSourceNames = parcel0.createStringArrayList();
        this.mSharedElementTargetNames = parcel0.createStringArrayList();
        this.mReorderingAllowed = parcel0.readInt() != 0;
    }

    BackStackRecordState(BackStackRecord backStackRecord0) {
        int v = backStackRecord0.mOps.size();
        this.mOps = new int[v * 6];
        if(!backStackRecord0.mAddToBackStack) {
            throw new IllegalStateException("Not on back stack");
        }
        this.mFragmentWhos = new ArrayList(v);
        this.mOldMaxLifecycleStates = new int[v];
        this.mCurrentMaxLifecycleStates = new int[v];
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            Op fragmentTransaction$Op0 = (Op)backStackRecord0.mOps.get(v1);
            this.mOps[v2] = fragmentTransaction$Op0.mCmd;
            this.mFragmentWhos.add((fragmentTransaction$Op0.mFragment == null ? null : fragmentTransaction$Op0.mFragment.mWho));
            this.mOps[v2 + 1] = fragmentTransaction$Op0.mFromExpandedOp;
            this.mOps[v2 + 2] = fragmentTransaction$Op0.mEnterAnim;
            this.mOps[v2 + 3] = fragmentTransaction$Op0.mExitAnim;
            int v3 = v2 + 5;
            this.mOps[v2 + 4] = fragmentTransaction$Op0.mPopEnterAnim;
            v2 += 6;
            this.mOps[v3] = fragmentTransaction$Op0.mPopExitAnim;
            this.mOldMaxLifecycleStates[v1] = fragmentTransaction$Op0.mOldMaxState.ordinal();
            this.mCurrentMaxLifecycleStates[v1] = fragmentTransaction$Op0.mCurrentMaxState.ordinal();
        }
        this.mTransition = backStackRecord0.mTransition;
        this.mName = backStackRecord0.mName;
        this.mIndex = backStackRecord0.mIndex;
        this.mBreadCrumbTitleRes = backStackRecord0.mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = backStackRecord0.mBreadCrumbTitleText;
        this.mBreadCrumbShortTitleRes = backStackRecord0.mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = backStackRecord0.mBreadCrumbShortTitleText;
        this.mSharedElementSourceNames = backStackRecord0.mSharedElementSourceNames;
        this.mSharedElementTargetNames = backStackRecord0.mSharedElementTargetNames;
        this.mReorderingAllowed = backStackRecord0.mReorderingAllowed;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private void fillInBackStackRecord(BackStackRecord backStackRecord0) {
        int v = 0;
        for(int v1 = 0; true; ++v1) {
            boolean z = true;
            if(v >= this.mOps.length) {
                break;
            }
            Op fragmentTransaction$Op0 = new Op();
            fragmentTransaction$Op0.mCmd = this.mOps[v];
            if(FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Instantiate " + backStackRecord0 + " op #" + v1 + " base fragment #" + this.mOps[v + 1]);
            }
            State[] arr_lifecycle$State = State.values();
            fragmentTransaction$Op0.mOldMaxState = arr_lifecycle$State[this.mOldMaxLifecycleStates[v1]];
            State[] arr_lifecycle$State1 = State.values();
            fragmentTransaction$Op0.mCurrentMaxState = arr_lifecycle$State1[this.mCurrentMaxLifecycleStates[v1]];
            if(this.mOps[v + 1] == 0) {
                z = false;
            }
            fragmentTransaction$Op0.mFromExpandedOp = z;
            fragmentTransaction$Op0.mEnterAnim = this.mOps[v + 2];
            fragmentTransaction$Op0.mExitAnim = this.mOps[v + 3];
            int v2 = v + 5;
            fragmentTransaction$Op0.mPopEnterAnim = this.mOps[v + 4];
            v += 6;
            fragmentTransaction$Op0.mPopExitAnim = this.mOps[v2];
            backStackRecord0.mEnterAnim = fragmentTransaction$Op0.mEnterAnim;
            backStackRecord0.mExitAnim = fragmentTransaction$Op0.mExitAnim;
            backStackRecord0.mPopEnterAnim = fragmentTransaction$Op0.mPopEnterAnim;
            backStackRecord0.mPopExitAnim = fragmentTransaction$Op0.mPopExitAnim;
            backStackRecord0.addOp(fragmentTransaction$Op0);
        }
        backStackRecord0.mTransition = this.mTransition;
        backStackRecord0.mName = this.mName;
        backStackRecord0.mAddToBackStack = true;
        backStackRecord0.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        backStackRecord0.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        backStackRecord0.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        backStackRecord0.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        backStackRecord0.mSharedElementSourceNames = this.mSharedElementSourceNames;
        backStackRecord0.mSharedElementTargetNames = this.mSharedElementTargetNames;
        backStackRecord0.mReorderingAllowed = this.mReorderingAllowed;
    }

    public BackStackRecord instantiate(FragmentManager fragmentManager0) {
        BackStackRecord backStackRecord0 = new BackStackRecord(fragmentManager0);
        this.fillInBackStackRecord(backStackRecord0);
        backStackRecord0.mIndex = this.mIndex;
        for(int v = 0; v < this.mFragmentWhos.size(); ++v) {
            String s = (String)this.mFragmentWhos.get(v);
            if(s != null) {
                Op fragmentTransaction$Op0 = (Op)backStackRecord0.mOps.get(v);
                fragmentTransaction$Op0.mFragment = fragmentManager0.findActiveFragment(s);
            }
        }
        backStackRecord0.bumpBackStackNesting(1);
        return backStackRecord0;
    }

    public BackStackRecord instantiate(FragmentManager fragmentManager0, Map map0) {
        BackStackRecord backStackRecord0 = new BackStackRecord(fragmentManager0);
        this.fillInBackStackRecord(backStackRecord0);
        for(int v = 0; v < this.mFragmentWhos.size(); ++v) {
            String s = (String)this.mFragmentWhos.get(v);
            if(s != null) {
                Fragment fragment0 = (Fragment)map0.get(s);
                if(fragment0 == null) {
                    throw new IllegalStateException("Restoring FragmentTransaction " + this.mName + " failed due to missing saved state for Fragment (" + s + ")");
                }
                ((Op)backStackRecord0.mOps.get(v)).mFragment = fragment0;
                continue;
            }
        }
        return backStackRecord0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeIntArray(this.mOps);
        parcel0.writeStringList(this.mFragmentWhos);
        parcel0.writeIntArray(this.mOldMaxLifecycleStates);
        parcel0.writeIntArray(this.mCurrentMaxLifecycleStates);
        parcel0.writeInt(this.mTransition);
        parcel0.writeString(this.mName);
        parcel0.writeInt(this.mIndex);
        parcel0.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel0, 0);
        parcel0.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel0, 0);
        parcel0.writeStringList(this.mSharedElementSourceNames);
        parcel0.writeStringList(this.mSharedElementTargetNames);
        parcel0.writeInt(((int)this.mReorderingAllowed));
    }
}

