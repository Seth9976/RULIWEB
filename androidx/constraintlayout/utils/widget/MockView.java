package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.R.styleable;

public class MockView extends View {
    private int mDiagonalsColor;
    private boolean mDrawDiagonals;
    private boolean mDrawLabel;
    private int mMargin;
    private Paint mPaintDiagonals;
    private Paint mPaintText;
    private Paint mPaintTextBackground;
    protected String mText;
    private int mTextBackgroundColor;
    private Rect mTextBounds;
    private int mTextColor;

    public MockView(Context context0) {
        super(context0);
        this.mPaintDiagonals = new Paint();
        this.mPaintText = new Paint();
        this.mPaintTextBackground = new Paint();
        this.mDrawDiagonals = true;
        this.mDrawLabel = true;
        this.mText = null;
        this.mTextBounds = new Rect();
        this.mDiagonalsColor = Color.argb(0xFF, 0, 0, 0);
        this.mTextColor = Color.argb(0xFF, 200, 200, 200);
        this.mTextBackgroundColor = Color.argb(0xFF, 50, 50, 50);
        this.mMargin = 4;
        this.init(context0, null);
    }

    public MockView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mPaintDiagonals = new Paint();
        this.mPaintText = new Paint();
        this.mPaintTextBackground = new Paint();
        this.mDrawDiagonals = true;
        this.mDrawLabel = true;
        this.mText = null;
        this.mTextBounds = new Rect();
        this.mDiagonalsColor = Color.argb(0xFF, 0, 0, 0);
        this.mTextColor = Color.argb(0xFF, 200, 200, 200);
        this.mTextBackgroundColor = Color.argb(0xFF, 50, 50, 50);
        this.mMargin = 4;
        this.init(context0, attributeSet0);
    }

    public MockView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mPaintDiagonals = new Paint();
        this.mPaintText = new Paint();
        this.mPaintTextBackground = new Paint();
        this.mDrawDiagonals = true;
        this.mDrawLabel = true;
        this.mText = null;
        this.mTextBounds = new Rect();
        this.mDiagonalsColor = Color.argb(0xFF, 0, 0, 0);
        this.mTextColor = Color.argb(0xFF, 200, 200, 200);
        this.mTextBackgroundColor = Color.argb(0xFF, 50, 50, 50);
        this.mMargin = 4;
        this.init(context0, attributeSet0);
    }

    private void init(Context context0, AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.MockView);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.MockView_mock_label) {
                    this.mText = typedArray0.getString(v2);
                }
                else if(v2 == styleable.MockView_mock_showDiagonals) {
                    this.mDrawDiagonals = typedArray0.getBoolean(v2, this.mDrawDiagonals);
                }
                else if(v2 == styleable.MockView_mock_diagonalsColor) {
                    this.mDiagonalsColor = typedArray0.getColor(v2, this.mDiagonalsColor);
                }
                else if(v2 == styleable.MockView_mock_labelBackgroundColor) {
                    this.mTextBackgroundColor = typedArray0.getColor(v2, this.mTextBackgroundColor);
                }
                else if(v2 == styleable.MockView_mock_labelColor) {
                    this.mTextColor = typedArray0.getColor(v2, this.mTextColor);
                }
                else if(v2 == styleable.MockView_mock_showLabel) {
                    this.mDrawLabel = typedArray0.getBoolean(v2, this.mDrawLabel);
                }
            }
            typedArray0.recycle();
        }
        if(this.mText == null) {
            try {
                this.mText = context0.getResources().getResourceEntryName(this.getId());
            }
            catch(Exception unused_ex) {
            }
        }
        this.mPaintDiagonals.setColor(this.mDiagonalsColor);
        this.mPaintDiagonals.setAntiAlias(true);
        this.mPaintText.setColor(this.mTextColor);
        this.mPaintText.setAntiAlias(true);
        this.mPaintTextBackground.setColor(this.mTextBackgroundColor);
        this.mMargin = Math.round(((float)this.mMargin) * (this.getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override  // android.view.View
    public void onDraw(Canvas canvas0) {
        super.onDraw(canvas0);
        int v = this.getWidth();
        int v1 = this.getHeight();
        if(this.mDrawDiagonals) {
            --v;
            --v1;
            canvas0.drawLine(0.0f, 0.0f, ((float)v), ((float)v1), this.mPaintDiagonals);
            canvas0.drawLine(0.0f, ((float)v1), ((float)v), 0.0f, this.mPaintDiagonals);
            canvas0.drawLine(0.0f, 0.0f, ((float)v), 0.0f, this.mPaintDiagonals);
            canvas0.drawLine(((float)v), 0.0f, ((float)v), ((float)v1), this.mPaintDiagonals);
            canvas0.drawLine(((float)v), ((float)v1), 0.0f, ((float)v1), this.mPaintDiagonals);
            canvas0.drawLine(0.0f, ((float)v1), 0.0f, 0.0f, this.mPaintDiagonals);
        }
        String s = this.mText;
        if(s != null && this.mDrawLabel) {
            this.mPaintText.getTextBounds(s, 0, s.length(), this.mTextBounds);
            float f = ((float)(v - this.mTextBounds.width())) / 2.0f;
            float f1 = ((float)(v1 - this.mTextBounds.height())) / 2.0f + ((float)this.mTextBounds.height());
            this.mTextBounds.offset(((int)f), ((int)f1));
            this.mTextBounds.set(this.mTextBounds.left - this.mMargin, this.mTextBounds.top - this.mMargin, this.mTextBounds.right + this.mMargin, this.mTextBounds.bottom + this.mMargin);
            canvas0.drawRect(this.mTextBounds, this.mPaintTextBackground);
            canvas0.drawText(this.mText, f, f1, this.mPaintText);
        }
    }
}

