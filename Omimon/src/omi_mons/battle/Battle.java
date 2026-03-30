package omi_mons.battle;
import omi_mons.omidex.omimons.WildOmimon;
import omi_mons.trainer.battleParticipant.IBattleParticipant;
import omi_mons.trainer.battleParticipant.BattleParticipantState;

import java.util.Objects;

/**
 * Die Klasse Battle verwaltet einen Kampf zwischen zwei IBattleParticipant-Instanzen.
 * Sie steuert den Rundenablauf, Aktionen der Teilnehmer, den Siegzustand und Sonderfälle wie Flucht.
 */
public class Battle {
    final Logger logger = new Logger();

    private BattleParticipantState participant1;
    private BattleParticipantState participant2;

    private boolean isAgainstTrainer;

    private BattleState battleState;

    enum BattleState {
        NOT_ENOUGH_OMIMONS_TO_PLAY,
        PLAYER1_NO_MORE_OMIMONS,
        PLAYER2_NO_MORE_OMIMONS,
        PLAYER1_FLED,
        PLAYER2_FLED,
    }

    /**
     * Startet einen Kampf zwischen zwei IBattleParticipant-Instanzen (Trainer vs. Trainer).
     *
     * @param firstTrainer  Der erste Teilnehmer
     * @param secondTrainer Der zweite Teilnehmer
     */
    public void battle(IBattleParticipant firstTrainer, IBattleParticipant secondTrainer) {
        if(trySetValues(firstTrainer, secondTrainer)) startBattle();
        else endGame();
    }
    /**
     * Startet einen Kampf zwischen einem IBattleParticipant(Trainer) und einem WildOmimon
     *
     * @param trainer     Der Trainer, der gegen das wilde Omimon kämpft
     * @param wildOmimon  Das wilde Omimon
     * @return true, wenn der Trainer gewonnen hat, sonst false
     */
    public boolean battle(IBattleParticipant trainer, WildOmimon wildOmimon) {
        if(trySetValues(trainer, wildOmimon)) startBattle();
        else endGame();

        return (participant1.getParticipant().equals(getWinner()));
    }

    /**
     * Initialisiert die Kampfteilnehmer und überprüft, ob sie kampfbereit sind.
     *
     * @param first  Der erste Teilnehmer
     * @param second Der zweite Teilnehmer
     * @return true, wenn beide Teilnehmer mindestens ein Omimon besitzen
     */
    private boolean trySetValues(IBattleParticipant first, IBattleParticipant second)  {
            resetGame();
            isAgainstTrainer = !(second instanceof WildOmimon);
            participant1 = new BattleParticipantState(first);
            participant2 = new BattleParticipantState(second);

            if(participant1.hasNoOmimons() || participant2.hasNoOmimons()) {
                battleState = BattleState.NOT_ENOUGH_OMIMONS_TO_PLAY;
                return false;
            } else return true;
    }
    /**
     * Startet den Ablauf des Kampfes.
     */

    private void startBattle() {
        logger.logStart(participant1.getParticipant(), participant2.getParticipant());
        runLoop();
    }

    /**
     * Hauptschleife des Kampfes. Führt Runden aus, bis ein Ende erreicht ist.
     * Print Round Nummer, alle aktuelle Omimons von Teilnehmern.
     */
    private void runLoop() {
       do {
                logger.logNextRound();
                logger.logBPNameAndOmimons(participant1);
                logger.logBPNameAndOmimons(participant2);
                processActions();
        } while (!proveIsEnd());
       endGame();
    }
    /**
     * Verarbeitet die Aktionen beider Teilnehmer in einer Runde.
     * Log von Aktionen, wenn nach einer zB Flucht weiter gehts, dann weiter.
     */

    private void processActions() {
        if(proveIsEnd()) return;
        BattleParticipantState first = getFirst();
        BattleParticipantState second = getSecond();

        Action action1 = first.getAction(second.getCurrentOmimon(), isAgainstTrainer);
        Action action2 = second.getAction(first.getCurrentOmimon(), isAgainstTrainer);

        logger.logAction(first.getParticipant(), action1, second.getParticipant(), action2);

        //Wenn Attack, dan überspringt, da Attacke wird immer in jeder Runde ausgeführt.
        if (action1 != Action.ATTACK) processAction(first, action1);
        if (action2 != Action.ATTACK) processAction(second, action2);
        processAttacks();
    }
    /**
     * Verarbeitet eine einzelne Aktion eines Teilnehmers (zB Flee oder Switch).
     * @param battleParticipant Der Teilnehmer
     * @param action            Die gewählte Aktion
     */
    private void processAction(BattleParticipantState battleParticipant, Action action) {
        if(proveIsEnd()) return;
        switch (action) {
            case FLEE -> AttempToFleeFromBattle(battleParticipant.getParticipant());
            case SWITCH -> switchOmimon(battleParticipant);
            default -> {
            }
        }

    }
    /**
     * Versucht den Omimon auszuwechseln, Loggt Ergebnis
     *
     * @param battleParticipant Teinehmer der Omimon in dieser Runde wechselt
     */
    private void switchOmimon(BattleParticipantState battleParticipant) {
        if(battleParticipant.isSwitchPossible()) {
            battleParticipant.switchOmimon();
            logger.logSwitchedTo(battleParticipant.getName(), battleParticipant.getCurrentOmimonName());
        } else logger.logNoSwitchPossible(battleParticipant.getName());
    }

