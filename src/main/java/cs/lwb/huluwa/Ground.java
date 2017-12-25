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

    Creature getCreatureNearby(Location location, int distance, Faction faction) {
        int up = location.y - distance, bottom = location.y + distance,
                left = location.x - distance, right = location.y + distance;
        if (up < 0) up = 0;
        if (bottom >= height) bottom = height - 1;
        if (left < 0) left = 0;
        if (right >= width) right = width - 1;

        for (int i = left; i <= right; i ++) {
            for (int j = up; j <= bottom; j ++) {
                if (creatures[i][j] != null && creatures[i][j].getFaction() == faction) {
                    return creatures[i][j];
                }
            }
        }
        return null;
    }
}
