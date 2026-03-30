package omi_mons.attack.additionalEffects;

import omi_mons.omidex.omimons.OmimonInstance;

public class CriticalAttack implements AttackEffect {
    private static final double critical_value = 0.1;
    /**
     * Effekttyp: Kritisch
     * <p>
     * Mit 10% Wahrscheinlichkeit der Schaden verdoppelt wird.
     */
    @Override
    public int modifyDamage(int baseDamage, OmimonInstance attacker) {
        if (Math.random() < critical_value) {
            System.out.println("- Critical hit! Damage doubled. New value: " + baseDamage * 2);
            return baseDamage * 2;
        }
        System.out.println("- NO critical hit!");
        return baseDamage;
    }
}
