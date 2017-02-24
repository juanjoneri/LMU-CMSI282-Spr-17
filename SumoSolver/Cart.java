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

    public Cart (Cart toClone) {
        // Constructs a list containing the elements of the specified collection, in the order they are returned by the collection's iterator.
        // Very nice..
        this.items = new ArrayList<>(toClone.getItems());
        this.totalCost = toClone.getTotalCost();
        this.totalWeight = toClone.getTotalWeight();
    }

    public void addItem (Item item) {
        items.add(item);
        totalCost += item.getCost();
        totalWeight += item.getWeight();
    }

    public static Cart getHeaviest (Cart c1, Cart c2) {
        return c1.getTotalWeight() > c2.getTotalWeight() ? c1 : c2;
    }

    public int getTotalCost () {
        return this.totalCost;
    }

    public int getTotalWeight () {
        return this.totalWeight;
    }

    public int getItemsCount () {
        return this.items.size();
    }

    public ArrayList<Item> getItems () {
        return this.items;
    }

}
