package com.google.firebase.messaging;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;

class RequestDeduplicator {
    interface GetTokenRequest {
        Task start();
    }

    private final Executor executor;
    private final Map getTokenRequests;

    RequestDeduplicator(Executor executor0) {
        this.getTokenRequests = new ArrayMap();
        this.executor = executor0;
    }

    Task getOrStartGetTokenRequest(String s, GetTokenRequest requestDeduplicator$GetTokenRequest0) {
        synchronized(this) {
            Task task0 = (Task)this.getTokenRequests.get(s);
            if(task0 != null) {
                if(Log.isLoggable("FirebaseMessaging", 3)) {
                    Log.d("FirebaseMessaging", "Joining ongoing request for: " + s);
                }
                return task0;
            }
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Making new request for: " + s);
            }
            Task task1 = requestDeduplicator$GetTokenRequest0.start();
            RequestDeduplicator..ExternalSyntheticLambda0 requestDeduplicator$$ExternalSyntheticLambda00 = (Task task0) -> synchronized(this) {
                this.getTokenRequests.remove(s);
                return task0;
            };
            Task task2 = task1.continueWithTask(this.executor, requestDeduplicator$$ExternalSyntheticLambda00);
            this.getTokenRequests.put(s, task2);
            return task2;
        }
    }

    // 检测为 Lambda 实现
    Task lambda$getOrStartGetTokenRequest$0$com-google-firebase-messaging-RequestDeduplicator(String s, Task task0) throws Exception [...]
}

