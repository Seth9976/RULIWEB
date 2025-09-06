package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.KeyAttributes;
import androidx.constraintlayout.motion.widget.KeyPosition;
import androidx.constraintlayout.motion.widget.MotionController;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R.styleable;
import java.util.HashMap;

public class MotionEffect extends MotionHelper {
    public static final int AUTO = -1;
    public static final int EAST = 2;
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final String TAG = "FadeMove";
    private static final int UNSET = -1;
    public static final int WEST = 3;
    private int mFadeMove;
    private float mMotionEffectAlpha;
    private int mMotionEffectEnd;
    private int mMotionEffectStart;
    private boolean mMotionEffectStrictMove;
    private int mMotionEffectTranslationX;
    private int mMotionEffectTranslationY;
    private int mViewTransitionId;

    public MotionEffect(Context context0) {
        super(context0);
        this.mMotionEffectAlpha = 0.1f;
        this.mMotionEffectStart = 49;
        this.mMotionEffectEnd = 50;
        this.mMotionEffectTranslationX = 0;
        this.mMotionEffectTranslationY = 0;
        this.mMotionEffectStrictMove = true;
        this.mViewTransitionId = -1;
        this.mFadeMove = -1;
    }

    public MotionEffect(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mMotionEffectAlpha = 0.1f;
        this.mMotionEffectStart = 49;
        this.mMotionEffectEnd = 50;
        this.mMotionEffectTranslationX = 0;
        this.mMotionEffectTranslationY = 0;
        this.mMotionEffectStrictMove = true;
        this.mViewTransitionId = -1;
        this.mFadeMove = -1;
        this.init(context0, attributeSet0);
    }

    public MotionEffect(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mMotionEffectAlpha = 0.1f;
        this.mMotionEffectStart = 49;
        this.mMotionEffectEnd = 50;
        this.mMotionEffectTranslationX = 0;
        this.mMotionEffectTranslationY = 0;
        this.mMotionEffectStrictMove = true;
        this.mViewTransitionId = -1;
        this.mFadeMove = -1;
        this.init(context0, attributeSet0);
    }

