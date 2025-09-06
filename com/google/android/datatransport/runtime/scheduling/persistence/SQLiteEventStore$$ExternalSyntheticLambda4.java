package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

public final class SQLiteEventStore..ExternalSyntheticLambda4 implements Function {
    public final SQLiteEventStore f$0;
    public final EventInternal f$1;
    public final TransportContext f$2;

    public SQLiteEventStore..ExternalSyntheticLambda4(SQLiteEventStore sQLiteEventStore0, EventInternal eventInternal0, TransportContext transportContext0) {
        this.f$0 = sQLiteEventStore0;
        this.f$1 = eventInternal0;
        this.f$2 = transportContext0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return this.f$0.lambda$persist$1$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(this.f$1, this.f$2, ((SQLiteDatabase)object0));
    }
}

