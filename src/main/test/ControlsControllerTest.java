import com.letop3.ktsh.controller.ControlsController;
import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;
import com.letop3.ktsh.view.player.stuff.StuffView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyPlayer extends Player {
    private boolean interacted = false;
    private boolean quickSlotUsed = false;
    private boolean attacked = false;
    private Direction directionAdded = null;
    private Direction directionRemoved = null;

    public DummyPlayer() {
        super(null, null, null);
    }

    @Override
    public void interract() {
        interacted = true;
    }

    @Override
    public void useQuickSlot() {
        quickSlotUsed = true;
    }

    @Override
    public void attack(Env env) {
        attacked = true;
    }

    @Override
    public void addDirection(Direction direction) {
        directionAdded = direction;
    }

    @Override
    public void remDirection(Direction direction) {
        directionRemoved = direction;
    }

    public boolean isInteracted() {
        return interacted;
    }

    public boolean isQuickSlotUsed() {
        return quickSlotUsed;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public Direction getDirectionAdded() {
        return directionAdded;
    }

    public Direction getDirectionRemoved() {
        return directionRemoved;
    }
}

class DummyStuffView extends StuffView {
    private boolean visibilityToggled = false;

    public DummyStuffView() {
        super(null, null, null);
    }

    @Override
    public void toogleVisibility() {
        visibilityToggled = true;
    }

    public boolean isVisibilityToggled() {
        return visibilityToggled;
    }
}

public class ControlsControllerTest {

    private TilePane gameGround;
    private DummyPlayer player;
    private DummyStuffView stuffView;
    private Env env;
    private ControlsController controlsController;

    @BeforeEach
    public void setUp() {
        gameGround = new TilePane();
        player = new DummyPlayer();
        stuffView = new DummyStuffView();
        env = new Env() {
            @Override
            public Ground getGround() {
                return null;
            }
        };
        controlsController = new ControlsController(gameGround, player, stuffView, env);
    }

    @Test
    public void testKeyPressed_Interact() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyPreference.INTERACT, false, false, false, false);
        controlsController.keyPressed(keyEvent);
        assertTrue(player.isInteracted());
    }

    @Test
    public void testKeyPressed_Inventory() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyPreference.INVENTORY, false, false, false, false);
        controlsController.keyPressed(keyEvent);
        assertTrue(stuffView.isVisibilityToggled());
    }

    @Test
    public void testKeyPressed_QuickSlot() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyPreference.QUICK_SLOT, false, false, false, false);
        controlsController.keyPressed(keyEvent);
        assertTrue(player.isQuickSlotUsed());
    }

    @Test
    public void testKeyPressed_Attack() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.P, false, false, false, false);
        controlsController.keyPressed(keyEvent);
        assertTrue(player.isAttacked());
    }

    @Test
    public void testKeyPressed_Move() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyPreference.MOVE_UP, false, false, false, false);
        controlsController.keyPressed(keyEvent);
        assertEquals(Direction.NORTH, player.getDirectionAdded());
    }

    @Test
    public void testKeyReleased_Move() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyPreference.MOVE_UP, false, false, false, false);
        controlsController.keyReleased(keyEvent);
        assertEquals(Direction.NORTH, player.getDirectionRemoved());
    }
}
