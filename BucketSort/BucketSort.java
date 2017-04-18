import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BucketSort {

    /**
     * Make BucketSort.java, a program that takes an arbitrary file of doubles
     * (i.e., both the amount of data and their range are arbitrary) from standard input,
     * then outputs them in ascending order, using the algorithm discussed in class.
     * Your program should read the data into a java.util.ArrayList;
     * use small ArrayLists (rather than linked lists) for the buckets;
     * and merge the buckets back into the original ArrayList before outputting the results. n
     * A typical invocation of your program might look like this: java BucketSort < FileFullOfDoubles
     */

     public static ArrayList<Double> bucketSort (double arr[], int n) {
          ArrayList<ArrayList<Double>> buckets = new ArrayList<ArrayList<Double>>(10);

          for (int i = 0; i < n; i ++) {
              buckets.add(new ArrayList<Double>());
          }

          // 2) Put array elements in different buckets
          for (int i = 0; i < n; i++) {
             int index = (int) (arr[i] * n);
             buckets.get(index).add(arr[i]);
          }

          // 3) Sort individual buckets and add to final list
          ArrayList<Double> ans = new ArrayList<>(n);
          int i = 0;

          for (ArrayList<Double> bucket : buckets) {
              if (bucket.size() >= 2) insertSort(bucket);
              for (Double d : bucket) {
                  ans.add(i++, d);
              }
          }

          return ans;
    }

    private static ArrayList<Double> insertSort (ArrayList<Double> bucket) {
        if (bucket.size() == 2) {
            if (bucket.get(0) > bucket.get(1)) swap(bucket, 0, 1);
            return bucket;
        }

        int edge = 0;
        for (int i = edge; i >= 0; i--) {
            if (bucket.get(i) > bucket.get(i+1)) swap(bucket, i, i+1);
            else break;
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

    public static void main (String[] args) {

        FileReader fr = new FileReader();
        double[] numbers = fr.readNumbers();
        boolean goodList = numbers.length > 0;

        if (goodList) {
            System.out.println(Arrays.toString(bucketSort(numbers, numbers.length).toArray()));
        } else {
            System.out.println("BAD DATA");
        }

	}
}
