package androidx.browser.browseractions;

import android.content.ClipData.Item;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri.Builder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.content.FileProvider;
import androidx.core.util.AtomicFile;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Deprecated
public final class BrowserServiceFileProvider extends FileProvider {
    static class FileCleanupTask extends AsyncTask {
        private static final long CLEANUP_REQUIRED_TIME_SPAN;
        private static final long DELETION_FAILED_REATTEMPT_DURATION;
        private static final long IMAGE_RETENTION_DURATION;
        private final Context mAppContext;

        static {
            FileCleanupTask.IMAGE_RETENTION_DURATION = TimeUnit.DAYS.toMillis(7L);
            FileCleanupTask.CLEANUP_REQUIRED_TIME_SPAN = TimeUnit.DAYS.toMillis(7L);
            FileCleanupTask.DELETION_FAILED_REATTEMPT_DURATION = TimeUnit.DAYS.toMillis(1L);
        }

        FileCleanupTask(Context context0) {
            this.mAppContext = context0.getApplicationContext();
        }

        @Override  // android.os.AsyncTask
        protected Object doInBackground(Object[] arr_object) {
            return this.doInBackground(((Void[])arr_object));
        }

        protected Void doInBackground(Void[] arr_void) {
            SharedPreferences sharedPreferences0 = this.mAppContext.getSharedPreferences("com.ruliweb.www.ruliapp.image_provider", 0);
            if(!FileCleanupTask.shouldCleanUp(sharedPreferences0)) {
                return null;
            }
            synchronized(BrowserServiceFileProvider.sFileCleanupLock) {
                File file0 = new File(this.mAppContext.getFilesDir(), "image_provider");
                if(!file0.exists()) {
                    return null;
                }
                File[] arr_file = file0.listFiles();
                long v1 = System.currentTimeMillis() - FileCleanupTask.IMAGE_RETENTION_DURATION;
                boolean z = true;
                for(int v2 = 0; v2 < arr_file.length; ++v2) {
                    File file1 = arr_file[v2];
                    if(FileCleanupTask.isImageFile(file1) && file1.lastModified() < v1 && !file1.delete()) {
                        Log.e("BrowserServiceFP", "Fail to delete image: " + file1.getAbsoluteFile());
                        z = false;
                    }
                }
                SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit();
                sharedPreferences$Editor0.putLong("last_cleanup_time", (z ? System.currentTimeMillis() : System.currentTimeMillis() - FileCleanupTask.CLEANUP_REQUIRED_TIME_SPAN + FileCleanupTask.DELETION_FAILED_REATTEMPT_DURATION));
                sharedPreferences$Editor0.apply();
                return null;
            }
        }

        private static boolean isImageFile(File file0) {
            return file0.getName().endsWith("..png");
        }

        private static boolean shouldCleanUp(SharedPreferences sharedPreferences0) {
            return System.currentTimeMillis() > sharedPreferences0.getLong("last_cleanup_time", System.currentTimeMillis()) + FileCleanupTask.CLEANUP_REQUIRED_TIME_SPAN;
        }
    }

    static class FileSaveTask extends AsyncTask {
        private final Context mAppContext;
        private final Bitmap mBitmap;
        private final Uri mFileUri;
        private final String mFilename;
        private final ResolvableFuture mResultFuture;

        FileSaveTask(Context context0, String s, Bitmap bitmap0, Uri uri0, ResolvableFuture resolvableFuture0) {
            this.mAppContext = context0.getApplicationContext();
            this.mFilename = s;
            this.mBitmap = bitmap0;
            this.mFileUri = uri0;
            this.mResultFuture = resolvableFuture0;
        }

        @Override  // android.os.AsyncTask
        protected Object doInBackground(Object[] arr_object) {
            return this.doInBackground(((String[])arr_object));
        }

        protected Void doInBackground(String[] arr_s) {
            this.saveFileIfNeededBlocking();
            return null;
        }

        @Override  // android.os.AsyncTask
        protected void onPostExecute(Object object0) {
            this.onPostExecute(((Void)object0));
        }

        protected void onPostExecute(Void void0) {
            new FileCleanupTask(this.mAppContext).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }

