package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.List;

public final class SQLiteEventStore..ExternalSyntheticLambda1 implements Function {
    public final SQLiteEventStore f$0;
    public final List f$1;
    public final TransportContext f$2;

    public SQLiteEventStore..ExternalSyntheticLambda1(SQLiteEventStore sQLiteEventStore0, List list0, TransportContext transportContext0) {
        this.f$0 = sQLiteEventStore0;
        this.f$1 = list0;
        this.f$2 = transportContext0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return this.f$0.lambda$loadEvents$14$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(this.f$1, this.f$2, ((Cursor)object0));
    }
}

