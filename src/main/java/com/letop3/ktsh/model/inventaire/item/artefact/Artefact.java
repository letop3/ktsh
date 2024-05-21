package com.letop3.ktsh.model.inventaire.item.artefact;

import com.letop3.ktsh.model.inventaire.item.Item;

public abstract class Artefact extends Item {

        public Artefact(int id, String nom, String description, int prix) {
            super(id, nom, description, prix);
        }
}
