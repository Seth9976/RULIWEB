package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fido.zzam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UvmEntries extends AbstractSafeParcelable {
    public static final class Builder {
        private final List zza;

        public Builder() {
            this.zza = new ArrayList();
        }

        public Builder addAll(List list0) {
            zzam.zzc(this.zza.size() + list0.size() <= 3);
            this.zza.addAll(list0);
            return this;
        }

        public Builder addUvmEntry(UvmEntry uvmEntry0) {
            if(this.zza.size() >= 3) {
                throw new IllegalStateException();
            }
            this.zza.add(uvmEntry0);
            return this;
        }

        public UvmEntries build() {
            return new UvmEntries(this.zza);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final List zza;

    static {
        UvmEntries.CREATOR = new zzaz();
    }

    UvmEntries(List list0) {
        this.zza = list0;
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof UvmEntries)) {
            return false;
        }
        List list0 = this.zza;
        if(list0 == null && ((UvmEntries)object0).zza == null) {
            return true;
        }
        if(list0 != null) {
            List list1 = ((UvmEntries)object0).zza;
            return list1 != null && list0.containsAll(list1) && ((UvmEntries)object0).zza.containsAll(this.zza);
        }
        return false;
    }

    public List getUvmEntryList() {
        return this.zza;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{new HashSet(this.zza)});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeTypedList(parcel0, 1, this.getUvmEntryList(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

