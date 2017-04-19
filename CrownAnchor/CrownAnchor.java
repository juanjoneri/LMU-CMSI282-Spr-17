/**
 * The dice game Crown and Anchor will finally be described in class on 4/18! Determine whether the game favors the house,
 * the player, or neither, and by what percentage. You must:
 * Solve the problem analytically (i.e., give some argument that talks about the game's probabilities).
 * Make program CrownAndAnchor.java which answers the question using Monte Carlo simulation (of, say, ten million games).
 */

public class CrownAnchor {

    private static final String[] suits = {"Heats", "Spade", "Club", "Diamond", "Crown", "Anchor"};

    public static int round (String suit, int bet) {
        int score = 0;

        for (int i = 0; i < 3; i ++) {
            String die = randomSuit(suits);
            if (die.equals(suit)) score ++;
        }

        if (score == 0) return 0;
        else return (score + 1) * (bet);
    }

    public static int game (int rounds) {
        int bet = 1;
        int money = 0;

        for (int round = 0; round < rounds; round ++) {
            money -= bet;
            money += round(randomSuit(suits), bet);
        }

        return money;
    }

    private static int random (int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static String randomSuit (String[] suits) {
        return suits[random(0, suits.length)];
    }

    public static void main (String[] args) {

        int earnedMoney = game(1_000_000);
        System.out.println("$" + earnedMoney);
        System.out.println(earnedMoney > 0 ? "Won Money" : "Lost Money");
    }

}
