package cs.lwb.huluwa;

public class Location implements Comparable<Location>{
    public final int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location getNext(int xOff, int yOff) {
        return new Location(x + xOff, y + yOff);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public int compareTo(Location o) {
        if (x == o.x)
            return y - o.y;
        else
            return x - o.x;
    }
}

