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

        knights.add(knight);
        getPlayer().getEnv().addEntity(knight);
    }

    @Override
    public void update(long frame) {
        super.update(frame);

        for (int i = 0; i < knights.size(); i++) {
            Knight knight = knights.get(i);

            if (knight != this) knight.doUpdate(frame);

            switch (knights.get(i).getState()) {
                case EnemyState.NORMAL:
                    if (i == currentAttackingKnightIndex && !attackInProgress) {
                        System.out.println("General: Knight " + i + " is attacking");

                        knights.get(i).setAction(new AttackAction(knights.get(i)));
                        attackInProgress = true;
                    }
                    break;

                case EnemyState.ENDENGERED:
                    if (!(knights.get(i).getAction() instanceof RetreatAction)) {
                        if (i == currentAttackingKnightIndex && attackInProgress) {
                            System.out.println("General: Knight " + i + " is backing off");
    
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
