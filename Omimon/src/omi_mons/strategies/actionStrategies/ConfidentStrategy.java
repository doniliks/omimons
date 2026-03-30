package omi_mons.strategies.actionStrategies;

import omi_mons.battle.Action;
import omi_mons.omidex.omimons.OmimonInstance;

/**
 * Die einfachste ActionStrategy
 * - Greift immer an
 */
public class ConfidentStrategy implements ActionStrategy{
    @Override
    public Action chooseAction(OmimonInstance own, OmimonInstance enemy, Boolean isOtherTrainerThere, Boolean isSwitchPossible) {
        return Action.ATTACK;
    }
    public String toString() {
        return "Action Strategy: Confident";
    }
}
