package androidx.loader.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.core.content.ContentResolverCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader extends AsyncTaskLoader {
    private CancellationSignal mCancellationSignal;
    private Cursor mCursor;
    private final ForceLoadContentObserver mObserver;
    private String[] mProjection;
    private String mSelection;
    private String[] mSelectionArgs;
    private String mSortOrder;
    private Uri mUri;

    public CursorLoader(Context context0) {
        super(context0);
        this.mObserver = new ForceLoadContentObserver(this);
    }

    public CursorLoader(Context context0, Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1) {
        super(context0);
        this.mObserver = new ForceLoadContentObserver(this);
        this.mUri = uri0;
        this.mProjection = arr_s;
        this.mSelection = s;
        this.mSelectionArgs = arr_s1;
        this.mSortOrder = s1;
    }

    @Override  // androidx.loader.content.AsyncTaskLoader
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized(this) {
            CancellationSignal cancellationSignal0 = this.mCancellationSignal;
            if(cancellationSignal0 != null) {
                cancellationSignal0.cancel();
            }
        }
    }

    public void deliverResult(Cursor cursor0) {
        if(!this.isReset()) {
            Cursor cursor1 = this.mCursor;
            this.mCursor = cursor0;
            if(this.isStarted()) {
                super.deliverResult(cursor0);
            }
            if(cursor1 != null && cursor1 != cursor0 && !cursor1.isClosed()) {
                cursor1.close();
            }
        }
        else if(cursor0 != null) {
            cursor0.close();
        }
    }

    @Override  // androidx.loader.content.Loader
    public void deliverResult(Object object0) {
        this.deliverResult(((Cursor)object0));
    }

    @Override  // androidx.loader.content.AsyncTaskLoader
    @Deprecated
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        super.dump(s, fileDescriptor0, printWriter0, arr_s);
        printWriter0.print(s);
        printWriter0.print("mUri=");
        printWriter0.println(this.mUri);
        printWriter0.print(s);
        printWriter0.print("mProjection=");
        printWriter0.println(Arrays.toString(this.mProjection));
        printWriter0.print(s);
        printWriter0.print("mSelection=");
        printWriter0.println(this.mSelection);
        printWriter0.print(s);
        printWriter0.print("mSelectionArgs=");
        printWriter0.println(Arrays.toString(this.mSelectionArgs));
        printWriter0.print(s);
        printWriter0.print("mSortOrder=");
        printWriter0.println(this.mSortOrder);
        printWriter0.print(s);
        printWriter0.print("mCursor=");
        printWriter0.println(this.mCursor);
    }

    public String[] getProjection() {
        return this.mProjection;
    }

    public String getSelection() {
        return this.mSelection;
    }

    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    public String getSortOrder() {
        return this.mSortOrder;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public Cursor loadInBackground() {
        synchronized(this) {
            if(!this.isLoadInBackgroundCanceled()) {
                this.mCancellationSignal = new CancellationSignal();
                try {
                    Cursor cursor0 = ContentResolverCompat.query(this.getContext().getContentResolver(), this.mUri, this.mProjection, this.mSelection, this.mSelectionArgs, this.mSortOrder, this.mCancellationSignal);
                    if(cursor0 != null) {
                        try {
                            cursor0.getCount();
                            cursor0.registerContentObserver(this.mObserver);
                        }
                        catch(RuntimeException runtimeException0) {
                            cursor0.close();
                            throw runtimeException0;
                        }
                    }
                    return cursor0;
                }
                finally {
                    synchronized(this) {
                        this.mCancellationSignal = null;
                    }
                }
            }
        }
        throw new OperationCanceledException();
    }

    @Override  // androidx.loader.content.AsyncTaskLoader
    public Object loadInBackground() {
        return this.loadInBackground();
    }

    public void onCanceled(Cursor cursor0) {
        if(cursor0 != null && !cursor0.isClosed()) {
            cursor0.close();
        }
    }

    @Override  // androidx.loader.content.AsyncTaskLoader
    public void onCanceled(Object object0) {
        this.onCanceled(((Cursor)object0));
    }

    @Override  // androidx.loader.content.Loader
    protected void onReset() {
        super.onReset();
        this.onStopLoading();
        if(this.mCursor != null && !this.mCursor.isClosed()) {
            this.mCursor.close();
        }
        this.mCursor = null;
    }

    @Override  // androidx.loader.content.Loader
    protected void onStartLoading() {
        Cursor cursor0 = this.mCursor;
        if(cursor0 != null) {
            this.deliverResult(cursor0);
        }
        if(!this.takeContentChanged() && this.mCursor != null) {
            return;
        }
        this.forceLoad();
    }

    @Override  // androidx.loader.content.Loader
    protected void onStopLoading() {
        this.cancelLoad();
    }

    public void setProjection(String[] arr_s) {
        this.mProjection = arr_s;
    }

    public void setSelection(String s) {
        this.mSelection = s;
    }

    public void setSelectionArgs(String[] arr_s) {
        this.mSelectionArgs = arr_s;
    }

    public void setSortOrder(String s) {
        this.mSortOrder = s;
    }

    public void setUri(Uri uri0) {
        this.mUri = uri0;
    }
}

