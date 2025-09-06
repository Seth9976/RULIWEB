package androidx.transition;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Scene {
    private Context mContext;
    private Runnable mEnterAction;
    private Runnable mExitAction;
    private View mLayout;
    private int mLayoutId;
    private ViewGroup mSceneRoot;

    public Scene(ViewGroup viewGroup0) {
        this.mLayoutId = -1;
        this.mSceneRoot = viewGroup0;
    }

    private Scene(ViewGroup viewGroup0, int v, Context context0) {
        this.mContext = context0;
        this.mSceneRoot = viewGroup0;
        this.mLayoutId = v;
    }

    public Scene(ViewGroup viewGroup0, View view0) {
        this.mLayoutId = -1;
        this.mSceneRoot = viewGroup0;
        this.mLayout = view0;
    }

    public void enter() {
        if(this.mLayoutId > 0 || this.mLayout != null) {
            this.getSceneRoot().removeAllViews();
            if(this.mLayoutId > 0) {
                LayoutInflater.from(this.mContext).inflate(this.mLayoutId, this.mSceneRoot);
            }
            else {
                this.mSceneRoot.addView(this.mLayout);
            }
        }
        Runnable runnable0 = this.mEnterAction;
        if(runnable0 != null) {
            runnable0.run();
        }
        Scene.setCurrentScene(this.mSceneRoot, this);
    }

    public void exit() {
        if(Scene.getCurrentScene(this.mSceneRoot) == this) {
            Runnable runnable0 = this.mExitAction;
            if(runnable0 != null) {
                runnable0.run();
            }
        }
    }

    public static Scene getCurrentScene(ViewGroup viewGroup0) {
        return (Scene)viewGroup0.getTag(id.transition_current_scene);
    }

    public static Scene getSceneForLayout(ViewGroup viewGroup0, int v, Context context0) {
        SparseArray sparseArray0 = (SparseArray)viewGroup0.getTag(id.transition_scene_layoutid_cache);
        if(sparseArray0 == null) {
            sparseArray0 = new SparseArray();
            viewGroup0.setTag(id.transition_scene_layoutid_cache, sparseArray0);
        }
        Scene scene0 = (Scene)sparseArray0.get(v);
        if(scene0 != null) {
            return scene0;
        }
        Scene scene1 = new Scene(viewGroup0, v, context0);
        sparseArray0.put(v, scene1);
        return scene1;
    }

    public ViewGroup getSceneRoot() {
        return this.mSceneRoot;
    }

    boolean isCreatedFromLayoutResource() {
        return this.mLayoutId > 0;
    }

    static void setCurrentScene(ViewGroup viewGroup0, Scene scene0) {
        viewGroup0.setTag(id.transition_current_scene, scene0);
    }

    public void setEnterAction(Runnable runnable0) {
        this.mEnterAction = runnable0;
    }

    public void setExitAction(Runnable runnable0) {
        this.mExitAction = runnable0;
    }
}

