package com.letop3.ktsh.model.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe Position représentant une position avec des coordonnées x et y.
 */
public class Position {
    private final DoubleProperty x;
    private final DoubleProperty y;

    /**
     * Constructeur de la classe Position.
     *
     * @param x la coordonnée x
     * @param y la coordonnée y
     */
    public Position(double x, double y) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
    }

    /**
     * Constructeur de la classe Position.
     *
     * @param position la position à copier
     */
    public Position(Position position) {
        this.x = new SimpleDoubleProperty(position.getX());
        this.y = new SimpleDoubleProperty(position.getY());
    }

    /**
     * Retourne la propriété x.
     *
     * @return la propriété x
     */
    public DoubleProperty xProperty() {
        return x;
    }

    /**
     * Retourne la valeur de x.
     *
     * @return la valeur de x
     */
    public double getX() {
        return x.get();
    }

    /**
     * Définit la valeur de x.
     *
     * @param x la nouvelle valeur de x
     */
    public void setX(double x) {
        this.x.set(x);
    }

    /**
     * Retourne la propriété y.
     *
     * @return la propriété y
     */
    public DoubleProperty yProperty() {
        return y;
    }

    /**
     * Retourne la valeur de y.
     *
     * @return la valeur de y
     */
    public double getY() {
        return y.get();
    }

    /**
     * Définit la valeur de y.
     *
     * @param y la nouvelle valeur de y
     */
    public void setY(double y) {
        this.y.set(y);
    }

    /**
     * Retourne la distance entre la position actuel et la position passé en paramètre.
     *
     * @param position la deuxième position avec laquel le calcul est effectué
     */
    public double distance(Position position) {
        return Math.sqrt(Math.pow(position.x.get() - x.get(), 2) + Math.pow(position.y.get() - y.get(), 2));
    }

    /**
     * Retourne une représentation stringn de la Position
     *
     * @return une représentation stringn de la Position
     */
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x.get() +
                ", y=" + y.get() +
                '}';
    }
}