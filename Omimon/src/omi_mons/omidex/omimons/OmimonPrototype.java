package omi_mons.omidex.omimons;

import omi_mons.attack.Attack;

import java.util.ArrayList;
import java.util.List;
/**
 * Repräsentiert einen Omimon-Typ mit seinen Eigenschaften, Angriffen und einer möglichen Weiterentwicklung.
 */
public class OmimonPrototype {
    private final String name;
    private final Types[] types;
    private final Value_Range health_range;
    private final Value_Range defense_range;
    private final Value_Range speed_range;
    private final List<Attack> attacks;
    private OmimonPrototype improvedOmimonPrototype;
    private final int evoLevel;
    private boolean isNextForm;
    // GETTER SETTER
    public OmimonPrototype getImprovedOmimon() {
        return improvedOmimonPrototype;
    }

    public String getName() {
        return name;
    }

    public void setImprovedOmimonType(OmimonPrototype improvedOmimonPrototype) {
        this.improvedOmimonPrototype = improvedOmimonPrototype;
    }
    public int getRandomDefenceValue() {
        return defense_range.getRandomValue();
    }
    public int getRandomSpeedValue() {
        return speed_range.getRandomValue();
    }
    public int getRandomHealthValue() {
        return health_range.getRandomValue();
    }
    public Types[] getOmimonTypes() {
        return types;
    }

    public Value_Range getHealth() {
        return health_range;
    }

    public Value_Range getDefense() {
        return defense_range;
    }

    public Value_Range getSpeed() {
        return speed_range;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setIsNextForm(boolean nextForm) {
        isNextForm = nextForm;
    }

    public int getEvoLevel() {
        return evoLevel;
    }

    public Types[] getTypes() {
        return types;
    }

    public boolean isNextForm() {
        return isNextForm;
    }

    public OmimonPrototype getImprovedOmimonType() {
        return improvedOmimonPrototype;
    }
    //ENDE GETTER SETTER
    /**
     * Konstruktor, der durch den Builder aufgerufen wird.
     */
    private OmimonPrototype(OmimonBuilder builder) {
        this.name = builder.name;
        this.types = builder.types;
        this.health_range = builder.health;
        this.defense_range = builder.defense;
        this.speed_range = builder.speed;
        this.attacks = builder.attacks;
        this.improvedOmimonPrototype = builder.improvedOmimonPrototype;
        if(improvedOmimonPrototype != null)
            improvedOmimonPrototype.setIsNextForm(true);
        this.evoLevel = builder.evoLevel;
        this.isNextForm = builder.isNextForm;
    }
    /**
     * Builder-Klasse für die flexible Erstellung eines Omimon-Typs.
     */

    public static class OmimonBuilder {
        public OmimonBuilder() {
            reset();
        }

        private String name = null;
        private boolean isNextForm;
        private Types[] types = new Types[2];
        private Value_Range health = null;
        private Value_Range defense = null;
        private Value_Range speed = null;
        private List<Attack> attacks = new ArrayList<>();
        private OmimonPrototype improvedOmimonPrototype = null;
        private int evoLevel = 0;
        public OmimonBuilder reset() {
                this.name = null;
                this.isNextForm = false;
                this.types = new Types[2];
                this.health = null;
                this.defense = null;
                this.speed = null;
                this.attacks = new ArrayList<>();
                this.improvedOmimonPrototype = null;
                this.evoLevel = 0;
            return this;
        }
        public OmimonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public OmimonBuilder setOmimonTypes(Types[] types) {
            if (types != null && types.length <= 2) {
                this.types = types;
            } else {
                throw new IllegalArgumentException("OmimonTypes must be an array of length 2 or less");
            }
            return this;
        }

        public OmimonBuilder setHealth(Value_Range health) {
            this.health = health;
            return this;
        }

        public OmimonBuilder setDefense(Value_Range defense) {
            this.defense = defense;
            return this;
        }

        public OmimonBuilder setSpeed(Value_Range speed) {
            this.speed = speed;
            return this;
        }


        public OmimonBuilder addAttack(Attack attack) {
            if (attacks.size() < 4) {
                this.attacks.add(attack);
            }
            return this;
        }

        public OmimonBuilder setImprovedOmimon(OmimonPrototype improvedOmimonPrototype) {
            this.improvedOmimonPrototype = improvedOmimonPrototype;
            return this;
        }

        public OmimonBuilder setEvoLevel(int evoLevel) {
            this.evoLevel = evoLevel;
            return this;
        }

        /**
         * Baut das Omimon-Objekt und validiert alle Eingaben.
         * @throws IllegalStateException wenn eine Bedingung nicht erfüllt ist
         */
        public OmimonPrototype build() {
            if (name == null) {
                throw new IllegalStateException("Name must be provided");
            }
            if((improvedOmimonPrototype == null && evoLevel != 0) || (improvedOmimonPrototype != null && (evoLevel <= 0 || evoLevel > 100)) || (isNextForm && this.evoLevel != 0)) {
                throw new IllegalStateException("Evolution level is invalid");
            }
            if (types == null || types.length == 0) {
                throw new IllegalStateException("OmimonTypes must be provided");
            }
            if (health == null) {
                throw new IllegalStateException("Health must be provided");
            }
            if (defense == null) {
                throw new IllegalStateException("Defense must be provided");
            }
            if (speed == null) {
                throw new IllegalStateException("Speed must be provided");
            }
            if (attacks == null || attacks.isEmpty()) {
                throw new IllegalStateException("At least one attack must be provided");
            }
            return new OmimonPrototype(this);
        }

    }
}
