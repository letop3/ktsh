package com.letop3.ktsh.model.item.artefact;

import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.view.music.SoundPlayer;

public class FluteEnchantee extends Artefact {
    private boolean actived;
    public FluteEnchantee(int id, String nom, String description, int prix) {
        super(id, nom, description, prix);
        this.setIcon("/com/letop3/ktsh/images/item/flute.png");
        this.actived = false;
    }

    @Override
    public void action(Player player) {
        if (!actived) {
            SoundPlayer.changeBackgroundMusic("src/main/resources/com/letop3/ktsh/audio/music/troll.mp3");
            actived = true;
            System.out.println("Flute enchantée activée");
        }
    }
}
