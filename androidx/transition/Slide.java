package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.core.content.res.TypedArrayUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;

public class Slide extends Visibility {
    interface CalculateSlide {
        float getGoneX(ViewGroup arg1, View arg2);

        float getGoneY(ViewGroup arg1, View arg2);
    }

    static abstract class CalculateSlideHorizontal implements CalculateSlide {
        private CalculateSlideHorizontal() {
        }

        CalculateSlideHorizontal(androidx.transition.Slide.1 slide$10) {
        }

        @Override  // androidx.transition.Slide$CalculateSlide
        public float getGoneY(ViewGroup viewGroup0, View view0) {
            return view0.getTranslationY();
        }
    }

    static abstract class CalculateSlideVertical implements CalculateSlide {
        private CalculateSlideVertical() {
        }

        CalculateSlideVertical(androidx.transition.Slide.1 slide$10) {
        }

        @Override  // androidx.transition.Slide$CalculateSlide
        public float getGoneX(ViewGroup viewGroup0, View view0) {
            return view0.getTranslationX();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GravityFlag {
    }

    private static final String PROPNAME_SCREEN_POSITION = "android:slide:screenPosition";
    private CalculateSlide mSlideCalculator;
    private int mSlideEdge;
    private static final TimeInterpolator sAccelerate;
    private static final CalculateSlide sCalculateBottom;
    private static final CalculateSlide sCalculateEnd;
    private static final CalculateSlide sCalculateLeft;
    private static final CalculateSlide sCalculateRight;
    private static final CalculateSlide sCalculateStart;
    private static final CalculateSlide sCalculateTop;
    private static final TimeInterpolator sDecelerate;

    static {
        Slide.sDecelerate = new DecelerateInterpolator();
        Slide.sAccelerate = new AccelerateInterpolator();
        Slide.sCalculateLeft = new CalculateSlideHorizontal() {
            {
                super(null);
            }

            @Override  // androidx.transition.Slide$CalculateSlide
            public float getGoneX(ViewGroup viewGroup0, View view0) {
                return view0.getTranslationX() - ((float)viewGroup0.getWidth());
            }
        };
        Slide.sCalculateStart = new CalculateSlideHorizontal() {
            {
                super(null);
            }

            @Override  // androidx.transition.Slide$CalculateSlide
            public float getGoneX(ViewGroup viewGroup0, View view0) {
                return viewGroup0.getLayoutDirection() == 1 ? view0.getTranslationX() + ((float)viewGroup0.getWidth()) : view0.getTranslationX() - ((float)viewGroup0.getWidth());
            }
        };
        Slide.sCalculateTop = new CalculateSlideVertical() {
            {
                super(null);
            }

            @Override  // androidx.transition.Slide$CalculateSlide
            public float getGoneY(ViewGroup viewGroup0, View view0) {
                return view0.getTranslationY() - ((float)viewGroup0.getHeight());
            }
        };
        Slide.sCalculateRight = new CalculateSlideHorizontal() {
            {
                super(null);
            }

            @Override  // androidx.transition.Slide$CalculateSlide
            public float getGoneX(ViewGroup viewGroup0, View view0) {
                return view0.getTranslationX() + ((float)viewGroup0.getWidth());
            }
        };
        Slide.sCalculateEnd = new CalculateSlideHorizontal() {
            {
                super(null);
            }

            @Override  // androidx.transition.Slide$CalculateSlide
            public float getGoneX(ViewGroup viewGroup0, View view0) {
                return viewGroup0.getLayoutDirection() == 1 ? view0.getTranslationX() - ((float)viewGroup0.getWidth()) : view0.getTranslationX() + ((float)viewGroup0.getWidth());
            }
        };
        Slide.sCalculateBottom = new CalculateSlideVertical() {
            {
                super(null);
            }

            @Override  // androidx.transition.Slide$CalculateSlide
            public float getGoneY(ViewGroup viewGroup0, View view0) {
                return view0.getTranslationY() + ((float)viewGroup0.getHeight());
            }
        };
    }

    public Slide() {
        this.mSlideCalculator = Slide.sCalculateBottom;
        this.mSlideEdge = 80;
        this.setSlideEdge(80);
    }

    public Slide(int v) {
        this.mSlideCalculator = Slide.sCalculateBottom;
        this.mSlideEdge = 80;
        this.setSlideEdge(v);
    }

    public Slide(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mSlideCalculator = Slide.sCalculateBottom;
        this.mSlideEdge = 80;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, Styleable.SLIDE);
        int v = TypedArrayUtils.getNamedInt(typedArray0, ((XmlPullParser)attributeSet0), "slideEdge", 0, 80);
        typedArray0.recycle();
        this.setSlideEdge(v);
    }

    @Override  // androidx.transition.Visibility
    public void captureEndValues(TransitionValues transitionValues0) {
        super.captureEndValues(transitionValues0);
        this.captureValues(transitionValues0);
    }

    @Override  // androidx.transition.Visibility
    public void captureStartValues(TransitionValues transitionValues0) {
        super.captureStartValues(transitionValues0);
        this.captureValues(transitionValues0);
    }

    private void captureValues(TransitionValues transitionValues0) {
        int[] arr_v = new int[2];
        transitionValues0.view.getLocationOnScreen(arr_v);
        transitionValues0.values.put("android:slide:screenPosition", arr_v);
    }

    public int getSlideEdge() {
        return this.mSlideEdge;
    }

    @Override  // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }

    @Override  // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        if(transitionValues1 == null) {
            return null;
        }
        int[] arr_v = (int[])transitionValues1.values.get("android:slide:screenPosition");
        float f = view0.getTranslationX();
        float f1 = view0.getTranslationY();
        float f2 = this.mSlideCalculator.getGoneX(viewGroup0, view0);
        float f3 = this.mSlideCalculator.getGoneY(viewGroup0, view0);
        return TranslationAnimationCreator.createAnimation(view0, transitionValues1, arr_v[0], arr_v[1], f2, f3, f, f1, Slide.sDecelerate, this);
    }

    @Override  // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        if(transitionValues0 == null) {
            return null;
        }
        int[] arr_v = (int[])transitionValues0.values.get("android:slide:screenPosition");
        float f = view0.getTranslationX();
        float f1 = view0.getTranslationY();
        float f2 = this.mSlideCalculator.getGoneX(viewGroup0, view0);
        float f3 = this.mSlideCalculator.getGoneY(viewGroup0, view0);
        return TranslationAnimationCreator.createAnimation(view0, transitionValues0, arr_v[0], arr_v[1], f, f1, f2, f3, Slide.sAccelerate, this);
    }

    public void setSlideEdge(int v) {
        switch(v) {
            case 3: {
                this.mSlideCalculator = Slide.sCalculateLeft;
                break;
            }
            case 5: {
                this.mSlideCalculator = Slide.sCalculateRight;
                break;
            }
            case 0x30: {
                this.mSlideCalculator = Slide.sCalculateTop;
                break;
            }
            case 80: {
                this.mSlideCalculator = Slide.sCalculateBottom;
                break;
            }
            case 0x800003: {
                this.mSlideCalculator = Slide.sCalculateStart;
                break;
            }
            case 0x800005: {
                this.mSlideCalculator = Slide.sCalculateEnd;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid slide direction");
            }
        }
        this.mSlideEdge = v;
        SidePropagation sidePropagation0 = new SidePropagation();
        sidePropagation0.setSide(v);
        this.setPropagation(sidePropagation0);
    }
}

