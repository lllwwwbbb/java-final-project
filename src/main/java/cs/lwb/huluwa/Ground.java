package cs.lwb.huluwa;

public class Ground {
    // width: left to right
    // height: up to down
    public final int WIDTH, HEIGHT;
    private final Position[][] positions;

    public Ground(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        positions = new Position[WIDTH][HEIGHT];
    }
}
