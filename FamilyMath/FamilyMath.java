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
        int[] permutation = new int[] {9,8,7,6,5,4,3,2,1,0};
        System.out.println(Arrays.deepToString(sides(permutation)));
    }
}
