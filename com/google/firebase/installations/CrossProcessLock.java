package com.google.firebase.installations;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

class CrossProcessLock {
    private static final String TAG = "CrossProcessLock";
    private final FileChannel channel;
    private final FileLock lock;

    private CrossProcessLock(FileChannel fileChannel0, FileLock fileLock0) {
        this.channel = fileChannel0;
        this.lock = fileLock0;
    }

    static CrossProcessLock acquire(Context context0, String s) {
        FileLock fileLock0;
        FileChannel fileChannel0;
        try {
            fileChannel0 = new RandomAccessFile(new File(context0.getFilesDir(), s), "rw").getChannel();
        }
        catch(IOException | Error | OverlappingFileLockException iOException0) {
            fileChannel0 = null;
            fileLock0 = null;
            goto label_13;
        }
        try {
            fileLock0 = fileChannel0.lock();
        }
        catch(IOException | Error | OverlappingFileLockException iOException0) {
            fileLock0 = null;
            goto label_13;
        }
        try {
            return new CrossProcessLock(fileChannel0, fileLock0);
        }
        catch(IOException | Error | OverlappingFileLockException iOException0) {
        }
    label_13:
        Log.e("CrossProcessLock", "encountered error while creating and acquiring the lock, ignoring", iOException0);
        if(fileLock0 != null) {
            try {
                fileLock0.release();
            }
            catch(IOException unused_ex) {
            }
        }
        if(fileChannel0 != null) {
            try {
                fileChannel0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        return null;
    }

    void releaseAndClose() {
        try {
            this.lock.release();
            this.channel.close();
        }
        catch(IOException iOException0) {
            Log.e("CrossProcessLock", "encountered error while releasing, ignoring", iOException0);
        }
    }
}

