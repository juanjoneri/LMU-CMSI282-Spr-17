import java.util.Hashtable;
public class SandBox {


    public static void main (String args[]) {

        Hashtable<String, Integer> memo = new Hashtable<>();
        String c = "new Coordinate(1, 1);";
        memo.put(c, 100);
        String d = "new Coordinate(1, 1);";
        System.out.println(memo.get(d));

    }



}
