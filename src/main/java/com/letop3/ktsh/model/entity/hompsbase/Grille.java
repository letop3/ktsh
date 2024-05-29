package com.letop3.ktsh.model.entity.hompsbase;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Grille {
    private int w;
    private int h;
    private Map<Sommet, Set<Sommet>> listeAdj;
    private ObservableList<Sommet> obstacles;

    public Grille(int w, int h) {
        this.w = w;
        this.h = h;
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();
        this.construit();
    }

    private void construit() {
        int i;
        int j;
        for(i = 0; i < this.w; ++i) {
            for(j = 0; j < this.h; ++j) {
                this.listeAdj.put(new Sommet(i, j), new HashSet());
            }
        }

        for(i = 0; i < this.w; ++i) {
            for(j = 0; j < this.h; ++j) {
                Sommet s = this.getSommet(i, j);
                if (this.dansGrille(i - 1, j)) {
                    ((Set)this.listeAdj.get(s)).add(this.getSommet(i - 1, j));
                }

                if (this.dansGrille(i + 1, j)) {
                    ((Set)this.listeAdj.get(s)).add(this.getSommet(i + 1, j));
                }

                if (this.dansGrille(i, j + 1)) {
                    ((Set)this.listeAdj.get(s)).add(this.getSommet(i, j + 1));
                }

                if (this.dansGrille(i, j - 1)) {
                    ((Set)this.listeAdj.get(s)).add(this.getSommet(i, j - 1));
                }
            }
        }

    }

    public void reconnecte(Sommet s) {
        this.obstacles.remove(s);
    }

    public void deconnecte(Sommet s) {
        this.obstacles.add(s);
    }

    public boolean estDeconnecte(Sommet s) {
        return this.obstacles.contains(s);
    }

    private boolean dansGrille(int x, int y) {
        return x >= 0 && x < this.w && y >= 0 && y < this.h;
    }

    public Set<Sommet> adjacents(Sommet s) {
        return (Set)(!this.estDeconnecte(s) ? (Set)this.listeAdj.get(s) : new HashSet());
    }

    public void poseObstacles(int pourcent) {
        for(int i = 0; i < this.w * this.h * pourcent / 100; ++i) {
            int aleaX = (int)(Math.random() * (double)this.w);
            int aleaY = (int)(Math.random() * (double)this.h);
            this.obstacles.add(this.getSommet(aleaX, aleaY));
        }

        this.reconnecte(this.getSommet(0, 0));
        this.reconnecte(this.getSommet(this.w - 1, this.h - 1));
    }

    public void donnePoids(int pourcent, int poids) {
        for(int i = 0; i < this.w * this.h * pourcent / 100; ++i) {
            int aleaX = (int)(Math.random() * (double)this.w);
            int aleaY = (int)(Math.random() * (double)this.h);
            this.getSommet(aleaX, aleaY).setPoids((double)poids);
        }

    }

    public Sommet getSommet(int x, int y) {
        Iterator var4 = this.listeAdj.keySet().iterator();

        Sommet sommet;
        do {
            if (!var4.hasNext()) {
                return null;
            }

            sommet = (Sommet)var4.next();
        } while(sommet.getX() != x || sommet.getY() != y);

        return sommet;
    }

    public ObservableList<Sommet> getObstacles() {
        return this.obstacles;
    }

    public Set<Sommet> getSommets() {
        return this.listeAdj.keySet();
    }

    public String toString() {
        return "Grille [w=" + this.w + ", h=" + this.h + ", listeAdj=" + this.listeAdj + "]";
    }
}
