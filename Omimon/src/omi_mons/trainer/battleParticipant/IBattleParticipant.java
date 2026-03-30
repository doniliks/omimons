package omi_mons.trainer.battleParticipant;

import omi_mons.battle.Action;
import omi_mons.strategies.attackStrategies.AttackStrategy;
import omi_mons.omidex.omimons.OmimonInstance;

import java.util.ArrayList;
/**
 * Interface für Teilnehmer an einem Kampf.
 * Implementierende Klassen müssen Methoden zur Aktionswahl,
 * Angriffsauswahl, Namensabfrage und Zugriff auf ihre Omimons bereitstellen.
 */
public interface IBattleParticipant {
    /**
     * Wählt die nächste Aktion im Kampf aus.
     * @param currentOmimon Das aktuell eingesetzte Omimon des Teilnehmers
     * @param enemy Das gegnerische Omimon
     * @param isOtherTrainerThere Gibt es einen gegnerischen Trainer (true) oder ist es ein wildes Omimon (false)?
     * @param isSwitchPossible Gibt an, ob ein Wechsel des Omimons möglich ist
     * @return Die gewählte Aktion (Angriff, Wechsel, Flucht, etc, wenn erweitert wird.)
     */
    Action chooseNextAction(OmimonInstance currentOmimon, OmimonInstance enemy, Boolean isOtherTrainerThere, Boolean isSwitchPossible);
    /**
     * Gibt die aktuelle Angriffstrategie des Teilnehmers zurück.
     * @return Die Angriffstrategie
     */
    AttackStrategy getAttackStrategy();
    /**
     * Gibt den Namen des Teilnehmers zurück.
     * @return Name als String
     */
    String getName();
    /**
     * Gibt die Liste aller Omimon-Instanzen des Teilnehmers zurück.
     * @return Liste der Omimons
     */
    ArrayList<OmimonInstance> getOmimonsList();


    String toString();
}
