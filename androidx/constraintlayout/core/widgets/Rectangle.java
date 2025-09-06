package androidx.constraintlayout.core.widgets;

public class Rectangle {
    public int height;
    public int width;
    public int x;
    public int y;

    public boolean contains(int v, int v1) {
        return v >= this.x && v < this.x + this.width && (v1 >= this.y && v1 < this.y + this.height);
    }

    public int getCenterX() {
        return (this.x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.y + this.height) / 2;
    }

    void grow(int v, int v1) {
        this.x -= v;
        this.y -= v1;
        this.width += v * 2;
        this.height += v1 * 2;
    }

    boolean intersects(Rectangle rectangle0) {
        return this.x >= rectangle0.x && this.x < rectangle0.x + rectangle0.width && (this.y >= rectangle0.y && this.y < rectangle0.y + rectangle0.height);
    }

    public void setBounds(int v, int v1, int v2, int v3) {
        this.x = v;
        this.y = v1;
        this.width = v2;
        this.height = v3;
    }
}

