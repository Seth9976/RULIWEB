package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zza;

    public CursorWrapper(Cursor cursor0) {
        super(cursor0);
        for(int v = 0; v < 10 && cursor0 instanceof android.database.CursorWrapper; ++v) {
            cursor0 = ((android.database.CursorWrapper)cursor0).getWrappedCursor();
        }
        if(!(cursor0 instanceof AbstractWindowedCursor)) {
            throw new IllegalArgumentException("Unknown type: " + cursor0.getClass().getName());
        }
        this.zza = (AbstractWindowedCursor)cursor0;
    }

    @Override  // android.database.CrossProcessCursor
    public void fillWindow(int v, CursorWindow cursorWindow0) {
        this.zza.fillWindow(v, cursorWindow0);
    }

    @Override  // android.database.CrossProcessCursor
    @ResultIgnorabilityUnspecified
    public CursorWindow getWindow() {
        return this.zza.getWindow();
    }

    @Override  // android.database.CursorWrapper
    public final Cursor getWrappedCursor() {
        return this.zza;
    }

    @Override  // android.database.CrossProcessCursor
    @ResultIgnorabilityUnspecified
    public final boolean onMove(int v, int v1) {
        return this.zza.onMove(v, v1);
    }

    public void setWindow(CursorWindow cursorWindow0) {
        this.zza.setWindow(cursorWindow0);
    }
}

