import java.util.ArrayList;

public class Cart {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private ArrayList<Item> items;
    private int totalCost;
    private int totalWeight;

    public Cart (int initialCapacity) {
        this.items = new ArrayList<Item>(initialCapacity);
        this.totalCost = 0;
        this.totalWeight = 0;
    }

    public Cart () {
        this(DEFAULT_INITIAL_CAPACITY);
    }



}
