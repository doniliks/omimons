package omi_mons.strategies.actionStrategies;

import omi_mons.battle.Action;
import omi_mons.omidex.omimons.OmimonInstance;
/**
 * ActionStrategy, die immer den Wechsel (SWITCH) als nächste Aktion wählt.
 */
public class AlwaysSwitch implements ActionStrategy {

    /**
     * Gibt immer Action.SWITCH zurück, unabhängig von den Parametern.
     */
    @Override
    public Action chooseAction(OmimonInstance own, OmimonInstance enemy, Boolean isOtherTrainerThere, Boolean isSwitchPossible) {
        return Action.SWITCH;
    }
    @Override
    public String toString() {
        return "Action Strategy: Always Switch";
    }
}