    /**
     * Versucht aus einem Kampf zu fliehen. Nur gegen wilde Omimons erlaubt.
     *
     * @param IBattleParticipant Der fliehende Teilnehmer
     */
    private void AttempToFleeFromBattle(IBattleParticipant IBattleParticipant) {
        if (!isAgainstTrainer) {
            logger.logFlee(IBattleParticipant);
            battleState = IBattleParticipant.equals(participant1.getParticipant()) ? BattleState.PLAYER1_FLED : BattleState.PLAYER2_FLED;
        } else {
            logger.logNoFlee(IBattleParticipant);
        }
    }
    /**
     * Führt Angriffe beider Teilnehmer durch – abhängig von der Speed-Value.
     * Überprüft ob Ende, oder macht weiter.
     */
    private void processAttacks() {
        if(proveIsEnd()) return;
        BattleParticipantState firstAttacker = getFirst();
        BattleParticipantState secondAttacker = getSecond();

        logger.logWhoInCharge(firstAttacker.getParticipant(), secondAttacker.getParticipant());
        attack(firstAttacker, secondAttacker);
        attack(secondAttacker, firstAttacker);
    }
    private void attack(BattleParticipantState firstAttacker, BattleParticipantState secondAttacker) {
        if(proveIsEnd()) return;
        logger.logAttack(firstAttacker.getCurrentOmimon(), secondAttacker.getCurrentOmimon());
        firstAttacker.attack(secondAttacker);
    }
    /**
     * Bestimmt, ob der erste Teilnehmer in der Runde den Vortritt hat.
     *
     * @return true, wenn Teilnehmer 1 bzw. Omimon schneller ist
     */
    private boolean isFirstInCharge() {
        return participant1.isInCharge(participant2.getCurrentOmimon());
    }
    /**
     * Gibt den Sieger des Kampfes zurück – abhängig vom {@code battleState}.
     *
     * @return Der Gewinner, oder null, wenn noch kein Sieger feststeht
     */
    private IBattleParticipant getWinner() {
        return switch (battleState) {
            case PLAYER1_NO_MORE_OMIMONS, PLAYER1_FLED -> participant2.getParticipant();
            case PLAYER2_NO_MORE_OMIMONS, PLAYER2_FLED -> participant1.getParticipant();
            default -> null;
        };
    }
    /**
     * Prüft, ob der Kampf zu Ende ist, zB.  ein Omimon tot ist oder wegen Flucht.
     *
     * @return true, wenn der Kampf beendet ist
     */
    private boolean proveIsEnd() {
        if(participant1.hasNoOmimons()) {
            battleState = BattleState.PLAYER1_NO_MORE_OMIMONS;
            return true;
        }
        if(participant2.hasNoOmimons()) {
            battleState = BattleState.PLAYER2_NO_MORE_OMIMONS;
            return true;
        }
        if(battleState == BattleState.PLAYER1_FLED || battleState == BattleState.PLAYER2_FLED) {
            return true;
        };
        return false;
    }
    /**
     * Beendet den Kampf, loggt das Ergebnis und setzt Omimons zurück.
     */
    private void endGame() {
        switch (battleState) {
            case NOT_ENOUGH_OMIMONS_TO_PLAY -> {
                logger.logProblem("Not enough Omimons to play, catch more and try again later :)");
                return;
            }
            case PLAYER1_NO_MORE_OMIMONS, PLAYER2_NO_MORE_OMIMONS -> logger.logResult(participant1, participant2);
        }

        logger.logWinner(getWinner());
        logger.logDefeatOfVictory(Objects.equals(getWinner(), participant1.getParticipant()));

        resetOmis();
    }

    /**
     * Setzt alle Omimons beider Teilnehmer in den normalen Zustand zurück.
     */
    private void resetOmis() {
        participant1.resetAllOmimons();
        participant2.resetAllOmimons();
    }
    private void resetGame() {
        battleState = null;
    }
    //Helper
    /**
     * Gibt den Teilnehmer mit höherem Speed zurück.
     *
     * @return Der zuerst handelnde Teilnehmer
     */
    private BattleParticipantState getFirst() {
        return (isFirstInCharge() ? participant1 : participant2);
    }
    /**
     * @return Das ummgekehrte - der aktuelle zweite Teilnehmer zu handeln.
     */
    private BattleParticipantState getSecond() {
        return (isFirstInCharge() ? participant2 : participant1);
    }
}