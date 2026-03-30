package omi_mons.strategies.actionStrategies;

import omi_mons.battle.Action;
import omi_mons.omidex.omimons.OmimonInstance;


public class SmartStrategy implements ActionStrategy{
    @Override
    public Action chooseAction(OmimonInstance own, OmimonInstance enemy, Boolean isOtherTrainerThere, Boolean isSwitchPossible) {
        // Wenn eigenes Omimon gegen Gegner schwach ist, versucht zu wechseln (sofern möglich)
        if (own.isWeakAgainst(enemy)) {
            if (isSwitchPossible) {
                return Action.SWITCH;
            } else {
                // Wenn Wechsel nicht möglich, dann Flucht
                if (!isOtherTrainerThere) return Action.FLEE;
            }
        }
        return Action.ATTACK;
        }


    @Override
    public String toString() {
        return "Action Strategy: Smart";
    }
}
