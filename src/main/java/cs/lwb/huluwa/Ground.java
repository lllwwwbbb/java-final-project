package cs.lwb.huluwa;

public class Ground {
    // width: left to right
    // height: up to down
    public final int WIDTH, HEIGHT;
    private final Creature[][] creatures;

    public Ground(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        creatures = new Creature[WIDTH][HEIGHT];
    }

    Creature getCreature(Location location) {
        int x = location.getX(), y = location.getY();
        assert x >= 0 && x < WIDTH && y >= 0 && y <= HEIGHT;
        return creatures[x][y];
    }
}
