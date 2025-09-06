package com.google.android.gms.tasks;

public final class DuplicateTaskCompletionException extends IllegalStateException {
    private DuplicateTaskCompletionException(String s, Throwable throwable0) {
        super(s, throwable0);
    }

    public static IllegalStateException of(Task task0) {
        if(!task0.isComplete()) {
            return new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
        }
        Exception exception0 = task0.getException();
        if(exception0 != null) {
            return new DuplicateTaskCompletionException("Complete with: failure", exception0);
        }
        if(task0.isSuccessful()) {
            return new DuplicateTaskCompletionException("Complete with: " + ("result " + task0.getResult()), null);
        }
        return task0.isCanceled() ? new DuplicateTaskCompletionException("Complete with: cancellation", null) : new DuplicateTaskCompletionException("Complete with: unknown issue", null);
    }
}

