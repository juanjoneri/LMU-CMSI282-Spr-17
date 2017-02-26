import java.util.ArrayList;
import java.util.Hashtable;

public class SumoSolver {

    public static Hashtable<Coordinate, Cart> memo = new Hashtable<>();
    // make a hashtable behave as if it where a table using Coordinate class
    // x -> items
    // y -> money

    public static Cart getHeaviestCart(int money, ArrayList<Item> storeItems) {

        Cart heaviestCart = new Cart();

        if (money <= 0 || storeItems.size() <= 0) return heaviestCart; //return the empty cart

        int x = storeItems.size();
        int y = money + 1;

        Coordinate thisC = new Coordinate(x, y); // follow the heuristics of algorithm

        if (memo.get(thisC) != null) {
            return memo.get(thisC); // this is the best solution then
        } else {
            if (money > storeItems.get(x - 1).getCost()) {
                // we can use one instance of the last item in the store
                // copy solution for remainder of money
                heaviestCart = new Cart(getHeaviestCart(y - storeItems.get(x - 1).getCost(), storeItems));
                // add one instance of the current item
                heaviestCart.addItem(storeItems.get(x - 1));
            }

            ArrayList<Item> storeItemsWOLast = new ArrayList<>(storeItems); // check if sol w/o last item is better
            storeItemsWOLast.remove(x - 1);

            // check if the solution for the same moeny y but store without last item is better
            if (getHeaviestCart(y, storeItemsWOLast).getTotalWeight() > heaviestCart.getTotalWeight()){
                heaviestCart = new Cart(getHeaviestCart(y, storeItemsWOLast));
            };
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
            4) make a table for DP: store.size x ammount
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
                    if (itemValue < 0 || itemWeight < 0) badArguments = true; // argument is negative
                }

            } catch (NumberFormatException nfe) {
                badArguments = true; // argument is not a number
            }

        } else {
            badArguments = true; // not an odd number of arguments
        }

        if (badArguments) {
            System.out.println(getInstructions());
        } else {
            Cart heaviestCart = getHeaviestCart(money, storeItems);
            System.out.println(heaviestCart);
        }
    }
}
