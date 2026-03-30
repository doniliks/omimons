package omi_mons;

import omi_mons.battle.Battle;
import omi_mons.omidex.Generator;
import omi_mons.trainer.trainer.Trainer;
import omi_mons.trainer.trainer.Gender;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();

        Generator generator = Generator.getGenerator();

        Trainer t = new Trainer("Danylo", Gender.M);
        t.setActionStrategy(generator.getRandomActionStrategy());
        t.setAttackStrategy(generator.getRandomAttackStrategy());

        Trainer t2 = new Trainer("NotMe", Gender.D);
        t2.setActionStrategy(generator.getRandomActionStrategy());
        t2.setAttackStrategy(generator.getRandomAttackStrategy());

        t.catchOmimon();
        t.addRandomOmimon();

        t2.catchOmimon();
        t2.addRandomOmimon();

        b.battle(t, t2);

    }

}