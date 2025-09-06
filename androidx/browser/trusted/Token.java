package androidx.browser.trusted;

import android.content.pm.PackageManager;
import android.util.Log;
import java.io.IOException;
import java.util.List;

public final class Token {
    private static final String TAG = "Token";
    private final TokenContents mContents;

    private Token(TokenContents tokenContents0) {
        this.mContents = tokenContents0;
    }

    public static Token create(String s, PackageManager packageManager0) {
        List list0 = PackageIdentityUtils.getFingerprintsForPackage(s, packageManager0);
        if(list0 == null) {
            return null;
        }
        try {
            return new Token(TokenContents.create(s, list0));
        }
        catch(IOException iOException0) {
            Log.e("Token", "Exception when creating token.", iOException0);
            return null;
        }
    }

    public static Token deserialize(byte[] arr_b) {
        return new Token(TokenContents.deserialize(arr_b));
    }

    public boolean matches(String s, PackageManager packageManager0) {
        return PackageIdentityUtils.packageMatchesToken(s, packageManager0, this.mContents);
    }

    public byte[] serialize() {
        return this.mContents.serialize();
    }
}

