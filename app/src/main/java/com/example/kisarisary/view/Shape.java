package com.example.kisarisary.view;

public abstract class Shape implements Cloneable{
    private TouchCoordinates touch;
    abstract void draw(TouchCoordinates touch);

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
