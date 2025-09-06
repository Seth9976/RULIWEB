package androidx.appcompat.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

class ActionBarBackgroundDrawable extends Drawable {
    static class Api21Impl {
        public static void getOutline(Drawable drawable0, Outline outline0) {
            drawable0.getOutline(outline0);
        }
    }

    final ActionBarContainer mContainer;

    public ActionBarBackgroundDrawable(ActionBarContainer actionBarContainer0) {
        this.mContainer = actionBarContainer0;
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        if(!this.mContainer.mIsSplit) {
            if(this.mContainer.mBackground != null) {
                this.mContainer.mBackground.draw(canvas0);
            }
            if(this.mContainer.mStackedBackground != null && this.mContainer.mIsStacked) {
                this.mContainer.mStackedBackground.draw(canvas0);
            }
        }
        else if(this.mContainer.mSplitBackground != null) {
            this.mContainer.mSplitBackground.draw(canvas0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override  // android.graphics.drawable.Drawable
    public void getOutline(Outline outline0) {
        if(this.mContainer.mIsSplit) {
            if(this.mContainer.mSplitBackground != null) {
                Api21Impl.getOutline(this.mContainer.mBackground, outline0);
            }
        }
        else if(this.mContainer.mBackground != null) {
            Api21Impl.getOutline(this.mContainer.mBackground, outline0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
    }
}

