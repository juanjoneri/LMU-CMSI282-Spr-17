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

    public boolean addItem (Item item) {
        for (Item i : this.items) {
            if (i.getId() == item.getId()) return false;
        }
        items.add(item);
        totalCost += item.getCost();
        totalWeight += item.getWeight();
        return true;
    }

    public void addItem (int cost, int weight) {
        Item toAdd = new Item(cost, weight);
        this.addItem (toAdd);
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

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        for (Item i : items) {
            sb.append(i.toString());
            sb.append("\n");
        }
        sb.append(getItemsCount());
        sb.append(" item(s) / ");
        sb.append("$");
        sb.append(this.totalCost);
        sb.append(" / ");
        sb.append(this.totalWeight);
        sb.append(" pounds");
        return sb.toString();
    }

}
