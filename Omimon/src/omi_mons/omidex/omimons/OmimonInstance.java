package omi_mons.omidex.omimons;

import omi_mons.attack.Attack;
import omi_mons.strategies.attackStrategies.AttackStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;
/**
 * Die Klasse OmimonInstance stellt eine Instanz eines Omimon dar,
 * die im Kampf verwendet werden kann. Enthält Attribute wie Lebenspunkte, Verteidigung,
 * Geschwindigkeit, Angriffe, Level, Erfahrungspunkte und Methoden zur Interaktion.
 */
public class OmimonInstance {
    private static int id_Counter = 0;

    private static final Logger logger = Logger.getLogger(OmimonInstance.class.getName());
    private static final int xpLevel = 10;
    private static final int maxLevel = 100;
    private static final int possibleHealing = 3;

    private final int id;
    private OmimonPrototype omimonPrototype;
    private Types[] omimonTypes;
    private String name;

    private int currentHealth;
    private int maxHealth;
    private int defense;
    private final int maxDefense;
    private int speed;
    private int healings = possibleHealing;

    private final List<Attack> attacks;


    private boolean isDead = false;
    private boolean canEvolve = false;
    private boolean isWild = false;

    private int XP = 0;
    private int level = 0;


    final Random random = new Random();

    /**
     * Konstruktor: Erstellt eine neue Omimon-Instanz basierend auf dem OmimonType.
     */
    public OmimonInstance(OmimonPrototype omimonPrototype){
        this.id = id_Counter++;
        this.omimonPrototype = omimonPrototype;
        this.omimonTypes = omimonPrototype.getTypes();
        this.name = omimonPrototype.getName();
        this.maxDefense = omimonPrototype.getRandomDefenceValue();
        this.defense = maxDefense;
        this.maxHealth = omimonPrototype.getRandomHealthValue();
        this.currentHealth = this.maxHealth;
        this.speed = omimonPrototype.getRandomSpeedValue();
        if(omimonPrototype.getImprovedOmimon() != null) {
            canEvolve = true;
        }
        attacks = omimonPrototype.getAttacks();
    }
    // Getter-Methoden für Attribute

    public boolean isDead() {
        return isDead;
    }

