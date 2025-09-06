package androidx.core.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import java.io.FileNotFoundException;
import java.util.List;

public final class DocumentsContractCompat {
    public static final class DocumentCompat {
        public static final int FLAG_VIRTUAL_DOCUMENT = 0x200;

    }

    static class DocumentsContractApi21Impl {
        static Uri buildChildDocumentsUri(String s, String s1) {
            return DocumentsContract.buildChildDocumentsUri(s, s1);
        }

        static Uri buildChildDocumentsUriUsingTree(Uri uri0, String s) {
            return DocumentsContract.buildChildDocumentsUriUsingTree(uri0, s);
        }

        static Uri buildDocumentUriUsingTree(Uri uri0, String s) {
            return DocumentsContract.buildDocumentUriUsingTree(uri0, s);
        }

        public static Uri buildTreeDocumentUri(String s, String s1) {
            return DocumentsContract.buildTreeDocumentUri(s, s1);
        }

        static Uri createDocument(ContentResolver contentResolver0, Uri uri0, String s, String s1) throws FileNotFoundException {
            return DocumentsContract.createDocument(contentResolver0, uri0, s, s1);
        }

        static String getTreeDocumentId(Uri uri0) {
            return DocumentsContract.getTreeDocumentId(uri0);
        }

        static Uri renameDocument(ContentResolver contentResolver0, Uri uri0, String s) throws FileNotFoundException {
            return DocumentsContract.renameDocument(contentResolver0, uri0, s);
        }
    }

    static class DocumentsContractApi24Impl {
        static boolean isTreeUri(Uri uri0) {
            return DocumentsContract.isTreeUri(uri0);
        }

        static boolean removeDocument(ContentResolver contentResolver0, Uri uri0, Uri uri1) throws FileNotFoundException {
            return DocumentsContract.removeDocument(contentResolver0, uri0, uri1);
        }
    }

    private static final String PATH_TREE = "tree";

    public static Uri buildChildDocumentsUri(String s, String s1) {
        return DocumentsContractApi21Impl.buildChildDocumentsUri(s, s1);
    }

    public static Uri buildChildDocumentsUriUsingTree(Uri uri0, String s) {
        return DocumentsContractApi21Impl.buildChildDocumentsUriUsingTree(uri0, s);
    }

    public static Uri buildDocumentUri(String s, String s1) {
        return DocumentsContract.buildDocumentUri(s, s1);
    }

    public static Uri buildDocumentUriUsingTree(Uri uri0, String s) {
        return DocumentsContractApi21Impl.buildDocumentUriUsingTree(uri0, s);
    }

    public static Uri buildTreeDocumentUri(String s, String s1) {
        return DocumentsContractApi21Impl.buildTreeDocumentUri(s, s1);
    }

    public static Uri createDocument(ContentResolver contentResolver0, Uri uri0, String s, String s1) throws FileNotFoundException {
        return DocumentsContractApi21Impl.createDocument(contentResolver0, uri0, s, s1);
    }

    public static String getDocumentId(Uri uri0) {
        return DocumentsContract.getDocumentId(uri0);
    }

    public static String getTreeDocumentId(Uri uri0) {
        return DocumentsContractApi21Impl.getTreeDocumentId(uri0);
    }

    public static boolean isDocumentUri(Context context0, Uri uri0) {
        return DocumentsContract.isDocumentUri(context0, uri0);
    }

    public static boolean isTreeUri(Uri uri0) {
        if(Build.VERSION.SDK_INT < 24) {
            List list0 = uri0.getPathSegments();
            return list0.size() >= 2 && "tree".equals(list0.get(0));
        }
        return DocumentsContractApi24Impl.isTreeUri(uri0);
    }

    public static boolean removeDocument(ContentResolver contentResolver0, Uri uri0, Uri uri1) throws FileNotFoundException {
        return Build.VERSION.SDK_INT < 24 ? DocumentsContract.deleteDocument(contentResolver0, uri0) : DocumentsContractApi24Impl.removeDocument(contentResolver0, uri0, uri1);
    }

    public static Uri renameDocument(ContentResolver contentResolver0, Uri uri0, String s) throws FileNotFoundException {
        return DocumentsContractApi21Impl.renameDocument(contentResolver0, uri0, s);
    }
}

