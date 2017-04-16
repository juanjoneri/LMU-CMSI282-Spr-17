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

     public static ArrayList<Double> sort (double arr[], int n) {
          ArrayList<ArrayList<Double>> buckets = new ArrayList<ArrayList<Double>>(10);

          for (int i = 0; i < 10; i ++) {
              buckets.add(new ArrayList<Double>());
          }

          // 2) Put array elements in different buckets
          for (int i = 0 ; i < n; i++) {
             int index = (int) (arr[i] * 10);
             buckets.get(index).add(arr[i]);
          }

          // 3) Sort individual buckets and add to final list
          ArrayList<Double> ans = new ArrayList<>(n);
          int i = 0;

          for (ArrayList<Double> bucket : buckets) {
              Collections.sort(bucket);
              for (Double d : bucket) {
                  ans.add(i++, d);
              }
          }

          return ans;
    }

    public static void main (String[] args) {

        FileReader fr = new FileReader();
        double[] numbers = fr.readNumbers();
        boolean goodList = numbers.length > 0;

        if (goodList) {
            System.out.println(Arrays.toString(sort(numbers, numbers.length).toArray()));
        } else {
            System.out.println("BAD DATA");
        }

	}
}
