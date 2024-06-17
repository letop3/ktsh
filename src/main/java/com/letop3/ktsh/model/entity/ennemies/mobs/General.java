package com.letop3.ktsh.model.entity.ennemies.mobs;

import java.util.ArrayList;
import java.util.List;

import com.letop3.ktsh.model.entity.Position;
import com.letop3.ktsh.model.entity.ennemies.EnemyState;
import com.letop3.ktsh.model.entity.ennemies.ennemieActions.AttackAction;
import com.letop3.ktsh.model.entity.ennemies.ennemieActions.RetreatAction;
import com.letop3.ktsh.model.entity.player.Player;

public class General extends Knight {
    int currentAttackingKnightIndex = 0;
    boolean attackInProgress = false;

    private List<Knight> knights;

    public General(Position position, Player player) {
        super(position, player, 5, 5);
        
        knights = new ArrayList<>();
        knights.add(this);
        for (int i = 0; i < 3; i++) {
            summonKnight();
        }
    }

    public void summonKnight() {
        Knight knight = new Knight(new Position(getPosition()), getPlayer());
        knight.setAction(null);
        knight.setControlled();

        knights.add(knight);
        getPlayer().getEnv().addEntity(knight);
    }

    public void test() {
        System.out.println("General: test");
    }

    @Override
    public void update(long frame) {
        setControlled();
        super.update(frame);

        for (int i = 0; i < knights.size(); i++) {
            Knight knight = knights.get(i);

            if (knight != this) {
                knight.setControlled();
                knight.doUpdate(frame);
                knight.setControlled();
            }

            switch (knights.get(i).getState()) {
                case NORMAL:
                    if (i == currentAttackingKnightIndex && !attackInProgress) {
                        knights.get(i).setAction(new AttackAction(knights.get(i)));
                        attackInProgress = true;
                    }
                    break;

                case ENDENGERED:
                    if (!(knights.get(i).getAction() instanceof RetreatAction)) {
                        if (i == currentAttackingKnightIndex && attackInProgress) {
                            attackInProgress = false;
                            currentAttackingKnightIndex = (currentAttackingKnightIndex + 1) % knights.size();
                        }

                        knights.get(i).setAction(new RetreatAction(knights.get(i)));
                    }
                    break;

                default:
                    knights.get(i).setAction(null);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "General";
    }
}
