package androidx.core.app;

import android.app.GrammaticalInflectionManager;
import android.content.Context;
import android.os.Build.VERSION;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class GrammaticalInflectionManagerCompat {
    static class Api34Impl {
        static int getApplicationGrammaticalGender(Context context0) {
            return Api34Impl.getGrammaticalInflectionManager(context0).getApplicationGrammaticalGender();
        }

        private static GrammaticalInflectionManager getGrammaticalInflectionManager(Context context0) {
            return (GrammaticalInflectionManager)context0.getSystemService(GrammaticalInflectionManager.class);
        }

        static void setRequestedApplicationGrammaticalGender(Context context0, int v) {
            Api34Impl.getGrammaticalInflectionManager(context0).setRequestedApplicationGrammaticalGender(v);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GrammaticalGender {
    }

    public static final int GRAMMATICAL_GENDER_FEMININE = 2;
    public static final int GRAMMATICAL_GENDER_MASCULINE = 3;
    public static final int GRAMMATICAL_GENDER_NEUTRAL = 1;
    public static final int GRAMMATICAL_GENDER_NOT_SPECIFIED;

    public static int getApplicationGrammaticalGender(Context context0) {
        return Build.VERSION.SDK_INT < 34 ? 0 : Api34Impl.getApplicationGrammaticalGender(context0);
    }

    public static void setRequestedApplicationGrammaticalGender(Context context0, int v) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setRequestedApplicationGrammaticalGender(context0, v);
        }
    }
}

