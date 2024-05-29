package com.letop3.ktsh.model.entity.hompsbase;


public class Sommet implements Comparable<Sommet> {
    private int x;
    private int y;
    private double poids;

    public Sommet(int x, int y, double poids) {
        this.x = x;
        this.y = y;
        this.poids = poids;
    }

    public Sommet(int x, int y) {
        this(x, y, 1.0);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getPoids() {
        return this.poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + this.x;
        result = 31 * result + this.y;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Sommet other = (Sommet)obj;
            if (this.x != other.x) {
                return false;
            } else {
                return this.y == other.y;
            }
        }
    }

    public String toString() {
        return "Sommet [" + this.x + ", " + this.y + ", p=" + this.poids + "]";
    }

    public int compareTo(Sommet o) {
        if (this.x < o.x) {
            return -1;
        } else {
            return this.x == o.x ? this.y - o.y : 1;
        }
    }
}
