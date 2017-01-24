import java.util.Arrays;
import java.io.*;

public class SandBox {

    public static void main (String[] args) {
        FileReader fr = new FileReader();
        int[] numbers = fr.readNumbers();
        System.out.println(Arrays.toString(numbers));
    }


}
