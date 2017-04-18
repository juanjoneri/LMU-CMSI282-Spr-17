import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

/**
 * this class deals with the complications of reading lines from stdIn
 */
public class FileReader {

    private java.io.BufferedReader stdIn;

    public FileReader () {
        stdIn = new java.io.BufferedReader (new java.io.InputStreamReader(System.in));
    }

    /**
     * @return a double array holding the doubles found in stdIn, or an empty array if there is a problem
     */
    public ArrayList<Double> readNumbers () {
        ArrayList<Double> numbers = new ArrayList<>();
        String line = null;
        try {
            while ((line  = stdIn.readLine()) != null) {
                double toAdd = readDouble(line);
                if (toAdd < Double.MAX_VALUE) numbers.add(toAdd);
                else return new ArrayList<Double>();
            }
        } catch (IOException e) {
            System.out.println("cannot read file ");
        }
        return numbers;
    }

    /**
     * @param line a String line that contains a double value to be safely converted to a double
     * @return the integer value of the string, or MAX_VALLUE in case the string is not a number
     */
    private double readDouble (String line) {
        try {
            return Double.parseDouble(line);
        } catch (NumberFormatException nfe) {
            return Double.MAX_VALUE;
        }
    }

}
