package omi_mons.attack.additionalEffects;

import omi_mons.omidex.omimons.OmimonInstance;

import java.util.Random;

public class MultiHitAttack implements AttackEffect {
    final Random random = new Random();
    private final static int maxMultiHit = 5;
    /**
     * Effekttyp: Folgeschlag
     * <p>
     * Die Attacke kann 1 bis 5 mal treffen.
     * Die Anzahl der Treffer wird zufällig bestimmt und multipliziert.
     * */

    @Override
    public int modifyDamage(int baseDamage, OmimonInstance attacker) {
        int hits = random.nextInt(maxMultiHit) + 1;
        System.out.println("- Multi-Strike: " + hits + " x -> " + baseDamage * hits);
        return baseDamage * hits;
    }
}
