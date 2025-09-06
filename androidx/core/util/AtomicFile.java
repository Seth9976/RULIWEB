package androidx.core.util;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
    private static final String LOG_TAG = "AtomicFile";
    private final File mBaseName;
    private final File mLegacyBackupName;
    private final File mNewName;

    public AtomicFile(File file0) {
        this.mBaseName = file0;
        this.mNewName = new File(file0.getPath() + ".new");
        this.mLegacyBackupName = new File(file0.getPath() + ".bak");
    }

    public void delete() {
        this.mBaseName.delete();
        this.mNewName.delete();
        this.mLegacyBackupName.delete();
    }

    public void failWrite(FileOutputStream fileOutputStream0) {
        if(fileOutputStream0 != null) {
            if(!AtomicFile.sync(fileOutputStream0)) {
                Log.e("AtomicFile", "Failed to sync file output stream");
            }
            try {
                fileOutputStream0.close();
            }
            catch(IOException iOException0) {
                Log.e("AtomicFile", "Failed to close file output stream", iOException0);
            }
            if(!this.mNewName.delete()) {
                Log.e("AtomicFile", "Failed to delete new file " + this.mNewName);
            }
        }
    }

    public void finishWrite(FileOutputStream fileOutputStream0) {
        if(fileOutputStream0 == null) {
            return;
        }
        if(!AtomicFile.sync(fileOutputStream0)) {
            Log.e("AtomicFile", "Failed to sync file output stream");
        }
        try {
            fileOutputStream0.close();
        }
        catch(IOException iOException0) {
            Log.e("AtomicFile", "Failed to close file output stream", iOException0);
        }
        AtomicFile.rename(this.mNewName, this.mBaseName);
    }

    public File getBaseFile() {
        return this.mBaseName;
    }

    public FileInputStream openRead() throws FileNotFoundException {
        if(this.mLegacyBackupName.exists()) {
            AtomicFile.rename(this.mLegacyBackupName, this.mBaseName);
        }
        if(this.mNewName.exists() && this.mBaseName.exists() && !this.mNewName.delete()) {
            Log.e("AtomicFile", "Failed to delete outdated new file " + this.mNewName);
        }
        return new FileInputStream(this.mBaseName);
    }

    public byte[] readFully() throws IOException {
        try(FileInputStream fileInputStream0 = this.openRead()) {
            byte[] arr_b = new byte[fileInputStream0.available()];
            int v = 0;
            int v1;
            while((v1 = fileInputStream0.read(arr_b, v, arr_b.length - v)) > 0) {
                v += v1;
                int v2 = fileInputStream0.available();
                if(v2 <= arr_b.length - v) {
                    continue;
                }
                byte[] arr_b1 = new byte[v2 + v];
                System.arraycopy(arr_b, 0, arr_b1, 0, v);
                arr_b = arr_b1;
            }
            return arr_b;
        }
    }

    private static void rename(File file0, File file1) {
        if(file1.isDirectory() && !file1.delete()) {
            Log.e("AtomicFile", "Failed to delete file which is a directory " + file1);
        }
        if(!file0.renameTo(file1)) {
            Log.e("AtomicFile", "Failed to rename " + file0 + " to " + file1);
        }
    }

    public FileOutputStream startWrite() throws IOException {
        if(this.mLegacyBackupName.exists()) {
            AtomicFile.rename(this.mLegacyBackupName, this.mBaseName);
        }
        try {
            return new FileOutputStream(this.mNewName);
        }
        catch(FileNotFoundException unused_ex) {
            if(this.mNewName.getParentFile().mkdirs()) {
                try {
                    return new FileOutputStream(this.mNewName);
                }
                catch(FileNotFoundException fileNotFoundException0) {
                    throw new IOException("Failed to create new file " + this.mNewName, fileNotFoundException0);
                }
            }
            throw new IOException("Failed to create directory for " + this.mNewName);
        }
    }

    private static boolean sync(FileOutputStream fileOutputStream0) {
        try {
            fileOutputStream0.getFD().sync();
            return true;
        }
        catch(IOException unused_ex) {
            return false;
        }
    }
}

