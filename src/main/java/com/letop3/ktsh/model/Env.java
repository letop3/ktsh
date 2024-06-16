package com.letop3.ktsh.model;

import java.util.ArrayList;
import java.util.List;

import com.letop3.ktsh.model.entity.BlockM;
import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Entity;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.mobs.General;
import com.letop3.ktsh.model.entity.npc.NPC;
import com.letop3.ktsh.model.entity.npc.action.Action;
import com.letop3.ktsh.model.entity.npc.action.MoveAction;
import com.letop3.ktsh.model.entity.npc.action.textAction.AskAction;
import com.letop3.ktsh.model.entity.npc.action.textAction.BuyAction;
import com.letop3.ktsh.model.entity.npc.action.textAction.SellAction;
import com.letop3.ktsh.model.entity.npc.action.textAction.SpeakAction;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.item.artefact.Bombe;
import com.letop3.ktsh.model.item.artefact.Projectile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Env {
    private EnvListener listener;

    private final Ground ground;
    private final Player player;
    private ObservableList<Projectile> projo;
    private ObservableList<Bombe> bombes;

    private long frame;

    public Env() {
        frame = 0;
        listener = null;

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

        // Adding a merchant
        Action goodbyeAction = new SpeakAction("Goodbye !", null);
        Action buyAction = new BuyAction("What do you want me to buy you ?", player, goodbyeAction);
        Action sellAction = new SellAction("What do you want to sell me ?", player, buyAction);

        ArrayList<Action> responsesAction = new ArrayList<>();
        responsesAction.add(sellAction);
        responsesAction.add(buyAction);
        Action merchantDialogue = new SpeakAction("Hello !", new AskAction("What do you want ?", new String[] {"Buy", "Sell"}, responsesAction));

        addEntity(new NPC(new Position(100, 100), ground, merchantDialogue));

        //Debug Mob
        addEntity(new General(new Position(900, 480), player));

        //BlockM
        addEntity(new BlockM(new Position(584, 100), ground, new ArrayList<>(List.of(Direction.EAST))));

        projo = FXCollections.observableArrayList();
        bombes = FXCollections.observableArrayList();
    }

    public void update() {
        player.doUpdate(frame);
        for (Chunk chunks : ground.getCurrentChunks()) {
            chunks.doUpdate(frame);
        }
        for (Projectile projo : projo){
            projo.doUpdate(frame);
        }

        frame++;
    }

    public void setListener(EnvListener listener) {
        this.listener = listener;
    }

    public void addEntity(Entity entity) {
        ground.getChunkFromPos(entity.getPosition().getX(), entity.getPosition().getY()).addEntity(entity);
        if (listener != null) listener.onEntityAdded(entity);
    }

    public Player getPlayer() {
        return player;
    }

    public Ground getGround() {
        return ground;
    }

    public ObservableList<Projectile> getProjo() {
        return projo;
    }

    public void addProjo(Projectile projo){
        this.projo.add(projo);
    }

    public void removeProjo(Projectile projo) {
        this.projo.remove(projo);
    }

    public ObservableList<Bombe> getBombe() {
        return bombes;
    }

    public void addBombe(Bombe bombe){
        this.bombes.add(bombe);
    }

    public void removeBombe(Bombe bombe) {
        this.bombes.remove(bombe);
    }
}

