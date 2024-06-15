package com.letop3.ktsh.model;

import java.util.ArrayList;

import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.Mob;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.action.Action;
import com.letop3.ktsh.model.entity.npc.action.MoveAction;
import com.letop3.ktsh.model.entity.npc.action.textAction.AskAction;
import com.letop3.ktsh.model.entity.npc.action.textAction.SpeakAction;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.item.artefact.Projectile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Env {
    private final Ground ground;
    private final Player player;
    private ObservableList<Projectile> projo;

    public Env() {
        this.ground = new Ground();
        this.player = new Player(new Position((int)(Chunk.CHUNK_SIZE * 1.45), (int)(Chunk.CHUNK_SIZE * 1.45)), ground, this);
        ground.setPlayer(player);

		// Debug NPC with dialogue
		Action endAction = new MoveAction(new Position(528, 480), null);

		ArrayList<Action> responses = new ArrayList<>();
		responses.add(new SpeakAction("Good to hear !", endAction));
		responses.add(new SpeakAction("I'm sorry to hear that", endAction));
		Action dialogue = new SpeakAction("Hello", new AskAction("How are you ?", new String[] {"Fine", "Could be better"}, responses));

        addEntity(new NPC(new Position(252, 512), ground, dialogue));

		//Debug Mob
		addEntity(new Mob(new Position(900, 480), player));

        projo = FXCollections.observableArrayList();
    }

    public void update() {
        player.update();
        for (Chunk chunks : ground.getCurrentChunks()) {
            chunks.update();
        }
        for (Projectile projo : projo){
            projo.update();
        }
    }

	public void addEntity(Entity entity) {
		ground.getChunkFromPos(entity.getPosition().getX(), entity.getPosition().getY()).addEntity(entity);
	}

    public Player getPlayer() {
        return player;
    }

    public Ground getGround() {
        return ground;
    }

    public void addProjo(Projectile projo){
        this.projo.add(projo);
    }

    public ObservableList<Projectile> getProjo() {
        return projo;
    }

    public void removeProjo(Projectile projo) {
        this.projo.remove(projo);
    }
}
