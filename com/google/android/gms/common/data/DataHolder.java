package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.sqlite.CursorWrapper;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import jeb.synthetic.TWR;

public final class DataHolder extends AbstractSafeParcelable implements Closeable, AutoCloseable {
    public static class Builder {
        private final String[] zaa;
        private final ArrayList zab;
        private final HashMap zac;

        Builder(String[] arr_s, String s, zac zac0) {
            this.zaa = (String[])Preconditions.checkNotNull(arr_s);
            this.zab = new ArrayList();
            this.zac = new HashMap();
        }

        public DataHolder build(int v) {
            return new DataHolder(this, v, null, null);
        }

        public DataHolder build(int v, Bundle bundle0) {
            return new DataHolder(this, v, bundle0, -1, null);
        }

        public Builder withRow(ContentValues contentValues0) {
            Asserts.checkNotNull(contentValues0);
            HashMap hashMap0 = new HashMap(contentValues0.size());
            for(Object object0: contentValues0.valueSet()) {
                hashMap0.put(((String)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
            }
            return this.zaa(hashMap0);
        }

        public Builder zaa(HashMap hashMap0) {
            Asserts.checkNotNull(hashMap0);
            this.zab.add(hashMap0);
            return this;
        }

        static ArrayList zab(Builder dataHolder$Builder0) {
            return dataHolder$Builder0.zab;
        }

        static String[] zac(Builder dataHolder$Builder0) {
            return dataHolder$Builder0.zaa;
        }
    }

    public static final Parcelable.Creator CREATOR;
    final int zaa;
    Bundle zab;
    int[] zac;
    int zad;
    boolean zae;
    private static final Builder zaf;
    private final String[] zag;
    private final CursorWindow[] zah;
    private final int zai;
    private final Bundle zaj;
    private boolean zak;

    static {
        DataHolder.CREATOR = new zaf();
        DataHolder.zaf = new zab(new String[0], null);
    }

    DataHolder(int v, String[] arr_s, CursorWindow[] arr_cursorWindow, int v1, Bundle bundle0) {
        this.zae = false;
        this.zak = true;
        this.zaa = v;
        this.zag = arr_s;
        this.zah = arr_cursorWindow;
        this.zai = v1;
        this.zaj = bundle0;
    }

    public DataHolder(Cursor cursor0, int v, Bundle bundle0) {
        int v2;
        CursorWrapper cursorWrapper0 = new CursorWrapper(cursor0);
        String[] arr_s = cursorWrapper0.getColumnNames();
        ArrayList arrayList0 = new ArrayList();
        try {
            int v1 = cursorWrapper0.getCount();
            CursorWindow cursorWindow0 = cursorWrapper0.getWindow();
            if(cursorWindow0 == null || cursorWindow0.getStartPosition() != 0) {
                v2 = 0;
            }
            else {
                cursorWindow0.acquireReference();
                cursorWrapper0.setWindow(null);
                arrayList0.add(cursorWindow0);
                v2 = cursorWindow0.getNumRows();
            }
            while(v2 < v1 && cursorWrapper0.moveToPosition(v2)) {
                CursorWindow cursorWindow1 = cursorWrapper0.getWindow();
                if(cursorWindow1 == null) {
                    cursorWindow1 = new CursorWindow(false);
                    cursorWindow1.setStartPosition(v2);
                    cursorWrapper0.fillWindow(v2, cursorWindow1);
                }
                else {
                    cursorWindow1.acquireReference();
                    cursorWrapper0.setWindow(null);
                }
                if(cursorWindow1.getNumRows() == 0) {
                    break;
                }
                arrayList0.add(cursorWindow1);
                v2 = cursorWindow1.getStartPosition() + cursorWindow1.getNumRows();
            }
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(cursorWrapper0, throwable0);
            throw throwable0;
        }
        cursorWrapper0.close();
        this(arr_s, ((CursorWindow[])arrayList0.toArray(new CursorWindow[arrayList0.size()])), v, bundle0);
    }

    private DataHolder(Builder dataHolder$Builder0, int v, Bundle bundle0) {
        this(Builder.zac(dataHolder$Builder0), DataHolder.zaf(dataHolder$Builder0, -1), v, null);
    }

    DataHolder(Builder dataHolder$Builder0, int v, Bundle bundle0, int v1, zae zae0) {
        this(Builder.zac(dataHolder$Builder0), DataHolder.zaf(dataHolder$Builder0, -1), v, bundle0);
    }

    DataHolder(Builder dataHolder$Builder0, int v, Bundle bundle0, zae zae0) {
        this(dataHolder$Builder0, v, null);
    }

    public DataHolder(String[] arr_s, CursorWindow[] arr_cursorWindow, int v, Bundle bundle0) {
        this.zae = false;
        this.zak = true;
        this.zaa = 1;
        this.zag = (String[])Preconditions.checkNotNull(arr_s);
        this.zah = (CursorWindow[])Preconditions.checkNotNull(arr_cursorWindow);
        this.zai = v;
        this.zaj = bundle0;
        this.zad();
    }

    public static Builder builder(String[] arr_s) {
        return new Builder(arr_s, null, null);
    }

    @Override
    public void close() {
        synchronized(this) {
            if(!this.zae) {
                this.zae = true;
                for(int v1 = 0; true; ++v1) {
                    CursorWindow[] arr_cursorWindow = this.zah;
                    if(v1 >= arr_cursorWindow.length) {
                        break;
                    }
                    arr_cursorWindow[v1].close();
                }
            }
        }
    }

    public static DataHolder empty(int v) {
        return new DataHolder(DataHolder.zaf, v, null);
    }

    @Override
    protected final void finalize() throws Throwable {
        try {
            if(this.zak && this.zah.length > 0 && !this.isClosed()) {
                this.close();
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: " + this.toString() + ")");
            }
        }
        finally {
            super.finalize();
        }
    }

