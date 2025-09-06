package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BackStackState implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    final List mFragments;
    final List mTransactions;

    static {
        BackStackState.CREATOR = new Parcelable.Creator() {
            public BackStackState createFromParcel(Parcel parcel0) {
                return new BackStackState(parcel0);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public BackStackState[] newArray(int v) {
                return new BackStackState[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    BackStackState(Parcel parcel0) {
        this.mFragments = parcel0.createStringArrayList();
        this.mTransactions = parcel0.createTypedArrayList(BackStackRecordState.CREATOR);
    }

    BackStackState(List list0, List list1) {
        this.mFragments = list0;
        this.mTransactions = list1;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    List instantiate(FragmentManager fragmentManager0, Map map0) {
        HashMap hashMap0 = new HashMap(this.mFragments.size());
        for(Object object0: this.mFragments) {
            String s = (String)object0;
            Fragment fragment0 = (Fragment)map0.get(s);
            if(fragment0 == null) {
                FragmentState fragmentState0 = fragmentManager0.getFragmentStore().setSavedState(s, null);
                if(fragmentState0 != null) {
                    Fragment fragment1 = fragmentState0.instantiate(fragmentManager0.getFragmentFactory(), fragmentManager0.getHost().getContext().getClassLoader());
                    hashMap0.put(fragment1.mWho, fragment1);
                }
            }
            else {
                hashMap0.put(fragment0.mWho, fragment0);
            }
        }
        List list0 = new ArrayList();
        for(Object object1: this.mTransactions) {
            ((ArrayList)list0).add(((BackStackRecordState)object1).instantiate(fragmentManager0, hashMap0));
        }
        return list0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeStringList(this.mFragments);
        parcel0.writeTypedList(this.mTransactions);
    }
}