    private void init(Context context0, AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.MotionEffect);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.MotionEffect_motionEffect_start) {
                    this.mMotionEffectStart = Math.max(Math.min(typedArray0.getInt(v2, this.mMotionEffectStart), 99), 0);
                }
                else if(v2 == styleable.MotionEffect_motionEffect_end) {
                    this.mMotionEffectEnd = Math.max(Math.min(typedArray0.getInt(v2, this.mMotionEffectEnd), 99), 0);
                }
                else if(v2 == styleable.MotionEffect_motionEffect_translationX) {
                    this.mMotionEffectTranslationX = typedArray0.getDimensionPixelOffset(v2, this.mMotionEffectTranslationX);
                }
                else if(v2 == styleable.MotionEffect_motionEffect_translationY) {
                    this.mMotionEffectTranslationY = typedArray0.getDimensionPixelOffset(v2, this.mMotionEffectTranslationY);
                }
                else if(v2 == styleable.MotionEffect_motionEffect_alpha) {
                    this.mMotionEffectAlpha = typedArray0.getFloat(v2, this.mMotionEffectAlpha);
                }
                else if(v2 == styleable.MotionEffect_motionEffect_move) {
                    this.mFadeMove = typedArray0.getInt(v2, this.mFadeMove);
                }
                else if(v2 == styleable.MotionEffect_motionEffect_strict) {
                    this.mMotionEffectStrictMove = typedArray0.getBoolean(v2, this.mMotionEffectStrictMove);
                }
                else if(v2 == styleable.MotionEffect_motionEffect_viewTransition) {
                    this.mViewTransitionId = typedArray0.getResourceId(v2, this.mViewTransitionId);
                }
            }
            int v3 = this.mMotionEffectStart;
            int v4 = this.mMotionEffectEnd;
            if(v3 == v4) {
                if(v3 > 0) {
                    this.mMotionEffectStart = v3 - 1;
                }
                else {
                    this.mMotionEffectEnd = v4 + 1;
                }
            }
            typedArray0.recycle();
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelper
    public boolean isDecorator() {
        return true;
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelper
    public void onPreSetup(MotionLayout motionLayout0, HashMap hashMap0) {
        KeyAttributes keyAttributes5;
        KeyAttributes keyAttributes4;
        KeyAttributes keyAttributes3;
        View[] arr_view = this.getViews(((ConstraintLayout)this.getParent()));
        if(arr_view == null) {
            Log.v("FadeMove", ".(null:-1) onPreSetup() views = null");
            return;
        }
        KeyAttributes keyAttributes0 = new KeyAttributes();
        KeyAttributes keyAttributes1 = new KeyAttributes();
        keyAttributes0.setValue("alpha", this.mMotionEffectAlpha);
        keyAttributes1.setValue("alpha", this.mMotionEffectAlpha);
        keyAttributes0.setFramePosition(this.mMotionEffectStart);
        keyAttributes1.setFramePosition(this.mMotionEffectEnd);
        KeyPosition keyPosition0 = new KeyPosition();
        keyPosition0.setFramePosition(this.mMotionEffectStart);
        keyPosition0.setType(0);
        keyPosition0.setValue("percentX", 0);
        keyPosition0.setValue("percentY", 0);
        KeyPosition keyPosition1 = new KeyPosition();
        keyPosition1.setFramePosition(this.mMotionEffectEnd);
        keyPosition1.setType(0);
        keyPosition1.setValue("percentX", 1);
        keyPosition1.setValue("percentY", 1);
        KeyAttributes keyAttributes2 = null;
        if(this.mMotionEffectTranslationX > 0) {
            keyAttributes3 = new KeyAttributes();
            keyAttributes4 = new KeyAttributes();
            keyAttributes3.setValue("translationX", this.mMotionEffectTranslationX);
            keyAttributes3.setFramePosition(this.mMotionEffectEnd);
            keyAttributes4.setValue("translationX", 0);
            keyAttributes4.setFramePosition(this.mMotionEffectEnd - 1);
        }
        else {
            keyAttributes3 = null;
            keyAttributes4 = null;
        }
        if(this.mMotionEffectTranslationY > 0) {
            keyAttributes2 = new KeyAttributes();
            keyAttributes5 = new KeyAttributes();
            keyAttributes2.setValue("translationY", this.mMotionEffectTranslationY);
            keyAttributes2.setFramePosition(this.mMotionEffectEnd);
            keyAttributes5.setValue("translationY", 0);
            keyAttributes5.setFramePosition(this.mMotionEffectEnd - 1);
        }
        else {
            keyAttributes5 = null;
        }
        int v1 = this.mFadeMove;
        if(v1 == -1) {
            int[] arr_v = new int[4];
            for(int v2 = 0; v2 < arr_view.length; ++v2) {
                MotionController motionController0 = (MotionController)hashMap0.get(arr_view[v2]);
                if(motionController0 != null) {
                    float f = motionController0.getFinalX() - motionController0.getStartX();
                    float f1 = motionController0.getFinalY() - motionController0.getStartY();
                    if(f1 < 0.0f) {
                        ++arr_v[1];
                    }
                    if(f1 > 0.0f) {
                        ++arr_v[0];
                    }
                    if(f > 0.0f) {
                        ++arr_v[3];
                    }
                    if(f < 0.0f) {
                        ++arr_v[2];
                    }
                }
            }
            int v3 = arr_v[0];
            int v4 = 0;
            for(int v = 1; v < 4; ++v) {
                int v5 = arr_v[v];
                if(v3 < v5) {
                    v4 = v;
                    v3 = v5;
                }
            }
            v1 = v4;
        }
        int v6 = 0;
        while(v6 < arr_view.length) {
            MotionController motionController1 = (MotionController)hashMap0.get(arr_view[v6]);
            if(motionController1 != null) {
                float f2 = motionController1.getFinalX() - motionController1.getStartX();
                float f3 = motionController1.getFinalY() - motionController1.getStartY();
                if(v1 != 0) {
                    switch(v1) {
                        case 1: {
                            if(f3 >= 0.0f || this.mMotionEffectStrictMove && f2 != 0.0f) {
                                goto label_86;
                            }
                            else {
                                break;
                            }
                            goto label_83;
                        }
                        case 2: {
                        label_83:
                            if(f2 >= 0.0f || this.mMotionEffectStrictMove && f3 != 0.0f) {
                                goto label_86;
                            }
                            else {
                                break;
                            }
                            goto label_85;
                        }
                        case 3: {
                        label_85:
                            if(f2 <= 0.0f || this.mMotionEffectStrictMove && f3 != 0.0f) {
                            label_86:
                                int v7 = this.mViewTransitionId;
                                if(v7 == -1) {
                                    motionController1.addKey(keyAttributes0);
                                    motionController1.addKey(keyAttributes1);
                                    motionController1.addKey(keyPosition0);
                                    motionController1.addKey(keyPosition1);
                                    if(this.mMotionEffectTranslationX > 0) {
                                        motionController1.addKey(keyAttributes3);
                                        motionController1.addKey(keyAttributes4);
                                    }
                                    if(this.mMotionEffectTranslationY > 0) {
                                        motionController1.addKey(keyAttributes2);
                                        motionController1.addKey(keyAttributes5);
                                    }
                                }
                                else {
                                    motionLayout0.applyViewTransition(v7, motionController1);
                                }
                            }
                            break;
                        }
                        default: {
                            goto label_86;
                        }
                    }
                }
                else if(f3 <= 0.0f || this.mMotionEffectStrictMove && f2 != 0.0f) {
                    goto label_86;
                }
            }
            ++v6;
        }
    }
}

