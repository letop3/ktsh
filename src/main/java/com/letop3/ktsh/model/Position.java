package com.letop3.ktsh.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Classe Position représentant une position avec des coordonnées x et y.
 */
public class Position {
    private final IntegerProperty x = new SimpleIntegerProperty();
    private final IntegerProperty y = new SimpleIntegerProperty();
    private final IntegerProperty dx = new SimpleIntegerProperty();
    private final IntegerProperty dy = new SimpleIntegerProperty();

    /**
     * Constructeur de la classe Position.
     *
     * @param x la coordonnée x
     * @param y la coordonnée y
     */
    public Position(int x, int y, int dx, int dy) {
        this.x.set(x);
        this.y.set(y);
        this.dx.set(dx);
        this.dy.set(dy);
    }

    /**
     * Retourne la propriété x.
     *
     * @return la propriété x
     */
    public IntegerProperty xProperty() {
        return x;
    }

    /**
     * Retourne la valeur de x.
     *
     * @return la valeur de x
     */
    public int getX() {
        return x.get();
    }

    /**
     * Définit la valeur de x.
     *
     * @param x la nouvelle valeur de x
     */
    public void setX(int x) {
        this.x.set(x);
    }

    /**
     * Retourne la propriété y.
     *
     * @return la propriété y
     */
    public IntegerProperty yProperty() {
        return y;
    }

    /**
     * Retourne la valeur de y.
     *
     * @return la valeur de y
     */
    public int getY() {
        return y.get();
    }

    /**
     * Définit la valeur de y.
     *
     * @param y la nouvelle valeur de y
     */
    public void setY(int y) {
        this.y.set(y);
    }

    /**
     * Retourne la propriété dx.
     *
     * @return la propriété dx
     */
    public IntegerProperty dxProperty() {
        return dx;
    }

    /**
     * Retourne la valeur de dx.
     *
     * @return la valeur de dx
     */

    public int getDx() {
        return dx.get();
    }

    /**
     * Définit la valeur de dx.
     *
     * @param dx la nouvelle valeur de dx
     */
    public void setDx(int dx) {
        this.dx.set(dx);
    }

    /**
     * Retourne la propriété dy.
     *
     * @return la propriété dy
     */
    public IntegerProperty dyProperty() {
        return dy;
    }

    /**
     * Retourne la valeur de dy.
     *
     * @return la valeur de dy
     */
    public int getDy() {
        return dy.get();
    }

    /**
     * Définit la valeur de dy.
     *
     * @param dy la nouvelle valeur de dy
     */
    public void setDy(int dy) {
        this.dy.set(dy);
    }

    /**
     * Retourne une représentation stringn de la Position
     *
     * @return une représentation stringn de la Position
     */
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}