package com.airbnb.lottie.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyPath {
    private final List keys;
    private KeyPathElement resolvedElement;

    private KeyPath(KeyPath keyPath0) {
        this.keys = new ArrayList(keyPath0.keys);
        this.resolvedElement = keyPath0.resolvedElement;
    }

    public KeyPath(String[] arr_s) {
        this.keys = Arrays.asList(arr_s);
    }

    public KeyPath addKey(String s) {
        KeyPath keyPath0 = new KeyPath(this);
        keyPath0.keys.add(s);
        return keyPath0;
    }

    private boolean endsWithGlobstar() {
        return ((String)this.keys.get(this.keys.size() - 1)).equals("**");
    }

    public boolean fullyResolvesTo(String s, int v) {
        if(v >= this.keys.size()) {
            return false;
        }
        boolean z = v == this.keys.size() - 1;
        String s1 = (String)this.keys.get(v);
        if(!s1.equals("**")) {
            return (z || v == this.keys.size() - 2 && this.endsWithGlobstar()) && (s1.equals(s) || s1.equals("*"));
        }
        if(!z && ((String)this.keys.get(v + 1)).equals(s)) {
            return v == this.keys.size() - 2 || v == this.keys.size() - 3 && this.endsWithGlobstar();
        }
        if(z) {
            return true;
        }
        return v + 1 >= this.keys.size() - 1 ? ((String)this.keys.get(v + 1)).equals(s) : false;
    }

    public KeyPathElement getResolvedElement() {
        return this.resolvedElement;
    }

    public int incrementDepthBy(String s, int v) {
        if(this.isContainer(s)) {
            return 0;
        }
        if(!((String)this.keys.get(v)).equals("**")) {
            return 1;
        }
        if(v == this.keys.size() - 1) {
            return 0;
        }
        return ((String)this.keys.get(v + 1)).equals(s) ? 2 : 0;
    }

    private boolean isContainer(String s) {
        return "__container".equals(s);
    }

    public String keysToString() {
        return this.keys.toString();
    }

    public boolean matches(String s, int v) {
        if(this.isContainer(s)) {
            return true;
        }
        return v < this.keys.size() ? ((String)this.keys.get(v)).equals(s) || ((String)this.keys.get(v)).equals("**") || ((String)this.keys.get(v)).equals("*") : false;
    }

    // 去混淆评级： 低(30)
    public boolean propagateToChildren(String s, int v) {
        return "__container".equals(s) ? true : v < this.keys.size() - 1 || ((String)this.keys.get(v)).equals("**");
    }

    public KeyPath resolve(KeyPathElement keyPathElement0) {
        KeyPath keyPath0 = new KeyPath(this);
        keyPath0.resolvedElement = keyPathElement0;
        return keyPath0;
    }

    @Override
    public String toString() {
        return "KeyPath{keys=" + this.keys + ",resolved=" + (this.resolvedElement != null) + '}';
    }
}

