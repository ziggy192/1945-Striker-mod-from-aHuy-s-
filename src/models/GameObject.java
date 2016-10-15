package models;

import controllers.GameSingleController;

/**
 * Created by apple on 10/11/16.
 */
public class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private final int radius;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = Math.max(width,height);
    }

    public GameObject(int x, int y, int width, int height, int radius) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRadius() {
        return radius;
    }



    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getTopLeftX() {
        return x - width / 2;
    }

    public int getTopLeftY() {
        return y  - height / 2;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(GameObject gameObject){
        double xDiff = getX()-gameObject.getX();
        double yDiff = getY()-gameObject.getY();
        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }
}
