import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class FileReader {

    private java.io.BufferedReader stdIn;

    /**
     * @param file the .txt file from which to read, of course
     */
    public FileReader () {
        stdIn = new java.io.BufferedReader ( new java.io.InputStreamReader ( System.in ) );
    }

    public int[] readNumbers () {
        ArrayList<Integer> numbers = new ArrayList<>();
        String line = null;
        try {
            while ((line  = stdIn.readLine()) != null) numbers.add(readNumber(line));
        } catch (IOException e) {
            System.out.println("cannot read file ");
        }
        return convertIntegers(numbers);
    }

    private int readNumber (String line) {
        //let java figure out what to do with illegal arguments
        return Integer.parseInt(line);
    }

    public static int[] convertIntegers(ArrayList<Integer> integers) {
        integers.trimToSize();
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) ret[i] = iterator.next().intValue();
        return ret;
}

}
