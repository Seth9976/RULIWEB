package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.collection.ArrayMap;

class VersionedParcelParcel extends VersionedParcel {
    private static final boolean DEBUG = false;
    private static final String TAG = "VersionedParcelParcel";
    private int mCurrentField;
    private final int mEnd;
    private int mFieldId;
    private int mNextRead;
    private final int mOffset;
    private final Parcel mParcel;
    private final SparseIntArray mPositionLookup;
    private final String mPrefix;

    VersionedParcelParcel(Parcel parcel0) {
        this(parcel0, parcel0.dataPosition(), parcel0.dataSize(), "", new ArrayMap(), new ArrayMap(), new ArrayMap());
    }

    private VersionedParcelParcel(Parcel parcel0, int v, int v1, String s, ArrayMap arrayMap0, ArrayMap arrayMap1, ArrayMap arrayMap2) {
        super(arrayMap0, arrayMap1, arrayMap2);
        this.mPositionLookup = new SparseIntArray();
        this.mCurrentField = -1;
        this.mFieldId = -1;
        this.mParcel = parcel0;
        this.mOffset = v;
        this.mEnd = v1;
        this.mNextRead = v;
        this.mPrefix = s;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void closeField() {
        int v = this.mCurrentField;
        if(v >= 0) {
            int v1 = this.mPositionLookup.get(v);
            int v2 = this.mParcel.dataPosition();
            this.mParcel.setDataPosition(v1);
            this.mParcel.writeInt(v2 - v1);
            this.mParcel.setDataPosition(v2);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    protected VersionedParcel createSubParcel() {
        int v = this.mParcel.dataPosition();
        return new VersionedParcelParcel(this.mParcel, v, (this.mNextRead == this.mOffset ? this.mEnd : this.mNextRead), this.mPrefix + "  ", this.mReadCache, this.mWriteCache, this.mParcelizerCache);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public boolean readBoolean() {
        return this.mParcel.readInt() != 0;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public Bundle readBundle() {
        ClassLoader classLoader0 = this.getClass().getClassLoader();
        return this.mParcel.readBundle(classLoader0);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public byte[] readByteArray() {
        int v = this.mParcel.readInt();
        if(v < 0) {
            return null;
        }
        byte[] arr_b = new byte[v];
        this.mParcel.readByteArray(arr_b);
        return arr_b;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    protected CharSequence readCharSequence() {
        return (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.mParcel);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public double readDouble() {
        return this.mParcel.readDouble();
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public boolean readField(int v) {
        while(this.mNextRead < this.mEnd) {
            int v1 = this.mFieldId;
            if(v1 == v) {
                return true;
            }
            if(String.valueOf(v1).compareTo(String.valueOf(v)) > 0) {
                return false;
            }
            this.mParcel.setDataPosition(this.mNextRead);
            int v2 = this.mParcel.readInt();
            this.mFieldId = this.mParcel.readInt();
            this.mNextRead += v2;
        }
        return this.mFieldId == v;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public float readFloat() {
        return this.mParcel.readFloat();
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public int readInt() {
        return this.mParcel.readInt();
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public long readLong() {
        return this.mParcel.readLong();
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public Parcelable readParcelable() {
        ClassLoader classLoader0 = this.getClass().getClassLoader();
        return this.mParcel.readParcelable(classLoader0);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public String readString() {
        return this.mParcel.readString();
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public IBinder readStrongBinder() {
        return this.mParcel.readStrongBinder();
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void setOutputField(int v) {
        this.closeField();
        this.mCurrentField = v;
        int v1 = this.mParcel.dataPosition();
        this.mPositionLookup.put(v, v1);
        this.writeInt(0);
        this.writeInt(v);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeBoolean(boolean z) {
        this.mParcel.writeInt(((int)z));
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeBundle(Bundle bundle0) {
        this.mParcel.writeBundle(bundle0);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeByteArray(byte[] arr_b) {
        if(arr_b != null) {
            this.mParcel.writeInt(arr_b.length);
            this.mParcel.writeByteArray(arr_b);
            return;
        }
        this.mParcel.writeInt(-1);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeByteArray(byte[] arr_b, int v, int v1) {
        if(arr_b != null) {
            this.mParcel.writeInt(arr_b.length);
            this.mParcel.writeByteArray(arr_b, v, v1);
            return;
        }
        this.mParcel.writeInt(-1);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    protected void writeCharSequence(CharSequence charSequence0) {
        TextUtils.writeToParcel(charSequence0, this.mParcel, 0);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeDouble(double f) {
        this.mParcel.writeDouble(f);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeFloat(float f) {
        this.mParcel.writeFloat(f);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeInt(int v) {
        this.mParcel.writeInt(v);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeLong(long v) {
        this.mParcel.writeLong(v);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeParcelable(Parcelable parcelable0) {
        this.mParcel.writeParcelable(parcelable0, 0);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeString(String s) {
        this.mParcel.writeString(s);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeStrongBinder(IBinder iBinder0) {
        this.mParcel.writeStrongBinder(iBinder0);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeStrongInterface(IInterface iInterface0) {
        this.mParcel.writeStrongInterface(iInterface0);
    }
}

