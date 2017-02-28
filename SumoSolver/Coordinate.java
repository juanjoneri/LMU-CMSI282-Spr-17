public class Coordinate {
    public int x, y;

    public Coordinate (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString () {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

}
