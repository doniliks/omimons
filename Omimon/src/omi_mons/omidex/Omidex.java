package omi_mons.omidex;

import omi_mons.attack.additionalEffects.CriticalAttack;
import omi_mons.attack.additionalEffects.HealingEffect;
import omi_mons.attack.additionalEffects.MultiHitAttack;
import omi_mons.attack.Attack;
import omi_mons.omidex.omimons.OmimonPrototype;
import omi_mons.omidex.omimons.Types;
import omi_mons.omidex.omimons.Value_Range;

import java.util.*;
/**
 * Die Klasse Omidex ist ein Singleton, das als zentrale Datenbank
 * für alle Omimons und Attacken im Spiel dient.
 * <p>
 * Sie initialisiert eine Sammlung von Omimon-Typen
 * mit unterschiedlichen Attributen, Angriffen und Weiterentwicklungen.
 * <p>
 * Die Attacken und Omimons werden zufällig mit Werten aus bestimmten Bereichen versehen,
 * um Variation in den Spielcharakteren zu ermöglichen.
 */
public class Omidex {
    private static Omidex instance = null;
    private static final HashSet<OmimonPrototype> omidex = new HashSet<>();
    private static final ArrayList<Attack> attacks = new ArrayList<>();
    final OmimonPrototype.OmimonBuilder omimonBuilder = new OmimonPrototype.OmimonBuilder();
    /**
     * Privater Konstruktor, der die Omidex initialisiert und
     * vordefinierte Omimon-Typen und Attacken erzeugt.
     * Die Werte für Attribute wie Gesundheit, Verteidigung und Geschwindigkeit
     * werden zufällig innerhalb vorgegebener Bereiche generiert.
     */
    private Omidex() {
        Attack.AttackBuilder attackBuilder = new Attack.AttackBuilder();
        Random random = new Random();
        Attack bonk =
                attackBuilder.reset()
                        .setName("Bonk")
                        .setAttackType(Types.WATER)
                        .setAttackType(Types.FIRE)
                        .setBasePower(random.nextInt(5, 10))
                        .addEffect(new CriticalAttack())
                        .addEffect(new HealingEffect())
                        .addEffect(new CriticalAttack())
                        .build()
                ;
        attacks.add(bonk);
        Attack flamethrower =
                attackBuilder.reset()
                        .setName("Flamethrower")
                        .setAttackType(Types.FIRE)
                        .setAttackType(Types.PLANT)
                        .setBasePower(random.nextInt(5, 10))
                        .addEffect(new CriticalAttack())
                        .addEffect(new MultiHitAttack())
                        .addEffect(new MultiHitAttack())
                        .build();
        attacks.add(flamethrower);
        Attack leafStrike = attackBuilder.reset()
                .setName("Leaf Strike")
                .setAttackType(Types.PLANT)
                .setAttackType(Types.WATER)
                .setBasePower(random.nextInt(5,10))
                .addEffect(new CriticalAttack())
                .addEffect(new HealingEffect())
                .addEffect(new MultiHitAttack())
                .build();
        attacks.add(leafStrike);
        Attack tackle = attackBuilder.reset()
                .setName("Tackle")
                .addEffect(new CriticalAttack())
                .addEffect(new HealingEffect())
                .addEffect(new CriticalAttack())
                .setAttackType(Types.NORMAL)
                .setBasePower(random.nextInt(5, 10))
                .build();
        attacks.add(tackle);
        OmimonPrototype Pokemon = omimonBuilder.reset()
                .addAttack(bonk)
                .addAttack(leafStrike)
                .setSpeed(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setName("Pokemon")
                .setHealth(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .setOmimonTypes(new Types[]{Types.WATER})
                .build();
        omidex.add(Pokemon);
        OmimonPrototype Bumanda = omimonBuilder.reset()
                .addAttack(flamethrower)
                .setSpeed(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setName("Bumanda")
                .setHealth(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .setOmimonTypes(new Types[]{Types.FIRE})
                .build();
        omidex.add(Bumanda);
        OmimonPrototype flamog = omimonBuilder.reset()
                .setName("Flamog")
                .setOmimonTypes(new Types[]{Types.FIRE})
                .setHealth(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .addAttack(flamethrower)
                .addAttack(tackle)
                .build();
        omidex.add(flamog);
        OmimonPrototype plantaur = omimonBuilder.reset()
                .setName("Plantaur")
                .setOmimonTypes(new Types[]{Types.FIRE})
                .setHealth(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .addAttack(leafStrike)
                .addAttack(bonk)
                .build();
        omidex.add(plantaur);
        OmimonPrototype aquanix = omimonBuilder.reset()
                .setName("Aquanix")
                .setOmimonTypes(new Types[]{Types.WATER})
                .setHealth(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .addAttack(tackle)
                .build();
        omidex.add(aquanix);
        OmimonPrototype normex = omimonBuilder.reset()
                .setName("Normex")
                .setOmimonTypes(new Types[]{Types.PLANT})
                .setHealth(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .addAttack(tackle)
                .addAttack(leafStrike)
                .build();
        omidex.add(normex);
        OmimonPrototype volgadra = omimonBuilder.reset()
                .setName("Volgadra")
                .setOmimonTypes(new Types[]{Types.FIRE, Types.WATER})
                .setHealth(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(1, 15), random.nextInt(15, 30)))
                .addAttack(flamethrower)
                .addAttack(bonk)
                .build();
        omidex.add(volgadra);
        // Embiflor ist die Weiterentwicklung von Embi
        OmimonPrototype embiflor = omimonBuilder.reset()
                .setName("Embiflor")
                .setOmimonTypes(new Types[]{Types.PLANT, Types.WATER})
                .setHealth(new Value_Range(random.nextInt(20, 30), random.nextInt(30, 40)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(15, 20), random.nextInt(25, 30)))
                .addAttack(leafStrike)
                .addAttack(bonk)
                .build();
        omidex.add(embiflor);
        // Embi zeigt auf seine Weiterentwicklung
        OmimonPrototype embi = omimonBuilder.reset()
                .setName("Embi")
                .setOmimonTypes(new Types[]{Types.PLANT})
                .setHealth(new Value_Range(random.nextInt(10, 20), random.nextInt(20, 30)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(10, 15), random.nextInt(20, 25)))
                .addAttack(leafStrike)
                .addAttack(tackle)
                .setEvoLevel(1)
                .setImprovedOmimon(embiflor)
                .build();
        omidex.add(embi);
        // Spargon ist die Weiterentwicklung
        OmimonPrototype spargon = omimonBuilder.reset()
                .setName("Spargon")
                .setOmimonTypes(new Types[]{Types.FIRE, Types.PLANT})
                .setHealth(new Value_Range(random.nextInt(20, 30), random.nextInt(30, 40)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(20, 25), random.nextInt(25, 30)))
                .addAttack(flamethrower)
                .addAttack(leafStrike)
                .build();
        omidex.add(spargon);
        // Sparki zeigt auf Spargon
        OmimonPrototype sparki = omimonBuilder.reset()
                .setName("Sparki")
                .setOmimonTypes(new Types[]{Types.FIRE})
                .setHealth(new Value_Range(random.nextInt(5, 15), random.nextInt(15, 25)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(15, 25), random.nextInt(25, 30)))
                .addAttack(flamethrower)
                .addAttack(tackle)
                .setEvoLevel(1)
                .setImprovedOmimon(spargon)
                .build();
        omidex.add(sparki);
        // Aquarion ist die Weiterentwicklung
        OmimonPrototype aquarion = omimonBuilder.reset()
                .setName("Aquarion")
                .setOmimonTypes(new Types[]{Types.WATER, Types.FIRE})
                .setHealth(new Value_Range(random.nextInt(18, 26), random.nextInt(28, 36)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(18, 24), random.nextInt(25, 30)))
                .addAttack(flamethrower)
                .addAttack(bonk)
                .build();
        omidex.add(aquarion);
        // Aquapp zeigt auf Aquarion
        OmimonPrototype aquapp = omimonBuilder.reset()
                .setName("Aquapp")
                .setOmimonTypes(new Types[]{Types.WATER})
                .setHealth(new Value_Range(random.nextInt(8, 14), random.nextInt(18, 26)))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(random.nextInt(10, 18), random.nextInt(20, 28)))
                .addAttack(tackle)
                .setEvoLevel(1)
                .setImprovedOmimon(aquarion)
                .build();
        omidex.add(aquapp);
        OmimonPrototype draglare = omimonBuilder.reset()
                .setName("Draglare")
                .setOmimonTypes(new Types[]{Types.FIRE})
                .setHealth(new Value_Range(25, 40))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(20, 30))
                .addAttack(flamethrower)
                .addAttack(bonk)
                .build();
        omidex.add(draglare);
        OmimonPrototype flamix = omimonBuilder.reset()
                .setName("Flamix")
                .setOmimonTypes(new Types[]{Types.FIRE})
                .setHealth(new Value_Range(10, 25))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(15, 28))
                .addAttack(tackle)
                .addAttack(flamethrower)
                .setEvoLevel(1)
                .setImprovedOmimon(draglare)
                .build();
        omidex.add(flamix);
        OmimonPrototype hydravent = omimonBuilder.reset()
                .setName("Hydravent")
                .setOmimonTypes(new Types[]{Types.WATER})
                .setHealth(new Value_Range(35, 45))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(25, 35))
                .addAttack(flamethrower)
                .addAttack(bonk)
                .build();
        omidex.add(hydravent);
        OmimonPrototype bubbloon = omimonBuilder.reset()
                .setName("Bubbloon")
                .setOmimonTypes(new Types[]{Types.WATER, Types.NORMAL})
                .setHealth(new Value_Range(20, 30))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(18, 28))
                .addAttack(leafStrike)
                .addAttack(flamethrower)
                .setEvoLevel(2)
                .setImprovedOmimon(hydravent)
                .build();
        omidex.add(bubbloon);
        OmimonPrototype aquabub = omimonBuilder.reset()
                .setName("Aquabub")
                .setOmimonTypes(new Types[]{Types.WATER})
                .setHealth(new Value_Range(10, 20))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(12, 22))
                .addAttack(tackle)
                .addAttack(leafStrike)
                .setEvoLevel(1)
                .setImprovedOmimon(bubbloon)
                .build();
        omidex.add(aquabub);
        OmimonPrototype florabeast = omimonBuilder.reset()
                .setName("Florabeast")
                .setOmimonTypes(new Types[]{Types.PLANT, Types.NORMAL})
                .setHealth(new Value_Range(25, 35))
                .setDefense(new Value_Range(random.nextInt(1, 5), random.nextInt(6, 10)))
                .setSpeed(new Value_Range(18, 26))
                .addAttack(leafStrike)
                .addAttack(bonk)
                .build();
        omidex.add(florabeast);

    }
    /**
     * Liefert die Singleton-Instanz der Omidex.
     * Wenn noch keine Instanz existiert, wird sie erzeugt.
     *
     * @return Die einzige Instanz von Omidex.
     */
    public static Omidex getInstance() {
        if(instance == null) {
            instance = new Omidex();
        }
        return instance;
    }


    public void printAll() {
        System.out.println("Omidex contains " + omidex.size() + " omimons.");
        for (OmimonPrototype omimonPrototype : omidex) {
            System.out.println(omimonPrototype);
        }
    }
    /**
     * Liefert die HashSet-Sammlung aller Omimon-Typen im Omidex.
     *
     * @return Menge aller OmimonType-Objekte.
     */
    public HashSet<OmimonPrototype> getOmidexHashSet() {
        return omidex;
    }

    /**
     * Liefert die Liste aller Attacken, die im Omidex definiert sind.
     *
     * @return Liste aller Attack-Objekte.
     */
    public ArrayList<Attack> getAttacks() {
        return attacks;
    }
}