        private void saveFileBlocking(File file0) {
            FileOutputStream fileOutputStream0;
            if(Build.VERSION.SDK_INT >= 22) {
                AtomicFile atomicFile0 = new AtomicFile(file0);
                try {
                    fileOutputStream0 = null;
                    fileOutputStream0 = atomicFile0.startWrite();
                    this.mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream0);
                    fileOutputStream0.close();
                    atomicFile0.finishWrite(fileOutputStream0);
                    this.mResultFuture.set(this.mFileUri);
                }
                catch(IOException iOException0) {
                    atomicFile0.failWrite(fileOutputStream0);
                    this.mResultFuture.setException(iOException0);
                }
                return;
            }
            try {
                FileOutputStream fileOutputStream1 = new FileOutputStream(file0);
                this.mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream1);
                fileOutputStream1.close();
                this.mResultFuture.set(this.mFileUri);
            }
            catch(IOException iOException1) {
                this.mResultFuture.setException(iOException1);
            }
        }

        private void saveFileIfNeededBlocking() {
            File file0 = new File(this.mAppContext.getFilesDir(), "image_provider");
            synchronized(BrowserServiceFileProvider.sFileCleanupLock) {
                if(!file0.exists() && !file0.mkdir()) {
                    IOException iOException0 = new IOException("Could not create file directory.");
                    this.mResultFuture.setException(iOException0);
                    return;
                }
                File file1 = new File(file0, this.mFilename + ".png");
                if(file1.exists()) {
                    this.mResultFuture.set(this.mFileUri);
                }
                else {
                    this.saveFileBlocking(file1);
                }
                file1.setLastModified(System.currentTimeMillis());
            }
        }
    }

    private static final String AUTHORITY_SUFFIX = ".image_provider";
    private static final String CLIP_DATA_LABEL = "image_provider_uris";
    private static final String CONTENT_SCHEME = "content";
    private static final String FILE_EXTENSION = ".png";
    private static final String FILE_SUB_DIR = "image_provider";
    private static final String FILE_SUB_DIR_NAME = "image_provider_images/";
    private static final String LAST_CLEANUP_TIME_KEY = "last_cleanup_time";
    private static final String TAG = "BrowserServiceFP";
    static Object sFileCleanupLock;

    static {
        BrowserServiceFileProvider.sFileCleanupLock = new Object();
    }

    // 去混淆评级： 低(20)
    private static Uri generateUri(Context context0, String s) {
        return new Uri.Builder().scheme("content").authority("com.ruliweb.www.ruliapp.image_provider").path("image_provider_images/" + s + ".png").build();
    }

    public static void grantReadPermission(Intent intent0, List list0, Context context0) {
        if(list0 != null && list0.size() != 0) {
            ContentResolver contentResolver0 = context0.getContentResolver();
            intent0.addFlags(1);
            ClipData clipData0 = ClipData.newUri(contentResolver0, "image_provider_uris", ((Uri)list0.get(0)));
            for(int v = 1; v < list0.size(); ++v) {
                clipData0.addItem(new ClipData.Item(((Uri)list0.get(v))));
            }
            intent0.setClipData(clipData0);
        }
    }

    public static ListenableFuture loadBitmap(ContentResolver contentResolver0, Uri uri0) {
        ListenableFuture listenableFuture0 = ResolvableFuture.create();
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ParcelFileDescriptor parcelFileDescriptor0 = contentResolver0.openFileDescriptor(uri0, "r");
                    if(parcelFileDescriptor0 == null) {
                        FileNotFoundException fileNotFoundException0 = new FileNotFoundException();
                        ((ResolvableFuture)listenableFuture0).setException(fileNotFoundException0);
                        return;
                    }
                    Bitmap bitmap0 = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor0.getFileDescriptor());
                    parcelFileDescriptor0.close();
                    if(bitmap0 == null) {
                        IOException iOException1 = new IOException("File could not be decoded.");
                        ((ResolvableFuture)listenableFuture0).setException(iOException1);
                        return;
                    }
                    ((ResolvableFuture)listenableFuture0).set(bitmap0);
                }
                catch(IOException iOException0) {
                    ((ResolvableFuture)listenableFuture0).setException(iOException0);
                }
            }
        });
        return listenableFuture0;
    }

    public static ResolvableFuture saveBitmap(Context context0, Bitmap bitmap0, String s, int v) {
        String s1 = s + "_" + Integer.toString(v);
        Uri uri0 = BrowserServiceFileProvider.generateUri(context0, s1);
        ResolvableFuture resolvableFuture0 = ResolvableFuture.create();
        new FileSaveTask(context0, s1, bitmap0, uri0, resolvableFuture0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        return resolvableFuture0;
    }
}

