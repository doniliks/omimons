package omi_mons.battle;

import omi_mons.omidex.omimons.OmimonInstance;
import omi_mons.trainer.battleParticipant.IBattleParticipant;
import omi_mons.trainer.battleParticipant.BattleParticipantState;
import omi_mons.trainer.trainer.Trainer;
/**
 * Die Logger.Klasse dient zu strukturierten Ausgabe von Informationen
 * während des Kampfes.
 * <p>
 * Sie ermöglicht das Loggen von:
 * Kampfbeginn und -ende<
 * Aktionen der Teilnehmer
 * Wechsel, Angriffe und Fluchtversuche<
 * sonstigen Status- und Fehlermeldungen
 *
 */
public class Logger {
    private int roundCounter = 1;
    /**
     * Gibt eine Überschrift mit automatischer Trennlinie aus.
     *
     * @param message Die anzuzeigende Nachricht
     */
    private void logTitle(String message) {
        logLine();
        System.out.println("[" + message.toUpperCase() + "]");
    }
    /**
     * Gibt einen VS-Balken zwischen zwei Teilnehmern aus.
     */
    public void logVS(){
        System.out.println("------------------ VS ------------------");
    }

    private void logLine() {
        System.out.println("────────────────────────────────────────");
    }
    /**
     * Gibt Informationen über den Kampfbeginn aus.
     *
     * @param IBattleParticipant1 Der erste Teilnehmer
     * @param IBattleParticipant2 Der zweite Teilnehmer
     */
    public void logStart(IBattleParticipant IBattleParticipant1, IBattleParticipant IBattleParticipant2) {
        logTitle("STARTING BATTLE");
        System.out.println(IBattleParticipant1.toString());
        logVS();
        System.out.println(IBattleParticipant2.toString());
        roundCounter = 1;
    }
    /**
     * Gibt den Beginn der nächsten Runde aus.
     */
    public void logNextRound() {
        logTitle("Round: " + roundCounter++);
    }
    /**
     * Gibt den Gewinner des Kampfes aus.
     *
     * @param IBattleParticipant Der Gewinner
     */
    public void logWinner(IBattleParticipant IBattleParticipant) {
        if(IBattleParticipant == null) return;
        logTitle("WINNER");
        System.out.println(IBattleParticipant.getName());
    }

    /**
     * Gibt ein Problem bzw. eine Fehlermeldung aus.
     *
     * @param problem Problem Beschreibung
     */
    public void logProblem(String problem) {
        logTitle("PROBLEM OCCURRED");
        System.out.println(problem);
    }

    /**
     * Gibt die gewählten Aktionen beider Teilnehmer aus.
     *
     * @param IBattleParticipant1 Teilnehmer 1
     * @param action1             Aktion von Teilnehmer 1
     * @param IBattleParticipant2 Teilnehmer 2
     * @param action2             Aktion von Teilnehmer 2
     */

    public void logAction(IBattleParticipant IBattleParticipant1, Action action1, IBattleParticipant IBattleParticipant2, Action action2) {
        logTitle("SELECTED ACTIONS");
        System.out.println(IBattleParticipant1.getName() + ": " + action1 + " || " + IBattleParticipant2.getName() + ": " + action2);
    }

    /**
     * Gibt Informationen über einen Teilnehmer, seine Omimons und das aktuelle Omimon aus.
     *
     * @param battleParticipantState Der Teilnehmerzustand
     */

