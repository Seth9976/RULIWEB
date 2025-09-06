package com.google.android.gms.common.api;

final class zab implements StatusListener {
    final Batch zaa;

    zab(Batch batch0) {
        this.zaa = batch0;
        super();
    }

    @Override  // com.google.android.gms.common.api.PendingResult$StatusListener
    public final void onComplete(Status status0) {
        synchronized(this.zaa.zai) {
            if(this.zaa.isCanceled()) {
                return;
            }
            if(status0.isCanceled()) {
                Batch.zad(this.zaa, true);
            }
            else if(!status0.isSuccess()) {
                Batch.zac(this.zaa, true);
            }
            --this.zaa.zae;
            Batch batch0 = this.zaa;
            if(batch0.zae == 0) {
                if(batch0.zag) {
                    batch0.super.cancel();
                }
                else {
                    BatchResult batchResult0 = new BatchResult((batch0.zaf ? new Status(13) : Status.RESULT_SUCCESS), this.zaa.zah);
                    this.zaa.setResult(batchResult0);
                }
            }
        }
    }
}

