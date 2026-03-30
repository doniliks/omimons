package omi_mons.omidex;

import omi_mons.strategies.actionStrategies.*;
import omi_mons.strategies.attackStrategies.*;
import omi_mons.omidex.omimons.OmimonInstance;
import omi_mons.omidex.omimons.OmimonPrototype;

import java.util.*;
/**
 * Der Generator ist eine zentrale Hilfsklasse zur Erzeugung von zufälligen OmimonInstance-Objekten,
 * sowie zur Auswahl zufälliger Aktions- und Angriffsstrategien.
 * Diese Klasse nutzt das Singleton-Pattern, um sicherzustellen, dass nur eine Instanz existiert.
 */
public class Generator {
    final Omidex omidex = Omidex.getInstance();
    private static Generator generator = null;

    final Random random = new Random();

    final ArrayList<ActionStrategy> actionStrategies = new ArrayList<>();
    final ArrayList<AttackStrategy> attackStrategies = new ArrayList<>();

    /**
     * Privater Konstruktor – Initialisiert verfügbare Strategien.
     * Wird nur einmal aufgerufen.
     */
    private Generator() {
        actionStrategies.add(new SmartStrategy());
        actionStrategies.add(new ConfidentStrategy());
        actionStrategies.add(new CautiousStrategy());
        actionStrategies.add(new AlwaysSwitch());

        attackStrategies.add(new SmartAttackStrategy());
        attackStrategies.add(new PowerStrategy());
        attackStrategies.add(new RandomStrategy());
    }
    /**
     * @return eine zufällig gewählte AttackStrategy
     */

    public AttackStrategy getRandomAttackStrategy() {
        return attackStrategies.get(random.nextInt(attackStrategies.size()));
    }

    /**
     * @return eine zufällig gewählte ActionStrategy
     */
    public ActionStrategy getRandomActionStrategy() {
        return actionStrategies.get(random.nextInt(actionStrategies.size()));
    }

    /**
     * Gibt die Singleton-Instanz des Generators zurück.
     * Erstellt eine neue Instanz, falls noch keine existiert.
     *
     * @return die Generator-Instanz
     */
    public static Generator getGenerator() {
        if (generator == null) {
            generator = new Generator();
    }
        return generator;
    }
    /**
     * Wählt zufällig einen OmimonTyp aus dem Omidex.
     * Falls der ausgewählte Typ eine Weiterentwicklung ist, wird erneut gesucht.
     *
     * @return ein zufälliger OmimonType oder null, wenn keine vorhanden sind
     * (in aktueller Implementierung nicht möglich)
     */
    private OmimonPrototype getRandomOmimonType() {
        if (omidex.getOmidexHashSet().isEmpty()) return null;
        ArrayList<OmimonPrototype> list = new ArrayList<>(omidex.getOmidexHashSet());
        Random random = new Random();
        OmimonPrototype omimonPrototype = list.get(random.nextInt(list.size()));
        if(omimonPrototype.isNextForm()) return getRandomOmimonType();
        else return omimonPrototype;
    }
    /**
     * Erstellt eine neue OmimonInstance mit einem zufälligen Typen.
     *
     * @return ein neues OmimonInstance-Objekt oder null, falls keine Typen verfügbar sind
     * (in aktueller Implementierung nicht möglich)

     */
    public  OmimonInstance getRandomOmimonInstance() {
        if(!omidex.getOmidexHashSet().isEmpty()) {
            return new OmimonInstance(Objects.requireNonNull(getRandomOmimonType()));
        }
        return null;
    }

}
