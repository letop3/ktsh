package com.letop3.ktsh.model.entity.ennemies;

import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Pathfinder;

import javafx.beans.value.ChangeListener;

public abstract class Ennemies extends Entity {
	private Player player;
	protected Pathfinder playerfinder;

	public Ennemies(Position position, Player player, int detectionRange) {
		super(position, player.getGround());
		this.player = player;
		playerfinder = new Pathfinder(player.getPosition(), player.getGround(), detectionRange);

		ChangeListener<Number> playerListener = (observable, oldValue, newValue) -> {
			playerfinder.setTarget(player.getPosition());
		};

		player.getPosition().xProperty().addListener(playerListener);
		player.getPosition().yProperty().addListener(playerListener);
	}
	
	public void attack() {
		player.setHp(0);
	}

	@Override
	public void update() {
		super.update();
        Direction direction = playerfinder.directionToTarget(getPosition());
        setDirection(direction);
	}
}
