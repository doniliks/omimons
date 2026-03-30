package omi_mons.strategies.actionStrategies;

import omi_mons.battle.Action;
import omi_mons.omidex.omimons.OmimonInstance;

public class CautiousStrategy implements ActionStrategy {

    /**
     * ActionStrategy, die vorsichtig agiert:
     * - Wechselt, wenn die eigene Gesundheit unter 50 % fällt und Wechsel möglich ist
     * - Flieht, wenn kein Trainer da ist und Wechsel nicht möglich ist
     * - Greift sonst an
     */
    @Override
    public Action chooseAction(OmimonInstance own, OmimonInstance enemy, Boolean isOtherTrainerThere, Boolean isSwitchPossible) {
        // Wenn eigene HP unter 50% sind
        if (own.getCurrentHealth() < ((own.getMaxHealth()) / 2)) {
            // Wenn Wechsel möglich, dann wechseln
            if (isSwitchPossible) return Action.SWITCH;
            // Wenn kein gegnerischer Trainer da, fliehen
            if (!isOtherTrainerThere) return Action.FLEE;
        }
        // Sonst angreifen

        return Action.ATTACK;

    }

    @Override
    public String toString() {
        return "Action Strategy: Cautious";
    }
}
