package omi_mons.attack.additionalEffects;

import omi_mons.omidex.omimons.OmimonInstance;

public interface AttackEffect {
    /**
     * Verändert den Basis-Schaden einer Attacke.
     * @param baseDamage Schaden
     * @param attacker Angreifer
     * @return Modifizierter Schaden
     */
    int modifyDamage(int baseDamage, OmimonInstance attacker);
}
