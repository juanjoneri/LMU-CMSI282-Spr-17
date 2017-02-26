import java.util.Arrays;
public class SumoSolver {

    public static Cart heaviestCart(int totalMoney, Item[] items) {

        Cart[][] cartGrid = new Cart[items.length+1][totalMoney+1];
        for (int i = 0; i <= items.length; i++) {
            for (int j = 0; j <= totalMoney; j++) {
                if (i == 0)
                    cartGrid[i][j] = new Cart();
                else if (j == 0)
                    cartGrid[i][j] = new Cart();
                else {
                    if (items[i - 1].getCost() <= j) {
                        int remaining = j - items[i-1].getCost();
                        cartGrid[i][j] = new Cart(cartGrid[i][remaining]);
                        cartGrid[i][j].addItem(items[i-1]);
                        cartGrid[i][j] = Cart.getHeaviest(cartGrid[i][j], cartGrid[i - 1][j]);
                    } else {
                        cartGrid[i][j] = cartGrid[i - 1][j];
                    }
                }
            }
        }
        //System.out.println(Arrays.deepToString(cartGrid));
        return cartGrid[items.length][totalMoney];
    }

    public static String getInstructions () {
        String instructions  = "\n# The args must be a sequence of cost-weight pairs, followed by the maximum amount he can spend.\n"
                             + "\nFor example, *java SumoSolver 48 51 49 52 55 99 100* describes:\n"
                             + "-An instance in which there are three items, "
                             + "-The first item costs $48 and guarantees a weight gain of 51 pounds;\n-the second item costs $49 and guarantees a gain of 52 pounds;\n-the third item costs $55 and guarantees a gain of 99 pounds.\n"
                             + "-Keiryo can spend at most $100.";
        return instructions;
    }




    public static void main (String[] args) {
        /*
            1) get items in the store
            2) get ammount to be spent
            3) put them in store ArrayLis
            4) make a table for DP: store.size x ammount
            5) fill in the table with new carts with DP:
                5.1) can I buy this item with this money?
                5.2) add the solution for the money that remains (use clone and add 0 or 1 from 5.1)
                5.3) compare with solution without this item (on top) and replace if necessary
        */

        if (args.length % 2 == 1){
            Item[] items = new Item[args.length / 2];
            int money = 0;

            try {
                money = Integer.parseInt(args[args.length - 1]);
                for (int i = 0; i < items.length; ++i) {
                    items[i] = new Item(Integer.parseInt(args[2*i]), Integer.parseInt(args[2*i+1]));
                    //Check if it is negative (DO)
                }
            }catch (NumberFormatException nfe) {
                System.out.println("Supplied values must be positive integers.");
            }
            Cart myCart = heaviestCart(money, items);
            System.out.println(myCart);

        } else {
            System.out.println(getInstructions());
        }
    }
}
