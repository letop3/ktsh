package com.letop3.ktsh.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Classe Position représentant une position avec des coordonnées x et y.
 */
public class Position {
    private final IntegerProperty x;
    private final IntegerProperty y;
    private char direction; // N, S, E, W

    /**
     * Constructeur de la classe Position.
     *
     * @param x la coordonnée x
     * @param y la coordonnée y
     */
    public Position(int x, int y, char direction) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.direction = direction;
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
     * Retourne la direction.
     *
     * @return la direction
     */
    public char getDirection() {
        return direction;
    }

    /**
     * Définit la direction.
     *
     * @param direction la nouvelle direction
     */

    public void setDirection(char direction) {
        this.direction = direction;
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