    public void logBPNameAndOmimons(BattleParticipantState battleParticipantState) {
        logTitle("Battle participant INFO");
        if (battleParticipantState.getParticipant() instanceof Trainer) {
            System.out.print(battleParticipantState.getName() + " has " + battleParticipantState.getOmimonCount() + " Omimon" + (battleParticipantState.isSwitchPossible() ? "s" : "") + " and uses: ");
            if(battleParticipantState.getOmimonCount() > 1){
                System.out.println(battleParticipantState.getCurrentOmimonName());
                System.out.println("All Omimons of " + battleParticipantState.getName() + ":");
                for (OmimonInstance omimonInstance : battleParticipantState.getOmimonList()) {
                    System.out.println(omimonInstance.toString());
                }
            } else System.out.println(battleParticipantState.getCurrentOmimon().toString());
        } else {
            System.out.println(battleParticipantState.getName() + " - " + battleParticipantState.getCurrentOmimon().toString());
        }
    }
    /**
     * Gibt eine erfolgreiche Fluchtmeldung aus.
     *
     * @param IBattleParticipant Der Teilnehmer, der geflohen ist
     */
    public void logFlee(IBattleParticipant IBattleParticipant) {
        logTitle("FLEE ATTEMPT");
        System.out.println(IBattleParticipant.getName() + " decided to flee from battle.");
    }
    /**
     * Gibt eine Meldung aus, dass die Flucht nicht erlaubt ist.
     *
     * @param IBattleParticipant Der Teilnehmer, der fliehen wollte
     */
    public void logNoFlee(IBattleParticipant IBattleParticipant) {
        logTitle("FLEE ATTEMPT");
        System.out.println(IBattleParticipant.getName() + ", you can not escape this battle.");
    }
    /**
     * Gibt einen Angriff aus, Angreifer und Verteidiger Namen.
     *
     * @param attacker Der angreifende Omimon
     * @param defender Der verteidigende Omimon
     */
    public void logAttack(OmimonInstance attacker, OmimonInstance defender) {

        logTitle("ATTACK");
        System.out.println(attacker.getName() + " VS. " + defender.getName());
    logLine();
    }
    /**
     * Gibt das Endergebnis beider Teilnehmer (Anzahl verbleibender Omimons) aus.
     *
     * @param battleParticipantState1 Teilnehmer 1
     * @param battleParticipantState2 Teilnehmer 2
     */
    public void logResult(BattleParticipantState battleParticipantState1, BattleParticipantState battleParticipantState2) {
        logTitle("RESULT");
        System.out.println(battleParticipantState1.getName() + ": " + battleParticipantState1.getOmimonCount() + " || " + battleParticipantState2.getName() + ": " + battleParticipantState2.getOmimonCount());
    }
    /**
     * Gibt aus, dass ein Wechsel nicht möglich ist,zB nur 1 Omimon übrig.
     *
     * @param name Der Name des Teilnehmers
     */
    public void logNoSwitchPossible(String name) {
        logTitle("SWITCH");
        System.out.println(name + ", it is not possible to switch Omimon, so you have to attack with current Omimon");
    }
    /**
     * Gibt aus, dass ein Omimon gewechselt wurde.
     *
     * @param nameBP  Der Name des Teilnehmers
     * @param omiName Der Name des neuen Omimons
     */
    public void logSwitchedTo(String nameBP, String omiName) {
        logTitle("SWITCH");
        System.out.println(nameBP + " switched to new Omimon - " + omiName);
    }
    /**
     * Gibt die Angriffsreihenfolge aus.
     *
     * @param IBattleParticipant1 Der schnellere Teilnehmer
     * @param IBattleParticipant2 Der langsamere Teilnehmer
     */
    public void logWhoInCharge(IBattleParticipant IBattleParticipant1, IBattleParticipant IBattleParticipant2){
        logTitle("ATTACKING ORDER");
        System.out.println("1. " + IBattleParticipant1.getName());
        System.out.println("2. " +IBattleParticipant2.getName());

    }
    /**
     * Gibt aus, ob der Spieler den Kampf gewonnen oder verloren hat.
     *
     * @param isVictory true, wenn Sieg, bzw. false, wenn Niederlage
     */
    public void logDefeatOfVictory(boolean isVictory) {
        if (isVictory) {
            logTitle("VICTORY");
        } else logTitle("DEFEat");
        logLine();
    }
}