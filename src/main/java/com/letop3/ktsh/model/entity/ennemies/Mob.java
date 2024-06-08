package com.letop3.ktsh.model.entity.ennemies;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;

public class Mob extends Ennemies {
	public Mob(Position position, Player player) {
		super(position, player, 10);
		this.hp = new SimpleIntegerProperty(3);
	}

	@Override
	public Rectangle2D getHitbox() {
		double x = getPosition().getX();
		double y = getPosition().getY();
		double width = 32;
		double height = 32;

		return new Rectangle2D(x, y, width, height);
	}
}
