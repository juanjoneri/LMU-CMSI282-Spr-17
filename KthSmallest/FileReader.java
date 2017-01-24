import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

/**
 * this class deals with the complications of reading external lines
 * could be made internal to KthSmallest but.. it would have been to much in just one file
 * besides we might use this in the future!
 */
public class FileReader {

    private java.io.BufferedReader stdIn;

    public FileReader () {
        stdIn = new java.io.BufferedReader (new java.io.InputStreamReader(System.in));
    }

    /**
     * @return an int array holding the integers found in stdIn, or an empty array if there is a problem
     */
    public int[] readNumbers () {
        ArrayList<Integer> numbers = new ArrayList<>();
        String line = null;
        try {
            while ((line  = stdIn.readLine()) != null) {
                int toAdd = readNumber(line);
                if (toAdd < Integer.MAX_VALUE) numbers.add(toAdd);
                else return new int[] {};
            }
        } catch (IOException e) {
            System.out.println("cannot read file ");
        }
        return convertIntegers(numbers);
    }

    /**
     * @return the integer value of the string, or MAX_VALLUE in case the string is not a number
     */
    private int readNumber (String line) {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException nfe) {
            return Integer.MAX_VALUE;
        }
    }

    public static int[] convertIntegers(ArrayList<Integer> integers) {
        integers.trimToSize();
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) ret[i] = iterator.next().intValue();
        return ret;
}

}
