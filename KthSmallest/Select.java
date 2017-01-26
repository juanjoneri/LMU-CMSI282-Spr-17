import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

/**
 * Select.java: takes an arbitrary dataset of integers, with repetitions permitted, plus a positive int, k, and
 * outputs the kth smallest number in the dataset. The dataset will be a textfile on standard input, with one integer on
 * each line of the file. The value of k will be specified by args[0]. The program should output BAD DATA if it encounters
 * any problem with the dataset or with k (e.g., k is out of range).
 */

public class Select {

    private static boolean isInRange (int k, int start, int end) {
        return (k >= start && k <= end);
    }

    public static void main (String[] args) {
        boolean goodK = false, goodList = false;
        int k = -1;

        if (args.length > 0) {
            goodK = true;
            for (char c : args[0].toCharArray())
                if ((c - '0') > 9 || (c - '0') < 0) goodK = false;
        }

        FileReader fr = new FileReader();
        int[] numbers = fr.readNumbers();
        goodList = numbers.length > 0; //because readNumbers() will return empty if something's wrong

        if (goodK) {
            k = Integer.parseInt(args[0]);
            goodK = isInRange(k, 1, numbers.length);
        }

        if (goodK && goodList) {
            System.out.println(KthSmallest.kSmallest(numbers, k));
        } else {
            System.out.println("BAD DATA");
        }

	}

    // -------------------------------------------------------------------------------------------------------
    // -------------------------------------FILE READER FUNCTIONALITY-----------------------------------------
    // -------------------------------------------------------------------------------------------------------

    private static class FileReader {

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

    // -------------------------------------------------------------------------------------------------------
    // ----------------------------------------KTH SMALLEST ALGORITHM-----------------------------------------
    // -------------------------------------------------------------------------------------------------------

    //isn't really a class. It's just a holder for static constants and methods.
    private static class KthSmallest {

        /**
         * When a partition pivot happens to have copies, we need to keep track of them accoritn to kSmalles
         * logisitcs
         * we will hold the information in this DS
         */
        private static class PivotData {
            int numOfPivots;
            int posFirst;
            int pivotValue;

            public PivotData (int pivotValue, int numOfPivots, int posFirst) {
                this.numOfPivots = numOfPivots;
                this.posFirst = posFirst;
                this.pivotValue = pivotValue;
            }

            @Override
            public String toString () {
                StringBuilder name = new StringBuilder("Pivot:");
                name.append("\n- Value: ").append(pivotValue);
                name.append("\n- Number: ").append(numOfPivots);
                name.append("\n- Fist: ").append(posFirst);
                return name.toString();
            }
        }

        public static int kSmallest (int[] arr, int k) {
            return kSmallest(arr, k, 0, arr.length - 1);
        }

        private static int kSmallest (int[] arr, int k, int start, int end) {
            if ( k - 1 < 0 || k - 1 > (end - start)) return -1;

            PivotData pd = randomPartition(arr, start, end);

            if (k - 1 >= (pd.posFirst - start) && k - 1 <= ((pd.posFirst - start) + pd.numOfPivots - 1))
                return pd.pivotValue;
            else if (k - 1 > ((pd.posFirst - start) + pd.numOfPivots - 1))
                return kSmallest(arr, k - (pd.posFirst + pd.numOfPivots) + start, pd.posFirst + pd.numOfPivots, end);
            else
                return kSmallest(arr, k, start, pd.posFirst - 1);

        }

        /**
         * @return a random integer in the range [start, end)
         */
        private static int ranBetween (int start, int end) {
            return (int) ( Math.random() * (end - start) ) + start;
        }

        private static PivotData randomPartition (int[] arr, int start, int end) {
            int pivotIndex = ranBetween(start, end + 1);
            exchange(arr, pivotIndex, end);
            return partition(arr, start, end);
        }

    	private static PivotData partition (int[] arr, int start, int end) {
            int pivot = arr[end];
            if (isSmallest(arr, pivot, start, end)) {
                exchange(arr, start, end);
                return joinPivots(arr, start, start, end);
            }

            int i = start, j = end-1;

            while (i <= j) {
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;
                if (i <= j) {
                    exchange(arr, i, j);
                    i++; j--;
                }
            }

            exchange(arr, i, end);
            return joinPivots(arr, i, start, end);
    	}

        /**
        * helper method to put all copies of pivot around the pivot in the edge of the partition O(n)
        * will not increase complexity
        * @return a new PivotData with the information of the pivots in this array
        * @param pivotIdx the position of the pivot in the array
        */
        private static PivotData joinPivots (int[] arr, int pivotIdx, int start, int end) {
            if (start > pivotIdx || end < pivotIdx) return new PivotData(arr[pivotIdx], 1, start);
            int pivot = arr[pivotIdx];
            int i = pivotIdx, j = pivotIdx;
            // There migh already be some pivots next to the pivot, so we want to skip those
            while (arr[i] == pivot && i > start) i --;
            while (arr[j] == pivot && j < end) j ++;

            // swap pivots to the left of pivot with the first elemeent to the left of pivot
            for (int k = start; k < i; k++) {
                if (arr[k] == pivot) {
                    exchange(arr, k, i);
                    while (arr[i] == pivot && i > start) i --;
                }
            }
            // swap pivots to the right of pivot with the first elemeent to the right of pivot
            for (int k = end; k > j; k--) {
                if (arr[k] == pivot) {
                    exchange(arr, k, j);
                    while (arr[j] == pivot && j < end) j ++;
                }
            }
            int count = 0, pos = pivotIdx;
            for (int k = start; k <= end; k++) {
                if(arr[k] == pivot){
                    count++;
                    pos = k;
                }
            }
            return new PivotData(arr[pivotIdx], count, pos - count + 1);
        }

    	private static void exchange (int[] arr, int i, int j) {
    		int temp = arr[i];
    		arr[i] = arr[j];
    		arr[j] = temp;
    	}

        /**
         * @return if pivot is the smallest element in range start, end
         */
        private static boolean isSmallest(int[] arr, int pivot, int start, int end) {
            for (int i = start; i < end; i ++) {
                if (arr[i] < pivot) return false;
            }
            return true;
        }

    }


}
