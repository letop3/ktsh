import com.letop3.ktsh.controller.ActionListener;
import com.letop3.ktsh.model.Env;
import com.letop3.ktsh.model.entity.Direction;
import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.player.Player;
import com.letop3.ktsh.model.ground.Chunk;
import com.letop3.ktsh.model.ground.Ground;
import com.letop3.ktsh.model.item.artefact.BotteErmS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestGround extends Ground {
    @Override
    public boolean isTileWalkable(double x, double y, Direction direction) {
        return true;
    }

    @Override
    public List<Chunk> getCurrentChunks() {
        return new ArrayList<>();
    }
}

class TestPlayer extends Player {
    private Direction direction;

    public TestPlayer(Position position, Ground ground, Env env) {
        super(position, ground, env);
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

public class BotteErmSTest {

    private TestPlayer player;
    private BotteErmS botteErmS;
    private TestGround ground;
    private Env env;

    @BeforeEach
    public void setUp() {
        ground = new TestGround();
        env = new Env() {
            @Override
            public Ground getGround() {
                return ground;
            }
        };
        player = new TestPlayer(new Position(10, 10), ground, env);
        botteErmS = new BotteErmS(1, "Botte Erm'S", "Test", 100);


        botteErmS.setActionListener(new ActionListener() {
            @Override
            public void onActionCalled() {
            }
        });
    }

    @Test
    public void testAction() {

        player.setDirection(Direction.EAST); // Moving right


        botteErmS.action(player);


        Position position = player.getPosition();
        double expectedX = 106;
        double expectedY = 10;

        // ~ à 0.01 près
        assertEquals(expectedX, position.getX(), 0.01);
        assertEquals(expectedY, position.getY(), 0.01);


        assertTrue(botteErmS.isOnCD());
    }
}