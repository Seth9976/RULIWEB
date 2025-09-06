package androidx.appcompat.widget;

class RtlSpacingHelper {
    public static final int UNDEFINED = 0x80000000;
    private int mEnd;
    private int mExplicitLeft;
    private int mExplicitRight;
    private boolean mIsRelative;
    private boolean mIsRtl;
    private int mLeft;
    private int mRight;
    private int mStart;

    RtlSpacingHelper() {
        this.mLeft = 0;
        this.mRight = 0;
        this.mStart = 0x80000000;
        this.mEnd = 0x80000000;
        this.mExplicitLeft = 0;
        this.mExplicitRight = 0;
        this.mIsRtl = false;
        this.mIsRelative = false;
    }

    // 去混淆评级： 低(20)
    public int getEnd() {
        return this.mIsRtl ? this.mLeft : this.mRight;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    // 去混淆评级： 低(20)
    public int getStart() {
        return this.mIsRtl ? this.mRight : this.mLeft;
    }

    public void setAbsolute(int v, int v1) {
        this.mIsRelative = false;
        if(v != 0x80000000) {
            this.mExplicitLeft = v;
            this.mLeft = v;
        }
        if(v1 != 0x80000000) {
            this.mExplicitRight = v1;
            this.mRight = v1;
        }
    }

    public void setDirection(boolean z) {
        if(z == this.mIsRtl) {
            return;
        }
        this.mIsRtl = z;
        if(this.mIsRelative) {
            if(z) {
                this.mLeft = this.mEnd == 0x80000000 ? this.mExplicitLeft : this.mEnd;
                this.mRight = this.mStart == 0x80000000 ? this.mExplicitRight : this.mStart;
                return;
            }
            this.mLeft = this.mStart == 0x80000000 ? this.mExplicitLeft : this.mStart;
            this.mRight = this.mEnd == 0x80000000 ? this.mExplicitRight : this.mEnd;
            return;
        }
        this.mLeft = this.mExplicitLeft;
        this.mRight = this.mExplicitRight;
    }

    public void setRelative(int v, int v1) {
        this.mStart = v;
        this.mEnd = v1;
        this.mIsRelative = true;
        if(this.mIsRtl) {
            if(v1 != 0x80000000) {
                this.mLeft = v1;
            }
            if(v != 0x80000000) {
                this.mRight = v;
            }
        }
        else {
            if(v != 0x80000000) {
                this.mLeft = v;
            }
            if(v1 != 0x80000000) {
                this.mRight = v1;
            }
        }
    }
}

