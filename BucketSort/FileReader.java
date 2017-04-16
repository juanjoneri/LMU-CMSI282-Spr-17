import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

/**
 * this class deals with the complications of reading lines from stdIn
 * could be made internal to KthSmallest but.. it would have been to much in just one file
 * besides we might use this in the future!
 */
public class FileReader {

    private java.io.BufferedReader stdIn;

    public FileReader () {
        stdIn = new java.io.BufferedReader (new java.io.InputStreamReader(System.in));
    }

    /**
     * @return a double array holding the doubles found in stdIn, or an empty array if there is a problem
     */
    public double[] readNumbers () {
        ArrayList<Double> numbers = new ArrayList<>();
        String line = null;
        try {
            while ((line  = stdIn.readLine()) != null) {
                double toAdd = readNumber(line);
                if (toAdd < Double.MAX_VALUE) numbers.add(toAdd);
                else return new double[] {};
            }
        } catch (IOException e) {
            System.out.println("cannot read file ");
        }
        return convertDoubles(numbers);
    }

    /**
     * @return the integer value of the string, or MAX_VALLUE in case the string is not a number
     */
    private double readNumber (String line) {
        try {
            return Double.parseDouble(line);
        } catch (NumberFormatException nfe) {
            return Double.MAX_VALUE;
        }
    }

    public static double[] convertDoubles(ArrayList<Double> doubles) {
        doubles.trimToSize();
        double[] ret = new double[doubles.size()];
        Iterator<Double> iterator = doubles.iterator();
        for (int i = 0; i < ret.length; i++)
            ret[i] = iterator.next().doubleValue();
        return ret;
    }

}
