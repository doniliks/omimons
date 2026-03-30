package omi_mons.trainer.battleParticipant;

import omi_mons.battle.Action;
import omi_mons.omidex.omimons.OmimonInstance;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
/**
 * Speichert den Zustand eines Battle-Teilnehmers während eines Kampfes.
 * Verwaltet aktuelle Omimon, Omimon-Liste, Aktionen und Zustandswechsel.
 */
public class BattleParticipantState {
    final Random random = new Random();
    private final IBattleParticipant participant;
    private final ArrayList<OmimonInstance> omimons;
    private OmimonInstance currentOmimon;
    /**
     * Konstruktor: Initialisiert den Zustand vom Teilnehmer.
     * @param participant Teilnehmer - Trainer oder WildOmimon
     */
    public BattleParticipantState(IBattleParticipant participant) {
        this.participant = participant;
        this.omimons = new ArrayList<>(participant.getOmimonsList());
        this.currentOmimon = getRandomOmimon();
    }
    /**
     * Fragt die nächste Aktion des Teilnehmers ab (z.B. Angriff, Wechsel).
     * @param opponent Gegnerisches Omimon
     * @param isAgainstTrainer Gibt es einen gegnerischen Trainer?
     * @return Gewählte Aktion
     */
    public Action getAction(OmimonInstance opponent, boolean isAgainstTrainer) {
        return participant.chooseNextAction(currentOmimon, opponent, isAgainstTrainer, isSwitchPossible());
    }
    /**
     * Greift mit dem aktuellen Omimon den Gegner an.
     * @param defender Der gegnerische BattleParticipantState
     */
    public void attack(BattleParticipantState defender)  {
        // Eigene Omimon greift mit Angriffstrategie des Teilnehmers den Gegner an
        getCurrentOmimon().attackOtherOmimon(participant.getAttackStrategy(), defender.getCurrentOmimon());
        // Prüft, ob gegnerisches Omimon tot ist und wechselt ggf.
        defender.isOmimonDead();
    }
    /**
     * Prüft, ob das aktuelle Omimon tot ist.
     * Falls ja, wird es entfernt und ein neues Omimon gewählt.
     */
    private void isOmimonDead(){
        if(currentOmimon.isDead()) {
            removeOmimon(currentOmimon);
            switchOmimon();
        }
    }
    /**
     * Wechselt das aktuelle Omimon auf ein anderes zufälliges Omimon,
     * sofern mindestens ein weiteres Omimon verfügbar ist.
     */
    public void switchOmimon() {
        if(!isSwitchPossible()) return;
        setCurrentOmimon(getRandomOmimon());
    }
    /**
     * Prüft, ob ein Omimon-Wechsel möglich ist (mindestens 2 Omimons vorhanden)
     * @return true, wenn Wechsel möglich
     */
    public boolean isSwitchPossible() {
        return omimons.size() > 1;
    }

    /** Entfernt ein Omimon aus der aktuellen Liste */
    public void removeOmimon(OmimonInstance omimon) {
        omimons.remove(omimon);

    }

    /** Setzt alle Omimons des Teilnehmers auf ihren Anfangszustand zurück */
    public void resetAllOmimons() {
        participant.getOmimonsList().forEach(OmimonInstance::reset);
    }

    public boolean isInCharge(OmimonInstance omimonInstance) {
        return currentOmimon.getSpeed() > omimonInstance.getSpeed();
    }


    public void setCurrentOmimon(OmimonInstance o) {
       this.currentOmimon = o;
    }

    @Override
    public String toString() {
        return participant.toString();
    }
    public boolean hasNoOmimons() {
        return (currentOmimon.isDead() && omimons.size() == 1) ||omimons.isEmpty();
    }

    //getter

    public String getName() {
        return participant.getName();
    }
    public String getCurrentOmimonName() {
        return currentOmimon.getName();
    }
    public ArrayList<OmimonInstance> getOmimonList() {
        return omimons;
    }

    public int getOmimonCount() {
        return omimons.size();
    }

    private OmimonInstance getRandomOmimon() {
        if (omimons.isEmpty()) return null;
        if (omimons.size() == 1) return omimons.getFirst();
        OmimonInstance omimonInstance;
        do {
            omimonInstance = omimons.get(random.nextInt(omimons.size()));
        } while (omimonInstance.equals(currentOmimon));
        return omimonInstance;
    }
    public IBattleParticipant getParticipant() {
        return participant;
    }

    public OmimonInstance getCurrentOmimon() {
        return currentOmimon;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BattleParticipantState that = (BattleParticipantState) o;
        return Objects.equals(participant, that.participant) && Objects.equals(omimons, that.omimons) && Objects.equals(currentOmimon, that.currentOmimon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participant, omimons, currentOmimon);
    }
}