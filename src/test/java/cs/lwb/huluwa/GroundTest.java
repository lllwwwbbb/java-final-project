package cs.lwb.huluwa;

import cs.lwb.huluwa.creature.Laoyeye;
import cs.lwb.huluwa.creature.SheJing;
import cs.lwb.huluwa.creature.XieJing;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroundTest {
    Ground g = new Ground(10, 10);

    @Before
    public void setUp() throws Exception {
        Location loc = new Location(0,0);
        g.setCreature(loc, new Laoyeye(null, loc));
        loc = new Location(0,5);
        g.setCreature(loc, new SheJing(null, loc));
        loc = new Location(0,6);
        g.setCreature(loc, new XieJing(null , loc));
    }

    @Test
    public void getCreature() {
        assert g.getCreature(new Location(0, 0)) != null;
        assert g.getCreature(new Location(0, 5)) != null;
        assert g.getCreature(new Location(0, 6)) != null;
        assert g.getCreature(new Location(0, 7)) == null;
    }

    @Test(expected = AssertionError.class)
    public void setCreature() {
        g.setCreature(new Location(10,10), null);
    }

    @Test
    public void getCreatureNearby() {
        assert  g.getCreatureNearby(new Location(0,0), 5, Creature.Faction.BAD) != null;
        assert  g.getCreatureNearby(new Location(5,5), 5, Creature.Faction.BAD) != null;
        assert  g.getCreatureNearby(new Location(0,5), 5, Creature.Faction.GOOD) != null;
        assert  g.getCreatureNearby(new Location(5,6), 5, Creature.Faction.GOOD) == null;
    }
}