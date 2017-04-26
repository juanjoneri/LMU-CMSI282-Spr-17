import java.util.Arrays;

public class FamilyMath {

    private static int[] nextPermutation (char[] currentPermutation) {
        return null;
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


    public static void main (String[] args) {
        int[] permutation = new int[] {1,0,2,0,1,1,1,1,1,1};
        System.out.println(Arrays.deepToString(getSides(permutation)));
        System.out.println(isSolution(permutation));
    }
}
