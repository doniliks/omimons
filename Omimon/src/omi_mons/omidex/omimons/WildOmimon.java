package omi_mons.omidex.omimons;

import omi_mons.battle.Action;
import omi_mons.omidex.Generator;
import omi_mons.strategies.actionStrategies.ActionStrategy;
import omi_mons.strategies.attackStrategies.AttackStrategy;
import omi_mons.strategies.attackStrategies.RandomStrategy;
import omi_mons.strategies.actionStrategies.ConfidentStrategy;
import omi_mons.trainer.battleParticipant.IBattleParticipant;

import java.util.ArrayList;

/**
 * Repräsentiert ein wildes Omimon, das in einem Kampf teilnimmt.
 * Implementiert das Interface IBattleParticipant und verwendet voreingestellte Strategien für Angriffe und Aktionen.
 */

public class WildOmimon implements IBattleParticipant {
    public final Generator generator = Generator.getGenerator();
    /** Strategie zur Auswahl eines Angriffs (Standard: zufällig) */
    final AttackStrategy attackStrategy = new RandomStrategy();
    /** Strategie zur Auswahl einer Aktion (Standard: immer Attacke) */
    final ActionStrategy actionStrategy = new ConfidentStrategy();
    /** Die aktuelle Instanz des wilden Omimons */
    final OmimonInstance omimonInstance;
    /** Liste aller Omimon-Instanzen dieses Teilnehmers )nur eine für wilde Omimon) */
    final ArrayList<OmimonInstance> omimonInstances;
    /**
     * Erstellt ein neues wildes Omimon mit einer zufälligen Instanz.
     */
    public WildOmimon() {
        omimonInstances = new ArrayList<>();
        omimonInstance = generator.getRandomOmimonInstance();
        omimonInstance.setWild();
        omimonInstances.add(omimonInstance);
    }
    /**
     * Wählt die nächste Aktion basierend auf der {@link ActionStrategy}.
     *
     * @param currentOmimon      das aktuelle Omimon dieser Instanz
     * @param enemy              das gegnerische Omimon
     * @param isOtherTrainerThere gibt an, ob ein anderer Trainer anwesend ist
     * @param isSwitchPossible   gibt an, ob ein Wechsel möglich wäre
     * @return die gewählte Aktion
     */
    public Action chooseNextAction(OmimonInstance currentOmimon, OmimonInstance enemy, Boolean isOtherTrainerThere, Boolean isSwitchPossible) {
      return actionStrategy.chooseAction(currentOmimon, enemy, false, false);
        //    return Action.ATTACK;
    }
    /**
     * Gibt die AttackStrategie zurück.
     *
     * @return die {@link AttackStrategy} dieses Omimons
     */
    @Override
    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    @Override
    public String getName() {
        return "Wild Omimon";
    }
    @Override
    /*
      Gibt eine String dieses wilden Omimons zurück.

      @return der Name und die Details der Omimon-Instanz
     */
    public String toString() {
        return getName() + ": " + omimonInstance.toString();
    }
    @Override
    public ArrayList<OmimonInstance> getOmimonsList() {
        return omimonInstances;
    }

    public OmimonInstance getOmimonInstance() {
        return omimonInstance;
    }

}
