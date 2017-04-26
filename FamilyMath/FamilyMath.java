import java.util.Arrays;

public class FamilyMath {

    private static void nextPermutation (int[] arr) {
        int i = findEdge(arr);
        if (i <= 0) return; // no further permutations exist

        int j = findNextBigger(arr, i - 1);

        swap(arr, i - 1, j);

        reverseSuffix(arr, i);
    }


    private static int findEdge (int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;
        return i;
    }

    // find the position of the next biggest to the right of s, s is a position
    private static int findNextBigger (int[] arr, int s) {
        int i = arr.length - 1;
        while (arr[i] <= arr[s])
            i --;
        return i;
    }

    private static void swap (int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private static void reverseSuffix (int[] arr, int start) {
        int end = arr.length - 1;
        while (start < end) {
            swap(arr, start, end);
            start ++;
            end --;
        }
    }

    private static boolean isSolution (int[] permutation) {
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

    private static int addSide (int[] arr) {
        int result = 0;
        for (int i : arr) result += i;
        return result;
    }
    /*
    Find the highest index i such that s[i] < s[i+1]. If no such index exists, the permutation is the last permutation.
    Find the highest index j > i such that s[j] > s[i]. Such a j must exist, since i+1 is such an index.
    Swap s[i] with s[j].
    Reverse the order of all of the elements after index i till the last element.
    */


    public static void main (String[] args) {
        int[] permutation = new int[] {0,1,2};

        for (int i = 0; i < 10; i ++) {
            // if (isSolution(permutation)) break;
            System.out.println(Arrays.toString(permutation));
            nextPermutation(permutation);
        }
    }
}
