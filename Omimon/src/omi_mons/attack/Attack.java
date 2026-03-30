package omi_mons.attack;

import omi_mons.attack.additionalEffects.AttackEffect;
import omi_mons.omidex.omimons.OmimonInstance;
import omi_mons.omidex.omimons.Types;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Repräsentiert eine Attacke von Omimons.
 * Eine Attacke besitzt einen Namen, einen Typ, eine Basisstärke und kann mehrere Zusatzeffekte haben.
 * Die Attacke kann Schaden berechnen, der durch Typ-Effizienz und Effekte modifiziert wird.
 */
public class Attack implements IAttack {

    private final String name;
    private final Types attackType;
    private final int basePower;
    private final ArrayList<AttackEffect> attackEffects;
    /**
     * Privater Konstruktor, nur über den Builder aufrufbar.
     * @param builder Der AttackBuilder mit allen notwendigen Feldern.
     */
    private Attack(AttackBuilder builder) {
        this.name = builder.name;
        this.attackType = builder.attackType;
        this.attackEffects = builder.attackEffects;
        this.basePower = builder.basePower;
    }

    @Override
    public double calculateEfficiencyAgainst(Types[] defenderTypes) {
        return attackType.getEfficiency(defenderTypes);
    }
    /**
     * Berechnet den Schaden inklusive Berücksichtigung der Typ-Effizienz.
     * Methode für Strategien.
     * @param defenderTypes Verteidiger-Typen.
     * @return Endgültiger Schaden als int.
     */

    @Override
    public int calculateExpectedDamage(Types[] defenderTypes) {
        return (int) calculateEfficiencyAgainst(defenderTypes) * getBasePower();
    }
    /**
     * Berechnet den endgültigen Schaden, den diese Attacke einem Gegner zufügt.
     * Dabei werden Basisstärke, Zusatzeffekte und Typ-Effizienz berücksichtigt.
     * @param attacker Omimon Angreifer.
     * @param defenderTypes Die Typen vom Omimon Verteidiger.
     * @return Endgültiger Schaden als int.
     */
    @Override
    public int calculateFinalDamage(OmimonInstance attacker, Types[] defenderTypes) {
        System.out.println("────────────────────────────────────────");
        System.out.println("Attack: "  + name);
        System.out.println("- Base Power: " + basePower);


        int finaldamage = applyBoosters(attacker);

        double eff = calculateEfficiencyAgainst(defenderTypes);

        String sb = "- Type Efficency: [" + attackType + "] vs " +
                Arrays.toString(defenderTypes) + " -> " + eff + 'x';
        System.out.println(sb);

        finaldamage = (int) (finaldamage * eff);
        System.out.println("- Final Damage: " + finaldamage);
        return finaldamage;
    }
    /**
     * Wendet alle Attackeneffekte an, die den Schaden modifizieren können.
     * Jeder Effekt kann den Schaden erhöhen, verringern oder verändern.
     * @param attacker Angreifer.
     * @return Angepasster Schaden nach Effekten.
     */
    private int applyBoosters(OmimonInstance attacker) {
        if(attackEffects.isEmpty()) return basePower;
        int damage = basePower;

        for (AttackEffect booster : attackEffects) {
            damage = booster.modifyDamage(damage, attacker);
        }
        return damage;
    }
    //getter
    public String getName() {
        return name;
    }

    public int getBasePower() {
        return basePower;
    }
    @Override
    public String toString() {
        return
                name +
                ", type: " + attackType +
                ", power: " + basePower;
    }
    /**
     * Builder-Klasse zum Erzeugen von Attacken mit konfigurierbaren Feldern.
     */
    public static class AttackBuilder {
        private String name;
        private Types attackType;
        private int basePower;
        private ArrayList<AttackEffect> attackEffects = new ArrayList<>();
        public AttackBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AttackBuilder setAttackType(Types attackType) {
            this.attackType = attackType;
            return this;
        }
        public AttackBuilder addEffect(AttackEffect attackEffect) {
            if(attackEffects.size() < 3) {
                attackEffects.add(attackEffect);
            }
            return this;
        }
        public AttackBuilder setBasePower(int basePower) {
            if (basePower < 1 || basePower > 255) {
                throw new IllegalArgumentException("Power must be between 1 and 255");
            }
            this.basePower = basePower;
            return this;
        }
        public Attack build() {
            if (name == null || attackType == null || basePower == 0) {
                throw new IllegalStateException("Name, attackType, and power must be set.");
            }
            return new Attack(this);
        }
        public AttackBuilder reset(){
             this.name = null;
             this.attackType = null;
             this.basePower = 0;
             this.attackEffects = new ArrayList<>();
             return this;
        }
    }
}