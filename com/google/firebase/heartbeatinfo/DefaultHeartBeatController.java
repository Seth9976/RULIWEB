package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.util.Base64OutputStream;
import androidx.core.os.UserManagerCompat;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.inject.Provider;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.zip.GZIPOutputStream;
import jeb.synthetic.TWR;
import org.json.JSONArray;
import org.json.JSONObject;

public class DefaultHeartBeatController implements HeartBeatController, HeartBeatInfo {
    private final Context applicationContext;
    private final Executor backgroundExecutor;
    private final Set consumers;
    private final Provider storageProvider;
    private final Provider userAgentProvider;

    private DefaultHeartBeatController(Context context0, String s, Set set0, Provider provider0, Executor executor0) {
        this(() -> new HeartBeatInfoStorage(context0, s), set0, executor0, provider0, context0);
    }

    DefaultHeartBeatController(Provider provider0, Set set0, Executor executor0, Provider provider1, Context context0) {
        this.storageProvider = provider0;
        this.consumers = set0;
        this.backgroundExecutor = executor0;
        this.userAgentProvider = provider1;
        this.applicationContext = context0;
    }

    public static Component component() {
        Qualified qualified0 = Qualified.qualified(Background.class, Executor.class);
        return Component.builder(DefaultHeartBeatController.class, new Class[]{HeartBeatController.class, HeartBeatInfo.class}).add(Dependency.required(Context.class)).add(Dependency.required(FirebaseApp.class)).add(Dependency.setOf(HeartBeatConsumer.class)).add(Dependency.requiredProvider(UserAgentPublisher.class)).add(Dependency.required(qualified0)).factory((ComponentContainer componentContainer0) -> new DefaultHeartBeatController(((Context)componentContainer0.get(Context.class)), ((FirebaseApp)componentContainer0.get(FirebaseApp.class)).getPersistenceKey(), componentContainer0.setOf(HeartBeatConsumer.class), componentContainer0.getProvider(UserAgentPublisher.class), ((Executor)componentContainer0.get(qualified0)))).build();
    }

    @Override  // com.google.firebase.heartbeatinfo.HeartBeatInfo
    public HeartBeat getHeartBeatCode(String s) {
        synchronized(this) {
            HeartBeatInfoStorage heartBeatInfoStorage0 = (HeartBeatInfoStorage)this.storageProvider.get();
            if(heartBeatInfoStorage0.shouldSendGlobalHeartBeat(System.currentTimeMillis())) {
                heartBeatInfoStorage0.postHeartBeatCleanUp();
                return HeartBeat.GLOBAL;
            }
            return HeartBeat.NONE;
        }
    }

    @Override  // com.google.firebase.heartbeatinfo.HeartBeatController
    public Task getHeartBeatsHeader() {
        if(!UserManagerCompat.isUserUnlocked(this.applicationContext)) {
            return Tasks.forResult("");
        }
        DefaultHeartBeatController..ExternalSyntheticLambda1 defaultHeartBeatController$$ExternalSyntheticLambda10 = () -> synchronized(this) {
            HeartBeatInfoStorage heartBeatInfoStorage0 = (HeartBeatInfoStorage)this.storageProvider.get();
            List list0 = heartBeatInfoStorage0.getAllHeartBeats();
            heartBeatInfoStorage0.deleteAllHeartBeats();
            JSONArray jSONArray0 = new JSONArray();
            for(int v1 = 0; v1 < list0.size(); ++v1) {
                HeartBeatResult heartBeatResult0 = (HeartBeatResult)list0.get(v1);
                JSONObject jSONObject0 = new JSONObject();
                jSONObject0.put("agent", heartBeatResult0.getUserAgent());
                jSONObject0.put("dates", new JSONArray(heartBeatResult0.getUsedDates()));
                jSONArray0.put(jSONObject0);
            }
            JSONObject jSONObject1 = new JSONObject();
            jSONObject1.put("heartbeats", jSONArray0);
            jSONObject1.put("version", "2");
            ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
            Base64OutputStream base64OutputStream0 = new Base64OutputStream(byteArrayOutputStream0, 11);
            try(GZIPOutputStream gZIPOutputStream0 = new GZIPOutputStream(base64OutputStream0)) {
                gZIPOutputStream0.write(jSONObject1.toString().getBytes("UTF-8"));
            }
            catch(Throwable throwable0) {
                TWR.safeClose$NT(base64OutputStream0, throwable0);
                throw throwable0;
            }
            base64OutputStream0.close();
            return byteArrayOutputStream0.toString("UTF-8");
        };
        return Tasks.call(this.backgroundExecutor, defaultHeartBeatController$$ExternalSyntheticLambda10);
    }

    // 检测为 Lambda 实现
    static DefaultHeartBeatController lambda$component$3(Qualified qualified0, ComponentContainer componentContainer0) [...]

    // 检测为 Lambda 实现
    String lambda$getHeartBeatsHeader$1$com-google-firebase-heartbeatinfo-DefaultHeartBeatController() throws Exception [...]

    // 检测为 Lambda 实现
    static HeartBeatInfoStorage lambda$new$2(Context context0, String s) [...]

    // 检测为 Lambda 实现
    Void lambda$registerHeartBeat$0$com-google-firebase-heartbeatinfo-DefaultHeartBeatController() throws Exception [...]

    public Task registerHeartBeat() {
        if(this.consumers.size() <= 0) {
            return Tasks.forResult(null);
        }
        if(!UserManagerCompat.isUserUnlocked(this.applicationContext)) {
            return Tasks.forResult(null);
        }
        DefaultHeartBeatController..ExternalSyntheticLambda3 defaultHeartBeatController$$ExternalSyntheticLambda30 = () -> synchronized(this) {
            ((HeartBeatInfoStorage)this.storageProvider.get()).storeHeartBeat(System.currentTimeMillis(), ((UserAgentPublisher)this.userAgentProvider.get()).getUserAgent());
            return null;
        };
        return Tasks.call(this.backgroundExecutor, defaultHeartBeatController$$ExternalSyntheticLambda30);
    }
}

