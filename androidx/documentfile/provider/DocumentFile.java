package androidx.documentfile.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import java.io.File;

public abstract class DocumentFile {
    static final String TAG = "DocumentFile";
    private final DocumentFile mParent;

    DocumentFile(DocumentFile documentFile0) {
        this.mParent = documentFile0;
    }

    public abstract boolean canRead();

    public abstract boolean canWrite();

    public abstract DocumentFile createDirectory(String arg1);

    public abstract DocumentFile createFile(String arg1, String arg2);

    public abstract boolean delete();

    public abstract boolean exists();

    public DocumentFile findFile(String s) {
        DocumentFile[] arr_documentFile = this.listFiles();
        for(int v = 0; v < arr_documentFile.length; ++v) {
            DocumentFile documentFile0 = arr_documentFile[v];
            if(s.equals(documentFile0.getName())) {
                return documentFile0;
            }
        }
        return null;
    }

    public static DocumentFile fromFile(File file0) {
        return new RawDocumentFile(null, file0);
    }

    public static DocumentFile fromSingleUri(Context context0, Uri uri0) {
        return new SingleDocumentFile(null, context0, uri0);
    }

    public static DocumentFile fromTreeUri(Context context0, Uri uri0) {
        return new TreeDocumentFile(null, context0, DocumentsContract.buildDocumentUriUsingTree(uri0, DocumentsContract.getTreeDocumentId(uri0)));
    }

    public abstract String getName();

    public DocumentFile getParentFile() {
        return this.mParent;
    }

    public abstract String getType();

    public abstract Uri getUri();

    public abstract boolean isDirectory();

    public static boolean isDocumentUri(Context context0, Uri uri0) {
        return DocumentsContract.isDocumentUri(context0, uri0);
    }

    public abstract boolean isFile();

    public abstract boolean isVirtual();

    public abstract long lastModified();

    public abstract long length();

    public abstract DocumentFile[] listFiles();

    public abstract boolean renameTo(String arg1);
}

