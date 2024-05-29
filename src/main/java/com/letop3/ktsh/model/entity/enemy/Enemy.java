package com.letop3.ktsh.model.entity.enemy;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;

/**
 * Classe abstraite Enemy qui étend la classe Entity.
 * Cette classe représente un ennemi dans le jeu.
 */
public abstract class Enemy extends Entity {
    // Santé de l'ennemi
    private int health;

    /**
     * Constructeur de la classe Enemy.
     * @param position La position initiale de l'ennemi.
     */
    public Enemy(Position position) {
        super(position);
    }

    /**
     * Méthode abstraite pour l'attaque de l'ennemi.
     * Doit être implémentée par les sous-classes.
     */
    public abstract void attack();

    /**
     * Méthode abstraite pour la défense de l'ennemi.
     * Doit être implémentée par les sous-classes.
     */
    public abstract void defend();

    /**
     * Méthode abstraite pour le mouvement de l'ennemi.
     * Doit être implémentée par les sous-classes.
     */
    public abstract void move();

    /**
     * Méthode pour vérifier si l'ennemi est vivant.
     * @return true si la santé de l'ennemi est supérieure à 0, false sinon.
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Méthode pour obtenir une représentation sous forme de chaîne de l'ennemi.
     * @return Une chaîne représentant l'ennemi.
     */
    @Override
    public String toString() {
        return "Enemy";
    }
}