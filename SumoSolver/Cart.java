import java.util.ArrayList;

public class Cart implements Clonable{

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

    // Not sure but i might need to clone in table memo
    // public Cart (Cart toClone) {
    //     this (toClone.getItemsCount, );
    // }

    public void addItem (Item item) {
        items.add(item);
        totalCost += item.getCost();
        totalWeight += item.getWeight();
    }

    public int getTotalCost () {
        return this.totalCost;
    }

    public int getTotalWeight () {
        return this.totalWeight;
    }

    public int getItemsCount () {
        return items.size();
    }



}
