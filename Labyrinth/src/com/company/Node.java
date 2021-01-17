package com.company;


import java.util.Objects;

public class Node {

    private int level;
    private int length;
    private int width;


    public Node(int level, int length, int width) {
        this.level = level;
        this.length = length;
        this.width = width;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // equals und hash code dementsprechend überschrieben, dass ein Knoten gleich sind, wenn sie die selben Koordinaten haben
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return level == node.level &&
                length == node.length &&
                width == node.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, length, width);
    }
}