    public OmimonPrototype getOmimonType() {
        return omimonPrototype;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDefense() {
        return defense;
    }

    public int getLevel() {
        return level;
    }

    public int getSpeed() {
        return speed;
    }


    public String getName() {
        return name;
    }

    public Types[] getOmimonTypes() {
        return omimonPrototype.getOmimonTypes();
    }

    public boolean isWild() {
        return isWild;
    }

    public void setWild() {
        isWild = !isWild;
    }

    public List<Attack> getAttacks() {
        return omimonPrototype.getAttacks();
    }

    public int getEvoLevel() {
        return omimonPrototype.getEvoLevel();
    }

    /**
     * Heilt das Omimon vollständig, wenn noch Heilungen verfügbar sind.
     */
    public void healToFull() {
        if(healings > 0) {
            if(currentHealth == maxHealth) {
                System.out.println(name + " is already at full health.");
                return;
            }
            System.out.println(name + " was fully healed (" + currentHealth + " -> " + maxHealth + "). Heals left: " + --healings);
            this.currentHealth = this.maxHealth;
        } else {
            System.out.println(name + " can not heal anymore (0 heals left).");
        }
    }
    /**
     * Reduziert die Verteidigung nach einer Attacke.
     */
    private void reduceDefence() {
        if(this.defense == 1) return;
        this.defense = Math.max(1, (this.defense - 1));
        System.out.println("- " + name +
                " is getting weaker in defending. Current value: " + this.defense);
    }

    /**
     * Wählt eine Attacke basierend auf einer Strategie.
     */
    public Attack chooseNextAttack(AttackStrategy attackStrategy, Types[] types) {
        return attackStrategy.getAttack(getAttacks(), types);
    }

    /**
     * Führt einen Angriff auf ein anderes Omimon aus.
     */
    public void attackOtherOmimon(AttackStrategy attackStrategy, OmimonInstance defender) {
        Attack attack = chooseNextAttack(attackStrategy, defender.getOmimonTypes());
        defender.getAttacked(attack.calculateFinalDamage(this, defender.getOmimonTypes()));
        gainXP();
    }
    /**
     * Gibt dem Omimon Erfahrungspunkte und levelt ggf. auf, wenn kein WildOmimon ist.
     */
    private void gainXP() {

        if(isWild || this.level == maxLevel) return;
        System.out.println("────────────────────────────────────────");
        System.out.println("[GAINING XP]");
        int xp = random.nextInt(1, xpLevel);
        this.XP += xp;
        System.out.println("- " + name + " gained " + xp + " XP.");
        if(this.XP >= xpLevel) {
            nextLevel();
            this.XP = this.XP - xpLevel;
        }
    }

    /**
     * Steigert das Level und prüft auf Entwicklung.
     */
    public void nextLevel(){
        if(isWild || this.level == maxLevel) return;
        System.out.println("- " + name + " leveled up! New level: " + ++level);
        tryToEvolve();
    }
    /**
     * Verarbeitet Schaden nach einem Angriff.
     */
    public void getAttacked(int power) {
        System.out.println("────────────────────────────────────────");
        System.out.println("[DEFENSE]");
    System.out.println(name + " is being attacked.");
        System.out.println("- Attack power: " + power + ", defense " + defense + ", HP: " + currentHealth);
        int damage = Math.max(1, power - this.defense);
        this.currentHealth = Math.max(0, this.currentHealth - damage);

        System.out.println("- Damage done: " + damage + ", HP: " + currentHealth);
        if (currentHealth == 0) {
            isDead = true;
            System.out.println("- " + name + " fainted.");
        } else {
           reduceDefence();
        }

    }

    /**
     * Prüft, ob das Omimon sich entwickeln kann.
     */
    public void tryToEvolve() {
        if (canEvolve && level == getEvoLevel()) {
            evolve();
        }
    }

    /**
     * Entwickelt das Omimon zur nächsten Form.
     */
    private void evolve() {
        System.out.println("[EVOLUTION]");
        System.out.print(name + " is evolving into... ");
        this.omimonPrototype = omimonPrototype.getImprovedOmimon();
        this.name = omimonPrototype.getName();
        System.out.println(name + "!");
        this.omimonTypes = omimonPrototype.getTypes();
        this.maxHealth = omimonPrototype.getRandomHealthValue();
        this.currentHealth = this.maxHealth;
        this.defense = this.omimonPrototype.getRandomDefenceValue();
        this.speed = this.omimonPrototype.getRandomSpeedValue();
        this.canEvolve = this.omimonPrototype.getImprovedOmimon() != null;
        reset();
    }

    public void reset() {
        this.healings = 3;
        this.currentHealth = this.maxHealth;
        this.defense = this.maxDefense;
        this.isDead = false;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append("\n");

        // Typen
        sb.append(" - Type").append(omimonTypes.length > 1 ? "s: " : ": ");
        for (int i = 0; i < omimonTypes.length; i++) {
            sb.append(omimonTypes[i]);
            if (i < omimonTypes.length - 1) sb.append(", ");
        }
        sb.append("\n");

        // Stats
        sb.append(" - Health: ").append(currentHealth).append("\n");
        sb.append(" - Defense: ").append(defense).append("\n");
        sb.append(" - Speed: ").append(speed).append("\n");
        sb.append(" - Heals left: ").append(healings).append("\n");

        if (!isWild()) {
            sb.append(" - Level: ").append(level)
                    .append(" (XP: ").append(XP).append("/").append(xpLevel).append(")").append("\n");
        }

        sb.append(" - Attacks (").append(attacks.size()).append("): ");
        for (int i = 0; i < attacks.size(); i++) {
            sb.append(attacks.get(i).getName());
            if (i < attacks.size() - 1) sb.append(", ");
        }

        return sb.toString();
    }


    /**
     * Prüft, ob dieses Omimon im Nachteil gegenüber einem anderen ist.
     */
    public boolean isWeakAgainst(OmimonInstance against){
        double eff = 1;
        for(Types omimonType : getOmimonTypes()) {
            eff *= omimonType.getEfficiency(against.getOmimonTypes());
        }
        return eff < 1;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OmimonInstance that = (OmimonInstance) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
