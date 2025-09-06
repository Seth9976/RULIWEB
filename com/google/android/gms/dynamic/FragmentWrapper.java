package com.google.android.gms.dynamic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;

public final class FragmentWrapper extends Stub {
    private final Fragment zza;

    private FragmentWrapper(Fragment fragment0) {
        this.zza = fragment0;
    }

    public static FragmentWrapper wrap(Fragment fragment0) {
        return fragment0 == null ? null : new FragmentWrapper(fragment0);
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzA() {
        return this.zza.isVisible();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final int zzb() {
        return this.zza.getId();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final int zzc() {
        return this.zza.getTargetRequestCode();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final Bundle zzd() {
        return this.zza.getArguments();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final IFragmentWrapper zze() {
        return FragmentWrapper.wrap(this.zza.getParentFragment());
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final IFragmentWrapper zzf() {
        return FragmentWrapper.wrap(this.zza.getTargetFragment());
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final IObjectWrapper zzg() {
        return ObjectWrapper.wrap(this.zza.getActivity());
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final IObjectWrapper zzh() {
        return ObjectWrapper.wrap(this.zza.getResources());
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final IObjectWrapper zzi() {
        return ObjectWrapper.wrap(this.zza.getView());
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final String zzj() {
        return this.zza.getTag();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzk(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
        Preconditions.checkNotNull(view0);
        this.zza.registerForContextMenu(view0);
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzl(boolean z) {
        this.zza.setHasOptionsMenu(z);
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzm(boolean z) {
        this.zza.setMenuVisibility(z);
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzn(boolean z) {
        this.zza.setRetainInstance(z);
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzo(boolean z) {
        this.zza.setUserVisibleHint(z);
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzp(Intent intent0) {
        this.zza.startActivity(intent0);
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzq(Intent intent0, int v) {
        this.zza.startActivityForResult(intent0, v);
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzr(IObjectWrapper iObjectWrapper0) {
        View view0 = (View)ObjectWrapper.unwrap(iObjectWrapper0);
        Preconditions.checkNotNull(view0);
        this.zza.unregisterForContextMenu(view0);
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzs() {
        return this.zza.getRetainInstance();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzt() {
        return this.zza.getUserVisibleHint();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzu() {
        return this.zza.isAdded();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzv() {
        return this.zza.isDetached();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzw() {
        return this.zza.isHidden();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzx() {
        return this.zza.isInLayout();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzy() {
        return this.zza.isRemoving();
    }

    @Override  // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzz() {
        return this.zza.isResumed();
    }
}

