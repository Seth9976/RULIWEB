package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.google.android.gms.auth.api.signin.internal.zbm;
import com.google.android.gms.auth.api.signin.internal.zbn;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Deprecated
public final class GoogleSignIn {
    public static GoogleSignInAccount getAccountForExtension(Context context0, GoogleSignInOptionsExtension googleSignInOptionsExtension0) {
        Preconditions.checkNotNull(context0, "please provide a valid Context object");
        Preconditions.checkNotNull(googleSignInOptionsExtension0, "please provide valid GoogleSignInOptionsExtension");
        GoogleSignInAccount googleSignInAccount0 = GoogleSignIn.getLastSignedInAccount(context0);
        if(googleSignInAccount0 == null) {
            googleSignInAccount0 = GoogleSignInAccount.createDefault();
        }
        return googleSignInAccount0.requestExtraScopes(GoogleSignIn.zbb(googleSignInOptionsExtension0.getImpliedScopes()));
    }

    public static GoogleSignInAccount getAccountForScopes(Context context0, Scope scope0, Scope[] arr_scope) {
        Preconditions.checkNotNull(context0, "please provide a valid Context object");
        Preconditions.checkNotNull(scope0, "please provide at least one valid scope");
        GoogleSignInAccount googleSignInAccount0 = GoogleSignIn.getLastSignedInAccount(context0);
        if(googleSignInAccount0 == null) {
            googleSignInAccount0 = GoogleSignInAccount.createDefault();
        }
        googleSignInAccount0.requestExtraScopes(new Scope[]{scope0});
        googleSignInAccount0.requestExtraScopes(arr_scope);
        return googleSignInAccount0;
    }

    public static GoogleSignInClient getClient(Activity activity0, GoogleSignInOptions googleSignInOptions0) {
        return new GoogleSignInClient(activity0, ((GoogleSignInOptions)Preconditions.checkNotNull(googleSignInOptions0)));
    }

    public static GoogleSignInClient getClient(Context context0, GoogleSignInOptions googleSignInOptions0) {
        return new GoogleSignInClient(context0, ((GoogleSignInOptions)Preconditions.checkNotNull(googleSignInOptions0)));
    }

    public static GoogleSignInAccount getLastSignedInAccount(Context context0) {
        return zbn.zbc(context0).zba();
    }

    public static Task getSignedInAccountFromIntent(Intent intent0) {
        GoogleSignInResult googleSignInResult0 = zbm.zbd(intent0);
        GoogleSignInAccount googleSignInAccount0 = googleSignInResult0.getSignInAccount();
        return !googleSignInResult0.getStatus().isSuccess() || googleSignInAccount0 == null ? Tasks.forException(ApiExceptionUtil.fromStatus(googleSignInResult0.getStatus())) : Tasks.forResult(googleSignInAccount0);
    }

    public static boolean hasPermissions(GoogleSignInAccount googleSignInAccount0, GoogleSignInOptionsExtension googleSignInOptionsExtension0) {
        Preconditions.checkNotNull(googleSignInOptionsExtension0, "Please provide a non-null GoogleSignInOptionsExtension");
        return GoogleSignIn.hasPermissions(googleSignInAccount0, GoogleSignIn.zbb(googleSignInOptionsExtension0.getImpliedScopes()));
    }

    public static boolean hasPermissions(GoogleSignInAccount googleSignInAccount0, Scope[] arr_scope) {
        if(googleSignInAccount0 == null) {
            return false;
        }
        HashSet hashSet0 = new HashSet();
        Collections.addAll(hashSet0, arr_scope);
        return googleSignInAccount0.getGrantedScopes().containsAll(hashSet0);
    }

    public static void requestPermissions(Activity activity0, int v, GoogleSignInAccount googleSignInAccount0, GoogleSignInOptionsExtension googleSignInOptionsExtension0) {
        Preconditions.checkNotNull(activity0, "Please provide a non-null Activity");
        Preconditions.checkNotNull(googleSignInOptionsExtension0, "Please provide a non-null GoogleSignInOptionsExtension");
        GoogleSignIn.requestPermissions(activity0, v, googleSignInAccount0, GoogleSignIn.zbb(googleSignInOptionsExtension0.getImpliedScopes()));
    }

    public static void requestPermissions(Activity activity0, int v, GoogleSignInAccount googleSignInAccount0, Scope[] arr_scope) {
        Preconditions.checkNotNull(activity0, "Please provide a non-null Activity");
        Preconditions.checkNotNull(arr_scope, "Please provide at least one scope");
        activity0.startActivityForResult(GoogleSignIn.zba(activity0, googleSignInAccount0, arr_scope), v);
    }

    public static void requestPermissions(Fragment fragment0, int v, GoogleSignInAccount googleSignInAccount0, GoogleSignInOptionsExtension googleSignInOptionsExtension0) {
        Preconditions.checkNotNull(fragment0, "Please provide a non-null Fragment");
        Preconditions.checkNotNull(googleSignInOptionsExtension0, "Please provide a non-null GoogleSignInOptionsExtension");
        GoogleSignIn.requestPermissions(fragment0, v, googleSignInAccount0, GoogleSignIn.zbb(googleSignInOptionsExtension0.getImpliedScopes()));
    }

    public static void requestPermissions(Fragment fragment0, int v, GoogleSignInAccount googleSignInAccount0, Scope[] arr_scope) {
        Preconditions.checkNotNull(fragment0, "Please provide a non-null Fragment");
        Preconditions.checkNotNull(arr_scope, "Please provide at least one scope");
        fragment0.startActivityForResult(GoogleSignIn.zba(fragment0.getActivity(), googleSignInAccount0, arr_scope), v);
    }

    private static Intent zba(Activity activity0, GoogleSignInAccount googleSignInAccount0, Scope[] arr_scope) {
        Builder googleSignInOptions$Builder0 = new Builder();
        if(arr_scope.length > 0) {
            googleSignInOptions$Builder0.requestScopes(arr_scope[0], arr_scope);
        }
        if(googleSignInAccount0 != null && !TextUtils.isEmpty(googleSignInAccount0.getEmail())) {
            googleSignInOptions$Builder0.setAccountName(((String)Preconditions.checkNotNull(googleSignInAccount0.getEmail())));
        }
        return new GoogleSignInClient(activity0, googleSignInOptions$Builder0.build()).getSignInIntent();
    }

    private static Scope[] zbb(List list0) {
        return list0 == null ? new Scope[0] : ((Scope[])list0.toArray(new Scope[list0.size()]));
    }
}

