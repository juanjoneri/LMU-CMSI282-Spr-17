import java.util.Arrays;
//import java.util.StringBuilder;

public class KthSmallest {

    /**
     * When a partition pivot happens to have copies, we need to keep track of them accoritn to kSmalles logisitcs
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
    * helper method to put all copies of pivot around the pivot in the edge of the partition
    * @return a new PivotData with the information of the pivots in this array
    * @param pivotIdx the position of the pivot in the array
    */
    private static PivotData joinPivots (int[] arr, int pivotIdx, int start, int end) {
        if (start > pivotIdx || end < pivotIdx) return new PivotData(arr[pivotIdx], 1, start);
        int pivot = arr[pivotIdx];

        // bubble up pivot that are below the "edge"
        for (int i = pivotIdx - 1; i >= start; i--)
            if (arr[i] == pivot) bubbleUp(arr, i);

        // bubble down pivots that are above the "edge"
        for (int j = pivotIdx + 1; j <= end; j++)
            if (arr[j] == pivot) bubbleDown(arr, j);

        int count = 0, pos = pivotIdx;
        for (int i = start; i <= end; i++) {
            if(arr[i] == pivot){
                count++;
                pos = i;
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
     * will bubble element at position idx until it finds another with the same value
     * efectively joining the copies in worst O(n)
     */
    private static void bubbleUp (int[] arr, int idx) {
        while (arr[idx+1] != arr[idx] && idx != arr.length - 1) {
            exchange(arr, idx, idx+1);
            idx++;
        }
    }

    /**
     * will bubble down element at position idx until it finds another with the same value
     */
    private static void bubbleDown (int [] arr, int idx) {
        while (arr[idx-1] != arr[idx] && idx != 0) {
            exchange(arr, idx, idx-1);
            idx--;
        }
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

	public static void main (String[] args) {
		int[] arr = new int[] {5,1,5,2,324,5,3,5,4,6,64,7,8,9};
        System.out.println(Arrays.toString(arr));
        System.out.println(kSmallest(arr, 6));
	}

}
