package com.letop3.ktsh.model.entity.hompsbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BFS {
    /**
     * Le graphe (ou grille) sur lequel on travaille
     */
    private Grille g;
    /**
     * Le sommet source de l'algo
     */
    private Sommet source;
    /**
     * Liste des sommets de la composante connexe de g obtenue par un parcours en largeur depuis le sommet source
     */
    private ArrayList<Sommet> parcours;
    /**
     * Chaque sommet (clé) est associé à son prédécesseur (valeur) du parcours en largeur
     */
    private Map<Sommet, Sommet> predecesseurs;

    public BFS(Grille g, Sommet source) {
        this.g = g;
        this.source = source;
        parcours = new ArrayList<>();
        predecesseurs = new HashMap<Sommet, Sommet>();
        algoBFS();
    }

    /**
     * Exécute l'algo BFS sur g à partir du sommet source Remplit la liste parcours avec les sommets dans l'ordre de
     * visite Remplit la map predecesseurs en indiquant quel est le prédécesseur de chaque sommet Le prédécesseur du
     * sommet source est le sommet null
     */
    private void algoBFS() {
        LinkedList<Sommet> fifo = new LinkedList<>();

        parcours.add(source);
        fifo.add(source);
        predecesseurs.put(source, null);

        while (!fifo.isEmpty()) {
            Sommet courant = fifo.pollFirst();

            for (Sommet voisin : g.adjacents(courant)) {
                if (!parcours.contains(voisin)) {
                    parcours.add(voisin);
                    fifo.add(voisin);
                    predecesseurs.put(voisin, courant);
                }
            }
        }
    }

    /**
     * Retourne une liste donnant la suite des sommets depuis la cible jusqu'à la source
     *
     * @param cible
     * @return le chemin sous forme de liste de sommets
     */
    public ArrayList<Sommet> cheminVersSource(Sommet cible) {
        ArrayList<Sommet> chemin = new ArrayList<>();

        if (!predecesseurs.containsKey(cible)) {
            return chemin;
        }

        chemin.add(cible);

        while (predecesseurs.get(cible) != null) {
            cible = predecesseurs.get(cible);
            chemin.add(0, cible);
        }

        return chemin;
    }

    /*************************************************
     **** Pas de modifications à faire ci-dessous ****
     *************************************************/

    public ArrayList<Sommet> getParcours() {
        return parcours;
    }

    public Map<Sommet, Sommet> getPredecesseurs() {
        return predecesseurs;
    }

    /**
     * Mise à jour quand la source est modifiée suite à un clic droit
     *
     * @param source
     *            le nouveau sommet source
     */
    public void setSource(Sommet source) {
        this.source = source;
        clear();
        algoBFS();
    }

    /**
     * Mise à jour suite au changement de graphe
     *
     * @param g
     *            le nouveau graphe
     */
    public void setG(Grille g) {
        this.g = g;
        clear();
        algoBFS();
    }

    private void clear() {
        this.parcours.clear();
        this.predecesseurs.clear();
    }

}