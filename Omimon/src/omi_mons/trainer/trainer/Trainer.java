package omi_mons.trainer.trainer;

import omi_mons.battle.Action;
import omi_mons.battle.Battle;
import omi_mons.omidex.Generator;
import omi_mons.omidex.omimons.WildOmimon;
import omi_mons.strategies.actionStrategies.ActionStrategy;
import omi_mons.strategies.attackStrategies.*;
import omi_mons.omidex.omimons.OmimonInstance;
import omi_mons.trainer.battleParticipant.IBattleParticipant;

import java.util.ArrayList;
import java.util.Objects;

public class Trainer implements IBattleParticipant {
    final Battle battle = new Battle();
    private final int id;
    private final String name;
    private final Gender gender;
    private static int id_counter = 0;
    private final ArrayList<OmimonInstance> omimonInstances;
    private ActionStrategy actionStrategy;
    private AttackStrategy attackStrategy;
    private final Generator generator = Generator.getGenerator();

    /**
     * Repräsentiert einen Trainer mit einem Namen, Geschlecht, einer ID und einer Liste von Omimons.
     * Der Trainer verfügt über Strategien für Aktionen und Angriffe und kann an Kämpfen teilnehmen.
     */
    public Trainer(String name, Gender gender) {
        this.gender = gender;
        this.name = name;
        this.id = id_counter++;
        this.omimonInstances = new ArrayList<>();
        // Start-Omimon zufällig generieren und hinzufügen
        omimonInstances.add(generator.getRandomOmimonInstance());
    }
    /**
     * Sucht die nächste Aktion aus, basierend auf der ActionStrategy.
     * @param own Das eigene Omimon
     * @param against Das gegnerische Omimon
     * @param isOtherTrainerThere Gibt es einen Gegner-Trainer?
     * @param isSwitchPossible ist Wechsel möglich?
     * @return Die gewählte Aktion
     */
    public Action chooseNextAction(OmimonInstance own, OmimonInstance against, Boolean isOtherTrainerThere, Boolean isSwitchPossible) {
        return actionStrategy.chooseAction(own, against, isOtherTrainerThere, isSwitchPossible);
    }

    /**
     * Versucht, ein wildes Omimon zu fangen.
     * Startet einen Kampf gegen das wilde Omimon, bei Sieg wird es zum Team hinzugefügt.
     */
    public void catchOmimon(){
        System.out.println(name + ", it is time to catch new Omimon");
        WildOmimon wildOmimon = new WildOmimon();
        if(battle.battle(this, wildOmimon)) {
            OmimonInstance omimonInstance = wildOmimon.getOmimonInstance();
            omimonInstance.setWild();
            omimonInstances.add(omimonInstance);
            System.out.println("You won against this Omimon: " + omimonInstances.getLast().getName() + ". It was added to your collection.");

        } else {
            System.out.println("To lose is not that bad, try again later ;)");
        }
            printOmimons();
    }

    @Override
    public String toString() {

        return "Trainer: " + getName() +
                "\n - " + actionStrategy.toString() +
                "\n - " + attackStrategy.toString();

    }
    public void addRandomOmimon() {
        System.out.println("TEST: Add Random Omimon");
        OmimonInstance omimonInstance = generator.getRandomOmimonInstance();
        System.out.println(omimonInstance.toString());
        omimonInstances.add(omimonInstance);
    }
/// getter setter
    public void setActionStrategy(ActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public int getId() {
        return id;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    @Override
    public String getName() {
        return name;
    }
    public ArrayList<OmimonInstance> getOmimonsList() {
        return omimonInstances;
    }
    public ActionStrategy getActionStrategy() {
        return actionStrategy;
    }

    public Gender getGender() {
        return gender;
    }
    /// getter setter ende

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return getId() == trainer.getId() && Objects.equals(getName(), trainer.getName()) && getGender() == trainer.getGender() && Objects.equals(omimonInstances, trainer.omimonInstances) && Objects.equals(getActionStrategy(), trainer.getActionStrategy()) && Objects.equals(getAttackStrategy(), trainer.getAttackStrategy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGender(), omimonInstances, getActionStrategy(), getAttackStrategy());
    }
    /**
     * Gibt alle Omimons des Trainers auf der Konsole aus.
     */
    public void printOmimons() {
        System.out.println();
        System.out.println("Your current Omimons: ");
        for(OmimonInstance omimonInstance : omimonInstances) {
            System.out.println(omimonInstance.toString());
        }
        System.out.println();
    }
}
