package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends BasePendingResult {
    public static final class Builder {
        private final List zaa;
        private final GoogleApiClient zab;

        public Builder(GoogleApiClient googleApiClient0) {
            this.zaa = new ArrayList();
            this.zab = googleApiClient0;
        }

        @ResultIgnorabilityUnspecified
        public BatchResultToken add(PendingResult pendingResult0) {
            BatchResultToken batchResultToken0 = new BatchResultToken(this.zaa.size());
            this.zaa.add(pendingResult0);
            return batchResultToken0;
        }

        public Batch build() {
            return new Batch(this.zaa, this.zab, null);
        }
    }

    private int zae;
    private boolean zaf;
    private boolean zag;
    private final PendingResult[] zah;
    private final Object zai;

    Batch(List list0, GoogleApiClient googleApiClient0, zac zac0) {
        super(googleApiClient0);
        this.zai = new Object();
        int v = list0.size();
        this.zae = v;
        PendingResult[] arr_pendingResult = new PendingResult[v];
        this.zah = arr_pendingResult;
        if(!list0.isEmpty()) {
            for(int v1 = 0; v1 < list0.size(); ++v1) {
                PendingResult pendingResult0 = (PendingResult)list0.get(v1);
                this.zah[v1] = pendingResult0;
                pendingResult0.addStatusListener(new zab(this));
            }
            return;
        }
        this.setResult(new BatchResult(Status.RESULT_SUCCESS, arr_pendingResult));
    }

    @Override  // com.google.android.gms.common.api.internal.BasePendingResult
    public void cancel() {
        super.cancel();
        for(int v = 0; true; ++v) {
            PendingResult[] arr_pendingResult = this.zah;
            if(v >= arr_pendingResult.length) {
                break;
            }
            arr_pendingResult[v].cancel();
        }
    }

    public BatchResult createFailedResult(Status status0) {
        return new BatchResult(status0, this.zah);
    }

    @Override  // com.google.android.gms.common.api.internal.BasePendingResult
    public final Result createFailedResult(Status status0) {
        return this.createFailedResult(status0);
    }

    static void zac(Batch batch0, boolean z) {
        batch0.zaf = true;
    }

    static void zad(Batch batch0, boolean z) {
        batch0.zag = true;
    }
}