    public boolean getBoolean(String s, int v, int v1) {
        this.zae(s, v);
        long v2 = this.zah[v1].getLong(v, this.zab.getInt(s));
        v2.getClass();
        return v2 == 1L;
    }

    public byte[] getByteArray(String s, int v, int v1) {
        this.zae(s, v);
        return this.zah[v1].getBlob(v, this.zab.getInt(s));
    }

    public int getCount() {
        return this.zad;
    }

    public int getInteger(String s, int v, int v1) {
        this.zae(s, v);
        return this.zah[v1].getInt(v, this.zab.getInt(s));
    }

    public long getLong(String s, int v, int v1) {
        this.zae(s, v);
        return this.zah[v1].getLong(v, this.zab.getInt(s));
    }

    public Bundle getMetadata() {
        return this.zaj;
    }

    public int getStatusCode() {
        return this.zai;
    }

    public String getString(String s, int v, int v1) {
        this.zae(s, v);
        return this.zah[v1].getString(v, this.zab.getInt(s));
    }

    public int getWindowIndex(int v) {
        int v2;
        int v1 = 0;
        Preconditions.checkState(v >= 0 && v < this.zad);
        while(true) {
            int[] arr_v = this.zac;
            v2 = arr_v.length;
            if(v1 >= v2) {
                break;
            }
            if(v < arr_v[v1]) {
                --v1;
                return v1 == v2 ? v1 - 1 : v1;
            }
            ++v1;
        }
        return v1 == v2 ? v1 - 1 : v1;
    }

    public boolean hasColumn(String s) {
        return this.zab.containsKey(s);
    }

    public boolean hasNull(String s, int v, int v1) {
        this.zae(s, v);
        return this.zah[v1].isNull(v, this.zab.getInt(s));
    }

    public boolean isClosed() {
        synchronized(this) {
        }
        return this.zae;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeStringArray(parcel0, 1, this.zag, false);
        SafeParcelWriter.writeTypedArray(parcel0, 2, this.zah, v, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.getStatusCode());
        SafeParcelWriter.writeBundle(parcel0, 4, this.getMetadata(), false);
        SafeParcelWriter.writeInt(parcel0, 1000, this.zaa);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
        if((v & 1) != 0) {
            this.close();
        }
    }

    public final double zaa(String s, int v, int v1) {
        this.zae(s, v);
        return this.zah[v1].getDouble(v, this.zab.getInt(s));
    }

    public final float zab(String s, int v, int v1) {
        this.zae(s, v);
        return this.zah[v1].getFloat(v, this.zab.getInt(s));
    }

    public final void zac(String s, int v, int v1, CharArrayBuffer charArrayBuffer0) {
        this.zae(s, v);
        this.zah[v1].copyStringToBuffer(v, this.zab.getInt(s), charArrayBuffer0);
    }

