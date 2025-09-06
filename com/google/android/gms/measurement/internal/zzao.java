package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class zzao extends zzgx {
    private long zza;
    private String zzb;
    private AccountManager zzc;
    private Boolean zzd;
    private long zze;

    zzao(zzgd zzgd0) {
        super(zzgd0);
    }

    final long zza() {
        this.zzg();
        return this.zze;
    }

    public final long zzb() {
        this.zzv();
        return this.zza;
    }

    public final String zzc() {
        this.zzv();
        return this.zzb;
    }

    final void zzd() {
        this.zzg();
        this.zzd = null;
        this.zze = 0L;
    }

    final boolean zze() {
        this.zzg();
        long v = this.zzt.zzax().currentTimeMillis();
        if(v - this.zze > 86400000L) {
            this.zzd = null;
        }
        Boolean boolean0 = this.zzd;
        if(boolean0 == null) {
            if(ContextCompat.checkSelfPermission(this.zzt.zzaw(), "android.permission.GET_ACCOUNTS") != 0) {
                this.zzt.zzaA().zzm().zza("Permission error checking for dasher/unicorn accounts");
                this.zze = v;
                this.zzd = Boolean.FALSE;
                return false;
            }
            if(this.zzc == null) {
                this.zzc = AccountManager.get(this.zzt.zzaw());
            }
            try {
                Account[] arr_account = (Account[])this.zzc.getAccountsByTypeAndFeatures("com.google", new String[]{"service_HOSTED"}, null, null).getResult();
                if(arr_account != null && arr_account.length > 0) {
                    this.zzd = Boolean.TRUE;
                    this.zze = v;
                    return true;
                }
                Account[] arr_account1 = (Account[])this.zzc.getAccountsByTypeAndFeatures("com.google", new String[]{"service_uca"}, null, null).getResult();
                if(arr_account1 != null && arr_account1.length > 0) {
                    this.zzd = Boolean.TRUE;
                    this.zze = v;
                    return true;
                }
            }
            catch(AuthenticatorException | IOException | OperationCanceledException authenticatorException0) {
                this.zzt.zzaA().zzh().zzb("Exception checking account types", authenticatorException0);
            }
            this.zze = v;
            this.zzd = Boolean.FALSE;
            return false;
        }
        return boolean0.booleanValue();
    }

    @Override  // com.google.android.gms.measurement.internal.zzgx
    protected final boolean zzf() {
        Calendar calendar0 = Calendar.getInstance();
        this.zza = TimeUnit.MINUTES.convert(((long)(calendar0.get(15) + calendar0.get(16))), TimeUnit.MILLISECONDS);
        Locale locale0 = Locale.getDefault();
        this.zzb = locale0.getLanguage().toLowerCase(Locale.ENGLISH) + "-" + locale0.getCountry().toLowerCase(Locale.ENGLISH);
        return false;
    }
}

