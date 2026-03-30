package omi_mons.strategies.actionStrategies;


import omi_mons.battle.Action;
import omi_mons.omidex.omimons.OmimonInstance;
/**
 * Interface für Strategien, die bestimmen, welche Aktion ein Teilnehmer im Kampf ausführt.
 */
public interface ActionStrategy {
     /**
     * Wählt basierend auf der eigenen Omimon-Instanz, dem Gegner und weiteren Bedingungen
     * die nächste Aktion im Kampf aus.
     *
     * @param own Die aktuelle Omimon-Instanz des Spielers.
     * @param enemy Die gegnerische Omimon-Instanz.
     * @param isOtherTrainerThere Ob ein gegnerischer Trainer im Kampf ist.
     * @param isSwitchPossible Ob ein Omimon-Wechsel aktuell möglich ist.
     * @return Die gewählte Aktion (Angriff, Wechsel, etc.).
     */
    Action chooseAction(OmimonInstance own, OmimonInstance enemy, Boolean isOtherTrainerThere, Boolean isSwitchPossible);
    /**
     * Gibt eine lesbare Beschreibung der Strategie zurück.
     *
     * @return Beschreibung der Aktionsstrategie.
     */
    String toString();
}