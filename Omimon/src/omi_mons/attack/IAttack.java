package omi_mons.attack;

import omi_mons.omidex.omimons.OmimonInstance;
import omi_mons.omidex.omimons.Types;

public interface IAttack {
    /**
     * Berechnet den Schaden dieser Attacke gegen ein bestimmtes Ziel.
     * @param attacker Omimon Angreifer
     * @param defenderTypes Die Typen vom Omimon Verteidiger
     * @return Der berechnete Schaden
     */
    int calculateFinalDamage(OmimonInstance attacker, Types[] defenderTypes);
    /**
     * Gibt die Basisstärke der Attacke zurück.
     */
    int getBasePower();

    /**
     * Berechnet die Effizienz gegen bestimmte Verteidiger-Typen.
     * @param defenderTypes Die Typen des Gegners
     * @return Der Effektivitätsfaktor (0.5, 1.0 oder 2.0)
     */
    double calculateEfficiencyAgainst(Types[] defenderTypes);
    /**
     * Berechnet den Schaden, ohne die Effekte anzuwenden.
     * Wird für Strategien verwendet.
     * @param defenderTypes Die Typen des Gegners
     * @return Der Effektivitätsfaktor (0.5, 1.0 oder 2.0)
     */
    int calculateExpectedDamage(Types[] defenderTypes);
}
