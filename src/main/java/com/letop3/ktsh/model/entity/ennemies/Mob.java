package com.letop3.ktsh.model.entity.ennemies;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;
import javafx.beans.property.SimpleIntegerProperty;

public class Mob extends Ennemies {
	public Mob(Position position, Player player) {
		super(position, player, 10);
		this.hp = new SimpleIntegerProperty(3);
	}
}
