package cs.lwb.huluwa;

public enum Faction {
    GOOD, BAD;

    public Faction getOpposeFaction() {
        switch (this) {
            case BAD: return GOOD;
            case GOOD: return BAD;
            default: assert false;
        }
        assert false;
        return null;
    }
}
