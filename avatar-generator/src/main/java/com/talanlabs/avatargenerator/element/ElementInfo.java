package com.talanlabs.avatargenerator.element;

public class ElementInfo {

    public final String name;
    public final int offsetX;
    public final int offsetY;

    private ElementInfo(String name, int offsetX, int offsetY) {
        super();

        this.name = name;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public static ElementInfo of(String name) {
        return new ElementInfo(name, 0, 0);
    }

    public static ElementInfo of(String name, int offsetX, int offsetY) {
        return new ElementInfo(name, offsetX, offsetY);
    }
}
