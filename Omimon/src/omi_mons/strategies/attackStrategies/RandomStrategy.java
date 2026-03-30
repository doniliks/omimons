package omi_mons.strategies.attackStrategies;

import omi_mons.attack.Attack;
import omi_mons.omidex.omimons.Types;


import java.util.List;
import java.util.Random;

/**
 * AttackStrategy, die zufällig eine Attacke aus der Liste auswählt.
 */
public class RandomStrategy implements AttackStrategy {
    final Random random = new Random();

    @Override
    public Attack getAttack(List<Attack> attacks, Types[] types) {
        Attack attack = attacks.get(random.nextInt(attacks.size()));
        System.out.println(this + " - chosen: " + attack.toString());
        return attack;
    }
    @Override
    public String toString() {
        return "Attack Strategy: Random";
    }
}
