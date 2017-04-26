import java.util.Arrays;

public class FamilyMath {

    private static int[] nextPermutation (int[] currentPermutation) {
        int edge = currentPermutation.length - 1;

        for (int i = currentPermutation.length - 1; i > 0; i --) {
            if (currentPermutation[i] > currentPermutation[i - 1]) {
                edge = (i - 1);
                break;
            }
        }
        return reverseSuffix(swap(currentPermutation, edge + 1, findNextBigger(currentPermutation, edge)), edge);
    }

    private static int[] reverseSuffix (int[] arr, int start) {
        int[] result = arr.clone();
        int end = arr.length - 1;

        while (start < end) {
            result = swap(arr, start, end);
            start ++;
            end --;
        }

        return result;
    }

    private static int[] swap (int[] arr, int x, int y) {
        int[] result = arr.clone();
        int temp = result[x];
        result[x] = result[y];
        result[y] = temp;
        return result;
    }

    // find the position of the next biggest to the right of s, s is a position
    private static int findNextBigger (int[] arr, int s) {
        int big = s + 1;

        for (int i = s; i < arr.length; i ++) {
            // check is bigger than the small vale
            // but smaller than the previously found solution
            if (arr[i] > arr[s] && arr[i] < arr[big])
                big = i;
        }
        return big;
    }

    private static boolean isSolution (int[] permutation) {
        int[][] sides = getSides(permutation);

        int sum = add(sides[0]);
        int newSum = 0;
        for (int[] side : sides) {
            newSum = add(side);
            if (newSum != sum) return false;
            sum = newSum;
        }
        return true;
    }

    private static int add (int[] arr) {
        int result = 0;
        for (int i : arr) result += i;
        return result;
    }

    private static int[][] getSides (int[] permutation) {
        int[][] sides = new int[5][3];

        if (permutation.length != 10) return sides;

        int i = 0; // position in sides array
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
            permutation = nextPermutation(permutation);
        }
    }
}
