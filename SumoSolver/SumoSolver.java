import java.util.ArrayList;
import java.util.Hashtable;

public class SumoSolver {

    public static Hashtable<Coordinate, Cart> memo = new Hashtable<>();
    // make a hashtable behave as if it where a table using Coordinate class
    // x -> items
    // y -> money
    // yes, i know its confusing

    public static Cart getHeaviestCart(int money, ArrayList<Item> storeItems) {

        Cart heaviestCart = new Cart(); // start with the empy cart

        int x = storeItems.size();
        int y = money;

        if (x == 0 || y == 0) return heaviestCart; //return the empty cart because we either have no money or no items :(

        Coordinate thisC = new Coordinate(x, y);
        Item currentI = storeItems.get(x - 1);

        if (memo.get(thisC) != null) {
            // we have already computer this solution and we know it to be the best
            return memo.get(thisC);
        } else {
            if (money >= currentI.getCost()) {
                // We have enough money to use one instance of the last item in the store
                // copy solution for remainder of money
                heaviestCart = new Cart(getHeaviestCart(y - currentI.getCost(), storeItems));
                // add one instance of the current item
                heaviestCart.addItem(currentI);
            }

            // check if the solution for the same money but store without last item is better
            // solution "on top" in the table
            ArrayList<Item> storeItemsWOLast = new ArrayList<>(storeItems);
            storeItemsWOLast.remove(x - 1);
            Cart topCart = new Cart(getHeaviestCart(money, storeItemsWOLast));
            if (topCart.getTotalWeight() > heaviestCart.getTotalWeight()){
                heaviestCart = topCart;
            }

            memo.put(thisC, heaviestCart);
        }

        return heaviestCart;
    }

    public static String getInstructions () {
        String instructions  = "\n# The args must be a sequence of cost-weight pairs, followed by the maximum amount he can spend.\n"
                             + "\nFor example, *java SumoSolver 48 51 49 52 55 99 100* describes:\n"
                             + "-An instance in which there are three items, "
                             + "-The first item costs $48 and guarantees a weight gain of 51 pounds;\n"
                             + "-the second item costs $49 and guarantees a gain of 52 pounds;\n"
                             + "-the third item costs $55 and guarantees a gain of 99 pounds.\n"
                             + "-Keiryo can spend at most $100.";
        return instructions;
    }




    public static void main (String[] args) {
        /*
            1) get items in the store
            2) get ammount to be spent
            3) put them in store ArrayList
            4) make a table for DP: store.size x money
            5) fill in the table with new carts with DP:
                5.1) can I buy this item with this money?
                5.2) add the solution for the money that remains (use clone and add 0 or 1 from 5.1)
                5.3) compare with solution without this item (on top) and replace if necessary
        */

        boolean badArguments = false;
        ArrayList<Item> storeItems = new ArrayList<Item>(args.length / 2);
        int money = 0;

        if (args.length % 2 == 1){

            try {
                money = Integer.parseInt(args[args.length - 1]);
                if (money < 0) badArguments = true;

                for (int i = 0; i < args.length / 2; ++i) {
                    int itemValue = Integer.parseInt(args[2 * i]);
                    int itemWeight = Integer.parseInt(args[2 * i + 1]);

                    storeItems.add(new Item(itemValue, itemWeight));
                    if (itemValue < 0 || itemWeight < 0)
                        badArguments = true; // argument is negative
                }

            } catch (NumberFormatException nfe) {
                badArguments = true; // argument is not a number
            }

        } else
            badArguments = true; // not an odd number of arguments

        if (badArguments) {
            System.out.println(getInstructions());
        } else {
            Cart heaviestCart = getHeaviestCart(money, storeItems);
            System.out.println(heaviestCart);
        }
    }
}
