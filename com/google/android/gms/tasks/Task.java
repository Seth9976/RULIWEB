package com.google.android.gms.tasks;

import android.app.Activity;
import java.util.concurrent.Executor;

public abstract class Task {
    public Task addOnCanceledListener(Activity activity0, OnCanceledListener onCanceledListener0) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }

    public Task addOnCanceledListener(OnCanceledListener onCanceledListener0) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }

    public Task addOnCanceledListener(Executor executor0, OnCanceledListener onCanceledListener0) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented");
    }

    public Task addOnCompleteListener(Activity activity0, OnCompleteListener onCompleteListener0) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public Task addOnCompleteListener(OnCompleteListener onCompleteListener0) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public Task addOnCompleteListener(Executor executor0, OnCompleteListener onCompleteListener0) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public abstract Task addOnFailureListener(Activity arg1, OnFailureListener arg2);

    public abstract Task addOnFailureListener(OnFailureListener arg1);

    public abstract Task addOnFailureListener(Executor arg1, OnFailureListener arg2);

    public abstract Task addOnSuccessListener(Activity arg1, OnSuccessListener arg2);

    public abstract Task addOnSuccessListener(OnSuccessListener arg1);

    public abstract Task addOnSuccessListener(Executor arg1, OnSuccessListener arg2);

    public Task continueWith(Continuation continuation0) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    public Task continueWith(Executor executor0, Continuation continuation0) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    public Task continueWithTask(Continuation continuation0) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }

    public Task continueWithTask(Executor executor0, Continuation continuation0) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }

    public abstract Exception getException();

    public abstract Object getResult();

    public abstract Object getResult(Class arg1) throws Throwable;

    public abstract boolean isCanceled();

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();

    public Task onSuccessTask(SuccessContinuation successContinuation0) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }

    public Task onSuccessTask(Executor executor0, SuccessContinuation successContinuation0) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }
}

