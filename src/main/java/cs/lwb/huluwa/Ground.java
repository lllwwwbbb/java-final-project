package cs.lwb.huluwa;

public class Ground {
    // width: left to right
    // height: up to down
    private final int width, height;
    private final Creature[][] creatures;

    Ground(int width, int height) {
        this.width = width;
        this.height = height;
        creatures = new Creature[width][height];
    }

    Creature getCreature(Location location) {
        int x = location.x, y = location.y;
        assert x >= 0 && x < width && y >= 0 && y <= height;
        return creatures[x][y];
    }

    void setCreature(Location location, Creature creature) {
        int x = location.x, y = location.y;
        assert x >= 0 && x < width && y >= 0 && y <= height;
        creatures[x][y] = creature;
    }
}
