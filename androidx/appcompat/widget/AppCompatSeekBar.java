package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R.attr;

public class AppCompatSeekBar extends SeekBar {
    private final AppCompatSeekBarHelper mAppCompatSeekBarHelper;

    public AppCompatSeekBar(Context context0) {
        this(context0, null);
    }

    public AppCompatSeekBar(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.seekBarStyle);
    }

    public AppCompatSeekBar(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        ThemeUtils.checkAppCompatTheme(this, this.getContext());
        AppCompatSeekBarHelper appCompatSeekBarHelper0 = new AppCompatSeekBarHelper(this);
        this.mAppCompatSeekBarHelper = appCompatSeekBarHelper0;
        appCompatSeekBarHelper0.loadFromAttributes(attributeSet0, v);
    }

    @Override  // android.widget.AbsSeekBar
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.mAppCompatSeekBarHelper.drawableStateChanged();
    }

    @Override  // android.widget.AbsSeekBar
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.mAppCompatSeekBarHelper.jumpDrawablesToCurrentState();
    }

    @Override  // android.widget.AbsSeekBar
    protected void onDraw(Canvas canvas0) {
        synchronized(this) {
            super.onDraw(canvas0);
            this.mAppCompatSeekBarHelper.drawTickMarks(canvas0);
        }
    }
}

