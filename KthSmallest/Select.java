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

}
