package omi_mons.strategies.attackStrategies;

import omi_mons.attack.Attack;
import omi_mons.omidex.omimons.Types;

import java.util.List;

public class SmartAttackStrategy implements AttackStrategy {
    /**
     * Wählt aus der Liste der verfügbaren Attacken eine mit dem höchsten
     * erwarteten Schaden gegen die angegebenen Typen ohne Zusatzeffekte.
     * @param attacks Liste der verfügbaren Attacken
     * @param types Typen des gegnerischen Omimons
     * @return Attack mit dem höchsten erwarteten Schaden
     */
    public Attack getAttack(List<Attack> attacks, Types[] types) {
        // Starte mit der ersten Attacke als aktuelle beste Wahl
        Attack attack = attacks.getFirst();
        double power = attack.calculateExpectedDamage(types);

        // Durchlauf von allen Attacken und findet die mit dem höchsten Schaden
        if(attacks.size() == 1) return attack;
        for (Attack nextAttack : attacks) {
            double newPower = nextAttack.calculateExpectedDamage(types);
            if(newPower > power) {
                attack = nextAttack;
                power = newPower;
            }
        }
        System.out.println(this + " - chosen: " + attack);
        return attack;
    }
    @Override
    public String toString() {
        return "Attack Strategy: Smart";
    }
    }

