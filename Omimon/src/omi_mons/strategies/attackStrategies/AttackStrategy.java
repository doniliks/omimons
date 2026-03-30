package omi_mons.strategies.attackStrategies;

import omi_mons.attack.Attack;
import omi_mons.omidex.omimons.Types;

import java.util.List;
/**
 * Interface für Strategien, die entscheiden, welche Attacke bei einem Kampf
 * ausgewählt werden soll.
 */
public interface AttackStrategy {
    /**
     * Gibt eine lesbare Beschreibung der Strategie zurück.
     * @return Beschreibung der Attack-Strategie.
     */
    String toString();
    /**
     * Wählt aus einer Liste von Attacken die passendste Attacke aus,
     * abhängig von den gegnerischen Typen.
     *
     * @param attacks Liste der verfügbaren Attacken.
     * @param types Typen des Gegners, um Effektivität zu berechnen.
     * @return Gewählte Attacke.
     */
    Attack getAttack(List<Attack> attacks, Types[] types);

}


