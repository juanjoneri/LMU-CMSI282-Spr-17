/**
 * The dice game Crown and Anchor will finally be described in class on 4/18! Determine whether the game favors the house,
 * the player, or neither, and by what percentage. You must:
 * Solve the problem analytically (i.e., give some argument that talks about the game's probabilities).
 * Make program CrownAndAnchor.java which answers the question using Monte Carlo simulation (of, say, ten million games).
 */

public class CrownAnchor {

    private static final String[] SUITS = {"Heats", "Spade", "Club", "Diamond", "Crown", "Anchor"};
    private static final int TRHOWS = 3;
    private static final int ROUNDS = 1_000_000;

    public static int round (String chosenSuit, int bet) {
        int score = 0;

        for (int i = 0; i < TRHOWS; i ++) {
            String die = randomSuit(SUITS);
            if (die.equals(chosenSuit)) score ++;
        }

        return score == 0 ? 0 : (score + 1) * bet;
    }

    public static int game (int rounds) {
        int bet = 1;
        int money = 0;

        for (int round = 0; round < rounds; round ++) {
            money -= bet;
            money += round(randomSuit(SUITS), bet);
        }

        return money;
    }

    private static int random (int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static String randomSuit (String[] SUITS) {
        return SUITS[random(0, SUITS.length)];
    }

    public static void main (String[] args) {

        int earnedMoney = game(ROUNDS);

        System.out.printf("# After " + ROUNDS + " rounds you ");
        System.out.printf(earnedMoney > 0 ? "Won" : "Lost");
        System.out.println("\n$" + earnedMoney);

        System.out.println("\n\n----\n");

        System.out.printf(earnedMoney > 0 ? "# Win" : "# Loss");
        System.out.printf(" rate\n");
        System.out.println(percentage + "%");
    }

}
