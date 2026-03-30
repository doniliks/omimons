package omi_mons.omidex.omimons;

import java.util.Random;
/**
 * Repräsentiert einen Wertebereich mit Minimal- und Maximalwerten.
 * Wird verwendet für Attribute wie Gesundheit, Verteidigung und Geschwindigkeit eines Omimons.
 */
public class Value_Range {
    final Random random = new Random();
    private final int min;
    private final int max;
    /**
     * Konstruktor zum Erstellen eines Wertebereichs.
     *
     * @param min der minimale Wert (muss ≥ 1 und < max sein)
     * @param max der maximale Wert (muss > min und ≤ 255 sein)
     * @throws IllegalArgumentException wenn die Grenzen ungültig sind
     */
    public Value_Range(int min, int max) {
        if (min > max || min < 1 || min > 255 || max > 255) {
            throw new IllegalArgumentException("Invalid value range: " + min + " - " + max);
        }
        this.min = min;
        this.max = max;
    }

    /**
     * Gibt einen zufälligen Wert innerhalb des Wertebereichs zurück.
     *
     * @return ein zufälliger Integer zwischen {@code min} und {@code max} (inklusive)
     */
    public int getRandomValue() {
        return random.nextInt(min, max) + 1;
    }

    @Override
    public String toString() {
        return
                "min=" + min +
                ", max=" + max;
    }
}
