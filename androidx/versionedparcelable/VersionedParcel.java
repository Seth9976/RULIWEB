package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public abstract class VersionedParcel {
    public static class ParcelException extends RuntimeException {
        public ParcelException(Throwable throwable0) {
            super(throwable0);
        }
    }

    private static final int EX_BAD_PARCELABLE = -2;
    private static final int EX_ILLEGAL_ARGUMENT = -3;
    private static final int EX_ILLEGAL_STATE = -5;
    private static final int EX_NETWORK_MAIN_THREAD = -6;
    private static final int EX_NULL_POINTER = -4;
    private static final int EX_PARCELABLE = -9;
    private static final int EX_SECURITY = -1;
    private static final int EX_UNSUPPORTED_OPERATION = -7;
    private static final String TAG = "VersionedParcel";
    private static final int TYPE_BINDER = 5;
    private static final int TYPE_FLOAT = 8;
    private static final int TYPE_INTEGER = 7;
    private static final int TYPE_PARCELABLE = 2;
    private static final int TYPE_SERIALIZABLE = 3;
    private static final int TYPE_STRING = 4;
    private static final int TYPE_VERSIONED_PARCELABLE = 1;
    protected final ArrayMap mParcelizerCache;
    protected final ArrayMap mReadCache;
    protected final ArrayMap mWriteCache;

    public VersionedParcel(ArrayMap arrayMap0, ArrayMap arrayMap1, ArrayMap arrayMap2) {
        this.mReadCache = arrayMap0;
        this.mWriteCache = arrayMap1;
        this.mParcelizerCache = arrayMap2;
    }

    protected abstract void closeField();

    private Exception createException(int v, String s) {
        switch(v) {
            case -9: {
                return (Exception)this.readParcelable();
            }
            case -7: {
                return new UnsupportedOperationException(s);
            }
            case -6: {
                return new NetworkOnMainThreadException();
            }
            case -5: {
                return new IllegalStateException(s);
            }
            case -4: {
                return new NullPointerException(s);
            }
            case -3: {
                return new IllegalArgumentException(s);
            }
            case -2: {
                return new BadParcelableException(s);
            }
            case -1: {
                return new SecurityException(s);
            }
            default: {
                return new RuntimeException("Unknown exception code: " + v + " msg " + s);
            }
        }
    }

    protected abstract VersionedParcel createSubParcel();

    private Class findParcelClass(Class class0) throws ClassNotFoundException {
        Class class1 = (Class)this.mParcelizerCache.get(class0.getName());
        if(class1 == null) {
            class1 = Class.forName(String.format("%s.%sParcelizer", class0.getPackage().getName(), class0.getSimpleName()), false, class0.getClassLoader());
            this.mParcelizerCache.put(class0.getName(), class1);
        }
        return class1;
    }

    private Method getReadMethod(String s) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method0 = (Method)this.mReadCache.get(s);
        if(method0 == null) {
            method0 = Class.forName(s, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
            this.mReadCache.put(s, method0);
        }
        return method0;
    }

    protected static Throwable getRootCause(Throwable throwable0) {
        while(throwable0.getCause() != null) {
            throwable0 = throwable0.getCause();
        }
        return throwable0;
    }

    private int getType(Object object0) {
        if(object0 instanceof String) {
            return 4;
        }
        if(object0 instanceof Parcelable) {
            return 2;
        }
        if(object0 instanceof VersionedParcelable) {
            return 1;
        }
        if(object0 instanceof Serializable) {
            return 3;
        }
        if(object0 instanceof IBinder) {
            return 5;
        }
        if(object0 instanceof Integer) {
            return 7;
        }
        if(!(object0 instanceof Float)) {
            throw new IllegalArgumentException(object0.getClass().getName() + " cannot be VersionedParcelled");
        }
        return 8;
    }

    private Method getWriteMethod(Class class0) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method0 = (Method)this.mWriteCache.get(class0.getName());
        if(method0 == null) {
            method0 = this.findParcelClass(class0).getDeclaredMethod("write", class0, VersionedParcel.class);
            this.mWriteCache.put(class0.getName(), method0);
        }
        return method0;
    }

    public boolean isStream() {
        return false;
    }

    protected Object[] readArray(Object[] arr_object) {
        int v = this.readInt();
        if(v < 0) {
            return null;
        }
        ArrayList arrayList0 = new ArrayList(v);
        if(v != 0) {
            switch(this.readInt()) {
                case 1: {
                    break;
                }
                case 2: {
                    while(v > 0) {
                        arrayList0.add(this.readParcelable());
                        --v;
                    }
                    return arrayList0.toArray(arr_object);
                }
                case 3: {
                    while(v > 0) {
                        arrayList0.add(this.readSerializable());
                        --v;
                    }
                    return arrayList0.toArray(arr_object);
                }
                case 4: {
                    while(v > 0) {
                        arrayList0.add(this.readString());
                        --v;
                    }
                    return arrayList0.toArray(arr_object);
                }
                case 5: {
                    while(v > 0) {
                        arrayList0.add(this.readStrongBinder());
                        --v;
                    }
                    return arrayList0.toArray(arr_object);
                }
                default: {
                    return arrayList0.toArray(arr_object);
                }
            }
            while(v > 0) {
                arrayList0.add(this.readVersionedParcelable());
                --v;
            }
        }
        return arrayList0.toArray(arr_object);
    }

    public Object[] readArray(Object[] arr_object, int v) {
        return this.readField(v) ? this.readArray(arr_object) : arr_object;
    }

    protected abstract boolean readBoolean();

    public boolean readBoolean(boolean z, int v) {
        return this.readField(v) ? this.readBoolean() : z;
    }

    protected boolean[] readBooleanArray() {
        int v = this.readInt();
        if(v < 0) {
            return null;
        }
        boolean[] arr_z = new boolean[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_z[v1] = this.readInt() != 0;
        }
        return arr_z;
    }

    public boolean[] readBooleanArray(boolean[] arr_z, int v) {
        return this.readField(v) ? this.readBooleanArray() : arr_z;
    }

    protected abstract Bundle readBundle();

    public Bundle readBundle(Bundle bundle0, int v) {
        return this.readField(v) ? this.readBundle() : bundle0;
    }

    public byte readByte(byte b, int v) {
        return this.readField(v) ? ((byte)(this.readInt() & 0xFF)) : b;
    }

    protected abstract byte[] readByteArray();

    public byte[] readByteArray(byte[] arr_b, int v) {
        return this.readField(v) ? this.readByteArray() : arr_b;
    }

    public char[] readCharArray(char[] arr_c, int v) {
        if(!this.readField(v)) {
            return arr_c;
        }
        int v1 = this.readInt();
        if(v1 < 0) {
            return null;
        }
        char[] arr_c1 = new char[v1];
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_c1[v2] = (char)this.readInt();
        }
        return arr_c1;
    }

    protected abstract CharSequence readCharSequence();

    public CharSequence readCharSequence(CharSequence charSequence0, int v) {
        return this.readField(v) ? this.readCharSequence() : charSequence0;
    }

    private Collection readCollection(Collection collection0) {
        int v = this.readInt();
        if(v < 0) {
            return null;
        }
        if(v != 0) {
            switch(this.readInt()) {
                case 1: {
                    break;
                }
                case 2: {
                    while(v > 0) {
                        collection0.add(this.readParcelable());
                        --v;
                    }
                    return collection0;
                }
                case 3: {
                    while(v > 0) {
                        collection0.add(this.readSerializable());
                        --v;
                    }
                    return collection0;
                }
                case 4: {
                    while(v > 0) {
                        collection0.add(this.readString());
                        --v;
                    }
                    return collection0;
                }
                case 5: {
                    while(v > 0) {
                        collection0.add(this.readStrongBinder());
                        --v;
                    }
                    return collection0;
                }
                default: {
                    return collection0;
                }
            }
            while(v > 0) {
                collection0.add(this.readVersionedParcelable());
                --v;
            }
        }
        return collection0;
    }

    protected abstract double readDouble();

    public double readDouble(double f, int v) {
        return this.readField(v) ? this.readDouble() : f;
    }

    protected double[] readDoubleArray() {
        int v = this.readInt();
        if(v < 0) {
            return null;
        }
        double[] arr_f = new double[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_f[v1] = this.readDouble();
        }
        return arr_f;
    }

    public double[] readDoubleArray(double[] arr_f, int v) {
        return this.readField(v) ? this.readDoubleArray() : arr_f;
    }

    private Exception readException(int v, String s) {
        return this.createException(v, s);
    }

    public Exception readException(Exception exception0, int v) {
        if(this.readField(v)) {
            int v1 = this.readExceptionCode();
            return v1 == 0 ? exception0 : this.readException(v1, this.readString());
        }
        return exception0;
    }

    private int readExceptionCode() {
        return this.readInt();
    }

    protected abstract boolean readField(int arg1);

    protected abstract float readFloat();

    public float readFloat(float f, int v) {
        return this.readField(v) ? this.readFloat() : f;
    }

    protected float[] readFloatArray() {
        int v = this.readInt();
        if(v < 0) {
            return null;
        }
        float[] arr_f = new float[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_f[v1] = this.readFloat();
        }
        return arr_f;
    }

    public float[] readFloatArray(float[] arr_f, int v) {
        return this.readField(v) ? this.readFloatArray() : arr_f;
    }

    protected VersionedParcelable readFromParcel(String s, VersionedParcel versionedParcel0) {
        try {
            return (VersionedParcelable)this.getReadMethod(s).invoke(null, versionedParcel0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw invocationTargetException0.getCause() instanceof RuntimeException ? ((RuntimeException)invocationTargetException0.getCause()) : new RuntimeException("VersionedParcel encountered InvocationTargetException", invocationTargetException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", noSuchMethodException0);
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", classNotFoundException0);
        }
    }

    protected abstract int readInt();

    public int readInt(int v, int v1) {
        return this.readField(v1) ? this.readInt() : v;
    }

    protected int[] readIntArray() {
        int v = this.readInt();
        if(v < 0) {
            return null;
        }
        int[] arr_v = new int[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = this.readInt();
        }
        return arr_v;
    }

    public int[] readIntArray(int[] arr_v, int v) {
        return this.readField(v) ? this.readIntArray() : arr_v;
    }

    public List readList(List list0, int v) {
        return this.readField(v) ? ((List)this.readCollection(new ArrayList())) : list0;
    }

    protected abstract long readLong();

    public long readLong(long v, int v1) {
        return this.readField(v1) ? this.readLong() : v;
    }

    protected long[] readLongArray() {
        int v = this.readInt();
        if(v < 0) {
            return null;
        }
        long[] arr_v = new long[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = this.readLong();
        }
        return arr_v;
    }

    public long[] readLongArray(long[] arr_v, int v) {
        return this.readField(v) ? this.readLongArray() : arr_v;
    }

    public Map readMap(Map map0, int v) {
        if(!this.readField(v)) {
            return map0;
        }
        int v1 = this.readInt();
        if(v1 < 0) {
            return null;
        }
        Map map1 = new ArrayMap();
        if(v1 != 0) {
            ArrayList arrayList0 = new ArrayList();
            ArrayList arrayList1 = new ArrayList();
            this.readCollection(arrayList0);
            this.readCollection(arrayList1);
            for(int v2 = 0; v2 < v1; ++v2) {
                map1.put(arrayList0.get(v2), arrayList1.get(v2));
            }
        }
        return map1;
    }

    protected abstract Parcelable readParcelable();

    public Parcelable readParcelable(Parcelable parcelable0, int v) {
        return this.readField(v) ? this.readParcelable() : parcelable0;
    }

    protected Serializable readSerializable() {
        String s = this.readString();
        if(s == null) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(this.readByteArray());
        try {
            return (Serializable)new ObjectInputStream(byteArrayInputStream0) {
                @Override
                protected Class resolveClass(ObjectStreamClass objectStreamClass0) throws IOException, ClassNotFoundException {
                    Class class0 = Class.forName(objectStreamClass0.getName(), false, this.getClass().getClassLoader());
                    return class0 == null ? super.resolveClass(objectStreamClass0) : class0;
                }
            }.readObject();
        }
        catch(IOException iOException0) {
            throw new RuntimeException("VersionedParcelable encountered IOException reading a Serializable object (name = " + s + ")", iOException0);
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw new RuntimeException("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = " + s + ")", classNotFoundException0);
        }
    }

    public Set readSet(Set set0, int v) {
        return this.readField(v) ? ((Set)this.readCollection(new ArraySet())) : set0;
    }

    public Size readSize(Size size0, int v) {
        if(!this.readField(v)) {
            return size0;
        }
        return this.readBoolean() ? new Size(this.readInt(), this.readInt()) : null;
    }

    public SizeF readSizeF(SizeF sizeF0, int v) {
        if(!this.readField(v)) {
            return sizeF0;
        }
        return this.readBoolean() ? new SizeF(this.readFloat(), this.readFloat()) : null;
    }

    public SparseBooleanArray readSparseBooleanArray(SparseBooleanArray sparseBooleanArray0, int v) {
        if(!this.readField(v)) {
            return sparseBooleanArray0;
        }
        int v1 = this.readInt();
        if(v1 < 0) {
            return null;
        }
        SparseBooleanArray sparseBooleanArray1 = new SparseBooleanArray(v1);
        for(int v2 = 0; v2 < v1; ++v2) {
            sparseBooleanArray1.put(this.readInt(), this.readBoolean());
        }
        return sparseBooleanArray1;
    }

    protected abstract String readString();

    public String readString(String s, int v) {
        return this.readField(v) ? this.readString() : s;
    }

    protected abstract IBinder readStrongBinder();

    public IBinder readStrongBinder(IBinder iBinder0, int v) {
        return this.readField(v) ? this.readStrongBinder() : iBinder0;
    }

    protected VersionedParcelable readVersionedParcelable() {
        String s = this.readString();
        return s == null ? null : this.readFromParcel(s, this.createSubParcel());
    }

    public VersionedParcelable readVersionedParcelable(VersionedParcelable versionedParcelable0, int v) {
        return this.readField(v) ? this.readVersionedParcelable() : versionedParcelable0;
    }

    protected abstract void setOutputField(int arg1);

    public void setSerializationFlags(boolean z, boolean z1) {
    }

    protected void writeArray(Object[] arr_object) {
        if(arr_object == null) {
            this.writeInt(-1);
            return;
        }
        this.writeInt(arr_object.length);
        if(arr_object.length > 0) {
            int v = 0;
            int v1 = this.getType(arr_object[0]);
            this.writeInt(v1);
            switch(v1) {
                case 1: {
                    break;
                }
                case 2: {
                    while(v < arr_object.length) {
                        this.writeParcelable(((Parcelable)arr_object[v]));
                        ++v;
                    }
                    return;
                }
                case 3: {
                    while(v < arr_object.length) {
                        this.writeSerializable(((Serializable)arr_object[v]));
                        ++v;
                    }
                    return;
                }
                case 4: {
                    while(v < arr_object.length) {
                        this.writeString(((String)arr_object[v]));
                        ++v;
                    }
                    return;
                }
                case 5: {
                    while(v < arr_object.length) {
                        this.writeStrongBinder(((IBinder)arr_object[v]));
                        ++v;
                    }
                    return;
                }
                default: {
                    return;
                }
            }
            while(v < arr_object.length) {
                this.writeVersionedParcelable(((VersionedParcelable)arr_object[v]));
                ++v;
            }
        }
    }

    public void writeArray(Object[] arr_object, int v) {
        this.setOutputField(v);
        this.writeArray(arr_object);
    }

    protected abstract void writeBoolean(boolean arg1);

    public void writeBoolean(boolean z, int v) {
        this.setOutputField(v);
        this.writeBoolean(z);
    }

    protected void writeBooleanArray(boolean[] arr_z) {
        if(arr_z != null) {
            this.writeInt(arr_z.length);
            for(int v = 0; v < arr_z.length; ++v) {
                this.writeInt(((int)arr_z[v]));
            }
            return;
        }
        this.writeInt(-1);
    }

    public void writeBooleanArray(boolean[] arr_z, int v) {
        this.setOutputField(v);
        this.writeBooleanArray(arr_z);
    }

    protected abstract void writeBundle(Bundle arg1);

    public void writeBundle(Bundle bundle0, int v) {
        this.setOutputField(v);
        this.writeBundle(bundle0);
    }

    public void writeByte(byte b, int v) {
        this.setOutputField(v);
        this.writeInt(((int)b));
    }

    protected abstract void writeByteArray(byte[] arg1);

    public void writeByteArray(byte[] arr_b, int v) {
        this.setOutputField(v);
        this.writeByteArray(arr_b);
    }

    protected abstract void writeByteArray(byte[] arg1, int arg2, int arg3);

    public void writeByteArray(byte[] arr_b, int v, int v1, int v2) {
        this.setOutputField(v2);
        this.writeByteArray(arr_b, v, v1);
    }

    public void writeCharArray(char[] arr_c, int v) {
        this.setOutputField(v);
        if(arr_c != null) {
            this.writeInt(arr_c.length);
            for(int v1 = 0; v1 < arr_c.length; ++v1) {
                this.writeInt(((int)arr_c[v1]));
            }
            return;
        }
        this.writeInt(-1);
    }

    protected abstract void writeCharSequence(CharSequence arg1);

    public void writeCharSequence(CharSequence charSequence0, int v) {
        this.setOutputField(v);
        this.writeCharSequence(charSequence0);
    }

    private void writeCollection(Collection collection0) {
        if(collection0 == null) {
            this.writeInt(-1);
            return;
        }
        int v = collection0.size();
        this.writeInt(v);
        if(v > 0) {
            Object object0 = collection0.iterator().next();
            int v1 = this.getType(object0);
            this.writeInt(v1);
        alab1:
            switch(v1) {
                case 1: {
                    for(Object object1: collection0) {
                        this.writeVersionedParcelable(((VersionedParcelable)object1));
                    }
                    return;
                }
                case 2: {
                    for(Object object2: collection0) {
                        this.writeParcelable(((Parcelable)object2));
                    }
                    return;
                }
                case 3: {
                    for(Object object3: collection0) {
                        this.writeSerializable(((Serializable)object3));
                    }
                    return;
                }
                case 4: {
                    for(Object object4: collection0) {
                        this.writeString(((String)object4));
                    }
                    return;
                }
                case 5: {
                    for(Object object5: collection0) {
                        this.writeStrongBinder(((IBinder)object5));
                    }
                    return;
                }
                case 7: {
                    for(Object object6: collection0) {
                        this.writeInt(((int)(((Integer)object6))));
                    }
                    return;
                }
                case 8: {
                    Iterator iterator6 = collection0.iterator();
                    while(true) {
                        if(!iterator6.hasNext()) {
                            break alab1;
                        }
                        Object object7 = iterator6.next();
                        this.writeFloat(((float)(((Float)object7))));
                    }
                }
            }
        }
    }

    private void writeCollection(Collection collection0, int v) {
        this.setOutputField(v);
        this.writeCollection(collection0);
    }

    protected abstract void writeDouble(double arg1);

    public void writeDouble(double f, int v) {
        this.setOutputField(v);
        this.writeDouble(f);
    }

    protected void writeDoubleArray(double[] arr_f) {
        if(arr_f != null) {
            this.writeInt(arr_f.length);
            for(int v = 0; v < arr_f.length; ++v) {
                this.writeDouble(arr_f[v]);
            }
            return;
        }
        this.writeInt(-1);
    }

    public void writeDoubleArray(double[] arr_f, int v) {
        this.setOutputField(v);
        this.writeDoubleArray(arr_f);
    }

    public void writeException(Exception exception0, int v) {
        int v1;
        this.setOutputField(v);
        if(exception0 == null) {
            this.writeNoException();
            return;
        }
        if(exception0 instanceof Parcelable && exception0.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
            v1 = -9;
        }
        else if(exception0 instanceof SecurityException) {
            v1 = -1;
        }
        else if(exception0 instanceof BadParcelableException) {
            v1 = -2;
        }
        else if(exception0 instanceof IllegalArgumentException) {
            v1 = -3;
        }
        else if(exception0 instanceof NullPointerException) {
            v1 = -4;
        }
        else if(exception0 instanceof IllegalStateException) {
            v1 = -5;
        }
        else if(exception0 instanceof NetworkOnMainThreadException) {
            v1 = -6;
        }
        else {
            v1 = exception0 instanceof UnsupportedOperationException ? -7 : 0;
        }
        this.writeInt(v1);
        if(v1 == 0) {
            throw exception0 instanceof RuntimeException ? ((RuntimeException)exception0) : new RuntimeException(exception0);
        }
        this.writeString(exception0.getMessage());
        if(v1 != -9) {
            return;
        }
        this.writeParcelable(((Parcelable)exception0));
    }

    protected abstract void writeFloat(float arg1);

    public void writeFloat(float f, int v) {
        this.setOutputField(v);
        this.writeFloat(f);
    }

    protected void writeFloatArray(float[] arr_f) {
        if(arr_f != null) {
            this.writeInt(arr_f.length);
            for(int v = 0; v < arr_f.length; ++v) {
                this.writeFloat(arr_f[v]);
            }
            return;
        }
        this.writeInt(-1);
    }

    public void writeFloatArray(float[] arr_f, int v) {
        this.setOutputField(v);
        this.writeFloatArray(arr_f);
    }

    protected abstract void writeInt(int arg1);

    public void writeInt(int v, int v1) {
        this.setOutputField(v1);
        this.writeInt(v);
    }

    protected void writeIntArray(int[] arr_v) {
        if(arr_v != null) {
            this.writeInt(arr_v.length);
            for(int v = 0; v < arr_v.length; ++v) {
                this.writeInt(arr_v[v]);
            }
            return;
        }
        this.writeInt(-1);
    }

    public void writeIntArray(int[] arr_v, int v) {
        this.setOutputField(v);
        this.writeIntArray(arr_v);
    }

    public void writeList(List list0, int v) {
        this.writeCollection(list0, v);
    }

    protected abstract void writeLong(long arg1);

    public void writeLong(long v, int v1) {
        this.setOutputField(v1);
        this.writeLong(v);
    }

    protected void writeLongArray(long[] arr_v) {
        if(arr_v != null) {
            this.writeInt(arr_v.length);
            for(int v = 0; v < arr_v.length; ++v) {
                this.writeLong(arr_v[v]);
            }
            return;
        }
        this.writeInt(-1);
    }

    public void writeLongArray(long[] arr_v, int v) {
        this.setOutputField(v);
        this.writeLongArray(arr_v);
    }

    public void writeMap(Map map0, int v) {
        this.setOutputField(v);
        if(map0 == null) {
            this.writeInt(-1);
            return;
        }
        int v1 = map0.size();
        this.writeInt(v1);
        if(v1 == 0) {
            return;
        }
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        for(Object object0: map0.entrySet()) {
            arrayList0.add(((Map.Entry)object0).getKey());
            arrayList1.add(((Map.Entry)object0).getValue());
        }
        this.writeCollection(arrayList0);
        this.writeCollection(arrayList1);
    }

    protected void writeNoException() {
        this.writeInt(0);
    }

    protected abstract void writeParcelable(Parcelable arg1);

    public void writeParcelable(Parcelable parcelable0, int v) {
        this.setOutputField(v);
        this.writeParcelable(parcelable0);
    }

    private void writeSerializable(Serializable serializable0) {
        if(serializable0 == null) {
            this.writeString(null);
            return;
        }
        String s = serializable0.getClass().getName();
        this.writeString(s);
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream0 = new ObjectOutputStream(byteArrayOutputStream0);
            objectOutputStream0.writeObject(serializable0);
            objectOutputStream0.close();
            this.writeByteArray(byteArrayOutputStream0.toByteArray());
        }
        catch(IOException iOException0) {
            throw new RuntimeException("VersionedParcelable encountered IOException writing serializable object (name = " + s + ")", iOException0);
        }
    }

    public void writeSerializable(Serializable serializable0, int v) {
        this.setOutputField(v);
        this.writeSerializable(serializable0);
    }

    public void writeSet(Set set0, int v) {
        this.writeCollection(set0, v);
    }

    public void writeSize(Size size0, int v) {
        this.setOutputField(v);
        this.writeBoolean(size0 != null);
        if(size0 != null) {
            this.writeInt(size0.getWidth());
            this.writeInt(size0.getHeight());
        }
    }

    public void writeSizeF(SizeF sizeF0, int v) {
        this.setOutputField(v);
        this.writeBoolean(sizeF0 != null);
        if(sizeF0 != null) {
            this.writeFloat(sizeF0.getWidth());
            this.writeFloat(sizeF0.getHeight());
        }
    }

    public void writeSparseBooleanArray(SparseBooleanArray sparseBooleanArray0, int v) {
        this.setOutputField(v);
        if(sparseBooleanArray0 == null) {
            this.writeInt(-1);
            return;
        }
        int v1 = sparseBooleanArray0.size();
        this.writeInt(v1);
        for(int v2 = 0; v2 < v1; ++v2) {
            this.writeInt(sparseBooleanArray0.keyAt(v2));
            this.writeBoolean(sparseBooleanArray0.valueAt(v2));
        }
    }

    protected abstract void writeString(String arg1);

    public void writeString(String s, int v) {
        this.setOutputField(v);
        this.writeString(s);
    }

    protected abstract void writeStrongBinder(IBinder arg1);

    public void writeStrongBinder(IBinder iBinder0, int v) {
        this.setOutputField(v);
        this.writeStrongBinder(iBinder0);
    }

    protected abstract void writeStrongInterface(IInterface arg1);

    public void writeStrongInterface(IInterface iInterface0, int v) {
        this.setOutputField(v);
        this.writeStrongInterface(iInterface0);
    }

    protected void writeToParcel(VersionedParcelable versionedParcelable0, VersionedParcel versionedParcel0) {
        try {
            this.getWriteMethod(versionedParcelable0.getClass()).invoke(null, versionedParcelable0, versionedParcel0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw invocationTargetException0.getCause() instanceof RuntimeException ? ((RuntimeException)invocationTargetException0.getCause()) : new RuntimeException("VersionedParcel encountered InvocationTargetException", invocationTargetException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", noSuchMethodException0);
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", classNotFoundException0);
        }
    }

    protected void writeVersionedParcelable(VersionedParcelable versionedParcelable0) {
        if(versionedParcelable0 == null) {
            this.writeString(null);
            return;
        }
        this.writeVersionedParcelableCreator(versionedParcelable0);
        VersionedParcel versionedParcel0 = this.createSubParcel();
        this.writeToParcel(versionedParcelable0, versionedParcel0);
        versionedParcel0.closeField();
    }

    public void writeVersionedParcelable(VersionedParcelable versionedParcelable0, int v) {
        this.setOutputField(v);
        this.writeVersionedParcelable(versionedParcelable0);
    }

    private void writeVersionedParcelableCreator(VersionedParcelable versionedParcelable0) {
        Class class0;
        try {
            class0 = this.findParcelClass(versionedParcelable0.getClass());
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw new RuntimeException(versionedParcelable0.getClass().getSimpleName() + " does not have a Parcelizer", classNotFoundException0);
        }
        this.writeString(class0.getName());
    }
}

