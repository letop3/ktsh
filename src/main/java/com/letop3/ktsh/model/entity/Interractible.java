package com.letop3.ktsh.model.entity;

import com.letop3.ktsh.model.entity.player.Player;

public interface Interractible {
    public boolean isInterractible(Player player);
    public void interract();
}
