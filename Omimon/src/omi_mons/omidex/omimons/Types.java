package omi_mons.omidex.omimons;

public enum Types {
    FIRE, WATER, PLANT, NORMAL;
    public double getEfficiency(Types[] types) {
        if(this == NORMAL) return 1.0;
        double efficiency = 1.0;
            for (Types type : types) {
                switch (this) {
                    case FIRE:
                        if (type == PLANT) efficiency *= 2.0;
                        if (type == Types.WATER) efficiency *= 0.5;
                        break;
                    case WATER:
                        if (type == Types.FIRE) efficiency *= 2.0;
                        if (type == Types.PLANT) efficiency *= 0.5;
                        break;
                    case PLANT:
                        if (type == Types.WATER) efficiency *= 2.0;
                        if (type == Types.FIRE) efficiency *= 0.5;
                        break;
                }
            }
        return efficiency;
    }
}
