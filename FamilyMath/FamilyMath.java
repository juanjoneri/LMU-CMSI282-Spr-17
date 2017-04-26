import java.util.Arrays;

public class FamilyMath {

    /**
     * Find the highest index i such that s[i] < s[i+1]. If no such index exists, the permutation is the last permutation.
     * Find the highest index j > i such that s[j] > s[i]. Such a j must exist, since i+1 is such an index.
     * Swap s[i] with s[j].
     * Reverse the order of all of the elements after index i till the last element.
     *
     * @param arr the collection whose elements are to be permuted
     */
    private static void nextPermutation (int[] arr) {
        int i = findEdge(arr);
        if (i <= 0) return; // no further permutations exist
        int j = findNextBigger(arr, i - 1);
        swap(arr, i - 1, j);
        reverseSuffix(arr, i);
    }

    /**
     * Find the highest index i such that s[i] < s[i+1]. If no such index exists, the permutation is the last permutation.
     *
     * @param arr the collection of which we want to know the edge
     * @return the position of the first element that satisfies the above
     */
    private static int findEdge (int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;
        return i;
    }

    /**
     * Find the highest index j > i such that s[j] > s[i]. Such a j must exist, since i+1 is such an index.
     *
     * @param arr the collection of which we want to know the edge
     * @param s position of the element from which we need fing the next bigger
     * @return the position of the element that satisfies the above
     */
    private static int findNextBigger (int[] arr, int s) {
        int i = arr.length - 1;
        while (arr[i] <= arr[s])
            i --;
        return i;
    }

    /**
     * Swap two elements of an array in place
     *
     * @param arr the collection from which we need to swap two elements
     * @param x position of the element to be swaped with y
     * @param y position of the element to be swaped with x
     */
    private static void swap (int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /**
     * Reverse the order of all of the elements after index i till the last element.
     *
     * @param arr the collection that needs to be reversed after start
     * @param start position of the element after which we need to reverse the arrary
     */
    private static void reverseSuffix (int[] arr, int start) {
        int end = arr.length - 1;
        while (start < end) {
            swap(arr, start, end);
            start ++;
            end --;
        }
    }

    /**
     * Applies the rules of family math game to check if the current permutation is a valid solution to the game
     *
     * @param permutation an array with the current permutation that might be a solution
     * @return whether this permutation follows the rules or the game and is indeed a solution
     */
    private static boolean isSolution (int[] permutation) {
        if (!hasDigits(permutation)) return false;

        int[][] sides = getSides(permutation);

        int sum = addSide(sides[0]);
        int newSum = 0;
        for (int[] side : sides) {
            newSum = addSide(side);
            if (newSum != sum) return false;
            sum = newSum;
        }
        return true;
    }

    /**
     * Checks if the array has all the digits from 0 to 9 inclusive. For use inside isSolution. This makes the solution slower but more accurate
     *
     * @param arr an array that might or might not have all the digits form 0 to 9
     * @return whether this array has all the digits
     */
    private static boolean hasDigits (int[] arr) {
        outer: for (int i = 0; i < 10; i ++) {
            for (int j : arr) {
                if (j == i) continue outer;
            }
            return false;
        }
        return true;
    }

    /**
     * Reshapes a solution array to match a pentagon-like configuration that is easier to do math with
     *
     * @param permutation an array with the current permutation in a flat, 1 dimensional array
     * @return a 2 dimensional array representing the 5 sides of the pentagon, each with 3 slots with numbers
     */
    private static int[][] getSides (int[] permutation) {
        int[][] sides = new int[5][3];

        if (permutation.length != 10) return sides;

        int i = 0; // position in sides arr
        int cell = 0; // current cell in the pentagon
        int side = 0; // current side of the pentagon

        // iterate over 15 elements to fill in the pentagon
        for (int j = 0; j < (5 * 3); j ++) {
            sides[side][cell] = permutation[i];

            cell ++;
            i ++;
            if (cell == 3) {
                side ++;
                cell = 0;
                i --;
            }
            i %= 10;
        }
        return sides;
    }

    /**
     * Adds the numbers in one side of the pentagon if used with an array of 3 elemnts
     *
     * @param arr in general, this method will return the sum of the elements of an int array
     * @return the value of the sum of the elemnts of arr
     */
    private static int addSide (int[] arr) {
        int result = 0;
        for (int i : arr) result += i;
        return result;
    }

    public static void main (String[] args) {
        int[] permutation = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < 3_628_800; i ++) {
            if (isSolution(permutation))
                System.out.println(Arrays.toString(permutation));
            nextPermutation(permutation);
        }
    }
}
