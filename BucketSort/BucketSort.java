import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

    /**
    * Make BucketSort.java, a program that takes an arbitrary file of doubles
    * (i.e., both the amount of data and their range are arbitrary) from standard input,
    * then outputs them in ascending order, using the algorithm discussed in class.
    * Your program should read the data into a java.util.ArrayList;
    * use small ArrayLists (rather than linked lists) for the buckets;
    * and merge the buckets back into the original ArrayList before outputting the results. n
    * A typical invocation of your program might look like this: java BucketSort < FileFullOfDoubles
    */

public class BucketSort {

    /**
     * Performs bucket sort on an ArrayList whose elements by evenly distributing them between 0 and 1
     *
     * @param arr the collection whose elements are to be insertion sorted
     * @param n the size of the ArrayList arr
     * @return the sorted ArrayList
     */
    public static ArrayList<Double> bucketSort (ArrayList<Double> arr) {
        int n = arr.size();
        int max = (int) Math.ceil(Collections.max(arr));
        ArrayList<ArrayList<Double>> buckets = new ArrayList<ArrayList<Double>>(n);

        // Initialize Arraylist with new Buckets
        for (int i = 0; i <= n; i ++) {
            buckets.add(new ArrayList<Double>());
        }

        // Place the doubles in their respective buckets
        for (int i = 0; i < n; i++) {
            int index = (int) (arr.get(i) * n / max); // normalize the values for numbers greater than 1
            buckets.get(index).add(arr.get(i));
        }

        // Sort the buckets in order and append them to ans
        arr.clear();
        int i = 0;

        for (ArrayList<Double> bucket : buckets) {
            if (bucket.size() >= 2) insertSort(bucket);
            for (Double d : bucket) {
                arr.add(i++, d);
            }
        }

        return arr;
    }

    /**
     * Performs insertion sort on an ArrayList in place O(n^2)
     *
     * @param bucket the collection whose elements are to be insertion sorted
     * @return the sorted arraylist
     */
    private static ArrayList<Double> insertSort (ArrayList<Double> bucket) {
        if (bucket.size() == 2) {
            if (bucket.get(0) > bucket.get(1)) swap(bucket, 0, 1);
            return bucket;
        }

        for (int edge = 0; edge < bucket.size() - 1; edge ++) {
            for (int i = edge; i >= 0; i--) {
                if (bucket.get(i) > bucket.get(i+1)) swap(bucket, i, i+1);
                else break;
            }
        }
        return bucket;
    }

    /**
     * Swaps two elemetns at position x and y form an arraylist
     *
     * @param bucket the collection whose elements are to be swaped
     * @param i the position of the element to be swaped with j
     * @param j the position of the element to be swaped with i
     */
    private static void swap (ArrayList<Double> bucket, int i, int j) {
        ArrayList<Double> l = bucket;
        l.set(i, l.set(j, l.get(i)));
    }

    /**
     * Returns a String with the elements of an ArrayList one after the other with like breaks
     *
     * @param arr the ArrayList which elements are to be stored in a String
     */
    private static String listDoubles (ArrayList<Double> arr) {
        StringBuilder ans = new StringBuilder();
        for (Double d : arr) {
            ans.append(d);
            ans.append("\n");
        }
        return ans.toString();
    }

    /**
     * Asserts that an ArrayList has its elements in ascending order, O(n)
     *
     * @param arr the ArrayList whose order is to be checked
     * @return a true or false value indicating the order of the list
     */
    private static boolean isSorted (ArrayList<Double> arr) {
        boolean sorted = true;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i+1)) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public static void main (String[] args) {
        boolean checkSolution = false;

        FileReader fr = new FileReader();
        ArrayList<Double> numbersList = fr.readNumbers();
        boolean goodList = numbersList.size() > 0;

        if (goodList) {
            ArrayList<Double> sortedDoubles = bucketSort(numbersList);

            System.out.println(listDoubles(sortedDoubles));
            if (checkSolution)
                System.out.println(isSorted(sortedDoubles) ? " ✔ Doubles are sorted" : " X Doubles are not sorted");
        } else {
            System.out.println("BAD DATA");
        }
	}
}
