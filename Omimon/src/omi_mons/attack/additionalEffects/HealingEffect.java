package omi_mons.attack.additionalEffects;

import omi_mons.omidex.omimons.OmimonInstance;


public class HealingEffect implements AttackEffect {
    /**
     * Effekttyp: Heilung
     * <p>
     * Dieser Effekt setzt die Gesundheit vom Attacker Omimon auf Maximum.
     * */

    @Override
    public int modifyDamage(int baseDamage, OmimonInstance attacker) {
        System.out.print("- Healing: ");
        attacker.healToFull();
        return baseDamage;
    }
}
