package com.google.android.gms.common.data;

import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    ParcelFileDescriptor zab;
    final int zac;
    private Bitmap zad;
    private boolean zae;
    private File zaf;

    static {
        BitmapTeleporter.CREATOR = new zaa();
    }

    BitmapTeleporter(int v, ParcelFileDescriptor parcelFileDescriptor0, int v1) {
        this.zaa = v;
        this.zab = parcelFileDescriptor0;
        this.zac = v1;
        this.zad = null;
        this.zae = false;
    }

    public BitmapTeleporter(Bitmap bitmap0) {
        this.zaa = 1;
        this.zab = null;
        this.zac = 0;
        this.zad = bitmap0;
        this.zae = true;
    }

    public Bitmap get() {
        Bitmap.Config bitmap$Config0;
        int v2;
        int v1;
        byte[] arr_b;
        if(!this.zae) {
            DataInputStream dataInputStream0 = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(((ParcelFileDescriptor)Preconditions.checkNotNull(this.zab))));
            try {
                arr_b = new byte[dataInputStream0.readInt()];
                v1 = dataInputStream0.readInt();
                v2 = dataInputStream0.readInt();
                bitmap$Config0 = Bitmap.Config.valueOf(dataInputStream0.readUTF());
                dataInputStream0.read(arr_b);
            }
            catch(IOException iOException0) {
                throw new IllegalStateException("Could not read from parcel file descriptor", iOException0);
            }
            finally {
                BitmapTeleporter.zaa(dataInputStream0);
            }
            ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b);
            Bitmap bitmap0 = Bitmap.createBitmap(v1, v2, bitmap$Config0);
            bitmap0.copyPixelsFromBuffer(byteBuffer0);
            this.zad = bitmap0;
            this.zae = true;
            return this.zad;
        }
        return this.zad;
    }

    public void release() {
        if(!this.zae) {
            try {
                ((ParcelFileDescriptor)Preconditions.checkNotNull(this.zab)).close();
            }
            catch(IOException iOException0) {
                Log.w("BitmapTeleporter", "Could not close PFD", iOException0);
            }
        }
    }

    public void setTempDir(File file0) {
        if(file0 == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zaf = file0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        FileOutputStream fileOutputStream0;
        File file1;
        if(this.zab == null) {
            Bitmap bitmap0 = (Bitmap)Preconditions.checkNotNull(this.zad);
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(bitmap0.getRowBytes() * bitmap0.getHeight());
            bitmap0.copyPixelsToBuffer(byteBuffer0);
            byte[] arr_b = byteBuffer0.array();
            File file0 = this.zaf;
            if(file0 == null) {
                throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
            }
            try {
                file1 = File.createTempFile("teleporter", ".tmp", file0);
            }
            catch(IOException iOException0) {
                throw new IllegalStateException("Could not create temporary file", iOException0);
            }
            try {
                fileOutputStream0 = new FileOutputStream(file1);
                this.zab = ParcelFileDescriptor.open(file1, 0x10000000);
            }
            catch(FileNotFoundException unused_ex) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
            file1.delete();
            DataOutputStream dataOutputStream0 = new DataOutputStream(new BufferedOutputStream(fileOutputStream0));
            try {
                dataOutputStream0.writeInt(arr_b.length);
                dataOutputStream0.writeInt(bitmap0.getWidth());
                dataOutputStream0.writeInt(bitmap0.getHeight());
                dataOutputStream0.writeUTF(bitmap0.getConfig().toString());
                dataOutputStream0.write(arr_b);
            }
            catch(IOException iOException1) {
                throw new IllegalStateException("Could not write into unlinked file", iOException1);
            }
            finally {
                BitmapTeleporter.zaa(dataOutputStream0);
            }
        }
        int v2 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zab, v | 1, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.zac);
        SafeParcelWriter.finishObjectHeader(parcel0, v2);
        this.zab = null;
    }

    private static final void zaa(Closeable closeable0) {
        try {
            closeable0.close();
        }
        catch(IOException iOException0) {
            Log.w("BitmapTeleporter", "Could not close stream", iOException0);
        }
    }
}

