package omi_mons.strategies.attackStrategies;

import omi_mons.attack.Attack;
import omi_mons.omidex.omimons.Types;

import java.util.List;


public class PowerStrategy implements AttackStrategy {


    @Override
    public Attack getAttack(List<Attack> attacks, Types[] types) {
        Attack attack = attacks.getFirst();
        for (Attack nextAttack : attacks) {
            if(nextAttack.getBasePower() > attack.getBasePower()) attack = nextAttack;
        }
        System.out.println(this + " - chosen: " + attack.toString());
        return attack;
    }
    @Override
    public String toString() {
        return "Attack Strategy: Powerful";
    }
}
