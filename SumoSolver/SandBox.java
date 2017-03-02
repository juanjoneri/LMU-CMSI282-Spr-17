import java.util.Hashtable;
public class SandBox {

    public static void main (String args[]) {
        Hashtable<Coordinate, Integer> memo = new Hashtable<>();
        Coordinate c = new Coordinate(1, 1);
        memo.put(c, 100);
        Coordinate d = new Coordinate(1, 1);
        System.out.println(memo.get(d));

    }



}
