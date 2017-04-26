import java.util.Arrays;

public class FamilyMath {

    private static int[] nextPermutation (char[] currentPermutation) {
        return null;
    }

    private static boolean isSolution (int[] permutation) {
        // Check the shape is compatible with the game
        return true;
    }

    private static int[][] sides (int[] permutation) {
        int[][] sides = new int[5][3];

        if (permutation.length != 10) return sides;

        int i = 0; // position in sides
        int counter = 0; // track the %3 thing
        int side = 0; // current side
        for (int j = 0; j < (5 * 3); j ++) {
            sides[side][counter] = permutation[i];

            counter ++;
            i ++;
            if (counter == 3) {
                side ++;
                counter = 0;
                i --;
            }
            i %= 10;
        }
        return sides;
    }


    public static void main (String[] args) {
        int[] permutation = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.deepToString(sides(permutation)));
    }
}
