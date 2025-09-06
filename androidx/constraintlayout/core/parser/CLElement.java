package androidx.constraintlayout.core.parser;

import java.util.Arrays;
import java.util.Objects;

public class CLElement implements Cloneable {
    protected CLContainer mContainer;
    private final char[] mContent;
    protected long mEnd;
    private int mLine;
    protected long mStart;
    protected static int sBaseIndent = 2;
    protected static int sMaxLine = 80;

    static {
    }

    public CLElement(char[] arr_c) {
        this.mStart = -1L;
        this.mEnd = 0x7FFFFFFFFFFFFFFFL;
        this.mContent = arr_c;
    }

    protected void addIndent(StringBuilder stringBuilder0, int v) {
        for(int v1 = 0; v1 < v; ++v1) {
            stringBuilder0.append(' ');
        }
    }

    public CLElement clone() {
        try {
            return (CLElement)super.clone();
        }
        catch(CloneNotSupportedException unused_ex) {
            throw new AssertionError();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public String content() {
        String s = new String(this.mContent);
        if(s.length() < 1) {
            return "";
        }
        long v = this.mEnd;
        if(v != 0x7FFFFFFFFFFFFFFFL) {
            return v < this.mStart ? s.substring(((int)this.mStart), ((int)this.mStart) + 1) : s.substring(((int)this.mStart), ((int)v) + 1);
        }
        return s.substring(((int)this.mStart), ((int)this.mStart) + 1);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof CLElement)) {
            return false;
        }
        if(this.mStart != ((CLElement)object0).mStart) {
            return false;
        }
        if(this.mEnd != ((CLElement)object0).mEnd) {
            return false;
        }
        if(this.mLine != ((CLElement)object0).mLine) {
            return false;
        }
        return Arrays.equals(this.mContent, ((CLElement)object0).mContent) ? Objects.equals(this.mContainer, ((CLElement)object0).mContainer) : false;
    }

    public CLElement getContainer() {
        return this.mContainer;
    }

    // 去混淆评级： 低(20)
    protected String getDebugName() {
        return CLParser.sDebug ? this.getStrClass() + " -> " : "";
    }

    public long getEnd() {
        return this.mEnd;
    }

    // 去混淆评级： 低(20)
    public float getFloat() {
        return this instanceof CLNumber ? ((CLNumber)this).getFloat() : NaNf;
    }

    // 去混淆评级： 低(20)
    public int getInt() {
        return this instanceof CLNumber ? ((CLNumber)this).getInt() : 0;
    }

    public int getLine() {
        return this.mLine;
    }

    public long getStart() {
        return this.mStart;
    }

    protected String getStrClass() {
        String s = this.getClass().toString();
        return s.substring(s.lastIndexOf(46) + 1);
    }

    public boolean hasContent() {
        return this.mContent != null && this.mContent.length >= 1;
    }

    @Override
    public int hashCode() {
        int v = ((Arrays.hashCode(this.mContent) * 0x1F + ((int)(this.mStart ^ this.mStart >>> 0x20))) * 0x1F + ((int)(this.mEnd ^ this.mEnd >>> 0x20))) * 0x1F;
        return this.mContainer == null ? v * 0x1F + this.mLine : (v + this.mContainer.hashCode()) * 0x1F + this.mLine;
    }

    public boolean isDone() {
        return this.mEnd != 0x7FFFFFFFFFFFFFFFL;
    }

    public boolean isStarted() {
        return this.mStart > -1L;
    }

    public boolean notStarted() {
        return this.mStart == -1L;
    }

    public void setContainer(CLContainer cLContainer0) {
        this.mContainer = cLContainer0;
    }

    public void setEnd(long v) {
        if(this.mEnd == 0x7FFFFFFFFFFFFFFFL) {
            this.mEnd = v;
            if(CLParser.sDebug) {
                System.out.println("closing " + this.hashCode() + " -> " + this);
            }
            CLContainer cLContainer0 = this.mContainer;
            if(cLContainer0 != null) {
                cLContainer0.add(this);
            }
        }
    }

    public void setLine(int v) {
        this.mLine = v;
    }

    public void setStart(long v) {
        this.mStart = v;
    }

    protected String toFormattedJSON(int v, int v1) {
        return "";
    }

    protected String toJSON() [...] // 潜在的解密器

    @Override
    public String toString() {
        if(this.mStart <= this.mEnd && this.mEnd != 0x7FFFFFFFFFFFFFFFL) {
            String s = new String(this.mContent).substring(((int)this.mStart), ((int)this.mEnd) + 1);
            return this.getStrClass() + " (" + this.mStart + " : " + this.mEnd + ") <<" + s + ">>";
        }
        return this.getClass() + " (INVALID, " + this.mStart + "-" + this.mEnd + ")";
    }
}