    public final void zad() {
        this.zab = new Bundle();
        for(int v1 = 0; true; ++v1) {
            String[] arr_s = this.zag;
            if(v1 >= arr_s.length) {
                break;
            }
            this.zab.putInt(arr_s[v1], v1);
        }
        this.zac = new int[this.zah.length];
        int v2 = 0;
        for(int v = 0; true; ++v) {
            CursorWindow[] arr_cursorWindow = this.zah;
            if(v >= arr_cursorWindow.length) {
                break;
            }
            this.zac[v] = v2;
            int v3 = arr_cursorWindow[v].getStartPosition();
            v2 += this.zah[v].getNumRows() - (v2 - v3);
        }
        this.zad = v2;
    }

    private final void zae(String s, int v) {
        if(this.zab == null || !this.zab.containsKey(s)) {
            throw new IllegalArgumentException("No such column: " + s);
        }
        if(this.isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if(v < 0 || v >= this.zad) {
            throw new CursorIndexOutOfBoundsException(v, this.zad);
        }
    }

    private static CursorWindow[] zaf(Builder dataHolder$Builder0, int v) {
        if(Builder.zac(dataHolder$Builder0).length == 0) {
            return new CursorWindow[0];
        }
        ArrayList arrayList0 = Builder.zab(dataHolder$Builder0);
        int v2 = arrayList0.size();
        CursorWindow cursorWindow0 = new CursorWindow(false);
        ArrayList arrayList1 = new ArrayList();
        arrayList1.add(cursorWindow0);
        cursorWindow0.setNumColumns(Builder.zac(dataHolder$Builder0).length);
        int v3 = 0;
        boolean z = false;
        while(v3 < v2) {
            try {
                if(!cursorWindow0.allocRow()) {
                    Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + v3 + ")");
                    cursorWindow0 = new CursorWindow(false);
                    cursorWindow0.setStartPosition(v3);
                    cursorWindow0.setNumColumns(Builder.zac(dataHolder$Builder0).length);
                    arrayList1.add(cursorWindow0);
                    if(!cursorWindow0.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList1.remove(cursorWindow0);
                        return (CursorWindow[])arrayList1.toArray(new CursorWindow[arrayList1.size()]);
                    }
                }
                Map map0 = (Map)arrayList0.get(v3);
                int v4 = 0;
                boolean z1 = true;
                while(v4 < Builder.zac(dataHolder$Builder0).length) {
                    if(!z1) {
                        goto label_56;
                    }
                    String s = Builder.zac(dataHolder$Builder0)[v4];
                    Object object0 = map0.get(s);
                    if(object0 == null) {
                        z1 = cursorWindow0.putNull(v3, v4);
                    }
                    else if(object0 instanceof String) {
                        z1 = cursorWindow0.putString(((String)object0), v3, v4);
                    }
                    else if(object0 instanceof Long) {
                        z1 = cursorWindow0.putLong(((long)(((Long)object0))), v3, v4);
                    }
                    else if(object0 instanceof Integer) {
                        z1 = cursorWindow0.putLong(((long)(((int)(((Integer)object0))))), v3, v4);
                    }
                    else if(object0 instanceof Boolean) {
                        z1 = cursorWindow0.putLong((((Boolean)object0).booleanValue() ? 1L : 0L), v3, v4);
                    }
                    else if(object0 instanceof byte[]) {
                        z1 = cursorWindow0.putBlob(((byte[])object0), v3, v4);
                    }
                    else if(object0 instanceof Double) {
                        z1 = cursorWindow0.putDouble(((double)(((Double)object0))), v3, v4);
                    }
                    else {
                        if(!(object0 instanceof Float)) {
                            throw new IllegalArgumentException("Unsupported object for column " + s + ": " + object0.toString());
                        }
                        z1 = cursorWindow0.putDouble(((double)(((float)(((Float)object0))))), v3, v4);
                    }
                    ++v4;
                }
                if(z1) {
                    z = false;
                }
                else {
                label_56:
                    if(z) {
                        throw new zad("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                    }
                    Log.d("DataHolder", "Couldn\'t populate window data for row " + v3 + " - allocating new window.");
                    cursorWindow0.freeLastRow();
                    cursorWindow0 = new CursorWindow(false);
                    cursorWindow0.setStartPosition(v3);
                    cursorWindow0.setNumColumns(Builder.zac(dataHolder$Builder0).length);
                    arrayList1.add(cursorWindow0);
                    --v3;
                    z = true;
                }
                ++v3;
            }
            catch(RuntimeException runtimeException0) {
                int v5 = arrayList1.size();
                for(int v1 = 0; v1 < v5; ++v1) {
                    ((CursorWindow)arrayList1.get(v1)).close();
                }
                throw runtimeException0;
            }
        }
        return (CursorWindow[])arrayList1.toArray(new CursorWindow[arrayList1.size()]);
    }
}

