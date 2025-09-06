package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbn;
import com.google.android.gms.internal.measurement.zzbo;
import java.util.List;

public abstract class zzei extends zzbn implements zzej {
    public zzei() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override  // com.google.android.gms.internal.measurement.zzbn
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                zzau zzau0 = (zzau)zzbo.zza(parcel0, zzau.CREATOR);
                zzq zzq0 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                this.zzk(zzau0, zzq0);
                parcel1.writeNoException();
                return true;
            }
            case 2: {
                zzlk zzlk0 = (zzlk)zzbo.zza(parcel0, zzlk.CREATOR);
                zzq zzq1 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                this.zzt(zzlk0, zzq1);
                parcel1.writeNoException();
                return true;
            }
            case 4: {
                zzq zzq2 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                this.zzj(zzq2);
                parcel1.writeNoException();
                return true;
            }
            case 5: {
                zzau zzau1 = (zzau)zzbo.zza(parcel0, zzau.CREATOR);
                String s = parcel0.readString();
                String s1 = parcel0.readString();
                zzbo.zzc(parcel0);
                this.zzl(zzau1, s, s1);
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                zzq zzq3 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                this.zzs(zzq3);
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                zzq zzq4 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                boolean z = zzbo.zzf(parcel0);
                zzbo.zzc(parcel0);
                List list0 = this.zze(zzq4, z);
                parcel1.writeNoException();
                parcel1.writeTypedList(list0);
                return true;
            }
            case 9: {
                zzau zzau2 = (zzau)zzbo.zza(parcel0, zzau.CREATOR);
                String s2 = parcel0.readString();
                zzbo.zzc(parcel0);
                byte[] arr_b = this.zzu(zzau2, s2);
                parcel1.writeNoException();
                parcel1.writeByteArray(arr_b);
                return true;
            }
            case 10: {
                long v2 = parcel0.readLong();
                String s3 = parcel0.readString();
                String s4 = parcel0.readString();
                String s5 = parcel0.readString();
                zzbo.zzc(parcel0);
                this.zzq(v2, s3, s4, s5);
                parcel1.writeNoException();
                return true;
            }
            case 11: {
                zzq zzq5 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                String s6 = this.zzd(zzq5);
                parcel1.writeNoException();
                parcel1.writeString(s6);
                return true;
            }
            case 12: {
                zzac zzac0 = (zzac)zzbo.zza(parcel0, zzac.CREATOR);
                zzq zzq6 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                this.zzn(zzac0, zzq6);
                parcel1.writeNoException();
                return true;
            }
            case 13: {
                zzac zzac1 = (zzac)zzbo.zza(parcel0, zzac.CREATOR);
                zzbo.zzc(parcel0);
                this.zzo(zzac1);
                parcel1.writeNoException();
                return true;
            }
            case 14: {
                String s7 = parcel0.readString();
                String s8 = parcel0.readString();
                boolean z1 = zzbo.zzf(parcel0);
                zzq zzq7 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                List list1 = this.zzh(s7, s8, z1, zzq7);
                parcel1.writeNoException();
                parcel1.writeTypedList(list1);
                return true;
            }
            case 15: {
                String s9 = parcel0.readString();
                String s10 = parcel0.readString();
                String s11 = parcel0.readString();
                boolean z2 = zzbo.zzf(parcel0);
                zzbo.zzc(parcel0);
                List list2 = this.zzi(s9, s10, s11, z2);
                parcel1.writeNoException();
                parcel1.writeTypedList(list2);
                return true;
            }
            case 16: {
                String s12 = parcel0.readString();
                String s13 = parcel0.readString();
                zzq zzq8 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                List list3 = this.zzf(s12, s13, zzq8);
                parcel1.writeNoException();
                parcel1.writeTypedList(list3);
                return true;
            }
            case 17: {
                String s14 = parcel0.readString();
                String s15 = parcel0.readString();
                String s16 = parcel0.readString();
                zzbo.zzc(parcel0);
                List list4 = this.zzg(s14, s15, s16);
                parcel1.writeNoException();
                parcel1.writeTypedList(list4);
                return true;
            }
            case 18: {
                zzq zzq9 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                this.zzm(zzq9);
                parcel1.writeNoException();
                return true;
            }
            case 19: {
                Bundle bundle0 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
                zzq zzq10 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                this.zzr(bundle0, zzq10);
                parcel1.writeNoException();
                return true;
            }
            case 20: {
                zzq zzq11 = (zzq)zzbo.zza(parcel0, zzq.CREATOR);
                zzbo.zzc(parcel0);
                this.zzp(zzq11);
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

